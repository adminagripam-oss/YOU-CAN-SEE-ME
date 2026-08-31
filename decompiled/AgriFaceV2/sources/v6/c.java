package v6;
/* loaded from: classes.dex */
public final class c implements t6.d {

    /* renamed from: e  reason: collision with root package name */
    public static final c f12432e = new c();

    private c() {
    }

    @Override // t6.d
    public t6.g getContext() {
        throw new IllegalStateException("This continuation is already complete".toString());
    }

    @Override // t6.d
    public void i(Object obj) {
        throw new IllegalStateException("This continuation is already complete".toString());
    }

    public String toString() {
        return "This continuation is already complete";
    }
}
