package com.google.android.gms.internal.mlkit_vision_face_bundled;

import android.content.Context;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes.dex */
public final class th implements ph {

    /* renamed from: a  reason: collision with root package name */
    final List f5619a;

    public th(Context context, rh rhVar) {
        ArrayList arrayList = new ArrayList();
        this.f5619a = arrayList;
        if (rhVar.c()) {
            arrayList.add(new fi(context, rhVar));
        }
    }

    @Override // com.google.android.gms.internal.mlkit_vision_face_bundled.ph
    public final void a(oh ohVar) {
        for (ph phVar : this.f5619a) {
            phVar.a(ohVar);
        }
    }
}
