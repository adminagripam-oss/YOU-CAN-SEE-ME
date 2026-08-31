package com.google.android.gms.internal.mlkit_vision_face_bundled;

import java.util.Set;
/* loaded from: classes.dex */
public abstract class y extends r implements Set {

    /* renamed from: f  reason: collision with root package name */
    private transient v f5942f;

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
        return g0.a(this);
    }

    public final v l() {
        v vVar = this.f5942f;
        if (vVar == null) {
            v m8 = m();
            this.f5942f = m8;
            return m8;
        }
        return vVar;
    }

    v m() {
        Object[] array = toArray();
        int i8 = v.f5680g;
        return v.m(array, array.length);
    }
}
