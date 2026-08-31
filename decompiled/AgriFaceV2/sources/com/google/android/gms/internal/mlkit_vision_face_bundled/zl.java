package com.google.android.gms.internal.mlkit_vision_face_bundled;

import java.util.Collections;
import java.util.List;
import java.util.Map;
/* loaded from: classes.dex */
final class zl extends gm {
    /* JADX INFO: Access modifiers changed from: package-private */
    public zl() {
        super(null);
    }

    @Override // com.google.android.gms.internal.mlkit_vision_face_bundled.gm
    public final void a() {
        if (!j()) {
            for (int i8 = 0; i8 < c(); i8++) {
                Map.Entry g8 = g(i8);
                ((ek) ((am) g8).d()).c();
                g8.setValue(Collections.unmodifiableList((List) g8.getValue()));
            }
            for (Map.Entry entry : d()) {
                ((ek) entry.getKey()).c();
                entry.setValue(Collections.unmodifiableList((List) entry.getValue()));
            }
        }
        super.a();
    }
}
