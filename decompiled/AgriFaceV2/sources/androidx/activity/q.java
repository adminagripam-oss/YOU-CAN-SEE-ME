package androidx.activity;

import android.os.Build;
import android.window.BackEvent;
import android.window.OnBackAnimationCallback;
import android.window.OnBackInvokedCallback;
import android.window.OnBackInvokedDispatcher;
import androidx.activity.q;
import androidx.lifecycle.i;
import java.util.Collection;
import java.util.Iterator;
import java.util.ListIterator;
/* loaded from: classes.dex */
public final class q {

    /* renamed from: a  reason: collision with root package name */
    private final Runnable f1443a;

    /* renamed from: b  reason: collision with root package name */
    private final g1.a f1444b;

    /* renamed from: c  reason: collision with root package name */
    private final s6.e f1445c;

    /* renamed from: d  reason: collision with root package name */
    private p f1446d;

    /* renamed from: e  reason: collision with root package name */
    private OnBackInvokedCallback f1447e;

    /* renamed from: f  reason: collision with root package name */
    private OnBackInvokedDispatcher f1448f;

    /* renamed from: g  reason: collision with root package name */
    private boolean f1449g;

    /* renamed from: h  reason: collision with root package name */
    private boolean f1450h;

    /* loaded from: classes.dex */
    static final class a extends d7.l implements c7.l {
        a() {
            super(1);
        }

        public final void b(androidx.activity.b bVar) {
            d7.k.e(bVar, "backEvent");
            q.this.m(bVar);
        }

        @Override // c7.l
        public /* bridge */ /* synthetic */ Object g(Object obj) {
            b((androidx.activity.b) obj);
            return r6.r.f11372a;
        }
    }

    /* loaded from: classes.dex */
    static final class b extends d7.l implements c7.l {
        b() {
            super(1);
        }

        public final void b(androidx.activity.b bVar) {
            d7.k.e(bVar, "backEvent");
            q.this.l(bVar);
        }

        @Override // c7.l
        public /* bridge */ /* synthetic */ Object g(Object obj) {
            b((androidx.activity.b) obj);
            return r6.r.f11372a;
        }
    }

    /* loaded from: classes.dex */
    static final class c extends d7.l implements c7.a {
        c() {
            super(0);
        }

        @Override // c7.a
        public /* bridge */ /* synthetic */ Object a() {
            b();
            return r6.r.f11372a;
        }

        public final void b() {
            q.this.k();
        }
    }

    /* loaded from: classes.dex */
    static final class d extends d7.l implements c7.a {
        d() {
            super(0);
        }

        @Override // c7.a
        public /* bridge */ /* synthetic */ Object a() {
            b();
            return r6.r.f11372a;
        }

        public final void b() {
            q.this.j();
        }
    }

    /* loaded from: classes.dex */
    static final class e extends d7.l implements c7.a {
        e() {
            super(0);
        }

        @Override // c7.a
        public /* bridge */ /* synthetic */ Object a() {
            b();
            return r6.r.f11372a;
        }

        public final void b() {
            q.this.k();
        }
    }

    /* loaded from: classes.dex */
    public static final class f {

        /* renamed from: a  reason: collision with root package name */
        public static final f f1456a = new f();

        private f() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void c(c7.a aVar) {
            d7.k.e(aVar, "$onBackInvoked");
            aVar.a();
        }

        public final OnBackInvokedCallback b(final c7.a aVar) {
            d7.k.e(aVar, "onBackInvoked");
            return new OnBackInvokedCallback() { // from class: androidx.activity.r
                public final void onBackInvoked() {
                    q.f.c(c7.a.this);
                }
            };
        }

        public final void d(Object obj, int i8, Object obj2) {
            d7.k.e(obj, "dispatcher");
            d7.k.e(obj2, "callback");
            ((OnBackInvokedDispatcher) obj).registerOnBackInvokedCallback(i8, (OnBackInvokedCallback) obj2);
        }

        public final void e(Object obj, Object obj2) {
            d7.k.e(obj, "dispatcher");
            d7.k.e(obj2, "callback");
            ((OnBackInvokedDispatcher) obj).unregisterOnBackInvokedCallback((OnBackInvokedCallback) obj2);
        }
    }

    /* loaded from: classes.dex */
    public static final class g {

        /* renamed from: a  reason: collision with root package name */
        public static final g f1457a = new g();

        /* loaded from: classes.dex */
        public static final class a implements OnBackAnimationCallback {

            /* renamed from: a  reason: collision with root package name */
            final /* synthetic */ c7.l f1458a;

            /* renamed from: b  reason: collision with root package name */
            final /* synthetic */ c7.l f1459b;

            /* renamed from: c  reason: collision with root package name */
            final /* synthetic */ c7.a f1460c;

            /* renamed from: d  reason: collision with root package name */
            final /* synthetic */ c7.a f1461d;

            a(c7.l lVar, c7.l lVar2, c7.a aVar, c7.a aVar2) {
                this.f1458a = lVar;
                this.f1459b = lVar2;
                this.f1460c = aVar;
                this.f1461d = aVar2;
            }

            public void onBackCancelled() {
                this.f1461d.a();
            }

            public void onBackInvoked() {
                this.f1460c.a();
            }

            public void onBackProgressed(BackEvent backEvent) {
                d7.k.e(backEvent, "backEvent");
                this.f1459b.g(new androidx.activity.b(backEvent));
            }

            public void onBackStarted(BackEvent backEvent) {
                d7.k.e(backEvent, "backEvent");
                this.f1458a.g(new androidx.activity.b(backEvent));
            }
        }

        private g() {
        }

        public final OnBackInvokedCallback a(c7.l lVar, c7.l lVar2, c7.a aVar, c7.a aVar2) {
            d7.k.e(lVar, "onBackStarted");
            d7.k.e(lVar2, "onBackProgressed");
            d7.k.e(aVar, "onBackInvoked");
            d7.k.e(aVar2, "onBackCancelled");
            return new a(lVar, lVar2, aVar, aVar2);
        }
    }

    /* loaded from: classes.dex */
    private final class h implements androidx.lifecycle.k, androidx.activity.c {

        /* renamed from: e  reason: collision with root package name */
        private final androidx.lifecycle.i f1462e;

        /* renamed from: f  reason: collision with root package name */
        private final p f1463f;

        /* renamed from: g  reason: collision with root package name */
        private androidx.activity.c f1464g;

        /* renamed from: h  reason: collision with root package name */
        final /* synthetic */ q f1465h;

        public h(q qVar, androidx.lifecycle.i iVar, p pVar) {
            d7.k.e(iVar, "lifecycle");
            d7.k.e(pVar, "onBackPressedCallback");
            this.f1465h = qVar;
            this.f1462e = iVar;
            this.f1463f = pVar;
            iVar.a(this);
        }

        @Override // androidx.activity.c
        public void cancel() {
            this.f1462e.c(this);
            this.f1463f.i(this);
            androidx.activity.c cVar = this.f1464g;
            if (cVar != null) {
                cVar.cancel();
            }
            this.f1464g = null;
        }

        @Override // androidx.lifecycle.k
        public void f(androidx.lifecycle.m mVar, i.a aVar) {
            d7.k.e(mVar, "source");
            d7.k.e(aVar, "event");
            if (aVar == i.a.ON_START) {
                this.f1464g = this.f1465h.i(this.f1463f);
            } else if (aVar != i.a.ON_STOP) {
                if (aVar == i.a.ON_DESTROY) {
                    cancel();
                }
            } else {
                androidx.activity.c cVar = this.f1464g;
                if (cVar != null) {
                    cVar.cancel();
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public final class i implements androidx.activity.c {

        /* renamed from: e  reason: collision with root package name */
        private final p f1466e;

        /* renamed from: f  reason: collision with root package name */
        final /* synthetic */ q f1467f;

        public i(q qVar, p pVar) {
            d7.k.e(pVar, "onBackPressedCallback");
            this.f1467f = qVar;
            this.f1466e = pVar;
        }

        @Override // androidx.activity.c
        public void cancel() {
            this.f1467f.f1445c.remove(this.f1466e);
            if (d7.k.a(this.f1467f.f1446d, this.f1466e)) {
                this.f1466e.c();
                this.f1467f.f1446d = null;
            }
            this.f1466e.i(this);
            c7.a b8 = this.f1466e.b();
            if (b8 != null) {
                b8.a();
            }
            this.f1466e.k(null);
        }
    }

    /* loaded from: classes.dex */
    /* synthetic */ class j extends d7.j implements c7.a {
        j(Object obj) {
            super(0, obj, q.class, "updateEnabledCallbacks", "updateEnabledCallbacks()V", 0);
        }

        @Override // c7.a
        public /* bridge */ /* synthetic */ Object a() {
            l();
            return r6.r.f11372a;
        }

        public final void l() {
            ((q) this.f6463f).p();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public /* synthetic */ class k extends d7.j implements c7.a {
        k(Object obj) {
            super(0, obj, q.class, "updateEnabledCallbacks", "updateEnabledCallbacks()V", 0);
        }

        @Override // c7.a
        public /* bridge */ /* synthetic */ Object a() {
            l();
            return r6.r.f11372a;
        }

        public final void l() {
            ((q) this.f6463f).p();
        }
    }

    public q(Runnable runnable) {
        this(runnable, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v2 */
    /* JADX WARN: Type inference failed for: r2v3 */
    /* JADX WARN: Type inference failed for: r2v4, types: [java.lang.Object] */
    public final void j() {
        ?? r22;
        p pVar = this.f1446d;
        if (pVar == null) {
            s6.e eVar = this.f1445c;
            ListIterator listIterator = eVar.listIterator(eVar.size());
            while (true) {
                if (!listIterator.hasPrevious()) {
                    r22 = 0;
                    break;
                }
                r22 = listIterator.previous();
                if (((p) r22).g()) {
                    break;
                }
            }
            pVar = r22;
        }
        this.f1446d = null;
        if (pVar != null) {
            pVar.c();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v2 */
    /* JADX WARN: Type inference failed for: r1v3 */
    /* JADX WARN: Type inference failed for: r1v4, types: [java.lang.Object] */
    public final void l(androidx.activity.b bVar) {
        ?? r12;
        p pVar = this.f1446d;
        if (pVar == null) {
            s6.e eVar = this.f1445c;
            ListIterator listIterator = eVar.listIterator(eVar.size());
            while (true) {
                if (!listIterator.hasPrevious()) {
                    r12 = 0;
                    break;
                }
                r12 = listIterator.previous();
                if (((p) r12).g()) {
                    break;
                }
            }
            pVar = r12;
        }
        if (pVar != null) {
            pVar.e(bVar);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void m(androidx.activity.b bVar) {
        Object obj;
        s6.e eVar = this.f1445c;
        ListIterator<E> listIterator = eVar.listIterator(eVar.size());
        while (true) {
            if (!listIterator.hasPrevious()) {
                obj = null;
                break;
            }
            obj = listIterator.previous();
            if (((p) obj).g()) {
                break;
            }
        }
        p pVar = (p) obj;
        this.f1446d = pVar;
        if (pVar != null) {
            pVar.f(bVar);
        }
    }

    private final void o(boolean z7) {
        OnBackInvokedDispatcher onBackInvokedDispatcher = this.f1448f;
        OnBackInvokedCallback onBackInvokedCallback = this.f1447e;
        if (onBackInvokedDispatcher == null || onBackInvokedCallback == null) {
            return;
        }
        if (z7 && !this.f1449g) {
            f.f1456a.d(onBackInvokedDispatcher, 0, onBackInvokedCallback);
            this.f1449g = true;
        } else if (z7 || !this.f1449g) {
        } else {
            f.f1456a.e(onBackInvokedDispatcher, onBackInvokedCallback);
            this.f1449g = false;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void p() {
        boolean z7 = this.f1450h;
        s6.e eVar = this.f1445c;
        boolean z8 = false;
        if (!(eVar instanceof Collection) || !eVar.isEmpty()) {
            Iterator<E> it = eVar.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                } else if (((p) it.next()).g()) {
                    z8 = true;
                    break;
                }
            }
        }
        this.f1450h = z8;
        if (z8 != z7) {
            g1.a aVar = this.f1444b;
            if (aVar != null) {
                aVar.accept(Boolean.valueOf(z8));
            }
            if (Build.VERSION.SDK_INT >= 33) {
                o(z8);
            }
        }
    }

    public final void h(androidx.lifecycle.m mVar, p pVar) {
        d7.k.e(mVar, "owner");
        d7.k.e(pVar, "onBackPressedCallback");
        androidx.lifecycle.i lifecycle = mVar.getLifecycle();
        if (lifecycle.b() == i.b.DESTROYED) {
            return;
        }
        pVar.a(new h(this, lifecycle, pVar));
        p();
        pVar.k(new j(this));
    }

    public final androidx.activity.c i(p pVar) {
        d7.k.e(pVar, "onBackPressedCallback");
        this.f1445c.add(pVar);
        i iVar = new i(this, pVar);
        pVar.a(iVar);
        p();
        pVar.k(new k(this));
        return iVar;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v2 */
    /* JADX WARN: Type inference failed for: r2v3 */
    /* JADX WARN: Type inference failed for: r2v4, types: [java.lang.Object] */
    public final void k() {
        ?? r22;
        p pVar = this.f1446d;
        if (pVar == null) {
            s6.e eVar = this.f1445c;
            ListIterator listIterator = eVar.listIterator(eVar.size());
            while (true) {
                if (!listIterator.hasPrevious()) {
                    r22 = 0;
                    break;
                }
                r22 = listIterator.previous();
                if (((p) r22).g()) {
                    break;
                }
            }
            pVar = r22;
        }
        this.f1446d = null;
        if (pVar != null) {
            pVar.d();
            return;
        }
        Runnable runnable = this.f1443a;
        if (runnable != null) {
            runnable.run();
        }
    }

    public final void n(OnBackInvokedDispatcher onBackInvokedDispatcher) {
        d7.k.e(onBackInvokedDispatcher, "invoker");
        this.f1448f = onBackInvokedDispatcher;
        o(this.f1450h);
    }

    public q(Runnable runnable, g1.a aVar) {
        this.f1443a = runnable;
        this.f1444b = aVar;
        this.f1445c = new s6.e();
        int i8 = Build.VERSION.SDK_INT;
        if (i8 >= 33) {
            this.f1447e = i8 >= 34 ? g.f1457a.a(new a(), new b(), new c(), new d()) : f.f1456a.b(new e());
        }
    }
}
