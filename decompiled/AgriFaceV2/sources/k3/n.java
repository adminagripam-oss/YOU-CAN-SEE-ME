package k3;

import java.util.concurrent.ConcurrentHashMap;
/* loaded from: classes.dex */
public class n {

    /* renamed from: b  reason: collision with root package name */
    private static final j f8448b = new j("LibraryVersion", "");

    /* renamed from: c  reason: collision with root package name */
    private static final n f8449c = new n();

    /* renamed from: a  reason: collision with root package name */
    private final ConcurrentHashMap f8450a = new ConcurrentHashMap();

    protected n() {
    }

    public static n a() {
        return f8449c;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:23:0x008f  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x0094  */
    /* JADX WARN: Type inference failed for: r3v11 */
    /* JADX WARN: Type inference failed for: r3v14 */
    /* JADX WARN: Type inference failed for: r3v15 */
    /* JADX WARN: Type inference failed for: r3v16 */
    /* JADX WARN: Type inference failed for: r3v5 */
    /* JADX WARN: Type inference failed for: r3v6, types: [java.lang.Object, java.lang.String] */
    /* JADX WARN: Type inference failed for: r3v7, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r3v9 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.String b(java.lang.String r9) {
        /*
            r8 = this;
            java.lang.String r0 = "Failed to get app version for libraryName: "
            java.lang.String r1 = "LibraryVersion"
            java.lang.String r2 = "Please provide a valid libraryName"
            k3.q.f(r9, r2)
            java.util.concurrent.ConcurrentHashMap r2 = r8.f8450a
            boolean r2 = r2.containsKey(r9)
            if (r2 == 0) goto L1a
            java.util.concurrent.ConcurrentHashMap r0 = r8.f8450a
            java.lang.Object r9 = r0.get(r9)
            java.lang.String r9 = (java.lang.String) r9
            return r9
        L1a:
            java.util.Properties r2 = new java.util.Properties
            r2.<init>()
            r3 = 0
            java.lang.String r4 = "/%s.properties"
            java.lang.Object[] r5 = new java.lang.Object[]{r9}     // Catch: java.lang.Throwable -> L72 java.io.IOException -> L74
            java.lang.String r4 = java.lang.String.format(r4, r5)     // Catch: java.lang.Throwable -> L72 java.io.IOException -> L74
            java.lang.Class<k3.n> r5 = k3.n.class
            java.io.InputStream r4 = r5.getResourceAsStream(r4)     // Catch: java.lang.Throwable -> L72 java.io.IOException -> L74
            if (r4 == 0) goto L55
            r2.load(r4)     // Catch: java.lang.Throwable -> L6a java.io.IOException -> L6d
            java.lang.String r5 = "version"
            java.lang.String r3 = r2.getProperty(r5, r3)     // Catch: java.lang.Throwable -> L6a java.io.IOException -> L6d
            k3.j r2 = k3.n.f8448b     // Catch: java.lang.Throwable -> L6a java.io.IOException -> L6d
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L6a java.io.IOException -> L6d
            r5.<init>()     // Catch: java.lang.Throwable -> L6a java.io.IOException -> L6d
            r5.append(r9)     // Catch: java.lang.Throwable -> L6a java.io.IOException -> L6d
            java.lang.String r6 = " version is "
            r5.append(r6)     // Catch: java.lang.Throwable -> L6a java.io.IOException -> L6d
            r5.append(r3)     // Catch: java.lang.Throwable -> L6a java.io.IOException -> L6d
            java.lang.String r5 = r5.toString()     // Catch: java.lang.Throwable -> L6a java.io.IOException -> L6d
            r2.e(r1, r5)     // Catch: java.lang.Throwable -> L6a java.io.IOException -> L6d
            goto L8d
        L55:
            k3.j r2 = k3.n.f8448b     // Catch: java.lang.Throwable -> L6a java.io.IOException -> L6d
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L6a java.io.IOException -> L6d
            r5.<init>()     // Catch: java.lang.Throwable -> L6a java.io.IOException -> L6d
            r5.append(r0)     // Catch: java.lang.Throwable -> L6a java.io.IOException -> L6d
            r5.append(r9)     // Catch: java.lang.Throwable -> L6a java.io.IOException -> L6d
            java.lang.String r5 = r5.toString()     // Catch: java.lang.Throwable -> L6a java.io.IOException -> L6d
            r2.f(r1, r5)     // Catch: java.lang.Throwable -> L6a java.io.IOException -> L6d
            goto L8d
        L6a:
            r9 = move-exception
            r3 = r4
            goto La3
        L6d:
            r2 = move-exception
            r7 = r4
            r4 = r3
            r3 = r7
            goto L76
        L72:
            r9 = move-exception
            goto La3
        L74:
            r2 = move-exception
            r4 = r3
        L76:
            k3.j r5 = k3.n.f8448b     // Catch: java.lang.Throwable -> L72
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L72
            r6.<init>()     // Catch: java.lang.Throwable -> L72
            r6.append(r0)     // Catch: java.lang.Throwable -> L72
            r6.append(r9)     // Catch: java.lang.Throwable -> L72
            java.lang.String r0 = r6.toString()     // Catch: java.lang.Throwable -> L72
            r5.d(r1, r0, r2)     // Catch: java.lang.Throwable -> L72
            r7 = r4
            r4 = r3
            r3 = r7
        L8d:
            if (r4 == 0) goto L92
            q3.f.a(r4)
        L92:
            if (r3 != 0) goto L9d
            k3.j r0 = k3.n.f8448b
            java.lang.String r2 = ".properties file is dropped during release process. Failure to read app version is expected during Google internal testing where locally-built libraries are used"
            r0.b(r1, r2)
            java.lang.String r3 = "UNKNOWN"
        L9d:
            java.util.concurrent.ConcurrentHashMap r0 = r8.f8450a
            r0.put(r9, r3)
            return r3
        La3:
            if (r3 == 0) goto La8
            q3.f.a(r3)
        La8:
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: k3.n.b(java.lang.String):java.lang.String");
    }
}
