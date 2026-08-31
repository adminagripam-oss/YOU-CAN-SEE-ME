package com.google.android.gms.internal.mlkit_vision_face_bundled;
/* loaded from: classes.dex */
public abstract class jk extends xi {

    /* renamed from: e  reason: collision with root package name */
    private final ok f5390e;

    /* renamed from: f  reason: collision with root package name */
    protected ok f5391f;

    /* JADX INFO: Access modifiers changed from: protected */
    public jk(ok okVar) {
        this.f5390e = okVar;
        if (okVar.q()) {
            throw new IllegalArgumentException("Default instance must be immutable.");
        }
        this.f5391f = okVar.y();
    }

    @Override // com.google.android.gms.internal.mlkit_vision_face_bundled.ml
    public final boolean g() {
        return ok.p(this.f5391f, false);
    }

    /* renamed from: h */
    public final jk clone() {
        jk jkVar = (jk) this.f5390e.t(5, null, null);
        jkVar.f5391f = b();
        return jkVar;
    }

    public final ok i() {
        ok b8 = b();
        if (ok.p(b8, true)) {
            return b8;
        }
        throw new im(b8);
    }

    @Override // com.google.android.gms.internal.mlkit_vision_face_bundled.kl
    /* renamed from: j */
    public ok b() {
        if (this.f5391f.q()) {
            this.f5391f.l();
            return this.f5391f;
        }
        return this.f5391f;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void k() {
        if (this.f5391f.q()) {
            return;
        }
        l();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void l() {
        ok y7 = this.f5390e.y();
        tl.a().b(y7.getClass()).e(y7, this.f5391f);
        this.f5391f = y7;
    }
}
