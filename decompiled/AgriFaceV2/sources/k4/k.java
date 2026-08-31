package k4;

import java.security.GeneralSecurityException;
import java.security.InvalidAlgorithmParameterException;
import java.util.Objects;
/* loaded from: classes.dex */
public final class k extends k4.c {

    /* renamed from: a  reason: collision with root package name */
    private final int f8553a;

    /* renamed from: b  reason: collision with root package name */
    private final int f8554b;

    /* renamed from: c  reason: collision with root package name */
    private final int f8555c;

    /* renamed from: d  reason: collision with root package name */
    private final int f8556d;

    /* renamed from: e  reason: collision with root package name */
    private final d f8557e;

    /* renamed from: f  reason: collision with root package name */
    private final c f8558f;

    /* loaded from: classes.dex */
    public static final class b {

        /* renamed from: a  reason: collision with root package name */
        private Integer f8559a;

        /* renamed from: b  reason: collision with root package name */
        private Integer f8560b;

        /* renamed from: c  reason: collision with root package name */
        private Integer f8561c;

        /* renamed from: d  reason: collision with root package name */
        private Integer f8562d;

        /* renamed from: e  reason: collision with root package name */
        private c f8563e;

        /* renamed from: f  reason: collision with root package name */
        private d f8564f;

        private b() {
            this.f8559a = null;
            this.f8560b = null;
            this.f8561c = null;
            this.f8562d = null;
            this.f8563e = null;
            this.f8564f = d.f8573d;
        }

        private static void h(int i8, c cVar) {
            if (cVar == c.f8565b) {
                if (i8 > 20) {
                    throw new GeneralSecurityException(String.format("Invalid tag size in bytes %d; can be at most 20 bytes for SHA1", Integer.valueOf(i8)));
                }
            } else if (cVar == c.f8566c) {
                if (i8 > 28) {
                    throw new GeneralSecurityException(String.format("Invalid tag size in bytes %d; can be at most 28 bytes for SHA224", Integer.valueOf(i8)));
                }
            } else if (cVar == c.f8567d) {
                if (i8 > 32) {
                    throw new GeneralSecurityException(String.format("Invalid tag size in bytes %d; can be at most 32 bytes for SHA256", Integer.valueOf(i8)));
                }
            } else if (cVar == c.f8568e) {
                if (i8 > 48) {
                    throw new GeneralSecurityException(String.format("Invalid tag size in bytes %d; can be at most 48 bytes for SHA384", Integer.valueOf(i8)));
                }
            } else if (cVar != c.f8569f) {
                throw new GeneralSecurityException("unknown hash type; must be SHA1, SHA224, SHA256, SHA384 or SHA512");
            } else {
                if (i8 > 64) {
                    throw new GeneralSecurityException(String.format("Invalid tag size in bytes %d; can be at most 64 bytes for SHA512", Integer.valueOf(i8)));
                }
            }
        }

        public k a() {
            if (this.f8559a != null) {
                if (this.f8560b != null) {
                    if (this.f8561c != null) {
                        Integer num = this.f8562d;
                        if (num != null) {
                            if (this.f8563e != null) {
                                if (this.f8564f != null) {
                                    h(num.intValue(), this.f8563e);
                                    return new k(this.f8559a.intValue(), this.f8560b.intValue(), this.f8561c.intValue(), this.f8562d.intValue(), this.f8564f, this.f8563e);
                                }
                                throw new GeneralSecurityException("variant is not set");
                            }
                            throw new GeneralSecurityException("hash type is not set");
                        }
                        throw new GeneralSecurityException("tag size is not set");
                    }
                    throw new GeneralSecurityException("iv size is not set");
                }
                throw new GeneralSecurityException("HMAC key size is not set");
            }
            throw new GeneralSecurityException("AES key size is not set");
        }

        public b b(int i8) {
            if (i8 == 16 || i8 == 24 || i8 == 32) {
                this.f8559a = Integer.valueOf(i8);
                return this;
            }
            throw new InvalidAlgorithmParameterException(String.format("Invalid key size %d; only 16-byte, 24-byte and 32-byte AES keys are supported", Integer.valueOf(i8)));
        }

        public b c(c cVar) {
            this.f8563e = cVar;
            return this;
        }

        public b d(int i8) {
            if (i8 >= 16) {
                this.f8560b = Integer.valueOf(i8);
                return this;
            }
            throw new InvalidAlgorithmParameterException(String.format("Invalid key size in bytes %d; HMAC key must be at least 16 bytes", Integer.valueOf(i8)));
        }

        public b e(int i8) {
            if (i8 < 12 || i8 > 16) {
                throw new GeneralSecurityException(String.format("Invalid IV size in bytes %d; IV size must be between 12 and 16 bytes", Integer.valueOf(i8)));
            }
            this.f8561c = Integer.valueOf(i8);
            return this;
        }

        public b f(int i8) {
            if (i8 >= 10) {
                this.f8562d = Integer.valueOf(i8);
                return this;
            }
            throw new GeneralSecurityException(String.format("Invalid tag size in bytes %d; must be at least 10 bytes", Integer.valueOf(i8)));
        }

        public b g(d dVar) {
            this.f8564f = dVar;
            return this;
        }
    }

    /* loaded from: classes.dex */
    public static final class c {

        /* renamed from: b  reason: collision with root package name */
        public static final c f8565b = new c("SHA1");

        /* renamed from: c  reason: collision with root package name */
        public static final c f8566c = new c("SHA224");

        /* renamed from: d  reason: collision with root package name */
        public static final c f8567d = new c("SHA256");

        /* renamed from: e  reason: collision with root package name */
        public static final c f8568e = new c("SHA384");

        /* renamed from: f  reason: collision with root package name */
        public static final c f8569f = new c("SHA512");

        /* renamed from: a  reason: collision with root package name */
        private final String f8570a;

        private c(String str) {
            this.f8570a = str;
        }

        public String toString() {
            return this.f8570a;
        }
    }

    /* loaded from: classes.dex */
    public static final class d {

        /* renamed from: b  reason: collision with root package name */
        public static final d f8571b = new d("TINK");

        /* renamed from: c  reason: collision with root package name */
        public static final d f8572c = new d("CRUNCHY");

        /* renamed from: d  reason: collision with root package name */
        public static final d f8573d = new d("NO_PREFIX");

        /* renamed from: a  reason: collision with root package name */
        private final String f8574a;

        private d(String str) {
            this.f8574a = str;
        }

        public String toString() {
            return this.f8574a;
        }
    }

    private k(int i8, int i9, int i10, int i11, d dVar, c cVar) {
        this.f8553a = i8;
        this.f8554b = i9;
        this.f8555c = i10;
        this.f8556d = i11;
        this.f8557e = dVar;
        this.f8558f = cVar;
    }

    public static b b() {
        return new b();
    }

    @Override // j4.w
    public boolean a() {
        return this.f8557e != d.f8573d;
    }

    public int c() {
        return this.f8553a;
    }

    public c d() {
        return this.f8558f;
    }

    public int e() {
        return this.f8554b;
    }

    public boolean equals(Object obj) {
        if (obj instanceof k) {
            k kVar = (k) obj;
            return kVar.c() == c() && kVar.e() == e() && kVar.f() == f() && kVar.g() == g() && kVar.h() == h() && kVar.d() == d();
        }
        return false;
    }

    public int f() {
        return this.f8555c;
    }

    public int g() {
        return this.f8556d;
    }

    public d h() {
        return this.f8557e;
    }

    public int hashCode() {
        return Objects.hash(k.class, Integer.valueOf(this.f8553a), Integer.valueOf(this.f8554b), Integer.valueOf(this.f8555c), Integer.valueOf(this.f8556d), this.f8557e, this.f8558f);
    }

    public String toString() {
        return "AesCtrHmacAead Parameters (variant: " + this.f8557e + ", hashType: " + this.f8558f + ", " + this.f8555c + "-byte IV, and " + this.f8556d + "-byte tags, and " + this.f8553a + "-byte AES key, and " + this.f8554b + "-byte HMAC key)";
    }
}
