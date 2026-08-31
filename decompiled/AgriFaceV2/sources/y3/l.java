package y3;

import java.util.Objects;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class l extends f {

    /* renamed from: j  reason: collision with root package name */
    static final f f13246j = new l(new Object[0], 0);

    /* renamed from: h  reason: collision with root package name */
    final transient Object[] f13247h;

    /* renamed from: i  reason: collision with root package name */
    private final transient int f13248i;

    /* JADX INFO: Access modifiers changed from: package-private */
    public l(Object[] objArr, int i8) {
        this.f13247h = objArr;
        this.f13248i = i8;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // y3.f, y3.c
    public final int b(Object[] objArr, int i8) {
        System.arraycopy(this.f13247h, 0, objArr, 0, this.f13248i);
        return this.f13248i;
    }

    @Override // y3.c
    final int f() {
        return this.f13248i;
    }

    @Override // java.util.List
    public final Object get(int i8) {
        t0.a(i8, this.f13248i, "index");
        Object obj = this.f13247h[i8];
        Objects.requireNonNull(obj);
        return obj;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // y3.c
    public final int j() {
        return 0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // y3.c
    public final Object[] k() {
        return this.f13247h;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return this.f13248i;
    }
}
