#!/usr/bin/env bash
set -euo pipefail

print_usage() {
    echo "Usage: $0 [-t seconds] host port" 1>&2
    echo "Env override: TIMEOUT_SECONDS (default 5)" 1>&2
}

timeout_seconds="${TIMEOUT_SECONDS:-5}"

while getopts ":t:h" opt; do
    case "$opt" in
        t)
            timeout_seconds="$OPTARG"
            ;;
        h)
            print_usage
            exit 0
            ;;
        \?)
            echo "Invalid option: -$OPTARG" 1>&2
            print_usage
            exit 2
            ;;
        :)
            echo "Option -$OPTARG requires an argument." 1>&2
            print_usage
            exit 2
            ;;
    esac
done
shift $((OPTIND-1))

if [ "$#" -ne 2 ]; then
    print_usage
    exit 2
fi

host="$1"
port="$2"

if ! [[ "$timeout_seconds" =~ ^[0-9]+$ ]]; then
    echo "Timeout must be an integer number of seconds" 1>&2
    exit 2
fi

exec timeout "${timeout_seconds}s" nc -v -n "$host" "$port"

#!/usr/bin/env bash
# nc with timeout wrapper
set -euo pipefail
if [ $# -lt 1 ]; then
  echo "Usage: $0 <timeout_seconds> -- <nc args...>" >&2
  exit 1
fi
TO=$1; shift
if [ "$1" != "--" ]; then
  echo "Missing -- separator before nc args" >&2
  exit 1
fi
shift
command -v nc >/dev/null 2>&1 || { echo "nc not found" >&2; exit 127; }
/usr/bin/timeout -k 2 ${TO}s nc "$@"
