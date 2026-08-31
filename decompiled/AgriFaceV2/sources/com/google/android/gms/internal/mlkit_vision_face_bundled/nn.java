package com.google.android.gms.internal.mlkit_vision_face_bundled;
/* loaded from: classes.dex */
public final class nn extends ok implements ml {
    private static final nn zzb;
    private int zzd;
    private float zze;
    private float zzf;
    private float zzg;
    private int zzh = 15000;
    private int zzi;
    private float zzj;

    static {
        nn nnVar = new nn();
        zzb = nnVar;
        ok.n(nn.class, nnVar);
    }

    private nn() {
    }

    public final float A() {
        return this.zze;
    }

    public final float B() {
        return this.zzf;
    }

    public final int D() {
        int a8 = ln.a(this.zzh);
        if (a8 == 0) {
            return 15001;
        }
        return a8;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_face_bundled.ok
    public final Object t(int i8, Object obj, Object obj2) {
        int i9 = i8 - 1;
        if (i9 != 0) {
            if (i9 == 2) {
                return ok.k(zzb, "\u0001\u0006\u0000\u0001\u0001\u0006\u0006\u0000\u0000\u0000\u0001ခ\u0000\u0002ခ\u0001\u0003ခ\u0002\u0004᠌\u0003\u0005᠌\u0004\u0006ခ\u0005", new Object[]{"zzd", "zze", "zzf", "zzg", "zzh", kn.f5407a, "zzi", mn.f5443a, "zzj"});
            } else if (i9 != 3) {
                if (i9 != 4) {
                    if (i9 != 5) {
                        return null;
                    }
                    return zzb;
                }
                return new jn(null);
            } else {
                return new nn();
            }
        }
        return (byte) 1;
    }
}
