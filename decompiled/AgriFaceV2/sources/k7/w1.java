package k7;
/* loaded from: classes.dex */
public final class w1 extends a0 {

    /* renamed from: g  reason: collision with root package name */
    public static final w1 f8798g = new w1();

    private w1() {
    }

    @Override // k7.a0
    public void P(t6.g gVar, Runnable runnable) {
        androidx.appcompat.app.w.a(gVar.c(a2.f8718f));
        throw new UnsupportedOperationException("Dispatchers.Unconfined.dispatch function can only be used by the yield function. If you wrap Unconfined dispatcher in your code, make sure you properly delegate isDispatchNeeded and dispatch calls.");
    }

    @Override // k7.a0
    public boolean Q(t6.g gVar) {
        return false;
    }

    @Override // k7.a0
    public String toString() {
        return "Dispatchers.Unconfined";
    }
}
