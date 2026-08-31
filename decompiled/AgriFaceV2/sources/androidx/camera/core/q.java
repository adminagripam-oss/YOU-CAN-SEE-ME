package androidx.camera.core;

import android.view.Surface;
import androidx.camera.core.e;
import b0.q1;
import java.util.concurrent.Executor;
/* loaded from: classes.dex */
public class q implements q1 {

    /* renamed from: d  reason: collision with root package name */
    private final q1 f2763d;

    /* renamed from: e  reason: collision with root package name */
    private final Surface f2764e;

    /* renamed from: f  reason: collision with root package name */
    private e.a f2765f;

    /* renamed from: a  reason: collision with root package name */
    private final Object f2760a = new Object();

    /* renamed from: b  reason: collision with root package name */
    private int f2761b = 0;

    /* renamed from: c  reason: collision with root package name */
    private boolean f2762c = false;

    /* renamed from: g  reason: collision with root package name */
    private final e.a f2766g = new e.a() { // from class: y.t1
        @Override // androidx.camera.core.e.a
        public final void b(androidx.camera.core.n nVar) {
            androidx.camera.core.q.this.i(nVar);
        }
    };

    public q(q1 q1Var) {
        this.f2763d = q1Var;
        this.f2764e = q1Var.getSurface();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void i(n nVar) {
        e.a aVar;
        synchronized (this.f2760a) {
            int i8 = this.f2761b - 1;
            this.f2761b = i8;
            if (this.f2762c && i8 == 0) {
                close();
            }
            aVar = this.f2765f;
        }
        if (aVar != null) {
            aVar.b(nVar);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void j(q1.a aVar, q1 q1Var) {
        aVar.a(this);
    }

    private n m(n nVar) {
        if (nVar != null) {
            this.f2761b++;
            s sVar = new s(nVar);
            sVar.b(this.f2766g);
            return sVar;
        }
        return null;
    }

    @Override // b0.q1
    public int a() {
        int a8;
        synchronized (this.f2760a) {
            a8 = this.f2763d.a();
        }
        return a8;
    }

    @Override // b0.q1
    public n acquireLatestImage() {
        n m8;
        synchronized (this.f2760a) {
            m8 = m(this.f2763d.acquireLatestImage());
        }
        return m8;
    }

    @Override // b0.q1
    public void c() {
        synchronized (this.f2760a) {
            this.f2763d.c();
        }
    }

    @Override // b0.q1
    public void close() {
        synchronized (this.f2760a) {
            Surface surface = this.f2764e;
            if (surface != null) {
                surface.release();
            }
            this.f2763d.close();
        }
    }

    @Override // b0.q1
    public void d(final q1.a aVar, Executor executor) {
        synchronized (this.f2760a) {
            this.f2763d.d(new q1.a() { // from class: y.u1
                @Override // b0.q1.a
                public final void a(b0.q1 q1Var) {
                    androidx.camera.core.q.this.j(aVar, q1Var);
                }
            }, executor);
        }
    }

    @Override // b0.q1
    public int e() {
        int e8;
        synchronized (this.f2760a) {
            e8 = this.f2763d.e();
        }
        return e8;
    }

    @Override // b0.q1
    public n f() {
        n m8;
        synchronized (this.f2760a) {
            m8 = m(this.f2763d.f());
        }
        return m8;
    }

    @Override // b0.q1
    public int getHeight() {
        int height;
        synchronized (this.f2760a) {
            height = this.f2763d.getHeight();
        }
        return height;
    }

    @Override // b0.q1
    public Surface getSurface() {
        Surface surface;
        synchronized (this.f2760a) {
            surface = this.f2763d.getSurface();
        }
        return surface;
    }

    @Override // b0.q1
    public int getWidth() {
        int width;
        synchronized (this.f2760a) {
            width = this.f2763d.getWidth();
        }
        return width;
    }

    public int h() {
        int e8;
        synchronized (this.f2760a) {
            e8 = this.f2763d.e() - this.f2761b;
        }
        return e8;
    }

    public void k() {
        synchronized (this.f2760a) {
            this.f2762c = true;
            this.f2763d.c();
            if (this.f2761b == 0) {
                close();
            }
        }
    }

    public void l(e.a aVar) {
        synchronized (this.f2760a) {
            this.f2765f = aVar;
        }
    }
}
