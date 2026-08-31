package com.google.android.gms.internal.mlkit_vision_face_bundled;

import java.util.Objects;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class z extends v {

    /* renamed from: j  reason: collision with root package name */
    static final v f5964j = new z(new Object[0], 0);

    /* renamed from: h  reason: collision with root package name */
    final transient Object[] f5965h;

    /* renamed from: i  reason: collision with root package name */
    private final transient int f5966i;

    /* JADX INFO: Access modifiers changed from: package-private */
    public z(Object[] objArr, int i8) {
        this.f5965h = objArr;
        this.f5966i = i8;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_face_bundled.v, com.google.android.gms.internal.mlkit_vision_face_bundled.r
    final int b(Object[] objArr, int i8) {
        System.arraycopy(this.f5965h, 0, objArr, 0, this.f5966i);
        return this.f5966i;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_face_bundled.r
    final int f() {
        return this.f5966i;
    }

    @Override // java.util.List
    public final Object get(int i8) {
        l.a(i8, this.f5966i, "index");
        Object obj = this.f5965h[i8];
        Objects.requireNonNull(obj);
        return obj;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.mlkit_vision_face_bundled.r
    public final int j() {
        return 0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.mlkit_vision_face_bundled.r
    public final Object[] k() {
        return this.f5965h;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return this.f5966i;
    }
}
