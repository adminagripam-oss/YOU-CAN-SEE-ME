package z3;

import java.util.HashMap;
import java.util.Map;
/* loaded from: classes.dex */
public final class n implements e5.b {

    /* renamed from: d  reason: collision with root package name */
    private static final d5.d f13918d = new d5.d() { // from class: z3.m
        @Override // d5.d
        public final void a(Object obj, Object obj2) {
            d5.e eVar = (d5.e) obj2;
            int i8 = n.f13919e;
            throw new d5.b("Couldn't find encoder for type ".concat(String.valueOf(obj.getClass().getCanonicalName())));
        }
    };

    /* renamed from: e  reason: collision with root package name */
    public static final /* synthetic */ int f13919e = 0;

    /* renamed from: a  reason: collision with root package name */
    private final Map f13920a = new HashMap();

    /* renamed from: b  reason: collision with root package name */
    private final Map f13921b = new HashMap();

    /* renamed from: c  reason: collision with root package name */
    private final d5.d f13922c = f13918d;

    @Override // e5.b
    public final /* bridge */ /* synthetic */ e5.b a(Class cls, d5.d dVar) {
        this.f13920a.put(cls, dVar);
        this.f13921b.remove(cls);
        return this;
    }

    public final o b() {
        return new o(new HashMap(this.f13920a), new HashMap(this.f13921b), this.f13922c);
    }
}
