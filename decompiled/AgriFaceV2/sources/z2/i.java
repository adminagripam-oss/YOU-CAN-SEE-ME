package z2;

import a3.p;
import android.content.Context;
/* loaded from: classes.dex */
public final class i implements w2.b {

    /* renamed from: a  reason: collision with root package name */
    private final q6.a f13397a;

    /* renamed from: b  reason: collision with root package name */
    private final q6.a f13398b;

    /* renamed from: c  reason: collision with root package name */
    private final q6.a f13399c;

    /* renamed from: d  reason: collision with root package name */
    private final q6.a f13400d;

    public i(q6.a aVar, q6.a aVar2, q6.a aVar3, q6.a aVar4) {
        this.f13397a = aVar;
        this.f13398b = aVar2;
        this.f13399c = aVar3;
        this.f13400d = aVar4;
    }

    public static i a(q6.a aVar, q6.a aVar2, q6.a aVar3, q6.a aVar4) {
        return new i(aVar, aVar2, aVar3, aVar4);
    }

    public static p c(Context context, b3.c cVar, a3.d dVar, d3.a aVar) {
        return (p) w2.d.c(h.a(context, cVar, dVar, aVar), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // q6.a
    /* renamed from: b */
    public p get() {
        return c((Context) this.f13397a.get(), (b3.c) this.f13398b.get(), (a3.d) this.f13399c.get(), (d3.a) this.f13400d.get());
    }
}
