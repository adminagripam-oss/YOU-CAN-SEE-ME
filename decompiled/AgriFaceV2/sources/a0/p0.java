package a0;

import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.Rect;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import y.u0;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class p0 {

    /* renamed from: a  reason: collision with root package name */
    private final int f80a;

    /* renamed from: b  reason: collision with root package name */
    private final u0.g f81b;

    /* renamed from: c  reason: collision with root package name */
    private final Rect f82c;

    /* renamed from: d  reason: collision with root package name */
    private final int f83d;

    /* renamed from: e  reason: collision with root package name */
    private final int f84e;

    /* renamed from: f  reason: collision with root package name */
    private final Matrix f85f;

    /* renamed from: g  reason: collision with root package name */
    private final u0 f86g;

    /* renamed from: h  reason: collision with root package name */
    private final String f87h;

    /* renamed from: j  reason: collision with root package name */
    final i4.d f89j;

    /* renamed from: k  reason: collision with root package name */
    private int f90k = -1;

    /* renamed from: i  reason: collision with root package name */
    private final List f88i = new ArrayList();

    /* JADX INFO: Access modifiers changed from: package-private */
    public p0(b0.r0 r0Var, u0.g gVar, Rect rect, int i8, int i9, Matrix matrix, u0 u0Var, i4.d dVar, int i10) {
        this.f80a = i10;
        this.f81b = gVar;
        this.f84e = i9;
        this.f83d = i8;
        this.f82c = rect;
        this.f85f = matrix;
        this.f86g = u0Var;
        this.f87h = String.valueOf(r0Var.hashCode());
        List<b0.t0> a8 = r0Var.a();
        Objects.requireNonNull(a8);
        for (b0.t0 t0Var : a8) {
            this.f88i.add(Integer.valueOf(t0Var.a()));
        }
        this.f89j = dVar;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public i4.d a() {
        return this.f89j;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Rect b() {
        return this.f82c;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int c() {
        return this.f84e;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public u0.g d() {
        return this.f81b;
    }

    public int e() {
        return this.f80a;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int f() {
        return this.f83d;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Matrix g() {
        return this.f85f;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public List h() {
        return this.f88i;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public String i() {
        return this.f87h;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean j() {
        return this.f86g.e();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean k() {
        return d() == null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void l(y.v0 v0Var) {
        this.f86g.h(v0Var);
    }

    void m(int i8) {
        if (this.f90k != i8) {
            this.f90k = i8;
            this.f86g.b(i8);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void n() {
        this.f86g.c();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void o(androidx.camera.core.n nVar) {
        this.f86g.i(nVar);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void p(u0.h hVar) {
        this.f86g.g(hVar);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void q() {
        if (this.f90k != -1) {
            m(100);
        }
        this.f86g.d();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void r(Bitmap bitmap) {
        this.f86g.a(bitmap);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void s(y.v0 v0Var) {
        this.f86g.f(v0Var);
    }
}
