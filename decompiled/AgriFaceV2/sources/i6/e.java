package i6;

import java.nio.ByteBuffer;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;
/* loaded from: classes.dex */
public final class e implements h {

    /* renamed from: a  reason: collision with root package name */
    public static final e f7533a = new e();

    private e() {
    }

    @Override // i6.h
    public ByteBuffer a(Object obj) {
        p pVar;
        String obj2;
        if (obj == null) {
            return null;
        }
        Object a8 = g.a(obj);
        if (a8 instanceof String) {
            pVar = p.f7553b;
            obj2 = JSONObject.quote((String) a8);
        } else {
            pVar = p.f7553b;
            obj2 = a8.toString();
        }
        return pVar.a(obj2);
    }

    @Override // i6.h
    public Object b(ByteBuffer byteBuffer) {
        if (byteBuffer == null) {
            return null;
        }
        try {
            JSONTokener jSONTokener = new JSONTokener(p.f7553b.b(byteBuffer));
            Object nextValue = jSONTokener.nextValue();
            if (jSONTokener.more()) {
                throw new IllegalArgumentException("Invalid JSON");
            }
            return nextValue;
        } catch (JSONException e8) {
            throw new IllegalArgumentException("Invalid JSON", e8);
        }
    }
}
