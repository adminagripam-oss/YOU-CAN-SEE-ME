package j4;

import y4.c0;
/* loaded from: classes.dex */
public final class p {

    /* renamed from: a  reason: collision with root package name */
    private final c0.b f8277a;

    private p(c0.b bVar) {
        this.f8277a = bVar;
    }

    public static p b(m mVar) {
        return new p((c0.b) mVar.u().T());
    }

    public synchronized m a() {
        return m.o((c0) this.f8277a.h());
    }
}
