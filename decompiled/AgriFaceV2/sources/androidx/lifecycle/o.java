package androidx.lifecycle;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/* loaded from: classes.dex */
public final class o {

    /* renamed from: a  reason: collision with root package name */
    public static final o f3815a = new o();

    /* renamed from: b  reason: collision with root package name */
    private static final Map f3816b = new HashMap();

    /* renamed from: c  reason: collision with root package name */
    private static final Map f3817c = new HashMap();

    private o() {
    }

    private final f a(Constructor constructor, Object obj) {
        try {
            Object newInstance = constructor.newInstance(obj);
            d7.k.d(newInstance, "{\n            constructo…tance(`object`)\n        }");
            androidx.appcompat.app.w.a(newInstance);
            return null;
        } catch (IllegalAccessException e8) {
            throw new RuntimeException(e8);
        } catch (InstantiationException e9) {
            throw new RuntimeException(e9);
        } catch (InvocationTargetException e10) {
            throw new RuntimeException(e10);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final Constructor b(Class cls) {
        try {
            Package r02 = cls.getPackage();
            String canonicalName = cls.getCanonicalName();
            String name = r02 != null ? r02.getName() : "";
            d7.k.d(name, "fullPackage");
            if (!(name.length() == 0)) {
                d7.k.d(canonicalName, "name");
                canonicalName = canonicalName.substring(name.length() + 1);
                d7.k.d(canonicalName, "this as java.lang.String).substring(startIndex)");
            }
            d7.k.d(canonicalName, "if (fullPackage.isEmpty(…g(fullPackage.length + 1)");
            String c8 = c(canonicalName);
            if (!(name.length() == 0)) {
                c8 = name + '.' + c8;
            }
            Class<?> cls2 = Class.forName(c8);
            d7.k.c(cls2, "null cannot be cast to non-null type java.lang.Class<out androidx.lifecycle.GeneratedAdapter>");
            Constructor declaredConstructor = cls2.getDeclaredConstructor(cls);
            if (declaredConstructor.isAccessible()) {
                return declaredConstructor;
            }
            declaredConstructor.setAccessible(true);
            return declaredConstructor;
        } catch (ClassNotFoundException unused) {
            return null;
        } catch (NoSuchMethodException e8) {
            throw new RuntimeException(e8);
        }
    }

    public static final String c(String str) {
        String g8;
        d7.k.e(str, "className");
        StringBuilder sb = new StringBuilder();
        g8 = j7.l.g(str, ".", "_", false, 4, null);
        sb.append(g8);
        sb.append("_LifecycleAdapter");
        return sb.toString();
    }

    private final int d(Class cls) {
        Map map = f3816b;
        Integer num = (Integer) map.get(cls);
        if (num != null) {
            return num.intValue();
        }
        int g8 = g(cls);
        map.put(cls, Integer.valueOf(g8));
        return g8;
    }

    private final boolean e(Class cls) {
        return cls != null && l.class.isAssignableFrom(cls);
    }

    public static final k f(Object obj) {
        d7.k.e(obj, "object");
        boolean z7 = obj instanceof k;
        boolean z8 = obj instanceof DefaultLifecycleObserver;
        if (z7 && z8) {
            return new d((DefaultLifecycleObserver) obj, (k) obj);
        }
        if (z8) {
            return new d((DefaultLifecycleObserver) obj, null);
        }
        if (z7) {
            return (k) obj;
        }
        Class<?> cls = obj.getClass();
        o oVar = f3815a;
        if (oVar.d(cls) == 2) {
            Object obj2 = f3817c.get(cls);
            d7.k.b(obj2);
            List list = (List) obj2;
            if (list.size() == 1) {
                oVar.a((Constructor) list.get(0), obj);
                return new j0(null);
            }
            int size = list.size();
            f[] fVarArr = new f[size];
            for (int i8 = 0; i8 < size; i8++) {
                f3815a.a((Constructor) list.get(i8), obj);
                fVarArr[i8] = null;
            }
            return new c(fVarArr);
        }
        return new x(obj);
    }

    private final int g(Class cls) {
        ArrayList arrayList;
        List b8;
        if (cls.getCanonicalName() == null) {
            return 1;
        }
        Constructor b9 = b(cls);
        if (b9 != null) {
            Map map = f3817c;
            b8 = s6.m.b(b9);
            map.put(cls, b8);
            return 2;
        } else if (b.f3733c.d(cls)) {
            return 1;
        } else {
            Class superclass = cls.getSuperclass();
            if (e(superclass)) {
                d7.k.d(superclass, "superclass");
                if (d(superclass) == 1) {
                    return 1;
                }
                Object obj = f3817c.get(superclass);
                d7.k.b(obj);
                arrayList = new ArrayList((Collection) obj);
            } else {
                arrayList = null;
            }
            Class<?>[] interfaces = cls.getInterfaces();
            d7.k.d(interfaces, "klass.interfaces");
            for (Class<?> cls2 : interfaces) {
                if (e(cls2)) {
                    d7.k.d(cls2, "intrface");
                    if (d(cls2) == 1) {
                        return 1;
                    }
                    if (arrayList == null) {
                        arrayList = new ArrayList();
                    }
                    Object obj2 = f3817c.get(cls2);
                    d7.k.b(obj2);
                    arrayList.addAll((Collection) obj2);
                }
            }
            if (arrayList != null) {
                f3817c.put(cls, arrayList);
                return 2;
            }
            return 1;
        }
    }
}
