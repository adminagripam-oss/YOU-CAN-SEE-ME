package d0;

import d0.i;
import java.io.BufferedOutputStream;
import java.io.FilterOutputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.HashMap;
import java.util.Map;
/* loaded from: classes.dex */
public final class j extends FilterOutputStream {

    /* renamed from: k  reason: collision with root package name */
    private static final byte[] f6405k = "Exif\u0000\u0000".getBytes(h.f6372e);

    /* renamed from: e  reason: collision with root package name */
    private final i f6406e;

    /* renamed from: f  reason: collision with root package name */
    private final byte[] f6407f;

    /* renamed from: g  reason: collision with root package name */
    private final ByteBuffer f6408g;

    /* renamed from: h  reason: collision with root package name */
    private int f6409h;

    /* renamed from: i  reason: collision with root package name */
    private int f6410i;

    /* renamed from: j  reason: collision with root package name */
    private int f6411j;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static final class a {
        public static boolean a(short s8) {
            return (s8 < -64 || s8 > -49 || s8 == -60 || s8 == -56 || s8 == -52) ? false : true;
        }
    }

    public j(OutputStream outputStream, i iVar) {
        super(new BufferedOutputStream(outputStream, 65536));
        this.f6407f = new byte[1];
        this.f6408g = ByteBuffer.allocate(4);
        this.f6409h = 0;
        this.f6406e = iVar;
    }

    private int b(int i8, byte[] bArr, int i9, int i10) {
        int min = Math.min(i10, i8 - this.f6408g.position());
        this.f6408g.put(bArr, i9, min);
        return min;
    }

    private void c(b bVar) {
        k[] kVarArr;
        k[][] kVarArr2 = i.f6386i;
        int[] iArr = new int[kVarArr2.length];
        int[] iArr2 = new int[kVarArr2.length];
        for (k kVar : i.f6384g) {
            for (int i8 = 0; i8 < i.f6386i.length; i8++) {
                this.f6406e.c(i8).remove(kVar.f6413b);
            }
        }
        if (!this.f6406e.c(1).isEmpty()) {
            this.f6406e.c(0).put(i.f6384g[1].f6413b, h.f(0L, this.f6406e.d()));
        }
        if (!this.f6406e.c(2).isEmpty()) {
            this.f6406e.c(0).put(i.f6384g[2].f6413b, h.f(0L, this.f6406e.d()));
        }
        if (!this.f6406e.c(3).isEmpty()) {
            this.f6406e.c(1).put(i.f6384g[3].f6413b, h.f(0L, this.f6406e.d()));
        }
        for (int i9 = 0; i9 < i.f6386i.length; i9++) {
            int i10 = 0;
            for (Map.Entry entry : this.f6406e.c(i9).entrySet()) {
                int j8 = ((h) entry.getValue()).j();
                if (j8 > 4) {
                    i10 += j8;
                }
            }
            iArr2[i9] = iArr2[i9] + i10;
        }
        int i11 = 8;
        for (int i12 = 0; i12 < i.f6386i.length; i12++) {
            if (!this.f6406e.c(i12).isEmpty()) {
                iArr[i12] = i11;
                i11 += (this.f6406e.c(i12).size() * 12) + 2 + 4 + iArr2[i12];
            }
        }
        int i13 = i11 + 8;
        if (!this.f6406e.c(1).isEmpty()) {
            this.f6406e.c(0).put(i.f6384g[1].f6413b, h.f(iArr[1], this.f6406e.d()));
        }
        if (!this.f6406e.c(2).isEmpty()) {
            this.f6406e.c(0).put(i.f6384g[2].f6413b, h.f(iArr[2], this.f6406e.d()));
        }
        if (!this.f6406e.c(3).isEmpty()) {
            this.f6406e.c(1).put(i.f6384g[3].f6413b, h.f(iArr[3], this.f6406e.d()));
        }
        bVar.j(i13);
        bVar.write(f6405k);
        bVar.f(this.f6406e.d() == ByteOrder.BIG_ENDIAN ? (short) 19789 : (short) 18761);
        bVar.b(this.f6406e.d());
        bVar.j(42);
        bVar.g(8L);
        for (int i14 = 0; i14 < i.f6386i.length; i14++) {
            if (!this.f6406e.c(i14).isEmpty()) {
                bVar.j(this.f6406e.c(i14).size());
                int size = iArr[i14] + 2 + (this.f6406e.c(i14).size() * 12) + 4;
                for (Map.Entry entry2 : this.f6406e.c(i14).entrySet()) {
                    int i15 = ((k) g1.e.k((k) ((HashMap) i.b.f6394f.get(i14)).get(entry2.getKey()), "Tag not supported: " + ((String) entry2.getKey()) + ". Tag needs to be ported from ExifInterface to ExifData.")).f6412a;
                    h hVar = (h) entry2.getValue();
                    int j9 = hVar.j();
                    bVar.j(i15);
                    bVar.j(hVar.f6376a);
                    bVar.d(hVar.f6377b);
                    if (j9 > 4) {
                        bVar.g(size);
                        size += j9;
                    } else {
                        bVar.write(hVar.f6379d);
                        if (j9 < 4) {
                            while (j9 < 4) {
                                bVar.c(0);
                                j9++;
                            }
                        }
                    }
                }
                bVar.g(0L);
                for (Map.Entry entry3 : this.f6406e.c(i14).entrySet()) {
                    byte[] bArr = ((h) entry3.getValue()).f6379d;
                    if (bArr.length > 4) {
                        bVar.write(bArr, 0, bArr.length);
                    }
                }
            }
        }
        bVar.b(ByteOrder.BIG_ENDIAN);
    }

    @Override // java.io.FilterOutputStream, java.io.OutputStream
    public void write(int i8) {
        byte[] bArr = this.f6407f;
        bArr[0] = (byte) (i8 & 255);
        write(bArr);
    }

    @Override // java.io.FilterOutputStream, java.io.OutputStream
    public void write(byte[] bArr) {
        write(bArr, 0, bArr.length);
    }

    /* JADX WARN: Code restructure failed: missing block: B:45:0x0105, code lost:
        if (r9 <= 0) goto L12;
     */
    /* JADX WARN: Code restructure failed: missing block: B:46:0x0107, code lost:
        ((java.io.FilterOutputStream) r6).out.write(r7, r8, r9);
     */
    /* JADX WARN: Code restructure failed: missing block: B:47:0x010c, code lost:
        return;
     */
    /* JADX WARN: Code restructure failed: missing block: B:61:?, code lost:
        return;
     */
    @Override // java.io.FilterOutputStream, java.io.OutputStream
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void write(byte[] r7, int r8, int r9) {
        /*
            Method dump skipped, instructions count: 269
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: d0.j.write(byte[], int, int):void");
    }
}
