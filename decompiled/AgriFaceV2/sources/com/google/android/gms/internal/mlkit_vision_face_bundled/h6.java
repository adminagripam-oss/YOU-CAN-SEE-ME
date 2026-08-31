package com.google.android.gms.internal.mlkit_vision_face_bundled;
/* loaded from: classes.dex */
public final class h6 extends ok implements ml {
    private static final h6 zzb;
    private int zzd;
    private int zze;
    private int zzf;
    private int zzg;
    private int zzh;
    private boolean zzi;
    private long zzj;
    private String zzk = "";

    static {
        h6 h6Var = new h6();
        zzb = h6Var;
        ok.n(h6.class, h6Var);
    }

    private h6() {
    }

    public static g5 A() {
        return (g5) zzb.v();
    }

    public static /* synthetic */ void C(h6 h6Var, int i8) {
        h6Var.zzd |= 1;
        h6Var.zze = i8;
    }

    public static /* synthetic */ void D(h6 h6Var, long j8) {
        h6Var.zzd |= 32;
        h6Var.zzj = j8;
    }

    public static /* synthetic */ void E(h6 h6Var, int i8) {
        h6Var.zzd |= 2;
        h6Var.zzf = i8;
    }

    public static /* synthetic */ void F(h6 h6Var, int i8) {
        h6Var.zzg = i8 - 1;
        h6Var.zzd |= 4;
    }

    public static /* synthetic */ void G(h6 h6Var, int i8) {
        h6Var.zzh = i8 - 1;
        h6Var.zzd |= 8;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_face_bundled.ok
    public final Object t(int i8, Object obj, Object obj2) {
        int i9 = i8 - 1;
        if (i9 != 0) {
            if (i9 == 2) {
                return ok.k(zzb, "\u0004\u0007\u0000\u0001\u0001\u0007\u0007\u0000\u0000\u0000\u0001င\u0000\u0002င\u0001\u0003᠌\u0002\u0004᠌\u0003\u0005ဇ\u0004\u0006ဂ\u0005\u0007ဈ\u0006", new Object[]{"zzd", "zze", "zzf", "zzg", e3.f5206a, "zzh", i7.f5374a, "zzi", "zzj", "zzk"});
            } else if (i9 != 3) {
                if (i9 != 4) {
                    if (i9 != 5) {
                        return null;
                    }
                    return zzb;
                }
                return new g5(null);
            } else {
                return new h6();
            }
        }
        return (byte) 1;
    }
}
