package e2;

import java.math.BigInteger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/* loaded from: classes.dex */
public final class k implements Comparable {

    /* renamed from: j  reason: collision with root package name */
    public static final a f6590j = new a(null);

    /* renamed from: k  reason: collision with root package name */
    private static final k f6591k = new k(0, 0, 0, "");

    /* renamed from: l  reason: collision with root package name */
    private static final k f6592l = new k(0, 1, 0, "");

    /* renamed from: m  reason: collision with root package name */
    private static final k f6593m;

    /* renamed from: n  reason: collision with root package name */
    private static final k f6594n;

    /* renamed from: e  reason: collision with root package name */
    private final int f6595e;

    /* renamed from: f  reason: collision with root package name */
    private final int f6596f;

    /* renamed from: g  reason: collision with root package name */
    private final int f6597g;

    /* renamed from: h  reason: collision with root package name */
    private final String f6598h;

    /* renamed from: i  reason: collision with root package name */
    private final r6.e f6599i;

    /* loaded from: classes.dex */
    public static final class a {
        private a() {
        }

        public /* synthetic */ a(d7.g gVar) {
            this();
        }

        public final k a() {
            return k.f6592l;
        }

        public final k b(String str) {
            boolean d8;
            String group;
            if (str != null) {
                d8 = j7.l.d(str);
                if (!d8) {
                    Matcher matcher = Pattern.compile("(\\d+)(?:\\.(\\d+))(?:\\.(\\d+))(?:-(.+))?").matcher(str);
                    if (matcher.matches() && (group = matcher.group(1)) != null) {
                        int parseInt = Integer.parseInt(group);
                        String group2 = matcher.group(2);
                        if (group2 != null) {
                            int parseInt2 = Integer.parseInt(group2);
                            String group3 = matcher.group(3);
                            if (group3 != null) {
                                int parseInt3 = Integer.parseInt(group3);
                                String group4 = matcher.group(4) != null ? matcher.group(4) : "";
                                d7.k.d(group4, "description");
                                return new k(parseInt, parseInt2, parseInt3, group4, null);
                            }
                        }
                    }
                }
            }
            return null;
        }
    }

    /* loaded from: classes.dex */
    static final class b extends d7.l implements c7.a {
        b() {
            super(0);
        }

        @Override // c7.a
        /* renamed from: b */
        public final BigInteger a() {
            return BigInteger.valueOf(k.this.g()).shiftLeft(32).or(BigInteger.valueOf(k.this.h())).shiftLeft(32).or(BigInteger.valueOf(k.this.i()));
        }
    }

    static {
        k kVar = new k(1, 0, 0, "");
        f6593m = kVar;
        f6594n = kVar;
    }

    private k(int i8, int i9, int i10, String str) {
        r6.e a8;
        this.f6595e = i8;
        this.f6596f = i9;
        this.f6597g = i10;
        this.f6598h = str;
        a8 = r6.g.a(new b());
        this.f6599i = a8;
    }

    private final BigInteger f() {
        Object value = this.f6599i.getValue();
        d7.k.d(value, "<get-bigInteger>(...)");
        return (BigInteger) value;
    }

    @Override // java.lang.Comparable
    /* renamed from: e */
    public int compareTo(k kVar) {
        d7.k.e(kVar, "other");
        return f().compareTo(kVar.f());
    }

    public boolean equals(Object obj) {
        if (obj instanceof k) {
            k kVar = (k) obj;
            return this.f6595e == kVar.f6595e && this.f6596f == kVar.f6596f && this.f6597g == kVar.f6597g;
        }
        return false;
    }

    public final int g() {
        return this.f6595e;
    }

    public final int h() {
        return this.f6596f;
    }

    public int hashCode() {
        return ((((527 + this.f6595e) * 31) + this.f6596f) * 31) + this.f6597g;
    }

    public final int i() {
        return this.f6597g;
    }

    public String toString() {
        boolean d8;
        String str;
        d8 = j7.l.d(this.f6598h);
        if (!d8) {
            str = '-' + this.f6598h;
        } else {
            str = "";
        }
        return this.f6595e + '.' + this.f6596f + '.' + this.f6597g + str;
    }

    public /* synthetic */ k(int i8, int i9, int i10, String str, d7.g gVar) {
        this(i8, i9, i10, str);
    }
}
