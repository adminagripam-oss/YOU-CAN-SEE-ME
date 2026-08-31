package z3;

import d5.c;
/* loaded from: classes.dex */
final class x3 implements d5.d {

    /* renamed from: a  reason: collision with root package name */
    static final x3 f14306a = new x3();

    /* renamed from: b  reason: collision with root package name */
    private static final d5.c f14307b;

    /* renamed from: c  reason: collision with root package name */
    private static final d5.c f14308c;

    /* renamed from: d  reason: collision with root package name */
    private static final d5.c f14309d;

    /* renamed from: e  reason: collision with root package name */
    private static final d5.c f14310e;

    static {
        c.b a8 = d5.c.a("isChargingRequired");
        f fVar = new f();
        fVar.a(1);
        f14307b = a8.b(fVar.b()).a();
        c.b a9 = d5.c.a("isWifiRequired");
        f fVar2 = new f();
        fVar2.a(2);
        f14308c = a9.b(fVar2.b()).a();
        c.b a10 = d5.c.a("isDeviceIdleRequired");
        f fVar3 = new f();
        fVar3.a(3);
        f14309d = a10.b(fVar3.b()).a();
        c.b a11 = d5.c.a("canDownloadInBackground");
        f fVar4 = new f();
        fVar4.a(4);
        f14310e = a11.b(fVar4.b()).a();
    }

    private x3() {
    }

    @Override // d5.d
    public final /* bridge */ /* synthetic */ void a(Object obj, Object obj2) {
        androidx.appcompat.app.w.a(obj);
        d5.e eVar = (d5.e) obj2;
        throw null;
    }
}
