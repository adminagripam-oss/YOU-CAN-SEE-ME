package a4;
/* loaded from: classes.dex */
public enum p9 implements u1 {
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
    private final int f956e;

    p9(int i8) {
        this.f956e = i8;
    }

    @Override // a4.u1
    public final int zza() {
        return this.f956e;
    }
}
