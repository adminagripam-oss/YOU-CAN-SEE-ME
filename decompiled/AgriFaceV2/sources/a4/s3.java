package a4;

import d5.c;
/* loaded from: classes.dex */
final class s3 implements d5.d {

    /* renamed from: a  reason: collision with root package name */
    static final s3 f1046a = new s3();

    /* renamed from: b  reason: collision with root package name */
    private static final d5.c f1047b;

    /* renamed from: c  reason: collision with root package name */
    private static final d5.c f1048c;

    /* renamed from: d  reason: collision with root package name */
    private static final d5.c f1049d;

    static {
        c.b a8 = d5.c.a("logEventKey");
        s1 s1Var = new s1();
        s1Var.a(1);
        f1047b = a8.b(s1Var.b()).a();
        c.b a9 = d5.c.a("eventCount");
        s1 s1Var2 = new s1();
        s1Var2.a(2);
        f1048c = a9.b(s1Var2.b()).a();
        c.b a10 = d5.c.a("inferenceDurationStats");
        s1 s1Var3 = new s1();
        s1Var3.a(3);
        f1049d = a10.b(s1Var3.b()).a();
    }

    private s3() {
    }

    @Override // d5.d
    public final /* bridge */ /* synthetic */ void a(Object obj, Object obj2) {
        s2 s2Var = (s2) obj;
        d5.e eVar = (d5.e) obj2;
        eVar.c(f1047b, s2Var.a());
        eVar.c(f1048c, s2Var.c());
        eVar.c(f1049d, s2Var.b());
    }
}
