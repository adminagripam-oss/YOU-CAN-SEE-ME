package com.google.android.gms.internal.mlkit_vision_face_bundled;

import d5.c;
/* loaded from: classes.dex */
final class m5 implements d5.d {

    /* renamed from: a  reason: collision with root package name */
    static final m5 f5429a = new m5();

    /* renamed from: b  reason: collision with root package name */
    private static final d5.c f5430b;

    /* renamed from: c  reason: collision with root package name */
    private static final d5.c f5431c;

    /* renamed from: d  reason: collision with root package name */
    private static final d5.c f5432d;

    static {
        c.b a8 = d5.c.a("options");
        k0 k0Var = new k0();
        k0Var.a(1);
        f5430b = a8.b(k0Var.b()).a();
        c.b a9 = d5.c.a("eventType");
        k0 k0Var2 = new k0();
        k0Var2.a(2);
        f5431c = a9.b(k0Var2.b()).a();
        c.b a10 = d5.c.a("errorCode");
        k0 k0Var3 = new k0();
        k0Var3.a(3);
        f5432d = a10.b(k0Var3.b()).a();
    }

    private m5() {
    }

    @Override // d5.d
    public final /* bridge */ /* synthetic */ void a(Object obj, Object obj2) {
        vb vbVar = (vb) obj;
        d5.e eVar = (d5.e) obj2;
        eVar.c(f5430b, vbVar.a());
        eVar.c(f5431c, null);
        eVar.c(f5432d, vbVar.b());
    }
}
