package b0;

import android.util.ArrayMap;
import java.util.Map;
/* loaded from: classes.dex */
public class c2 extends y2 {
    private c2(Map map) {
        super(map);
    }

    public static c2 g() {
        return new c2(new ArrayMap());
    }

    public static c2 h(y2 y2Var) {
        ArrayMap arrayMap = new ArrayMap();
        for (String str : y2Var.e()) {
            arrayMap.put(str, y2Var.d(str));
        }
        return new c2(arrayMap);
    }

    public void f(y2 y2Var) {
        Map map;
        Map map2 = this.f4486a;
        if (map2 == null || (map = y2Var.f4486a) == null) {
            return;
        }
        map2.putAll(map);
    }

    public void i(String str, Object obj) {
        this.f4486a.put(str, obj);
    }
}
