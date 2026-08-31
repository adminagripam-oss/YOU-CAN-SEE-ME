package androidx.appcompat.view;

import android.view.View;
import android.view.animation.Interpolator;
import androidx.core.view.x0;
import androidx.core.view.y0;
import androidx.core.view.z0;
import java.util.ArrayList;
import java.util.Iterator;
/* loaded from: classes.dex */
public class h {

    /* renamed from: c  reason: collision with root package name */
    private Interpolator f1771c;

    /* renamed from: d  reason: collision with root package name */
    y0 f1772d;

    /* renamed from: e  reason: collision with root package name */
    private boolean f1773e;

    /* renamed from: b  reason: collision with root package name */
    private long f1770b = -1;

    /* renamed from: f  reason: collision with root package name */
    private final z0 f1774f = new a();

    /* renamed from: a  reason: collision with root package name */
    final ArrayList f1769a = new ArrayList();

    /* loaded from: classes.dex */
    class a extends z0 {

        /* renamed from: a  reason: collision with root package name */
        private boolean f1775a = false;

        /* renamed from: b  reason: collision with root package name */
        private int f1776b = 0;

        a() {
        }

        @Override // androidx.core.view.y0
        public void b(View view) {
            int i8 = this.f1776b + 1;
            this.f1776b = i8;
            if (i8 == h.this.f1769a.size()) {
                y0 y0Var = h.this.f1772d;
                if (y0Var != null) {
                    y0Var.b(null);
                }
                d();
            }
        }

        @Override // androidx.core.view.z0, androidx.core.view.y0
        public void c(View view) {
            if (this.f1775a) {
                return;
            }
            this.f1775a = true;
            y0 y0Var = h.this.f1772d;
            if (y0Var != null) {
                y0Var.c(null);
            }
        }

        void d() {
            this.f1776b = 0;
            this.f1775a = false;
            h.this.b();
        }
    }

    public void a() {
        if (this.f1773e) {
            Iterator it = this.f1769a.iterator();
            while (it.hasNext()) {
                ((x0) it.next()).c();
            }
            this.f1773e = false;
        }
    }

    void b() {
        this.f1773e = false;
    }

    public h c(x0 x0Var) {
        if (!this.f1773e) {
            this.f1769a.add(x0Var);
        }
        return this;
    }

    public h d(x0 x0Var, x0 x0Var2) {
        this.f1769a.add(x0Var);
        x0Var2.j(x0Var.d());
        this.f1769a.add(x0Var2);
        return this;
    }

    public h e(long j8) {
        if (!this.f1773e) {
            this.f1770b = j8;
        }
        return this;
    }

    public h f(Interpolator interpolator) {
        if (!this.f1773e) {
            this.f1771c = interpolator;
        }
        return this;
    }

    public h g(y0 y0Var) {
        if (!this.f1773e) {
            this.f1772d = y0Var;
        }
        return this;
    }

    public void h() {
        if (this.f1773e) {
            return;
        }
        Iterator it = this.f1769a.iterator();
        while (it.hasNext()) {
            x0 x0Var = (x0) it.next();
            long j8 = this.f1770b;
            if (j8 >= 0) {
                x0Var.f(j8);
            }
            Interpolator interpolator = this.f1771c;
            if (interpolator != null) {
                x0Var.g(interpolator);
            }
            if (this.f1772d != null) {
                x0Var.h(this.f1774f);
            }
            x0Var.l();
        }
        this.f1773e = true;
    }
}
