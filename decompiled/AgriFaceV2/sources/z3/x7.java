package z3;
/* loaded from: classes.dex */
public enum x7 implements h {
    SOURCE_UNKNOWN(0),
    BITMAP(1),
    BYTEARRAY(2),
    BYTEBUFFER(3),
    FILEPATH(4),
    ANDROID_MEDIA_IMAGE(5);
    

    /* renamed from: e  reason: collision with root package name */
    private final int f14325e;

    x7(int i8) {
        this.f14325e = i8;
    }

    @Override // z3.h
    public final int zza() {
        return this.f14325e;
    }
}
