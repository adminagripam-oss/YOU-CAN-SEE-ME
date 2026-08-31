package a4;

import d5.c;
/* loaded from: classes.dex */
final class t3 implements d5.d {

    /* renamed from: a  reason: collision with root package name */
    static final t3 f1079a = new t3();

    /* renamed from: b  reason: collision with root package name */
    private static final d5.c f1080b;

    /* renamed from: c  reason: collision with root package name */
    private static final d5.c f1081c;

    /* renamed from: d  reason: collision with root package name */
    private static final d5.c f1082d;

    /* renamed from: e  reason: collision with root package name */
    private static final d5.c f1083e;

    /* renamed from: f  reason: collision with root package name */
    private static final d5.c f1084f;

    /* renamed from: g  reason: collision with root package name */
    private static final d5.c f1085g;

    static {
        c.b a8 = d5.c.a("errorCode");
        s1 s1Var = new s1();
        s1Var.a(1);
        f1080b = a8.b(s1Var.b()).a();
        c.b a9 = d5.c.a("isColdCall");
        s1 s1Var2 = new s1();
        s1Var2.a(2);
        f1081c = a9.b(s1Var2.b()).a();
        c.b a10 = d5.c.a("imageInfo");
        s1 s1Var3 = new s1();
        s1Var3.a(3);
        f1082d = a10.b(s1Var3.b()).a();
        c.b a11 = d5.c.a("detectorOptions");
        s1 s1Var4 = new s1();
        s1Var4.a(4);
        f1083e = a11.b(s1Var4.b()).a();
        c.b a12 = d5.c.a("contourDetectedFaces");
        s1 s1Var5 = new s1();
        s1Var5.a(5);
        f1084f = a12.b(s1Var5.b()).a();
        c.b a13 = d5.c.a("nonContourDetectedFaces");
        s1 s1Var6 = new s1();
        s1Var6.a(6);
        f1085g = a13.b(s1Var6.b()).a();
    }

    private t3() {
    }

    @Override // d5.d
    public final /* bridge */ /* synthetic */ void a(Object obj, Object obj2) {
        q2 q2Var = (q2) obj;
        d5.e eVar = (d5.e) obj2;
        eVar.c(f1080b, q2Var.b());
        eVar.c(f1081c, q2Var.c());
        eVar.c(f1082d, null);
        eVar.c(f1083e, q2Var.a());
        eVar.c(f1084f, q2Var.d());
        eVar.c(f1085g, q2Var.e());
    }
}
