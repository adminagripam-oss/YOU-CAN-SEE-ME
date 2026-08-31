package k4;

import java.security.GeneralSecurityException;
import java.security.InvalidAlgorithmParameterException;
import java.util.Objects;
/* loaded from: classes.dex */
public final class z extends k4.c {

    /* renamed from: a  reason: collision with root package name */
    private final int f8694a;

    /* renamed from: b  reason: collision with root package name */
    private final c f8695b;

    /* loaded from: classes.dex */
    public static final class b {

        /* renamed from: a  reason: collision with root package name */
        private Integer f8696a;

        /* renamed from: b  reason: collision with root package name */
        private c f8697b;

        private b() {
            this.f8696a = null;
            this.f8697b = c.f8700d;
        }

        public z a() {
            Integer num = this.f8696a;
            if (num != null) {
                if (this.f8697b != null) {
                    return new z(num.intValue(), this.f8697b);
                }
                throw new GeneralSecurityException("Variant is not set");
            }
            throw new GeneralSecurityException("Key size is not set");
        }

        public b b(int i8) {
            if (i8 == 16 || i8 == 32) {
                this.f8696a = Integer.valueOf(i8);
                return this;
            }
            throw new InvalidAlgorithmParameterException(String.format("Invalid key size %d; only 16-byte and 32-byte AES keys are supported", Integer.valueOf(i8)));
        }

        public b c(c cVar) {
            this.f8697b = cVar;
            return this;
        }
    }

    /* loaded from: classes.dex */
    public static final class c {

        /* renamed from: b  reason: collision with root package name */
        public static final c f8698b = new c("TINK");

        /* renamed from: c  reason: collision with root package name */
        public static final c f8699c = new c("CRUNCHY");

        /* renamed from: d  reason: collision with root package name */
        public static final c f8700d = new c("NO_PREFIX");

        /* renamed from: a  reason: collision with root package name */
        private final String f8701a;

        private c(String str) {
            this.f8701a = str;
        }

        public String toString() {
            return this.f8701a;
        }
    }

    private z(int i8, c cVar) {
        this.f8694a = i8;
        this.f8695b = cVar;
    }

    public static b b() {
        return new b();
    }

    @Override // j4.w
    public boolean a() {
        return this.f8695b != c.f8700d;
    }

    public int c() {
        return this.f8694a;
    }

    public c d() {
        return this.f8695b;
    }

    public boolean equals(Object obj) {
        if (obj instanceof z) {
            z zVar = (z) obj;
            return zVar.c() == c() && zVar.d() == d();
        }
        return false;
    }

    public int hashCode() {
        return Objects.hash(z.class, Integer.valueOf(this.f8694a), this.f8695b);
    }

    public String toString() {
        return "AesGcmSiv Parameters (variant: " + this.f8695b + ", " + this.f8694a + "-byte key)";
    }
}
