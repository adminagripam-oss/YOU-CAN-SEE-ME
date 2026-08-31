package com.google.android.gms.internal.mlkit_vision_face_bundled;
/* loaded from: classes.dex */
public final class dn extends ok implements ml {
    private static final dn zzb;
    private int zzd;
    private float zze;
    private float zzf;
    private float zzg;
    private float zzh;

    static {
        dn dnVar = new dn();
        zzb = dnVar;
        ok.n(dn.class, dnVar);
    }

    private dn() {
    }

    public static dn F() {
        return zzb;
    }

    public final float A() {
        return this.zze;
    }

    public final float B() {
        return this.zzg;
    }

    public final float C() {
        return this.zzf;
    }

    public final float D() {
        return this.zzh;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.android.gms.internal.mlkit_vision_face_bundled.ok
    public final Object t(int i8, Object obj, Object obj2) {
        int i9 = i8 - 1;
        if (i9 != 0) {
            if (i9 == 2) {
                return ok.k(zzb, "\u0001\u0004\u0000\u0001\u0001\u0004\u0004\u0000\u0000\u0000\u0001ခ\u0000\u0002ခ\u0001\u0003ခ\u0002\u0004ခ\u0003", new Object[]{"zzd", "zze", "zzf", "zzg", "zzh"});
            } else if (i9 != 3) {
                if (i9 != 4) {
                    if (i9 != 5) {
                        return null;
                    }
                    return zzb;
                }
                return new cn(null);
            } else {
                return new dn();
            }
        }
        return (byte) 1;
    }
}
