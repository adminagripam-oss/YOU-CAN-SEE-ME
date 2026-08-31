package h4;

import java.util.logging.Level;
import java.util.logging.Logger;
/* loaded from: classes.dex */
public abstract class c {
    public static String a(String str, Object... objArr) {
        int indexOf;
        String valueOf = String.valueOf(str);
        int i8 = 0;
        if (objArr == null) {
            objArr = new Object[]{"(Object[])null"};
        } else {
            for (int i9 = 0; i9 < objArr.length; i9++) {
                objArr[i9] = b(objArr[i9]);
            }
        }
        StringBuilder sb = new StringBuilder(valueOf.length() + (objArr.length * 16));
        int i10 = 0;
        while (i8 < objArr.length && (indexOf = valueOf.indexOf("%s", i10)) != -1) {
            sb.append((CharSequence) valueOf, i10, indexOf);
            sb.append(objArr[i8]);
            i10 = indexOf + 2;
            i8++;
        }
        sb.append((CharSequence) valueOf, i10, valueOf.length());
        if (i8 < objArr.length) {
            sb.append(" [");
            sb.append(objArr[i8]);
            for (int i11 = i8 + 1; i11 < objArr.length; i11++) {
                sb.append(", ");
                sb.append(objArr[i11]);
            }
            sb.append(']');
        }
        return sb.toString();
    }

    private static String b(Object obj) {
        if (obj == null) {
            return "null";
        }
        try {
            return obj.toString();
        } catch (Exception e8) {
            String str = obj.getClass().getName() + '@' + Integer.toHexString(System.identityHashCode(obj));
            Logger.getLogger("com.google.common.base.Strings").log(Level.WARNING, "Exception during lenientFormat for " + str, (Throwable) e8);
            return "<" + str + " threw " + e8.getClass().getName() + ">";
        }
    }
}
