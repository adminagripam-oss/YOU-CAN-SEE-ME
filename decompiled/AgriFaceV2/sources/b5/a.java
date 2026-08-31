package b5;

import java.util.Set;
/* loaded from: classes.dex */
abstract class a implements d {
    @Override // b5.d
    public Object a(Class cls) {
        h5.b c8 = c(cls);
        if (c8 == null) {
            return null;
        }
        return c8.get();
    }

    @Override // b5.d
    public Set b(Class cls) {
        return (Set) d(cls).get();
    }
}
