#!/bin/bash
# Start Xvfb
Xvfb :99 -screen 0 1024x768x24 &
XVFB_PID=$!
sleep 2

# Set display
export DISPLAY=:99

# Run the game with timeout
timeout 10s ./noclip

# Kill Xvfb
kill $XVFB_PID 2>/dev/null