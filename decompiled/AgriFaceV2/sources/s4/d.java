package s4;

import j4.s;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.KeyStoreException;
import java.util.Arrays;
import java.util.Locale;
import z4.p;
import z4.r;
/* loaded from: classes.dex */
public final class d implements s {

    /* renamed from: b  reason: collision with root package name */
    private static final Object f11579b = new Object();

    /* renamed from: a  reason: collision with root package name */
    private final String f11580a;

    /* loaded from: classes.dex */
    public static final class a {

        /* renamed from: a  reason: collision with root package name */
        String f11581a = null;

        public a() {
            if (!d.c()) {
                throw new IllegalStateException("need Android Keystore on Android M or newer");
            }
        }
    }

    public d() {
        this(new a());
    }

    static /* synthetic */ boolean c() {
        return e();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean d(String str) {
        synchronized (f11579b) {
            String b8 = r.b("android-keystore://", str);
            if (b.e(b8)) {
                return false;
            }
            b.a(b8);
            return true;
        }
    }

    private static boolean e() {
        return true;
    }

    private static j4.a f(j4.a aVar) {
        byte[] a8 = p.a(10);
        byte[] bArr = new byte[0];
        if (Arrays.equals(a8, aVar.b(aVar.a(a8, bArr), bArr))) {
            return aVar;
        }
        throw new KeyStoreException("cannot use Android Keystore: encryption/decryption of non-empty message and empty aad returns an incorrect result");
    }

    @Override // j4.s
    public j4.a a(String str) {
        j4.a f8;
        String str2 = this.f11580a;
        if (str2 == null || str2.equals(str)) {
            try {
                synchronized (f11579b) {
                    f8 = f(new c(r.b("android-keystore://", str)));
                }
                return f8;
            } catch (IOException e8) {
                throw new GeneralSecurityException(e8);
            }
        }
        throw new GeneralSecurityException(String.format("this client is bound to %s, cannot load keys bound to %s", this.f11580a, str));
    }

    @Override // j4.s
    public boolean b(String str) {
        String str2 = this.f11580a;
        if (str2 == null || !str2.equals(str)) {
            return this.f11580a == null && str.toLowerCase(Locale.US).startsWith("android-keystore://");
        }
        return true;
    }

    private d(a aVar) {
        this.f11580a = aVar.f11581a;
    }
}
