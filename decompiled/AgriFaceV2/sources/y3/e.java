package y3;

import java.util.List;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class e extends f {

    /* renamed from: h  reason: collision with root package name */
    final transient int f13218h;

    /* renamed from: i  reason: collision with root package name */
    final transient int f13219i;

    /* renamed from: j  reason: collision with root package name */
    final /* synthetic */ f f13220j;

    /* JADX INFO: Access modifiers changed from: package-private */
    public e(f fVar, int i8, int i9) {
        this.f13220j = fVar;
        this.f13218h = i8;
        this.f13219i = i9;
    }

    @Override // y3.c
    final int f() {
        return this.f13220j.j() + this.f13218h + this.f13219i;
    }

    @Override // java.util.List
    public final Object get(int i8) {
        t0.a(i8, this.f13219i, "index");
        return this.f13220j.get(i8 + this.f13218h);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // y3.c
    public final int j() {
        return this.f13220j.j() + this.f13218h;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // y3.c
    public final Object[] k() {
        return this.f13220j.k();
    }

    @Override // y3.f
    public final f l(int i8, int i9) {
        t0.c(i8, i9, this.f13219i);
        int i10 = this.f13218h;
        return this.f13220j.subList(i8 + i10, i9 + i10);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return this.f13219i;
    }

    @Override // y3.f, java.util.List
    public final /* bridge */ /* synthetic */ List subList(int i8, int i9) {
        return subList(i8, i9);
    }
}
