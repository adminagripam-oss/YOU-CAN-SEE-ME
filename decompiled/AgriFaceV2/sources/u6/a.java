package u6;
/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* loaded from: classes.dex */
public final class a {

    /* renamed from: e  reason: collision with root package name */
    public static final a f12273e = new a("COROUTINE_SUSPENDED", 0);

    /* renamed from: f  reason: collision with root package name */
    public static final a f12274f = new a("UNDECIDED", 1);

    /* renamed from: g  reason: collision with root package name */
    public static final a f12275g = new a("RESUMED", 2);

    /* renamed from: h  reason: collision with root package name */
    private static final /* synthetic */ a[] f12276h;

    /* renamed from: i  reason: collision with root package name */
    private static final /* synthetic */ w6.a f12277i;

    static {
        a[] d8 = d();
        f12276h = d8;
        f12277i = w6.b.a(d8);
    }

    private a(String str, int i8) {
    }

    private static final /* synthetic */ a[] d() {
        return new a[]{f12273e, f12274f, f12275g};
    }

    public static a valueOf(String str) {
        return (a) Enum.valueOf(a.class, str);
    }

    public static a[] values() {
        return (a[]) f12276h.clone();
    }
}
