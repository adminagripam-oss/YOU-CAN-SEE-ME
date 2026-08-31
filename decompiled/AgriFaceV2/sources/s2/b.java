package s2;
/* loaded from: classes.dex */
public final class b {

    /* renamed from: a  reason: collision with root package name */
    private final String f11552a;

    private b(String str) {
        if (str == null) {
            throw new NullPointerException("name is null");
        }
        this.f11552a = str;
    }

    public static b b(String str) {
        return new b(str);
    }

    public String a() {
        return this.f11552a;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof b) {
            return this.f11552a.equals(((b) obj).f11552a);
        }
        return false;
    }

    public int hashCode() {
        return this.f11552a.hashCode() ^ 1000003;
    }

    public String toString() {
        return "Encoding{name=\"" + this.f11552a + "\"}";
    }
}
