package z3;

import java.util.Set;
/* loaded from: classes.dex */
public abstract class pc extends la implements Set {

    /* renamed from: f  reason: collision with root package name */
    private transient mc f13997f;

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
        return b.a(this);
    }

    public final mc l() {
        mc mcVar = this.f13997f;
        if (mcVar == null) {
            mc m8 = m();
            this.f13997f = m8;
            return m8;
        }
        return mcVar;
    }

    mc m() {
        return mc.m(toArray());
    }
}
