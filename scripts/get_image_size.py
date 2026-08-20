import struct
import sys

def get_image_info(filepath):
    try:
        with open(filepath, 'rb') as f:
            data = f.read()
    except Exception as e:
        print(f"Error reading {filepath}: {e}")
        return None

    size = len(data)
    height = -1
    width = -1
    img_type = 'Unknown'

    if size >= 10 and data[:6] in (b'GIF87a', b'GIF89a'):
        img_type = 'GIF'
        w, h = struct.unpack('<HH', data[6:10])
        width, height = int(w), int(h)
    elif size >= 8 and data.startswith(b'\x89PNG\r\n\x1a\n'):
        img_type = 'PNG'
        w, h = struct.unpack('>II', data[16:24])
        width, height = int(w), int(h)
    elif size >= 2 and data.startswith(b'\xff\xd8'):
        img_type = 'JPEG'
        idx = 2
        while idx < size:
            while idx < size and data[idx] != 0xff:
                idx += 1
            while idx < size and data[idx] == 0xff:
                idx += 1
            if idx >= size:
                break
            marker = data[idx]
            idx += 1
            if marker == 0x00 or (marker >= 0xd0 and marker <= 0xd9):
                continue
            if idx + 2 > size:
                break
            length = struct.unpack('>H', data[idx:idx+2])[0]
            if marker >= 0xc0 and marker <= 0xcf and marker != 0xc4 and marker != 0xcc and marker != 0xce:
                if idx + 7 <= size:
                    h, w = struct.unpack('>HH', data[idx+3:idx+7])
                    height, width = int(h), int(w)
                    break
            idx += length
    return {"type": img_type, "width": width, "height": height, "size_bytes": size}

if __name__ == '__main__':
    for path in ['d:/FACE VERIFICATION/public/logo.png', 'd:/FACE VERIFICATION/public/logo-pwa.png']:
        info = get_image_info(path)
        print(f"{path} -> {info}")
