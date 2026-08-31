package com.google.android.gms.internal.mlkit_vision_face_bundled;
/* loaded from: classes.dex */
final class hj extends lj {

    /* renamed from: h  reason: collision with root package name */
    private final int f5367h;

    /* JADX INFO: Access modifiers changed from: package-private */
    public hj(byte[] bArr, int i8, int i9) {
        super(bArr);
        mj.n(0, i9, bArr.length);
        this.f5367h = i9;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_face_bundled.lj, com.google.android.gms.internal.mlkit_vision_face_bundled.mj
    public final byte b(int i8) {
        int i9 = this.f5367h;
        if (((i9 - (i8 + 1)) | i8) < 0) {
            if (i8 < 0) {
                throw new ArrayIndexOutOfBoundsException("Index < 0: " + i8);
            }
            throw new ArrayIndexOutOfBoundsException("Index > length: " + i8 + ", " + i9);
        }
        return this.f5426g[i8];
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.mlkit_vision_face_bundled.lj, com.google.android.gms.internal.mlkit_vision_face_bundled.mj
    public final byte f(int i8) {
        return this.f5426g[i8];
    }

    @Override // com.google.android.gms.internal.mlkit_vision_face_bundled.lj, com.google.android.gms.internal.mlkit_vision_face_bundled.mj
    public final int j() {
        return this.f5367h;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_face_bundled.lj
    protected final int q() {
        return 0;
    }
}
