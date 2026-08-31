package w6;

import d7.k;
import java.io.Serializable;
import s6.j;
/* loaded from: classes.dex */
final class c extends s6.b implements a, Serializable {

    /* renamed from: f  reason: collision with root package name */
    private final Enum[] f12583f;

    public c(Enum[] enumArr) {
        k.e(enumArr, "entries");
        this.f12583f = enumArr;
    }

    @Override // s6.a
    public int b() {
        return this.f12583f.length;
    }

    @Override // s6.a, java.util.Collection
    public final /* bridge */ boolean contains(Object obj) {
        if (obj instanceof Enum) {
            return f((Enum) obj);
        }
        return false;
    }

    public boolean f(Enum r32) {
        Object k8;
        k.e(r32, "element");
        k8 = j.k(this.f12583f, r32.ordinal());
        return ((Enum) k8) == r32;
    }

    @Override // s6.b, java.util.List
    public final /* bridge */ int indexOf(Object obj) {
        if (obj instanceof Enum) {
            return k((Enum) obj);
        }
        return -1;
    }

    @Override // s6.b, java.util.List
    /* renamed from: j */
    public Enum get(int i8) {
        s6.b.f11586e.a(i8, this.f12583f.length);
        return this.f12583f[i8];
    }

    public int k(Enum r32) {
        Object k8;
        k.e(r32, "element");
        int ordinal = r32.ordinal();
        k8 = j.k(this.f12583f, ordinal);
        if (((Enum) k8) == r32) {
            return ordinal;
        }
        return -1;
    }

    public int l(Enum r22) {
        k.e(r22, "element");
        return indexOf(r22);
    }

    @Override // s6.b, java.util.List
    public final /* bridge */ int lastIndexOf(Object obj) {
        if (obj instanceof Enum) {
            return l((Enum) obj);
        }
        return -1;
    }
}
