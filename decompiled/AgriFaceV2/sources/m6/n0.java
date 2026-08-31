package m6;

import android.util.Range;
import m6.t0;
/* loaded from: classes.dex */
public class n0 extends t0.z {

    /* renamed from: b  reason: collision with root package name */
    private final v4 f9383b;

    public n0(i6.b bVar, v4 v4Var) {
        super(bVar);
        this.f9383b = v4Var;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void e(y.b0 b0Var, t0.z.a aVar) {
        if (this.f9383b.e(b0Var)) {
            return;
        }
        Range a8 = b0Var.a();
        b(Long.valueOf(this.f9383b.b(b0Var)), new t0.y.a().c(Long.valueOf(((Integer) a8.getLower()).longValue())).b(Long.valueOf(((Integer) a8.getUpper()).longValue())).a(), Double.valueOf(b0Var.b().doubleValue()), aVar);
    }
}
