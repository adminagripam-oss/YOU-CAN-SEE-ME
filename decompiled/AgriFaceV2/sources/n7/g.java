package n7;

import m7.s;
import r6.r;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public abstract /* synthetic */ class g {

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static final class a extends v6.d {

        /* renamed from: h  reason: collision with root package name */
        Object f9775h;

        /* renamed from: i  reason: collision with root package name */
        Object f9776i;

        /* renamed from: j  reason: collision with root package name */
        Object f9777j;

        /* renamed from: k  reason: collision with root package name */
        boolean f9778k;

        /* renamed from: l  reason: collision with root package name */
        /* synthetic */ Object f9779l;

        /* renamed from: m  reason: collision with root package name */
        int f9780m;

        a(t6.d dVar) {
            super(dVar);
        }

        @Override // v6.a
        public final Object l(Object obj) {
            this.f9779l = obj;
            this.f9780m |= Integer.MIN_VALUE;
            return g.c(null, null, false, this);
        }
    }

    public static final Object b(d dVar, s sVar, t6.d dVar2) {
        Object c8;
        Object c9 = c(dVar, sVar, true, dVar2);
        c8 = u6.d.c();
        return c9 == c8 ? c9 : r.f11372a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:10:0x0024  */
    /* JADX WARN: Removed duplicated region for block: B:20:0x0056  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x0070 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:25:0x0071  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x007d A[Catch: all -> 0x009b, TRY_LEAVE, TryCatch #0 {all -> 0x009b, blocks: (B:13:0x0036, B:22:0x0060, B:26:0x0075, B:28:0x007d, B:18:0x0052, B:21:0x005c), top: B:41:0x0022 }] */
    /* JADX WARN: Removed duplicated region for block: B:31:0x0092  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:29:0x008f -> B:14:0x0039). Please submit an issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object c(n7.d r6, m7.s r7, boolean r8, t6.d r9) {
        /*
            boolean r0 = r9 instanceof n7.g.a
            if (r0 == 0) goto L13
            r0 = r9
            n7.g$a r0 = (n7.g.a) r0
            int r1 = r0.f9780m
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.f9780m = r1
            goto L18
        L13:
            n7.g$a r0 = new n7.g$a
            r0.<init>(r9)
        L18:
            java.lang.Object r9 = r0.f9779l
            java.lang.Object r1 = u6.b.c()
            int r2 = r0.f9780m
            r3 = 2
            r4 = 1
            if (r2 == 0) goto L56
            if (r2 == r4) goto L44
            if (r2 != r3) goto L3c
            boolean r8 = r0.f9778k
            java.lang.Object r6 = r0.f9777j
            m7.f r6 = (m7.f) r6
            java.lang.Object r7 = r0.f9776i
            m7.s r7 = (m7.s) r7
            java.lang.Object r2 = r0.f9775h
            n7.d r2 = (n7.d) r2
            r6.l.b(r9)     // Catch: java.lang.Throwable -> L9b
        L39:
            r9 = r6
            r6 = r2
            goto L60
        L3c:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r7)
            throw r6
        L44:
            boolean r8 = r0.f9778k
            java.lang.Object r6 = r0.f9777j
            m7.f r6 = (m7.f) r6
            java.lang.Object r7 = r0.f9776i
            m7.s r7 = (m7.s) r7
            java.lang.Object r2 = r0.f9775h
            n7.d r2 = (n7.d) r2
            r6.l.b(r9)     // Catch: java.lang.Throwable -> L9b
            goto L75
        L56:
            r6.l.b(r9)
            n7.e.c(r6)
            m7.f r9 = r7.iterator()     // Catch: java.lang.Throwable -> L9b
        L60:
            r0.f9775h = r6     // Catch: java.lang.Throwable -> L9b
            r0.f9776i = r7     // Catch: java.lang.Throwable -> L9b
            r0.f9777j = r9     // Catch: java.lang.Throwable -> L9b
            r0.f9778k = r8     // Catch: java.lang.Throwable -> L9b
            r0.f9780m = r4     // Catch: java.lang.Throwable -> L9b
            java.lang.Object r2 = r9.b(r0)     // Catch: java.lang.Throwable -> L9b
            if (r2 != r1) goto L71
            return r1
        L71:
            r5 = r2
            r2 = r6
            r6 = r9
            r9 = r5
        L75:
            java.lang.Boolean r9 = (java.lang.Boolean) r9     // Catch: java.lang.Throwable -> L9b
            boolean r9 = r9.booleanValue()     // Catch: java.lang.Throwable -> L9b
            if (r9 == 0) goto L92
            java.lang.Object r9 = r6.next()     // Catch: java.lang.Throwable -> L9b
            r0.f9775h = r2     // Catch: java.lang.Throwable -> L9b
            r0.f9776i = r7     // Catch: java.lang.Throwable -> L9b
            r0.f9777j = r6     // Catch: java.lang.Throwable -> L9b
            r0.f9778k = r8     // Catch: java.lang.Throwable -> L9b
            r0.f9780m = r3     // Catch: java.lang.Throwable -> L9b
            java.lang.Object r9 = r2.c(r9, r0)     // Catch: java.lang.Throwable -> L9b
            if (r9 != r1) goto L39
            return r1
        L92:
            if (r8 == 0) goto L98
            r6 = 0
            m7.k.a(r7, r6)
        L98:
            r6.r r6 = r6.r.f11372a
            return r6
        L9b:
            r6 = move-exception
            throw r6     // Catch: java.lang.Throwable -> L9d
        L9d:
            r9 = move-exception
            if (r8 == 0) goto La3
            m7.k.a(r7, r6)
        La3:
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: n7.g.c(n7.d, m7.s, boolean, t6.d):java.lang.Object");
    }
}
