package x3;

import java.util.Objects;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class w0 extends v0 {

    /* renamed from: i  reason: collision with root package name */
    static final v0 f12708i = new w0(new Object[0], 0);

    /* renamed from: g  reason: collision with root package name */
    final transient Object[] f12709g;

    /* renamed from: h  reason: collision with root package name */
    private final transient int f12710h;

    /* JADX INFO: Access modifiers changed from: package-private */
    public w0(Object[] objArr, int i8) {
        this.f12709g = objArr;
        this.f12710h = i8;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // x3.s0
    public final Object[] b() {
        return this.f12709g;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // x3.s0
    public final int f() {
        return 0;
    }

    @Override // java.util.List
    public final Object get(int i8) {
        p0.a(i8, this.f12710h, "index");
        Object obj = this.f12709g[i8];
        Objects.requireNonNull(obj);
        return obj;
    }

    @Override // x3.s0
    final int j() {
        return this.f12710h;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // x3.s0
    public final boolean l() {
        return false;
    }

    @Override // x3.v0, x3.s0
    final int m(Object[] objArr, int i8) {
        System.arraycopy(this.f12709g, 0, objArr, 0, this.f12710h);
        return this.f12710h;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return this.f12710h;
    }
}
