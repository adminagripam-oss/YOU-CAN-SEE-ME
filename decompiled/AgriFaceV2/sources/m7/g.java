package m7;
/* loaded from: classes.dex */
public abstract class g {
    public static final d a(int i8, a aVar, c7.l lVar) {
        d bVar;
        if (i8 != -2) {
            if (i8 == -1) {
                if (aVar == a.SUSPEND) {
                    return new o(1, a.DROP_OLDEST, lVar);
                }
                throw new IllegalArgumentException("CONFLATED capacity cannot be used with non-default onBufferOverflow".toString());
            } else if (i8 != 0) {
                return i8 != Integer.MAX_VALUE ? aVar == a.SUSPEND ? new b(i8, lVar) : new o(i8, aVar, lVar) : new b(Integer.MAX_VALUE, lVar);
            } else {
                bVar = aVar == a.SUSPEND ? new b(0, lVar) : new o(1, aVar, lVar);
            }
        } else {
            bVar = aVar == a.SUSPEND ? new b(d.f9680c.a(), lVar) : new o(1, aVar, lVar);
        }
        return bVar;
    }

    public static /* synthetic */ d b(int i8, a aVar, c7.l lVar, int i9, Object obj) {
        if ((i9 & 1) != 0) {
            i8 = 0;
        }
        if ((i9 & 2) != 0) {
            aVar = a.SUSPEND;
        }
        if ((i9 & 4) != 0) {
            lVar = null;
        }
        return a(i8, aVar, lVar);
    }
}
