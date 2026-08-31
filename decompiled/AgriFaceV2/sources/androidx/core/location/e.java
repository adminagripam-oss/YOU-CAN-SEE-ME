package androidx.core.location;

import android.location.LocationRequest;
import android.os.Build;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
/* loaded from: classes.dex */
public final class e {

    /* renamed from: a  reason: collision with root package name */
    final int f3018a;

    /* renamed from: b  reason: collision with root package name */
    final long f3019b;

    /* renamed from: c  reason: collision with root package name */
    final long f3020c;

    /* renamed from: d  reason: collision with root package name */
    final long f3021d;

    /* renamed from: e  reason: collision with root package name */
    final int f3022e;

    /* renamed from: f  reason: collision with root package name */
    final float f3023f;

    /* renamed from: g  reason: collision with root package name */
    final long f3024g;

    /* loaded from: classes.dex */
    private static class a {

        /* renamed from: a  reason: collision with root package name */
        private static Class f3025a;

        /* renamed from: b  reason: collision with root package name */
        private static Method f3026b;

        /* renamed from: c  reason: collision with root package name */
        private static Method f3027c;

        /* renamed from: d  reason: collision with root package name */
        private static Method f3028d;

        /* renamed from: e  reason: collision with root package name */
        private static Method f3029e;

        /* renamed from: f  reason: collision with root package name */
        private static Method f3030f;

        public static Object a(e eVar, String str) {
            try {
                if (f3025a == null) {
                    f3025a = Class.forName("android.location.LocationRequest");
                }
                if (f3026b == null) {
                    Method declaredMethod = f3025a.getDeclaredMethod("createFromDeprecatedProvider", String.class, Long.TYPE, Float.TYPE, Boolean.TYPE);
                    f3026b = declaredMethod;
                    declaredMethod.setAccessible(true);
                }
                Object invoke = f3026b.invoke(null, str, Long.valueOf(eVar.b()), Float.valueOf(eVar.e()), Boolean.FALSE);
                if (invoke == null) {
                    return null;
                }
                if (f3027c == null) {
                    Method declaredMethod2 = f3025a.getDeclaredMethod("setQuality", Integer.TYPE);
                    f3027c = declaredMethod2;
                    declaredMethod2.setAccessible(true);
                }
                f3027c.invoke(invoke, Integer.valueOf(eVar.g()));
                if (f3028d == null) {
                    Method declaredMethod3 = f3025a.getDeclaredMethod("setFastestInterval", Long.TYPE);
                    f3028d = declaredMethod3;
                    declaredMethod3.setAccessible(true);
                }
                f3028d.invoke(invoke, Long.valueOf(eVar.f()));
                if (eVar.d() < Integer.MAX_VALUE) {
                    if (f3029e == null) {
                        Method declaredMethod4 = f3025a.getDeclaredMethod("setNumUpdates", Integer.TYPE);
                        f3029e = declaredMethod4;
                        declaredMethod4.setAccessible(true);
                    }
                    f3029e.invoke(invoke, Integer.valueOf(eVar.d()));
                }
                if (eVar.a() < Long.MAX_VALUE) {
                    if (f3030f == null) {
                        Method declaredMethod5 = f3025a.getDeclaredMethod("setExpireIn", Long.TYPE);
                        f3030f = declaredMethod5;
                        declaredMethod5.setAccessible(true);
                    }
                    f3030f.invoke(invoke, Long.valueOf(eVar.a()));
                }
                return invoke;
            } catch (ClassNotFoundException | IllegalAccessException | NoSuchMethodException | InvocationTargetException unused) {
                return null;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class b {
        public static LocationRequest a(e eVar) {
            return new LocationRequest.Builder(eVar.b()).setQuality(eVar.g()).setMinUpdateIntervalMillis(eVar.f()).setDurationMillis(eVar.a()).setMaxUpdates(eVar.d()).setMinUpdateDistanceMeters(eVar.e()).setMaxUpdateDelayMillis(eVar.c()).build();
        }
    }

    /* loaded from: classes.dex */
    public static final class c {

        /* renamed from: a  reason: collision with root package name */
        private long f3031a;

        /* renamed from: b  reason: collision with root package name */
        private int f3032b;

        /* renamed from: c  reason: collision with root package name */
        private long f3033c;

        /* renamed from: d  reason: collision with root package name */
        private int f3034d;

        /* renamed from: e  reason: collision with root package name */
        private long f3035e;

        /* renamed from: f  reason: collision with root package name */
        private float f3036f;

        /* renamed from: g  reason: collision with root package name */
        private long f3037g;

        public c(long j8) {
            b(j8);
            this.f3032b = 102;
            this.f3033c = Long.MAX_VALUE;
            this.f3034d = Integer.MAX_VALUE;
            this.f3035e = -1L;
            this.f3036f = 0.0f;
            this.f3037g = 0L;
        }

        public e a() {
            g1.e.m((this.f3031a == Long.MAX_VALUE && this.f3035e == -1) ? false : true, "passive location requests must have an explicit minimum update interval");
            long j8 = this.f3031a;
            return new e(j8, this.f3032b, this.f3033c, this.f3034d, Math.min(this.f3035e, j8), this.f3036f, this.f3037g);
        }

        public c b(long j8) {
            this.f3031a = g1.e.f(j8, 0L, Long.MAX_VALUE, "intervalMillis");
            return this;
        }

        public c c(float f8) {
            this.f3036f = f8;
            this.f3036f = g1.e.d(f8, 0.0f, Float.MAX_VALUE, "minUpdateDistanceMeters");
            return this;
        }

        public c d(long j8) {
            this.f3035e = g1.e.f(j8, 0L, Long.MAX_VALUE, "minUpdateIntervalMillis");
            return this;
        }

        public c e(int i8) {
            g1.e.c(i8 == 104 || i8 == 102 || i8 == 100, "quality must be a defined QUALITY constant, not %d", Integer.valueOf(i8));
            this.f3032b = i8;
            return this;
        }
    }

    e(long j8, int i8, long j9, int i9, long j10, float f8, long j11) {
        this.f3019b = j8;
        this.f3018a = i8;
        this.f3020c = j10;
        this.f3021d = j9;
        this.f3022e = i9;
        this.f3023f = f8;
        this.f3024g = j11;
    }

    public long a() {
        return this.f3021d;
    }

    public long b() {
        return this.f3019b;
    }

    public long c() {
        return this.f3024g;
    }

    public int d() {
        return this.f3022e;
    }

    public float e() {
        return this.f3023f;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof e) {
            e eVar = (e) obj;
            return this.f3018a == eVar.f3018a && this.f3019b == eVar.f3019b && this.f3020c == eVar.f3020c && this.f3021d == eVar.f3021d && this.f3022e == eVar.f3022e && Float.compare(eVar.f3023f, this.f3023f) == 0 && this.f3024g == eVar.f3024g;
        }
        return false;
    }

    public long f() {
        long j8 = this.f3020c;
        return j8 == -1 ? this.f3019b : j8;
    }

    public int g() {
        return this.f3018a;
    }

    public LocationRequest h() {
        return b.a(this);
    }

    public int hashCode() {
        long j8 = this.f3019b;
        long j9 = this.f3020c;
        return (((this.f3018a * 31) + ((int) (j8 ^ (j8 >>> 32)))) * 31) + ((int) (j9 ^ (j9 >>> 32)));
    }

    public LocationRequest i(String str) {
        return Build.VERSION.SDK_INT >= 31 ? h() : d.a(a.a(this, str));
    }

    /* JADX WARN: Removed duplicated region for block: B:18:0x0042  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x0053  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x007e  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x0093  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.String toString() {
        /*
            r5 = this;
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "Request["
            r0.append(r1)
            long r1 = r5.f3019b
            r3 = 9223372036854775807(0x7fffffffffffffff, double:NaN)
            int r1 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1))
            if (r1 == 0) goto L37
            java.lang.String r1 = "@"
            r0.append(r1)
            long r1 = r5.f3019b
            g1.g.b(r1, r0)
            int r1 = r5.f3018a
            r2 = 100
            if (r1 == r2) goto L34
            r2 = 102(0x66, float:1.43E-43)
            if (r1 == r2) goto L31
            r2 = 104(0x68, float:1.46E-43)
            if (r1 == r2) goto L2e
            goto L3c
        L2e:
            java.lang.String r1 = " LOW_POWER"
            goto L39
        L31:
            java.lang.String r1 = " BALANCED"
            goto L39
        L34:
            java.lang.String r1 = " HIGH_ACCURACY"
            goto L39
        L37:
            java.lang.String r1 = "PASSIVE"
        L39:
            r0.append(r1)
        L3c:
            long r1 = r5.f3021d
            int r1 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1))
            if (r1 == 0) goto L4c
            java.lang.String r1 = ", duration="
            r0.append(r1)
            long r1 = r5.f3021d
            g1.g.b(r1, r0)
        L4c:
            int r1 = r5.f3022e
            r2 = 2147483647(0x7fffffff, float:NaN)
            if (r1 == r2) goto L5d
            java.lang.String r1 = ", maxUpdates="
            r0.append(r1)
            int r1 = r5.f3022e
            r0.append(r1)
        L5d:
            long r1 = r5.f3020c
            r3 = -1
            int r3 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1))
            if (r3 == 0) goto L75
            long r3 = r5.f3019b
            int r1 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1))
            if (r1 >= 0) goto L75
            java.lang.String r1 = ", minUpdateInterval="
            r0.append(r1)
            long r1 = r5.f3020c
            g1.g.b(r1, r0)
        L75:
            float r1 = r5.f3023f
            double r1 = (double) r1
            r3 = 0
            int r1 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1))
            if (r1 <= 0) goto L88
            java.lang.String r1 = ", minUpdateDistance="
            r0.append(r1)
            float r1 = r5.f3023f
            r0.append(r1)
        L88:
            long r1 = r5.f3024g
            r3 = 2
            long r1 = r1 / r3
            long r3 = r5.f3019b
            int r1 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1))
            if (r1 <= 0) goto L9d
            java.lang.String r1 = ", maxUpdateDelay="
            r0.append(r1)
            long r1 = r5.f3024g
            g1.g.b(r1, r0)
        L9d:
            r1 = 93
            r0.append(r1)
            java.lang.String r0 = r0.toString()
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.core.location.e.toString():java.lang.String");
    }
}
