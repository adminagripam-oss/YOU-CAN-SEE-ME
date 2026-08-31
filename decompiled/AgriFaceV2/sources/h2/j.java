package h2;

import java.util.List;
import s6.v;
/* loaded from: classes.dex */
public final class j {

    /* renamed from: a  reason: collision with root package name */
    private final List f7210a;

    public j(List list) {
        d7.k.e(list, "displayFeatures");
        this.f7210a = list;
    }

    public final List a() {
        return this.f7210a;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !d7.k.a(j.class, obj.getClass())) {
            return false;
        }
        return d7.k.a(this.f7210a, ((j) obj).f7210a);
    }

    public int hashCode() {
        return this.f7210a.hashCode();
    }

    public String toString() {
        String s8;
        s8 = v.s(this.f7210a, ", ", "WindowLayoutInfo{ DisplayFeatures[", "] }", 0, null, null, 56, null);
        return s8;
    }
}
