package io.flutter.plugin.platform;

import android.graphics.SurfaceTexture;
import android.os.Build;
import android.view.Surface;
import io.flutter.view.TextureRegistry;
/* loaded from: classes.dex */
public class e0 implements o {

    /* renamed from: a  reason: collision with root package name */
    private final TextureRegistry.SurfaceTextureEntry f7944a;

    /* renamed from: b  reason: collision with root package name */
    private SurfaceTexture f7945b;

    /* renamed from: c  reason: collision with root package name */
    private Surface f7946c;

    /* renamed from: d  reason: collision with root package name */
    private int f7947d = 0;

    /* renamed from: e  reason: collision with root package name */
    private int f7948e = 0;

    /* renamed from: f  reason: collision with root package name */
    private boolean f7949f = false;

    /* renamed from: g  reason: collision with root package name */
    private final TextureRegistry.b f7950g;

    /* loaded from: classes.dex */
    class a implements TextureRegistry.b {
        a() {
        }

        @Override // io.flutter.view.TextureRegistry.b
        public void onTrimMemory(int i8) {
            if (i8 != 80 || Build.VERSION.SDK_INT < 29) {
                return;
            }
            e0.this.f7949f = true;
        }
    }

    public e0(TextureRegistry.SurfaceTextureEntry surfaceTextureEntry) {
        a aVar = new a();
        this.f7950g = aVar;
        this.f7944a = surfaceTextureEntry;
        this.f7945b = surfaceTextureEntry.surfaceTexture();
        surfaceTextureEntry.setOnTrimMemoryListener(aVar);
    }

    private void e() {
        Surface surface = this.f7946c;
        if (surface == null || this.f7949f) {
            if (surface != null) {
                surface.release();
                this.f7946c = null;
            }
            this.f7946c = d();
            this.f7949f = false;
        }
    }

    @Override // io.flutter.plugin.platform.o
    public long a() {
        return this.f7944a.id();
    }

    @Override // io.flutter.plugin.platform.o
    public void b(int i8, int i9) {
        this.f7947d = i8;
        this.f7948e = i9;
        SurfaceTexture surfaceTexture = this.f7945b;
        if (surfaceTexture != null) {
            surfaceTexture.setDefaultBufferSize(i8, i9);
        }
    }

    protected Surface d() {
        return new Surface(this.f7945b);
    }

    @Override // io.flutter.plugin.platform.o
    public int getHeight() {
        return this.f7948e;
    }

    @Override // io.flutter.plugin.platform.o
    public Surface getSurface() {
        e();
        SurfaceTexture surfaceTexture = this.f7945b;
        if (surfaceTexture == null || surfaceTexture.isReleased()) {
            return null;
        }
        return this.f7946c;
    }

    @Override // io.flutter.plugin.platform.o
    public int getWidth() {
        return this.f7947d;
    }

    @Override // io.flutter.plugin.platform.o
    public void release() {
        this.f7945b = null;
        Surface surface = this.f7946c;
        if (surface != null) {
            surface.release();
            this.f7946c = null;
        }
    }
}
