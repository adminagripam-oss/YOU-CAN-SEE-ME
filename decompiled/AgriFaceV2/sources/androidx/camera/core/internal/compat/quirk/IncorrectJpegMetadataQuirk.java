package androidx.camera.core.internal.compat.quirk;

import android.os.Build;
import androidx.camera.core.n;
import b0.h2;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;
/* loaded from: classes.dex */
public final class IncorrectJpegMetadataQuirk implements h2 {

    /* renamed from: a  reason: collision with root package name */
    private static final Set f2730a = new HashSet(Arrays.asList("A24"));

    private boolean f(byte[] bArr) {
        byte b8;
        int i8 = 2;
        while (i8 + 4 <= bArr.length && (b8 = bArr[i8]) == -1) {
            if (b8 == -1 && bArr[i8 + 1] == -38) {
                return true;
            }
            i8 += (((bArr[i8 + 2] & 255) << 8) | (bArr[i8 + 3] & 255)) + 2;
        }
        return false;
    }

    private int g(byte[] bArr) {
        int i8 = 2;
        while (true) {
            int i9 = i8 + 1;
            if (i9 > bArr.length) {
                return -1;
            }
            if (bArr[i8] == -1 && bArr[i9] == -40) {
                return i8;
            }
            i8 = i9;
        }
    }

    private static boolean h() {
        return "Samsung".equalsIgnoreCase(Build.BRAND) && f2730a.contains(Build.DEVICE.toUpperCase(Locale.US));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean j() {
        return h();
    }

    public byte[] i(n nVar) {
        int i8 = 0;
        ByteBuffer c8 = nVar.i()[0].c();
        byte[] bArr = new byte[c8.capacity()];
        c8.rewind();
        c8.get(bArr);
        return (f(bArr) || (i8 = g(bArr)) != -1) ? Arrays.copyOfRange(bArr, i8, c8.limit()) : bArr;
    }
}
