package io.flutter.embedding.android;

import android.content.Context;
import android.graphics.SurfaceTexture;
import android.util.AttributeSet;
import android.view.Surface;
import android.view.TextureView;
import io.flutter.embedding.engine.renderer.FlutterRenderer;
/* loaded from: classes.dex */
public class m extends TextureView implements io.flutter.embedding.engine.renderer.m {

    /* renamed from: a  reason: collision with root package name */
    private boolean f7638a;

    /* renamed from: b  reason: collision with root package name */
    private boolean f7639b;

    /* renamed from: c  reason: collision with root package name */
    private FlutterRenderer f7640c;

    /* renamed from: d  reason: collision with root package name */
    private Surface f7641d;

    /* renamed from: e  reason: collision with root package name */
    private final TextureView.SurfaceTextureListener f7642e;

    /* loaded from: classes.dex */
    class a implements TextureView.SurfaceTextureListener {
        a() {
        }

        @Override // android.view.TextureView.SurfaceTextureListener
        public void onSurfaceTextureAvailable(SurfaceTexture surfaceTexture, int i8, int i9) {
            z5.b.f("FlutterTextureView", "SurfaceTextureListener.onSurfaceTextureAvailable()");
            m.this.f7638a = true;
            if (m.this.q()) {
                m.this.m();
            }
        }

        @Override // android.view.TextureView.SurfaceTextureListener
        public boolean onSurfaceTextureDestroyed(SurfaceTexture surfaceTexture) {
            z5.b.f("FlutterTextureView", "SurfaceTextureListener.onSurfaceTextureDestroyed()");
            m.this.f7638a = false;
            if (m.this.q()) {
                m.this.n();
            }
            if (m.this.f7641d != null) {
                m.this.f7641d.release();
                m.this.f7641d = null;
                return true;
            }
            return true;
        }

        @Override // android.view.TextureView.SurfaceTextureListener
        public void onSurfaceTextureSizeChanged(SurfaceTexture surfaceTexture, int i8, int i9) {
            z5.b.f("FlutterTextureView", "SurfaceTextureListener.onSurfaceTextureSizeChanged()");
            if (m.this.q()) {
                m.this.l(i8, i9);
            }
        }

        @Override // android.view.TextureView.SurfaceTextureListener
        public void onSurfaceTextureUpdated(SurfaceTexture surfaceTexture) {
        }
    }

    public m(Context context) {
        this(context, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void l(int i8, int i9) {
        if (this.f7640c == null) {
            throw new IllegalStateException("changeSurfaceSize() should only be called when flutterRenderer is non-null.");
        }
        z5.b.f("FlutterTextureView", "Notifying FlutterRenderer that Android surface size has changed to " + i8 + " x " + i9);
        this.f7640c.B(i8, i9);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void m() {
        if (this.f7640c == null || getSurfaceTexture() == null) {
            throw new IllegalStateException("connectSurfaceToRenderer() should only be called when flutterRenderer and getSurfaceTexture() are non-null.");
        }
        Surface surface = this.f7641d;
        if (surface != null) {
            surface.release();
            this.f7641d = null;
        }
        Surface surface2 = new Surface(getSurfaceTexture());
        this.f7641d = surface2;
        this.f7640c.z(surface2, this.f7639b);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void n() {
        FlutterRenderer flutterRenderer = this.f7640c;
        if (flutterRenderer == null) {
            throw new IllegalStateException("disconnectSurfaceFromRenderer() should only be called when flutterRenderer is non-null.");
        }
        flutterRenderer.A();
        Surface surface = this.f7641d;
        if (surface != null) {
            surface.release();
            this.f7641d = null;
        }
    }

    private void o() {
        setSurfaceTextureListener(this.f7642e);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean q() {
        return (this.f7640c == null || this.f7639b) ? false : true;
    }

    @Override // io.flutter.embedding.engine.renderer.m
    public void a() {
        if (this.f7640c == null) {
            z5.b.g("FlutterTextureView", "pause() invoked when no FlutterRenderer was attached.");
        } else {
            this.f7639b = true;
        }
    }

    @Override // io.flutter.embedding.engine.renderer.m
    public void b() {
        if (this.f7640c == null) {
            z5.b.g("FlutterTextureView", "detachFromRenderer() invoked when no FlutterRenderer was attached.");
            return;
        }
        if (getWindowToken() != null) {
            z5.b.f("FlutterTextureView", "Disconnecting FlutterRenderer from Android surface.");
            n();
        }
        this.f7640c = null;
    }

    @Override // io.flutter.embedding.engine.renderer.m
    public void c() {
        if (this.f7640c == null) {
            z5.b.g("FlutterTextureView", "resume() invoked when no FlutterRenderer was attached.");
            return;
        }
        if (p()) {
            z5.b.f("FlutterTextureView", "Surface is available for rendering. Connecting FlutterRenderer to Android surface.");
            m();
        }
        this.f7639b = false;
    }

    @Override // io.flutter.embedding.engine.renderer.m
    public void d(FlutterRenderer flutterRenderer) {
        z5.b.f("FlutterTextureView", "Attaching to FlutterRenderer.");
        if (this.f7640c != null) {
            z5.b.f("FlutterTextureView", "Already connected to a FlutterRenderer. Detaching from old one and attaching to new one.");
            this.f7640c.A();
        }
        this.f7640c = flutterRenderer;
        c();
    }

    @Override // io.flutter.embedding.engine.renderer.m
    public FlutterRenderer getAttachedRenderer() {
        return this.f7640c;
    }

    boolean p() {
        return this.f7638a;
    }

    public void setRenderSurface(Surface surface) {
        this.f7641d = surface;
    }

    public m(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f7638a = false;
        this.f7639b = false;
        this.f7642e = new a();
        o();
    }
}
