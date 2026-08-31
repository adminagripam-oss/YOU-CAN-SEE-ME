package k4;

import java.security.GeneralSecurityException;
import java.util.Objects;
/* loaded from: classes.dex */
public final class o1 extends c {

    /* renamed from: a  reason: collision with root package name */
    private final a f8602a;

    /* renamed from: b  reason: collision with root package name */
    private final int f8603b;

    /* loaded from: classes.dex */
    public static final class a {

        /* renamed from: b  reason: collision with root package name */
        public static final a f8604b = new a("TINK");

        /* renamed from: c  reason: collision with root package name */
        public static final a f8605c = new a("NO_PREFIX");

        /* renamed from: a  reason: collision with root package name */
        private final String f8606a;

        private a(String str) {
            this.f8606a = str;
        }

        public String toString() {
            return this.f8606a;
        }
    }

    private o1(a aVar, int i8) {
        this.f8602a = aVar;
        this.f8603b = i8;
    }

    public static o1 b(a aVar, int i8) {
        if (i8 < 8 || i8 > 12) {
            throw new GeneralSecurityException("Salt size must be between 8 and 12 bytes");
        }
        return new o1(aVar, i8);
    }

    @Override // j4.w
    public boolean a() {
        return this.f8602a != a.f8605c;
    }

    public int c() {
        return this.f8603b;
    }

    public a d() {
        return this.f8602a;
    }

    public boolean equals(Object obj) {
        if (obj instanceof o1) {
            o1 o1Var = (o1) obj;
            return o1Var.d() == d() && o1Var.c() == c();
        }
        return false;
    }

    public int hashCode() {
        return Objects.hash(o1.class, this.f8602a, Integer.valueOf(this.f8603b));
    }

    public String toString() {
        return "X-AES-GCM Parameters (variant: " + this.f8602a + "salt_size_bytes: " + this.f8603b + ")";
    }
}
