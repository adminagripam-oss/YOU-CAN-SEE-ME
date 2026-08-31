package b0;

import java.util.Objects;
import java.util.Set;
/* loaded from: classes.dex */
public interface u0 {

    /* loaded from: classes.dex */
    public static abstract class a {
        public static a a(String str, Class cls) {
            return b(str, cls, null);
        }

        public static a b(String str, Class cls, Object obj) {
            return new d(str, cls, obj);
        }

        public abstract String c();

        public abstract Object d();

        public abstract Class e();
    }

    /* loaded from: classes.dex */
    public interface b {
        boolean a(a aVar);
    }

    /* loaded from: classes.dex */
    public enum c {
        ALWAYS_OVERRIDE,
        HIGH_PRIORITY_REQUIRED,
        REQUIRED,
        OPTIONAL
    }

    static boolean J(c cVar, c cVar2) {
        c cVar3 = c.REQUIRED;
        return cVar == cVar3 && cVar2 == cVar3;
    }

    static u0 u(u0 u0Var, u0 u0Var2) {
        if (u0Var == null && u0Var2 == null) {
            return f2.a0();
        }
        a2 e02 = u0Var2 != null ? a2.e0(u0Var2) : a2.d0();
        if (u0Var != null) {
            for (a aVar : u0Var.d()) {
                x(e02, u0Var2, u0Var, aVar);
            }
        }
        return f2.b0(e02);
    }

    static void x(a2 a2Var, u0 u0Var, u0 u0Var2, a aVar) {
        if (!Objects.equals(aVar, p1.f4317r)) {
            a2Var.F(aVar, u0Var2.h(aVar), u0Var2.b(aVar));
            return;
        }
        a2Var.F(aVar, u0Var2.h(aVar), d0.o.a((o0.c) u0Var.f(aVar, null), (o0.c) u0Var2.f(aVar, null)));
    }

    boolean a(a aVar);

    Object b(a aVar);

    void c(String str, b bVar);

    Set d();

    Object e(a aVar, c cVar);

    Object f(a aVar, Object obj);

    Set g(a aVar);

    c h(a aVar);
}
