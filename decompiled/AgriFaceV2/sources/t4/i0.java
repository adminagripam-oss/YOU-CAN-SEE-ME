package t4;

import java.security.GeneralSecurityException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
/* loaded from: classes.dex */
public final class i0 {

    /* renamed from: a  reason: collision with root package name */
    private final Map f11856a;

    /* renamed from: b  reason: collision with root package name */
    private final Map f11857b;

    /* renamed from: c  reason: collision with root package name */
    private final Map f11858c;

    /* renamed from: d  reason: collision with root package name */
    private final Map f11859d;

    /* loaded from: classes.dex */
    public static final class b {

        /* renamed from: a  reason: collision with root package name */
        private final Map f11860a;

        /* renamed from: b  reason: collision with root package name */
        private final Map f11861b;

        /* renamed from: c  reason: collision with root package name */
        private final Map f11862c;

        /* renamed from: d  reason: collision with root package name */
        private final Map f11863d;

        public b() {
            this.f11860a = new HashMap();
            this.f11861b = new HashMap();
            this.f11862c = new HashMap();
            this.f11863d = new HashMap();
        }

        public i0 e() {
            return new i0(this);
        }

        public b f(f fVar) {
            c cVar = new c(fVar.c(), fVar.b());
            if (this.f11861b.containsKey(cVar)) {
                f fVar2 = (f) this.f11861b.get(cVar);
                if (!fVar2.equals(fVar) || !fVar.equals(fVar2)) {
                    throw new GeneralSecurityException("Attempt to register non-equal parser for already existing object of type: " + cVar);
                }
            } else {
                this.f11861b.put(cVar, fVar);
            }
            return this;
        }

        public b g(g gVar) {
            d dVar = new d(gVar.b(), gVar.c());
            if (this.f11860a.containsKey(dVar)) {
                g gVar2 = (g) this.f11860a.get(dVar);
                if (!gVar2.equals(gVar) || !gVar.equals(gVar2)) {
                    throw new GeneralSecurityException("Attempt to register non-equal serializer for already existing object of type: " + dVar);
                }
            } else {
                this.f11860a.put(dVar, gVar);
            }
            return this;
        }

        public b h(w wVar) {
            c cVar = new c(wVar.c(), wVar.b());
            if (this.f11863d.containsKey(cVar)) {
                w wVar2 = (w) this.f11863d.get(cVar);
                if (!wVar2.equals(wVar) || !wVar.equals(wVar2)) {
                    throw new GeneralSecurityException("Attempt to register non-equal parser for already existing object of type: " + cVar);
                }
            } else {
                this.f11863d.put(cVar, wVar);
            }
            return this;
        }

        public b i(x xVar) {
            d dVar = new d(xVar.b(), xVar.c());
            if (this.f11862c.containsKey(dVar)) {
                x xVar2 = (x) this.f11862c.get(dVar);
                if (!xVar2.equals(xVar) || !xVar.equals(xVar2)) {
                    throw new GeneralSecurityException("Attempt to register non-equal serializer for already existing object of type: " + dVar);
                }
            } else {
                this.f11862c.put(dVar, xVar);
            }
            return this;
        }

        public b(i0 i0Var) {
            this.f11860a = new HashMap(i0Var.f11856a);
            this.f11861b = new HashMap(i0Var.f11857b);
            this.f11862c = new HashMap(i0Var.f11858c);
            this.f11863d = new HashMap(i0Var.f11859d);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class c {

        /* renamed from: a  reason: collision with root package name */
        private final Class f11864a;

        /* renamed from: b  reason: collision with root package name */
        private final a5.a f11865b;

        private c(Class cls, a5.a aVar) {
            this.f11864a = cls;
            this.f11865b = aVar;
        }

        public boolean equals(Object obj) {
            if (obj instanceof c) {
                c cVar = (c) obj;
                return cVar.f11864a.equals(this.f11864a) && cVar.f11865b.equals(this.f11865b);
            }
            return false;
        }

        public int hashCode() {
            return Objects.hash(this.f11864a, this.f11865b);
        }

        public String toString() {
            return this.f11864a.getSimpleName() + ", object identifier: " + this.f11865b;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class d {

        /* renamed from: a  reason: collision with root package name */
        private final Class f11866a;

        /* renamed from: b  reason: collision with root package name */
        private final Class f11867b;

        private d(Class cls, Class cls2) {
            this.f11866a = cls;
            this.f11867b = cls2;
        }

        public boolean equals(Object obj) {
            if (obj instanceof d) {
                d dVar = (d) obj;
                return dVar.f11866a.equals(this.f11866a) && dVar.f11867b.equals(this.f11867b);
            }
            return false;
        }

        public int hashCode() {
            return Objects.hash(this.f11866a, this.f11867b);
        }

        public String toString() {
            return this.f11866a.getSimpleName() + " with serialization type: " + this.f11867b.getSimpleName();
        }
    }

    private i0(b bVar) {
        this.f11856a = new HashMap(bVar.f11860a);
        this.f11857b = new HashMap(bVar.f11861b);
        this.f11858c = new HashMap(bVar.f11862c);
        this.f11859d = new HashMap(bVar.f11863d);
    }

    public boolean e(h0 h0Var) {
        return this.f11857b.containsKey(new c(h0Var.getClass(), h0Var.a()));
    }

    public boolean f(h0 h0Var) {
        return this.f11859d.containsKey(new c(h0Var.getClass(), h0Var.a()));
    }

    public j4.h g(h0 h0Var, j4.y yVar) {
        c cVar = new c(h0Var.getClass(), h0Var.a());
        if (this.f11857b.containsKey(cVar)) {
            return ((f) this.f11857b.get(cVar)).d(h0Var, yVar);
        }
        throw new GeneralSecurityException("No Key Parser for requested key type " + cVar + " available");
    }

    public j4.w h(h0 h0Var) {
        c cVar = new c(h0Var.getClass(), h0Var.a());
        if (this.f11859d.containsKey(cVar)) {
            return ((w) this.f11859d.get(cVar)).d(h0Var);
        }
        throw new GeneralSecurityException("No Parameters Parser for requested key type " + cVar + " available");
    }

    public h0 i(j4.h hVar, Class cls, j4.y yVar) {
        d dVar = new d(hVar.getClass(), cls);
        if (this.f11856a.containsKey(dVar)) {
            return ((g) this.f11856a.get(dVar)).d(hVar, yVar);
        }
        throw new GeneralSecurityException("No Key serializer for " + dVar + " available");
    }

    public h0 j(j4.w wVar, Class cls) {
        d dVar = new d(wVar.getClass(), cls);
        if (this.f11858c.containsKey(dVar)) {
            return ((x) this.f11858c.get(dVar)).d(wVar);
        }
        throw new GeneralSecurityException("No Key Format serializer for " + dVar + " available");
    }
}
