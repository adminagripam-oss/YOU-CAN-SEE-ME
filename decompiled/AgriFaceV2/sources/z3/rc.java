package z3;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class rc extends mc {

    /* renamed from: i  reason: collision with root package name */
    static final mc f14053i = new rc(new Object[0], 0);

    /* renamed from: g  reason: collision with root package name */
    final transient Object[] f14054g;

    /* renamed from: h  reason: collision with root package name */
    private final transient int f14055h;

    /* JADX INFO: Access modifiers changed from: package-private */
    public rc(Object[] objArr, int i8) {
        this.f14054g = objArr;
        this.f14055h = i8;
    }

    @Override // z3.mc, z3.la
    final int b(Object[] objArr, int i8) {
        System.arraycopy(this.f14054g, 0, objArr, 0, this.f14055h);
        return this.f14055h;
    }

    @Override // z3.la
    final int f() {
        return this.f14055h;
    }

    @Override // java.util.List
    public final Object get(int i8) {
        f4.a(i8, this.f14055h, "index");
        Object obj = this.f14054g[i8];
        obj.getClass();
        return obj;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // z3.la
    public final int j() {
        return 0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // z3.la
    public final Object[] k() {
        return this.f14054g;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return this.f14055h;
    }
}
