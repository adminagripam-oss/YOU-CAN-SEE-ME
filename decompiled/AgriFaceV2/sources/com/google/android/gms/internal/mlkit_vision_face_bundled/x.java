package com.google.android.gms.internal.mlkit_vision_face_bundled;

import java.io.Serializable;
import java.util.Map;
import java.util.Set;
/* loaded from: classes.dex */
public abstract class x implements Map, Serializable {

    /* renamed from: e  reason: collision with root package name */
    private transient y f5903e;

    /* renamed from: f  reason: collision with root package name */
    private transient y f5904f;

    /* renamed from: g  reason: collision with root package name */
    private transient r f5905g;

    public static x c(Object obj, Object obj2) {
        o.a("optional-module-barcode", "com.google.android.gms.vision.barcode");
        return f0.g(1, new Object[]{"optional-module-barcode", "com.google.android.gms.vision.barcode"}, null);
    }

    abstract r a();

    @Override // java.util.Map
    /* renamed from: b */
    public final r values() {
        r rVar = this.f5905g;
        if (rVar == null) {
            r a8 = a();
            this.f5905g = a8;
            return a8;
        }
        return rVar;
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

    abstract y d();

    abstract y e();

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
    public final y entrySet() {
        y yVar = this.f5903e;
        if (yVar == null) {
            y d8 = d();
            this.f5903e = d8;
            return d8;
        }
        return yVar;
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
        return g0.a(entrySet());
    }

    @Override // java.util.Map
    public final boolean isEmpty() {
        return false;
    }

    @Override // java.util.Map
    public final /* bridge */ /* synthetic */ Set keySet() {
        y yVar = this.f5904f;
        if (yVar == null) {
            y e8 = e();
            this.f5904f = e8;
            return e8;
        }
        return yVar;
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
