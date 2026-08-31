package t4;

import j4.o;
import java.security.GeneralSecurityException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import t4.c0;
/* loaded from: classes.dex */
public class b0 {

    /* renamed from: a  reason: collision with root package name */
    private final Map f11815a;

    /* renamed from: b  reason: collision with root package name */
    private final Map f11816b;

    /* renamed from: c  reason: collision with root package name */
    private final boolean f11817c;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static final class c {

        /* renamed from: a  reason: collision with root package name */
        private final Class f11821a;

        /* renamed from: b  reason: collision with root package name */
        private final Class f11822b;

        private c(Class cls, Class cls2) {
            this.f11821a = cls;
            this.f11822b = cls2;
        }

        public boolean equals(Object obj) {
            if (obj instanceof c) {
                c cVar = (c) obj;
                return cVar.f11821a.equals(this.f11821a) && cVar.f11822b.equals(this.f11822b);
            }
            return false;
        }

        public int hashCode() {
            return Objects.hash(this.f11821a, this.f11822b);
        }

        public String toString() {
            return this.f11821a.getSimpleName() + " with primitive type: " + this.f11822b.getSimpleName();
        }
    }

    private b0(b bVar) {
        this.f11815a = new HashMap(bVar.f11818a);
        this.f11816b = new HashMap(bVar.f11819b);
        this.f11817c = bVar.f11820c;
    }

    public static b d() {
        return new b();
    }

    public static b e(b0 b0Var) {
        return new b();
    }

    private Object g(j4.h hVar, Class cls) {
        c cVar = new c(hVar.getClass(), cls);
        if (this.f11815a.containsKey(cVar)) {
            return ((z) this.f11815a.get(cVar)).a(hVar);
        }
        throw new GeneralSecurityException("No PrimitiveConstructor for " + cVar + " available, see https://developers.google.com/tink/faq/registration_errors");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ Object h(c0 c0Var, o.a aVar) {
        return f(aVar.getKey(), c0Var.b());
    }

    private Object j(j4.o oVar, final c0 c0Var) {
        return c0Var.c(oVar, new c0.a() { // from class: t4.a0
            @Override // t4.c0.a
            public final Object a(o.a aVar) {
                Object h8;
                h8 = b0.this.h(c0Var, aVar);
                return h8;
            }
        });
    }

    public Object f(j4.h hVar, Class cls) {
        return (this.f11817c && (hVar instanceof i)) ? g(u.c().f(((i) hVar).e(j4.g.a()), j4.g.a()), cls) : g(hVar, cls);
    }

    public Object i(j4.o oVar, Class cls) {
        if (this.f11816b.containsKey(cls)) {
            return j(oVar, (c0) this.f11816b.get(cls));
        }
        throw new GeneralSecurityException("No wrapper found for " + cls);
    }

    /* loaded from: classes.dex */
    public static final class b {

        /* renamed from: a  reason: collision with root package name */
        private final Map f11818a;

        /* renamed from: b  reason: collision with root package name */
        private final Map f11819b;

        /* renamed from: c  reason: collision with root package name */
        private boolean f11820c;

        private b() {
            this.f11820c = false;
            this.f11818a = new HashMap();
            this.f11819b = new HashMap();
        }

        public b0 d() {
            return new b0(this);
        }

        public b e(z zVar) {
            if (zVar != null) {
                c cVar = new c(zVar.c(), zVar.d());
                if (this.f11818a.containsKey(cVar)) {
                    z zVar2 = (z) this.f11818a.get(cVar);
                    if (!zVar2.equals(zVar) || !zVar.equals(zVar2)) {
                        throw new GeneralSecurityException("Attempt to register non-equal PrimitiveConstructor object for already existing object of type: " + cVar);
                    }
                } else {
                    this.f11818a.put(cVar, zVar);
                }
                return this;
            }
            throw new NullPointerException("primitive constructor must be non-null");
        }

        public b f(c0 c0Var) {
            if (c0Var != null) {
                Class a8 = c0Var.a();
                if (this.f11819b.containsKey(a8)) {
                    c0 c0Var2 = (c0) this.f11819b.get(a8);
                    if (!c0Var2.equals(c0Var) || !c0Var.equals(c0Var2)) {
                        throw new GeneralSecurityException("Attempt to register non-equal PrimitiveWrapper object or input class object for already existing object of type" + a8);
                    }
                } else {
                    this.f11819b.put(a8, c0Var);
                }
                return this;
            }
            throw new NullPointerException("wrapper must be non-null");
        }

        private b(b0 b0Var) {
            this.f11820c = false;
            this.f11818a = new HashMap(b0Var.f11815a);
            this.f11819b = new HashMap(b0Var.f11816b);
        }
    }
}
