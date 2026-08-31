package f4;

import java.util.concurrent.Executor;
/* loaded from: classes.dex */
final class w implements x {

    /* renamed from: a  reason: collision with root package name */
    private final Executor f6765a;

    /* renamed from: b  reason: collision with root package name */
    private final Object f6766b = new Object();

    /* renamed from: c  reason: collision with root package name */
    private g f6767c;

    public w(Executor executor, g gVar) {
        this.f6765a = executor;
        this.f6767c = gVar;
    }

    @Override // f4.x
    public final void a(j jVar) {
        if (jVar.j()) {
            synchronized (this.f6766b) {
                if (this.f6767c == null) {
                    return;
                }
                this.f6765a.execute(new v(this, jVar));
            }
        }
    }
}
