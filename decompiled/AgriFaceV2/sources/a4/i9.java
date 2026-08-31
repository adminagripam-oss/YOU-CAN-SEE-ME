package a4;
/* loaded from: classes.dex */
public enum i9 implements u1 {
    UNKNOWN_CONTOURS(0),
    NO_CONTOURS(1),
    ALL_CONTOURS(2);
    

    /* renamed from: e  reason: collision with root package name */
    private final int f687e;

    i9(int i8) {
        this.f687e = i8;
    }

    @Override // a4.u1
    public final int zza() {
        return this.f687e;
    }
}
