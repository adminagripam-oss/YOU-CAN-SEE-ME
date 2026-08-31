package com.google.crypto.tink.shaded.protobuf;

import java.lang.reflect.Field;
import java.nio.Buffer;
import java.nio.ByteOrder;
import java.security.AccessController;
import java.security.PrivilegedExceptionAction;
import java.util.logging.Level;
import java.util.logging.Logger;
import sun.misc.Unsafe;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public abstract class m1 {

    /* renamed from: a  reason: collision with root package name */
    private static final Unsafe f6139a = D();

    /* renamed from: b  reason: collision with root package name */
    private static final Class f6140b = com.google.crypto.tink.shaded.protobuf.d.b();

    /* renamed from: c  reason: collision with root package name */
    private static final boolean f6141c = o(Long.TYPE);

    /* renamed from: d  reason: collision with root package name */
    private static final boolean f6142d = o(Integer.TYPE);

    /* renamed from: e  reason: collision with root package name */
    private static final e f6143e = B();

    /* renamed from: f  reason: collision with root package name */
    private static final boolean f6144f = T();

    /* renamed from: g  reason: collision with root package name */
    private static final boolean f6145g = S();

    /* renamed from: h  reason: collision with root package name */
    static final long f6146h;

    /* renamed from: i  reason: collision with root package name */
    private static final long f6147i;

    /* renamed from: j  reason: collision with root package name */
    private static final long f6148j;

    /* renamed from: k  reason: collision with root package name */
    private static final long f6149k;

    /* renamed from: l  reason: collision with root package name */
    private static final long f6150l;

    /* renamed from: m  reason: collision with root package name */
    private static final long f6151m;

    /* renamed from: n  reason: collision with root package name */
    private static final long f6152n;

    /* renamed from: o  reason: collision with root package name */
    private static final long f6153o;

    /* renamed from: p  reason: collision with root package name */
    private static final long f6154p;

    /* renamed from: q  reason: collision with root package name */
    private static final long f6155q;

    /* renamed from: r  reason: collision with root package name */
    private static final long f6156r;

    /* renamed from: s  reason: collision with root package name */
    private static final long f6157s;

    /* renamed from: t  reason: collision with root package name */
    private static final long f6158t;

    /* renamed from: u  reason: collision with root package name */
    private static final long f6159u;

    /* renamed from: v  reason: collision with root package name */
    private static final int f6160v;

    /* renamed from: w  reason: collision with root package name */
    static final boolean f6161w;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class a implements PrivilegedExceptionAction {
        a() {
        }

        @Override // java.security.PrivilegedExceptionAction
        /* renamed from: a */
        public Unsafe run() {
            Field[] declaredFields;
            for (Field field : Unsafe.class.getDeclaredFields()) {
                field.setAccessible(true);
                Object obj = field.get(null);
                if (Unsafe.class.isInstance(obj)) {
                    return (Unsafe) Unsafe.class.cast(obj);
                }
            }
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static final class b extends e {
        b(Unsafe unsafe) {
            super(unsafe);
        }

        @Override // com.google.crypto.tink.shaded.protobuf.m1.e
        public boolean c(Object obj, long j8) {
            return m1.f6161w ? m1.s(obj, j8) : m1.t(obj, j8);
        }

        @Override // com.google.crypto.tink.shaded.protobuf.m1.e
        public byte d(Object obj, long j8) {
            return m1.f6161w ? m1.v(obj, j8) : m1.w(obj, j8);
        }

        @Override // com.google.crypto.tink.shaded.protobuf.m1.e
        public double e(Object obj, long j8) {
            return Double.longBitsToDouble(h(obj, j8));
        }

        @Override // com.google.crypto.tink.shaded.protobuf.m1.e
        public float f(Object obj, long j8) {
            return Float.intBitsToFloat(g(obj, j8));
        }

        @Override // com.google.crypto.tink.shaded.protobuf.m1.e
        public void k(Object obj, long j8, boolean z7) {
            if (m1.f6161w) {
                m1.I(obj, j8, z7);
            } else {
                m1.J(obj, j8, z7);
            }
        }

        @Override // com.google.crypto.tink.shaded.protobuf.m1.e
        public void l(Object obj, long j8, byte b8) {
            if (m1.f6161w) {
                m1.L(obj, j8, b8);
            } else {
                m1.M(obj, j8, b8);
            }
        }

        @Override // com.google.crypto.tink.shaded.protobuf.m1.e
        public void m(Object obj, long j8, double d8) {
            p(obj, j8, Double.doubleToLongBits(d8));
        }

        @Override // com.google.crypto.tink.shaded.protobuf.m1.e
        public void n(Object obj, long j8, float f8) {
            o(obj, j8, Float.floatToIntBits(f8));
        }

        @Override // com.google.crypto.tink.shaded.protobuf.m1.e
        public boolean s() {
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static final class c extends e {
        c(Unsafe unsafe) {
            super(unsafe);
        }

        @Override // com.google.crypto.tink.shaded.protobuf.m1.e
        public boolean c(Object obj, long j8) {
            return m1.f6161w ? m1.s(obj, j8) : m1.t(obj, j8);
        }

        @Override // com.google.crypto.tink.shaded.protobuf.m1.e
        public byte d(Object obj, long j8) {
            return m1.f6161w ? m1.v(obj, j8) : m1.w(obj, j8);
        }

        @Override // com.google.crypto.tink.shaded.protobuf.m1.e
        public double e(Object obj, long j8) {
            return Double.longBitsToDouble(h(obj, j8));
        }

        @Override // com.google.crypto.tink.shaded.protobuf.m1.e
        public float f(Object obj, long j8) {
            return Float.intBitsToFloat(g(obj, j8));
        }

        @Override // com.google.crypto.tink.shaded.protobuf.m1.e
        public void k(Object obj, long j8, boolean z7) {
            if (m1.f6161w) {
                m1.I(obj, j8, z7);
            } else {
                m1.J(obj, j8, z7);
            }
        }

        @Override // com.google.crypto.tink.shaded.protobuf.m1.e
        public void l(Object obj, long j8, byte b8) {
            if (m1.f6161w) {
                m1.L(obj, j8, b8);
            } else {
                m1.M(obj, j8, b8);
            }
        }

        @Override // com.google.crypto.tink.shaded.protobuf.m1.e
        public void m(Object obj, long j8, double d8) {
            p(obj, j8, Double.doubleToLongBits(d8));
        }

        @Override // com.google.crypto.tink.shaded.protobuf.m1.e
        public void n(Object obj, long j8, float f8) {
            o(obj, j8, Float.floatToIntBits(f8));
        }

        @Override // com.google.crypto.tink.shaded.protobuf.m1.e
        public boolean s() {
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static final class d extends e {
        d(Unsafe unsafe) {
            super(unsafe);
        }

        @Override // com.google.crypto.tink.shaded.protobuf.m1.e
        public boolean c(Object obj, long j8) {
            return this.f6162a.getBoolean(obj, j8);
        }

        @Override // com.google.crypto.tink.shaded.protobuf.m1.e
        public byte d(Object obj, long j8) {
            return this.f6162a.getByte(obj, j8);
        }

        @Override // com.google.crypto.tink.shaded.protobuf.m1.e
        public double e(Object obj, long j8) {
            return this.f6162a.getDouble(obj, j8);
        }

        @Override // com.google.crypto.tink.shaded.protobuf.m1.e
        public float f(Object obj, long j8) {
            return this.f6162a.getFloat(obj, j8);
        }

        @Override // com.google.crypto.tink.shaded.protobuf.m1.e
        public void k(Object obj, long j8, boolean z7) {
            this.f6162a.putBoolean(obj, j8, z7);
        }

        @Override // com.google.crypto.tink.shaded.protobuf.m1.e
        public void l(Object obj, long j8, byte b8) {
            this.f6162a.putByte(obj, j8, b8);
        }

        @Override // com.google.crypto.tink.shaded.protobuf.m1.e
        public void m(Object obj, long j8, double d8) {
            this.f6162a.putDouble(obj, j8, d8);
        }

        @Override // com.google.crypto.tink.shaded.protobuf.m1.e
        public void n(Object obj, long j8, float f8) {
            this.f6162a.putFloat(obj, j8, f8);
        }

        @Override // com.google.crypto.tink.shaded.protobuf.m1.e
        public boolean r() {
            if (super.r()) {
                try {
                    Class<?> cls = this.f6162a.getClass();
                    Class<?> cls2 = Long.TYPE;
                    cls.getMethod("getByte", Object.class, cls2);
                    cls.getMethod("putByte", Object.class, cls2, Byte.TYPE);
                    cls.getMethod("getBoolean", Object.class, cls2);
                    cls.getMethod("putBoolean", Object.class, cls2, Boolean.TYPE);
                    cls.getMethod("getFloat", Object.class, cls2);
                    cls.getMethod("putFloat", Object.class, cls2, Float.TYPE);
                    cls.getMethod("getDouble", Object.class, cls2);
                    cls.getMethod("putDouble", Object.class, cls2, Double.TYPE);
                    return true;
                } catch (Throwable th) {
                    m1.G(th);
                    return false;
                }
            }
            return false;
        }

        @Override // com.google.crypto.tink.shaded.protobuf.m1.e
        public boolean s() {
            if (super.s()) {
                try {
                    Class<?> cls = this.f6162a.getClass();
                    Class<?> cls2 = Long.TYPE;
                    cls.getMethod("getByte", cls2);
                    cls.getMethod("putByte", cls2, Byte.TYPE);
                    cls.getMethod("getInt", cls2);
                    cls.getMethod("putInt", cls2, Integer.TYPE);
                    cls.getMethod("getLong", cls2);
                    cls.getMethod("putLong", cls2, cls2);
                    cls.getMethod("copyMemory", cls2, cls2, cls2);
                    cls.getMethod("copyMemory", Object.class, cls2, Object.class, cls2, cls2);
                    return true;
                } catch (Throwable th) {
                    m1.G(th);
                    return false;
                }
            }
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static abstract class e {

        /* renamed from: a  reason: collision with root package name */
        Unsafe f6162a;

        e(Unsafe unsafe) {
            this.f6162a = unsafe;
        }

        public final int a(Class cls) {
            return this.f6162a.arrayBaseOffset(cls);
        }

        public final int b(Class cls) {
            return this.f6162a.arrayIndexScale(cls);
        }

        public abstract boolean c(Object obj, long j8);

        public abstract byte d(Object obj, long j8);

        public abstract double e(Object obj, long j8);

        public abstract float f(Object obj, long j8);

        public final int g(Object obj, long j8) {
            return this.f6162a.getInt(obj, j8);
        }

        public final long h(Object obj, long j8) {
            return this.f6162a.getLong(obj, j8);
        }

        public final Object i(Object obj, long j8) {
            return this.f6162a.getObject(obj, j8);
        }

        public final long j(Field field) {
            return this.f6162a.objectFieldOffset(field);
        }

        public abstract void k(Object obj, long j8, boolean z7);

        public abstract void l(Object obj, long j8, byte b8);

        public abstract void m(Object obj, long j8, double d8);

        public abstract void n(Object obj, long j8, float f8);

        public final void o(Object obj, long j8, int i8) {
            this.f6162a.putInt(obj, j8, i8);
        }

        public final void p(Object obj, long j8, long j9) {
            this.f6162a.putLong(obj, j8, j9);
        }

        public final void q(Object obj, long j8, Object obj2) {
            this.f6162a.putObject(obj, j8, obj2);
        }

        public boolean r() {
            Unsafe unsafe = this.f6162a;
            if (unsafe == null) {
                return false;
            }
            try {
                Class<?> cls = unsafe.getClass();
                cls.getMethod("objectFieldOffset", Field.class);
                cls.getMethod("arrayBaseOffset", Class.class);
                cls.getMethod("arrayIndexScale", Class.class);
                Class<?> cls2 = Long.TYPE;
                cls.getMethod("getInt", Object.class, cls2);
                cls.getMethod("putInt", Object.class, cls2, Integer.TYPE);
                cls.getMethod("getLong", Object.class, cls2);
                cls.getMethod("putLong", Object.class, cls2, cls2);
                cls.getMethod("getObject", Object.class, cls2);
                cls.getMethod("putObject", Object.class, cls2, Object.class);
                return true;
            } catch (Throwable th) {
                m1.G(th);
                return false;
            }
        }

        public boolean s() {
            Unsafe unsafe = this.f6162a;
            if (unsafe == null) {
                return false;
            }
            try {
                Class<?> cls = unsafe.getClass();
                cls.getMethod("objectFieldOffset", Field.class);
                cls.getMethod("getLong", Object.class, Long.TYPE);
                return m1.b() != null;
            } catch (Throwable th) {
                m1.G(th);
                return false;
            }
        }
    }

    static {
        long l8 = l(byte[].class);
        f6146h = l8;
        f6147i = l(boolean[].class);
        f6148j = m(boolean[].class);
        f6149k = l(int[].class);
        f6150l = m(int[].class);
        f6151m = l(long[].class);
        f6152n = m(long[].class);
        f6153o = l(float[].class);
        f6154p = m(float[].class);
        f6155q = l(double[].class);
        f6156r = m(double[].class);
        f6157s = l(Object[].class);
        f6158t = m(Object[].class);
        f6159u = q(n());
        f6160v = (int) (l8 & 7);
        f6161w = ByteOrder.nativeOrder() == ByteOrder.BIG_ENDIAN;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static long A(Object obj, long j8) {
        return f6143e.h(obj, j8);
    }

    private static e B() {
        Unsafe unsafe = f6139a;
        if (unsafe == null) {
            return null;
        }
        if (com.google.crypto.tink.shaded.protobuf.d.c()) {
            if (f6141c) {
                return new c(unsafe);
            }
            if (f6142d) {
                return new b(unsafe);
            }
            return null;
        }
        return new d(unsafe);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Object C(Object obj, long j8) {
        return f6143e.i(obj, j8);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Unsafe D() {
        try {
            return (Unsafe) AccessController.doPrivileged(new a());
        } catch (Throwable unused) {
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean E() {
        return f6145g;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean F() {
        return f6144f;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void G(Throwable th) {
        Logger logger = Logger.getLogger(m1.class.getName());
        Level level = Level.WARNING;
        logger.log(level, "platform method missing - proto runtime falling back to safer methods: " + th);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void H(Object obj, long j8, boolean z7) {
        f6143e.k(obj, j8, z7);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void I(Object obj, long j8, boolean z7) {
        L(obj, j8, z7 ? (byte) 1 : (byte) 0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void J(Object obj, long j8, boolean z7) {
        M(obj, j8, z7 ? (byte) 1 : (byte) 0);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void K(byte[] bArr, long j8, byte b8) {
        f6143e.l(bArr, f6146h + j8, b8);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void L(Object obj, long j8, byte b8) {
        long j9 = (-4) & j8;
        int z7 = z(obj, j9);
        int i8 = ((~((int) j8)) & 3) << 3;
        P(obj, j9, ((255 & b8) << i8) | (z7 & (~(255 << i8))));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void M(Object obj, long j8, byte b8) {
        long j9 = (-4) & j8;
        int i8 = (((int) j8) & 3) << 3;
        P(obj, j9, ((255 & b8) << i8) | (z(obj, j9) & (~(255 << i8))));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void N(Object obj, long j8, double d8) {
        f6143e.m(obj, j8, d8);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void O(Object obj, long j8, float f8) {
        f6143e.n(obj, j8, f8);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void P(Object obj, long j8, int i8) {
        f6143e.o(obj, j8, i8);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void Q(Object obj, long j8, long j9) {
        f6143e.p(obj, j8, j9);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void R(Object obj, long j8, Object obj2) {
        f6143e.q(obj, j8, obj2);
    }

    private static boolean S() {
        e eVar = f6143e;
        if (eVar == null) {
            return false;
        }
        return eVar.r();
    }

    private static boolean T() {
        e eVar = f6143e;
        if (eVar == null) {
            return false;
        }
        return eVar.s();
    }

    static /* synthetic */ Field b() {
        return n();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Object k(Class cls) {
        try {
            return f6139a.allocateInstance(cls);
        } catch (InstantiationException e8) {
            throw new IllegalStateException(e8);
        }
    }

    private static int l(Class cls) {
        if (f6145g) {
            return f6143e.a(cls);
        }
        return -1;
    }

    private static int m(Class cls) {
        if (f6145g) {
            return f6143e.b(cls);
        }
        return -1;
    }

    private static Field n() {
        Field p8;
        if (!com.google.crypto.tink.shaded.protobuf.d.c() || (p8 = p(Buffer.class, "effectiveDirectAddress")) == null) {
            Field p9 = p(Buffer.class, "address");
            if (p9 == null || p9.getType() != Long.TYPE) {
                return null;
            }
            return p9;
        }
        return p8;
    }

    /* JADX WARN: Multi-variable type inference failed */
    static boolean o(Class cls) {
        if (com.google.crypto.tink.shaded.protobuf.d.c()) {
            try {
                Class cls2 = f6140b;
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
        return false;
    }

    private static Field p(Class cls, String str) {
        try {
            return cls.getDeclaredField(str);
        } catch (Throwable unused) {
            return null;
        }
    }

    private static long q(Field field) {
        e eVar;
        if (field == null || (eVar = f6143e) == null) {
            return -1L;
        }
        return eVar.j(field);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean r(Object obj, long j8) {
        return f6143e.c(obj, j8);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean s(Object obj, long j8) {
        return v(obj, j8) != 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean t(Object obj, long j8) {
        return w(obj, j8) != 0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static byte u(byte[] bArr, long j8) {
        return f6143e.d(bArr, f6146h + j8);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static byte v(Object obj, long j8) {
        return (byte) ((z(obj, (-4) & j8) >>> ((int) (((~j8) & 3) << 3))) & 255);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static byte w(Object obj, long j8) {
        return (byte) ((z(obj, (-4) & j8) >>> ((int) ((j8 & 3) << 3))) & 255);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static double x(Object obj, long j8) {
        return f6143e.e(obj, j8);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static float y(Object obj, long j8) {
        return f6143e.f(obj, j8);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int z(Object obj, long j8) {
        return f6143e.g(obj, j8);
    }
}
