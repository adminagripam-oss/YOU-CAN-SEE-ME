package y;

import android.graphics.Rect;
import android.util.Size;
import android.view.Surface;
import java.io.Closeable;
import java.util.concurrent.Executor;
/* loaded from: classes.dex */
public interface v1 extends Closeable {

    /* loaded from: classes.dex */
    public static abstract class a {
        public static a f(Size size, Rect rect, b0.h0 h0Var, int i8, boolean z7) {
            return new f(size, rect, h0Var, i8, z7);
        }

        public abstract b0.h0 a();

        public abstract Rect b();

        public abstract Size c();

        public abstract boolean d();

        public abstract int e();
    }

    /* loaded from: classes.dex */
    public static abstract class b {
        public static b c(int i8, v1 v1Var) {
            return new g(i8, v1Var);
        }

        public abstract int a();

        public abstract v1 b();
    }

    Surface N(Executor executor, g1.a aVar);

    Size O();

    @Override // java.io.Closeable, java.lang.AutoCloseable
    void close();

    int e();

    void r(float[] fArr, float[] fArr2, boolean z7);

    void s(float[] fArr, float[] fArr2);
}
