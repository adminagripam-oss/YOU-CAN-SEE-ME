package l;

import java.util.HashMap;
import java.util.Map;
import l.b;
/* loaded from: classes.dex */
public class a extends b {

    /* renamed from: i  reason: collision with root package name */
    private final HashMap f8806i = new HashMap();

    public boolean contains(Object obj) {
        return this.f8806i.containsKey(obj);
    }

    @Override // l.b
    protected b.c j(Object obj) {
        return (b.c) this.f8806i.get(obj);
    }

    @Override // l.b
    public Object n(Object obj, Object obj2) {
        b.c j8 = j(obj);
        if (j8 != null) {
            return j8.f8812f;
        }
        this.f8806i.put(obj, m(obj, obj2));
        return null;
    }

    @Override // l.b
    public Object o(Object obj) {
        Object o8 = super.o(obj);
        this.f8806i.remove(obj);
        return o8;
    }

    public Map.Entry p(Object obj) {
        if (contains(obj)) {
            return ((b.c) this.f8806i.get(obj)).f8814h;
        }
        return null;
    }
}
