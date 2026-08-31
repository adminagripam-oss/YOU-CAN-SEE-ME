package b0;

import android.os.SystemClock;
import b0.q0;
import y.s1;
/* loaded from: classes.dex */
public final class j0 implements s1.b {

    /* renamed from: a  reason: collision with root package name */
    private final int f4247a;

    /* renamed from: b  reason: collision with root package name */
    private final int f4248b;

    /* renamed from: c  reason: collision with root package name */
    private final long f4249c;

    /* renamed from: d  reason: collision with root package name */
    private final Throwable f4250d;

    public j0(long j8, int i8, Throwable th) {
        this.f4249c = SystemClock.elapsedRealtime() - j8;
        this.f4248b = i8;
        if (th instanceof q0.b) {
            this.f4247a = 2;
        } else if (th instanceof y.d1) {
            Throwable cause = th.getCause();
            th = cause != null ? cause : th;
            this.f4250d = th;
            if (th instanceof y.s) {
                this.f4247a = 2;
                return;
            } else if (th instanceof IllegalArgumentException) {
                this.f4247a = 1;
                return;
            } else {
                this.f4247a = 0;
                return;
            }
        } else {
            this.f4247a = 0;
        }
        this.f4250d = th;
    }

    @Override // y.s1.b
    public Throwable a() {
        return this.f4250d;
    }

    @Override // y.s1.b
    public int b() {
        return this.f4247a;
    }

    @Override // y.s1.b
    public long c() {
        return this.f4249c;
    }
}
