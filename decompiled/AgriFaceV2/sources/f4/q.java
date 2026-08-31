package f4;

import java.util.concurrent.Executor;
/* loaded from: classes.dex */
final class q implements x {

    /* renamed from: a  reason: collision with root package name */
    private final Executor f6750a;

    /* renamed from: b  reason: collision with root package name */
    private final c f6751b;

    /* renamed from: c  reason: collision with root package name */
    private final c0 f6752c;

    public q(Executor executor, c cVar, c0 c0Var) {
        this.f6750a = executor;
        this.f6751b = cVar;
        this.f6752c = c0Var;
    }

    @Override // f4.x
    public final void a(j jVar) {
        this.f6750a.execute(new p(this, jVar));
    }
}
