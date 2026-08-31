package k7;
/* loaded from: classes.dex */
public abstract class m1 {

    /* renamed from: a  reason: collision with root package name */
    private static final p7.h0 f8761a = new p7.h0("COMPLETING_ALREADY");

    /* renamed from: b  reason: collision with root package name */
    public static final p7.h0 f8762b = new p7.h0("COMPLETING_WAITING_CHILDREN");

    /* renamed from: c  reason: collision with root package name */
    private static final p7.h0 f8763c = new p7.h0("COMPLETING_RETRY");

    /* renamed from: d  reason: collision with root package name */
    private static final p7.h0 f8764d = new p7.h0("TOO_LATE_TO_CANCEL");

    /* renamed from: e  reason: collision with root package name */
    private static final p7.h0 f8765e = new p7.h0("SEALED");

    /* renamed from: f  reason: collision with root package name */
    private static final p0 f8766f = new p0(false);

    /* renamed from: g  reason: collision with root package name */
    private static final p0 f8767g = new p0(true);

    public static final Object g(Object obj) {
        return obj instanceof a1 ? new b1((a1) obj) : obj;
    }

    public static final Object h(Object obj) {
        a1 a1Var;
        b1 b1Var = obj instanceof b1 ? (b1) obj : null;
        return (b1Var == null || (a1Var = b1Var.f8721a) == null) ? obj : a1Var;
    }
}
