#!/usr/bin/env python3
import os
import binascii

def analyze_video_directly():
    """Direct analysis of the video file without external tools"""
    
    filename = "secret.mp4"
    
    if not os.path.exists(filename):
        print(f"Error: {filename} not found")
        return
    
    # Get file info
    size = os.path.getsize(filename)
    print(f"=== Video Analysis ===")
    print(f"File: {filename}")
    print(f"Size: {size} bytes")
    print()
    
    # Read file in chunks to avoid memory issues
    chunk_size = 8192
    total_read = 0
    all_data = b''
    
    with open(filename, 'rb') as f:
        while True:
            chunk = f.read(chunk_size)
            if not chunk:
                break
            all_data += chunk
            total_read += len(chunk)
            print(f"Read {total_read}/{size} bytes ({total_read/size*100:.1f}%)")
    
    print(f"\nTotal data read: {len(all_data)} bytes")
    
    # Look for file signatures
    print("\n=== Searching for File Signatures ===")
    signatures = {
        b'PK\x03\x04': 'ZIP file',
        b'PK\x05\x06': 'ZIP file (empty)',
        b'\x89PNG\r\n\x1a\n': 'PNG image',
        b'\xFF\xD8\xFF': 'JPEG image',
        b'GIF8': 'GIF image',
        b'%PDF': 'PDF file',
        b'RIFF': 'WAV/AVI file',
        b'\x1F\x8B\x08': 'GZIP file',
        b'\x7F\x45\x4C\x46': 'ELF executable',
        b'\x4D\x5A': 'Windows PE executable'
    }
    
    found_files = []
    
    for sig, desc in signatures.items():
        pos = all_data.find(sig)
        if pos != -1:
            print(f"✓ Found {desc} at position {pos}")
            found_files.append((pos, sig, desc))
        else:
            print(f"✗ {desc} not found")
    
    # If we found file signatures, try to extract them
    if found_files:
        print(f"\n=== Extracting Found Files ===")
        
        for pos, sig, desc in found_files:
            print(f"\nExtracting {desc} from position {pos}...")
            
            # Try to extract the file
            if sig == b'PK\x03\x04':  # ZIP file
                # Look for the end of ZIP file
                end_sig = b'PK\x05\x06'
                end_pos = all_data.find(end_sig, pos)
                
                if end_pos != -1:
                    # Extract ZIP file
                    zip_data = all_data[pos:end_pos + 22]  # +22 for end record
                    output_file = f"extracted_{desc.lower().replace(' ', '_')}.zip"
                    
                    with open(output_file, 'wb') as f:
                        f.write(zip_data)
                    
                    print(f"  Saved ZIP file as {output_file}")
                    
                    # Try to extract contents
                    import zipfile
                    try:
                        with zipfile.ZipFile(output_file, 'r') as zf:
                            print(f"  ZIP contents:")
                            for name in zf.namelist():
                                print(f"    - {name}")
                            
                            # Extract all files
                            zf.extractall("extracted_files/")
                            print(f"  Extracted all files to extracted_files/")
                            
                    except Exception as e:
                        print(f"  Error reading ZIP: {e}")
            
            elif sig == b'\x89PNG':  # PNG file
                # PNG files end with IEND chunk
                iend_pos = all_data.find(b'IEND\xaeB`\x82', pos)
                if iend_pos != -1:
                    png_data = all_data[pos:iend_pos + 12]  # +12 for IEND chunk
                    output_file = f"extracted_{desc.lower().replace(' ', '_')}.png"
                    
                    with open(output_file, 'wb') as f:
                        f.write(png_data)
                    
                    print(f"  Saved PNG file as {output_file}")
            
            elif sig == b'%PDF':  # PDF file
                # PDF files typically end with %%EOF
                eof_pos = all_data.find(b'%%EOF', pos)
                if eof_pos != -1:
                    pdf_data = all_data[pos:eof_pos + 5]
                    output_file = f"extracted_{desc.lower().replace(' ', '_')}.pdf"
                    
                    with open(output_file, 'wb') as f:
                        f.write(pdf_data)
                    
                    print(f"  Saved PDF file as {output_file}")
    
    # If no file signatures found, try other extraction methods
    else:
        print("\n=== No File Signatures Found ===")
        print("Trying alternative extraction methods...")
        
        # Method 1: Extract every nth byte
        for step in [2, 4, 8, 16]:
            extracted = all_data[::step]
            output_file = f"extracted_step_{step}.bin"
            
            with open(output_file, 'wb') as f:
                f.write(extracted)
            
            print(f"Extracted data with step {step} -> {output_file} ({len(extracted)} bytes)")
            
            # Check if extracted data has signatures
            for sig, desc in signatures.items():
                if sig in extracted:
                    print(f"  Found {desc} signature in extracted data!")
        
        # Method 2: Look for repeating patterns
        print("\nLooking for repeating patterns...")
        
        # Check for patterns every 1024 bytes
        for offset in range(0, min(1024, len(all_data)), 128):
            pattern_data = all_data[offset::1024]
            if len(pattern_data) > 100:
                output_file = f"extracted_pattern_offset_{offset}.bin"
                
                with open(output_file, 'wb') as f:
                    f.write(pattern_data)
                
                print(f"Pattern extraction offset {offset} -> {output_file} ({len(pattern_data)} bytes)")
    
    # Save raw data chunks for manual analysis
    print(f"\n=== Saving Analysis Data ===")
    
    # Save header
    with open('video_header.bin', 'wb') as f:
        f.write(all_data[:4096])
    print("Saved video header (first 4KB) as video_header.bin")
    
    # Save footer
    with open('video_footer.bin', 'wb') as f:
        f.write(all_data[-4096:])
    print("Saved video footer (last 4KB) as video_footer.bin")
    
    # Save middle section
    middle_start = len(all_data) // 2
    with open('video_middle.bin', 'wb') as f:
        f.write(all_data[middle_start:middle_start + 4096])
    print("Saved video middle section (4KB) as video_middle.bin")
    
    print(f"\n=== Analysis Complete ===")

if __name__ == "__main__":
    analyze_video_directly()