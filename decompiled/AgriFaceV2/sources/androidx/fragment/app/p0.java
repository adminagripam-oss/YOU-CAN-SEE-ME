package androidx.fragment.app;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class p0 {

    /* renamed from: a  reason: collision with root package name */
    private final ArrayList f3611a = new ArrayList();

    /* renamed from: b  reason: collision with root package name */
    private final HashMap f3612b = new HashMap();

    /* renamed from: c  reason: collision with root package name */
    private final HashMap f3613c = new HashMap();

    /* renamed from: d  reason: collision with root package name */
    private l0 f3614d;

    /* JADX INFO: Access modifiers changed from: package-private */
    public void A(l0 l0Var) {
        this.f3614d = l0Var;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Bundle B(String str, Bundle bundle) {
        return (Bundle) (bundle != null ? this.f3613c.put(str, bundle) : this.f3613c.remove(str));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a(p pVar) {
        if (this.f3611a.contains(pVar)) {
            throw new IllegalStateException("Fragment already added: " + pVar);
        }
        synchronized (this.f3611a) {
            this.f3611a.add(pVar);
        }
        pVar.f3569l = true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void b() {
        this.f3612b.values().removeAll(Collections.singleton(null));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean c(String str) {
        return this.f3612b.get(str) != null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void d(int i8) {
        for (o0 o0Var : this.f3612b.values()) {
            if (o0Var != null) {
                o0Var.s(i8);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void e(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        String str2 = str + "    ";
        if (!this.f3612b.isEmpty()) {
            printWriter.print(str);
            printWriter.println("Active Fragments:");
            for (o0 o0Var : this.f3612b.values()) {
                printWriter.print(str);
                if (o0Var != null) {
                    p k8 = o0Var.k();
                    printWriter.println(k8);
                    k8.g(str2, fileDescriptor, printWriter, strArr);
                } else {
                    printWriter.println("null");
                }
            }
        }
        int size = this.f3611a.size();
        if (size > 0) {
            printWriter.print(str);
            printWriter.println("Added Fragments:");
            for (int i8 = 0; i8 < size; i8++) {
                printWriter.print(str);
                printWriter.print("  #");
                printWriter.print(i8);
                printWriter.print(": ");
                printWriter.println(((p) this.f3611a.get(i8)).toString());
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public p f(String str) {
        o0 o0Var = (o0) this.f3612b.get(str);
        if (o0Var != null) {
            return o0Var.k();
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public p g(int i8) {
        for (int size = this.f3611a.size() - 1; size >= 0; size--) {
            p pVar = (p) this.f3611a.get(size);
            if (pVar != null && pVar.f3582y == i8) {
                return pVar;
            }
        }
        for (o0 o0Var : this.f3612b.values()) {
            if (o0Var != null) {
                p k8 = o0Var.k();
                if (k8.f3582y == i8) {
                    return k8;
                }
            }
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public p h(String str) {
        if (str != null) {
            for (int size = this.f3611a.size() - 1; size >= 0; size--) {
                p pVar = (p) this.f3611a.get(size);
                if (pVar != null && str.equals(pVar.A)) {
                    return pVar;
                }
            }
        }
        if (str != null) {
            for (o0 o0Var : this.f3612b.values()) {
                if (o0Var != null) {
                    p k8 = o0Var.k();
                    if (str.equals(k8.A)) {
                        return k8;
                    }
                }
            }
            return null;
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public p i(String str) {
        p i8;
        for (o0 o0Var : this.f3612b.values()) {
            if (o0Var != null && (i8 = o0Var.k().i(str)) != null) {
                return i8;
            }
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int j(p pVar) {
        View view;
        View view2;
        ViewGroup viewGroup = pVar.I;
        if (viewGroup == null) {
            return -1;
        }
        int indexOf = this.f3611a.indexOf(pVar);
        for (int i8 = indexOf - 1; i8 >= 0; i8--) {
            p pVar2 = (p) this.f3611a.get(i8);
            if (pVar2.I == viewGroup && (view2 = pVar2.J) != null) {
                return viewGroup.indexOfChild(view2) + 1;
            }
        }
        while (true) {
            indexOf++;
            if (indexOf >= this.f3611a.size()) {
                return -1;
            }
            p pVar3 = (p) this.f3611a.get(indexOf);
            if (pVar3.I == viewGroup && (view = pVar3.J) != null) {
                return viewGroup.indexOfChild(view);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public List k() {
        ArrayList arrayList = new ArrayList();
        for (o0 o0Var : this.f3612b.values()) {
            if (o0Var != null) {
                arrayList.add(o0Var);
            }
        }
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public List l() {
        ArrayList arrayList = new ArrayList();
        for (o0 o0Var : this.f3612b.values()) {
            arrayList.add(o0Var != null ? o0Var.k() : null);
        }
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public HashMap m() {
        return this.f3613c;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public o0 n(String str) {
        return (o0) this.f3612b.get(str);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public List o() {
        ArrayList arrayList;
        if (this.f3611a.isEmpty()) {
            return Collections.emptyList();
        }
        synchronized (this.f3611a) {
            arrayList = new ArrayList(this.f3611a);
        }
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public l0 p() {
        return this.f3614d;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Bundle q(String str) {
        return (Bundle) this.f3613c.get(str);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void r(o0 o0Var) {
        p k8 = o0Var.k();
        if (c(k8.f3563f)) {
            return;
        }
        this.f3612b.put(k8.f3563f, o0Var);
        if (k8.E) {
            if (k8.D) {
                this.f3614d.f(k8);
            } else {
                this.f3614d.p(k8);
            }
            k8.E = false;
        }
        if (i0.I0(2)) {
            Log.v("FragmentManager", "Added fragment to active set " + k8);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void s(o0 o0Var) {
        p k8 = o0Var.k();
        if (k8.D) {
            this.f3614d.p(k8);
        }
        if (this.f3612b.get(k8.f3563f) == o0Var && ((o0) this.f3612b.put(k8.f3563f, null)) != null && i0.I0(2)) {
            Log.v("FragmentManager", "Removed fragment from active set " + k8);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void t() {
        Iterator it = this.f3611a.iterator();
        while (it.hasNext()) {
            o0 o0Var = (o0) this.f3612b.get(((p) it.next()).f3563f);
            if (o0Var != null) {
                o0Var.m();
            }
        }
        for (o0 o0Var2 : this.f3612b.values()) {
            if (o0Var2 != null) {
                o0Var2.m();
                p k8 = o0Var2.k();
                if (k8.f3570m && !k8.W()) {
                    if (k8.f3572o && !this.f3613c.containsKey(k8.f3563f)) {
                        B(k8.f3563f, o0Var2.q());
                    }
                    s(o0Var2);
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void u(p pVar) {
        synchronized (this.f3611a) {
            this.f3611a.remove(pVar);
        }
        pVar.f3569l = false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void v() {
        this.f3612b.clear();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void w(List list) {
        this.f3611a.clear();
        if (list != null) {
            Iterator it = list.iterator();
            while (it.hasNext()) {
                String str = (String) it.next();
                p f8 = f(str);
                if (f8 == null) {
                    throw new IllegalStateException("No instantiated fragment for (" + str + ")");
                }
                if (i0.I0(2)) {
                    Log.v("FragmentManager", "restoreSaveState: added (" + str + "): " + f8);
                }
                a(f8);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void x(HashMap hashMap) {
        this.f3613c.clear();
        this.f3613c.putAll(hashMap);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ArrayList y() {
        ArrayList arrayList = new ArrayList(this.f3612b.size());
        for (o0 o0Var : this.f3612b.values()) {
            if (o0Var != null) {
                p k8 = o0Var.k();
                B(k8.f3563f, o0Var.q());
                arrayList.add(k8.f3563f);
                if (i0.I0(2)) {
                    Log.v("FragmentManager", "Saved state of " + k8 + ": " + k8.f3557b);
                }
            }
        }
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ArrayList z() {
        synchronized (this.f3611a) {
            if (this.f3611a.isEmpty()) {
                return null;
            }
            ArrayList arrayList = new ArrayList(this.f3611a.size());
            Iterator it = this.f3611a.iterator();
            while (it.hasNext()) {
                p pVar = (p) it.next();
                arrayList.add(pVar.f3563f);
                if (i0.I0(2)) {
                    Log.v("FragmentManager", "saveAllState: adding fragment (" + pVar.f3563f + "): " + pVar);
                }
            }
            return arrayList;
        }
    }
}
