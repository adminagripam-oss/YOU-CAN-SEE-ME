package j4;

import java.security.GeneralSecurityException;
import t4.e0;
import t4.j0;
/* loaded from: classes.dex */
public final class k {

    /* renamed from: a  reason: collision with root package name */
    private final y4.a0 f8251a = null;

    /* renamed from: b  reason: collision with root package name */
    private final w f8252b;

    private k(w wVar) {
        this.f8252b = wVar;
    }

    public static k a(w wVar) {
        return new k(wVar);
    }

    y4.a0 b() {
        try {
            return c();
        } catch (GeneralSecurityException e8) {
            throw new j0("Parsing parameters failed in getProto(). You probably want to call some Tink register function for " + this.f8252b, e8);
        }
    }

    y4.a0 c() {
        y4.a0 a0Var = this.f8251a;
        if (a0Var != null) {
            return a0Var;
        }
        w wVar = this.f8252b;
        return (wVar instanceof t4.j ? ((t4.j) wVar).b() : (e0) t4.u.c().o(this.f8252b, e0.class)).d();
    }

    public w d() {
        w wVar = this.f8252b;
        return wVar != null ? wVar : z.a(b().i());
    }
}
