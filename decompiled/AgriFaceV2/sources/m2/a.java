package m2;

import d7.k;
import h7.b;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
/* loaded from: classes.dex */
public final class a {

    /* renamed from: a  reason: collision with root package name */
    public static final a f9130a = new a();

    private a() {
    }

    /* JADX WARN: Code restructure failed: missing block: B:12:0x0034, code lost:
        if (r4 != null) goto L14;
     */
    /* JADX WARN: Code restructure failed: missing block: B:13:0x0036, code lost:
        r4 = "";
     */
    /* JADX WARN: Code restructure failed: missing block: B:14:0x0037, code lost:
        r5.append(r4);
        android.util.Log.e("ReflectionGuard", r5.toString());
     */
    /* JADX WARN: Code restructure failed: missing block: B:15:0x0041, code lost:
        return false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:9:0x0027, code lost:
        if (r4 != null) goto L14;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final boolean e(java.lang.String r4, c7.a r5) {
        /*
            java.lang.String r0 = ""
            java.lang.String r1 = "ReflectionGuard"
            java.lang.String r2 = "block"
            d7.k.e(r5, r2)
            r2 = 0
            java.lang.Object r5 = r5.a()     // Catch: java.lang.NoSuchMethodException -> L1d java.lang.ClassNotFoundException -> L2a
            java.lang.Boolean r5 = (java.lang.Boolean) r5     // Catch: java.lang.NoSuchMethodException -> L1d java.lang.ClassNotFoundException -> L2a
            boolean r5 = r5.booleanValue()     // Catch: java.lang.NoSuchMethodException -> L1d java.lang.ClassNotFoundException -> L2a
            if (r5 != 0) goto L1b
            if (r4 == 0) goto L1b
            android.util.Log.e(r1, r4)     // Catch: java.lang.NoSuchMethodException -> L1d java.lang.ClassNotFoundException -> L2a
        L1b:
            r2 = r5
            goto L41
        L1d:
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            java.lang.String r3 = "NoSuchMethod: "
            r5.append(r3)
            if (r4 != 0) goto L37
            goto L36
        L2a:
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            java.lang.String r3 = "ClassNotFound: "
            r5.append(r3)
            if (r4 != 0) goto L37
        L36:
            r4 = r0
        L37:
            r5.append(r4)
            java.lang.String r4 = r5.toString()
            android.util.Log.e(r1, r4)
        L41:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: m2.a.e(java.lang.String, c7.a):boolean");
    }

    public final boolean a(c7.a aVar) {
        k.e(aVar, "classLoader");
        try {
            aVar.a();
            return true;
        } catch (ClassNotFoundException | NoClassDefFoundError unused) {
            return false;
        }
    }

    public final boolean b(Method method, b bVar) {
        k.e(method, "<this>");
        k.e(bVar, "clazz");
        return c(method, b7.a.a(bVar));
    }

    public final boolean c(Method method, Class cls) {
        k.e(method, "<this>");
        k.e(cls, "clazz");
        return method.getReturnType().equals(cls);
    }

    public final boolean d(Method method) {
        k.e(method, "<this>");
        return Modifier.isPublic(method.getModifiers());
    }
}
