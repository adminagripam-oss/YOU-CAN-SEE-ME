package androidx.appcompat.widget;

import android.os.SystemClock;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewParent;
/* loaded from: classes.dex */
public abstract class n0 implements View.OnTouchListener, View.OnAttachStateChangeListener {

    /* renamed from: a  reason: collision with root package name */
    private final float f2396a;

    /* renamed from: b  reason: collision with root package name */
    private final int f2397b;

    /* renamed from: c  reason: collision with root package name */
    private final int f2398c;

    /* renamed from: d  reason: collision with root package name */
    final View f2399d;

    /* renamed from: e  reason: collision with root package name */
    private Runnable f2400e;

    /* renamed from: f  reason: collision with root package name */
    private Runnable f2401f;

    /* renamed from: g  reason: collision with root package name */
    private boolean f2402g;

    /* renamed from: h  reason: collision with root package name */
    private int f2403h;

    /* renamed from: i  reason: collision with root package name */
    private final int[] f2404i = new int[2];

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public class a implements Runnable {
        a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            ViewParent parent = n0.this.f2399d.getParent();
            if (parent != null) {
                parent.requestDisallowInterceptTouchEvent(true);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public class b implements Runnable {
        b() {
        }

        @Override // java.lang.Runnable
        public void run() {
            n0.this.e();
        }
    }

    public n0(View view) {
        this.f2399d = view;
        view.setLongClickable(true);
        view.addOnAttachStateChangeListener(this);
        this.f2396a = ViewConfiguration.get(view.getContext()).getScaledTouchSlop();
        int tapTimeout = ViewConfiguration.getTapTimeout();
        this.f2397b = tapTimeout;
        this.f2398c = (tapTimeout + ViewConfiguration.getLongPressTimeout()) / 2;
    }

    private void a() {
        Runnable runnable = this.f2401f;
        if (runnable != null) {
            this.f2399d.removeCallbacks(runnable);
        }
        Runnable runnable2 = this.f2400e;
        if (runnable2 != null) {
            this.f2399d.removeCallbacks(runnable2);
        }
    }

    private boolean f(MotionEvent motionEvent) {
        l0 l0Var;
        View view = this.f2399d;
        androidx.appcompat.view.menu.p b8 = b();
        if (b8 == null || !b8.c() || (l0Var = (l0) b8.h()) == null || !l0Var.isShown()) {
            return false;
        }
        MotionEvent obtainNoHistory = MotionEvent.obtainNoHistory(motionEvent);
        i(view, obtainNoHistory);
        j(l0Var, obtainNoHistory);
        boolean e8 = l0Var.e(obtainNoHistory, this.f2403h);
        obtainNoHistory.recycle();
        int actionMasked = motionEvent.getActionMasked();
        return e8 && (actionMasked != 1 && actionMasked != 3);
    }

    /* JADX WARN: Code restructure failed: missing block: B:12:0x0017, code lost:
        if (r1 != 3) goto L13;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private boolean g(android.view.MotionEvent r6) {
        /*
            r5 = this;
            android.view.View r0 = r5.f2399d
            boolean r1 = r0.isEnabled()
            r2 = 0
            if (r1 != 0) goto La
            return r2
        La:
            int r1 = r6.getActionMasked()
            if (r1 == 0) goto L41
            r3 = 1
            if (r1 == r3) goto L3d
            r4 = 2
            if (r1 == r4) goto L1a
            r6 = 3
            if (r1 == r6) goto L3d
            goto L6d
        L1a:
            int r1 = r5.f2403h
            int r1 = r6.findPointerIndex(r1)
            if (r1 < 0) goto L6d
            float r4 = r6.getX(r1)
            float r6 = r6.getY(r1)
            float r1 = r5.f2396a
            boolean r6 = h(r0, r4, r6, r1)
            if (r6 != 0) goto L6d
            r5.a()
            android.view.ViewParent r6 = r0.getParent()
            r6.requestDisallowInterceptTouchEvent(r3)
            return r3
        L3d:
            r5.a()
            goto L6d
        L41:
            int r6 = r6.getPointerId(r2)
            r5.f2403h = r6
            java.lang.Runnable r6 = r5.f2400e
            if (r6 != 0) goto L52
            androidx.appcompat.widget.n0$a r6 = new androidx.appcompat.widget.n0$a
            r6.<init>()
            r5.f2400e = r6
        L52:
            java.lang.Runnable r6 = r5.f2400e
            int r1 = r5.f2397b
            long r3 = (long) r1
            r0.postDelayed(r6, r3)
            java.lang.Runnable r6 = r5.f2401f
            if (r6 != 0) goto L65
            androidx.appcompat.widget.n0$b r6 = new androidx.appcompat.widget.n0$b
            r6.<init>()
            r5.f2401f = r6
        L65:
            java.lang.Runnable r6 = r5.f2401f
            int r1 = r5.f2398c
            long r3 = (long) r1
            r0.postDelayed(r6, r3)
        L6d:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.widget.n0.g(android.view.MotionEvent):boolean");
    }

    private static boolean h(View view, float f8, float f9, float f10) {
        float f11 = -f10;
        return f8 >= f11 && f9 >= f11 && f8 < ((float) (view.getRight() - view.getLeft())) + f10 && f9 < ((float) (view.getBottom() - view.getTop())) + f10;
    }

    private boolean i(View view, MotionEvent motionEvent) {
        int[] iArr = this.f2404i;
        view.getLocationOnScreen(iArr);
        motionEvent.offsetLocation(iArr[0], iArr[1]);
        return true;
    }

    private boolean j(View view, MotionEvent motionEvent) {
        int[] iArr = this.f2404i;
        view.getLocationOnScreen(iArr);
        motionEvent.offsetLocation(-iArr[0], -iArr[1]);
        return true;
    }

    public abstract androidx.appcompat.view.menu.p b();

    protected abstract boolean c();

    protected boolean d() {
        androidx.appcompat.view.menu.p b8 = b();
        if (b8 == null || !b8.c()) {
            return true;
        }
        b8.dismiss();
        return true;
    }

    void e() {
        a();
        View view = this.f2399d;
        if (view.isEnabled() && !view.isLongClickable() && c()) {
            view.getParent().requestDisallowInterceptTouchEvent(true);
            long uptimeMillis = SystemClock.uptimeMillis();
            MotionEvent obtain = MotionEvent.obtain(uptimeMillis, uptimeMillis, 3, 0.0f, 0.0f, 0);
            view.onTouchEvent(obtain);
            obtain.recycle();
            this.f2402g = true;
        }
    }

    @Override // android.view.View.OnTouchListener
    public boolean onTouch(View view, MotionEvent motionEvent) {
        boolean z7;
        boolean z8 = this.f2402g;
        if (z8) {
            z7 = f(motionEvent) || !d();
        } else {
            z7 = g(motionEvent) && c();
            if (z7) {
                long uptimeMillis = SystemClock.uptimeMillis();
                MotionEvent obtain = MotionEvent.obtain(uptimeMillis, uptimeMillis, 3, 0.0f, 0.0f, 0);
                this.f2399d.onTouchEvent(obtain);
                obtain.recycle();
            }
        }
        this.f2402g = z7;
        return z7 || z8;
    }

    @Override // android.view.View.OnAttachStateChangeListener
    public void onViewAttachedToWindow(View view) {
    }

    @Override // android.view.View.OnAttachStateChangeListener
    public void onViewDetachedFromWindow(View view) {
        this.f2402g = false;
        this.f2403h = -1;
        Runnable runnable = this.f2400e;
        if (runnable != null) {
            this.f2399d.removeCallbacks(runnable);
        }
    }
}
