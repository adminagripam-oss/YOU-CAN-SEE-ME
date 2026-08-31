package l5;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
/* loaded from: classes.dex */
public class c {

    /* renamed from: a  reason: collision with root package name */
    private final Map f9061a = new HashMap();

    /* loaded from: classes.dex */
    public static class a {

        /* renamed from: a  reason: collision with root package name */
        private final Class f9062a;

        /* renamed from: b  reason: collision with root package name */
        private final h5.b f9063b;

        public a(Class cls, h5.b bVar) {
            this.f9062a = cls;
            this.f9063b = bVar;
        }

        final h5.b a() {
            return this.f9063b;
        }

        final Class b() {
            return this.f9062a;
        }
    }

    public c(Set set) {
        Iterator it = set.iterator();
        while (it.hasNext()) {
            a aVar = (a) it.next();
            this.f9061a.put(aVar.b(), aVar.a());
        }
    }
}
