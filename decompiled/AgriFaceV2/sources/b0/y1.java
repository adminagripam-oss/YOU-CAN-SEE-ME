package b0;

import android.os.SystemClock;
import androidx.concurrent.futures.c;
import b0.d2;
import b0.y1;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicBoolean;
/* loaded from: classes.dex */
public final class y1 implements d2 {

    /* renamed from: a  reason: collision with root package name */
    final androidx.lifecycle.s f4478a = new androidx.lifecycle.s();

    /* renamed from: b  reason: collision with root package name */
    private final Map f4479b = new HashMap();

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static final class a implements androidx.lifecycle.t {

        /* renamed from: a  reason: collision with root package name */
        final AtomicBoolean f4480a = new AtomicBoolean(true);

        /* renamed from: b  reason: collision with root package name */
        final d2.a f4481b;

        /* renamed from: c  reason: collision with root package name */
        final Executor f4482c;

        a(Executor executor, d2.a aVar) {
            this.f4482c = executor;
            this.f4481b = aVar;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void d(b bVar) {
            if (this.f4480a.get()) {
                if (bVar.a()) {
                    this.f4481b.b(bVar.d());
                    return;
                }
                g1.e.j(bVar.c());
                this.f4481b.a(bVar.c());
            }
        }

        void c() {
            this.f4480a.set(false);
        }

        @Override // androidx.lifecycle.t
        /* renamed from: e */
        public void a(final b bVar) {
            this.f4482c.execute(new Runnable() { // from class: b0.x1
                @Override // java.lang.Runnable
                public final void run() {
                    y1.a.this.d(bVar);
                }
            });
        }
    }

    /* loaded from: classes.dex */
    public static final class b {

        /* renamed from: a  reason: collision with root package name */
        private final Object f4483a;

        /* renamed from: b  reason: collision with root package name */
        private final Throwable f4484b;

        private b(Object obj, Throwable th) {
            this.f4483a = obj;
            this.f4484b = th;
        }

        static b b(Object obj) {
            return new b(obj, null);
        }

        public boolean a() {
            return this.f4484b == null;
        }

        public Throwable c() {
            return this.f4484b;
        }

        public Object d() {
            if (a()) {
                return this.f4483a;
            }
            throw new IllegalStateException("Result contains an error. Does not contain a value.");
        }

        public String toString() {
            StringBuilder sb;
            Object obj;
            StringBuilder sb2 = new StringBuilder();
            sb2.append("[Result: <");
            if (a()) {
                sb = new StringBuilder();
                sb.append("Value: ");
                obj = this.f4483a;
            } else {
                sb = new StringBuilder();
                sb.append("Error: ");
                obj = this.f4484b;
            }
            sb.append(obj);
            sb2.append(sb.toString());
            sb2.append(">]");
            return sb2.toString();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void i(a aVar, a aVar2) {
        if (aVar != null) {
            this.f4478a.m(aVar);
        }
        this.f4478a.i(aVar2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void j(c.a aVar) {
        Throwable c8;
        b bVar = (b) this.f4478a.e();
        if (bVar == null) {
            c8 = new IllegalStateException("Observable has not yet been initialized with a value.");
        } else if (bVar.a()) {
            aVar.c(bVar.d());
            return;
        } else {
            g1.e.j(bVar.c());
            c8 = bVar.c();
        }
        aVar.f(c8);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ Object k(final c.a aVar) {
        e0.c.e().execute(new Runnable() { // from class: b0.u1
            @Override // java.lang.Runnable
            public final void run() {
                y1.this.j(aVar);
            }
        });
        return this + " [fetch@" + SystemClock.uptimeMillis() + "]";
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void l(a aVar) {
        this.f4478a.m(aVar);
    }

    @Override // b0.d2
    public i4.d a() {
        return androidx.concurrent.futures.c.a(new c.InterfaceC0018c() { // from class: b0.t1
            @Override // androidx.concurrent.futures.c.InterfaceC0018c
            public final Object a(c.a aVar) {
                Object k8;
                k8 = y1.this.k(aVar);
                return k8;
            }
        });
    }

    @Override // b0.d2
    public void b(d2.a aVar) {
        synchronized (this.f4479b) {
            final a aVar2 = (a) this.f4479b.remove(aVar);
            if (aVar2 != null) {
                aVar2.c();
                e0.c.e().execute(new Runnable() { // from class: b0.v1
                    @Override // java.lang.Runnable
                    public final void run() {
                        y1.this.l(aVar2);
                    }
                });
            }
        }
    }

    @Override // b0.d2
    public void d(Executor executor, d2.a aVar) {
        synchronized (this.f4479b) {
            final a aVar2 = (a) this.f4479b.get(aVar);
            if (aVar2 != null) {
                aVar2.c();
            }
            final a aVar3 = new a(executor, aVar);
            this.f4479b.put(aVar, aVar3);
            e0.c.e().execute(new Runnable() { // from class: b0.w1
                @Override // java.lang.Runnable
                public final void run() {
                    y1.this.i(aVar2, aVar3);
                }
            });
        }
    }

    public void m(Object obj) {
        this.f4478a.l(b.b(obj));
    }
}
