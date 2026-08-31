package b0;

import java.util.ArrayList;
import java.util.List;
/* loaded from: classes.dex */
public class l2 {

    /* renamed from: a  reason: collision with root package name */
    private final List f4277a;

    public l2(List list) {
        this.f4277a = new ArrayList(list);
    }

    public static String d(l2 l2Var) {
        ArrayList arrayList = new ArrayList();
        for (h2 h2Var : l2Var.f4277a) {
            arrayList.add(h2Var.getClass().getSimpleName());
        }
        return String.join(" | ", arrayList);
    }

    public boolean a(Class cls) {
        for (h2 h2Var : this.f4277a) {
            if (cls.isAssignableFrom(h2Var.getClass())) {
                return true;
            }
        }
        return false;
    }

    public h2 b(Class cls) {
        for (h2 h2Var : this.f4277a) {
            if (h2Var.getClass() == cls) {
                return h2Var;
            }
        }
        return null;
    }

    public List c(Class cls) {
        ArrayList arrayList = new ArrayList();
        for (h2 h2Var : this.f4277a) {
            if (cls.isAssignableFrom(h2Var.getClass())) {
                arrayList.add(h2Var);
            }
        }
        return arrayList;
    }
}
