package androidx.concurrent.futures;

import java.lang.ref.WeakReference;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
/* loaded from: classes.dex */
public abstract class c {

    /* loaded from: classes.dex */
    public static final class a {

        /* renamed from: a  reason: collision with root package name */
        Object f2805a;

        /* renamed from: b  reason: collision with root package name */
        d f2806b;

        /* renamed from: c  reason: collision with root package name */
        private androidx.concurrent.futures.d f2807c = androidx.concurrent.futures.d.r();

        /* renamed from: d  reason: collision with root package name */
        private boolean f2808d;

        a() {
        }

        private void e() {
            this.f2805a = null;
            this.f2806b = null;
            this.f2807c = null;
        }

        public void a(Runnable runnable, Executor executor) {
            androidx.concurrent.futures.d dVar = this.f2807c;
            if (dVar != null) {
                dVar.d(runnable, executor);
            }
        }

        void b() {
            this.f2805a = null;
            this.f2806b = null;
            this.f2807c.o(null);
        }

        public boolean c(Object obj) {
            boolean z7 = true;
            this.f2808d = true;
            d dVar = this.f2806b;
            z7 = (dVar == null || !dVar.b(obj)) ? false : false;
            if (z7) {
                e();
            }
            return z7;
        }

        public boolean d() {
            boolean z7 = true;
            this.f2808d = true;
            d dVar = this.f2806b;
            z7 = (dVar == null || !dVar.a(true)) ? false : false;
            if (z7) {
                e();
            }
            return z7;
        }

        public boolean f(Throwable th) {
            boolean z7 = true;
            this.f2808d = true;
            d dVar = this.f2806b;
            z7 = (dVar == null || !dVar.c(th)) ? false : false;
            if (z7) {
                e();
            }
            return z7;
        }

        protected void finalize() {
            androidx.concurrent.futures.d dVar;
            d dVar2 = this.f2806b;
            if (dVar2 != null && !dVar2.isDone()) {
                dVar2.c(new b("The completer object was garbage collected - this future would otherwise never complete. The tag was: " + this.f2805a));
            }
            if (this.f2808d || (dVar = this.f2807c) == null) {
                return;
            }
            dVar.o(null);
        }
    }

    /* loaded from: classes.dex */
    static final class b extends Throwable {
        b(String str) {
            super(str);
        }

        @Override // java.lang.Throwable
        public synchronized Throwable fillInStackTrace() {
            return this;
        }
    }

    /* renamed from: androidx.concurrent.futures.c$c  reason: collision with other inner class name */
    /* loaded from: classes.dex */
    public interface InterfaceC0018c {
        Object a(a aVar);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static final class d implements i4.d {

        /* renamed from: e  reason: collision with root package name */
        final WeakReference f2809e;

        /* renamed from: f  reason: collision with root package name */
        private final androidx.concurrent.futures.a f2810f = new a();

        /* loaded from: classes.dex */
        class a extends androidx.concurrent.futures.a {
            a() {
            }

            @Override // androidx.concurrent.futures.a
            protected String l() {
                a aVar = (a) d.this.f2809e.get();
                if (aVar == null) {
                    return "Completer object has been garbage collected, future will fail soon";
                }
                return "tag=[" + aVar.f2805a + "]";
            }
        }

        d(a aVar) {
            this.f2809e = new WeakReference(aVar);
        }

        boolean a(boolean z7) {
            return this.f2810f.cancel(z7);
        }

        boolean b(Object obj) {
            return this.f2810f.o(obj);
        }

        boolean c(Throwable th) {
            return this.f2810f.p(th);
        }

        @Override // java.util.concurrent.Future
        public boolean cancel(boolean z7) {
            a aVar = (a) this.f2809e.get();
            boolean cancel = this.f2810f.cancel(z7);
            if (cancel && aVar != null) {
                aVar.b();
            }
            return cancel;
        }

        @Override // i4.d
        public void d(Runnable runnable, Executor executor) {
            this.f2810f.d(runnable, executor);
        }

        @Override // java.util.concurrent.Future
        public Object get() {
            return this.f2810f.get();
        }

        @Override // java.util.concurrent.Future
        public boolean isCancelled() {
            return this.f2810f.isCancelled();
        }

        @Override // java.util.concurrent.Future
        public boolean isDone() {
            return this.f2810f.isDone();
        }

        public String toString() {
            return this.f2810f.toString();
        }

        @Override // java.util.concurrent.Future
        public Object get(long j8, TimeUnit timeUnit) {
            return this.f2810f.get(j8, timeUnit);
        }
    }

    public static i4.d a(InterfaceC0018c interfaceC0018c) {
        a aVar = new a();
        d dVar = new d(aVar);
        aVar.f2806b = dVar;
        aVar.f2805a = interfaceC0018c.getClass();
        try {
            Object a8 = interfaceC0018c.a(aVar);
            if (a8 != null) {
                aVar.f2805a = a8;
            }
        } catch (Exception e8) {
            dVar.c(e8);
        }
        return dVar;
    }
}
