package i6;

import java.nio.ByteBuffer;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes.dex */
public final class f implements k {

    /* renamed from: a  reason: collision with root package name */
    public static final f f7534a = new f();

    private f() {
    }

    @Override // i6.k
    public ByteBuffer a(i iVar) {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("method", iVar.f7535a);
            jSONObject.put("args", g.a(iVar.f7536b));
            return e.f7533a.a(jSONObject);
        } catch (JSONException e8) {
            throw new IllegalArgumentException("Invalid JSON", e8);
        }
    }

    @Override // i6.k
    public ByteBuffer b(Object obj) {
        return e.f7533a.a(new JSONArray().put(g.a(obj)));
    }

    @Override // i6.k
    public ByteBuffer c(String str, String str2, Object obj, String str3) {
        return e.f7533a.a(new JSONArray().put(str).put(g.a(str2)).put(g.a(obj)).put(g.a(str3)));
    }

    @Override // i6.k
    public ByteBuffer d(String str, String str2, Object obj) {
        return e.f7533a.a(new JSONArray().put(str).put(g.a(str2)).put(g.a(obj)));
    }

    @Override // i6.k
    public i e(ByteBuffer byteBuffer) {
        try {
            Object b8 = e.f7533a.b(byteBuffer);
            if (b8 instanceof JSONObject) {
                JSONObject jSONObject = (JSONObject) b8;
                Object obj = jSONObject.get("method");
                Object g8 = g(jSONObject.opt("args"));
                if (obj instanceof String) {
                    return new i((String) obj, g8);
                }
            }
            throw new IllegalArgumentException("Invalid method call: " + b8);
        } catch (JSONException e8) {
            throw new IllegalArgumentException("Invalid JSON", e8);
        }
    }

    @Override // i6.k
    public Object f(ByteBuffer byteBuffer) {
        try {
            Object b8 = e.f7533a.b(byteBuffer);
            if (b8 instanceof JSONArray) {
                JSONArray jSONArray = (JSONArray) b8;
                if (jSONArray.length() == 1) {
                    return g(jSONArray.opt(0));
                }
                if (jSONArray.length() == 3) {
                    Object obj = jSONArray.get(0);
                    Object g8 = g(jSONArray.opt(1));
                    Object g9 = g(jSONArray.opt(2));
                    if ((obj instanceof String) && (g8 == null || (g8 instanceof String))) {
                        throw new d((String) obj, (String) g8, g9);
                    }
                }
            }
            throw new IllegalArgumentException("Invalid envelope: " + b8);
        } catch (JSONException e8) {
            throw new IllegalArgumentException("Invalid JSON", e8);
        }
    }

    Object g(Object obj) {
        if (obj == JSONObject.NULL) {
            return null;
        }
        return obj;
    }
}
