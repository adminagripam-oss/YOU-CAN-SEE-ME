package k7;
/* loaded from: classes.dex */
public abstract class q0 extends a0 {

    /* renamed from: g  reason: collision with root package name */
    private long f8777g;

    /* renamed from: h  reason: collision with root package name */
    private boolean f8778h;

    /* renamed from: i  reason: collision with root package name */
    private s6.e f8779i;

    private final long T(boolean z7) {
        return z7 ? 4294967296L : 1L;
    }

    public static /* synthetic */ void X(q0 q0Var, boolean z7, int i8, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: incrementUseCount");
        }
        if ((i8 & 1) != 0) {
            z7 = false;
        }
        q0Var.W(z7);
    }

    public final void S(boolean z7) {
        long T = this.f8777g - T(z7);
        this.f8777g = T;
        if (T <= 0 && this.f8778h) {
            shutdown();
        }
    }

    public final void U(l0 l0Var) {
        s6.e eVar = this.f8779i;
        if (eVar == null) {
            eVar = new s6.e();
            this.f8779i = eVar;
        }
        eVar.addLast(l0Var);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public long V() {
        s6.e eVar = this.f8779i;
        return (eVar == null || eVar.isEmpty()) ? Long.MAX_VALUE : 0L;
    }

    public final void W(boolean z7) {
        this.f8777g += T(z7);
        if (z7) {
            return;
        }
        this.f8778h = true;
    }

    public final boolean Y() {
        return this.f8777g >= T(true);
    }

    public final boolean Z() {
        s6.e eVar = this.f8779i;
        if (eVar != null) {
            return eVar.isEmpty();
        }
        return true;
    }

    public final boolean a0() {
        l0 l0Var;
        s6.e eVar = this.f8779i;
        if (eVar == null || (l0Var = (l0) eVar.q()) == null) {
            return false;
        }
        l0Var.run();
        return true;
    }

    public abstract void shutdown();
}
