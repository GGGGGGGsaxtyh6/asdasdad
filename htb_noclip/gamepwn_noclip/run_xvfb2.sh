#!/bin/bash
# Kill any existing Xvfb
pkill Xvfb 2>/dev/null
rm -f /tmp/.X100-lock

# Start Xvfb on display 100
Xvfb :100 -screen 0 1024x768x24 &
XVFB_PID=$!
sleep 2

# Set display
export DISPLAY=:100

# Run the game with timeout
timeout 10s ./noclip

# Kill Xvfb
kill $XVFB_PID 2>/dev/null