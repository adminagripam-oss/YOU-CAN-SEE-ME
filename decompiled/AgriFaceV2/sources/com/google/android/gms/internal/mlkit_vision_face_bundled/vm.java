package com.google.android.gms.internal.mlkit_vision_face_bundled;
/* JADX WARN: Enum visitor error
jadx.core.utils.exceptions.JadxRuntimeException: Init of enum h uses external variables
	at jadx.core.dex.visitors.EnumVisitor.createEnumFieldByConstructor(EnumVisitor.java:444)
	at jadx.core.dex.visitors.EnumVisitor.processEnumFieldByRegister(EnumVisitor.java:391)
	at jadx.core.dex.visitors.EnumVisitor.extractEnumFieldsFromFilledArray(EnumVisitor.java:320)
	at jadx.core.dex.visitors.EnumVisitor.extractEnumFieldsFromInsn(EnumVisitor.java:258)
	at jadx.core.dex.visitors.EnumVisitor.convertToEnum(EnumVisitor.java:151)
	at jadx.core.dex.visitors.EnumVisitor.visit(EnumVisitor.java:100)
 */
/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* loaded from: classes.dex */
public final class vm {

    /* renamed from: f  reason: collision with root package name */
    public static final vm f5853f;

    /* renamed from: g  reason: collision with root package name */
    public static final vm f5854g;

    /* renamed from: h  reason: collision with root package name */
    public static final vm f5855h;

    /* renamed from: i  reason: collision with root package name */
    public static final vm f5856i;

    /* renamed from: j  reason: collision with root package name */
    public static final vm f5857j;

    /* renamed from: k  reason: collision with root package name */
    public static final vm f5858k;

    /* renamed from: l  reason: collision with root package name */
    public static final vm f5859l;

    /* renamed from: m  reason: collision with root package name */
    public static final vm f5860m;

    /* renamed from: n  reason: collision with root package name */
    public static final vm f5861n;

    /* renamed from: o  reason: collision with root package name */
    public static final vm f5862o;

    /* renamed from: p  reason: collision with root package name */
    public static final vm f5863p;

    /* renamed from: q  reason: collision with root package name */
    public static final vm f5864q;

    /* renamed from: r  reason: collision with root package name */
    public static final vm f5865r;

    /* renamed from: s  reason: collision with root package name */
    public static final vm f5866s;

    /* renamed from: t  reason: collision with root package name */
    public static final vm f5867t;

    /* renamed from: u  reason: collision with root package name */
    public static final vm f5868u;

    /* renamed from: v  reason: collision with root package name */
    public static final vm f5869v;

    /* renamed from: w  reason: collision with root package name */
    public static final vm f5870w;

    /* renamed from: x  reason: collision with root package name */
    private static final /* synthetic */ vm[] f5871x;

    /* renamed from: e  reason: collision with root package name */
    private final wm f5872e;

    static {
        vm vmVar = new vm("DOUBLE", 0, wm.DOUBLE, 1);
        f5853f = vmVar;
        vm vmVar2 = new vm("FLOAT", 1, wm.FLOAT, 5);
        f5854g = vmVar2;
        wm wmVar = wm.LONG;
        vm vmVar3 = new vm("INT64", 2, wmVar, 0);
        f5855h = vmVar3;
        vm vmVar4 = new vm("UINT64", 3, wmVar, 0);
        f5856i = vmVar4;
        wm wmVar2 = wm.INT;
        vm vmVar5 = new vm("INT32", 4, wmVar2, 0);
        f5857j = vmVar5;
        vm vmVar6 = new vm("FIXED64", 5, wmVar, 1);
        f5858k = vmVar6;
        vm vmVar7 = new vm("FIXED32", 6, wmVar2, 5);
        f5859l = vmVar7;
        vm vmVar8 = new vm("BOOL", 7, wm.BOOLEAN, 0);
        f5860m = vmVar8;
        vm vmVar9 = new vm("STRING", 8, wm.STRING, 2);
        f5861n = vmVar9;
        wm wmVar3 = wm.MESSAGE;
        vm vmVar10 = new vm("GROUP", 9, wmVar3, 3);
        f5862o = vmVar10;
        vm vmVar11 = new vm("MESSAGE", 10, wmVar3, 2);
        f5863p = vmVar11;
        vm vmVar12 = new vm("BYTES", 11, wm.BYTE_STRING, 2);
        f5864q = vmVar12;
        vm vmVar13 = new vm("UINT32", 12, wmVar2, 0);
        f5865r = vmVar13;
        vm vmVar14 = new vm("ENUM", 13, wm.ENUM, 0);
        f5866s = vmVar14;
        vm vmVar15 = new vm("SFIXED32", 14, wmVar2, 5);
        f5867t = vmVar15;
        vm vmVar16 = new vm("SFIXED64", 15, wmVar, 1);
        f5868u = vmVar16;
        vm vmVar17 = new vm("SINT32", 16, wmVar2, 0);
        f5869v = vmVar17;
        vm vmVar18 = new vm("SINT64", 17, wmVar, 0);
        f5870w = vmVar18;
        f5871x = new vm[]{vmVar, vmVar2, vmVar3, vmVar4, vmVar5, vmVar6, vmVar7, vmVar8, vmVar9, vmVar10, vmVar11, vmVar12, vmVar13, vmVar14, vmVar15, vmVar16, vmVar17, vmVar18};
    }

    private vm(String str, int i8, wm wmVar, int i9) {
        this.f5872e = wmVar;
    }

    public static vm[] values() {
        return (vm[]) f5871x.clone();
    }

    public final wm d() {
        return this.f5872e;
    }
}
