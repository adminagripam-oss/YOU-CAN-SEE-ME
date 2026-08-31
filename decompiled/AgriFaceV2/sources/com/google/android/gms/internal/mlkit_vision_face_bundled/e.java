package com.google.android.gms.internal.mlkit_vision_face_bundled;
/* loaded from: classes.dex */
public final class e extends ok implements ml {
    private static final e zzb;
    private int zzd;
    private h zze;
    private h zzf;
    private h zzg;
    private h zzh;

    static {
        e eVar = new e();
        zzb = eVar;
        ok.n(e.class, eVar);
    }

    private e() {
    }

    public static d A() {
        return (d) zzb.v();
    }

    public static /* synthetic */ void C(e eVar, h hVar) {
        hVar.getClass();
        eVar.zze = hVar;
        eVar.zzd |= 1;
    }

    public static /* synthetic */ void D(e eVar, h hVar) {
        hVar.getClass();
        eVar.zzf = hVar;
        eVar.zzd |= 2;
    }

    public static /* synthetic */ void E(e eVar, h hVar) {
        hVar.getClass();
        eVar.zzg = hVar;
        eVar.zzd |= 4;
    }

    public static /* synthetic */ void F(e eVar, h hVar) {
        hVar.getClass();
        eVar.zzh = hVar;
        eVar.zzd |= 8;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_face_bundled.ok
    public final Object t(int i8, Object obj, Object obj2) {
        int i9 = i8 - 1;
        if (i9 != 0) {
            if (i9 == 2) {
                return ok.k(zzb, "\u0004\u0004\u0000\u0001\u0001\u0004\u0004\u0000\u0000\u0000\u0001ဉ\u0000\u0002ဉ\u0001\u0003ဉ\u0002\u0004ဉ\u0003", new Object[]{"zzd", "zze", "zzf", "zzg", "zzh"});
            } else if (i9 != 3) {
                if (i9 != 4) {
                    if (i9 != 5) {
                        return null;
                    }
                    return zzb;
                }
                return new d(null);
            } else {
                return new e();
            }
        }
        return (byte) 1;
    }
}
