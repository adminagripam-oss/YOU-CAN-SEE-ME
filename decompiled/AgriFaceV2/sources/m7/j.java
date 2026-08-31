package m7;

import java.util.concurrent.atomic.AtomicReferenceArray;
import p7.e0;
/* loaded from: classes.dex */
public final class j extends e0 {

    /* renamed from: i  reason: collision with root package name */
    private final b f9687i;

    /* renamed from: j  reason: collision with root package name */
    private final AtomicReferenceArray f9688j;

    public j(long j8, j jVar, b bVar, int i8) {
        super(j8, jVar, i8);
        this.f9687i = bVar;
        this.f9688j = new AtomicReferenceArray(c.f9661b * 2);
    }

    private final void z(int i8, Object obj) {
        this.f9688j.lazySet(i8 * 2, obj);
    }

    public final void A(int i8, Object obj) {
        this.f9688j.set((i8 * 2) + 1, obj);
    }

    public final void B(int i8, Object obj) {
        z(i8, obj);
    }

    @Override // p7.e0
    public int n() {
        return c.f9661b;
    }

    /* JADX WARN: Code restructure failed: missing block: B:35:0x0063, code lost:
        s(r4);
     */
    /* JADX WARN: Code restructure failed: missing block: B:36:0x0066, code lost:
        if (r0 == false) goto L61;
     */
    /* JADX WARN: Code restructure failed: missing block: B:37:0x0068, code lost:
        r4 = u().f9652f;
     */
    /* JADX WARN: Code restructure failed: missing block: B:38:0x006e, code lost:
        if (r4 == null) goto L60;
     */
    /* JADX WARN: Code restructure failed: missing block: B:39:0x0070, code lost:
        p7.z.b(r4, r5, r6);
     */
    /* JADX WARN: Code restructure failed: missing block: B:40:0x0073, code lost:
        return;
     */
    /* JADX WARN: Code restructure failed: missing block: B:65:?, code lost:
        return;
     */
    /* JADX WARN: Code restructure failed: missing block: B:66:?, code lost:
        return;
     */
    @Override // p7.e0
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void o(int r4, java.lang.Throwable r5, t6.g r6) {
        /*
            r3 = this;
            int r5 = m7.c.f9661b
            if (r4 < r5) goto L6
            r0 = 1
            goto L7
        L6:
            r0 = 0
        L7:
            if (r0 == 0) goto La
            int r4 = r4 - r5
        La:
            java.lang.Object r5 = r3.v(r4)
        Le:
            java.lang.Object r1 = r3.w(r4)
            boolean r2 = r1 instanceof k7.z1
            if (r2 != 0) goto L74
            boolean r2 = r1 instanceof m7.u
            if (r2 == 0) goto L1b
            goto L74
        L1b:
            p7.h0 r2 = m7.c.j()
            if (r1 == r2) goto L63
            p7.h0 r2 = m7.c.i()
            if (r1 != r2) goto L28
            goto L63
        L28:
            p7.h0 r2 = m7.c.p()
            if (r1 == r2) goto Le
            p7.h0 r2 = m7.c.q()
            if (r1 != r2) goto L35
            goto Le
        L35:
            p7.h0 r4 = m7.c.f()
            if (r1 == r4) goto L62
            p7.h0 r4 = m7.c.f9663d
            if (r1 != r4) goto L40
            goto L62
        L40:
            p7.h0 r4 = m7.c.z()
            if (r1 != r4) goto L47
            return
        L47:
            java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            java.lang.String r6 = "unexpected state: "
            r5.append(r6)
            r5.append(r1)
            java.lang.String r5 = r5.toString()
            java.lang.String r5 = r5.toString()
            r4.<init>(r5)
            throw r4
        L62:
            return
        L63:
            r3.s(r4)
            if (r0 == 0) goto L73
            m7.b r4 = r3.u()
            c7.l r4 = r4.f9652f
            if (r4 == 0) goto L73
            p7.z.b(r4, r5, r6)
        L73:
            return
        L74:
            if (r0 == 0) goto L7b
            p7.h0 r2 = m7.c.j()
            goto L7f
        L7b:
            p7.h0 r2 = m7.c.i()
        L7f:
            boolean r1 = r3.r(r4, r1, r2)
            if (r1 == 0) goto Le
            r3.s(r4)
            r1 = r0 ^ 1
            r3.x(r4, r1)
            if (r0 == 0) goto L9a
            m7.b r4 = r3.u()
            c7.l r4 = r4.f9652f
            if (r4 == 0) goto L9a
            p7.z.b(r4, r5, r6)
        L9a:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: m7.j.o(int, java.lang.Throwable, t6.g):void");
    }

    public final boolean r(int i8, Object obj, Object obj2) {
        return i.a(this.f9688j, (i8 * 2) + 1, obj, obj2);
    }

    public final void s(int i8) {
        z(i8, null);
    }

    public final Object t(int i8, Object obj) {
        return this.f9688j.getAndSet((i8 * 2) + 1, obj);
    }

    public final b u() {
        b bVar = this.f9687i;
        d7.k.b(bVar);
        return bVar;
    }

    public final Object v(int i8) {
        return this.f9688j.get(i8 * 2);
    }

    public final Object w(int i8) {
        return this.f9688j.get((i8 * 2) + 1);
    }

    public final void x(int i8, boolean z7) {
        if (z7) {
            u().A0((this.f10143g * c.f9661b) + i8);
        }
        p();
    }

    public final Object y(int i8) {
        Object v7 = v(i8);
        s(i8);
        return v7;
    }
}
