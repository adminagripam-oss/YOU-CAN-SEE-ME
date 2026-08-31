package a0;

import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.Rect;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.Executor;
import y.u0;
/* loaded from: classes.dex */
public abstract class e1 {

    /* renamed from: a  reason: collision with root package name */
    private int f31a = new j0.a().a();

    /* loaded from: classes.dex */
    interface a {
        void a(e1 e1Var);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void q(int i8) {
        if (l() != null) {
            l().b(i8);
        } else {
            j();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void r(y.v0 v0Var) {
        j();
        if (!(l() != null)) {
            throw new IllegalStateException("One and only one callback is allowed.");
        }
        u0.f l8 = l();
        Objects.requireNonNull(l8);
        l8.e(v0Var);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void s(Bitmap bitmap) {
        if (l() != null) {
            l().a(bitmap);
        } else {
            j();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void t(u0.h hVar) {
        u0.f l8 = l();
        Objects.requireNonNull(l8);
        Objects.requireNonNull(hVar);
        l8.d(hVar);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void u(androidx.camera.core.n nVar) {
        j();
        throw null;
    }

    public static e1 v(Executor executor, u0.e eVar, u0.f fVar, u0.g gVar, Rect rect, Matrix matrix, int i8, int i9, int i10, List list) {
        g1.e.b((fVar == null) == (gVar == null), "onDiskCallback and outputFileOptions should be both null or both non-null.");
        g1.e.b(!(fVar == null), "One and only one on-disk or in-memory callback should be present.");
        return new h(executor, eVar, fVar, gVar, rect, matrix, i8, i9, i10, list);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void A(final u0.h hVar) {
        g().execute(new Runnable() { // from class: a0.b1
            @Override // java.lang.Runnable
            public final void run() {
                e1.this.t(hVar);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean f() {
        d0.q.a();
        int i8 = this.f31a;
        if (i8 > 0) {
            this.f31a = i8 - 1;
            return true;
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract Executor g();

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract int h();

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract Rect i();

    public abstract u0.e j();

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract int k();

    public abstract u0.f l();

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract u0.g m();

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract int n();

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract Matrix o();

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract List p();

    /* JADX INFO: Access modifiers changed from: package-private */
    public void w(final int i8) {
        g().execute(new Runnable() { // from class: a0.z0
            @Override // java.lang.Runnable
            public final void run() {
                e1.this.q(i8);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void x(final y.v0 v0Var) {
        g().execute(new Runnable() { // from class: a0.d1
            @Override // java.lang.Runnable
            public final void run() {
                e1.this.r(v0Var);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void y(final Bitmap bitmap) {
        g().execute(new Runnable() { // from class: a0.a1
            @Override // java.lang.Runnable
            public final void run() {
                e1.this.s(bitmap);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void z(final androidx.camera.core.n nVar) {
        g().execute(new Runnable() { // from class: a0.c1
            @Override // java.lang.Runnable
            public final void run() {
                e1.this.u(nVar);
            }
        });
    }
}
