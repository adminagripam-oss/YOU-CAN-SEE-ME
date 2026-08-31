package a4;

import java.util.HashMap;
import java.util.Map;
/* loaded from: classes.dex */
public final class a2 implements e5.b {

    /* renamed from: d  reason: collision with root package name */
    private static final d5.d f234d = new d5.d() { // from class: a4.z1
        @Override // d5.d
        public final void a(Object obj, Object obj2) {
            d5.e eVar = (d5.e) obj2;
            int i8 = a2.f235e;
            throw new d5.b("Couldn't find encoder for type ".concat(String.valueOf(obj.getClass().getCanonicalName())));
        }
    };

    /* renamed from: e  reason: collision with root package name */
    public static final /* synthetic */ int f235e = 0;

    /* renamed from: a  reason: collision with root package name */
    private final Map f236a = new HashMap();

    /* renamed from: b  reason: collision with root package name */
    private final Map f237b = new HashMap();

    /* renamed from: c  reason: collision with root package name */
    private final d5.d f238c = f234d;

    @Override // e5.b
    public final /* bridge */ /* synthetic */ e5.b a(Class cls, d5.d dVar) {
        this.f236a.put(cls, dVar);
        this.f237b.remove(cls);
        return this;
    }

    public final b2 b() {
        return new b2(new HashMap(this.f236a), new HashMap(this.f237b), this.f238c);
    }
}
