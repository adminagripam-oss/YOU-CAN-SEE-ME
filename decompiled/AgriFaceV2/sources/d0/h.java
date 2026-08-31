package d0;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
/* loaded from: classes.dex */
final class h {

    /* renamed from: e  reason: collision with root package name */
    static final Charset f6372e = StandardCharsets.US_ASCII;

    /* renamed from: f  reason: collision with root package name */
    static final String[] f6373f = {"", "BYTE", "STRING", "USHORT", "ULONG", "URATIONAL", "SBYTE", "UNDEFINED", "SSHORT", "SLONG", "SRATIONAL", "SINGLE", "DOUBLE", "IFD"};

    /* renamed from: g  reason: collision with root package name */
    static final int[] f6374g = {0, 1, 1, 2, 4, 8, 1, 1, 2, 4, 8, 4, 8, 1};

    /* renamed from: h  reason: collision with root package name */
    static final byte[] f6375h = {65, 83, 67, 73, 73, 0, 0, 0};

    /* renamed from: a  reason: collision with root package name */
    public final int f6376a;

    /* renamed from: b  reason: collision with root package name */
    public final int f6377b;

    /* renamed from: c  reason: collision with root package name */
    public final long f6378c;

    /* renamed from: d  reason: collision with root package name */
    public final byte[] f6379d;

    h(int i8, int i9, long j8, byte[] bArr) {
        this.f6376a = i8;
        this.f6377b = i9;
        this.f6378c = j8;
        this.f6379d = bArr;
    }

    public static h a(String str) {
        if (str.length() != 1 || str.charAt(0) < '0' || str.charAt(0) > '1') {
            byte[] bytes = str.getBytes(f6372e);
            return new h(1, bytes.length, bytes);
        }
        return new h(1, 1, new byte[]{(byte) (str.charAt(0) - '0')});
    }

    public static h b(double[] dArr, ByteOrder byteOrder) {
        ByteBuffer wrap = ByteBuffer.wrap(new byte[f6374g[12] * dArr.length]);
        wrap.order(byteOrder);
        for (double d8 : dArr) {
            wrap.putDouble(d8);
        }
        return new h(12, dArr.length, wrap.array());
    }

    public static h c(int[] iArr, ByteOrder byteOrder) {
        ByteBuffer wrap = ByteBuffer.wrap(new byte[f6374g[9] * iArr.length]);
        wrap.order(byteOrder);
        for (int i8 : iArr) {
            wrap.putInt(i8);
        }
        return new h(9, iArr.length, wrap.array());
    }

    public static h d(l[] lVarArr, ByteOrder byteOrder) {
        ByteBuffer wrap = ByteBuffer.wrap(new byte[f6374g[10] * lVarArr.length]);
        wrap.order(byteOrder);
        for (l lVar : lVarArr) {
            wrap.putInt((int) lVar.b());
            wrap.putInt((int) lVar.a());
        }
        return new h(10, lVarArr.length, wrap.array());
    }

    public static h e(String str) {
        byte[] bytes = (str + (char) 0).getBytes(f6372e);
        return new h(2, bytes.length, bytes);
    }

    public static h f(long j8, ByteOrder byteOrder) {
        return g(new long[]{j8}, byteOrder);
    }

    public static h g(long[] jArr, ByteOrder byteOrder) {
        ByteBuffer wrap = ByteBuffer.wrap(new byte[f6374g[4] * jArr.length]);
        wrap.order(byteOrder);
        for (long j8 : jArr) {
            wrap.putInt((int) j8);
        }
        return new h(4, jArr.length, wrap.array());
    }

    public static h h(l[] lVarArr, ByteOrder byteOrder) {
        ByteBuffer wrap = ByteBuffer.wrap(new byte[f6374g[5] * lVarArr.length]);
        wrap.order(byteOrder);
        for (l lVar : lVarArr) {
            wrap.putInt((int) lVar.b());
            wrap.putInt((int) lVar.a());
        }
        return new h(5, lVarArr.length, wrap.array());
    }

    public static h i(int[] iArr, ByteOrder byteOrder) {
        ByteBuffer wrap = ByteBuffer.wrap(new byte[f6374g[3] * iArr.length]);
        wrap.order(byteOrder);
        for (int i8 : iArr) {
            wrap.putShort((short) i8);
        }
        return new h(3, iArr.length, wrap.array());
    }

    public int j() {
        return f6374g[this.f6376a] * this.f6377b;
    }

    public String toString() {
        return "(" + f6373f[this.f6376a] + ", data length:" + this.f6379d.length + ")";
    }

    h(int i8, int i9, byte[] bArr) {
        this(i8, i9, -1L, bArr);
    }
}
