package u0;

import android.media.AudioTimestamp;
import java.util.concurrent.TimeUnit;
/* loaded from: classes.dex */
public abstract class t {
    public static int a(int i8) {
        return i8 == 1 ? 16 : 12;
    }

    public static int b(int i8) {
        return i8 == 1 ? 16 : 12;
    }

    public static long c(int i8, long j8, AudioTimestamp audioTimestamp) {
        g1.e.b(((long) i8) > 0, "sampleRate must be greater than 0.");
        g1.e.b(j8 >= 0, "framePosition must be no less than 0.");
        long d8 = audioTimestamp.nanoTime + d(j8 - audioTimestamp.framePosition, i8);
        if (d8 < 0) {
            return 0L;
        }
        return d8;
    }

    public static long d(long j8, int i8) {
        long j9 = i8;
        g1.e.b(j9 > 0, "sampleRate must be greater than 0.");
        return (TimeUnit.SECONDS.toNanos(1L) * j8) / j9;
    }

    public static long e(long j8, int i8) {
        long j9 = i8;
        g1.e.b(j9 > 0, "bytesPerFrame must be greater than 0.");
        return j8 * j9;
    }

    public static int f(int i8, int i9) {
        boolean z7 = i9 > 0;
        g1.e.b(z7, "Invalid channel count: " + i9);
        if (i8 != 2) {
            if (i8 != 3) {
                if (i8 != 4) {
                    if (i8 == 21) {
                        return i9 * 3;
                    }
                    if (i8 != 22) {
                        throw new IllegalArgumentException("Invalid audio encoding: " + i8);
                    }
                }
                return i9 * 4;
            }
            return i9;
        }
        return i9 * 2;
    }

    public static long g(long j8, int i8) {
        long j9 = i8;
        g1.e.b(j9 > 0, "bytesPerFrame must be greater than 0.");
        return j8 / j9;
    }
}
