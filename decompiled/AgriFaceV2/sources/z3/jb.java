package z3;

import android.os.SystemClock;
import java.io.Closeable;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
/* loaded from: classes.dex */
public class jb implements Closeable {

    /* renamed from: l  reason: collision with root package name */
    private static final Map f13803l = new HashMap();

    /* renamed from: e  reason: collision with root package name */
    private final String f13804e;

    /* renamed from: f  reason: collision with root package name */
    private int f13805f;

    /* renamed from: g  reason: collision with root package name */
    private double f13806g;

    /* renamed from: h  reason: collision with root package name */
    private long f13807h;

    /* renamed from: i  reason: collision with root package name */
    private long f13808i;

    /* renamed from: j  reason: collision with root package name */
    private long f13809j;

    /* renamed from: k  reason: collision with root package name */
    private long f13810k;

    private jb(String str) {
        this.f13809j = 2147483647L;
        this.f13810k = -2147483648L;
        this.f13804e = str;
    }

    private final void b() {
        this.f13805f = 0;
        this.f13806g = 0.0d;
        this.f13807h = 0L;
        this.f13809j = 2147483647L;
        this.f13810k = -2147483648L;
    }

    public static jb g(String str) {
        hb hbVar;
        jc.a();
        if (!jc.b()) {
            hbVar = hb.f13730m;
            return hbVar;
        }
        Map map = f13803l;
        if (map.get("detectorTaskWithResource#run") == null) {
            map.put("detectorTaskWithResource#run", new jb("detectorTaskWithResource#run"));
        }
        return (jb) map.get("detectorTaskWithResource#run");
    }

    public jb c() {
        this.f13807h = SystemClock.elapsedRealtimeNanos() / 1000;
        return this;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        long j8 = this.f13807h;
        if (j8 == 0) {
            throw new IllegalStateException("Did you forget to call start()?");
        }
        f(j8);
    }

    public void d(long j8) {
        long elapsedRealtimeNanos = SystemClock.elapsedRealtimeNanos() / 1000;
        long j9 = this.f13808i;
        if (j9 != 0 && elapsedRealtimeNanos - j9 >= 1000000) {
            b();
        }
        this.f13808i = elapsedRealtimeNanos;
        this.f13805f++;
        this.f13806g += j8;
        this.f13809j = Math.min(this.f13809j, j8);
        this.f13810k = Math.max(this.f13810k, j8);
        if (this.f13805f % 50 == 0) {
            String.format(Locale.US, "[%s] cur=%dus, counts=%d, min=%dus, max=%dus, avg=%dus", this.f13804e, Long.valueOf(j8), Integer.valueOf(this.f13805f), Long.valueOf(this.f13809j), Long.valueOf(this.f13810k), Integer.valueOf((int) (this.f13806g / this.f13805f)));
            jc.a();
        }
        if (this.f13805f % 500 == 0) {
            b();
        }
    }

    public void f(long j8) {
        d((SystemClock.elapsedRealtimeNanos() / 1000) - j8);
    }
}
