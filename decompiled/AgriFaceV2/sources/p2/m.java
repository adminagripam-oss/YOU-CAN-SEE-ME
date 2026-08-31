package p2;

import android.content.Context;
/* loaded from: classes.dex */
public class m {

    /* renamed from: a  reason: collision with root package name */
    private static m f10067a;

    private m() {
    }

    public static synchronized m a() {
        m mVar;
        synchronized (m.class) {
            if (f10067a == null) {
                f10067a = new m();
            }
            mVar = f10067a;
        }
        return mVar;
    }

    public n b(Context context, o2.a aVar) {
        if (androidx.core.content.a.a(context, "android.permission.ACCESS_FINE_LOCATION") == 0) {
            return n.precise;
        }
        if (androidx.core.content.a.a(context, "android.permission.ACCESS_COARSE_LOCATION") == 0) {
            return n.reduced;
        }
        aVar.a(o2.b.permissionDenied);
        return null;
    }
}
