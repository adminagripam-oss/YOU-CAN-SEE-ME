package u2;
/* loaded from: classes.dex */
final class p implements s2.f {

    /* renamed from: a  reason: collision with root package name */
    private final m f12083a;

    /* renamed from: b  reason: collision with root package name */
    private final String f12084b;

    /* renamed from: c  reason: collision with root package name */
    private final s2.b f12085c;

    /* renamed from: d  reason: collision with root package name */
    private final s2.e f12086d;

    /* renamed from: e  reason: collision with root package name */
    private final q f12087e;

    /* JADX INFO: Access modifiers changed from: package-private */
    public p(m mVar, String str, s2.b bVar, s2.e eVar, q qVar) {
        this.f12083a = mVar;
        this.f12084b = str;
        this.f12085c = bVar;
        this.f12086d = eVar;
        this.f12087e = qVar;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void b(Exception exc) {
    }

    @Override // s2.f
    public void a(s2.c cVar) {
        c(cVar, o.b());
    }

    public void c(s2.c cVar, s2.h hVar) {
        this.f12087e.a(l.a().e(this.f12083a).c(cVar).f(this.f12084b).d(this.f12086d).b(this.f12085c).a(), hVar);
    }
}
