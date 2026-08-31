package s6;

import java.util.Collections;
import java.util.Map;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public abstract class d0 extends c0 {
    public static int a(int i8) {
        if (i8 < 0) {
            return i8;
        }
        if (i8 < 3) {
            return i8 + 1;
        }
        if (i8 < 1073741824) {
            return (int) ((i8 / 0.75f) + 1.0f);
        }
        return Integer.MAX_VALUE;
    }

    public static final Map b(r6.j jVar) {
        d7.k.e(jVar, "pair");
        Map singletonMap = Collections.singletonMap(jVar.c(), jVar.d());
        d7.k.d(singletonMap, "singletonMap(...)");
        return singletonMap;
    }

    public static final Map c(Map map) {
        d7.k.e(map, "<this>");
        Map.Entry entry = (Map.Entry) map.entrySet().iterator().next();
        Map singletonMap = Collections.singletonMap(entry.getKey(), entry.getValue());
        d7.k.d(singletonMap, "with(...)");
        return singletonMap;
    }
}
