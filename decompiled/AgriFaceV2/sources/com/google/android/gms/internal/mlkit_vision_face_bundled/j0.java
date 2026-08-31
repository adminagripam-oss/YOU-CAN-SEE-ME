package com.google.android.gms.internal.mlkit_vision_face_bundled;
/* loaded from: classes.dex */
final class j0 implements o0 {

    /* renamed from: a  reason: collision with root package name */
    private final int f5379a;

    /* renamed from: b  reason: collision with root package name */
    private final n0 f5380b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public j0(int i8, n0 n0Var) {
        this.f5379a = i8;
        this.f5380b = n0Var;
    }

    @Override // java.lang.annotation.Annotation
    public final Class annotationType() {
        return o0.class;
    }

    @Override // java.lang.annotation.Annotation
    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof o0) {
            o0 o0Var = (o0) obj;
            return this.f5379a == o0Var.zza() && this.f5380b.equals(o0Var.zzb());
        }
        return false;
    }

    @Override // java.lang.annotation.Annotation
    public final int hashCode() {
        return (this.f5379a ^ 14552422) + (this.f5380b.hashCode() ^ 2041407134);
    }

    @Override // java.lang.annotation.Annotation
    public final String toString() {
        return "@com.google.firebase.encoders.proto.Protobuf(tag=" + this.f5379a + "intEncoding=" + this.f5380b + ')';
    }

    @Override // com.google.android.gms.internal.mlkit_vision_face_bundled.o0
    public final int zza() {
        return this.f5379a;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_face_bundled.o0
    public final n0 zzb() {
        return this.f5380b;
    }
}
