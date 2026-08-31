package io.flutter.embedding.android;

import android.content.Context;
import android.graphics.Region;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import io.flutter.embedding.engine.renderer.FlutterRenderer;
/* loaded from: classes.dex */
public class l extends SurfaceView implements io.flutter.embedding.engine.renderer.m {

    /* renamed from: a  reason: collision with root package name */
    private final boolean f7630a;

    /* renamed from: b  reason: collision with root package name */
    private boolean f7631b;

    /* renamed from: c  reason: collision with root package name */
    private boolean f7632c;

    /* renamed from: d  reason: collision with root package name */
    private FlutterRenderer f7633d;

    /* renamed from: e  reason: collision with root package name */
    private final SurfaceHolder.Callback f7634e;

    /* renamed from: f  reason: collision with root package name */
    private final io.flutter.embedding.engine.renderer.l f7635f;

    /* loaded from: classes.dex */
    class a implements SurfaceHolder.Callback {
        a() {
        }

        @Override // android.view.SurfaceHolder.Callback
        public void surfaceChanged(SurfaceHolder surfaceHolder, int i8, int i9, int i10) {
            z5.b.f("FlutterSurfaceView", "SurfaceHolder.Callback.surfaceChanged()");
            if (l.this.p()) {
                l.this.k(i9, i10);
            }
        }

        @Override // android.view.SurfaceHolder.Callback
        public void surfaceCreated(SurfaceHolder surfaceHolder) {
            z5.b.f("FlutterSurfaceView", "SurfaceHolder.Callback.startRenderingToSurface()");
            l.this.f7631b = true;
            if (l.this.p()) {
                l.this.l();
            }
        }

        @Override // android.view.SurfaceHolder.Callback
        public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
            z5.b.f("FlutterSurfaceView", "SurfaceHolder.Callback.stopRenderingToSurface()");
            l.this.f7631b = false;
            if (l.this.p()) {
                l.this.m();
            }
        }
    }

    /* loaded from: classes.dex */
    class b implements io.flutter.embedding.engine.renderer.l {
        b() {
        }

        @Override // io.flutter.embedding.engine.renderer.l
        public void b() {
        }

        @Override // io.flutter.embedding.engine.renderer.l
        public void d() {
            z5.b.f("FlutterSurfaceView", "onFlutterUiDisplayed()");
            l.this.setAlpha(1.0f);
            if (l.this.f7633d != null) {
                l.this.f7633d.u(this);
            }
        }
    }

    private l(Context context, AttributeSet attributeSet, boolean z7) {
        super(context, attributeSet);
        this.f7631b = false;
        this.f7632c = false;
        this.f7634e = new a();
        this.f7635f = new b();
        this.f7630a = z7;
        n();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void k(int i8, int i9) {
        if (this.f7633d == null) {
            throw new IllegalStateException("changeSurfaceSize() should only be called when flutterRenderer is non-null.");
        }
        z5.b.f("FlutterSurfaceView", "Notifying FlutterRenderer that Android surface size has changed to " + i8 + " x " + i9);
        this.f7633d.B(i8, i9);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void l() {
        if (this.f7633d == null || getHolder() == null) {
            throw new IllegalStateException("connectSurfaceToRenderer() should only be called when flutterRenderer and getHolder() are non-null.");
        }
        this.f7633d.z(getHolder().getSurface(), this.f7632c);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void m() {
        FlutterRenderer flutterRenderer = this.f7633d;
        if (flutterRenderer == null) {
            throw new IllegalStateException("disconnectSurfaceFromRenderer() should only be called when flutterRenderer is non-null.");
        }
        flutterRenderer.A();
    }

    private void n() {
        if (this.f7630a) {
            getHolder().setFormat(-2);
            setZOrderOnTop(true);
        }
        getHolder().addCallback(this.f7634e);
        setAlpha(0.0f);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean p() {
        return (this.f7633d == null || this.f7632c) ? false : true;
    }

    @Override // io.flutter.embedding.engine.renderer.m
    public void a() {
        if (this.f7633d == null) {
            z5.b.g("FlutterSurfaceView", "pause() invoked when no FlutterRenderer was attached.");
        } else {
            this.f7632c = true;
        }
    }

    @Override // io.flutter.embedding.engine.renderer.m
    public void b() {
        if (this.f7633d == null) {
            z5.b.g("FlutterSurfaceView", "detachFromRenderer() invoked when no FlutterRenderer was attached.");
            return;
        }
        if (getWindowToken() != null) {
            z5.b.f("FlutterSurfaceView", "Disconnecting FlutterRenderer from Android surface.");
            m();
        }
        setAlpha(0.0f);
        this.f7633d.u(this.f7635f);
        this.f7633d = null;
    }

    @Override // io.flutter.embedding.engine.renderer.m
    public void c() {
        FlutterRenderer flutterRenderer = this.f7633d;
        if (flutterRenderer == null) {
            z5.b.g("FlutterSurfaceView", "resume() invoked when no FlutterRenderer was attached.");
            return;
        }
        flutterRenderer.j(this.f7635f);
        if (o()) {
            z5.b.f("FlutterSurfaceView", "Surface is available for rendering. Connecting FlutterRenderer to Android surface.");
            l();
        }
        this.f7632c = false;
    }

    @Override // io.flutter.embedding.engine.renderer.m
    public void d(FlutterRenderer flutterRenderer) {
        z5.b.f("FlutterSurfaceView", "Attaching to FlutterRenderer.");
        if (this.f7633d != null) {
            z5.b.f("FlutterSurfaceView", "Already connected to a FlutterRenderer. Detaching from old one and attaching to new one.");
            this.f7633d.A();
            this.f7633d.u(this.f7635f);
        }
        this.f7633d = flutterRenderer;
        c();
    }

    @Override // android.view.SurfaceView, android.view.View
    public boolean gatherTransparentRegion(Region region) {
        if (getAlpha() < 1.0f) {
            return false;
        }
        int[] iArr = new int[2];
        getLocationInWindow(iArr);
        int i8 = iArr[0];
        region.op(i8, iArr[1], (getRight() + i8) - getLeft(), (iArr[1] + getBottom()) - getTop(), Region.Op.DIFFERENCE);
        return true;
    }

    @Override // io.flutter.embedding.engine.renderer.m
    public FlutterRenderer getAttachedRenderer() {
        return this.f7633d;
    }

    boolean o() {
        return this.f7631b;
    }

    public l(Context context, boolean z7) {
        this(context, null, z7);
    }
}
