package j3;

import com.google.android.gms.common.api.Status;
/* loaded from: classes.dex */
public abstract class k {
    public static void a(Status status, f4.k kVar) {
        b(status, null, kVar);
    }

    public static void b(Status status, Object obj, f4.k kVar) {
        if (status.e()) {
            kVar.c(obj);
        } else {
            kVar.b(k3.b.a(status));
        }
    }

    public static boolean c(Status status, Object obj, f4.k kVar) {
        return status.e() ? kVar.e(obj) : kVar.d(k3.b.a(status));
    }
}
