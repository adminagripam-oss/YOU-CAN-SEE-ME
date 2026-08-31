package androidx.fragment.app;

import android.app.Activity;
import android.content.res.Resources;
import android.os.BadParcelableException;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import androidx.fragment.app.y0;
import androidx.lifecycle.i;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class o0 {

    /* renamed from: a  reason: collision with root package name */
    private final c0 f3546a;

    /* renamed from: b  reason: collision with root package name */
    private final p0 f3547b;

    /* renamed from: c  reason: collision with root package name */
    private final p f3548c;

    /* renamed from: d  reason: collision with root package name */
    private boolean f3549d = false;

    /* renamed from: e  reason: collision with root package name */
    private int f3550e = -1;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class a implements View.OnAttachStateChangeListener {

        /* renamed from: a  reason: collision with root package name */
        final /* synthetic */ View f3551a;

        a(View view) {
            this.f3551a = view;
        }

        @Override // android.view.View.OnAttachStateChangeListener
        public void onViewAttachedToWindow(View view) {
            this.f3551a.removeOnAttachStateChangeListener(this);
            androidx.core.view.p0.K(this.f3551a);
        }

        @Override // android.view.View.OnAttachStateChangeListener
        public void onViewDetachedFromWindow(View view) {
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static /* synthetic */ class b {

        /* renamed from: a  reason: collision with root package name */
        static final /* synthetic */ int[] f3553a;

        static {
            int[] iArr = new int[i.b.values().length];
            f3553a = iArr;
            try {
                iArr[i.b.RESUMED.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f3553a[i.b.STARTED.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f3553a[i.b.CREATED.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f3553a[i.b.INITIALIZED.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public o0(c0 c0Var, p0 p0Var, p pVar) {
        this.f3546a = c0Var;
        this.f3547b = p0Var;
        this.f3548c = pVar;
    }

    private boolean l(View view) {
        if (view == this.f3548c.J) {
            return true;
        }
        for (ViewParent parent = view.getParent(); parent != null; parent = parent.getParent()) {
            if (parent == this.f3548c.J) {
                return true;
            }
        }
        return false;
    }

    void a() {
        if (i0.I0(3)) {
            Log.d("FragmentManager", "moveto ACTIVITY_CREATED: " + this.f3548c);
        }
        Bundle bundle = this.f3548c.f3557b;
        Bundle bundle2 = bundle != null ? bundle.getBundle("savedInstanceState") : null;
        this.f3548c.K0(bundle2);
        this.f3546a.a(this.f3548c, bundle2, false);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void b() {
        p l02 = i0.l0(this.f3548c.I);
        p B = this.f3548c.B();
        if (l02 != null && !l02.equals(B)) {
            p pVar = this.f3548c;
            s1.c.j(pVar, l02, pVar.f3583z);
        }
        int j8 = this.f3547b.j(this.f3548c);
        p pVar2 = this.f3548c;
        pVar2.I.addView(pVar2.J, j8);
    }

    void c() {
        if (i0.I0(3)) {
            Log.d("FragmentManager", "moveto ATTACHED: " + this.f3548c);
        }
        p pVar = this.f3548c;
        p pVar2 = pVar.f3565h;
        o0 o0Var = null;
        if (pVar2 != null) {
            o0 n8 = this.f3547b.n(pVar2.f3563f);
            if (n8 == null) {
                throw new IllegalStateException("Fragment " + this.f3548c + " declared target fragment " + this.f3548c.f3565h + " that does not belong to this FragmentManager!");
            }
            p pVar3 = this.f3548c;
            pVar3.f3566i = pVar3.f3565h.f3563f;
            pVar3.f3565h = null;
            o0Var = n8;
        } else {
            String str = pVar.f3566i;
            if (str != null && (o0Var = this.f3547b.n(str)) == null) {
                throw new IllegalStateException("Fragment " + this.f3548c + " declared target fragment " + this.f3548c.f3566i + " that does not belong to this FragmentManager!");
            }
        }
        if (o0Var != null) {
            o0Var.m();
        }
        p pVar4 = this.f3548c;
        pVar4.f3579v = pVar4.f3578u.v0();
        p pVar5 = this.f3548c;
        pVar5.f3581x = pVar5.f3578u.y0();
        this.f3546a.g(this.f3548c, false);
        this.f3548c.L0();
        this.f3546a.b(this.f3548c, false);
    }

    int d() {
        p pVar = this.f3548c;
        if (pVar.f3578u == null) {
            return pVar.f3555a;
        }
        int i8 = this.f3550e;
        int i9 = b.f3553a[pVar.T.ordinal()];
        if (i9 != 1) {
            i8 = i9 != 2 ? i9 != 3 ? i9 != 4 ? Math.min(i8, -1) : Math.min(i8, 0) : Math.min(i8, 1) : Math.min(i8, 5);
        }
        p pVar2 = this.f3548c;
        if (pVar2.f3573p) {
            if (pVar2.f3574q) {
                i8 = Math.max(this.f3550e, 2);
                View view = this.f3548c.J;
                if (view != null && view.getParent() == null) {
                    i8 = Math.min(i8, 2);
                }
            } else {
                i8 = this.f3550e < 4 ? Math.min(i8, pVar2.f3555a) : Math.min(i8, 1);
            }
        }
        if (!this.f3548c.f3569l) {
            i8 = Math.min(i8, 1);
        }
        p pVar3 = this.f3548c;
        ViewGroup viewGroup = pVar3.I;
        y0.d.a s8 = viewGroup != null ? y0.u(viewGroup, pVar3.C()).s(this) : null;
        if (s8 == y0.d.a.ADDING) {
            i8 = Math.min(i8, 6);
        } else if (s8 == y0.d.a.REMOVING) {
            i8 = Math.max(i8, 3);
        } else {
            p pVar4 = this.f3548c;
            if (pVar4.f3570m) {
                i8 = pVar4.W() ? Math.min(i8, 1) : Math.min(i8, -1);
            }
        }
        p pVar5 = this.f3548c;
        if (pVar5.K && pVar5.f3555a < 5) {
            i8 = Math.min(i8, 4);
        }
        p pVar6 = this.f3548c;
        if (pVar6.f3571n && pVar6.I != null) {
            i8 = Math.max(i8, 3);
        }
        if (i0.I0(2)) {
            Log.v("FragmentManager", "computeExpectedState() of " + i8 + " for " + this.f3548c);
        }
        return i8;
    }

    void e() {
        if (i0.I0(3)) {
            Log.d("FragmentManager", "moveto CREATED: " + this.f3548c);
        }
        Bundle bundle = this.f3548c.f3557b;
        Bundle bundle2 = bundle != null ? bundle.getBundle("savedInstanceState") : null;
        p pVar = this.f3548c;
        if (pVar.R) {
            pVar.f3555a = 1;
            pVar.m1();
            return;
        }
        this.f3546a.h(pVar, bundle2, false);
        this.f3548c.O0(bundle2);
        this.f3546a.c(this.f3548c, bundle2, false);
    }

    void f() {
        String str;
        if (this.f3548c.f3573p) {
            return;
        }
        if (i0.I0(3)) {
            Log.d("FragmentManager", "moveto CREATE_VIEW: " + this.f3548c);
        }
        Bundle bundle = this.f3548c.f3557b;
        ViewGroup viewGroup = null;
        Bundle bundle2 = bundle != null ? bundle.getBundle("savedInstanceState") : null;
        LayoutInflater U0 = this.f3548c.U0(bundle2);
        p pVar = this.f3548c;
        ViewGroup viewGroup2 = pVar.I;
        if (viewGroup2 != null) {
            viewGroup = viewGroup2;
        } else {
            int i8 = pVar.f3583z;
            if (i8 != 0) {
                if (i8 == -1) {
                    throw new IllegalArgumentException("Cannot create fragment " + this.f3548c + " for a container view with no id");
                }
                viewGroup = (ViewGroup) pVar.f3578u.r0().c(this.f3548c.f3583z);
                if (viewGroup == null) {
                    p pVar2 = this.f3548c;
                    if (!pVar2.f3575r) {
                        try {
                            str = pVar2.I().getResourceName(this.f3548c.f3583z);
                        } catch (Resources.NotFoundException unused) {
                            str = "unknown";
                        }
                        throw new IllegalArgumentException("No view found for id 0x" + Integer.toHexString(this.f3548c.f3583z) + " (" + str + ") for fragment " + this.f3548c);
                    }
                } else if (!(viewGroup instanceof x)) {
                    s1.c.i(this.f3548c, viewGroup);
                }
            }
        }
        p pVar3 = this.f3548c;
        pVar3.I = viewGroup;
        pVar3.Q0(U0, viewGroup, bundle2);
        if (this.f3548c.J != null) {
            if (i0.I0(3)) {
                Log.d("FragmentManager", "moveto VIEW_CREATED: " + this.f3548c);
            }
            this.f3548c.J.setSaveFromParentEnabled(false);
            p pVar4 = this.f3548c;
            pVar4.J.setTag(r1.b.f11310a, pVar4);
            if (viewGroup != null) {
                b();
            }
            p pVar5 = this.f3548c;
            if (pVar5.B) {
                pVar5.J.setVisibility(8);
            }
            if (this.f3548c.J.isAttachedToWindow()) {
                androidx.core.view.p0.K(this.f3548c.J);
            } else {
                View view = this.f3548c.J;
                view.addOnAttachStateChangeListener(new a(view));
            }
            this.f3548c.h1();
            c0 c0Var = this.f3546a;
            p pVar6 = this.f3548c;
            c0Var.m(pVar6, pVar6.J, bundle2, false);
            int visibility = this.f3548c.J.getVisibility();
            this.f3548c.u1(this.f3548c.J.getAlpha());
            p pVar7 = this.f3548c;
            if (pVar7.I != null && visibility == 0) {
                View findFocus = pVar7.J.findFocus();
                if (findFocus != null) {
                    this.f3548c.r1(findFocus);
                    if (i0.I0(2)) {
                        Log.v("FragmentManager", "requestFocus: Saved focused view " + findFocus + " for Fragment " + this.f3548c);
                    }
                }
                this.f3548c.J.setAlpha(0.0f);
            }
        }
        this.f3548c.f3555a = 2;
    }

    void g() {
        p f8;
        if (i0.I0(3)) {
            Log.d("FragmentManager", "movefrom CREATED: " + this.f3548c);
        }
        p pVar = this.f3548c;
        boolean z7 = true;
        boolean z8 = pVar.f3570m && !pVar.W();
        if (z8) {
            p pVar2 = this.f3548c;
            if (!pVar2.f3572o) {
                this.f3547b.B(pVar2.f3563f, null);
            }
        }
        if (!(z8 || this.f3547b.p().r(this.f3548c))) {
            String str = this.f3548c.f3566i;
            if (str != null && (f8 = this.f3547b.f(str)) != null && f8.D) {
                this.f3548c.f3565h = f8;
            }
            this.f3548c.f3555a = 0;
            return;
        }
        a0 a0Var = this.f3548c.f3579v;
        if (a0Var instanceof androidx.lifecycle.o0) {
            z7 = this.f3547b.p().o();
        } else if (a0Var.f() instanceof Activity) {
            z7 = true ^ ((Activity) a0Var.f()).isChangingConfigurations();
        }
        if ((z8 && !this.f3548c.f3572o) || z7) {
            this.f3547b.p().g(this.f3548c, false);
        }
        this.f3548c.R0();
        this.f3546a.d(this.f3548c, false);
        for (o0 o0Var : this.f3547b.k()) {
            if (o0Var != null) {
                p k8 = o0Var.k();
                if (this.f3548c.f3563f.equals(k8.f3566i)) {
                    k8.f3565h = this.f3548c;
                    k8.f3566i = null;
                }
            }
        }
        p pVar3 = this.f3548c;
        String str2 = pVar3.f3566i;
        if (str2 != null) {
            pVar3.f3565h = this.f3547b.f(str2);
        }
        this.f3547b.s(this);
    }

    void h() {
        View view;
        if (i0.I0(3)) {
            Log.d("FragmentManager", "movefrom CREATE_VIEW: " + this.f3548c);
        }
        p pVar = this.f3548c;
        ViewGroup viewGroup = pVar.I;
        if (viewGroup != null && (view = pVar.J) != null) {
            viewGroup.removeView(view);
        }
        this.f3548c.S0();
        this.f3546a.n(this.f3548c, false);
        p pVar2 = this.f3548c;
        pVar2.I = null;
        pVar2.J = null;
        pVar2.V = null;
        pVar2.W.o(null);
        this.f3548c.f3574q = false;
    }

    void i() {
        if (i0.I0(3)) {
            Log.d("FragmentManager", "movefrom ATTACHED: " + this.f3548c);
        }
        this.f3548c.T0();
        boolean z7 = false;
        this.f3546a.e(this.f3548c, false);
        p pVar = this.f3548c;
        pVar.f3555a = -1;
        pVar.f3579v = null;
        pVar.f3581x = null;
        pVar.f3578u = null;
        if (pVar.f3570m && !pVar.W()) {
            z7 = true;
        }
        if (z7 || this.f3547b.p().r(this.f3548c)) {
            if (i0.I0(3)) {
                Log.d("FragmentManager", "initState called for fragment: " + this.f3548c);
            }
            this.f3548c.S();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void j() {
        p pVar = this.f3548c;
        if (pVar.f3573p && pVar.f3574q && !pVar.f3576s) {
            if (i0.I0(3)) {
                Log.d("FragmentManager", "moveto CREATE_VIEW: " + this.f3548c);
            }
            Bundle bundle = this.f3548c.f3557b;
            Bundle bundle2 = bundle != null ? bundle.getBundle("savedInstanceState") : null;
            p pVar2 = this.f3548c;
            pVar2.Q0(pVar2.U0(bundle2), null, bundle2);
            View view = this.f3548c.J;
            if (view != null) {
                view.setSaveFromParentEnabled(false);
                p pVar3 = this.f3548c;
                pVar3.J.setTag(r1.b.f11310a, pVar3);
                p pVar4 = this.f3548c;
                if (pVar4.B) {
                    pVar4.J.setVisibility(8);
                }
                this.f3548c.h1();
                c0 c0Var = this.f3546a;
                p pVar5 = this.f3548c;
                c0Var.m(pVar5, pVar5.J, bundle2, false);
                this.f3548c.f3555a = 2;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public p k() {
        return this.f3548c;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void m() {
        ViewGroup viewGroup;
        ViewGroup viewGroup2;
        ViewGroup viewGroup3;
        if (this.f3549d) {
            if (i0.I0(2)) {
                Log.v("FragmentManager", "Ignoring re-entrant call to moveToExpectedState() for " + k());
                return;
            }
            return;
        }
        try {
            this.f3549d = true;
            boolean z7 = false;
            while (true) {
                int d8 = d();
                p pVar = this.f3548c;
                int i8 = pVar.f3555a;
                if (d8 == i8) {
                    if (!z7 && i8 == -1 && pVar.f3570m && !pVar.W() && !this.f3548c.f3572o) {
                        if (i0.I0(3)) {
                            Log.d("FragmentManager", "Cleaning up state of never attached fragment: " + this.f3548c);
                        }
                        this.f3547b.p().g(this.f3548c, true);
                        this.f3547b.s(this);
                        if (i0.I0(3)) {
                            Log.d("FragmentManager", "initState called for fragment: " + this.f3548c);
                        }
                        this.f3548c.S();
                    }
                    p pVar2 = this.f3548c;
                    if (pVar2.P) {
                        if (pVar2.J != null && (viewGroup = pVar2.I) != null) {
                            y0 u7 = y0.u(viewGroup, pVar2.C());
                            if (this.f3548c.B) {
                                u7.k(this);
                            } else {
                                u7.m(this);
                            }
                        }
                        p pVar3 = this.f3548c;
                        i0 i0Var = pVar3.f3578u;
                        if (i0Var != null) {
                            i0Var.G0(pVar3);
                        }
                        p pVar4 = this.f3548c;
                        pVar4.P = false;
                        pVar4.t0(pVar4.B);
                        this.f3548c.f3580w.I();
                    }
                    return;
                }
                if (d8 > i8) {
                    switch (i8 + 1) {
                        case 0:
                            c();
                            continue;
                        case 1:
                            e();
                            continue;
                        case 2:
                            j();
                            f();
                            continue;
                        case 3:
                            a();
                            continue;
                        case 4:
                            if (pVar.J != null && (viewGroup3 = pVar.I) != null) {
                                y0.u(viewGroup3, pVar.C()).j(y0.d.b.f(this.f3548c.J.getVisibility()), this);
                            }
                            this.f3548c.f3555a = 4;
                            continue;
                        case 5:
                            t();
                            continue;
                        case androidx.browser.customtabs.b.TAB_HIDDEN /* 6 */:
                            pVar.f3555a = 6;
                            continue;
                        case 7:
                            p();
                            continue;
                        default:
                            continue;
                    }
                } else {
                    switch (i8 - 1) {
                        case androidx.swiperefreshlayout.widget.c.DEFAULT_SLINGSHOT_DISTANCE /* -1 */:
                            i();
                            continue;
                        case 0:
                            if (pVar.f3572o && this.f3547b.q(pVar.f3563f) == null) {
                                this.f3547b.B(this.f3548c.f3563f, q());
                            }
                            g();
                            continue;
                        case 1:
                            h();
                            this.f3548c.f3555a = 1;
                            continue;
                        case 2:
                            pVar.f3574q = false;
                            pVar.f3555a = 2;
                            continue;
                        case 3:
                            if (i0.I0(3)) {
                                Log.d("FragmentManager", "movefrom ACTIVITY_CREATED: " + this.f3548c);
                            }
                            p pVar5 = this.f3548c;
                            if (pVar5.f3572o) {
                                this.f3547b.B(pVar5.f3563f, q());
                            } else if (pVar5.J != null && pVar5.f3559c == null) {
                                r();
                            }
                            p pVar6 = this.f3548c;
                            if (pVar6.J != null && (viewGroup2 = pVar6.I) != null) {
                                y0.u(viewGroup2, pVar6.C()).l(this);
                            }
                            this.f3548c.f3555a = 3;
                            continue;
                        case 4:
                            u();
                            continue;
                        case 5:
                            pVar.f3555a = 5;
                            continue;
                        case androidx.browser.customtabs.b.TAB_HIDDEN /* 6 */:
                            n();
                            continue;
                        default:
                            continue;
                    }
                }
                z7 = true;
            }
        } finally {
            this.f3549d = false;
        }
    }

    void n() {
        if (i0.I0(3)) {
            Log.d("FragmentManager", "movefrom RESUMED: " + this.f3548c);
        }
        this.f3548c.Z0();
        this.f3546a.f(this.f3548c, false);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void o(ClassLoader classLoader) {
        Bundle bundle = this.f3548c.f3557b;
        if (bundle == null) {
            return;
        }
        bundle.setClassLoader(classLoader);
        if (this.f3548c.f3557b.getBundle("savedInstanceState") == null) {
            this.f3548c.f3557b.putBundle("savedInstanceState", new Bundle());
        }
        try {
            p pVar = this.f3548c;
            pVar.f3559c = pVar.f3557b.getSparseParcelableArray("viewState");
            p pVar2 = this.f3548c;
            pVar2.f3561d = pVar2.f3557b.getBundle("viewRegistryState");
            n0 n0Var = (n0) this.f3548c.f3557b.getParcelable("state");
            if (n0Var != null) {
                p pVar3 = this.f3548c;
                pVar3.f3566i = n0Var.f3542l;
                pVar3.f3567j = n0Var.f3543m;
                Boolean bool = pVar3.f3562e;
                if (bool != null) {
                    pVar3.L = bool.booleanValue();
                    this.f3548c.f3562e = null;
                } else {
                    pVar3.L = n0Var.f3544n;
                }
            }
            p pVar4 = this.f3548c;
            if (pVar4.L) {
                return;
            }
            pVar4.K = true;
        } catch (BadParcelableException e8) {
            throw new IllegalStateException("Failed to restore view hierarchy state for fragment " + k(), e8);
        }
    }

    void p() {
        if (i0.I0(3)) {
            Log.d("FragmentManager", "moveto RESUMED: " + this.f3548c);
        }
        View w7 = this.f3548c.w();
        if (w7 != null && l(w7)) {
            boolean requestFocus = w7.requestFocus();
            if (i0.I0(2)) {
                StringBuilder sb = new StringBuilder();
                sb.append("requestFocus: Restoring focused view ");
                sb.append(w7);
                sb.append(" ");
                sb.append(requestFocus ? "succeeded" : "failed");
                sb.append(" on Fragment ");
                sb.append(this.f3548c);
                sb.append(" resulting in focused view ");
                sb.append(this.f3548c.J.findFocus());
                Log.v("FragmentManager", sb.toString());
            }
        }
        this.f3548c.r1(null);
        this.f3548c.d1();
        this.f3546a.i(this.f3548c, false);
        this.f3547b.B(this.f3548c.f3563f, null);
        p pVar = this.f3548c;
        pVar.f3557b = null;
        pVar.f3559c = null;
        pVar.f3561d = null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Bundle q() {
        Bundle bundle;
        Bundle bundle2 = new Bundle();
        p pVar = this.f3548c;
        if (pVar.f3555a == -1 && (bundle = pVar.f3557b) != null) {
            bundle2.putAll(bundle);
        }
        bundle2.putParcelable("state", new n0(this.f3548c));
        if (this.f3548c.f3555a > -1) {
            Bundle bundle3 = new Bundle();
            this.f3548c.e1(bundle3);
            if (!bundle3.isEmpty()) {
                bundle2.putBundle("savedInstanceState", bundle3);
            }
            this.f3546a.j(this.f3548c, bundle3, false);
            Bundle bundle4 = new Bundle();
            this.f3548c.Y.e(bundle4);
            if (!bundle4.isEmpty()) {
                bundle2.putBundle("registryState", bundle4);
            }
            Bundle Q0 = this.f3548c.f3580w.Q0();
            if (!Q0.isEmpty()) {
                bundle2.putBundle("childFragmentManager", Q0);
            }
            if (this.f3548c.J != null) {
                r();
            }
            SparseArray<? extends Parcelable> sparseArray = this.f3548c.f3559c;
            if (sparseArray != null) {
                bundle2.putSparseParcelableArray("viewState", sparseArray);
            }
            Bundle bundle5 = this.f3548c.f3561d;
            if (bundle5 != null) {
                bundle2.putBundle("viewRegistryState", bundle5);
            }
        }
        Bundle bundle6 = this.f3548c.f3564g;
        if (bundle6 != null) {
            bundle2.putBundle("arguments", bundle6);
        }
        return bundle2;
    }

    void r() {
        if (this.f3548c.J == null) {
            return;
        }
        if (i0.I0(2)) {
            Log.v("FragmentManager", "Saving view state for fragment " + this.f3548c + " with view " + this.f3548c.J);
        }
        SparseArray<Parcelable> sparseArray = new SparseArray<>();
        this.f3548c.J.saveHierarchyState(sparseArray);
        if (sparseArray.size() > 0) {
            this.f3548c.f3559c = sparseArray;
        }
        Bundle bundle = new Bundle();
        this.f3548c.V.e(bundle);
        if (bundle.isEmpty()) {
            return;
        }
        this.f3548c.f3561d = bundle;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void s(int i8) {
        this.f3550e = i8;
    }

    void t() {
        if (i0.I0(3)) {
            Log.d("FragmentManager", "moveto STARTED: " + this.f3548c);
        }
        this.f3548c.f1();
        this.f3546a.k(this.f3548c, false);
    }

    void u() {
        if (i0.I0(3)) {
            Log.d("FragmentManager", "movefrom STARTED: " + this.f3548c);
        }
        this.f3548c.g1();
        this.f3546a.l(this.f3548c, false);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public o0(c0 c0Var, p0 p0Var, p pVar, Bundle bundle) {
        this.f3546a = c0Var;
        this.f3547b = p0Var;
        this.f3548c = pVar;
        pVar.f3559c = null;
        pVar.f3561d = null;
        pVar.f3577t = 0;
        pVar.f3574q = false;
        pVar.f3569l = false;
        p pVar2 = pVar.f3565h;
        pVar.f3566i = pVar2 != null ? pVar2.f3563f : null;
        pVar.f3565h = null;
        pVar.f3557b = bundle;
        pVar.f3564g = bundle.getBundle("arguments");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public o0(c0 c0Var, p0 p0Var, ClassLoader classLoader, z zVar, Bundle bundle) {
        this.f3546a = c0Var;
        this.f3547b = p0Var;
        p a8 = ((n0) bundle.getParcelable("state")).a(zVar, classLoader);
        this.f3548c = a8;
        a8.f3557b = bundle;
        Bundle bundle2 = bundle.getBundle("arguments");
        if (bundle2 != null) {
            bundle2.setClassLoader(classLoader);
        }
        a8.q1(bundle2);
        if (i0.I0(2)) {
            Log.v("FragmentManager", "Instantiated fragment " + a8);
        }
    }
}
