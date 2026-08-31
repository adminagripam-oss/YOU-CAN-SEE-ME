package y3;
/* loaded from: classes.dex */
final class g {

    /* renamed from: a  reason: collision with root package name */
    private final Object f13224a;

    /* renamed from: b  reason: collision with root package name */
    private final Object f13225b;

    /* renamed from: c  reason: collision with root package name */
    private final Object f13226c;

    /* JADX INFO: Access modifiers changed from: package-private */
    public g(Object obj, Object obj2, Object obj3) {
        this.f13224a = obj;
        this.f13225b = obj2;
        this.f13226c = obj3;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final IllegalArgumentException a() {
        Object obj = this.f13226c;
        Object obj2 = this.f13225b;
        Object obj3 = this.f13224a;
        String valueOf = String.valueOf(obj3);
        String valueOf2 = String.valueOf(obj2);
        String valueOf3 = String.valueOf(obj3);
        String valueOf4 = String.valueOf(obj);
        return new IllegalArgumentException("Multiple entries with same key: " + valueOf + "=" + valueOf2 + " and " + valueOf3 + "=" + valueOf4);
    }
}
