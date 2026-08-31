package a4;

import d5.c;
/* loaded from: classes.dex */
final class m6 implements d5.d {

    /* renamed from: a  reason: collision with root package name */
    static final m6 f818a = new m6();

    /* renamed from: b  reason: collision with root package name */
    private static final d5.c f819b;

    /* renamed from: c  reason: collision with root package name */
    private static final d5.c f820c;

    /* renamed from: d  reason: collision with root package name */
    private static final d5.c f821d;

    /* renamed from: e  reason: collision with root package name */
    private static final d5.c f822e;

    /* renamed from: f  reason: collision with root package name */
    private static final d5.c f823f;

    /* renamed from: g  reason: collision with root package name */
    private static final d5.c f824g;

    static {
        c.b a8 = d5.c.a("inferenceCommonLogEvent");
        s1 s1Var = new s1();
        s1Var.a(1);
        f819b = a8.b(s1Var.b()).a();
        c.b a9 = d5.c.a("options");
        s1 s1Var2 = new s1();
        s1Var2.a(2);
        f820c = a9.b(s1Var2.b()).a();
        c.b a10 = d5.c.a("imageInfo");
        s1 s1Var3 = new s1();
        s1Var3.a(3);
        f821d = a10.b(s1Var3.b()).a();
        c.b a11 = d5.c.a("detectorOptions");
        s1 s1Var4 = new s1();
        s1Var4.a(4);
        f822e = a11.b(s1Var4.b()).a();
        c.b a12 = d5.c.a("contourDetectedFaces");
        s1 s1Var5 = new s1();
        s1Var5.a(5);
        f823f = a12.b(s1Var5.b()).a();
        c.b a13 = d5.c.a("nonContourDetectedFaces");
        s1 s1Var6 = new s1();
        s1Var6.a(6);
        f824g = a13.b(s1Var6.b()).a();
    }

    private m6() {
    }

    @Override // d5.d
    public final /* bridge */ /* synthetic */ void a(Object obj, Object obj2) {
        wa waVar = (wa) obj;
        d5.e eVar = (d5.e) obj2;
        eVar.c(f819b, waVar.c());
        eVar.c(f820c, null);
        eVar.c(f821d, waVar.b());
        eVar.c(f822e, waVar.a());
        eVar.c(f823f, waVar.d());
        eVar.c(f824g, waVar.e());
    }
}
