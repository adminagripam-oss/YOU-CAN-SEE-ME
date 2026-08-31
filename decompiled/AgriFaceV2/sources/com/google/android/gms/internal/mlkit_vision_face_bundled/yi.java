package com.google.android.gms.internal.mlkit_vision_face_bundled;

import java.io.IOException;
/* loaded from: classes.dex */
public abstract class yi implements ll {
    protected int zza = 0;

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract int f(wl wlVar);

    public final byte[] h() {
        try {
            int a8 = a();
            byte[] bArr = new byte[a8];
            rj rjVar = new rj(bArr, 0, a8);
            e(rjVar);
            rjVar.b();
            return bArr;
        } catch (IOException e8) {
            String name = getClass().getName();
            throw new RuntimeException("Serializing " + name + " to a byte array threw an IOException (should never happen).", e8);
        }
    }
}
