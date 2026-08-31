package io.flutter.plugin.platform;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.Surface;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.ViewTreeObserver;
import android.view.accessibility.AccessibilityEvent;
import android.widget.FrameLayout;
import io.flutter.embedding.engine.renderer.FlutterRenderer;
/* loaded from: classes.dex */
public class p extends FrameLayout {

    /* renamed from: a  reason: collision with root package name */
    private int f7983a;

    /* renamed from: b  reason: collision with root package name */
    private int f7984b;

    /* renamed from: c  reason: collision with root package name */
    private int f7985c;

    /* renamed from: d  reason: collision with root package name */
    private int f7986d;

    /* renamed from: e  reason: collision with root package name */
    private io.flutter.embedding.android.a f7987e;

    /* renamed from: f  reason: collision with root package name */
    private o f7988f;

    /* renamed from: g  reason: collision with root package name */
    private ViewTreeObserver.OnGlobalFocusChangeListener f7989g;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class a implements ViewTreeObserver.OnGlobalFocusChangeListener {

        /* renamed from: a  reason: collision with root package name */
        final /* synthetic */ View.OnFocusChangeListener f7990a;

        a(View.OnFocusChangeListener onFocusChangeListener) {
            this.f7990a = onFocusChangeListener;
        }

        @Override // android.view.ViewTreeObserver.OnGlobalFocusChangeListener
        public void onGlobalFocusChanged(View view, View view2) {
            View.OnFocusChangeListener onFocusChangeListener = this.f7990a;
            p pVar = p.this;
            onFocusChangeListener.onFocusChange(pVar, p6.h.d(pVar));
        }
    }

    public p(Context context) {
        super(context);
        setWillNotDraw(false);
    }

    public void a() {
        o oVar = this.f7988f;
        if (oVar != null) {
            oVar.release();
            this.f7988f = null;
        }
    }

    public void b(int i8, int i9) {
        o oVar = this.f7988f;
        if (oVar != null) {
            oVar.b(i8, i9);
        }
    }

    public void c() {
        ViewTreeObserver.OnGlobalFocusChangeListener onGlobalFocusChangeListener;
        ViewTreeObserver viewTreeObserver = getViewTreeObserver();
        if (!viewTreeObserver.isAlive() || (onGlobalFocusChangeListener = this.f7989g) == null) {
            return;
        }
        this.f7989g = null;
        viewTreeObserver.removeOnGlobalFocusChangeListener(onGlobalFocusChangeListener);
    }

    @Override // android.view.View
    public void draw(Canvas canvas) {
        o oVar = this.f7988f;
        if (oVar == null) {
            super.draw(canvas);
            z5.b.b("PlatformViewWrapper", "Platform view cannot be composed without a RenderTarget.");
            return;
        }
        Surface surface = oVar.getSurface();
        Canvas lockHardwareCanvas = surface.lockHardwareCanvas();
        if (lockHardwareCanvas == null) {
            invalidate();
            return;
        }
        try {
            lockHardwareCanvas.drawColor(0, PorterDuff.Mode.CLEAR);
            super.draw(lockHardwareCanvas);
        } finally {
            this.f7988f.scheduleFrame();
            surface.unlockCanvasAndPost(lockHardwareCanvas);
        }
    }

    public ViewTreeObserver.OnGlobalFocusChangeListener getActiveFocusListener() {
        return this.f7989g;
    }

    public int getRenderTargetHeight() {
        o oVar = this.f7988f;
        if (oVar != null) {
            return oVar.getHeight();
        }
        return 0;
    }

    public int getRenderTargetWidth() {
        o oVar = this.f7988f;
        if (oVar != null) {
            return oVar.getWidth();
        }
        return 0;
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public ViewParent invalidateChildInParent(int[] iArr, Rect rect) {
        invalidate();
        return super.invalidateChildInParent(iArr, rect);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public void onDescendantInvalidated(View view, View view2) {
        super.onDescendantInvalidated(view, view2);
        invalidate();
    }

    @Override // android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        return true;
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        int i8;
        float f8;
        if (this.f7987e == null) {
            return super.onTouchEvent(motionEvent);
        }
        Matrix matrix = new Matrix();
        int action = motionEvent.getAction();
        if (action == 0) {
            int i9 = this.f7985c;
            this.f7983a = i9;
            i8 = this.f7986d;
            this.f7984b = i8;
            f8 = i9;
        } else if (action == 2) {
            matrix.postTranslate(this.f7983a, this.f7984b);
            this.f7983a = this.f7985c;
            this.f7984b = this.f7986d;
            return this.f7987e.k(motionEvent, matrix);
        } else {
            f8 = this.f7985c;
            i8 = this.f7986d;
        }
        matrix.postTranslate(f8, i8);
        return this.f7987e.k(motionEvent, matrix);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public boolean requestSendAccessibilityEvent(View view, AccessibilityEvent accessibilityEvent) {
        View childAt = getChildAt(0);
        if (childAt == null || childAt.getImportantForAccessibility() != 4) {
            return super.requestSendAccessibilityEvent(view, accessibilityEvent);
        }
        return false;
    }

    public void setLayoutParams(FrameLayout.LayoutParams layoutParams) {
        super.setLayoutParams((ViewGroup.LayoutParams) layoutParams);
        this.f7985c = layoutParams.leftMargin;
        this.f7986d = layoutParams.topMargin;
    }

    public void setOnDescendantFocusChangeListener(View.OnFocusChangeListener onFocusChangeListener) {
        c();
        ViewTreeObserver viewTreeObserver = getViewTreeObserver();
        if (viewTreeObserver.isAlive() && this.f7989g == null) {
            a aVar = new a(onFocusChangeListener);
            this.f7989g = aVar;
            viewTreeObserver.addOnGlobalFocusChangeListener(aVar);
        }
    }

    public void setTouchProcessor(io.flutter.embedding.android.a aVar) {
        this.f7987e = aVar;
    }

    public p(Context context, o oVar) {
        this(context);
        this.f7988f = oVar;
        Surface surface = oVar.getSurface();
        if (surface == null || FlutterRenderer.f7787j) {
            return;
        }
        Canvas lockHardwareCanvas = surface.lockHardwareCanvas();
        try {
            lockHardwareCanvas.drawColor(0, PorterDuff.Mode.CLEAR);
        } finally {
            surface.unlockCanvasAndPost(lockHardwareCanvas);
        }
    }
}
