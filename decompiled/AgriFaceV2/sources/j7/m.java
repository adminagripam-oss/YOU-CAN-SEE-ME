package j7;

import s6.a0;
/* loaded from: classes.dex */
public abstract class m extends l {
    public static final String A(String str, char c8, String str2) {
        d7.k.e(str, "<this>");
        d7.k.e(str2, "missingDelimiterValue");
        int t8 = t(str, c8, 0, false, 6, null);
        if (t8 == -1) {
            return str2;
        }
        String substring = str.substring(t8 + 1, str.length());
        d7.k.d(substring, "substring(...)");
        return substring;
    }

    public static /* synthetic */ String B(String str, char c8, String str2, int i8, Object obj) {
        if ((i8 & 2) != 0) {
            str2 = str;
        }
        return A(str, c8, str2);
    }

    public static final String C(String str, char c8, String str2) {
        d7.k.e(str, "<this>");
        d7.k.e(str2, "missingDelimiterValue");
        int p8 = p(str, c8, 0, false, 6, null);
        if (p8 == -1) {
            return str2;
        }
        String substring = str.substring(0, p8);
        d7.k.d(substring, "substring(...)");
        return substring;
    }

    public static final String D(String str, String str2, String str3) {
        d7.k.e(str, "<this>");
        d7.k.e(str2, "delimiter");
        d7.k.e(str3, "missingDelimiterValue");
        int q8 = q(str, str2, 0, false, 6, null);
        if (q8 == -1) {
            return str3;
        }
        String substring = str.substring(0, q8);
        d7.k.d(substring, "substring(...)");
        return substring;
    }

    public static /* synthetic */ String E(String str, char c8, String str2, int i8, Object obj) {
        if ((i8 & 2) != 0) {
            str2 = str;
        }
        return C(str, c8, str2);
    }

    public static /* synthetic */ String F(String str, String str2, String str3, int i8, Object obj) {
        if ((i8 & 2) != 0) {
            str3 = str;
        }
        return D(str, str2, str3);
    }

    public static CharSequence G(CharSequence charSequence) {
        d7.k.e(charSequence, "<this>");
        int length = charSequence.length() - 1;
        int i8 = 0;
        boolean z7 = false;
        while (i8 <= length) {
            boolean c8 = a.c(charSequence.charAt(!z7 ? i8 : length));
            if (z7) {
                if (!c8) {
                    break;
                }
                length--;
            } else if (c8) {
                i8++;
            } else {
                z7 = true;
            }
        }
        return charSequence.subSequence(i8, length + 1);
    }

    public static final g7.c j(CharSequence charSequence) {
        d7.k.e(charSequence, "<this>");
        return new g7.c(0, charSequence.length() - 1);
    }

    public static final int k(CharSequence charSequence) {
        d7.k.e(charSequence, "<this>");
        return charSequence.length() - 1;
    }

    public static final int l(CharSequence charSequence, char c8, int i8, boolean z7) {
        d7.k.e(charSequence, "<this>");
        return (z7 || !(charSequence instanceof String)) ? r(charSequence, new char[]{c8}, i8, z7) : ((String) charSequence).indexOf(c8, i8);
    }

    public static final int m(CharSequence charSequence, String str, int i8, boolean z7) {
        d7.k.e(charSequence, "<this>");
        d7.k.e(str, "string");
        return (z7 || !(charSequence instanceof String)) ? o(charSequence, str, i8, charSequence.length(), z7, false, 16, null) : ((String) charSequence).indexOf(str, i8);
    }

    private static final int n(CharSequence charSequence, CharSequence charSequence2, int i8, int i9, boolean z7, boolean z8) {
        int b8;
        int a8;
        g7.a d8;
        int a9;
        int b9;
        if (z8) {
            b8 = g7.f.b(i8, k(charSequence));
            a8 = g7.f.a(i9, 0);
            d8 = g7.f.d(b8, a8);
        } else {
            a9 = g7.f.a(i8, 0);
            b9 = g7.f.b(i9, charSequence.length());
            d8 = new g7.c(a9, b9);
        }
        if ((charSequence instanceof String) && (charSequence2 instanceof String)) {
            int b10 = d8.b();
            int f8 = d8.f();
            int j8 = d8.j();
            if ((j8 <= 0 || b10 > f8) && (j8 >= 0 || f8 > b10)) {
                return -1;
            }
            while (!l.e((String) charSequence2, 0, (String) charSequence, b10, charSequence2.length(), z7)) {
                if (b10 == f8) {
                    return -1;
                }
                b10 += j8;
            }
            return b10;
        }
        int b11 = d8.b();
        int f9 = d8.f();
        int j9 = d8.j();
        if ((j9 <= 0 || b11 > f9) && (j9 >= 0 || f9 > b11)) {
            return -1;
        }
        while (!v(charSequence2, 0, charSequence, b11, charSequence2.length(), z7)) {
            if (b11 == f9) {
                return -1;
            }
            b11 += j9;
        }
        return b11;
    }

    static /* synthetic */ int o(CharSequence charSequence, CharSequence charSequence2, int i8, int i9, boolean z7, boolean z8, int i10, Object obj) {
        if ((i10 & 16) != 0) {
            z8 = false;
        }
        return n(charSequence, charSequence2, i8, i9, z7, z8);
    }

    public static /* synthetic */ int p(CharSequence charSequence, char c8, int i8, boolean z7, int i9, Object obj) {
        if ((i9 & 2) != 0) {
            i8 = 0;
        }
        if ((i9 & 4) != 0) {
            z7 = false;
        }
        return l(charSequence, c8, i8, z7);
    }

    public static /* synthetic */ int q(CharSequence charSequence, String str, int i8, boolean z7, int i9, Object obj) {
        if ((i9 & 2) != 0) {
            i8 = 0;
        }
        if ((i9 & 4) != 0) {
            z7 = false;
        }
        return m(charSequence, str, i8, z7);
    }

    public static final int r(CharSequence charSequence, char[] cArr, int i8, boolean z7) {
        int a8;
        boolean z8;
        char m8;
        d7.k.e(charSequence, "<this>");
        d7.k.e(cArr, "chars");
        if (!z7 && cArr.length == 1 && (charSequence instanceof String)) {
            m8 = s6.j.m(cArr);
            return ((String) charSequence).indexOf(m8, i8);
        }
        a8 = g7.f.a(i8, 0);
        a0 it = new g7.c(a8, k(charSequence)).iterator();
        while (it.hasNext()) {
            int b8 = it.b();
            char charAt = charSequence.charAt(b8);
            int length = cArr.length;
            int i9 = 0;
            while (true) {
                if (i9 >= length) {
                    z8 = false;
                    continue;
                    break;
                } else if (b.d(cArr[i9], charAt, z7)) {
                    z8 = true;
                    continue;
                    break;
                } else {
                    i9++;
                }
            }
            if (z8) {
                return b8;
            }
        }
        return -1;
    }

    public static final int s(CharSequence charSequence, char c8, int i8, boolean z7) {
        d7.k.e(charSequence, "<this>");
        return (z7 || !(charSequence instanceof String)) ? u(charSequence, new char[]{c8}, i8, z7) : ((String) charSequence).lastIndexOf(c8, i8);
    }

    public static /* synthetic */ int t(CharSequence charSequence, char c8, int i8, boolean z7, int i9, Object obj) {
        if ((i9 & 2) != 0) {
            i8 = k(charSequence);
        }
        if ((i9 & 4) != 0) {
            z7 = false;
        }
        return s(charSequence, c8, i8, z7);
    }

    public static final int u(CharSequence charSequence, char[] cArr, int i8, boolean z7) {
        int b8;
        char m8;
        d7.k.e(charSequence, "<this>");
        d7.k.e(cArr, "chars");
        if (!z7 && cArr.length == 1 && (charSequence instanceof String)) {
            m8 = s6.j.m(cArr);
            return ((String) charSequence).lastIndexOf(m8, i8);
        }
        for (b8 = g7.f.b(i8, k(charSequence)); -1 < b8; b8--) {
            char charAt = charSequence.charAt(b8);
            int length = cArr.length;
            boolean z8 = false;
            int i9 = 0;
            while (true) {
                if (i9 >= length) {
                    break;
                } else if (b.d(cArr[i9], charAt, z7)) {
                    z8 = true;
                    break;
                } else {
                    i9++;
                }
            }
            if (z8) {
                return b8;
            }
        }
        return -1;
    }

    public static final boolean v(CharSequence charSequence, int i8, CharSequence charSequence2, int i9, int i10, boolean z7) {
        d7.k.e(charSequence, "<this>");
        d7.k.e(charSequence2, "other");
        if (i9 < 0 || i8 < 0 || i8 > charSequence.length() - i10 || i9 > charSequence2.length() - i10) {
            return false;
        }
        for (int i11 = 0; i11 < i10; i11++) {
            if (!b.d(charSequence.charAt(i8 + i11), charSequence2.charAt(i9 + i11), z7)) {
                return false;
            }
        }
        return true;
    }

    public static final String w(String str, char c8, String str2) {
        d7.k.e(str, "<this>");
        d7.k.e(str2, "missingDelimiterValue");
        int p8 = p(str, c8, 0, false, 6, null);
        if (p8 == -1) {
            return str2;
        }
        String substring = str.substring(p8 + 1, str.length());
        d7.k.d(substring, "substring(...)");
        return substring;
    }

    public static final String x(String str, String str2, String str3) {
        d7.k.e(str, "<this>");
        d7.k.e(str2, "delimiter");
        d7.k.e(str3, "missingDelimiterValue");
        int q8 = q(str, str2, 0, false, 6, null);
        if (q8 == -1) {
            return str3;
        }
        String substring = str.substring(q8 + str2.length(), str.length());
        d7.k.d(substring, "substring(...)");
        return substring;
    }

    public static /* synthetic */ String y(String str, char c8, String str2, int i8, Object obj) {
        if ((i8 & 2) != 0) {
            str2 = str;
        }
        return w(str, c8, str2);
    }

    public static /* synthetic */ String z(String str, String str2, String str3, int i8, Object obj) {
        if ((i8 & 2) != 0) {
            str3 = str;
        }
        return x(str, str2, str3);
    }
}
