package t4;

import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
/* loaded from: classes.dex */
public final class c {

    /* renamed from: a  reason: collision with root package name */
    private final Map f11823a;

    /* renamed from: b  reason: collision with root package name */
    private final Map f11824b;

    /* loaded from: classes.dex */
    public static final class b {

        /* renamed from: a  reason: collision with root package name */
        Map f11825a;

        /* renamed from: b  reason: collision with root package name */
        Map f11826b;

        private b() {
            this.f11825a = new HashMap();
            this.f11826b = new HashMap();
        }

        public b a(Enum r22, Object obj) {
            this.f11825a.put(r22, obj);
            this.f11826b.put(obj, r22);
            return this;
        }

        public c b() {
            return new c(Collections.unmodifiableMap(this.f11825a), Collections.unmodifiableMap(this.f11826b));
        }
    }

    private c(Map map, Map map2) {
        this.f11823a = map;
        this.f11824b = map2;
    }

    public static b a() {
        return new b();
    }

    public Object b(Enum r42) {
        Object obj = this.f11823a.get(r42);
        if (obj != null) {
            return obj;
        }
        throw new GeneralSecurityException("Unable to convert proto enum: " + r42);
    }

    public Enum c(Object obj) {
        Enum r02 = (Enum) this.f11824b.get(obj);
        if (r02 != null) {
            return r02;
        }
        throw new GeneralSecurityException("Unable to convert object enum: " + obj);
    }
}
