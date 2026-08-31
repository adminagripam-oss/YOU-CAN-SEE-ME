package j7;

import java.util.Collection;
import java.util.Iterator;
import s6.a0;
/* loaded from: classes.dex */
public abstract class l extends k {
    public static boolean d(CharSequence charSequence) {
        boolean z7;
        d7.k.e(charSequence, "<this>");
        if (charSequence.length() != 0) {
            g7.c j8 = m.j(charSequence);
            if (!(j8 instanceof Collection) || !((Collection) j8).isEmpty()) {
                Iterator it = j8.iterator();
                while (it.hasNext()) {
                    if (!a.c(charSequence.charAt(((a0) it).b()))) {
                        z7 = false;
                        break;
                    }
                }
            }
            z7 = true;
            return z7;
        }
        return true;
    }

    public static final boolean e(String str, int i8, String str2, int i9, int i10, boolean z7) {
        d7.k.e(str, "<this>");
        d7.k.e(str2, "other");
        return !z7 ? str.regionMatches(i8, str2, i9, i10) : str.regionMatches(z7, i8, str2, i9, i10);
    }

    public static final String f(String str, String str2, String str3, boolean z7) {
        int a8;
        d7.k.e(str, "<this>");
        d7.k.e(str2, "oldValue");
        d7.k.e(str3, "newValue");
        int i8 = 0;
        int m8 = m.m(str, str2, 0, z7);
        if (m8 < 0) {
            return str;
        }
        int length = str2.length();
        a8 = g7.f.a(length, 1);
        int length2 = (str.length() - length) + str3.length();
        if (length2 >= 0) {
            StringBuilder sb = new StringBuilder(length2);
            do {
                sb.append((CharSequence) str, i8, m8);
                sb.append(str3);
                i8 = m8 + length;
                if (m8 >= str.length()) {
                    break;
                }
                m8 = m.m(str, str2, m8 + a8, z7);
            } while (m8 > 0);
            sb.append((CharSequence) str, i8, str.length());
            String sb2 = sb.toString();
            d7.k.d(sb2, "toString(...)");
            return sb2;
        }
        throw new OutOfMemoryError();
    }

    public static /* synthetic */ String g(String str, String str2, String str3, boolean z7, int i8, Object obj) {
        if ((i8 & 4) != 0) {
            z7 = false;
        }
        return f(str, str2, str3, z7);
    }

    public static final boolean h(String str, String str2, boolean z7) {
        d7.k.e(str, "<this>");
        d7.k.e(str2, "prefix");
        return !z7 ? str.startsWith(str2) : e(str, 0, str2, 0, str2.length(), z7);
    }

    public static /* synthetic */ boolean i(String str, String str2, boolean z7, int i8, Object obj) {
        if ((i8 & 2) != 0) {
            z7 = false;
        }
        return h(str, str2, z7);
    }
}
