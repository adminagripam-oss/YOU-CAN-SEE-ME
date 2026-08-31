package a4;

import d5.c;
/* loaded from: classes.dex */
final class a6 implements d5.d {

    /* renamed from: a  reason: collision with root package name */
    static final a6 f246a = new a6();

    /* renamed from: b  reason: collision with root package name */
    private static final d5.c f247b;

    /* renamed from: c  reason: collision with root package name */
    private static final d5.c f248c;

    /* renamed from: d  reason: collision with root package name */
    private static final d5.c f249d;

    /* renamed from: e  reason: collision with root package name */
    private static final d5.c f250e;

    static {
        c.b a8 = d5.c.a("isChargingRequired");
        s1 s1Var = new s1();
        s1Var.a(1);
        f247b = a8.b(s1Var.b()).a();
        c.b a9 = d5.c.a("isWifiRequired");
        s1 s1Var2 = new s1();
        s1Var2.a(2);
        f248c = a9.b(s1Var2.b()).a();
        c.b a10 = d5.c.a("isDeviceIdleRequired");
        s1 s1Var3 = new s1();
        s1Var3.a(3);
        f249d = a10.b(s1Var3.b()).a();
        c.b a11 = d5.c.a("canDownloadInBackground");
        s1 s1Var4 = new s1();
        s1Var4.a(4);
        f250e = a11.b(s1Var4.b()).a();
    }

    private a6() {
    }

    @Override // d5.d
    public final /* bridge */ /* synthetic */ void a(Object obj, Object obj2) {
        androidx.appcompat.app.w.a(obj);
        d5.e eVar = (d5.e) obj2;
        throw null;
    }
}
