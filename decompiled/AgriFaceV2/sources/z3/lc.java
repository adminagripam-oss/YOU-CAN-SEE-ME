package z3;

import java.util.List;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class lc extends mc {

    /* renamed from: g  reason: collision with root package name */
    final transient int f13873g;

    /* renamed from: h  reason: collision with root package name */
    final transient int f13874h;

    /* renamed from: i  reason: collision with root package name */
    final /* synthetic */ mc f13875i;

    /* JADX INFO: Access modifiers changed from: package-private */
    public lc(mc mcVar, int i8, int i9) {
        this.f13875i = mcVar;
        this.f13873g = i8;
        this.f13874h = i9;
    }

    @Override // z3.la
    final int f() {
        return this.f13875i.j() + this.f13873g + this.f13874h;
    }

    @Override // java.util.List
    public final Object get(int i8) {
        f4.a(i8, this.f13874h, "index");
        return this.f13875i.get(i8 + this.f13873g);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // z3.la
    public final int j() {
        return this.f13875i.j() + this.f13873g;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // z3.la
    public final Object[] k() {
        return this.f13875i.k();
    }

    @Override // z3.mc
    public final mc l(int i8, int i9) {
        f4.c(i8, i9, this.f13874h);
        mc mcVar = this.f13875i;
        int i10 = this.f13873g;
        return mcVar.subList(i8 + i10, i9 + i10);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return this.f13874h;
    }

    @Override // z3.mc, java.util.List
    public final /* bridge */ /* synthetic */ List subList(int i8, int i9) {
        return subList(i8, i9);
    }
}
