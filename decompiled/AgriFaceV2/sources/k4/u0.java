package k4;

import java.security.GeneralSecurityException;
import java.util.Objects;
/* loaded from: classes.dex */
public final class u0 extends k4.c {

    /* renamed from: a  reason: collision with root package name */
    private final d f8665a;

    /* renamed from: b  reason: collision with root package name */
    private final String f8666b;

    /* renamed from: c  reason: collision with root package name */
    private final c f8667c;

    /* renamed from: d  reason: collision with root package name */
    private final k4.c f8668d;

    /* loaded from: classes.dex */
    public static class b {

        /* renamed from: a  reason: collision with root package name */
        private d f8669a;

        /* renamed from: b  reason: collision with root package name */
        private String f8670b;

        /* renamed from: c  reason: collision with root package name */
        private c f8671c;

        /* renamed from: d  reason: collision with root package name */
        private k4.c f8672d;

        private b() {
        }

        private static boolean b(c cVar, k4.c cVar2) {
            if (cVar.equals(c.f8673b) && (cVar2 instanceof u)) {
                return true;
            }
            if (cVar.equals(c.f8675d) && (cVar2 instanceof e0)) {
                return true;
            }
            if (cVar.equals(c.f8674c) && (cVar2 instanceof t1)) {
                return true;
            }
            if (cVar.equals(c.f8676e) && (cVar2 instanceof k)) {
                return true;
            }
            if (cVar.equals(c.f8677f) && (cVar2 instanceof p)) {
                return true;
            }
            return cVar.equals(c.f8678g) && (cVar2 instanceof z);
        }

        public u0 a() {
            if (this.f8669a == null) {
                this.f8669a = d.f8681c;
            }
            if (this.f8670b != null) {
                if (this.f8671c != null) {
                    k4.c cVar = this.f8672d;
                    if (cVar != null) {
                        if (cVar.a()) {
                            throw new GeneralSecurityException("dekParametersForNewKeys must not have ID Requirements");
                        }
                        if (b(this.f8671c, this.f8672d)) {
                            return new u0(this.f8669a, this.f8670b, this.f8671c, this.f8672d);
                        }
                        throw new GeneralSecurityException("Cannot use parsing strategy " + this.f8671c.toString() + " when new keys are picked according to " + this.f8672d + ".");
                    }
                    throw new GeneralSecurityException("dekParametersForNewKeys must be set");
                }
                throw new GeneralSecurityException("dekParsingStrategy must be set");
            }
            throw new GeneralSecurityException("kekUri must be set");
        }

        public b c(k4.c cVar) {
            this.f8672d = cVar;
            return this;
        }

        public b d(c cVar) {
            this.f8671c = cVar;
            return this;
        }

        public b e(String str) {
            this.f8670b = str;
            return this;
        }

        public b f(d dVar) {
            this.f8669a = dVar;
            return this;
        }
    }

    /* loaded from: classes.dex */
    public static final class c {

        /* renamed from: b  reason: collision with root package name */
        public static final c f8673b = new c("ASSUME_AES_GCM");

        /* renamed from: c  reason: collision with root package name */
        public static final c f8674c = new c("ASSUME_XCHACHA20POLY1305");

        /* renamed from: d  reason: collision with root package name */
        public static final c f8675d = new c("ASSUME_CHACHA20POLY1305");

        /* renamed from: e  reason: collision with root package name */
        public static final c f8676e = new c("ASSUME_AES_CTR_HMAC");

        /* renamed from: f  reason: collision with root package name */
        public static final c f8677f = new c("ASSUME_AES_EAX");

        /* renamed from: g  reason: collision with root package name */
        public static final c f8678g = new c("ASSUME_AES_GCM_SIV");

        /* renamed from: a  reason: collision with root package name */
        private final String f8679a;

        private c(String str) {
            this.f8679a = str;
        }

        public String toString() {
            return this.f8679a;
        }
    }

    /* loaded from: classes.dex */
    public static final class d {

        /* renamed from: b  reason: collision with root package name */
        public static final d f8680b = new d("TINK");

        /* renamed from: c  reason: collision with root package name */
        public static final d f8681c = new d("NO_PREFIX");

        /* renamed from: a  reason: collision with root package name */
        private final String f8682a;

        private d(String str) {
            this.f8682a = str;
        }

        public String toString() {
            return this.f8682a;
        }
    }

    private u0(d dVar, String str, c cVar, k4.c cVar2) {
        this.f8665a = dVar;
        this.f8666b = str;
        this.f8667c = cVar;
        this.f8668d = cVar2;
    }

    public static b b() {
        return new b();
    }

    @Override // j4.w
    public boolean a() {
        return this.f8665a != d.f8681c;
    }

    public k4.c c() {
        return this.f8668d;
    }

    public String d() {
        return this.f8666b;
    }

    public d e() {
        return this.f8665a;
    }

    public boolean equals(Object obj) {
        if (obj instanceof u0) {
            u0 u0Var = (u0) obj;
            return u0Var.f8667c.equals(this.f8667c) && u0Var.f8668d.equals(this.f8668d) && u0Var.f8666b.equals(this.f8666b) && u0Var.f8665a.equals(this.f8665a);
        }
        return false;
    }

    public int hashCode() {
        return Objects.hash(u0.class, this.f8666b, this.f8667c, this.f8668d, this.f8665a);
    }

    public String toString() {
        return "LegacyKmsEnvelopeAead Parameters (kekUri: " + this.f8666b + ", dekParsingStrategy: " + this.f8667c + ", dekParametersForNewKeys: " + this.f8668d + ", variant: " + this.f8665a + ")";
    }
}
