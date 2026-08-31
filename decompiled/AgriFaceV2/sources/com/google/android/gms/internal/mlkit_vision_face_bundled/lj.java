package com.google.android.gms.internal.mlkit_vision_face_bundled;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class lj extends kj {

    /* renamed from: g  reason: collision with root package name */
    protected final byte[] f5426g;

    /* JADX INFO: Access modifiers changed from: package-private */
    public lj(byte[] bArr) {
        super(null);
        bArr.getClass();
        this.f5426g = bArr;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_face_bundled.mj
    public byte b(int i8) {
        return this.f5426g[i8];
    }

    @Override // com.google.android.gms.internal.mlkit_vision_face_bundled.mj
    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if ((obj instanceof mj) && j() == ((mj) obj).j()) {
            if (j() == 0) {
                return true;
            }
            if (obj instanceof lj) {
                lj ljVar = (lj) obj;
                int o8 = o();
                int o9 = ljVar.o();
                if (o8 == 0 || o9 == 0 || o8 == o9) {
                    int j8 = j();
                    if (j8 > ljVar.j()) {
                        throw new IllegalArgumentException("Length too large: " + j8 + j());
                    } else if (j8 > ljVar.j()) {
                        throw new IllegalArgumentException("Ran off end of other: 0, " + j8 + ", " + ljVar.j());
                    } else {
                        byte[] bArr = this.f5426g;
                        byte[] bArr2 = ljVar.f5426g;
                        ljVar.q();
                        int i8 = 0;
                        int i9 = 0;
                        while (i8 < j8) {
                            if (bArr[i8] != bArr2[i9]) {
                                return false;
                            }
                            i8++;
                            i9++;
                        }
                        return true;
                    }
                }
                return false;
            }
            return obj.equals(this);
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.mlkit_vision_face_bundled.mj
    public byte f(int i8) {
        return this.f5426g[i8];
    }

    @Override // com.google.android.gms.internal.mlkit_vision_face_bundled.mj
    public int j() {
        return this.f5426g.length;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_face_bundled.mj
    protected final int k(int i8, int i9, int i10) {
        return uk.b(i8, this.f5426g, 0, i10);
    }

    @Override // com.google.android.gms.internal.mlkit_vision_face_bundled.mj
    public final mj l(int i8, int i9) {
        int n8 = mj.n(0, i9, j());
        return n8 == 0 ? mj.f5439f : new hj(this.f5426g, 0, n8);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.mlkit_vision_face_bundled.mj
    public final void m(ej ejVar) {
        ((rj) ejVar).C(this.f5426g, 0, j());
    }

    protected int q() {
        return 0;
    }
}
