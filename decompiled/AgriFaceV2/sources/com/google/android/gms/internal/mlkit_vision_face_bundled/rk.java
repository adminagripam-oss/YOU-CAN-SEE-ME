package com.google.android.gms.internal.mlkit_vision_face_bundled;
/* loaded from: classes.dex */
public final class rk extends ok implements ml {
    private static final rk zzb;
    private int zzd;
    private sn zze;
    private byte zzf = 2;

    static {
        rk rkVar = new rk();
        zzb = rkVar;
        ok.n(rk.class, rkVar);
    }

    private rk() {
    }

    public static rk B(byte[] bArr, ak akVar) {
        return (rk) ok.z(zzb, bArr, akVar);
    }

    public final sn C() {
        sn snVar = this.zze;
        return snVar == null ? sn.B() : snVar;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_face_bundled.ok
    public final Object t(int i8, Object obj, Object obj2) {
        int i9 = i8 - 1;
        if (i9 != 0) {
            if (i9 == 2) {
                return ok.k(zzb, "\u0004\u0001\u0000\u0001\u0001\u0001\u0001\u0000\u0000\u0001\u0001ᐉ\u0000", new Object[]{"zzd", "zze"});
            } else if (i9 != 3) {
                if (i9 != 4) {
                    if (i9 != 5) {
                        this.zzf = obj == null ? (byte) 0 : (byte) 1;
                        return null;
                    }
                    return zzb;
                }
                return new sj(null);
            } else {
                return new rk();
            }
        }
        return Byte.valueOf(this.zzf);
    }
}
