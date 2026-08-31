package g0;

import y.j2;
/* loaded from: classes.dex */
public abstract class g implements j2 {
    public static j2 e(float f8, float f9, float f10, float f11) {
        return new b(f8, f9, f10, f11);
    }

    public static j2 f(j2 j2Var) {
        return new b(j2Var.b(), j2Var.a(), j2Var.d(), j2Var.c());
    }

    @Override // y.j2
    public abstract float a();

    @Override // y.j2
    public abstract float b();

    @Override // y.j2
    public abstract float c();

    @Override // y.j2
    public abstract float d();
}
