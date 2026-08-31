package com.google.android.gms.internal.mlkit_vision_face_bundled;
/* loaded from: classes.dex */
final class lh extends qh {

    /* renamed from: a  reason: collision with root package name */
    private String f5416a;

    /* renamed from: b  reason: collision with root package name */
    private boolean f5417b;

    /* renamed from: c  reason: collision with root package name */
    private int f5418c;

    /* renamed from: d  reason: collision with root package name */
    private byte f5419d;

    @Override // com.google.android.gms.internal.mlkit_vision_face_bundled.qh
    public final qh a(boolean z7) {
        this.f5417b = true;
        this.f5419d = (byte) (1 | this.f5419d);
        return this;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_face_bundled.qh
    public final qh b(int i8) {
        this.f5418c = 1;
        this.f5419d = (byte) (this.f5419d | 2);
        return this;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_face_bundled.qh
    public final rh c() {
        String str;
        if (this.f5419d != 3 || (str = this.f5416a) == null) {
            StringBuilder sb = new StringBuilder();
            if (this.f5416a == null) {
                sb.append(" libraryName");
            }
            if ((this.f5419d & 1) == 0) {
                sb.append(" enableFirelog");
            }
            if ((this.f5419d & 2) == 0) {
                sb.append(" firelogEventType");
            }
            throw new IllegalStateException("Missing required properties:".concat(sb.toString()));
        }
        return new nh(str, this.f5417b, this.f5418c, null);
    }

    public final qh d(String str) {
        this.f5416a = "optional-module-face";
        return this;
    }
}
