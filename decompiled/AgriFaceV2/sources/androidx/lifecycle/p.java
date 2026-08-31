package androidx.lifecycle;

import androidx.lifecycle.i;
import java.util.Iterator;
import java.util.Map;
import l.b;
/* loaded from: classes.dex */
public abstract class p {

    /* renamed from: k  reason: collision with root package name */
    static final Object f3818k = new Object();

    /* renamed from: a  reason: collision with root package name */
    final Object f3819a;

    /* renamed from: b  reason: collision with root package name */
    private l.b f3820b;

    /* renamed from: c  reason: collision with root package name */
    int f3821c;

    /* renamed from: d  reason: collision with root package name */
    private boolean f3822d;

    /* renamed from: e  reason: collision with root package name */
    private volatile Object f3823e;

    /* renamed from: f  reason: collision with root package name */
    volatile Object f3824f;

    /* renamed from: g  reason: collision with root package name */
    private int f3825g;

    /* renamed from: h  reason: collision with root package name */
    private boolean f3826h;

    /* renamed from: i  reason: collision with root package name */
    private boolean f3827i;

    /* renamed from: j  reason: collision with root package name */
    private final Runnable f3828j;

    /* loaded from: classes.dex */
    class a implements Runnable {
        a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            Object obj;
            synchronized (p.this.f3819a) {
                obj = p.this.f3824f;
                p.this.f3824f = p.f3818k;
            }
            p.this.o(obj);
        }
    }

    /* loaded from: classes.dex */
    private class b extends d {
        b(t tVar) {
            super(tVar);
        }

        @Override // androidx.lifecycle.p.d
        boolean e() {
            return true;
        }
    }

    /* loaded from: classes.dex */
    class c extends d implements k {

        /* renamed from: i  reason: collision with root package name */
        final m f3831i;

        c(m mVar, t tVar) {
            super(tVar);
            this.f3831i = mVar;
        }

        @Override // androidx.lifecycle.p.d
        void b() {
            this.f3831i.getLifecycle().c(this);
        }

        @Override // androidx.lifecycle.p.d
        boolean c(m mVar) {
            return this.f3831i == mVar;
        }

        @Override // androidx.lifecycle.p.d
        boolean e() {
            return this.f3831i.getLifecycle().b().e(i.b.STARTED);
        }

        @Override // androidx.lifecycle.k
        public void f(m mVar, i.a aVar) {
            i.b b8 = this.f3831i.getLifecycle().b();
            if (b8 == i.b.DESTROYED) {
                p.this.m(this.f3833e);
                return;
            }
            i.b bVar = null;
            while (bVar != b8) {
                a(e());
                bVar = b8;
                b8 = this.f3831i.getLifecycle().b();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public abstract class d {

        /* renamed from: e  reason: collision with root package name */
        final t f3833e;

        /* renamed from: f  reason: collision with root package name */
        boolean f3834f;

        /* renamed from: g  reason: collision with root package name */
        int f3835g = -1;

        d(t tVar) {
            this.f3833e = tVar;
        }

        void a(boolean z7) {
            if (z7 == this.f3834f) {
                return;
            }
            this.f3834f = z7;
            p.this.b(z7 ? 1 : -1);
            if (this.f3834f) {
                p.this.d(this);
            }
        }

        void b() {
        }

        boolean c(m mVar) {
            return false;
        }

        abstract boolean e();
    }

    public p() {
        this.f3819a = new Object();
        this.f3820b = new l.b();
        this.f3821c = 0;
        Object obj = f3818k;
        this.f3824f = obj;
        this.f3828j = new a();
        this.f3823e = obj;
        this.f3825g = -1;
    }

    static void a(String str) {
        if (k.c.f().b()) {
            return;
        }
        throw new IllegalStateException("Cannot invoke " + str + " on a background thread");
    }

    private void c(d dVar) {
        if (dVar.f3834f) {
            if (!dVar.e()) {
                dVar.a(false);
                return;
            }
            int i8 = dVar.f3835g;
            int i9 = this.f3825g;
            if (i8 >= i9) {
                return;
            }
            dVar.f3835g = i9;
            dVar.f3833e.a(this.f3823e);
        }
    }

    void b(int i8) {
        int i9 = this.f3821c;
        this.f3821c = i8 + i9;
        if (this.f3822d) {
            return;
        }
        this.f3822d = true;
        while (true) {
            try {
                int i10 = this.f3821c;
                if (i9 == i10) {
                    return;
                }
                boolean z7 = i9 == 0 && i10 > 0;
                boolean z8 = i9 > 0 && i10 == 0;
                if (z7) {
                    j();
                } else if (z8) {
                    k();
                }
                i9 = i10;
            } finally {
                this.f3822d = false;
            }
        }
    }

    void d(d dVar) {
        if (this.f3826h) {
            this.f3827i = true;
            return;
        }
        this.f3826h = true;
        do {
            this.f3827i = false;
            if (dVar == null) {
                b.d k8 = this.f3820b.k();
                while (k8.hasNext()) {
                    c((d) ((Map.Entry) k8.next()).getValue());
                    if (this.f3827i) {
                        break;
                    }
                }
            } else {
                c(dVar);
                dVar = null;
            }
        } while (this.f3827i);
        this.f3826h = false;
    }

    public Object e() {
        Object obj = this.f3823e;
        if (obj != f3818k) {
            return obj;
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int f() {
        return this.f3825g;
    }

    public boolean g() {
        return this.f3821c > 0;
    }

    public void h(m mVar, t tVar) {
        a("observe");
        if (mVar.getLifecycle().b() == i.b.DESTROYED) {
            return;
        }
        c cVar = new c(mVar, tVar);
        d dVar = (d) this.f3820b.n(tVar, cVar);
        if (dVar != null && !dVar.c(mVar)) {
            throw new IllegalArgumentException("Cannot add the same observer with different lifecycles");
        }
        if (dVar != null) {
            return;
        }
        mVar.getLifecycle().a(cVar);
    }

    public void i(t tVar) {
        a("observeForever");
        b bVar = new b(tVar);
        d dVar = (d) this.f3820b.n(tVar, bVar);
        if (dVar instanceof c) {
            throw new IllegalArgumentException("Cannot add the same observer with different lifecycles");
        }
        if (dVar != null) {
            return;
        }
        bVar.a(true);
    }

    protected void j() {
    }

    protected void k() {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void l(Object obj) {
        boolean z7;
        synchronized (this.f3819a) {
            z7 = this.f3824f == f3818k;
            this.f3824f = obj;
        }
        if (z7) {
            k.c.f().c(this.f3828j);
        }
    }

    public void m(t tVar) {
        a("removeObserver");
        d dVar = (d) this.f3820b.o(tVar);
        if (dVar == null) {
            return;
        }
        dVar.b();
        dVar.a(false);
    }

    public void n(m mVar) {
        a("removeObservers");
        Iterator it = this.f3820b.iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            if (((d) entry.getValue()).c(mVar)) {
                m((t) entry.getKey());
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void o(Object obj) {
        a("setValue");
        this.f3825g++;
        this.f3823e = obj;
        d(null);
    }

    public p(Object obj) {
        this.f3819a = new Object();
        this.f3820b = new l.b();
        this.f3821c = 0;
        this.f3824f = f3818k;
        this.f3828j = new a();
        this.f3823e = obj;
        this.f3825g = 0;
    }
}
