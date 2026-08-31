package e2;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class i extends h {

    /* renamed from: b  reason: collision with root package name */
    private final Object f6582b;

    /* renamed from: c  reason: collision with root package name */
    private final String f6583c;

    /* renamed from: d  reason: collision with root package name */
    private final j f6584d;

    /* renamed from: e  reason: collision with root package name */
    private final g f6585e;

    public i(Object obj, String str, j jVar, g gVar) {
        d7.k.e(obj, "value");
        d7.k.e(str, "tag");
        d7.k.e(jVar, "verificationMode");
        d7.k.e(gVar, "logger");
        this.f6582b = obj;
        this.f6583c = str;
        this.f6584d = jVar;
        this.f6585e = gVar;
    }

    @Override // e2.h
    public Object a() {
        return this.f6582b;
    }

    @Override // e2.h
    public h c(String str, c7.l lVar) {
        d7.k.e(str, "message");
        d7.k.e(lVar, "condition");
        return ((Boolean) lVar.g(this.f6582b)).booleanValue() ? this : new f(this.f6582b, this.f6583c, str, this.f6585e, this.f6584d);
    }
}
