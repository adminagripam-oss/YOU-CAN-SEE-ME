package t6;

import c7.p;
import d7.k;
import d7.l;
import java.io.Serializable;
import t6.g;
/* loaded from: classes.dex */
public final class c implements g, Serializable {

    /* renamed from: e  reason: collision with root package name */
    private final g f11910e;

    /* renamed from: f  reason: collision with root package name */
    private final g.b f11911f;

    /* loaded from: classes.dex */
    static final class a extends l implements p {

        /* renamed from: f  reason: collision with root package name */
        public static final a f11912f = new a();

        a() {
            super(2);
        }

        @Override // c7.p
        /* renamed from: b */
        public final String f(String str, g.b bVar) {
            k.e(str, "acc");
            k.e(bVar, "element");
            if (str.length() == 0) {
                return bVar.toString();
            }
            return str + ", " + bVar;
        }
    }

    public c(g gVar, g.b bVar) {
        k.e(gVar, "left");
        k.e(bVar, "element");
        this.f11910e = gVar;
        this.f11911f = bVar;
    }

    private final boolean a(g.b bVar) {
        return k.a(c(bVar.getKey()), bVar);
    }

    private final boolean e(c cVar) {
        while (a(cVar.f11911f)) {
            g gVar = cVar.f11910e;
            if (!(gVar instanceof c)) {
                k.c(gVar, "null cannot be cast to non-null type kotlin.coroutines.CoroutineContext.Element");
                return a((g.b) gVar);
            }
            cVar = (c) gVar;
        }
        return false;
    }

    private final int f() {
        int i8 = 2;
        c cVar = this;
        while (true) {
            g gVar = cVar.f11910e;
            cVar = gVar instanceof c ? (c) gVar : null;
            if (cVar == null) {
                return i8;
            }
            i8++;
        }
    }

    @Override // t6.g
    public g G(g.c cVar) {
        k.e(cVar, "key");
        if (this.f11911f.c(cVar) != null) {
            return this.f11910e;
        }
        g G = this.f11910e.G(cVar);
        return G == this.f11910e ? this : G == h.f11916e ? this.f11911f : new c(G, this.f11911f);
    }

    @Override // t6.g
    public g M(g gVar) {
        return g.a.a(this, gVar);
    }

    @Override // t6.g
    public g.b c(g.c cVar) {
        k.e(cVar, "key");
        c cVar2 = this;
        while (true) {
            g.b c8 = cVar2.f11911f.c(cVar);
            if (c8 != null) {
                return c8;
            }
            g gVar = cVar2.f11910e;
            if (!(gVar instanceof c)) {
                return gVar.c(cVar);
            }
            cVar2 = (c) gVar;
        }
    }

    public boolean equals(Object obj) {
        if (this != obj) {
            if (obj instanceof c) {
                c cVar = (c) obj;
                if (cVar.f() != f() || !cVar.e(this)) {
                }
            }
            return false;
        }
        return true;
    }

    @Override // t6.g
    public Object g(Object obj, p pVar) {
        k.e(pVar, "operation");
        return pVar.f(this.f11910e.g(obj, pVar), this.f11911f);
    }

    public int hashCode() {
        return this.f11910e.hashCode() + this.f11911f.hashCode();
    }

    public String toString() {
        return '[' + ((String) g("", a.f11912f)) + ']';
    }
}
