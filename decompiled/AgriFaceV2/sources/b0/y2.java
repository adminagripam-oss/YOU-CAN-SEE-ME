package b0;

import android.util.ArrayMap;
import android.util.Pair;
import java.util.Map;
import java.util.Set;
/* loaded from: classes.dex */
public class y2 {

    /* renamed from: b  reason: collision with root package name */
    private static final y2 f4485b = new y2(new ArrayMap());

    /* renamed from: a  reason: collision with root package name */
    protected final Map f4486a;

    /* JADX INFO: Access modifiers changed from: protected */
    public y2(Map map) {
        this.f4486a = map;
    }

    public static y2 a(Pair pair) {
        ArrayMap arrayMap = new ArrayMap();
        arrayMap.put((String) pair.first, pair.second);
        return new y2(arrayMap);
    }

    public static y2 b() {
        return f4485b;
    }

    public static y2 c(y2 y2Var) {
        ArrayMap arrayMap = new ArrayMap();
        for (String str : y2Var.e()) {
            arrayMap.put(str, y2Var.d(str));
        }
        return new y2(arrayMap);
    }

    public Object d(String str) {
        return this.f4486a.get(str);
    }

    public Set e() {
        return this.f4486a.keySet();
    }

    public final String toString() {
        return "android.hardware.camera2.CaptureRequest.setTag.CX";
    }
}
