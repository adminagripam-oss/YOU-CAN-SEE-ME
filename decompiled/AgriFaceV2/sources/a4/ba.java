package a4;
/* loaded from: classes.dex */
public enum ba implements u1 {
    TYPE_UNKNOWN(0),
    TYPE_THIN(1),
    TYPE_THICK(2),
    TYPE_GMV(3);
    

    /* renamed from: e  reason: collision with root package name */
    private final int f293e;

    ba(int i8) {
        this.f293e = i8;
    }

    @Override // a4.u1
    public final int zza() {
        return this.f293e;
    }
}
