package f4;

import java.util.concurrent.Executor;
/* loaded from: classes.dex */
final class s implements x {

    /* renamed from: a  reason: collision with root package name */
    private final Executor f6755a;

    /* renamed from: b  reason: collision with root package name */
    private final Object f6756b = new Object();

    /* renamed from: c  reason: collision with root package name */
    private e f6757c;

    public s(Executor executor, e eVar) {
        this.f6755a = executor;
        this.f6757c = eVar;
    }

    @Override // f4.x
    public final void a(j jVar) {
        synchronized (this.f6756b) {
            if (this.f6757c == null) {
                return;
            }
            this.f6755a.execute(new r(this, jVar));
        }
    }
}
