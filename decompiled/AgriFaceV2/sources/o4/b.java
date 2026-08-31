package o4;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.logging.Logger;
/* loaded from: classes.dex */
public abstract class b {

    /* renamed from: a  reason: collision with root package name */
    private static final Logger f9846a = Logger.getLogger(b.class.getName());

    /* renamed from: b  reason: collision with root package name */
    private static final AtomicBoolean f9847b = new AtomicBoolean(false);

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    /* renamed from: o4.b$b  reason: collision with other inner class name */
    /* loaded from: classes.dex */
    public static abstract class EnumC0135b {

        /* renamed from: e  reason: collision with root package name */
        public static final EnumC0135b f9848e = new a("ALGORITHM_NOT_FIPS", 0);

        /* renamed from: f  reason: collision with root package name */
        public static final EnumC0135b f9849f = new C0136b("ALGORITHM_REQUIRES_BORINGCRYPTO", 1);

        /* renamed from: g  reason: collision with root package name */
        private static final /* synthetic */ EnumC0135b[] f9850g = d();

        /* renamed from: o4.b$b$a */
        /* loaded from: classes.dex */
        enum a extends EnumC0135b {
            a(String str, int i8) {
                super(str, i8);
            }

            @Override // o4.b.EnumC0135b
            public boolean e() {
                return !b.c();
            }
        }

        /* renamed from: o4.b$b$b  reason: collision with other inner class name */
        /* loaded from: classes.dex */
        enum C0136b extends EnumC0135b {
            C0136b(String str, int i8) {
                super(str, i8);
            }

            @Override // o4.b.EnumC0135b
            public boolean e() {
                return !b.c() || b.b();
            }
        }

        private EnumC0135b(String str, int i8) {
        }

        private static /* synthetic */ EnumC0135b[] d() {
            return new EnumC0135b[]{f9848e, f9849f};
        }

        public static EnumC0135b valueOf(String str) {
            return (EnumC0135b) Enum.valueOf(EnumC0135b.class, str);
        }

        public static EnumC0135b[] values() {
            return (EnumC0135b[]) f9850g.clone();
        }

        public abstract boolean e();
    }

    static Boolean a() {
        try {
            return (Boolean) Class.forName("org.conscrypt.Conscrypt").getMethod("isBoringSslFIPSBuild", new Class[0]).invoke(null, new Object[0]);
        } catch (Exception unused) {
            f9846a.info("Conscrypt is not available or does not support checking for FIPS build.");
            return Boolean.FALSE;
        }
    }

    public static boolean b() {
        return a().booleanValue();
    }

    public static boolean c() {
        return o4.a.a() || f9847b.get();
    }
}
