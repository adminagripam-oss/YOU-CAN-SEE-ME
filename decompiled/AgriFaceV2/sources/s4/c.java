package s4;

import android.util.Log;
import java.security.GeneralSecurityException;
import java.security.ProviderException;
import javax.crypto.BadPaddingException;
/* loaded from: classes.dex */
public final class c implements j4.a {

    /* renamed from: b  reason: collision with root package name */
    private static final String f11577b = "c";

    /* renamed from: a  reason: collision with root package name */
    private final j4.a f11578a;

    public c(String str) {
        this.f11578a = b.c(str);
    }

    private static void c() {
        try {
            Thread.sleep((int) (Math.random() * 100.0d));
        } catch (InterruptedException unused) {
        }
    }

    @Override // j4.a
    public byte[] a(byte[] bArr, byte[] bArr2) {
        try {
            return this.f11578a.a(bArr, bArr2);
        } catch (GeneralSecurityException | ProviderException e8) {
            Log.w(f11577b, "encountered a potentially transient KeyStore error, will wait and retry", e8);
            c();
            return this.f11578a.a(bArr, bArr2);
        }
    }

    @Override // j4.a
    public byte[] b(byte[] bArr, byte[] bArr2) {
        try {
            return this.f11578a.b(bArr, bArr2);
        } catch (GeneralSecurityException e8) {
            e = e8;
            Log.w(f11577b, "encountered a potentially transient KeyStore error, will wait and retry", e);
            c();
            return this.f11578a.b(bArr, bArr2);
        } catch (ProviderException e9) {
            e = e9;
            Log.w(f11577b, "encountered a potentially transient KeyStore error, will wait and retry", e);
            c();
            return this.f11578a.b(bArr, bArr2);
        } catch (BadPaddingException e10) {
            throw e10;
        }
    }
}
