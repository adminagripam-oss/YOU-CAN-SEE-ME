package x3;

import java.util.List;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class u0 extends v0 {

    /* renamed from: g  reason: collision with root package name */
    final transient int f12701g;

    /* renamed from: h  reason: collision with root package name */
    final transient int f12702h;

    /* renamed from: i  reason: collision with root package name */
    final /* synthetic */ v0 f12703i;

    /* JADX INFO: Access modifiers changed from: package-private */
    public u0(v0 v0Var, int i8, int i9) {
        this.f12703i = v0Var;
        this.f12701g = i8;
        this.f12702h = i9;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // x3.s0
    public final Object[] b() {
        return this.f12703i.b();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // x3.s0
    public final int f() {
        return this.f12703i.f() + this.f12701g;
    }

    @Override // java.util.List
    public final Object get(int i8) {
        p0.a(i8, this.f12702h, "index");
        return this.f12703i.get(i8 + this.f12701g);
    }

    @Override // x3.s0
    final int j() {
        return this.f12703i.f() + this.f12701g + this.f12702h;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // x3.s0
    public final boolean l() {
        return true;
    }

    @Override // x3.v0
    public final v0 n(int i8, int i9) {
        p0.c(i8, i9, this.f12702h);
        int i10 = this.f12701g;
        return this.f12703i.subList(i8 + i10, i9 + i10);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return this.f12702h;
    }

    @Override // x3.v0, java.util.List
    public final /* bridge */ /* synthetic */ List subList(int i8, int i9) {
        return subList(i8, i9);
    }
}
