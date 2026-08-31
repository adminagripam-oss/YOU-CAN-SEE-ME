package androidx.core.widget;

import android.content.res.Resources;
import android.os.SystemClock;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AnimationUtils;
import android.view.animation.Interpolator;
import androidx.core.view.p0;
/* loaded from: classes.dex */
public abstract class a implements View.OnTouchListener {

    /* renamed from: r  reason: collision with root package name */
    private static final int f3228r = ViewConfiguration.getTapTimeout();

    /* renamed from: c  reason: collision with root package name */
    final View f3231c;

    /* renamed from: d  reason: collision with root package name */
    private Runnable f3232d;

    /* renamed from: g  reason: collision with root package name */
    private int f3235g;

    /* renamed from: h  reason: collision with root package name */
    private int f3236h;

    /* renamed from: l  reason: collision with root package name */
    private boolean f3240l;

    /* renamed from: m  reason: collision with root package name */
    boolean f3241m;

    /* renamed from: n  reason: collision with root package name */
    boolean f3242n;

    /* renamed from: o  reason: collision with root package name */
    boolean f3243o;

    /* renamed from: p  reason: collision with root package name */
    private boolean f3244p;

    /* renamed from: q  reason: collision with root package name */
    private boolean f3245q;

    /* renamed from: a  reason: collision with root package name */
    final C0034a f3229a = new C0034a();

    /* renamed from: b  reason: collision with root package name */
    private final Interpolator f3230b = new AccelerateInterpolator();

    /* renamed from: e  reason: collision with root package name */
    private float[] f3233e = {0.0f, 0.0f};

    /* renamed from: f  reason: collision with root package name */
    private float[] f3234f = {Float.MAX_VALUE, Float.MAX_VALUE};

    /* renamed from: i  reason: collision with root package name */
    private float[] f3237i = {0.0f, 0.0f};

    /* renamed from: j  reason: collision with root package name */
    private float[] f3238j = {0.0f, 0.0f};

    /* renamed from: k  reason: collision with root package name */
    private float[] f3239k = {Float.MAX_VALUE, Float.MAX_VALUE};

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: androidx.core.widget.a$a  reason: collision with other inner class name */
    /* loaded from: classes.dex */
    public static class C0034a {

        /* renamed from: a  reason: collision with root package name */
        private int f3246a;

        /* renamed from: b  reason: collision with root package name */
        private int f3247b;

        /* renamed from: c  reason: collision with root package name */
        private float f3248c;

        /* renamed from: d  reason: collision with root package name */
        private float f3249d;

        /* renamed from: j  reason: collision with root package name */
        private float f3255j;

        /* renamed from: k  reason: collision with root package name */
        private int f3256k;

        /* renamed from: e  reason: collision with root package name */
        private long f3250e = Long.MIN_VALUE;

        /* renamed from: i  reason: collision with root package name */
        private long f3254i = -1;

        /* renamed from: f  reason: collision with root package name */
        private long f3251f = 0;

        /* renamed from: g  reason: collision with root package name */
        private int f3252g = 0;

        /* renamed from: h  reason: collision with root package name */
        private int f3253h = 0;

        C0034a() {
        }

        private float e(long j8) {
            long j9 = this.f3250e;
            if (j8 < j9) {
                return 0.0f;
            }
            long j10 = this.f3254i;
            if (j10 < 0 || j8 < j10) {
                return a.e(((float) (j8 - j9)) / this.f3246a, 0.0f, 1.0f) * 0.5f;
            }
            float f8 = this.f3255j;
            return (1.0f - f8) + (f8 * a.e(((float) (j8 - j10)) / this.f3256k, 0.0f, 1.0f));
        }

        private float g(float f8) {
            return ((-4.0f) * f8 * f8) + (f8 * 4.0f);
        }

        public void a() {
            if (this.f3251f == 0) {
                throw new RuntimeException("Cannot compute scroll delta before calling start()");
            }
            long currentAnimationTimeMillis = AnimationUtils.currentAnimationTimeMillis();
            float g8 = g(e(currentAnimationTimeMillis));
            this.f3251f = currentAnimationTimeMillis;
            float f8 = ((float) (currentAnimationTimeMillis - this.f3251f)) * g8;
            this.f3252g = (int) (this.f3248c * f8);
            this.f3253h = (int) (f8 * this.f3249d);
        }

        public int b() {
            return this.f3252g;
        }

        public int c() {
            return this.f3253h;
        }

        public int d() {
            float f8 = this.f3248c;
            return (int) (f8 / Math.abs(f8));
        }

        public int f() {
            float f8 = this.f3249d;
            return (int) (f8 / Math.abs(f8));
        }

        public boolean h() {
            return this.f3254i > 0 && AnimationUtils.currentAnimationTimeMillis() > this.f3254i + ((long) this.f3256k);
        }

        public void i() {
            long currentAnimationTimeMillis = AnimationUtils.currentAnimationTimeMillis();
            this.f3256k = a.f((int) (currentAnimationTimeMillis - this.f3250e), 0, this.f3247b);
            this.f3255j = e(currentAnimationTimeMillis);
            this.f3254i = currentAnimationTimeMillis;
        }

        public void j(int i8) {
            this.f3247b = i8;
        }

        public void k(int i8) {
            this.f3246a = i8;
        }

        public void l(float f8, float f9) {
            this.f3248c = f8;
            this.f3249d = f9;
        }

        public void m() {
            long currentAnimationTimeMillis = AnimationUtils.currentAnimationTimeMillis();
            this.f3250e = currentAnimationTimeMillis;
            this.f3254i = -1L;
            this.f3251f = currentAnimationTimeMillis;
            this.f3255j = 0.5f;
            this.f3252g = 0;
            this.f3253h = 0;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public class b implements Runnable {
        b() {
        }

        @Override // java.lang.Runnable
        public void run() {
            a aVar = a.this;
            if (aVar.f3243o) {
                if (aVar.f3241m) {
                    aVar.f3241m = false;
                    aVar.f3229a.m();
                }
                C0034a c0034a = a.this.f3229a;
                if (c0034a.h() || !a.this.u()) {
                    a.this.f3243o = false;
                    return;
                }
                a aVar2 = a.this;
                if (aVar2.f3242n) {
                    aVar2.f3242n = false;
                    aVar2.c();
                }
                c0034a.a();
                a.this.j(c0034a.b(), c0034a.c());
                p0.I(a.this.f3231c, this);
            }
        }
    }

    public a(View view) {
        this.f3231c = view;
        float f8 = Resources.getSystem().getDisplayMetrics().density;
        float f9 = (int) ((1575.0f * f8) + 0.5f);
        o(f9, f9);
        float f10 = (int) ((f8 * 315.0f) + 0.5f);
        p(f10, f10);
        l(1);
        n(Float.MAX_VALUE, Float.MAX_VALUE);
        s(0.2f, 0.2f);
        t(1.0f, 1.0f);
        k(f3228r);
        r(500);
        q(500);
    }

    private float d(int i8, float f8, float f9, float f10) {
        float h8 = h(this.f3233e[i8], f9, this.f3234f[i8], f8);
        int i9 = (h8 > 0.0f ? 1 : (h8 == 0.0f ? 0 : -1));
        if (i9 == 0) {
            return 0.0f;
        }
        float f11 = this.f3237i[i8];
        float f12 = this.f3238j[i8];
        float f13 = this.f3239k[i8];
        float f14 = f11 * f10;
        return i9 > 0 ? e(h8 * f14, f12, f13) : -e((-h8) * f14, f12, f13);
    }

    static float e(float f8, float f9, float f10) {
        return f8 > f10 ? f10 : f8 < f9 ? f9 : f8;
    }

    static int f(int i8, int i9, int i10) {
        return i8 > i10 ? i10 : i8 < i9 ? i9 : i8;
    }

    private float g(float f8, float f9) {
        if (f9 == 0.0f) {
            return 0.0f;
        }
        int i8 = this.f3235g;
        if (i8 == 0 || i8 == 1) {
            if (f8 < f9) {
                if (f8 >= 0.0f) {
                    return 1.0f - (f8 / f9);
                }
                if (this.f3243o && i8 == 1) {
                    return 1.0f;
                }
            }
        } else if (i8 == 2 && f8 < 0.0f) {
            return f8 / (-f9);
        }
        return 0.0f;
    }

    private float h(float f8, float f9, float f10, float f11) {
        float interpolation;
        float e8 = e(f8 * f9, 0.0f, f10);
        float g8 = g(f9 - f11, e8) - g(f11, e8);
        if (g8 < 0.0f) {
            interpolation = -this.f3230b.getInterpolation(-g8);
        } else if (g8 <= 0.0f) {
            return 0.0f;
        } else {
            interpolation = this.f3230b.getInterpolation(g8);
        }
        return e(interpolation, -1.0f, 1.0f);
    }

    private void i() {
        if (this.f3241m) {
            this.f3243o = false;
        } else {
            this.f3229a.i();
        }
    }

    private void v() {
        int i8;
        if (this.f3232d == null) {
            this.f3232d = new b();
        }
        this.f3243o = true;
        this.f3241m = true;
        if (this.f3240l || (i8 = this.f3236h) <= 0) {
            this.f3232d.run();
        } else {
            p0.J(this.f3231c, this.f3232d, i8);
        }
        this.f3240l = true;
    }

    public abstract boolean a(int i8);

    public abstract boolean b(int i8);

    void c() {
        long uptimeMillis = SystemClock.uptimeMillis();
        MotionEvent obtain = MotionEvent.obtain(uptimeMillis, uptimeMillis, 3, 0.0f, 0.0f, 0);
        this.f3231c.onTouchEvent(obtain);
        obtain.recycle();
    }

    public abstract void j(int i8, int i9);

    public a k(int i8) {
        this.f3236h = i8;
        return this;
    }

    public a l(int i8) {
        this.f3235g = i8;
        return this;
    }

    public a m(boolean z7) {
        if (this.f3244p && !z7) {
            i();
        }
        this.f3244p = z7;
        return this;
    }

    public a n(float f8, float f9) {
        float[] fArr = this.f3234f;
        fArr[0] = f8;
        fArr[1] = f9;
        return this;
    }

    public a o(float f8, float f9) {
        float[] fArr = this.f3239k;
        fArr[0] = f8 / 1000.0f;
        fArr[1] = f9 / 1000.0f;
        return this;
    }

    /* JADX WARN: Code restructure failed: missing block: B:11:0x0013, code lost:
        if (r0 != 3) goto L12;
     */
    @Override // android.view.View.OnTouchListener
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean onTouch(android.view.View r6, android.view.MotionEvent r7) {
        /*
            r5 = this;
            boolean r0 = r5.f3244p
            r1 = 0
            if (r0 != 0) goto L6
            return r1
        L6:
            int r0 = r7.getActionMasked()
            r2 = 1
            if (r0 == 0) goto L1a
            if (r0 == r2) goto L16
            r3 = 2
            if (r0 == r3) goto L1e
            r6 = 3
            if (r0 == r6) goto L16
            goto L58
        L16:
            r5.i()
            goto L58
        L1a:
            r5.f3242n = r2
            r5.f3240l = r1
        L1e:
            float r0 = r7.getX()
            int r3 = r6.getWidth()
            float r3 = (float) r3
            android.view.View r4 = r5.f3231c
            int r4 = r4.getWidth()
            float r4 = (float) r4
            float r0 = r5.d(r1, r0, r3, r4)
            float r7 = r7.getY()
            int r6 = r6.getHeight()
            float r6 = (float) r6
            android.view.View r3 = r5.f3231c
            int r3 = r3.getHeight()
            float r3 = (float) r3
            float r6 = r5.d(r2, r7, r6, r3)
            androidx.core.widget.a$a r7 = r5.f3229a
            r7.l(r0, r6)
            boolean r6 = r5.f3243o
            if (r6 != 0) goto L58
            boolean r6 = r5.u()
            if (r6 == 0) goto L58
            r5.v()
        L58:
            boolean r6 = r5.f3245q
            if (r6 == 0) goto L61
            boolean r6 = r5.f3243o
            if (r6 == 0) goto L61
            r1 = r2
        L61:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.core.widget.a.onTouch(android.view.View, android.view.MotionEvent):boolean");
    }

    public a p(float f8, float f9) {
        float[] fArr = this.f3238j;
        fArr[0] = f8 / 1000.0f;
        fArr[1] = f9 / 1000.0f;
        return this;
    }

    public a q(int i8) {
        this.f3229a.j(i8);
        return this;
    }

    public a r(int i8) {
        this.f3229a.k(i8);
        return this;
    }

    public a s(float f8, float f9) {
        float[] fArr = this.f3233e;
        fArr[0] = f8;
        fArr[1] = f9;
        return this;
    }

    public a t(float f8, float f9) {
        float[] fArr = this.f3237i;
        fArr[0] = f8 / 1000.0f;
        fArr[1] = f9 / 1000.0f;
        return this;
    }

    boolean u() {
        C0034a c0034a = this.f3229a;
        int f8 = c0034a.f();
        int d8 = c0034a.d();
        return (f8 != 0 && b(f8)) || (d8 != 0 && a(d8));
    }
}
