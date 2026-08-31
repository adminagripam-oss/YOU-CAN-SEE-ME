package com.google.android.gms.internal.mlkit_vision_face_bundled;

import d5.c;
/* loaded from: classes.dex */
final class n5 implements d5.d {

    /* renamed from: a  reason: collision with root package name */
    static final n5 f5453a = new n5();

    /* renamed from: b  reason: collision with root package name */
    private static final d5.c f5454b;

    /* renamed from: c  reason: collision with root package name */
    private static final d5.c f5455c;

    /* renamed from: d  reason: collision with root package name */
    private static final d5.c f5456d;

    /* renamed from: e  reason: collision with root package name */
    private static final d5.c f5457e;

    /* renamed from: f  reason: collision with root package name */
    private static final d5.c f5458f;

    /* renamed from: g  reason: collision with root package name */
    private static final d5.c f5459g;

    static {
        c.b a8 = d5.c.a("landmarkMode");
        k0 k0Var = new k0();
        k0Var.a(1);
        f5454b = a8.b(k0Var.b()).a();
        c.b a9 = d5.c.a("classificationMode");
        k0 k0Var2 = new k0();
        k0Var2.a(2);
        f5455c = a9.b(k0Var2.b()).a();
        c.b a10 = d5.c.a("performanceMode");
        k0 k0Var3 = new k0();
        k0Var3.a(3);
        f5456d = a10.b(k0Var3.b()).a();
        c.b a11 = d5.c.a("contourMode");
        k0 k0Var4 = new k0();
        k0Var4.a(4);
        f5457e = a11.b(k0Var4.b()).a();
        c.b a12 = d5.c.a("isTrackingEnabled");
        k0 k0Var5 = new k0();
        k0Var5.a(5);
        f5458f = a12.b(k0Var5.b()).a();
        c.b a13 = d5.c.a("minFaceSize");
        k0 k0Var6 = new k0();
        k0Var6.a(6);
        f5459g = a13.b(k0Var6.b()).a();
    }

    private n5() {
    }

    @Override // d5.d
    public final /* bridge */ /* synthetic */ void a(Object obj, Object obj2) {
        cc ccVar = (cc) obj;
        d5.e eVar = (d5.e) obj2;
        eVar.c(f5454b, ccVar.c());
        eVar.c(f5455c, ccVar.a());
        eVar.c(f5456d, ccVar.d());
        eVar.c(f5457e, ccVar.b());
        eVar.c(f5458f, ccVar.e());
        eVar.c(f5459g, ccVar.f());
    }
}
