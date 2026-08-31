package a4;

import java.util.Iterator;
/* loaded from: classes.dex */
public abstract class t0 {
    /* JADX INFO: Access modifiers changed from: package-private */
    public static void a(Iterator it) {
        it.getClass();
        while (it.hasNext()) {
            it.next();
            it.remove();
        }
    }
}
