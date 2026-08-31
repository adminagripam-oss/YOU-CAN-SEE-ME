package h2;

import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import androidx.window.extensions.WindowExtensionsProvider;
import androidx.window.extensions.core.util.function.Consumer;
import androidx.window.extensions.layout.WindowLayoutComponent;
import d7.s;
import java.lang.reflect.Method;
/* loaded from: classes.dex */
public final class e {

    /* renamed from: a  reason: collision with root package name */
    private final ClassLoader f7187a;

    /* renamed from: b  reason: collision with root package name */
    private final e2.d f7188b;

    /* renamed from: c  reason: collision with root package name */
    private final d2.a f7189c;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static final class a extends d7.l implements c7.a {
        a() {
            super(0);
        }

        @Override // c7.a
        /* renamed from: b */
        public final Boolean a() {
            Class f8 = e.this.f();
            boolean z7 = false;
            Method method = f8.getMethod("getBounds", new Class[0]);
            Method method2 = f8.getMethod("getType", new Class[0]);
            Method method3 = f8.getMethod("getState", new Class[0]);
            m2.a aVar = m2.a.f9130a;
            d7.k.d(method, "getBoundsMethod");
            if (aVar.b(method, s.b(Rect.class)) && aVar.d(method)) {
                d7.k.d(method2, "getTypeMethod");
                Class cls = Integer.TYPE;
                if (aVar.b(method2, s.b(cls)) && aVar.d(method2)) {
                    d7.k.d(method3, "getStateMethod");
                    if (aVar.b(method3, s.b(cls)) && aVar.d(method3)) {
                        z7 = true;
                    }
                }
            }
            return Boolean.valueOf(z7);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static final class b extends d7.l implements c7.a {
        b() {
            super(0);
        }

        @Override // c7.a
        /* renamed from: b */
        public final Boolean a() {
            Class<?> b8 = e.this.f7188b.b();
            if (b8 == null) {
                return Boolean.FALSE;
            }
            Class h8 = e.this.h();
            boolean z7 = false;
            Method method = h8.getMethod("addWindowLayoutInfoListener", Activity.class, b8);
            Method method2 = h8.getMethod("removeWindowLayoutInfoListener", b8);
            m2.a aVar = m2.a.f9130a;
            d7.k.d(method, "addListenerMethod");
            if (aVar.d(method)) {
                d7.k.d(method2, "removeListenerMethod");
                if (aVar.d(method2)) {
                    z7 = true;
                }
            }
            return Boolean.valueOf(z7);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static final class c extends d7.l implements c7.a {
        c() {
            super(0);
        }

        @Override // c7.a
        /* renamed from: b */
        public final Boolean a() {
            Class h8 = e.this.h();
            boolean z7 = false;
            Method method = h8.getMethod("addWindowLayoutInfoListener", Context.class, Consumer.class);
            Method method2 = h8.getMethod("removeWindowLayoutInfoListener", Consumer.class);
            m2.a aVar = m2.a.f9130a;
            d7.k.d(method, "addListenerMethod");
            if (aVar.d(method)) {
                d7.k.d(method2, "removeListenerMethod");
                if (aVar.d(method2)) {
                    z7 = true;
                }
            }
            return Boolean.valueOf(z7);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static final class d extends d7.l implements c7.a {
        d() {
            super(0);
        }

        @Override // c7.a
        /* renamed from: b */
        public final Boolean a() {
            boolean z7 = false;
            Method method = e.this.f7189c.c().getMethod("getWindowLayoutComponent", new Class[0]);
            Class h8 = e.this.h();
            m2.a aVar = m2.a.f9130a;
            d7.k.d(method, "getWindowLayoutComponentMethod");
            if (aVar.d(method) && aVar.c(method, h8)) {
                z7 = true;
            }
            return Boolean.valueOf(z7);
        }
    }

    public e(ClassLoader classLoader, e2.d dVar) {
        d7.k.e(classLoader, "loader");
        d7.k.e(dVar, "consumerAdapter");
        this.f7187a = classLoader;
        this.f7188b = dVar;
        this.f7189c = new d2.a(classLoader);
    }

    private final boolean e() {
        if (n()) {
            int a8 = e2.e.f6572a.a();
            boolean z7 = true;
            if (a8 == 1) {
                return i();
            }
            if (2 > a8 || a8 > Integer.MAX_VALUE) {
                z7 = false;
            }
            if (z7) {
                return j();
            }
            return false;
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Class f() {
        Class<?> loadClass = this.f7187a.loadClass("androidx.window.extensions.layout.FoldingFeature");
        d7.k.d(loadClass, "loader.loadClass(FOLDING_FEATURE_CLASS)");
        return loadClass;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Class h() {
        Class<?> loadClass = this.f7187a.loadClass("androidx.window.extensions.layout.WindowLayoutComponent");
        d7.k.d(loadClass, "loader.loadClass(WINDOW_LAYOUT_COMPONENT_CLASS)");
        return loadClass;
    }

    private final boolean k() {
        return m2.a.e("FoldingFeature class is not valid", new a());
    }

    private final boolean l() {
        return m2.a.e("WindowLayoutComponent#addWindowLayoutInfoListener(" + Activity.class.getName() + ", java.util.function.Consumer) is not valid", new b());
    }

    private final boolean m() {
        return m2.a.e("WindowLayoutComponent#addWindowLayoutInfoListener(" + Context.class.getName() + ", androidx.window.extensions.core.util.function.Consumer) is not valid", new c());
    }

    private final boolean o() {
        return m2.a.e("WindowExtensions#getWindowLayoutComponent is not valid", new d());
    }

    public final WindowLayoutComponent g() {
        if (e()) {
            try {
                return WindowExtensionsProvider.getWindowExtensions().getWindowLayoutComponent();
            } catch (UnsupportedOperationException unused) {
                return null;
            }
        }
        return null;
    }

    public final boolean i() {
        return l();
    }

    public final boolean j() {
        return i() && m();
    }

    public final boolean n() {
        return this.f7189c.f() && o() && k();
    }
}
