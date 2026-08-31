package com.google.android.gms.common.api.internal;

import java.util.Collections;
import java.util.Map;
import java.util.WeakHashMap;
/* loaded from: classes.dex */
public final class j {

    /* renamed from: a  reason: collision with root package name */
    private final Map f5042a = Collections.synchronizedMap(new WeakHashMap());

    /* renamed from: b  reason: collision with root package name */
    private final Map f5043b = Collections.synchronizedMap(new WeakHashMap());

    /* JADX WARN: Removed duplicated region for block: B:13:0x0024  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private final void f(boolean r5, com.google.android.gms.common.api.Status r6) {
        /*
            r4 = this;
            java.util.Map r0 = r4.f5042a
            monitor-enter(r0)
            java.util.HashMap r1 = new java.util.HashMap     // Catch: java.lang.Throwable -> L77
            java.util.Map r2 = r4.f5042a     // Catch: java.lang.Throwable -> L77
            r1.<init>(r2)     // Catch: java.lang.Throwable -> L77
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L77
            java.util.Map r2 = r4.f5043b
            monitor-enter(r2)
            java.util.HashMap r0 = new java.util.HashMap     // Catch: java.lang.Throwable -> L74
            java.util.Map r3 = r4.f5043b     // Catch: java.lang.Throwable -> L74
            r0.<init>(r3)     // Catch: java.lang.Throwable -> L74
            monitor-exit(r2)     // Catch: java.lang.Throwable -> L74
            java.util.Set r1 = r1.entrySet()
            java.util.Iterator r1 = r1.iterator()
        L1e:
            boolean r2 = r1.hasNext()
            if (r2 == 0) goto L42
            java.lang.Object r2 = r1.next()
            java.util.Map$Entry r2 = (java.util.Map.Entry) r2
            if (r5 != 0) goto L39
            java.lang.Object r3 = r2.getValue()
            java.lang.Boolean r3 = (java.lang.Boolean) r3
            boolean r3 = r3.booleanValue()
            if (r3 != 0) goto L39
            goto L1e
        L39:
            java.lang.Object r5 = r2.getKey()
            androidx.appcompat.app.w.a(r5)
            r5 = 0
            throw r5
        L42:
            java.util.Set r0 = r0.entrySet()
            java.util.Iterator r0 = r0.iterator()
        L4a:
            boolean r1 = r0.hasNext()
            if (r1 == 0) goto L73
            java.lang.Object r1 = r0.next()
            java.util.Map$Entry r1 = (java.util.Map.Entry) r1
            if (r5 != 0) goto L64
            java.lang.Object r2 = r1.getValue()
            java.lang.Boolean r2 = (java.lang.Boolean) r2
            boolean r2 = r2.booleanValue()
            if (r2 == 0) goto L4a
        L64:
            java.lang.Object r1 = r1.getKey()
            f4.k r1 = (f4.k) r1
            i3.b r2 = new i3.b
            r2.<init>(r6)
            r1.d(r2)
            goto L4a
        L73:
            return
        L74:
            r5 = move-exception
            monitor-exit(r2)     // Catch: java.lang.Throwable -> L74
            throw r5
        L77:
            r5 = move-exception
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L77
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.common.api.internal.j.f(boolean, com.google.android.gms.common.api.Status):void");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void b(f4.k kVar, boolean z7) {
        this.f5043b.put(kVar, Boolean.valueOf(z7));
        kVar.a().a(new i(this, kVar));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Removed duplicated region for block: B:10:0x0018  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void c(int r4, java.lang.String r5) {
        /*
            r3 = this;
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r1 = "The connection to Google Play services was lost"
            r0.<init>(r1)
            r1 = 1
            if (r4 != r1) goto L10
            java.lang.String r4 = " due to service disconnection."
        Lc:
            r0.append(r4)
            goto L16
        L10:
            r2 = 3
            if (r4 != r2) goto L16
            java.lang.String r4 = " due to dead object exception."
            goto Lc
        L16:
            if (r5 == 0) goto L20
            java.lang.String r4 = " Last reason for disconnect: "
            r0.append(r4)
            r0.append(r5)
        L20:
            java.lang.String r4 = r0.toString()
            com.google.android.gms.common.api.Status r5 = new com.google.android.gms.common.api.Status
            r0 = 20
            r5.<init>(r0, r4)
            r3.f(r1, r5)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.common.api.internal.j.c(int, java.lang.String):void");
    }

    public final void d() {
        f(false, b.f4978p);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final boolean e() {
        return (this.f5042a.isEmpty() && this.f5043b.isEmpty()) ? false : true;
    }
}
