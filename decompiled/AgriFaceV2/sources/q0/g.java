package q0;

import android.content.Context;
import androidx.concurrent.futures.c;
import androidx.lifecycle.m;
import b0.b0;
import b0.c0;
import b0.e0;
import b0.g0;
import b0.h0;
import b0.i1;
import b0.o2;
import b0.z;
import d7.k;
import d7.l;
import d7.u;
import f0.n;
import g0.f;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import q0.g;
import r6.r;
import y.e1;
import y.h2;
import y.i2;
import y.j;
import y.o;
import y.p;
import y.q;
import y.w;
import y.x;
/* loaded from: classes.dex */
public final class g {

    /* renamed from: i  reason: collision with root package name */
    public static final a f10225i = new a(null);

    /* renamed from: j  reason: collision with root package name */
    private static final g f10226j = new g();

    /* renamed from: a  reason: collision with root package name */
    private final Object f10227a = new Object();

    /* renamed from: b  reason: collision with root package name */
    private x.b f10228b;

    /* renamed from: c  reason: collision with root package name */
    private i4.d f10229c;

    /* renamed from: d  reason: collision with root package name */
    private i4.d f10230d;

    /* renamed from: e  reason: collision with root package name */
    private final q0.c f10231e;

    /* renamed from: f  reason: collision with root package name */
    private w f10232f;

    /* renamed from: g  reason: collision with root package name */
    private Context f10233g;

    /* renamed from: h  reason: collision with root package name */
    private final Map f10234h;

    /* loaded from: classes.dex */
    public static final class a {

        /* JADX INFO: Access modifiers changed from: package-private */
        /* renamed from: q0.g$a$a  reason: collision with other inner class name */
        /* loaded from: classes.dex */
        public static final class C0141a extends l implements c7.l {

            /* renamed from: f  reason: collision with root package name */
            final /* synthetic */ Context f10235f;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            C0141a(Context context) {
                super(1);
                this.f10235f = context;
            }

            @Override // c7.l
            /* renamed from: b */
            public final g g(w wVar) {
                g gVar = g.f10226j;
                k.d(wVar, "cameraX");
                gVar.z(wVar);
                g gVar2 = g.f10226j;
                Context a8 = d0.f.a(this.f10235f);
                k.d(a8, "getApplicationContext(context)");
                gVar2.A(a8);
                return g.f10226j;
            }
        }

        private a() {
        }

        public /* synthetic */ a(d7.g gVar) {
            this();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final g c(c7.l lVar, Object obj) {
            k.e(lVar, "$tmp0");
            return (g) lVar.g(obj);
        }

        public final i4.d b(Context context) {
            k.e(context, "context");
            g1.e.j(context);
            i4.d u7 = g.f10226j.u(context);
            final C0141a c0141a = new C0141a(context);
            i4.d G = n.G(u7, new m.a() { // from class: q0.f
                @Override // m.a
                public final Object apply(Object obj) {
                    g c8;
                    c8 = g.a.c(c7.l.this, obj);
                    return c8;
                }
            }, e0.c.b());
            k.d(G, "context: Context): Liste…tExecutor()\n            )");
            return G;
        }
    }

    /* loaded from: classes.dex */
    public static final class b implements f0.c {

        /* renamed from: a  reason: collision with root package name */
        final /* synthetic */ c.a f10236a;

        /* renamed from: b  reason: collision with root package name */
        final /* synthetic */ w f10237b;

        b(c.a aVar, w wVar) {
            this.f10236a = aVar;
            this.f10237b = wVar;
        }

        @Override // f0.c
        public void b(Throwable th) {
            k.e(th, "t");
            this.f10236a.f(th);
        }

        @Override // f0.c
        /* renamed from: c */
        public void a(Void r22) {
            this.f10236a.c(this.f10237b);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static final class c extends l implements c7.l {

        /* renamed from: f  reason: collision with root package name */
        final /* synthetic */ w f10238f;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        c(w wVar) {
            super(1);
            this.f10238f = wVar;
        }

        @Override // c7.l
        /* renamed from: b */
        public final i4.d g(Void r12) {
            return this.f10238f.i();
        }
    }

    private g() {
        i4.d p8 = n.p(null);
        k.d(p8, "immediateFuture<Void>(null)");
        this.f10230d = p8;
        this.f10231e = new q0.c();
        this.f10234h = new HashMap();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void A(Context context) {
        this.f10233g = context;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final z q(q qVar, p pVar) {
        Iterator it = qVar.c().iterator();
        z zVar = null;
        while (it.hasNext()) {
            Object next = it.next();
            k.d(next, "cameraSelector.cameraFilterSet");
            o oVar = (o) next;
            if (!k.a(oVar.a(), o.f12867a)) {
                b0 a8 = i1.a(oVar.a());
                Context context = this.f10233g;
                k.b(context);
                z a9 = a8.a(pVar, context);
                if (a9 == null) {
                    continue;
                } else if (zVar != null) {
                    throw new IllegalArgumentException("Cannot apply multiple extended camera configs at the same time.");
                } else {
                    zVar = a9;
                }
            }
        }
        return zVar == null ? c0.a() : zVar;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final int s() {
        w wVar = this.f10232f;
        if (wVar == null) {
            return 0;
        }
        k.b(wVar);
        return wVar.e().d().a();
    }

    public static final i4.d t(Context context) {
        return f10225i.b(context);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final i4.d u(Context context) {
        synchronized (this.f10227a) {
            i4.d dVar = this.f10229c;
            if (dVar != null) {
                k.c(dVar, "null cannot be cast to non-null type com.google.common.util.concurrent.ListenableFuture<androidx.camera.core.CameraX>");
                return dVar;
            }
            final w wVar = new w(context, this.f10228b);
            i4.d a8 = androidx.concurrent.futures.c.a(new c.InterfaceC0018c() { // from class: q0.d
                @Override // androidx.concurrent.futures.c.InterfaceC0018c
                public final Object a(c.a aVar) {
                    Object v7;
                    v7 = g.v(g.this, wVar, aVar);
                    return v7;
                }
            });
            this.f10229c = a8;
            k.c(a8, "null cannot be cast to non-null type com.google.common.util.concurrent.ListenableFuture<androidx.camera.core.CameraX>");
            return a8;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Object v(g gVar, w wVar, c.a aVar) {
        k.e(gVar, "this$0");
        k.e(wVar, "$cameraX");
        k.e(aVar, "completer");
        synchronized (gVar.f10227a) {
            f0.d a8 = f0.d.a(gVar.f10230d);
            final c cVar = new c(wVar);
            f0.d f8 = a8.f(new f0.a() { // from class: q0.e
                @Override // f0.a
                public final i4.d apply(Object obj) {
                    i4.d w7;
                    w7 = g.w(c7.l.this, obj);
                    return w7;
                }
            }, e0.c.b());
            k.d(f8, "cameraX = CameraX(contex…                        )");
            n.j(f8, new b(aVar, wVar), e0.c.b());
            r rVar = r.f11372a;
        }
        return "ProcessCameraProvider-initializeCameraX";
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final i4.d w(c7.l lVar, Object obj) {
        k.e(lVar, "$tmp0");
        return (i4.d) lVar.g(obj);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void y(int i8) {
        w wVar = this.f10232f;
        if (wVar == null) {
            return;
        }
        k.b(wVar);
        wVar.e().d().c(i8);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void z(w wVar) {
        this.f10232f = wVar;
    }

    public void B(h2... h2VarArr) {
        List h8;
        k.e(h2VarArr, "useCases");
        z1.a.c("CX:unbind");
        try {
            d0.q.a();
            if (s() == 2) {
                throw new UnsupportedOperationException("Unbind usecase is not supported in concurrent camera mode, call unbindAll() first.");
            }
            q0.c cVar = this.f10231e;
            h8 = s6.n.h(Arrays.copyOf(h2VarArr, h2VarArr.length));
            cVar.k(h8);
            r rVar = r.f11372a;
        } finally {
            z1.a.f();
        }
    }

    public void C() {
        z1.a.c("CX:unbindAll");
        try {
            d0.q.a();
            y(0);
            this.f10231e.l();
            r rVar = r.f11372a;
        } finally {
            z1.a.f();
        }
    }

    public final j n(m mVar, q qVar, h2... h2VarArr) {
        List f8;
        k.e(mVar, "lifecycleOwner");
        k.e(qVar, "cameraSelector");
        k.e(h2VarArr, "useCases");
        z1.a.c("CX:bindToLifecycle");
        try {
            if (s() != 2) {
                y(1);
                e1 e1Var = e1.f12769f;
                k.d(e1Var, "DEFAULT");
                k.d(e1Var, "DEFAULT");
                f8 = s6.n.f();
                return o(mVar, qVar, null, e1Var, e1Var, null, f8, (h2[]) Arrays.copyOf(h2VarArr, h2VarArr.length));
            }
            throw new UnsupportedOperationException("bindToLifecycle for single camera is not supported in concurrent camera mode, call unbindAll() first");
        } finally {
            z1.a.f();
        }
    }

    public final j o(m mVar, q qVar, q qVar2, e1 e1Var, e1 e1Var2, i2 i2Var, List list, h2... h2VarArr) {
        h0 h0Var;
        o2 o2Var;
        List<h2> h8;
        List h9;
        k.e(mVar, "lifecycleOwner");
        k.e(qVar, "primaryCameraSelector");
        k.e(e1Var, "primaryLayoutSettings");
        k.e(e1Var2, "secondaryLayoutSettings");
        k.e(list, "effects");
        k.e(h2VarArr, "useCases");
        z1.a.c("CX:bindToLifecycle-internal");
        try {
            d0.q.a();
            w wVar = this.f10232f;
            k.b(wVar);
            h0 e8 = qVar.e(wVar.f().a());
            k.d(e8, "primaryCameraSelector.se…cameraRepository.cameras)");
            boolean z7 = true;
            e8.k(true);
            p r8 = r(qVar);
            k.c(r8, "null cannot be cast to non-null type androidx.camera.core.impl.RestrictedCameraInfo");
            o2 o2Var2 = (o2) r8;
            if (qVar2 != null) {
                w wVar2 = this.f10232f;
                k.b(wVar2);
                h0 e9 = qVar2.e(wVar2.f().a());
                e9.k(false);
                p r9 = r(qVar2);
                k.c(r9, "null cannot be cast to non-null type androidx.camera.core.impl.RestrictedCameraInfo");
                o2Var = (o2) r9;
                h0Var = e9;
            } else {
                h0Var = null;
                o2Var = null;
            }
            q0.b c8 = this.f10231e.c(mVar, g0.f.A(o2Var2, o2Var));
            Collection e10 = this.f10231e.e();
            h8 = s6.j.h(h2VarArr);
            for (h2 h2Var : h8) {
                for (Object obj : e10) {
                    k.d(obj, "lifecycleCameras");
                    q0.b bVar = (q0.b) obj;
                    if (bVar.u(h2Var) && !k.a(bVar, c8)) {
                        u uVar = u.f6487a;
                        String format = String.format("Use case %s already bound to a different lifecycle.", Arrays.copyOf(new Object[]{h2Var}, 1));
                        k.d(format, "format(format, *args)");
                        throw new IllegalStateException(format);
                    }
                    z7 = true;
                }
            }
            if (c8 == null) {
                q0.c cVar = this.f10231e;
                w wVar3 = this.f10232f;
                k.b(wVar3);
                z.a d8 = wVar3.e().d();
                w wVar4 = this.f10232f;
                k.b(wVar4);
                e0 d9 = wVar4.d();
                w wVar5 = this.f10232f;
                k.b(wVar5);
                c8 = cVar.b(mVar, new g0.f(e8, h0Var, o2Var2, o2Var, e1Var, e1Var2, d8, d9, wVar5.h()));
            }
            if (h2VarArr.length != 0) {
                z7 = false;
            }
            if (z7) {
                k.b(c8);
            } else {
                q0.c cVar2 = this.f10231e;
                k.b(c8);
                h9 = s6.n.h(Arrays.copyOf(h2VarArr, h2VarArr.length));
                List list2 = h9;
                w wVar6 = this.f10232f;
                k.b(wVar6);
                cVar2.a(c8, i2Var, list, list2, wVar6.e().d());
            }
            return c8;
        } finally {
            z1.a.f();
        }
    }

    public List p() {
        z1.a.c("CX:getAvailableCameraInfos");
        try {
            ArrayList arrayList = new ArrayList();
            w wVar = this.f10232f;
            k.b(wVar);
            LinkedHashSet<h0> a8 = wVar.f().a();
            k.d(a8, "mCameraX!!.cameraRepository.cameras");
            for (h0 h0Var : a8) {
                p b8 = h0Var.b();
                k.d(b8, "camera.cameraInfo");
                arrayList.add(b8);
            }
            return arrayList;
        } finally {
            z1.a.f();
        }
    }

    public p r(q qVar) {
        Object obj;
        k.e(qVar, "cameraSelector");
        z1.a.c("CX:getCameraInfo");
        try {
            w wVar = this.f10232f;
            k.b(wVar);
            g0 l8 = qVar.e(wVar.f().a()).l();
            k.d(l8, "cameraSelector.select(mC…meras).cameraInfoInternal");
            z q8 = q(qVar, l8);
            f.b a8 = f.b.a(l8.g(), q8.G());
            k.d(a8, "create(\n                …ilityId\n                )");
            synchronized (this.f10227a) {
                obj = this.f10234h.get(a8);
                if (obj == null) {
                    obj = new o2(l8, q8);
                    this.f10234h.put(a8, obj);
                }
                r rVar = r.f11372a;
            }
            return (o2) obj;
        } finally {
            z1.a.f();
        }
    }

    public boolean x(h2 h2Var) {
        k.e(h2Var, "useCase");
        for (Object obj : this.f10231e.e()) {
            k.d(obj, "mLifecycleCameraRepository.lifecycleCameras");
            if (((q0.b) obj).u(h2Var)) {
                return true;
            }
        }
        return false;
    }
}
