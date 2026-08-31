package m6;

import m6.t0;
/* loaded from: classes.dex */
public class o6 extends t0.c2 {

    /* renamed from: b  reason: collision with root package name */
    private final v4 f9405b;

    public o6(i6.b bVar, v4 v4Var) {
        super(bVar);
        this.f9405b = v4Var;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void e(y.j2 j2Var, t0.c2.a aVar) {
        if (this.f9405b.e(j2Var)) {
            return;
        }
        b(Long.valueOf(this.f9405b.b(j2Var)), Double.valueOf(Float.valueOf(j2Var.d()).doubleValue()), Double.valueOf(Float.valueOf(j2Var.a()).doubleValue()), aVar);
    }
}
