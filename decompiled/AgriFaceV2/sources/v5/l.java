package v5;

import android.content.Context;
import android.security.keystore.KeyGenParameterSpec;
import java.math.BigInteger;
import java.security.spec.AlgorithmParameterSpec;
import java.security.spec.MGF1ParameterSpec;
import java.util.Calendar;
import javax.crypto.Cipher;
import javax.crypto.spec.OAEPParameterSpec;
import javax.crypto.spec.PSource;
import javax.security.auth.x500.X500Principal;
/* loaded from: classes.dex */
class l extends k {
    public l(Context context, u5.s sVar) {
        super(context, sVar);
    }

    @Override // v5.k
    protected String e() {
        return this.f12405b.getPackageName() + ".FlutterSecureStoragePluginKeyOAEP" + this.f12406c.g();
    }

    @Override // v5.k
    protected AlgorithmParameterSpec h() {
        return new OAEPParameterSpec("SHA-256", "MGF1", MGF1ParameterSpec.SHA1, PSource.PSpecified.DEFAULT);
    }

    @Override // v5.k
    protected Cipher k() {
        return Cipher.getInstance("RSA/ECB/OAEPPadding", "AndroidKeyStoreBCWorkaround");
    }

    @Override // v5.k
    protected AlgorithmParameterSpec l(Context context, Calendar calendar, Calendar calendar2) {
        KeyGenParameterSpec.Builder builder = new KeyGenParameterSpec.Builder(this.f12404a, 3);
        return builder.setCertificateSubject(new X500Principal("CN=" + this.f12404a)).setDigests("SHA-256").setBlockModes("ECB").setEncryptionPaddings("OAEPPadding").setCertificateSerialNumber(BigInteger.valueOf(1L)).setCertificateNotBefore(calendar.getTime()).setCertificateNotAfter(calendar2.getTime()).build();
    }
}
