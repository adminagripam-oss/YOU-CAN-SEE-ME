package l4;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.security.GeneralSecurityException;
import javax.crypto.AEADBadTagException;
import o4.b;
/* loaded from: classes.dex */
abstract class h0 {

    /* renamed from: c  reason: collision with root package name */
    public static final b.EnumC0135b f8996c = b.EnumC0135b.f9848e;

    /* renamed from: a  reason: collision with root package name */
    private final f0 f8997a;

    /* renamed from: b  reason: collision with root package name */
    private final f0 f8998b;

    public h0(byte[] bArr) {
        if (!f8996c.e()) {
            throw new GeneralSecurityException("Can not use ChaCha20Poly1305 in FIPS-mode.");
        }
        this.f8997a = e(bArr, 1);
        this.f8998b = e(bArr, 0);
    }

    private byte[] c(byte[] bArr) {
        byte[] bArr2 = new byte[32];
        this.f8998b.a(bArr, 0).get(bArr2);
        return bArr2;
    }

    private static byte[] d(byte[] bArr, ByteBuffer byteBuffer) {
        int length = bArr.length % 16 == 0 ? bArr.length : (bArr.length + 16) - (bArr.length % 16);
        int remaining = byteBuffer.remaining();
        int i8 = remaining % 16;
        int i9 = (i8 == 0 ? remaining : (remaining + 16) - i8) + length;
        ByteBuffer order = ByteBuffer.allocate(i9 + 16).order(ByteOrder.LITTLE_ENDIAN);
        order.put(bArr);
        order.position(length);
        order.put(byteBuffer);
        order.position(i9);
        order.putLong(bArr.length);
        order.putLong(remaining);
        return order.array();
    }

    public byte[] a(ByteBuffer byteBuffer, byte[] bArr, byte[] bArr2) {
        if (byteBuffer.remaining() >= 16) {
            int position = byteBuffer.position();
            byte[] bArr3 = new byte[16];
            byteBuffer.position(byteBuffer.limit() - 16);
            byteBuffer.get(bArr3);
            byteBuffer.position(position);
            byteBuffer.limit(byteBuffer.limit() - 16);
            if (bArr2 == null) {
                bArr2 = new byte[0];
            }
            try {
                l0.f(c(bArr), d(bArr2, byteBuffer), bArr3);
                byteBuffer.position(position);
                return this.f8997a.c(bArr, byteBuffer);
            } catch (GeneralSecurityException e8) {
                throw new AEADBadTagException(e8.toString());
            }
        }
        throw new GeneralSecurityException("ciphertext too short");
    }

    public void b(ByteBuffer byteBuffer, byte[] bArr, byte[] bArr2, byte[] bArr3) {
        if (byteBuffer.remaining() < bArr2.length + 16) {
            throw new IllegalArgumentException("Given ByteBuffer output is too small");
        }
        int position = byteBuffer.position();
        this.f8997a.d(byteBuffer, bArr, bArr2);
        byteBuffer.position(position);
        byteBuffer.limit(byteBuffer.limit() - 16);
        if (bArr3 == null) {
            bArr3 = new byte[0];
        }
        byte[] a8 = l0.a(c(bArr), d(bArr3, byteBuffer));
        byteBuffer.limit(byteBuffer.limit() + 16);
        byteBuffer.put(a8);
    }

    abstract f0 e(byte[] bArr, int i8);
}
