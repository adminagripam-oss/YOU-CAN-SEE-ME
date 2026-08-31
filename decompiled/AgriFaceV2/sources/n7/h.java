package n7;

import k7.e1;
import o7.h;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public abstract /* synthetic */ class h {
    private static final void a(t6.g gVar) {
        if (gVar.c(e1.f8727b) == null) {
            return;
        }
        throw new IllegalArgumentException(("Flow context cannot contain job in it. Had " + gVar).toString());
    }

    public static final c b(c cVar, t6.g gVar) {
        a(gVar);
        return d7.k.a(gVar, t6.h.f11916e) ? cVar : cVar instanceof o7.h ? h.a.a((o7.h) cVar, gVar, 0, null, 6, null) : new o7.g(cVar, gVar, 0, null, 12, null);
    }
}
