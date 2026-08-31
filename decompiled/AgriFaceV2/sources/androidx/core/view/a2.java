package androidx.core.view;

import android.os.Build;
import android.view.View;
import android.view.Window;
import android.view.WindowInsetsController;
/* loaded from: classes.dex */
public final class a2 {

    /* renamed from: a  reason: collision with root package name */
    private final e f3071a;

    /* loaded from: classes.dex */
    private static class a extends e {

        /* renamed from: a  reason: collision with root package name */
        protected final Window f3072a;

        /* renamed from: b  reason: collision with root package name */
        private final l0 f3073b;

        a(Window window, l0 l0Var) {
            this.f3072a = window;
            this.f3073b = l0Var;
        }

        protected void c(int i8) {
            View decorView = this.f3072a.getDecorView();
            decorView.setSystemUiVisibility(i8 | decorView.getSystemUiVisibility());
        }

        protected void d(int i8) {
            this.f3072a.addFlags(i8);
        }

        protected void e(int i8) {
            View decorView = this.f3072a.getDecorView();
            decorView.setSystemUiVisibility((~i8) & decorView.getSystemUiVisibility());
        }

        protected void f(int i8) {
            this.f3072a.clearFlags(i8);
        }
    }

    /* loaded from: classes.dex */
    private static class b extends a {
        b(Window window, l0 l0Var) {
            super(window, l0Var);
        }

        @Override // androidx.core.view.a2.e
        public void b(boolean z7) {
            if (!z7) {
                e(8192);
                return;
            }
            f(67108864);
            d(Integer.MIN_VALUE);
            c(8192);
        }
    }

    /* loaded from: classes.dex */
    private static class c extends b {
        c(Window window, l0 l0Var) {
            super(window, l0Var);
        }

        @Override // androidx.core.view.a2.e
        public void a(boolean z7) {
            if (!z7) {
                e(16);
                return;
            }
            f(134217728);
            d(Integer.MIN_VALUE);
            c(16);
        }
    }

    /* loaded from: classes.dex */
    private static class d extends e {

        /* renamed from: a  reason: collision with root package name */
        final a2 f3074a;

        /* renamed from: b  reason: collision with root package name */
        final WindowInsetsController f3075b;

        /* renamed from: c  reason: collision with root package name */
        final l0 f3076c;

        /* renamed from: d  reason: collision with root package name */
        private final b1.g f3077d;

        /* renamed from: e  reason: collision with root package name */
        protected Window f3078e;

        /* JADX WARN: Illegal instructions before constructor call */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        d(android.view.Window r2, androidx.core.view.a2 r3, androidx.core.view.l0 r4) {
            /*
                r1 = this;
                android.view.WindowInsetsController r0 = androidx.core.view.b2.a(r2)
                r1.<init>(r0, r3, r4)
                r1.f3078e = r2
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.core.view.a2.d.<init>(android.view.Window, androidx.core.view.a2, androidx.core.view.l0):void");
        }

        @Override // androidx.core.view.a2.e
        public void a(boolean z7) {
            if (z7) {
                if (this.f3078e != null) {
                    c(16);
                }
                this.f3075b.setSystemBarsAppearance(16, 16);
                return;
            }
            if (this.f3078e != null) {
                d(16);
            }
            this.f3075b.setSystemBarsAppearance(0, 16);
        }

        @Override // androidx.core.view.a2.e
        public void b(boolean z7) {
            if (z7) {
                if (this.f3078e != null) {
                    c(8192);
                }
                this.f3075b.setSystemBarsAppearance(8, 8);
                return;
            }
            if (this.f3078e != null) {
                d(8192);
            }
            this.f3075b.setSystemBarsAppearance(0, 8);
        }

        protected void c(int i8) {
            View decorView = this.f3078e.getDecorView();
            decorView.setSystemUiVisibility(i8 | decorView.getSystemUiVisibility());
        }

        protected void d(int i8) {
            View decorView = this.f3078e.getDecorView();
            decorView.setSystemUiVisibility((~i8) & decorView.getSystemUiVisibility());
        }

        d(WindowInsetsController windowInsetsController, a2 a2Var, l0 l0Var) {
            this.f3077d = new b1.g();
            this.f3075b = windowInsetsController;
            this.f3074a = a2Var;
            this.f3076c = l0Var;
        }
    }

    /* loaded from: classes.dex */
    private static class e {
        e() {
        }

        public abstract void a(boolean z7);

        public abstract void b(boolean z7);
    }

    public a2(Window window, View view) {
        l0 l0Var = new l0(view);
        this.f3071a = Build.VERSION.SDK_INT >= 30 ? new d(window, this, l0Var) : new c(window, l0Var);
    }

    public void a(boolean z7) {
        this.f3071a.a(z7);
    }

    public void b(boolean z7) {
        this.f3071a.b(z7);
    }
}
