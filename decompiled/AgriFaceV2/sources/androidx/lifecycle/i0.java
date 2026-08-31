package androidx.lifecycle;

import android.app.Application;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.List;
/* loaded from: classes.dex */
public abstract class i0 {

    /* renamed from: a  reason: collision with root package name */
    private static final List f3781a;

    /* renamed from: b  reason: collision with root package name */
    private static final List f3782b;

    static {
        List h8;
        List b8;
        h8 = s6.n.h(Application.class, b0.class);
        f3781a = h8;
        b8 = s6.m.b(b0.class);
        f3782b = b8;
    }

    public static final Constructor c(Class cls, List list) {
        List p8;
        d7.k.e(cls, "modelClass");
        d7.k.e(list, "signature");
        Constructor<?>[] constructors = cls.getConstructors();
        d7.k.d(constructors, "modelClass.constructors");
        for (Constructor<?> constructor : constructors) {
            Class<?>[] parameterTypes = constructor.getParameterTypes();
            d7.k.d(parameterTypes, "constructor.parameterTypes");
            p8 = s6.j.p(parameterTypes);
            if (d7.k.a(list, p8)) {
                d7.k.c(constructor, "null cannot be cast to non-null type java.lang.reflect.Constructor<T of androidx.lifecycle.SavedStateViewModelFactoryKt.findMatchingConstructor>");
                return constructor;
            } else if (list.size() == p8.size() && p8.containsAll(list)) {
                throw new UnsupportedOperationException("Class " + cls.getSimpleName() + " must have parameters in the proper order: " + list);
            }
        }
        return null;
    }

    public static final k0 d(Class cls, Constructor constructor, Object... objArr) {
        d7.k.e(cls, "modelClass");
        d7.k.e(constructor, "constructor");
        d7.k.e(objArr, "params");
        try {
            return (k0) constructor.newInstance(Arrays.copyOf(objArr, objArr.length));
        } catch (IllegalAccessException e8) {
            throw new RuntimeException("Failed to access " + cls, e8);
        } catch (InstantiationException e9) {
            throw new RuntimeException("A " + cls + " cannot be instantiated.", e9);
        } catch (InvocationTargetException e10) {
            throw new RuntimeException("An exception happened in constructor of " + cls, e10.getCause());
        }
    }
}
