package r7;

import k7.h0;
/* loaded from: classes.dex */
public final class k extends h {

    /* renamed from: g  reason: collision with root package name */
    public final Runnable f11413g;

    public k(Runnable runnable, long j8, i iVar) {
        super(j8, iVar);
        this.f11413g = runnable;
    }

    @Override // java.lang.Runnable
    public void run() {
        try {
            this.f11413g.run();
        } finally {
            this.f11411f.a();
        }
    }

    public String toString() {
        return "Task[" + h0.a(this.f11413g) + '@' + h0.b(this.f11413g) + ", " + this.f11410e + ", " + this.f11411f + ']';
    }
}
