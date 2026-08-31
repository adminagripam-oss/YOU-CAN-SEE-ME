package a0;

import android.util.Pair;
import android.util.Size;
import android.view.Surface;
import b0.q1;
import b0.y2;
import java.util.concurrent.Executor;
/* loaded from: classes.dex */
public class e0 implements q1 {

    /* renamed from: a  reason: collision with root package name */
    private final q1 f29a;

    /* renamed from: b  reason: collision with root package name */
    private p0 f30b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public e0(q1 q1Var) {
        this.f29a = q1Var;
    }

    private androidx.camera.core.n i(androidx.camera.core.n nVar) {
        if (nVar == null) {
            return null;
        }
        y2 b8 = this.f30b == null ? y2.b() : y2.a(new Pair(this.f30b.i(), this.f30b.h().get(0)));
        this.f30b = null;
        return new androidx.camera.core.r(nVar, new Size(nVar.getWidth(), nVar.getHeight()), new g0.c(new p0.m(b8, nVar.p().c())));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void j(q1.a aVar, q1 q1Var) {
        aVar.a(this);
    }

    @Override // b0.q1
    public int a() {
        return this.f29a.a();
    }

    @Override // b0.q1
    public androidx.camera.core.n acquireLatestImage() {
        return i(this.f29a.acquireLatestImage());
    }

    @Override // b0.q1
    public void c() {
        this.f29a.c();
    }

    @Override // b0.q1
    public void close() {
        this.f29a.close();
    }

    @Override // b0.q1
    public void d(final q1.a aVar, Executor executor) {
        this.f29a.d(new q1.a() { // from class: a0.d0
            @Override // b0.q1.a
            public final void a(q1 q1Var) {
                e0.this.j(aVar, q1Var);
            }
        }, executor);
    }

    @Override // b0.q1
    public int e() {
        return this.f29a.e();
    }

    @Override // b0.q1
    public androidx.camera.core.n f() {
        return i(this.f29a.f());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void g(p0 p0Var) {
        g1.e.m(this.f30b == null, "Pending request should be null");
        this.f30b = p0Var;
    }

    @Override // b0.q1
    public int getHeight() {
        return this.f29a.getHeight();
    }

    @Override // b0.q1
    public Surface getSurface() {
        return this.f29a.getSurface();
    }

    @Override // b0.q1
    public int getWidth() {
        return this.f29a.getWidth();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void h() {
        this.f30b = null;
    }
}
