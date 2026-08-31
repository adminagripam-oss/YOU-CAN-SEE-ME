package j2;

import android.content.Context;
import d7.k;
import h2.j;
import java.util.List;
import java.util.concurrent.Executor;
import s6.n;
/* loaded from: classes.dex */
public final class c implements i2.a {
    /* JADX INFO: Access modifiers changed from: private */
    public static final void d(g1.a aVar) {
        List f8;
        k.e(aVar, "$callback");
        f8 = n.f();
        aVar.accept(new j(f8));
    }

    @Override // i2.a
    public void a(g1.a aVar) {
        k.e(aVar, "callback");
    }

    @Override // i2.a
    public void b(Context context, Executor executor, final g1.a aVar) {
        k.e(context, "context");
        k.e(executor, "executor");
        k.e(aVar, "callback");
        executor.execute(new Runnable() { // from class: j2.b
            @Override // java.lang.Runnable
            public final void run() {
                c.d(g1.a.this);
            }
        });
    }
}
