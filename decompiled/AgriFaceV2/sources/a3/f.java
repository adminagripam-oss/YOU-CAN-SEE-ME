package a3;

import c3.b;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final /* synthetic */ class f implements b.a {

    /* renamed from: a  reason: collision with root package name */
    private final j f193a;

    /* renamed from: b  reason: collision with root package name */
    private final u2.m f194b;

    private f(j jVar, u2.m mVar) {
        this.f193a = jVar;
        this.f194b = mVar;
    }

    public static b.a b(j jVar, u2.m mVar) {
        return new f(jVar, mVar);
    }

    @Override // c3.b.a
    public Object a() {
        Iterable L;
        L = this.f193a.f206c.L(this.f194b);
        return L;
    }
}
