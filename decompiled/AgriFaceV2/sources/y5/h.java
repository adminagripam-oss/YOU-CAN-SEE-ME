package y5;

import java.util.HashMap;
import java.util.Map;
import w5.e0;
/* loaded from: classes.dex */
public abstract class h {
    public static Map a(e eVar) {
        e0 b8 = eVar.b();
        if (b8 != null) {
            HashMap hashMap = new HashMap();
            hashMap.put("sql", b8.c());
            hashMap.put("arguments", b8.b());
            return hashMap;
        }
        return null;
    }
}
