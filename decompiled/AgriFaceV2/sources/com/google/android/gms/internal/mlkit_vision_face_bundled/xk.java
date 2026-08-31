package com.google.android.gms.internal.mlkit_vision_face_bundled;
/* JADX WARN: Enum visitor error
jadx.core.utils.exceptions.JadxRuntimeException: Init of enum g uses external variables
	at jadx.core.dex.visitors.EnumVisitor.createEnumFieldByConstructor(EnumVisitor.java:444)
	at jadx.core.dex.visitors.EnumVisitor.processEnumFieldByRegister(EnumVisitor.java:391)
	at jadx.core.dex.visitors.EnumVisitor.extractEnumFieldsFromFilledArray(EnumVisitor.java:320)
	at jadx.core.dex.visitors.EnumVisitor.extractEnumFieldsFromInsn(EnumVisitor.java:258)
	at jadx.core.dex.visitors.EnumVisitor.convertToEnum(EnumVisitor.java:151)
	at jadx.core.dex.visitors.EnumVisitor.visit(EnumVisitor.java:100)
 */
/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* loaded from: classes.dex */
public final class xk {

    /* renamed from: f  reason: collision with root package name */
    public static final xk f5930f;

    /* renamed from: g  reason: collision with root package name */
    public static final xk f5931g;

    /* renamed from: h  reason: collision with root package name */
    public static final xk f5932h;

    /* renamed from: i  reason: collision with root package name */
    public static final xk f5933i;

    /* renamed from: j  reason: collision with root package name */
    public static final xk f5934j;

    /* renamed from: k  reason: collision with root package name */
    public static final xk f5935k;

    /* renamed from: l  reason: collision with root package name */
    public static final xk f5936l;

    /* renamed from: m  reason: collision with root package name */
    public static final xk f5937m;

    /* renamed from: n  reason: collision with root package name */
    public static final xk f5938n;

    /* renamed from: o  reason: collision with root package name */
    public static final xk f5939o;

    /* renamed from: p  reason: collision with root package name */
    private static final /* synthetic */ xk[] f5940p;

    /* renamed from: e  reason: collision with root package name */
    private final Class f5941e;

    static {
        xk xkVar = new xk("VOID", 0, Void.class, Void.class, null);
        f5930f = xkVar;
        Class cls = Integer.TYPE;
        xk xkVar2 = new xk("INT", 1, cls, Integer.class, 0);
        f5931g = xkVar2;
        xk xkVar3 = new xk("LONG", 2, Long.TYPE, Long.class, 0L);
        f5932h = xkVar3;
        xk xkVar4 = new xk("FLOAT", 3, Float.TYPE, Float.class, Float.valueOf(0.0f));
        f5933i = xkVar4;
        xk xkVar5 = new xk("DOUBLE", 4, Double.TYPE, Double.class, Double.valueOf(0.0d));
        f5934j = xkVar5;
        xk xkVar6 = new xk("BOOLEAN", 5, Boolean.TYPE, Boolean.class, Boolean.FALSE);
        f5935k = xkVar6;
        xk xkVar7 = new xk("STRING", 6, String.class, String.class, "");
        f5936l = xkVar7;
        xk xkVar8 = new xk("BYTE_STRING", 7, mj.class, mj.class, mj.f5439f);
        f5937m = xkVar8;
        xk xkVar9 = new xk("ENUM", 8, cls, Integer.class, null);
        f5938n = xkVar9;
        xk xkVar10 = new xk("MESSAGE", 9, Object.class, Object.class, null);
        f5939o = xkVar10;
        f5940p = new xk[]{xkVar, xkVar2, xkVar3, xkVar4, xkVar5, xkVar6, xkVar7, xkVar8, xkVar9, xkVar10};
    }

    private xk(String str, int i8, Class cls, Class cls2, Object obj) {
        this.f5941e = cls2;
    }

    public static xk[] values() {
        return (xk[]) f5940p.clone();
    }

    public final Class d() {
        return this.f5941e;
    }
}
