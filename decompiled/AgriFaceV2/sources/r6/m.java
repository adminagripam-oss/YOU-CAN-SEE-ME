package r6;

import java.io.Serializable;
/* loaded from: classes.dex */
final class m implements e, Serializable {

    /* renamed from: e  reason: collision with root package name */
    private c7.a f11365e;

    /* renamed from: f  reason: collision with root package name */
    private volatile Object f11366f;

    /* renamed from: g  reason: collision with root package name */
    private final Object f11367g;

    public m(c7.a aVar, Object obj) {
        d7.k.e(aVar, "initializer");
        this.f11365e = aVar;
        this.f11366f = p.f11371a;
        this.f11367g = obj == null ? this : obj;
    }

    public boolean a() {
        return this.f11366f != p.f11371a;
    }

    @Override // r6.e
    public Object getValue() {
        Object obj;
        Object obj2 = this.f11366f;
        p pVar = p.f11371a;
        if (obj2 != pVar) {
            return obj2;
        }
        synchronized (this.f11367g) {
            obj = this.f11366f;
            if (obj == pVar) {
                c7.a aVar = this.f11365e;
                d7.k.b(aVar);
                obj = aVar.a();
                this.f11366f = obj;
                this.f11365e = null;
            }
        }
        return obj;
    }

    public String toString() {
        return a() ? String.valueOf(getValue()) : "Lazy value not initialized yet.";
    }

    public /* synthetic */ m(c7.a aVar, Object obj, int i8, d7.g gVar) {
        this(aVar, (i8 & 2) != 0 ? null : obj);
    }
}
