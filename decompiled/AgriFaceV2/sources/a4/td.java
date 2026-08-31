package a4;

import java.io.UnsupportedEncodingException;
/* loaded from: classes.dex */
public final class td implements ed {

    /* renamed from: a  reason: collision with root package name */
    private final ea f1105a;

    /* renamed from: b  reason: collision with root package name */
    private fc f1106b = new fc();

    /* renamed from: c  reason: collision with root package name */
    private final int f1107c;

    private td(ea eaVar, int i8) {
        this.f1105a = eaVar;
        ce.a();
        this.f1107c = i8;
    }

    public static ed a(ea eaVar) {
        return new td(eaVar, 0);
    }

    public static ed f(ea eaVar, int i8) {
        return new td(eaVar, 1);
    }

    @Override // a4.ed
    public final String b() {
        hc f8 = this.f1105a.j().f();
        return (f8 == null || d.b(f8.k())) ? "NA" : (String) k3.q.g(f8.k());
    }

    @Override // a4.ed
    public final byte[] c(int i8, boolean z7) {
        this.f1106b.f(Boolean.valueOf(1 == (i8 ^ 1)));
        this.f1106b.e(Boolean.FALSE);
        this.f1105a.i(this.f1106b.m());
        try {
            ce.a();
            if (i8 == 0) {
                return new f5.d().g(f8.f561a).h(true).f().a(this.f1105a.j()).getBytes("utf-8");
            }
            ga j8 = this.f1105a.j();
            a2 a2Var = new a2();
            f8.f561a.a(a2Var);
            return a2Var.b().a(j8);
        } catch (UnsupportedEncodingException e8) {
            throw new UnsupportedOperationException("Failed to covert logging to UTF-8 byte array", e8);
        }
    }

    @Override // a4.ed
    public final ed d(fc fcVar) {
        this.f1106b = fcVar;
        return this;
    }

    @Override // a4.ed
    public final ed e(da daVar) {
        this.f1105a.f(daVar);
        return this;
    }

    @Override // a4.ed
    public final int zza() {
        return this.f1107c;
    }
}
