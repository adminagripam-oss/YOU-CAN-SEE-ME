package com.google.android.gms.internal.mlkit_vision_face_bundled;

import java.util.NoSuchElementException;
/* loaded from: classes.dex */
final class fj extends gj {

    /* renamed from: e  reason: collision with root package name */
    private int f5229e = 0;

    /* renamed from: f  reason: collision with root package name */
    private final int f5230f;

    /* renamed from: g  reason: collision with root package name */
    final /* synthetic */ mj f5231g;

    /* JADX INFO: Access modifiers changed from: package-private */
    public fj(mj mjVar) {
        this.f5231g = mjVar;
        this.f5230f = mjVar.j();
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        return this.f5229e < this.f5230f;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_face_bundled.ij
    public final byte zza() {
        int i8 = this.f5229e;
        if (i8 < this.f5230f) {
            this.f5229e = i8 + 1;
            return this.f5231g.f(i8);
        }
        throw new NoSuchElementException();
    }
}
