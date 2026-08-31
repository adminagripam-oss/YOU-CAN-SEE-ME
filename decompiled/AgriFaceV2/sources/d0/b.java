package d0;

import java.io.FilterOutputStream;
import java.io.OutputStream;
import java.nio.ByteOrder;
/* loaded from: classes.dex */
class b extends FilterOutputStream {

    /* renamed from: e  reason: collision with root package name */
    final OutputStream f6358e;

    /* renamed from: f  reason: collision with root package name */
    private ByteOrder f6359f;

    /* JADX INFO: Access modifiers changed from: package-private */
    public b(OutputStream outputStream, ByteOrder byteOrder) {
        super(outputStream);
        this.f6358e = outputStream;
        this.f6359f = byteOrder;
    }

    public void b(ByteOrder byteOrder) {
        this.f6359f = byteOrder;
    }

    public void c(int i8) {
        this.f6358e.write(i8);
    }

    public void d(int i8) {
        OutputStream outputStream;
        int i9;
        ByteOrder byteOrder = this.f6359f;
        if (byteOrder == ByteOrder.LITTLE_ENDIAN) {
            this.f6358e.write((i8 >>> 0) & 255);
            this.f6358e.write((i8 >>> 8) & 255);
            this.f6358e.write((i8 >>> 16) & 255);
            outputStream = this.f6358e;
            i9 = i8 >>> 24;
        } else if (byteOrder != ByteOrder.BIG_ENDIAN) {
            return;
        } else {
            this.f6358e.write((i8 >>> 24) & 255);
            this.f6358e.write((i8 >>> 16) & 255);
            this.f6358e.write((i8 >>> 8) & 255);
            outputStream = this.f6358e;
            i9 = i8 >>> 0;
        }
        outputStream.write(i9 & 255);
    }

    public void f(short s8) {
        OutputStream outputStream;
        int i8;
        ByteOrder byteOrder = this.f6359f;
        if (byteOrder == ByteOrder.LITTLE_ENDIAN) {
            this.f6358e.write((s8 >>> 0) & 255);
            outputStream = this.f6358e;
            i8 = s8 >>> 8;
        } else if (byteOrder != ByteOrder.BIG_ENDIAN) {
            return;
        } else {
            this.f6358e.write((s8 >>> 8) & 255);
            outputStream = this.f6358e;
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
        this.f6358e.write(bArr);
    }

    @Override // java.io.FilterOutputStream, java.io.OutputStream
    public void write(byte[] bArr, int i8, int i9) {
        this.f6358e.write(bArr, i8, i9);
    }
}
