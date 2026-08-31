package b0;

import b0.d2;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
/* loaded from: classes.dex */
public abstract class t2 implements d2 {

    /* renamed from: b  reason: collision with root package name */
    private final AtomicReference f4402b;

    /* renamed from: a  reason: collision with root package name */
    private final Object f4401a = new Object();

    /* renamed from: c  reason: collision with root package name */
    private int f4403c = 0;

    /* renamed from: d  reason: collision with root package name */
    private boolean f4404d = false;

    /* renamed from: e  reason: collision with root package name */
    private final Map f4405e = new HashMap();

    /* renamed from: f  reason: collision with root package name */
    private final CopyOnWriteArraySet f4406f = new CopyOnWriteArraySet();

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static abstract class a {
        static a b(Throwable th) {
            return new j(th);
        }

        public abstract Throwable a();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static final class b implements Runnable {

        /* renamed from: l  reason: collision with root package name */
        private static final Object f4407l = new Object();

        /* renamed from: e  reason: collision with root package name */
        private final Executor f4408e;

        /* renamed from: f  reason: collision with root package name */
        private final d2.a f4409f;

        /* renamed from: h  reason: collision with root package name */
        private final AtomicReference f4411h;

        /* renamed from: g  reason: collision with root package name */
        private final AtomicBoolean f4410g = new AtomicBoolean(true);

        /* renamed from: i  reason: collision with root package name */
        private Object f4412i = f4407l;

        /* renamed from: j  reason: collision with root package name */
        private int f4413j = -1;

        /* renamed from: k  reason: collision with root package name */
        private boolean f4414k = false;

        b(AtomicReference atomicReference, Executor executor, d2.a aVar) {
            this.f4411h = atomicReference;
            this.f4408e = executor;
            this.f4409f = aVar;
        }

        void a() {
            this.f4410g.set(false);
        }

        void b(int i8) {
            synchronized (this) {
                if (!this.f4410g.get()) {
                    return;
                }
                if (i8 <= this.f4413j) {
                    return;
                }
                this.f4413j = i8;
                if (this.f4414k) {
                    return;
                }
                this.f4414k = true;
                try {
                    this.f4408e.execute(this);
                } finally {
                    synchronized (this) {
                    }
                }
            }
        }

        @Override // java.lang.Runnable
        public void run() {
            synchronized (this) {
                if (!this.f4410g.get()) {
                    this.f4414k = false;
                    return;
                }
                Object obj = this.f4411h.get();
                int i8 = this.f4413j;
                while (true) {
                    if (!Objects.equals(this.f4412i, obj)) {
                        this.f4412i = obj;
                        if (obj instanceof a) {
                            this.f4409f.a(((a) obj).a());
                        } else {
                            this.f4409f.b(obj);
                        }
                    }
                    synchronized (this) {
                        if (i8 == this.f4413j || !this.f4410g.get()) {
                            break;
                        }
                        obj = this.f4411h.get();
                        i8 = this.f4413j;
                    }
                }
                this.f4414k = false;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public t2(Object obj, boolean z7) {
        AtomicReference atomicReference;
        if (z7) {
            g1.e.b(obj instanceof Throwable, "Initial errors must be Throwable");
            atomicReference = new AtomicReference(a.b((Throwable) obj));
        } else {
            atomicReference = new AtomicReference(obj);
        }
        this.f4402b = atomicReference;
    }

    private void e(d2.a aVar) {
        b bVar = (b) this.f4405e.remove(aVar);
        if (bVar != null) {
            bVar.a();
            this.f4406f.remove(bVar);
        }
    }

    private void i(Object obj) {
        Iterator it;
        int i8;
        synchronized (this.f4401a) {
            if (Objects.equals(this.f4402b.getAndSet(obj), obj)) {
                return;
            }
            int i9 = this.f4403c + 1;
            this.f4403c = i9;
            if (this.f4404d) {
                return;
            }
            this.f4404d = true;
            Iterator it2 = this.f4406f.iterator();
            while (true) {
                if (it2.hasNext()) {
                    ((b) it2.next()).b(i9);
                } else {
                    synchronized (this.f4401a) {
                        if (this.f4403c == i9) {
                            this.f4404d = false;
                            return;
                        } else {
                            it = this.f4406f.iterator();
                            i8 = this.f4403c;
                        }
                    }
                    it2 = it;
                    i9 = i8;
                }
            }
        }
    }

    @Override // b0.d2
    public i4.d a() {
        Object obj = this.f4402b.get();
        return obj instanceof a ? f0.n.n(((a) obj).a()) : f0.n.p(obj);
    }

    @Override // b0.d2
    public void b(d2.a aVar) {
        synchronized (this.f4401a) {
            e(aVar);
        }
    }

    @Override // b0.d2
    public void d(Executor executor, d2.a aVar) {
        b bVar;
        synchronized (this.f4401a) {
            e(aVar);
            bVar = new b(this.f4402b, executor, aVar);
            this.f4405e.put(aVar, bVar);
            this.f4406f.add(bVar);
        }
        bVar.b(0);
    }

    public void f() {
        synchronized (this.f4401a) {
            Iterator it = new HashSet(this.f4405e.keySet()).iterator();
            while (it.hasNext()) {
                e((d2.a) it.next());
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void g(Object obj) {
        i(obj);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void h(Throwable th) {
        i(a.b(th));
    }
}
