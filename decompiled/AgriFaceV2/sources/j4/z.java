package j4;

import java.io.IOException;
import java.security.GeneralSecurityException;
import t4.e0;
/* loaded from: classes.dex */
public abstract class z {
    public static w a(byte[] bArr) {
        try {
            return t4.u.c().i(e0.b(y4.a0.e0(bArr, com.google.crypto.tink.shaded.protobuf.p.b())));
        } catch (IOException e8) {
            throw new GeneralSecurityException("Failed to parse proto", e8);
        }
    }

    public static byte[] b(w wVar) {
        return (wVar instanceof t4.j ? ((t4.j) wVar).b() : (e0) t4.u.c().o(wVar, e0.class)).d().i();
    }
}
