package com.google.android.gms.internal.mlkit_vision_face_bundled;
/* loaded from: classes.dex */
public final class gn extends ok implements ml {
    private static final gn zzb;
    private int zzd;
    private int zze;
    private mj zzf = mj.f5439f;
    private String zzg = "";
    private float zzh;

    static {
        gn gnVar = new gn();
        zzb = gnVar;
        ok.n(gn.class, gnVar);
    }

    private gn() {
    }

    @Override // com.google.android.gms.internal.mlkit_vision_face_bundled.ok
    public final Object t(int i8, Object obj, Object obj2) {
        int i9 = i8 - 1;
        if (i9 != 0) {
            if (i9 == 2) {
                return ok.k(zzb, "\u0001\u0004\u0000\u0001\u0001\u0004\u0004\u0000\u0000\u0000\u0001င\u0000\u0002ည\u0001\u0003ဈ\u0002\u0004ခ\u0003", new Object[]{"zzd", "zze", "zzf", "zzg", "zzh"});
            } else if (i9 != 3) {
                if (i9 != 4) {
                    if (i9 != 5) {
                        return null;
                    }
                    return zzb;
                }
                return new fn(null);
            } else {
                return new gn();
            }
        }
        return (byte) 1;
    }
}
