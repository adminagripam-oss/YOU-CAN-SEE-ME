package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.LocaleList;
import android.text.method.PasswordTransformationMethod;
import android.util.AttributeSet;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.widget.TextView;
import androidx.core.content.res.f;
import java.lang.ref.WeakReference;
import java.util.Locale;
/* loaded from: classes.dex */
class c0 {

    /* renamed from: a  reason: collision with root package name */
    private final TextView f2244a;

    /* renamed from: b  reason: collision with root package name */
    private z0 f2245b;

    /* renamed from: c  reason: collision with root package name */
    private z0 f2246c;

    /* renamed from: d  reason: collision with root package name */
    private z0 f2247d;

    /* renamed from: e  reason: collision with root package name */
    private z0 f2248e;

    /* renamed from: f  reason: collision with root package name */
    private z0 f2249f;

    /* renamed from: g  reason: collision with root package name */
    private z0 f2250g;

    /* renamed from: h  reason: collision with root package name */
    private z0 f2251h;

    /* renamed from: i  reason: collision with root package name */
    private final e0 f2252i;

    /* renamed from: j  reason: collision with root package name */
    private int f2253j = 0;

    /* renamed from: k  reason: collision with root package name */
    private int f2254k = -1;

    /* renamed from: l  reason: collision with root package name */
    private Typeface f2255l;

    /* renamed from: m  reason: collision with root package name */
    private boolean f2256m;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class a extends f.e {

        /* renamed from: a  reason: collision with root package name */
        final /* synthetic */ int f2257a;

        /* renamed from: b  reason: collision with root package name */
        final /* synthetic */ int f2258b;

        /* renamed from: c  reason: collision with root package name */
        final /* synthetic */ WeakReference f2259c;

        a(int i8, int i9, WeakReference weakReference) {
            this.f2257a = i8;
            this.f2258b = i9;
            this.f2259c = weakReference;
        }

        @Override // androidx.core.content.res.f.e
        public void h(int i8) {
        }

        @Override // androidx.core.content.res.f.e
        public void i(Typeface typeface) {
            int i8;
            if (Build.VERSION.SDK_INT >= 28 && (i8 = this.f2257a) != -1) {
                typeface = f.a(typeface, i8, (this.f2258b & 2) != 0);
            }
            c0.this.n(this.f2259c, typeface);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class b implements Runnable {

        /* renamed from: e  reason: collision with root package name */
        final /* synthetic */ TextView f2261e;

        /* renamed from: f  reason: collision with root package name */
        final /* synthetic */ Typeface f2262f;

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ int f2263g;

        b(TextView textView, Typeface typeface, int i8) {
            this.f2261e = textView;
            this.f2262f = typeface;
            this.f2263g = i8;
        }

        @Override // java.lang.Runnable
        public void run() {
            this.f2261e.setTypeface(this.f2262f, this.f2263g);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static class c {
        static Drawable[] a(TextView textView) {
            return textView.getCompoundDrawablesRelative();
        }

        static void b(TextView textView, Drawable drawable, Drawable drawable2, Drawable drawable3, Drawable drawable4) {
            textView.setCompoundDrawablesRelativeWithIntrinsicBounds(drawable, drawable2, drawable3, drawable4);
        }

        static void c(TextView textView, Locale locale) {
            textView.setTextLocale(locale);
        }
    }

    /* loaded from: classes.dex */
    static class d {
        static LocaleList a(String str) {
            return LocaleList.forLanguageTags(str);
        }

        static void b(TextView textView, LocaleList localeList) {
            textView.setTextLocales(localeList);
        }
    }

    /* loaded from: classes.dex */
    static class e {
        static int a(TextView textView) {
            return textView.getAutoSizeStepGranularity();
        }

        static void b(TextView textView, int i8, int i9, int i10, int i11) {
            textView.setAutoSizeTextTypeUniformWithConfiguration(i8, i9, i10, i11);
        }

        static void c(TextView textView, int[] iArr, int i8) {
            textView.setAutoSizeTextTypeUniformWithPresetSizes(iArr, i8);
        }

        static boolean d(TextView textView, String str) {
            return textView.setFontVariationSettings(str);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static class f {
        static Typeface a(Typeface typeface, int i8, boolean z7) {
            return Typeface.create(typeface, i8, z7);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public c0(TextView textView) {
        this.f2244a = textView;
        this.f2252i = new e0(textView);
    }

    private void B(int i8, float f8) {
        this.f2252i.t(i8, f8);
    }

    private void C(Context context, b1 b1Var) {
        String n8;
        Typeface create;
        Typeface typeface;
        this.f2253j = b1Var.j(g.j.H2, this.f2253j);
        int i8 = Build.VERSION.SDK_INT;
        if (i8 >= 28) {
            int j8 = b1Var.j(g.j.J2, -1);
            this.f2254k = j8;
            if (j8 != -1) {
                this.f2253j = (this.f2253j & 2) | 0;
            }
        }
        if (!b1Var.r(g.j.I2) && !b1Var.r(g.j.K2)) {
            if (b1Var.r(g.j.G2)) {
                this.f2256m = false;
                int j9 = b1Var.j(g.j.G2, 1);
                if (j9 == 1) {
                    typeface = Typeface.SANS_SERIF;
                } else if (j9 == 2) {
                    typeface = Typeface.SERIF;
                } else if (j9 != 3) {
                    return;
                } else {
                    typeface = Typeface.MONOSPACE;
                }
                this.f2255l = typeface;
                return;
            }
            return;
        }
        this.f2255l = null;
        int i9 = b1Var.r(g.j.K2) ? g.j.K2 : g.j.I2;
        int i10 = this.f2254k;
        int i11 = this.f2253j;
        if (!context.isRestricted()) {
            try {
                Typeface i12 = b1Var.i(i9, this.f2253j, new a(i10, i11, new WeakReference(this.f2244a)));
                if (i12 != null) {
                    if (i8 >= 28 && this.f2254k != -1) {
                        i12 = f.a(Typeface.create(i12, 0), this.f2254k, (this.f2253j & 2) != 0);
                    }
                    this.f2255l = i12;
                }
                this.f2256m = this.f2255l == null;
            } catch (Resources.NotFoundException | UnsupportedOperationException unused) {
            }
        }
        if (this.f2255l != null || (n8 = b1Var.n(i9)) == null) {
            return;
        }
        if (Build.VERSION.SDK_INT < 28 || this.f2254k == -1) {
            create = Typeface.create(n8, this.f2253j);
        } else {
            create = f.a(Typeface.create(n8, 0), this.f2254k, (this.f2253j & 2) != 0);
        }
        this.f2255l = create;
    }

    private void a(Drawable drawable, z0 z0Var) {
        if (drawable == null || z0Var == null) {
            return;
        }
        k.i(drawable, z0Var, this.f2244a.getDrawableState());
    }

    private static z0 d(Context context, k kVar, int i8) {
        ColorStateList f8 = kVar.f(context, i8);
        if (f8 != null) {
            z0 z0Var = new z0();
            z0Var.f2546d = true;
            z0Var.f2543a = f8;
            return z0Var;
        }
        return null;
    }

    private void y(Drawable drawable, Drawable drawable2, Drawable drawable3, Drawable drawable4, Drawable drawable5, Drawable drawable6) {
        if (drawable5 != null || drawable6 != null) {
            Drawable[] a8 = c.a(this.f2244a);
            TextView textView = this.f2244a;
            if (drawable5 == null) {
                drawable5 = a8[0];
            }
            if (drawable2 == null) {
                drawable2 = a8[1];
            }
            if (drawable6 == null) {
                drawable6 = a8[2];
            }
            if (drawable4 == null) {
                drawable4 = a8[3];
            }
            c.b(textView, drawable5, drawable2, drawable6, drawable4);
        } else if (drawable == null && drawable2 == null && drawable3 == null && drawable4 == null) {
        } else {
            Drawable[] a9 = c.a(this.f2244a);
            Drawable drawable7 = a9[0];
            if (drawable7 != null || a9[2] != null) {
                TextView textView2 = this.f2244a;
                if (drawable2 == null) {
                    drawable2 = a9[1];
                }
                Drawable drawable8 = a9[2];
                if (drawable4 == null) {
                    drawable4 = a9[3];
                }
                c.b(textView2, drawable7, drawable2, drawable8, drawable4);
                return;
            }
            Drawable[] compoundDrawables = this.f2244a.getCompoundDrawables();
            TextView textView3 = this.f2244a;
            if (drawable == null) {
                drawable = compoundDrawables[0];
            }
            if (drawable2 == null) {
                drawable2 = compoundDrawables[1];
            }
            if (drawable3 == null) {
                drawable3 = compoundDrawables[2];
            }
            if (drawable4 == null) {
                drawable4 = compoundDrawables[3];
            }
            textView3.setCompoundDrawablesWithIntrinsicBounds(drawable, drawable2, drawable3, drawable4);
        }
    }

    private void z() {
        z0 z0Var = this.f2251h;
        this.f2245b = z0Var;
        this.f2246c = z0Var;
        this.f2247d = z0Var;
        this.f2248e = z0Var;
        this.f2249f = z0Var;
        this.f2250g = z0Var;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void A(int i8, float f8) {
        if (i1.f2346b || l()) {
            return;
        }
        B(i8, f8);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void b() {
        if (this.f2245b != null || this.f2246c != null || this.f2247d != null || this.f2248e != null) {
            Drawable[] compoundDrawables = this.f2244a.getCompoundDrawables();
            a(compoundDrawables[0], this.f2245b);
            a(compoundDrawables[1], this.f2246c);
            a(compoundDrawables[2], this.f2247d);
            a(compoundDrawables[3], this.f2248e);
        }
        if (this.f2249f == null && this.f2250g == null) {
            return;
        }
        Drawable[] a8 = c.a(this.f2244a);
        a(a8[0], this.f2249f);
        a(a8[2], this.f2250g);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void c() {
        this.f2252i.a();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int e() {
        return this.f2252i.f();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int f() {
        return this.f2252i.g();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int g() {
        return this.f2252i.h();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int[] h() {
        return this.f2252i.i();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int i() {
        return this.f2252i.j();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ColorStateList j() {
        z0 z0Var = this.f2251h;
        if (z0Var != null) {
            return z0Var.f2543a;
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public PorterDuff.Mode k() {
        z0 z0Var = this.f2251h;
        if (z0Var != null) {
            return z0Var.f2544b;
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean l() {
        return this.f2252i.n();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void m(AttributeSet attributeSet, int i8) {
        boolean z7;
        boolean z8;
        String str;
        String str2;
        boolean z9;
        Context context = this.f2244a.getContext();
        k b8 = k.b();
        b1 u7 = b1.u(context, attributeSet, g.j.Y, i8, 0);
        TextView textView = this.f2244a;
        androidx.core.view.p0.L(textView, textView.getContext(), g.j.Y, attributeSet, u7.q(), i8, 0);
        int m8 = u7.m(g.j.Z, -1);
        if (u7.r(g.j.f6934c0)) {
            this.f2245b = d(context, b8, u7.m(g.j.f6934c0, 0));
        }
        if (u7.r(g.j.f6924a0)) {
            this.f2246c = d(context, b8, u7.m(g.j.f6924a0, 0));
        }
        if (u7.r(g.j.f6939d0)) {
            this.f2247d = d(context, b8, u7.m(g.j.f6939d0, 0));
        }
        if (u7.r(g.j.f6929b0)) {
            this.f2248e = d(context, b8, u7.m(g.j.f6929b0, 0));
        }
        int i9 = Build.VERSION.SDK_INT;
        if (u7.r(g.j.f6944e0)) {
            this.f2249f = d(context, b8, u7.m(g.j.f6944e0, 0));
        }
        if (u7.r(g.j.f6949f0)) {
            this.f2250g = d(context, b8, u7.m(g.j.f6949f0, 0));
        }
        u7.v();
        boolean z10 = this.f2244a.getTransformationMethod() instanceof PasswordTransformationMethod;
        if (m8 != -1) {
            b1 s8 = b1.s(context, m8, g.j.E2);
            if (z10 || !s8.r(g.j.M2)) {
                z7 = false;
                z8 = false;
            } else {
                z7 = s8.a(g.j.M2, false);
                z8 = true;
            }
            C(context, s8);
            str = s8.r(g.j.N2) ? s8.n(g.j.N2) : null;
            str2 = s8.r(g.j.L2) ? s8.n(g.j.L2) : null;
            s8.v();
        } else {
            z7 = false;
            z8 = false;
            str = null;
            str2 = null;
        }
        b1 u8 = b1.u(context, attributeSet, g.j.E2, i8, 0);
        if (z10 || !u8.r(g.j.M2)) {
            z9 = z8;
        } else {
            z7 = u8.a(g.j.M2, false);
            z9 = true;
        }
        if (u8.r(g.j.N2)) {
            str = u8.n(g.j.N2);
        }
        if (u8.r(g.j.L2)) {
            str2 = u8.n(g.j.L2);
        }
        if (i9 >= 28 && u8.r(g.j.F2) && u8.e(g.j.F2, -1) == 0) {
            this.f2244a.setTextSize(0, 0.0f);
        }
        C(context, u8);
        u8.v();
        if (!z10 && z9) {
            s(z7);
        }
        Typeface typeface = this.f2255l;
        if (typeface != null) {
            if (this.f2254k == -1) {
                this.f2244a.setTypeface(typeface, this.f2253j);
            } else {
                this.f2244a.setTypeface(typeface);
            }
        }
        if (str2 != null) {
            e.d(this.f2244a, str2);
        }
        if (str != null) {
            d.b(this.f2244a, d.a(str));
        }
        this.f2252i.o(attributeSet, i8);
        if (i1.f2346b && this.f2252i.j() != 0) {
            int[] i10 = this.f2252i.i();
            if (i10.length > 0) {
                if (e.a(this.f2244a) != -1.0f) {
                    e.b(this.f2244a, this.f2252i.g(), this.f2252i.f(), this.f2252i.h(), 0);
                } else {
                    e.c(this.f2244a, i10, 0);
                }
            }
        }
        b1 t8 = b1.t(context, attributeSet, g.j.f6954g0);
        int m9 = t8.m(g.j.f6994o0, -1);
        Drawable c8 = m9 != -1 ? b8.c(context, m9) : null;
        int m10 = t8.m(g.j.f7019t0, -1);
        Drawable c9 = m10 != -1 ? b8.c(context, m10) : null;
        int m11 = t8.m(g.j.f6999p0, -1);
        Drawable c10 = m11 != -1 ? b8.c(context, m11) : null;
        int m12 = t8.m(g.j.f6984m0, -1);
        Drawable c11 = m12 != -1 ? b8.c(context, m12) : null;
        int m13 = t8.m(g.j.f7004q0, -1);
        Drawable c12 = m13 != -1 ? b8.c(context, m13) : null;
        int m14 = t8.m(g.j.f6989n0, -1);
        y(c8, c9, c10, c11, c12, m14 != -1 ? b8.c(context, m14) : null);
        if (t8.r(g.j.f7009r0)) {
            androidx.core.widget.i.f(this.f2244a, t8.c(g.j.f7009r0));
        }
        if (t8.r(g.j.f7014s0)) {
            androidx.core.widget.i.g(this.f2244a, k0.d(t8.j(g.j.f7014s0, -1), null));
        }
        int e8 = t8.e(g.j.f7029v0, -1);
        int e9 = t8.e(g.j.f7034w0, -1);
        int e10 = t8.e(g.j.f7039x0, -1);
        t8.v();
        if (e8 != -1) {
            androidx.core.widget.i.h(this.f2244a, e8);
        }
        if (e9 != -1) {
            androidx.core.widget.i.i(this.f2244a, e9);
        }
        if (e10 != -1) {
            androidx.core.widget.i.j(this.f2244a, e10);
        }
    }

    void n(WeakReference weakReference, Typeface typeface) {
        if (this.f2256m) {
            this.f2255l = typeface;
            TextView textView = (TextView) weakReference.get();
            if (textView != null) {
                if (androidx.core.view.p0.y(textView)) {
                    textView.post(new b(textView, typeface, this.f2253j));
                } else {
                    textView.setTypeface(typeface, this.f2253j);
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void o(boolean z7, int i8, int i9, int i10, int i11) {
        if (i1.f2346b) {
            return;
        }
        c();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void p() {
        b();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void q(Context context, int i8) {
        String n8;
        b1 s8 = b1.s(context, i8, g.j.E2);
        if (s8.r(g.j.M2)) {
            s(s8.a(g.j.M2, false));
        }
        if (s8.r(g.j.F2) && s8.e(g.j.F2, -1) == 0) {
            this.f2244a.setTextSize(0, 0.0f);
        }
        C(context, s8);
        if (s8.r(g.j.L2) && (n8 = s8.n(g.j.L2)) != null) {
            e.d(this.f2244a, n8);
        }
        s8.v();
        Typeface typeface = this.f2255l;
        if (typeface != null) {
            this.f2244a.setTypeface(typeface, this.f2253j);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void r(TextView textView, InputConnection inputConnection, EditorInfo editorInfo) {
        if (Build.VERSION.SDK_INT >= 30 || inputConnection == null) {
            return;
        }
        k1.a.e(editorInfo, textView.getText());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void s(boolean z7) {
        this.f2244a.setAllCaps(z7);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void t(int i8, int i9, int i10, int i11) {
        this.f2252i.p(i8, i9, i10, i11);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void u(int[] iArr, int i8) {
        this.f2252i.q(iArr, i8);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void v(int i8) {
        this.f2252i.r(i8);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void w(ColorStateList colorStateList) {
        if (this.f2251h == null) {
            this.f2251h = new z0();
        }
        z0 z0Var = this.f2251h;
        z0Var.f2543a = colorStateList;
        z0Var.f2546d = colorStateList != null;
        z();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void x(PorterDuff.Mode mode) {
        if (this.f2251h == null) {
            this.f2251h = new z0();
        }
        z0 z0Var = this.f2251h;
        z0Var.f2544b = mode;
        z0Var.f2545c = mode != null;
        z();
    }
}
