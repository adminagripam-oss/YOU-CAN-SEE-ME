package com.google.crypto.tink.shaded.protobuf;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public abstract class r0 {

    /* renamed from: a  reason: collision with root package name */
    private static final char[] f6209a;

    static {
        char[] cArr = new char[80];
        f6209a = cArr;
        Arrays.fill(cArr, ' ');
    }

    private static void a(int i8, StringBuilder sb) {
        while (i8 > 0) {
            char[] cArr = f6209a;
            int length = i8 > cArr.length ? cArr.length : i8;
            sb.append(cArr, 0, length);
            i8 -= length;
        }
    }

    private static boolean b(Object obj) {
        Object obj2;
        if (obj instanceof Boolean) {
            return !((Boolean) obj).booleanValue();
        }
        if (obj instanceof Integer) {
            return ((Integer) obj).intValue() == 0;
        } else if (obj instanceof Float) {
            return Float.floatToRawIntBits(((Float) obj).floatValue()) == 0;
        } else if (obj instanceof Double) {
            return Double.doubleToRawLongBits(((Double) obj).doubleValue()) == 0;
        } else {
            if (obj instanceof String) {
                obj2 = "";
            } else if (!(obj instanceof h)) {
                return obj instanceof p0 ? obj == ((p0) obj).c() : (obj instanceof Enum) && ((Enum) obj).ordinal() == 0;
            } else {
                obj2 = h.f6072f;
            }
            return obj.equals(obj2);
        }
    }

    private static String c(String str) {
        if (str.isEmpty()) {
            return str;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(Character.toLowerCase(str.charAt(0)));
        for (int i8 = 1; i8 < str.length(); i8++) {
            char charAt = str.charAt(i8);
            if (Character.isUpperCase(charAt)) {
                sb.append("_");
            }
            sb.append(Character.toLowerCase(charAt));
        }
        return sb.toString();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void d(StringBuilder sb, int i8, String str, Object obj) {
        String a8;
        if (obj instanceof List) {
            for (Object obj2 : (List) obj) {
                d(sb, i8, str, obj2);
            }
        } else if (obj instanceof Map) {
            for (Map.Entry entry : ((Map) obj).entrySet()) {
                d(sb, i8, str, entry);
            }
        } else {
            sb.append('\n');
            a(i8, sb);
            sb.append(c(str));
            if (obj instanceof String) {
                sb.append(": \"");
                a8 = h1.c((String) obj);
            } else if (!(obj instanceof h)) {
                if (obj instanceof x) {
                    sb.append(" {");
                    e((x) obj, sb, i8 + 2);
                } else if (!(obj instanceof Map.Entry)) {
                    sb.append(": ");
                    sb.append(obj);
                    return;
                } else {
                    sb.append(" {");
                    Map.Entry entry2 = (Map.Entry) obj;
                    int i9 = i8 + 2;
                    d(sb, i9, "key", entry2.getKey());
                    d(sb, i9, "value", entry2.getValue());
                }
                sb.append("\n");
                a(i8, sb);
                sb.append("}");
                return;
            } else {
                sb.append(": \"");
                a8 = h1.a((h) obj);
            }
            sb.append(a8);
            sb.append('\"');
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:60:0x016f, code lost:
        if (r5.containsKey("get" + r9.substring(0, r9.length() - 5)) != false) goto L58;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static void e(com.google.crypto.tink.shaded.protobuf.p0 r16, java.lang.StringBuilder r17, int r18) {
        /*
            Method dump skipped, instructions count: 445
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.crypto.tink.shaded.protobuf.r0.e(com.google.crypto.tink.shaded.protobuf.p0, java.lang.StringBuilder, int):void");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String f(p0 p0Var, String str) {
        StringBuilder sb = new StringBuilder();
        sb.append("# ");
        sb.append(str);
        e(p0Var, sb, 0);
        return sb.toString();
    }
}
