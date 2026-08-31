package f4;

import java.util.concurrent.Executor;
/* loaded from: classes.dex */
final class u implements x {

    /* renamed from: a  reason: collision with root package name */
    private final Executor f6760a;

    /* renamed from: b  reason: collision with root package name */
    private final Object f6761b = new Object();

    /* renamed from: c  reason: collision with root package name */
    private f f6762c;

    public u(Executor executor, f fVar) {
        this.f6760a = executor;
        this.f6762c = fVar;
    }

    @Override // f4.x
    public final void a(j jVar) {
        if (jVar.j() || jVar.h()) {
            return;
        }
        synchronized (this.f6761b) {
            if (this.f6762c == null) {
                return;
            }
            this.f6760a.execute(new t(this, jVar));
        }
    }
}
