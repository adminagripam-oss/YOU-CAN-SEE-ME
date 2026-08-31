package com.google.android.gms.internal.mlkit_vision_face_bundled;
/* loaded from: classes.dex */
final class dl implements jl {

    /* renamed from: a  reason: collision with root package name */
    private final jl[] f5202a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public dl(jl... jlVarArr) {
        this.f5202a = jlVarArr;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_face_bundled.jl
    public final il a(Class cls) {
        for (int i8 = 0; i8 < 2; i8++) {
            jl jlVar = this.f5202a[i8];
            if (jlVar.b(cls)) {
                return jlVar.a(cls);
            }
        }
        throw new UnsupportedOperationException("No factory is available for message type: ".concat(cls.getName()));
    }

    @Override // com.google.android.gms.internal.mlkit_vision_face_bundled.jl
    public final boolean b(Class cls) {
        for (int i8 = 0; i8 < 2; i8++) {
            if (this.f5202a[i8].b(cls)) {
                return true;
            }
        }
        return false;
    }
}
