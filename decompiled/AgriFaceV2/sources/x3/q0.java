package x3;

import java.util.logging.Level;
import java.util.logging.Logger;
/* loaded from: classes.dex */
public abstract class q0 {
    public static String a(String str, Object... objArr) {
        int length;
        int length2;
        int indexOf;
        String sb;
        int i8 = 0;
        int i9 = 0;
        while (true) {
            length = objArr.length;
            if (i9 >= length) {
                break;
            }
            Object obj = objArr[i9];
            if (obj == null) {
                sb = "null";
            } else {
                try {
                    sb = obj.toString();
                } catch (Exception e8) {
                    String name = obj.getClass().getName();
                    String hexString = Integer.toHexString(System.identityHashCode(obj));
                    StringBuilder sb2 = new StringBuilder(name.length() + 1 + String.valueOf(hexString).length());
                    sb2.append(name);
                    sb2.append("@");
                    sb2.append(hexString);
                    String sb3 = sb2.toString();
                    Logger.getLogger("com.google.common.base.Strings").logp(Level.WARNING, "com.google.common.base.Strings", "lenientToString", "Exception during lenientFormat for ".concat(sb3), (Throwable) e8);
                    String name2 = e8.getClass().getName();
                    StringBuilder sb4 = new StringBuilder(sb3.length() + 8 + name2.length() + 1);
                    sb4.append("<");
                    sb4.append(sb3);
                    sb4.append(" threw ");
                    sb4.append(name2);
                    sb4.append(">");
                    sb = sb4.toString();
                }
            }
            objArr[i9] = sb;
            i9++;
        }
        StringBuilder sb5 = new StringBuilder(str.length() + (length * 16));
        int i10 = 0;
        while (true) {
            length2 = objArr.length;
            if (i8 >= length2 || (indexOf = str.indexOf("%s", i10)) == -1) {
                break;
            }
            sb5.append((CharSequence) str, i10, indexOf);
            sb5.append(objArr[i8]);
            i10 = indexOf + 2;
            i8++;
        }
        sb5.append((CharSequence) str, i10, str.length());
        if (i8 < length2) {
            sb5.append(" [");
            sb5.append(objArr[i8]);
            for (int i11 = i8 + 1; i11 < objArr.length; i11++) {
                sb5.append(", ");
                sb5.append(objArr[i11]);
            }
            sb5.append(']');
        }
        return sb5.toString();
    }
}
