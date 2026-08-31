package d6;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Path;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.accessibility.AccessibilityEvent;
import android.widget.FrameLayout;
import io.flutter.embedding.engine.mutatorsstack.FlutterMutatorsStack;
import p6.h;
/* loaded from: classes.dex */
public class a extends FrameLayout {

    /* renamed from: a  reason: collision with root package name */
    private FlutterMutatorsStack f6448a;

    /* renamed from: b  reason: collision with root package name */
    private float f6449b;

    /* renamed from: c  reason: collision with root package name */
    private int f6450c;

    /* renamed from: d  reason: collision with root package name */
    private int f6451d;

    /* renamed from: e  reason: collision with root package name */
    private int f6452e;

    /* renamed from: f  reason: collision with root package name */
    private int f6453f;

    /* renamed from: g  reason: collision with root package name */
    private final io.flutter.embedding.android.a f6454g;

    /* renamed from: h  reason: collision with root package name */
    ViewTreeObserver.OnGlobalFocusChangeListener f6455h;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: d6.a$a  reason: collision with other inner class name */
    /* loaded from: classes.dex */
    public class ViewTreeObserver$OnGlobalFocusChangeListenerC0079a implements ViewTreeObserver.OnGlobalFocusChangeListener {

        /* renamed from: a  reason: collision with root package name */
        final /* synthetic */ View.OnFocusChangeListener f6456a;

        /* renamed from: b  reason: collision with root package name */
        final /* synthetic */ View f6457b;

        ViewTreeObserver$OnGlobalFocusChangeListenerC0079a(View.OnFocusChangeListener onFocusChangeListener, View view) {
            this.f6456a = onFocusChangeListener;
            this.f6457b = view;
        }

        @Override // android.view.ViewTreeObserver.OnGlobalFocusChangeListener
        public void onGlobalFocusChanged(View view, View view2) {
            View.OnFocusChangeListener onFocusChangeListener = this.f6456a;
            View view3 = this.f6457b;
            onFocusChangeListener.onFocusChange(view3, h.d(view3));
        }
    }

    public a(Context context, float f8, io.flutter.embedding.android.a aVar) {
        super(context, null);
        this.f6449b = f8;
        this.f6454g = aVar;
    }

    private Matrix getPlatformViewMatrix() {
        Matrix matrix = new Matrix(this.f6448a.getFinalMatrix());
        float f8 = this.f6449b;
        matrix.preScale(1.0f / f8, 1.0f / f8);
        matrix.postTranslate(-this.f6450c, -this.f6451d);
        return matrix;
    }

    public void a(FlutterMutatorsStack flutterMutatorsStack, int i8, int i9, int i10, int i11) {
        this.f6448a = flutterMutatorsStack;
        this.f6450c = i8;
        this.f6451d = i9;
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(i10, i11);
        layoutParams.leftMargin = i8;
        layoutParams.topMargin = i9;
        setLayoutParams(layoutParams);
        setWillNotDraw(false);
    }

    public void b() {
        ViewTreeObserver.OnGlobalFocusChangeListener onGlobalFocusChangeListener;
        ViewTreeObserver viewTreeObserver = getViewTreeObserver();
        if (!viewTreeObserver.isAlive() || (onGlobalFocusChangeListener = this.f6455h) == null) {
            return;
        }
        this.f6455h = null;
        viewTreeObserver.removeOnGlobalFocusChangeListener(onGlobalFocusChangeListener);
    }

    @Override // android.view.ViewGroup, android.view.View
    public void dispatchDraw(Canvas canvas) {
        canvas.save();
        canvas.concat(getPlatformViewMatrix());
        super.dispatchDraw(canvas);
        canvas.restore();
    }

    @Override // android.view.View
    public void draw(Canvas canvas) {
        canvas.save();
        for (Path path : this.f6448a.getFinalClippingPaths()) {
            Path path2 = new Path(path);
            path2.offset(-this.f6450c, -this.f6451d);
            canvas.clipPath(path2);
        }
        super.draw(canvas);
        canvas.restore();
    }

    @Override // android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        return true;
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        int i8;
        float f8;
        if (this.f6454g == null) {
            return super.onTouchEvent(motionEvent);
        }
        Matrix matrix = new Matrix();
        int action = motionEvent.getAction();
        if (action == 0) {
            int i9 = this.f6450c;
            this.f6452e = i9;
            i8 = this.f6451d;
            this.f6453f = i8;
            f8 = i9;
        } else if (action == 2) {
            matrix.postTranslate(this.f6452e, this.f6453f);
            this.f6452e = this.f6450c;
            this.f6453f = this.f6451d;
            return this.f6454g.k(motionEvent, matrix);
        } else {
            f8 = this.f6450c;
            i8 = this.f6451d;
        }
        matrix.postTranslate(f8, i8);
        return this.f6454g.k(motionEvent, matrix);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public boolean requestSendAccessibilityEvent(View view, AccessibilityEvent accessibilityEvent) {
        View childAt = getChildAt(0);
        if (childAt == null || childAt.getImportantForAccessibility() != 4) {
            return super.requestSendAccessibilityEvent(view, accessibilityEvent);
        }
        return false;
    }

    public void setOnDescendantFocusChangeListener(View.OnFocusChangeListener onFocusChangeListener) {
        b();
        ViewTreeObserver viewTreeObserver = getViewTreeObserver();
        if (viewTreeObserver.isAlive() && this.f6455h == null) {
            ViewTreeObserver$OnGlobalFocusChangeListenerC0079a viewTreeObserver$OnGlobalFocusChangeListenerC0079a = new ViewTreeObserver$OnGlobalFocusChangeListenerC0079a(onFocusChangeListener, this);
            this.f6455h = viewTreeObserver$OnGlobalFocusChangeListenerC0079a;
            viewTreeObserver.addOnGlobalFocusChangeListener(viewTreeObserver$OnGlobalFocusChangeListenerC0079a);
        }
    }
}
