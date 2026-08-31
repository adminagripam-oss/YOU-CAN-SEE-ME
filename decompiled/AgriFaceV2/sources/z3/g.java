package z3;

import java.io.OutputStream;
/* loaded from: classes.dex */
final class g extends OutputStream {

    /* renamed from: e  reason: collision with root package name */
    private long f13669e = 0;

    /* JADX INFO: Access modifiers changed from: package-private */
    public final long b() {
        return this.f13669e;
    }

    @Override // java.io.OutputStream
    public final void write(int i8) {
        this.f13669e++;
    }

    @Override // java.io.OutputStream
    public final void write(byte[] bArr) {
        this.f13669e += bArr.length;
    }

    @Override // java.io.OutputStream
    public final void write(byte[] bArr, int i8, int i9) {
        int length;
        int i10;
        if (i8 < 0 || i8 > (length = bArr.length) || i9 < 0 || (i10 = i8 + i9) > length || i10 < 0) {
            throw new IndexOutOfBoundsException();
        }
        this.f13669e += i9;
    }
}
