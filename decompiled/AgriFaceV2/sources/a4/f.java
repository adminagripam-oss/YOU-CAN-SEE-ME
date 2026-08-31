package a4;

import java.util.Collection;
import java.util.List;
import java.util.Map;
/* loaded from: classes.dex */
abstract class f extends q implements u0 {
    /* JADX INFO: Access modifiers changed from: protected */
    public f(Map map) {
        super(map);
    }

    /* JADX WARN: Type inference failed for: r1v1, types: [java.util.List, java.util.Collection] */
    @Override // a4.u0
    public final List a(Object obj) {
        return super.m(obj);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // a4.q
    public final Collection h(Object obj, Collection collection) {
        return n(obj, (List) collection, null);
    }
}
