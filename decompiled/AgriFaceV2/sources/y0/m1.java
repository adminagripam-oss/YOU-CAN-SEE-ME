package y0;

import android.os.SystemClock;
import java.util.concurrent.TimeUnit;
/* loaded from: classes.dex */
public class m1 implements n1 {
    @Override // y0.n1
    public long a() {
        return TimeUnit.NANOSECONDS.toMicros(System.nanoTime());
    }

    @Override // y0.n1
    public long b() {
        return TimeUnit.NANOSECONDS.toMicros(SystemClock.elapsedRealtimeNanos());
    }
}
