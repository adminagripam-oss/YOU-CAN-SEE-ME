package com.google.android.gms.internal.mlkit_vision_face_bundled;

import java.util.ArrayList;
import java.util.List;
/* loaded from: classes.dex */
public abstract class lk extends ok implements ml {
    protected fk zzb = fk.d();

    /* JADX INFO: Access modifiers changed from: package-private */
    public final fk A() {
        if (this.zzb.k()) {
            this.zzb = this.zzb.clone();
        }
        return this.zzb;
    }

    public final Object B(yj yjVar) {
        nk nkVar = (nk) yjVar;
        if (nkVar.f5490a == ((ok) t(6, null, null))) {
            Object e8 = this.zzb.e(nkVar.f5493d);
            if (e8 == null) {
                return nkVar.f5491b;
            }
            if (nkVar.f5493d.f5442f.d() == wm.ENUM) {
                ArrayList arrayList = new ArrayList();
                for (Object obj : (List) e8) {
                    if (nkVar.f5493d.f5442f.d() == wm.ENUM) {
                        ((Integer) obj).intValue();
                        throw null;
                    }
                    arrayList.add(obj);
                }
                return arrayList;
            }
            return e8;
        }
        throw new IllegalArgumentException("This extension is for a different message type.  Please make sure that you are not suppressing any generics type warnings.");
    }
}
