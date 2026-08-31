package androidx.appcompat.app;

import java.util.LinkedHashSet;
import java.util.Locale;
/* loaded from: classes.dex */
abstract class v {
    private static androidx.core.os.h a(androidx.core.os.h hVar, androidx.core.os.h hVar2) {
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        int i8 = 0;
        while (i8 < hVar.f() + hVar2.f()) {
            Locale c8 = i8 < hVar.f() ? hVar.c(i8) : hVar2.c(i8 - hVar.f());
            if (c8 != null) {
                linkedHashSet.add(c8);
            }
            i8++;
        }
        return androidx.core.os.h.a((Locale[]) linkedHashSet.toArray(new Locale[linkedHashSet.size()]));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static androidx.core.os.h b(androidx.core.os.h hVar, androidx.core.os.h hVar2) {
        return (hVar == null || hVar.e()) ? androidx.core.os.h.d() : a(hVar, hVar2);
    }
}
