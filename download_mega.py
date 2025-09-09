#!/usr/bin/env python3
import requests
import re
import base64
import json

def download_mega_file(url):
    # Extract file ID from MEGA URL
    match = re.search(r'#!(.+)!(.+)', url)
    if not match:
        print("Invalid MEGA URL format")
        return False
    
    file_id = match.group(1)
    key = match.group(2)
    
    print(f"File ID: {file_id}")
    print(f"Key: {key}")
    
    # Try to get file info from MEGA API
    api_url = f"https://g.api.mega.co.nz/cs?id=0&n={file_id}"
    
    try:
        response = requests.get(api_url, timeout=30)
        print(f"API Response: {response.text}")
        
        # Try direct download approach
        download_url = f"https://mega.nz/file/{file_id}#{key}"
        print(f"Trying direct download: {download_url}")
        
        # Use requests to download
        response = requests.get(download_url, timeout=30)
        if response.status_code == 200:
            with open('encrypted_file', 'wb') as f:
                f.write(response.content)
            print("File downloaded successfully!")
            return True
        else:
            print(f"Download failed with status code: {response.status_code}")
            return False
            
    except Exception as e:
        print(f"Error: {e}")
        return False

if __name__ == "__main__":
    url = "https://mega.nz/#!OHhUyIqA!H9WxSdG1O7eVcCm0dffggNB0-dBemSpBAXiZ0OXJnLk"
    download_mega_file(url)