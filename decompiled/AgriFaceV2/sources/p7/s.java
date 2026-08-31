package p7;

import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
/* loaded from: classes.dex */
public class s {

    /* renamed from: e  reason: collision with root package name */
    private static final AtomicReferenceFieldUpdater f10181e = AtomicReferenceFieldUpdater.newUpdater(s.class, Object.class, "_next");

    /* renamed from: f  reason: collision with root package name */
    private static final AtomicReferenceFieldUpdater f10182f = AtomicReferenceFieldUpdater.newUpdater(s.class, Object.class, "_prev");

    /* renamed from: g  reason: collision with root package name */
    private static final AtomicReferenceFieldUpdater f10183g = AtomicReferenceFieldUpdater.newUpdater(s.class, Object.class, "_removedRef");
    private volatile Object _next = this;
    private volatile Object _prev = this;
    private volatile Object _removedRef;

    /* loaded from: classes.dex */
    public static abstract class a extends p7.b {

        /* renamed from: b  reason: collision with root package name */
        public final s f10184b;

        /* renamed from: c  reason: collision with root package name */
        public s f10185c;

        public a(s sVar) {
            this.f10184b = sVar;
        }

        @Override // p7.b
        /* renamed from: e */
        public void b(s sVar, Object obj) {
            boolean z7 = obj == null;
            s sVar2 = z7 ? this.f10184b : this.f10185c;
            if (sVar2 != null && androidx.concurrent.futures.b.a(s.f10181e, sVar, this, sVar2) && z7) {
                s sVar3 = this.f10184b;
                s sVar4 = this.f10185c;
                d7.k.b(sVar4);
                sVar3.n(sVar4);
            }
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:24:0x0042, code lost:
        if (androidx.concurrent.futures.b.a(r4, r3, r2, ((p7.b0) r5).f10136a) != false) goto L23;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private final p7.s l(p7.a0 r9) {
        /*
            r8 = this;
        L0:
            java.util.concurrent.atomic.AtomicReferenceFieldUpdater r0 = p7.s.f10182f
            java.lang.Object r0 = r0.get(r8)
            p7.s r0 = (p7.s) r0
            r1 = 0
            r2 = r0
        La:
            r3 = r1
        Lb:
            java.util.concurrent.atomic.AtomicReferenceFieldUpdater r4 = p7.s.f10181e
            java.lang.Object r5 = r4.get(r2)
            if (r5 != r8) goto L20
            if (r0 != r2) goto L16
            return r2
        L16:
            java.util.concurrent.atomic.AtomicReferenceFieldUpdater r1 = p7.s.f10182f
            boolean r0 = androidx.concurrent.futures.b.a(r1, r8, r0, r2)
            if (r0 != 0) goto L1f
            goto L0
        L1f:
            return r2
        L20:
            boolean r6 = r8.r()
            if (r6 == 0) goto L27
            return r1
        L27:
            if (r5 != r9) goto L2a
            return r2
        L2a:
            boolean r6 = r5 instanceof p7.a0
            if (r6 == 0) goto L34
            p7.a0 r5 = (p7.a0) r5
            r5.a(r2)
            goto L0
        L34:
            boolean r6 = r5 instanceof p7.b0
            if (r6 == 0) goto L50
            if (r3 == 0) goto L47
            p7.b0 r5 = (p7.b0) r5
            p7.s r5 = r5.f10136a
            boolean r2 = androidx.concurrent.futures.b.a(r4, r3, r2, r5)
            if (r2 != 0) goto L45
            goto L0
        L45:
            r2 = r3
            goto La
        L47:
            java.util.concurrent.atomic.AtomicReferenceFieldUpdater r4 = p7.s.f10182f
            java.lang.Object r2 = r4.get(r2)
            p7.s r2 = (p7.s) r2
            goto Lb
        L50:
            java.lang.String r3 = "null cannot be cast to non-null type kotlinx.coroutines.internal.LockFreeLinkedListNode{ kotlinx.coroutines.internal.LockFreeLinkedListKt.Node }"
            d7.k.c(r5, r3)
            r3 = r5
            p7.s r3 = (p7.s) r3
            r7 = r3
            r3 = r2
            r2 = r7
            goto Lb
        */
        throw new UnsupportedOperationException("Method not decompiled: p7.s.l(p7.a0):p7.s");
    }

    private final s m(s sVar) {
        while (sVar.r()) {
            sVar = (s) f10182f.get(sVar);
        }
        return sVar;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void n(s sVar) {
        s sVar2;
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = f10182f;
        do {
            sVar2 = (s) atomicReferenceFieldUpdater.get(sVar);
            if (o() != sVar) {
                return;
            }
        } while (!androidx.concurrent.futures.b.a(f10182f, sVar, sVar2, this));
        if (r()) {
            sVar.l(null);
        }
    }

    private final b0 u() {
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = f10183g;
        b0 b0Var = (b0) atomicReferenceFieldUpdater.get(this);
        if (b0Var == null) {
            b0 b0Var2 = new b0(this);
            atomicReferenceFieldUpdater.lazySet(this, b0Var2);
            return b0Var2;
        }
        return b0Var;
    }

    public final boolean k(s sVar) {
        f10182f.lazySet(sVar, this);
        f10181e.lazySet(sVar, this);
        while (o() == this) {
            if (androidx.concurrent.futures.b.a(f10181e, this, this, sVar)) {
                sVar.n(this);
                return true;
            }
        }
        return false;
    }

    public final Object o() {
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = f10181e;
        while (true) {
            Object obj = atomicReferenceFieldUpdater.get(this);
            if (!(obj instanceof a0)) {
                return obj;
            }
            ((a0) obj).a(this);
        }
    }

    public final s p() {
        return r.b(o());
    }

    public final s q() {
        s l8 = l(null);
        return l8 == null ? m((s) f10182f.get(this)) : l8;
    }

    public boolean r() {
        return o() instanceof b0;
    }

    public boolean s() {
        return t() == null;
    }

    public final s t() {
        Object o8;
        s sVar;
        do {
            o8 = o();
            if (o8 instanceof b0) {
                return ((b0) o8).f10136a;
            }
            if (o8 == this) {
                return (s) o8;
            }
            d7.k.c(o8, "null cannot be cast to non-null type kotlinx.coroutines.internal.LockFreeLinkedListNode{ kotlinx.coroutines.internal.LockFreeLinkedListKt.Node }");
            sVar = (s) o8;
        } while (!androidx.concurrent.futures.b.a(f10181e, this, o8, sVar.u()));
        sVar.l(null);
        return null;
    }

    public String toString() {
        return new d7.o(this) { // from class: p7.s.b
            @Override // h7.f
            public Object get() {
                return k7.h0.a(this.f6463f);
            }
        } + '@' + k7.h0.b(this);
    }

    public final int v(s sVar, s sVar2, a aVar) {
        f10182f.lazySet(sVar, this);
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = f10181e;
        atomicReferenceFieldUpdater.lazySet(sVar, sVar2);
        aVar.f10185c = sVar2;
        if (androidx.concurrent.futures.b.a(atomicReferenceFieldUpdater, this, sVar2, aVar)) {
            return aVar.a(this) == null ? 1 : 2;
        }
        return 0;
    }
}
