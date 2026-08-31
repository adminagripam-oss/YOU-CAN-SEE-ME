package t4;

import java.security.GeneralSecurityException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.logging.Logger;
import o4.b;
/* loaded from: classes.dex */
public final class e {

    /* renamed from: c  reason: collision with root package name */
    private static final Logger f11833c = Logger.getLogger(e.class.getName());

    /* renamed from: d  reason: collision with root package name */
    private static final e f11834d = new e();

    /* renamed from: a  reason: collision with root package name */
    private ConcurrentMap f11835a = new ConcurrentHashMap();

    /* renamed from: b  reason: collision with root package name */
    private ConcurrentMap f11836b = new ConcurrentHashMap();

    private synchronized j4.i b(String str) {
        if (!this.f11835a.containsKey(str)) {
            throw new GeneralSecurityException("No key manager found for key type " + str + ", see https://developers.google.com/tink/faq/registration_errors");
        }
        return (j4.i) this.f11835a.get(str);
    }

    public static e d() {
        return f11834d;
    }

    /* JADX WARN: Code restructure failed: missing block: B:20:0x008e, code lost:
        r4.f11835a.putIfAbsent(r0, r5);
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private synchronized void e(j4.i r5, boolean r6, boolean r7) {
        /*
            r4 = this;
            monitor-enter(r4)
            java.lang.String r0 = r5.d()     // Catch: java.lang.Throwable -> La4
            if (r7 == 0) goto L35
            java.util.concurrent.ConcurrentMap r1 = r4.f11836b     // Catch: java.lang.Throwable -> La4
            boolean r1 = r1.containsKey(r0)     // Catch: java.lang.Throwable -> La4
            if (r1 == 0) goto L35
            java.util.concurrent.ConcurrentMap r1 = r4.f11836b     // Catch: java.lang.Throwable -> La4
            java.lang.Object r1 = r1.get(r0)     // Catch: java.lang.Throwable -> La4
            java.lang.Boolean r1 = (java.lang.Boolean) r1     // Catch: java.lang.Throwable -> La4
            boolean r1 = r1.booleanValue()     // Catch: java.lang.Throwable -> La4
            if (r1 == 0) goto L1e
            goto L35
        L1e:
            java.security.GeneralSecurityException r5 = new java.security.GeneralSecurityException     // Catch: java.lang.Throwable -> La4
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> La4
            r6.<init>()     // Catch: java.lang.Throwable -> La4
            java.lang.String r7 = "New keys are already disallowed for key type "
            r6.append(r7)     // Catch: java.lang.Throwable -> La4
            r6.append(r0)     // Catch: java.lang.Throwable -> La4
            java.lang.String r6 = r6.toString()     // Catch: java.lang.Throwable -> La4
            r5.<init>(r6)     // Catch: java.lang.Throwable -> La4
            throw r5     // Catch: java.lang.Throwable -> La4
        L35:
            java.util.concurrent.ConcurrentMap r1 = r4.f11835a     // Catch: java.lang.Throwable -> La4
            java.lang.Object r1 = r1.get(r0)     // Catch: java.lang.Throwable -> La4
            j4.i r1 = (j4.i) r1     // Catch: java.lang.Throwable -> La4
            if (r1 == 0) goto L8c
            java.lang.Class r2 = r1.getClass()     // Catch: java.lang.Throwable -> La4
            java.lang.Class r3 = r5.getClass()     // Catch: java.lang.Throwable -> La4
            boolean r2 = r2.equals(r3)     // Catch: java.lang.Throwable -> La4
            if (r2 == 0) goto L4e
            goto L8c
        L4e:
            java.util.logging.Logger r6 = t4.e.f11833c     // Catch: java.lang.Throwable -> La4
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> La4
            r7.<init>()     // Catch: java.lang.Throwable -> La4
            java.lang.String r2 = "Attempted overwrite of a registered key manager for key type "
            r7.append(r2)     // Catch: java.lang.Throwable -> La4
            r7.append(r0)     // Catch: java.lang.Throwable -> La4
            java.lang.String r7 = r7.toString()     // Catch: java.lang.Throwable -> La4
            r6.warning(r7)     // Catch: java.lang.Throwable -> La4
            java.security.GeneralSecurityException r6 = new java.security.GeneralSecurityException     // Catch: java.lang.Throwable -> La4
            java.lang.String r7 = "typeUrl (%s) is already registered with %s, cannot be re-registered with %s"
            r2 = 3
            java.lang.Object[] r2 = new java.lang.Object[r2]     // Catch: java.lang.Throwable -> La4
            r3 = 0
            r2[r3] = r0     // Catch: java.lang.Throwable -> La4
            java.lang.Class r0 = r1.getClass()     // Catch: java.lang.Throwable -> La4
            java.lang.String r0 = r0.getName()     // Catch: java.lang.Throwable -> La4
            r1 = 1
            r2[r1] = r0     // Catch: java.lang.Throwable -> La4
            java.lang.Class r5 = r5.getClass()     // Catch: java.lang.Throwable -> La4
            java.lang.String r5 = r5.getName()     // Catch: java.lang.Throwable -> La4
            r0 = 2
            r2[r0] = r5     // Catch: java.lang.Throwable -> La4
            java.lang.String r5 = java.lang.String.format(r7, r2)     // Catch: java.lang.Throwable -> La4
            r6.<init>(r5)     // Catch: java.lang.Throwable -> La4
            throw r6     // Catch: java.lang.Throwable -> La4
        L8c:
            if (r6 != 0) goto L94
            java.util.concurrent.ConcurrentMap r6 = r4.f11835a     // Catch: java.lang.Throwable -> La4
            r6.putIfAbsent(r0, r5)     // Catch: java.lang.Throwable -> La4
            goto L99
        L94:
            java.util.concurrent.ConcurrentMap r6 = r4.f11835a     // Catch: java.lang.Throwable -> La4
            r6.put(r0, r5)     // Catch: java.lang.Throwable -> La4
        L99:
            java.util.concurrent.ConcurrentMap r5 = r4.f11836b     // Catch: java.lang.Throwable -> La4
            java.lang.Boolean r6 = java.lang.Boolean.valueOf(r7)     // Catch: java.lang.Throwable -> La4
            r5.put(r0, r6)     // Catch: java.lang.Throwable -> La4
            monitor-exit(r4)
            return
        La4:
            r5 = move-exception
            monitor-exit(r4)
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: t4.e.e(j4.i, boolean, boolean):void");
    }

    public j4.i a(String str, Class cls) {
        j4.i b8 = b(str);
        if (b8.a().equals(cls)) {
            return b8;
        }
        throw new GeneralSecurityException("Primitive type " + cls.getName() + " not supported by key manager of type " + b8.getClass() + ", which only supports: " + b8.a());
    }

    public j4.i c(String str) {
        return b(str);
    }

    public boolean f(String str) {
        return ((Boolean) this.f11836b.get(str)).booleanValue();
    }

    public synchronized void g(j4.i iVar, boolean z7) {
        h(iVar, b.EnumC0135b.f9848e, z7);
    }

    public synchronized void h(j4.i iVar, b.EnumC0135b enumC0135b, boolean z7) {
        if (!enumC0135b.e()) {
            throw new GeneralSecurityException("Cannot register key manager: FIPS compatibility insufficient");
        }
        e(iVar, false, z7);
    }
}
