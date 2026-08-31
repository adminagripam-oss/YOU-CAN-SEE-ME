package g0;

import b0.m2;
import b0.u0;
/* loaded from: classes.dex */
public interface m extends m2 {
    public static final u0.a F = u0.a.a("camerax.core.target.name", String.class);
    public static final u0.a G = u0.a.a("camerax.core.target.class", Class.class);

    default String U(String str) {
        return (String) f(F, str);
    }

    default String r() {
        return (String) b(F);
    }
}
