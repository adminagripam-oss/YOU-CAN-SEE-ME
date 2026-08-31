package p2;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
/* loaded from: classes.dex */
public class k implements i6.l {

    /* renamed from: b  reason: collision with root package name */
    private static k f10058b;

    /* renamed from: a  reason: collision with root package name */
    private final List f10059a = new CopyOnWriteArrayList();

    private k() {
    }

    public static synchronized k b() {
        k kVar;
        synchronized (k.class) {
            if (f10058b == null) {
                f10058b = new k();
            }
            kVar = f10058b;
        }
        return kVar;
    }

    private boolean d(Context context) {
        try {
            return h3.d.m().g(context) == 0;
        } catch (NoClassDefFoundError unused) {
            return false;
        }
    }

    public o a(Context context, boolean z7, w wVar) {
        if (!z7 && d(context)) {
            return new j(context, wVar);
        }
        return new p(context, wVar);
    }

    public void c(Context context, boolean z7, b0 b0Var, o2.a aVar) {
        a(context, z7, null).d(b0Var, aVar);
    }

    public void e(Context context, x xVar) {
        if (context == null) {
            xVar.a(o2.b.locationServicesDisabled);
        }
        a(context, false, null).b(xVar);
    }

    public void f(o oVar, Activity activity, b0 b0Var, o2.a aVar) {
        this.f10059a.add(oVar);
        oVar.e(activity, b0Var, aVar);
    }

    public void g(o oVar) {
        this.f10059a.remove(oVar);
        oVar.f();
    }

    @Override // i6.l, com.pichillilorenzo.flutter_inappwebview_android.in_app_browser.ActivityResultListener
    public boolean onActivityResult(int i8, int i9, Intent intent) {
        for (o oVar : this.f10059a) {
            if (oVar.a(i8, i9)) {
                return true;
            }
        }
        return false;
    }
}
