package io.flutter.embedding.engine.renderer;

import android.graphics.SurfaceTexture;
import android.os.Handler;
import android.view.Surface;
import io.flutter.embedding.engine.FlutterJNI;
import io.flutter.embedding.engine.renderer.FlutterRenderer;
import io.flutter.view.TextureRegistry;
/* loaded from: classes.dex */
final class n implements TextureRegistry.SurfaceProducer, TextureRegistry.GLTextureConsumer {

    /* renamed from: a  reason: collision with root package name */
    private final long f7846a;

    /* renamed from: b  reason: collision with root package name */
    private int f7847b;

    /* renamed from: c  reason: collision with root package name */
    private int f7848c;

    /* renamed from: d  reason: collision with root package name */
    private boolean f7849d;

    /* renamed from: e  reason: collision with root package name */
    private Surface f7850e;

    /* renamed from: f  reason: collision with root package name */
    private final TextureRegistry.SurfaceTextureEntry f7851f;

    /* renamed from: g  reason: collision with root package name */
    private final Handler f7852g;

    /* renamed from: h  reason: collision with root package name */
    private final FlutterJNI f7853h;

    /* JADX INFO: Access modifiers changed from: package-private */
    public n(long j8, Handler handler, FlutterJNI flutterJNI, TextureRegistry.SurfaceTextureEntry surfaceTextureEntry) {
        this.f7846a = j8;
        this.f7852g = handler;
        this.f7853h = flutterJNI;
        this.f7851f = surfaceTextureEntry;
    }

    protected void finalize() {
        try {
            if (this.f7849d) {
                return;
            }
            release();
            this.f7852g.post(new FlutterRenderer.g(this.f7846a, this.f7853h));
        } finally {
            super.finalize();
        }
    }

    @Override // io.flutter.view.TextureRegistry.SurfaceProducer
    public int getHeight() {
        return this.f7848c;
    }

    @Override // io.flutter.view.TextureRegistry.SurfaceProducer
    public Surface getSurface() {
        if (this.f7850e == null) {
            this.f7850e = new Surface(this.f7851f.surfaceTexture());
        }
        return this.f7850e;
    }

    @Override // io.flutter.view.TextureRegistry.GLTextureConsumer
    public SurfaceTexture getSurfaceTexture() {
        return this.f7851f.surfaceTexture();
    }

    @Override // io.flutter.view.TextureRegistry.SurfaceProducer
    public int getWidth() {
        return this.f7847b;
    }

    @Override // io.flutter.view.TextureRegistry.SurfaceProducer
    public long id() {
        return this.f7846a;
    }

    @Override // io.flutter.view.TextureRegistry.SurfaceProducer
    public void release() {
        this.f7851f.release();
        this.f7849d = true;
    }

    @Override // io.flutter.view.TextureRegistry.SurfaceProducer
    public void scheduleFrame() {
        this.f7853h.markTextureFrameAvailable(this.f7846a);
    }

    @Override // io.flutter.view.TextureRegistry.SurfaceProducer
    public void setCallback(TextureRegistry.SurfaceProducer.a aVar) {
    }

    @Override // io.flutter.view.TextureRegistry.SurfaceProducer
    public void setSize(int i8, int i9) {
        this.f7847b = i8;
        this.f7848c = i9;
        getSurfaceTexture().setDefaultBufferSize(i8, i9);
    }
}
