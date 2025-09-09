#!/usr/bin/env python3
import requests
import re

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
    
    # Try direct download approach
    download_url = f"https://mega.nz/file/{file_id}#{key}"
    print(f"Trying direct download: {download_url}")
    
    try:
        response = requests.get(download_url, timeout=30)
        if response.status_code == 200:
            with open('corrupted_audio', 'wb') as f:
                f.write(response.content)
            print("Audio file downloaded successfully!")
            return True
        else:
            print(f"Download failed with status code: {response.status_code}")
            return False
            
    except Exception as e:
        print(f"Error: {e}")
        return False

if __name__ == "__main__":
    url = "https://mega.nz/#!jexRzTzD!Fd3tD8ZcLquXJrsycMFUzozC9MHqaG-srUBfGREtL-0"
    download_mega_file(url)