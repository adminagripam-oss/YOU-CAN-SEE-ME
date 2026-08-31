package a4;

import java.util.List;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class o0 extends p0 {

    /* renamed from: g  reason: collision with root package name */
    final transient int f878g;

    /* renamed from: h  reason: collision with root package name */
    final transient int f879h;

    /* renamed from: i  reason: collision with root package name */
    final /* synthetic */ p0 f880i;

    /* JADX INFO: Access modifiers changed from: package-private */
    public o0(p0 p0Var, int i8, int i9) {
        this.f880i = p0Var;
        this.f878g = i8;
        this.f879h = i9;
    }

    @Override // a4.k0
    final int f() {
        return this.f880i.j() + this.f878g + this.f879h;
    }

    @Override // java.util.List
    public final Object get(int i8) {
        c.a(i8, this.f879h, "index");
        return this.f880i.get(i8 + this.f878g);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // a4.k0
    public final int j() {
        return this.f880i.j() + this.f878g;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // a4.k0
    public final Object[] k() {
        return this.f880i.k();
    }

    @Override // a4.p0
    public final p0 l(int i8, int i9) {
        c.c(i8, i9, this.f879h);
        p0 p0Var = this.f880i;
        int i10 = this.f878g;
        return p0Var.subList(i8 + i10, i9 + i10);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return this.f879h;
    }

    @Override // a4.p0, java.util.List
    public final /* bridge */ /* synthetic */ List subList(int i8, int i9) {
        return subList(i8, i9);
    }
}
