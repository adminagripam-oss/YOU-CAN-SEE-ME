package com.google.android.gms.internal.mlkit_vision_face_bundled;
/* loaded from: classes.dex */
public final class im extends RuntimeException {
    public im(ll llVar) {
        super("Message was missing required fields.  (Lite runtime could not determine which fields were missing).");
    }

    public final wk a() {
        return new wk(getMessage());
    }
}
