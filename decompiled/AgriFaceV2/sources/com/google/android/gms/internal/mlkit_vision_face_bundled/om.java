package com.google.android.gms.internal.mlkit_vision_face_bundled;
/* loaded from: classes.dex */
public final class om extends ok implements ml {
    private static final om zzb;
    private int zzd;
    private boolean zzi;
    private boolean zzj;
    private boolean zzm;
    private tn zzn;
    private la zzo;
    private e zzp;
    private float zze = 0.1f;
    private int zzf = 1;
    private int zzg = 1;
    private int zzh = 1;
    private float zzk = 45.0f;
    private float zzl = 0.5f;

    static {
        om omVar = new om();
        zzb = omVar;
        ok.n(om.class, omVar);
    }

    private om() {
    }

    public static pl A() {
        return (pl) zzb.v();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void C(om omVar, float f8) {
        omVar.zzd |= 1;
        omVar.zze = f8;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void D(om omVar, boolean z7) {
        omVar.zzd |= 32;
        omVar.zzj = z7;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void E(om omVar, boolean z7) {
        omVar.zzd |= 256;
        omVar.zzm = true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void F(om omVar, tn tnVar) {
        tnVar.getClass();
        omVar.zzn = tnVar;
        omVar.zzd |= 512;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void G(om omVar, la laVar) {
        laVar.getClass();
        omVar.zzo = laVar;
        omVar.zzd |= 1024;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void H(om omVar, e eVar) {
        eVar.getClass();
        omVar.zzp = eVar;
        omVar.zzd |= 2048;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void I(om omVar, boolean z7) {
        omVar.zzd |= 16;
        omVar.zzi = z7;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void L(om omVar, int i8) {
        omVar.zzf = i8 - 1;
        omVar.zzd |= 2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void M(om omVar, int i8) {
        omVar.zzg = i8 - 1;
        omVar.zzd |= 4;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void N(om omVar, int i8) {
        omVar.zzh = i8 - 1;
        omVar.zzd |= 8;
    }

    public final int J() {
        int a8 = nc.a(this.zzg);
        if (a8 == 0) {
            return 2;
        }
        return a8;
    }

    public final int K() {
        int a8 = c.a(this.zzf);
        if (a8 == 0) {
            return 2;
        }
        return a8;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.android.gms.internal.mlkit_vision_face_bundled.ok
    public final Object t(int i8, Object obj, Object obj2) {
        int i9 = i8 - 1;
        if (i9 != 0) {
            if (i9 == 2) {
                return ok.k(zzb, "\u0004\f\u0000\u0001\u0001\f\f\u0000\u0000\u0000\u0001ခ\u0000\u0002᠌\u0001\u0003᠌\u0002\u0004᠌\u0003\u0005ဇ\u0004\u0006ဇ\u0005\u0007ခ\u0006\bခ\u0007\tဇ\b\nဉ\t\u000bဉ\n\fဉ\u000b", new Object[]{"zzd", "zze", "zzf", b.f5138a, "zzg", mb.f5438a, "zzh", f.f5217a, "zzi", "zzj", "zzk", "zzl", "zzm", "zzn", "zzo", "zzp"});
            } else if (i9 != 3) {
                if (i9 != 4) {
                    if (i9 != 5) {
                        return null;
                    }
                    return zzb;
                }
                return new pl(null);
            } else {
                return new om();
            }
        }
        return (byte) 1;
    }
}
