package j2;

import android.content.Context;
import androidx.window.extensions.layout.WindowLayoutComponent;
import d7.k;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.Executor;
import java.util.concurrent.locks.ReentrantLock;
import r6.r;
/* loaded from: classes.dex */
public final class e implements i2.a {

    /* renamed from: a  reason: collision with root package name */
    private final WindowLayoutComponent f8194a;

    /* renamed from: b  reason: collision with root package name */
    private final ReentrantLock f8195b;

    /* renamed from: c  reason: collision with root package name */
    private final Map f8196c;

    /* renamed from: d  reason: collision with root package name */
    private final Map f8197d;

    public e(WindowLayoutComponent windowLayoutComponent) {
        k.e(windowLayoutComponent, "component");
        this.f8194a = windowLayoutComponent;
        this.f8195b = new ReentrantLock();
        this.f8196c = new LinkedHashMap();
        this.f8197d = new LinkedHashMap();
    }

    @Override // i2.a
    public void a(g1.a aVar) {
        k.e(aVar, "callback");
        ReentrantLock reentrantLock = this.f8195b;
        reentrantLock.lock();
        try {
            Context context = (Context) this.f8197d.get(aVar);
            if (context == null) {
                return;
            }
            g gVar = (g) this.f8196c.get(context);
            if (gVar == null) {
                return;
            }
            gVar.d(aVar);
            this.f8197d.remove(aVar);
            if (gVar.c()) {
                this.f8196c.remove(context);
                this.f8194a.removeWindowLayoutInfoListener(gVar);
            }
            r rVar = r.f11372a;
        } finally {
            reentrantLock.unlock();
        }
    }

    @Override // i2.a
    public void b(Context context, Executor executor, g1.a aVar) {
        r rVar;
        k.e(context, "context");
        k.e(executor, "executor");
        k.e(aVar, "callback");
        ReentrantLock reentrantLock = this.f8195b;
        reentrantLock.lock();
        try {
            g gVar = (g) this.f8196c.get(context);
            if (gVar != null) {
                gVar.b(aVar);
                this.f8197d.put(aVar, context);
                rVar = r.f11372a;
            } else {
                rVar = null;
            }
            if (rVar == null) {
                g gVar2 = new g(context);
                this.f8196c.put(context, gVar2);
                this.f8197d.put(aVar, context);
                gVar2.b(aVar);
                this.f8194a.addWindowLayoutInfoListener(context, gVar2);
            }
            r rVar2 = r.f11372a;
        } finally {
            reentrantLock.unlock();
        }
    }
}
