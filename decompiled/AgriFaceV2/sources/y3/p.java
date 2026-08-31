package y3;

import java.util.Objects;
/* loaded from: classes.dex */
final class p extends f {

    /* renamed from: h  reason: collision with root package name */
    private final transient Object[] f13259h;

    /* renamed from: i  reason: collision with root package name */
    private final transient int f13260i;

    /* renamed from: j  reason: collision with root package name */
    private final transient int f13261j;

    /* JADX INFO: Access modifiers changed from: package-private */
    public p(Object[] objArr, int i8, int i9) {
        this.f13259h = objArr;
        this.f13260i = i8;
        this.f13261j = i9;
    }

    @Override // java.util.List
    public final Object get(int i8) {
        t0.a(i8, this.f13261j, "index");
        Object obj = this.f13259h[i8 + i8 + this.f13260i];
        Objects.requireNonNull(obj);
        return obj;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return this.f13261j;
    }
}
