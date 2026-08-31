package androidx.window.layout.adapter.sidecar;

import android.app.Activity;
import android.content.Context;
import androidx.window.layout.adapter.sidecar.a;
import androidx.window.layout.adapter.sidecar.b;
import d7.g;
import d7.k;
import h2.j;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.Executor;
import java.util.concurrent.locks.ReentrantLock;
import r6.r;
import s6.n;
/* loaded from: classes.dex */
public final class b implements i2.a {

    /* renamed from: d  reason: collision with root package name */
    private static volatile b f4113d;

    /* renamed from: a  reason: collision with root package name */
    private androidx.window.layout.adapter.sidecar.a f4115a;

    /* renamed from: b  reason: collision with root package name */
    private final CopyOnWriteArrayList f4116b = new CopyOnWriteArrayList();

    /* renamed from: c  reason: collision with root package name */
    public static final a f4112c = new a(null);

    /* renamed from: e  reason: collision with root package name */
    private static final ReentrantLock f4114e = new ReentrantLock();

    /* loaded from: classes.dex */
    public static final class a {
        private a() {
        }

        public /* synthetic */ a(g gVar) {
            this();
        }

        public final b a(Context context) {
            k.e(context, "context");
            if (b.f4113d == null) {
                ReentrantLock reentrantLock = b.f4114e;
                reentrantLock.lock();
                try {
                    if (b.f4113d == null) {
                        b.f4113d = new b(b.f4112c.b(context));
                    }
                    r rVar = r.f11372a;
                } finally {
                    reentrantLock.unlock();
                }
            }
            b bVar = b.f4113d;
            k.b(bVar);
            return bVar;
        }

        public final androidx.window.layout.adapter.sidecar.a b(Context context) {
            k.e(context, "context");
            try {
                if (c(SidecarCompat.f4100f.c())) {
                    SidecarCompat sidecarCompat = new SidecarCompat(context);
                    if (sidecarCompat.n()) {
                        return sidecarCompat;
                    }
                    return null;
                }
                return null;
            } catch (Throwable unused) {
                return null;
            }
        }

        public final boolean c(e2.k kVar) {
            return kVar != null && kVar.compareTo(e2.k.f6590j.a()) >= 0;
        }
    }

    /* renamed from: androidx.window.layout.adapter.sidecar.b$b  reason: collision with other inner class name */
    /* loaded from: classes.dex */
    public final class C0055b implements a.InterfaceC0054a {
        public C0055b() {
        }

        @Override // androidx.window.layout.adapter.sidecar.a.InterfaceC0054a
        public void a(Activity activity, j jVar) {
            k.e(activity, "activity");
            k.e(jVar, "newLayout");
            Iterator it = b.this.g().iterator();
            while (it.hasNext()) {
                c cVar = (c) it.next();
                if (k.a(cVar.d(), activity)) {
                    cVar.b(jVar);
                }
            }
        }
    }

    /* loaded from: classes.dex */
    public static final class c {

        /* renamed from: a  reason: collision with root package name */
        private final Activity f4118a;

        /* renamed from: b  reason: collision with root package name */
        private final Executor f4119b;

        /* renamed from: c  reason: collision with root package name */
        private final g1.a f4120c;

        /* renamed from: d  reason: collision with root package name */
        private j f4121d;

        public c(Activity activity, Executor executor, g1.a aVar) {
            k.e(activity, "activity");
            k.e(executor, "executor");
            k.e(aVar, "callback");
            this.f4118a = activity;
            this.f4119b = executor;
            this.f4120c = aVar;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void c(c cVar, j jVar) {
            k.e(cVar, "this$0");
            k.e(jVar, "$newLayoutInfo");
            cVar.f4120c.accept(jVar);
        }

        public final void b(final j jVar) {
            k.e(jVar, "newLayoutInfo");
            this.f4121d = jVar;
            this.f4119b.execute(new Runnable() { // from class: k2.c
                @Override // java.lang.Runnable
                public final void run() {
                    b.c.c(b.c.this, jVar);
                }
            });
        }

        public final Activity d() {
            return this.f4118a;
        }

        public final g1.a e() {
            return this.f4120c;
        }

        public final j f() {
            return this.f4121d;
        }
    }

    public b(androidx.window.layout.adapter.sidecar.a aVar) {
        this.f4115a = aVar;
        androidx.window.layout.adapter.sidecar.a aVar2 = this.f4115a;
        if (aVar2 != null) {
            aVar2.a(new C0055b());
        }
    }

    private final void f(Activity activity) {
        androidx.window.layout.adapter.sidecar.a aVar;
        CopyOnWriteArrayList copyOnWriteArrayList = this.f4116b;
        boolean z7 = false;
        if (!(copyOnWriteArrayList instanceof Collection) || !copyOnWriteArrayList.isEmpty()) {
            Iterator it = copyOnWriteArrayList.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                } else if (k.a(((c) it.next()).d(), activity)) {
                    z7 = true;
                    break;
                }
            }
        }
        if (z7 || (aVar = this.f4115a) == null) {
            return;
        }
        aVar.c(activity);
    }

    private final boolean h(Activity activity) {
        CopyOnWriteArrayList<c> copyOnWriteArrayList = this.f4116b;
        if ((copyOnWriteArrayList instanceof Collection) && copyOnWriteArrayList.isEmpty()) {
            return false;
        }
        for (c cVar : copyOnWriteArrayList) {
            if (k.a(cVar.d(), activity)) {
                return true;
            }
        }
        return false;
    }

    @Override // i2.a
    public void a(g1.a aVar) {
        k.e(aVar, "callback");
        synchronized (f4114e) {
            if (this.f4115a == null) {
                return;
            }
            ArrayList<c> arrayList = new ArrayList();
            Iterator it = this.f4116b.iterator();
            while (it.hasNext()) {
                c cVar = (c) it.next();
                if (cVar.e() == aVar) {
                    k.d(cVar, "callbackWrapper");
                    arrayList.add(cVar);
                }
            }
            this.f4116b.removeAll(arrayList);
            for (c cVar2 : arrayList) {
                f(cVar2.d());
            }
            r rVar = r.f11372a;
        }
    }

    @Override // i2.a
    public void b(Context context, Executor executor, g1.a aVar) {
        List f8;
        Object obj;
        List f9;
        k.e(context, "context");
        k.e(executor, "executor");
        k.e(aVar, "callback");
        r rVar = null;
        Activity activity = context instanceof Activity ? (Activity) context : null;
        if (activity != null) {
            ReentrantLock reentrantLock = f4114e;
            reentrantLock.lock();
            try {
                androidx.window.layout.adapter.sidecar.a aVar2 = this.f4115a;
                if (aVar2 == null) {
                    f9 = n.f();
                    aVar.accept(new j(f9));
                    return;
                }
                boolean h8 = h(activity);
                c cVar = new c(activity, executor, aVar);
                this.f4116b.add(cVar);
                if (h8) {
                    Iterator it = this.f4116b.iterator();
                    while (true) {
                        if (!it.hasNext()) {
                            obj = null;
                            break;
                        }
                        obj = it.next();
                        if (k.a(activity, ((c) obj).d())) {
                            break;
                        }
                    }
                    c cVar2 = (c) obj;
                    j f10 = cVar2 != null ? cVar2.f() : null;
                    if (f10 != null) {
                        cVar.b(f10);
                    }
                } else {
                    aVar2.b(activity);
                }
                r rVar2 = r.f11372a;
                reentrantLock.unlock();
                rVar = r.f11372a;
            } finally {
                reentrantLock.unlock();
            }
        }
        if (rVar == null) {
            f8 = n.f();
            aVar.accept(new j(f8));
        }
    }

    public final CopyOnWriteArrayList g() {
        return this.f4116b;
    }
}
