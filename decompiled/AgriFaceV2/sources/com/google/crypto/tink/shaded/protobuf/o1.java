package com.google.crypto.tink.shaded.protobuf;
/* loaded from: classes.dex */
public abstract class o1 {

    /* renamed from: a  reason: collision with root package name */
    static final int f6165a = c(1, 3);

    /* renamed from: b  reason: collision with root package name */
    static final int f6166b = c(1, 4);

    /* renamed from: c  reason: collision with root package name */
    static final int f6167c = c(2, 0);

    /* renamed from: d  reason: collision with root package name */
    static final int f6168d = c(3, 2);

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
    public static class b {

        /* renamed from: g  reason: collision with root package name */
        public static final b f6169g = new b("DOUBLE", 0, c.DOUBLE, 1);

        /* renamed from: h  reason: collision with root package name */
        public static final b f6170h = new b("FLOAT", 1, c.FLOAT, 5);

        /* renamed from: i  reason: collision with root package name */
        public static final b f6171i;

        /* renamed from: j  reason: collision with root package name */
        public static final b f6172j;

        /* renamed from: k  reason: collision with root package name */
        public static final b f6173k;

        /* renamed from: l  reason: collision with root package name */
        public static final b f6174l;

        /* renamed from: m  reason: collision with root package name */
        public static final b f6175m;

        /* renamed from: n  reason: collision with root package name */
        public static final b f6176n;

        /* renamed from: o  reason: collision with root package name */
        public static final b f6177o;

        /* renamed from: p  reason: collision with root package name */
        public static final b f6178p;

        /* renamed from: q  reason: collision with root package name */
        public static final b f6179q;

        /* renamed from: r  reason: collision with root package name */
        public static final b f6180r;

        /* renamed from: s  reason: collision with root package name */
        public static final b f6181s;

        /* renamed from: t  reason: collision with root package name */
        public static final b f6182t;

        /* renamed from: u  reason: collision with root package name */
        public static final b f6183u;

        /* renamed from: v  reason: collision with root package name */
        public static final b f6184v;

        /* renamed from: w  reason: collision with root package name */
        public static final b f6185w;

        /* renamed from: x  reason: collision with root package name */
        public static final b f6186x;

        /* renamed from: y  reason: collision with root package name */
        private static final /* synthetic */ b[] f6187y;

        /* renamed from: e  reason: collision with root package name */
        private final c f6188e;

        /* renamed from: f  reason: collision with root package name */
        private final int f6189f;

        /* loaded from: classes.dex */
        enum a extends b {
            a(String str, int i8, c cVar, int i9) {
                super(str, i8, cVar, i9);
            }
        }

        /* renamed from: com.google.crypto.tink.shaded.protobuf.o1$b$b  reason: collision with other inner class name */
        /* loaded from: classes.dex */
        enum C0068b extends b {
            C0068b(String str, int i8, c cVar, int i9) {
                super(str, i8, cVar, i9);
            }
        }

        /* loaded from: classes.dex */
        enum c extends b {
            c(String str, int i8, c cVar, int i9) {
                super(str, i8, cVar, i9);
            }
        }

        /* loaded from: classes.dex */
        enum d extends b {
            d(String str, int i8, c cVar, int i9) {
                super(str, i8, cVar, i9);
            }
        }

        static {
            c cVar = c.LONG;
            f6171i = new b("INT64", 2, cVar, 0);
            f6172j = new b("UINT64", 3, cVar, 0);
            c cVar2 = c.INT;
            f6173k = new b("INT32", 4, cVar2, 0);
            f6174l = new b("FIXED64", 5, cVar, 1);
            f6175m = new b("FIXED32", 6, cVar2, 5);
            f6176n = new b("BOOL", 7, c.BOOLEAN, 0);
            f6177o = new a("STRING", 8, c.STRING, 2);
            c cVar3 = c.MESSAGE;
            f6178p = new C0068b("GROUP", 9, cVar3, 3);
            f6179q = new c("MESSAGE", 10, cVar3, 2);
            f6180r = new d("BYTES", 11, c.BYTE_STRING, 2);
            f6181s = new b("UINT32", 12, cVar2, 0);
            f6182t = new b("ENUM", 13, c.ENUM, 0);
            f6183u = new b("SFIXED32", 14, cVar2, 5);
            f6184v = new b("SFIXED64", 15, cVar, 1);
            f6185w = new b("SINT32", 16, cVar2, 0);
            f6186x = new b("SINT64", 17, cVar, 0);
            f6187y = d();
        }

        private b(String str, int i8, c cVar, int i9) {
            this.f6188e = cVar;
            this.f6189f = i9;
        }

        private static /* synthetic */ b[] d() {
            return new b[]{f6169g, f6170h, f6171i, f6172j, f6173k, f6174l, f6175m, f6176n, f6177o, f6178p, f6179q, f6180r, f6181s, f6182t, f6183u, f6184v, f6185w, f6186x};
        }

        public static b valueOf(String str) {
            return (b) Enum.valueOf(b.class, str);
        }

        public static b[] values() {
            return (b[]) f6187y.clone();
        }
    }

    /* loaded from: classes.dex */
    public enum c {
        INT(0),
        LONG(0L),
        FLOAT(Float.valueOf(0.0f)),
        DOUBLE(Double.valueOf(0.0d)),
        BOOLEAN(Boolean.FALSE),
        STRING(""),
        BYTE_STRING(h.f6072f),
        ENUM(null),
        MESSAGE(null);
        

        /* renamed from: e  reason: collision with root package name */
        private final Object f6200e;

        c(Object obj) {
            this.f6200e = obj;
        }
    }

    public static int a(int i8) {
        return i8 >>> 3;
    }

    public static int b(int i8) {
        return i8 & 7;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int c(int i8, int i9) {
        return (i8 << 3) | i9;
    }
}
