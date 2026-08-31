package k4;

import java.security.GeneralSecurityException;
import java.security.InvalidAlgorithmParameterException;
import java.util.Objects;
/* loaded from: classes.dex */
public final class u extends k4.c {

    /* renamed from: a  reason: collision with root package name */
    private final int f8653a;

    /* renamed from: b  reason: collision with root package name */
    private final int f8654b;

    /* renamed from: c  reason: collision with root package name */
    private final int f8655c;

    /* renamed from: d  reason: collision with root package name */
    private final c f8656d;

    /* loaded from: classes.dex */
    public static final class b {

        /* renamed from: a  reason: collision with root package name */
        private Integer f8657a;

        /* renamed from: b  reason: collision with root package name */
        private Integer f8658b;

        /* renamed from: c  reason: collision with root package name */
        private Integer f8659c;

        /* renamed from: d  reason: collision with root package name */
        private c f8660d;

        private b() {
            this.f8657a = null;
            this.f8658b = null;
            this.f8659c = null;
            this.f8660d = c.f8663d;
        }

        public u a() {
            Integer num = this.f8657a;
            if (num != null) {
                if (this.f8660d != null) {
                    if (this.f8658b != null) {
                        if (this.f8659c != null) {
                            return new u(num.intValue(), this.f8658b.intValue(), this.f8659c.intValue(), this.f8660d);
                        }
                        throw new GeneralSecurityException("Tag size is not set");
                    }
                    throw new GeneralSecurityException("IV size is not set");
                }
                throw new GeneralSecurityException("Variant is not set");
            }
            throw new GeneralSecurityException("Key size is not set");
        }

        public b b(int i8) {
            if (i8 > 0) {
                this.f8658b = Integer.valueOf(i8);
                return this;
            }
            throw new GeneralSecurityException(String.format("Invalid IV size in bytes %d; IV size must be positive", Integer.valueOf(i8)));
        }

        public b c(int i8) {
            if (i8 == 16 || i8 == 24 || i8 == 32) {
                this.f8657a = Integer.valueOf(i8);
                return this;
            }
            throw new InvalidAlgorithmParameterException(String.format("Invalid key size %d; only 16-byte, 24-byte and 32-byte AES keys are supported", Integer.valueOf(i8)));
        }

        public b d(int i8) {
            if (i8 < 12 || i8 > 16) {
                throw new GeneralSecurityException(String.format("Invalid tag size in bytes %d; value must be between 12 and 16 bytes", Integer.valueOf(i8)));
            }
            this.f8659c = Integer.valueOf(i8);
            return this;
        }

        public b e(c cVar) {
            this.f8660d = cVar;
            return this;
        }
    }

    /* loaded from: classes.dex */
    public static final class c {

        /* renamed from: b  reason: collision with root package name */
        public static final c f8661b = new c("TINK");

        /* renamed from: c  reason: collision with root package name */
        public static final c f8662c = new c("CRUNCHY");

        /* renamed from: d  reason: collision with root package name */
        public static final c f8663d = new c("NO_PREFIX");

        /* renamed from: a  reason: collision with root package name */
        private final String f8664a;

        private c(String str) {
            this.f8664a = str;
        }

        public String toString() {
            return this.f8664a;
        }
    }

    private u(int i8, int i9, int i10, c cVar) {
        this.f8653a = i8;
        this.f8654b = i9;
        this.f8655c = i10;
        this.f8656d = cVar;
    }

    public static b b() {
        return new b();
    }

    @Override // j4.w
    public boolean a() {
        return this.f8656d != c.f8663d;
    }

    public int c() {
        return this.f8654b;
    }

    public int d() {
        return this.f8653a;
    }

    public int e() {
        return this.f8655c;
    }

    public boolean equals(Object obj) {
        if (obj instanceof u) {
            u uVar = (u) obj;
            return uVar.d() == d() && uVar.c() == c() && uVar.e() == e() && uVar.f() == f();
        }
        return false;
    }

    public c f() {
        return this.f8656d;
    }

    public int hashCode() {
        return Objects.hash(u.class, Integer.valueOf(this.f8653a), Integer.valueOf(this.f8654b), Integer.valueOf(this.f8655c), this.f8656d);
    }

    public String toString() {
        return "AesGcm Parameters (variant: " + this.f8656d + ", " + this.f8654b + "-byte IV, " + this.f8655c + "-byte tag, and " + this.f8653a + "-byte key)";
    }
}
