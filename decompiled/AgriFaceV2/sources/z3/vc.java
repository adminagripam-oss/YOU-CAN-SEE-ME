package z3;
/* loaded from: classes.dex */
final class vc extends mc {

    /* renamed from: g  reason: collision with root package name */
    private final transient Object[] f14228g;

    /* renamed from: h  reason: collision with root package name */
    private final transient int f14229h;

    /* renamed from: i  reason: collision with root package name */
    private final transient int f14230i;

    /* JADX INFO: Access modifiers changed from: package-private */
    public vc(Object[] objArr, int i8, int i9) {
        this.f14228g = objArr;
        this.f14229h = i8;
        this.f14230i = i9;
    }

    @Override // java.util.List
    public final Object get(int i8) {
        f4.a(i8, this.f14230i, "index");
        Object obj = this.f14228g[i8 + i8 + this.f14229h];
        obj.getClass();
        return obj;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return this.f14230i;
    }
}
