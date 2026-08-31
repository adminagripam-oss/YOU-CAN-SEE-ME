package v4;

import j4.o;
import java.security.GeneralSecurityException;
import t4.c0;
import t4.y;
/* loaded from: classes.dex */
public abstract class p {

    /* loaded from: classes.dex */
    private static class b implements u4.g {

        /* renamed from: a  reason: collision with root package name */
        private final y f12387a;

        /* renamed from: b  reason: collision with root package name */
        private final u4.g f12388b;

        private b(y yVar, u4.g gVar) {
            this.f12387a = yVar;
            this.f12388b = gVar;
        }
    }

    public static u4.g a(j4.o oVar, c0.a aVar) {
        o.a c8 = oVar.c();
        if (c8 != null) {
            y.b bVar = new y.b();
            for (int i8 = 0; i8 < oVar.size(); i8++) {
                o.a b8 = oVar.b(i8);
                if (b8.b().equals(j4.j.f8247b)) {
                    bVar.b(b(b8.getKey()), (u4.g) aVar.a(b8));
                }
            }
            return new b(bVar.a(), (u4.g) aVar.a(c8));
        }
        throw new GeneralSecurityException("no primary in primitive set");
    }

    private static a5.a b(j4.h hVar) {
        if (hVar instanceof u4.p) {
            return ((u4.p) hVar).c();
        }
        if (hVar instanceof t4.i) {
            return ((t4.i) hVar).d();
        }
        throw new GeneralSecurityException("Cannot get output prefix for key of class " + hVar.getClass().getName() + " with parameters " + hVar.b());
    }
}
