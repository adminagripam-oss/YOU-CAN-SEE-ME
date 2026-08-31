package com.google.android.gms.internal.mlkit_vision_face_bundled;
/* loaded from: classes.dex */
public abstract class kk extends jk implements ml {
    /* JADX INFO: Access modifiers changed from: protected */
    public kk(lk lkVar) {
        super(lkVar);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.android.gms.internal.mlkit_vision_face_bundled.jk
    public final void l() {
        super.l();
        if (((lk) this.f5391f).zzb != fk.d()) {
            lk lkVar = (lk) this.f5391f;
            lkVar.zzb = lkVar.zzb.clone();
        }
    }

    @Override // com.google.android.gms.internal.mlkit_vision_face_bundled.jk
    /* renamed from: m */
    public final lk j() {
        ok b8;
        if (((lk) this.f5391f).q()) {
            ((lk) this.f5391f).zzb.h();
            b8 = super.b();
        } else {
            b8 = this.f5391f;
        }
        return (lk) b8;
    }
}
