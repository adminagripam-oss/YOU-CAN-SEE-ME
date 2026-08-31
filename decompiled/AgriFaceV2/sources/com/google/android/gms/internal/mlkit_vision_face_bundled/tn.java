package com.google.android.gms.internal.mlkit_vision_face_bundled;
/* loaded from: classes.dex */
public final class tn extends ok implements ml {
    private static final tn zzb;
    private int zzd;
    private h zze;
    private h zzf;
    private h zzg;
    private h zzh;

    static {
        tn tnVar = new tn();
        zzb = tnVar;
        ok.n(tn.class, tnVar);
    }

    private tn() {
    }

    public static pn A() {
        return (pn) zzb.v();
    }

    public static /* synthetic */ void C(tn tnVar, h hVar) {
        hVar.getClass();
        tnVar.zze = hVar;
        tnVar.zzd |= 1;
    }

    public static /* synthetic */ void D(tn tnVar, h hVar) {
        hVar.getClass();
        tnVar.zzf = hVar;
        tnVar.zzd |= 2;
    }

    public static /* synthetic */ void E(tn tnVar, h hVar) {
        hVar.getClass();
        tnVar.zzg = hVar;
        tnVar.zzd |= 4;
    }

    public static /* synthetic */ void F(tn tnVar, h hVar) {
        hVar.getClass();
        tnVar.zzh = hVar;
        tnVar.zzd |= 8;
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
                return new pn(null);
            } else {
                return new tn();
            }
        }
        return (byte) 1;
    }
}
