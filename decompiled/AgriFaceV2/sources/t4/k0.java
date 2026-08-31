package t4;

import java.nio.charset.Charset;
import java.security.GeneralSecurityException;
import java.util.Objects;
/* loaded from: classes.dex */
public abstract class k0 {

    /* renamed from: a  reason: collision with root package name */
    public static final Charset f11870a = Charset.forName("UTF-8");

    private static final byte a(char c8) {
        if (c8 < '!' || c8 > '~') {
            throw new GeneralSecurityException("Not a printable ASCII character: " + c8);
        }
        return (byte) c8;
    }

    public static final a5.a b(String str) {
        byte[] bArr = new byte[str.length()];
        for (int i8 = 0; i8 < str.length(); i8++) {
            bArr[i8] = a(str.charAt(i8));
        }
        return a5.a.a(bArr);
    }

    public static Integer c() {
        if (d()) {
            return a.a();
        }
        return null;
    }

    public static boolean d() {
        return Objects.equals(System.getProperty("java.vendor"), "The Android Project");
    }

    public static boolean e(byte[] bArr, byte[] bArr2) {
        if (bArr2.length < bArr.length) {
            return false;
        }
        for (int i8 = 0; i8 < bArr.length; i8++) {
            if (bArr2[i8] != bArr[i8]) {
                return false;
            }
        }
        return true;
    }

    public static int f() {
        int i8 = 0;
        while (i8 == 0) {
            byte[] d8 = f0.d(4);
            i8 = (d8[3] & 255) | ((d8[0] & 255) << 24) | ((d8[1] & 255) << 16) | ((d8[2] & 255) << 8);
        }
        return i8;
    }

    private static final byte g(char c8) {
        if (c8 < '!' || c8 > '~') {
            throw new j0("Not a printable ASCII character: " + c8);
        }
        return (byte) c8;
    }

    public static final a5.a h(String str) {
        byte[] bArr = new byte[str.length()];
        for (int i8 = 0; i8 < str.length(); i8++) {
            bArr[i8] = g(str.charAt(i8));
        }
        return a5.a.a(bArr);
    }
}
