import os
from PIL import Image, ImageColor

def generate_assets():
    # Paths
    workspace_dir = 'd:/FACE VERIFICATION'
    public_logo_path = os.path.join(workspace_dir, 'public/logo-pwa.png')
    assets_dir = os.path.join(workspace_dir, 'assets')
    
    # Create assets directory if not exists
    if not os.path.exists(assets_dir):
        os.makedirs(assets_dir)
        print(f"Created directory: {assets_dir}")
        
    # Load source image
    if not os.path.exists(public_logo_path):
        print(f"Error: Source image not found at {public_logo_path}")
        return
        
    img = Image.open(public_logo_path)
    print(f"Loaded source image: {public_logo_path} ({img.size} {img.format})")
    
    # 1. Generate icon.png (Legacy square icon with background)
    icon_path = os.path.join(assets_dir, 'icon.png')
    img.save(icon_path, 'PNG')
    print(f"Generated legacy icon: {icon_path}")
    
    # 2. Generate icon-background.png (Adaptive icon background)
    # Background color is #0d1520 (RGB: 13, 21, 32)
    bg_color = (13, 21, 32)
    icon_bg_path = os.path.join(assets_dir, 'icon-background.png')
    bg_img = Image.new('RGB', (1024, 1024), bg_color)
    bg_img.save(icon_bg_path, 'PNG')
    print(f"Generated adaptive icon background: {icon_bg_path}")
    
    # 3. Generate icon-foreground.png (Adaptive icon foreground with transparency)
    # We remove the background color with tolerance to handle JPEG artifacts
    icon_fg_path = os.path.join(assets_dir, 'icon-foreground.png')
    rgba_img = img.convert('RGBA')
    datas = rgba_img.getdata()
    
    new_data = []
    # Target color to remove: (13, 21, 32)
    target_r, target_g, target_b = bg_color
    tolerance = 25 # Tolerance to catch dark edges
    
    for item in datas:
        r, g, b, a = item
        # Calculate Euclidean distance in RGB space
        dist = ((r - target_r) ** 2 + (g - target_g) ** 2 + (b - target_b) ** 2) ** 0.5
        if dist < tolerance:
            # Replace background with transparent pixel
            new_data.append((0, 0, 0, 0))
        else:
            new_data.append(item)
            
    rgba_img.putdata(new_data)
    rgba_img.save(icon_fg_path, 'PNG')
    print(f"Generated adaptive icon foreground (transparent): {icon_fg_path}")
    
    # 4. Generate splash.png & splash-dark.png (Centered splash screen)
    # Splash screens should be 2732x2732 square for iOS/Android high-res scaling
    splash_size = (2732, 2732)
    
    # Create dark background canvas
    splash_img = Image.new('RGB', splash_size, bg_color)
    
    # Resize logo to fit nicely in the center of the splash screen (e.g. 700x700 pixels)
    logo_size = (700, 700)
    resized_logo = img.resize(logo_size, Image.Resampling.LANCZOS)
    
    # Calculate center position
    paste_x = (splash_size[0] - logo_size[0]) // 2
    paste_y = (splash_size[1] - logo_size[1]) // 2
    
    # Paste logo onto the center of the canvas
    splash_img.paste(resized_logo, (paste_x, paste_y))
    
    # Save splash screens
    splash_path = os.path.join(assets_dir, 'splash.png')
    splash_dark_path = os.path.join(assets_dir, 'splash-dark.png')
    
    splash_img.save(splash_path, 'PNG')
    splash_img.save(splash_dark_path, 'PNG')
    print(f"Generated splash screen: {splash_path}")
    print(f"Generated dark splash screen: {splash_dark_path}")
    print("All source assets generated successfully inside assets/ directory!")

if __name__ == '__main__':
    generate_assets()
