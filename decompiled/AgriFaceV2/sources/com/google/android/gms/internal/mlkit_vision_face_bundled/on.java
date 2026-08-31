package com.google.android.gms.internal.mlkit_vision_face_bundled;

import java.util.List;
/* loaded from: classes.dex */
public final class on extends lk {
    private static final on zzd;
    private int zze;
    private dn zzf;
    private float zzh;
    private float zzi;
    private float zzj;
    private float zzk;
    private float zzl;
    private long zzo;
    private long zzp;
    private long zzq;
    private float zzr;
    private in zzs;
    private byte zzt = 2;
    private tk zzg = ok.i();
    private tk zzm = ok.i();
    private tk zzn = ok.i();

    static {
        on onVar = new on();
        zzd = onVar;
        ok.n(on.class, onVar);
    }

    private on() {
    }

    public static on K() {
        return zzd;
    }

    public final boolean C() {
        return (this.zze & 2) != 0;
    }

    public final float D() {
        return this.zzh;
    }

    public final float E() {
        return this.zzj;
    }

    public final float F() {
        return this.zzi;
    }

    public final float G() {
        return this.zzk;
    }

    public final long H() {
        return this.zzp;
    }

    public final dn I() {
        dn dnVar = this.zzf;
        return dnVar == null ? dn.F() : dnVar;
    }

    public final List L() {
        return this.zzn;
    }

    public final List M() {
        return this.zzg;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_face_bundled.ok
    public final Object t(int i8, Object obj, Object obj2) {
        int i9 = i8 - 1;
        if (i9 != 0) {
            if (i9 == 2) {
                return ok.k(zzd, "\u0001\u000e\u0000\u0001\u0001\u000e\u000e\u0000\u0003\u0000\u0001ဉ\u0000\u0002\u001b\u0003ခ\u0001\u0004ခ\u0002\u0005ခ\u0003\u0006ခ\u0004\u0007\u001b\b\u001b\tဃ\u0007\nခ\t\u000bဃ\b\fဃ\u0006\rခ\u0005\u000eဉ\n", new Object[]{"zze", "zzf", "zzg", nn.class, "zzh", "zzi", "zzj", "zzk", "zzm", gn.class, "zzn", bn.class, "zzp", "zzr", "zzq", "zzo", "zzl", "zzs"});
            } else if (i9 != 3) {
                if (i9 != 4) {
                    if (i9 != 5) {
                        this.zzt = obj == null ? (byte) 0 : (byte) 1;
                        return null;
                    }
                    return zzd;
                }
                return new en(null);
            } else {
                return new on();
            }
        }
        return Byte.valueOf(this.zzt);
    }
}
