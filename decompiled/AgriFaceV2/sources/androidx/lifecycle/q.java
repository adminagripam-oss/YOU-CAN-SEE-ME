package androidx.lifecycle;

import java.util.Iterator;
import java.util.Map;
/* loaded from: classes.dex */
public abstract class q extends s {

    /* renamed from: l  reason: collision with root package name */
    private l.b f3837l = new l.b();

    /* loaded from: classes.dex */
    private static class a implements t {

        /* renamed from: a  reason: collision with root package name */
        final p f3838a;

        /* renamed from: b  reason: collision with root package name */
        final t f3839b;

        /* renamed from: c  reason: collision with root package name */
        int f3840c = -1;

        a(p pVar, t tVar) {
            this.f3838a = pVar;
            this.f3839b = tVar;
        }

        @Override // androidx.lifecycle.t
        public void a(Object obj) {
            if (this.f3840c != this.f3838a.f()) {
                this.f3840c = this.f3838a.f();
                this.f3839b.a(obj);
            }
        }

        void b() {
            this.f3838a.i(this);
        }

        void c() {
            this.f3838a.m(this);
        }
    }

    @Override // androidx.lifecycle.p
    protected void j() {
        Iterator it = this.f3837l.iterator();
        while (it.hasNext()) {
            ((a) ((Map.Entry) it.next()).getValue()).b();
        }
    }

    @Override // androidx.lifecycle.p
    protected void k() {
        Iterator it = this.f3837l.iterator();
        while (it.hasNext()) {
            ((a) ((Map.Entry) it.next()).getValue()).c();
        }
    }

    public void p(p pVar, t tVar) {
        if (pVar == null) {
            throw new NullPointerException("source cannot be null");
        }
        a aVar = new a(pVar, tVar);
        a aVar2 = (a) this.f3837l.n(pVar, aVar);
        if (aVar2 != null && aVar2.f3839b != tVar) {
            throw new IllegalArgumentException("This source was already added with the different observer");
        }
        if (aVar2 == null && g()) {
            aVar.b();
        }
    }

    public void q(p pVar) {
        a aVar = (a) this.f3837l.o(pVar);
        if (aVar != null) {
            aVar.c();
        }
    }
}
