package s4;

import android.security.keystore.KeyGenParameterSpec;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.InvalidKeyException;
import java.security.KeyStore;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;
/* loaded from: classes.dex */
public abstract class b {

    /* loaded from: classes.dex */
    private static final class a implements j4.a {

        /* renamed from: a  reason: collision with root package name */
        private final SecretKey f11576a;

        public a(String str, KeyStore keyStore) {
            SecretKey secretKey = (SecretKey) keyStore.getKey(str, null);
            this.f11576a = secretKey;
            if (secretKey != null) {
                return;
            }
            throw new InvalidKeyException("Keystore cannot load the key with ID: " + str);
        }

        @Override // j4.a
        public byte[] a(byte[] bArr, byte[] bArr2) {
            if (bArr.length <= 2147483619) {
                byte[] bArr3 = new byte[bArr.length + 12 + 16];
                Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");
                cipher.init(1, this.f11576a);
                cipher.updateAAD(bArr2);
                if (cipher.doFinal(bArr, 0, bArr.length, bArr3, 12) == bArr.length + 16) {
                    byte[] iv = cipher.getIV();
                    if (iv.length == 12) {
                        System.arraycopy(iv, 0, bArr3, 0, 12);
                        return bArr3;
                    }
                    throw new GeneralSecurityException("IV has unexpected length");
                }
                throw new GeneralSecurityException("encryption failed: bytesWritten is wrong");
            }
            throw new GeneralSecurityException("plaintext too long");
        }

        @Override // j4.a
        public byte[] b(byte[] bArr, byte[] bArr2) {
            if (bArr.length >= 28) {
                GCMParameterSpec gCMParameterSpec = new GCMParameterSpec(128, bArr, 0, 12);
                Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");
                cipher.init(2, this.f11576a, gCMParameterSpec);
                cipher.updateAAD(bArr2);
                return cipher.doFinal(bArr, 12, bArr.length - 12);
            }
            throw new BadPaddingException("ciphertext too short");
        }
    }

    public static void a(String str) {
        b(new KeyGenParameterSpec.Builder(str, 3).setKeySize(256).setBlockModes("GCM").setEncryptionPaddings("NoPadding").build());
    }

    public static void b(KeyGenParameterSpec keyGenParameterSpec) {
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES", "AndroidKeyStore");
        keyGenerator.init(keyGenParameterSpec);
        keyGenerator.generateKey();
    }

    public static j4.a c(String str) {
        return new a(str, d());
    }

    private static KeyStore d() {
        if (f()) {
            try {
                KeyStore keyStore = KeyStore.getInstance("AndroidKeyStore");
                keyStore.load(null);
                return keyStore;
            } catch (IOException e8) {
                throw new GeneralSecurityException(e8);
            }
        }
        throw new IllegalStateException("Need Android Keystore on Android M or newer");
    }

    public static boolean e(String str) {
        return d().containsAlias(str);
    }

    private static boolean f() {
        return true;
    }
}
