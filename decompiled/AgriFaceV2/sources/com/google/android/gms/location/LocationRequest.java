package com.google.android.gms.location;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.SystemClock;
import android.os.WorkSource;
import c4.t;
import c4.u;
import c4.w;
import com.google.android.gms.common.internal.ReflectedParcelable;
import k3.p;
import k3.q;
import q3.h;
import x3.f0;
import x3.m0;
/* loaded from: classes.dex */
public final class LocationRequest extends l3.a implements ReflectedParcelable {
    public static final Parcelable.Creator<LocationRequest> CREATOR = new b();

    /* renamed from: a  reason: collision with root package name */
    private int f5992a;

    /* renamed from: b  reason: collision with root package name */
    private long f5993b;

    /* renamed from: c  reason: collision with root package name */
    private long f5994c;

    /* renamed from: d  reason: collision with root package name */
    private long f5995d;

    /* renamed from: e  reason: collision with root package name */
    private long f5996e;

    /* renamed from: f  reason: collision with root package name */
    private int f5997f;

    /* renamed from: g  reason: collision with root package name */
    private float f5998g;

    /* renamed from: h  reason: collision with root package name */
    private boolean f5999h;

    /* renamed from: i  reason: collision with root package name */
    private long f6000i;

    /* renamed from: j  reason: collision with root package name */
    private final int f6001j;

    /* renamed from: k  reason: collision with root package name */
    private final int f6002k;

    /* renamed from: l  reason: collision with root package name */
    private final boolean f6003l;

    /* renamed from: m  reason: collision with root package name */
    private final WorkSource f6004m;

    /* renamed from: n  reason: collision with root package name */
    private final f0 f6005n;

    /* loaded from: classes.dex */
    public static final class a {

        /* renamed from: a  reason: collision with root package name */
        private int f6006a;

        /* renamed from: b  reason: collision with root package name */
        private long f6007b;

        /* renamed from: c  reason: collision with root package name */
        private long f6008c;

        /* renamed from: d  reason: collision with root package name */
        private long f6009d;

        /* renamed from: e  reason: collision with root package name */
        private long f6010e;

        /* renamed from: f  reason: collision with root package name */
        private int f6011f;

        /* renamed from: g  reason: collision with root package name */
        private float f6012g;

        /* renamed from: h  reason: collision with root package name */
        private boolean f6013h;

        /* renamed from: i  reason: collision with root package name */
        private long f6014i;

        /* renamed from: j  reason: collision with root package name */
        private int f6015j;

        /* renamed from: k  reason: collision with root package name */
        private int f6016k;

        /* renamed from: l  reason: collision with root package name */
        private boolean f6017l;

        /* renamed from: m  reason: collision with root package name */
        private WorkSource f6018m;

        /* renamed from: n  reason: collision with root package name */
        private f0 f6019n;

        public a(int i8, long j8) {
            this(j8);
            j(i8);
        }

        public LocationRequest a() {
            int i8 = this.f6006a;
            long j8 = this.f6007b;
            long j9 = this.f6008c;
            if (j9 == -1) {
                j9 = j8;
            } else if (i8 != 105) {
                j9 = Math.min(j9, j8);
            }
            long max = Math.max(this.f6009d, this.f6007b);
            long j10 = this.f6010e;
            int i9 = this.f6011f;
            float f8 = this.f6012g;
            boolean z7 = this.f6013h;
            long j11 = this.f6014i;
            return new LocationRequest(i8, j8, j9, max, Long.MAX_VALUE, j10, i9, f8, z7, j11 == -1 ? this.f6007b : j11, this.f6015j, this.f6016k, this.f6017l, new WorkSource(this.f6018m), this.f6019n);
        }

        public a b(long j8) {
            q.b(j8 > 0, "durationMillis must be greater than 0");
            this.f6010e = j8;
            return this;
        }

        public a c(int i8) {
            w.a(i8);
            this.f6015j = i8;
            return this;
        }

        public a d(long j8) {
            q.b(j8 >= 0, "intervalMillis must be greater than or equal to 0");
            this.f6007b = j8;
            return this;
        }

        public a e(long j8) {
            boolean z7 = true;
            if (j8 != -1 && j8 < 0) {
                z7 = false;
            }
            q.b(z7, "maxUpdateAgeMillis must be greater than or equal to 0, or IMPLICIT_MAX_UPDATE_AGE");
            this.f6014i = j8;
            return this;
        }

        public a f(long j8) {
            q.b(j8 >= 0, "maxUpdateDelayMillis must be greater than or equal to 0");
            this.f6009d = j8;
            return this;
        }

        public a g(int i8) {
            q.b(i8 > 0, "maxUpdates must be greater than 0");
            this.f6011f = i8;
            return this;
        }

        public a h(float f8) {
            q.b(f8 >= 0.0f, "minUpdateDistanceMeters must be greater than or equal to 0");
            this.f6012g = f8;
            return this;
        }

        public a i(long j8) {
            boolean z7 = true;
            if (j8 != -1 && j8 < 0) {
                z7 = false;
            }
            q.b(z7, "minUpdateIntervalMillis must be greater than or equal to 0, or IMPLICIT_MIN_UPDATE_INTERVAL");
            this.f6008c = j8;
            return this;
        }

        public a j(int i8) {
            t.a(i8);
            this.f6006a = i8;
            return this;
        }

        public a k(boolean z7) {
            this.f6013h = z7;
            return this;
        }

        public final a l(int i8) {
            u.a(i8);
            this.f6016k = i8;
            return this;
        }

        public final a m(boolean z7) {
            this.f6017l = z7;
            return this;
        }

        public final a n(WorkSource workSource) {
            this.f6018m = workSource;
            return this;
        }

        public a(long j8) {
            this.f6006a = 102;
            this.f6008c = -1L;
            this.f6009d = 0L;
            this.f6010e = Long.MAX_VALUE;
            this.f6011f = Integer.MAX_VALUE;
            this.f6012g = 0.0f;
            this.f6013h = true;
            this.f6014i = -1L;
            this.f6015j = 0;
            this.f6016k = 0;
            this.f6017l = false;
            this.f6018m = null;
            this.f6019n = null;
            d(j8);
        }

        public a(LocationRequest locationRequest) {
            this(locationRequest.j(), locationRequest.d());
            i(locationRequest.i());
            f(locationRequest.f());
            b(locationRequest.b());
            g(locationRequest.g());
            h(locationRequest.h());
            k(locationRequest.m());
            e(locationRequest.e());
            c(locationRequest.c());
            int r8 = locationRequest.r();
            u.a(r8);
            this.f6016k = r8;
            this.f6017l = locationRequest.s();
            this.f6018m = locationRequest.t();
            f0 u7 = locationRequest.u();
            boolean z7 = true;
            if (u7 != null && u7.a()) {
                z7 = false;
            }
            q.a(z7);
            this.f6019n = u7;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public LocationRequest(int i8, long j8, long j9, long j10, long j11, long j12, int i9, float f8, boolean z7, long j13, int i10, int i11, boolean z8, WorkSource workSource, f0 f0Var) {
        long j14;
        this.f5992a = i8;
        if (i8 == 105) {
            this.f5993b = Long.MAX_VALUE;
            j14 = j8;
        } else {
            j14 = j8;
            this.f5993b = j14;
        }
        this.f5994c = j9;
        this.f5995d = j10;
        this.f5996e = j11 == Long.MAX_VALUE ? j12 : Math.min(Math.max(1L, j11 - SystemClock.elapsedRealtime()), j12);
        this.f5997f = i9;
        this.f5998g = f8;
        this.f5999h = z7;
        this.f6000i = j13 != -1 ? j13 : j14;
        this.f6001j = i10;
        this.f6002k = i11;
        this.f6003l = z8;
        this.f6004m = workSource;
        this.f6005n = f0Var;
    }

    public static LocationRequest a() {
        return new LocationRequest(102, 3600000L, 600000L, 0L, Long.MAX_VALUE, Long.MAX_VALUE, Integer.MAX_VALUE, 0.0f, true, 3600000L, 0, 0, false, new WorkSource(), null);
    }

    private static String v(long j8) {
        return j8 == Long.MAX_VALUE ? "∞" : m0.b(j8);
    }

    public long b() {
        return this.f5996e;
    }

    public int c() {
        return this.f6001j;
    }

    public long d() {
        return this.f5993b;
    }

    public long e() {
        return this.f6000i;
    }

    public boolean equals(Object obj) {
        if (obj instanceof LocationRequest) {
            LocationRequest locationRequest = (LocationRequest) obj;
            if (this.f5992a == locationRequest.f5992a && ((l() || this.f5993b == locationRequest.f5993b) && this.f5994c == locationRequest.f5994c && k() == locationRequest.k() && ((!k() || this.f5995d == locationRequest.f5995d) && this.f5996e == locationRequest.f5996e && this.f5997f == locationRequest.f5997f && this.f5998g == locationRequest.f5998g && this.f5999h == locationRequest.f5999h && this.f6001j == locationRequest.f6001j && this.f6002k == locationRequest.f6002k && this.f6003l == locationRequest.f6003l && this.f6004m.equals(locationRequest.f6004m) && p.a(this.f6005n, locationRequest.f6005n)))) {
                return true;
            }
        }
        return false;
    }

    public long f() {
        return this.f5995d;
    }

    public int g() {
        return this.f5997f;
    }

    public float h() {
        return this.f5998g;
    }

    public int hashCode() {
        return p.b(Integer.valueOf(this.f5992a), Long.valueOf(this.f5993b), Long.valueOf(this.f5994c), this.f6004m);
    }

    public long i() {
        return this.f5994c;
    }

    public int j() {
        return this.f5992a;
    }

    public boolean k() {
        long j8 = this.f5995d;
        return j8 > 0 && (j8 >> 1) >= this.f5993b;
    }

    public boolean l() {
        return this.f5992a == 105;
    }

    public boolean m() {
        return this.f5999h;
    }

    public LocationRequest n(long j8) {
        q.c(j8 >= 0, "illegal fastest interval: %d", Long.valueOf(j8));
        this.f5994c = j8;
        return this;
    }

    public LocationRequest o(long j8) {
        q.b(j8 >= 0, "intervalMillis must be greater than or equal to 0");
        long j9 = this.f5994c;
        long j10 = this.f5993b;
        if (j9 == j10 / 6) {
            this.f5994c = j8 / 6;
        }
        if (this.f6000i == j10) {
            this.f6000i = j8;
        }
        this.f5993b = j8;
        return this;
    }

    public LocationRequest p(int i8) {
        t.a(i8);
        this.f5992a = i8;
        return this;
    }

    public LocationRequest q(float f8) {
        if (f8 >= 0.0f) {
            this.f5998g = f8;
            return this;
        }
        StringBuilder sb = new StringBuilder(String.valueOf(f8).length() + 22);
        sb.append("invalid displacement: ");
        sb.append(f8);
        throw new IllegalArgumentException(sb.toString());
    }

    public final int r() {
        return this.f6002k;
    }

    public final boolean s() {
        return this.f6003l;
    }

    public final WorkSource t() {
        return this.f6004m;
    }

    public String toString() {
        long j8;
        StringBuilder sb = new StringBuilder();
        sb.append("Request[");
        if (l()) {
            sb.append(t.b(this.f5992a));
            if (this.f5995d > 0) {
                sb.append("/");
                m0.c(this.f5995d, sb);
            }
        } else {
            sb.append("@");
            if (k()) {
                m0.c(this.f5993b, sb);
                sb.append("/");
                j8 = this.f5995d;
            } else {
                j8 = this.f5993b;
            }
            m0.c(j8, sb);
            sb.append(" ");
            sb.append(t.b(this.f5992a));
        }
        if (l() || this.f5994c != this.f5993b) {
            sb.append(", minUpdateInterval=");
            sb.append(v(this.f5994c));
        }
        if (this.f5998g > 0.0d) {
            sb.append(", minUpdateDistance=");
            sb.append(this.f5998g);
        }
        boolean l8 = l();
        long j9 = this.f6000i;
        if (!l8 ? j9 != this.f5993b : j9 != Long.MAX_VALUE) {
            sb.append(", maxUpdateAge=");
            sb.append(v(this.f6000i));
        }
        if (this.f5996e != Long.MAX_VALUE) {
            sb.append(", duration=");
            m0.c(this.f5996e, sb);
        }
        if (this.f5997f != Integer.MAX_VALUE) {
            sb.append(", maxUpdates=");
            sb.append(this.f5997f);
        }
        if (this.f6002k != 0) {
            sb.append(", ");
            sb.append(u.b(this.f6002k));
        }
        if (this.f6001j != 0) {
            sb.append(", ");
            sb.append(w.b(this.f6001j));
        }
        if (this.f5999h) {
            sb.append(", waitForAccurateLocation");
        }
        if (this.f6003l) {
            sb.append(", bypass");
        }
        if (!h.b(this.f6004m)) {
            sb.append(", ");
            sb.append(this.f6004m);
        }
        if (this.f6005n != null) {
            sb.append(", impersonation=");
            sb.append(this.f6005n);
        }
        sb.append(']');
        return sb.toString();
    }

    public final f0 u() {
        return this.f6005n;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i8) {
        int a8 = l3.c.a(parcel);
        l3.c.g(parcel, 1, j());
        l3.c.i(parcel, 2, d());
        l3.c.i(parcel, 3, i());
        l3.c.g(parcel, 6, g());
        l3.c.e(parcel, 7, h());
        l3.c.i(parcel, 8, f());
        l3.c.c(parcel, 9, m());
        l3.c.i(parcel, 10, b());
        l3.c.i(parcel, 11, e());
        l3.c.g(parcel, 12, c());
        l3.c.g(parcel, 13, this.f6002k);
        l3.c.c(parcel, 15, this.f6003l);
        l3.c.j(parcel, 16, this.f6004m, i8, false);
        l3.c.j(parcel, 17, this.f6005n, i8, false);
        l3.c.b(parcel, a8);
    }
}
