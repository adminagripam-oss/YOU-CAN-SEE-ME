package k7;

import java.util.ArrayList;
import java.util.Collections;
import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CancellationException;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import k7.e1;
import p7.s;
import t6.g;
/* loaded from: classes.dex */
public class l1 implements e1, r, s1 {

    /* renamed from: e  reason: collision with root package name */
    private static final AtomicReferenceFieldUpdater f8749e = AtomicReferenceFieldUpdater.newUpdater(l1.class, Object.class, "_state");

    /* renamed from: f  reason: collision with root package name */
    private static final AtomicReferenceFieldUpdater f8750f = AtomicReferenceFieldUpdater.newUpdater(l1.class, Object.class, "_parentHandle");
    private volatile Object _parentHandle;
    private volatile Object _state;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static final class a extends k1 {

        /* renamed from: i  reason: collision with root package name */
        private final l1 f8751i;

        /* renamed from: j  reason: collision with root package name */
        private final b f8752j;

        /* renamed from: k  reason: collision with root package name */
        private final q f8753k;

        /* renamed from: l  reason: collision with root package name */
        private final Object f8754l;

        public a(l1 l1Var, b bVar, q qVar, Object obj) {
            this.f8751i = l1Var;
            this.f8752j = bVar;
            this.f8753k = qVar;
            this.f8754l = obj;
        }

        @Override // c7.l
        public /* bridge */ /* synthetic */ Object g(Object obj) {
            w((Throwable) obj);
            return r6.r.f11372a;
        }

        @Override // k7.w
        public void w(Throwable th) {
            this.f8751i.N(this.f8752j, this.f8753k, this.f8754l);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static final class b implements a1 {

        /* renamed from: f  reason: collision with root package name */
        private static final AtomicIntegerFieldUpdater f8755f = AtomicIntegerFieldUpdater.newUpdater(b.class, "_isCompleting");

        /* renamed from: g  reason: collision with root package name */
        private static final AtomicReferenceFieldUpdater f8756g = AtomicReferenceFieldUpdater.newUpdater(b.class, Object.class, "_rootCause");

        /* renamed from: h  reason: collision with root package name */
        private static final AtomicReferenceFieldUpdater f8757h = AtomicReferenceFieldUpdater.newUpdater(b.class, Object.class, "_exceptionsHolder");
        private volatile Object _exceptionsHolder;
        private volatile int _isCompleting;
        private volatile Object _rootCause;

        /* renamed from: e  reason: collision with root package name */
        private final p1 f8758e;

        public b(p1 p1Var, boolean z7, Throwable th) {
            this.f8758e = p1Var;
            this._isCompleting = z7 ? 1 : 0;
            this._rootCause = th;
        }

        private final ArrayList c() {
            return new ArrayList(4);
        }

        private final Object d() {
            return f8757h.get(this);
        }

        private final void l(Object obj) {
            f8757h.set(this, obj);
        }

        public final void a(Throwable th) {
            Throwable e8 = e();
            if (e8 == null) {
                m(th);
            } else if (th == e8) {
            } else {
                Object d8 = d();
                if (d8 == null) {
                    l(th);
                } else if (d8 instanceof Throwable) {
                    if (th == d8) {
                        return;
                    }
                    ArrayList c8 = c();
                    c8.add(d8);
                    c8.add(th);
                    l(c8);
                } else if (d8 instanceof ArrayList) {
                    ((ArrayList) d8).add(th);
                } else {
                    throw new IllegalStateException(("State is " + d8).toString());
                }
            }
        }

        @Override // k7.a1
        public boolean b() {
            return e() == null;
        }

        public final Throwable e() {
            return (Throwable) f8756g.get(this);
        }

        public final boolean f() {
            return e() != null;
        }

        public final boolean g() {
            return f8755f.get(this) != 0;
        }

        @Override // k7.a1
        public p1 h() {
            return this.f8758e;
        }

        public final boolean i() {
            p7.h0 h0Var;
            Object d8 = d();
            h0Var = m1.f8765e;
            return d8 == h0Var;
        }

        public final List j(Throwable th) {
            ArrayList arrayList;
            p7.h0 h0Var;
            Object d8 = d();
            if (d8 == null) {
                arrayList = c();
            } else if (d8 instanceof Throwable) {
                ArrayList c8 = c();
                c8.add(d8);
                arrayList = c8;
            } else if (!(d8 instanceof ArrayList)) {
                throw new IllegalStateException(("State is " + d8).toString());
            } else {
                arrayList = (ArrayList) d8;
            }
            Throwable e8 = e();
            if (e8 != null) {
                arrayList.add(0, e8);
            }
            if (th != null && !d7.k.a(th, e8)) {
                arrayList.add(th);
            }
            h0Var = m1.f8765e;
            l(h0Var);
            return arrayList;
        }

        public final void k(boolean z7) {
            f8755f.set(this, z7 ? 1 : 0);
        }

        public final void m(Throwable th) {
            f8756g.set(this, th);
        }

        public String toString() {
            return "Finishing[cancelling=" + f() + ", completing=" + g() + ", rootCause=" + e() + ", exceptions=" + d() + ", list=" + h() + ']';
        }
    }

    /* loaded from: classes.dex */
    public static final class c extends s.a {

        /* renamed from: d  reason: collision with root package name */
        final /* synthetic */ l1 f8759d;

        /* renamed from: e  reason: collision with root package name */
        final /* synthetic */ Object f8760e;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public c(p7.s sVar, l1 l1Var, Object obj) {
            super(sVar);
            this.f8759d = l1Var;
            this.f8760e = obj;
        }

        @Override // p7.b
        /* renamed from: f */
        public Object d(p7.s sVar) {
            if (this.f8759d.X() == this.f8760e) {
                return null;
            }
            return p7.r.a();
        }
    }

    public l1(boolean z7) {
        this._state = z7 ? m1.f8767g : m1.f8766f;
    }

    private final Object A0(Object obj, Object obj2) {
        p7.h0 h0Var;
        p7.h0 h0Var2;
        if (!(obj instanceof a1)) {
            h0Var2 = m1.f8761a;
            return h0Var2;
        } else if ((!(obj instanceof p0) && !(obj instanceof k1)) || (obj instanceof q) || (obj2 instanceof u)) {
            return B0((a1) obj, obj2);
        } else {
            if (y0((a1) obj, obj2)) {
                return obj2;
            }
            h0Var = m1.f8763c;
            return h0Var;
        }
    }

    private final Object B0(a1 a1Var, Object obj) {
        p7.h0 h0Var;
        p7.h0 h0Var2;
        p7.h0 h0Var3;
        p1 V = V(a1Var);
        if (V == null) {
            h0Var3 = m1.f8763c;
            return h0Var3;
        }
        b bVar = a1Var instanceof b ? (b) a1Var : null;
        if (bVar == null) {
            bVar = new b(V, false, null);
        }
        d7.r rVar = new d7.r();
        synchronized (bVar) {
            if (bVar.g()) {
                h0Var2 = m1.f8761a;
                return h0Var2;
            }
            bVar.k(true);
            if (bVar != a1Var && !androidx.concurrent.futures.b.a(f8749e, this, a1Var, bVar)) {
                h0Var = m1.f8763c;
                return h0Var;
            }
            boolean f8 = bVar.f();
            u uVar = obj instanceof u ? (u) obj : null;
            if (uVar != null) {
                bVar.a(uVar.f8789a);
            }
            Throwable e8 = Boolean.valueOf(f8 ? false : true).booleanValue() ? bVar.e() : null;
            rVar.f6484e = e8;
            r6.r rVar2 = r6.r.f11372a;
            if (e8 != null) {
                k0(V, e8);
            }
            q Q = Q(a1Var);
            return (Q == null || !C0(bVar, Q, obj)) ? P(bVar, obj) : m1.f8762b;
        }
    }

    private final boolean C0(b bVar, q qVar, Object obj) {
        while (e1.a.d(qVar.f8776i, false, false, new a(this, bVar, qVar, obj), 1, null) == q1.f8780e) {
            qVar = j0(qVar);
            if (qVar == null) {
                return false;
            }
        }
        return true;
    }

    private final Object H(Object obj) {
        p7.h0 h0Var;
        Object A0;
        p7.h0 h0Var2;
        do {
            Object X = X();
            if (!(X instanceof a1) || ((X instanceof b) && ((b) X).g())) {
                h0Var = m1.f8761a;
                return h0Var;
            }
            A0 = A0(X, new u(O(obj), false, 2, null));
            h0Var2 = m1.f8763c;
        } while (A0 == h0Var2);
        return A0;
    }

    private final boolean I(Throwable th) {
        if (e0()) {
            return true;
        }
        boolean z7 = th instanceof CancellationException;
        p W = W();
        return (W == null || W == q1.f8780e) ? z7 : W.e(th) || z7;
    }

    private final void L(a1 a1Var, Object obj) {
        p W = W();
        if (W != null) {
            W.dispose();
            s0(q1.f8780e);
        }
        u uVar = obj instanceof u ? (u) obj : null;
        Throwable th = uVar != null ? uVar.f8789a : null;
        if (!(a1Var instanceof k1)) {
            p1 h8 = a1Var.h();
            if (h8 != null) {
                l0(h8, th);
                return;
            }
            return;
        }
        try {
            ((k1) a1Var).w(th);
        } catch (Throwable th2) {
            Z(new x("Exception in completion handler " + a1Var + " for " + this, th2));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void N(b bVar, q qVar, Object obj) {
        q j02 = j0(qVar);
        if (j02 == null || !C0(bVar, j02, obj)) {
            y(P(bVar, obj));
        }
    }

    private final Throwable O(Object obj) {
        if (obj == null ? true : obj instanceof Throwable) {
            Throwable th = (Throwable) obj;
            return th == null ? new f1(J(), null, this) : th;
        }
        d7.k.c(obj, "null cannot be cast to non-null type kotlinx.coroutines.ParentJob");
        return ((s1) obj).D();
    }

    private final Object P(b bVar, Object obj) {
        boolean f8;
        Throwable S;
        u uVar = obj instanceof u ? (u) obj : null;
        Throwable th = uVar != null ? uVar.f8789a : null;
        synchronized (bVar) {
            f8 = bVar.f();
            List j8 = bVar.j(th);
            S = S(bVar, j8);
            if (S != null) {
                v(S, j8);
            }
        }
        boolean z7 = false;
        if (S != null && S != th) {
            obj = new u(S, false, 2, null);
        }
        if (S != null) {
            if ((I(S) || Y(S)) ? true : true) {
                d7.k.c(obj, "null cannot be cast to non-null type kotlinx.coroutines.CompletedExceptionally");
                ((u) obj).b();
            }
        }
        if (!f8) {
            m0(S);
        }
        n0(obj);
        androidx.concurrent.futures.b.a(f8749e, this, bVar, m1.g(obj));
        L(bVar, obj);
        return obj;
    }

    private final q Q(a1 a1Var) {
        q qVar = a1Var instanceof q ? (q) a1Var : null;
        if (qVar == null) {
            p1 h8 = a1Var.h();
            if (h8 != null) {
                return j0(h8);
            }
            return null;
        }
        return qVar;
    }

    private final Throwable R(Object obj) {
        u uVar = obj instanceof u ? (u) obj : null;
        if (uVar != null) {
            return uVar.f8789a;
        }
        return null;
    }

    private final Throwable S(b bVar, List list) {
        Object obj = null;
        if (list.isEmpty()) {
            if (bVar.f()) {
                return new f1(J(), null, this);
            }
            return null;
        }
        Iterator it = list.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            Object next = it.next();
            if (!(((Throwable) next) instanceof CancellationException)) {
                obj = next;
                break;
            }
        }
        Throwable th = (Throwable) obj;
        return th != null ? th : (Throwable) list.get(0);
    }

    private final p1 V(a1 a1Var) {
        p1 h8 = a1Var.h();
        if (h8 == null) {
            if (a1Var instanceof p0) {
                return new p1();
            }
            if (a1Var instanceof k1) {
                q0((k1) a1Var);
                return null;
            }
            throw new IllegalStateException(("State should have list: " + a1Var).toString());
        }
        return h8;
    }

    private final Object f0(Object obj) {
        p7.h0 h0Var;
        p7.h0 h0Var2;
        p7.h0 h0Var3;
        p7.h0 h0Var4;
        p7.h0 h0Var5;
        p7.h0 h0Var6;
        Throwable th = null;
        while (true) {
            Object X = X();
            if (X instanceof b) {
                synchronized (X) {
                    if (((b) X).i()) {
                        h0Var2 = m1.f8764d;
                        return h0Var2;
                    }
                    boolean f8 = ((b) X).f();
                    if (obj != null || !f8) {
                        if (th == null) {
                            th = O(obj);
                        }
                        ((b) X).a(th);
                    }
                    Throwable e8 = f8 ^ true ? ((b) X).e() : null;
                    if (e8 != null) {
                        k0(((b) X).h(), e8);
                    }
                    h0Var = m1.f8761a;
                    return h0Var;
                }
            } else if (!(X instanceof a1)) {
                h0Var3 = m1.f8764d;
                return h0Var3;
            } else {
                if (th == null) {
                    th = O(obj);
                }
                a1 a1Var = (a1) X;
                if (!a1Var.b()) {
                    Object A0 = A0(X, new u(th, false, 2, null));
                    h0Var5 = m1.f8761a;
                    if (A0 == h0Var5) {
                        throw new IllegalStateException(("Cannot happen in " + X).toString());
                    }
                    h0Var6 = m1.f8763c;
                    if (A0 != h0Var6) {
                        return A0;
                    }
                } else if (z0(a1Var, th)) {
                    h0Var4 = m1.f8761a;
                    return h0Var4;
                }
            }
        }
    }

    private final k1 h0(c7.l lVar, boolean z7) {
        k1 k1Var;
        if (z7) {
            k1Var = lVar instanceof g1 ? (g1) lVar : null;
            if (k1Var == null) {
                k1Var = new c1(lVar);
            }
        } else {
            k1Var = lVar instanceof k1 ? (k1) lVar : null;
            if (k1Var == null) {
                k1Var = new d1(lVar);
            }
        }
        k1Var.y(this);
        return k1Var;
    }

    private final q j0(p7.s sVar) {
        while (sVar.r()) {
            sVar = sVar.q();
        }
        while (true) {
            sVar = sVar.p();
            if (!sVar.r()) {
                if (sVar instanceof q) {
                    return (q) sVar;
                }
                if (sVar instanceof p1) {
                    return null;
                }
            }
        }
    }

    private final void k0(p1 p1Var, Throwable th) {
        m0(th);
        Object o8 = p1Var.o();
        d7.k.c(o8, "null cannot be cast to non-null type kotlinx.coroutines.internal.LockFreeLinkedListNode{ kotlinx.coroutines.internal.LockFreeLinkedListKt.Node }");
        x xVar = null;
        for (p7.s sVar = (p7.s) o8; !d7.k.a(sVar, p1Var); sVar = sVar.p()) {
            if (sVar instanceof g1) {
                k1 k1Var = (k1) sVar;
                try {
                    k1Var.w(th);
                } catch (Throwable th2) {
                    if (xVar != null) {
                        r6.b.a(xVar, th2);
                    } else {
                        xVar = new x("Exception in completion handler " + k1Var + " for " + this, th2);
                        r6.r rVar = r6.r.f11372a;
                    }
                }
            }
        }
        if (xVar != null) {
            Z(xVar);
        }
        I(th);
    }

    private final void l0(p1 p1Var, Throwable th) {
        Object o8 = p1Var.o();
        d7.k.c(o8, "null cannot be cast to non-null type kotlinx.coroutines.internal.LockFreeLinkedListNode{ kotlinx.coroutines.internal.LockFreeLinkedListKt.Node }");
        x xVar = null;
        for (p7.s sVar = (p7.s) o8; !d7.k.a(sVar, p1Var); sVar = sVar.p()) {
            if (sVar instanceof k1) {
                k1 k1Var = (k1) sVar;
                try {
                    k1Var.w(th);
                } catch (Throwable th2) {
                    if (xVar != null) {
                        r6.b.a(xVar, th2);
                    } else {
                        xVar = new x("Exception in completion handler " + k1Var + " for " + this, th2);
                        r6.r rVar = r6.r.f11372a;
                    }
                }
            }
        }
        if (xVar != null) {
            Z(xVar);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v2, types: [k7.z0] */
    private final void p0(p0 p0Var) {
        p1 p1Var = new p1();
        if (!p0Var.b()) {
            p1Var = new z0(p1Var);
        }
        androidx.concurrent.futures.b.a(f8749e, this, p0Var, p1Var);
    }

    private final void q0(k1 k1Var) {
        k1Var.k(new p1());
        androidx.concurrent.futures.b.a(f8749e, this, k1Var, k1Var.p());
    }

    private final int t0(Object obj) {
        p0 p0Var;
        if (!(obj instanceof p0)) {
            if (obj instanceof z0) {
                if (androidx.concurrent.futures.b.a(f8749e, this, obj, ((z0) obj).h())) {
                    o0();
                    return 1;
                }
                return -1;
            }
            return 0;
        } else if (((p0) obj).b()) {
            return 0;
        } else {
            AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = f8749e;
            p0Var = m1.f8767g;
            if (androidx.concurrent.futures.b.a(atomicReferenceFieldUpdater, this, obj, p0Var)) {
                o0();
                return 1;
            }
            return -1;
        }
    }

    private final boolean u(Object obj, p1 p1Var, k1 k1Var) {
        int v7;
        c cVar = new c(k1Var, this, obj);
        do {
            v7 = p1Var.q().v(k1Var, p1Var, cVar);
            if (v7 == 1) {
                return true;
            }
        } while (v7 != 2);
        return false;
    }

    private final String u0(Object obj) {
        if (!(obj instanceof b)) {
            return obj instanceof a1 ? ((a1) obj).b() ? "Active" : "New" : obj instanceof u ? "Cancelled" : "Completed";
        }
        b bVar = (b) obj;
        return bVar.f() ? "Cancelling" : bVar.g() ? "Completing" : "Active";
    }

    private final void v(Throwable th, List list) {
        if (list.size() <= 1) {
            return;
        }
        Set newSetFromMap = Collections.newSetFromMap(new IdentityHashMap(list.size()));
        Iterator it = list.iterator();
        while (it.hasNext()) {
            Throwable th2 = (Throwable) it.next();
            if (th2 != th && th2 != th && !(th2 instanceof CancellationException) && newSetFromMap.add(th2)) {
                r6.b.a(th, th2);
            }
        }
    }

    public static /* synthetic */ CancellationException w0(l1 l1Var, Throwable th, String str, int i8, Object obj) {
        if (obj == null) {
            if ((i8 & 1) != 0) {
                str = null;
            }
            return l1Var.v0(th, str);
        }
        throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: toCancellationException");
    }

    private final boolean y0(a1 a1Var, Object obj) {
        if (androidx.concurrent.futures.b.a(f8749e, this, a1Var, m1.g(obj))) {
            m0(null);
            n0(obj);
            L(a1Var, obj);
            return true;
        }
        return false;
    }

    private final boolean z0(a1 a1Var, Throwable th) {
        p1 V = V(a1Var);
        if (V == null) {
            return false;
        }
        if (androidx.concurrent.futures.b.a(f8749e, this, a1Var, new b(V, false, th))) {
            k0(V, th);
            return true;
        }
        return false;
    }

    @Override // k7.e1
    public final o0 A(boolean z7, boolean z8, c7.l lVar) {
        k1 h02 = h0(lVar, z7);
        while (true) {
            Object X = X();
            if (X instanceof p0) {
                p0 p0Var = (p0) X;
                if (!p0Var.b()) {
                    p0(p0Var);
                } else if (androidx.concurrent.futures.b.a(f8749e, this, X, h02)) {
                    return h02;
                }
            } else {
                if (!(X instanceof a1)) {
                    if (z8) {
                        u uVar = X instanceof u ? (u) X : null;
                        lVar.g(uVar != null ? uVar.f8789a : null);
                    }
                    return q1.f8780e;
                }
                p1 h8 = ((a1) X).h();
                if (h8 == null) {
                    d7.k.c(X, "null cannot be cast to non-null type kotlinx.coroutines.JobNode");
                    q0((k1) X);
                } else {
                    o0 o0Var = q1.f8780e;
                    if (z7 && (X instanceof b)) {
                        synchronized (X) {
                            r3 = ((b) X).e();
                            if (r3 == null || ((lVar instanceof q) && !((b) X).g())) {
                                if (u(X, h8, h02)) {
                                    if (r3 == null) {
                                        return h02;
                                    }
                                    o0Var = h02;
                                }
                            }
                            r6.r rVar = r6.r.f11372a;
                        }
                    }
                    if (r3 != null) {
                        if (z8) {
                            lVar.g(r3);
                        }
                        return o0Var;
                    } else if (u(X, h8, h02)) {
                        return h02;
                    }
                }
            }
        }
    }

    public final boolean B(Object obj) {
        Object obj2;
        p7.h0 h0Var;
        p7.h0 h0Var2;
        p7.h0 h0Var3;
        obj2 = m1.f8761a;
        if (U() && (obj2 = H(obj)) == m1.f8762b) {
            return true;
        }
        h0Var = m1.f8761a;
        if (obj2 == h0Var) {
            obj2 = f0(obj);
        }
        h0Var2 = m1.f8761a;
        if (obj2 == h0Var2 || obj2 == m1.f8762b) {
            return true;
        }
        h0Var3 = m1.f8764d;
        if (obj2 == h0Var3) {
            return false;
        }
        y(obj2);
        return true;
    }

    @Override // k7.r
    public final void C(s1 s1Var) {
        B(s1Var);
    }

    @Override // k7.s1
    public CancellationException D() {
        Throwable th;
        Object X = X();
        if (X instanceof b) {
            th = ((b) X).e();
        } else if (X instanceof u) {
            th = ((u) X).f8789a;
        } else if (X instanceof a1) {
            throw new IllegalStateException(("Cannot be cancelling child in this state: " + X).toString());
        } else {
            th = null;
        }
        CancellationException cancellationException = th instanceof CancellationException ? th : null;
        if (cancellationException == null) {
            return new f1("Parent job is " + u0(X), th, this);
        }
        return cancellationException;
    }

    @Override // k7.e1
    public final CancellationException E() {
        Object X = X();
        if (!(X instanceof b)) {
            if (X instanceof a1) {
                throw new IllegalStateException(("Job is still new or active: " + this).toString());
            } else if (X instanceof u) {
                return w0(this, ((u) X).f8789a, null, 1, null);
            } else {
                return new f1(h0.a(this) + " has completed normally", null, this);
            }
        }
        Throwable e8 = ((b) X).e();
        if (e8 != null) {
            CancellationException v02 = v0(e8, h0.a(this) + " is cancelling");
            if (v02 != null) {
                return v02;
            }
        }
        throw new IllegalStateException(("Job is still new or active: " + this).toString());
    }

    public void F(Throwable th) {
        B(th);
    }

    @Override // t6.g
    public t6.g G(g.c cVar) {
        return e1.a.e(this, cVar);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public String J() {
        return "Job was cancelled";
    }

    public boolean K(Throwable th) {
        if (th instanceof CancellationException) {
            return true;
        }
        return B(th) && T();
    }

    @Override // t6.g
    public t6.g M(t6.g gVar) {
        return e1.a.f(this, gVar);
    }

    public boolean T() {
        return true;
    }

    public boolean U() {
        return false;
    }

    public final p W() {
        return (p) f8750f.get(this);
    }

    public final Object X() {
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = f8749e;
        while (true) {
            Object obj = atomicReferenceFieldUpdater.get(this);
            if (!(obj instanceof p7.a0)) {
                return obj;
            }
            ((p7.a0) obj).a(this);
        }
    }

    protected boolean Y(Throwable th) {
        return false;
    }

    public void Z(Throwable th) {
        throw th;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void a0(e1 e1Var) {
        if (e1Var == null) {
            s0(q1.f8780e);
            return;
        }
        e1Var.start();
        p t8 = e1Var.t(this);
        s0(t8);
        if (d0()) {
            t8.dispose();
            s0(q1.f8780e);
        }
    }

    @Override // k7.e1
    public boolean b() {
        Object X = X();
        return (X instanceof a1) && ((a1) X).b();
    }

    public final o0 b0(c7.l lVar) {
        return A(false, true, lVar);
    }

    @Override // t6.g.b, t6.g
    public g.b c(g.c cVar) {
        return e1.a.c(this, cVar);
    }

    public final boolean c0() {
        Object X = X();
        return (X instanceof u) || ((X instanceof b) && ((b) X).f());
    }

    @Override // k7.e1
    public void d(CancellationException cancellationException) {
        if (cancellationException == null) {
            cancellationException = new f1(J(), null, this);
        }
        F(cancellationException);
    }

    public final boolean d0() {
        return !(X() instanceof a1);
    }

    protected boolean e0() {
        return false;
    }

    @Override // t6.g
    public Object g(Object obj, c7.p pVar) {
        return e1.a.b(this, obj, pVar);
    }

    public final Object g0(Object obj) {
        Object A0;
        p7.h0 h0Var;
        p7.h0 h0Var2;
        do {
            A0 = A0(X(), obj);
            h0Var = m1.f8761a;
            if (A0 == h0Var) {
                throw new IllegalStateException("Job " + this + " is already complete or completing, but is being completed with " + obj, R(obj));
            }
            h0Var2 = m1.f8763c;
        } while (A0 == h0Var2);
        return A0;
    }

    @Override // t6.g.b
    public final g.c getKey() {
        return e1.f8727b;
    }

    public String i0() {
        return h0.a(this);
    }

    protected void m0(Throwable th) {
    }

    protected void n0(Object obj) {
    }

    protected void o0() {
    }

    public final void r0(k1 k1Var) {
        Object X;
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater;
        p0 p0Var;
        do {
            X = X();
            if (!(X instanceof k1)) {
                if (!(X instanceof a1) || ((a1) X).h() == null) {
                    return;
                }
                k1Var.s();
                return;
            } else if (X != k1Var) {
                return;
            } else {
                atomicReferenceFieldUpdater = f8749e;
                p0Var = m1.f8767g;
            }
        } while (!androidx.concurrent.futures.b.a(atomicReferenceFieldUpdater, this, X, p0Var));
    }

    public final void s0(p pVar) {
        f8750f.set(this, pVar);
    }

    @Override // k7.e1
    public final boolean start() {
        int t02;
        do {
            t02 = t0(X());
            if (t02 == 0) {
                return false;
            }
        } while (t02 != 1);
        return true;
    }

    @Override // k7.e1
    public final p t(r rVar) {
        o0 d8 = e1.a.d(this, true, false, new q(rVar), 2, null);
        d7.k.c(d8, "null cannot be cast to non-null type kotlinx.coroutines.ChildHandle");
        return (p) d8;
    }

    public String toString() {
        return x0() + '@' + h0.b(this);
    }

    protected final CancellationException v0(Throwable th, String str) {
        CancellationException cancellationException = th instanceof CancellationException ? (CancellationException) th : null;
        if (cancellationException == null) {
            if (str == null) {
                str = J();
            }
            cancellationException = new f1(str, th, this);
        }
        return cancellationException;
    }

    public final String x0() {
        return i0() + '{' + u0(X()) + '}';
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void y(Object obj) {
    }

    public final boolean z(Throwable th) {
        return B(th);
    }
}
