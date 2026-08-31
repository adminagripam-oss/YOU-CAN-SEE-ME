package k7;
/* loaded from: classes.dex */
public abstract class o1 extends a0 {
    public abstract o1 S();

    /* JADX INFO: Access modifiers changed from: protected */
    public final String T() {
        o1 o1Var;
        o1 c8 = n0.c();
        if (this == c8) {
            return "Dispatchers.Main";
        }
        try {
            o1Var = c8.S();
        } catch (UnsupportedOperationException unused) {
            o1Var = null;
        }
        if (this == o1Var) {
            return "Dispatchers.Main.immediate";
        }
        return null;
    }
}
