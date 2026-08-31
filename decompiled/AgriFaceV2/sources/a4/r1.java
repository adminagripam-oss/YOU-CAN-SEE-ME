package a4;
/* loaded from: classes.dex */
final class r1 implements w1 {

    /* renamed from: a  reason: collision with root package name */
    private final int f1003a;

    /* renamed from: b  reason: collision with root package name */
    private final v1 f1004b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public r1(int i8, v1 v1Var) {
        this.f1003a = i8;
        this.f1004b = v1Var;
    }

    @Override // java.lang.annotation.Annotation
    public final Class annotationType() {
        return w1.class;
    }

    @Override // java.lang.annotation.Annotation
    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof w1) {
            w1 w1Var = (w1) obj;
            return this.f1003a == w1Var.zza() && this.f1004b.equals(w1Var.zzb());
        }
        return false;
    }

    @Override // java.lang.annotation.Annotation
    public final int hashCode() {
        return (this.f1003a ^ 14552422) + (this.f1004b.hashCode() ^ 2041407134);
    }

    @Override // java.lang.annotation.Annotation
    public final String toString() {
        return "@com.google.firebase.encoders.proto.Protobuf(tag=" + this.f1003a + "intEncoding=" + this.f1004b + ')';
    }

    @Override // a4.w1
    public final int zza() {
        return this.f1003a;
    }

    @Override // a4.w1
    public final v1 zzb() {
        return this.f1004b;
    }
}
