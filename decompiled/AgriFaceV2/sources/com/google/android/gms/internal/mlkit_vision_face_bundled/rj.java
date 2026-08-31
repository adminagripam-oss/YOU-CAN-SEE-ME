package com.google.android.gms.internal.mlkit_vision_face_bundled;
/* loaded from: classes.dex */
final class rj extends vj {

    /* renamed from: d  reason: collision with root package name */
    private final byte[] f5574d;

    /* renamed from: e  reason: collision with root package name */
    private final int f5575e;

    /* renamed from: f  reason: collision with root package name */
    private int f5576f;

    /* JADX INFO: Access modifiers changed from: package-private */
    public rj(byte[] bArr, int i8, int i9) {
        super(null);
        int length = bArr.length;
        if (((length - i9) | i9) < 0) {
            throw new IllegalArgumentException(String.format("Array range is invalid. Buffer.length=%d, offset=%d, length=%d", Integer.valueOf(length), 0, Integer.valueOf(i9)));
        }
        this.f5574d = bArr;
        this.f5576f = 0;
        this.f5575e = i9;
    }

    public final void C(byte[] bArr, int i8, int i9) {
        try {
            System.arraycopy(bArr, 0, this.f5574d, this.f5576f, i9);
            this.f5576f += i9;
        } catch (IndexOutOfBoundsException e8) {
            throw new tj(String.format("Pos: %d, limit: %d, len: %d", Integer.valueOf(this.f5576f), Integer.valueOf(this.f5575e), Integer.valueOf(i9)), e8);
        }
    }

    public final void D(String str) {
        int i8 = this.f5576f;
        try {
            int B = vj.B(str.length() * 3);
            int B2 = vj.B(str.length());
            if (B2 != B) {
                v(um.c(str));
                byte[] bArr = this.f5574d;
                int i9 = this.f5576f;
                this.f5576f = um.b(str, bArr, i9, this.f5575e - i9);
                return;
            }
            int i10 = i8 + B2;
            this.f5576f = i10;
            int b8 = um.b(str, this.f5574d, i10, this.f5575e - i10);
            this.f5576f = i8;
            v((b8 - i8) - B2);
            this.f5576f = b8;
        } catch (tm e8) {
            this.f5576f = i8;
            c(str, e8);
        } catch (IndexOutOfBoundsException e9) {
            throw new tj(e9);
        }
    }

    @Override // com.google.android.gms.internal.mlkit_vision_face_bundled.vj
    public final int e() {
        return this.f5575e - this.f5576f;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_face_bundled.vj
    public final void f(byte b8) {
        try {
            byte[] bArr = this.f5574d;
            int i8 = this.f5576f;
            this.f5576f = i8 + 1;
            bArr[i8] = b8;
        } catch (IndexOutOfBoundsException e8) {
            throw new tj(String.format("Pos: %d, limit: %d, len: %d", Integer.valueOf(this.f5576f), Integer.valueOf(this.f5575e), 1), e8);
        }
    }

    @Override // com.google.android.gms.internal.mlkit_vision_face_bundled.vj
    public final void g(int i8, boolean z7) {
        v(i8 << 3);
        f(z7 ? (byte) 1 : (byte) 0);
    }

    @Override // com.google.android.gms.internal.mlkit_vision_face_bundled.vj
    public final void h(int i8, mj mjVar) {
        v((i8 << 3) | 2);
        v(mjVar.j());
        mjVar.m(this);
    }

    @Override // com.google.android.gms.internal.mlkit_vision_face_bundled.vj
    public final void i(int i8, int i9) {
        v((i8 << 3) | 5);
        j(i9);
    }

    @Override // com.google.android.gms.internal.mlkit_vision_face_bundled.vj
    public final void j(int i8) {
        try {
            byte[] bArr = this.f5574d;
            int i9 = this.f5576f;
            int i10 = i9 + 1;
            bArr[i9] = (byte) (i8 & 255);
            int i11 = i10 + 1;
            bArr[i10] = (byte) ((i8 >> 8) & 255);
            int i12 = i11 + 1;
            bArr[i11] = (byte) ((i8 >> 16) & 255);
            this.f5576f = i12 + 1;
            bArr[i12] = (byte) ((i8 >> 24) & 255);
        } catch (IndexOutOfBoundsException e8) {
            throw new tj(String.format("Pos: %d, limit: %d, len: %d", Integer.valueOf(this.f5576f), Integer.valueOf(this.f5575e), 1), e8);
        }
    }

    @Override // com.google.android.gms.internal.mlkit_vision_face_bundled.vj
    public final void k(int i8, long j8) {
        v((i8 << 3) | 1);
        l(j8);
    }

    @Override // com.google.android.gms.internal.mlkit_vision_face_bundled.vj
    public final void l(long j8) {
        try {
            byte[] bArr = this.f5574d;
            int i8 = this.f5576f;
            int i9 = i8 + 1;
            bArr[i8] = (byte) (((int) j8) & 255);
            int i10 = i9 + 1;
            bArr[i9] = (byte) (((int) (j8 >> 8)) & 255);
            int i11 = i10 + 1;
            bArr[i10] = (byte) (((int) (j8 >> 16)) & 255);
            int i12 = i11 + 1;
            bArr[i11] = (byte) (((int) (j8 >> 24)) & 255);
            int i13 = i12 + 1;
            bArr[i12] = (byte) (((int) (j8 >> 32)) & 255);
            int i14 = i13 + 1;
            bArr[i13] = (byte) (((int) (j8 >> 40)) & 255);
            int i15 = i14 + 1;
            bArr[i14] = (byte) (((int) (j8 >> 48)) & 255);
            this.f5576f = i15 + 1;
            bArr[i15] = (byte) (((int) (j8 >> 56)) & 255);
        } catch (IndexOutOfBoundsException e8) {
            throw new tj(String.format("Pos: %d, limit: %d, len: %d", Integer.valueOf(this.f5576f), Integer.valueOf(this.f5575e), 1), e8);
        }
    }

    @Override // com.google.android.gms.internal.mlkit_vision_face_bundled.vj
    public final void m(int i8, int i9) {
        v(i8 << 3);
        n(i9);
    }

    @Override // com.google.android.gms.internal.mlkit_vision_face_bundled.vj
    public final void n(int i8) {
        if (i8 >= 0) {
            v(i8);
        } else {
            x(i8);
        }
    }

    @Override // com.google.android.gms.internal.mlkit_vision_face_bundled.vj
    public final void o(byte[] bArr, int i8, int i9) {
        C(bArr, 0, i9);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.mlkit_vision_face_bundled.vj
    public final void p(int i8, ll llVar, wl wlVar) {
        v((i8 << 3) | 2);
        v(((yi) llVar).f(wlVar));
        wlVar.d(llVar, this.f5848a);
    }

    @Override // com.google.android.gms.internal.mlkit_vision_face_bundled.vj
    public final void q(int i8, ll llVar) {
        v(11);
        u(2, i8);
        v(26);
        v(llVar.a());
        llVar.e(this);
        v(12);
    }

    @Override // com.google.android.gms.internal.mlkit_vision_face_bundled.vj
    public final void r(int i8, mj mjVar) {
        v(11);
        u(2, i8);
        h(3, mjVar);
        v(12);
    }

    @Override // com.google.android.gms.internal.mlkit_vision_face_bundled.vj
    public final void s(int i8, String str) {
        v((i8 << 3) | 2);
        D(str);
    }

    @Override // com.google.android.gms.internal.mlkit_vision_face_bundled.vj
    public final void t(int i8, int i9) {
        v((i8 << 3) | i9);
    }

    @Override // com.google.android.gms.internal.mlkit_vision_face_bundled.vj
    public final void u(int i8, int i9) {
        v(i8 << 3);
        v(i9);
    }

    @Override // com.google.android.gms.internal.mlkit_vision_face_bundled.vj
    public final void v(int i8) {
        while ((i8 & (-128)) != 0) {
            try {
                byte[] bArr = this.f5574d;
                int i9 = this.f5576f;
                this.f5576f = i9 + 1;
                bArr[i9] = (byte) ((i8 | 128) & 255);
                i8 >>>= 7;
            } catch (IndexOutOfBoundsException e8) {
                throw new tj(String.format("Pos: %d, limit: %d, len: %d", Integer.valueOf(this.f5576f), Integer.valueOf(this.f5575e), 1), e8);
            }
        }
        byte[] bArr2 = this.f5574d;
        int i10 = this.f5576f;
        this.f5576f = i10 + 1;
        bArr2[i10] = (byte) i8;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_face_bundled.vj
    public final void w(int i8, long j8) {
        v(i8 << 3);
        x(j8);
    }

    @Override // com.google.android.gms.internal.mlkit_vision_face_bundled.vj
    public final void x(long j8) {
        boolean z7;
        z7 = vj.f5847c;
        if (!z7 || this.f5575e - this.f5576f < 10) {
            while ((j8 & (-128)) != 0) {
                try {
                    byte[] bArr = this.f5574d;
                    int i8 = this.f5576f;
                    this.f5576f = i8 + 1;
                    bArr[i8] = (byte) ((((int) j8) | 128) & 255);
                    j8 >>>= 7;
                } catch (IndexOutOfBoundsException e8) {
                    throw new tj(String.format("Pos: %d, limit: %d, len: %d", Integer.valueOf(this.f5576f), Integer.valueOf(this.f5575e), 1), e8);
                }
            }
            byte[] bArr2 = this.f5574d;
            int i9 = this.f5576f;
            this.f5576f = i9 + 1;
            bArr2[i9] = (byte) j8;
            return;
        }
        while (true) {
            int i10 = (int) j8;
            if ((j8 & (-128)) == 0) {
                byte[] bArr3 = this.f5574d;
                int i11 = this.f5576f;
                this.f5576f = i11 + 1;
                rm.s(bArr3, i11, (byte) i10);
                return;
            }
            byte[] bArr4 = this.f5574d;
            int i12 = this.f5576f;
            this.f5576f = i12 + 1;
            rm.s(bArr4, i12, (byte) ((i10 | 128) & 255));
            j8 >>>= 7;
        }
    }
}
