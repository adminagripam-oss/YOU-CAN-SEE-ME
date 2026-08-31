package com.google.android.gms.internal.mlkit_vision_face_bundled;
/* loaded from: classes.dex */
final class zj {

    /* renamed from: a  reason: collision with root package name */
    private final Object f5983a;

    /* renamed from: b  reason: collision with root package name */
    private final int f5984b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zj(Object obj, int i8) {
        this.f5983a = obj;
        this.f5984b = i8;
    }

    public final boolean equals(Object obj) {
        if (obj instanceof zj) {
            zj zjVar = (zj) obj;
            return this.f5983a == zjVar.f5983a && this.f5984b == zjVar.f5984b;
        }
        return false;
    }

    public final int hashCode() {
        return (System.identityHashCode(this.f5983a) * 65535) + this.f5984b;
    }
}
