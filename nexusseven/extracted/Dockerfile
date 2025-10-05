FROM ubuntu:22.04

ENV DEBIAN_FRONTEND=noninteractive

RUN apt-get update && apt-get install -y --no-install-recommends build-essential supervisor
RUN rm -rf /var/lib/apt/lists/*

WORKDIR /app

COPY source/ .
RUN gcc -g -Wall -o httpd httpd.c

COPY flag.txt /flag.txt

EXPOSE 1337

COPY conf/supervisord.conf /etc/supervisord.conf
CMD ["/usr/bin/supervisord", "-c", "/etc/supervisord.conf"]
