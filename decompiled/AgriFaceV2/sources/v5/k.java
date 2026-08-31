package v5;

import android.content.Context;
import android.content.res.Configuration;
import android.security.keystore.KeyGenParameterSpec;
import java.math.BigInteger;
import java.security.Key;
import java.security.KeyPairGenerator;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.cert.Certificate;
import java.security.spec.AlgorithmParameterSpec;
import java.util.Calendar;
import java.util.Locale;
import javax.crypto.Cipher;
import javax.security.auth.x500.X500Principal;
/* loaded from: classes.dex */
class k implements a {

    /* renamed from: a  reason: collision with root package name */
    protected final String f12404a = e();

    /* renamed from: b  reason: collision with root package name */
    protected final Context f12405b;

    /* renamed from: c  reason: collision with root package name */
    protected final u5.s f12406c;

    public k(Context context, u5.s sVar) {
        this.f12405b = context;
        this.f12406c = sVar;
        g(context);
    }

    private void f(Context context) {
        Locale locale = Locale.getDefault();
        try {
            m(Locale.ENGLISH);
            Calendar calendar = Calendar.getInstance();
            Calendar calendar2 = Calendar.getInstance();
            calendar2.add(1, 25);
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA", "AndroidKeyStore");
            keyPairGenerator.initialize(l(context, calendar, calendar2));
            keyPairGenerator.generateKeyPair();
        } finally {
            m(locale);
        }
    }

    private void g(Context context) {
        KeyStore keyStore = KeyStore.getInstance("AndroidKeyStore");
        keyStore.load(null);
        Key key = keyStore.getKey(this.f12404a, null);
        Certificate certificate = keyStore.getCertificate(this.f12404a);
        if (key == null || certificate == null) {
            f(context);
        }
    }

    private PrivateKey i() {
        KeyStore keyStore = KeyStore.getInstance("AndroidKeyStore");
        keyStore.load(null);
        Key key = keyStore.getKey(this.f12404a, null);
        if (key != null) {
            if (key instanceof PrivateKey) {
                return (PrivateKey) key;
            }
            throw new Exception("Not an instance of a PrivateKey");
        }
        throw new Exception("No key found under alias: " + this.f12404a);
    }

    private PublicKey j() {
        KeyStore keyStore = KeyStore.getInstance("AndroidKeyStore");
        keyStore.load(null);
        Certificate certificate = keyStore.getCertificate(this.f12404a);
        if (certificate == null) {
            throw new Exception("No certificate found under alias: " + this.f12404a);
        }
        PublicKey publicKey = certificate.getPublicKey();
        if (publicKey != null) {
            return publicKey;
        }
        throw new Exception("No key found under alias: " + this.f12404a);
    }

    private void m(Locale locale) {
        Locale.setDefault(locale);
        Configuration configuration = this.f12405b.getResources().getConfiguration();
        configuration.setLocale(locale);
        this.f12405b.createConfigurationContext(configuration);
    }

    @Override // v5.a
    public byte[] a(Key key) {
        PublicKey j8 = j();
        Cipher k8 = k();
        k8.init(3, j8, h());
        return k8.wrap(key);
    }

    @Override // v5.a
    public Key b(byte[] bArr, String str) {
        PrivateKey i8 = i();
        Cipher k8 = k();
        k8.init(4, i8, h());
        return k8.unwrap(bArr, str, 3);
    }

    @Override // v5.a
    public Cipher c(Context context) {
        return null;
    }

    @Override // v5.a
    public void d() {
        KeyStore keyStore = KeyStore.getInstance("AndroidKeyStore");
        keyStore.load(null);
        keyStore.deleteEntry(this.f12404a);
    }

    protected String e() {
        return this.f12405b.getPackageName() + ".FlutterSecureStoragePluginKey" + this.f12406c.g();
    }

    protected AlgorithmParameterSpec h() {
        return null;
    }

    protected Cipher k() {
        return Cipher.getInstance("RSA/ECB/PKCS1Padding", "AndroidKeyStoreBCWorkaround");
    }

    protected AlgorithmParameterSpec l(Context context, Calendar calendar, Calendar calendar2) {
        KeyGenParameterSpec.Builder builder = new KeyGenParameterSpec.Builder(this.f12404a, 3);
        return builder.setCertificateSubject(new X500Principal("CN=" + this.f12404a)).setDigests("SHA-256").setBlockModes("ECB").setEncryptionPaddings("PKCS1Padding").setCertificateSerialNumber(BigInteger.valueOf(1L)).setCertificateNotBefore(calendar.getTime()).setCertificateNotAfter(calendar2.getTime()).build();
    }
}
