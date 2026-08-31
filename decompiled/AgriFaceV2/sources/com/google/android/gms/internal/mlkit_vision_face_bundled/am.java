package com.google.android.gms.internal.mlkit_vision_face_bundled;

import java.util.Map;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class am implements Map.Entry, Comparable {

    /* renamed from: e  reason: collision with root package name */
    private final Comparable f5134e;

    /* renamed from: f  reason: collision with root package name */
    private Object f5135f;

    /* renamed from: g  reason: collision with root package name */
    final /* synthetic */ gm f5136g;

    /* JADX INFO: Access modifiers changed from: package-private */
    public am(gm gmVar, Comparable comparable, Object obj) {
        this.f5136g = gmVar;
        this.f5134e = comparable;
        this.f5135f = obj;
    }

    private static final boolean e(Object obj, Object obj2) {
        return obj == null ? obj2 == null : obj.equals(obj2);
    }

    @Override // java.lang.Comparable
    public final /* bridge */ /* synthetic */ int compareTo(Object obj) {
        return this.f5134e.compareTo(((am) obj).f5134e);
    }

    public final Comparable d() {
        return this.f5134e;
    }

    @Override // java.util.Map.Entry
    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof Map.Entry) {
            Map.Entry entry = (Map.Entry) obj;
            return e(this.f5134e, entry.getKey()) && e(this.f5135f, entry.getValue());
        }
        return false;
    }

    @Override // java.util.Map.Entry
    public final /* synthetic */ Object getKey() {
        return this.f5134e;
    }

    @Override // java.util.Map.Entry
    public final Object getValue() {
        return this.f5135f;
    }

    @Override // java.util.Map.Entry
    public final int hashCode() {
        Comparable comparable = this.f5134e;
        int hashCode = comparable == null ? 0 : comparable.hashCode();
        Object obj = this.f5135f;
        return hashCode ^ (obj != null ? obj.hashCode() : 0);
    }

    @Override // java.util.Map.Entry
    public final Object setValue(Object obj) {
        this.f5136g.o();
        Object obj2 = this.f5135f;
        this.f5135f = obj;
        return obj2;
    }

    public final String toString() {
        String valueOf = String.valueOf(this.f5134e);
        String valueOf2 = String.valueOf(this.f5135f);
        return valueOf + "=" + valueOf2;
    }
}
