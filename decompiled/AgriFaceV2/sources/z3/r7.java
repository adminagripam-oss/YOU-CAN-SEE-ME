package z3;
/* loaded from: classes.dex */
public enum r7 implements h {
    UNKNOWN_FORMAT(0),
    NV16(1),
    NV21(2),
    YV12(3),
    YUV_420_888(7),
    JPEG(8),
    BITMAP(4),
    CM_SAMPLE_BUFFER_REF(5),
    UI_IMAGE(6),
    CV_PIXEL_BUFFER_REF(9);
    

    /* renamed from: e  reason: collision with root package name */
    private final int f14052e;

    r7(int i8) {
        this.f14052e = i8;
    }

    @Override // z3.h
    public final int zza() {
        return this.f14052e;
    }
}
