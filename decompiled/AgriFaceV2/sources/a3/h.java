package a3;

import c3.b;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final /* synthetic */ class h implements b.a {

    /* renamed from: a  reason: collision with root package name */
    private final b3.c f200a;

    private h(b3.c cVar) {
        this.f200a = cVar;
    }

    public static b.a b(b3.c cVar) {
        return new h(cVar);
    }

    @Override // c3.b.a
    public Object a() {
        return Integer.valueOf(this.f200a.h());
    }
}
