package androidx.startup;

import android.content.ComponentName;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import x1.b;
import x1.c;
/* loaded from: classes.dex */
public final class a {

    /* renamed from: d  reason: collision with root package name */
    private static volatile a f3928d;

    /* renamed from: e  reason: collision with root package name */
    private static final Object f3929e = new Object();

    /* renamed from: c  reason: collision with root package name */
    final Context f3932c;

    /* renamed from: b  reason: collision with root package name */
    final Set f3931b = new HashSet();

    /* renamed from: a  reason: collision with root package name */
    final Map f3930a = new HashMap();

    a(Context context) {
        this.f3932c = context.getApplicationContext();
    }

    private Object d(Class cls, Set set) {
        Object obj;
        if (z1.a.h()) {
            try {
                z1.a.c(cls.getSimpleName());
            } finally {
                z1.a.f();
            }
        }
        if (set.contains(cls)) {
            throw new IllegalStateException(String.format("Cannot initialize %s. Cycle detected.", cls.getName()));
        }
        if (this.f3930a.containsKey(cls)) {
            obj = this.f3930a.get(cls);
        } else {
            set.add(cls);
            x1.a aVar = (x1.a) cls.getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
            List<Class> a8 = aVar.a();
            if (!a8.isEmpty()) {
                for (Class cls2 : a8) {
                    if (!this.f3930a.containsKey(cls2)) {
                        d(cls2, set);
                    }
                }
            }
            obj = aVar.b(this.f3932c);
            set.remove(cls);
            this.f3930a.put(cls, obj);
        }
        return obj;
    }

    public static a e(Context context) {
        if (f3928d == null) {
            synchronized (f3929e) {
                if (f3928d == null) {
                    f3928d = new a(context);
                }
            }
        }
        return f3928d;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a() {
        try {
            try {
                z1.a.c("Startup");
                b(this.f3932c.getPackageManager().getProviderInfo(new ComponentName(this.f3932c.getPackageName(), InitializationProvider.class.getName()), 128).metaData);
            } catch (PackageManager.NameNotFoundException e8) {
                throw new c(e8);
            }
        } finally {
            z1.a.f();
        }
    }

    void b(Bundle bundle) {
        String string = this.f3932c.getString(b.f12648a);
        if (bundle != null) {
            try {
                HashSet hashSet = new HashSet();
                for (String str : bundle.keySet()) {
                    if (string.equals(bundle.getString(str, null))) {
                        Class<?> cls = Class.forName(str);
                        if (x1.a.class.isAssignableFrom(cls)) {
                            this.f3931b.add(cls);
                        }
                    }
                }
                for (Class cls2 : this.f3931b) {
                    d(cls2, hashSet);
                }
            } catch (ClassNotFoundException e8) {
                throw new c(e8);
            }
        }
    }

    Object c(Class cls) {
        Object obj;
        synchronized (f3929e) {
            obj = this.f3930a.get(cls);
            if (obj == null) {
                obj = d(cls, new HashSet());
            }
        }
        return obj;
    }

    public Object f(Class cls) {
        return c(cls);
    }

    public boolean g(Class cls) {
        return this.f3931b.contains(cls);
    }
}
