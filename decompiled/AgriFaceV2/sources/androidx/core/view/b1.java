package androidx.core.view;

import android.graphics.Insets;
import android.graphics.Rect;
import android.os.Build;
import android.util.Log;
import android.view.DisplayCutout;
import android.view.View;
import android.view.WindowInsets;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Objects;
/* loaded from: classes.dex */
public class b1 {

    /* renamed from: b  reason: collision with root package name */
    public static final b1 f3082b;

    /* renamed from: a  reason: collision with root package name */
    private final l f3083a;

    /* loaded from: classes.dex */
    static class a {

        /* renamed from: a  reason: collision with root package name */
        private static Field f3084a;

        /* renamed from: b  reason: collision with root package name */
        private static Field f3085b;

        /* renamed from: c  reason: collision with root package name */
        private static Field f3086c;

        /* renamed from: d  reason: collision with root package name */
        private static boolean f3087d;

        static {
            try {
                Field declaredField = View.class.getDeclaredField("mAttachInfo");
                f3084a = declaredField;
                declaredField.setAccessible(true);
                Class<?> cls = Class.forName("android.view.View$AttachInfo");
                Field declaredField2 = cls.getDeclaredField("mStableInsets");
                f3085b = declaredField2;
                declaredField2.setAccessible(true);
                Field declaredField3 = cls.getDeclaredField("mContentInsets");
                f3086c = declaredField3;
                declaredField3.setAccessible(true);
                f3087d = true;
            } catch (ReflectiveOperationException e8) {
                Log.w("WindowInsetsCompat", "Failed to get visible insets from AttachInfo " + e8.getMessage(), e8);
            }
        }

        public static b1 a(View view) {
            if (f3087d && view.isAttachedToWindow()) {
                try {
                    Object obj = f3084a.get(view.getRootView());
                    if (obj != null) {
                        Rect rect = (Rect) f3085b.get(obj);
                        Rect rect2 = (Rect) f3086c.get(obj);
                        if (rect != null && rect2 != null) {
                            b1 a8 = new b().b(androidx.core.graphics.f.c(rect)).c(androidx.core.graphics.f.c(rect2)).a();
                            a8.r(a8);
                            a8.d(view.getRootView());
                            return a8;
                        }
                    }
                } catch (IllegalAccessException e8) {
                    Log.w("WindowInsetsCompat", "Failed to get insets from AttachInfo. " + e8.getMessage(), e8);
                }
            }
            return null;
        }
    }

    /* loaded from: classes.dex */
    public static final class b {

        /* renamed from: a  reason: collision with root package name */
        private final f f3088a;

        public b() {
            int i8 = Build.VERSION.SDK_INT;
            this.f3088a = i8 >= 30 ? new e() : i8 >= 29 ? new d() : new c();
        }

        public b1 a() {
            return this.f3088a.b();
        }

        public b b(androidx.core.graphics.f fVar) {
            this.f3088a.d(fVar);
            return this;
        }

        public b c(androidx.core.graphics.f fVar) {
            this.f3088a.f(fVar);
            return this;
        }

        public b(b1 b1Var) {
            int i8 = Build.VERSION.SDK_INT;
            this.f3088a = i8 >= 30 ? new e(b1Var) : i8 >= 29 ? new d(b1Var) : new c(b1Var);
        }
    }

    /* loaded from: classes.dex */
    private static class c extends f {

        /* renamed from: e  reason: collision with root package name */
        private static Field f3089e = null;

        /* renamed from: f  reason: collision with root package name */
        private static boolean f3090f = false;

        /* renamed from: g  reason: collision with root package name */
        private static Constructor f3091g = null;

        /* renamed from: h  reason: collision with root package name */
        private static boolean f3092h = false;

        /* renamed from: c  reason: collision with root package name */
        private WindowInsets f3093c;

        /* renamed from: d  reason: collision with root package name */
        private androidx.core.graphics.f f3094d;

        c() {
            this.f3093c = h();
        }

        private static WindowInsets h() {
            if (!f3090f) {
                try {
                    f3089e = WindowInsets.class.getDeclaredField("CONSUMED");
                } catch (ReflectiveOperationException e8) {
                    Log.i("WindowInsetsCompat", "Could not retrieve WindowInsets.CONSUMED field", e8);
                }
                f3090f = true;
            }
            Field field = f3089e;
            if (field != null) {
                try {
                    WindowInsets windowInsets = (WindowInsets) field.get(null);
                    if (windowInsets != null) {
                        return new WindowInsets(windowInsets);
                    }
                } catch (ReflectiveOperationException e9) {
                    Log.i("WindowInsetsCompat", "Could not get value from WindowInsets.CONSUMED field", e9);
                }
            }
            if (!f3092h) {
                try {
                    f3091g = WindowInsets.class.getConstructor(Rect.class);
                } catch (ReflectiveOperationException e10) {
                    Log.i("WindowInsetsCompat", "Could not retrieve WindowInsets(Rect) constructor", e10);
                }
                f3092h = true;
            }
            Constructor constructor = f3091g;
            if (constructor != null) {
                try {
                    return (WindowInsets) constructor.newInstance(new Rect());
                } catch (ReflectiveOperationException e11) {
                    Log.i("WindowInsetsCompat", "Could not invoke WindowInsets(Rect) constructor", e11);
                }
            }
            return null;
        }

        @Override // androidx.core.view.b1.f
        b1 b() {
            a();
            b1 u7 = b1.u(this.f3093c);
            u7.p(this.f3097b);
            u7.s(this.f3094d);
            return u7;
        }

        @Override // androidx.core.view.b1.f
        void d(androidx.core.graphics.f fVar) {
            this.f3094d = fVar;
        }

        @Override // androidx.core.view.b1.f
        void f(androidx.core.graphics.f fVar) {
            WindowInsets windowInsets = this.f3093c;
            if (windowInsets != null) {
                this.f3093c = windowInsets.replaceSystemWindowInsets(fVar.f2989a, fVar.f2990b, fVar.f2991c, fVar.f2992d);
            }
        }

        c(b1 b1Var) {
            super(b1Var);
            this.f3093c = b1Var.t();
        }
    }

    /* loaded from: classes.dex */
    private static class d extends f {

        /* renamed from: c  reason: collision with root package name */
        final WindowInsets.Builder f3095c;

        d() {
            this.f3095c = i1.a();
        }

        @Override // androidx.core.view.b1.f
        b1 b() {
            WindowInsets build;
            a();
            build = this.f3095c.build();
            b1 u7 = b1.u(build);
            u7.p(this.f3097b);
            return u7;
        }

        @Override // androidx.core.view.b1.f
        void c(androidx.core.graphics.f fVar) {
            this.f3095c.setMandatorySystemGestureInsets(fVar.e());
        }

        @Override // androidx.core.view.b1.f
        void d(androidx.core.graphics.f fVar) {
            this.f3095c.setStableInsets(fVar.e());
        }

        @Override // androidx.core.view.b1.f
        void e(androidx.core.graphics.f fVar) {
            this.f3095c.setSystemGestureInsets(fVar.e());
        }

        @Override // androidx.core.view.b1.f
        void f(androidx.core.graphics.f fVar) {
            this.f3095c.setSystemWindowInsets(fVar.e());
        }

        @Override // androidx.core.view.b1.f
        void g(androidx.core.graphics.f fVar) {
            this.f3095c.setTappableElementInsets(fVar.e());
        }

        d(b1 b1Var) {
            super(b1Var);
            WindowInsets t8 = b1Var.t();
            this.f3095c = t8 != null ? j1.a(t8) : i1.a();
        }
    }

    /* loaded from: classes.dex */
    private static class e extends d {
        e() {
        }

        e(b1 b1Var) {
            super(b1Var);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class f {

        /* renamed from: a  reason: collision with root package name */
        private final b1 f3096a;

        /* renamed from: b  reason: collision with root package name */
        androidx.core.graphics.f[] f3097b;

        f() {
            this(new b1((b1) null));
        }

        protected final void a() {
            androidx.core.graphics.f[] fVarArr = this.f3097b;
            if (fVarArr != null) {
                androidx.core.graphics.f fVar = fVarArr[m.d(1)];
                androidx.core.graphics.f fVar2 = this.f3097b[m.d(2)];
                if (fVar2 == null) {
                    fVar2 = this.f3096a.f(2);
                }
                if (fVar == null) {
                    fVar = this.f3096a.f(1);
                }
                f(androidx.core.graphics.f.a(fVar, fVar2));
                androidx.core.graphics.f fVar3 = this.f3097b[m.d(16)];
                if (fVar3 != null) {
                    e(fVar3);
                }
                androidx.core.graphics.f fVar4 = this.f3097b[m.d(32)];
                if (fVar4 != null) {
                    c(fVar4);
                }
                androidx.core.graphics.f fVar5 = this.f3097b[m.d(64)];
                if (fVar5 != null) {
                    g(fVar5);
                }
            }
        }

        abstract b1 b();

        void c(androidx.core.graphics.f fVar) {
        }

        abstract void d(androidx.core.graphics.f fVar);

        void e(androidx.core.graphics.f fVar) {
        }

        abstract void f(androidx.core.graphics.f fVar);

        void g(androidx.core.graphics.f fVar) {
        }

        f(b1 b1Var) {
            this.f3096a = b1Var;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class g extends l {

        /* renamed from: h  reason: collision with root package name */
        private static boolean f3098h = false;

        /* renamed from: i  reason: collision with root package name */
        private static Method f3099i;

        /* renamed from: j  reason: collision with root package name */
        private static Class f3100j;

        /* renamed from: k  reason: collision with root package name */
        private static Field f3101k;

        /* renamed from: l  reason: collision with root package name */
        private static Field f3102l;

        /* renamed from: c  reason: collision with root package name */
        final WindowInsets f3103c;

        /* renamed from: d  reason: collision with root package name */
        private androidx.core.graphics.f[] f3104d;

        /* renamed from: e  reason: collision with root package name */
        private androidx.core.graphics.f f3105e;

        /* renamed from: f  reason: collision with root package name */
        private b1 f3106f;

        /* renamed from: g  reason: collision with root package name */
        androidx.core.graphics.f f3107g;

        g(b1 b1Var, WindowInsets windowInsets) {
            super(b1Var);
            this.f3105e = null;
            this.f3103c = windowInsets;
        }

        private androidx.core.graphics.f t(int i8, boolean z7) {
            androidx.core.graphics.f fVar = androidx.core.graphics.f.f2988e;
            for (int i9 = 1; i9 <= 256; i9 <<= 1) {
                if ((i8 & i9) != 0) {
                    fVar = androidx.core.graphics.f.a(fVar, u(i9, z7));
                }
            }
            return fVar;
        }

        private androidx.core.graphics.f v() {
            b1 b1Var = this.f3106f;
            return b1Var != null ? b1Var.g() : androidx.core.graphics.f.f2988e;
        }

        private androidx.core.graphics.f w(View view) {
            if (Build.VERSION.SDK_INT < 30) {
                if (!f3098h) {
                    x();
                }
                Method method = f3099i;
                if (method != null && f3100j != null && f3101k != null) {
                    try {
                        Object invoke = method.invoke(view, new Object[0]);
                        if (invoke == null) {
                            Log.w("WindowInsetsCompat", "Failed to get visible insets. getViewRootImpl() returned null from the provided view. This means that the view is either not attached or the method has been overridden", new NullPointerException());
                            return null;
                        }
                        Rect rect = (Rect) f3101k.get(f3102l.get(invoke));
                        if (rect != null) {
                            return androidx.core.graphics.f.c(rect);
                        }
                        return null;
                    } catch (ReflectiveOperationException e8) {
                        Log.e("WindowInsetsCompat", "Failed to get visible insets. (Reflection error). " + e8.getMessage(), e8);
                    }
                }
                return null;
            }
            throw new UnsupportedOperationException("getVisibleInsets() should not be called on API >= 30. Use WindowInsets.isVisible() instead.");
        }

        private static void x() {
            try {
                f3099i = View.class.getDeclaredMethod("getViewRootImpl", new Class[0]);
                Class<?> cls = Class.forName("android.view.View$AttachInfo");
                f3100j = cls;
                f3101k = cls.getDeclaredField("mVisibleInsets");
                f3102l = Class.forName("android.view.ViewRootImpl").getDeclaredField("mAttachInfo");
                f3101k.setAccessible(true);
                f3102l.setAccessible(true);
            } catch (ReflectiveOperationException e8) {
                Log.e("WindowInsetsCompat", "Failed to get visible insets. (Reflection error). " + e8.getMessage(), e8);
            }
            f3098h = true;
        }

        @Override // androidx.core.view.b1.l
        void d(View view) {
            androidx.core.graphics.f w7 = w(view);
            if (w7 == null) {
                w7 = androidx.core.graphics.f.f2988e;
            }
            q(w7);
        }

        @Override // androidx.core.view.b1.l
        void e(b1 b1Var) {
            b1Var.r(this.f3106f);
            b1Var.q(this.f3107g);
        }

        @Override // androidx.core.view.b1.l
        public boolean equals(Object obj) {
            if (super.equals(obj)) {
                return Objects.equals(this.f3107g, ((g) obj).f3107g);
            }
            return false;
        }

        @Override // androidx.core.view.b1.l
        public androidx.core.graphics.f g(int i8) {
            return t(i8, false);
        }

        @Override // androidx.core.view.b1.l
        final androidx.core.graphics.f k() {
            if (this.f3105e == null) {
                this.f3105e = androidx.core.graphics.f.b(this.f3103c.getSystemWindowInsetLeft(), this.f3103c.getSystemWindowInsetTop(), this.f3103c.getSystemWindowInsetRight(), this.f3103c.getSystemWindowInsetBottom());
            }
            return this.f3105e;
        }

        @Override // androidx.core.view.b1.l
        b1 m(int i8, int i9, int i10, int i11) {
            b bVar = new b(b1.u(this.f3103c));
            bVar.c(b1.m(k(), i8, i9, i10, i11));
            bVar.b(b1.m(i(), i8, i9, i10, i11));
            return bVar.a();
        }

        @Override // androidx.core.view.b1.l
        boolean o() {
            return this.f3103c.isRound();
        }

        @Override // androidx.core.view.b1.l
        public void p(androidx.core.graphics.f[] fVarArr) {
            this.f3104d = fVarArr;
        }

        @Override // androidx.core.view.b1.l
        void q(androidx.core.graphics.f fVar) {
            this.f3107g = fVar;
        }

        @Override // androidx.core.view.b1.l
        void r(b1 b1Var) {
            this.f3106f = b1Var;
        }

        protected androidx.core.graphics.f u(int i8, boolean z7) {
            androidx.core.graphics.f g8;
            int i9;
            if (i8 == 1) {
                return z7 ? androidx.core.graphics.f.b(0, Math.max(v().f2990b, k().f2990b), 0, 0) : androidx.core.graphics.f.b(0, k().f2990b, 0, 0);
            }
            if (i8 == 2) {
                if (z7) {
                    androidx.core.graphics.f v7 = v();
                    androidx.core.graphics.f i10 = i();
                    return androidx.core.graphics.f.b(Math.max(v7.f2989a, i10.f2989a), 0, Math.max(v7.f2991c, i10.f2991c), Math.max(v7.f2992d, i10.f2992d));
                }
                androidx.core.graphics.f k8 = k();
                b1 b1Var = this.f3106f;
                g8 = b1Var != null ? b1Var.g() : null;
                int i11 = k8.f2992d;
                if (g8 != null) {
                    i11 = Math.min(i11, g8.f2992d);
                }
                return androidx.core.graphics.f.b(k8.f2989a, 0, k8.f2991c, i11);
            } else if (i8 != 8) {
                if (i8 != 16) {
                    if (i8 != 32) {
                        if (i8 != 64) {
                            if (i8 != 128) {
                                return androidx.core.graphics.f.f2988e;
                            }
                            b1 b1Var2 = this.f3106f;
                            r e8 = b1Var2 != null ? b1Var2.e() : f();
                            return e8 != null ? androidx.core.graphics.f.b(e8.b(), e8.d(), e8.c(), e8.a()) : androidx.core.graphics.f.f2988e;
                        }
                        return l();
                    }
                    return h();
                }
                return j();
            } else {
                androidx.core.graphics.f[] fVarArr = this.f3104d;
                g8 = fVarArr != null ? fVarArr[m.d(8)] : null;
                if (g8 != null) {
                    return g8;
                }
                androidx.core.graphics.f k9 = k();
                androidx.core.graphics.f v8 = v();
                int i12 = k9.f2992d;
                if (i12 > v8.f2992d) {
                    return androidx.core.graphics.f.b(0, 0, 0, i12);
                }
                androidx.core.graphics.f fVar = this.f3107g;
                return (fVar == null || fVar.equals(androidx.core.graphics.f.f2988e) || (i9 = this.f3107g.f2992d) <= v8.f2992d) ? androidx.core.graphics.f.f2988e : androidx.core.graphics.f.b(0, 0, 0, i9);
            }
        }

        g(b1 b1Var, g gVar) {
            this(b1Var, new WindowInsets(gVar.f3103c));
        }
    }

    /* loaded from: classes.dex */
    private static class h extends g {

        /* renamed from: m  reason: collision with root package name */
        private androidx.core.graphics.f f3108m;

        h(b1 b1Var, WindowInsets windowInsets) {
            super(b1Var, windowInsets);
            this.f3108m = null;
        }

        @Override // androidx.core.view.b1.l
        b1 b() {
            return b1.u(this.f3103c.consumeStableInsets());
        }

        @Override // androidx.core.view.b1.l
        b1 c() {
            return b1.u(this.f3103c.consumeSystemWindowInsets());
        }

        @Override // androidx.core.view.b1.l
        final androidx.core.graphics.f i() {
            if (this.f3108m == null) {
                this.f3108m = androidx.core.graphics.f.b(this.f3103c.getStableInsetLeft(), this.f3103c.getStableInsetTop(), this.f3103c.getStableInsetRight(), this.f3103c.getStableInsetBottom());
            }
            return this.f3108m;
        }

        @Override // androidx.core.view.b1.l
        boolean n() {
            return this.f3103c.isConsumed();
        }

        @Override // androidx.core.view.b1.l
        public void s(androidx.core.graphics.f fVar) {
            this.f3108m = fVar;
        }

        h(b1 b1Var, h hVar) {
            super(b1Var, hVar);
            this.f3108m = null;
            this.f3108m = hVar.f3108m;
        }
    }

    /* loaded from: classes.dex */
    private static class i extends h {
        i(b1 b1Var, WindowInsets windowInsets) {
            super(b1Var, windowInsets);
        }

        @Override // androidx.core.view.b1.l
        b1 a() {
            WindowInsets consumeDisplayCutout;
            consumeDisplayCutout = this.f3103c.consumeDisplayCutout();
            return b1.u(consumeDisplayCutout);
        }

        @Override // androidx.core.view.b1.g, androidx.core.view.b1.l
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj instanceof i) {
                i iVar = (i) obj;
                return Objects.equals(this.f3103c, iVar.f3103c) && Objects.equals(this.f3107g, iVar.f3107g);
            }
            return false;
        }

        @Override // androidx.core.view.b1.l
        r f() {
            DisplayCutout displayCutout;
            displayCutout = this.f3103c.getDisplayCutout();
            return r.e(displayCutout);
        }

        @Override // androidx.core.view.b1.l
        public int hashCode() {
            return this.f3103c.hashCode();
        }

        i(b1 b1Var, i iVar) {
            super(b1Var, iVar);
        }
    }

    /* loaded from: classes.dex */
    private static class j extends i {

        /* renamed from: n  reason: collision with root package name */
        private androidx.core.graphics.f f3109n;

        /* renamed from: o  reason: collision with root package name */
        private androidx.core.graphics.f f3110o;

        /* renamed from: p  reason: collision with root package name */
        private androidx.core.graphics.f f3111p;

        j(b1 b1Var, WindowInsets windowInsets) {
            super(b1Var, windowInsets);
            this.f3109n = null;
            this.f3110o = null;
            this.f3111p = null;
        }

        @Override // androidx.core.view.b1.l
        androidx.core.graphics.f h() {
            Insets mandatorySystemGestureInsets;
            if (this.f3110o == null) {
                mandatorySystemGestureInsets = this.f3103c.getMandatorySystemGestureInsets();
                this.f3110o = androidx.core.graphics.f.d(mandatorySystemGestureInsets);
            }
            return this.f3110o;
        }

        @Override // androidx.core.view.b1.l
        androidx.core.graphics.f j() {
            Insets systemGestureInsets;
            if (this.f3109n == null) {
                systemGestureInsets = this.f3103c.getSystemGestureInsets();
                this.f3109n = androidx.core.graphics.f.d(systemGestureInsets);
            }
            return this.f3109n;
        }

        @Override // androidx.core.view.b1.l
        androidx.core.graphics.f l() {
            Insets tappableElementInsets;
            if (this.f3111p == null) {
                tappableElementInsets = this.f3103c.getTappableElementInsets();
                this.f3111p = androidx.core.graphics.f.d(tappableElementInsets);
            }
            return this.f3111p;
        }

        @Override // androidx.core.view.b1.g, androidx.core.view.b1.l
        b1 m(int i8, int i9, int i10, int i11) {
            WindowInsets inset;
            inset = this.f3103c.inset(i8, i9, i10, i11);
            return b1.u(inset);
        }

        @Override // androidx.core.view.b1.h, androidx.core.view.b1.l
        public void s(androidx.core.graphics.f fVar) {
        }

        j(b1 b1Var, j jVar) {
            super(b1Var, jVar);
            this.f3109n = null;
            this.f3110o = null;
            this.f3111p = null;
        }
    }

    /* loaded from: classes.dex */
    private static class k extends j {

        /* renamed from: q  reason: collision with root package name */
        static final b1 f3112q;

        static {
            WindowInsets windowInsets;
            windowInsets = WindowInsets.CONSUMED;
            f3112q = b1.u(windowInsets);
        }

        k(b1 b1Var, WindowInsets windowInsets) {
            super(b1Var, windowInsets);
        }

        @Override // androidx.core.view.b1.g, androidx.core.view.b1.l
        final void d(View view) {
        }

        @Override // androidx.core.view.b1.g, androidx.core.view.b1.l
        public androidx.core.graphics.f g(int i8) {
            Insets insets;
            insets = this.f3103c.getInsets(n.a(i8));
            return androidx.core.graphics.f.d(insets);
        }

        k(b1 b1Var, k kVar) {
            super(b1Var, kVar);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class l {

        /* renamed from: b  reason: collision with root package name */
        static final b1 f3113b = new b().a().a().b().c();

        /* renamed from: a  reason: collision with root package name */
        final b1 f3114a;

        l(b1 b1Var) {
            this.f3114a = b1Var;
        }

        b1 a() {
            return this.f3114a;
        }

        b1 b() {
            return this.f3114a;
        }

        b1 c() {
            return this.f3114a;
        }

        void d(View view) {
        }

        void e(b1 b1Var) {
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj instanceof l) {
                l lVar = (l) obj;
                return o() == lVar.o() && n() == lVar.n() && g1.c.a(k(), lVar.k()) && g1.c.a(i(), lVar.i()) && g1.c.a(f(), lVar.f());
            }
            return false;
        }

        r f() {
            return null;
        }

        androidx.core.graphics.f g(int i8) {
            return androidx.core.graphics.f.f2988e;
        }

        androidx.core.graphics.f h() {
            return k();
        }

        public int hashCode() {
            return g1.c.b(Boolean.valueOf(o()), Boolean.valueOf(n()), k(), i(), f());
        }

        androidx.core.graphics.f i() {
            return androidx.core.graphics.f.f2988e;
        }

        androidx.core.graphics.f j() {
            return k();
        }

        androidx.core.graphics.f k() {
            return androidx.core.graphics.f.f2988e;
        }

        androidx.core.graphics.f l() {
            return k();
        }

        b1 m(int i8, int i9, int i10, int i11) {
            return f3113b;
        }

        boolean n() {
            return false;
        }

        boolean o() {
            return false;
        }

        public void p(androidx.core.graphics.f[] fVarArr) {
        }

        void q(androidx.core.graphics.f fVar) {
        }

        void r(b1 b1Var) {
        }

        public void s(androidx.core.graphics.f fVar) {
        }
    }

    /* loaded from: classes.dex */
    public static final class m {
        public static int a() {
            return 4;
        }

        public static int b() {
            return 128;
        }

        public static int c() {
            return 8;
        }

        static int d(int i8) {
            if (i8 != 1) {
                if (i8 != 2) {
                    if (i8 != 4) {
                        if (i8 != 8) {
                            if (i8 != 16) {
                                if (i8 != 32) {
                                    if (i8 != 64) {
                                        if (i8 != 128) {
                                            if (i8 == 256) {
                                                return 8;
                                            }
                                            throw new IllegalArgumentException("type needs to be >= FIRST and <= LAST, type=" + i8);
                                        }
                                        return 7;
                                    }
                                    return 6;
                                }
                                return 5;
                            }
                            return 4;
                        }
                        return 3;
                    }
                    return 2;
                }
                return 1;
            }
            return 0;
        }

        public static int e() {
            return 32;
        }

        public static int f() {
            return 2;
        }

        public static int g() {
            return 1;
        }

        public static int h() {
            return 16;
        }

        public static int i() {
            return 64;
        }
    }

    /* loaded from: classes.dex */
    private static final class n {
        static int a(int i8) {
            int statusBars;
            int i9 = 0;
            for (int i10 = 1; i10 <= 256; i10 <<= 1) {
                if ((i8 & i10) != 0) {
                    if (i10 == 1) {
                        statusBars = WindowInsets.Type.statusBars();
                    } else if (i10 == 2) {
                        statusBars = WindowInsets.Type.navigationBars();
                    } else if (i10 == 4) {
                        statusBars = WindowInsets.Type.captionBar();
                    } else if (i10 == 8) {
                        statusBars = WindowInsets.Type.ime();
                    } else if (i10 == 16) {
                        statusBars = WindowInsets.Type.systemGestures();
                    } else if (i10 == 32) {
                        statusBars = WindowInsets.Type.mandatorySystemGestures();
                    } else if (i10 == 64) {
                        statusBars = WindowInsets.Type.tappableElement();
                    } else if (i10 == 128) {
                        statusBars = WindowInsets.Type.displayCutout();
                    }
                    i9 |= statusBars;
                }
            }
            return i9;
        }
    }

    static {
        f3082b = Build.VERSION.SDK_INT >= 30 ? k.f3112q : l.f3113b;
    }

    private b1(WindowInsets windowInsets) {
        int i8 = Build.VERSION.SDK_INT;
        this.f3083a = i8 >= 30 ? new k(this, windowInsets) : i8 >= 29 ? new j(this, windowInsets) : i8 >= 28 ? new i(this, windowInsets) : new h(this, windowInsets);
    }

    static androidx.core.graphics.f m(androidx.core.graphics.f fVar, int i8, int i9, int i10, int i11) {
        int max = Math.max(0, fVar.f2989a - i8);
        int max2 = Math.max(0, fVar.f2990b - i9);
        int max3 = Math.max(0, fVar.f2991c - i10);
        int max4 = Math.max(0, fVar.f2992d - i11);
        return (max == i8 && max2 == i9 && max3 == i10 && max4 == i11) ? fVar : androidx.core.graphics.f.b(max, max2, max3, max4);
    }

    public static b1 u(WindowInsets windowInsets) {
        return v(windowInsets, null);
    }

    public static b1 v(WindowInsets windowInsets, View view) {
        b1 b1Var = new b1((WindowInsets) g1.e.j(windowInsets));
        if (view != null && view.isAttachedToWindow()) {
            b1Var.r(p0.s(view));
            b1Var.d(view.getRootView());
        }
        return b1Var;
    }

    public b1 a() {
        return this.f3083a.a();
    }

    public b1 b() {
        return this.f3083a.b();
    }

    public b1 c() {
        return this.f3083a.c();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void d(View view) {
        this.f3083a.d(view);
    }

    public r e() {
        return this.f3083a.f();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof b1) {
            return g1.c.a(this.f3083a, ((b1) obj).f3083a);
        }
        return false;
    }

    public androidx.core.graphics.f f(int i8) {
        return this.f3083a.g(i8);
    }

    public androidx.core.graphics.f g() {
        return this.f3083a.i();
    }

    public int h() {
        return this.f3083a.k().f2992d;
    }

    public int hashCode() {
        l lVar = this.f3083a;
        if (lVar == null) {
            return 0;
        }
        return lVar.hashCode();
    }

    public int i() {
        return this.f3083a.k().f2989a;
    }

    public int j() {
        return this.f3083a.k().f2991c;
    }

    public int k() {
        return this.f3083a.k().f2990b;
    }

    public b1 l(int i8, int i9, int i10, int i11) {
        return this.f3083a.m(i8, i9, i10, i11);
    }

    public boolean n() {
        return this.f3083a.n();
    }

    public b1 o(int i8, int i9, int i10, int i11) {
        return new b(this).c(androidx.core.graphics.f.b(i8, i9, i10, i11)).a();
    }

    void p(androidx.core.graphics.f[] fVarArr) {
        this.f3083a.p(fVarArr);
    }

    void q(androidx.core.graphics.f fVar) {
        this.f3083a.q(fVar);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void r(b1 b1Var) {
        this.f3083a.r(b1Var);
    }

    void s(androidx.core.graphics.f fVar) {
        this.f3083a.s(fVar);
    }

    public WindowInsets t() {
        l lVar = this.f3083a;
        if (lVar instanceof g) {
            return ((g) lVar).f3103c;
        }
        return null;
    }

    public b1(b1 b1Var) {
        if (b1Var == null) {
            this.f3083a = new l(this);
            return;
        }
        l lVar = b1Var.f3083a;
        int i8 = Build.VERSION.SDK_INT;
        this.f3083a = (i8 < 30 || !(lVar instanceof k)) ? (i8 < 29 || !(lVar instanceof j)) ? (i8 < 28 || !(lVar instanceof i)) ? lVar instanceof h ? new h(this, (h) lVar) : lVar instanceof g ? new g(this, (g) lVar) : new l(this) : new i(this, (i) lVar) : new j(this, (j) lVar) : new k(this, (k) lVar);
        lVar.e(this);
    }
}
