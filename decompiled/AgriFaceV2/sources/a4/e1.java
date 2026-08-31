package a4;
/* loaded from: classes.dex */
final class e1 extends p0 {

    /* renamed from: i  reason: collision with root package name */
    static final p0 f491i = new e1(new Object[0], 0);

    /* renamed from: g  reason: collision with root package name */
    final transient Object[] f492g;

    /* renamed from: h  reason: collision with root package name */
    private final transient int f493h;

    /* JADX INFO: Access modifiers changed from: package-private */
    public e1(Object[] objArr, int i8) {
        this.f492g = objArr;
        this.f493h = i8;
    }

    @Override // a4.p0, a4.k0
    final int b(Object[] objArr, int i8) {
        System.arraycopy(this.f492g, 0, objArr, 0, this.f493h);
        return this.f493h;
    }

    @Override // a4.k0
    final int f() {
        return this.f493h;
    }

    @Override // java.util.List
    public final Object get(int i8) {
        c.a(i8, this.f493h, "index");
        Object obj = this.f492g[i8];
        obj.getClass();
        return obj;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // a4.k0
    public final int j() {
        return 0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // a4.k0
    public final Object[] k() {
        return this.f492g;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return this.f493h;
    }
}
