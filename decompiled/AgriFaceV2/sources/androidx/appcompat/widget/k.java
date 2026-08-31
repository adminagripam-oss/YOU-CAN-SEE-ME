package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import androidx.appcompat.widget.s0;
/* loaded from: classes.dex */
public final class k {

    /* renamed from: b  reason: collision with root package name */
    private static final PorterDuff.Mode f2353b = PorterDuff.Mode.SRC_IN;

    /* renamed from: c  reason: collision with root package name */
    private static k f2354c;

    /* renamed from: a  reason: collision with root package name */
    private s0 f2355a;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class a implements s0.c {

        /* renamed from: a  reason: collision with root package name */
        private final int[] f2356a = {g.e.R, g.e.P, g.e.f6836a};

        /* renamed from: b  reason: collision with root package name */
        private final int[] f2357b = {g.e.f6850o, g.e.B, g.e.f6855t, g.e.f6851p, g.e.f6852q, g.e.f6854s, g.e.f6853r};

        /* renamed from: c  reason: collision with root package name */
        private final int[] f2358c = {g.e.O, g.e.Q, g.e.f6846k, g.e.K, g.e.L, g.e.M, g.e.N};

        /* renamed from: d  reason: collision with root package name */
        private final int[] f2359d = {g.e.f6858w, g.e.f6844i, g.e.f6857v};

        /* renamed from: e  reason: collision with root package name */
        private final int[] f2360e = {g.e.J, g.e.S};

        /* renamed from: f  reason: collision with root package name */
        private final int[] f2361f = {g.e.f6838c, g.e.f6842g, g.e.f6839d, g.e.f6843h};

        a() {
        }

        private boolean f(int[] iArr, int i8) {
            for (int i9 : iArr) {
                if (i9 == i8) {
                    return true;
                }
            }
            return false;
        }

        private ColorStateList g(Context context) {
            return h(context, 0);
        }

        private ColorStateList h(Context context, int i8) {
            int c8 = x0.c(context, g.a.f6811t);
            return new ColorStateList(new int[][]{x0.f2523b, x0.f2526e, x0.f2524c, x0.f2530i}, new int[]{x0.b(context, g.a.f6809r), androidx.core.graphics.a.c(c8, i8), androidx.core.graphics.a.c(c8, i8), i8});
        }

        private ColorStateList i(Context context) {
            return h(context, x0.c(context, g.a.f6808q));
        }

        private ColorStateList j(Context context) {
            return h(context, x0.c(context, g.a.f6809r));
        }

        private ColorStateList k(Context context) {
            int[][] iArr = new int[3];
            int[] iArr2 = new int[3];
            ColorStateList e8 = x0.e(context, g.a.f6813v);
            if (e8 == null || !e8.isStateful()) {
                iArr[0] = x0.f2523b;
                iArr2[0] = x0.b(context, g.a.f6813v);
                iArr[1] = x0.f2527f;
                iArr2[1] = x0.c(context, g.a.f6810s);
                iArr[2] = x0.f2530i;
                iArr2[2] = x0.c(context, g.a.f6813v);
            } else {
                int[] iArr3 = x0.f2523b;
                iArr[0] = iArr3;
                iArr2[0] = e8.getColorForState(iArr3, 0);
                iArr[1] = x0.f2527f;
                iArr2[1] = x0.c(context, g.a.f6810s);
                iArr[2] = x0.f2530i;
                iArr2[2] = e8.getDefaultColor();
            }
            return new ColorStateList(iArr, iArr2);
        }

        private LayerDrawable l(s0 s0Var, Context context, int i8) {
            BitmapDrawable bitmapDrawable;
            BitmapDrawable bitmapDrawable2;
            BitmapDrawable bitmapDrawable3;
            int dimensionPixelSize = context.getResources().getDimensionPixelSize(i8);
            Drawable i9 = s0Var.i(context, g.e.F);
            Drawable i10 = s0Var.i(context, g.e.G);
            if ((i9 instanceof BitmapDrawable) && i9.getIntrinsicWidth() == dimensionPixelSize && i9.getIntrinsicHeight() == dimensionPixelSize) {
                bitmapDrawable = (BitmapDrawable) i9;
                bitmapDrawable2 = new BitmapDrawable(bitmapDrawable.getBitmap());
            } else {
                Bitmap createBitmap = Bitmap.createBitmap(dimensionPixelSize, dimensionPixelSize, Bitmap.Config.ARGB_8888);
                Canvas canvas = new Canvas(createBitmap);
                i9.setBounds(0, 0, dimensionPixelSize, dimensionPixelSize);
                i9.draw(canvas);
                bitmapDrawable = new BitmapDrawable(createBitmap);
                bitmapDrawable2 = new BitmapDrawable(createBitmap);
            }
            bitmapDrawable2.setTileModeX(Shader.TileMode.REPEAT);
            if ((i10 instanceof BitmapDrawable) && i10.getIntrinsicWidth() == dimensionPixelSize && i10.getIntrinsicHeight() == dimensionPixelSize) {
                bitmapDrawable3 = (BitmapDrawable) i10;
            } else {
                Bitmap createBitmap2 = Bitmap.createBitmap(dimensionPixelSize, dimensionPixelSize, Bitmap.Config.ARGB_8888);
                Canvas canvas2 = new Canvas(createBitmap2);
                i10.setBounds(0, 0, dimensionPixelSize, dimensionPixelSize);
                i10.draw(canvas2);
                bitmapDrawable3 = new BitmapDrawable(createBitmap2);
            }
            LayerDrawable layerDrawable = new LayerDrawable(new Drawable[]{bitmapDrawable, bitmapDrawable3, bitmapDrawable2});
            layerDrawable.setId(0, 16908288);
            layerDrawable.setId(1, 16908303);
            layerDrawable.setId(2, 16908301);
            return layerDrawable;
        }

        private void m(Drawable drawable, int i8, PorterDuff.Mode mode) {
            if (k0.a(drawable)) {
                drawable = drawable.mutate();
            }
            if (mode == null) {
                mode = k.f2353b;
            }
            drawable.setColorFilter(k.e(i8, mode));
        }

        /* JADX WARN: Removed duplicated region for block: B:22:0x0051  */
        /* JADX WARN: Removed duplicated region for block: B:29:0x006c A[RETURN] */
        @Override // androidx.appcompat.widget.s0.c
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public boolean a(android.content.Context r8, int r9, android.graphics.drawable.Drawable r10) {
            /*
                r7 = this;
                android.graphics.PorterDuff$Mode r0 = androidx.appcompat.widget.k.a()
                int[] r1 = r7.f2356a
                boolean r1 = r7.f(r1, r9)
                r2 = 1
                r3 = 0
                r4 = -1
                if (r1 == 0) goto L15
                int r9 = g.a.f6812u
            L11:
                r1 = r0
                r5 = r2
            L13:
                r0 = r4
                goto L4f
            L15:
                int[] r1 = r7.f2358c
                boolean r1 = r7.f(r1, r9)
                if (r1 == 0) goto L20
                int r9 = g.a.f6810s
                goto L11
            L20:
                int[] r1 = r7.f2359d
                boolean r1 = r7.f(r1, r9)
                r5 = 16842801(0x1010031, float:2.3693695E-38)
                if (r1 == 0) goto L32
                android.graphics.PorterDuff$Mode r0 = android.graphics.PorterDuff.Mode.MULTIPLY
            L2d:
                r1 = r0
                r0 = r4
                r9 = r5
                r5 = r2
                goto L4f
            L32:
                int r1 = g.e.f6856u
                if (r9 != r1) goto L46
                r9 = 1109603123(0x42233333, float:40.8)
                int r9 = java.lang.Math.round(r9)
                r1 = 16842800(0x1010030, float:2.3693693E-38)
                r5 = r2
                r6 = r0
                r0 = r9
                r9 = r1
                r1 = r6
                goto L4f
            L46:
                int r1 = g.e.f6847l
                if (r9 != r1) goto L4b
                goto L2d
            L4b:
                r1 = r0
                r9 = r3
                r5 = r9
                goto L13
            L4f:
                if (r5 == 0) goto L6c
                boolean r3 = androidx.appcompat.widget.k0.a(r10)
                if (r3 == 0) goto L5b
                android.graphics.drawable.Drawable r10 = r10.mutate()
            L5b:
                int r8 = androidx.appcompat.widget.x0.c(r8, r9)
                android.graphics.PorterDuffColorFilter r8 = androidx.appcompat.widget.k.e(r8, r1)
                r10.setColorFilter(r8)
                if (r0 == r4) goto L6b
                r10.setAlpha(r0)
            L6b:
                return r2
            L6c:
                return r3
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.widget.k.a.a(android.content.Context, int, android.graphics.drawable.Drawable):boolean");
        }

        @Override // androidx.appcompat.widget.s0.c
        public PorterDuff.Mode b(int i8) {
            if (i8 == g.e.H) {
                return PorterDuff.Mode.MULTIPLY;
            }
            return null;
        }

        @Override // androidx.appcompat.widget.s0.c
        public Drawable c(s0 s0Var, Context context, int i8) {
            int i9;
            if (i8 == g.e.f6845j) {
                return new LayerDrawable(new Drawable[]{s0Var.i(context, g.e.f6844i), s0Var.i(context, g.e.f6846k)});
            }
            if (i8 == g.e.f6860y) {
                i9 = g.d.f6833g;
            } else if (i8 == g.e.f6859x) {
                i9 = g.d.f6834h;
            } else if (i8 != g.e.f6861z) {
                return null;
            } else {
                i9 = g.d.f6835i;
            }
            return l(s0Var, context, i9);
        }

        @Override // androidx.appcompat.widget.s0.c
        public ColorStateList d(Context context, int i8) {
            if (i8 == g.e.f6848m) {
                return h.a.a(context, g.c.f6823e);
            }
            if (i8 == g.e.I) {
                return h.a.a(context, g.c.f6826h);
            }
            if (i8 == g.e.H) {
                return k(context);
            }
            if (i8 == g.e.f6841f) {
                return j(context);
            }
            if (i8 == g.e.f6837b) {
                return g(context);
            }
            if (i8 == g.e.f6840e) {
                return i(context);
            }
            if (i8 == g.e.D || i8 == g.e.E) {
                return h.a.a(context, g.c.f6825g);
            }
            if (f(this.f2357b, i8)) {
                return x0.e(context, g.a.f6812u);
            }
            if (f(this.f2360e, i8)) {
                return h.a.a(context, g.c.f6822d);
            }
            if (f(this.f2361f, i8)) {
                return h.a.a(context, g.c.f6821c);
            }
            if (i8 == g.e.A) {
                return h.a.a(context, g.c.f6824f);
            }
            return null;
        }

        @Override // androidx.appcompat.widget.s0.c
        public boolean e(Context context, int i8, Drawable drawable) {
            LayerDrawable layerDrawable;
            Drawable findDrawableByLayerId;
            int i9;
            if (i8 == g.e.C) {
                layerDrawable = (LayerDrawable) drawable;
                m(layerDrawable.findDrawableByLayerId(16908288), x0.c(context, g.a.f6812u), k.f2353b);
                findDrawableByLayerId = layerDrawable.findDrawableByLayerId(16908303);
                i9 = g.a.f6812u;
            } else if (i8 != g.e.f6860y && i8 != g.e.f6859x && i8 != g.e.f6861z) {
                return false;
            } else {
                layerDrawable = (LayerDrawable) drawable;
                m(layerDrawable.findDrawableByLayerId(16908288), x0.b(context, g.a.f6812u), k.f2353b);
                findDrawableByLayerId = layerDrawable.findDrawableByLayerId(16908303);
                i9 = g.a.f6810s;
            }
            m(findDrawableByLayerId, x0.c(context, i9), k.f2353b);
            m(layerDrawable.findDrawableByLayerId(16908301), x0.c(context, g.a.f6810s), k.f2353b);
            return true;
        }
    }

    public static synchronized k b() {
        k kVar;
        synchronized (k.class) {
            if (f2354c == null) {
                h();
            }
            kVar = f2354c;
        }
        return kVar;
    }

    public static synchronized PorterDuffColorFilter e(int i8, PorterDuff.Mode mode) {
        PorterDuffColorFilter k8;
        synchronized (k.class) {
            k8 = s0.k(i8, mode);
        }
        return k8;
    }

    public static synchronized void h() {
        synchronized (k.class) {
            if (f2354c == null) {
                k kVar = new k();
                f2354c = kVar;
                kVar.f2355a = s0.g();
                f2354c.f2355a.t(new a());
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void i(Drawable drawable, z0 z0Var, int[] iArr) {
        s0.v(drawable, z0Var, iArr);
    }

    public synchronized Drawable c(Context context, int i8) {
        return this.f2355a.i(context, i8);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public synchronized Drawable d(Context context, int i8, boolean z7) {
        return this.f2355a.j(context, i8, z7);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public synchronized ColorStateList f(Context context, int i8) {
        return this.f2355a.l(context, i8);
    }

    public synchronized void g(Context context) {
        this.f2355a.r(context);
    }
}
