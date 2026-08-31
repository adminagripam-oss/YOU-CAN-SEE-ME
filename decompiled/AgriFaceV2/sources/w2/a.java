package w2;
/* loaded from: classes.dex */
public final class a implements q6.a {

    /* renamed from: c  reason: collision with root package name */
    private static final Object f12464c = new Object();

    /* renamed from: a  reason: collision with root package name */
    private volatile q6.a f12465a;

    /* renamed from: b  reason: collision with root package name */
    private volatile Object f12466b = f12464c;

    private a(q6.a aVar) {
        this.f12465a = aVar;
    }

    public static q6.a a(q6.a aVar) {
        d.b(aVar);
        return aVar instanceof a ? aVar : new a(aVar);
    }

    public static Object b(Object obj, Object obj2) {
        if (!(obj != f12464c) || obj == obj2) {
            return obj2;
        }
        throw new IllegalStateException("Scoped provider was invoked recursively returning different results: " + obj + " & " + obj2 + ". This is likely due to a circular dependency.");
    }

    @Override // q6.a
    public Object get() {
        Object obj = this.f12466b;
        Object obj2 = f12464c;
        if (obj == obj2) {
            synchronized (this) {
                obj = this.f12466b;
                if (obj == obj2) {
                    obj = this.f12465a.get();
                    this.f12466b = b(this.f12466b, obj);
                    this.f12465a = null;
                }
            }
        }
        return obj;
    }
}
