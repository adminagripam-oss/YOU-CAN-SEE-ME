package z2;

import a3.p;
import java.util.concurrent.Executor;
/* loaded from: classes.dex */
public final class d implements w2.b {

    /* renamed from: a  reason: collision with root package name */
    private final q6.a f13391a;

    /* renamed from: b  reason: collision with root package name */
    private final q6.a f13392b;

    /* renamed from: c  reason: collision with root package name */
    private final q6.a f13393c;

    /* renamed from: d  reason: collision with root package name */
    private final q6.a f13394d;

    /* renamed from: e  reason: collision with root package name */
    private final q6.a f13395e;

    public d(q6.a aVar, q6.a aVar2, q6.a aVar3, q6.a aVar4, q6.a aVar5) {
        this.f13391a = aVar;
        this.f13392b = aVar2;
        this.f13393c = aVar3;
        this.f13394d = aVar4;
        this.f13395e = aVar5;
    }

    public static d a(q6.a aVar, q6.a aVar2, q6.a aVar3, q6.a aVar4, q6.a aVar5) {
        return new d(aVar, aVar2, aVar3, aVar4, aVar5);
    }

    public static c c(Executor executor, v2.e eVar, p pVar, b3.c cVar, c3.b bVar) {
        return new c(executor, eVar, pVar, cVar, bVar);
    }

    @Override // q6.a
    /* renamed from: b */
    public c get() {
        return c((Executor) this.f13391a.get(), (v2.e) this.f13392b.get(), (p) this.f13393c.get(), (b3.c) this.f13394d.get(), (c3.b) this.f13395e.get());
    }
}
