package j2;

import android.content.Context;
import androidx.window.extensions.core.util.function.Consumer;
import androidx.window.extensions.layout.WindowLayoutInfo;
import d7.k;
import h2.j;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.concurrent.locks.ReentrantLock;
import r6.r;
/* loaded from: classes.dex */
public final class g implements g1.a, Consumer {

    /* renamed from: a  reason: collision with root package name */
    private final Context f8199a;

    /* renamed from: b  reason: collision with root package name */
    private final ReentrantLock f8200b;

    /* renamed from: c  reason: collision with root package name */
    private j f8201c;

    /* renamed from: d  reason: collision with root package name */
    private final Set f8202d;

    public g(Context context) {
        k.e(context, "context");
        this.f8199a = context;
        this.f8200b = new ReentrantLock();
        this.f8202d = new LinkedHashSet();
    }

    @Override // g1.a
    /* renamed from: a */
    public void accept(WindowLayoutInfo windowLayoutInfo) {
        k.e(windowLayoutInfo, "value");
        ReentrantLock reentrantLock = this.f8200b;
        reentrantLock.lock();
        try {
            this.f8201c = f.f8198a.b(this.f8199a, windowLayoutInfo);
            for (g1.a aVar : this.f8202d) {
                aVar.accept(this.f8201c);
            }
            r rVar = r.f11372a;
        } finally {
            reentrantLock.unlock();
        }
    }

    public final void b(g1.a aVar) {
        k.e(aVar, "listener");
        ReentrantLock reentrantLock = this.f8200b;
        reentrantLock.lock();
        try {
            j jVar = this.f8201c;
            if (jVar != null) {
                aVar.accept(jVar);
            }
            this.f8202d.add(aVar);
        } finally {
            reentrantLock.unlock();
        }
    }

    public final boolean c() {
        return this.f8202d.isEmpty();
    }

    public final void d(g1.a aVar) {
        k.e(aVar, "listener");
        ReentrantLock reentrantLock = this.f8200b;
        reentrantLock.lock();
        try {
            this.f8202d.remove(aVar);
        } finally {
            reentrantLock.unlock();
        }
    }
}
