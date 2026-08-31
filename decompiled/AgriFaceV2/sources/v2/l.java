package v2;

import android.content.Context;
/* loaded from: classes.dex */
public final class l implements w2.b {

    /* renamed from: a  reason: collision with root package name */
    private final q6.a f12349a;

    /* renamed from: b  reason: collision with root package name */
    private final q6.a f12350b;

    public l(q6.a aVar, q6.a aVar2) {
        this.f12349a = aVar;
        this.f12350b = aVar2;
    }

    public static l a(q6.a aVar, q6.a aVar2) {
        return new l(aVar, aVar2);
    }

    public static k c(Context context, Object obj) {
        return new k(context, (i) obj);
    }

    @Override // q6.a
    /* renamed from: b */
    public k get() {
        return c((Context) this.f12349a.get(), this.f12350b.get());
    }
}
