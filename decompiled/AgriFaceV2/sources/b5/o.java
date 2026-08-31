package b5;
/* loaded from: classes.dex */
public final class o {

    /* renamed from: a  reason: collision with root package name */
    private final Class f4671a;

    /* renamed from: b  reason: collision with root package name */
    private final int f4672b;

    /* renamed from: c  reason: collision with root package name */
    private final int f4673c;

    private o(Class cls, int i8, int i9) {
        this.f4671a = (Class) a0.c(cls, "Null dependency anInterface.");
        this.f4672b = i8;
        this.f4673c = i9;
    }

    private static String a(int i8) {
        if (i8 != 0) {
            if (i8 != 1) {
                if (i8 == 2) {
                    return "deferred";
                }
                throw new AssertionError("Unsupported injection: " + i8);
            }
            return "provider";
        }
        return "direct";
    }

    public static o g(Class cls) {
        return new o(cls, 1, 0);
    }

    public static o h(Class cls) {
        return new o(cls, 1, 1);
    }

    public static o i(Class cls) {
        return new o(cls, 2, 0);
    }

    public Class b() {
        return this.f4671a;
    }

    public boolean c() {
        return this.f4673c == 2;
    }

    public boolean d() {
        return this.f4673c == 0;
    }

    public boolean e() {
        return this.f4672b == 1;
    }

    public boolean equals(Object obj) {
        if (obj instanceof o) {
            o oVar = (o) obj;
            return this.f4671a == oVar.f4671a && this.f4672b == oVar.f4672b && this.f4673c == oVar.f4673c;
        }
        return false;
    }

    public boolean f() {
        return this.f4672b == 2;
    }

    public int hashCode() {
        return ((((this.f4671a.hashCode() ^ 1000003) * 1000003) ^ this.f4672b) * 1000003) ^ this.f4673c;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("Dependency{anInterface=");
        sb.append(this.f4671a);
        sb.append(", type=");
        int i8 = this.f4672b;
        sb.append(i8 == 1 ? "required" : i8 == 0 ? "optional" : "set");
        sb.append(", injection=");
        sb.append(a(this.f4673c));
        sb.append("}");
        return sb.toString();
    }
}
