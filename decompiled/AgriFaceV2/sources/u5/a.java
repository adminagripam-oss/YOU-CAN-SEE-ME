package u5;

import android.hardware.biometrics.BiometricPrompt;
import javax.crypto.Cipher;
/* loaded from: classes.dex */
public abstract /* synthetic */ class a {
    public static /* synthetic */ BiometricPrompt.CryptoObject a(Cipher cipher) {
        return new BiometricPrompt.CryptoObject(cipher);
    }
}
