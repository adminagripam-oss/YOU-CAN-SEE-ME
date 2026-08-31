package y;
/* loaded from: classes.dex */
public final class a0 {

    /* renamed from: c  reason: collision with root package name */
    public static final a0 f12729c = new a0(0, 0);

    /* renamed from: d  reason: collision with root package name */
    public static final a0 f12730d = new a0(1, 8);

    /* renamed from: e  reason: collision with root package name */
    public static final a0 f12731e = new a0(2, 10);

    /* renamed from: f  reason: collision with root package name */
    public static final a0 f12732f = new a0(3, 10);

    /* renamed from: g  reason: collision with root package name */
    public static final a0 f12733g = new a0(4, 10);

    /* renamed from: h  reason: collision with root package name */
    public static final a0 f12734h = new a0(5, 10);

    /* renamed from: i  reason: collision with root package name */
    public static final a0 f12735i = new a0(6, 10);

    /* renamed from: j  reason: collision with root package name */
    public static final a0 f12736j = new a0(6, 8);

    /* renamed from: a  reason: collision with root package name */
    private final int f12737a;

    /* renamed from: b  reason: collision with root package name */
    private final int f12738b;

    public a0(int i8, int i9) {
        this.f12737a = i8;
        this.f12738b = i9;
    }

    private static String c(int i8) {
        switch (i8) {
            case 0:
                return "UNSPECIFIED";
            case 1:
                return "SDR";
            case 2:
                return "HDR_UNSPECIFIED";
            case 3:
                return "HLG";
            case 4:
                return "HDR10";
            case 5:
                return "HDR10_PLUS";
            case androidx.browser.customtabs.b.TAB_HIDDEN /* 6 */:
                return "DOLBY_VISION";
            default:
                return "<Unknown>";
        }
    }

    public int a() {
        return this.f12738b;
    }

    public int b() {
        return this.f12737a;
    }

    public boolean d() {
        return e() && b() != 1 && a() == 10;
    }

    public boolean e() {
        return (b() == 0 || b() == 2 || a() == 0) ? false : true;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof a0) {
            a0 a0Var = (a0) obj;
            return this.f12737a == a0Var.b() && this.f12738b == a0Var.a();
        }
        return false;
    }

    public int hashCode() {
        return ((this.f12737a ^ 1000003) * 1000003) ^ this.f12738b;
    }

    public String toString() {
        return "DynamicRange@" + Integer.toHexString(System.identityHashCode(this)) + "{encoding=" + c(this.f12737a) + ", bitDepth=" + this.f12738b + "}";
    }
}
