package androidx.fragment.app;

import android.util.Log;
import androidx.fragment.app.i0;
import androidx.fragment.app.q0;
import java.io.PrintWriter;
import java.util.ArrayList;
/* loaded from: classes.dex */
final class a extends q0 implements i0.l {

    /* renamed from: t  reason: collision with root package name */
    final i0 f3350t;

    /* renamed from: u  reason: collision with root package name */
    boolean f3351u;

    /* renamed from: v  reason: collision with root package name */
    int f3352v;

    /* renamed from: w  reason: collision with root package name */
    boolean f3353w;

    /* JADX INFO: Access modifiers changed from: package-private */
    public a(i0 i0Var) {
        super(i0Var.t0(), i0Var.v0() != null ? i0Var.v0().f().getClassLoader() : null);
        this.f3352v = -1;
        this.f3353w = false;
        this.f3350t = i0Var;
    }

    @Override // androidx.fragment.app.i0.l
    public boolean a(ArrayList arrayList, ArrayList arrayList2) {
        if (i0.I0(2)) {
            Log.v("FragmentManager", "Run: " + this);
        }
        arrayList.add(this);
        arrayList2.add(Boolean.FALSE);
        if (this.f3624i) {
            this.f3350t.h(this);
            return true;
        }
        return true;
    }

    @Override // androidx.fragment.app.q0
    public int f() {
        return o(false);
    }

    @Override // androidx.fragment.app.q0
    public int g() {
        return o(true);
    }

    @Override // androidx.fragment.app.q0
    public void h() {
        j();
        this.f3350t.b0(this, false);
    }

    @Override // androidx.fragment.app.q0
    public void i() {
        j();
        this.f3350t.b0(this, true);
    }

    @Override // androidx.fragment.app.q0
    void k(int i8, p pVar, String str, int i9) {
        super.k(i8, pVar, str, i9);
        pVar.f3578u = this.f3350t;
    }

    @Override // androidx.fragment.app.q0
    public q0 l(p pVar) {
        i0 i0Var = pVar.f3578u;
        if (i0Var == null || i0Var == this.f3350t) {
            return super.l(pVar);
        }
        throw new IllegalStateException("Cannot remove Fragment attached to a different FragmentManager. Fragment " + pVar.toString() + " is already attached to a FragmentManager.");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void n(int i8) {
        q0.a aVar;
        if (this.f3624i) {
            if (i0.I0(2)) {
                Log.v("FragmentManager", "Bump nesting in " + this + " by " + i8);
            }
            int size = this.f3618c.size();
            for (int i9 = 0; i9 < size; i9++) {
                p pVar = ((q0.a) this.f3618c.get(i9)).f3636b;
                if (pVar != null) {
                    pVar.f3577t += i8;
                    if (i0.I0(2)) {
                        Log.v("FragmentManager", "Bump nesting of " + aVar.f3636b + " to " + aVar.f3636b.f3577t);
                    }
                }
            }
        }
    }

    int o(boolean z7) {
        if (this.f3351u) {
            throw new IllegalStateException("commit already called");
        }
        if (i0.I0(2)) {
            Log.v("FragmentManager", "Commit: " + this);
            PrintWriter printWriter = new PrintWriter(new v0("FragmentManager"));
            p("  ", printWriter);
            printWriter.close();
        }
        this.f3351u = true;
        this.f3352v = this.f3624i ? this.f3350t.k() : -1;
        this.f3350t.Y(this, z7);
        return this.f3352v;
    }

    public void p(String str, PrintWriter printWriter) {
        q(str, printWriter, true);
    }

    public void q(String str, PrintWriter printWriter, boolean z7) {
        String str2;
        if (z7) {
            printWriter.print(str);
            printWriter.print("mName=");
            printWriter.print(this.f3626k);
            printWriter.print(" mIndex=");
            printWriter.print(this.f3352v);
            printWriter.print(" mCommitted=");
            printWriter.println(this.f3351u);
            if (this.f3623h != 0) {
                printWriter.print(str);
                printWriter.print("mTransition=#");
                printWriter.print(Integer.toHexString(this.f3623h));
            }
            if (this.f3619d != 0 || this.f3620e != 0) {
                printWriter.print(str);
                printWriter.print("mEnterAnim=#");
                printWriter.print(Integer.toHexString(this.f3619d));
                printWriter.print(" mExitAnim=#");
                printWriter.println(Integer.toHexString(this.f3620e));
            }
            if (this.f3621f != 0 || this.f3622g != 0) {
                printWriter.print(str);
                printWriter.print("mPopEnterAnim=#");
                printWriter.print(Integer.toHexString(this.f3621f));
                printWriter.print(" mPopExitAnim=#");
                printWriter.println(Integer.toHexString(this.f3622g));
            }
            if (this.f3627l != 0 || this.f3628m != null) {
                printWriter.print(str);
                printWriter.print("mBreadCrumbTitleRes=#");
                printWriter.print(Integer.toHexString(this.f3627l));
                printWriter.print(" mBreadCrumbTitleText=");
                printWriter.println(this.f3628m);
            }
            if (this.f3629n != 0 || this.f3630o != null) {
                printWriter.print(str);
                printWriter.print("mBreadCrumbShortTitleRes=#");
                printWriter.print(Integer.toHexString(this.f3629n));
                printWriter.print(" mBreadCrumbShortTitleText=");
                printWriter.println(this.f3630o);
            }
        }
        if (this.f3618c.isEmpty()) {
            return;
        }
        printWriter.print(str);
        printWriter.println("Operations:");
        int size = this.f3618c.size();
        for (int i8 = 0; i8 < size; i8++) {
            q0.a aVar = (q0.a) this.f3618c.get(i8);
            switch (aVar.f3635a) {
                case 0:
                    str2 = "NULL";
                    break;
                case 1:
                    str2 = "ADD";
                    break;
                case 2:
                    str2 = "REPLACE";
                    break;
                case 3:
                    str2 = "REMOVE";
                    break;
                case 4:
                    str2 = "HIDE";
                    break;
                case 5:
                    str2 = "SHOW";
                    break;
                case androidx.browser.customtabs.b.TAB_HIDDEN /* 6 */:
                    str2 = "DETACH";
                    break;
                case 7:
                    str2 = "ATTACH";
                    break;
                case 8:
                    str2 = "SET_PRIMARY_NAV";
                    break;
                case 9:
                    str2 = "UNSET_PRIMARY_NAV";
                    break;
                case 10:
                    str2 = "OP_SET_MAX_LIFECYCLE";
                    break;
                default:
                    str2 = "cmd=" + aVar.f3635a;
                    break;
            }
            printWriter.print(str);
            printWriter.print("  Op #");
            printWriter.print(i8);
            printWriter.print(": ");
            printWriter.print(str2);
            printWriter.print(" ");
            printWriter.println(aVar.f3636b);
            if (z7) {
                if (aVar.f3638d != 0 || aVar.f3639e != 0) {
                    printWriter.print(str);
                    printWriter.print("enterAnim=#");
                    printWriter.print(Integer.toHexString(aVar.f3638d));
                    printWriter.print(" exitAnim=#");
                    printWriter.println(Integer.toHexString(aVar.f3639e));
                }
                if (aVar.f3640f != 0 || aVar.f3641g != 0) {
                    printWriter.print(str);
                    printWriter.print("popEnterAnim=#");
                    printWriter.print(Integer.toHexString(aVar.f3640f));
                    printWriter.print(" popExitAnim=#");
                    printWriter.println(Integer.toHexString(aVar.f3641g));
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void r() {
        i0 i0Var;
        int size = this.f3618c.size();
        for (int i8 = 0; i8 < size; i8++) {
            q0.a aVar = (q0.a) this.f3618c.get(i8);
            p pVar = aVar.f3636b;
            if (pVar != null) {
                pVar.f3572o = this.f3353w;
                pVar.t1(false);
                pVar.s1(this.f3623h);
                pVar.v1(this.f3631p, this.f3632q);
            }
            switch (aVar.f3635a) {
                case 1:
                    pVar.p1(aVar.f3638d, aVar.f3639e, aVar.f3640f, aVar.f3641g);
                    this.f3350t.p1(pVar, false);
                    this.f3350t.i(pVar);
                    continue;
                case 2:
                default:
                    throw new IllegalArgumentException("Unknown cmd: " + aVar.f3635a);
                case 3:
                    pVar.p1(aVar.f3638d, aVar.f3639e, aVar.f3640f, aVar.f3641g);
                    this.f3350t.i1(pVar);
                    continue;
                case 4:
                    pVar.p1(aVar.f3638d, aVar.f3639e, aVar.f3640f, aVar.f3641g);
                    this.f3350t.F0(pVar);
                    continue;
                case 5:
                    pVar.p1(aVar.f3638d, aVar.f3639e, aVar.f3640f, aVar.f3641g);
                    this.f3350t.p1(pVar, false);
                    this.f3350t.t1(pVar);
                    continue;
                case androidx.browser.customtabs.b.TAB_HIDDEN /* 6 */:
                    pVar.p1(aVar.f3638d, aVar.f3639e, aVar.f3640f, aVar.f3641g);
                    this.f3350t.w(pVar);
                    continue;
                case 7:
                    pVar.p1(aVar.f3638d, aVar.f3639e, aVar.f3640f, aVar.f3641g);
                    this.f3350t.p1(pVar, false);
                    this.f3350t.m(pVar);
                    continue;
                case 8:
                    i0Var = this.f3350t;
                    break;
                case 9:
                    i0Var = this.f3350t;
                    pVar = null;
                    break;
                case 10:
                    this.f3350t.q1(pVar, aVar.f3643i);
                    continue;
            }
            i0Var.r1(pVar);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void s() {
        i0 i0Var;
        for (int size = this.f3618c.size() - 1; size >= 0; size--) {
            q0.a aVar = (q0.a) this.f3618c.get(size);
            p pVar = aVar.f3636b;
            if (pVar != null) {
                pVar.f3572o = this.f3353w;
                pVar.t1(true);
                pVar.s1(i0.m1(this.f3623h));
                pVar.v1(this.f3632q, this.f3631p);
            }
            switch (aVar.f3635a) {
                case 1:
                    pVar.p1(aVar.f3638d, aVar.f3639e, aVar.f3640f, aVar.f3641g);
                    this.f3350t.p1(pVar, true);
                    this.f3350t.i1(pVar);
                    continue;
                case 2:
                default:
                    throw new IllegalArgumentException("Unknown cmd: " + aVar.f3635a);
                case 3:
                    pVar.p1(aVar.f3638d, aVar.f3639e, aVar.f3640f, aVar.f3641g);
                    this.f3350t.i(pVar);
                    continue;
                case 4:
                    pVar.p1(aVar.f3638d, aVar.f3639e, aVar.f3640f, aVar.f3641g);
                    this.f3350t.t1(pVar);
                    continue;
                case 5:
                    pVar.p1(aVar.f3638d, aVar.f3639e, aVar.f3640f, aVar.f3641g);
                    this.f3350t.p1(pVar, true);
                    this.f3350t.F0(pVar);
                    continue;
                case androidx.browser.customtabs.b.TAB_HIDDEN /* 6 */:
                    pVar.p1(aVar.f3638d, aVar.f3639e, aVar.f3640f, aVar.f3641g);
                    this.f3350t.m(pVar);
                    continue;
                case 7:
                    pVar.p1(aVar.f3638d, aVar.f3639e, aVar.f3640f, aVar.f3641g);
                    this.f3350t.p1(pVar, true);
                    this.f3350t.w(pVar);
                    continue;
                case 8:
                    i0Var = this.f3350t;
                    pVar = null;
                    break;
                case 9:
                    i0Var = this.f3350t;
                    break;
                case 10:
                    this.f3350t.q1(pVar, aVar.f3642h);
                    continue;
            }
            i0Var.r1(pVar);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public p t(ArrayList arrayList, p pVar) {
        p pVar2 = pVar;
        int i8 = 0;
        while (i8 < this.f3618c.size()) {
            q0.a aVar = (q0.a) this.f3618c.get(i8);
            int i9 = aVar.f3635a;
            if (i9 != 1) {
                if (i9 == 2) {
                    p pVar3 = aVar.f3636b;
                    int i10 = pVar3.f3583z;
                    boolean z7 = false;
                    for (int size = arrayList.size() - 1; size >= 0; size--) {
                        p pVar4 = (p) arrayList.get(size);
                        if (pVar4.f3583z == i10) {
                            if (pVar4 == pVar3) {
                                z7 = true;
                            } else {
                                if (pVar4 == pVar2) {
                                    this.f3618c.add(i8, new q0.a(9, pVar4, true));
                                    i8++;
                                    pVar2 = null;
                                }
                                q0.a aVar2 = new q0.a(3, pVar4, true);
                                aVar2.f3638d = aVar.f3638d;
                                aVar2.f3640f = aVar.f3640f;
                                aVar2.f3639e = aVar.f3639e;
                                aVar2.f3641g = aVar.f3641g;
                                this.f3618c.add(i8, aVar2);
                                arrayList.remove(pVar4);
                                i8++;
                            }
                        }
                    }
                    if (z7) {
                        this.f3618c.remove(i8);
                        i8--;
                    } else {
                        aVar.f3635a = 1;
                        aVar.f3637c = true;
                        arrayList.add(pVar3);
                    }
                } else if (i9 == 3 || i9 == 6) {
                    arrayList.remove(aVar.f3636b);
                    p pVar5 = aVar.f3636b;
                    if (pVar5 == pVar2) {
                        this.f3618c.add(i8, new q0.a(9, pVar5));
                        i8++;
                        pVar2 = null;
                    }
                } else if (i9 != 7) {
                    if (i9 == 8) {
                        this.f3618c.add(i8, new q0.a(9, pVar2, true));
                        aVar.f3637c = true;
                        i8++;
                        pVar2 = aVar.f3636b;
                    }
                }
                i8++;
            }
            arrayList.add(aVar.f3636b);
            i8++;
        }
        return pVar2;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(128);
        sb.append("BackStackEntry{");
        sb.append(Integer.toHexString(System.identityHashCode(this)));
        if (this.f3352v >= 0) {
            sb.append(" #");
            sb.append(this.f3352v);
        }
        if (this.f3626k != null) {
            sb.append(" ");
            sb.append(this.f3626k);
        }
        sb.append("}");
        return sb.toString();
    }

    public String u() {
        return this.f3626k;
    }

    public void v() {
        if (this.f3634s != null) {
            for (int i8 = 0; i8 < this.f3634s.size(); i8++) {
                ((Runnable) this.f3634s.get(i8)).run();
            }
            this.f3634s = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public p w(ArrayList arrayList, p pVar) {
        for (int size = this.f3618c.size() - 1; size >= 0; size--) {
            q0.a aVar = (q0.a) this.f3618c.get(size);
            int i8 = aVar.f3635a;
            if (i8 != 1) {
                if (i8 != 3) {
                    switch (i8) {
                        case 8:
                            pVar = null;
                            break;
                        case 9:
                            pVar = aVar.f3636b;
                            break;
                        case 10:
                            aVar.f3643i = aVar.f3642h;
                            break;
                    }
                }
                arrayList.add(aVar.f3636b);
            }
            arrayList.remove(aVar.f3636b);
        }
        return pVar;
    }
}
