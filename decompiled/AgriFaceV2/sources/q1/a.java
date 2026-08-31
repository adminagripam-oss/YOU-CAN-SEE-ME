package q1;

import android.content.res.AssetManager;
import android.location.Location;
import android.media.MediaDataSource;
import android.media.MediaMetadataRetriever;
import android.system.Os;
import android.system.OsConstants;
import android.util.Log;
import android.util.Pair;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.DataInput;
import java.io.DataInputStream;
import java.io.EOFException;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.CRC32;
/* loaded from: classes.dex */
public class a {
    private static SimpleDateFormat U;
    private static SimpleDateFormat V;
    private static final e[] Z;

    /* renamed from: a0  reason: collision with root package name */
    private static final e[] f10239a0;

    /* renamed from: b0  reason: collision with root package name */
    private static final e[] f10240b0;

    /* renamed from: c0  reason: collision with root package name */
    private static final e[] f10241c0;

    /* renamed from: d0  reason: collision with root package name */
    private static final e[] f10242d0;

    /* renamed from: e0  reason: collision with root package name */
    private static final e f10243e0;

    /* renamed from: f0  reason: collision with root package name */
    private static final e[] f10244f0;

    /* renamed from: g0  reason: collision with root package name */
    private static final e[] f10245g0;

    /* renamed from: h0  reason: collision with root package name */
    private static final e[] f10246h0;

    /* renamed from: i0  reason: collision with root package name */
    private static final e[] f10247i0;

    /* renamed from: j0  reason: collision with root package name */
    static final e[][] f10248j0;

    /* renamed from: k0  reason: collision with root package name */
    private static final e[] f10249k0;

    /* renamed from: l0  reason: collision with root package name */
    private static final e f10250l0;

    /* renamed from: m0  reason: collision with root package name */
    private static final e f10251m0;

    /* renamed from: n0  reason: collision with root package name */
    private static final HashMap[] f10252n0;

    /* renamed from: o0  reason: collision with root package name */
    private static final HashMap[] f10253o0;

    /* renamed from: p0  reason: collision with root package name */
    private static final HashSet f10254p0;

    /* renamed from: q0  reason: collision with root package name */
    private static final HashMap f10255q0;

    /* renamed from: r0  reason: collision with root package name */
    static final Charset f10256r0;

    /* renamed from: s0  reason: collision with root package name */
    static final byte[] f10257s0;

    /* renamed from: t0  reason: collision with root package name */
    private static final byte[] f10258t0;

    /* renamed from: u0  reason: collision with root package name */
    private static final Pattern f10259u0;

    /* renamed from: v0  reason: collision with root package name */
    private static final Pattern f10261v0;

    /* renamed from: w0  reason: collision with root package name */
    private static final Pattern f10263w0;

    /* renamed from: x0  reason: collision with root package name */
    private static final Pattern f10265x0;

    /* renamed from: a  reason: collision with root package name */
    private String f10268a;

    /* renamed from: b  reason: collision with root package name */
    private FileDescriptor f10269b;

    /* renamed from: c  reason: collision with root package name */
    private AssetManager.AssetInputStream f10270c;

    /* renamed from: d  reason: collision with root package name */
    private int f10271d;

    /* renamed from: e  reason: collision with root package name */
    private boolean f10272e;

    /* renamed from: f  reason: collision with root package name */
    private final HashMap[] f10273f;

    /* renamed from: g  reason: collision with root package name */
    private Set f10274g;

    /* renamed from: h  reason: collision with root package name */
    private ByteOrder f10275h;

    /* renamed from: i  reason: collision with root package name */
    private boolean f10276i;

    /* renamed from: j  reason: collision with root package name */
    private boolean f10277j;

    /* renamed from: k  reason: collision with root package name */
    private boolean f10278k;

    /* renamed from: l  reason: collision with root package name */
    private int f10279l;

    /* renamed from: m  reason: collision with root package name */
    private int f10280m;

    /* renamed from: n  reason: collision with root package name */
    private byte[] f10281n;

    /* renamed from: o  reason: collision with root package name */
    private int f10282o;

    /* renamed from: p  reason: collision with root package name */
    private int f10283p;

    /* renamed from: q  reason: collision with root package name */
    private int f10284q;

    /* renamed from: r  reason: collision with root package name */
    private int f10285r;

    /* renamed from: s  reason: collision with root package name */
    private int f10286s;

    /* renamed from: t  reason: collision with root package name */
    private boolean f10287t;

    /* renamed from: u  reason: collision with root package name */
    private boolean f10288u;

    /* renamed from: v  reason: collision with root package name */
    private static final boolean f10260v = Log.isLoggable("ExifInterface", 3);

    /* renamed from: w  reason: collision with root package name */
    private static final List f10262w = Arrays.asList(1, 6, 3, 8);

    /* renamed from: x  reason: collision with root package name */
    private static final List f10264x = Arrays.asList(2, 7, 4, 5);

    /* renamed from: y  reason: collision with root package name */
    public static final int[] f10266y = {8, 8, 8};

    /* renamed from: z  reason: collision with root package name */
    public static final int[] f10267z = {4};
    public static final int[] A = {8};
    static final byte[] B = {-1, -40, -1};
    private static final byte[] C = {102, 116, 121, 112};
    private static final byte[] D = {109, 105, 102, 49};
    private static final byte[] E = {104, 101, 105, 99};
    private static final byte[] F = {79, 76, 89, 77, 80, 0};
    private static final byte[] G = {79, 76, 89, 77, 80, 85, 83, 0, 73, 73};
    private static final byte[] H = {-119, 80, 78, 71, 13, 10, 26, 10};
    private static final byte[] I = {101, 88, 73, 102};
    private static final byte[] J = {73, 72, 68, 82};
    private static final byte[] K = {73, 69, 78, 68};
    private static final byte[] L = {82, 73, 70, 70};
    private static final byte[] M = {87, 69, 66, 80};
    private static final byte[] N = {69, 88, 73, 70};
    private static final byte[] O = {-99, 1, 42};
    private static final byte[] P = "VP8X".getBytes(Charset.defaultCharset());
    private static final byte[] Q = "VP8L".getBytes(Charset.defaultCharset());
    private static final byte[] R = "VP8 ".getBytes(Charset.defaultCharset());
    private static final byte[] S = "ANIM".getBytes(Charset.defaultCharset());
    private static final byte[] T = "ANMF".getBytes(Charset.defaultCharset());
    static final String[] W = {"", "BYTE", "STRING", "USHORT", "ULONG", "URATIONAL", "SBYTE", "UNDEFINED", "SSHORT", "SLONG", "SRATIONAL", "SINGLE", "DOUBLE", "IFD"};
    static final int[] X = {0, 1, 1, 2, 4, 8, 1, 1, 2, 4, 8, 4, 8, 1};
    static final byte[] Y = {65, 83, 67, 73, 73, 0, 0, 0};

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: q1.a$a  reason: collision with other inner class name */
    /* loaded from: classes.dex */
    public class C0142a extends MediaDataSource {

        /* renamed from: e  reason: collision with root package name */
        long f10289e;

        /* renamed from: f  reason: collision with root package name */
        final /* synthetic */ b f10290f;

        C0142a(b bVar) {
            this.f10290f = bVar;
        }

        @Override // java.io.Closeable, java.lang.AutoCloseable
        public void close() {
        }

        @Override // android.media.MediaDataSource
        public long getSize() {
            return -1L;
        }

        @Override // android.media.MediaDataSource
        public int readAt(long j8, byte[] bArr, int i8, int i9) {
            if (i9 == 0) {
                return 0;
            }
            if (j8 < 0) {
                return -1;
            }
            try {
                long j9 = this.f10289e;
                if (j9 != j8) {
                    if (j9 >= 0 && j8 >= j9 + this.f10290f.available()) {
                        return -1;
                    }
                    this.f10290f.f(j8);
                    this.f10289e = j8;
                }
                if (i9 > this.f10290f.available()) {
                    i9 = this.f10290f.available();
                }
                int read = this.f10290f.read(bArr, i8, i9);
                if (read >= 0) {
                    this.f10289e += read;
                    return read;
                }
            } catch (IOException unused) {
            }
            this.f10289e = -1L;
            return -1;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class b extends InputStream implements DataInput {

        /* renamed from: i  reason: collision with root package name */
        private static final ByteOrder f10292i = ByteOrder.LITTLE_ENDIAN;

        /* renamed from: j  reason: collision with root package name */
        private static final ByteOrder f10293j = ByteOrder.BIG_ENDIAN;

        /* renamed from: e  reason: collision with root package name */
        private DataInputStream f10294e;

        /* renamed from: f  reason: collision with root package name */
        private ByteOrder f10295f;

        /* renamed from: g  reason: collision with root package name */
        final int f10296g;

        /* renamed from: h  reason: collision with root package name */
        int f10297h;

        public b(InputStream inputStream) {
            this(inputStream, ByteOrder.BIG_ENDIAN);
        }

        @Override // java.io.InputStream
        public int available() {
            return this.f10294e.available();
        }

        public int b() {
            return this.f10296g;
        }

        public int c() {
            return this.f10297h;
        }

        public long d() {
            return readInt() & 4294967295L;
        }

        public void f(long j8) {
            int i8 = this.f10297h;
            if (i8 > j8) {
                this.f10297h = 0;
                this.f10294e.reset();
                this.f10294e.mark(this.f10296g);
            } else {
                j8 -= i8;
            }
            int i9 = (int) j8;
            if (skipBytes(i9) != i9) {
                throw new IOException("Couldn't seek up to the byteCount");
            }
        }

        public void g(ByteOrder byteOrder) {
            this.f10295f = byteOrder;
        }

        @Override // java.io.InputStream
        public synchronized void mark(int i8) {
            this.f10294e.mark(i8);
        }

        @Override // java.io.InputStream
        public int read() {
            this.f10297h++;
            return this.f10294e.read();
        }

        @Override // java.io.DataInput
        public boolean readBoolean() {
            this.f10297h++;
            return this.f10294e.readBoolean();
        }

        @Override // java.io.DataInput
        public byte readByte() {
            int i8 = this.f10297h + 1;
            this.f10297h = i8;
            if (i8 <= this.f10296g) {
                int read = this.f10294e.read();
                if (read >= 0) {
                    return (byte) read;
                }
                throw new EOFException();
            }
            throw new EOFException();
        }

        @Override // java.io.DataInput
        public char readChar() {
            this.f10297h += 2;
            return this.f10294e.readChar();
        }

        @Override // java.io.DataInput
        public double readDouble() {
            return Double.longBitsToDouble(readLong());
        }

        @Override // java.io.DataInput
        public float readFloat() {
            return Float.intBitsToFloat(readInt());
        }

        @Override // java.io.DataInput
        public void readFully(byte[] bArr) {
            int length = this.f10297h + bArr.length;
            this.f10297h = length;
            if (length > this.f10296g) {
                throw new EOFException();
            }
            if (this.f10294e.read(bArr, 0, bArr.length) != bArr.length) {
                throw new IOException("Couldn't read up to the length of buffer");
            }
        }

        @Override // java.io.DataInput
        public int readInt() {
            int i8 = this.f10297h + 4;
            this.f10297h = i8;
            if (i8 <= this.f10296g) {
                int read = this.f10294e.read();
                int read2 = this.f10294e.read();
                int read3 = this.f10294e.read();
                int read4 = this.f10294e.read();
                if ((read | read2 | read3 | read4) >= 0) {
                    ByteOrder byteOrder = this.f10295f;
                    if (byteOrder == f10292i) {
                        return (read4 << 24) + (read3 << 16) + (read2 << 8) + read;
                    }
                    if (byteOrder == f10293j) {
                        return (read << 24) + (read2 << 16) + (read3 << 8) + read4;
                    }
                    throw new IOException("Invalid byte order: " + this.f10295f);
                }
                throw new EOFException();
            }
            throw new EOFException();
        }

        @Override // java.io.DataInput
        public String readLine() {
            Log.d("ExifInterface", "Currently unsupported");
            return null;
        }

        @Override // java.io.DataInput
        public long readLong() {
            int i8 = this.f10297h + 8;
            this.f10297h = i8;
            if (i8 <= this.f10296g) {
                int read = this.f10294e.read();
                int read2 = this.f10294e.read();
                int read3 = this.f10294e.read();
                int read4 = this.f10294e.read();
                int read5 = this.f10294e.read();
                int read6 = this.f10294e.read();
                int read7 = this.f10294e.read();
                int read8 = this.f10294e.read();
                if ((read | read2 | read3 | read4 | read5 | read6 | read7 | read8) >= 0) {
                    ByteOrder byteOrder = this.f10295f;
                    if (byteOrder == f10292i) {
                        return (read8 << 56) + (read7 << 48) + (read6 << 40) + (read5 << 32) + (read4 << 24) + (read3 << 16) + (read2 << 8) + read;
                    }
                    if (byteOrder == f10293j) {
                        return (read << 56) + (read2 << 48) + (read3 << 40) + (read4 << 32) + (read5 << 24) + (read6 << 16) + (read7 << 8) + read8;
                    }
                    throw new IOException("Invalid byte order: " + this.f10295f);
                }
                throw new EOFException();
            }
            throw new EOFException();
        }

        @Override // java.io.DataInput
        public short readShort() {
            int i8 = this.f10297h + 2;
            this.f10297h = i8;
            if (i8 <= this.f10296g) {
                int read = this.f10294e.read();
                int read2 = this.f10294e.read();
                if ((read | read2) >= 0) {
                    ByteOrder byteOrder = this.f10295f;
                    if (byteOrder == f10292i) {
                        return (short) ((read2 << 8) + read);
                    }
                    if (byteOrder == f10293j) {
                        return (short) ((read << 8) + read2);
                    }
                    throw new IOException("Invalid byte order: " + this.f10295f);
                }
                throw new EOFException();
            }
            throw new EOFException();
        }

        @Override // java.io.DataInput
        public String readUTF() {
            this.f10297h += 2;
            return this.f10294e.readUTF();
        }

        @Override // java.io.DataInput
        public int readUnsignedByte() {
            this.f10297h++;
            return this.f10294e.readUnsignedByte();
        }

        @Override // java.io.DataInput
        public int readUnsignedShort() {
            int i8 = this.f10297h + 2;
            this.f10297h = i8;
            if (i8 <= this.f10296g) {
                int read = this.f10294e.read();
                int read2 = this.f10294e.read();
                if ((read | read2) >= 0) {
                    ByteOrder byteOrder = this.f10295f;
                    if (byteOrder == f10292i) {
                        return (read2 << 8) + read;
                    }
                    if (byteOrder == f10293j) {
                        return (read << 8) + read2;
                    }
                    throw new IOException("Invalid byte order: " + this.f10295f);
                }
                throw new EOFException();
            }
            throw new EOFException();
        }

        @Override // java.io.DataInput
        public int skipBytes(int i8) {
            int min = Math.min(i8, this.f10296g - this.f10297h);
            int i9 = 0;
            while (i9 < min) {
                i9 += this.f10294e.skipBytes(min - i9);
            }
            this.f10297h += i9;
            return i9;
        }

        b(InputStream inputStream, ByteOrder byteOrder) {
            this.f10295f = ByteOrder.BIG_ENDIAN;
            DataInputStream dataInputStream = new DataInputStream(inputStream);
            this.f10294e = dataInputStream;
            int available = dataInputStream.available();
            this.f10296g = available;
            this.f10297h = 0;
            this.f10294e.mark(available);
            this.f10295f = byteOrder;
        }

        @Override // java.io.InputStream
        public int read(byte[] bArr, int i8, int i9) {
            int read = this.f10294e.read(bArr, i8, i9);
            this.f10297h += read;
            return read;
        }

        @Override // java.io.DataInput
        public void readFully(byte[] bArr, int i8, int i9) {
            int i10 = this.f10297h + i9;
            this.f10297h = i10;
            if (i10 > this.f10296g) {
                throw new EOFException();
            }
            if (this.f10294e.read(bArr, i8, i9) != i9) {
                throw new IOException("Couldn't read up to the length of buffer");
            }
        }

        public b(byte[] bArr) {
            this(new ByteArrayInputStream(bArr));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class c extends FilterOutputStream {

        /* renamed from: e  reason: collision with root package name */
        final OutputStream f10298e;

        /* renamed from: f  reason: collision with root package name */
        private ByteOrder f10299f;

        public c(OutputStream outputStream, ByteOrder byteOrder) {
            super(outputStream);
            this.f10298e = outputStream;
            this.f10299f = byteOrder;
        }

        public void b(ByteOrder byteOrder) {
            this.f10299f = byteOrder;
        }

        public void c(int i8) {
            this.f10298e.write(i8);
        }

        public void d(int i8) {
            OutputStream outputStream;
            int i9;
            ByteOrder byteOrder = this.f10299f;
            if (byteOrder == ByteOrder.LITTLE_ENDIAN) {
                this.f10298e.write((i8 >>> 0) & 255);
                this.f10298e.write((i8 >>> 8) & 255);
                this.f10298e.write((i8 >>> 16) & 255);
                outputStream = this.f10298e;
                i9 = i8 >>> 24;
            } else if (byteOrder != ByteOrder.BIG_ENDIAN) {
                return;
            } else {
                this.f10298e.write((i8 >>> 24) & 255);
                this.f10298e.write((i8 >>> 16) & 255);
                this.f10298e.write((i8 >>> 8) & 255);
                outputStream = this.f10298e;
                i9 = i8 >>> 0;
            }
            outputStream.write(i9 & 255);
        }

        public void f(short s8) {
            OutputStream outputStream;
            int i8;
            ByteOrder byteOrder = this.f10299f;
            if (byteOrder == ByteOrder.LITTLE_ENDIAN) {
                this.f10298e.write((s8 >>> 0) & 255);
                outputStream = this.f10298e;
                i8 = s8 >>> 8;
            } else if (byteOrder != ByteOrder.BIG_ENDIAN) {
                return;
            } else {
                this.f10298e.write((s8 >>> 8) & 255);
                outputStream = this.f10298e;
                i8 = s8 >>> 0;
            }
            outputStream.write(i8 & 255);
        }

        public void g(long j8) {
            d((int) j8);
        }

        public void j(int i8) {
            f((short) i8);
        }

        @Override // java.io.FilterOutputStream, java.io.OutputStream
        public void write(byte[] bArr) {
            this.f10298e.write(bArr);
        }

        @Override // java.io.FilterOutputStream, java.io.OutputStream
        public void write(byte[] bArr, int i8, int i9) {
            this.f10298e.write(bArr, i8, i9);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class d {

        /* renamed from: a  reason: collision with root package name */
        public final int f10300a;

        /* renamed from: b  reason: collision with root package name */
        public final int f10301b;

        /* renamed from: c  reason: collision with root package name */
        public final long f10302c;

        /* renamed from: d  reason: collision with root package name */
        public final byte[] f10303d;

        d(int i8, int i9, long j8, byte[] bArr) {
            this.f10300a = i8;
            this.f10301b = i9;
            this.f10302c = j8;
            this.f10303d = bArr;
        }

        public static d a(String str) {
            if (str.length() != 1 || str.charAt(0) < '0' || str.charAt(0) > '1') {
                byte[] bytes = str.getBytes(a.f10256r0);
                return new d(1, bytes.length, bytes);
            }
            return new d(1, 1, new byte[]{(byte) (str.charAt(0) - '0')});
        }

        public static d b(double[] dArr, ByteOrder byteOrder) {
            ByteBuffer wrap = ByteBuffer.wrap(new byte[a.X[12] * dArr.length]);
            wrap.order(byteOrder);
            for (double d8 : dArr) {
                wrap.putDouble(d8);
            }
            return new d(12, dArr.length, wrap.array());
        }

        public static d c(int[] iArr, ByteOrder byteOrder) {
            ByteBuffer wrap = ByteBuffer.wrap(new byte[a.X[9] * iArr.length]);
            wrap.order(byteOrder);
            for (int i8 : iArr) {
                wrap.putInt(i8);
            }
            return new d(9, iArr.length, wrap.array());
        }

        public static d d(f[] fVarArr, ByteOrder byteOrder) {
            ByteBuffer wrap = ByteBuffer.wrap(new byte[a.X[10] * fVarArr.length]);
            wrap.order(byteOrder);
            for (f fVar : fVarArr) {
                wrap.putInt((int) fVar.f10308a);
                wrap.putInt((int) fVar.f10309b);
            }
            return new d(10, fVarArr.length, wrap.array());
        }

        public static d e(String str) {
            byte[] bytes = (str + (char) 0).getBytes(a.f10256r0);
            return new d(2, bytes.length, bytes);
        }

        public static d f(long j8, ByteOrder byteOrder) {
            return g(new long[]{j8}, byteOrder);
        }

        public static d g(long[] jArr, ByteOrder byteOrder) {
            ByteBuffer wrap = ByteBuffer.wrap(new byte[a.X[4] * jArr.length]);
            wrap.order(byteOrder);
            for (long j8 : jArr) {
                wrap.putInt((int) j8);
            }
            return new d(4, jArr.length, wrap.array());
        }

        public static d h(f fVar, ByteOrder byteOrder) {
            return i(new f[]{fVar}, byteOrder);
        }

        public static d i(f[] fVarArr, ByteOrder byteOrder) {
            ByteBuffer wrap = ByteBuffer.wrap(new byte[a.X[5] * fVarArr.length]);
            wrap.order(byteOrder);
            for (f fVar : fVarArr) {
                wrap.putInt((int) fVar.f10308a);
                wrap.putInt((int) fVar.f10309b);
            }
            return new d(5, fVarArr.length, wrap.array());
        }

        public static d j(int i8, ByteOrder byteOrder) {
            return k(new int[]{i8}, byteOrder);
        }

        public static d k(int[] iArr, ByteOrder byteOrder) {
            ByteBuffer wrap = ByteBuffer.wrap(new byte[a.X[3] * iArr.length]);
            wrap.order(byteOrder);
            for (int i8 : iArr) {
                wrap.putShort((short) i8);
            }
            return new d(3, iArr.length, wrap.array());
        }

        public double l(ByteOrder byteOrder) {
            Object o8 = o(byteOrder);
            if (o8 != null) {
                if (o8 instanceof String) {
                    return Double.parseDouble((String) o8);
                }
                if (o8 instanceof long[]) {
                    long[] jArr = (long[]) o8;
                    if (jArr.length == 1) {
                        return jArr[0];
                    }
                    throw new NumberFormatException("There are more than one component");
                } else if (o8 instanceof int[]) {
                    int[] iArr = (int[]) o8;
                    if (iArr.length == 1) {
                        return iArr[0];
                    }
                    throw new NumberFormatException("There are more than one component");
                } else if (o8 instanceof double[]) {
                    double[] dArr = (double[]) o8;
                    if (dArr.length == 1) {
                        return dArr[0];
                    }
                    throw new NumberFormatException("There are more than one component");
                } else if (o8 instanceof f[]) {
                    f[] fVarArr = (f[]) o8;
                    if (fVarArr.length == 1) {
                        return fVarArr[0].a();
                    }
                    throw new NumberFormatException("There are more than one component");
                } else {
                    throw new NumberFormatException("Couldn't find a double value");
                }
            }
            throw new NumberFormatException("NULL can't be converted to a double value");
        }

        public int m(ByteOrder byteOrder) {
            Object o8 = o(byteOrder);
            if (o8 != null) {
                if (o8 instanceof String) {
                    return Integer.parseInt((String) o8);
                }
                if (o8 instanceof long[]) {
                    long[] jArr = (long[]) o8;
                    if (jArr.length == 1) {
                        return (int) jArr[0];
                    }
                    throw new NumberFormatException("There are more than one component");
                } else if (o8 instanceof int[]) {
                    int[] iArr = (int[]) o8;
                    if (iArr.length == 1) {
                        return iArr[0];
                    }
                    throw new NumberFormatException("There are more than one component");
                } else {
                    throw new NumberFormatException("Couldn't find a integer value");
                }
            }
            throw new NumberFormatException("NULL can't be converted to a integer value");
        }

        public String n(ByteOrder byteOrder) {
            Object o8 = o(byteOrder);
            if (o8 == null) {
                return null;
            }
            if (o8 instanceof String) {
                return (String) o8;
            }
            StringBuilder sb = new StringBuilder();
            int i8 = 0;
            if (o8 instanceof long[]) {
                long[] jArr = (long[]) o8;
                while (i8 < jArr.length) {
                    sb.append(jArr[i8]);
                    i8++;
                    if (i8 != jArr.length) {
                        sb.append(",");
                    }
                }
                return sb.toString();
            } else if (o8 instanceof int[]) {
                int[] iArr = (int[]) o8;
                while (i8 < iArr.length) {
                    sb.append(iArr[i8]);
                    i8++;
                    if (i8 != iArr.length) {
                        sb.append(",");
                    }
                }
                return sb.toString();
            } else if (o8 instanceof double[]) {
                double[] dArr = (double[]) o8;
                while (i8 < dArr.length) {
                    sb.append(dArr[i8]);
                    i8++;
                    if (i8 != dArr.length) {
                        sb.append(",");
                    }
                }
                return sb.toString();
            } else if (o8 instanceof f[]) {
                f[] fVarArr = (f[]) o8;
                while (i8 < fVarArr.length) {
                    sb.append(fVarArr[i8].f10308a);
                    sb.append('/');
                    sb.append(fVarArr[i8].f10309b);
                    i8++;
                    if (i8 != fVarArr.length) {
                        sb.append(",");
                    }
                }
                return sb.toString();
            } else {
                return null;
            }
        }

        /* JADX WARN: Not initialized variable reg: 3, insn: 0x0198: MOVE  (r2 I:??[OBJECT, ARRAY]) = (r3 I:??[OBJECT, ARRAY]), block:B:151:0x0198 */
        /* JADX WARN: Removed duplicated region for block: B:182:0x019b A[EXC_TOP_SPLITTER, SYNTHETIC] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        java.lang.Object o(java.nio.ByteOrder r11) {
            /*
                Method dump skipped, instructions count: 448
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: q1.a.d.o(java.nio.ByteOrder):java.lang.Object");
        }

        public int p() {
            return a.X[this.f10300a] * this.f10301b;
        }

        public String toString() {
            return "(" + a.W[this.f10300a] + ", data length:" + this.f10303d.length + ")";
        }

        d(int i8, int i9, byte[] bArr) {
            this(i8, i9, -1L, bArr);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static class e {

        /* renamed from: a  reason: collision with root package name */
        public final int f10304a;

        /* renamed from: b  reason: collision with root package name */
        public final String f10305b;

        /* renamed from: c  reason: collision with root package name */
        public final int f10306c;

        /* renamed from: d  reason: collision with root package name */
        public final int f10307d;

        e(String str, int i8, int i9) {
            this.f10305b = str;
            this.f10304a = i8;
            this.f10306c = i9;
            this.f10307d = -1;
        }

        boolean a(int i8) {
            int i9;
            int i10 = this.f10306c;
            if (i10 == 7 || i8 == 7 || i10 == i8 || (i9 = this.f10307d) == i8) {
                return true;
            }
            if ((i10 == 4 || i9 == 4) && i8 == 3) {
                return true;
            }
            if ((i10 == 9 || i9 == 9) && i8 == 8) {
                return true;
            }
            return (i10 == 12 || i9 == 12) && i8 == 11;
        }

        e(String str, int i8, int i9, int i10) {
            this.f10305b = str;
            this.f10304a = i8;
            this.f10306c = i9;
            this.f10307d = i10;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class f {

        /* renamed from: a  reason: collision with root package name */
        public final long f10308a;

        /* renamed from: b  reason: collision with root package name */
        public final long f10309b;

        f(double d8) {
            this((long) (d8 * 10000.0d), 10000L);
        }

        public double a() {
            return this.f10308a / this.f10309b;
        }

        public String toString() {
            return this.f10308a + "/" + this.f10309b;
        }

        f(long j8, long j9) {
            if (j9 == 0) {
                this.f10308a = 0L;
                this.f10309b = 1L;
                return;
            }
            this.f10308a = j8;
            this.f10309b = j9;
        }
    }

    static {
        e[] eVarArr;
        e[] eVarArr2 = {new e("NewSubfileType", 254, 4), new e("SubfileType", 255, 4), new e("ImageWidth", 256, 3, 4), new e("ImageLength", 257, 3, 4), new e("BitsPerSample", 258, 3), new e("Compression", 259, 3), new e("PhotometricInterpretation", 262, 3), new e("ImageDescription", 270, 2), new e("Make", 271, 2), new e("Model", 272, 2), new e("StripOffsets", 273, 3, 4), new e("Orientation", 274, 3), new e("SamplesPerPixel", 277, 3), new e("RowsPerStrip", 278, 3, 4), new e("StripByteCounts", 279, 3, 4), new e("XResolution", 282, 5), new e("YResolution", 283, 5), new e("PlanarConfiguration", 284, 3), new e("ResolutionUnit", 296, 3), new e("TransferFunction", 301, 3), new e("Software", 305, 2), new e("DateTime", 306, 2), new e("Artist", 315, 2), new e("WhitePoint", 318, 5), new e("PrimaryChromaticities", 319, 5), new e("SubIFDPointer", 330, 4), new e("JPEGInterchangeFormat", 513, 4), new e("JPEGInterchangeFormatLength", 514, 4), new e("YCbCrCoefficients", 529, 5), new e("YCbCrSubSampling", 530, 3), new e("YCbCrPositioning", 531, 3), new e("ReferenceBlackWhite", 532, 5), new e("Copyright", 33432, 2), new e("ExifIFDPointer", 34665, 4), new e("GPSInfoIFDPointer", 34853, 4), new e("SensorTopBorder", 4, 4), new e("SensorLeftBorder", 5, 4), new e("SensorBottomBorder", 6, 4), new e("SensorRightBorder", 7, 4), new e("ISO", 23, 3), new e("JpgFromRaw", 46, 7), new e("Xmp", 700, 1)};
        Z = eVarArr2;
        e[] eVarArr3 = {new e("ExposureTime", 33434, 5), new e("FNumber", 33437, 5), new e("ExposureProgram", 34850, 3), new e("SpectralSensitivity", 34852, 2), new e("PhotographicSensitivity", 34855, 3), new e("OECF", 34856, 7), new e("SensitivityType", 34864, 3), new e("StandardOutputSensitivity", 34865, 4), new e("RecommendedExposureIndex", 34866, 4), new e("ISOSpeed", 34867, 4), new e("ISOSpeedLatitudeyyy", 34868, 4), new e("ISOSpeedLatitudezzz", 34869, 4), new e("ExifVersion", 36864, 2), new e("DateTimeOriginal", 36867, 2), new e("DateTimeDigitized", 36868, 2), new e("OffsetTime", 36880, 2), new e("OffsetTimeOriginal", 36881, 2), new e("OffsetTimeDigitized", 36882, 2), new e("ComponentsConfiguration", 37121, 7), new e("CompressedBitsPerPixel", 37122, 5), new e("ShutterSpeedValue", 37377, 10), new e("ApertureValue", 37378, 5), new e("BrightnessValue", 37379, 10), new e("ExposureBiasValue", 37380, 10), new e("MaxApertureValue", 37381, 5), new e("SubjectDistance", 37382, 5), new e("MeteringMode", 37383, 3), new e("LightSource", 37384, 3), new e("Flash", 37385, 3), new e("FocalLength", 37386, 5), new e("SubjectArea", 37396, 3), new e("MakerNote", 37500, 7), new e("UserComment", 37510, 7), new e("SubSecTime", 37520, 2), new e("SubSecTimeOriginal", 37521, 2), new e("SubSecTimeDigitized", 37522, 2), new e("FlashpixVersion", 40960, 7), new e("ColorSpace", 40961, 3), new e("PixelXDimension", 40962, 3, 4), new e("PixelYDimension", 40963, 3, 4), new e("RelatedSoundFile", 40964, 2), new e("InteroperabilityIFDPointer", 40965, 4), new e("FlashEnergy", 41483, 5), new e("SpatialFrequencyResponse", 41484, 7), new e("FocalPlaneXResolution", 41486, 5), new e("FocalPlaneYResolution", 41487, 5), new e("FocalPlaneResolutionUnit", 41488, 3), new e("SubjectLocation", 41492, 3), new e("ExposureIndex", 41493, 5), new e("SensingMethod", 41495, 3), new e("FileSource", 41728, 7), new e("SceneType", 41729, 7), new e("CFAPattern", 41730, 7), new e("CustomRendered", 41985, 3), new e("ExposureMode", 41986, 3), new e("WhiteBalance", 41987, 3), new e("DigitalZoomRatio", 41988, 5), new e("FocalLengthIn35mmFilm", 41989, 3), new e("SceneCaptureType", 41990, 3), new e("GainControl", 41991, 3), new e("Contrast", 41992, 3), new e("Saturation", 41993, 3), new e("Sharpness", 41994, 3), new e("DeviceSettingDescription", 41995, 7), new e("SubjectDistanceRange", 41996, 3), new e("ImageUniqueID", 42016, 2), new e("CameraOwnerName", 42032, 2), new e("BodySerialNumber", 42033, 2), new e("LensSpecification", 42034, 5), new e("LensMake", 42035, 2), new e("LensModel", 42036, 2), new e("Gamma", 42240, 5), new e("DNGVersion", 50706, 1), new e("DefaultCropSize", 50720, 3, 4)};
        f10239a0 = eVarArr3;
        e[] eVarArr4 = {new e("GPSVersionID", 0, 1), new e("GPSLatitudeRef", 1, 2), new e("GPSLatitude", 2, 5, 10), new e("GPSLongitudeRef", 3, 2), new e("GPSLongitude", 4, 5, 10), new e("GPSAltitudeRef", 5, 1), new e("GPSAltitude", 6, 5), new e("GPSTimeStamp", 7, 5), new e("GPSSatellites", 8, 2), new e("GPSStatus", 9, 2), new e("GPSMeasureMode", 10, 2), new e("GPSDOP", 11, 5), new e("GPSSpeedRef", 12, 2), new e("GPSSpeed", 13, 5), new e("GPSTrackRef", 14, 2), new e("GPSTrack", 15, 5), new e("GPSImgDirectionRef", 16, 2), new e("GPSImgDirection", 17, 5), new e("GPSMapDatum", 18, 2), new e("GPSDestLatitudeRef", 19, 2), new e("GPSDestLatitude", 20, 5), new e("GPSDestLongitudeRef", 21, 2), new e("GPSDestLongitude", 22, 5), new e("GPSDestBearingRef", 23, 2), new e("GPSDestBearing", 24, 5), new e("GPSDestDistanceRef", 25, 2), new e("GPSDestDistance", 26, 5), new e("GPSProcessingMethod", 27, 7), new e("GPSAreaInformation", 28, 7), new e("GPSDateStamp", 29, 2), new e("GPSDifferential", 30, 3), new e("GPSHPositioningError", 31, 5)};
        f10240b0 = eVarArr4;
        e[] eVarArr5 = {new e("InteroperabilityIndex", 1, 2)};
        f10241c0 = eVarArr5;
        e[] eVarArr6 = {new e("NewSubfileType", 254, 4), new e("SubfileType", 255, 4), new e("ThumbnailImageWidth", 256, 3, 4), new e("ThumbnailImageLength", 257, 3, 4), new e("BitsPerSample", 258, 3), new e("Compression", 259, 3), new e("PhotometricInterpretation", 262, 3), new e("ImageDescription", 270, 2), new e("Make", 271, 2), new e("Model", 272, 2), new e("StripOffsets", 273, 3, 4), new e("ThumbnailOrientation", 274, 3), new e("SamplesPerPixel", 277, 3), new e("RowsPerStrip", 278, 3, 4), new e("StripByteCounts", 279, 3, 4), new e("XResolution", 282, 5), new e("YResolution", 283, 5), new e("PlanarConfiguration", 284, 3), new e("ResolutionUnit", 296, 3), new e("TransferFunction", 301, 3), new e("Software", 305, 2), new e("DateTime", 306, 2), new e("Artist", 315, 2), new e("WhitePoint", 318, 5), new e("PrimaryChromaticities", 319, 5), new e("SubIFDPointer", 330, 4), new e("JPEGInterchangeFormat", 513, 4), new e("JPEGInterchangeFormatLength", 514, 4), new e("YCbCrCoefficients", 529, 5), new e("YCbCrSubSampling", 530, 3), new e("YCbCrPositioning", 531, 3), new e("ReferenceBlackWhite", 532, 5), new e("Copyright", 33432, 2), new e("ExifIFDPointer", 34665, 4), new e("GPSInfoIFDPointer", 34853, 4), new e("DNGVersion", 50706, 1), new e("DefaultCropSize", 50720, 3, 4)};
        f10242d0 = eVarArr6;
        f10243e0 = new e("StripOffsets", 273, 3);
        e[] eVarArr7 = {new e("ThumbnailImage", 256, 7), new e("CameraSettingsIFDPointer", 8224, 4), new e("ImageProcessingIFDPointer", 8256, 4)};
        f10244f0 = eVarArr7;
        e[] eVarArr8 = {new e("PreviewImageStart", 257, 4), new e("PreviewImageLength", 258, 4)};
        f10245g0 = eVarArr8;
        e[] eVarArr9 = {new e("AspectFrame", 4371, 3)};
        f10246h0 = eVarArr9;
        e[] eVarArr10 = {new e("ColorSpace", 55, 3)};
        f10247i0 = eVarArr10;
        e[][] eVarArr11 = {eVarArr2, eVarArr3, eVarArr4, eVarArr5, eVarArr6, eVarArr2, eVarArr7, eVarArr8, eVarArr9, eVarArr10};
        f10248j0 = eVarArr11;
        f10249k0 = new e[]{new e("SubIFDPointer", 330, 4), new e("ExifIFDPointer", 34665, 4), new e("GPSInfoIFDPointer", 34853, 4), new e("InteroperabilityIFDPointer", 40965, 4), new e("CameraSettingsIFDPointer", 8224, 1), new e("ImageProcessingIFDPointer", 8256, 1)};
        f10250l0 = new e("JPEGInterchangeFormat", 513, 4);
        f10251m0 = new e("JPEGInterchangeFormatLength", 514, 4);
        f10252n0 = new HashMap[eVarArr11.length];
        f10253o0 = new HashMap[eVarArr11.length];
        f10254p0 = new HashSet(Arrays.asList("FNumber", "DigitalZoomRatio", "ExposureTime", "SubjectDistance", "GPSTimeStamp"));
        f10255q0 = new HashMap();
        Charset forName = Charset.forName("US-ASCII");
        f10256r0 = forName;
        f10257s0 = "Exif\u0000\u0000".getBytes(forName);
        f10258t0 = "http://ns.adobe.com/xap/1.0/\u0000".getBytes(forName);
        Locale locale = Locale.US;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy:MM:dd HH:mm:ss", locale);
        U = simpleDateFormat;
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", locale);
        V = simpleDateFormat2;
        simpleDateFormat2.setTimeZone(TimeZone.getTimeZone("UTC"));
        int i8 = 0;
        while (true) {
            e[][] eVarArr12 = f10248j0;
            if (i8 >= eVarArr12.length) {
                HashMap hashMap = f10255q0;
                e[] eVarArr13 = f10249k0;
                hashMap.put(Integer.valueOf(eVarArr13[0].f10304a), 5);
                hashMap.put(Integer.valueOf(eVarArr13[1].f10304a), 1);
                hashMap.put(Integer.valueOf(eVarArr13[2].f10304a), 2);
                hashMap.put(Integer.valueOf(eVarArr13[3].f10304a), 3);
                hashMap.put(Integer.valueOf(eVarArr13[4].f10304a), 7);
                hashMap.put(Integer.valueOf(eVarArr13[5].f10304a), 8);
                f10259u0 = Pattern.compile(".*[1-9].*");
                f10261v0 = Pattern.compile("^(\\d{2}):(\\d{2}):(\\d{2})$");
                f10263w0 = Pattern.compile("^(\\d{4}):(\\d{2}):(\\d{2})\\s(\\d{2}):(\\d{2}):(\\d{2})$");
                f10265x0 = Pattern.compile("^(\\d{4})-(\\d{2})-(\\d{2})\\s(\\d{2}):(\\d{2}):(\\d{2})$");
                return;
            }
            f10252n0[i8] = new HashMap();
            f10253o0[i8] = new HashMap();
            for (e eVar : eVarArr12[i8]) {
                f10252n0[i8].put(Integer.valueOf(eVar.f10304a), eVar);
                f10253o0[i8].put(eVar.f10305b, eVar);
            }
            i8++;
        }
    }

    public a(InputStream inputStream) {
        this(inputStream, 0);
    }

    private void C(b bVar) {
        if (f10260v) {
            Log.d("ExifInterface", "getWebpAttributes starting with: " + bVar);
        }
        bVar.mark(0);
        bVar.g(ByteOrder.LITTLE_ENDIAN);
        bVar.skipBytes(L.length);
        int readInt = bVar.readInt() + 8;
        int skipBytes = bVar.skipBytes(M.length) + 8;
        while (true) {
            try {
                byte[] bArr = new byte[4];
                if (bVar.read(bArr) != 4) {
                    throw new IOException("Encountered invalid length while parsing WebP chunktype");
                }
                int readInt2 = bVar.readInt();
                int i8 = skipBytes + 4 + 4;
                if (Arrays.equals(N, bArr)) {
                    byte[] bArr2 = new byte[readInt2];
                    if (bVar.read(bArr2) == readInt2) {
                        this.f10283p = i8;
                        X(bArr2, 0);
                        j0(new b(bArr2));
                        return;
                    }
                    throw new IOException("Failed to read given length for given PNG chunk type: " + b(bArr));
                }
                if (readInt2 % 2 == 1) {
                    readInt2++;
                }
                int i9 = i8 + readInt2;
                if (i9 == readInt) {
                    return;
                }
                if (i9 > readInt) {
                    throw new IOException("Encountered WebP file with invalid chunk size");
                }
                int skipBytes2 = bVar.skipBytes(readInt2);
                if (skipBytes2 != readInt2) {
                    throw new IOException("Encountered WebP file with invalid chunk size");
                }
                skipBytes = i8 + skipBytes2;
            } catch (EOFException unused) {
                throw new IOException("Encountered corrupt WebP file.");
            }
        }
    }

    private static Pair D(String str) {
        if (str.contains(",")) {
            String[] split = str.split(",", -1);
            Pair D2 = D(split[0]);
            if (((Integer) D2.first).intValue() == 2) {
                return D2;
            }
            for (int i8 = 1; i8 < split.length; i8++) {
                Pair D3 = D(split[i8]);
                int intValue = (((Integer) D3.first).equals(D2.first) || ((Integer) D3.second).equals(D2.first)) ? ((Integer) D2.first).intValue() : -1;
                int intValue2 = (((Integer) D2.second).intValue() == -1 || !(((Integer) D3.first).equals(D2.second) || ((Integer) D3.second).equals(D2.second))) ? -1 : ((Integer) D2.second).intValue();
                if (intValue == -1 && intValue2 == -1) {
                    return new Pair(2, -1);
                }
                if (intValue == -1) {
                    D2 = new Pair(Integer.valueOf(intValue2), -1);
                } else if (intValue2 == -1) {
                    D2 = new Pair(Integer.valueOf(intValue), -1);
                }
            }
            return D2;
        } else if (!str.contains("/")) {
            try {
                try {
                    Long valueOf = Long.valueOf(Long.parseLong(str));
                    return (valueOf.longValue() < 0 || valueOf.longValue() > 65535) ? valueOf.longValue() < 0 ? new Pair(9, -1) : new Pair(4, -1) : new Pair(3, 4);
                } catch (NumberFormatException unused) {
                    return new Pair(2, -1);
                }
            } catch (NumberFormatException unused2) {
                Double.parseDouble(str);
                return new Pair(12, -1);
            }
        } else {
            String[] split2 = str.split("/", -1);
            if (split2.length == 2) {
                try {
                    long parseDouble = (long) Double.parseDouble(split2[0]);
                    long parseDouble2 = (long) Double.parseDouble(split2[1]);
                    if (parseDouble >= 0 && parseDouble2 >= 0) {
                        if (parseDouble <= 2147483647L && parseDouble2 <= 2147483647L) {
                            return new Pair(10, 5);
                        }
                        return new Pair(5, -1);
                    }
                    return new Pair(10, -1);
                } catch (NumberFormatException unused3) {
                }
            }
            return new Pair(2, -1);
        }
    }

    private void E(b bVar, HashMap hashMap) {
        d dVar = (d) hashMap.get("JPEGInterchangeFormat");
        d dVar2 = (d) hashMap.get("JPEGInterchangeFormatLength");
        if (dVar == null || dVar2 == null) {
            return;
        }
        int m8 = dVar.m(this.f10275h);
        int m9 = dVar2.m(this.f10275h);
        if (this.f10271d == 7) {
            m8 += this.f10284q;
        }
        int min = Math.min(m9, bVar.b() - m8);
        if (m8 > 0 && min > 0) {
            this.f10276i = true;
            if (this.f10268a == null && this.f10270c == null && this.f10269b == null) {
                byte[] bArr = new byte[min];
                bVar.skip(m8);
                bVar.read(bArr);
                this.f10281n = bArr;
            }
            this.f10279l = m8;
            this.f10280m = min;
        }
        if (f10260v) {
            Log.d("ExifInterface", "Setting thumbnail attributes with offset: " + m8 + ", length: " + min);
        }
    }

    private void F(b bVar, HashMap hashMap) {
        d dVar = (d) hashMap.get("StripOffsets");
        d dVar2 = (d) hashMap.get("StripByteCounts");
        if (dVar == null || dVar2 == null) {
            return;
        }
        long[] g8 = g(dVar.o(this.f10275h));
        long[] g9 = g(dVar2.o(this.f10275h));
        if (g8 == null || g8.length == 0) {
            Log.w("ExifInterface", "stripOffsets should not be null or have zero length.");
        } else if (g9 == null || g9.length == 0) {
            Log.w("ExifInterface", "stripByteCounts should not be null or have zero length.");
        } else if (g8.length != g9.length) {
            Log.w("ExifInterface", "stripOffsets and stripByteCounts should have same length.");
        } else {
            long j8 = 0;
            for (long j9 : g9) {
                j8 += j9;
            }
            int i8 = (int) j8;
            byte[] bArr = new byte[i8];
            this.f10278k = true;
            this.f10277j = true;
            this.f10276i = true;
            int i9 = 0;
            int i10 = 0;
            for (int i11 = 0; i11 < g8.length; i11++) {
                int i12 = (int) g8[i11];
                int i13 = (int) g9[i11];
                if (i11 < g8.length - 1 && i12 + i13 != g8[i11 + 1]) {
                    this.f10278k = false;
                }
                int i14 = i12 - i9;
                if (i14 < 0) {
                    Log.d("ExifInterface", "Invalid strip offset value");
                    return;
                }
                long j10 = i14;
                if (bVar.skip(j10) != j10) {
                    Log.d("ExifInterface", "Failed to skip " + i14 + " bytes.");
                    return;
                }
                int i15 = i9 + i14;
                byte[] bArr2 = new byte[i13];
                if (bVar.read(bArr2) != i13) {
                    Log.d("ExifInterface", "Failed to read " + i13 + " bytes.");
                    return;
                }
                i9 = i15 + i13;
                System.arraycopy(bArr2, 0, bArr, i10, i13);
                i10 += i13;
            }
            this.f10281n = bArr;
            if (this.f10278k) {
                this.f10279l = (int) g8[0];
                this.f10280m = i8;
            }
        }
    }

    private void G(String str) {
        if (str == null) {
            throw new NullPointerException("filename cannot be null");
        }
        FileInputStream fileInputStream = null;
        this.f10270c = null;
        this.f10268a = str;
        try {
            FileInputStream fileInputStream2 = new FileInputStream(str);
            try {
                if (O(fileInputStream2.getFD())) {
                    this.f10269b = fileInputStream2.getFD();
                } else {
                    this.f10269b = null;
                }
                T(fileInputStream2);
                d(fileInputStream2);
            } catch (Throwable th) {
                th = th;
                fileInputStream = fileInputStream2;
                d(fileInputStream);
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }

    private static boolean H(BufferedInputStream bufferedInputStream) {
        byte[] bArr = f10257s0;
        bufferedInputStream.mark(bArr.length);
        byte[] bArr2 = new byte[bArr.length];
        bufferedInputStream.read(bArr2);
        bufferedInputStream.reset();
        int i8 = 0;
        while (true) {
            byte[] bArr3 = f10257s0;
            if (i8 >= bArr3.length) {
                return true;
            }
            if (bArr2[i8] != bArr3[i8]) {
                return false;
            }
            i8++;
        }
    }

    private boolean I(byte[] bArr) {
        b bVar;
        long readInt;
        byte[] bArr2;
        long j8;
        b bVar2 = null;
        try {
            try {
                bVar = new b(bArr);
            } catch (Exception e8) {
                e = e8;
            }
        } catch (Throwable th) {
            th = th;
        }
        try {
            readInt = bVar.readInt();
            bArr2 = new byte[4];
            bVar.read(bArr2);
        } catch (Exception e9) {
            e = e9;
            bVar2 = bVar;
            if (f10260v) {
                Log.d("ExifInterface", "Exception parsing HEIF file type box.", e);
            }
            if (bVar2 != null) {
                bVar2.close();
            }
            return false;
        } catch (Throwable th2) {
            th = th2;
            bVar2 = bVar;
            if (bVar2 != null) {
                bVar2.close();
            }
            throw th;
        }
        if (!Arrays.equals(bArr2, C)) {
            bVar.close();
            return false;
        }
        if (readInt == 1) {
            readInt = bVar.readLong();
            j8 = 16;
            if (readInt < 16) {
                bVar.close();
                return false;
            }
        } else {
            j8 = 8;
        }
        if (readInt > bArr.length) {
            readInt = bArr.length;
        }
        long j9 = readInt - j8;
        if (j9 < 8) {
            bVar.close();
            return false;
        }
        byte[] bArr3 = new byte[4];
        boolean z7 = false;
        boolean z8 = false;
        for (long j10 = 0; j10 < j9 / 4; j10++) {
            if (bVar.read(bArr3) != 4) {
                bVar.close();
                return false;
            }
            if (j10 != 1) {
                if (Arrays.equals(bArr3, D)) {
                    z7 = true;
                } else if (Arrays.equals(bArr3, E)) {
                    z8 = true;
                }
                if (z7 && z8) {
                    bVar.close();
                    return true;
                }
            }
        }
        bVar.close();
        return false;
    }

    private static boolean J(byte[] bArr) {
        int i8 = 0;
        while (true) {
            byte[] bArr2 = B;
            if (i8 >= bArr2.length) {
                return true;
            }
            if (bArr[i8] != bArr2[i8]) {
                return false;
            }
            i8++;
        }
    }

    private boolean K(byte[] bArr) {
        boolean z7 = false;
        b bVar = null;
        try {
            b bVar2 = new b(bArr);
            try {
                ByteOrder W2 = W(bVar2);
                this.f10275h = W2;
                bVar2.g(W2);
                short readShort = bVar2.readShort();
                z7 = (readShort == 20306 || readShort == 21330) ? true : true;
                bVar2.close();
                return z7;
            } catch (Exception unused) {
                bVar = bVar2;
                if (bVar != null) {
                    bVar.close();
                }
                return false;
            } catch (Throwable th) {
                th = th;
                bVar = bVar2;
                if (bVar != null) {
                    bVar.close();
                }
                throw th;
            }
        } catch (Exception unused2) {
        } catch (Throwable th2) {
            th = th2;
        }
    }

    private boolean L(byte[] bArr) {
        int i8 = 0;
        while (true) {
            byte[] bArr2 = H;
            if (i8 >= bArr2.length) {
                return true;
            }
            if (bArr[i8] != bArr2[i8]) {
                return false;
            }
            i8++;
        }
    }

    private boolean M(byte[] bArr) {
        byte[] bytes = "FUJIFILMCCD-RAW".getBytes(Charset.defaultCharset());
        for (int i8 = 0; i8 < bytes.length; i8++) {
            if (bArr[i8] != bytes[i8]) {
                return false;
            }
        }
        return true;
    }

    private boolean N(byte[] bArr) {
        b bVar = null;
        try {
            b bVar2 = new b(bArr);
            try {
                ByteOrder W2 = W(bVar2);
                this.f10275h = W2;
                bVar2.g(W2);
                boolean z7 = bVar2.readShort() == 85;
                bVar2.close();
                return z7;
            } catch (Exception unused) {
                bVar = bVar2;
                if (bVar != null) {
                    bVar.close();
                }
                return false;
            } catch (Throwable th) {
                th = th;
                bVar = bVar2;
                if (bVar != null) {
                    bVar.close();
                }
                throw th;
            }
        } catch (Exception unused2) {
        } catch (Throwable th2) {
            th = th2;
        }
    }

    private static boolean O(FileDescriptor fileDescriptor) {
        try {
            Os.lseek(fileDescriptor, 0L, OsConstants.SEEK_CUR);
            return true;
        } catch (Exception unused) {
            if (f10260v) {
                Log.d("ExifInterface", "The file descriptor for the given input is not seekable");
                return false;
            }
            return false;
        }
    }

    private boolean P(HashMap hashMap) {
        d dVar;
        int m8;
        d dVar2 = (d) hashMap.get("BitsPerSample");
        if (dVar2 != null) {
            int[] iArr = (int[]) dVar2.o(this.f10275h);
            int[] iArr2 = f10266y;
            if (Arrays.equals(iArr2, iArr)) {
                return true;
            }
            if (this.f10271d == 3 && (dVar = (d) hashMap.get("PhotometricInterpretation")) != null && (((m8 = dVar.m(this.f10275h)) == 1 && Arrays.equals(iArr, A)) || (m8 == 6 && Arrays.equals(iArr, iArr2)))) {
                return true;
            }
        }
        if (f10260v) {
            Log.d("ExifInterface", "Unsupported data type value");
            return false;
        }
        return false;
    }

    private boolean Q() {
        int i8 = this.f10271d;
        return i8 == 4 || i8 == 13 || i8 == 14;
    }

    private boolean R(HashMap hashMap) {
        d dVar = (d) hashMap.get("ImageLength");
        d dVar2 = (d) hashMap.get("ImageWidth");
        if (dVar == null || dVar2 == null) {
            return false;
        }
        return dVar.m(this.f10275h) <= 512 && dVar2.m(this.f10275h) <= 512;
    }

    private boolean S(byte[] bArr) {
        int i8 = 0;
        while (true) {
            byte[] bArr2 = L;
            if (i8 >= bArr2.length) {
                int i9 = 0;
                while (true) {
                    byte[] bArr3 = M;
                    if (i9 >= bArr3.length) {
                        return true;
                    }
                    if (bArr[L.length + i9 + 4] != bArr3[i9]) {
                        return false;
                    }
                    i9++;
                }
            } else if (bArr[i8] != bArr2[i8]) {
                return false;
            } else {
                i8++;
            }
        }
    }

    private void T(InputStream inputStream) {
        if (inputStream == null) {
            throw new NullPointerException("inputstream shouldn't be null");
        }
        for (int i8 = 0; i8 < f10248j0.length; i8++) {
            try {
                try {
                    this.f10273f[i8] = new HashMap();
                } catch (IOException e8) {
                    boolean z7 = f10260v;
                    if (z7) {
                        Log.w("ExifInterface", "Invalid image: ExifInterface got an unsupported image format file(ExifInterface supports JPEG and some RAW image formats only) or a corrupted JPEG file to ExifInterface.", e8);
                    }
                    a();
                    if (!z7) {
                        return;
                    }
                }
            } finally {
                a();
                if (f10260v) {
                    V();
                }
            }
        }
        if (!this.f10272e) {
            BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream, 5000);
            this.f10271d = t(bufferedInputStream);
            inputStream = bufferedInputStream;
        }
        b bVar = new b(inputStream);
        if (this.f10272e) {
            z(bVar);
        } else {
            switch (this.f10271d) {
                case 0:
                case 1:
                case 2:
                case 3:
                case 5:
                case androidx.browser.customtabs.b.TAB_HIDDEN /* 6 */:
                case 8:
                case 11:
                    x(bVar);
                    break;
                case 4:
                    r(bVar, 0, 0);
                    a();
                    if (f10260v) {
                        V();
                        return;
                    }
                    return;
                case 7:
                    u(bVar);
                    break;
                case 9:
                    w(bVar);
                    a();
                    if (f10260v) {
                        V();
                        return;
                    }
                    return;
                case 10:
                    y(bVar);
                    a();
                    if (f10260v) {
                        V();
                        return;
                    }
                    return;
                case 12:
                    q(bVar);
                    break;
                case 13:
                    v(bVar);
                    a();
                    if (f10260v) {
                        V();
                        return;
                    }
                    return;
                case 14:
                    C(bVar);
                    a();
                    if (f10260v) {
                        V();
                        return;
                    }
                    return;
            }
        }
        bVar.f(this.f10283p);
        j0(bVar);
    }

    private void U(b bVar, int i8) {
        ByteOrder W2 = W(bVar);
        this.f10275h = W2;
        bVar.g(W2);
        int readUnsignedShort = bVar.readUnsignedShort();
        int i9 = this.f10271d;
        if (i9 != 7 && i9 != 10 && readUnsignedShort != 42) {
            throw new IOException("Invalid start code: " + Integer.toHexString(readUnsignedShort));
        }
        int readInt = bVar.readInt();
        if (readInt < 8 || readInt >= i8) {
            throw new IOException("Invalid first Ifd offset: " + readInt);
        }
        int i10 = readInt - 8;
        if (i10 <= 0 || bVar.skipBytes(i10) == i10) {
            return;
        }
        throw new IOException("Couldn't jump to first Ifd: " + i10);
    }

    private void V() {
        for (int i8 = 0; i8 < this.f10273f.length; i8++) {
            Log.d("ExifInterface", "The size of tag group[" + i8 + "]: " + this.f10273f[i8].size());
            for (Map.Entry entry : this.f10273f[i8].entrySet()) {
                d dVar = (d) entry.getValue();
                Log.d("ExifInterface", "tagName: " + ((String) entry.getKey()) + ", tagType: " + dVar.toString() + ", tagValue: '" + dVar.n(this.f10275h) + "'");
            }
        }
    }

    private ByteOrder W(b bVar) {
        short readShort = bVar.readShort();
        if (readShort == 18761) {
            if (f10260v) {
                Log.d("ExifInterface", "readExifSegment: Byte Align II");
            }
            return ByteOrder.LITTLE_ENDIAN;
        } else if (readShort == 19789) {
            if (f10260v) {
                Log.d("ExifInterface", "readExifSegment: Byte Align MM");
            }
            return ByteOrder.BIG_ENDIAN;
        } else {
            throw new IOException("Invalid byte order: " + Integer.toHexString(readShort));
        }
    }

    private void X(byte[] bArr, int i8) {
        b bVar = new b(bArr);
        U(bVar, bArr.length);
        Y(bVar, i8);
    }

    /* JADX WARN: Code restructure failed: missing block: B:126:0x0355, code lost:
        if (r5.m(r29.f10275h) == 65535) goto L97;
     */
    /* JADX WARN: Removed duplicated region for block: B:101:0x027b  */
    /* JADX WARN: Removed duplicated region for block: B:112:0x02d7  */
    /* JADX WARN: Removed duplicated region for block: B:158:0x0364 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:54:0x014e  */
    /* JADX WARN: Removed duplicated region for block: B:55:0x0159  */
    /* JADX WARN: Removed duplicated region for block: B:74:0x01f4  */
    /* JADX WARN: Removed duplicated region for block: B:75:0x01fa  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void Y(q1.a.b r30, int r31) {
        /*
            Method dump skipped, instructions count: 1003
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: q1.a.Y(q1.a$b, int):void");
    }

    private void Z(String str) {
        for (int i8 = 0; i8 < f10248j0.length; i8++) {
            this.f10273f[i8].remove(str);
        }
    }

    private void a() {
        String m8 = m("DateTimeOriginal");
        if (m8 != null && m("DateTime") == null) {
            this.f10273f[0].put("DateTime", d.e(m8));
        }
        if (m("ImageWidth") == null) {
            this.f10273f[0].put("ImageWidth", d.f(0L, this.f10275h));
        }
        if (m("ImageLength") == null) {
            this.f10273f[0].put("ImageLength", d.f(0L, this.f10275h));
        }
        if (m("Orientation") == null) {
            this.f10273f[0].put("Orientation", d.f(0L, this.f10275h));
        }
        if (m("LightSource") == null) {
            this.f10273f[1].put("LightSource", d.f(0L, this.f10275h));
        }
    }

    private void a0(b bVar, int i8) {
        d dVar = (d) this.f10273f[i8].get("ImageLength");
        d dVar2 = (d) this.f10273f[i8].get("ImageWidth");
        if (dVar == null || dVar2 == null) {
            d dVar3 = (d) this.f10273f[i8].get("JPEGInterchangeFormat");
            d dVar4 = (d) this.f10273f[i8].get("JPEGInterchangeFormatLength");
            if (dVar3 == null || dVar4 == null) {
                return;
            }
            int m8 = dVar3.m(this.f10275h);
            int m9 = dVar3.m(this.f10275h);
            bVar.f(m8);
            byte[] bArr = new byte[m9];
            bVar.read(bArr);
            r(new b(bArr), m8, i8);
        }
    }

    private static String b(byte[] bArr) {
        StringBuilder sb = new StringBuilder(bArr.length * 2);
        for (byte b8 : bArr) {
            sb.append(String.format("%02x", Byte.valueOf(b8)));
        }
        return sb.toString();
    }

    private static void c(FileDescriptor fileDescriptor) {
        try {
            Os.close(fileDescriptor);
        } catch (Exception unused) {
            Log.e("ExifInterface", "Error closing fd.");
        }
    }

    private void c0(InputStream inputStream, OutputStream outputStream) {
        if (f10260v) {
            Log.d("ExifInterface", "saveJpegAttributes starting with (inputStream: " + inputStream + ", outputStream: " + outputStream + ")");
        }
        DataInputStream dataInputStream = new DataInputStream(inputStream);
        c cVar = new c(outputStream, ByteOrder.BIG_ENDIAN);
        if (dataInputStream.readByte() != -1) {
            throw new IOException("Invalid marker");
        }
        cVar.c(-1);
        if (dataInputStream.readByte() != -40) {
            throw new IOException("Invalid marker");
        }
        cVar.c(-40);
        d dVar = (m("Xmp") == null || !this.f10288u) ? null : (d) this.f10273f[0].remove("Xmp");
        cVar.c(-1);
        cVar.c(-31);
        o0(cVar);
        if (dVar != null) {
            this.f10273f[0].put("Xmp", dVar);
        }
        byte[] bArr = new byte[4096];
        while (dataInputStream.readByte() == -1) {
            byte readByte = dataInputStream.readByte();
            if (readByte == -39 || readByte == -38) {
                cVar.c(-1);
                cVar.c(readByte);
                h(dataInputStream, cVar);
                return;
            } else if (readByte != -31) {
                cVar.c(-1);
                cVar.c(readByte);
                int readUnsignedShort = dataInputStream.readUnsignedShort();
                cVar.j(readUnsignedShort);
                int i8 = readUnsignedShort - 2;
                if (i8 < 0) {
                    throw new IOException("Invalid length");
                }
                while (i8 > 0) {
                    int read = dataInputStream.read(bArr, 0, Math.min(i8, 4096));
                    if (read >= 0) {
                        cVar.write(bArr, 0, read);
                        i8 -= read;
                    }
                }
            } else {
                int readUnsignedShort2 = dataInputStream.readUnsignedShort() - 2;
                if (readUnsignedShort2 < 0) {
                    throw new IOException("Invalid length");
                }
                byte[] bArr2 = new byte[6];
                if (readUnsignedShort2 >= 6) {
                    if (dataInputStream.read(bArr2) != 6) {
                        throw new IOException("Invalid exif");
                    }
                    if (Arrays.equals(bArr2, f10257s0)) {
                        int i9 = readUnsignedShort2 - 6;
                        if (dataInputStream.skipBytes(i9) != i9) {
                            throw new IOException("Invalid length");
                        }
                    }
                }
                cVar.c(-1);
                cVar.c(readByte);
                cVar.j(readUnsignedShort2 + 2);
                if (readUnsignedShort2 >= 6) {
                    readUnsignedShort2 -= 6;
                    cVar.write(bArr2);
                }
                while (readUnsignedShort2 > 0) {
                    int read2 = dataInputStream.read(bArr, 0, Math.min(readUnsignedShort2, 4096));
                    if (read2 >= 0) {
                        cVar.write(bArr, 0, read2);
                        readUnsignedShort2 -= read2;
                    }
                }
            }
        }
        throw new IOException("Invalid marker");
    }

    private static void d(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (RuntimeException e8) {
                throw e8;
            } catch (Exception unused) {
            }
        }
    }

    private void d0(InputStream inputStream, OutputStream outputStream) {
        ByteArrayOutputStream byteArrayOutputStream;
        if (f10260v) {
            Log.d("ExifInterface", "savePngAttributes starting with (inputStream: " + inputStream + ", outputStream: " + outputStream + ")");
        }
        DataInputStream dataInputStream = new DataInputStream(inputStream);
        ByteOrder byteOrder = ByteOrder.BIG_ENDIAN;
        c cVar = new c(outputStream, byteOrder);
        byte[] bArr = H;
        i(dataInputStream, cVar, bArr.length);
        int i8 = this.f10283p;
        if (i8 == 0) {
            int readInt = dataInputStream.readInt();
            cVar.d(readInt);
            i(dataInputStream, cVar, readInt + 4 + 4);
        } else {
            i(dataInputStream, cVar, ((i8 - bArr.length) - 4) - 4);
            dataInputStream.skipBytes(dataInputStream.readInt() + 4 + 4);
        }
        ByteArrayOutputStream byteArrayOutputStream2 = null;
        try {
            byteArrayOutputStream = new ByteArrayOutputStream();
        } catch (Throwable th) {
            th = th;
        }
        try {
            c cVar2 = new c(byteArrayOutputStream, byteOrder);
            o0(cVar2);
            byte[] byteArray = ((ByteArrayOutputStream) cVar2.f10298e).toByteArray();
            cVar.write(byteArray);
            CRC32 crc32 = new CRC32();
            crc32.update(byteArray, 4, byteArray.length - 4);
            cVar.d((int) crc32.getValue());
            d(byteArrayOutputStream);
            h(dataInputStream, cVar);
        } catch (Throwable th2) {
            th = th2;
            byteArrayOutputStream2 = byteArrayOutputStream;
            d(byteArrayOutputStream2);
            throw th;
        }
    }

    private String e(double d8) {
        long j8 = (long) d8;
        double d9 = d8 - j8;
        long j9 = (long) (d9 * 60.0d);
        long round = Math.round((d9 - (j9 / 60.0d)) * 3600.0d * 1.0E7d);
        return j8 + "/1," + j9 + "/1," + round + "/10000000";
    }

    private void e0(InputStream inputStream, OutputStream outputStream) {
        ByteArrayOutputStream byteArrayOutputStream;
        int i8;
        int i9;
        int i10;
        int i11;
        if (f10260v) {
            Log.d("ExifInterface", "saveWebpAttributes starting with (inputStream: " + inputStream + ", outputStream: " + outputStream + ")");
        }
        ByteOrder byteOrder = ByteOrder.LITTLE_ENDIAN;
        b bVar = new b(inputStream, byteOrder);
        c cVar = new c(outputStream, byteOrder);
        byte[] bArr = L;
        i(bVar, cVar, bArr.length);
        byte[] bArr2 = M;
        bVar.skipBytes(bArr2.length + 4);
        ByteArrayOutputStream byteArrayOutputStream2 = null;
        try {
            try {
                byteArrayOutputStream = new ByteArrayOutputStream();
            } catch (Throwable th) {
                th = th;
            }
        } catch (Exception e8) {
            e = e8;
        }
        try {
            c cVar2 = new c(byteArrayOutputStream, byteOrder);
            int i12 = this.f10283p;
            if (i12 != 0) {
                i(bVar, cVar2, ((i12 - ((bArr.length + 4) + bArr2.length)) - 4) - 4);
                bVar.skipBytes(4);
                bVar.skipBytes(bVar.readInt());
            } else {
                byte[] bArr3 = new byte[4];
                if (bVar.read(bArr3) != 4) {
                    throw new IOException("Encountered invalid length while parsing WebP chunk type");
                }
                byte[] bArr4 = P;
                if (!Arrays.equals(bArr3, bArr4)) {
                    byte[] bArr5 = R;
                    if (Arrays.equals(bArr3, bArr5) || Arrays.equals(bArr3, Q)) {
                        int readInt = bVar.readInt();
                        int i13 = readInt % 2 == 1 ? readInt + 1 : readInt;
                        byte[] bArr6 = new byte[3];
                        if (Arrays.equals(bArr3, bArr5)) {
                            bVar.read(bArr6);
                            byte[] bArr7 = new byte[3];
                            if (bVar.read(bArr7) != 3 || !Arrays.equals(O, bArr7)) {
                                throw new IOException("Encountered error while checking VP8 signature");
                            }
                            i8 = bVar.readInt();
                            i9 = (i8 << 18) >> 18;
                            i10 = (i8 << 2) >> 18;
                            i13 -= 10;
                            i11 = 0;
                        } else if (!Arrays.equals(bArr3, Q)) {
                            i8 = 0;
                            i9 = 0;
                            i10 = 0;
                            i11 = 0;
                        } else if (bVar.readByte() != 47) {
                            throw new IOException("Encountered error while checking VP8L signature");
                        } else {
                            i8 = bVar.readInt();
                            i11 = i8 & 8;
                            i13 -= 5;
                            i10 = ((i8 << 4) >> 18) + 1;
                            i9 = ((i8 << 18) >> 18) + 1;
                        }
                        cVar2.write(bArr4);
                        cVar2.d(10);
                        byte[] bArr8 = new byte[10];
                        byte b8 = (byte) (bArr8[0] | 8);
                        bArr8[0] = b8;
                        bArr8[0] = (byte) (b8 | (i11 << 4));
                        int i14 = i9 - 1;
                        int i15 = i10 - 1;
                        bArr8[4] = (byte) i14;
                        bArr8[5] = (byte) (i14 >> 8);
                        bArr8[6] = (byte) (i14 >> 16);
                        bArr8[7] = (byte) i15;
                        bArr8[8] = (byte) (i15 >> 8);
                        bArr8[9] = (byte) (i15 >> 16);
                        cVar2.write(bArr8);
                        cVar2.write(bArr3);
                        cVar2.d(readInt);
                        if (Arrays.equals(bArr3, bArr5)) {
                            cVar2.write(bArr6);
                            cVar2.write(O);
                        } else {
                            if (Arrays.equals(bArr3, Q)) {
                                cVar2.write(47);
                            }
                            i(bVar, cVar2, i13);
                        }
                        cVar2.d(i8);
                        i(bVar, cVar2, i13);
                    }
                    h(bVar, cVar2);
                    int size = byteArrayOutputStream.size();
                    byte[] bArr9 = M;
                    cVar.d(size + bArr9.length);
                    cVar.write(bArr9);
                    byteArrayOutputStream.writeTo(cVar);
                    d(byteArrayOutputStream);
                }
                int readInt2 = bVar.readInt();
                byte[] bArr10 = new byte[readInt2 % 2 == 1 ? readInt2 + 1 : readInt2];
                bVar.read(bArr10);
                byte b9 = (byte) (8 | bArr10[0]);
                bArr10[0] = b9;
                boolean z7 = ((b9 >> 1) & 1) == 1;
                cVar2.write(bArr4);
                cVar2.d(readInt2);
                cVar2.write(bArr10);
                if (z7) {
                    j(bVar, cVar2, S, null);
                    while (true) {
                        byte[] bArr11 = new byte[4];
                        inputStream.read(bArr11);
                        if (!Arrays.equals(bArr11, T)) {
                            break;
                        }
                        k(bVar, cVar2, bArr11);
                    }
                } else {
                    j(bVar, cVar2, R, Q);
                }
            }
            o0(cVar2);
            h(bVar, cVar2);
            int size2 = byteArrayOutputStream.size();
            byte[] bArr92 = M;
            cVar.d(size2 + bArr92.length);
            cVar.write(bArr92);
            byteArrayOutputStream.writeTo(cVar);
            d(byteArrayOutputStream);
        } catch (Exception e9) {
            e = e9;
            throw new IOException("Failed to save WebP file", e);
        } catch (Throwable th2) {
            th = th2;
            byteArrayOutputStream2 = byteArrayOutputStream;
            d(byteArrayOutputStream2);
            throw th;
        }
    }

    private static double f(String str, String str2) {
        try {
            String[] split = str.split(",", -1);
            String[] split2 = split[0].split("/", -1);
            String[] split3 = split[1].split("/", -1);
            String[] split4 = split[2].split("/", -1);
            double parseDouble = (Double.parseDouble(split2[0].trim()) / Double.parseDouble(split2[1].trim())) + ((Double.parseDouble(split3[0].trim()) / Double.parseDouble(split3[1].trim())) / 60.0d) + ((Double.parseDouble(split4[0].trim()) / Double.parseDouble(split4[1].trim())) / 3600.0d);
            if (!str2.equals("S") && !str2.equals("W")) {
                if (!str2.equals("N") && !str2.equals("E")) {
                    throw new IllegalArgumentException();
                }
                return parseDouble;
            }
            return -parseDouble;
        } catch (ArrayIndexOutOfBoundsException | NumberFormatException unused) {
            throw new IllegalArgumentException();
        }
    }

    private static long[] g(Object obj) {
        if (!(obj instanceof int[])) {
            if (obj instanceof long[]) {
                return (long[]) obj;
            }
            return null;
        }
        int[] iArr = (int[]) obj;
        long[] jArr = new long[iArr.length];
        for (int i8 = 0; i8 < iArr.length; i8++) {
            jArr[i8] = iArr[i8];
        }
        return jArr;
    }

    private static int h(InputStream inputStream, OutputStream outputStream) {
        byte[] bArr = new byte[8192];
        int i8 = 0;
        while (true) {
            int read = inputStream.read(bArr);
            if (read == -1) {
                return i8;
            }
            i8 += read;
            outputStream.write(bArr, 0, read);
        }
    }

    private static void i(InputStream inputStream, OutputStream outputStream, int i8) {
        byte[] bArr = new byte[8192];
        while (i8 > 0) {
            int min = Math.min(i8, 8192);
            int read = inputStream.read(bArr, 0, min);
            if (read != min) {
                throw new IOException("Failed to copy the given amount of bytes from the inputstream to the output stream.");
            }
            i8 -= read;
            outputStream.write(bArr, 0, read);
        }
    }

    private void j(b bVar, c cVar, byte[] bArr, byte[] bArr2) {
        Charset charset;
        String str;
        while (true) {
            byte[] bArr3 = new byte[4];
            if (bVar.read(bArr3) != 4) {
                StringBuilder sb = new StringBuilder();
                sb.append("Encountered invalid length while copying WebP chunks up tochunk type ");
                sb.append(new String(bArr, f10256r0));
                if (bArr2 == null) {
                    str = "";
                } else {
                    str = " or " + new String(bArr2, charset);
                }
                sb.append(str);
                throw new IOException(sb.toString());
            }
            k(bVar, cVar, bArr3);
            if (Arrays.equals(bArr3, bArr)) {
                return;
            }
            if (bArr2 != null && Arrays.equals(bArr3, bArr2)) {
                return;
            }
        }
    }

    private void j0(b bVar) {
        HashMap hashMap = this.f10273f[4];
        d dVar = (d) hashMap.get("Compression");
        if (dVar != null) {
            int m8 = dVar.m(this.f10275h);
            this.f10282o = m8;
            if (m8 != 1) {
                if (m8 != 6) {
                    if (m8 != 7) {
                        return;
                    }
                }
            }
            if (P(hashMap)) {
                F(bVar, hashMap);
                return;
            }
            return;
        }
        this.f10282o = 6;
        E(bVar, hashMap);
    }

    private void k(b bVar, c cVar, byte[] bArr) {
        int readInt = bVar.readInt();
        cVar.write(bArr);
        cVar.d(readInt);
        if (readInt % 2 == 1) {
            readInt++;
        }
        i(bVar, cVar, readInt);
    }

    private static boolean k0(byte[] bArr, byte[] bArr2) {
        if (bArr == null || bArr2 == null || bArr.length < bArr2.length) {
            return false;
        }
        for (int i8 = 0; i8 < bArr2.length; i8++) {
            if (bArr[i8] != bArr2[i8]) {
                return false;
            }
        }
        return true;
    }

    private void l0(int i8, int i9) {
        String str;
        if (this.f10273f[i8].isEmpty() || this.f10273f[i9].isEmpty()) {
            if (f10260v) {
                Log.d("ExifInterface", "Cannot perform swap since only one image data exists");
                return;
            }
            return;
        }
        d dVar = (d) this.f10273f[i8].get("ImageLength");
        d dVar2 = (d) this.f10273f[i8].get("ImageWidth");
        d dVar3 = (d) this.f10273f[i9].get("ImageLength");
        d dVar4 = (d) this.f10273f[i9].get("ImageWidth");
        if (dVar == null || dVar2 == null) {
            if (!f10260v) {
                return;
            }
            str = "First image does not contain valid size information";
        } else if (dVar3 != null && dVar4 != null) {
            int m8 = dVar.m(this.f10275h);
            int m9 = dVar2.m(this.f10275h);
            int m10 = dVar3.m(this.f10275h);
            int m11 = dVar4.m(this.f10275h);
            if (m8 >= m10 || m9 >= m11) {
                return;
            }
            HashMap[] hashMapArr = this.f10273f;
            HashMap hashMap = hashMapArr[i8];
            hashMapArr[i8] = hashMapArr[i9];
            hashMapArr[i9] = hashMap;
            return;
        } else if (!f10260v) {
            return;
        } else {
            str = "Second image does not contain valid size information";
        }
        Log.d("ExifInterface", str);
    }

    private void m0(b bVar, int i8) {
        StringBuilder sb;
        String arrays;
        d j8;
        d j9;
        d dVar = (d) this.f10273f[i8].get("DefaultCropSize");
        d dVar2 = (d) this.f10273f[i8].get("SensorTopBorder");
        d dVar3 = (d) this.f10273f[i8].get("SensorLeftBorder");
        d dVar4 = (d) this.f10273f[i8].get("SensorBottomBorder");
        d dVar5 = (d) this.f10273f[i8].get("SensorRightBorder");
        if (dVar == null) {
            if (dVar2 == null || dVar3 == null || dVar4 == null || dVar5 == null) {
                a0(bVar, i8);
                return;
            }
            int m8 = dVar2.m(this.f10275h);
            int m9 = dVar4.m(this.f10275h);
            int m10 = dVar5.m(this.f10275h);
            int m11 = dVar3.m(this.f10275h);
            if (m9 <= m8 || m10 <= m11) {
                return;
            }
            d j10 = d.j(m9 - m8, this.f10275h);
            d j11 = d.j(m10 - m11, this.f10275h);
            this.f10273f[i8].put("ImageLength", j10);
            this.f10273f[i8].put("ImageWidth", j11);
        } else if (dVar.f10300a == 5) {
            f[] fVarArr = (f[]) dVar.o(this.f10275h);
            if (fVarArr != null && fVarArr.length == 2) {
                j8 = d.h(fVarArr[0], this.f10275h);
                j9 = d.h(fVarArr[1], this.f10275h);
                this.f10273f[i8].put("ImageWidth", j8);
                this.f10273f[i8].put("ImageLength", j9);
                return;
            }
            sb = new StringBuilder();
            sb.append("Invalid crop size values. cropSize=");
            arrays = Arrays.toString(fVarArr);
            sb.append(arrays);
            Log.w("ExifInterface", sb.toString());
        } else {
            int[] iArr = (int[]) dVar.o(this.f10275h);
            if (iArr != null && iArr.length == 2) {
                j8 = d.j(iArr[0], this.f10275h);
                j9 = d.j(iArr[1], this.f10275h);
                this.f10273f[i8].put("ImageWidth", j8);
                this.f10273f[i8].put("ImageLength", j9);
                return;
            }
            sb = new StringBuilder();
            sb.append("Invalid crop size values. cropSize=");
            arrays = Arrays.toString(iArr);
            sb.append(arrays);
            Log.w("ExifInterface", sb.toString());
        }
    }

    private void n0() {
        l0(0, 5);
        l0(0, 4);
        l0(5, 4);
        d dVar = (d) this.f10273f[1].get("PixelXDimension");
        d dVar2 = (d) this.f10273f[1].get("PixelYDimension");
        if (dVar != null && dVar2 != null) {
            this.f10273f[0].put("ImageWidth", dVar);
            this.f10273f[0].put("ImageLength", dVar2);
        }
        if (this.f10273f[4].isEmpty() && R(this.f10273f[5])) {
            HashMap[] hashMapArr = this.f10273f;
            hashMapArr[4] = hashMapArr[5];
            hashMapArr[5] = new HashMap();
        }
        if (R(this.f10273f[4])) {
            return;
        }
        Log.d("ExifInterface", "No image meets the size requirements of a thumbnail image.");
    }

    private int o0(c cVar) {
        e[][] eVarArr = f10248j0;
        int[] iArr = new int[eVarArr.length];
        int[] iArr2 = new int[eVarArr.length];
        for (e eVar : f10249k0) {
            Z(eVar.f10305b);
        }
        Z(f10250l0.f10305b);
        Z(f10251m0.f10305b);
        for (int i8 = 0; i8 < f10248j0.length; i8++) {
            for (Object obj : this.f10273f[i8].entrySet().toArray()) {
                Map.Entry entry = (Map.Entry) obj;
                if (entry.getValue() == null) {
                    this.f10273f[i8].remove(entry.getKey());
                }
            }
        }
        if (!this.f10273f[1].isEmpty()) {
            this.f10273f[0].put(f10249k0[1].f10305b, d.f(0L, this.f10275h));
        }
        if (!this.f10273f[2].isEmpty()) {
            this.f10273f[0].put(f10249k0[2].f10305b, d.f(0L, this.f10275h));
        }
        if (!this.f10273f[3].isEmpty()) {
            this.f10273f[1].put(f10249k0[3].f10305b, d.f(0L, this.f10275h));
        }
        if (this.f10276i) {
            this.f10273f[4].put(f10250l0.f10305b, d.f(0L, this.f10275h));
            this.f10273f[4].put(f10251m0.f10305b, d.f(this.f10280m, this.f10275h));
        }
        for (int i9 = 0; i9 < f10248j0.length; i9++) {
            int i10 = 0;
            for (Map.Entry entry2 : this.f10273f[i9].entrySet()) {
                int p8 = ((d) entry2.getValue()).p();
                if (p8 > 4) {
                    i10 += p8;
                }
            }
            iArr2[i9] = iArr2[i9] + i10;
        }
        int i11 = 8;
        for (int i12 = 0; i12 < f10248j0.length; i12++) {
            if (!this.f10273f[i12].isEmpty()) {
                iArr[i12] = i11;
                i11 += (this.f10273f[i12].size() * 12) + 2 + 4 + iArr2[i12];
            }
        }
        if (this.f10276i) {
            this.f10273f[4].put(f10250l0.f10305b, d.f(i11, this.f10275h));
            this.f10279l = i11;
            i11 += this.f10280m;
        }
        if (this.f10271d == 4) {
            i11 += 8;
        }
        if (f10260v) {
            for (int i13 = 0; i13 < f10248j0.length; i13++) {
                Log.d("ExifInterface", String.format("index: %d, offsets: %d, tag count: %d, data sizes: %d, total size: %d", Integer.valueOf(i13), Integer.valueOf(iArr[i13]), Integer.valueOf(this.f10273f[i13].size()), Integer.valueOf(iArr2[i13]), Integer.valueOf(i11)));
            }
        }
        if (!this.f10273f[1].isEmpty()) {
            this.f10273f[0].put(f10249k0[1].f10305b, d.f(iArr[1], this.f10275h));
        }
        if (!this.f10273f[2].isEmpty()) {
            this.f10273f[0].put(f10249k0[2].f10305b, d.f(iArr[2], this.f10275h));
        }
        if (!this.f10273f[3].isEmpty()) {
            this.f10273f[1].put(f10249k0[3].f10305b, d.f(iArr[3], this.f10275h));
        }
        int i14 = this.f10271d;
        if (i14 == 4) {
            cVar.j(i11);
            cVar.write(f10257s0);
        } else if (i14 == 13) {
            cVar.d(i11);
            cVar.write(I);
        } else if (i14 == 14) {
            cVar.write(N);
            cVar.d(i11);
        }
        cVar.f(this.f10275h == ByteOrder.BIG_ENDIAN ? (short) 19789 : (short) 18761);
        cVar.b(this.f10275h);
        cVar.j(42);
        cVar.g(8L);
        for (int i15 = 0; i15 < f10248j0.length; i15++) {
            if (!this.f10273f[i15].isEmpty()) {
                cVar.j(this.f10273f[i15].size());
                int size = iArr[i15] + 2 + (this.f10273f[i15].size() * 12) + 4;
                for (Map.Entry entry3 : this.f10273f[i15].entrySet()) {
                    int i16 = ((e) f10253o0[i15].get(entry3.getKey())).f10304a;
                    d dVar = (d) entry3.getValue();
                    int p9 = dVar.p();
                    cVar.j(i16);
                    cVar.j(dVar.f10300a);
                    cVar.d(dVar.f10301b);
                    if (p9 > 4) {
                        cVar.g(size);
                        size += p9;
                    } else {
                        cVar.write(dVar.f10303d);
                        if (p9 < 4) {
                            while (p9 < 4) {
                                cVar.c(0);
                                p9++;
                            }
                        }
                    }
                }
                if (i15 != 0 || this.f10273f[4].isEmpty()) {
                    cVar.g(0L);
                } else {
                    cVar.g(iArr[4]);
                }
                for (Map.Entry entry4 : this.f10273f[i15].entrySet()) {
                    byte[] bArr = ((d) entry4.getValue()).f10303d;
                    if (bArr.length > 4) {
                        cVar.write(bArr, 0, bArr.length);
                    }
                }
            }
        }
        if (this.f10276i) {
            cVar.write(B());
        }
        if (this.f10271d == 14 && i11 % 2 == 1) {
            cVar.c(0);
        }
        cVar.b(ByteOrder.BIG_ENDIAN);
        return i11;
    }

    private d p(String str) {
        if (str != null) {
            if ("ISOSpeedRatings".equals(str)) {
                if (f10260v) {
                    Log.d("ExifInterface", "getExifAttribute: Replacing TAG_ISO_SPEED_RATINGS with TAG_PHOTOGRAPHIC_SENSITIVITY.");
                }
                str = "PhotographicSensitivity";
            }
            for (int i8 = 0; i8 < f10248j0.length; i8++) {
                d dVar = (d) this.f10273f[i8].get(str);
                if (dVar != null) {
                    return dVar;
                }
            }
            return null;
        }
        throw new NullPointerException("tag shouldn't be null");
    }

    private void q(b bVar) {
        String str;
        String str2;
        String str3;
        MediaMetadataRetriever mediaMetadataRetriever = new MediaMetadataRetriever();
        try {
            mediaMetadataRetriever.setDataSource(new C0142a(bVar));
            String extractMetadata = mediaMetadataRetriever.extractMetadata(33);
            String extractMetadata2 = mediaMetadataRetriever.extractMetadata(34);
            String extractMetadata3 = mediaMetadataRetriever.extractMetadata(26);
            String extractMetadata4 = mediaMetadataRetriever.extractMetadata(17);
            if ("yes".equals(extractMetadata3)) {
                str = mediaMetadataRetriever.extractMetadata(29);
                str2 = mediaMetadataRetriever.extractMetadata(30);
                str3 = mediaMetadataRetriever.extractMetadata(31);
            } else if ("yes".equals(extractMetadata4)) {
                str = mediaMetadataRetriever.extractMetadata(18);
                str2 = mediaMetadataRetriever.extractMetadata(19);
                str3 = mediaMetadataRetriever.extractMetadata(24);
            } else {
                str = null;
                str2 = null;
                str3 = null;
            }
            if (str != null) {
                this.f10273f[0].put("ImageWidth", d.j(Integer.parseInt(str), this.f10275h));
            }
            if (str2 != null) {
                this.f10273f[0].put("ImageLength", d.j(Integer.parseInt(str2), this.f10275h));
            }
            if (str3 != null) {
                int parseInt = Integer.parseInt(str3);
                this.f10273f[0].put("Orientation", d.j(parseInt != 90 ? parseInt != 180 ? parseInt != 270 ? 1 : 8 : 3 : 6, this.f10275h));
            }
            if (extractMetadata != null && extractMetadata2 != null) {
                int parseInt2 = Integer.parseInt(extractMetadata);
                int parseInt3 = Integer.parseInt(extractMetadata2);
                if (parseInt3 <= 6) {
                    throw new IOException("Invalid exif length");
                }
                bVar.f(parseInt2);
                byte[] bArr = new byte[6];
                if (bVar.read(bArr) != 6) {
                    throw new IOException("Can't read identifier");
                }
                int i8 = parseInt2 + 6;
                int i9 = parseInt3 - 6;
                if (!Arrays.equals(bArr, f10257s0)) {
                    throw new IOException("Invalid identifier");
                }
                byte[] bArr2 = new byte[i9];
                if (bVar.read(bArr2) != i9) {
                    throw new IOException("Can't read exif");
                }
                this.f10283p = i8;
                X(bArr2, 0);
            }
            if (f10260v) {
                Log.d("ExifInterface", "Heif meta: " + str + "x" + str2 + ", rotation " + str3);
            }
        } finally {
            mediaMetadataRetriever.release();
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:67:0x01a6, code lost:
        r22.g(r21.f10275h);
     */
    /* JADX WARN: Code restructure failed: missing block: B:68:0x01ab, code lost:
        return;
     */
    /* JADX WARN: Removed duplicated region for block: B:34:0x00bd A[FALL_THROUGH] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void r(q1.a.b r22, int r23, int r24) {
        /*
            Method dump skipped, instructions count: 554
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: q1.a.r(q1.a$b, int, int):void");
    }

    private int t(BufferedInputStream bufferedInputStream) {
        bufferedInputStream.mark(5000);
        byte[] bArr = new byte[5000];
        bufferedInputStream.read(bArr);
        bufferedInputStream.reset();
        if (J(bArr)) {
            return 4;
        }
        if (M(bArr)) {
            return 9;
        }
        if (I(bArr)) {
            return 12;
        }
        if (K(bArr)) {
            return 7;
        }
        if (N(bArr)) {
            return 10;
        }
        if (L(bArr)) {
            return 13;
        }
        return S(bArr) ? 14 : 0;
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x008c  */
    /* JADX WARN: Removed duplicated region for block: B:33:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void u(q1.a.b r6) {
        /*
            Method dump skipped, instructions count: 238
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: q1.a.u(q1.a$b):void");
    }

    private void v(b bVar) {
        if (f10260v) {
            Log.d("ExifInterface", "getPngAttributes starting with: " + bVar);
        }
        bVar.mark(0);
        bVar.g(ByteOrder.BIG_ENDIAN);
        byte[] bArr = H;
        bVar.skipBytes(bArr.length);
        int length = bArr.length + 0;
        while (true) {
            try {
                int readInt = bVar.readInt();
                int i8 = length + 4;
                byte[] bArr2 = new byte[4];
                if (bVar.read(bArr2) != 4) {
                    throw new IOException("Encountered invalid length while parsing PNG chunktype");
                }
                int i9 = i8 + 4;
                if (i9 == 16 && !Arrays.equals(bArr2, J)) {
                    throw new IOException("Encountered invalid PNG file--IHDR chunk should appearas the first chunk");
                }
                if (Arrays.equals(bArr2, K)) {
                    return;
                }
                if (Arrays.equals(bArr2, I)) {
                    byte[] bArr3 = new byte[readInt];
                    if (bVar.read(bArr3) != readInt) {
                        throw new IOException("Failed to read given length for given PNG chunk type: " + b(bArr2));
                    }
                    int readInt2 = bVar.readInt();
                    CRC32 crc32 = new CRC32();
                    crc32.update(bArr2);
                    crc32.update(bArr3);
                    if (((int) crc32.getValue()) == readInt2) {
                        this.f10283p = i9;
                        X(bArr3, 0);
                        n0();
                        j0(new b(bArr3));
                        return;
                    }
                    throw new IOException("Encountered invalid CRC value for PNG-EXIF chunk.\n recorded CRC value: " + readInt2 + ", calculated CRC value: " + crc32.getValue());
                }
                int i10 = readInt + 4;
                bVar.skipBytes(i10);
                length = i9 + i10;
            } catch (EOFException unused) {
                throw new IOException("Encountered corrupt PNG file.");
            }
        }
    }

    private void w(b bVar) {
        boolean z7 = f10260v;
        if (z7) {
            Log.d("ExifInterface", "getRafAttributes starting with: " + bVar);
        }
        bVar.mark(0);
        bVar.skipBytes(84);
        byte[] bArr = new byte[4];
        byte[] bArr2 = new byte[4];
        byte[] bArr3 = new byte[4];
        bVar.read(bArr);
        bVar.read(bArr2);
        bVar.read(bArr3);
        int i8 = ByteBuffer.wrap(bArr).getInt();
        int i9 = ByteBuffer.wrap(bArr2).getInt();
        int i10 = ByteBuffer.wrap(bArr3).getInt();
        byte[] bArr4 = new byte[i9];
        bVar.f(i8);
        bVar.read(bArr4);
        r(new b(bArr4), i8, 5);
        bVar.f(i10);
        bVar.g(ByteOrder.BIG_ENDIAN);
        int readInt = bVar.readInt();
        if (z7) {
            Log.d("ExifInterface", "numberOfDirectoryEntry: " + readInt);
        }
        for (int i11 = 0; i11 < readInt; i11++) {
            int readUnsignedShort = bVar.readUnsignedShort();
            int readUnsignedShort2 = bVar.readUnsignedShort();
            if (readUnsignedShort == f10243e0.f10304a) {
                short readShort = bVar.readShort();
                short readShort2 = bVar.readShort();
                d j8 = d.j(readShort, this.f10275h);
                d j9 = d.j(readShort2, this.f10275h);
                this.f10273f[0].put("ImageLength", j8);
                this.f10273f[0].put("ImageWidth", j9);
                if (f10260v) {
                    Log.d("ExifInterface", "Updated to length: " + ((int) readShort) + ", width: " + ((int) readShort2));
                    return;
                }
                return;
            }
            bVar.skipBytes(readUnsignedShort2);
        }
    }

    private void x(b bVar) {
        d dVar;
        U(bVar, bVar.available());
        Y(bVar, 0);
        m0(bVar, 0);
        m0(bVar, 5);
        m0(bVar, 4);
        n0();
        if (this.f10271d != 8 || (dVar = (d) this.f10273f[1].get("MakerNote")) == null) {
            return;
        }
        b bVar2 = new b(dVar.f10303d);
        bVar2.g(this.f10275h);
        bVar2.f(6L);
        Y(bVar2, 9);
        d dVar2 = (d) this.f10273f[9].get("ColorSpace");
        if (dVar2 != null) {
            this.f10273f[1].put("ColorSpace", dVar2);
        }
    }

    private void y(b bVar) {
        if (f10260v) {
            Log.d("ExifInterface", "getRw2Attributes starting with: " + bVar);
        }
        x(bVar);
        d dVar = (d) this.f10273f[0].get("JpgFromRaw");
        if (dVar != null) {
            r(new b(dVar.f10303d), (int) dVar.f10302c, 5);
        }
        d dVar2 = (d) this.f10273f[0].get("ISO");
        d dVar3 = (d) this.f10273f[1].get("PhotographicSensitivity");
        if (dVar2 == null || dVar3 != null) {
            return;
        }
        this.f10273f[1].put("PhotographicSensitivity", dVar2);
    }

    private void z(b bVar) {
        byte[] bArr = f10257s0;
        bVar.skipBytes(bArr.length);
        byte[] bArr2 = new byte[bVar.available()];
        bVar.readFully(bArr2);
        this.f10283p = bArr.length;
        X(bArr2, 0);
    }

    public byte[] A() {
        int i8 = this.f10282o;
        if (i8 == 6 || i8 == 7) {
            return B();
        }
        return null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:30:0x0065 A[Catch: Exception -> 0x0088, all -> 0x00a7, TRY_ENTER, TryCatch #3 {all -> 0x00a7, blocks: (B:27:0x004f, B:30:0x0065, B:32:0x0071, B:37:0x007c, B:38:0x0081, B:39:0x0082, B:40:0x0087, B:51:0x0099), top: B:62:0x000a }] */
    /* JADX WARN: Removed duplicated region for block: B:39:0x0082 A[Catch: Exception -> 0x0088, all -> 0x00a7, TryCatch #3 {all -> 0x00a7, blocks: (B:27:0x004f, B:30:0x0065, B:32:0x0071, B:37:0x007c, B:38:0x0081, B:39:0x0082, B:40:0x0087, B:51:0x0099), top: B:62:0x000a }] */
    /* JADX WARN: Removed duplicated region for block: B:54:0x00a3  */
    /* JADX WARN: Removed duplicated region for block: B:60:0x00ae  */
    /* JADX WARN: Type inference failed for: r1v1, types: [byte[]] */
    /* JADX WARN: Type inference failed for: r1v11 */
    /* JADX WARN: Type inference failed for: r1v12 */
    /* JADX WARN: Type inference failed for: r1v16 */
    /* JADX WARN: Type inference failed for: r1v2 */
    /* JADX WARN: Type inference failed for: r1v3 */
    /* JADX WARN: Type inference failed for: r1v4, types: [java.io.Closeable] */
    /* JADX WARN: Type inference failed for: r1v5, types: [android.content.res.AssetManager$AssetInputStream, java.io.Closeable, java.io.InputStream] */
    /* JADX WARN: Type inference failed for: r1v6, types: [java.io.Closeable, java.io.InputStream] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public byte[] B() {
        /*
            r9 = this;
            java.lang.String r0 = "ExifInterface"
            boolean r1 = r9.f10276i
            r2 = 0
            if (r1 != 0) goto L8
            return r2
        L8:
            byte[] r1 = r9.f10281n
            if (r1 == 0) goto Ld
            return r1
        Ld:
            android.content.res.AssetManager$AssetInputStream r1 = r9.f10270c     // Catch: java.lang.Throwable -> L92 java.lang.Exception -> L95
            if (r1 == 0) goto L2e
            boolean r3 = r1.markSupported()     // Catch: java.lang.Throwable -> L25 java.lang.Exception -> L29
            if (r3 == 0) goto L1c
            r1.reset()     // Catch: java.lang.Throwable -> L25 java.lang.Exception -> L29
        L1a:
            r3 = r2
            goto L4f
        L1c:
            java.lang.String r3 = "Cannot read thumbnail from inputstream without mark/reset support"
            android.util.Log.d(r0, r3)     // Catch: java.lang.Throwable -> L25 java.lang.Exception -> L29
            d(r1)
            return r2
        L25:
            r0 = move-exception
            r3 = r2
            goto La8
        L29:
            r3 = move-exception
            r4 = r3
            r3 = r2
            goto L99
        L2e:
            java.lang.String r1 = r9.f10268a     // Catch: java.lang.Throwable -> L92 java.lang.Exception -> L95
            if (r1 == 0) goto L3a
            java.io.FileInputStream r1 = new java.io.FileInputStream     // Catch: java.lang.Throwable -> L92 java.lang.Exception -> L95
            java.lang.String r3 = r9.f10268a     // Catch: java.lang.Throwable -> L92 java.lang.Exception -> L95
            r1.<init>(r3)     // Catch: java.lang.Throwable -> L92 java.lang.Exception -> L95
            goto L1a
        L3a:
            java.io.FileDescriptor r1 = r9.f10269b     // Catch: java.lang.Throwable -> L92 java.lang.Exception -> L95
            java.io.FileDescriptor r1 = android.system.Os.dup(r1)     // Catch: java.lang.Throwable -> L92 java.lang.Exception -> L95
            int r3 = android.system.OsConstants.SEEK_SET     // Catch: java.lang.Throwable -> L8a java.lang.Exception -> L8d
            r4 = 0
            android.system.Os.lseek(r1, r4, r3)     // Catch: java.lang.Throwable -> L8a java.lang.Exception -> L8d
            java.io.FileInputStream r3 = new java.io.FileInputStream     // Catch: java.lang.Throwable -> L8a java.lang.Exception -> L8d
            r3.<init>(r1)     // Catch: java.lang.Throwable -> L8a java.lang.Exception -> L8d
            r8 = r3
            r3 = r1
            r1 = r8
        L4f:
            int r4 = r9.f10279l     // Catch: java.lang.Exception -> L88 java.lang.Throwable -> La7
            int r5 = r9.f10283p     // Catch: java.lang.Exception -> L88 java.lang.Throwable -> La7
            int r4 = r4 + r5
            long r4 = (long) r4     // Catch: java.lang.Exception -> L88 java.lang.Throwable -> La7
            long r4 = r1.skip(r4)     // Catch: java.lang.Exception -> L88 java.lang.Throwable -> La7
            int r6 = r9.f10279l     // Catch: java.lang.Exception -> L88 java.lang.Throwable -> La7
            int r7 = r9.f10283p     // Catch: java.lang.Exception -> L88 java.lang.Throwable -> La7
            int r6 = r6 + r7
            long r6 = (long) r6
            int r4 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            java.lang.String r5 = "Corrupted image"
            if (r4 != 0) goto L82
            int r4 = r9.f10280m     // Catch: java.lang.Exception -> L88 java.lang.Throwable -> La7
            byte[] r4 = new byte[r4]     // Catch: java.lang.Exception -> L88 java.lang.Throwable -> La7
            int r6 = r1.read(r4)     // Catch: java.lang.Exception -> L88 java.lang.Throwable -> La7
            int r7 = r9.f10280m     // Catch: java.lang.Exception -> L88 java.lang.Throwable -> La7
            if (r6 != r7) goto L7c
            r9.f10281n = r4     // Catch: java.lang.Exception -> L88 java.lang.Throwable -> La7
            d(r1)
            if (r3 == 0) goto L7b
            c(r3)
        L7b:
            return r4
        L7c:
            java.io.IOException r4 = new java.io.IOException     // Catch: java.lang.Exception -> L88 java.lang.Throwable -> La7
            r4.<init>(r5)     // Catch: java.lang.Exception -> L88 java.lang.Throwable -> La7
            throw r4     // Catch: java.lang.Exception -> L88 java.lang.Throwable -> La7
        L82:
            java.io.IOException r4 = new java.io.IOException     // Catch: java.lang.Exception -> L88 java.lang.Throwable -> La7
            r4.<init>(r5)     // Catch: java.lang.Exception -> L88 java.lang.Throwable -> La7
            throw r4     // Catch: java.lang.Exception -> L88 java.lang.Throwable -> La7
        L88:
            r4 = move-exception
            goto L99
        L8a:
            r0 = move-exception
            r3 = r1
            goto La9
        L8d:
            r3 = move-exception
            r4 = r3
            r3 = r1
            r1 = r2
            goto L99
        L92:
            r0 = move-exception
            r3 = r2
            goto La9
        L95:
            r3 = move-exception
            r1 = r2
            r4 = r3
            r3 = r1
        L99:
            java.lang.String r5 = "Encountered exception while getting thumbnail"
            android.util.Log.d(r0, r5, r4)     // Catch: java.lang.Throwable -> La7
            d(r1)
            if (r3 == 0) goto La6
            c(r3)
        La6:
            return r2
        La7:
            r0 = move-exception
        La8:
            r2 = r1
        La9:
            d(r2)
            if (r3 == 0) goto Lb1
            c(r3)
        Lb1:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: q1.a.B():byte[]");
    }

    /* JADX WARN: Removed duplicated region for block: B:55:0x00cd A[Catch: all -> 0x00f5, Exception -> 0x00f7, TryCatch #19 {Exception -> 0x00f7, all -> 0x00f5, blocks: (B:53:0x00c9, B:55:0x00cd, B:57:0x00e3, B:56:0x00dc), top: B:114:0x00c9 }] */
    /* JADX WARN: Removed duplicated region for block: B:56:0x00dc A[Catch: all -> 0x00f5, Exception -> 0x00f7, TryCatch #19 {Exception -> 0x00f7, all -> 0x00f5, blocks: (B:53:0x00c9, B:55:0x00cd, B:57:0x00e3, B:56:0x00dc), top: B:114:0x00c9 }] */
    /* JADX WARN: Removed duplicated region for block: B:79:0x012e  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void b0() {
        /*
            Method dump skipped, instructions count: 347
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: q1.a.b0():void");
    }

    public void f0(double d8) {
        String str = d8 >= 0.0d ? "0" : "1";
        g0("GPSAltitude", new f(Math.abs(d8)).toString());
        g0("GPSAltitudeRef", str);
    }

    public void g0(String str, String str2) {
        StringBuilder sb;
        e eVar;
        int i8;
        int i9;
        HashMap hashMap;
        d a8;
        HashMap hashMap2;
        d c8;
        Matcher matcher;
        String str3 = str;
        String str4 = str2;
        if (str3 == null) {
            throw new NullPointerException("tag shouldn't be null");
        }
        if (("DateTime".equals(str3) || "DateTimeOriginal".equals(str3) || "DateTimeDigitized".equals(str3)) && str4 != null) {
            boolean find = f10263w0.matcher(str4).find();
            boolean find2 = f10265x0.matcher(str4).find();
            if (str2.length() != 19 || (!find && !find2)) {
                sb = new StringBuilder();
                sb.append("Invalid value for ");
                sb.append(str3);
                sb.append(" : ");
                sb.append(str4);
                Log.w("ExifInterface", sb.toString());
                return;
            } else if (find2) {
                str4 = str4.replaceAll("-", ":");
            }
        }
        if ("ISOSpeedRatings".equals(str3)) {
            if (f10260v) {
                Log.d("ExifInterface", "setAttribute: Replacing TAG_ISO_SPEED_RATINGS with TAG_PHOTOGRAPHIC_SENSITIVITY.");
            }
            str3 = "PhotographicSensitivity";
        }
        int i10 = 2;
        int i11 = 1;
        if (str4 != null && f10254p0.contains(str3)) {
            if (str3.equals("GPSTimeStamp")) {
                if (!f10261v0.matcher(str4).find()) {
                    sb = new StringBuilder();
                    sb.append("Invalid value for ");
                    sb.append(str3);
                    sb.append(" : ");
                    sb.append(str4);
                    Log.w("ExifInterface", sb.toString());
                    return;
                }
                str4 = Integer.parseInt(matcher.group(1)) + "/1," + Integer.parseInt(matcher.group(2)) + "/1," + Integer.parseInt(matcher.group(3)) + "/1";
            } else {
                try {
                    str4 = new f(Double.parseDouble(str4)).toString();
                } catch (NumberFormatException unused) {
                    sb = new StringBuilder();
                }
            }
        }
        int i12 = 0;
        int i13 = 0;
        while (i13 < f10248j0.length) {
            if ((i13 != 4 || this.f10276i) && (eVar = (e) f10253o0[i13].get(str3)) != null) {
                if (str4 == null) {
                    this.f10273f[i13].remove(str3);
                } else {
                    Pair D2 = D(str4);
                    int i14 = -1;
                    if (eVar.f10306c == ((Integer) D2.first).intValue() || eVar.f10306c == ((Integer) D2.second).intValue()) {
                        i8 = eVar.f10306c;
                    } else {
                        int i15 = eVar.f10307d;
                        if (i15 == -1 || !(i15 == ((Integer) D2.first).intValue() || eVar.f10307d == ((Integer) D2.second).intValue())) {
                            int i16 = eVar.f10306c;
                            if (i16 == i11 || i16 == 7 || i16 == i10) {
                                i8 = i16;
                            } else if (f10260v) {
                                StringBuilder sb2 = new StringBuilder();
                                sb2.append("Given tag (");
                                sb2.append(str3);
                                sb2.append(") value didn't match with one of expected formats: ");
                                String[] strArr = W;
                                sb2.append(strArr[eVar.f10306c]);
                                sb2.append(eVar.f10307d == -1 ? "" : ", " + strArr[eVar.f10307d]);
                                sb2.append(" (guess: ");
                                sb2.append(strArr[((Integer) D2.first).intValue()]);
                                sb2.append(((Integer) D2.second).intValue() != -1 ? ", " + strArr[((Integer) D2.second).intValue()] : "");
                                sb2.append(")");
                                Log.d("ExifInterface", sb2.toString());
                            }
                        } else {
                            i8 = eVar.f10307d;
                        }
                    }
                    switch (i8) {
                        case 1:
                            i9 = i11;
                            hashMap = this.f10273f[i13];
                            a8 = d.a(str4);
                            hashMap.put(str3, a8);
                            break;
                        case 2:
                        case 7:
                            i9 = i11;
                            hashMap = this.f10273f[i13];
                            a8 = d.e(str4);
                            hashMap.put(str3, a8);
                            break;
                        case 3:
                            i9 = i11;
                            String[] split = str4.split(",", -1);
                            int[] iArr = new int[split.length];
                            for (int i17 = 0; i17 < split.length; i17++) {
                                iArr[i17] = Integer.parseInt(split[i17]);
                            }
                            hashMap = this.f10273f[i13];
                            a8 = d.k(iArr, this.f10275h);
                            hashMap.put(str3, a8);
                            break;
                        case 4:
                            i9 = i11;
                            String[] split2 = str4.split(",", -1);
                            long[] jArr = new long[split2.length];
                            for (int i18 = 0; i18 < split2.length; i18++) {
                                jArr[i18] = Long.parseLong(split2[i18]);
                            }
                            hashMap = this.f10273f[i13];
                            a8 = d.g(jArr, this.f10275h);
                            hashMap.put(str3, a8);
                            break;
                        case 5:
                            String[] split3 = str4.split(",", -1);
                            f[] fVarArr = new f[split3.length];
                            int i19 = 0;
                            while (i19 < split3.length) {
                                String[] split4 = split3[i19].split("/", i14);
                                fVarArr[i19] = new f((long) Double.parseDouble(split4[0]), (long) Double.parseDouble(split4[1]));
                                i19++;
                                i14 = -1;
                            }
                            i9 = 1;
                            hashMap = this.f10273f[i13];
                            a8 = d.i(fVarArr, this.f10275h);
                            hashMap.put(str3, a8);
                            break;
                        case androidx.browser.customtabs.b.TAB_HIDDEN /* 6 */:
                        case 8:
                        case 11:
                        default:
                            i9 = i11;
                            if (f10260v) {
                                Log.d("ExifInterface", "Data format isn't one of expected formats: " + i8);
                                break;
                            } else {
                                break;
                            }
                        case 9:
                            String[] split5 = str4.split(",", -1);
                            int[] iArr2 = new int[split5.length];
                            for (int i20 = 0; i20 < split5.length; i20++) {
                                iArr2[i20] = Integer.parseInt(split5[i20]);
                            }
                            hashMap2 = this.f10273f[i13];
                            c8 = d.c(iArr2, this.f10275h);
                            hashMap2.put(str3, c8);
                            i9 = 1;
                            break;
                        case 10:
                            String[] split6 = str4.split(",", -1);
                            f[] fVarArr2 = new f[split6.length];
                            int i21 = i12;
                            while (i21 < split6.length) {
                                String[] split7 = split6[i21].split("/", -1);
                                fVarArr2[i21] = new f((long) Double.parseDouble(split7[i12]), (long) Double.parseDouble(split7[i11]));
                                i21++;
                                split6 = split6;
                                i12 = 0;
                                i11 = 1;
                            }
                            hashMap2 = this.f10273f[i13];
                            c8 = d.d(fVarArr2, this.f10275h);
                            hashMap2.put(str3, c8);
                            i9 = 1;
                            break;
                        case 12:
                            String[] split8 = str4.split(",", -1);
                            double[] dArr = new double[split8.length];
                            for (int i22 = i12; i22 < split8.length; i22++) {
                                dArr[i22] = Double.parseDouble(split8[i22]);
                            }
                            this.f10273f[i13].put(str3, d.b(dArr, this.f10275h));
                            break;
                    }
                    i13++;
                    i11 = i9;
                    i10 = 2;
                    i12 = 0;
                }
            }
            i9 = i11;
            i13++;
            i11 = i9;
            i10 = 2;
            i12 = 0;
        }
    }

    public void h0(Location location) {
        if (location == null) {
            return;
        }
        g0("GPSProcessingMethod", location.getProvider());
        i0(location.getLatitude(), location.getLongitude());
        f0(location.getAltitude());
        g0("GPSSpeedRef", "K");
        g0("GPSSpeed", new f((location.getSpeed() * ((float) TimeUnit.HOURS.toSeconds(1L))) / 1000.0f).toString());
        String[] split = U.format(new Date(location.getTime())).split("\\s+", -1);
        g0("GPSDateStamp", split[0]);
        g0("GPSTimeStamp", split[1]);
    }

    public void i0(double d8, double d9) {
        if (d8 < -90.0d || d8 > 90.0d || Double.isNaN(d8)) {
            throw new IllegalArgumentException("Latitude value " + d8 + " is not valid.");
        } else if (d9 < -180.0d || d9 > 180.0d || Double.isNaN(d9)) {
            throw new IllegalArgumentException("Longitude value " + d9 + " is not valid.");
        } else {
            g0("GPSLatitudeRef", d8 >= 0.0d ? "N" : "S");
            g0("GPSLatitude", e(Math.abs(d8)));
            g0("GPSLongitudeRef", d9 >= 0.0d ? "E" : "W");
            g0("GPSLongitude", e(Math.abs(d9)));
        }
    }

    public double l(double d8) {
        double n8 = n("GPSAltitude", -1.0d);
        int o8 = o("GPSAltitudeRef", -1);
        if (n8 < 0.0d || o8 < 0) {
            return d8;
        }
        return n8 * (o8 != 1 ? 1 : -1);
    }

    public String m(String str) {
        String str2;
        if (str != null) {
            d p8 = p(str);
            if (p8 != null) {
                if (!f10254p0.contains(str)) {
                    return p8.n(this.f10275h);
                }
                if (str.equals("GPSTimeStamp")) {
                    int i8 = p8.f10300a;
                    if (i8 == 5 || i8 == 10) {
                        f[] fVarArr = (f[]) p8.o(this.f10275h);
                        if (fVarArr != null && fVarArr.length == 3) {
                            f fVar = fVarArr[0];
                            Integer valueOf = Integer.valueOf((int) (((float) fVar.f10308a) / ((float) fVar.f10309b)));
                            f fVar2 = fVarArr[1];
                            Integer valueOf2 = Integer.valueOf((int) (((float) fVar2.f10308a) / ((float) fVar2.f10309b)));
                            f fVar3 = fVarArr[2];
                            return String.format("%02d:%02d:%02d", valueOf, valueOf2, Integer.valueOf((int) (((float) fVar3.f10308a) / ((float) fVar3.f10309b))));
                        }
                        str2 = "Invalid GPS Timestamp array. array=" + Arrays.toString(fVarArr);
                    } else {
                        str2 = "GPS Timestamp format is not rational. format=" + p8.f10300a;
                    }
                    Log.w("ExifInterface", str2);
                    return null;
                }
                try {
                    return Double.toString(p8.l(this.f10275h));
                } catch (NumberFormatException unused) {
                }
            }
            return null;
        }
        throw new NullPointerException("tag shouldn't be null");
    }

    public double n(String str, double d8) {
        if (str != null) {
            d p8 = p(str);
            if (p8 == null) {
                return d8;
            }
            try {
                return p8.l(this.f10275h);
            } catch (NumberFormatException unused) {
                return d8;
            }
        }
        throw new NullPointerException("tag shouldn't be null");
    }

    public int o(String str, int i8) {
        if (str != null) {
            d p8 = p(str);
            if (p8 == null) {
                return i8;
            }
            try {
                return p8.m(this.f10275h);
            } catch (NumberFormatException unused) {
                return i8;
            }
        }
        throw new NullPointerException("tag shouldn't be null");
    }

    public double[] s() {
        String m8 = m("GPSLatitude");
        String m9 = m("GPSLatitudeRef");
        String m10 = m("GPSLongitude");
        String m11 = m("GPSLongitudeRef");
        if (m8 == null || m9 == null || m10 == null || m11 == null) {
            return null;
        }
        try {
            return new double[]{f(m8, m9), f(m10, m11)};
        } catch (IllegalArgumentException unused) {
            Log.w("ExifInterface", "Latitude/longitude values are not parsable. " + String.format("latValue=%s, latRef=%s, lngValue=%s, lngRef=%s", m8, m9, m10, m11));
            return null;
        }
    }

    public a(InputStream inputStream, int i8) {
        e[][] eVarArr = f10248j0;
        this.f10273f = new HashMap[eVarArr.length];
        this.f10274g = new HashSet(eVarArr.length);
        this.f10275h = ByteOrder.BIG_ENDIAN;
        if (inputStream == null) {
            throw new NullPointerException("inputStream cannot be null");
        }
        this.f10268a = null;
        if (i8 == 1) {
            BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream, 5000);
            if (!H(bufferedInputStream)) {
                Log.w("ExifInterface", "Given data does not follow the structure of an Exif-only data.");
                return;
            }
            this.f10272e = true;
            this.f10270c = null;
            this.f10269b = null;
            inputStream = bufferedInputStream;
        } else {
            if (inputStream instanceof AssetManager.AssetInputStream) {
                this.f10270c = (AssetManager.AssetInputStream) inputStream;
            } else {
                if (inputStream instanceof FileInputStream) {
                    FileInputStream fileInputStream = (FileInputStream) inputStream;
                    if (O(fileInputStream.getFD())) {
                        this.f10270c = null;
                        this.f10269b = fileInputStream.getFD();
                    }
                }
                this.f10270c = null;
            }
            this.f10269b = null;
        }
        T(inputStream);
    }

    public a(String str) {
        e[][] eVarArr = f10248j0;
        this.f10273f = new HashMap[eVarArr.length];
        this.f10274g = new HashSet(eVarArr.length);
        this.f10275h = ByteOrder.BIG_ENDIAN;
        if (str == null) {
            throw new NullPointerException("filename cannot be null");
        }
        G(str);
    }
}
