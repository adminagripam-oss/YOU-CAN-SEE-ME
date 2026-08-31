package com.google.android.gms.internal.mlkit_vision_face_bundled;

import java.util.List;
/* loaded from: classes.dex */
public final class si extends ok implements ml {
    private static final si zzb;
    private int zzd;
    private int zze;
    private tk zzf = ok.i();

    static {
        si siVar = new si();
        zzb = siVar;
        ok.n(si.class, siVar);
    }

    private si() {
    }

    public static si B() {
        return zzb;
    }

    public final List C() {
        return this.zzf;
    }

    public final int D() {
        int a8 = sh.a(this.zze);
        if (a8 == 0) {
            return 1;
        }
        return a8;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_face_bundled.ok
    public final Object t(int i8, Object obj, Object obj2) {
        int i9 = i8 - 1;
        if (i9 != 0) {
            if (i9 == 2) {
                return ok.k(zzb, "\u0004\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0001\u0000\u0001᠌\u0000\u0002\u001b", new Object[]{"zzd", "zze", rg.f5573a, "zzf", qf.class});
            } else if (i9 != 3) {
                if (i9 != 4) {
                    if (i9 != 5) {
                        return null;
                    }
                    return zzb;
                }
                return new od(null);
            } else {
                return new si();
            }
        }
        return (byte) 1;
    }
}
