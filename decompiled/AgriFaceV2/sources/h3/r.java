package h3;

import android.os.RemoteException;
import android.util.Log;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import k3.n1;
import k3.o0;
/* loaded from: classes.dex */
abstract class r extends n1 {

    /* renamed from: e  reason: collision with root package name */
    private final int f7249e;

    /* JADX INFO: Access modifiers changed from: protected */
    public r(byte[] bArr) {
        k3.q.a(bArr.length == 25);
        this.f7249e = Arrays.hashCode(bArr);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static byte[] e(String str) {
        try {
            return str.getBytes("ISO-8859-1");
        } catch (UnsupportedEncodingException e8) {
            throw new AssertionError(e8);
        }
    }

    @Override // k3.o0
    public final int a() {
        return this.f7249e;
    }

    @Override // k3.o0
    public final t3.a b() {
        return t3.b.d0(f());
    }

    public final boolean equals(Object obj) {
        t3.a b8;
        if (obj != null && (obj instanceof o0)) {
            try {
                o0 o0Var = (o0) obj;
                if (o0Var.a() == this.f7249e && (b8 = o0Var.b()) != null) {
                    return Arrays.equals(f(), (byte[]) t3.b.f(b8));
                }
                return false;
            } catch (RemoteException e8) {
                Log.e("GoogleCertificates", "Failed to get Google certificates from remote", e8);
            }
        }
        return false;
    }

    abstract byte[] f();

    public final int hashCode() {
        return this.f7249e;
    }
}
