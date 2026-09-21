from PIL import Image
import os
import sys

def upscale_gif(input_path, output_path, scale_factor=2.0):
    if not os.path.exists(input_path):
        print(f"Error: File {input_path} tidak ditemukan.")
        return

    try:
        print(f"Membuka {input_path} untuk proses upscaling...")
        with Image.open(input_path) as gif:
            frames = []
            
            duration = gif.info.get('duration', 100)
            loop = gif.info.get('loop', 0)
            
            for frame_idx in range(gif.n_frames):
                gif.seek(frame_idx)
                frame_rgba = gif.convert("RGBA")
                
                new_width = int(frame_rgba.width * scale_factor)
                new_height = int(frame_rgba.height * scale_factor)
                
                upscaled_frame = frame_rgba.resize((new_width, new_height), Image.Resampling.LANCZOS)
                frames.append(upscaled_frame)
                
            print(f"Memproses {len(frames)} frame... Menyimpan file...")
            
            # Tentukan format berdasarkan ekstensi output
            output_format = 'WEBP' if output_path.lower().endswith('.webp') else 'GIF'
            save_kwargs = {
                'format': output_format,
                'save_all': True,
                'append_images': frames[1:],
                'duration': duration,
                'loop': loop
            }
            
            if output_format == 'GIF':
                save_kwargs['optimize'] = False
                save_kwargs['disposal'] = 2
            elif output_format == 'WEBP':
                # WebP lossless mode untuk kualitas tinggi dan transparansi halus
                save_kwargs['lossless'] = True
                save_kwargs['method'] = 6 # method 6 is slowest/best compression
            
            frames[0].save(output_path, **save_kwargs)
            
        print(f"Sukses! Hasil upscaling disimpan di: {output_path}")

    except Exception as e:
        print(f"Terjadi kesalahan saat memproses gambar: {e}")

if __name__ == "__main__":
    if len(sys.argv) < 3:
        print("Usage: python upscale_gif.py <input_file> <output_file> [scale_factor]")
        print("Note: output_file should end with .webp for best transparent anti-aliasing!")
        sys.exit(1)
        
    input_file = sys.argv[1]
    output_file = sys.argv[2]
    scale_factor = float(sys.argv[3]) if len(sys.argv) > 3 else 2.0
    
    upscale_gif(input_file, output_file, scale_factor)
