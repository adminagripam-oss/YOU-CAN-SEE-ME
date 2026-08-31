package r7;

import java.util.concurrent.TimeUnit;
import p7.i0;
import p7.k0;
/* loaded from: classes.dex */
public abstract class l {

    /* renamed from: a  reason: collision with root package name */
    public static final String f11414a = i0.e("kotlinx.coroutines.scheduler.default.name", "DefaultDispatcher");

    /* renamed from: b  reason: collision with root package name */
    public static final long f11415b;

    /* renamed from: c  reason: collision with root package name */
    public static final int f11416c;

    /* renamed from: d  reason: collision with root package name */
    public static final int f11417d;

    /* renamed from: e  reason: collision with root package name */
    public static final long f11418e;

    /* renamed from: f  reason: collision with root package name */
    public static g f11419f;

    /* renamed from: g  reason: collision with root package name */
    public static final i f11420g;

    /* renamed from: h  reason: collision with root package name */
    public static final i f11421h;

    static {
        long f8;
        int a8;
        int e8;
        int e9;
        long f9;
        f8 = k0.f("kotlinx.coroutines.scheduler.resolution.ns", 100000L, 0L, 0L, 12, null);
        f11415b = f8;
        a8 = g7.f.a(i0.a(), 2);
        e8 = k0.e("kotlinx.coroutines.scheduler.core.pool.size", a8, 1, 0, 8, null);
        f11416c = e8;
        e9 = k0.e("kotlinx.coroutines.scheduler.max.pool.size", 2097150, 0, 2097150, 4, null);
        f11417d = e9;
        TimeUnit timeUnit = TimeUnit.SECONDS;
        f9 = k0.f("kotlinx.coroutines.scheduler.keep.alive.sec", 60L, 0L, 0L, 12, null);
        f11418e = timeUnit.toNanos(f9);
        f11419f = e.f11404a;
        f11420g = new j(0);
        f11421h = new j(1);
    }
}
