package v6;

import java.lang.reflect.Field;
/* loaded from: classes.dex */
public abstract class g {
    private static final void a(int i8, int i9) {
        if (i9 <= i8) {
            return;
        }
        throw new IllegalStateException(("Debug metadata version mismatch. Expected: " + i8 + ", got " + i9 + ". Please update the Kotlin standard library.").toString());
    }

    private static final f b(a aVar) {
        return (f) aVar.getClass().getAnnotation(f.class);
    }

    private static final int c(a aVar) {
        try {
            Field declaredField = aVar.getClass().getDeclaredField("label");
            declaredField.setAccessible(true);
            Object obj = declaredField.get(aVar);
            Integer num = obj instanceof Integer ? (Integer) obj : null;
            return (num != null ? num.intValue() : 0) - 1;
        } catch (Exception unused) {
            return -1;
        }
    }

    public static final StackTraceElement d(a aVar) {
        String str;
        d7.k.e(aVar, "<this>");
        f b8 = b(aVar);
        if (b8 == null) {
            return null;
        }
        a(1, b8.v());
        int c8 = c(aVar);
        int i8 = c8 < 0 ? -1 : b8.l()[c8];
        String b9 = i.f12435a.b(aVar);
        if (b9 == null) {
            str = b8.c();
        } else {
            str = b9 + '/' + b8.c();
        }
        return new StackTraceElement(str, b8.m(), b8.f(), i8);
    }
}
