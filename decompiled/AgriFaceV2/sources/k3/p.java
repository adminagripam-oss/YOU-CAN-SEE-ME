package k3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
/* loaded from: classes.dex */
public abstract class p {

    /* loaded from: classes.dex */
    public static final class a {

        /* renamed from: a  reason: collision with root package name */
        private final List f8463a;

        /* renamed from: b  reason: collision with root package name */
        private final Object f8464b;

        /* synthetic */ a(Object obj, s0 s0Var) {
            q.g(obj);
            this.f8464b = obj;
            this.f8463a = new ArrayList();
        }

        public a a(String str, Object obj) {
            q.g(str);
            String valueOf = String.valueOf(obj);
            this.f8463a.add(str + "=" + valueOf);
            return this;
        }

        public String toString() {
            StringBuilder sb = new StringBuilder(100);
            sb.append(this.f8464b.getClass().getSimpleName());
            sb.append('{');
            int size = this.f8463a.size();
            for (int i8 = 0; i8 < size; i8++) {
                sb.append((String) this.f8463a.get(i8));
                if (i8 < size - 1) {
                    sb.append(", ");
                }
            }
            sb.append('}');
            return sb.toString();
        }
    }

    public static boolean a(Object obj, Object obj2) {
        if (obj != obj2) {
            return obj != null && obj.equals(obj2);
        }
        return true;
    }

    public static int b(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    public static a c(Object obj) {
        return new a(obj, null);
    }
}
