package f2;

import c7.p;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.Executor;
import java.util.concurrent.locks.ReentrantLock;
import k7.d0;
import k7.e0;
import k7.e1;
import k7.f;
import k7.y0;
import n7.c;
import n7.d;
import r6.l;
import r6.r;
import u6.b;
import v6.k;
/* loaded from: classes.dex */
public final class a {

    /* renamed from: a  reason: collision with root package name */
    private final ReentrantLock f6725a = new ReentrantLock();

    /* renamed from: b  reason: collision with root package name */
    private final Map f6726b = new LinkedHashMap();

    /* renamed from: f2.a$a  reason: collision with other inner class name */
    /* loaded from: classes.dex */
    static final class C0087a extends k implements p {

        /* renamed from: i  reason: collision with root package name */
        int f6727i;

        /* renamed from: j  reason: collision with root package name */
        final /* synthetic */ c f6728j;

        /* renamed from: k  reason: collision with root package name */
        final /* synthetic */ g1.a f6729k;

        /* JADX INFO: Access modifiers changed from: package-private */
        /* renamed from: f2.a$a$a  reason: collision with other inner class name */
        /* loaded from: classes.dex */
        public static final class C0088a implements d {

            /* renamed from: a  reason: collision with root package name */
            final /* synthetic */ g1.a f6730a;

            C0088a(g1.a aVar) {
                this.f6730a = aVar;
            }

            @Override // n7.d
            public final Object c(Object obj, t6.d dVar) {
                this.f6730a.accept(obj);
                return r.f11372a;
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C0087a(c cVar, g1.a aVar, t6.d dVar) {
            super(2, dVar);
            this.f6728j = cVar;
            this.f6729k = aVar;
        }

        @Override // v6.a
        public final t6.d b(Object obj, t6.d dVar) {
            return new C0087a(this.f6728j, this.f6729k, dVar);
        }

        @Override // v6.a
        public final Object l(Object obj) {
            Object c8 = b.c();
            int i8 = this.f6727i;
            if (i8 == 0) {
                l.b(obj);
                c cVar = this.f6728j;
                C0088a c0088a = new C0088a(this.f6729k);
                this.f6727i = 1;
                if (cVar.a(c0088a, this) == c8) {
                    return c8;
                }
            } else if (i8 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            } else {
                l.b(obj);
            }
            return r.f11372a;
        }

        @Override // c7.p
        /* renamed from: o */
        public final Object f(d0 d0Var, t6.d dVar) {
            return ((C0087a) b(d0Var, dVar)).l(r.f11372a);
        }
    }

    public final void a(Executor executor, g1.a aVar, c cVar) {
        d7.k.e(executor, "executor");
        d7.k.e(aVar, "consumer");
        d7.k.e(cVar, "flow");
        ReentrantLock reentrantLock = this.f6725a;
        reentrantLock.lock();
        try {
            if (this.f6726b.get(aVar) == null) {
                this.f6726b.put(aVar, f.b(e0.a(y0.a(executor)), null, null, new C0087a(cVar, aVar, null), 3, null));
            }
            r rVar = r.f11372a;
        } finally {
            reentrantLock.unlock();
        }
    }

    public final void b(g1.a aVar) {
        d7.k.e(aVar, "consumer");
        ReentrantLock reentrantLock = this.f6725a;
        reentrantLock.lock();
        try {
            e1 e1Var = (e1) this.f6726b.get(aVar);
            if (e1Var != null) {
                e1.a.a(e1Var, null, 1, null);
            }
            e1 e1Var2 = (e1) this.f6726b.remove(aVar);
        } finally {
            reentrantLock.unlock();
        }
    }
}
