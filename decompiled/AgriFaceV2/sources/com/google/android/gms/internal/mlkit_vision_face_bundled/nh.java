package com.google.android.gms.internal.mlkit_vision_face_bundled;
/* loaded from: classes.dex */
final class nh extends rh {

    /* renamed from: a  reason: collision with root package name */
    private final String f5476a;

    /* renamed from: b  reason: collision with root package name */
    private final boolean f5477b;

    /* renamed from: c  reason: collision with root package name */
    private final int f5478c;

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ nh(String str, boolean z7, int i8, mh mhVar) {
        this.f5476a = str;
        this.f5477b = z7;
        this.f5478c = i8;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_face_bundled.rh
    public final int a() {
        return this.f5478c;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_face_bundled.rh
    public final String b() {
        return this.f5476a;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_face_bundled.rh
    public final boolean c() {
        return this.f5477b;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof rh) {
            rh rhVar = (rh) obj;
            if (this.f5476a.equals(rhVar.b()) && this.f5477b == rhVar.c() && this.f5478c == rhVar.a()) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        return ((((this.f5476a.hashCode() ^ 1000003) * 1000003) ^ (true != this.f5477b ? 1237 : 1231)) * 1000003) ^ this.f5478c;
    }

    public final String toString() {
        return "MLKitLoggingOptions{libraryName=" + this.f5476a + ", enableFirelog=" + this.f5477b + ", firelogEventType=" + this.f5478c + "}";
    }
}
