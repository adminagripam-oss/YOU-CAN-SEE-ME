package d5;

import java.lang.annotation.Annotation;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
/* loaded from: classes.dex */
public final class c {

    /* renamed from: a  reason: collision with root package name */
    private final String f6444a;

    /* renamed from: b  reason: collision with root package name */
    private final Map f6445b;

    /* loaded from: classes.dex */
    public static final class b {

        /* renamed from: a  reason: collision with root package name */
        private final String f6446a;

        /* renamed from: b  reason: collision with root package name */
        private Map f6447b = null;

        b(String str) {
            this.f6446a = str;
        }

        public c a() {
            return new c(this.f6446a, this.f6447b == null ? Collections.emptyMap() : Collections.unmodifiableMap(new HashMap(this.f6447b)));
        }

        public b b(Annotation annotation) {
            if (this.f6447b == null) {
                this.f6447b = new HashMap();
            }
            this.f6447b.put(annotation.annotationType(), annotation);
            return this;
        }
    }

    private c(String str, Map map) {
        this.f6444a = str;
        this.f6445b = map;
    }

    public static b a(String str) {
        return new b(str);
    }

    public static c d(String str) {
        return new c(str, Collections.emptyMap());
    }

    public String b() {
        return this.f6444a;
    }

    public Annotation c(Class cls) {
        return (Annotation) this.f6445b.get(cls);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof c) {
            c cVar = (c) obj;
            return this.f6444a.equals(cVar.f6444a) && this.f6445b.equals(cVar.f6445b);
        }
        return false;
    }

    public int hashCode() {
        return (this.f6444a.hashCode() * 31) + this.f6445b.hashCode();
    }

    public String toString() {
        return "FieldDescriptor{name=" + this.f6444a + ", properties=" + this.f6445b.values() + "}";
    }
}
