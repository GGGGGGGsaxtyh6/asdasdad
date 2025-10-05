#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>
#include <string.h>
#include <unistd.h>
#include <errno.h>
#include <ctype.h>
#include <regex.h>
#include <pthread.h>
#include <netinet/in.h>
#include <sys/socket.h>
#include <sys/stat.h>
#include <sys/types.h>
#include <time.h>
#include <stdint.h>

#define PORT                  1337
#define MAX_CONN              10
#define MAX_REQ_SIZE          ((int)10e5)
#define MAX_RESP_SIZE         ((int)10e6)
#define MAX_METHOD_SIZE       16
#define MAX_ROUTE_SIZE        256
#define MAX_HTTP_VERSION_SIZE 16
#define MAX_FILE_EXT_SIZE     16
#define MAX_MIME_TYPE_SIZE    32
#define MAX_STATUS_SIZE       16
#define MAX_HEADER_SIZE       128
#define error_exit(msg)       { perror(msg); exit(EXIT_FAILURE); }
#define DEFAULT_ROUTE         "index.html"
#define EXT_LIST_SIZE         7
#define EXT_LIST              { "html", "htm", "txt", "jpg", "jpeg", "png", "pdf" }
#define BAD_REQ_STATUS        "400 Bad Request"
#define NOT_FOUND_STATUS      "404 Not Found"
#define STATS_ROOT_DIR        "stats"
#define STATS_FILE_NAME       "stats.txt"
#define RANDOM_SUFFIX_LEN     8

typedef struct {
  char request[MAX_REQ_SIZE];
  char method[MAX_METHOD_SIZE];
  char route[MAX_ROUTE_SIZE];
  char http_version[MAX_HTTP_VERSION_SIZE];
  char filepath[MAX_ROUTE_SIZE];
  char file_extension[MAX_FILE_EXT_SIZE];
  char host_header[MAX_HEADER_SIZE];
  char user_agent_header[MAX_HEADER_SIZE];
  char accept_header[MAX_HEADER_SIZE];
  char accept_language_header[MAX_HEADER_SIZE];
  char accept_encoding_header[MAX_HEADER_SIZE];
  char connection_header[MAX_HEADER_SIZE];
  char status[MAX_STATUS_SIZE];
  char response[MAX_REQ_SIZE];
  char mime_type[MAX_MIME_TYPE_SIZE];
} __attribute__((packed)) ctx_t;

static void ensure_stats_root_created(void) {
  struct stat st = {0};
  if (stat(STATS_ROOT_DIR, &st) == -1) {
    if (mkdir(STATS_ROOT_DIR, 0755) == -1 && errno != EEXIST) {
      perror("mkdir stats");
    }
  }
}

static void random_hex(char *out, size_t len) {
  for (size_t i = 0; i < len; ++i) {
    uint8_t byte = rand() & 0xFF;
    sprintf(out + (i * 2), "%02x", byte);
  }
  out[len * 2] = '\0';
}

static void build_stats_dir_name(const char *filename, char *out, size_t out_size) {
  char suffix[RANDOM_SUFFIX_LEN * 2 + 1];
  random_hex(suffix, RANDOM_SUFFIX_LEN);
  snprintf(out, out_size, "%s/%s_%s", STATS_ROOT_DIR, suffix, filename);
}

static void write_request_stats(ctx_t *ctx, const char *dir_path, int client_fd) {
  char file_path[512];
  snprintf(file_path, sizeof(file_path), "%s/%s", dir_path, STATS_FILE_NAME);
  FILE *f = fopen(file_path, "w");
  if (!f) return;

  fprintf(f, "Method: %s\n", ctx->method);
  fprintf(f, "Route: %s\n", ctx->route);
  fprintf(f, "HTTP Version: %s\n", ctx->http_version);
  fprintf(f, "Status: %s\n", ctx->status[0] ? ctx->status : "(pending)");
  fprintf(f, "Host: %s\n", ctx->host_header);
  fprintf(f, "User-Agent: %s\n", ctx->user_agent_header);
  fflush(f);
  fclose(f);
}

static void remove_stats_dir(const char *dir_path) {
  char file_path[512];
  snprintf(file_path, sizeof(file_path), "%s/%s", dir_path, STATS_FILE_NAME);
  unlink(file_path);
  rmdir(dir_path);
}

void cleanup_filepath(ctx_t *ctx)
{
  while (strlen(ctx->filepath) && ctx->filepath[0] == '/') {
    memmove(ctx->filepath, ctx->filepath+1, strlen(ctx->filepath));
  }
  for (size_t i = 0; i < strlen(ctx->filepath); ++i) {
    if (ctx->filepath[i] == '.' || ctx->filepath[i] == '/' \
        || ctx->filepath[i] == '-' || ctx->filepath[i] == '_' \
        || isalnum(ctx->filepath[i])) continue;
    strcpy(ctx->filepath, DEFAULT_ROUTE);
    ctx->filepath[strlen(DEFAULT_ROUTE)] = '\0';
    break;
  }
}

void url_decode(ctx_t *ctx) {
  char *dest = ctx->filepath;
  const char *src = ctx->route;
  while (*src) {
      if (*src == '%') {
          if (isxdigit(*(src + 1)) && isxdigit(*(src + 2))) {
              char hex[3] = { *(src + 1), *(src + 2), '\0' };
              *dest++ = (char)strtol(hex, NULL, 16);
              src += 3;
          } else {
              *dest++ = *src++;
          }
      } else if (*src == '+') {
          *dest++ = ' ';
          src++;
      } else {
          *dest++ = *src++;
      }
  }
  *dest = '\0';
}

void parse_request_line(ctx_t *ctx)
{
  char *method = strtok(ctx->request, " ");
  char *route = strtok(NULL, " ");
  char *version = strtok(NULL, "\r\n");
  if (method == NULL || route == NULL || version == NULL) {
    return;
  }
  strncpy(ctx->method, method, MAX_METHOD_SIZE);
  strncpy(ctx->route, route, MAX_ROUTE_SIZE);
  strncpy(ctx->http_version, version, MAX_HTTP_VERSION_SIZE);
}

void parse_headers(ctx_t *ctx)
{
  const char *line_end = strstr(ctx->request, "\r\n");
  if (line_end == NULL) return;

  const char *header_start = line_end + strlen("\r\n");
  char *header_end;
  while ((header_end = strstr(header_start, "\r\n")) != NULL) {
    size_t line_length = header_end - header_start;
    if (line_length == 0) break;

    char header_line[line_length + 1];
    strncpy(header_line, header_start, line_length);
    header_line[line_length] = '\0';

    char *colon_pos = strchr(header_line, ':');
    if (colon_pos) {
      *colon_pos = '\0';
      char *header_name = header_line;
      char *header_value = colon_pos + 1;
      while (*header_value == ' ') header_value++;

      size_t len = (size_t)(header_end - header_value) > MAX_HEADER_SIZE ? MAX_HEADER_SIZE : (size_t)(header_end - header_value);
      if (strcmp(header_name, "Host") == 0) {
        strncpy(ctx->host_header, header_value, len);
      }
      else if (strcmp(header_name, "User-Agent") == 0) {
        strncpy(ctx->user_agent_header, header_value, len);
      }
      else if (strcmp(header_name, "Accept") == 0) {
        strncpy(ctx->accept_header, header_value, len);
      }
      else if (strcmp(header_name, "Accept-Language") == 0) {
        strncpy(ctx->accept_language_header, header_value, len);
      }
      else if (strcmp(header_name, "Accept-Encoding") == 0) {
        strncpy(ctx->accept_encoding_header, header_value, len);
      }
      else if (strcmp(header_name, "Connection") == 0) {
        strncpy(ctx->connection_header, header_value, len);
      }
    }
    header_start = header_end + 2;
  }
}

void get_file_extension(ctx_t *ctx)
{
  const char *dot = strchr(ctx->filepath, '.');
  if (dot == NULL || dot == ctx->filepath) return;
  strncpy(ctx->file_extension, dot + 1, MAX_FILE_EXT_SIZE);
}

bool extension_is_allowed(ctx_t *ctx)
{
  char *allowed_extensions[EXT_LIST_SIZE] = EXT_LIST;
  for (size_t i = 0; i < EXT_LIST_SIZE; ++i) {
    if (strncmp(ctx->file_extension, allowed_extensions[i], strlen(allowed_extensions[i])) == 0)
      return true;
  }
  return false;
}

void get_mime_type(ctx_t *ctx)
{
  if (strcasecmp(ctx->file_extension, "html") == 0 || strcasecmp(ctx->file_extension, "htm") == 0)
    strncpy(ctx->mime_type, "text/html", MAX_MIME_TYPE_SIZE);
  else if (strcasecmp(ctx->file_extension, "txt") == 0)
    strncpy(ctx->mime_type, "text/plain", MAX_MIME_TYPE_SIZE);
  else if (strcasecmp(ctx->file_extension, "jpg") == 0 || strcasecmp(ctx->file_extension, "jpeg") == 0)
    strncpy(ctx->mime_type, "image/jpeg", MAX_MIME_TYPE_SIZE);
  else if (strcasecmp(ctx->file_extension, "png") == 0)
    strncpy(ctx->mime_type, "image/png", MAX_MIME_TYPE_SIZE);
  else if (strcasecmp(ctx->file_extension, "pdf") == 0)
    strncpy(ctx->mime_type, "application/pdf", MAX_MIME_TYPE_SIZE);
  else
    strncpy(ctx->mime_type, "text/html", MAX_MIME_TYPE_SIZE);
}

void build_bad_http_response(ctx_t *ctx)
{
  const char *badreq = "HTTP/1.1 400 Bad Request\r\n\n";
  strncpy(ctx->response, badreq, MAX_REQ_SIZE);
  strncpy(ctx->status, BAD_REQ_STATUS, MAX_STATUS_SIZE);
  strcpy(ctx->mime_type, "text/html");
  char *template = "HTTP/1.1 %s\r\n"
                   "Content-Type: %s\r\n"
                   "\r\n"
                   "%s"
                   "\r\n\n";
  char *basic_400 = "<h1 style=\"text-align:center;\">400 Bad Request</h1>";
  snprintf(ctx->response, MAX_RESP_SIZE * sizeof(char), template, ctx->status, ctx->mime_type, basic_400);
}

void build_http_response(ctx_t *ctx)
{
  char *template = "HTTP/1.1 %s\r\n"
                   "Content-Type: %s\r\n"
                   "\r\n"
                   "%s"
                   "\r\n\n";
 
  FILE *file;
  char *contents;
  if ((file = fopen(ctx->filepath, "r")) != NULL) {
    fseek(file, 0, SEEK_END);
    size_t file_size = ftell(file);
    fseek(file, 0, SEEK_SET);
    contents = (char *)calloc(file_size + 1, sizeof(char));
    strcpy(ctx->status, "200 OK");
    fread(contents, sizeof(char), file_size + 1, file);
    fclose(file);
  }
  else {
    strcpy(ctx->status, NOT_FOUND_STATUS);
    strcpy(ctx->mime_type, "text/html");
    char *basic_404 = "<h1 style=\"text-align:center;\">404 Not Found</h1>";
    contents = (char *)calloc(strlen(basic_404)+1, sizeof(char));
    strcpy(contents, basic_404);
  }

  snprintf(ctx->response, MAX_RESP_SIZE * sizeof(char), template, ctx->status, ctx->mime_type, contents);
  free(contents);
}

void *handle_client(void *arg)
{
    int   client_fd = *(int *)arg;
    ctx_t *ctx      = (ctx_t *)calloc(1, sizeof(ctx_t));

    size_t total_received = 0;
    while (total_received < MAX_REQ_SIZE - 1) {
        char byte;
        ssize_t n = recv(client_fd, &byte, 1, 0);
        if (n <= 0)
            goto cleanup;
        ctx->request[total_received++] = byte;
        ctx->request[total_received]  = '\0';

        if (total_received >= 4 && strstr(ctx->request, "\r\n\r\n"))
            break;
    }

    char stats_dir_path[512] = "";
    bool stats_dir_created   = false;

    if (total_received > 0) {
        parse_request_line(ctx);

        if (strcmp(ctx->method, "GET") == 0) {
            if (strcmp(ctx->route, "/") == 0)
                strcpy(ctx->route, DEFAULT_ROUTE);

            url_decode(ctx);
            cleanup_filepath(ctx);
            get_file_extension(ctx);
            get_mime_type(ctx);
            parse_headers(ctx);

            const char *filename_for_stats = ctx->filepath[0] ? ctx->filepath : DEFAULT_ROUTE;
            const char *base               = strrchr(filename_for_stats, '/');
            base = base ? base + 1 : filename_for_stats;

            build_stats_dir_name(base, stats_dir_path, sizeof(stats_dir_path));
            if (mkdir(stats_dir_path, 0700) == 0)
                stats_dir_created = true;
            if (stats_dir_created)
                write_request_stats(ctx, stats_dir_path, client_fd);

            if (!extension_is_allowed(ctx) || strstr("..", ctx->filepath) != NULL) {
              build_bad_http_response(ctx);
            } else {
              build_http_response(ctx);
            }

            if (stats_dir_created)
                write_request_stats(ctx, stats_dir_path, client_fd);
        } else {
            build_bad_http_response(ctx);
        }
    } else {
        build_bad_http_response(ctx);
    }

    char *hdr_end = strstr(ctx->response, "\r\n\r\n");
    if (hdr_end) {
        size_t hdr_len  = (size_t)(hdr_end - ctx->response);
        char  *body     = hdr_end + 4;
        size_t body_len = strlen(body);

        char extra[128];
        int  extra_len = snprintf(extra, sizeof(extra), "\r\nContent-Length: %zu\r\nConnection: keep-alive\r\n\r\n", body_len);

        send(client_fd, ctx->response, hdr_len, 0);
        send(client_fd, extra, extra_len, 0);
        send(client_fd, body, body_len, 0);
    } else {
        send(client_fd, ctx->response, strlen(ctx->response), 0);
    }

    char discard;
    while (recv(client_fd, &discard, 1, 0) > 0) {}

cleanup:
    if (stats_dir_created)
        remove_stats_dir(stats_dir_path);

    close(client_fd);
    free(arg);
    free(ctx);
    return NULL;
}

int main(int argc, const char **argv)
{
  srand(0);
  ensure_stats_root_created();

  int server_fd = socket(AF_INET, SOCK_STREAM, 0);

  int opt = 1;
  if (setsockopt(server_fd, SOL_SOCKET, SO_REUSEADDR, &opt, sizeof(opt)))
    error_exit("setsockopt");
  
  struct sockaddr_in server_addr = {
    .sin_family = AF_INET,
    .sin_addr = { .s_addr = INADDR_ANY },
    .sin_port = htons(PORT)
  };

  if (bind(server_fd, (struct sockaddr *)&server_addr, sizeof(server_addr)))
    error_exit("bind");

  if (listen(server_fd, MAX_CONN))
    error_exit("listen");
  
  for (;;) {
    struct sockaddr_in client_addr;
    socklen_t client_addr_len = sizeof(client_addr);
    int *client_fd = (int *)malloc(sizeof(int));
    if ((*client_fd = accept(server_fd, (struct sockaddr *)&client_addr, &client_addr_len)) < 0)
      error_exit("accept");
    pthread_t thread_id;
    pthread_create(&thread_id, NULL, handle_client, (void *)client_fd);
    pthread_detach(thread_id);
  }

  return 0;
}
