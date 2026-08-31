package g2;

import android.app.Activity;
import d7.k;
import h2.f;
import java.util.concurrent.Executor;
import n7.c;
/* loaded from: classes.dex */
public final class a implements f {

    /* renamed from: b  reason: collision with root package name */
    private final f f7103b;

    /* renamed from: c  reason: collision with root package name */
    private final f2.a f7104c;

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public a(f fVar) {
        this(fVar, new f2.a());
        k.e(fVar, "tracker");
    }

    @Override // h2.f
    public c a(Activity activity) {
        k.e(activity, "activity");
        return this.f7103b.a(activity);
    }

    public final void b(Activity activity, Executor executor, g1.a aVar) {
        k.e(activity, "activity");
        k.e(executor, "executor");
        k.e(aVar, "consumer");
        this.f7104c.a(executor, aVar, this.f7103b.a(activity));
    }

    public final void c(g1.a aVar) {
        k.e(aVar, "consumer");
        this.f7104c.b(aVar);
    }

    private a(f fVar, f2.a aVar) {
        this.f7103b = fVar;
        this.f7104c = aVar;
    }
}
