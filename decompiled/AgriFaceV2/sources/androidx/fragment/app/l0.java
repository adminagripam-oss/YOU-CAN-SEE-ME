package androidx.fragment.app;

import android.util.Log;
import androidx.lifecycle.l0;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class l0 extends androidx.lifecycle.k0 {

    /* renamed from: k  reason: collision with root package name */
    private static final l0.b f3499k = new a();

    /* renamed from: g  reason: collision with root package name */
    private final boolean f3503g;

    /* renamed from: d  reason: collision with root package name */
    private final HashMap f3500d = new HashMap();

    /* renamed from: e  reason: collision with root package name */
    private final HashMap f3501e = new HashMap();

    /* renamed from: f  reason: collision with root package name */
    private final HashMap f3502f = new HashMap();

    /* renamed from: h  reason: collision with root package name */
    private boolean f3504h = false;

    /* renamed from: i  reason: collision with root package name */
    private boolean f3505i = false;

    /* renamed from: j  reason: collision with root package name */
    private boolean f3506j = false;

    /* loaded from: classes.dex */
    class a implements l0.b {
        a() {
        }

        @Override // androidx.lifecycle.l0.b
        public androidx.lifecycle.k0 a(Class cls) {
            return new l0(true);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public l0(boolean z7) {
        this.f3503g = z7;
    }

    private void i(String str, boolean z7) {
        l0 l0Var = (l0) this.f3501e.get(str);
        if (l0Var != null) {
            if (z7) {
                ArrayList arrayList = new ArrayList();
                arrayList.addAll(l0Var.f3501e.keySet());
                Iterator it = arrayList.iterator();
                while (it.hasNext()) {
                    l0Var.h((String) it.next(), true);
                }
            }
            l0Var.d();
            this.f3501e.remove(str);
        }
        androidx.lifecycle.n0 n0Var = (androidx.lifecycle.n0) this.f3502f.get(str);
        if (n0Var != null) {
            n0Var.a();
            this.f3502f.remove(str);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static l0 l(androidx.lifecycle.n0 n0Var) {
        return (l0) new androidx.lifecycle.l0(n0Var, f3499k).a(l0.class);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.lifecycle.k0
    public void d() {
        if (i0.I0(3)) {
            Log.d("FragmentManager", "onCleared called for " + this);
        }
        this.f3504h = true;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || l0.class != obj.getClass()) {
            return false;
        }
        l0 l0Var = (l0) obj;
        return this.f3500d.equals(l0Var.f3500d) && this.f3501e.equals(l0Var.f3501e) && this.f3502f.equals(l0Var.f3502f);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void f(p pVar) {
        if (this.f3506j) {
            if (i0.I0(2)) {
                Log.v("FragmentManager", "Ignoring addRetainedFragment as the state is already saved");
            }
        } else if (this.f3500d.containsKey(pVar.f3563f)) {
        } else {
            this.f3500d.put(pVar.f3563f, pVar);
            if (i0.I0(2)) {
                Log.v("FragmentManager", "Updating retained Fragments: Added " + pVar);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void g(p pVar, boolean z7) {
        if (i0.I0(3)) {
            Log.d("FragmentManager", "Clearing non-config state for " + pVar);
        }
        i(pVar.f3563f, z7);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void h(String str, boolean z7) {
        if (i0.I0(3)) {
            Log.d("FragmentManager", "Clearing non-config state for saved state of Fragment " + str);
        }
        i(str, z7);
    }

    public int hashCode() {
        return (((this.f3500d.hashCode() * 31) + this.f3501e.hashCode()) * 31) + this.f3502f.hashCode();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public p j(String str) {
        return (p) this.f3500d.get(str);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public l0 k(p pVar) {
        l0 l0Var = (l0) this.f3501e.get(pVar.f3563f);
        if (l0Var == null) {
            l0 l0Var2 = new l0(this.f3503g);
            this.f3501e.put(pVar.f3563f, l0Var2);
            return l0Var2;
        }
        return l0Var;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Collection m() {
        return new ArrayList(this.f3500d.values());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public androidx.lifecycle.n0 n(p pVar) {
        androidx.lifecycle.n0 n0Var = (androidx.lifecycle.n0) this.f3502f.get(pVar.f3563f);
        if (n0Var == null) {
            androidx.lifecycle.n0 n0Var2 = new androidx.lifecycle.n0();
            this.f3502f.put(pVar.f3563f, n0Var2);
            return n0Var2;
        }
        return n0Var;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean o() {
        return this.f3504h;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void p(p pVar) {
        if (this.f3506j) {
            if (i0.I0(2)) {
                Log.v("FragmentManager", "Ignoring removeRetainedFragment as the state is already saved");
                return;
            }
            return;
        }
        if ((this.f3500d.remove(pVar.f3563f) != null) && i0.I0(2)) {
            Log.v("FragmentManager", "Updating retained Fragments: Removed " + pVar);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void q(boolean z7) {
        this.f3506j = z7;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean r(p pVar) {
        if (this.f3500d.containsKey(pVar.f3563f)) {
            return this.f3503g ? this.f3504h : !this.f3505i;
        }
        return true;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("FragmentManagerViewModel{");
        sb.append(Integer.toHexString(System.identityHashCode(this)));
        sb.append("} Fragments (");
        Iterator it = this.f3500d.values().iterator();
        while (it.hasNext()) {
            sb.append(it.next());
            if (it.hasNext()) {
                sb.append(", ");
            }
        }
        sb.append(") Child Non Config (");
        Iterator it2 = this.f3501e.keySet().iterator();
        while (it2.hasNext()) {
            sb.append((String) it2.next());
            if (it2.hasNext()) {
                sb.append(", ");
            }
        }
        sb.append(") ViewModelStores (");
        Iterator it3 = this.f3502f.keySet().iterator();
        while (it3.hasNext()) {
            sb.append((String) it3.next());
            if (it3.hasNext()) {
                sb.append(", ");
            }
        }
        sb.append(')');
        return sb.toString();
    }
}
