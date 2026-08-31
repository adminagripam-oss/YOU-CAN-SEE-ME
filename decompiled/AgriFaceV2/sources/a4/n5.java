package a4;

import d5.c;
/* loaded from: classes.dex */
final class n5 implements d5.d {

    /* renamed from: a  reason: collision with root package name */
    static final n5 f855a = new n5();

    /* renamed from: b  reason: collision with root package name */
    private static final d5.c f856b;

    /* renamed from: c  reason: collision with root package name */
    private static final d5.c f857c;

    /* renamed from: d  reason: collision with root package name */
    private static final d5.c f858d;

    /* renamed from: e  reason: collision with root package name */
    private static final d5.c f859e;

    static {
        c.b a8 = d5.c.a("imageFormat");
        s1 s1Var = new s1();
        s1Var.a(1);
        f856b = a8.b(s1Var.b()).a();
        c.b a9 = d5.c.a("originalImageSize");
        s1 s1Var2 = new s1();
        s1Var2.a(2);
        f857c = a9.b(s1Var2.b()).a();
        c.b a10 = d5.c.a("compressedImageSize");
        s1 s1Var3 = new s1();
        s1Var3.a(3);
        f858d = a10.b(s1Var3.b()).a();
        c.b a11 = d5.c.a("isOdmlImage");
        s1 s1Var4 = new s1();
        s1Var4.a(4);
        f859e = a11.b(s1Var4.b()).a();
    }

    private n5() {
    }

    @Override // d5.d
    public final /* bridge */ /* synthetic */ void a(Object obj, Object obj2) {
        r9 r9Var = (r9) obj;
        d5.e eVar = (d5.e) obj2;
        eVar.c(f856b, r9Var.a());
        eVar.c(f857c, r9Var.b());
        eVar.c(f858d, null);
        eVar.c(f859e, null);
    }
}
