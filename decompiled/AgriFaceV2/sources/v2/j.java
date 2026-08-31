package v2;

import android.content.Context;
/* loaded from: classes.dex */
public final class j implements w2.b {

    /* renamed from: a  reason: collision with root package name */
    private final q6.a f12341a;

    /* renamed from: b  reason: collision with root package name */
    private final q6.a f12342b;

    /* renamed from: c  reason: collision with root package name */
    private final q6.a f12343c;

    public j(q6.a aVar, q6.a aVar2, q6.a aVar3) {
        this.f12341a = aVar;
        this.f12342b = aVar2;
        this.f12343c = aVar3;
    }

    public static j a(q6.a aVar, q6.a aVar2, q6.a aVar3) {
        return new j(aVar, aVar2, aVar3);
    }

    public static i c(Context context, d3.a aVar, d3.a aVar2) {
        return new i(context, aVar, aVar2);
    }

    @Override // q6.a
    /* renamed from: b */
    public i get() {
        return c((Context) this.f12341a.get(), (d3.a) this.f12342b.get(), (d3.a) this.f12343c.get());
    }
}
