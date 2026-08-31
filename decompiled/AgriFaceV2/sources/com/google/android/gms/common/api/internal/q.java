package com.google.android.gms.common.api.internal;

import android.content.Context;
import android.os.Bundle;
import android.os.DeadObjectException;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.c;
import i3.a;
import i3.f;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
/* loaded from: classes.dex */
public final class q implements f.a, f.b {

    /* renamed from: f */
    private final a.f f5053f;

    /* renamed from: g */
    private final j3.b f5054g;

    /* renamed from: h */
    private final j f5055h;

    /* renamed from: k */
    private final int f5058k;

    /* renamed from: l */
    private final j3.a0 f5059l;

    /* renamed from: m */
    private boolean f5060m;

    /* renamed from: q */
    final /* synthetic */ b f5064q;

    /* renamed from: e */
    private final Queue f5052e = new LinkedList();

    /* renamed from: i */
    private final Set f5056i = new HashSet();

    /* renamed from: j */
    private final Map f5057j = new HashMap();

    /* renamed from: n */
    private final List f5061n = new ArrayList();

    /* renamed from: o */
    private h3.a f5062o = null;

    /* renamed from: p */
    private int f5063p = 0;

    public q(b bVar, i3.e eVar) {
        Handler handler;
        Context context;
        Handler handler2;
        this.f5064q = bVar;
        handler = bVar.f4995n;
        a.f p8 = eVar.p(handler.getLooper(), this);
        this.f5053f = p8;
        this.f5054g = eVar.m();
        this.f5055h = new j();
        this.f5058k = eVar.o();
        if (!p8.o()) {
            this.f5059l = null;
            return;
        }
        context = bVar.f4986e;
        handler2 = bVar.f4995n;
        this.f5059l = eVar.q(context, handler2);
    }

    public static /* bridge */ /* synthetic */ void B(q qVar, r rVar) {
        if (qVar.f5061n.contains(rVar) && !qVar.f5060m) {
            if (qVar.f5053f.a()) {
                qVar.j();
            } else {
                qVar.E();
            }
        }
    }

    public static /* bridge */ /* synthetic */ void C(q qVar, r rVar) {
        Handler handler;
        Handler handler2;
        h3.c cVar;
        h3.c[] g8;
        if (qVar.f5061n.remove(rVar)) {
            handler = qVar.f5064q.f4995n;
            handler.removeMessages(15, rVar);
            handler2 = qVar.f5064q.f4995n;
            handler2.removeMessages(16, rVar);
            cVar = rVar.f5066b;
            ArrayList arrayList = new ArrayList(qVar.f5052e.size());
            for (e0 e0Var : qVar.f5052e) {
                if ((e0Var instanceof j3.r) && (g8 = ((j3.r) e0Var).g(qVar)) != null && q3.a.b(g8, cVar)) {
                    arrayList.add(e0Var);
                }
            }
            int size = arrayList.size();
            for (int i8 = 0; i8 < size; i8++) {
                e0 e0Var2 = (e0) arrayList.get(i8);
                qVar.f5052e.remove(e0Var2);
                e0Var2.b(new i3.k(cVar));
            }
        }
    }

    private final h3.c c(h3.c[] cVarArr) {
        if (cVarArr != null && cVarArr.length != 0) {
            h3.c[] i8 = this.f5053f.i();
            if (i8 == null) {
                i8 = new h3.c[0];
            }
            b1.a aVar = new b1.a(i8.length);
            for (h3.c cVar : i8) {
                aVar.put(cVar.a(), Long.valueOf(cVar.b()));
            }
            for (h3.c cVar2 : cVarArr) {
                Long l8 = (Long) aVar.get(cVar2.a());
                if (l8 == null || l8.longValue() < cVar2.b()) {
                    return cVar2;
                }
            }
        }
        return null;
    }

    private final void g(h3.a aVar) {
        Iterator it = this.f5056i.iterator();
        if (!it.hasNext()) {
            this.f5056i.clear();
            return;
        }
        androidx.appcompat.app.w.a(it.next());
        if (k3.p.a(aVar, h3.a.f7220e)) {
            this.f5053f.k();
        }
        throw null;
    }

    public final void h(Status status) {
        Handler handler;
        handler = this.f5064q.f4995n;
        k3.q.d(handler);
        i(status, null, false);
    }

    private final void i(Status status, Exception exc, boolean z7) {
        Handler handler;
        handler = this.f5064q.f4995n;
        k3.q.d(handler);
        if ((status == null) == (exc == null)) {
            throw new IllegalArgumentException("Status XOR exception should be null");
        }
        Iterator it = this.f5052e.iterator();
        while (it.hasNext()) {
            e0 e0Var = (e0) it.next();
            if (!z7 || e0Var.f5011a == 2) {
                if (status != null) {
                    e0Var.a(status);
                } else {
                    e0Var.b(exc);
                }
                it.remove();
            }
        }
    }

    private final void j() {
        ArrayList arrayList = new ArrayList(this.f5052e);
        int size = arrayList.size();
        for (int i8 = 0; i8 < size; i8++) {
            e0 e0Var = (e0) arrayList.get(i8);
            if (!this.f5053f.a()) {
                return;
            }
            if (p(e0Var)) {
                this.f5052e.remove(e0Var);
            }
        }
    }

    public final void k() {
        D();
        g(h3.a.f7220e);
        o();
        Iterator it = this.f5057j.values().iterator();
        while (it.hasNext()) {
            j3.t tVar = (j3.t) it.next();
            if (c(tVar.f8238a.c()) == null) {
                try {
                    tVar.f8238a.d(this.f5053f, new f4.k());
                } catch (DeadObjectException unused) {
                    d(3);
                    this.f5053f.d("DeadObjectException thrown while calling register listener method.");
                } catch (RemoteException unused2) {
                }
            }
            it.remove();
        }
        j();
        m();
    }

    public final void l(int i8) {
        Handler handler;
        Handler handler2;
        Handler handler3;
        Handler handler4;
        k3.h0 h0Var;
        D();
        this.f5060m = true;
        this.f5055h.c(i8, this.f5053f.m());
        j3.b bVar = this.f5054g;
        b bVar2 = this.f5064q;
        handler = bVar2.f4995n;
        handler2 = bVar2.f4995n;
        handler.sendMessageDelayed(Message.obtain(handler2, 9, bVar), 5000L);
        j3.b bVar3 = this.f5054g;
        b bVar4 = this.f5064q;
        handler3 = bVar4.f4995n;
        handler4 = bVar4.f4995n;
        handler3.sendMessageDelayed(Message.obtain(handler4, 11, bVar3), 120000L);
        h0Var = this.f5064q.f4988g;
        h0Var.c();
        for (j3.t tVar : this.f5057j.values()) {
            tVar.f8240c.run();
        }
    }

    private final void m() {
        Handler handler;
        Handler handler2;
        Handler handler3;
        long j8;
        j3.b bVar = this.f5054g;
        handler = this.f5064q.f4995n;
        handler.removeMessages(12, bVar);
        j3.b bVar2 = this.f5054g;
        b bVar3 = this.f5064q;
        handler2 = bVar3.f4995n;
        handler3 = bVar3.f4995n;
        Message obtainMessage = handler3.obtainMessage(12, bVar2);
        j8 = this.f5064q.f4982a;
        handler2.sendMessageDelayed(obtainMessage, j8);
    }

    private final void n(e0 e0Var) {
        e0Var.d(this.f5055h, a());
        try {
            e0Var.c(this);
        } catch (DeadObjectException unused) {
            d(1);
            this.f5053f.d("DeadObjectException thrown while running ApiCallRunner.");
        }
    }

    private final void o() {
        Handler handler;
        Handler handler2;
        if (this.f5060m) {
            b bVar = this.f5064q;
            j3.b bVar2 = this.f5054g;
            handler = bVar.f4995n;
            handler.removeMessages(11, bVar2);
            b bVar3 = this.f5064q;
            j3.b bVar4 = this.f5054g;
            handler2 = bVar3.f4995n;
            handler2.removeMessages(9, bVar4);
            this.f5060m = false;
        }
    }

    private final boolean p(e0 e0Var) {
        boolean z7;
        Handler handler;
        Handler handler2;
        Handler handler3;
        Handler handler4;
        Handler handler5;
        Handler handler6;
        Handler handler7;
        if (!(e0Var instanceof j3.r)) {
            n(e0Var);
            return true;
        }
        j3.r rVar = (j3.r) e0Var;
        h3.c c8 = c(rVar.g(this));
        if (c8 == null) {
            n(e0Var);
            return true;
        }
        String name = this.f5053f.getClass().getName();
        String a8 = c8.a();
        long b8 = c8.b();
        Log.w("GoogleApiManager", name + " could not execute call because it requires feature (" + a8 + ", " + b8 + ").");
        z7 = this.f5064q.f4996o;
        if (!z7 || !rVar.f(this)) {
            rVar.b(new i3.k(c8));
            return true;
        }
        r rVar2 = new r(this.f5054g, c8, null);
        int indexOf = this.f5061n.indexOf(rVar2);
        if (indexOf >= 0) {
            r rVar3 = (r) this.f5061n.get(indexOf);
            handler5 = this.f5064q.f4995n;
            handler5.removeMessages(15, rVar3);
            b bVar = this.f5064q;
            handler6 = bVar.f4995n;
            handler7 = bVar.f4995n;
            handler6.sendMessageDelayed(Message.obtain(handler7, 15, rVar3), 5000L);
            return false;
        }
        this.f5061n.add(rVar2);
        b bVar2 = this.f5064q;
        handler = bVar2.f4995n;
        handler2 = bVar2.f4995n;
        handler.sendMessageDelayed(Message.obtain(handler2, 15, rVar2), 5000L);
        b bVar3 = this.f5064q;
        handler3 = bVar3.f4995n;
        handler4 = bVar3.f4995n;
        handler3.sendMessageDelayed(Message.obtain(handler4, 16, rVar2), 120000L);
        h3.a aVar = new h3.a(2, null);
        if (q(aVar)) {
            return false;
        }
        this.f5064q.e(aVar, this.f5058k);
        return false;
    }

    private final boolean q(h3.a aVar) {
        Object obj;
        k kVar;
        Set set;
        k kVar2;
        obj = b.f4980r;
        synchronized (obj) {
            b bVar = this.f5064q;
            kVar = bVar.f4992k;
            if (kVar != null) {
                set = bVar.f4993l;
                if (set.contains(this.f5054g)) {
                    kVar2 = this.f5064q.f4992k;
                    kVar2.s(aVar, this.f5058k);
                    return true;
                }
            }
            return false;
        }
    }

    private final boolean r(boolean z7) {
        Handler handler;
        handler = this.f5064q.f4995n;
        k3.q.d(handler);
        if (this.f5053f.a() && this.f5057j.isEmpty()) {
            if (!this.f5055h.e()) {
                this.f5053f.d("Timing out service connection.");
                return true;
            } else if (z7) {
                m();
                return false;
            } else {
                return false;
            }
        }
        return false;
    }

    public static /* bridge */ /* synthetic */ j3.b w(q qVar) {
        return qVar.f5054g;
    }

    public static /* bridge */ /* synthetic */ void y(q qVar, Status status) {
        qVar.h(status);
    }

    public final void D() {
        Handler handler;
        handler = this.f5064q.f4995n;
        k3.q.d(handler);
        this.f5062o = null;
    }

    public final void E() {
        Handler handler;
        h3.a aVar;
        k3.h0 h0Var;
        Context context;
        handler = this.f5064q.f4995n;
        k3.q.d(handler);
        if (this.f5053f.a() || this.f5053f.h()) {
            return;
        }
        try {
            b bVar = this.f5064q;
            h0Var = bVar.f4988g;
            context = bVar.f4986e;
            int b8 = h0Var.b(context, this.f5053f);
            if (b8 != 0) {
                h3.a aVar2 = new h3.a(b8, null);
                String name = this.f5053f.getClass().getName();
                String obj = aVar2.toString();
                Log.w("GoogleApiManager", "The service for " + name + " is not available: " + obj);
                H(aVar2, null);
                return;
            }
            b bVar2 = this.f5064q;
            a.f fVar = this.f5053f;
            t tVar = new t(bVar2, fVar, this.f5054g);
            if (fVar.o()) {
                ((j3.a0) k3.q.g(this.f5059l)).g0(tVar);
            }
            try {
                this.f5053f.l(tVar);
            } catch (SecurityException e8) {
                e = e8;
                aVar = new h3.a(10);
                H(aVar, e);
            }
        } catch (IllegalStateException e9) {
            e = e9;
            aVar = new h3.a(10);
        }
    }

    public final void F(e0 e0Var) {
        Handler handler;
        handler = this.f5064q.f4995n;
        k3.q.d(handler);
        if (this.f5053f.a()) {
            if (p(e0Var)) {
                m();
                return;
            } else {
                this.f5052e.add(e0Var);
                return;
            }
        }
        this.f5052e.add(e0Var);
        h3.a aVar = this.f5062o;
        if (aVar == null || !aVar.d()) {
            E();
        } else {
            H(this.f5062o, null);
        }
    }

    public final void G() {
        this.f5063p++;
    }

    public final void H(h3.a aVar, Exception exc) {
        Handler handler;
        k3.h0 h0Var;
        boolean z7;
        Status f8;
        Status f9;
        Status f10;
        Handler handler2;
        Handler handler3;
        Handler handler4;
        Status status;
        Handler handler5;
        Handler handler6;
        handler = this.f5064q.f4995n;
        k3.q.d(handler);
        j3.a0 a0Var = this.f5059l;
        if (a0Var != null) {
            a0Var.h0();
        }
        D();
        h0Var = this.f5064q.f4988g;
        h0Var.c();
        g(aVar);
        if ((this.f5053f instanceof m3.e) && aVar.a() != 24) {
            this.f5064q.f4983b = true;
            b bVar = this.f5064q;
            handler5 = bVar.f4995n;
            handler6 = bVar.f4995n;
            handler5.sendMessageDelayed(handler6.obtainMessage(19), 300000L);
        }
        if (aVar.a() == 4) {
            status = b.f4979q;
            h(status);
        } else if (this.f5052e.isEmpty()) {
            this.f5062o = aVar;
        } else if (exc != null) {
            handler4 = this.f5064q.f4995n;
            k3.q.d(handler4);
            i(null, exc, false);
        } else {
            z7 = this.f5064q.f4996o;
            if (!z7) {
                f8 = b.f(this.f5054g, aVar);
                h(f8);
                return;
            }
            f9 = b.f(this.f5054g, aVar);
            i(f9, null, true);
            if (this.f5052e.isEmpty() || q(aVar) || this.f5064q.e(aVar, this.f5058k)) {
                return;
            }
            if (aVar.a() == 18) {
                this.f5060m = true;
            }
            if (!this.f5060m) {
                f10 = b.f(this.f5054g, aVar);
                h(f10);
                return;
            }
            b bVar2 = this.f5064q;
            j3.b bVar3 = this.f5054g;
            handler2 = bVar2.f4995n;
            handler3 = bVar2.f4995n;
            handler2.sendMessageDelayed(Message.obtain(handler3, 9, bVar3), 5000L);
        }
    }

    public final void I(h3.a aVar) {
        Handler handler;
        handler = this.f5064q.f4995n;
        k3.q.d(handler);
        a.f fVar = this.f5053f;
        String name = fVar.getClass().getName();
        String valueOf = String.valueOf(aVar);
        fVar.d("onSignInFailed for " + name + " with " + valueOf);
        H(aVar, null);
    }

    public final void J() {
        Handler handler;
        handler = this.f5064q.f4995n;
        k3.q.d(handler);
        if (this.f5060m) {
            E();
        }
    }

    public final void K() {
        Handler handler;
        handler = this.f5064q.f4995n;
        k3.q.d(handler);
        h(b.f4978p);
        this.f5055h.d();
        for (c.a aVar : (c.a[]) this.f5057j.keySet().toArray(new c.a[0])) {
            F(new d0(aVar, new f4.k()));
        }
        g(new h3.a(4));
        if (this.f5053f.a()) {
            this.f5053f.f(new p(this));
        }
    }

    public final void L() {
        Handler handler;
        h3.d dVar;
        Context context;
        handler = this.f5064q.f4995n;
        k3.q.d(handler);
        if (this.f5060m) {
            o();
            b bVar = this.f5064q;
            dVar = bVar.f4987f;
            context = bVar.f4986e;
            h(dVar.g(context) == 18 ? new Status(21, "Connection timed out waiting for Google Play services update to complete.") : new Status(22, "API failed to connect while resuming due to an unknown error."));
            this.f5053f.d("Timing out connection while resuming.");
        }
    }

    public final boolean a() {
        return this.f5053f.o();
    }

    public final boolean b() {
        return r(true);
    }

    @Override // j3.c
    public final void d(int i8) {
        Handler handler;
        Handler handler2;
        b bVar = this.f5064q;
        Looper myLooper = Looper.myLooper();
        handler = bVar.f4995n;
        if (myLooper == handler.getLooper()) {
            l(i8);
            return;
        }
        handler2 = this.f5064q.f4995n;
        handler2.post(new n(this, i8));
    }

    @Override // j3.h
    public final void e(h3.a aVar) {
        H(aVar, null);
    }

    @Override // j3.c
    public final void f(Bundle bundle) {
        Handler handler;
        Handler handler2;
        b bVar = this.f5064q;
        Looper myLooper = Looper.myLooper();
        handler = bVar.f4995n;
        if (myLooper == handler.getLooper()) {
            k();
            return;
        }
        handler2 = this.f5064q.f4995n;
        handler2.post(new m(this));
    }

    public final int s() {
        return this.f5058k;
    }

    public final int t() {
        return this.f5063p;
    }

    public final a.f v() {
        return this.f5053f;
    }

    public final Map x() {
        return this.f5057j;
    }
}
