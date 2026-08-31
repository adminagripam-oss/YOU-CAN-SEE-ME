package z3;

import java.io.Serializable;
import java.util.Map;
import java.util.Set;
/* loaded from: classes.dex */
public abstract class oc implements Map, Serializable {

    /* renamed from: e  reason: collision with root package name */
    private transient pc f13973e;

    /* renamed from: f  reason: collision with root package name */
    private transient pc f13974f;

    /* renamed from: g  reason: collision with root package name */
    private transient la f13975g;

    public static oc c(Object obj, Object obj2) {
        i7.a("optional-module-barcode", "com.google.android.gms.vision.barcode");
        return wc.g(1, new Object[]{"optional-module-barcode", "com.google.android.gms.vision.barcode"}, null);
    }

    abstract la a();

    @Override // java.util.Map
    /* renamed from: b */
    public final la values() {
        la laVar = this.f13975g;
        if (laVar == null) {
            la a8 = a();
            this.f13975g = a8;
            return a8;
        }
        return laVar;
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

    abstract pc d();

    abstract pc e();

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
    public final pc entrySet() {
        pc pcVar = this.f13973e;
        if (pcVar == null) {
            pc d8 = d();
            this.f13973e = d8;
            return d8;
        }
        return pcVar;
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
        return b.a(entrySet());
    }

    @Override // java.util.Map
    public final boolean isEmpty() {
        return size() == 0;
    }

    @Override // java.util.Map
    public final /* bridge */ /* synthetic */ Set keySet() {
        pc pcVar = this.f13974f;
        if (pcVar == null) {
            pc e8 = e();
            this.f13974f = e8;
            return e8;
        }
        return pcVar;
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
        if (size < 0) {
            throw new IllegalArgumentException("size cannot be negative but was: " + size);
        }
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
