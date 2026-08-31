package y5;

import java.util.List;
import w5.e0;
/* loaded from: classes.dex */
public abstract class b implements e {
    private Boolean i(String str) {
        Object a8 = a(str);
        if (a8 instanceof Boolean) {
            return (Boolean) a8;
        }
        return null;
    }

    private String k() {
        return (String) a("sql");
    }

    private List l() {
        return (List) a("arguments");
    }

    @Override // y5.e
    public e0 b() {
        return new e0(k(), l());
    }

    @Override // y5.e
    public boolean c() {
        return Boolean.TRUE.equals(a("noResult"));
    }

    @Override // y5.e
    public Boolean d() {
        return i("inTransaction");
    }

    @Override // y5.e
    public Integer e() {
        return (Integer) a("transactionId");
    }

    @Override // y5.e
    public boolean f() {
        return h("transactionId") && e() == null;
    }

    public boolean j() {
        return Boolean.TRUE.equals(a("continueOnError"));
    }

    public String toString() {
        return g() + " " + k() + " " + l();
    }
}
