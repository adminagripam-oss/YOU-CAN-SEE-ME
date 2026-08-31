package p7;
/* loaded from: classes.dex */
public abstract class r {

    /* renamed from: a  reason: collision with root package name */
    private static final Object f10180a = new h0("CONDITION_FALSE");

    public static final Object a() {
        return f10180a;
    }

    public static final s b(Object obj) {
        s sVar;
        b0 b0Var = obj instanceof b0 ? (b0) obj : null;
        if (b0Var == null || (sVar = b0Var.f10136a) == null) {
            d7.k.c(obj, "null cannot be cast to non-null type kotlinx.coroutines.internal.LockFreeLinkedListNode{ kotlinx.coroutines.internal.LockFreeLinkedListKt.Node }");
            return (s) obj;
        }
        return sVar;
    }
}
