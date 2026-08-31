package com.google.android.gms.internal.mlkit_vision_face_bundled;

import java.util.List;
import java.util.Map;
/* loaded from: classes.dex */
final class ck extends bk {
    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.mlkit_vision_face_bundled.bk
    public final void a(Object obj) {
        ((lk) obj).zzb.h();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.mlkit_vision_face_bundled.bk
    public final void b(xm xmVar, Map.Entry entry) {
        vm vmVar = vm.f5853f;
        switch (((mk) entry.getKey()).f5442f.ordinal()) {
            case 0:
                yl.B(202056002, (List) entry.getValue(), xmVar, false);
                return;
            case 1:
                yl.F(202056002, (List) entry.getValue(), xmVar, false);
                return;
            case 2:
                yl.I(202056002, (List) entry.getValue(), xmVar, false);
                return;
            case 3:
                yl.h(202056002, (List) entry.getValue(), xmVar, false);
                return;
            case 4:
                yl.H(202056002, (List) entry.getValue(), xmVar, false);
                return;
            case 5:
                yl.E(202056002, (List) entry.getValue(), xmVar, false);
                return;
            case androidx.browser.customtabs.b.TAB_HIDDEN /* 6 */:
                yl.D(202056002, (List) entry.getValue(), xmVar, false);
                return;
            case 7:
                yl.z(202056002, (List) entry.getValue(), xmVar, false);
                return;
            case 8:
                yl.f(202056002, (List) entry.getValue(), xmVar);
                return;
            case 9:
                List list = (List) entry.getValue();
                if (list == null || list.isEmpty()) {
                    return;
                }
                yl.G(202056002, (List) entry.getValue(), xmVar, tl.a().b(list.get(0).getClass()));
                return;
            case 10:
                List list2 = (List) entry.getValue();
                if (list2 == null || list2.isEmpty()) {
                    return;
                }
                yl.a(202056002, (List) entry.getValue(), xmVar, tl.a().b(list2.get(0).getClass()));
                return;
            case 11:
                yl.A(202056002, (List) entry.getValue(), xmVar);
                return;
            case 12:
                yl.g(202056002, (List) entry.getValue(), xmVar, false);
                return;
            case 13:
                yl.H(202056002, (List) entry.getValue(), xmVar, false);
                return;
            case 14:
                yl.b(202056002, (List) entry.getValue(), xmVar, false);
                return;
            case 15:
                yl.c(202056002, (List) entry.getValue(), xmVar, false);
                return;
            case 16:
                yl.d(202056002, (List) entry.getValue(), xmVar, false);
                return;
            case 17:
                yl.e(202056002, (List) entry.getValue(), xmVar, false);
                return;
            default:
                return;
        }
    }
}
