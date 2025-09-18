#!/usr/bin/env python3
"""
Script to upload mindmaze challenge to gofile.io
"""

import requests
import json
import os

def get_server():
    """Get the best server for upload"""
    try:
        response = requests.get("https://api.gofile.io/getServer")
        if response.status_code == 200:
            data = response.json()
            if data.get("status") == "ok":
                return data.get("data", {}).get("server")
        return None
    except Exception as e:
        print(f"Error getting server: {e}")
        return None

def upload_file(file_path, server):
    """Upload file to gofile.io"""
    try:
        url = f"https://{server}.gofile.io/uploadFile"
        
        with open(file_path, 'rb') as f:
            files = {'file': f}
            data = {
                'token': '',  # Optional token
                'folderId': ''  # Optional folder ID
            }
            
            response = requests.post(url, files=files, data=data)
            
            if response.status_code == 200:
                result = response.json()
                if result.get("status") == "ok":
                    return result.get("data")
                else:
                    print(f"Upload failed: {result.get('error')}")
                    return None
            else:
                print(f"HTTP error: {response.status_code}")
                return None
                
    except Exception as e:
        print(f"Error uploading file: {e}")
        return None

def main():
    print("Uploading MindMaze Challenge to gofile.io...")
    
    # Get server
    server = get_server()
    if not server:
        print("Failed to get server")
        return
    
    print(f"Using server: {server}")
    
    # Upload file
    file_path = "mindmaze_challenge.zip"
    if not os.path.exists(file_path):
        print(f"File {file_path} not found")
        return
    
    print(f"Uploading {file_path}...")
    result = upload_file(file_path, server)
    
    if result:
        print("Upload successful!")
        print(f"Download link: {result.get('downloadPage')}")
        print(f"Direct download: {result.get('link')}")
        print(f"Admin code: {result.get('adminCode')}")
    else:
        print("Upload failed")

if __name__ == "__main__":
    main()