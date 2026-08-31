package e2;

import android.app.Activity;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import r6.r;
/* loaded from: classes.dex */
public final class d {

    /* renamed from: a  reason: collision with root package name */
    private final ClassLoader f6566a;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static final class a implements InvocationHandler {

        /* renamed from: a  reason: collision with root package name */
        private final h7.b f6567a;

        /* renamed from: b  reason: collision with root package name */
        private final c7.l f6568b;

        public a(h7.b bVar, c7.l lVar) {
            d7.k.e(bVar, "clazz");
            d7.k.e(lVar, "consumer");
            this.f6567a = bVar;
            this.f6568b = lVar;
        }

        private final boolean b(Method method, Object[] objArr) {
            if (d7.k.a(method.getName(), "accept")) {
                return objArr != null && objArr.length == 1;
            }
            return false;
        }

        private final boolean c(Method method, Object[] objArr) {
            if (d7.k.a(method.getName(), "equals") && method.getReturnType().equals(Boolean.TYPE)) {
                return objArr != null && objArr.length == 1;
            }
            return false;
        }

        private final boolean d(Method method, Object[] objArr) {
            return d7.k.a(method.getName(), "hashCode") && method.getReturnType().equals(Integer.TYPE) && objArr == null;
        }

        private final boolean e(Method method, Object[] objArr) {
            return d7.k.a(method.getName(), "toString") && method.getReturnType().equals(String.class) && objArr == null;
        }

        public final void a(Object obj) {
            d7.k.e(obj, "parameter");
            this.f6568b.g(obj);
        }

        @Override // java.lang.reflect.InvocationHandler
        public Object invoke(Object obj, Method method, Object[] objArr) {
            d7.k.e(obj, "obj");
            d7.k.e(method, "method");
            if (b(method, objArr)) {
                a(h7.c.a(this.f6567a, objArr != null ? objArr[0] : null));
                return r.f11372a;
            } else if (c(method, objArr)) {
                return Boolean.valueOf(obj == (objArr != null ? objArr[0] : null));
            } else if (d(method, objArr)) {
                return Integer.valueOf(this.f6568b.hashCode());
            } else {
                if (e(method, objArr)) {
                    return this.f6568b.toString();
                }
                throw new UnsupportedOperationException("Unexpected method call object:" + obj + ", method: " + method + ", args: " + objArr);
            }
        }
    }

    /* loaded from: classes.dex */
    public interface b {
        void dispose();
    }

    /* loaded from: classes.dex */
    public static final class c implements b {

        /* renamed from: a  reason: collision with root package name */
        final /* synthetic */ Method f6569a;

        /* renamed from: b  reason: collision with root package name */
        final /* synthetic */ Object f6570b;

        /* renamed from: c  reason: collision with root package name */
        final /* synthetic */ Object f6571c;

        c(Method method, Object obj, Object obj2) {
            this.f6569a = method;
            this.f6570b = obj;
            this.f6571c = obj2;
        }

        @Override // e2.d.b
        public void dispose() {
            this.f6569a.invoke(this.f6570b, this.f6571c);
        }
    }

    public d(ClassLoader classLoader) {
        d7.k.e(classLoader, "loader");
        this.f6566a = classLoader;
    }

    private final Object a(h7.b bVar, c7.l lVar) {
        Object newProxyInstance = Proxy.newProxyInstance(this.f6566a, new Class[]{d()}, new a(bVar, lVar));
        d7.k.d(newProxyInstance, "newProxyInstance(loader,…onsumerClass()), handler)");
        return newProxyInstance;
    }

    private final Class d() {
        Class<?> loadClass = this.f6566a.loadClass("java.util.function.Consumer");
        d7.k.d(loadClass, "loader.loadClass(\"java.util.function.Consumer\")");
        return loadClass;
    }

    public final Class b() {
        try {
            return d();
        } catch (ClassNotFoundException unused) {
            return null;
        }
    }

    public final b c(Object obj, h7.b bVar, String str, String str2, Activity activity, c7.l lVar) {
        d7.k.e(obj, "obj");
        d7.k.e(bVar, "clazz");
        d7.k.e(str, "addMethodName");
        d7.k.e(str2, "removeMethodName");
        d7.k.e(activity, "activity");
        d7.k.e(lVar, "consumer");
        Object a8 = a(bVar, lVar);
        obj.getClass().getMethod(str, Activity.class, d()).invoke(obj, activity, a8);
        return new c(obj.getClass().getMethod(str2, d()), obj, a8);
    }
}
