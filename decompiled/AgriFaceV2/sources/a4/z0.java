package a4;

import java.util.AbstractMap;
import java.util.Collection;
import java.util.Set;
/* loaded from: classes.dex */
abstract class z0 extends AbstractMap {

    /* renamed from: e  reason: collision with root package name */
    private transient Set f1324e;

    /* renamed from: f  reason: collision with root package name */
    private transient Collection f1325f;

    abstract Set a();

    @Override // java.util.AbstractMap, java.util.Map
    public final Set entrySet() {
        Set set = this.f1324e;
        if (set == null) {
            Set a8 = a();
            this.f1324e = a8;
            return a8;
        }
        return set;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final Collection values() {
        Collection collection = this.f1325f;
        if (collection == null) {
            y0 y0Var = new y0(this);
            this.f1325f = y0Var;
            return y0Var;
        }
        return collection;
    }
}
