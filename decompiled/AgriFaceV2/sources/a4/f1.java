package a4;

import java.util.AbstractMap;
/* loaded from: classes.dex */
final class f1 extends p0 {

    /* renamed from: g  reason: collision with root package name */
    final /* synthetic */ g1 f547g;

    /* JADX INFO: Access modifiers changed from: package-private */
    public f1(g1 g1Var) {
        this.f547g = g1Var;
    }

    @Override // java.util.List
    public final /* bridge */ /* synthetic */ Object get(int i8) {
        int i9;
        Object[] objArr;
        Object[] objArr2;
        i9 = this.f547g.f578i;
        c.a(i8, i9, "index");
        g1 g1Var = this.f547g;
        int i10 = i8 + i8;
        objArr = g1Var.f577h;
        Object obj = objArr[i10];
        obj.getClass();
        objArr2 = g1Var.f577h;
        Object obj2 = objArr2[i10 + 1];
        obj2.getClass();
        return new AbstractMap.SimpleImmutableEntry(obj, obj2);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        int i8;
        i8 = this.f547g.f578i;
        return i8;
    }
}
