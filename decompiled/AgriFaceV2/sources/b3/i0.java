package b3;

import android.content.Context;
/* loaded from: classes.dex */
public final class i0 implements w2.b {

    /* renamed from: a  reason: collision with root package name */
    private final q6.a f4596a;

    /* renamed from: b  reason: collision with root package name */
    private final q6.a f4597b;

    /* renamed from: c  reason: collision with root package name */
    private final q6.a f4598c;

    public i0(q6.a aVar, q6.a aVar2, q6.a aVar3) {
        this.f4596a = aVar;
        this.f4597b = aVar2;
        this.f4598c = aVar3;
    }

    public static i0 a(q6.a aVar, q6.a aVar2, q6.a aVar3) {
        return new i0(aVar, aVar2, aVar3);
    }

    public static h0 c(Context context, String str, int i8) {
        return new h0(context, str, i8);
    }

    @Override // q6.a
    /* renamed from: b */
    public h0 get() {
        return c((Context) this.f4596a.get(), (String) this.f4597b.get(), ((Integer) this.f4598c.get()).intValue());
    }
}
