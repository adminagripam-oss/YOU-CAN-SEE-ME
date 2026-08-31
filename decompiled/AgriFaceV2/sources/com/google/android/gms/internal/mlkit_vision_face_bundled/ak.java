package com.google.android.gms.internal.mlkit_vision_face_bundled;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
/* loaded from: classes.dex */
public final class ak {

    /* renamed from: b  reason: collision with root package name */
    static final ak f5131b = new ak(true);

    /* renamed from: a  reason: collision with root package name */
    private final Map f5132a;

    ak() {
        this.f5132a = new HashMap();
    }

    public static ak a() {
        int i8 = tl.f5621d;
        return new ak();
    }

    public final nk b(ll llVar, int i8) {
        return (nk) this.f5132a.get(new zj(llVar, i8));
    }

    public final void c(nk nkVar) {
        this.f5132a.put(new zj(nkVar.f5490a, 202056002), nkVar);
    }

    ak(boolean z7) {
        this.f5132a = Collections.emptyMap();
    }
}
