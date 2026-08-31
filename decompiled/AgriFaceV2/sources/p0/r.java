package p0;

import b0.g0;
import b0.k1;
import java.util.UUID;
/* loaded from: classes.dex */
public class r extends k1 {

    /* renamed from: b  reason: collision with root package name */
    private final String f9979b;

    /* renamed from: c  reason: collision with root package name */
    private int f9980c;

    /* JADX INFO: Access modifiers changed from: package-private */
    public r(g0 g0Var) {
        super(g0Var);
        this.f9979b = "virtual-" + g0Var.g() + "-" + UUID.randomUUID().toString();
    }

    @Override // b0.k1, y.p
    public int c() {
        return i(0);
    }

    @Override // b0.k1, b0.g0
    public String g() {
        return this.f9979b;
    }

    @Override // b0.k1, y.p
    public int i(int i8) {
        return d0.r.u(super.i(i8) - this.f9980c);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void p(int i8) {
        this.f9980c = i8;
    }
}
