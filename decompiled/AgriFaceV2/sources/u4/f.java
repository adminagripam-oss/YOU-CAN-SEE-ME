package u4;

import java.security.GeneralSecurityException;
import java.security.InvalidAlgorithmParameterException;
import java.util.Objects;
/* loaded from: classes.dex */
public final class f extends q {

    /* renamed from: a  reason: collision with root package name */
    private final int f12111a;

    /* renamed from: b  reason: collision with root package name */
    private final int f12112b;

    /* renamed from: c  reason: collision with root package name */
    private final c f12113c;

    /* loaded from: classes.dex */
    public static final class b {

        /* renamed from: a  reason: collision with root package name */
        private Integer f12114a;

        /* renamed from: b  reason: collision with root package name */
        private Integer f12115b;

        /* renamed from: c  reason: collision with root package name */
        private c f12116c;

        private b() {
            this.f12114a = null;
            this.f12115b = null;
            this.f12116c = c.f12120e;
        }

        public f a() {
            Integer num = this.f12114a;
            if (num != null) {
                if (this.f12115b != null) {
                    if (this.f12116c != null) {
                        return new f(num.intValue(), this.f12115b.intValue(), this.f12116c);
                    }
                    throw new GeneralSecurityException("variant not set");
                }
                throw new GeneralSecurityException("tag size not set");
            }
            throw new GeneralSecurityException("key size not set");
        }

        public b b(int i8) {
            if (i8 == 16 || i8 == 32) {
                this.f12114a = Integer.valueOf(i8);
                return this;
            }
            throw new InvalidAlgorithmParameterException(String.format("Invalid key size %d; only 128-bit and 256-bit AES keys are supported", Integer.valueOf(i8 * 8)));
        }

        public b c(int i8) {
            if (i8 >= 10 && 16 >= i8) {
                this.f12115b = Integer.valueOf(i8);
                return this;
            }
            throw new GeneralSecurityException("Invalid tag size for AesCmacParameters: " + i8);
        }

        public b d(c cVar) {
            this.f12116c = cVar;
            return this;
        }
    }

    /* loaded from: classes.dex */
    public static final class c {

        /* renamed from: b  reason: collision with root package name */
        public static final c f12117b = new c("TINK");

        /* renamed from: c  reason: collision with root package name */
        public static final c f12118c = new c("CRUNCHY");

        /* renamed from: d  reason: collision with root package name */
        public static final c f12119d = new c("LEGACY");

        /* renamed from: e  reason: collision with root package name */
        public static final c f12120e = new c("NO_PREFIX");

        /* renamed from: a  reason: collision with root package name */
        private final String f12121a;

        private c(String str) {
            this.f12121a = str;
        }

        public String toString() {
            return this.f12121a;
        }
    }

    private f(int i8, int i9, c cVar) {
        this.f12111a = i8;
        this.f12112b = i9;
        this.f12113c = cVar;
    }

    public static b b() {
        return new b();
    }

    @Override // j4.w
    public boolean a() {
        return this.f12113c != c.f12120e;
    }

    public int c() {
        return this.f12112b;
    }

    public int d() {
        return this.f12111a;
    }

    public int e() {
        c cVar = this.f12113c;
        if (cVar == c.f12120e) {
            return c();
        }
        if (cVar == c.f12117b || cVar == c.f12118c || cVar == c.f12119d) {
            return c() + 5;
        }
        throw new IllegalStateException("Unknown variant");
    }

    public boolean equals(Object obj) {
        if (obj instanceof f) {
            f fVar = (f) obj;
            return fVar.d() == d() && fVar.e() == e() && fVar.f() == f();
        }
        return false;
    }

    public c f() {
        return this.f12113c;
    }

    public int hashCode() {
        return Objects.hash(f.class, Integer.valueOf(this.f12111a), Integer.valueOf(this.f12112b), this.f12113c);
    }

    public String toString() {
        return "AES-CMAC Parameters (variant: " + this.f12113c + ", " + this.f12112b + "-byte tags, and " + this.f12111a + "-byte key)";
    }
}
