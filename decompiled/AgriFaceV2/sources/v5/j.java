package v5;

import android.app.KeyguardManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.security.keystore.KeyGenParameterSpec;
import android.util.Base64;
import android.util.Log;
import java.security.Key;
import java.security.KeyStore;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.spec.GCMParameterSpec;
/* loaded from: classes.dex */
class j implements a {

    /* renamed from: a  reason: collision with root package name */
    protected final String f12401a;

    /* renamed from: b  reason: collision with root package name */
    protected final Context f12402b;

    /* renamed from: c  reason: collision with root package name */
    protected final u5.s f12403c;

    public j(Context context, u5.s sVar) {
        this.f12402b = context;
        this.f12403c = sVar;
        String f8 = f(context);
        this.f12401a = f8;
        KeyStore keyStore = KeyStore.getInstance("AndroidKeyStore");
        keyStore.load(null);
        if (keyStore.getKey(f8, null) == null) {
            g();
        }
    }

    private void e(KeyGenParameterSpec.Builder builder) {
        builder.setUserAuthenticationValidityDurationSeconds(-1);
    }

    @Override // v5.a
    public byte[] a(Key key) {
        throw new UnsupportedOperationException("AES symmetric keys in AndroidKeyStore cannot wrap other keys");
    }

    @Override // v5.a
    public Key b(byte[] bArr, String str) {
        throw new UnsupportedOperationException("AES symmetric keys in AndroidKeyStore cannot unwrap other keys");
    }

    @Override // v5.a
    public Cipher c(Context context) {
        KeyStore keyStore = KeyStore.getInstance("AndroidKeyStore");
        keyStore.load(null);
        Key key = keyStore.getKey(this.f12401a, null);
        if (key == null) {
            g();
            return h(context, keyStore.getKey(this.f12401a, null));
        }
        return h(context, key);
    }

    @Override // v5.a
    public void d() {
        KeyStore keyStore = KeyStore.getInstance("AndroidKeyStore");
        keyStore.load(null);
        keyStore.deleteEntry(this.f12401a);
        this.f12402b.getSharedPreferences(this.f12403c.e(), 0).edit().remove("KeyStoreIV1").apply();
    }

    protected String f(Context context) {
        return context.getPackageName() + ".FlutterSecureStoragePluginKey" + this.f12403c.g();
    }

    public void g() {
        KeyGenParameterSpec.Builder unlockedDeviceRequired;
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES", "AndroidKeyStore");
        boolean i8 = i();
        if (this.f12403c.f() && !i8) {
            throw new Exception("BIOMETRIC_UNAVAILABLE: Biometric enforcement enabled but device has no PIN, pattern, password, or biometric enrolled. Cannot generate secure key.");
        }
        if (!i8) {
            Log.w("AESCipher23", "Device has no PIN/biometric security. Generating key without user authentication requirement (enforceBiometrics=false).");
        }
        KeyGenParameterSpec.Builder keySize = new KeyGenParameterSpec.Builder(this.f12401a, 3).setBlockModes("GCM").setEncryptionPaddings("NoPadding").setKeySize(256);
        if (i8) {
            keySize.setUserAuthenticationRequired(true);
            if (Build.VERSION.SDK_INT >= 30) {
                keySize.setUserAuthenticationParameters(0, this.f12403c.r() ? 2 : 3);
            } else {
                e(keySize);
            }
            keySize.setInvalidatedByBiometricEnrollment(true);
        } else {
            keySize.setUserAuthenticationRequired(false);
        }
        if (Build.VERSION.SDK_INT >= 28) {
            keySize.setUnlockedDeviceRequired(true);
            if (j()) {
                keySize.setIsStrongBoxBacked(true);
                Log.d("AESCipher23", "StrongBox is available and enabled for biometric key");
            } else {
                Log.w("AESCipher23", "StrongBox requested but not available on this device. Using standard TEE.");
            }
        }
        try {
            keyGenerator.init(keySize.build());
            keyGenerator.generateKey();
        } catch (Exception e8) {
            int i9 = Build.VERSION.SDK_INT;
            if (i9 < 28 || !j()) {
                throw e8;
            }
            Log.w("AESCipher23", " Key generation failed with StrongBox. Retrying without StrongBox.", e8);
            unlockedDeviceRequired = new KeyGenParameterSpec.Builder(this.f12401a, 3).setBlockModes("GCM").setEncryptionPaddings("NoPadding").setKeySize(256).setUnlockedDeviceRequired(true);
            if (i8) {
                unlockedDeviceRequired.setUserAuthenticationRequired(true);
                if (i9 >= 30) {
                    unlockedDeviceRequired.setUserAuthenticationParameters(0, this.f12403c.r() ? 2 : 3);
                } else {
                    e(unlockedDeviceRequired);
                }
                unlockedDeviceRequired.setInvalidatedByBiometricEnrollment(true);
            }
            keyGenerator.init(unlockedDeviceRequired.build());
            keyGenerator.generateKey();
            Log.d("AESCipher23", "Key generation succeeded without StrongBox");
        }
    }

    public Cipher h(Context context, Key key) {
        Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");
        SharedPreferences sharedPreferences = context.getSharedPreferences(this.f12403c.e(), 0);
        String string = sharedPreferences.getString("KeyStoreIV1", null);
        if (string == null || !s.f(sharedPreferences)) {
            cipher.init(1, key);
            byte[] iv = cipher.getIV();
            SharedPreferences.Editor edit = sharedPreferences.edit();
            edit.putString("KeyStoreIV1", Base64.encodeToString(iv, 0));
            edit.apply();
        } else {
            cipher.init(2, key, new GCMParameterSpec(128, Base64.decode(string, 0)));
        }
        return cipher;
    }

    protected boolean i() {
        KeyguardManager keyguardManager = (KeyguardManager) this.f12402b.getSystemService("keyguard");
        return keyguardManager != null && keyguardManager.isDeviceSecure();
    }

    protected boolean j() {
        if (Build.VERSION.SDK_INT < 28) {
            return false;
        }
        return this.f12402b.getPackageManager().hasSystemFeature("android.hardware.strongbox_keystore");
    }
}
