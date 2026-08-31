package io.flutter.plugin.platform;

import android.media.Image;
import android.media.ImageReader;
import android.os.Build;
import android.os.Handler;
import android.view.Surface;
import io.flutter.view.TextureRegistry;
/* loaded from: classes.dex */
public class b implements o {

    /* renamed from: a  reason: collision with root package name */
    private TextureRegistry.ImageTextureEntry f7933a;

    /* renamed from: b  reason: collision with root package name */
    private ImageReader f7934b;

    /* renamed from: c  reason: collision with root package name */
    private int f7935c = 0;

    /* renamed from: d  reason: collision with root package name */
    private int f7936d = 0;

    /* renamed from: e  reason: collision with root package name */
    private final Handler f7937e = new Handler();

    /* renamed from: f  reason: collision with root package name */
    private final ImageReader.OnImageAvailableListener f7938f = new a();

    /* loaded from: classes.dex */
    class a implements ImageReader.OnImageAvailableListener {
        a() {
        }

        @Override // android.media.ImageReader.OnImageAvailableListener
        public void onImageAvailable(ImageReader imageReader) {
            Image image;
            try {
                image = imageReader.acquireLatestImage();
            } catch (IllegalStateException e8) {
                z5.b.b("ImageReaderPlatformViewRenderTarget", "onImageAvailable acquireLatestImage failed: " + e8.toString());
                image = null;
            }
            if (image == null) {
                return;
            }
            b.this.f7933a.pushImage(image);
        }
    }

    public b(TextureRegistry.ImageTextureEntry imageTextureEntry) {
        if (Build.VERSION.SDK_INT < 29) {
            throw new UnsupportedOperationException("ImageReaderPlatformViewRenderTarget requires API version 29+");
        }
        this.f7933a = imageTextureEntry;
    }

    private void d() {
        if (this.f7934b != null) {
            this.f7933a.pushImage(null);
            this.f7934b.close();
            this.f7934b = null;
        }
    }

    @Override // io.flutter.plugin.platform.o
    public long a() {
        return this.f7933a.id();
    }

    @Override // io.flutter.plugin.platform.o
    public void b(int i8, int i9) {
        if (this.f7934b != null && this.f7935c == i8 && this.f7936d == i9) {
            return;
        }
        d();
        this.f7935c = i8;
        this.f7936d = i9;
        this.f7934b = e();
    }

    protected ImageReader e() {
        int i8 = Build.VERSION.SDK_INT;
        if (i8 >= 33) {
            return g();
        }
        if (i8 >= 29) {
            return f();
        }
        throw new UnsupportedOperationException("ImageReaderPlatformViewRenderTarget requires API version 29+");
    }

    protected ImageReader f() {
        ImageReader newInstance;
        newInstance = ImageReader.newInstance(this.f7935c, this.f7936d, 34, 4, 256L);
        newInstance.setOnImageAvailableListener(this.f7938f, this.f7937e);
        return newInstance;
    }

    protected ImageReader g() {
        ImageReader build;
        io.flutter.embedding.engine.renderer.h.a();
        ImageReader.Builder a8 = io.flutter.embedding.engine.renderer.g.a(this.f7935c, this.f7936d);
        a8.setMaxImages(4);
        a8.setImageFormat(34);
        a8.setUsage(256L);
        build = a8.build();
        build.setOnImageAvailableListener(this.f7938f, this.f7937e);
        return build;
    }

    @Override // io.flutter.plugin.platform.o
    public int getHeight() {
        return this.f7936d;
    }

    @Override // io.flutter.plugin.platform.o
    public Surface getSurface() {
        return this.f7934b.getSurface();
    }

    @Override // io.flutter.plugin.platform.o
    public int getWidth() {
        return this.f7935c;
    }

    @Override // io.flutter.plugin.platform.o
    public void release() {
        d();
        this.f7933a = null;
    }
}
