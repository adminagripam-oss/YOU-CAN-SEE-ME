package m7;

import k7.d0;
import k7.f0;
import k7.z;
import r6.k;
/* loaded from: classes.dex */
public abstract class p {

    /* loaded from: classes.dex */
    public static final class a extends v6.d {

        /* renamed from: h */
        Object f9691h;

        /* renamed from: i */
        Object f9692i;

        /* renamed from: j */
        /* synthetic */ Object f9693j;

        /* renamed from: k */
        int f9694k;

        a(t6.d dVar) {
            super(dVar);
        }

        @Override // v6.a
        public final Object l(Object obj) {
            this.f9693j = obj;
            this.f9694k |= Integer.MIN_VALUE;
            return p.a(null, null, this);
        }
    }

    /* loaded from: classes.dex */
    public static final class b extends d7.l implements c7.l {

        /* renamed from: f */
        final /* synthetic */ k7.j f9695f;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        b(k7.j jVar) {
            super(1);
            this.f9695f = jVar;
        }

        public final void b(Throwable th) {
            k7.j jVar = this.f9695f;
            k.a aVar = r6.k.f11363e;
            jVar.i(r6.k.a(r6.r.f11372a));
        }

        @Override // c7.l
        public /* bridge */ /* synthetic */ Object g(Object obj) {
            b((Throwable) obj);
            return r6.r.f11372a;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:45:0x0023  */
    /* JADX WARN: Removed duplicated region for block: B:53:0x003c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object a(m7.r r4, c7.a r5, t6.d r6) {
        /*
            boolean r0 = r6 instanceof m7.p.a
            if (r0 == 0) goto L13
            r0 = r6
            m7.p$a r0 = (m7.p.a) r0
            int r1 = r0.f9694k
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.f9694k = r1
            goto L18
        L13:
            m7.p$a r0 = new m7.p$a
            r0.<init>(r6)
        L18:
            java.lang.Object r6 = r0.f9693j
            java.lang.Object r1 = u6.b.c()
            int r2 = r0.f9694k
            r3 = 1
            if (r2 == 0) goto L3c
            if (r2 != r3) goto L34
            java.lang.Object r4 = r0.f9692i
            r5 = r4
            c7.a r5 = (c7.a) r5
            java.lang.Object r4 = r0.f9691h
            m7.r r4 = (m7.r) r4
            r6.l.b(r6)     // Catch: java.lang.Throwable -> L32
            goto L7a
        L32:
            r4 = move-exception
            goto L80
        L34:
            java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
            java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
            r4.<init>(r5)
            throw r4
        L3c:
            r6.l.b(r6)
            t6.g r6 = r0.getContext()
            k7.e1$b r2 = k7.e1.f8727b
            t6.g$b r6 = r6.c(r2)
            if (r6 != r4) goto L4d
            r6 = r3
            goto L4e
        L4d:
            r6 = 0
        L4e:
            if (r6 == 0) goto L84
            r0.f9691h = r4     // Catch: java.lang.Throwable -> L32
            r0.f9692i = r5     // Catch: java.lang.Throwable -> L32
            r0.f9694k = r3     // Catch: java.lang.Throwable -> L32
            k7.k r6 = new k7.k     // Catch: java.lang.Throwable -> L32
            t6.d r2 = u6.b.b(r0)     // Catch: java.lang.Throwable -> L32
            r6.<init>(r2, r3)     // Catch: java.lang.Throwable -> L32
            r6.z()     // Catch: java.lang.Throwable -> L32
            m7.p$b r2 = new m7.p$b     // Catch: java.lang.Throwable -> L32
            r2.<init>(r6)     // Catch: java.lang.Throwable -> L32
            r4.a(r2)     // Catch: java.lang.Throwable -> L32
            java.lang.Object r4 = r6.w()     // Catch: java.lang.Throwable -> L32
            java.lang.Object r6 = u6.b.c()     // Catch: java.lang.Throwable -> L32
            if (r4 != r6) goto L77
            v6.h.c(r0)     // Catch: java.lang.Throwable -> L32
        L77:
            if (r4 != r1) goto L7a
            return r1
        L7a:
            r5.a()
            r6.r r4 = r6.r.f11372a
            return r4
        L80:
            r5.a()
            throw r4
        L84:
            java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
            java.lang.String r5 = "awaitClose() can only be invoked from the producer context"
            java.lang.String r5 = r5.toString()
            r4.<init>(r5)
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: m7.p.a(m7.r, c7.a, t6.d):java.lang.Object");
    }

    public static final s b(d0 d0Var, t6.g gVar, int i8, m7.a aVar, f0 f0Var, c7.l lVar, c7.p pVar) {
        q qVar = new q(z.d(d0Var, gVar), g.b(i8, aVar, null, 4, null));
        if (lVar != null) {
            qVar.b0(lVar);
        }
        qVar.G0(f0Var, qVar, pVar);
        return qVar;
    }

    public static /* synthetic */ s c(d0 d0Var, t6.g gVar, int i8, m7.a aVar, f0 f0Var, c7.l lVar, c7.p pVar, int i9, Object obj) {
        if ((i9 & 1) != 0) {
            gVar = t6.h.f11916e;
        }
        t6.g gVar2 = gVar;
        if ((i9 & 2) != 0) {
            i8 = 0;
        }
        int i10 = i8;
        if ((i9 & 4) != 0) {
            aVar = m7.a.SUSPEND;
        }
        m7.a aVar2 = aVar;
        if ((i9 & 8) != 0) {
            f0Var = f0.DEFAULT;
        }
        f0 f0Var2 = f0Var;
        if ((i9 & 16) != 0) {
            lVar = null;
        }
        return b(d0Var, gVar2, i10, aVar2, f0Var2, lVar, pVar);
    }
}
