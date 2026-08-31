package u0;

import java.nio.ByteBuffer;
import java.util.concurrent.Executor;
/* loaded from: classes.dex */
public interface p {

    /* loaded from: classes.dex */
    public interface a {
        void b(boolean z7);
    }

    /* loaded from: classes.dex */
    public static class b extends Exception {
        public b(String str) {
            super(str);
        }

        public b(Throwable th) {
            super(th);
        }
    }

    /* loaded from: classes.dex */
    public static abstract class c {
        public static c c(int i8, long j8) {
            return new v(i8, j8);
        }

        public abstract int a();

        public abstract long b();
    }

    c a(ByteBuffer byteBuffer);

    void b(a aVar, Executor executor);

    void release();

    void start();

    void stop();
}
