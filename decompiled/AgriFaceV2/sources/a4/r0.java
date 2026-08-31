package a4;

import java.io.Serializable;
import java.util.Map;
import java.util.Set;
/* loaded from: classes.dex */
public abstract class r0 implements Map, Serializable {

    /* renamed from: e  reason: collision with root package name */
    private transient s0 f1000e;

    /* renamed from: f  reason: collision with root package name */
    private transient s0 f1001f;

    /* renamed from: g  reason: collision with root package name */
    private transient k0 f1002g;

    public static r0 c(Object obj, Object obj2) {
        v.b("optional-module-barcode", "com.google.android.gms.vision.barcode");
        return j1.g(1, new Object[]{"optional-module-barcode", "com.google.android.gms.vision.barcode"}, null);
    }

    abstract k0 a();

    @Override // java.util.Map
    /* renamed from: b */
    public final k0 values() {
        k0 k0Var = this.f1002g;
        if (k0Var == null) {
            k0 a8 = a();
            this.f1002g = a8;
            return a8;
        }
        return k0Var;
    }

    @Override // java.util.Map
    public final void clear() {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.Map
    public final boolean containsKey(Object obj) {
        return get(obj) != null;
    }

    @Override // java.util.Map
    public final boolean containsValue(Object obj) {
        return values().contains(obj);
    }

    abstract s0 d();

    abstract s0 e();

    @Override // java.util.Map
    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof Map) {
            return entrySet().equals(((Map) obj).entrySet());
        }
        return false;
    }

    @Override // java.util.Map
    /* renamed from: f */
    public final s0 entrySet() {
        s0 s0Var = this.f1000e;
        if (s0Var == null) {
            s0 d8 = d();
            this.f1000e = d8;
            return d8;
        }
        return s0Var;
    }

    @Override // java.util.Map
    public abstract Object get(Object obj);

    @Override // java.util.Map
    public final Object getOrDefault(Object obj, Object obj2) {
        Object obj3 = get(obj);
        return obj3 != null ? obj3 : obj2;
    }

    @Override // java.util.Map
    public final int hashCode() {
        return l1.a(entrySet());
    }

    @Override // java.util.Map
    public final boolean isEmpty() {
        return size() == 0;
    }

    @Override // java.util.Map
    public final /* bridge */ /* synthetic */ Set keySet() {
        s0 s0Var = this.f1001f;
        if (s0Var == null) {
            s0 e8 = e();
            this.f1001f = e8;
            return e8;
        }
        return s0Var;
    }

    @Override // java.util.Map
    public final Object put(Object obj, Object obj2) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.Map
    public final void putAll(Map map) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.Map
    public final Object remove(Object obj) {
        throw new UnsupportedOperationException();
    }

    public final String toString() {
        int size = size();
        v.a(size, "size");
        StringBuilder sb = new StringBuilder((int) Math.min(size * 8, 1073741824L));
        sb.append('{');
        boolean z7 = true;
        for (Map.Entry entry : entrySet()) {
            if (!z7) {
                sb.append(", ");
            }
            sb.append(entry.getKey());
            sb.append('=');
            sb.append(entry.getValue());
            z7 = false;
        }
        sb.append('}');
        return sb.toString();
    }
}
