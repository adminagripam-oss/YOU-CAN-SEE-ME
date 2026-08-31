package z4;

import j4.v;
import java.nio.ByteBuffer;
import java.security.GeneralSecurityException;
import java.util.Arrays;
import javax.crypto.spec.SecretKeySpec;
import t4.k0;
/* loaded from: classes.dex */
public final class h implements j4.a {

    /* renamed from: a  reason: collision with root package name */
    private final l f14423a;

    /* renamed from: b  reason: collision with root package name */
    private final v f14424b;

    /* renamed from: c  reason: collision with root package name */
    private final int f14425c;

    /* renamed from: d  reason: collision with root package name */
    private final byte[] f14426d;

    private h(l lVar, v vVar, int i8, byte[] bArr) {
        this.f14423a = lVar;
        this.f14424b = vVar;
        this.f14425c = i8;
        this.f14426d = bArr;
    }

    public static j4.a c(k4.f fVar) {
        a aVar = new a(fVar.e().d(j4.g.a()), fVar.b().f());
        return new h(aVar, new o(new n("HMAC" + fVar.b().d(), new SecretKeySpec(fVar.f().d(j4.g.a()), "HMAC")), fVar.b().g()), fVar.b().g(), fVar.c().d());
    }

    @Override // j4.a
    public byte[] a(byte[] bArr, byte[] bArr2) {
        byte[] a8 = this.f14423a.a(bArr);
        if (bArr2 == null) {
            bArr2 = new byte[0];
        }
        return f.a(this.f14426d, a8, this.f14424b.b(f.a(bArr2, a8, Arrays.copyOf(ByteBuffer.allocate(8).putLong(bArr2.length * 8).array(), 8))));
    }

    @Override // j4.a
    public byte[] b(byte[] bArr, byte[] bArr2) {
        int length = bArr.length;
        int i8 = this.f14425c;
        byte[] bArr3 = this.f14426d;
        if (length >= i8 + bArr3.length) {
            if (k0.e(bArr3, bArr)) {
                byte[] copyOfRange = Arrays.copyOfRange(bArr, this.f14426d.length, bArr.length - this.f14425c);
                byte[] copyOfRange2 = Arrays.copyOfRange(bArr, bArr.length - this.f14425c, bArr.length);
                if (bArr2 == null) {
                    bArr2 = new byte[0];
                }
                this.f14424b.a(copyOfRange2, f.a(bArr2, copyOfRange, Arrays.copyOf(ByteBuffer.allocate(8).putLong(bArr2.length * 8).array(), 8)));
                return this.f14423a.b(copyOfRange);
            }
            throw new GeneralSecurityException("Decryption failed (OutputPrefix mismatch).");
        }
        throw new GeneralSecurityException("Decryption failed (ciphertext too short).");
    }
}
