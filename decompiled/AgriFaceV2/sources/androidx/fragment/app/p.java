package androidx.fragment.app;

import android.animation.Animator;
import android.app.Activity;
import android.app.Application;
import android.content.ComponentCallbacks;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.IntentSender;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseArray;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import androidx.lifecycle.i;
import androidx.lifecycle.l0;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;
/* loaded from: classes.dex */
public abstract class p implements ComponentCallbacks, View.OnCreateContextMenuListener, androidx.lifecycle.m, androidx.lifecycle.o0, androidx.lifecycle.g, w1.f {

    /* renamed from: d0  reason: collision with root package name */
    static final Object f3554d0 = new Object();
    String A;
    boolean B;
    boolean C;
    boolean D;
    boolean E;
    boolean F;
    private boolean H;
    ViewGroup I;
    View J;
    boolean K;
    g M;
    Handler N;
    boolean P;
    LayoutInflater Q;
    boolean R;
    public String S;
    androidx.lifecycle.n U;
    u0 V;
    l0.b X;
    w1.e Y;
    private int Z;

    /* renamed from: b  reason: collision with root package name */
    Bundle f3557b;

    /* renamed from: c  reason: collision with root package name */
    SparseArray f3559c;

    /* renamed from: d  reason: collision with root package name */
    Bundle f3561d;

    /* renamed from: e  reason: collision with root package name */
    Boolean f3562e;

    /* renamed from: g  reason: collision with root package name */
    Bundle f3564g;

    /* renamed from: h  reason: collision with root package name */
    p f3565h;

    /* renamed from: j  reason: collision with root package name */
    int f3567j;

    /* renamed from: l  reason: collision with root package name */
    boolean f3569l;

    /* renamed from: m  reason: collision with root package name */
    boolean f3570m;

    /* renamed from: n  reason: collision with root package name */
    boolean f3571n;

    /* renamed from: o  reason: collision with root package name */
    boolean f3572o;

    /* renamed from: p  reason: collision with root package name */
    boolean f3573p;

    /* renamed from: q  reason: collision with root package name */
    boolean f3574q;

    /* renamed from: r  reason: collision with root package name */
    boolean f3575r;

    /* renamed from: s  reason: collision with root package name */
    boolean f3576s;

    /* renamed from: t  reason: collision with root package name */
    int f3577t;

    /* renamed from: u  reason: collision with root package name */
    i0 f3578u;

    /* renamed from: v  reason: collision with root package name */
    a0 f3579v;

    /* renamed from: x  reason: collision with root package name */
    p f3581x;

    /* renamed from: y  reason: collision with root package name */
    int f3582y;

    /* renamed from: z  reason: collision with root package name */
    int f3583z;

    /* renamed from: a  reason: collision with root package name */
    int f3555a = -1;

    /* renamed from: f  reason: collision with root package name */
    String f3563f = UUID.randomUUID().toString();

    /* renamed from: i  reason: collision with root package name */
    String f3566i = null;

    /* renamed from: k  reason: collision with root package name */
    private Boolean f3568k = null;

    /* renamed from: w  reason: collision with root package name */
    i0 f3580w = new j0();
    boolean G = true;
    boolean L = true;
    Runnable O = new a();
    i.b T = i.b.RESUMED;
    androidx.lifecycle.s W = new androidx.lifecycle.s();

    /* renamed from: a0  reason: collision with root package name */
    private final AtomicInteger f3556a0 = new AtomicInteger();

    /* renamed from: b0  reason: collision with root package name */
    private final ArrayList f3558b0 = new ArrayList();

    /* renamed from: c0  reason: collision with root package name */
    private final i f3560c0 = new b();

    /* loaded from: classes.dex */
    class a implements Runnable {
        a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            p.this.y1();
        }
    }

    /* loaded from: classes.dex */
    class b extends i {
        b() {
            super(null);
        }

        @Override // androidx.fragment.app.p.i
        void a() {
            p.this.Y.c();
            androidx.lifecycle.e0.c(p.this);
            Bundle bundle = p.this.f3557b;
            p.this.Y.d(bundle != null ? bundle.getBundle("registryState") : null);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class c implements Runnable {
        c() {
        }

        @Override // java.lang.Runnable
        public void run() {
            p.this.e(false);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class d implements Runnable {

        /* renamed from: e  reason: collision with root package name */
        final /* synthetic */ y0 f3587e;

        d(y0 y0Var) {
            this.f3587e = y0Var;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (this.f3587e.w()) {
                this.f3587e.n();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class e extends w {
        e() {
        }

        @Override // androidx.fragment.app.w
        public View c(int i8) {
            View view = p.this.J;
            if (view != null) {
                return view.findViewById(i8);
            }
            throw new IllegalStateException("Fragment " + p.this + " does not have a view");
        }

        @Override // androidx.fragment.app.w
        public boolean d() {
            return p.this.J != null;
        }
    }

    /* loaded from: classes.dex */
    class f implements androidx.lifecycle.k {
        f() {
        }

        @Override // androidx.lifecycle.k
        public void f(androidx.lifecycle.m mVar, i.a aVar) {
            View view;
            if (aVar != i.a.ON_STOP || (view = p.this.J) == null) {
                return;
            }
            view.cancelPendingInputEvents();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static class g {

        /* renamed from: a  reason: collision with root package name */
        View f3591a;

        /* renamed from: b  reason: collision with root package name */
        boolean f3592b;

        /* renamed from: c  reason: collision with root package name */
        int f3593c;

        /* renamed from: d  reason: collision with root package name */
        int f3594d;

        /* renamed from: e  reason: collision with root package name */
        int f3595e;

        /* renamed from: f  reason: collision with root package name */
        int f3596f;

        /* renamed from: g  reason: collision with root package name */
        int f3597g;

        /* renamed from: h  reason: collision with root package name */
        ArrayList f3598h;

        /* renamed from: i  reason: collision with root package name */
        ArrayList f3599i;

        /* renamed from: j  reason: collision with root package name */
        Object f3600j = null;

        /* renamed from: k  reason: collision with root package name */
        Object f3601k;

        /* renamed from: l  reason: collision with root package name */
        Object f3602l;

        /* renamed from: m  reason: collision with root package name */
        Object f3603m;

        /* renamed from: n  reason: collision with root package name */
        Object f3604n;

        /* renamed from: o  reason: collision with root package name */
        Object f3605o;

        /* renamed from: p  reason: collision with root package name */
        Boolean f3606p;

        /* renamed from: q  reason: collision with root package name */
        Boolean f3607q;

        /* renamed from: r  reason: collision with root package name */
        float f3608r;

        /* renamed from: s  reason: collision with root package name */
        View f3609s;

        /* renamed from: t  reason: collision with root package name */
        boolean f3610t;

        g() {
            Object obj = p.f3554d0;
            this.f3601k = obj;
            this.f3602l = null;
            this.f3603m = obj;
            this.f3604n = null;
            this.f3605o = obj;
            this.f3608r = 1.0f;
            this.f3609s = null;
        }
    }

    /* loaded from: classes.dex */
    public static class h extends RuntimeException {
        public h(String str, Exception exc) {
            super(str, exc);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static abstract class i {
        private i() {
        }

        /* synthetic */ i(a aVar) {
            this();
        }

        abstract void a();
    }

    public p() {
        R();
    }

    private p O(boolean z7) {
        String str;
        if (z7) {
            s1.c.h(this);
        }
        p pVar = this.f3565h;
        if (pVar != null) {
            return pVar;
        }
        i0 i0Var = this.f3578u;
        if (i0Var == null || (str = this.f3566i) == null) {
            return null;
        }
        return i0Var.f0(str);
    }

    private void R() {
        this.U = new androidx.lifecycle.n(this);
        this.Y = w1.e.a(this);
        this.X = null;
        if (this.f3558b0.contains(this.f3560c0)) {
            return;
        }
        i1(this.f3560c0);
    }

    public static p T(Context context, String str, Bundle bundle) {
        try {
            p pVar = (p) z.d(context.getClassLoader(), str).getConstructor(new Class[0]).newInstance(new Object[0]);
            if (bundle != null) {
                bundle.setClassLoader(pVar.getClass().getClassLoader());
                pVar.q1(bundle);
            }
            return pVar;
        } catch (IllegalAccessException e8) {
            throw new h("Unable to instantiate fragment " + str + ": make sure class name exists, is public, and has an empty constructor that is public", e8);
        } catch (InstantiationException e9) {
            throw new h("Unable to instantiate fragment " + str + ": make sure class name exists, is public, and has an empty constructor that is public", e9);
        } catch (NoSuchMethodException e10) {
            throw new h("Unable to instantiate fragment " + str + ": could not find Fragment constructor", e10);
        } catch (InvocationTargetException e11) {
            throw new h("Unable to instantiate fragment " + str + ": calling Fragment constructor caused an exception", e11);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void b0() {
        this.V.d(this.f3561d);
        this.f3561d = null;
    }

    private g h() {
        if (this.M == null) {
            this.M = new g();
        }
        return this.M;
    }

    private void i1(i iVar) {
        if (this.f3555a >= 0) {
            iVar.a();
        } else {
            this.f3558b0.add(iVar);
        }
    }

    private void n1() {
        if (i0.I0(3)) {
            Log.d("FragmentManager", "moveto RESTORE_VIEW_STATE: " + this);
        }
        if (this.J != null) {
            Bundle bundle = this.f3557b;
            o1(bundle != null ? bundle.getBundle("savedInstanceState") : null);
        }
        this.f3557b = null;
    }

    private int z() {
        i.b bVar = this.T;
        return (bVar == i.b.INITIALIZED || this.f3581x == null) ? bVar.ordinal() : Math.min(bVar.ordinal(), this.f3581x.z());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int A() {
        g gVar = this.M;
        if (gVar == null) {
            return 0;
        }
        return gVar.f3597g;
    }

    public void A0(boolean z7) {
    }

    public final p B() {
        return this.f3581x;
    }

    public void B0(Menu menu) {
    }

    public final i0 C() {
        i0 i0Var = this.f3578u;
        if (i0Var != null) {
            return i0Var;
        }
        throw new IllegalStateException("Fragment " + this + " not associated with a fragment manager.");
    }

    public void C0(boolean z7) {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean D() {
        g gVar = this.M;
        if (gVar == null) {
            return false;
        }
        return gVar.f3592b;
    }

    public void D0(int i8, String[] strArr, int[] iArr) {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int E() {
        g gVar = this.M;
        if (gVar == null) {
            return 0;
        }
        return gVar.f3595e;
    }

    public void E0() {
        this.H = true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int F() {
        g gVar = this.M;
        if (gVar == null) {
            return 0;
        }
        return gVar.f3596f;
    }

    public void F0(Bundle bundle) {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public float G() {
        g gVar = this.M;
        if (gVar == null) {
            return 1.0f;
        }
        return gVar.f3608r;
    }

    public void G0() {
        this.H = true;
    }

    public Object H() {
        g gVar = this.M;
        if (gVar == null) {
            return null;
        }
        Object obj = gVar.f3603m;
        return obj == f3554d0 ? u() : obj;
    }

    public void H0() {
        this.H = true;
    }

    public final Resources I() {
        return k1().getResources();
    }

    public void I0(View view, Bundle bundle) {
    }

    public Object J() {
        g gVar = this.M;
        if (gVar == null) {
            return null;
        }
        Object obj = gVar.f3601k;
        return obj == f3554d0 ? r() : obj;
    }

    public void J0(Bundle bundle) {
        this.H = true;
    }

    public Object K() {
        g gVar = this.M;
        if (gVar == null) {
            return null;
        }
        return gVar.f3604n;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void K0(Bundle bundle) {
        this.f3580w.Y0();
        this.f3555a = 3;
        this.H = false;
        d0(bundle);
        if (this.H) {
            n1();
            this.f3580w.x();
            return;
        }
        throw new a1("Fragment " + this + " did not call through to super.onActivityCreated()");
    }

    public Object L() {
        g gVar = this.M;
        if (gVar == null) {
            return null;
        }
        Object obj = gVar.f3605o;
        return obj == f3554d0 ? K() : obj;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void L0() {
        Iterator it = this.f3558b0.iterator();
        while (it.hasNext()) {
            ((i) it.next()).a();
        }
        this.f3558b0.clear();
        this.f3580w.l(this.f3579v, f(), this);
        this.f3555a = 0;
        this.H = false;
        g0(this.f3579v.f());
        if (this.H) {
            this.f3578u.H(this);
            this.f3580w.y();
            return;
        }
        throw new a1("Fragment " + this + " did not call through to super.onAttach()");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ArrayList M() {
        ArrayList arrayList;
        g gVar = this.M;
        return (gVar == null || (arrayList = gVar.f3598h) == null) ? new ArrayList() : arrayList;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void M0(Configuration configuration) {
        onConfigurationChanged(configuration);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ArrayList N() {
        ArrayList arrayList;
        g gVar = this.M;
        return (gVar == null || (arrayList = gVar.f3599i) == null) ? new ArrayList() : arrayList;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean N0(MenuItem menuItem) {
        if (this.B) {
            return false;
        }
        if (i0(menuItem)) {
            return true;
        }
        return this.f3580w.A(menuItem);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void O0(Bundle bundle) {
        this.f3580w.Y0();
        this.f3555a = 1;
        this.H = false;
        this.U.a(new f());
        j0(bundle);
        this.R = true;
        if (this.H) {
            this.U.h(i.a.ON_CREATE);
            return;
        }
        throw new a1("Fragment " + this + " did not call through to super.onCreate()");
    }

    public View P() {
        return this.J;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean P0(Menu menu, MenuInflater menuInflater) {
        boolean z7 = false;
        if (this.B) {
            return false;
        }
        if (this.F && this.G) {
            m0(menu, menuInflater);
            z7 = true;
        }
        return z7 | this.f3580w.C(menu, menuInflater);
    }

    public androidx.lifecycle.p Q() {
        return this.W;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void Q0(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.f3580w.Y0();
        this.f3576s = true;
        this.V = new u0(this, getViewModelStore(), new Runnable() { // from class: androidx.fragment.app.o
            @Override // java.lang.Runnable
            public final void run() {
                p.this.b0();
            }
        });
        View n02 = n0(layoutInflater, viewGroup, bundle);
        this.J = n02;
        if (n02 == null) {
            if (this.V.c()) {
                throw new IllegalStateException("Called getViewLifecycleOwner() but onCreateView() returned null");
            }
            this.V = null;
            return;
        }
        this.V.b();
        if (i0.I0(3)) {
            Log.d("FragmentManager", "Setting ViewLifecycleOwner on View " + this.J + " for Fragment " + this);
        }
        androidx.lifecycle.p0.a(this.J, this.V);
        androidx.lifecycle.q0.a(this.J, this.V);
        w1.g.a(this.J, this.V);
        this.W.o(this.V);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void R0() {
        this.f3580w.D();
        this.U.h(i.a.ON_DESTROY);
        this.f3555a = 0;
        this.H = false;
        this.R = false;
        o0();
        if (this.H) {
            return;
        }
        throw new a1("Fragment " + this + " did not call through to super.onDestroy()");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void S() {
        R();
        this.S = this.f3563f;
        this.f3563f = UUID.randomUUID().toString();
        this.f3569l = false;
        this.f3570m = false;
        this.f3573p = false;
        this.f3574q = false;
        this.f3575r = false;
        this.f3577t = 0;
        this.f3578u = null;
        this.f3580w = new j0();
        this.f3579v = null;
        this.f3582y = 0;
        this.f3583z = 0;
        this.A = null;
        this.B = false;
        this.C = false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void S0() {
        this.f3580w.E();
        if (this.J != null && this.V.getLifecycle().b().e(i.b.CREATED)) {
            this.V.a(i.a.ON_DESTROY);
        }
        this.f3555a = 1;
        this.H = false;
        q0();
        if (this.H) {
            androidx.loader.app.a.b(this).c();
            this.f3576s = false;
            return;
        }
        throw new a1("Fragment " + this + " did not call through to super.onDestroyView()");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void T0() {
        this.f3555a = -1;
        this.H = false;
        r0();
        this.Q = null;
        if (this.H) {
            if (this.f3580w.H0()) {
                return;
            }
            this.f3580w.D();
            this.f3580w = new j0();
            return;
        }
        throw new a1("Fragment " + this + " did not call through to super.onDetach()");
    }

    public final boolean U() {
        return this.f3579v != null && this.f3569l;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public LayoutInflater U0(Bundle bundle) {
        LayoutInflater s02 = s0(bundle);
        this.Q = s02;
        return s02;
    }

    public final boolean V() {
        i0 i0Var;
        return this.B || ((i0Var = this.f3578u) != null && i0Var.L0(this.f3581x));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void V0() {
        onLowMemory();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final boolean W() {
        return this.f3577t > 0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void W0(boolean z7) {
        w0(z7);
    }

    public final boolean X() {
        i0 i0Var;
        return this.G && ((i0Var = this.f3578u) == null || i0Var.M0(this.f3581x));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean X0(MenuItem menuItem) {
        if (this.B) {
            return false;
        }
        if (this.F && this.G && x0(menuItem)) {
            return true;
        }
        return this.f3580w.J(menuItem);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean Y() {
        g gVar = this.M;
        if (gVar == null) {
            return false;
        }
        return gVar.f3610t;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void Y0(Menu menu) {
        if (this.B) {
            return;
        }
        if (this.F && this.G) {
            y0(menu);
        }
        this.f3580w.K(menu);
    }

    public final boolean Z() {
        return this.f3570m;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void Z0() {
        this.f3580w.M();
        if (this.J != null) {
            this.V.a(i.a.ON_PAUSE);
        }
        this.U.h(i.a.ON_PAUSE);
        this.f3555a = 6;
        this.H = false;
        z0();
        if (this.H) {
            return;
        }
        throw new a1("Fragment " + this + " did not call through to super.onPause()");
    }

    public final boolean a0() {
        i0 i0Var = this.f3578u;
        if (i0Var == null) {
            return false;
        }
        return i0Var.P0();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a1(boolean z7) {
        A0(z7);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean b1(Menu menu) {
        boolean z7 = false;
        if (this.B) {
            return false;
        }
        if (this.F && this.G) {
            B0(menu);
            z7 = true;
        }
        return z7 | this.f3580w.O(menu);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void c0() {
        this.f3580w.Y0();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void c1() {
        boolean N0 = this.f3578u.N0(this);
        Boolean bool = this.f3568k;
        if (bool == null || bool.booleanValue() != N0) {
            this.f3568k = Boolean.valueOf(N0);
            C0(N0);
            this.f3580w.P();
        }
    }

    public void d0(Bundle bundle) {
        this.H = true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void d1() {
        this.f3580w.Y0();
        this.f3580w.a0(true);
        this.f3555a = 7;
        this.H = false;
        E0();
        if (!this.H) {
            throw new a1("Fragment " + this + " did not call through to super.onResume()");
        }
        androidx.lifecycle.n nVar = this.U;
        i.a aVar = i.a.ON_RESUME;
        nVar.h(aVar);
        if (this.J != null) {
            this.V.a(aVar);
        }
        this.f3580w.Q();
    }

    void e(boolean z7) {
        ViewGroup viewGroup;
        i0 i0Var;
        g gVar = this.M;
        if (gVar != null) {
            gVar.f3610t = false;
        }
        if (this.J == null || (viewGroup = this.I) == null || (i0Var = this.f3578u) == null) {
            return;
        }
        y0 u7 = y0.u(viewGroup, i0Var);
        u7.x();
        if (z7) {
            this.f3579v.h().post(new d(u7));
        } else {
            u7.n();
        }
        Handler handler = this.N;
        if (handler != null) {
            handler.removeCallbacks(this.O);
            this.N = null;
        }
    }

    public void e0(int i8, int i9, Intent intent) {
        if (i0.I0(2)) {
            Log.v("FragmentManager", "Fragment " + this + " received the following in onActivityResult(): requestCode: " + i8 + " resultCode: " + i9 + " data: " + intent);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void e1(Bundle bundle) {
        F0(bundle);
    }

    public final boolean equals(Object obj) {
        return super.equals(obj);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public w f() {
        return new e();
    }

    public void f0(Activity activity) {
        this.H = true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void f1() {
        this.f3580w.Y0();
        this.f3580w.a0(true);
        this.f3555a = 5;
        this.H = false;
        G0();
        if (!this.H) {
            throw new a1("Fragment " + this + " did not call through to super.onStart()");
        }
        androidx.lifecycle.n nVar = this.U;
        i.a aVar = i.a.ON_START;
        nVar.h(aVar);
        if (this.J != null) {
            this.V.a(aVar);
        }
        this.f3580w.R();
    }

    public void g(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        printWriter.print(str);
        printWriter.print("mFragmentId=#");
        printWriter.print(Integer.toHexString(this.f3582y));
        printWriter.print(" mContainerId=#");
        printWriter.print(Integer.toHexString(this.f3583z));
        printWriter.print(" mTag=");
        printWriter.println(this.A);
        printWriter.print(str);
        printWriter.print("mState=");
        printWriter.print(this.f3555a);
        printWriter.print(" mWho=");
        printWriter.print(this.f3563f);
        printWriter.print(" mBackStackNesting=");
        printWriter.println(this.f3577t);
        printWriter.print(str);
        printWriter.print("mAdded=");
        printWriter.print(this.f3569l);
        printWriter.print(" mRemoving=");
        printWriter.print(this.f3570m);
        printWriter.print(" mFromLayout=");
        printWriter.print(this.f3573p);
        printWriter.print(" mInLayout=");
        printWriter.println(this.f3574q);
        printWriter.print(str);
        printWriter.print("mHidden=");
        printWriter.print(this.B);
        printWriter.print(" mDetached=");
        printWriter.print(this.C);
        printWriter.print(" mMenuVisible=");
        printWriter.print(this.G);
        printWriter.print(" mHasMenu=");
        printWriter.println(this.F);
        printWriter.print(str);
        printWriter.print("mRetainInstance=");
        printWriter.print(this.D);
        printWriter.print(" mUserVisibleHint=");
        printWriter.println(this.L);
        if (this.f3578u != null) {
            printWriter.print(str);
            printWriter.print("mFragmentManager=");
            printWriter.println(this.f3578u);
        }
        if (this.f3579v != null) {
            printWriter.print(str);
            printWriter.print("mHost=");
            printWriter.println(this.f3579v);
        }
        if (this.f3581x != null) {
            printWriter.print(str);
            printWriter.print("mParentFragment=");
            printWriter.println(this.f3581x);
        }
        if (this.f3564g != null) {
            printWriter.print(str);
            printWriter.print("mArguments=");
            printWriter.println(this.f3564g);
        }
        if (this.f3557b != null) {
            printWriter.print(str);
            printWriter.print("mSavedFragmentState=");
            printWriter.println(this.f3557b);
        }
        if (this.f3559c != null) {
            printWriter.print(str);
            printWriter.print("mSavedViewState=");
            printWriter.println(this.f3559c);
        }
        if (this.f3561d != null) {
            printWriter.print(str);
            printWriter.print("mSavedViewRegistryState=");
            printWriter.println(this.f3561d);
        }
        p O = O(false);
        if (O != null) {
            printWriter.print(str);
            printWriter.print("mTarget=");
            printWriter.print(O);
            printWriter.print(" mTargetRequestCode=");
            printWriter.println(this.f3567j);
        }
        printWriter.print(str);
        printWriter.print("mPopDirection=");
        printWriter.println(D());
        if (q() != 0) {
            printWriter.print(str);
            printWriter.print("getEnterAnim=");
            printWriter.println(q());
        }
        if (t() != 0) {
            printWriter.print(str);
            printWriter.print("getExitAnim=");
            printWriter.println(t());
        }
        if (E() != 0) {
            printWriter.print(str);
            printWriter.print("getPopEnterAnim=");
            printWriter.println(E());
        }
        if (F() != 0) {
            printWriter.print(str);
            printWriter.print("getPopExitAnim=");
            printWriter.println(F());
        }
        if (this.I != null) {
            printWriter.print(str);
            printWriter.print("mContainer=");
            printWriter.println(this.I);
        }
        if (this.J != null) {
            printWriter.print(str);
            printWriter.print("mView=");
            printWriter.println(this.J);
        }
        if (m() != null) {
            printWriter.print(str);
            printWriter.print("mAnimatingAway=");
            printWriter.println(m());
        }
        if (p() != null) {
            androidx.loader.app.a.b(this).a(str, fileDescriptor, printWriter, strArr);
        }
        printWriter.print(str);
        printWriter.println("Child " + this.f3580w + ":");
        i0 i0Var = this.f3580w;
        i0Var.W(str + "  ", fileDescriptor, printWriter, strArr);
    }

    public void g0(Context context) {
        this.H = true;
        a0 a0Var = this.f3579v;
        Activity e8 = a0Var == null ? null : a0Var.e();
        if (e8 != null) {
            this.H = false;
            f0(e8);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void g1() {
        this.f3580w.T();
        if (this.J != null) {
            this.V.a(i.a.ON_STOP);
        }
        this.U.h(i.a.ON_STOP);
        this.f3555a = 4;
        this.H = false;
        H0();
        if (this.H) {
            return;
        }
        throw new a1("Fragment " + this + " did not call through to super.onStop()");
    }

    @Override // androidx.lifecycle.g
    public v1.a getDefaultViewModelCreationExtras() {
        Application application;
        Context applicationContext = k1().getApplicationContext();
        while (true) {
            if (!(applicationContext instanceof ContextWrapper)) {
                application = null;
                break;
            } else if (applicationContext instanceof Application) {
                application = (Application) applicationContext;
                break;
            } else {
                applicationContext = ((ContextWrapper) applicationContext).getBaseContext();
            }
        }
        if (application == null && i0.I0(3)) {
            Log.d("FragmentManager", "Could not find Application instance from Context " + k1().getApplicationContext() + ", you will not be able to use AndroidViewModel with the default ViewModelProvider.Factory");
        }
        v1.b bVar = new v1.b();
        if (application != null) {
            bVar.c(l0.a.f3793h, application);
        }
        bVar.c(androidx.lifecycle.e0.f3755a, this);
        bVar.c(androidx.lifecycle.e0.f3756b, this);
        if (n() != null) {
            bVar.c(androidx.lifecycle.e0.f3757c, n());
        }
        return bVar;
    }

    @Override // androidx.lifecycle.m
    public androidx.lifecycle.i getLifecycle() {
        return this.U;
    }

    @Override // w1.f
    public final w1.d getSavedStateRegistry() {
        return this.Y.b();
    }

    @Override // androidx.lifecycle.o0
    public androidx.lifecycle.n0 getViewModelStore() {
        if (this.f3578u != null) {
            if (z() != i.b.INITIALIZED.ordinal()) {
                return this.f3578u.D0(this);
            }
            throw new IllegalStateException("Calling getViewModelStore() before a Fragment reaches onCreate() when using setMaxLifecycle(INITIALIZED) is not supported");
        }
        throw new IllegalStateException("Can't access ViewModels from detached fragment");
    }

    public void h0(p pVar) {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void h1() {
        Bundle bundle = this.f3557b;
        I0(this.J, bundle != null ? bundle.getBundle("savedInstanceState") : null);
        this.f3580w.U();
    }

    public final int hashCode() {
        return super.hashCode();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public p i(String str) {
        return str.equals(this.f3563f) ? this : this.f3580w.j0(str);
    }

    public boolean i0(MenuItem menuItem) {
        return false;
    }

    public final u j() {
        a0 a0Var = this.f3579v;
        if (a0Var == null) {
            return null;
        }
        return (u) a0Var.e();
    }

    public void j0(Bundle bundle) {
        this.H = true;
        m1();
        if (this.f3580w.O0(1)) {
            return;
        }
        this.f3580w.B();
    }

    public final u j1() {
        u j8 = j();
        if (j8 != null) {
            return j8;
        }
        throw new IllegalStateException("Fragment " + this + " not attached to an activity.");
    }

    public boolean k() {
        Boolean bool;
        g gVar = this.M;
        if (gVar == null || (bool = gVar.f3607q) == null) {
            return true;
        }
        return bool.booleanValue();
    }

    public Animation k0(int i8, boolean z7, int i9) {
        return null;
    }

    public final Context k1() {
        Context p8 = p();
        if (p8 != null) {
            return p8;
        }
        throw new IllegalStateException("Fragment " + this + " not attached to a context.");
    }

    public boolean l() {
        Boolean bool;
        g gVar = this.M;
        if (gVar == null || (bool = gVar.f3606p) == null) {
            return true;
        }
        return bool.booleanValue();
    }

    public Animator l0(int i8, boolean z7, int i9) {
        return null;
    }

    public final View l1() {
        View P = P();
        if (P != null) {
            return P;
        }
        throw new IllegalStateException("Fragment " + this + " did not return a View from onCreateView() or this was called before onCreateView().");
    }

    View m() {
        g gVar = this.M;
        if (gVar == null) {
            return null;
        }
        return gVar.f3591a;
    }

    public void m0(Menu menu, MenuInflater menuInflater) {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void m1() {
        Bundle bundle;
        Bundle bundle2 = this.f3557b;
        if (bundle2 == null || (bundle = bundle2.getBundle("childFragmentManager")) == null) {
            return;
        }
        this.f3580w.l1(bundle);
        this.f3580w.B();
    }

    public final Bundle n() {
        return this.f3564g;
    }

    public View n0(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        int i8 = this.Z;
        if (i8 != 0) {
            return layoutInflater.inflate(i8, viewGroup, false);
        }
        return null;
    }

    public final i0 o() {
        if (this.f3579v != null) {
            return this.f3580w;
        }
        throw new IllegalStateException("Fragment " + this + " has not been attached yet.");
    }

    public void o0() {
        this.H = true;
    }

    final void o1(Bundle bundle) {
        SparseArray<Parcelable> sparseArray = this.f3559c;
        if (sparseArray != null) {
            this.J.restoreHierarchyState(sparseArray);
            this.f3559c = null;
        }
        this.H = false;
        J0(bundle);
        if (this.H) {
            if (this.J != null) {
                this.V.a(i.a.ON_CREATE);
                return;
            }
            return;
        }
        throw new a1("Fragment " + this + " did not call through to super.onViewStateRestored()");
    }

    @Override // android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        this.H = true;
    }

    @Override // android.view.View.OnCreateContextMenuListener
    public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {
        j1().onCreateContextMenu(contextMenu, view, contextMenuInfo);
    }

    @Override // android.content.ComponentCallbacks
    public void onLowMemory() {
        this.H = true;
    }

    public Context p() {
        a0 a0Var = this.f3579v;
        if (a0Var == null) {
            return null;
        }
        return a0Var.f();
    }

    public void p0() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void p1(int i8, int i9, int i10, int i11) {
        if (this.M == null && i8 == 0 && i9 == 0 && i10 == 0 && i11 == 0) {
            return;
        }
        h().f3593c = i8;
        h().f3594d = i9;
        h().f3595e = i10;
        h().f3596f = i11;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int q() {
        g gVar = this.M;
        if (gVar == null) {
            return 0;
        }
        return gVar.f3593c;
    }

    public void q0() {
        this.H = true;
    }

    public void q1(Bundle bundle) {
        if (this.f3578u != null && a0()) {
            throw new IllegalStateException("Fragment already added and state has been saved");
        }
        this.f3564g = bundle;
    }

    public Object r() {
        g gVar = this.M;
        if (gVar == null) {
            return null;
        }
        return gVar.f3600j;
    }

    public void r0() {
        this.H = true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void r1(View view) {
        h().f3609s = view;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public androidx.core.app.r s() {
        g gVar = this.M;
        if (gVar == null) {
            return null;
        }
        gVar.getClass();
        return null;
    }

    public LayoutInflater s0(Bundle bundle) {
        return y(bundle);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void s1(int i8) {
        if (this.M == null && i8 == 0) {
            return;
        }
        h();
        this.M.f3597g = i8;
    }

    public void startActivityForResult(Intent intent, int i8) {
        w1(intent, i8, null);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int t() {
        g gVar = this.M;
        if (gVar == null) {
            return 0;
        }
        return gVar.f3594d;
    }

    public void t0(boolean z7) {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void t1(boolean z7) {
        if (this.M == null) {
            return;
        }
        h().f3592b = z7;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(128);
        sb.append(getClass().getSimpleName());
        sb.append("{");
        sb.append(Integer.toHexString(System.identityHashCode(this)));
        sb.append("}");
        sb.append(" (");
        sb.append(this.f3563f);
        if (this.f3582y != 0) {
            sb.append(" id=0x");
            sb.append(Integer.toHexString(this.f3582y));
        }
        if (this.A != null) {
            sb.append(" tag=");
            sb.append(this.A);
        }
        sb.append(")");
        return sb.toString();
    }

    public Object u() {
        g gVar = this.M;
        if (gVar == null) {
            return null;
        }
        return gVar.f3602l;
    }

    public void u0(Activity activity, AttributeSet attributeSet, Bundle bundle) {
        this.H = true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void u1(float f8) {
        h().f3608r = f8;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public androidx.core.app.r v() {
        g gVar = this.M;
        if (gVar == null) {
            return null;
        }
        gVar.getClass();
        return null;
    }

    public void v0(Context context, AttributeSet attributeSet, Bundle bundle) {
        this.H = true;
        a0 a0Var = this.f3579v;
        Activity e8 = a0Var == null ? null : a0Var.e();
        if (e8 != null) {
            this.H = false;
            u0(e8, attributeSet, bundle);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void v1(ArrayList arrayList, ArrayList arrayList2) {
        h();
        g gVar = this.M;
        gVar.f3598h = arrayList;
        gVar.f3599i = arrayList2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public View w() {
        g gVar = this.M;
        if (gVar == null) {
            return null;
        }
        return gVar.f3609s;
    }

    public void w0(boolean z7) {
    }

    public void w1(Intent intent, int i8, Bundle bundle) {
        if (this.f3579v != null) {
            C().V0(this, intent, i8, bundle);
            return;
        }
        throw new IllegalStateException("Fragment " + this + " not attached to Activity");
    }

    public final Object x() {
        a0 a0Var = this.f3579v;
        if (a0Var == null) {
            return null;
        }
        return a0Var.j();
    }

    public boolean x0(MenuItem menuItem) {
        return false;
    }

    public void x1(IntentSender intentSender, int i8, Intent intent, int i9, int i10, int i11, Bundle bundle) {
        if (this.f3579v == null) {
            throw new IllegalStateException("Fragment " + this + " not attached to Activity");
        }
        if (i0.I0(2)) {
            Log.v("FragmentManager", "Fragment " + this + " received the following in startIntentSenderForResult() requestCode: " + i8 + " IntentSender: " + intentSender + " fillInIntent: " + intent + " options: " + bundle);
        }
        C().W0(this, intentSender, i8, intent, i9, i10, i11, bundle);
    }

    public LayoutInflater y(Bundle bundle) {
        a0 a0Var = this.f3579v;
        if (a0Var != null) {
            LayoutInflater k8 = a0Var.k();
            androidx.core.view.u.a(k8, this.f3580w.w0());
            return k8;
        }
        throw new IllegalStateException("onGetLayoutInflater() cannot be executed until the Fragment is attached to the FragmentManager.");
    }

    public void y0(Menu menu) {
    }

    public void y1() {
        if (this.M == null || !h().f3610t) {
            return;
        }
        if (this.f3579v == null) {
            h().f3610t = false;
        } else if (Looper.myLooper() != this.f3579v.h().getLooper()) {
            this.f3579v.h().postAtFrontOfQueue(new c());
        } else {
            e(true);
        }
    }

    public void z0() {
        this.H = true;
    }
}
