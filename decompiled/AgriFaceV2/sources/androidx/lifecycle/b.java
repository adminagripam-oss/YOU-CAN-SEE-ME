package androidx.lifecycle;

import androidx.lifecycle.i;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/* loaded from: classes.dex */
final class b {

    /* renamed from: c  reason: collision with root package name */
    static b f3733c = new b();

    /* renamed from: a  reason: collision with root package name */
    private final Map f3734a = new HashMap();

    /* renamed from: b  reason: collision with root package name */
    private final Map f3735b = new HashMap();

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static class a {

        /* renamed from: a  reason: collision with root package name */
        final Map f3736a = new HashMap();

        /* renamed from: b  reason: collision with root package name */
        final Map f3737b;

        a(Map map) {
            this.f3737b = map;
            for (Map.Entry entry : map.entrySet()) {
                i.a aVar = (i.a) entry.getValue();
                List list = (List) this.f3736a.get(aVar);
                if (list == null) {
                    list = new ArrayList();
                    this.f3736a.put(aVar, list);
                }
                list.add((C0041b) entry.getKey());
            }
        }

        private static void b(List list, m mVar, i.a aVar, Object obj) {
            if (list != null) {
                for (int size = list.size() - 1; size >= 0; size--) {
                    ((C0041b) list.get(size)).a(mVar, aVar, obj);
                }
            }
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public void a(m mVar, i.a aVar, Object obj) {
            b((List) this.f3736a.get(aVar), mVar, aVar, obj);
            b((List) this.f3736a.get(i.a.ON_ANY), mVar, aVar, obj);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: androidx.lifecycle.b$b  reason: collision with other inner class name */
    /* loaded from: classes.dex */
    public static final class C0041b {

        /* renamed from: a  reason: collision with root package name */
        final int f3738a;

        /* renamed from: b  reason: collision with root package name */
        final Method f3739b;

        C0041b(int i8, Method method) {
            this.f3738a = i8;
            this.f3739b = method;
            method.setAccessible(true);
        }

        void a(m mVar, i.a aVar, Object obj) {
            try {
                int i8 = this.f3738a;
                if (i8 == 0) {
                    this.f3739b.invoke(obj, new Object[0]);
                } else if (i8 == 1) {
                    this.f3739b.invoke(obj, mVar);
                } else if (i8 != 2) {
                } else {
                    this.f3739b.invoke(obj, mVar, aVar);
                }
            } catch (IllegalAccessException e8) {
                throw new RuntimeException(e8);
            } catch (InvocationTargetException e9) {
                throw new RuntimeException("Failed to call observer method", e9.getCause());
            }
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj instanceof C0041b) {
                C0041b c0041b = (C0041b) obj;
                return this.f3738a == c0041b.f3738a && this.f3739b.getName().equals(c0041b.f3739b.getName());
            }
            return false;
        }

        public int hashCode() {
            return (this.f3738a * 31) + this.f3739b.getName().hashCode();
        }
    }

    b() {
    }

    private a a(Class cls, Method[] methodArr) {
        int i8;
        a c8;
        Class superclass = cls.getSuperclass();
        HashMap hashMap = new HashMap();
        if (superclass != null && (c8 = c(superclass)) != null) {
            hashMap.putAll(c8.f3737b);
        }
        for (Class<?> cls2 : cls.getInterfaces()) {
            for (Map.Entry entry : c(cls2).f3737b.entrySet()) {
                e(hashMap, (C0041b) entry.getKey(), (i.a) entry.getValue(), cls);
            }
        }
        if (methodArr == null) {
            methodArr = b(cls);
        }
        boolean z7 = false;
        for (Method method : methodArr) {
            u uVar = (u) method.getAnnotation(u.class);
            if (uVar != null) {
                Class<?>[] parameterTypes = method.getParameterTypes();
                if (parameterTypes.length <= 0) {
                    i8 = 0;
                } else if (!m.class.isAssignableFrom(parameterTypes[0])) {
                    throw new IllegalArgumentException("invalid parameter type. Must be one and instanceof LifecycleOwner");
                } else {
                    i8 = 1;
                }
                i.a value = uVar.value();
                if (parameterTypes.length > 1) {
                    if (!i.a.class.isAssignableFrom(parameterTypes[1])) {
                        throw new IllegalArgumentException("invalid parameter type. second arg must be an event");
                    }
                    if (value != i.a.ON_ANY) {
                        throw new IllegalArgumentException("Second arg is supported only for ON_ANY value");
                    }
                    i8 = 2;
                }
                if (parameterTypes.length > 2) {
                    throw new IllegalArgumentException("cannot have more than 2 params");
                }
                e(hashMap, new C0041b(i8, method), value, cls);
                z7 = true;
            }
        }
        a aVar = new a(hashMap);
        this.f3734a.put(cls, aVar);
        this.f3735b.put(cls, Boolean.valueOf(z7));
        return aVar;
    }

    private Method[] b(Class cls) {
        try {
            return cls.getDeclaredMethods();
        } catch (NoClassDefFoundError e8) {
            throw new IllegalArgumentException("The observer class has some methods that use newer APIs which are not available in the current OS version. Lifecycles cannot access even other methods so you should make sure that your observer classes only access framework classes that are available in your min API level OR use lifecycle:compiler annotation processor.", e8);
        }
    }

    private void e(Map map, C0041b c0041b, i.a aVar, Class cls) {
        i.a aVar2 = (i.a) map.get(c0041b);
        if (aVar2 == null || aVar == aVar2) {
            if (aVar2 == null) {
                map.put(c0041b, aVar);
                return;
            }
            return;
        }
        Method method = c0041b.f3739b;
        throw new IllegalArgumentException("Method " + method.getName() + " in " + cls.getName() + " already declared with different @OnLifecycleEvent value: previous value " + aVar2 + ", new value " + aVar);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public a c(Class cls) {
        a aVar = (a) this.f3734a.get(cls);
        return aVar != null ? aVar : a(cls, null);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean d(Class cls) {
        Boolean bool = (Boolean) this.f3735b.get(cls);
        if (bool != null) {
            return bool.booleanValue();
        }
        Method[] b8 = b(cls);
        for (Method method : b8) {
            if (((u) method.getAnnotation(u.class)) != null) {
                a(cls, b8);
                return true;
            }
        }
        this.f3735b.put(cls, Boolean.FALSE);
        return false;
    }
}
