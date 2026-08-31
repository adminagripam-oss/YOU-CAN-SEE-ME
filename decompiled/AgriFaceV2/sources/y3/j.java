package y3;

import java.util.Set;
/* loaded from: classes.dex */
public abstract class j extends c implements Set {

    /* renamed from: f  reason: collision with root package name */
    private transient f f13244f;

    @Override // java.util.Collection, java.util.Set
    public final boolean equals(Object obj) {
        if (obj == this || obj == this) {
            return true;
        }
        if (obj instanceof Set) {
            Set set = (Set) obj;
            try {
                if (size() == set.size()) {
                    if (containsAll(set)) {
                        return true;
                    }
                }
            } catch (ClassCastException | NullPointerException unused) {
            }
        }
        return false;
    }

    @Override // java.util.Collection, java.util.Set
    public final int hashCode() {
        return r.a(this);
    }

    public final f l() {
        f fVar = this.f13244f;
        if (fVar == null) {
            f m8 = m();
            this.f13244f = m8;
            return m8;
        }
        return fVar;
    }

    f m() {
        Object[] array = toArray();
        int i8 = f.f13222g;
        return f.m(array, array.length);
    }
}
