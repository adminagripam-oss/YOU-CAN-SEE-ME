package com.google.android.gms.internal.mlkit_vision_face_bundled;

import java.io.IOException;
/* loaded from: classes.dex */
public final class tj extends IOException {
    /* JADX INFO: Access modifiers changed from: package-private */
    public tj(String str, Throwable th) {
        super("CodedOutputStream was writing to a flat byte array and ran out of space.: ".concat(String.valueOf(str)), th);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public tj(Throwable th) {
        super("CodedOutputStream was writing to a flat byte array and ran out of space.", th);
    }
}
