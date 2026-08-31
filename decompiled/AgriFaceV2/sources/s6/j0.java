package s6;

import java.util.Set;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public abstract class j0 extends i0 {
    public static Set b() {
        return z.f11603e;
    }

    public static final Set c(Set set) {
        d7.k.e(set, "<this>");
        int size = set.size();
        return size != 0 ? size != 1 ? set : i0.a(set.iterator().next()) : h0.b();
    }
}
