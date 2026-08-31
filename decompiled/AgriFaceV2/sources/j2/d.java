package j2;

import android.app.Activity;
import android.content.Context;
import androidx.window.extensions.layout.WindowLayoutComponent;
import androidx.window.extensions.layout.WindowLayoutInfo;
import c7.l;
import d7.j;
import d7.k;
import d7.s;
import e2.d;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executor;
import java.util.concurrent.locks.ReentrantLock;
import r6.r;
import s6.n;
/* loaded from: classes.dex */
public final class d implements i2.a {

    /* renamed from: a  reason: collision with root package name */
    private final WindowLayoutComponent f8188a;

    /* renamed from: b  reason: collision with root package name */
    private final e2.d f8189b;

    /* renamed from: c  reason: collision with root package name */
    private final ReentrantLock f8190c;

    /* renamed from: d  reason: collision with root package name */
    private final Map f8191d;

    /* renamed from: e  reason: collision with root package name */
    private final Map f8192e;

    /* renamed from: f  reason: collision with root package name */
    private final Map f8193f;

    /* loaded from: classes.dex */
    /* synthetic */ class a extends j implements l {
        a(Object obj) {
            super(1, obj, g.class, "accept", "accept(Landroidx/window/extensions/layout/WindowLayoutInfo;)V", 0);
        }

        @Override // c7.l
        public /* bridge */ /* synthetic */ Object g(Object obj) {
            l((WindowLayoutInfo) obj);
            return r.f11372a;
        }

        public final void l(WindowLayoutInfo windowLayoutInfo) {
            k.e(windowLayoutInfo, "p0");
            ((g) this.f6463f).accept(windowLayoutInfo);
        }
    }

    public d(WindowLayoutComponent windowLayoutComponent, e2.d dVar) {
        k.e(windowLayoutComponent, "component");
        k.e(dVar, "consumerAdapter");
        this.f8188a = windowLayoutComponent;
        this.f8189b = dVar;
        this.f8190c = new ReentrantLock();
        this.f8191d = new LinkedHashMap();
        this.f8192e = new LinkedHashMap();
        this.f8193f = new LinkedHashMap();
    }

    @Override // i2.a
    public void a(g1.a aVar) {
        k.e(aVar, "callback");
        ReentrantLock reentrantLock = this.f8190c;
        reentrantLock.lock();
        try {
            Context context = (Context) this.f8192e.get(aVar);
            if (context == null) {
                return;
            }
            g gVar = (g) this.f8191d.get(context);
            if (gVar == null) {
                return;
            }
            gVar.d(aVar);
            this.f8192e.remove(aVar);
            if (gVar.c()) {
                this.f8191d.remove(context);
                d.b bVar = (d.b) this.f8193f.remove(gVar);
                if (bVar != null) {
                    bVar.dispose();
                }
            }
            r rVar = r.f11372a;
        } finally {
            reentrantLock.unlock();
        }
    }

    @Override // i2.a
    public void b(Context context, Executor executor, g1.a aVar) {
        r rVar;
        List f8;
        k.e(context, "context");
        k.e(executor, "executor");
        k.e(aVar, "callback");
        ReentrantLock reentrantLock = this.f8190c;
        reentrantLock.lock();
        try {
            g gVar = (g) this.f8191d.get(context);
            if (gVar != null) {
                gVar.b(aVar);
                this.f8192e.put(aVar, context);
                rVar = r.f11372a;
            } else {
                rVar = null;
            }
            if (rVar == null) {
                g gVar2 = new g(context);
                this.f8191d.put(context, gVar2);
                this.f8192e.put(aVar, context);
                gVar2.b(aVar);
                if (!(context instanceof Activity)) {
                    f8 = n.f();
                    gVar2.accept(new WindowLayoutInfo(f8));
                    return;
                }
                this.f8193f.put(gVar2, this.f8189b.c(this.f8188a, s.b(WindowLayoutInfo.class), "addWindowLayoutInfoListener", "removeWindowLayoutInfoListener", (Activity) context, new a(gVar2)));
            }
            r rVar2 = r.f11372a;
        } finally {
            reentrantLock.unlock();
        }
    }
}
