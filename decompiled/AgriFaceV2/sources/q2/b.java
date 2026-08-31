package q2;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.util.Log;
import i6.m;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
/* loaded from: classes.dex */
public class b implements m {

    /* renamed from: d  reason: collision with root package name */
    private static b f10316d;

    /* renamed from: a  reason: collision with root package name */
    private Activity f10317a;

    /* renamed from: b  reason: collision with root package name */
    private o2.a f10318b;

    /* renamed from: c  reason: collision with root package name */
    private c f10319c;

    private b() {
    }

    public static synchronized b c() {
        b bVar;
        synchronized (b.class) {
            if (f10316d == null) {
                f10316d = new b();
            }
            bVar = f10316d;
        }
        return bVar;
    }

    private static List d(Context context) {
        boolean b8 = f.b(context, "android.permission.ACCESS_FINE_LOCATION");
        boolean b9 = f.b(context, "android.permission.ACCESS_COARSE_LOCATION");
        if (b8 || b9) {
            ArrayList arrayList = new ArrayList();
            if (b8) {
                arrayList.add("android.permission.ACCESS_FINE_LOCATION");
            }
            if (b9) {
                arrayList.add("android.permission.ACCESS_COARSE_LOCATION");
            }
            return arrayList;
        }
        throw new o2.c();
    }

    private boolean e(String[] strArr, int[] iArr) {
        int g8 = g(strArr, "android.permission.ACCESS_BACKGROUND_LOCATION");
        return g8 >= 0 && iArr[g8] == 0;
    }

    private static int g(Object[] objArr, Object obj) {
        return Arrays.asList(objArr).indexOf(obj);
    }

    @Override // i6.m
    public boolean a(int i8, String[] strArr, int[] iArr) {
        if (i8 != 109) {
            return false;
        }
        Activity activity = this.f10317a;
        if (activity == null) {
            Log.e("Geolocator", "Trying to process permission result without an valid Activity instance");
            o2.a aVar = this.f10318b;
            if (aVar != null) {
                aVar.a(o2.b.activityMissing);
            }
            return false;
        }
        try {
            List<String> d8 = d(activity);
            if (iArr.length == 0) {
                Log.i("Geolocator", "The grantResults array is empty. This can happen when the user cancels the permission request");
                return false;
            }
            a aVar2 = a.denied;
            char c8 = 65535;
            boolean z7 = false;
            boolean z8 = false;
            for (String str : d8) {
                int g8 = g(strArr, str);
                if (g8 >= 0) {
                    z7 = true;
                }
                if (iArr[g8] == 0) {
                    c8 = 0;
                }
                if (androidx.core.app.b.v(this.f10317a, str)) {
                    z8 = true;
                }
            }
            if (!z7) {
                Log.w("Geolocator", "Location permissions not part of permissions send to onRequestPermissionsResult method.");
                return false;
            }
            if (c8 == 0) {
                aVar2 = (Build.VERSION.SDK_INT < 29 || e(strArr, iArr)) ? a.always : a.whileInUse;
            } else if (!z8) {
                aVar2 = a.deniedForever;
            }
            c cVar = this.f10319c;
            if (cVar != null) {
                cVar.a(aVar2);
            }
            return true;
        } catch (o2.c unused) {
            o2.a aVar3 = this.f10318b;
            if (aVar3 != null) {
                aVar3.a(o2.b.permissionDefinitionsNotFound);
            }
            return false;
        }
    }

    public a b(Context context) {
        boolean z7;
        Iterator it = d(context).iterator();
        while (true) {
            if (!it.hasNext()) {
                z7 = true;
                break;
            } else if (androidx.core.content.a.a(context, (String) it.next()) == 0) {
                z7 = false;
                break;
            }
        }
        if (z7) {
            return a.denied;
        }
        if (Build.VERSION.SDK_INT < 29) {
            return a.always;
        }
        if (f.b(context, "android.permission.ACCESS_BACKGROUND_LOCATION") && androidx.core.content.a.a(context, "android.permission.ACCESS_BACKGROUND_LOCATION") == 0) {
            return a.always;
        }
        return a.whileInUse;
    }

    public boolean f(Context context) {
        a b8 = b(context);
        return b8 == a.whileInUse || b8 == a.always;
    }

    public void h(Activity activity, c cVar, o2.a aVar) {
        if (activity == null) {
            aVar.a(o2.b.activityMissing);
            return;
        }
        int i8 = Build.VERSION.SDK_INT;
        List d8 = d(activity);
        if (i8 >= 29 && f.b(activity, "android.permission.ACCESS_BACKGROUND_LOCATION") && b(activity) == a.whileInUse) {
            d8.add("android.permission.ACCESS_BACKGROUND_LOCATION");
        }
        this.f10318b = aVar;
        this.f10319c = cVar;
        this.f10317a = activity;
        androidx.core.app.b.s(activity, (String[]) d8.toArray(new String[0]), 109);
    }
}
