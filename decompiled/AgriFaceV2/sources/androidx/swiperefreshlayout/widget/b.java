package androidx.swiperefreshlayout.widget;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import g1.e;
/* loaded from: classes.dex */
public class b extends Drawable implements Animatable {

    /* renamed from: g  reason: collision with root package name */
    private static final Interpolator f3939g = new LinearInterpolator();

    /* renamed from: h  reason: collision with root package name */
    private static final Interpolator f3940h = new t1.a();

    /* renamed from: i  reason: collision with root package name */
    private static final int[] f3941i = {-16777216};

    /* renamed from: a  reason: collision with root package name */
    private final c f3942a;

    /* renamed from: b  reason: collision with root package name */
    private float f3943b;

    /* renamed from: c  reason: collision with root package name */
    private Resources f3944c;

    /* renamed from: d  reason: collision with root package name */
    private Animator f3945d;

    /* renamed from: e  reason: collision with root package name */
    float f3946e;

    /* renamed from: f  reason: collision with root package name */
    boolean f3947f;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class a implements ValueAnimator.AnimatorUpdateListener {

        /* renamed from: a  reason: collision with root package name */
        final /* synthetic */ c f3948a;

        a(c cVar) {
            this.f3948a = cVar;
        }

        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            float floatValue = ((Float) valueAnimator.getAnimatedValue()).floatValue();
            b.this.n(floatValue, this.f3948a);
            b.this.b(floatValue, this.f3948a, false);
            b.this.invalidateSelf();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: androidx.swiperefreshlayout.widget.b$b  reason: collision with other inner class name */
    /* loaded from: classes.dex */
    public class C0050b implements Animator.AnimatorListener {

        /* renamed from: a  reason: collision with root package name */
        final /* synthetic */ c f3950a;

        C0050b(c cVar) {
            this.f3950a = cVar;
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationCancel(Animator animator) {
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationRepeat(Animator animator) {
            b.this.b(1.0f, this.f3950a, true);
            this.f3950a.A();
            this.f3950a.l();
            b bVar = b.this;
            if (!bVar.f3947f) {
                bVar.f3946e += 1.0f;
                return;
            }
            bVar.f3947f = false;
            animator.cancel();
            animator.setDuration(1332L);
            animator.start();
            this.f3950a.x(false);
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animator) {
            b.this.f3946e = 0.0f;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class c {

        /* renamed from: a  reason: collision with root package name */
        final RectF f3952a = new RectF();

        /* renamed from: b  reason: collision with root package name */
        final Paint f3953b;

        /* renamed from: c  reason: collision with root package name */
        final Paint f3954c;

        /* renamed from: d  reason: collision with root package name */
        final Paint f3955d;

        /* renamed from: e  reason: collision with root package name */
        float f3956e;

        /* renamed from: f  reason: collision with root package name */
        float f3957f;

        /* renamed from: g  reason: collision with root package name */
        float f3958g;

        /* renamed from: h  reason: collision with root package name */
        float f3959h;

        /* renamed from: i  reason: collision with root package name */
        int[] f3960i;

        /* renamed from: j  reason: collision with root package name */
        int f3961j;

        /* renamed from: k  reason: collision with root package name */
        float f3962k;

        /* renamed from: l  reason: collision with root package name */
        float f3963l;

        /* renamed from: m  reason: collision with root package name */
        float f3964m;

        /* renamed from: n  reason: collision with root package name */
        boolean f3965n;

        /* renamed from: o  reason: collision with root package name */
        Path f3966o;

        /* renamed from: p  reason: collision with root package name */
        float f3967p;

        /* renamed from: q  reason: collision with root package name */
        float f3968q;

        /* renamed from: r  reason: collision with root package name */
        int f3969r;

        /* renamed from: s  reason: collision with root package name */
        int f3970s;

        /* renamed from: t  reason: collision with root package name */
        int f3971t;

        /* renamed from: u  reason: collision with root package name */
        int f3972u;

        c() {
            Paint paint = new Paint();
            this.f3953b = paint;
            Paint paint2 = new Paint();
            this.f3954c = paint2;
            Paint paint3 = new Paint();
            this.f3955d = paint3;
            this.f3956e = 0.0f;
            this.f3957f = 0.0f;
            this.f3958g = 0.0f;
            this.f3959h = 5.0f;
            this.f3967p = 1.0f;
            this.f3971t = 255;
            paint.setStrokeCap(Paint.Cap.SQUARE);
            paint.setAntiAlias(true);
            paint.setStyle(Paint.Style.STROKE);
            paint2.setStyle(Paint.Style.FILL);
            paint2.setAntiAlias(true);
            paint3.setColor(0);
        }

        void A() {
            this.f3962k = this.f3956e;
            this.f3963l = this.f3957f;
            this.f3964m = this.f3958g;
        }

        void a(Canvas canvas, Rect rect) {
            RectF rectF = this.f3952a;
            float f8 = this.f3968q;
            float f9 = (this.f3959h / 2.0f) + f8;
            if (f8 <= 0.0f) {
                f9 = (Math.min(rect.width(), rect.height()) / 2.0f) - Math.max((this.f3969r * this.f3967p) / 2.0f, this.f3959h / 2.0f);
            }
            rectF.set(rect.centerX() - f9, rect.centerY() - f9, rect.centerX() + f9, rect.centerY() + f9);
            float f10 = this.f3956e;
            float f11 = this.f3958g;
            float f12 = (f10 + f11) * 360.0f;
            float f13 = ((this.f3957f + f11) * 360.0f) - f12;
            this.f3953b.setColor(this.f3972u);
            this.f3953b.setAlpha(this.f3971t);
            float f14 = this.f3959h / 2.0f;
            rectF.inset(f14, f14);
            canvas.drawCircle(rectF.centerX(), rectF.centerY(), rectF.width() / 2.0f, this.f3955d);
            float f15 = -f14;
            rectF.inset(f15, f15);
            canvas.drawArc(rectF, f12, f13, false, this.f3953b);
            b(canvas, f12, f13, rectF);
        }

        void b(Canvas canvas, float f8, float f9, RectF rectF) {
            if (this.f3965n) {
                Path path = this.f3966o;
                if (path == null) {
                    Path path2 = new Path();
                    this.f3966o = path2;
                    path2.setFillType(Path.FillType.EVEN_ODD);
                } else {
                    path.reset();
                }
                this.f3966o.moveTo(0.0f, 0.0f);
                this.f3966o.lineTo(this.f3969r * this.f3967p, 0.0f);
                Path path3 = this.f3966o;
                float f10 = this.f3967p;
                path3.lineTo((this.f3969r * f10) / 2.0f, this.f3970s * f10);
                this.f3966o.offset(((Math.min(rectF.width(), rectF.height()) / 2.0f) + rectF.centerX()) - ((this.f3969r * this.f3967p) / 2.0f), rectF.centerY() + (this.f3959h / 2.0f));
                this.f3966o.close();
                this.f3954c.setColor(this.f3972u);
                this.f3954c.setAlpha(this.f3971t);
                canvas.save();
                canvas.rotate(f8 + f9, rectF.centerX(), rectF.centerY());
                canvas.drawPath(this.f3966o, this.f3954c);
                canvas.restore();
            }
        }

        int c() {
            return this.f3971t;
        }

        float d() {
            return this.f3957f;
        }

        int e() {
            return this.f3960i[f()];
        }

        int f() {
            return (this.f3961j + 1) % this.f3960i.length;
        }

        float g() {
            return this.f3956e;
        }

        int h() {
            return this.f3960i[this.f3961j];
        }

        float i() {
            return this.f3963l;
        }

        float j() {
            return this.f3964m;
        }

        float k() {
            return this.f3962k;
        }

        void l() {
            t(f());
        }

        void m() {
            this.f3962k = 0.0f;
            this.f3963l = 0.0f;
            this.f3964m = 0.0f;
            y(0.0f);
            v(0.0f);
            w(0.0f);
        }

        void n(int i8) {
            this.f3971t = i8;
        }

        void o(float f8, float f9) {
            this.f3969r = (int) f8;
            this.f3970s = (int) f9;
        }

        void p(float f8) {
            if (f8 != this.f3967p) {
                this.f3967p = f8;
            }
        }

        void q(float f8) {
            this.f3968q = f8;
        }

        void r(int i8) {
            this.f3972u = i8;
        }

        void s(ColorFilter colorFilter) {
            this.f3953b.setColorFilter(colorFilter);
        }

        void t(int i8) {
            this.f3961j = i8;
            this.f3972u = this.f3960i[i8];
        }

        void u(int[] iArr) {
            this.f3960i = iArr;
            t(0);
        }

        void v(float f8) {
            this.f3957f = f8;
        }

        void w(float f8) {
            this.f3958g = f8;
        }

        void x(boolean z7) {
            if (this.f3965n != z7) {
                this.f3965n = z7;
            }
        }

        void y(float f8) {
            this.f3956e = f8;
        }

        void z(float f8) {
            this.f3959h = f8;
            this.f3953b.setStrokeWidth(f8);
        }
    }

    public b(Context context) {
        this.f3944c = ((Context) e.j(context)).getResources();
        c cVar = new c();
        this.f3942a = cVar;
        cVar.u(f3941i);
        k(2.5f);
        m();
    }

    private void a(float f8, c cVar) {
        n(f8, cVar);
        cVar.y(cVar.k() + (((cVar.i() - 0.01f) - cVar.k()) * f8));
        cVar.v(cVar.i());
        cVar.w(cVar.j() + ((((float) (Math.floor(cVar.j() / 0.8f) + 1.0d)) - cVar.j()) * f8));
    }

    private int c(float f8, int i8, int i9) {
        int i10 = (i8 >> 24) & 255;
        int i11 = (i8 >> 16) & 255;
        int i12 = (i8 >> 8) & 255;
        int i13 = i8 & 255;
        return ((i10 + ((int) ((((i9 >> 24) & 255) - i10) * f8))) << 24) | ((i11 + ((int) ((((i9 >> 16) & 255) - i11) * f8))) << 16) | ((i12 + ((int) ((((i9 >> 8) & 255) - i12) * f8))) << 8) | (i13 + ((int) (f8 * ((i9 & 255) - i13))));
    }

    private void h(float f8) {
        this.f3943b = f8;
    }

    private void i(float f8, float f9, float f10, float f11) {
        c cVar = this.f3942a;
        float f12 = this.f3944c.getDisplayMetrics().density;
        cVar.z(f9 * f12);
        cVar.q(f8 * f12);
        cVar.t(0);
        cVar.o(f10 * f12, f11 * f12);
    }

    private void m() {
        c cVar = this.f3942a;
        ValueAnimator ofFloat = ValueAnimator.ofFloat(0.0f, 1.0f);
        ofFloat.addUpdateListener(new a(cVar));
        ofFloat.setRepeatCount(-1);
        ofFloat.setRepeatMode(1);
        ofFloat.setInterpolator(f3939g);
        ofFloat.addListener(new C0050b(cVar));
        this.f3945d = ofFloat;
    }

    void b(float f8, c cVar, boolean z7) {
        float interpolation;
        float f9;
        if (this.f3947f) {
            a(f8, cVar);
        } else if (f8 != 1.0f || z7) {
            float j8 = cVar.j();
            if (f8 < 0.5f) {
                interpolation = cVar.k();
                f9 = (f3940h.getInterpolation(f8 / 0.5f) * 0.79f) + 0.01f + interpolation;
            } else {
                float k8 = cVar.k() + 0.79f;
                interpolation = k8 - (((1.0f - f3940h.getInterpolation((f8 - 0.5f) / 0.5f)) * 0.79f) + 0.01f);
                f9 = k8;
            }
            cVar.y(interpolation);
            cVar.v(f9);
            cVar.w(j8 + (0.20999998f * f8));
            h((f8 + this.f3946e) * 216.0f);
        }
    }

    public void d(boolean z7) {
        this.f3942a.x(z7);
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        Rect bounds = getBounds();
        canvas.save();
        canvas.rotate(this.f3943b, bounds.exactCenterX(), bounds.exactCenterY());
        this.f3942a.a(canvas, bounds);
        canvas.restore();
    }

    public void e(float f8) {
        this.f3942a.p(f8);
        invalidateSelf();
    }

    public void f(int... iArr) {
        this.f3942a.u(iArr);
        this.f3942a.t(0);
        invalidateSelf();
    }

    public void g(float f8) {
        this.f3942a.w(f8);
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable
    public int getAlpha() {
        return this.f3942a.c();
    }

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        return -3;
    }

    @Override // android.graphics.drawable.Animatable
    public boolean isRunning() {
        return this.f3945d.isRunning();
    }

    public void j(float f8, float f9) {
        this.f3942a.y(f8);
        this.f3942a.v(f9);
        invalidateSelf();
    }

    public void k(float f8) {
        this.f3942a.z(f8);
        invalidateSelf();
    }

    public void l(int i8) {
        float f8;
        float f9;
        float f10;
        float f11;
        if (i8 == 0) {
            f8 = 12.0f;
            f9 = 6.0f;
            f10 = 11.0f;
            f11 = 3.0f;
        } else {
            f8 = 10.0f;
            f9 = 5.0f;
            f10 = 7.5f;
            f11 = 2.5f;
        }
        i(f10, f11, f8, f9);
        invalidateSelf();
    }

    void n(float f8, c cVar) {
        cVar.r(f8 > 0.75f ? c((f8 - 0.75f) / 0.25f, cVar.h(), cVar.e()) : cVar.h());
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int i8) {
        this.f3942a.n(i8);
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter colorFilter) {
        this.f3942a.s(colorFilter);
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Animatable
    public void start() {
        Animator animator;
        long j8;
        this.f3945d.cancel();
        this.f3942a.A();
        if (this.f3942a.d() != this.f3942a.g()) {
            this.f3947f = true;
            animator = this.f3945d;
            j8 = 666;
        } else {
            this.f3942a.t(0);
            this.f3942a.m();
            animator = this.f3945d;
            j8 = 1332;
        }
        animator.setDuration(j8);
        this.f3945d.start();
    }

    @Override // android.graphics.drawable.Animatable
    public void stop() {
        this.f3945d.cancel();
        h(0.0f);
        this.f3942a.x(false);
        this.f3942a.t(0);
        this.f3942a.m();
        invalidateSelf();
    }
}
