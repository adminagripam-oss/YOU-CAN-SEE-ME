package t7;

import android.os.Build;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Collection;
/* loaded from: classes.dex */
public abstract class a {

    /* renamed from: t7.a$a  reason: collision with other inner class name */
    /* loaded from: classes.dex */
    private static class C0154a implements InvocationHandler {

        /* renamed from: a  reason: collision with root package name */
        private final Object f11917a;

        public C0154a(Object obj) {
            this.f11917a = obj;
        }

        @Override // java.lang.reflect.InvocationHandler
        public Object invoke(Object obj, Method method, Object[] objArr) {
            try {
                return a.d(method, this.f11917a.getClass().getClassLoader()).invoke(this.f11917a, objArr);
            } catch (InvocationTargetException e8) {
                throw e8.getTargetException();
            } catch (ReflectiveOperationException e9) {
                throw new RuntimeException("Reflection failed for method " + method, e9);
            }
        }
    }

    public static Object a(Class cls, InvocationHandler invocationHandler) {
        if (invocationHandler == null) {
            return null;
        }
        return cls.cast(Proxy.newProxyInstance(a.class.getClassLoader(), new Class[]{cls}, invocationHandler));
    }

    public static boolean b(Collection collection, String str) {
        if (!collection.contains(str)) {
            if (e()) {
                if (collection.contains(str + ":dev")) {
                }
            }
            return false;
        }
        return true;
    }

    public static InvocationHandler c(Object obj) {
        if (obj == null) {
            return null;
        }
        return new C0154a(obj);
    }

    public static Method d(Method method, ClassLoader classLoader) {
        return Class.forName(method.getDeclaringClass().getName(), true, classLoader).getDeclaredMethod(method.getName(), method.getParameterTypes());
    }

    private static boolean e() {
        String str = Build.TYPE;
        return "eng".equals(str) || "userdebug".equals(str);
    }
}
