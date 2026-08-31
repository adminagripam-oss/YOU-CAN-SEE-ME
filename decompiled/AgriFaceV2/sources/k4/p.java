package k4;

import java.security.GeneralSecurityException;
import java.security.InvalidAlgorithmParameterException;
import java.util.Objects;
/* loaded from: classes.dex */
public final class p extends k4.c {

    /* renamed from: a  reason: collision with root package name */
    private final int f8607a;

    /* renamed from: b  reason: collision with root package name */
    private final int f8608b;

    /* renamed from: c  reason: collision with root package name */
    private final int f8609c;

    /* renamed from: d  reason: collision with root package name */
    private final c f8610d;

    /* loaded from: classes.dex */
    public static final class b {

        /* renamed from: a  reason: collision with root package name */
        private Integer f8611a;

        /* renamed from: b  reason: collision with root package name */
        private Integer f8612b;

        /* renamed from: c  reason: collision with root package name */
        private Integer f8613c;

        /* renamed from: d  reason: collision with root package name */
        private c f8614d;

        private b() {
            this.f8611a = null;
            this.f8612b = null;
            this.f8613c = null;
            this.f8614d = c.f8617d;
        }

        public p a() {
            Integer num = this.f8611a;
            if (num != null) {
                if (this.f8612b != null) {
                    if (this.f8614d != null) {
                        if (this.f8613c != null) {
                            return new p(num.intValue(), this.f8612b.intValue(), this.f8613c.intValue(), this.f8614d);
                        }
                        throw new GeneralSecurityException("Tag size is not set");
                    }
                    throw new GeneralSecurityException("Variant is not set");
                }
                throw new GeneralSecurityException("IV size is not set");
            }
            throw new GeneralSecurityException("Key size is not set");
        }

        public b b(int i8) {
            if (i8 == 12 || i8 == 16) {
                this.f8612b = Integer.valueOf(i8);
                return this;
            }
            throw new GeneralSecurityException(String.format("Invalid IV size in bytes %d; acceptable values have 12 or 16 bytes", Integer.valueOf(i8)));
        }

        public b c(int i8) {
            if (i8 == 16 || i8 == 24 || i8 == 32) {
                this.f8611a = Integer.valueOf(i8);
                return this;
            }
            throw new InvalidAlgorithmParameterException(String.format("Invalid key size %d; only 16-byte, 24-byte and 32-byte AES keys are supported", Integer.valueOf(i8)));
        }

        public b d(int i8) {
            if (i8 < 0 || i8 > 16) {
                throw new GeneralSecurityException(String.format("Invalid tag size in bytes %d; value must be at most 16 bytes", Integer.valueOf(i8)));
            }
            this.f8613c = Integer.valueOf(i8);
            return this;
        }

        public b e(c cVar) {
            this.f8614d = cVar;
            return this;
        }
    }

    /* loaded from: classes.dex */
    public static final class c {

        /* renamed from: b  reason: collision with root package name */
        public static final c f8615b = new c("TINK");

        /* renamed from: c  reason: collision with root package name */
        public static final c f8616c = new c("CRUNCHY");

        /* renamed from: d  reason: collision with root package name */
        public static final c f8617d = new c("NO_PREFIX");

        /* renamed from: a  reason: collision with root package name */
        private final String f8618a;

        private c(String str) {
            this.f8618a = str;
        }

        public String toString() {
            return this.f8618a;
        }
    }

    private p(int i8, int i9, int i10, c cVar) {
        this.f8607a = i8;
        this.f8608b = i9;
        this.f8609c = i10;
        this.f8610d = cVar;
    }

    public static b b() {
        return new b();
    }

    @Override // j4.w
    public boolean a() {
        return this.f8610d != c.f8617d;
    }

    public int c() {
        return this.f8608b;
    }

    public int d() {
        return this.f8607a;
    }

    public int e() {
        return this.f8609c;
    }

    public boolean equals(Object obj) {
        if (obj instanceof p) {
            p pVar = (p) obj;
            return pVar.d() == d() && pVar.c() == c() && pVar.e() == e() && pVar.f() == f();
        }
        return false;
    }

    public c f() {
        return this.f8610d;
    }

    public int hashCode() {
        return Objects.hash(p.class, Integer.valueOf(this.f8607a), Integer.valueOf(this.f8608b), Integer.valueOf(this.f8609c), this.f8610d);
    }

    public String toString() {
        return "AesEax Parameters (variant: " + this.f8610d + ", " + this.f8608b + "-byte IV, " + this.f8609c + "-byte tag, and " + this.f8607a + "-byte key)";
    }
}
