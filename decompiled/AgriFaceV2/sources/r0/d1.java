package r0;

import android.util.Size;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes.dex */
public interface d1 {

    /* renamed from: a  reason: collision with root package name */
    public static final d1 f11031a = new a();

    /* loaded from: classes.dex */
    class a implements d1 {
        a() {
        }

        @Override // r0.d1
        public List d(y.a0 a0Var) {
            return new ArrayList();
        }
    }

    default t0.g a(Size size, y.a0 a0Var) {
        return null;
    }

    default v b(Size size, y.a0 a0Var) {
        return v.f11273g;
    }

    default t0.g c(v vVar, y.a0 a0Var) {
        return null;
    }

    List d(y.a0 a0Var);
}
