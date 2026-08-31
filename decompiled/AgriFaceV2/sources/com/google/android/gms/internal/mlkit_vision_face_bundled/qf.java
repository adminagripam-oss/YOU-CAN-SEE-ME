package com.google.android.gms.internal.mlkit_vision_face_bundled;
/* loaded from: classes.dex */
public final class qf extends ok implements ml {
    private static final qf zzb;
    private int zzd;
    private float zze;
    private float zzf;
    private float zzg;

    static {
        qf qfVar = new qf();
        zzb = qfVar;
        ok.n(qf.class, qfVar);
    }

    private qf() {
    }

    public final float A() {
        return this.zze;
    }

    public final float B() {
        return this.zzf;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.android.gms.internal.mlkit_vision_face_bundled.ok
    public final Object t(int i8, Object obj, Object obj2) {
        int i9 = i8 - 1;
        if (i9 != 0) {
            if (i9 == 2) {
                return ok.k(zzb, "\u0004\u0003\u0000\u0001\u0001\u0003\u0003\u0000\u0000\u0000\u0001ခ\u0000\u0002ခ\u0001\u0003ခ\u0002", new Object[]{"zzd", "zze", "zzf", "zzg"});
            } else if (i9 != 3) {
                if (i9 != 4) {
                    if (i9 != 5) {
                        return null;
                    }
                    return zzb;
                }
                return new pe(null);
            } else {
                return new qf();
            }
        }
        return (byte) 1;
    }
}
