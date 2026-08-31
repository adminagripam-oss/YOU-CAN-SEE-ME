package v2;

import android.content.ComponentName;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.pm.ServiceInfo;
import android.os.Bundle;
import android.util.Log;
import com.google.android.datatransport.runtime.backends.TransportBackendDiscovery;
import java.lang.reflect.InvocationTargetException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
/* loaded from: classes.dex */
class k implements e {

    /* renamed from: a  reason: collision with root package name */
    private final a f12344a;

    /* renamed from: b  reason: collision with root package name */
    private final i f12345b;

    /* renamed from: c  reason: collision with root package name */
    private final Map f12346c;

    /* loaded from: classes.dex */
    static class a {

        /* renamed from: a  reason: collision with root package name */
        private final Context f12347a;

        /* renamed from: b  reason: collision with root package name */
        private Map f12348b = null;

        a(Context context) {
            this.f12347a = context;
        }

        private Map a(Context context) {
            Bundle d8 = d(context);
            if (d8 == null) {
                Log.w("BackendRegistry", "Could not retrieve metadata, returning empty list of transport backends.");
                return Collections.emptyMap();
            }
            HashMap hashMap = new HashMap();
            for (String str : d8.keySet()) {
                Object obj = d8.get(str);
                if ((obj instanceof String) && str.startsWith("backend:")) {
                    for (String str2 : ((String) obj).split(",", -1)) {
                        String trim = str2.trim();
                        if (!trim.isEmpty()) {
                            hashMap.put(trim, str.substring(8));
                        }
                    }
                }
            }
            return hashMap;
        }

        private Map c() {
            if (this.f12348b == null) {
                this.f12348b = a(this.f12347a);
            }
            return this.f12348b;
        }

        private static Bundle d(Context context) {
            try {
                PackageManager packageManager = context.getPackageManager();
                if (packageManager == null) {
                    Log.w("BackendRegistry", "Context has no PackageManager.");
                    return null;
                }
                ServiceInfo serviceInfo = packageManager.getServiceInfo(new ComponentName(context, TransportBackendDiscovery.class), 128);
                if (serviceInfo == null) {
                    Log.w("BackendRegistry", "TransportBackendDiscovery has no service info.");
                    return null;
                }
                return serviceInfo.metaData;
            } catch (PackageManager.NameNotFoundException unused) {
                Log.w("BackendRegistry", "Application info not found.");
                return null;
            }
        }

        d b(String str) {
            String format;
            String format2;
            String str2 = (String) c().get(str);
            if (str2 == null) {
                return null;
            }
            try {
                return (d) Class.forName(str2).asSubclass(d.class).getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
            } catch (ClassNotFoundException e8) {
                e = e8;
                format2 = String.format("Class %s is not found.", str2);
                Log.w("BackendRegistry", format2, e);
                return null;
            } catch (IllegalAccessException e9) {
                e = e9;
                format2 = String.format("Could not instantiate %s.", str2);
                Log.w("BackendRegistry", format2, e);
                return null;
            } catch (InstantiationException e10) {
                e = e10;
                format2 = String.format("Could not instantiate %s.", str2);
                Log.w("BackendRegistry", format2, e);
                return null;
            } catch (NoSuchMethodException e11) {
                e = e11;
                format = String.format("Could not instantiate %s", str2);
                Log.w("BackendRegistry", format, e);
                return null;
            } catch (InvocationTargetException e12) {
                e = e12;
                format = String.format("Could not instantiate %s", str2);
                Log.w("BackendRegistry", format, e);
                return null;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public k(Context context, i iVar) {
        this(new a(context), iVar);
    }

    @Override // v2.e
    public synchronized m a(String str) {
        if (this.f12346c.containsKey(str)) {
            return (m) this.f12346c.get(str);
        }
        d b8 = this.f12344a.b(str);
        if (b8 == null) {
            return null;
        }
        m create = b8.create(this.f12345b.a(str));
        this.f12346c.put(str, create);
        return create;
    }

    k(a aVar, i iVar) {
        this.f12346c = new HashMap();
        this.f12344a = aVar;
        this.f12345b = iVar;
    }
}
