package a4;

import java.util.Map;
import java.util.Set;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public abstract class s implements c1 {

    /* renamed from: e  reason: collision with root package name */
    private transient Set f1038e;

    /* renamed from: f  reason: collision with root package name */
    private transient Map f1039f;

    @Override // a4.c1
    public final Map b() {
        Map map = this.f1039f;
        if (map == null) {
            Map e8 = e();
            this.f1039f = e8;
            return e8;
        }
        return map;
    }

    @Override // a4.c1
    public final Set c() {
        Set set = this.f1038e;
        if (set == null) {
            Set f8 = f();
            this.f1038e = f8;
            return f8;
        }
        return set;
    }

    abstract Map e();

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof c1) {
            return b().equals(((c1) obj).b());
        }
        return false;
    }

    abstract Set f();

    public final int hashCode() {
        return b().hashCode();
    }

    public final String toString() {
        return ((i) b()).f658g.toString();
    }
}
