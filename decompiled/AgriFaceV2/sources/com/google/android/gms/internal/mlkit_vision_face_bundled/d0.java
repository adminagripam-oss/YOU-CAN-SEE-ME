package com.google.android.gms.internal.mlkit_vision_face_bundled;

import java.util.Iterator;
/* loaded from: classes.dex */
final class d0 extends y {

    /* renamed from: g  reason: collision with root package name */
    private final transient x f5190g;

    /* renamed from: h  reason: collision with root package name */
    private final transient v f5191h;

    /* JADX INFO: Access modifiers changed from: package-private */
    public d0(x xVar, v vVar) {
        this.f5190g = xVar;
        this.f5191h = vVar;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.mlkit_vision_face_bundled.r
    public final int b(Object[] objArr, int i8) {
        return this.f5191h.b(objArr, 0);
    }

    @Override // com.google.android.gms.internal.mlkit_vision_face_bundled.r, java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final boolean contains(Object obj) {
        return this.f5190g.get(obj) != null;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
    public final /* synthetic */ Iterator iterator() {
        return this.f5191h.listIterator(0);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final int size() {
        return 1;
    }
}
