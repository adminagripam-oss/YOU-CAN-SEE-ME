package k7;
/* loaded from: classes.dex */
public abstract class m {
    public static final k a(t6.d dVar) {
        if (dVar instanceof p7.j) {
            k l8 = ((p7.j) dVar).l();
            if (l8 != null) {
                if (!l8.I()) {
                    l8 = null;
                }
                if (l8 != null) {
                    return l8;
                }
            }
            return new k(dVar, 2);
        }
        return new k(dVar, 1);
    }
}
