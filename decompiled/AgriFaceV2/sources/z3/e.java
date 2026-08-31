package z3;
/* loaded from: classes.dex */
final class e implements j {

    /* renamed from: a  reason: collision with root package name */
    private final int f13505a;

    /* renamed from: b  reason: collision with root package name */
    private final i f13506b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public e(int i8, i iVar) {
        this.f13505a = i8;
        this.f13506b = iVar;
    }

    @Override // java.lang.annotation.Annotation
    public final Class annotationType() {
        return j.class;
    }

    @Override // java.lang.annotation.Annotation
    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof j) {
            j jVar = (j) obj;
            return this.f13505a == jVar.zza() && this.f13506b.equals(jVar.zzb());
        }
        return false;
    }

    @Override // java.lang.annotation.Annotation
    public final int hashCode() {
        return (this.f13505a ^ 14552422) + (this.f13506b.hashCode() ^ 2041407134);
    }

    @Override // java.lang.annotation.Annotation
    public final String toString() {
        return "@com.google.firebase.encoders.proto.Protobuf(tag=" + this.f13505a + "intEncoding=" + this.f13506b + ')';
    }

    @Override // z3.j
    public final int zza() {
        return this.f13505a;
    }

    @Override // z3.j
    public final i zzb() {
        return this.f13506b;
    }
}
