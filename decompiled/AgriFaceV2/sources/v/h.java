package v;

import androidx.camera.camera2.internal.compat.quirk.CaptureSessionOnClosedNotCalledQuirk;
import b0.l2;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import r.k4;
/* loaded from: classes.dex */
public class h {

    /* renamed from: a  reason: collision with root package name */
    private final CaptureSessionOnClosedNotCalledQuirk f12292a;

    /* loaded from: classes.dex */
    public interface a {
        void a(k4 k4Var);
    }

    public h(l2 l2Var) {
        this.f12292a = (CaptureSessionOnClosedNotCalledQuirk) l2Var.b(CaptureSessionOnClosedNotCalledQuirk.class);
    }

    private void a(Set set) {
        Iterator it = set.iterator();
        while (it.hasNext()) {
            k4 k4Var = (k4) it.next();
            k4Var.a().q(k4Var);
        }
    }

    private void b(Set set) {
        Iterator it = set.iterator();
        while (it.hasNext()) {
            k4 k4Var = (k4) it.next();
            k4Var.a().r(k4Var);
        }
    }

    public void c(k4 k4Var, List list, List list2, a aVar) {
        k4 k4Var2;
        k4 k4Var3;
        if (d()) {
            LinkedHashSet linkedHashSet = new LinkedHashSet();
            Iterator it = list.iterator();
            while (it.hasNext() && (k4Var3 = (k4) it.next()) != k4Var) {
                linkedHashSet.add(k4Var3);
            }
            b(linkedHashSet);
        }
        aVar.a(k4Var);
        if (d()) {
            LinkedHashSet linkedHashSet2 = new LinkedHashSet();
            Iterator it2 = list2.iterator();
            while (it2.hasNext() && (k4Var2 = (k4) it2.next()) != k4Var) {
                linkedHashSet2.add(k4Var2);
            }
            a(linkedHashSet2);
        }
    }

    public boolean d() {
        return this.f12292a != null;
    }
}
