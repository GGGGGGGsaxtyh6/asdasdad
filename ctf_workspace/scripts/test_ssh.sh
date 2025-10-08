#!/bin/bash
timeout 10s sshpass -p hackthebox ssh -o StrictHostKeyChecking=no -p 48117 root@94.237.122.241 "ls -la"
