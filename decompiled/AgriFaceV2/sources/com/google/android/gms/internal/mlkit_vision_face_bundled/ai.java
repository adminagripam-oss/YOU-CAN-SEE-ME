package com.google.android.gms.internal.mlkit_vision_face_bundled;

import java.io.UnsupportedEncodingException;
/* loaded from: classes.dex */
public final class ai implements oh {

    /* renamed from: a  reason: collision with root package name */
    private final wc f5127a;

    /* renamed from: b  reason: collision with root package name */
    private ng f5128b = new ng();

    /* renamed from: c  reason: collision with root package name */
    private final int f5129c;

    private ai(wc wcVar, int i8) {
        this.f5127a = wcVar;
        gi.a();
        this.f5129c = i8;
    }

    public static oh e(wc wcVar, int i8) {
        return new ai(wcVar, i8);
    }

    @Override // com.google.android.gms.internal.mlkit_vision_face_bundled.oh
    public final oh a(ng ngVar) {
        this.f5128b = ngVar;
        return this;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_face_bundled.oh
    public final String b() {
        String k8;
        pg d8 = this.f5127a.h().d();
        return (d8 == null || (k8 = d8.k()) == null || k8.isEmpty()) ? "NA" : (String) k3.q.g(d8.k());
    }

    @Override // com.google.android.gms.internal.mlkit_vision_face_bundled.oh
    public final byte[] c(int i8, boolean z7) {
        this.f5128b.f(Boolean.valueOf(1 == (i8 ^ 1)));
        this.f5128b.e(Boolean.FALSE);
        this.f5127a.g(this.f5128b.m());
        try {
            gi.a();
            if (i8 == 0) {
                return new f5.d().g(na.f5464a).h(true).f().a(this.f5127a.h()).getBytes("utf-8");
            }
            yc h8 = this.f5127a.h();
            s0 s0Var = new s0();
            na.f5464a.a(s0Var);
            return s0Var.b().a(h8);
        } catch (UnsupportedEncodingException e8) {
            throw new UnsupportedOperationException("Failed to covert logging to UTF-8 byte array", e8);
        }
    }

    @Override // com.google.android.gms.internal.mlkit_vision_face_bundled.oh
    public final oh d(vc vcVar) {
        this.f5127a.e(vcVar);
        return this;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_face_bundled.oh
    public final int zza() {
        return this.f5129c;
    }
}
