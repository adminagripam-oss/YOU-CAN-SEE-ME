package s6;

import java.util.Collections;
import java.util.Set;
/* loaded from: classes.dex */
abstract class i0 {
    public static final Set a(Object obj) {
        Set singleton = Collections.singleton(obj);
        d7.k.d(singleton, "singleton(...)");
        return singleton;
    }
}
