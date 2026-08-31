package j4;
/* loaded from: classes.dex */
public abstract class u {
    public static m a(q qVar, a aVar, byte[] bArr) {
        return m.C(qVar, aVar, bArr);
    }

    public static m b(q qVar, y yVar) {
        if (yVar != null) {
            return d.a(qVar);
        }
        throw new NullPointerException("SecretKeyAccess cannot be null");
    }

    public static void c(m mVar, r rVar, a aVar, byte[] bArr) {
        mVar.J(rVar, aVar, bArr);
    }

    public static void d(m mVar, r rVar, y yVar) {
        if (yVar == null) {
            throw new NullPointerException("SecretKeyAccess cannot be null");
        }
        d.b(mVar, rVar);
    }
}
