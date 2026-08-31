package p4;

import java.security.GeneralSecurityException;
import java.security.InvalidAlgorithmParameterException;
import java.util.Objects;
/* loaded from: classes.dex */
public final class e extends h {

    /* renamed from: a  reason: collision with root package name */
    private final int f10102a;

    /* renamed from: b  reason: collision with root package name */
    private final c f10103b;

    /* loaded from: classes.dex */
    public static final class b {

        /* renamed from: a  reason: collision with root package name */
        private Integer f10104a;

        /* renamed from: b  reason: collision with root package name */
        private c f10105b;

        private b() {
            this.f10104a = null;
            this.f10105b = c.f10108d;
        }

        public e a() {
            Integer num = this.f10104a;
            if (num != null) {
                if (this.f10105b != null) {
                    return new e(num.intValue(), this.f10105b);
                }
                throw new GeneralSecurityException("Variant is not set");
            }
            throw new GeneralSecurityException("Key size is not set");
        }

        public b b(int i8) {
            if (i8 == 32 || i8 == 48 || i8 == 64) {
                this.f10104a = Integer.valueOf(i8);
                return this;
            }
            throw new InvalidAlgorithmParameterException(String.format("Invalid key size %d; only 32-byte, 48-byte and 64-byte AES-SIV keys are supported", Integer.valueOf(i8)));
        }

        public b c(c cVar) {
            this.f10105b = cVar;
            return this;
        }
    }

    /* loaded from: classes.dex */
    public static final class c {

        /* renamed from: b  reason: collision with root package name */
        public static final c f10106b = new c("TINK");

        /* renamed from: c  reason: collision with root package name */
        public static final c f10107c = new c("CRUNCHY");

        /* renamed from: d  reason: collision with root package name */
        public static final c f10108d = new c("NO_PREFIX");

        /* renamed from: a  reason: collision with root package name */
        private final String f10109a;

        private c(String str) {
            this.f10109a = str;
        }

        public String toString() {
            return this.f10109a;
        }
    }

    private e(int i8, c cVar) {
        this.f10102a = i8;
        this.f10103b = cVar;
    }

    public static b b() {
        return new b();
    }

    @Override // j4.w
    public boolean a() {
        return this.f10103b != c.f10108d;
    }

    public int c() {
        return this.f10102a;
    }

    public c d() {
        return this.f10103b;
    }

    public boolean equals(Object obj) {
        if (obj instanceof e) {
            e eVar = (e) obj;
            return eVar.c() == c() && eVar.d() == d();
        }
        return false;
    }

    public int hashCode() {
        return Objects.hash(e.class, Integer.valueOf(this.f10102a), this.f10103b);
    }

    public String toString() {
        return "AesSiv Parameters (variant: " + this.f10103b + ", " + this.f10102a + "-byte key)";
    }
}
