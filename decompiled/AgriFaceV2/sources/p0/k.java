package p0;

import b0.d0;
import b0.g0;
import b0.h0;
import java.util.Collection;
import p0.h;
import y.h2;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class k implements h0 {

    /* renamed from: e  reason: collision with root package name */
    private final h0 f9954e;

    /* renamed from: f  reason: collision with root package name */
    private final q f9955f;

    /* renamed from: g  reason: collision with root package name */
    private final r f9956g;

    /* renamed from: h  reason: collision with root package name */
    private final h2.b f9957h;

    /* JADX INFO: Access modifiers changed from: package-private */
    public k(h0 h0Var, h2.b bVar, h.a aVar) {
        this.f9954e = h0Var;
        this.f9957h = bVar;
        this.f9955f = new q(h0Var.p(), aVar);
        this.f9956g = new r(h0Var.l());
    }

    @Override // y.h2.b
    public void d(h2 h2Var) {
        d0.q.a();
        this.f9957h.d(h2Var);
    }

    @Override // b0.h0
    public void e(Collection collection) {
        throw new UnsupportedOperationException("Operation not supported by VirtualCamera.");
    }

    @Override // y.h2.b
    public void f(h2 h2Var) {
        d0.q.a();
        this.f9957h.f(h2Var);
    }

    @Override // b0.h0
    public void h(Collection collection) {
        throw new UnsupportedOperationException("Operation not supported by VirtualCamera.");
    }

    @Override // b0.h0
    public boolean j() {
        return false;
    }

    @Override // b0.h0
    public g0 l() {
        return this.f9956g;
    }

    @Override // y.h2.b
    public void m(h2 h2Var) {
        d0.q.a();
        this.f9957h.m(h2Var);
    }

    @Override // y.h2.b
    public void n(h2 h2Var) {
        d0.q.a();
        this.f9957h.n(h2Var);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void o(int i8) {
        this.f9956g.p(i8);
    }

    @Override // b0.h0
    public d0 p() {
        return this.f9955f;
    }
}
