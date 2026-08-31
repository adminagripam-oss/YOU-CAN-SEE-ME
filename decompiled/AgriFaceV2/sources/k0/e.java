package k0;

import android.util.Size;
import b0.g3;
import b0.p1;
/* loaded from: classes.dex */
public abstract class e {
    public static void a(g3.a aVar, int i8) {
        Size S;
        p1 p1Var = (p1) aVar.d();
        int V = p1Var.V(-1);
        if (V == -1 || V != i8) {
            ((p1.a) aVar).a(i8);
        }
        if (V == -1 || i8 == -1 || V == i8) {
            return;
        }
        if (Math.abs(d0.c.b(i8) - d0.c.b(V)) % 180 != 90 || (S = p1Var.S(null)) == null) {
            return;
        }
        ((p1.a) aVar).b(new Size(S.getHeight(), S.getWidth()));
    }
}
