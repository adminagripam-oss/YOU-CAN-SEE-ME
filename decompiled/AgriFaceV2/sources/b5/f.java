package b5;

import android.content.ComponentName;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.pm.ServiceInfo;
import android.os.Bundle;
import android.util.Log;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
/* loaded from: classes.dex */
public final class f {

    /* renamed from: a  reason: collision with root package name */
    private final Object f4646a;

    /* renamed from: b  reason: collision with root package name */
    private final c f4647b;

    /* loaded from: classes.dex */
    private static class b implements c {

        /* renamed from: a  reason: collision with root package name */
        private final Class f4648a;

        private b(Class cls) {
            this.f4648a = cls;
        }

        private Bundle b(Context context) {
            try {
                PackageManager packageManager = context.getPackageManager();
                if (packageManager == null) {
                    Log.w("ComponentDiscovery", "Context has no PackageManager.");
                    return null;
                }
                ServiceInfo serviceInfo = packageManager.getServiceInfo(new ComponentName(context, this.f4648a), 128);
                if (serviceInfo == null) {
                    Log.w("ComponentDiscovery", this.f4648a + " has no service info.");
                    return null;
                }
                return serviceInfo.metaData;
            } catch (PackageManager.NameNotFoundException unused) {
                Log.w("ComponentDiscovery", "Application info not found.");
                return null;
            }
        }

        @Override // b5.f.c
        /* renamed from: c */
        public List a(Context context) {
            Bundle b8 = b(context);
            if (b8 == null) {
                Log.w("ComponentDiscovery", "Could not retrieve metadata, returning empty list of registrars.");
                return Collections.emptyList();
            }
            ArrayList arrayList = new ArrayList();
            for (String str : b8.keySet()) {
                if ("com.google.firebase.components.ComponentRegistrar".equals(b8.get(str)) && str.startsWith("com.google.firebase.components:")) {
                    arrayList.add(str.substring(31));
                }
            }
            return arrayList;
        }
    }

    /* loaded from: classes.dex */
    interface c {
        List a(Object obj);
    }

    f(Object obj, c cVar) {
        this.f4646a = obj;
        this.f4647b = cVar;
    }

    public static f b(Context context, Class cls) {
        return new f(context, new b(cls));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static h c(String str) {
        try {
            Class<?> cls = Class.forName(str);
            if (h.class.isAssignableFrom(cls)) {
                return (h) cls.getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
            }
            throw new t(String.format("Class %s is not an instance of %s", str, "com.google.firebase.components.ComponentRegistrar"));
        } catch (ClassNotFoundException unused) {
            Log.w("ComponentDiscovery", String.format("Class %s is not an found.", str));
            return null;
        } catch (IllegalAccessException e8) {
            throw new t(String.format("Could not instantiate %s.", str), e8);
        } catch (InstantiationException e9) {
            throw new t(String.format("Could not instantiate %s.", str), e9);
        } catch (NoSuchMethodException e10) {
            throw new t(String.format("Could not instantiate %s", str), e10);
        } catch (InvocationTargetException e11) {
            throw new t(String.format("Could not instantiate %s", str), e11);
        }
    }

    public List a() {
        ArrayList arrayList = new ArrayList();
        for (String str : this.f4647b.a(this.f4646a)) {
            arrayList.add(e.a(str));
        }
        return arrayList;
    }
}
