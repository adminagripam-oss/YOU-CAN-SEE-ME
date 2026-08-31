package a4;

import d5.c;
/* loaded from: classes.dex */
final class k5 implements d5.d {

    /* renamed from: a  reason: collision with root package name */
    static final k5 f743a = new k5();

    /* renamed from: b  reason: collision with root package name */
    private static final d5.c f744b;

    /* renamed from: c  reason: collision with root package name */
    private static final d5.c f745c;

    /* renamed from: d  reason: collision with root package name */
    private static final d5.c f746d;

    /* renamed from: e  reason: collision with root package name */
    private static final d5.c f747e;

    /* renamed from: f  reason: collision with root package name */
    private static final d5.c f748f;

    /* renamed from: g  reason: collision with root package name */
    private static final d5.c f749g;

    static {
        c.b a8 = d5.c.a("landmarkMode");
        s1 s1Var = new s1();
        s1Var.a(1);
        f744b = a8.b(s1Var.b()).a();
        c.b a9 = d5.c.a("classificationMode");
        s1 s1Var2 = new s1();
        s1Var2.a(2);
        f745c = a9.b(s1Var2.b()).a();
        c.b a10 = d5.c.a("performanceMode");
        s1 s1Var3 = new s1();
        s1Var3.a(3);
        f746d = a10.b(s1Var3.b()).a();
        c.b a11 = d5.c.a("contourMode");
        s1 s1Var4 = new s1();
        s1Var4.a(4);
        f747e = a11.b(s1Var4.b()).a();
        c.b a12 = d5.c.a("isTrackingEnabled");
        s1 s1Var5 = new s1();
        s1Var5.a(5);
        f748f = a12.b(s1Var5.b()).a();
        c.b a13 = d5.c.a("minFaceSize");
        s1 s1Var6 = new s1();
        s1Var6.a(6);
        f749g = a13.b(s1Var6.b()).a();
    }

    private k5() {
    }

    @Override // d5.d
    public final /* bridge */ /* synthetic */ void a(Object obj, Object obj2) {
        n9 n9Var = (n9) obj;
        d5.e eVar = (d5.e) obj2;
        eVar.c(f744b, n9Var.c());
        eVar.c(f745c, n9Var.a());
        eVar.c(f746d, n9Var.d());
        eVar.c(f747e, n9Var.b());
        eVar.c(f748f, n9Var.e());
        eVar.c(f749g, n9Var.f());
    }
}
