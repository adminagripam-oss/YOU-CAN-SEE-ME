package b0;

import android.view.Surface;
import java.util.concurrent.Executor;
/* loaded from: classes.dex */
public interface q1 {

    /* loaded from: classes.dex */
    public interface a {
        void a(q1 q1Var);
    }

    int a();

    androidx.camera.core.n acquireLatestImage();

    void c();

    void close();

    void d(a aVar, Executor executor);

    int e();

    androidx.camera.core.n f();

    int getHeight();

    Surface getSurface();

    int getWidth();
}
