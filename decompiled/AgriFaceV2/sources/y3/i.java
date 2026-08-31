package y3;

import java.io.Serializable;
import java.util.Map;
import java.util.Set;
/* loaded from: classes.dex */
public abstract class i implements Map, Serializable {

    /* renamed from: e  reason: collision with root package name */
    private transient j f13232e;

    /* renamed from: f  reason: collision with root package name */
    private transient j f13233f;

    /* renamed from: g  reason: collision with root package name */
    private transient c f13234g;

    public static i c(Object obj, Object obj2) {
        w0.a("optional-module-barcode", "com.google.android.gms.vision.barcode");
        return q.g(1, new Object[]{"optional-module-barcode", "com.google.android.gms.vision.barcode"}, null);
    }

    abstract c a();

    @Override // java.util.Map
    /* renamed from: b */
    public final c values() {
        c cVar = this.f13234g;
        if (cVar == null) {
            c a8 = a();
            this.f13234g = a8;
            return a8;
        }
        return cVar;
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

    abstract j d();

    abstract j e();

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
    public final j entrySet() {
        j jVar = this.f13232e;
        if (jVar == null) {
            j d8 = d();
            this.f13232e = d8;
            return d8;
        }
        return jVar;
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
        return r.a(entrySet());
    }

    @Override // java.util.Map
    public final boolean isEmpty() {
        return size() == 0;
    }

    @Override // java.util.Map
    public final /* bridge */ /* synthetic */ Set keySet() {
        j jVar = this.f13233f;
        if (jVar == null) {
            j e8 = e();
            this.f13233f = e8;
            return e8;
        }
        return jVar;
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
