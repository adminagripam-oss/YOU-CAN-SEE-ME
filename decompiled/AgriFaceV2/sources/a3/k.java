package a3;

import android.content.Context;
import java.util.concurrent.Executor;
/* loaded from: classes.dex */
public final class k implements w2.b {

    /* renamed from: a  reason: collision with root package name */
    private final q6.a f211a;

    /* renamed from: b  reason: collision with root package name */
    private final q6.a f212b;

    /* renamed from: c  reason: collision with root package name */
    private final q6.a f213c;

    /* renamed from: d  reason: collision with root package name */
    private final q6.a f214d;

    /* renamed from: e  reason: collision with root package name */
    private final q6.a f215e;

    /* renamed from: f  reason: collision with root package name */
    private final q6.a f216f;

    /* renamed from: g  reason: collision with root package name */
    private final q6.a f217g;

    public k(q6.a aVar, q6.a aVar2, q6.a aVar3, q6.a aVar4, q6.a aVar5, q6.a aVar6, q6.a aVar7) {
        this.f211a = aVar;
        this.f212b = aVar2;
        this.f213c = aVar3;
        this.f214d = aVar4;
        this.f215e = aVar5;
        this.f216f = aVar6;
        this.f217g = aVar7;
    }

    public static k a(q6.a aVar, q6.a aVar2, q6.a aVar3, q6.a aVar4, q6.a aVar5, q6.a aVar6, q6.a aVar7) {
        return new k(aVar, aVar2, aVar3, aVar4, aVar5, aVar6, aVar7);
    }

    public static j c(Context context, v2.e eVar, b3.c cVar, p pVar, Executor executor, c3.b bVar, d3.a aVar) {
        return new j(context, eVar, cVar, pVar, executor, bVar, aVar);
    }

    @Override // q6.a
    /* renamed from: b */
    public j get() {
        return c((Context) this.f211a.get(), (v2.e) this.f212b.get(), (b3.c) this.f213c.get(), (p) this.f214d.get(), (Executor) this.f215e.get(), (c3.b) this.f216f.get(), (d3.a) this.f217g.get());
    }
}
