package a4;
/* loaded from: classes.dex */
public enum l9 implements u1 {
    UNKNOWN_PERFORMANCE(0),
    FAST(1),
    ACCURATE(2);
    

    /* renamed from: e  reason: collision with root package name */
    private final int f789e;

    l9(int i8) {
        this.f789e = i8;
    }

    @Override // a4.u1
    public final int zza() {
        return this.f789e;
    }
}
