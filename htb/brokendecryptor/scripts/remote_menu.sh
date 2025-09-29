#!/usr/bin/env bash
set -Eeuo pipefail

# Wrapper to interact with remote challenge using nc with timeout
# Usage:
#   remote_menu.sh <host> <port> get_flag
#   remote_menu.sh <host> <port> encrypt <hex>
#   remote_menu.sh <host> <port> decrypt <hex>

if [[ $# -lt 3 ]]; then
    echo "Usage: $0 <host> <port> <get_flag|encrypt|decrypt> [arg]" >&2
    exit 2
fi

host="$1"; port="$2"; action="$3"; shift 3

send() {
    local data="$1"
    printf "%b" "$data" | "$(dirname "$0")/nc_timeout.sh" 15s "$host" "$port"
}

case "$action" in
    get_flag)
        send "1\n"
        ;;
    encrypt)
        hex="${1:-}"
        if [[ -z "$hex" ]]; then echo "Missing <hex>" >&2; exit 2; fi
        {
            printf "2\n"; sleep 0.1; printf "%s\n" "$hex";
        } | "$(dirname "$0")/nc_timeout.sh" 15s "$host" "$port"
        ;;
    decrypt)
        hex="${1:-}"
        if [[ -z "$hex" ]]; then echo "Missing <hex>" >&2; exit 2; fi
        {
            printf "3\n"; sleep 0.1; printf "%s\n" "$hex";
        } | "$(dirname "$0")/nc_timeout.sh" 15s "$host" "$port"
        ;;
    *)
        echo "Unknown action: $action" >&2; exit 2 ;;
esac

