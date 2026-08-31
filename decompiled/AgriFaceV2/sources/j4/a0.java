package j4;

import java.nio.charset.Charset;
import java.security.GeneralSecurityException;
import y4.c0;
import y4.d0;
import y4.i0;
import y4.y;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public abstract class a0 {

    /* renamed from: a  reason: collision with root package name */
    public static final Charset f8245a = Charset.forName("UTF-8");

    public static d0.c a(c0.c cVar) {
        return (d0.c) d0.c.Z().t(cVar.Z().a0()).s(cVar.c0()).r(cVar.b0()).q(cVar.a0()).h();
    }

    public static d0 b(c0 c0Var) {
        d0.b r8 = d0.Z().r(c0Var.c0());
        for (c0.c cVar : c0Var.b0()) {
            r8.q(a(cVar));
        }
        return (d0) r8.h();
    }

    public static void c(c0.c cVar) {
        if (!cVar.d0()) {
            throw new GeneralSecurityException(String.format("key %d has no key data", Integer.valueOf(cVar.a0())));
        }
        if (cVar.b0() == i0.UNKNOWN_PREFIX) {
            throw new GeneralSecurityException(String.format("key %d has unknown prefix", Integer.valueOf(cVar.a0())));
        }
        if (cVar.c0() == y4.z.UNKNOWN_STATUS) {
            throw new GeneralSecurityException(String.format("key %d has unknown status", Integer.valueOf(cVar.a0())));
        }
    }

    public static void d(c0 c0Var) {
        int c02 = c0Var.c0();
        int i8 = 0;
        boolean z7 = false;
        boolean z8 = true;
        for (c0.c cVar : c0Var.b0()) {
            if (cVar.c0() == y4.z.ENABLED) {
                c(cVar);
                if (cVar.a0() == c02) {
                    if (z7) {
                        throw new GeneralSecurityException("keyset contains multiple primary keys");
                    }
                    z7 = true;
                }
                if (cVar.Z().Z() != y.c.ASYMMETRIC_PUBLIC) {
                    z8 = false;
                }
                i8++;
            }
        }
        if (i8 == 0) {
            throw new GeneralSecurityException("keyset must contain at least one ENABLED key");
        }
        if (!z7 && !z8) {
            throw new GeneralSecurityException("keyset doesn't contain a valid primary key");
        }
    }
}
