#!/usr/bin/env python3
import cv2
import numpy as np
import sys
import os

def extract_frames_from_video(video_path):
    """Extract all frames from video and analyze them for hidden data"""
    
    if not os.path.exists(video_path):
        print(f"Error: Video file {video_path} not found")
        return
    
    # Open video
    cap = cv2.VideoCapture(video_path)
    
    if not cap.isOpened():
        print("Error: Could not open video")
        return
    
    frame_count = int(cap.get(cv2.CAP_PROP_FRAME_COUNT))
    fps = cap.get(cv2.CAP_PROP_FPS)
    width = int(cap.get(cv2.CAP_PROP_FRAME_WIDTH))
    height = int(cap.get(cv2.CAP_PROP_FRAME_HEIGHT))
    
    print(f"Video Info:")
    print(f"  Frames: {frame_count}")
    print(f"  FPS: {fps}")
    print(f"  Resolution: {width}x{height}")
    print(f"  Duration: {frame_count/fps:.2f} seconds")
    print()
    
    frames = []
    frame_num = 0
    
    while True:
        ret, frame = cap.read()
        if not ret:
            break
            
        frames.append(frame)
        print(f"Extracted frame {frame_num + 1}/{frame_count}")
        frame_num += 1
    
    cap.release()
    
    # Analyze frames for patterns
    print(f"\nAnalyzing {len(frames)} frames...")
    
    # Convert frames to analyze pixel patterns
    binary_data = []
    
    for i, frame in enumerate(frames):
        # Convert to grayscale for easier analysis
        gray = cv2.cvtColor(frame, cv2.COLOR_BGR2GRAY)
        
        # Analyze pixel patterns - look for binary encoding
        # Check if frame has patterns that could represent binary data
        height, width = gray.shape
        
        # Extract data from specific regions or patterns
        # This is where we'll look for the hidden file data
        
        # For now, let's extract the raw pixel data
        frame_data = gray.flatten()
        binary_data.extend(frame_data)
        
        print(f"Frame {i}: {width}x{height}, data size: {len(frame_data)} bytes")
    
    # Try to find file signatures in the binary data
    print(f"\nTotal binary data collected: {len(binary_data)} bytes")
    
    # Look for common file signatures
    signatures = {
        b'PK': 'ZIP/Office file',
        b'\x89PNG': 'PNG image',
        b'\xFF\xD8\xFF': 'JPEG image',
        b'GIF8': 'GIF image',
        b'%PDF': 'PDF file',
        b'\x50\x4B\x03\x04': 'ZIP file',
        b'RIFF': 'WAV/AVI file',
        b'\x1F\x8B': 'GZIP file'
    }
    
    binary_bytes = bytes(binary_data)
    
    print("\nSearching for file signatures...")
    for sig, desc in signatures.items():
        pos = binary_bytes.find(sig)
        if pos != -1:
            print(f"Found {desc} signature at position {pos}")
    
    # Save frames for manual inspection
    os.makedirs("frames", exist_ok=True)
    for i, frame in enumerate(frames):
        cv2.imwrite(f"frames/frame_{i:03d}.png", frame)
    
    print(f"\nFrames saved to 'frames/' directory")
    
    return frames, binary_data

if __name__ == "__main__":
    video_file = "secret.mp4"
    extract_frames_from_video(video_file)