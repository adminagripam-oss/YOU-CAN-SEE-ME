package d;

import android.content.Context;
import d7.k;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
/* loaded from: classes.dex */
public final class a {

    /* renamed from: a  reason: collision with root package name */
    private final Set f6349a = new CopyOnWriteArraySet();

    /* renamed from: b  reason: collision with root package name */
    private volatile Context f6350b;

    public final void a(b bVar) {
        k.e(bVar, "listener");
        Context context = this.f6350b;
        if (context != null) {
            bVar.a(context);
        }
        this.f6349a.add(bVar);
    }

    public final void b() {
        this.f6350b = null;
    }

    public final void c(Context context) {
        k.e(context, "context");
        this.f6350b = context;
        for (b bVar : this.f6349a) {
            bVar.a(context);
        }
    }

    public final Context d() {
        return this.f6350b;
    }

    public final void e(b bVar) {
        k.e(bVar, "listener");
        this.f6349a.remove(bVar);
    }
}
