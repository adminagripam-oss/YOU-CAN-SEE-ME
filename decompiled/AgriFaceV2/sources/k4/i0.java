package k4;

import java.nio.BufferUnderflowException;
import java.nio.ByteBuffer;
import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import y4.y;
/* loaded from: classes.dex */
public final class i0 implements j4.a {

    /* renamed from: d  reason: collision with root package name */
    private static final byte[] f8531d = new byte[0];

    /* renamed from: e  reason: collision with root package name */
    private static final Set f8532e = g();

    /* renamed from: a  reason: collision with root package name */
    private final String f8533a;

    /* renamed from: b  reason: collision with root package name */
    private final j4.w f8534b;

    /* renamed from: c  reason: collision with root package name */
    private final j4.a f8535c;

    public i0(y4.a0 a0Var, j4.a aVar) {
        if (f(a0Var.a0())) {
            this.f8533a = a0Var.a0();
            this.f8534b = e(a0Var);
            this.f8535c = aVar;
            return;
        }
        throw new IllegalArgumentException("Unsupported DEK key type: " + a0Var.a0() + ". Only Tink AEAD key types are supported.");
    }

    private byte[] c(byte[] bArr, byte[] bArr2) {
        return ByteBuffer.allocate(bArr.length + 4 + bArr2.length).putInt(bArr.length).put(bArr).put(bArr2).array();
    }

    public static j4.a d(c cVar, j4.a aVar) {
        try {
            return new i0(y4.a0.e0(j4.z.b(cVar), com.google.crypto.tink.shaded.protobuf.p.b()), aVar);
        } catch (com.google.crypto.tink.shaded.protobuf.a0 e8) {
            throw new GeneralSecurityException(e8);
        }
    }

    private j4.w e(y4.a0 a0Var) {
        return j4.z.a(((y4.a0) y4.a0.d0(a0Var).q(y4.i0.RAW).h()).i());
    }

    public static boolean f(String str) {
        return f8532e.contains(str);
    }

    private static Set g() {
        HashSet hashSet = new HashSet();
        hashSet.add("type.googleapis.com/google.crypto.tink.AesGcmKey");
        hashSet.add("type.googleapis.com/google.crypto.tink.ChaCha20Poly1305Key");
        hashSet.add("type.googleapis.com/google.crypto.tink.XChaCha20Poly1305Key");
        hashSet.add("type.googleapis.com/google.crypto.tink.AesCtrHmacAeadKey");
        hashSet.add("type.googleapis.com/google.crypto.tink.AesGcmSivKey");
        hashSet.add("type.googleapis.com/google.crypto.tink.AesEaxKey");
        return Collections.unmodifiableSet(hashSet);
    }

    @Override // j4.a
    public byte[] a(byte[] bArr, byte[] bArr2) {
        j4.h c8 = t4.o.f().c(this.f8534b, null);
        byte[] a8 = this.f8535c.a(((t4.d0) t4.u.c().n(c8, t4.d0.class, j4.g.a())).g().w(), f8531d);
        if (a8.length <= 4096) {
            return c(a8, ((j4.a) t4.r.b().a(c8, j4.a.class)).a(bArr, bArr2));
        }
        throw new GeneralSecurityException("length of encrypted DEK too large");
    }

    @Override // j4.a
    public byte[] b(byte[] bArr, byte[] bArr2) {
        try {
            ByteBuffer wrap = ByteBuffer.wrap(bArr);
            int i8 = wrap.getInt();
            if (i8 <= 0 || i8 > 4096 || i8 > bArr.length - 4) {
                throw new GeneralSecurityException("length of encrypted DEK too large");
            }
            byte[] bArr3 = new byte[i8];
            wrap.get(bArr3, 0, i8);
            byte[] bArr4 = new byte[wrap.remaining()];
            wrap.get(bArr4, 0, wrap.remaining());
            return ((j4.a) t4.r.b().a(t4.u.c().f(t4.d0.b(this.f8533a, com.google.crypto.tink.shaded.protobuf.h.l(this.f8535c.b(bArr3, f8531d)), y.c.SYMMETRIC, y4.i0.RAW, null), j4.g.a()), j4.a.class)).b(bArr4, bArr2);
        } catch (IndexOutOfBoundsException | NegativeArraySizeException | BufferUnderflowException e8) {
            throw new GeneralSecurityException("invalid ciphertext", e8);
        }
    }
}
