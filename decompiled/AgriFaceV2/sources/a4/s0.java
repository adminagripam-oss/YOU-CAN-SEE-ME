package a4;

import java.util.Set;
/* loaded from: classes.dex */
public abstract class s0 extends k0 implements Set {

    /* renamed from: f  reason: collision with root package name */
    private transient p0 f1040f;

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
        return l1.a(this);
    }

    public final p0 l() {
        p0 p0Var = this.f1040f;
        if (p0Var == null) {
            p0 m8 = m();
            this.f1040f = m8;
            return m8;
        }
        return p0Var;
    }

    p0 m() {
        return p0.m(toArray());
    }
}
