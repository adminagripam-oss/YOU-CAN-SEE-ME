package com.google.android.gms.internal.mlkit_vision_face_bundled;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class el implements xl {

    /* renamed from: b  reason: collision with root package name */
    private static final jl f5214b = new cl();

    /* renamed from: a  reason: collision with root package name */
    private final jl f5215a;

    public el() {
        jl jlVar = f5214b;
        int i8 = tl.f5621d;
        dl dlVar = new dl(ik.c(), jlVar);
        byte[] bArr = uk.f5674b;
        this.f5215a = dlVar;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_face_bundled.xl
    public final wl a(Class cls) {
        int i8 = yl.f5963b;
        if (!ok.class.isAssignableFrom(cls)) {
            int i9 = tl.f5621d;
        }
        il a8 = this.f5215a.a(cls);
        if (a8.zzb()) {
            int i10 = tl.f5621d;
            return ql.j(yl.v(), dk.a(), a8.zza());
        }
        int i11 = tl.f5621d;
        return ol.A(cls, a8, sl.a(), al.a(), yl.v(), a8.a() + (-1) != 1 ? dk.a() : null, hl.a());
    }
}
