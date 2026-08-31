package l4;

import java.security.GeneralSecurityException;
import java.security.spec.AlgorithmParameterSpec;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
/* loaded from: classes.dex */
public abstract class k {

    /* renamed from: a  reason: collision with root package name */
    private static final ThreadLocal f9005a = new a();

    /* loaded from: classes.dex */
    class a extends ThreadLocal {
        a() {
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // java.lang.ThreadLocal
        /* renamed from: a */
        public Cipher initialValue() {
            try {
                return (Cipher) z4.i.f14427b.a("AES/GCM/NoPadding");
            } catch (GeneralSecurityException e8) {
                throw new IllegalStateException(e8);
            }
        }
    }

    public static AlgorithmParameterSpec a(byte[] bArr) {
        return b(bArr, 0, bArr.length);
    }

    public static AlgorithmParameterSpec b(byte[] bArr, int i8, int i9) {
        Integer c8 = t4.k0.c();
        return (c8 == null || c8.intValue() > 19) ? new GCMParameterSpec(128, bArr, i8, i9) : new IvParameterSpec(bArr, i8, i9);
    }

    public static SecretKey c(byte[] bArr) {
        z4.r.a(bArr.length);
        return new SecretKeySpec(bArr, "AES");
    }

    public static Cipher d() {
        return (Cipher) f9005a.get();
    }
}
