package t4;

import java.security.GeneralSecurityException;
import java.security.Provider;
import java.security.SecureRandom;
/* loaded from: classes.dex */
public abstract class f0 {

    /* renamed from: a  reason: collision with root package name */
    private static final ThreadLocal f11842a = new a();

    /* loaded from: classes.dex */
    class a extends ThreadLocal {
        a() {
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // java.lang.ThreadLocal
        /* renamed from: a */
        public SecureRandom initialValue() {
            return f0.a();
        }
    }

    static /* synthetic */ SecureRandom a() {
        return c();
    }

    private static SecureRandom b() {
        Provider a8 = b.a();
        if (a8 != null) {
            try {
                return SecureRandom.getInstance("SHA1PRNG", a8);
            } catch (GeneralSecurityException unused) {
            }
        }
        Provider b8 = b.b();
        if (b8 != null) {
            try {
                return SecureRandom.getInstance("SHA1PRNG", b8);
            } catch (GeneralSecurityException unused2) {
            }
        }
        return new SecureRandom();
    }

    private static SecureRandom c() {
        SecureRandom b8 = b();
        b8.nextLong();
        return b8;
    }

    public static byte[] d(int i8) {
        byte[] bArr = new byte[i8];
        ((SecureRandom) f11842a.get()).nextBytes(bArr);
        return bArr;
    }
}
