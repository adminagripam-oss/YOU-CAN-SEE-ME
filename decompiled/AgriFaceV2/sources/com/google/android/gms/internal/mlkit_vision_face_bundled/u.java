package com.google.android.gms.internal.mlkit_vision_face_bundled;

import java.util.List;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class u extends v {

    /* renamed from: h  reason: collision with root package name */
    final transient int f5624h;

    /* renamed from: i  reason: collision with root package name */
    final transient int f5625i;

    /* renamed from: j  reason: collision with root package name */
    final /* synthetic */ v f5626j;

    /* JADX INFO: Access modifiers changed from: package-private */
    public u(v vVar, int i8, int i9) {
        this.f5626j = vVar;
        this.f5624h = i8;
        this.f5625i = i9;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_face_bundled.r
    final int f() {
        return this.f5626j.j() + this.f5624h + this.f5625i;
    }

    @Override // java.util.List
    public final Object get(int i8) {
        l.a(i8, this.f5625i, "index");
        return this.f5626j.get(i8 + this.f5624h);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.mlkit_vision_face_bundled.r
    public final int j() {
        return this.f5626j.j() + this.f5624h;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.mlkit_vision_face_bundled.r
    public final Object[] k() {
        return this.f5626j.k();
    }

    @Override // com.google.android.gms.internal.mlkit_vision_face_bundled.v
    public final v l(int i8, int i9) {
        l.c(i8, i9, this.f5625i);
        int i10 = this.f5624h;
        return this.f5626j.subList(i8 + i10, i9 + i10);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return this.f5625i;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_face_bundled.v, java.util.List
    public final /* bridge */ /* synthetic */ List subList(int i8, int i9) {
        return subList(i8, i9);
    }
}
