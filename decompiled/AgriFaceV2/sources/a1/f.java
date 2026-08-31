package a1;

import android.os.Build;
import androidx.camera.video.internal.compat.quirk.CameraUseInconsistentTimebaseQuirk;
import b0.z2;
import y.f1;
import y0.n1;
/* loaded from: classes.dex */
public class f {

    /* renamed from: a  reason: collision with root package name */
    private final n1 f166a;

    /* renamed from: b  reason: collision with root package name */
    private final z2 f167b;

    /* renamed from: c  reason: collision with root package name */
    private final CameraUseInconsistentTimebaseQuirk f168c;

    /* renamed from: d  reason: collision with root package name */
    private long f169d = -1;

    /* renamed from: e  reason: collision with root package name */
    private z2 f170e;

    /* loaded from: classes.dex */
    static /* synthetic */ class a {

        /* renamed from: a  reason: collision with root package name */
        static final /* synthetic */ int[] f171a;

        static {
            int[] iArr = new int[z2.values().length];
            f171a = iArr;
            try {
                iArr[z2.REALTIME.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f171a[z2.UPTIME.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    public f(n1 n1Var, z2 z2Var, CameraUseInconsistentTimebaseQuirk cameraUseInconsistentTimebaseQuirk) {
        this.f166a = n1Var;
        this.f167b = z2Var;
        this.f168c = cameraUseInconsistentTimebaseQuirk;
    }

    private long a() {
        long j8 = Long.MAX_VALUE;
        long j9 = 0;
        for (int i8 = 0; i8 < 3; i8++) {
            long a8 = this.f166a.a();
            long b8 = this.f166a.b();
            long a9 = this.f166a.a();
            long j10 = a9 - a8;
            if (i8 == 0 || j10 < j8) {
                j9 = b8 - ((a8 + a9) >> 1);
                j8 = j10;
            }
        }
        return Math.max(0L, j9);
    }

    private boolean c() {
        return this.f166a.b() - this.f166a.a() > 3000000;
    }

    private boolean d(long j8) {
        return Math.abs(j8 - this.f166a.b()) < Math.abs(j8 - this.f166a.a());
    }

    private z2 e(long j8) {
        boolean z7;
        String str;
        String str2;
        if (this.f168c != null) {
            f1.l("VideoTimebaseConverter", "CameraUseInconsistentTimebaseQuirk is enabled");
            z7 = false;
        } else if (!c()) {
            return this.f167b;
        } else {
            z7 = true;
        }
        z2 z2Var = d(j8) ? z2.REALTIME : z2.UPTIME;
        if (!z7 || z2Var == this.f167b) {
            f1.a("VideoTimebaseConverter", "Detect input timebase = " + z2Var);
        } else {
            int i8 = Build.VERSION.SDK_INT;
            if (i8 >= 31) {
                StringBuilder sb = new StringBuilder();
                sb.append(", SOC: ");
                str2 = Build.SOC_MODEL;
                sb.append(str2);
                str = sb.toString();
            } else {
                str = "";
            }
            f1.c("VideoTimebaseConverter", String.format("Detected camera timebase inconsistent. Please file an issue at https://issuetracker.google.com/issues/new?component=618491&template=1257717 with this error message [Manufacturer: %s, Model: %s, Hardware: %s, API Level: %d%s].\nCamera timebase is inconsistent. The timebase reported by the camera is %s, but the actual timebase contained in the frame is detected as %s.", Build.MANUFACTURER, Build.MODEL, Build.HARDWARE, Integer.valueOf(i8), str, this.f167b, z2Var));
        }
        return z2Var;
    }

    public long b(long j8) {
        if (this.f170e == null) {
            this.f170e = e(j8);
        }
        int i8 = a.f171a[this.f170e.ordinal()];
        if (i8 != 1) {
            if (i8 == 2) {
                return j8;
            }
            throw new AssertionError("Unknown timebase: " + this.f170e);
        }
        if (this.f169d == -1) {
            this.f169d = a();
            f1.a("VideoTimebaseConverter", "mUptimeToRealtimeOffsetUs = " + this.f169d);
        }
        return j8 - this.f169d;
    }
}
