package com.google.android.gms.internal.mlkit_vision_face_bundled;
/* loaded from: classes.dex */
public final class la extends ok implements ml {
    private static final la zzb;
    private int zzd;
    private h zze;
    private h zzf;

    static {
        la laVar = new la();
        zzb = laVar;
        ok.n(la.class, laVar);
    }

    private la() {
    }

    public static k9 A() {
        return (k9) zzb.v();
    }

    public static /* synthetic */ void C(la laVar, h hVar) {
        hVar.getClass();
        laVar.zze = hVar;
        laVar.zzd |= 1;
    }

    public static /* synthetic */ void D(la laVar, h hVar) {
        hVar.getClass();
        laVar.zzf = hVar;
        laVar.zzd |= 2;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_face_bundled.ok
    public final Object t(int i8, Object obj, Object obj2) {
        int i9 = i8 - 1;
        if (i9 != 0) {
            if (i9 == 2) {
                return ok.k(zzb, "\u0004\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001ဉ\u0000\u0002ဉ\u0001", new Object[]{"zzd", "zze", "zzf"});
            } else if (i9 != 3) {
                if (i9 != 4) {
                    if (i9 != 5) {
                        return null;
                    }
                    return zzb;
                }
                return new k9(null);
            } else {
                return new la();
            }
        }
        return (byte) 1;
    }
}
