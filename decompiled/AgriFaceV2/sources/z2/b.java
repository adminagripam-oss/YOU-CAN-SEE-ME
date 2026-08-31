package z2;

import c3.b;
import u2.m;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final /* synthetic */ class b implements b.a {

    /* renamed from: a  reason: collision with root package name */
    private final c f13382a;

    /* renamed from: b  reason: collision with root package name */
    private final m f13383b;

    /* renamed from: c  reason: collision with root package name */
    private final u2.h f13384c;

    private b(c cVar, m mVar, u2.h hVar) {
        this.f13382a = cVar;
        this.f13383b = mVar;
        this.f13384c = hVar;
    }

    public static b.a b(c cVar, m mVar, u2.h hVar) {
        return new b(cVar, mVar, hVar);
    }

    @Override // c3.b.a
    public Object a() {
        return c.b(this.f13382a, this.f13383b, this.f13384c);
    }
}
