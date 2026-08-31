package p0;

import b0.f2;
import b0.g3;
import b0.p1;
import b0.u0;
import java.util.List;
/* loaded from: classes.dex */
public class j implements g3, p1, g0.n {
    static final u0.a J = u0.a.a("camerax.core.streamSharing.captureTypes", List.class);
    private final f2 I;

    /* JADX INFO: Access modifiers changed from: package-private */
    public j(f2 f2Var) {
        this.I = f2Var;
    }

    public List Z() {
        return (List) b(J);
    }

    @Override // b0.m2
    public u0 z() {
        return this.I;
    }
}
