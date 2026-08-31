package y3;

import java.util.AbstractMap;
import java.util.Objects;
/* loaded from: classes.dex */
final class m extends f {

    /* renamed from: h  reason: collision with root package name */
    final /* synthetic */ n f13250h;

    /* JADX INFO: Access modifiers changed from: package-private */
    public m(n nVar) {
        this.f13250h = nVar;
    }

    @Override // java.util.List
    public final /* bridge */ /* synthetic */ Object get(int i8) {
        int i9;
        Object[] objArr;
        Object[] objArr2;
        i9 = this.f13250h.f13253i;
        t0.a(i8, i9, "index");
        objArr = this.f13250h.f13252h;
        int i10 = i8 + i8;
        Object obj = objArr[i10];
        Objects.requireNonNull(obj);
        objArr2 = this.f13250h.f13252h;
        Object obj2 = objArr2[i10 + 1];
        Objects.requireNonNull(obj2);
        return new AbstractMap.SimpleImmutableEntry(obj, obj2);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        int i8;
        i8 = this.f13250h.f13253i;
        return i8;
    }
}
