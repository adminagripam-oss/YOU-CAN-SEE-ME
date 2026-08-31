package a3;

import java.util.concurrent.Executor;
/* loaded from: classes.dex */
public class n {

    /* renamed from: a  reason: collision with root package name */
    private final Executor f220a;

    /* renamed from: b  reason: collision with root package name */
    private final b3.c f221b;

    /* renamed from: c  reason: collision with root package name */
    private final p f222c;

    /* renamed from: d  reason: collision with root package name */
    private final c3.b f223d;

    /* JADX INFO: Access modifiers changed from: package-private */
    public n(Executor executor, b3.c cVar, p pVar, c3.b bVar) {
        this.f220a = executor;
        this.f221b = cVar;
        this.f222c = pVar;
        this.f223d = bVar;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ Object b(n nVar) {
        for (u2.m mVar : nVar.f221b.B()) {
            nVar.f222c.a(mVar, 1);
        }
        return null;
    }

    public void a() {
        this.f220a.execute(l.a(this));
    }
}
