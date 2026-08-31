package y;

import android.graphics.Matrix;
import b0.y2;
import d0.i;
/* loaded from: classes.dex */
public abstract class c1 implements w0 {
    public static w0 d(y2 y2Var, long j8, int i8, Matrix matrix) {
        return new d(y2Var, j8, i8, matrix);
    }

    @Override // y.w0
    public void a(i.b bVar) {
        bVar.m(e());
    }

    @Override // y.w0
    public abstract y2 b();

    @Override // y.w0
    public abstract long c();

    public abstract int e();

    public abstract Matrix f();
}
