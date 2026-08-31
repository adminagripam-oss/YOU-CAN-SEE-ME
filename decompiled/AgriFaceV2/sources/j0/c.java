package j0;

import androidx.camera.core.internal.compat.quirk.LargeJpegImageQuirk;
/* loaded from: classes.dex */
public class c {

    /* renamed from: a  reason: collision with root package name */
    private final LargeJpegImageQuirk f8182a = (LargeJpegImageQuirk) androidx.camera.core.internal.compat.quirk.a.b(LargeJpegImageQuirk.class);

    public static int a(byte[] bArr) {
        byte b8;
        int i8 = 2;
        while (i8 + 4 <= bArr.length && (b8 = bArr[i8]) == -1) {
            int i9 = i8 + 2;
            int i10 = ((bArr[i9] & 255) << 8) | (bArr[i8 + 3] & 255);
            if (b8 == -1 && bArr[i8 + 1] == -38) {
                while (true) {
                    int i11 = i9 + 2;
                    if (i11 > bArr.length) {
                        return -1;
                    }
                    if (bArr[i9] == -1 && bArr[i9 + 1] == -39) {
                        return i11;
                    }
                    i9++;
                }
            } else {
                i8 += i10 + 2;
            }
        }
        return -1;
    }

    public int b(byte[] bArr) {
        LargeJpegImageQuirk largeJpegImageQuirk = this.f8182a;
        if (largeJpegImageQuirk == null || !largeJpegImageQuirk.j(bArr)) {
            return bArr.length;
        }
        int a8 = a(bArr);
        return a8 != -1 ? a8 : bArr.length;
    }
}
