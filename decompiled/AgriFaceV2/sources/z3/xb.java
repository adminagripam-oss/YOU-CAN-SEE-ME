package z3;

import java.io.UnsupportedEncodingException;
/* loaded from: classes.dex */
public final class xb implements ob {

    /* renamed from: a  reason: collision with root package name */
    private final f8 f14326a;

    /* renamed from: b  reason: collision with root package name */
    private ja f14327b = new ja();

    private xb(f8 f8Var, int i8) {
        this.f14326a = f8Var;
        jc.a();
    }

    public static ob e(f8 f8Var) {
        return new xb(f8Var, 0);
    }

    @Override // z3.ob
    public final String a() {
        ma c8 = this.f14326a.f().c();
        return (c8 == null || g5.b(c8.k())) ? "NA" : (String) k3.q.g(c8.k());
    }

    @Override // z3.ob
    public final ob b(e8 e8Var) {
        this.f14326a.c(e8Var);
        return this;
    }

    @Override // z3.ob
    public final byte[] c(int i8, boolean z7) {
        this.f14327b.f(Boolean.valueOf(1 == (i8 ^ 1)));
        this.f14327b.e(Boolean.FALSE);
        this.f14326a.e(this.f14327b.m());
        try {
            jc.a();
            if (i8 == 0) {
                return new f5.d().g(m6.f13905a).h(true).f().a(this.f14326a.f()).getBytes("utf-8");
            }
            h8 f8 = this.f14326a.f();
            n nVar = new n();
            m6.f13905a.a(nVar);
            return nVar.b().a(f8);
        } catch (UnsupportedEncodingException e8) {
            throw new UnsupportedOperationException("Failed to covert logging to UTF-8 byte array", e8);
        }
    }

    @Override // z3.ob
    public final ob d(ja jaVar) {
        this.f14327b = jaVar;
        return this;
    }
}
