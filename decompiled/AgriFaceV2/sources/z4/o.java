package z4;

import j4.v;
import java.security.GeneralSecurityException;
import java.security.InvalidAlgorithmParameterException;
import java.util.Arrays;
import javax.crypto.spec.SecretKeySpec;
import u4.f;
import u4.n;
/* loaded from: classes.dex */
public class o implements v {

    /* renamed from: e  reason: collision with root package name */
    private static final byte[] f14446e = {0};

    /* renamed from: a  reason: collision with root package name */
    private final w4.c f14447a;

    /* renamed from: b  reason: collision with root package name */
    private final int f14448b;

    /* renamed from: c  reason: collision with root package name */
    private final byte[] f14449c;

    /* renamed from: d  reason: collision with root package name */
    private final byte[] f14450d;

    private o(u4.a aVar) {
        this.f14447a = e(aVar);
        this.f14448b = aVar.b().c();
        this.f14449c = aVar.c().d();
        if (!aVar.b().f().equals(f.c.f12119d)) {
            this.f14450d = new byte[0];
            return;
        }
        byte[] bArr = f14446e;
        this.f14450d = Arrays.copyOf(bArr, bArr.length);
    }

    public static v c(u4.a aVar) {
        return new o(aVar);
    }

    public static v d(u4.i iVar) {
        return new o(iVar);
    }

    private static w4.c e(u4.a aVar) {
        return m.b(w4.a.c(w4.b.b(aVar.b().d()), aVar.e()));
    }

    @Override // j4.v
    public void a(byte[] bArr, byte[] bArr2) {
        if (!f.b(b(bArr2), bArr)) {
            throw new GeneralSecurityException("invalid MAC");
        }
    }

    @Override // j4.v
    public byte[] b(byte[] bArr) {
        byte[] bArr2 = this.f14450d;
        return bArr2.length > 0 ? f.a(this.f14449c, this.f14447a.a(f.a(bArr, bArr2), this.f14448b)) : f.a(this.f14449c, this.f14447a.a(bArr, this.f14448b));
    }

    private o(u4.i iVar) {
        this.f14447a = new n("HMAC" + iVar.b().d(), new SecretKeySpec(iVar.e().d(j4.g.a()), "HMAC"));
        this.f14448b = iVar.b().c();
        this.f14449c = iVar.c().d();
        if (!iVar.b().g().equals(n.d.f12152d)) {
            this.f14450d = new byte[0];
            return;
        }
        byte[] bArr = f14446e;
        this.f14450d = Arrays.copyOf(bArr, bArr.length);
    }

    public o(w4.c cVar, int i8) {
        this.f14447a = cVar;
        this.f14448b = i8;
        this.f14449c = new byte[0];
        this.f14450d = new byte[0];
        if (i8 < 10) {
            throw new InvalidAlgorithmParameterException("tag size too small, need at least 10 bytes");
        }
        cVar.a(new byte[0], i8);
    }
}
