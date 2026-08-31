package com.google.android.gms.internal.mlkit_vision_face_bundled;

import java.util.AbstractMap;
import java.util.Objects;
/* loaded from: classes.dex */
final class a0 extends v {

    /* renamed from: h  reason: collision with root package name */
    final /* synthetic */ c0 f5113h;

    /* JADX INFO: Access modifiers changed from: package-private */
    public a0(c0 c0Var) {
        this.f5113h = c0Var;
    }

    @Override // java.util.List
    public final /* bridge */ /* synthetic */ Object get(int i8) {
        int i9;
        Object[] objArr;
        Object[] objArr2;
        i9 = this.f5113h.f5154i;
        l.a(i8, i9, "index");
        objArr = this.f5113h.f5153h;
        int i10 = i8 + i8;
        Object obj = objArr[i10];
        Objects.requireNonNull(obj);
        objArr2 = this.f5113h.f5153h;
        Object obj2 = objArr2[i10 + 1];
        Objects.requireNonNull(obj2);
        return new AbstractMap.SimpleImmutableEntry(obj, obj2);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        int i8;
        i8 = this.f5113h.f5154i;
        return i8;
    }
}
