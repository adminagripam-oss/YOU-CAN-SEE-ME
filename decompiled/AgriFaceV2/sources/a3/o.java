package a3;

import java.util.concurrent.Executor;
/* loaded from: classes.dex */
public final class o implements w2.b {

    /* renamed from: a  reason: collision with root package name */
    private final q6.a f224a;

    /* renamed from: b  reason: collision with root package name */
    private final q6.a f225b;

    /* renamed from: c  reason: collision with root package name */
    private final q6.a f226c;

    /* renamed from: d  reason: collision with root package name */
    private final q6.a f227d;

    public o(q6.a aVar, q6.a aVar2, q6.a aVar3, q6.a aVar4) {
        this.f224a = aVar;
        this.f225b = aVar2;
        this.f226c = aVar3;
        this.f227d = aVar4;
    }

    public static o a(q6.a aVar, q6.a aVar2, q6.a aVar3, q6.a aVar4) {
        return new o(aVar, aVar2, aVar3, aVar4);
    }

    public static n c(Executor executor, b3.c cVar, p pVar, c3.b bVar) {
        return new n(executor, cVar, pVar, bVar);
    }

    @Override // q6.a
    /* renamed from: b */
    public n get() {
        return c((Executor) this.f224a.get(), (b3.c) this.f225b.get(), (p) this.f226c.get(), (c3.b) this.f227d.get());
    }
}
