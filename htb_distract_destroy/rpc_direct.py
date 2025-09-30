#!/usr/bin/env python3
import json
import socket

host = "94.237.57.211"
port = 55233

# Intentar como servidor RPC JSON directo
s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
s.settimeout(10)
s.connect((host, port))

# Intentar un request RPC común
rpc_request = {
    "jsonrpc": "2.0",
    "method": "eth_blockNumber",
    "params": [],
    "id": 1
}

request_str = json.dumps(rpc_request) + "\n"
print(f"Sending: {request_str}")
s.sendall(request_str.encode())

response = s.recv(4096)
print(f"Response: {response.decode()}")

s.close()
