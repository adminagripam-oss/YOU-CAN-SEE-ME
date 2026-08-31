package v1;

import d7.k;
import java.util.LinkedHashMap;
import java.util.Map;
/* loaded from: classes.dex */
public abstract class a {

    /* renamed from: a  reason: collision with root package name */
    private final Map f12321a = new LinkedHashMap();

    /* renamed from: v1.a$a  reason: collision with other inner class name */
    /* loaded from: classes.dex */
    public static final class C0159a extends a {

        /* renamed from: b  reason: collision with root package name */
        public static final C0159a f12322b = new C0159a();

        private C0159a() {
        }

        @Override // v1.a
        public Object a(b bVar) {
            k.e(bVar, "key");
            return null;
        }
    }

    /* loaded from: classes.dex */
    public interface b {
    }

    public abstract Object a(b bVar);

    public final Map b() {
        return this.f12321a;
    }
}
