package a3;

import c3.b;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final /* synthetic */ class i implements b.a {

    /* renamed from: a  reason: collision with root package name */
    private final j f201a;

    /* renamed from: b  reason: collision with root package name */
    private final u2.m f202b;

    /* renamed from: c  reason: collision with root package name */
    private final int f203c;

    private i(j jVar, u2.m mVar, int i8) {
        this.f201a = jVar;
        this.f202b = mVar;
        this.f203c = i8;
    }

    public static b.a b(j jVar, u2.m mVar, int i8) {
        return new i(jVar, mVar, i8);
    }

    @Override // c3.b.a
    public Object a() {
        return j.d(this.f201a, this.f202b, this.f203c);
    }
}
