package s0;

import b0.f2;
import b0.g3;
import b0.p1;
import b0.u0;
import g0.n;
import g1.e;
import java.util.Objects;
import r0.b2;
/* loaded from: classes.dex */
public final class a implements g3, p1, n {
    public static final u0.a J = u0.a.a("camerax.video.VideoCapture.videoOutput", b2.class);
    public static final u0.a K = u0.a.a("camerax.video.VideoCapture.videoEncoderInfoFinder", m.a.class);
    public static final u0.a L = u0.a.a("camerax.video.VideoCapture.forceEnableSurfaceProcessing", Boolean.class);
    private final f2 I;

    public a(f2 f2Var) {
        e.a(f2Var.a(J));
        this.I = f2Var;
    }

    @Override // b0.o1
    public int B() {
        return 34;
    }

    public m.a Z() {
        m.a aVar = (m.a) b(K);
        Objects.requireNonNull(aVar);
        return aVar;
    }

    public b2 a0() {
        b2 b2Var = (b2) b(J);
        Objects.requireNonNull(b2Var);
        return b2Var;
    }

    public boolean b0() {
        Boolean bool = (Boolean) f(L, Boolean.FALSE);
        Objects.requireNonNull(bool);
        return bool.booleanValue();
    }

    @Override // b0.m2
    public u0 z() {
        return this.I;
    }
}
