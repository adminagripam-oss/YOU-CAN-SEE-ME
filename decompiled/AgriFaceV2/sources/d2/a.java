package d2;

import d7.k;
import d7.l;
import java.lang.reflect.Method;
/* loaded from: classes.dex */
public final class a {

    /* renamed from: a  reason: collision with root package name */
    private final ClassLoader f6421a;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: d2.a$a  reason: collision with other inner class name */
    /* loaded from: classes.dex */
    public static final class C0078a extends l implements c7.a {
        C0078a() {
            super(0);
        }

        @Override // c7.a
        /* renamed from: b */
        public final Class a() {
            Class<?> loadClass = a.this.f6421a.loadClass("androidx.window.extensions.WindowExtensionsProvider");
            k.d(loadClass, "loader.loadClass(WindowE…XTENSIONS_PROVIDER_CLASS)");
            return loadClass;
        }
    }

    /* loaded from: classes.dex */
    static final class b extends l implements c7.a {
        b() {
            super(0);
        }

        @Override // c7.a
        /* renamed from: b */
        public final Boolean a() {
            boolean z7 = false;
            Method declaredMethod = a.this.d().getDeclaredMethod("getWindowExtensions", new Class[0]);
            Class c8 = a.this.c();
            m2.a aVar = m2.a.f9130a;
            k.d(declaredMethod, "getWindowExtensionsMethod");
            if (aVar.c(declaredMethod, c8) && aVar.d(declaredMethod)) {
                z7 = true;
            }
            return Boolean.valueOf(z7);
        }
    }

    public a(ClassLoader classLoader) {
        k.e(classLoader, "loader");
        this.f6421a = classLoader;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Class d() {
        Class<?> loadClass = this.f6421a.loadClass("androidx.window.extensions.WindowExtensionsProvider");
        k.d(loadClass, "loader.loadClass(WindowE…XTENSIONS_PROVIDER_CLASS)");
        return loadClass;
    }

    private final boolean e() {
        return m2.a.f9130a.a(new C0078a());
    }

    public final Class c() {
        Class<?> loadClass = this.f6421a.loadClass("androidx.window.extensions.WindowExtensions");
        k.d(loadClass, "loader.loadClass(WindowE….WINDOW_EXTENSIONS_CLASS)");
        return loadClass;
    }

    public final boolean f() {
        return e() && m2.a.e("WindowExtensionsProvider#getWindowExtensions is not valid", new b());
    }
}
