package r;
/* loaded from: classes.dex */
public abstract class n2 {
    public static y.s a(s.g gVar) {
        int d8 = gVar.d();
        int i8 = 1;
        if (d8 != 1) {
            i8 = 2;
            if (d8 != 2) {
                i8 = 3;
                if (d8 != 3) {
                    i8 = 4;
                    if (d8 != 4) {
                        i8 = 5;
                        if (d8 != 5) {
                            i8 = d8 != 10001 ? 0 : 6;
                        }
                    }
                }
            }
        }
        return new y.s(i8, gVar);
    }
}
