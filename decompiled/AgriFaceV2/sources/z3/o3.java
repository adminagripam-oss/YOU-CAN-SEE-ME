package z3;

import d5.c;
/* loaded from: classes.dex */
final class o3 implements d5.d {

    /* renamed from: a  reason: collision with root package name */
    static final o3 f13960a = new o3();

    /* renamed from: b  reason: collision with root package name */
    private static final d5.c f13961b;

    /* renamed from: c  reason: collision with root package name */
    private static final d5.c f13962c;

    /* renamed from: d  reason: collision with root package name */
    private static final d5.c f13963d;

    /* renamed from: e  reason: collision with root package name */
    private static final d5.c f13964e;

    /* renamed from: f  reason: collision with root package name */
    private static final d5.c f13965f;

    /* renamed from: g  reason: collision with root package name */
    private static final d5.c f13966g;

    /* renamed from: h  reason: collision with root package name */
    private static final d5.c f13967h;

    static {
        c.b a8 = d5.c.a("durationMs");
        f fVar = new f();
        fVar.a(1);
        f13961b = a8.b(fVar.b()).a();
        c.b a9 = d5.c.a("imageSource");
        f fVar2 = new f();
        fVar2.a(2);
        f13962c = a9.b(fVar2.b()).a();
        c.b a10 = d5.c.a("imageFormat");
        f fVar3 = new f();
        fVar3.a(3);
        f13963d = a10.b(fVar3.b()).a();
        c.b a11 = d5.c.a("imageByteSize");
        f fVar4 = new f();
        fVar4.a(4);
        f13964e = a11.b(fVar4.b()).a();
        c.b a12 = d5.c.a("imageWidth");
        f fVar5 = new f();
        fVar5.a(5);
        f13965f = a12.b(fVar5.b()).a();
        c.b a13 = d5.c.a("imageHeight");
        f fVar6 = new f();
        fVar6.a(6);
        f13966g = a13.b(fVar6.b()).a();
        c.b a14 = d5.c.a("rotationDegrees");
        f fVar7 = new f();
        fVar7.a(7);
        f13967h = a14.b(fVar7.b()).a();
    }

    private o3() {
    }

    @Override // d5.d
    public final /* bridge */ /* synthetic */ void a(Object obj, Object obj2) {
        z7 z7Var = (z7) obj;
        d5.e eVar = (d5.e) obj2;
        eVar.c(f13961b, z7Var.g());
        eVar.c(f13962c, z7Var.b());
        eVar.c(f13963d, z7Var.a());
        eVar.c(f13964e, z7Var.c());
        eVar.c(f13965f, z7Var.e());
        eVar.c(f13966g, z7Var.d());
        eVar.c(f13967h, z7Var.f());
    }
}
