package i6;

import java.util.Map;
import org.json.JSONObject;
/* loaded from: classes.dex */
public final class i {

    /* renamed from: a  reason: collision with root package name */
    public final String f7535a;

    /* renamed from: b  reason: collision with root package name */
    public final Object f7536b;

    public i(String str, Object obj) {
        this.f7535a = str;
        this.f7536b = obj;
    }

    public Object a(String str) {
        Object obj = this.f7536b;
        if (obj == null) {
            return null;
        }
        if (obj instanceof Map) {
            return ((Map) obj).get(str);
        }
        if (obj instanceof JSONObject) {
            return ((JSONObject) obj).opt(str);
        }
        throw new ClassCastException();
    }

    public Object b() {
        return this.f7536b;
    }

    public boolean c(String str) {
        Object obj = this.f7536b;
        if (obj == null) {
            return false;
        }
        if (obj instanceof Map) {
            return ((Map) obj).containsKey(str);
        }
        if (obj instanceof JSONObject) {
            return ((JSONObject) obj).has(str);
        }
        throw new ClassCastException();
    }
}
