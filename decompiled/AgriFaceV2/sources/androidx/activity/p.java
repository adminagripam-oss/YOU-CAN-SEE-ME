package androidx.activity;

import java.util.concurrent.CopyOnWriteArrayList;
/* loaded from: classes.dex */
public abstract class p {

    /* renamed from: a  reason: collision with root package name */
    private boolean f1440a;

    /* renamed from: b  reason: collision with root package name */
    private final CopyOnWriteArrayList f1441b = new CopyOnWriteArrayList();

    /* renamed from: c  reason: collision with root package name */
    private c7.a f1442c;

    public p(boolean z7) {
        this.f1440a = z7;
    }

    public final void a(c cVar) {
        d7.k.e(cVar, "cancellable");
        this.f1441b.add(cVar);
    }

    public final c7.a b() {
        return this.f1442c;
    }

    public abstract void c();

    public abstract void d();

    public abstract void e(b bVar);

    public abstract void f(b bVar);

    public final boolean g() {
        return this.f1440a;
    }

    public final void h() {
        for (c cVar : this.f1441b) {
            cVar.cancel();
        }
    }

    public final void i(c cVar) {
        d7.k.e(cVar, "cancellable");
        this.f1441b.remove(cVar);
    }

    public final void j(boolean z7) {
        this.f1440a = z7;
        c7.a aVar = this.f1442c;
        if (aVar != null) {
            aVar.a();
        }
    }

    public final void k(c7.a aVar) {
        this.f1442c = aVar;
    }
}
