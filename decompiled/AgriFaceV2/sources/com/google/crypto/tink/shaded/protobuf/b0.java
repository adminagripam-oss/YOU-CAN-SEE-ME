package com.google.crypto.tink.shaded.protobuf;
/* JADX WARN: Enum visitor error
jadx.core.utils.exceptions.JadxRuntimeException: Init of enum i uses external variables
	at jadx.core.dex.visitors.EnumVisitor.createEnumFieldByConstructor(EnumVisitor.java:444)
	at jadx.core.dex.visitors.EnumVisitor.processEnumFieldByField(EnumVisitor.java:368)
	at jadx.core.dex.visitors.EnumVisitor.processEnumFieldByWrappedInsn(EnumVisitor.java:333)
	at jadx.core.dex.visitors.EnumVisitor.extractEnumFieldsFromFilledArray(EnumVisitor.java:318)
	at jadx.core.dex.visitors.EnumVisitor.extractEnumFieldsFromInsn(EnumVisitor.java:258)
	at jadx.core.dex.visitors.EnumVisitor.extractEnumFieldsFromInvoke(EnumVisitor.java:289)
	at jadx.core.dex.visitors.EnumVisitor.extractEnumFieldsFromInsn(EnumVisitor.java:262)
	at jadx.core.dex.visitors.EnumVisitor.convertToEnum(EnumVisitor.java:151)
	at jadx.core.dex.visitors.EnumVisitor.visit(EnumVisitor.java:100)
 */
/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* loaded from: classes.dex */
public final class b0 {

    /* renamed from: h  reason: collision with root package name */
    public static final b0 f6039h = new b0("VOID", 0, Void.class, Void.class, null);

    /* renamed from: i  reason: collision with root package name */
    public static final b0 f6040i;

    /* renamed from: j  reason: collision with root package name */
    public static final b0 f6041j;

    /* renamed from: k  reason: collision with root package name */
    public static final b0 f6042k;

    /* renamed from: l  reason: collision with root package name */
    public static final b0 f6043l;

    /* renamed from: m  reason: collision with root package name */
    public static final b0 f6044m;

    /* renamed from: n  reason: collision with root package name */
    public static final b0 f6045n;

    /* renamed from: o  reason: collision with root package name */
    public static final b0 f6046o;

    /* renamed from: p  reason: collision with root package name */
    public static final b0 f6047p;

    /* renamed from: q  reason: collision with root package name */
    public static final b0 f6048q;

    /* renamed from: r  reason: collision with root package name */
    private static final /* synthetic */ b0[] f6049r;

    /* renamed from: e  reason: collision with root package name */
    private final Class f6050e;

    /* renamed from: f  reason: collision with root package name */
    private final Class f6051f;

    /* renamed from: g  reason: collision with root package name */
    private final Object f6052g;

    static {
        Class cls = Integer.TYPE;
        f6040i = new b0("INT", 1, cls, Integer.class, 0);
        f6041j = new b0("LONG", 2, Long.TYPE, Long.class, 0L);
        f6042k = new b0("FLOAT", 3, Float.TYPE, Float.class, Float.valueOf(0.0f));
        f6043l = new b0("DOUBLE", 4, Double.TYPE, Double.class, Double.valueOf(0.0d));
        f6044m = new b0("BOOLEAN", 5, Boolean.TYPE, Boolean.class, Boolean.FALSE);
        f6045n = new b0("STRING", 6, String.class, String.class, "");
        f6046o = new b0("BYTE_STRING", 7, h.class, h.class, h.f6072f);
        f6047p = new b0("ENUM", 8, cls, Integer.class, null);
        f6048q = new b0("MESSAGE", 9, Object.class, Object.class, null);
        f6049r = d();
    }

    private b0(String str, int i8, Class cls, Class cls2, Object obj) {
        this.f6050e = cls;
        this.f6051f = cls2;
        this.f6052g = obj;
    }

    private static /* synthetic */ b0[] d() {
        return new b0[]{f6039h, f6040i, f6041j, f6042k, f6043l, f6044m, f6045n, f6046o, f6047p, f6048q};
    }

    public static b0 valueOf(String str) {
        return (b0) Enum.valueOf(b0.class, str);
    }

    public static b0[] values() {
        return (b0[]) f6049r.clone();
    }

    public Class e() {
        return this.f6051f;
    }
}
