package m5;

import java.util.HashMap;
import java.util.Map;
/* loaded from: classes.dex */
public abstract class e {

    /* renamed from: a  reason: collision with root package name */
    private final Map f9145a = new HashMap();

    protected abstract Object a(Object obj);

    public Object b(Object obj) {
        synchronized (this.f9145a) {
            if (this.f9145a.containsKey(obj)) {
                return this.f9145a.get(obj);
            }
            Object a8 = a(obj);
            this.f9145a.put(obj, a8);
            return a8;
        }
    }
}
