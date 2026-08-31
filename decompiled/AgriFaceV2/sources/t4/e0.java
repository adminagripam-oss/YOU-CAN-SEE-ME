package t4;
/* loaded from: classes.dex */
public final class e0 implements h0 {

    /* renamed from: a  reason: collision with root package name */
    private final a5.a f11837a;

    /* renamed from: b  reason: collision with root package name */
    private final y4.a0 f11838b;

    private e0(y4.a0 a0Var, a5.a aVar) {
        this.f11838b = a0Var;
        this.f11837a = aVar;
    }

    public static e0 b(y4.a0 a0Var) {
        return new e0(a0Var, k0.b(a0Var.a0()));
    }

    public static e0 c(y4.a0 a0Var) {
        return new e0(a0Var, k0.h(a0Var.a0()));
    }

    @Override // t4.h0
    public a5.a a() {
        return this.f11837a;
    }

    public y4.a0 d() {
        return this.f11838b;
    }
}
