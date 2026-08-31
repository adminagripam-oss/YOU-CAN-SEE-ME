package p5;

import android.os.SystemClock;
import java.util.LinkedList;
import java.util.concurrent.TimeUnit;
import k3.j;
import k3.q;
/* loaded from: classes.dex */
public class a {

    /* renamed from: c  reason: collision with root package name */
    private static final j f10116c = new j("StreamingFormatChecker", "");

    /* renamed from: a  reason: collision with root package name */
    private final LinkedList f10117a = new LinkedList();

    /* renamed from: b  reason: collision with root package name */
    private long f10118b = -1;

    public void a(o5.a aVar) {
        if (aVar.h() != -1) {
            return;
        }
        long elapsedRealtime = SystemClock.elapsedRealtime();
        this.f10117a.add(Long.valueOf(elapsedRealtime));
        if (this.f10117a.size() > 5) {
            this.f10117a.removeFirst();
        }
        if (this.f10117a.size() != 5 || elapsedRealtime - ((Long) q.g((Long) this.f10117a.peekFirst())).longValue() >= 5000) {
            return;
        }
        long j8 = this.f10118b;
        if (j8 == -1 || elapsedRealtime - j8 >= TimeUnit.SECONDS.toMillis(5L)) {
            this.f10118b = elapsedRealtime;
            f10116c.f("StreamingFormatChecker", "ML Kit has detected that you seem to pass camera frames to the detector as a Bitmap object. This is inefficient. Please use YUV_420_888 format for camera2 API or NV21 format for (legacy) camera API and directly pass down the byte array to ML Kit.");
        }
    }
}
