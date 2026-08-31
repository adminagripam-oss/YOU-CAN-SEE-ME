package v1;

import d7.g;
import d7.k;
import v1.a;
/* loaded from: classes.dex */
public final class b extends a {
    public b() {
        this(null, 1, null);
    }

    @Override // v1.a
    public Object a(a.b bVar) {
        k.e(bVar, "key");
        return b().get(bVar);
    }

    public final void c(a.b bVar, Object obj) {
        k.e(bVar, "key");
        b().put(bVar, obj);
    }

    public b(a aVar) {
        k.e(aVar, "initialExtras");
        b().putAll(aVar.b());
    }

    public /* synthetic */ b(a aVar, int i8, g gVar) {
        this((i8 & 1) != 0 ? a.C0159a.f12322b : aVar);
    }
}
