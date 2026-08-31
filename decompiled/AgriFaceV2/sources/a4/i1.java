package a4;
/* loaded from: classes.dex */
final class i1 extends p0 {

    /* renamed from: g  reason: collision with root package name */
    private final transient Object[] f663g;

    /* renamed from: h  reason: collision with root package name */
    private final transient int f664h;

    /* renamed from: i  reason: collision with root package name */
    private final transient int f665i;

    /* JADX INFO: Access modifiers changed from: package-private */
    public i1(Object[] objArr, int i8, int i9) {
        this.f663g = objArr;
        this.f664h = i8;
        this.f665i = i9;
    }

    @Override // java.util.List
    public final Object get(int i8) {
        c.a(i8, this.f665i, "index");
        Object obj = this.f663g[i8 + i8 + this.f664h];
        obj.getClass();
        return obj;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return this.f665i;
    }
}
