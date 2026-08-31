package com.google.android.gms.internal.mlkit_vision_face_bundled;

import java.util.List;
/* loaded from: classes.dex */
public final class sn extends ok implements ml {
    private static final sn zzb;
    private byte zze = 2;
    private tk zzd = ok.i();

    static {
        sn snVar = new sn();
        zzb = snVar;
        ok.n(sn.class, snVar);
    }

    private sn() {
    }

    public static sn B() {
        return zzb;
    }

    public final List C() {
        return this.zzd;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_face_bundled.ok
    public final Object t(int i8, Object obj, Object obj2) {
        int i9 = i8 - 1;
        if (i9 != 0) {
            if (i9 == 2) {
                return ok.k(zzb, "\u0001\u0001\u0000\u0000\u0001\u0001\u0001\u0000\u0001\u0001\u0001Л", new Object[]{"zzd", on.class});
            } else if (i9 != 3) {
                if (i9 != 4) {
                    if (i9 != 5) {
                        this.zze = obj == null ? (byte) 0 : (byte) 1;
                        return null;
                    }
                    return zzb;
                }
                return new rn(null);
            } else {
                return new sn();
            }
        }
        return Byte.valueOf(this.zze);
    }
}
