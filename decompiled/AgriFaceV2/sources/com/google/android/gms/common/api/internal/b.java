package com.google.android.gms.common.api.internal;

import android.app.Application;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.c;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicInteger;
/* loaded from: classes.dex */
public class b implements Handler.Callback {

    /* renamed from: p  reason: collision with root package name */
    public static final Status f4978p = new Status(4, "Sign-out occurred while this API call was in progress.");

    /* renamed from: q  reason: collision with root package name */
    private static final Status f4979q = new Status(4, "The user must be signed in to make this API call.");

    /* renamed from: r  reason: collision with root package name */
    private static final Object f4980r = new Object();

    /* renamed from: s  reason: collision with root package name */
    private static b f4981s;

    /* renamed from: c  reason: collision with root package name */
    private k3.t f4984c;

    /* renamed from: d  reason: collision with root package name */
    private k3.v f4985d;

    /* renamed from: e  reason: collision with root package name */
    private final Context f4986e;

    /* renamed from: f  reason: collision with root package name */
    private final h3.d f4987f;

    /* renamed from: g  reason: collision with root package name */
    private final k3.h0 f4988g;

    /* renamed from: n  reason: collision with root package name */
    private final Handler f4995n;

    /* renamed from: o  reason: collision with root package name */
    private volatile boolean f4996o;

    /* renamed from: a  reason: collision with root package name */
    private long f4982a = 10000;

    /* renamed from: b  reason: collision with root package name */
    private boolean f4983b = false;

    /* renamed from: h  reason: collision with root package name */
    private final AtomicInteger f4989h = new AtomicInteger(1);

    /* renamed from: i  reason: collision with root package name */
    private final AtomicInteger f4990i = new AtomicInteger(0);

    /* renamed from: j  reason: collision with root package name */
    private final Map f4991j = new ConcurrentHashMap(5, 0.75f, 1);

    /* renamed from: k  reason: collision with root package name */
    private k f4992k = null;

    /* renamed from: l  reason: collision with root package name */
    private final Set f4993l = new b1.b();

    /* renamed from: m  reason: collision with root package name */
    private final Set f4994m = new b1.b();

    private b(Context context, Looper looper, h3.d dVar) {
        this.f4996o = true;
        this.f4986e = context;
        v3.h hVar = new v3.h(looper, this);
        this.f4995n = hVar;
        this.f4987f = dVar;
        this.f4988g = new k3.h0(dVar);
        if (q3.e.a(context)) {
            this.f4996o = false;
        }
        hVar.sendMessage(hVar.obtainMessage(6));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static Status f(j3.b bVar, h3.a aVar) {
        String b8 = bVar.b();
        String valueOf = String.valueOf(aVar);
        return new Status(aVar, "API: " + b8 + " is not available on this device. Connection failed with: " + valueOf);
    }

    private final q g(i3.e eVar) {
        Map map = this.f4991j;
        j3.b m8 = eVar.m();
        q qVar = (q) map.get(m8);
        if (qVar == null) {
            qVar = new q(this, eVar);
            this.f4991j.put(m8, qVar);
        }
        if (qVar.a()) {
            this.f4994m.add(m8);
        }
        qVar.E();
        return qVar;
    }

    private final k3.v h() {
        if (this.f4985d == null) {
            this.f4985d = k3.u.a(this.f4986e);
        }
        return this.f4985d;
    }

    private final void i() {
        k3.t tVar = this.f4984c;
        if (tVar != null) {
            if (tVar.a() > 0 || d()) {
                h().a(tVar);
            }
            this.f4984c = null;
        }
    }

    private final void j(f4.k kVar, int i8, i3.e eVar) {
        v b8;
        if (i8 == 0 || (b8 = v.b(this, i8, eVar.m())) == null) {
            return;
        }
        f4.j a8 = kVar.a();
        final Handler handler = this.f4995n;
        handler.getClass();
        a8.b(new Executor() { // from class: j3.m
            @Override // java.util.concurrent.Executor
            public final void execute(Runnable runnable) {
                handler.post(runnable);
            }
        }, b8);
    }

    public static b t(Context context) {
        b bVar;
        synchronized (f4980r) {
            if (f4981s == null) {
                f4981s = new b(context.getApplicationContext(), k3.i.b().getLooper(), h3.d.m());
            }
            bVar = f4981s;
        }
        return bVar;
    }

    public final void B(i3.e eVar, int i8, g gVar, f4.k kVar, j3.j jVar) {
        j(kVar, gVar.d(), eVar);
        this.f4995n.sendMessage(this.f4995n.obtainMessage(4, new j3.s(new c0(i8, gVar, kVar, jVar), this.f4990i.get(), eVar)));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void C(k3.o oVar, int i8, long j8, int i9) {
        this.f4995n.sendMessage(this.f4995n.obtainMessage(18, new w(oVar, i8, j8, i9)));
    }

    public final void D(h3.a aVar, int i8) {
        if (e(aVar, i8)) {
            return;
        }
        Handler handler = this.f4995n;
        handler.sendMessage(handler.obtainMessage(5, i8, 0, aVar));
    }

    public final void E() {
        Handler handler = this.f4995n;
        handler.sendMessage(handler.obtainMessage(3));
    }

    public final void F(i3.e eVar) {
        Handler handler = this.f4995n;
        handler.sendMessage(handler.obtainMessage(7, eVar));
    }

    public final void a(k kVar) {
        synchronized (f4980r) {
            if (this.f4992k != kVar) {
                this.f4992k = kVar;
                this.f4993l.clear();
            }
            this.f4993l.addAll(kVar.t());
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void b(k kVar) {
        synchronized (f4980r) {
            if (this.f4992k == kVar) {
                this.f4992k = null;
                this.f4993l.clear();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final boolean d() {
        if (this.f4983b) {
            return false;
        }
        k3.s a8 = k3.r.b().a();
        if (a8 == null || a8.c()) {
            int a9 = this.f4988g.a(this.f4986e, 203400000);
            return a9 == -1 || a9 == 0;
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final boolean e(h3.a aVar, int i8) {
        return this.f4987f.w(this.f4986e, aVar, i8);
    }

    @Override // android.os.Handler.Callback
    public final boolean handleMessage(Message message) {
        q qVar;
        j3.b bVar;
        j3.b bVar2;
        j3.b bVar3;
        j3.b bVar4;
        int i8 = message.what;
        switch (i8) {
            case 1:
                this.f4982a = true == ((Boolean) message.obj).booleanValue() ? 10000L : 300000L;
                this.f4995n.removeMessages(12);
                for (j3.b bVar5 : this.f4991j.keySet()) {
                    Handler handler = this.f4995n;
                    handler.sendMessageDelayed(handler.obtainMessage(12, bVar5), this.f4982a);
                }
                break;
            case 2:
                androidx.appcompat.app.w.a(message.obj);
                throw null;
            case 3:
                for (q qVar2 : this.f4991j.values()) {
                    qVar2.D();
                    qVar2.E();
                }
                break;
            case 4:
            case 8:
            case 13:
                j3.s sVar = (j3.s) message.obj;
                q qVar3 = (q) this.f4991j.get(sVar.f8237c.m());
                if (qVar3 == null) {
                    qVar3 = g(sVar.f8237c);
                }
                if (!qVar3.a() || this.f4990i.get() == sVar.f8236b) {
                    qVar3.F(sVar.f8235a);
                    break;
                } else {
                    sVar.f8235a.a(f4978p);
                    qVar3.K();
                    break;
                }
            case 5:
                int i9 = message.arg1;
                h3.a aVar = (h3.a) message.obj;
                Iterator it = this.f4991j.values().iterator();
                while (true) {
                    if (it.hasNext()) {
                        q qVar4 = (q) it.next();
                        qVar = qVar4.s() == i9 ? qVar4 : null;
                    }
                }
                if (qVar != null) {
                    if (aVar.a() == 13) {
                        String e8 = this.f4987f.e(aVar.a());
                        String b8 = aVar.b();
                        q.y(qVar, new Status(17, "Error resolution was canceled by the user, original error message: " + e8 + ": " + b8));
                        break;
                    } else {
                        q.y(qVar, f(q.w(qVar), aVar));
                        break;
                    }
                } else {
                    Log.wtf("GoogleApiManager", "Could not find API instance " + i9 + " while trying to fail enqueued calls.", new Exception());
                    break;
                }
            case androidx.browser.customtabs.b.TAB_HIDDEN /* 6 */:
                if (this.f4986e.getApplicationContext() instanceof Application) {
                    a.c((Application) this.f4986e.getApplicationContext());
                    a.b().a(new l(this));
                    if (!a.b().e(true)) {
                        this.f4982a = 300000L;
                        break;
                    }
                }
                break;
            case 7:
                g((i3.e) message.obj);
                break;
            case 9:
                if (this.f4991j.containsKey(message.obj)) {
                    ((q) this.f4991j.get(message.obj)).J();
                    break;
                }
                break;
            case 10:
                for (j3.b bVar6 : this.f4994m) {
                    q qVar5 = (q) this.f4991j.remove(bVar6);
                    if (qVar5 != null) {
                        qVar5.K();
                    }
                }
                this.f4994m.clear();
                break;
            case 11:
                if (this.f4991j.containsKey(message.obj)) {
                    ((q) this.f4991j.get(message.obj)).L();
                    break;
                }
                break;
            case 12:
                if (this.f4991j.containsKey(message.obj)) {
                    ((q) this.f4991j.get(message.obj)).b();
                    break;
                }
                break;
            case 14:
                androidx.appcompat.app.w.a(message.obj);
                throw null;
            case 15:
                r rVar = (r) message.obj;
                Map map = this.f4991j;
                bVar = rVar.f5065a;
                if (map.containsKey(bVar)) {
                    Map map2 = this.f4991j;
                    bVar2 = rVar.f5065a;
                    q.B((q) map2.get(bVar2), rVar);
                    break;
                }
                break;
            case 16:
                r rVar2 = (r) message.obj;
                Map map3 = this.f4991j;
                bVar3 = rVar2.f5065a;
                if (map3.containsKey(bVar3)) {
                    Map map4 = this.f4991j;
                    bVar4 = rVar2.f5065a;
                    q.C((q) map4.get(bVar4), rVar2);
                    break;
                }
                break;
            case 17:
                i();
                break;
            case 18:
                w wVar = (w) message.obj;
                if (wVar.f5084c == 0) {
                    h().a(new k3.t(wVar.f5083b, Arrays.asList(wVar.f5082a)));
                    break;
                } else {
                    k3.t tVar = this.f4984c;
                    if (tVar != null) {
                        List b9 = tVar.b();
                        if (tVar.a() != wVar.f5083b || (b9 != null && b9.size() >= wVar.f5085d)) {
                            this.f4995n.removeMessages(17);
                            i();
                        } else {
                            this.f4984c.c(wVar.f5082a);
                        }
                    }
                    if (this.f4984c == null) {
                        ArrayList arrayList = new ArrayList();
                        arrayList.add(wVar.f5082a);
                        this.f4984c = new k3.t(wVar.f5083b, arrayList);
                        Handler handler2 = this.f4995n;
                        handler2.sendMessageDelayed(handler2.obtainMessage(17), wVar.f5084c);
                        break;
                    }
                }
                break;
            case 19:
                this.f4983b = false;
                break;
            default:
                Log.w("GoogleApiManager", "Unknown message id: " + i8);
                return false;
        }
        return true;
    }

    public final int k() {
        return this.f4989h.getAndIncrement();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final q s(j3.b bVar) {
        return (q) this.f4991j.get(bVar);
    }

    public final f4.j v(i3.e eVar, e eVar2, h hVar, Runnable runnable) {
        f4.k kVar = new f4.k();
        j(kVar, eVar2.e(), eVar);
        this.f4995n.sendMessage(this.f4995n.obtainMessage(8, new j3.s(new b0(new j3.t(eVar2, hVar, runnable), kVar), this.f4990i.get(), eVar)));
        return kVar.a();
    }

    public final f4.j w(i3.e eVar, c.a aVar, int i8) {
        f4.k kVar = new f4.k();
        j(kVar, i8, eVar);
        this.f4995n.sendMessage(this.f4995n.obtainMessage(13, new j3.s(new d0(aVar, kVar), this.f4990i.get(), eVar)));
        return kVar.a();
    }
}
