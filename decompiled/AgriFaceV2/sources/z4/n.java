package z4;

import java.security.GeneralSecurityException;
import java.security.InvalidAlgorithmParameterException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import javax.crypto.Mac;
import o4.b;
/* loaded from: classes.dex */
public final class n implements w4.c {

    /* renamed from: e  reason: collision with root package name */
    public static final b.EnumC0135b f14440e = b.EnumC0135b.f9849f;

    /* renamed from: a  reason: collision with root package name */
    private final ThreadLocal f14441a;

    /* renamed from: b  reason: collision with root package name */
    private final String f14442b;

    /* renamed from: c  reason: collision with root package name */
    private final Key f14443c;

    /* renamed from: d  reason: collision with root package name */
    private final int f14444d;

    /* loaded from: classes.dex */
    class a extends ThreadLocal {
        a() {
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // java.lang.ThreadLocal
        /* renamed from: a */
        public Mac initialValue() {
            try {
                Mac mac = (Mac) i.f14428c.a(n.this.f14442b);
                mac.init(n.this.f14443c);
                return mac;
            } catch (GeneralSecurityException e8) {
                throw new IllegalStateException(e8);
            }
        }
    }

    public n(String str, Key key) {
        int i8;
        a aVar = new a();
        this.f14441a = aVar;
        if (!f14440e.e()) {
            throw new GeneralSecurityException("Can not use HMAC in FIPS-mode, as BoringCrypto module is not available.");
        }
        this.f14442b = str;
        this.f14443c = key;
        if (key.getEncoded().length < 16) {
            throw new InvalidAlgorithmParameterException("key size too small, need at least 16 bytes");
        }
        str.hashCode();
        char c8 = 65535;
        switch (str.hashCode()) {
            case -1823053428:
                if (str.equals("HMACSHA1")) {
                    c8 = 0;
                    break;
                }
                break;
            case 392315023:
                if (str.equals("HMACSHA224")) {
                    c8 = 1;
                    break;
                }
                break;
            case 392315118:
                if (str.equals("HMACSHA256")) {
                    c8 = 2;
                    break;
                }
                break;
            case 392316170:
                if (str.equals("HMACSHA384")) {
                    c8 = 3;
                    break;
                }
                break;
            case 392317873:
                if (str.equals("HMACSHA512")) {
                    c8 = 4;
                    break;
                }
                break;
        }
        switch (c8) {
            case 0:
                i8 = 20;
                break;
            case 1:
                i8 = 28;
                break;
            case 2:
                i8 = 32;
                break;
            case 3:
                i8 = 48;
                break;
            case 4:
                i8 = 64;
                break;
            default:
                throw new NoSuchAlgorithmException("unknown Hmac algorithm: " + str);
        }
        this.f14444d = i8;
        aVar.get();
    }

    @Override // w4.c
    public byte[] a(byte[] bArr, int i8) {
        if (i8 <= this.f14444d) {
            ((Mac) this.f14441a.get()).update(bArr);
            return Arrays.copyOf(((Mac) this.f14441a.get()).doFinal(), i8);
        }
        throw new InvalidAlgorithmParameterException("tag size too big");
    }
}
