package com.google.android.gms.internal.mlkit_vision_face_bundled;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class u0 implements d5.g {

    /* renamed from: a  reason: collision with root package name */
    private boolean f5627a = false;

    /* renamed from: b  reason: collision with root package name */
    private boolean f5628b = false;

    /* renamed from: c  reason: collision with root package name */
    private d5.c f5629c;

    /* renamed from: d  reason: collision with root package name */
    private final q0 f5630d;

    /* JADX INFO: Access modifiers changed from: package-private */
    public u0(q0 q0Var) {
        this.f5630d = q0Var;
    }

    private final void c() {
        if (this.f5627a) {
            throw new d5.b("Cannot encode a second value in the ValueEncoderContext");
        }
        this.f5627a = true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void a(d5.c cVar, boolean z7) {
        this.f5627a = false;
        this.f5629c = cVar;
        this.f5628b = z7;
    }

    @Override // d5.g
    public final d5.g b(String str) {
        c();
        this.f5630d.e(this.f5629c, str, this.f5628b);
        return this;
    }

    @Override // d5.g
    public final d5.g d(boolean z7) {
        c();
        this.f5630d.f(this.f5629c, z7 ? 1 : 0, this.f5628b);
        return this;
    }
}
