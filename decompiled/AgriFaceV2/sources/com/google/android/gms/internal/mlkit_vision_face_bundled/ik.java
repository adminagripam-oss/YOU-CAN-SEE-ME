package com.google.android.gms.internal.mlkit_vision_face_bundled;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class ik implements jl {

    /* renamed from: a  reason: collision with root package name */
    private static final ik f5378a = new ik();

    private ik() {
    }

    public static ik c() {
        return f5378a;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_face_bundled.jl
    public final il a(Class cls) {
        if (ok.class.isAssignableFrom(cls)) {
            try {
                return (il) ok.x(cls.asSubclass(ok.class)).t(3, null, null);
            } catch (Exception e8) {
                throw new RuntimeException("Unable to get message info for ".concat(cls.getName()), e8);
            }
        }
        throw new IllegalArgumentException("Unsupported message type: ".concat(cls.getName()));
    }

    @Override // com.google.android.gms.internal.mlkit_vision_face_bundled.jl
    public final boolean b(Class cls) {
        return ok.class.isAssignableFrom(cls);
    }
}
