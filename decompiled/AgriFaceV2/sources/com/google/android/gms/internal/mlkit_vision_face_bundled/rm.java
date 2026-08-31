package com.google.android.gms.internal.mlkit_vision_face_bundled;

import java.lang.reflect.Field;
import java.nio.Buffer;
import java.security.AccessController;
import java.util.logging.Level;
import java.util.logging.Logger;
import sun.misc.Unsafe;
/* loaded from: classes.dex */
abstract class rm {

    /* renamed from: a  reason: collision with root package name */
    private static final Unsafe f5577a;

    /* renamed from: b  reason: collision with root package name */
    private static final Class f5578b;

    /* renamed from: c  reason: collision with root package name */
    private static final boolean f5579c;

    /* renamed from: d  reason: collision with root package name */
    private static final qm f5580d;

    /* renamed from: e  reason: collision with root package name */
    private static final boolean f5581e;

    /* renamed from: f  reason: collision with root package name */
    private static final boolean f5582f;

    /* renamed from: g  reason: collision with root package name */
    static final long f5583g;

    /* renamed from: h  reason: collision with root package name */
    static final boolean f5584h;

    /* JADX WARN: Removed duplicated region for block: B:22:0x006c  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x011e  */
    /* JADX WARN: Removed duplicated region for block: B:35:0x0130  */
    static {
        /*
            Method dump skipped, instructions count: 308
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.mlkit_vision_face_bundled.rm.<clinit>():void");
    }

    /* JADX WARN: Multi-variable type inference failed */
    static boolean A(Class cls) {
        int i8 = aj.f5130a;
        try {
            Class cls2 = f5578b;
            Class cls3 = Boolean.TYPE;
            cls2.getMethod("peekLong", cls, cls3);
            cls2.getMethod("pokeLong", cls, Long.TYPE, cls3);
            Class cls4 = Integer.TYPE;
            cls2.getMethod("pokeInt", cls, cls4, cls3);
            cls2.getMethod("peekInt", cls, cls3);
            cls2.getMethod("pokeByte", cls, Byte.TYPE);
            cls2.getMethod("peekByte", cls);
            cls2.getMethod("pokeByteArray", cls, byte[].class, cls4, cls4);
            cls2.getMethod("peekByteArray", cls, byte[].class, cls4, cls4);
            return true;
        } catch (Throwable unused) {
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean B(Object obj, long j8) {
        return f5580d.g(obj, j8);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean C() {
        return f5582f;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean D() {
        return f5581e;
    }

    private static int E(Class cls) {
        if (f5582f) {
            return f5580d.f5563a.arrayBaseOffset(cls);
        }
        return -1;
    }

    private static int a(Class cls) {
        if (f5582f) {
            return f5580d.f5563a.arrayIndexScale(cls);
        }
        return -1;
    }

    private static Field b() {
        int i8 = aj.f5130a;
        Field c8 = c(Buffer.class, "effectiveDirectAddress");
        if (c8 == null) {
            Field c9 = c(Buffer.class, "address");
            if (c9 == null || c9.getType() != Long.TYPE) {
                return null;
            }
            return c9;
        }
        return c8;
    }

    private static Field c(Class cls, String str) {
        try {
            return cls.getDeclaredField(str);
        } catch (Throwable unused) {
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void d(Object obj, long j8, byte b8) {
        qm qmVar = f5580d;
        long j9 = (-4) & j8;
        int i8 = qmVar.f5563a.getInt(obj, j9);
        int i9 = ((~((int) j8)) & 3) << 3;
        qmVar.f5563a.putInt(obj, j9, ((255 & b8) << i9) | (i8 & (~(255 << i9))));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void e(Object obj, long j8, byte b8) {
        qm qmVar = f5580d;
        long j9 = (-4) & j8;
        int i8 = (((int) j8) & 3) << 3;
        qmVar.f5563a.putInt(obj, j9, ((255 & b8) << i8) | (qmVar.f5563a.getInt(obj, j9) & (~(255 << i8))));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static double f(Object obj, long j8) {
        return f5580d.a(obj, j8);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static float g(Object obj, long j8) {
        return f5580d.b(obj, j8);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int h(Object obj, long j8) {
        return f5580d.f5563a.getInt(obj, j8);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static long i(Object obj, long j8) {
        return f5580d.f5563a.getLong(obj, j8);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Object j(Class cls) {
        try {
            return f5577a.allocateInstance(cls);
        } catch (InstantiationException e8) {
            throw new IllegalStateException(e8);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Object k(Object obj, long j8) {
        return f5580d.f5563a.getObject(obj, j8);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Unsafe l() {
        try {
            return (Unsafe) AccessController.doPrivileged(new mm());
        } catch (Throwable unused) {
            return null;
        }
    }

    static /* bridge */ /* synthetic */ void m(Throwable th) {
        Logger.getLogger(rm.class.getName()).logp(Level.WARNING, "com.google.protobuf.UnsafeUtil", "logMissingMethod", "platform method missing - proto runtime falling back to safer methods: ".concat(th.toString()));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void r(Object obj, long j8, boolean z7) {
        f5580d.c(obj, j8, z7);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void s(byte[] bArr, long j8, byte b8) {
        f5580d.d(bArr, f5583g + j8, b8);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void t(Object obj, long j8, double d8) {
        f5580d.e(obj, j8, d8);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void u(Object obj, long j8, float f8) {
        f5580d.f(obj, j8, f8);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void v(Object obj, long j8, int i8) {
        f5580d.f5563a.putInt(obj, j8, i8);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void w(Object obj, long j8, long j9) {
        f5580d.f5563a.putLong(obj, j8, j9);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void x(Object obj, long j8, Object obj2) {
        f5580d.f5563a.putObject(obj, j8, obj2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* bridge */ /* synthetic */ boolean y(Object obj, long j8) {
        return ((byte) ((f5580d.f5563a.getInt(obj, (-4) & j8) >>> ((int) (((~j8) & 3) << 3))) & 255)) != 0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* bridge */ /* synthetic */ boolean z(Object obj, long j8) {
        return ((byte) ((f5580d.f5563a.getInt(obj, (-4) & j8) >>> ((int) ((j8 & 3) << 3))) & 255)) != 0;
    }
}
