package com.google.android.gms.internal.mlkit_vision_face_bundled;
/* loaded from: classes.dex */
public final class h extends ok implements ml {
    private static final h zzb;
    private int zzd;
    private mj zze = mj.f5439f;
    private String zzf = "";
    private String zzg = "";

    static {
        h hVar = new h();
        zzb = hVar;
        ok.n(h.class, hVar);
    }

    private h() {
    }

    public static g A() {
        return (g) zzb.v();
    }

    public static /* synthetic */ void C(h hVar, String str) {
        hVar.zzd |= 2;
        hVar.zzf = str;
    }

    public static /* synthetic */ void D(h hVar, String str) {
        hVar.zzd |= 4;
        hVar.zzg = str;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_face_bundled.ok
    public final Object t(int i8, Object obj, Object obj2) {
        int i9 = i8 - 1;
        if (i9 != 0) {
            if (i9 == 2) {
                return ok.k(zzb, "\u0004\u0003\u0000\u0001\u0001\u0003\u0003\u0000\u0000\u0000\u0001ည\u0000\u0002ဈ\u0001\u0003ဈ\u0002", new Object[]{"zzd", "zze", "zzf", "zzg"});
            } else if (i9 != 3) {
                if (i9 != 4) {
                    if (i9 != 5) {
                        return null;
                    }
                    return zzb;
                }
                return new g(null);
            } else {
                return new h();
            }
        }
        return (byte) 1;
    }
}
