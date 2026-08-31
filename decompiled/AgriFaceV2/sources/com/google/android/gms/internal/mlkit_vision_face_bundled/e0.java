package com.google.android.gms.internal.mlkit_vision_face_bundled;

import java.util.Objects;
/* loaded from: classes.dex */
final class e0 extends v {

    /* renamed from: h  reason: collision with root package name */
    private final transient Object[] f5203h;

    /* renamed from: i  reason: collision with root package name */
    private final transient int f5204i;

    /* renamed from: j  reason: collision with root package name */
    private final transient int f5205j = 1;

    /* JADX INFO: Access modifiers changed from: package-private */
    public e0(Object[] objArr, int i8, int i9) {
        this.f5203h = objArr;
        this.f5204i = i8;
    }

    @Override // java.util.List
    public final Object get(int i8) {
        l.a(i8, this.f5205j, "index");
        Object obj = this.f5203h[i8 + i8 + this.f5204i];
        Objects.requireNonNull(obj);
        return obj;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return this.f5205j;
    }
}
