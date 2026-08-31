package z4;
/* loaded from: classes.dex */
public abstract class k {
    public static byte[] a(String str) {
        if (str.length() % 2 == 0) {
            int length = str.length() / 2;
            byte[] bArr = new byte[length];
            for (int i8 = 0; i8 < length; i8++) {
                int i9 = i8 * 2;
                int digit = Character.digit(str.charAt(i9), 16);
                int digit2 = Character.digit(str.charAt(i9 + 1), 16);
                if (digit == -1 || digit2 == -1) {
                    throw new IllegalArgumentException("input is not hexadecimal");
                }
                bArr[i8] = (byte) ((digit * 16) + digit2);
            }
            return bArr;
        }
        throw new IllegalArgumentException("Expected a string of even length");
    }

    public static String b(byte[] bArr) {
        StringBuilder sb = new StringBuilder(bArr.length * 2);
        for (byte b8 : bArr) {
            int i8 = b8 & 255;
            sb.append("0123456789abcdef".charAt(i8 / 16));
            sb.append("0123456789abcdef".charAt(i8 % 16));
        }
        return sb.toString();
    }
}
