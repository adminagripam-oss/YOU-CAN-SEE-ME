package y0;

import android.view.Surface;
import java.util.concurrent.Executor;
/* loaded from: classes.dex */
public interface l {

    /* loaded from: classes.dex */
    public interface a extends b, t0.c {
    }

    /* loaded from: classes.dex */
    public interface b {
    }

    /* loaded from: classes.dex */
    public interface c extends b {

        /* loaded from: classes.dex */
        public interface a {
            void a(Surface surface);
        }

        void e(Executor executor, a aVar);
    }

    void a();

    void b(long j8);

    b c();

    i4.d d();

    void e(m mVar, Executor executor);

    void f();

    int g();

    e1 h();

    void release();

    void start();
}
