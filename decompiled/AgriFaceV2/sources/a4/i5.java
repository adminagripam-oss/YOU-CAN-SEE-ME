package a4;

import d5.c;
/* loaded from: classes.dex */
final class i5 implements d5.d {

    /* renamed from: a  reason: collision with root package name */
    static final i5 f672a = new i5();

    /* renamed from: b  reason: collision with root package name */
    private static final d5.c f673b;

    /* renamed from: c  reason: collision with root package name */
    private static final d5.c f674c;

    /* renamed from: d  reason: collision with root package name */
    private static final d5.c f675d;

    /* renamed from: e  reason: collision with root package name */
    private static final d5.c f676e;

    /* renamed from: f  reason: collision with root package name */
    private static final d5.c f677f;

    /* renamed from: g  reason: collision with root package name */
    private static final d5.c f678g;

    static {
        c.b a8 = d5.c.a("maxMs");
        s1 s1Var = new s1();
        s1Var.a(1);
        f673b = a8.b(s1Var.b()).a();
        c.b a9 = d5.c.a("minMs");
        s1 s1Var2 = new s1();
        s1Var2.a(2);
        f674c = a9.b(s1Var2.b()).a();
        c.b a10 = d5.c.a("avgMs");
        s1 s1Var3 = new s1();
        s1Var3.a(3);
        f675d = a10.b(s1Var3.b()).a();
        c.b a11 = d5.c.a("firstQuartileMs");
        s1 s1Var4 = new s1();
        s1Var4.a(4);
        f676e = a11.b(s1Var4.b()).a();
        c.b a12 = d5.c.a("medianMs");
        s1 s1Var5 = new s1();
        s1Var5.a(5);
        f677f = a12.b(s1Var5.b()).a();
        c.b a13 = d5.c.a("thirdQuartileMs");
        s1 s1Var6 = new s1();
        s1Var6.a(6);
        f678g = a13.b(s1Var6.b()).a();
    }

    private i5() {
    }

    @Override // d5.d
    public final /* bridge */ /* synthetic */ void a(Object obj, Object obj2) {
        e9 e9Var = (e9) obj;
        d5.e eVar = (d5.e) obj2;
        eVar.c(f673b, e9Var.c());
        eVar.c(f674c, e9Var.e());
        eVar.c(f675d, e9Var.a());
        eVar.c(f676e, e9Var.b());
        eVar.c(f677f, e9Var.d());
        eVar.c(f678g, e9Var.f());
    }
}
