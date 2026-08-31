package androidx.fragment.app;

import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Set;
/* loaded from: classes.dex */
public abstract class y0 {

    /* renamed from: f  reason: collision with root package name */
    public static final a f3697f = new a(null);

    /* renamed from: a  reason: collision with root package name */
    private final ViewGroup f3698a;

    /* renamed from: b  reason: collision with root package name */
    private final List f3699b;

    /* renamed from: c  reason: collision with root package name */
    private final List f3700c;

    /* renamed from: d  reason: collision with root package name */
    private boolean f3701d;

    /* renamed from: e  reason: collision with root package name */
    private boolean f3702e;

    /* loaded from: classes.dex */
    public static final class a {
        private a() {
        }

        public /* synthetic */ a(d7.g gVar) {
            this();
        }

        public final y0 a(ViewGroup viewGroup, i0 i0Var) {
            d7.k.e(viewGroup, "container");
            d7.k.e(i0Var, "fragmentManager");
            z0 A0 = i0Var.A0();
            d7.k.d(A0, "fragmentManager.specialEffectsControllerFactory");
            return b(viewGroup, A0);
        }

        public final y0 b(ViewGroup viewGroup, z0 z0Var) {
            d7.k.e(viewGroup, "container");
            d7.k.e(z0Var, "factory");
            Object tag = viewGroup.getTag(r1.b.f11311b);
            if (tag instanceof y0) {
                return (y0) tag;
            }
            y0 a8 = z0Var.a(viewGroup);
            d7.k.d(a8, "factory.createController(container)");
            viewGroup.setTag(r1.b.f11311b, a8);
            return a8;
        }
    }

    /* loaded from: classes.dex */
    public static class b {

        /* renamed from: a  reason: collision with root package name */
        private final boolean f3703a;

        /* renamed from: b  reason: collision with root package name */
        private boolean f3704b;

        /* renamed from: c  reason: collision with root package name */
        private boolean f3705c;

        public final void a(ViewGroup viewGroup) {
            d7.k.e(viewGroup, "container");
            if (!this.f3705c) {
                c(viewGroup);
            }
            this.f3705c = true;
        }

        public boolean b() {
            return this.f3703a;
        }

        public abstract void c(ViewGroup viewGroup);

        public abstract void d(ViewGroup viewGroup);

        public void e(androidx.activity.b bVar, ViewGroup viewGroup) {
            d7.k.e(bVar, "backEvent");
            d7.k.e(viewGroup, "container");
        }

        public void f(ViewGroup viewGroup) {
            d7.k.e(viewGroup, "container");
        }

        public final void g(ViewGroup viewGroup) {
            d7.k.e(viewGroup, "container");
            if (!this.f3704b) {
                f(viewGroup);
            }
            this.f3704b = true;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static final class c extends d {

        /* renamed from: l  reason: collision with root package name */
        private final o0 f3706l;

        /* JADX WARN: Illegal instructions before constructor call */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public c(androidx.fragment.app.y0.d.b r3, androidx.fragment.app.y0.d.a r4, androidx.fragment.app.o0 r5) {
            /*
                r2 = this;
                java.lang.String r0 = "finalState"
                d7.k.e(r3, r0)
                java.lang.String r0 = "lifecycleImpact"
                d7.k.e(r4, r0)
                java.lang.String r0 = "fragmentStateManager"
                d7.k.e(r5, r0)
                androidx.fragment.app.p r0 = r5.k()
                java.lang.String r1 = "fragmentStateManager.fragment"
                d7.k.d(r0, r1)
                r2.<init>(r3, r4, r0)
                r2.f3706l = r5
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.fragment.app.y0.c.<init>(androidx.fragment.app.y0$d$b, androidx.fragment.app.y0$d$a, androidx.fragment.app.o0):void");
        }

        @Override // androidx.fragment.app.y0.d
        public void e() {
            super.e();
            i().f3571n = false;
            this.f3706l.m();
        }

        @Override // androidx.fragment.app.y0.d
        public void q() {
            if (o()) {
                return;
            }
            super.q();
            if (j() != d.a.ADDING) {
                if (j() == d.a.REMOVING) {
                    p k8 = this.f3706l.k();
                    d7.k.d(k8, "fragmentStateManager.fragment");
                    View l12 = k8.l1();
                    d7.k.d(l12, "fragment.requireView()");
                    if (i0.I0(2)) {
                        Log.v("FragmentManager", "Clearing focus " + l12.findFocus() + " on view " + l12 + " for Fragment " + k8);
                    }
                    l12.clearFocus();
                    return;
                }
                return;
            }
            p k9 = this.f3706l.k();
            d7.k.d(k9, "fragmentStateManager.fragment");
            View findFocus = k9.J.findFocus();
            if (findFocus != null) {
                k9.r1(findFocus);
                if (i0.I0(2)) {
                    Log.v("FragmentManager", "requestFocus: Saved focused view " + findFocus + " for Fragment " + k9);
                }
            }
            View l13 = i().l1();
            d7.k.d(l13, "this.fragment.requireView()");
            if (l13.getParent() == null) {
                this.f3706l.b();
                l13.setAlpha(0.0f);
            }
            if ((l13.getAlpha() == 0.0f) && l13.getVisibility() == 0) {
                l13.setVisibility(4);
            }
            l13.setAlpha(k9.G());
        }
    }

    /* loaded from: classes.dex */
    public static class d {

        /* renamed from: a  reason: collision with root package name */
        private b f3707a;

        /* renamed from: b  reason: collision with root package name */
        private a f3708b;

        /* renamed from: c  reason: collision with root package name */
        private final p f3709c;

        /* renamed from: d  reason: collision with root package name */
        private final List f3710d;

        /* renamed from: e  reason: collision with root package name */
        private boolean f3711e;

        /* renamed from: f  reason: collision with root package name */
        private boolean f3712f;

        /* renamed from: g  reason: collision with root package name */
        private boolean f3713g;

        /* renamed from: h  reason: collision with root package name */
        private boolean f3714h;

        /* renamed from: i  reason: collision with root package name */
        private boolean f3715i;

        /* renamed from: j  reason: collision with root package name */
        private final List f3716j;

        /* renamed from: k  reason: collision with root package name */
        private final List f3717k;

        /* loaded from: classes.dex */
        public enum a {
            NONE,
            ADDING,
            REMOVING
        }

        /* loaded from: classes.dex */
        public enum b {
            REMOVED,
            VISIBLE,
            GONE,
            INVISIBLE;
            

            /* renamed from: e  reason: collision with root package name */
            public static final a f3722e = new a(null);

            /* loaded from: classes.dex */
            public static final class a {
                private a() {
                }

                public /* synthetic */ a(d7.g gVar) {
                    this();
                }

                public final b a(View view) {
                    d7.k.e(view, "<this>");
                    return (((view.getAlpha() > 0.0f ? 1 : (view.getAlpha() == 0.0f ? 0 : -1)) == 0) && view.getVisibility() == 0) ? b.INVISIBLE : b(view.getVisibility());
                }

                public final b b(int i8) {
                    if (i8 != 0) {
                        if (i8 != 4) {
                            if (i8 == 8) {
                                return b.GONE;
                            }
                            throw new IllegalArgumentException("Unknown visibility " + i8);
                        }
                        return b.INVISIBLE;
                    }
                    return b.VISIBLE;
                }
            }

            /* renamed from: androidx.fragment.app.y0$d$b$b  reason: collision with other inner class name */
            /* loaded from: classes.dex */
            public /* synthetic */ class C0040b {

                /* renamed from: a  reason: collision with root package name */
                public static final /* synthetic */ int[] f3728a;

                static {
                    int[] iArr = new int[b.values().length];
                    try {
                        iArr[b.REMOVED.ordinal()] = 1;
                    } catch (NoSuchFieldError unused) {
                    }
                    try {
                        iArr[b.VISIBLE.ordinal()] = 2;
                    } catch (NoSuchFieldError unused2) {
                    }
                    try {
                        iArr[b.GONE.ordinal()] = 3;
                    } catch (NoSuchFieldError unused3) {
                    }
                    try {
                        iArr[b.INVISIBLE.ordinal()] = 4;
                    } catch (NoSuchFieldError unused4) {
                    }
                    f3728a = iArr;
                }
            }

            public static final b f(int i8) {
                return f3722e.b(i8);
            }

            public final void e(View view, ViewGroup viewGroup) {
                int i8;
                d7.k.e(view, "view");
                d7.k.e(viewGroup, "container");
                int i9 = C0040b.f3728a[ordinal()];
                if (i9 == 1) {
                    ViewParent parent = view.getParent();
                    ViewGroup viewGroup2 = parent instanceof ViewGroup ? (ViewGroup) parent : null;
                    if (viewGroup2 != null) {
                        if (i0.I0(2)) {
                            Log.v("FragmentManager", "SpecialEffectsController: Removing view " + view + " from container " + viewGroup2);
                        }
                        viewGroup2.removeView(view);
                        return;
                    }
                    return;
                }
                if (i9 == 2) {
                    if (i0.I0(2)) {
                        Log.v("FragmentManager", "SpecialEffectsController: Setting view " + view + " to VISIBLE");
                    }
                    ViewParent parent2 = view.getParent();
                    if ((parent2 instanceof ViewGroup ? (ViewGroup) parent2 : null) == null) {
                        if (i0.I0(2)) {
                            Log.v("FragmentManager", "SpecialEffectsController: Adding view " + view + " to Container " + viewGroup);
                        }
                        viewGroup.addView(view);
                    }
                    i8 = 0;
                } else if (i9 != 3) {
                    i8 = 4;
                    if (i9 != 4) {
                        return;
                    }
                    if (i0.I0(2)) {
                        Log.v("FragmentManager", "SpecialEffectsController: Setting view " + view + " to INVISIBLE");
                    }
                } else {
                    if (i0.I0(2)) {
                        Log.v("FragmentManager", "SpecialEffectsController: Setting view " + view + " to GONE");
                    }
                    i8 = 8;
                }
                view.setVisibility(i8);
            }
        }

        /* loaded from: classes.dex */
        public /* synthetic */ class c {

            /* renamed from: a  reason: collision with root package name */
            public static final /* synthetic */ int[] f3729a;

            static {
                int[] iArr = new int[a.values().length];
                try {
                    iArr[a.ADDING.ordinal()] = 1;
                } catch (NoSuchFieldError unused) {
                }
                try {
                    iArr[a.REMOVING.ordinal()] = 2;
                } catch (NoSuchFieldError unused2) {
                }
                try {
                    iArr[a.NONE.ordinal()] = 3;
                } catch (NoSuchFieldError unused3) {
                }
                f3729a = iArr;
            }
        }

        public d(b bVar, a aVar, p pVar) {
            d7.k.e(bVar, "finalState");
            d7.k.e(aVar, "lifecycleImpact");
            d7.k.e(pVar, "fragment");
            this.f3707a = bVar;
            this.f3708b = aVar;
            this.f3709c = pVar;
            this.f3710d = new ArrayList();
            this.f3715i = true;
            ArrayList arrayList = new ArrayList();
            this.f3716j = arrayList;
            this.f3717k = arrayList;
        }

        public final void a(Runnable runnable) {
            d7.k.e(runnable, "listener");
            this.f3710d.add(runnable);
        }

        public final void b(b bVar) {
            d7.k.e(bVar, "effect");
            this.f3716j.add(bVar);
        }

        public final void c(ViewGroup viewGroup) {
            List<b> v7;
            d7.k.e(viewGroup, "container");
            this.f3714h = false;
            if (this.f3711e) {
                return;
            }
            this.f3711e = true;
            if (this.f3716j.isEmpty()) {
                e();
                return;
            }
            v7 = s6.v.v(this.f3717k);
            for (b bVar : v7) {
                bVar.a(viewGroup);
            }
        }

        public final void d(ViewGroup viewGroup, boolean z7) {
            d7.k.e(viewGroup, "container");
            if (this.f3711e) {
                return;
            }
            if (z7) {
                this.f3713g = true;
            }
            c(viewGroup);
        }

        public void e() {
            this.f3714h = false;
            if (this.f3712f) {
                return;
            }
            if (i0.I0(2)) {
                Log.v("FragmentManager", "SpecialEffectsController: " + this + " has called complete.");
            }
            this.f3712f = true;
            for (Runnable runnable : this.f3710d) {
                runnable.run();
            }
        }

        public final void f(b bVar) {
            d7.k.e(bVar, "effect");
            if (this.f3716j.remove(bVar) && this.f3716j.isEmpty()) {
                e();
            }
        }

        public final List g() {
            return this.f3717k;
        }

        public final b h() {
            return this.f3707a;
        }

        public final p i() {
            return this.f3709c;
        }

        public final a j() {
            return this.f3708b;
        }

        public final boolean k() {
            return this.f3715i;
        }

        public final boolean l() {
            return this.f3711e;
        }

        public final boolean m() {
            return this.f3712f;
        }

        public final boolean n() {
            return this.f3713g;
        }

        public final boolean o() {
            return this.f3714h;
        }

        public final void p(b bVar, a aVar) {
            a aVar2;
            d7.k.e(bVar, "finalState");
            d7.k.e(aVar, "lifecycleImpact");
            int i8 = c.f3729a[aVar.ordinal()];
            if (i8 != 1) {
                if (i8 != 2) {
                    if (i8 == 3 && this.f3707a != b.REMOVED) {
                        if (i0.I0(2)) {
                            Log.v("FragmentManager", "SpecialEffectsController: For fragment " + this.f3709c + " mFinalState = " + this.f3707a + " -> " + bVar + '.');
                        }
                        this.f3707a = bVar;
                        return;
                    }
                    return;
                }
                if (i0.I0(2)) {
                    Log.v("FragmentManager", "SpecialEffectsController: For fragment " + this.f3709c + " mFinalState = " + this.f3707a + " -> REMOVED. mLifecycleImpact  = " + this.f3708b + " to REMOVING.");
                }
                this.f3707a = b.REMOVED;
                aVar2 = a.REMOVING;
            } else if (this.f3707a != b.REMOVED) {
                return;
            } else {
                if (i0.I0(2)) {
                    Log.v("FragmentManager", "SpecialEffectsController: For fragment " + this.f3709c + " mFinalState = REMOVED -> VISIBLE. mLifecycleImpact = " + this.f3708b + " to ADDING.");
                }
                this.f3707a = b.VISIBLE;
                aVar2 = a.ADDING;
            }
            this.f3708b = aVar2;
            this.f3715i = true;
        }

        public void q() {
            this.f3714h = true;
        }

        public final void r(boolean z7) {
            this.f3715i = z7;
        }

        public String toString() {
            String hexString = Integer.toHexString(System.identityHashCode(this));
            return "Operation {" + hexString + "} {finalState = " + this.f3707a + " lifecycleImpact = " + this.f3708b + " fragment = " + this.f3709c + '}';
        }
    }

    /* loaded from: classes.dex */
    public /* synthetic */ class e {

        /* renamed from: a  reason: collision with root package name */
        public static final /* synthetic */ int[] f3730a;

        static {
            int[] iArr = new int[d.a.values().length];
            try {
                iArr[d.a.NONE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            f3730a = iArr;
        }
    }

    public y0(ViewGroup viewGroup) {
        d7.k.e(viewGroup, "container");
        this.f3698a = viewGroup;
        this.f3699b = new ArrayList();
        this.f3700c = new ArrayList();
    }

    private final void A() {
        for (d dVar : this.f3699b) {
            if (dVar.j() == d.a.ADDING) {
                View l12 = dVar.i().l1();
                d7.k.d(l12, "fragment.requireView()");
                dVar.p(d.b.f3722e.b(l12.getVisibility()), d.a.NONE);
            }
        }
    }

    private final void g(d.b bVar, d.a aVar, o0 o0Var) {
        synchronized (this.f3699b) {
            p k8 = o0Var.k();
            d7.k.d(k8, "fragmentStateManager.fragment");
            d o8 = o(k8);
            if (o8 == null) {
                if (o0Var.k().f3571n) {
                    p k9 = o0Var.k();
                    d7.k.d(k9, "fragmentStateManager.fragment");
                    o8 = p(k9);
                } else {
                    o8 = null;
                }
            }
            if (o8 != null) {
                o8.p(bVar, aVar);
                return;
            }
            final c cVar = new c(bVar, aVar, o0Var);
            this.f3699b.add(cVar);
            cVar.a(new Runnable() { // from class: androidx.fragment.app.w0
                @Override // java.lang.Runnable
                public final void run() {
                    y0.h(y0.this, cVar);
                }
            });
            cVar.a(new Runnable() { // from class: androidx.fragment.app.x0
                @Override // java.lang.Runnable
                public final void run() {
                    y0.i(y0.this, cVar);
                }
            });
            r6.r rVar = r6.r.f11372a;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void h(y0 y0Var, c cVar) {
        d7.k.e(y0Var, "this$0");
        d7.k.e(cVar, "$operation");
        if (y0Var.f3699b.contains(cVar)) {
            d.b h8 = cVar.h();
            View view = cVar.i().J;
            d7.k.d(view, "operation.fragment.mView");
            h8.e(view, y0Var.f3698a);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void i(y0 y0Var, c cVar) {
        d7.k.e(y0Var, "this$0");
        d7.k.e(cVar, "$operation");
        y0Var.f3699b.remove(cVar);
        y0Var.f3700c.remove(cVar);
    }

    private final d o(p pVar) {
        Object obj;
        boolean z7;
        Iterator it = this.f3699b.iterator();
        while (true) {
            if (!it.hasNext()) {
                obj = null;
                break;
            }
            obj = it.next();
            d dVar = (d) obj;
            if (!d7.k.a(dVar.i(), pVar) || dVar.l()) {
                z7 = false;
                continue;
            } else {
                z7 = true;
                continue;
            }
            if (z7) {
                break;
            }
        }
        return (d) obj;
    }

    private final d p(p pVar) {
        Object obj;
        boolean z7;
        Iterator it = this.f3700c.iterator();
        while (true) {
            if (!it.hasNext()) {
                obj = null;
                break;
            }
            obj = it.next();
            d dVar = (d) obj;
            if (!d7.k.a(dVar.i(), pVar) || dVar.l()) {
                z7 = false;
                continue;
            } else {
                z7 = true;
                continue;
            }
            if (z7) {
                break;
            }
        }
        return (d) obj;
    }

    public static final y0 u(ViewGroup viewGroup, i0 i0Var) {
        return f3697f.a(viewGroup, i0Var);
    }

    public static final y0 v(ViewGroup viewGroup, z0 z0Var) {
        return f3697f.b(viewGroup, z0Var);
    }

    private final void z(List list) {
        Set y7;
        List v7;
        int size = list.size();
        for (int i8 = 0; i8 < size; i8++) {
            ((d) list.get(i8)).q();
        }
        ArrayList arrayList = new ArrayList();
        Iterator it = list.iterator();
        while (it.hasNext()) {
            s6.s.l(arrayList, ((d) it.next()).g());
        }
        y7 = s6.v.y(arrayList);
        v7 = s6.v.v(y7);
        int size2 = v7.size();
        for (int i9 = 0; i9 < size2; i9++) {
            ((b) v7.get(i9)).g(this.f3698a);
        }
    }

    public final void B(boolean z7) {
        this.f3701d = z7;
    }

    public final void c(d dVar) {
        d7.k.e(dVar, "operation");
        if (dVar.k()) {
            d.b h8 = dVar.h();
            View l12 = dVar.i().l1();
            d7.k.d(l12, "operation.fragment.requireView()");
            h8.e(l12, this.f3698a);
            dVar.r(false);
        }
    }

    public abstract void d(List list, boolean z7);

    public void e(List list) {
        Set y7;
        List v7;
        List v8;
        d7.k.e(list, "operations");
        ArrayList arrayList = new ArrayList();
        Iterator it = list.iterator();
        while (it.hasNext()) {
            s6.s.l(arrayList, ((d) it.next()).g());
        }
        y7 = s6.v.y(arrayList);
        v7 = s6.v.v(y7);
        int size = v7.size();
        for (int i8 = 0; i8 < size; i8++) {
            ((b) v7.get(i8)).d(this.f3698a);
        }
        int size2 = list.size();
        for (int i9 = 0; i9 < size2; i9++) {
            c((d) list.get(i9));
        }
        v8 = s6.v.v(list);
        int size3 = v8.size();
        for (int i10 = 0; i10 < size3; i10++) {
            d dVar = (d) v8.get(i10);
            if (dVar.g().isEmpty()) {
                dVar.e();
            }
        }
    }

    public final void f() {
        if (i0.I0(3)) {
            Log.d("FragmentManager", "SpecialEffectsController: Completing Back ");
        }
        z(this.f3700c);
        e(this.f3700c);
    }

    public final void j(d.b bVar, o0 o0Var) {
        d7.k.e(bVar, "finalState");
        d7.k.e(o0Var, "fragmentStateManager");
        if (i0.I0(2)) {
            Log.v("FragmentManager", "SpecialEffectsController: Enqueuing add operation for fragment " + o0Var.k());
        }
        g(bVar, d.a.ADDING, o0Var);
    }

    public final void k(o0 o0Var) {
        d7.k.e(o0Var, "fragmentStateManager");
        if (i0.I0(2)) {
            Log.v("FragmentManager", "SpecialEffectsController: Enqueuing hide operation for fragment " + o0Var.k());
        }
        g(d.b.GONE, d.a.NONE, o0Var);
    }

    public final void l(o0 o0Var) {
        d7.k.e(o0Var, "fragmentStateManager");
        if (i0.I0(2)) {
            Log.v("FragmentManager", "SpecialEffectsController: Enqueuing remove operation for fragment " + o0Var.k());
        }
        g(d.b.REMOVED, d.a.REMOVING, o0Var);
    }

    public final void m(o0 o0Var) {
        d7.k.e(o0Var, "fragmentStateManager");
        if (i0.I0(2)) {
            Log.v("FragmentManager", "SpecialEffectsController: Enqueuing show operation for fragment " + o0Var.k());
        }
        g(d.b.VISIBLE, d.a.NONE, o0Var);
    }

    /* JADX WARN: Removed duplicated region for block: B:101:0x0101 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:72:0x018a A[Catch: all -> 0x01bc, TryCatch #0 {, blocks: (B:11:0x0017, B:13:0x0020, B:14:0x0031, B:16:0x0037, B:18:0x0043, B:19:0x005e, B:21:0x0069, B:80:0x01b8, B:22:0x006f, B:23:0x0080, B:25:0x0086, B:27:0x0092, B:28:0x00a8, B:30:0x00b9, B:31:0x00bf, B:35:0x00d2, B:37:0x00e5, B:38:0x00ec, B:39:0x0101, B:41:0x0107, B:43:0x011a, B:45:0x0124, B:57:0x014b, B:48:0x012f, B:49:0x0133, B:51:0x0139, B:60:0x0157, B:62:0x015b, B:63:0x0167, B:65:0x016d, B:66:0x017d, B:70:0x0186, B:72:0x018a, B:77:0x01a9, B:79:0x01b1, B:74:0x0193, B:76:0x019d), top: B:86:0x0017 }] */
    /* JADX WARN: Removed duplicated region for block: B:73:0x0191  */
    /* JADX WARN: Removed duplicated region for block: B:79:0x01b1 A[Catch: all -> 0x01bc, TryCatch #0 {, blocks: (B:11:0x0017, B:13:0x0020, B:14:0x0031, B:16:0x0037, B:18:0x0043, B:19:0x005e, B:21:0x0069, B:80:0x01b8, B:22:0x006f, B:23:0x0080, B:25:0x0086, B:27:0x0092, B:28:0x00a8, B:30:0x00b9, B:31:0x00bf, B:35:0x00d2, B:37:0x00e5, B:38:0x00ec, B:39:0x0101, B:41:0x0107, B:43:0x011a, B:45:0x0124, B:57:0x014b, B:48:0x012f, B:49:0x0133, B:51:0x0139, B:60:0x0157, B:62:0x015b, B:63:0x0167, B:65:0x016d, B:66:0x017d, B:70:0x0186, B:72:0x018a, B:77:0x01a9, B:79:0x01b1, B:74:0x0193, B:76:0x019d), top: B:86:0x0017 }] */
    /* JADX WARN: Removed duplicated region for block: B:99:0x0155 A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void n() {
        /*
            Method dump skipped, instructions count: 447
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.fragment.app.y0.n():void");
    }

    public final void q() {
        List<d> x7;
        List<d> x8;
        if (i0.I0(2)) {
            Log.v("FragmentManager", "SpecialEffectsController: Forcing all operations to complete");
        }
        boolean isAttachedToWindow = this.f3698a.isAttachedToWindow();
        synchronized (this.f3699b) {
            A();
            z(this.f3699b);
            x7 = s6.v.x(this.f3700c);
            for (d dVar : x7) {
                if (i0.I0(2)) {
                    Log.v("FragmentManager", "SpecialEffectsController: " + (isAttachedToWindow ? "" : "Container " + this.f3698a + " is not attached to window. ") + "Cancelling running operation " + dVar);
                }
                dVar.c(this.f3698a);
            }
            x8 = s6.v.x(this.f3699b);
            for (d dVar2 : x8) {
                if (i0.I0(2)) {
                    Log.v("FragmentManager", "SpecialEffectsController: " + (isAttachedToWindow ? "" : "Container " + this.f3698a + " is not attached to window. ") + "Cancelling pending operation " + dVar2);
                }
                dVar2.c(this.f3698a);
            }
            r6.r rVar = r6.r.f11372a;
        }
    }

    public final void r() {
        if (this.f3702e) {
            if (i0.I0(2)) {
                Log.v("FragmentManager", "SpecialEffectsController: Forcing postponed operations");
            }
            this.f3702e = false;
            n();
        }
    }

    public final d.a s(o0 o0Var) {
        d7.k.e(o0Var, "fragmentStateManager");
        p k8 = o0Var.k();
        d7.k.d(k8, "fragmentStateManager.fragment");
        d o8 = o(k8);
        d.a j8 = o8 != null ? o8.j() : null;
        d p8 = p(k8);
        d.a j9 = p8 != null ? p8.j() : null;
        int i8 = j8 == null ? -1 : e.f3730a[j8.ordinal()];
        return (i8 == -1 || i8 == 1) ? j9 : j8;
    }

    public final ViewGroup t() {
        return this.f3698a;
    }

    public final boolean w() {
        return !this.f3699b.isEmpty();
    }

    public final void x() {
        Object obj;
        boolean z7;
        synchronized (this.f3699b) {
            A();
            List list = this.f3699b;
            ListIterator listIterator = list.listIterator(list.size());
            while (true) {
                if (!listIterator.hasPrevious()) {
                    obj = null;
                    break;
                }
                obj = listIterator.previous();
                d dVar = (d) obj;
                d.b.a aVar = d.b.f3722e;
                View view = dVar.i().J;
                d7.k.d(view, "operation.fragment.mView");
                d.b a8 = aVar.a(view);
                d.b h8 = dVar.h();
                d.b bVar = d.b.VISIBLE;
                if (h8 != bVar || a8 == bVar) {
                    z7 = false;
                    continue;
                } else {
                    z7 = true;
                    continue;
                }
                if (z7) {
                    break;
                }
            }
            d dVar2 = (d) obj;
            p i8 = dVar2 != null ? dVar2.i() : null;
            this.f3702e = i8 != null ? i8.Y() : false;
            r6.r rVar = r6.r.f11372a;
        }
    }

    public final void y(androidx.activity.b bVar) {
        Set y7;
        List v7;
        d7.k.e(bVar, "backEvent");
        if (i0.I0(2)) {
            Log.v("FragmentManager", "SpecialEffectsController: Processing Progress " + bVar.a());
        }
        ArrayList arrayList = new ArrayList();
        for (d dVar : this.f3700c) {
            s6.s.l(arrayList, dVar.g());
        }
        y7 = s6.v.y(arrayList);
        v7 = s6.v.v(y7);
        int size = v7.size();
        for (int i8 = 0; i8 < size; i8++) {
            ((b) v7.get(i8)).e(bVar, this.f3698a);
        }
    }
}
