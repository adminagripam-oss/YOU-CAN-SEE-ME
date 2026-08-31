package o7;
/* loaded from: classes.dex */
public interface h extends n7.c {

    /* loaded from: classes.dex */
    public static final class a {
        public static /* synthetic */ n7.c a(h hVar, t6.g gVar, int i8, m7.a aVar, int i9, Object obj) {
            if (obj == null) {
                if ((i9 & 1) != 0) {
                    gVar = t6.h.f11916e;
                }
                if ((i9 & 2) != 0) {
                    i8 = -3;
                }
                if ((i9 & 4) != 0) {
                    aVar = m7.a.SUSPEND;
                }
                return hVar.b(gVar, i8, aVar);
            }
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: fuse");
        }
    }

    n7.c b(t6.g gVar, int i8, m7.a aVar);
}
