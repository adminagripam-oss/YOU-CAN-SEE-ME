package d7;
/* loaded from: classes.dex */
public abstract class s {

    /* renamed from: a  reason: collision with root package name */
    private static final t f6485a;

    /* renamed from: b  reason: collision with root package name */
    private static final h7.b[] f6486b;

    static {
        t tVar = null;
        try {
            tVar = (t) Class.forName("kotlin.reflect.jvm.internal.ReflectionFactoryImpl").newInstance();
        } catch (ClassCastException | ClassNotFoundException | IllegalAccessException | InstantiationException unused) {
        }
        if (tVar == null) {
            tVar = new t();
        }
        f6485a = tVar;
        f6486b = new h7.b[0];
    }

    public static h7.e a(i iVar) {
        return f6485a.a(iVar);
    }

    public static h7.b b(Class cls) {
        return f6485a.b(cls);
    }

    public static h7.d c(Class cls) {
        return f6485a.c(cls, "");
    }

    public static h7.f d(n nVar) {
        return f6485a.d(nVar);
    }

    public static String e(h hVar) {
        return f6485a.e(hVar);
    }

    public static String f(l lVar) {
        return f6485a.f(lVar);
    }
}
