package m5;

import android.content.Context;
import com.google.mlkit.common.internal.MlKitComponentDiscoveryService;
import java.util.concurrent.Executor;
/* loaded from: classes.dex */
public class i {

    /* renamed from: b  reason: collision with root package name */
    private static final Object f9152b = new Object();

    /* renamed from: c  reason: collision with root package name */
    private static i f9153c;

    /* renamed from: a  reason: collision with root package name */
    private b5.m f9154a;

    private i() {
    }

    public static i c() {
        i iVar;
        synchronized (f9152b) {
            k3.q.j(f9153c != null, "MlKitContext has not been initialized");
            iVar = (i) k3.q.g(f9153c);
        }
        return iVar;
    }

    public static i d(Context context) {
        i e8;
        synchronized (f9152b) {
            e8 = e(context, f4.l.f6744a);
        }
        return e8;
    }

    public static i e(Context context, Executor executor) {
        i iVar;
        synchronized (f9152b) {
            k3.q.j(f9153c == null, "MlKitContext is already initialized");
            i iVar2 = new i();
            f9153c = iVar2;
            Context f8 = f(context);
            b5.m c8 = b5.m.e(executor).b(b5.f.b(f8, MlKitComponentDiscoveryService.class).a()).a(b5.c.l(f8, Context.class, new Class[0])).a(b5.c.l(iVar2, i.class, new Class[0])).c();
            iVar2.f9154a = c8;
            c8.h(true);
            iVar = f9153c;
        }
        return iVar;
    }

    private static Context f(Context context) {
        Context applicationContext = context.getApplicationContext();
        return applicationContext != null ? applicationContext : context;
    }

    public Object a(Class cls) {
        k3.q.j(f9153c == this, "MlKitContext has been deleted");
        k3.q.g(this.f9154a);
        return this.f9154a.a(cls);
    }

    public Context b() {
        return (Context) a(Context.class);
    }
}
