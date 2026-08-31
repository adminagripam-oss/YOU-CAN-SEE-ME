package z3;

import java.util.AbstractMap;
/* loaded from: classes.dex */
final class sc extends mc {

    /* renamed from: g  reason: collision with root package name */
    final /* synthetic */ tc f14079g;

    /* JADX INFO: Access modifiers changed from: package-private */
    public sc(tc tcVar) {
        this.f14079g = tcVar;
    }

    @Override // java.util.List
    public final /* bridge */ /* synthetic */ Object get(int i8) {
        int i9;
        Object[] objArr;
        Object[] objArr2;
        i9 = this.f14079g.f14151i;
        f4.a(i8, i9, "index");
        tc tcVar = this.f14079g;
        objArr = tcVar.f14150h;
        int i10 = i8 + i8;
        Object obj = objArr[i10];
        obj.getClass();
        objArr2 = tcVar.f14150h;
        Object obj2 = objArr2[i10 + 1];
        obj2.getClass();
        return new AbstractMap.SimpleImmutableEntry(obj, obj2);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        int i8;
        i8 = this.f14079g.f14151i;
        return i8;
    }
}
