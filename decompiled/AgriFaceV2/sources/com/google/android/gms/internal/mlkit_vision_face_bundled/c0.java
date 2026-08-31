package com.google.android.gms.internal.mlkit_vision_face_bundled;

import java.util.Iterator;
import java.util.Map;
/* loaded from: classes.dex */
final class c0 extends y {

    /* renamed from: g  reason: collision with root package name */
    private final transient x f5152g;

    /* renamed from: h  reason: collision with root package name */
    private final transient Object[] f5153h;

    /* renamed from: i  reason: collision with root package name */
    private final transient int f5154i = 1;

    /* JADX INFO: Access modifiers changed from: package-private */
    public c0(x xVar, Object[] objArr, int i8, int i9) {
        this.f5152g = xVar;
        this.f5153h = objArr;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.mlkit_vision_face_bundled.r
    public final int b(Object[] objArr, int i8) {
        return l().b(objArr, 0);
    }

    @Override // com.google.android.gms.internal.mlkit_vision_face_bundled.r, java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final boolean contains(Object obj) {
        if (obj instanceof Map.Entry) {
            Map.Entry entry = (Map.Entry) obj;
            Object key = entry.getKey();
            Object value = entry.getValue();
            if (value != null && value.equals(this.f5152g.get(key))) {
                return true;
            }
        }
        return false;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
    public final /* synthetic */ Iterator iterator() {
        return l().listIterator(0);
    }

    @Override // com.google.android.gms.internal.mlkit_vision_face_bundled.y
    final v m() {
        return new a0(this);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final int size() {
        return this.f5154i;
    }
}
