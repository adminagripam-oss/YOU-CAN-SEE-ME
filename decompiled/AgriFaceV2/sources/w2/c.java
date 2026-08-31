package w2;
/* loaded from: classes.dex */
public final class c implements b {

    /* renamed from: b  reason: collision with root package name */
    private static final c f12467b = new c(null);

    /* renamed from: a  reason: collision with root package name */
    private final Object f12468a;

    private c(Object obj) {
        this.f12468a = obj;
    }

    public static b a(Object obj) {
        return new c(d.c(obj, "instance cannot be null"));
    }

    @Override // q6.a
    public Object get() {
        return this.f12468a;
    }
}
