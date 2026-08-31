package i6;

import java.io.ByteArrayOutputStream;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/* loaded from: classes.dex */
public class n implements h {

    /* renamed from: a  reason: collision with root package name */
    public static final n f7547a = new n();

    /* renamed from: b  reason: collision with root package name */
    private static final boolean f7548b;

    /* renamed from: c  reason: collision with root package name */
    private static final Charset f7549c;

    /* loaded from: classes.dex */
    static final class a extends ByteArrayOutputStream {
        /* JADX INFO: Access modifiers changed from: package-private */
        public byte[] b() {
            return ((ByteArrayOutputStream) this).buf;
        }
    }

    static {
        f7548b = ByteOrder.nativeOrder() == ByteOrder.LITTLE_ENDIAN;
        f7549c = Charset.forName("UTF8");
    }

    protected static final void c(ByteBuffer byteBuffer, int i8) {
        int position = byteBuffer.position() % i8;
        if (position != 0) {
            byteBuffer.position((byteBuffer.position() + i8) - position);
        }
    }

    protected static final byte[] d(ByteBuffer byteBuffer) {
        byte[] bArr = new byte[e(byteBuffer)];
        byteBuffer.get(bArr);
        return bArr;
    }

    protected static final int e(ByteBuffer byteBuffer) {
        if (byteBuffer.hasRemaining()) {
            int i8 = byteBuffer.get() & 255;
            return i8 < 254 ? i8 : i8 == 254 ? byteBuffer.getChar() : byteBuffer.getInt();
        }
        throw new IllegalArgumentException("Message corrupted");
    }

    protected static final void h(ByteArrayOutputStream byteArrayOutputStream, int i8) {
        int size = byteArrayOutputStream.size() % i8;
        if (size != 0) {
            for (int i9 = 0; i9 < i8 - size; i9++) {
                byteArrayOutputStream.write(0);
            }
        }
    }

    protected static final void i(ByteArrayOutputStream byteArrayOutputStream, byte[] bArr) {
        o(byteArrayOutputStream, bArr.length);
        byteArrayOutputStream.write(bArr, 0, bArr.length);
    }

    protected static final void j(ByteArrayOutputStream byteArrayOutputStream, int i8) {
        if (f7548b) {
            byteArrayOutputStream.write(i8);
            i8 >>>= 8;
        } else {
            byteArrayOutputStream.write(i8 >>> 8);
        }
        byteArrayOutputStream.write(i8);
    }

    protected static final void k(ByteArrayOutputStream byteArrayOutputStream, double d8) {
        n(byteArrayOutputStream, Double.doubleToLongBits(d8));
    }

    protected static final void l(ByteArrayOutputStream byteArrayOutputStream, float f8) {
        m(byteArrayOutputStream, Float.floatToIntBits(f8));
    }

    protected static final void m(ByteArrayOutputStream byteArrayOutputStream, int i8) {
        if (f7548b) {
            byteArrayOutputStream.write(i8);
            byteArrayOutputStream.write(i8 >>> 8);
            byteArrayOutputStream.write(i8 >>> 16);
            i8 >>>= 24;
        } else {
            byteArrayOutputStream.write(i8 >>> 24);
            byteArrayOutputStream.write(i8 >>> 16);
            byteArrayOutputStream.write(i8 >>> 8);
        }
        byteArrayOutputStream.write(i8);
    }

    protected static final void n(ByteArrayOutputStream byteArrayOutputStream, long j8) {
        if (f7548b) {
            byteArrayOutputStream.write((byte) j8);
            byteArrayOutputStream.write((byte) (j8 >>> 8));
            byteArrayOutputStream.write((byte) (j8 >>> 16));
            byteArrayOutputStream.write((byte) (j8 >>> 24));
            byteArrayOutputStream.write((byte) (j8 >>> 32));
            byteArrayOutputStream.write((byte) (j8 >>> 40));
            byteArrayOutputStream.write((byte) (j8 >>> 48));
            j8 >>>= 56;
        } else {
            byteArrayOutputStream.write((byte) (j8 >>> 56));
            byteArrayOutputStream.write((byte) (j8 >>> 48));
            byteArrayOutputStream.write((byte) (j8 >>> 40));
            byteArrayOutputStream.write((byte) (j8 >>> 32));
            byteArrayOutputStream.write((byte) (j8 >>> 24));
            byteArrayOutputStream.write((byte) (j8 >>> 16));
            byteArrayOutputStream.write((byte) (j8 >>> 8));
        }
        byteArrayOutputStream.write((byte) j8);
    }

    protected static final void o(ByteArrayOutputStream byteArrayOutputStream, int i8) {
        if (i8 < 254) {
            byteArrayOutputStream.write(i8);
        } else if (i8 <= 65535) {
            byteArrayOutputStream.write(254);
            j(byteArrayOutputStream, i8);
        } else {
            byteArrayOutputStream.write(255);
            m(byteArrayOutputStream, i8);
        }
    }

    @Override // i6.h
    public ByteBuffer a(Object obj) {
        if (obj == null) {
            return null;
        }
        a aVar = new a();
        p(aVar, obj);
        ByteBuffer allocateDirect = ByteBuffer.allocateDirect(aVar.size());
        allocateDirect.put(aVar.b(), 0, aVar.size());
        return allocateDirect;
    }

    @Override // i6.h
    public Object b(ByteBuffer byteBuffer) {
        if (byteBuffer == null) {
            return null;
        }
        byteBuffer.order(ByteOrder.nativeOrder());
        Object f8 = f(byteBuffer);
        if (byteBuffer.hasRemaining()) {
            throw new IllegalArgumentException("Message corrupted");
        }
        return f8;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final Object f(ByteBuffer byteBuffer) {
        if (byteBuffer.hasRemaining()) {
            return g(byteBuffer.get(), byteBuffer);
        }
        throw new IllegalArgumentException("Message corrupted");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v6, types: [java.util.List, java.util.ArrayList] */
    /* JADX WARN: Type inference failed for: r1v7 */
    /* JADX WARN: Type inference failed for: r1v8, types: [java.util.Map, java.util.HashMap] */
    public Object g(byte b8, ByteBuffer byteBuffer) {
        int e8;
        int e9;
        long[] jArr;
        ?? arrayList;
        int[] iArr;
        int i8 = 0;
        switch (b8) {
            case 0:
                return null;
            case 1:
                return Boolean.TRUE;
            case 2:
                return Boolean.FALSE;
            case 3:
                return Integer.valueOf(byteBuffer.getInt());
            case 4:
                return Long.valueOf(byteBuffer.getLong());
            case 5:
                return new BigInteger(new String(d(byteBuffer), f7549c), 16);
            case androidx.browser.customtabs.b.TAB_HIDDEN /* 6 */:
                c(byteBuffer, 8);
                return Double.valueOf(byteBuffer.getDouble());
            case 7:
                return new String(d(byteBuffer), f7549c);
            case 8:
                return d(byteBuffer);
            case 9:
                e8 = e(byteBuffer);
                int[] iArr2 = new int[e8];
                c(byteBuffer, 4);
                byteBuffer.asIntBuffer().get(iArr2);
                iArr = iArr2;
                byteBuffer.position(byteBuffer.position() + (e8 * 4));
                return iArr;
            case 10:
                e9 = e(byteBuffer);
                long[] jArr2 = new long[e9];
                c(byteBuffer, 8);
                byteBuffer.asLongBuffer().get(jArr2);
                jArr = jArr2;
                byteBuffer.position(byteBuffer.position() + (e9 * 8));
                return jArr;
            case 11:
                e9 = e(byteBuffer);
                double[] dArr = new double[e9];
                c(byteBuffer, 8);
                byteBuffer.asDoubleBuffer().get(dArr);
                jArr = dArr;
                byteBuffer.position(byteBuffer.position() + (e9 * 8));
                return jArr;
            case 12:
                int e10 = e(byteBuffer);
                arrayList = new ArrayList(e10);
                while (i8 < e10) {
                    arrayList.add(f(byteBuffer));
                    i8++;
                }
                return arrayList;
            case 13:
                int e11 = e(byteBuffer);
                arrayList = new HashMap();
                while (i8 < e11) {
                    arrayList.put(f(byteBuffer), f(byteBuffer));
                    i8++;
                }
                return arrayList;
            case 14:
                e8 = e(byteBuffer);
                float[] fArr = new float[e8];
                c(byteBuffer, 4);
                byteBuffer.asFloatBuffer().get(fArr);
                iArr = fArr;
                byteBuffer.position(byteBuffer.position() + (e8 * 4));
                return iArr;
            default:
                throw new IllegalArgumentException("Message corrupted");
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void p(ByteArrayOutputStream byteArrayOutputStream, Object obj) {
        int i8 = 0;
        if (obj == null || obj.equals(null)) {
            byteArrayOutputStream.write(0);
        } else if (obj instanceof Boolean) {
            byteArrayOutputStream.write(((Boolean) obj).booleanValue() ? 1 : 2);
        } else if (obj instanceof Number) {
            if ((obj instanceof Integer) || (obj instanceof Short) || (obj instanceof Byte)) {
                byteArrayOutputStream.write(3);
                m(byteArrayOutputStream, ((Number) obj).intValue());
            } else if (obj instanceof Long) {
                byteArrayOutputStream.write(4);
                n(byteArrayOutputStream, ((Long) obj).longValue());
            } else if ((obj instanceof Float) || (obj instanceof Double)) {
                byteArrayOutputStream.write(6);
                h(byteArrayOutputStream, 8);
                k(byteArrayOutputStream, ((Number) obj).doubleValue());
            } else if (!(obj instanceof BigInteger)) {
                throw new IllegalArgumentException("Unsupported Number type: " + obj.getClass());
            } else {
                byteArrayOutputStream.write(5);
                i(byteArrayOutputStream, ((BigInteger) obj).toString(16).getBytes(f7549c));
            }
        } else if (obj instanceof CharSequence) {
            byteArrayOutputStream.write(7);
            i(byteArrayOutputStream, obj.toString().getBytes(f7549c));
        } else if (obj instanceof byte[]) {
            byteArrayOutputStream.write(8);
            i(byteArrayOutputStream, (byte[]) obj);
        } else if (obj instanceof int[]) {
            byteArrayOutputStream.write(9);
            int[] iArr = (int[]) obj;
            o(byteArrayOutputStream, iArr.length);
            h(byteArrayOutputStream, 4);
            int length = iArr.length;
            while (i8 < length) {
                m(byteArrayOutputStream, iArr[i8]);
                i8++;
            }
        } else if (obj instanceof long[]) {
            byteArrayOutputStream.write(10);
            long[] jArr = (long[]) obj;
            o(byteArrayOutputStream, jArr.length);
            h(byteArrayOutputStream, 8);
            int length2 = jArr.length;
            while (i8 < length2) {
                n(byteArrayOutputStream, jArr[i8]);
                i8++;
            }
        } else if (obj instanceof double[]) {
            byteArrayOutputStream.write(11);
            double[] dArr = (double[]) obj;
            o(byteArrayOutputStream, dArr.length);
            h(byteArrayOutputStream, 8);
            int length3 = dArr.length;
            while (i8 < length3) {
                k(byteArrayOutputStream, dArr[i8]);
                i8++;
            }
        } else if (obj instanceof List) {
            byteArrayOutputStream.write(12);
            List<Object> list = (List) obj;
            o(byteArrayOutputStream, list.size());
            for (Object obj2 : list) {
                p(byteArrayOutputStream, obj2);
            }
        } else if (obj instanceof Map) {
            byteArrayOutputStream.write(13);
            Map map = (Map) obj;
            o(byteArrayOutputStream, map.size());
            for (Map.Entry entry : map.entrySet()) {
                p(byteArrayOutputStream, entry.getKey());
                p(byteArrayOutputStream, entry.getValue());
            }
        } else if (!(obj instanceof float[])) {
            throw new IllegalArgumentException("Unsupported value: '" + obj + "' of type '" + obj.getClass() + "'");
        } else {
            byteArrayOutputStream.write(14);
            float[] fArr = (float[]) obj;
            o(byteArrayOutputStream, fArr.length);
            h(byteArrayOutputStream, 4);
            int length4 = fArr.length;
            while (i8 < length4) {
                l(byteArrayOutputStream, fArr[i8]);
                i8++;
            }
        }
    }
}
