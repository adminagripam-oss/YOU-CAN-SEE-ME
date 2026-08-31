package m7;

import java.util.concurrent.CancellationException;
import k7.f1;
import k7.l1;
/* loaded from: classes.dex */
public abstract class e extends k7.a implements d {

    /* renamed from: h  reason: collision with root package name */
    private final d f9683h;

    public e(t6.g gVar, d dVar, boolean z7, boolean z8) {
        super(gVar, z7, z8);
        this.f9683h = dVar;
    }

    @Override // k7.l1
    public void F(Throwable th) {
        CancellationException w02 = l1.w0(this, th, null, 1, null);
        this.f9683h.d(w02);
        z(w02);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final d H0() {
        return this.f9683h;
    }

    @Override // m7.t
    public void a(c7.l lVar) {
        this.f9683h.a(lVar);
    }

    @Override // k7.l1, k7.e1
    public final void d(CancellationException cancellationException) {
        if (c0()) {
            return;
        }
        if (cancellationException == null) {
            cancellationException = new f1(J(), null, this);
        }
        F(cancellationException);
    }

    @Override // m7.t
    public boolean f(Throwable th) {
        return this.f9683h.f(th);
    }

    @Override // m7.s
    public f iterator() {
        return this.f9683h.iterator();
    }

    @Override // m7.t
    public Object l(Object obj) {
        return this.f9683h.l(obj);
    }

    @Override // m7.t
    public Object m(Object obj, t6.d dVar) {
        return this.f9683h.m(obj, dVar);
    }

    @Override // m7.t
    public boolean o() {
        return this.f9683h.o();
    }
}
