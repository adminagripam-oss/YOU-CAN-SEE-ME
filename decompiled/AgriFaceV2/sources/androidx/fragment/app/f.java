package androidx.fragment.app;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.content.Context;
import android.graphics.Rect;
import android.os.Build;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import androidx.fragment.app.f;
import androidx.fragment.app.v;
import androidx.fragment.app.y0;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Set;
/* loaded from: classes.dex */
public final class f extends y0 {

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static final class a extends y0.b {

        /* renamed from: d  reason: collision with root package name */
        private final b f3387d;

        /* renamed from: androidx.fragment.app.f$a$a  reason: collision with other inner class name */
        /* loaded from: classes.dex */
        public static final class animation.Animation$AnimationListenerC0038a implements Animation.AnimationListener {

            /* renamed from: a  reason: collision with root package name */
            final /* synthetic */ y0.d f3388a;

            /* renamed from: b  reason: collision with root package name */
            final /* synthetic */ ViewGroup f3389b;

            /* renamed from: c  reason: collision with root package name */
            final /* synthetic */ View f3390c;

            /* renamed from: d  reason: collision with root package name */
            final /* synthetic */ a f3391d;

            animation.Animation$AnimationListenerC0038a(y0.d dVar, ViewGroup viewGroup, View view, a aVar) {
                this.f3388a = dVar;
                this.f3389b = viewGroup;
                this.f3390c = view;
                this.f3391d = aVar;
            }

            /* JADX INFO: Access modifiers changed from: private */
            public static final void b(ViewGroup viewGroup, View view, a aVar) {
                d7.k.e(viewGroup, "$container");
                d7.k.e(aVar, "this$0");
                viewGroup.endViewTransition(view);
                aVar.h().a().f(aVar);
            }

            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationEnd(Animation animation) {
                d7.k.e(animation, "animation");
                final ViewGroup viewGroup = this.f3389b;
                final View view = this.f3390c;
                final a aVar = this.f3391d;
                viewGroup.post(new Runnable() { // from class: androidx.fragment.app.e
                    @Override // java.lang.Runnable
                    public final void run() {
                        f.a.animation.Animation$AnimationListenerC0038a.b(viewGroup, view, aVar);
                    }
                });
                if (i0.I0(2)) {
                    Log.v("FragmentManager", "Animation from operation " + this.f3388a + " has ended.");
                }
            }

            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationRepeat(Animation animation) {
                d7.k.e(animation, "animation");
            }

            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationStart(Animation animation) {
                d7.k.e(animation, "animation");
                if (i0.I0(2)) {
                    Log.v("FragmentManager", "Animation from operation " + this.f3388a + " has reached onAnimationStart.");
                }
            }
        }

        public a(b bVar) {
            d7.k.e(bVar, "animationInfo");
            this.f3387d = bVar;
        }

        @Override // androidx.fragment.app.y0.b
        public void c(ViewGroup viewGroup) {
            d7.k.e(viewGroup, "container");
            y0.d a8 = this.f3387d.a();
            View view = a8.i().J;
            view.clearAnimation();
            viewGroup.endViewTransition(view);
            this.f3387d.a().f(this);
            if (i0.I0(2)) {
                Log.v("FragmentManager", "Animation from operation " + a8 + " has been cancelled.");
            }
        }

        @Override // androidx.fragment.app.y0.b
        public void d(ViewGroup viewGroup) {
            d7.k.e(viewGroup, "container");
            if (this.f3387d.b()) {
                this.f3387d.a().f(this);
                return;
            }
            Context context = viewGroup.getContext();
            y0.d a8 = this.f3387d.a();
            View view = a8.i().J;
            b bVar = this.f3387d;
            d7.k.d(context, "context");
            v.a c8 = bVar.c(context);
            if (c8 == null) {
                throw new IllegalStateException("Required value was null.".toString());
            }
            Animation animation = c8.f3678a;
            if (animation == null) {
                throw new IllegalStateException("Required value was null.".toString());
            }
            if (a8.h() != y0.d.b.REMOVED) {
                view.startAnimation(animation);
                this.f3387d.a().f(this);
                return;
            }
            viewGroup.startViewTransition(view);
            v.b bVar2 = new v.b(animation, viewGroup, view);
            bVar2.setAnimationListener(new animation.Animation$AnimationListenerC0038a(a8, viewGroup, view, this));
            view.startAnimation(bVar2);
            if (i0.I0(2)) {
                Log.v("FragmentManager", "Animation from operation " + a8 + " has started.");
            }
        }

        public final b h() {
            return this.f3387d;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static final class b extends C0039f {

        /* renamed from: b  reason: collision with root package name */
        private final boolean f3392b;

        /* renamed from: c  reason: collision with root package name */
        private boolean f3393c;

        /* renamed from: d  reason: collision with root package name */
        private v.a f3394d;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public b(y0.d dVar, boolean z7) {
            super(dVar);
            d7.k.e(dVar, "operation");
            this.f3392b = z7;
        }

        public final v.a c(Context context) {
            d7.k.e(context, "context");
            if (this.f3393c) {
                return this.f3394d;
            }
            v.a b8 = v.b(context, a().i(), a().h() == y0.d.b.VISIBLE, this.f3392b);
            this.f3394d = b8;
            this.f3393c = true;
            return b8;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static final class c extends y0.b {

        /* renamed from: d  reason: collision with root package name */
        private final b f3395d;

        /* renamed from: e  reason: collision with root package name */
        private AnimatorSet f3396e;

        /* loaded from: classes.dex */
        public static final class a extends AnimatorListenerAdapter {

            /* renamed from: a  reason: collision with root package name */
            final /* synthetic */ ViewGroup f3397a;

            /* renamed from: b  reason: collision with root package name */
            final /* synthetic */ View f3398b;

            /* renamed from: c  reason: collision with root package name */
            final /* synthetic */ boolean f3399c;

            /* renamed from: d  reason: collision with root package name */
            final /* synthetic */ y0.d f3400d;

            /* renamed from: e  reason: collision with root package name */
            final /* synthetic */ c f3401e;

            a(ViewGroup viewGroup, View view, boolean z7, y0.d dVar, c cVar) {
                this.f3397a = viewGroup;
                this.f3398b = view;
                this.f3399c = z7;
                this.f3400d = dVar;
                this.f3401e = cVar;
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator) {
                d7.k.e(animator, "anim");
                this.f3397a.endViewTransition(this.f3398b);
                if (this.f3399c) {
                    y0.d.b h8 = this.f3400d.h();
                    View view = this.f3398b;
                    d7.k.d(view, "viewToAnimate");
                    h8.e(view, this.f3397a);
                }
                this.f3401e.h().a().f(this.f3401e);
                if (i0.I0(2)) {
                    Log.v("FragmentManager", "Animator from operation " + this.f3400d + " has ended.");
                }
            }
        }

        public c(b bVar) {
            d7.k.e(bVar, "animatorInfo");
            this.f3395d = bVar;
        }

        @Override // androidx.fragment.app.y0.b
        public boolean b() {
            return true;
        }

        @Override // androidx.fragment.app.y0.b
        public void c(ViewGroup viewGroup) {
            d7.k.e(viewGroup, "container");
            AnimatorSet animatorSet = this.f3396e;
            if (animatorSet == null) {
                this.f3395d.a().f(this);
                return;
            }
            y0.d a8 = this.f3395d.a();
            if (a8.n()) {
                e.f3403a.a(animatorSet);
            } else {
                animatorSet.end();
            }
            if (i0.I0(2)) {
                StringBuilder sb = new StringBuilder();
                sb.append("Animator from operation ");
                sb.append(a8);
                sb.append(" has been canceled");
                sb.append(a8.n() ? " with seeking." : ".");
                sb.append(' ');
                Log.v("FragmentManager", sb.toString());
            }
        }

        @Override // androidx.fragment.app.y0.b
        public void d(ViewGroup viewGroup) {
            d7.k.e(viewGroup, "container");
            y0.d a8 = this.f3395d.a();
            AnimatorSet animatorSet = this.f3396e;
            if (animatorSet == null) {
                this.f3395d.a().f(this);
                return;
            }
            animatorSet.start();
            if (i0.I0(2)) {
                Log.v("FragmentManager", "Animator from operation " + a8 + " has started.");
            }
        }

        @Override // androidx.fragment.app.y0.b
        public void e(androidx.activity.b bVar, ViewGroup viewGroup) {
            d7.k.e(bVar, "backEvent");
            d7.k.e(viewGroup, "container");
            y0.d a8 = this.f3395d.a();
            AnimatorSet animatorSet = this.f3396e;
            if (animatorSet == null) {
                this.f3395d.a().f(this);
            } else if (Build.VERSION.SDK_INT < 34 || !a8.i().f3571n) {
            } else {
                if (i0.I0(2)) {
                    Log.v("FragmentManager", "Adding BackProgressCallbacks for Animators to operation " + a8);
                }
                long a9 = d.f3402a.a(animatorSet);
                long a10 = bVar.a() * ((float) a9);
                if (a10 == 0) {
                    a10 = 1;
                }
                if (a10 == a9) {
                    a10 = a9 - 1;
                }
                if (i0.I0(2)) {
                    Log.v("FragmentManager", "Setting currentPlayTime to " + a10 + " for Animator " + animatorSet + " on operation " + a8);
                }
                e.f3403a.b(animatorSet, a10);
            }
        }

        @Override // androidx.fragment.app.y0.b
        public void f(ViewGroup viewGroup) {
            d7.k.e(viewGroup, "container");
            if (this.f3395d.b()) {
                return;
            }
            Context context = viewGroup.getContext();
            b bVar = this.f3395d;
            d7.k.d(context, "context");
            v.a c8 = bVar.c(context);
            this.f3396e = c8 != null ? c8.f3679b : null;
            y0.d a8 = this.f3395d.a();
            p i8 = a8.i();
            boolean z7 = a8.h() == y0.d.b.GONE;
            View view = i8.J;
            viewGroup.startViewTransition(view);
            AnimatorSet animatorSet = this.f3396e;
            if (animatorSet != null) {
                animatorSet.addListener(new a(viewGroup, view, z7, a8, this));
            }
            AnimatorSet animatorSet2 = this.f3396e;
            if (animatorSet2 != null) {
                animatorSet2.setTarget(view);
            }
        }

        public final b h() {
            return this.f3395d;
        }
    }

    /* loaded from: classes.dex */
    public static final class d {

        /* renamed from: a  reason: collision with root package name */
        public static final d f3402a = new d();

        private d() {
        }

        public final long a(AnimatorSet animatorSet) {
            d7.k.e(animatorSet, "animatorSet");
            return animatorSet.getTotalDuration();
        }
    }

    /* loaded from: classes.dex */
    public static final class e {

        /* renamed from: a  reason: collision with root package name */
        public static final e f3403a = new e();

        private e() {
        }

        public final void a(AnimatorSet animatorSet) {
            d7.k.e(animatorSet, "animatorSet");
            animatorSet.reverse();
        }

        public final void b(AnimatorSet animatorSet, long j8) {
            d7.k.e(animatorSet, "animatorSet");
            animatorSet.setCurrentPlayTime(j8);
        }
    }

    /* renamed from: androidx.fragment.app.f$f  reason: collision with other inner class name */
    /* loaded from: classes.dex */
    public static class C0039f {

        /* renamed from: a  reason: collision with root package name */
        private final y0.d f3404a;

        public C0039f(y0.d dVar) {
            d7.k.e(dVar, "operation");
            this.f3404a = dVar;
        }

        public final y0.d a() {
            return this.f3404a;
        }

        public final boolean b() {
            y0.d.b bVar;
            View view = this.f3404a.i().J;
            y0.d.b a8 = view != null ? y0.d.b.f3722e.a(view) : null;
            y0.d.b h8 = this.f3404a.h();
            return a8 == h8 || !(a8 == (bVar = y0.d.b.VISIBLE) || h8 == bVar);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static final class g extends y0.b {

        /* renamed from: d  reason: collision with root package name */
        private final List f3405d;

        /* renamed from: e  reason: collision with root package name */
        private final y0.d f3406e;

        /* renamed from: f  reason: collision with root package name */
        private final y0.d f3407f;

        /* renamed from: g  reason: collision with root package name */
        private final t0 f3408g;

        /* renamed from: h  reason: collision with root package name */
        private final Object f3409h;

        /* renamed from: i  reason: collision with root package name */
        private final ArrayList f3410i;

        /* renamed from: j  reason: collision with root package name */
        private final ArrayList f3411j;

        /* renamed from: k  reason: collision with root package name */
        private final b1.a f3412k;

        /* renamed from: l  reason: collision with root package name */
        private final ArrayList f3413l;

        /* renamed from: m  reason: collision with root package name */
        private final ArrayList f3414m;

        /* renamed from: n  reason: collision with root package name */
        private final b1.a f3415n;

        /* renamed from: o  reason: collision with root package name */
        private final b1.a f3416o;

        /* renamed from: p  reason: collision with root package name */
        private final boolean f3417p;

        /* renamed from: q  reason: collision with root package name */
        private final androidx.core.os.d f3418q;

        /* renamed from: r  reason: collision with root package name */
        private Object f3419r;

        /* loaded from: classes.dex */
        static final class a extends d7.l implements c7.a {

            /* renamed from: g  reason: collision with root package name */
            final /* synthetic */ ViewGroup f3421g;

            /* renamed from: h  reason: collision with root package name */
            final /* synthetic */ Object f3422h;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            a(ViewGroup viewGroup, Object obj) {
                super(0);
                this.f3421g = viewGroup;
                this.f3422h = obj;
            }

            @Override // c7.a
            public /* bridge */ /* synthetic */ Object a() {
                b();
                return r6.r.f11372a;
            }

            public final void b() {
                g.this.v().e(this.f3421g, this.f3422h);
            }
        }

        /* loaded from: classes.dex */
        static final class b extends d7.l implements c7.a {

            /* renamed from: g  reason: collision with root package name */
            final /* synthetic */ ViewGroup f3424g;

            /* renamed from: h  reason: collision with root package name */
            final /* synthetic */ Object f3425h;

            /* renamed from: i  reason: collision with root package name */
            final /* synthetic */ d7.r f3426i;

            /* JADX INFO: Access modifiers changed from: package-private */
            /* loaded from: classes.dex */
            public static final class a extends d7.l implements c7.a {

                /* renamed from: f  reason: collision with root package name */
                final /* synthetic */ g f3427f;

                /* renamed from: g  reason: collision with root package name */
                final /* synthetic */ ViewGroup f3428g;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                a(g gVar, ViewGroup viewGroup) {
                    super(0);
                    this.f3427f = gVar;
                    this.f3428g = viewGroup;
                }

                /* JADX INFO: Access modifiers changed from: private */
                public static final void h(g gVar, ViewGroup viewGroup) {
                    d7.k.e(gVar, "this$0");
                    d7.k.e(viewGroup, "$container");
                    for (h hVar : gVar.w()) {
                        y0.d a8 = hVar.a();
                        View P = a8.i().P();
                        if (P != null) {
                            a8.h().e(P, viewGroup);
                        }
                    }
                }

                @Override // c7.a
                public /* bridge */ /* synthetic */ Object a() {
                    e();
                    return r6.r.f11372a;
                }

                public final void e() {
                    if (i0.I0(2)) {
                        Log.v("FragmentManager", "Animating to start");
                    }
                    t0 v7 = this.f3427f.v();
                    Object s8 = this.f3427f.s();
                    d7.k.b(s8);
                    final g gVar = this.f3427f;
                    final ViewGroup viewGroup = this.f3428g;
                    v7.d(s8, new Runnable() { // from class: androidx.fragment.app.m
                        @Override // java.lang.Runnable
                        public final void run() {
                            f.g.b.a.h(f.g.this, viewGroup);
                        }
                    });
                }
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            b(ViewGroup viewGroup, Object obj, d7.r rVar) {
                super(0);
                this.f3424g = viewGroup;
                this.f3425h = obj;
                this.f3426i = rVar;
            }

            @Override // c7.a
            public /* bridge */ /* synthetic */ Object a() {
                b();
                return r6.r.f11372a;
            }

            public final void b() {
                g gVar = g.this;
                gVar.C(gVar.v().j(this.f3424g, this.f3425h));
                boolean z7 = g.this.s() != null;
                Object obj = this.f3425h;
                ViewGroup viewGroup = this.f3424g;
                if (!z7) {
                    throw new IllegalStateException(("Unable to start transition " + obj + " for container " + viewGroup + '.').toString());
                }
                this.f3426i.f6484e = new a(g.this, viewGroup);
                if (i0.I0(2)) {
                    Log.v("FragmentManager", "Started executing operations from " + g.this.t() + " to " + g.this.u());
                }
            }
        }

        public g(List list, y0.d dVar, y0.d dVar2, t0 t0Var, Object obj, ArrayList arrayList, ArrayList arrayList2, b1.a aVar, ArrayList arrayList3, ArrayList arrayList4, b1.a aVar2, b1.a aVar3, boolean z7) {
            d7.k.e(list, "transitionInfos");
            d7.k.e(t0Var, "transitionImpl");
            d7.k.e(arrayList, "sharedElementFirstOutViews");
            d7.k.e(arrayList2, "sharedElementLastInViews");
            d7.k.e(aVar, "sharedElementNameMapping");
            d7.k.e(arrayList3, "enteringNames");
            d7.k.e(arrayList4, "exitingNames");
            d7.k.e(aVar2, "firstOutViews");
            d7.k.e(aVar3, "lastInViews");
            this.f3405d = list;
            this.f3406e = dVar;
            this.f3407f = dVar2;
            this.f3408g = t0Var;
            this.f3409h = obj;
            this.f3410i = arrayList;
            this.f3411j = arrayList2;
            this.f3412k = aVar;
            this.f3413l = arrayList3;
            this.f3414m = arrayList4;
            this.f3415n = aVar2;
            this.f3416o = aVar3;
            this.f3417p = z7;
            this.f3418q = new androidx.core.os.d();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void A(y0.d dVar, g gVar) {
            d7.k.e(dVar, "$operation");
            d7.k.e(gVar, "this$0");
            if (i0.I0(2)) {
                Log.v("FragmentManager", "Transition for operation " + dVar + " has completed");
            }
            dVar.f(gVar);
        }

        private final void B(ArrayList arrayList, ViewGroup viewGroup, c7.a aVar) {
            r0.d(arrayList, 4);
            ArrayList q8 = this.f3408g.q(this.f3411j);
            if (i0.I0(2)) {
                Log.v("FragmentManager", ">>>>> Beginning transition <<<<<");
                Log.v("FragmentManager", ">>>>> SharedElementFirstOutViews <<<<<");
                Iterator it = this.f3410i.iterator();
                while (it.hasNext()) {
                    Object next = it.next();
                    d7.k.d(next, "sharedElementFirstOutViews");
                    View view = (View) next;
                    Log.v("FragmentManager", "View: " + view + " Name: " + androidx.core.view.p0.u(view));
                }
                Log.v("FragmentManager", ">>>>> SharedElementLastInViews <<<<<");
                Iterator it2 = this.f3411j.iterator();
                while (it2.hasNext()) {
                    Object next2 = it2.next();
                    d7.k.d(next2, "sharedElementLastInViews");
                    View view2 = (View) next2;
                    Log.v("FragmentManager", "View: " + view2 + " Name: " + androidx.core.view.p0.u(view2));
                }
            }
            aVar.a();
            this.f3408g.y(viewGroup, this.f3410i, this.f3411j, q8, this.f3412k);
            r0.d(arrayList, 0);
            this.f3408g.A(this.f3409h, this.f3410i, this.f3411j);
        }

        private final void n(ArrayList arrayList, View view) {
            if (view instanceof ViewGroup) {
                ViewGroup viewGroup = (ViewGroup) view;
                if (!androidx.core.view.u0.a(viewGroup)) {
                    int childCount = viewGroup.getChildCount();
                    for (int i8 = 0; i8 < childCount; i8++) {
                        View childAt = viewGroup.getChildAt(i8);
                        if (childAt.getVisibility() == 0) {
                            d7.k.d(childAt, "child");
                            n(arrayList, childAt);
                        }
                    }
                    return;
                } else if (arrayList.contains(view)) {
                    return;
                }
            } else if (arrayList.contains(view)) {
                return;
            }
            arrayList.add(view);
        }

        private final r6.j o(ViewGroup viewGroup, y0.d dVar, final y0.d dVar2) {
            Object next;
            Object next2;
            Set y7;
            final y0.d dVar3 = dVar;
            View view = new View(viewGroup.getContext());
            final Rect rect = new Rect();
            boolean z7 = false;
            View view2 = null;
            for (h hVar : this.f3405d) {
                if (hVar.g() && dVar2 != null && dVar3 != null && (!this.f3412k.isEmpty()) && this.f3409h != null) {
                    r0.a(dVar.i(), dVar2.i(), this.f3417p, this.f3415n, true);
                    androidx.core.view.k0.a(viewGroup, new Runnable() { // from class: androidx.fragment.app.j
                        @Override // java.lang.Runnable
                        public final void run() {
                            f.g.p(y0.d.this, dVar2, this);
                        }
                    });
                    this.f3410i.addAll(this.f3415n.values());
                    if (!this.f3414m.isEmpty()) {
                        Object obj = this.f3414m.get(0);
                        d7.k.d(obj, "exitingNames[0]");
                        view2 = (View) this.f3415n.get((String) obj);
                        this.f3408g.v(this.f3409h, view2);
                    }
                    this.f3411j.addAll(this.f3416o.values());
                    if (!this.f3413l.isEmpty()) {
                        Object obj2 = this.f3413l.get(0);
                        d7.k.d(obj2, "enteringNames[0]");
                        final View view3 = (View) this.f3416o.get((String) obj2);
                        if (view3 != null) {
                            final t0 t0Var = this.f3408g;
                            androidx.core.view.k0.a(viewGroup, new Runnable() { // from class: androidx.fragment.app.k
                                @Override // java.lang.Runnable
                                public final void run() {
                                    f.g.q(t0.this, view3, rect);
                                }
                            });
                            z7 = true;
                        }
                    }
                    this.f3408g.z(this.f3409h, view, this.f3410i);
                    t0 t0Var2 = this.f3408g;
                    Object obj3 = this.f3409h;
                    t0Var2.s(obj3, null, null, null, null, obj3, this.f3411j);
                }
            }
            ArrayList arrayList = new ArrayList();
            Iterator it = this.f3405d.iterator();
            Object obj4 = null;
            Object obj5 = null;
            while (it.hasNext()) {
                h hVar2 = (h) it.next();
                y0.d a8 = hVar2.a();
                Iterator it2 = it;
                Object h8 = this.f3408g.h(hVar2.f());
                if (h8 != null) {
                    final ArrayList arrayList2 = new ArrayList();
                    Object obj6 = obj5;
                    View view4 = a8.i().J;
                    Object obj7 = obj4;
                    d7.k.d(view4, "operation.fragment.mView");
                    n(arrayList2, view4);
                    if (this.f3409h != null && (a8 == dVar2 || a8 == dVar3)) {
                        y7 = s6.v.y(a8 == dVar2 ? this.f3410i : this.f3411j);
                        arrayList2.removeAll(y7);
                    }
                    if (arrayList2.isEmpty()) {
                        this.f3408g.a(h8, view);
                    } else {
                        this.f3408g.b(h8, arrayList2);
                        this.f3408g.s(h8, h8, arrayList2, null, null, null, null);
                        if (a8.h() == y0.d.b.GONE) {
                            a8.r(false);
                            ArrayList arrayList3 = new ArrayList(arrayList2);
                            arrayList3.remove(a8.i().J);
                            this.f3408g.r(h8, a8.i().J, arrayList3);
                            androidx.core.view.k0.a(viewGroup, new Runnable() { // from class: androidx.fragment.app.l
                                @Override // java.lang.Runnable
                                public final void run() {
                                    f.g.r(arrayList2);
                                }
                            });
                        }
                    }
                    if (a8.h() == y0.d.b.VISIBLE) {
                        arrayList.addAll(arrayList2);
                        if (z7) {
                            this.f3408g.u(h8, rect);
                        }
                        if (i0.I0(2)) {
                            Log.v("FragmentManager", "Entering Transition: " + h8);
                            Log.v("FragmentManager", ">>>>> EnteringViews <<<<<");
                            Iterator it3 = arrayList2.iterator();
                            while (it3.hasNext()) {
                                d7.k.d(it3.next(), "transitioningViews");
                                Log.v("FragmentManager", "View: " + ((View) next2));
                            }
                        }
                    } else {
                        this.f3408g.v(h8, view2);
                        if (i0.I0(2)) {
                            Log.v("FragmentManager", "Exiting Transition: " + h8);
                            Log.v("FragmentManager", ">>>>> ExitingViews <<<<<");
                            Iterator it4 = arrayList2.iterator();
                            while (it4.hasNext()) {
                                d7.k.d(it4.next(), "transitioningViews");
                                Log.v("FragmentManager", "View: " + ((View) next));
                            }
                        }
                    }
                    if (hVar2.h()) {
                        obj4 = this.f3408g.p(obj7, h8, null);
                        dVar3 = dVar;
                        it = it2;
                        obj5 = obj6;
                    } else {
                        obj5 = this.f3408g.p(obj6, h8, null);
                        dVar3 = dVar;
                        obj4 = obj7;
                        it = it2;
                    }
                } else {
                    dVar3 = dVar;
                    it = it2;
                    obj4 = obj4;
                }
            }
            Object o8 = this.f3408g.o(obj4, obj5, this.f3409h);
            if (i0.I0(2)) {
                Log.v("FragmentManager", "Final merged transition: " + o8);
            }
            return new r6.j(arrayList, o8);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void p(y0.d dVar, y0.d dVar2, g gVar) {
            d7.k.e(gVar, "this$0");
            r0.a(dVar.i(), dVar2.i(), gVar.f3417p, gVar.f3416o, false);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void q(t0 t0Var, View view, Rect rect) {
            d7.k.e(t0Var, "$impl");
            d7.k.e(rect, "$lastInEpicenterRect");
            t0Var.k(view, rect);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void r(ArrayList arrayList) {
            d7.k.e(arrayList, "$transitioningViews");
            r0.d(arrayList, 4);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void y(y0.d dVar, g gVar) {
            d7.k.e(dVar, "$operation");
            d7.k.e(gVar, "this$0");
            if (i0.I0(2)) {
                Log.v("FragmentManager", "Transition for operation " + dVar + " has completed");
            }
            dVar.f(gVar);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void z(d7.r rVar) {
            d7.k.e(rVar, "$seekCancelLambda");
            c7.a aVar = (c7.a) rVar.f6484e;
            if (aVar != null) {
                aVar.a();
            }
        }

        public final void C(Object obj) {
            this.f3419r = obj;
        }

        @Override // androidx.fragment.app.y0.b
        public boolean b() {
            boolean z7;
            boolean z8;
            if (this.f3408g.m()) {
                List<h> list = this.f3405d;
                if (!(list instanceof Collection) || !list.isEmpty()) {
                    for (h hVar : list) {
                        if (Build.VERSION.SDK_INT < 34 || hVar.f() == null || !this.f3408g.n(hVar.f())) {
                            z7 = false;
                            continue;
                        } else {
                            z7 = true;
                            continue;
                        }
                        if (!z7) {
                            z8 = false;
                            break;
                        }
                    }
                }
                z8 = true;
                if (z8) {
                    Object obj = this.f3409h;
                    return obj == null || this.f3408g.n(obj);
                }
                return false;
            }
            return false;
        }

        @Override // androidx.fragment.app.y0.b
        public void c(ViewGroup viewGroup) {
            d7.k.e(viewGroup, "container");
            this.f3418q.a();
        }

        @Override // androidx.fragment.app.y0.b
        public void d(ViewGroup viewGroup) {
            int k8;
            StringBuilder sb;
            String str;
            d7.k.e(viewGroup, "container");
            if (!viewGroup.isLaidOut()) {
                for (h hVar : this.f3405d) {
                    y0.d a8 = hVar.a();
                    if (i0.I0(2)) {
                        Log.v("FragmentManager", "SpecialEffectsController: Container " + viewGroup + " has not been laid out. Completing operation " + a8);
                    }
                    hVar.a().f(this);
                }
                return;
            }
            Object obj = this.f3419r;
            if (obj != null) {
                t0 t0Var = this.f3408g;
                d7.k.b(obj);
                t0Var.c(obj);
                if (!i0.I0(2)) {
                    return;
                }
                sb = new StringBuilder();
                str = "Ending execution of operations from ";
            } else {
                r6.j o8 = o(viewGroup, this.f3407f, this.f3406e);
                ArrayList arrayList = (ArrayList) o8.a();
                Object b8 = o8.b();
                List<h> list = this.f3405d;
                k8 = s6.o.k(list, 10);
                ArrayList<y0.d> arrayList2 = new ArrayList(k8);
                for (h hVar2 : list) {
                    arrayList2.add(hVar2.a());
                }
                for (final y0.d dVar : arrayList2) {
                    this.f3408g.w(dVar.i(), b8, this.f3418q, new Runnable() { // from class: androidx.fragment.app.g
                        @Override // java.lang.Runnable
                        public final void run() {
                            f.g.y(y0.d.this, this);
                        }
                    });
                }
                B(arrayList, viewGroup, new a(viewGroup, b8));
                if (!i0.I0(2)) {
                    return;
                }
                sb = new StringBuilder();
                str = "Completed executing operations from ";
            }
            sb.append(str);
            sb.append(this.f3406e);
            sb.append(" to ");
            sb.append(this.f3407f);
            Log.v("FragmentManager", sb.toString());
        }

        @Override // androidx.fragment.app.y0.b
        public void e(androidx.activity.b bVar, ViewGroup viewGroup) {
            d7.k.e(bVar, "backEvent");
            d7.k.e(viewGroup, "container");
            Object obj = this.f3419r;
            if (obj != null) {
                this.f3408g.t(obj, bVar.a());
            }
        }

        @Override // androidx.fragment.app.y0.b
        public void f(ViewGroup viewGroup) {
            int k8;
            d7.k.e(viewGroup, "container");
            if (!viewGroup.isLaidOut()) {
                for (h hVar : this.f3405d) {
                    y0.d a8 = hVar.a();
                    if (i0.I0(2)) {
                        Log.v("FragmentManager", "SpecialEffectsController: Container " + viewGroup + " has not been laid out. Skipping onStart for operation " + a8);
                    }
                }
                return;
            }
            if (x() && this.f3409h != null && !b()) {
                Log.i("FragmentManager", "Ignoring shared elements transition " + this.f3409h + " between " + this.f3406e + " and " + this.f3407f + " as neither fragment has set a Transition. In order to run a SharedElementTransition, you must also set either an enter or exit transition on a fragment involved in the transaction. The sharedElementTransition will run after the back gesture has been committed.");
            }
            if (b() && x()) {
                final d7.r rVar = new d7.r();
                r6.j o8 = o(viewGroup, this.f3407f, this.f3406e);
                ArrayList arrayList = (ArrayList) o8.a();
                Object b8 = o8.b();
                List<h> list = this.f3405d;
                k8 = s6.o.k(list, 10);
                ArrayList<y0.d> arrayList2 = new ArrayList(k8);
                for (h hVar2 : list) {
                    arrayList2.add(hVar2.a());
                }
                for (final y0.d dVar : arrayList2) {
                    this.f3408g.x(dVar.i(), b8, this.f3418q, new Runnable() { // from class: androidx.fragment.app.h
                        @Override // java.lang.Runnable
                        public final void run() {
                            f.g.z(d7.r.this);
                        }
                    }, new Runnable() { // from class: androidx.fragment.app.i
                        @Override // java.lang.Runnable
                        public final void run() {
                            f.g.A(y0.d.this, this);
                        }
                    });
                }
                B(arrayList, viewGroup, new b(viewGroup, b8, rVar));
            }
        }

        public final Object s() {
            return this.f3419r;
        }

        public final y0.d t() {
            return this.f3406e;
        }

        public final y0.d u() {
            return this.f3407f;
        }

        public final t0 v() {
            return this.f3408g;
        }

        public final List w() {
            return this.f3405d;
        }

        public final boolean x() {
            List<h> list = this.f3405d;
            if ((list instanceof Collection) && list.isEmpty()) {
                return true;
            }
            for (h hVar : list) {
                if (!hVar.a().i().f3571n) {
                    return false;
                }
            }
            return true;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static final class h extends C0039f {

        /* renamed from: b  reason: collision with root package name */
        private final Object f3429b;

        /* renamed from: c  reason: collision with root package name */
        private final boolean f3430c;

        /* renamed from: d  reason: collision with root package name */
        private final Object f3431d;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public h(y0.d dVar, boolean z7, boolean z8) {
            super(dVar);
            Object J;
            boolean z9;
            Object obj;
            d7.k.e(dVar, "operation");
            y0.d.b h8 = dVar.h();
            y0.d.b bVar = y0.d.b.VISIBLE;
            if (h8 == bVar) {
                p i8 = dVar.i();
                J = z7 ? i8.H() : i8.r();
            } else {
                p i9 = dVar.i();
                J = z7 ? i9.J() : i9.u();
            }
            this.f3429b = J;
            if (dVar.h() == bVar) {
                p i10 = dVar.i();
                z9 = z7 ? i10.l() : i10.k();
            } else {
                z9 = true;
            }
            this.f3430c = z9;
            if (z8) {
                p i11 = dVar.i();
                obj = z7 ? i11.L() : i11.K();
            } else {
                obj = null;
            }
            this.f3431d = obj;
        }

        private final t0 d(Object obj) {
            if (obj == null) {
                return null;
            }
            t0 t0Var = r0.f3646b;
            if (t0Var == null || !t0Var.g(obj)) {
                t0 t0Var2 = r0.f3647c;
                if (t0Var2 == null || !t0Var2.g(obj)) {
                    throw new IllegalArgumentException("Transition " + obj + " for fragment " + a().i() + " is not a valid framework Transition or AndroidX Transition");
                }
                return t0Var2;
            }
            return t0Var;
        }

        public final t0 c() {
            t0 d8 = d(this.f3429b);
            t0 d9 = d(this.f3431d);
            if (d8 == null || d9 == null || d8 == d9) {
                return d8 == null ? d9 : d8;
            }
            throw new IllegalArgumentException(("Mixing framework transitions and AndroidX transitions is not allowed. Fragment " + a().i() + " returned Transition " + this.f3429b + " which uses a different Transition  type than its shared element transition " + this.f3431d).toString());
        }

        public final Object e() {
            return this.f3431d;
        }

        public final Object f() {
            return this.f3429b;
        }

        public final boolean g() {
            return this.f3431d != null;
        }

        public final boolean h() {
            return this.f3430c;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static final class i extends d7.l implements c7.l {

        /* renamed from: f  reason: collision with root package name */
        final /* synthetic */ Collection f3432f;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        i(Collection collection) {
            super(1);
            this.f3432f = collection;
        }

        @Override // c7.l
        /* renamed from: b */
        public final Boolean g(Map.Entry entry) {
            boolean o8;
            d7.k.e(entry, "entry");
            o8 = s6.v.o(this.f3432f, androidx.core.view.p0.u((View) entry.getValue()));
            return Boolean.valueOf(o8);
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public f(ViewGroup viewGroup) {
        super(viewGroup);
        d7.k.e(viewGroup, "container");
    }

    private final void D(List list) {
        StringBuilder sb;
        String str;
        ArrayList<b> arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        Iterator it = list.iterator();
        while (it.hasNext()) {
            s6.s.l(arrayList2, ((b) it.next()).a().g());
        }
        boolean z7 = !arrayList2.isEmpty();
        Iterator it2 = list.iterator();
        boolean z8 = false;
        while (it2.hasNext()) {
            b bVar = (b) it2.next();
            Context context = t().getContext();
            y0.d a8 = bVar.a();
            d7.k.d(context, "context");
            v.a c8 = bVar.c(context);
            if (c8 != null) {
                if (c8.f3679b == null) {
                    arrayList.add(bVar);
                } else {
                    p i8 = a8.i();
                    if (!(!a8.g().isEmpty())) {
                        if (a8.h() == y0.d.b.GONE) {
                            a8.r(false);
                        }
                        a8.b(new c(bVar));
                        z8 = true;
                    } else if (i0.I0(2)) {
                        Log.v("FragmentManager", "Ignoring Animator set on " + i8 + " as this Fragment was involved in a Transition.");
                    }
                }
            }
        }
        for (b bVar2 : arrayList) {
            y0.d a9 = bVar2.a();
            p i9 = a9.i();
            if (z7) {
                if (i0.I0(2)) {
                    sb = new StringBuilder();
                    sb.append("Ignoring Animation set on ");
                    sb.append(i9);
                    str = " as Animations cannot run alongside Transitions.";
                    sb.append(str);
                    Log.v("FragmentManager", sb.toString());
                }
            } else if (!z8) {
                a9.b(new a(bVar2));
            } else if (i0.I0(2)) {
                sb = new StringBuilder();
                sb.append("Ignoring Animation set on ");
                sb.append(i9);
                str = " as Animations cannot run alongside Animators.";
                sb.append(str);
                Log.v("FragmentManager", sb.toString());
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void E(f fVar, y0.d dVar) {
        d7.k.e(fVar, "this$0");
        d7.k.e(dVar, "$operation");
        fVar.c(dVar);
    }

    private final void F(List list, boolean z7, y0.d dVar, y0.d dVar2) {
        Object obj;
        boolean z8;
        boolean z9;
        t0 t0Var;
        Iterator it;
        ArrayList M;
        ArrayList N;
        Object obj2;
        ArrayList arrayList = new ArrayList();
        for (Object obj3 : list) {
            if (!((h) obj3).b()) {
                arrayList.add(obj3);
            }
        }
        ArrayList<h> arrayList2 = new ArrayList();
        for (Object obj4 : arrayList) {
            if (((h) obj4).c() != null) {
                arrayList2.add(obj4);
            }
        }
        t0 t0Var2 = null;
        for (h hVar : arrayList2) {
            t0 c8 = hVar.c();
            if (!(t0Var2 == null || c8 == t0Var2)) {
                throw new IllegalArgumentException(("Mixing framework transitions and AndroidX transitions is not allowed. Fragment " + hVar.a().i() + " returned Transition " + hVar.f() + " which uses a different Transition type than other Fragments.").toString());
            }
            t0Var2 = c8;
        }
        if (t0Var2 == null) {
            return;
        }
        ArrayList arrayList3 = new ArrayList();
        ArrayList arrayList4 = new ArrayList();
        b1.a aVar = new b1.a();
        ArrayList arrayList5 = new ArrayList();
        ArrayList arrayList6 = new ArrayList();
        b1.a aVar2 = new b1.a();
        b1.a aVar3 = new b1.a();
        Iterator it2 = arrayList2.iterator();
        ArrayList arrayList7 = arrayList5;
        ArrayList arrayList8 = arrayList6;
        loop3: while (true) {
            obj = null;
            while (it2.hasNext()) {
                h hVar2 = (h) it2.next();
                if (!hVar2.g() || dVar == null || dVar2 == null) {
                    t0Var = t0Var2;
                    it = it2;
                } else {
                    obj = t0Var2.B(t0Var2.h(hVar2.e()));
                    M = dVar2.i().M();
                    d7.k.d(M, "lastIn.fragment.sharedElementSourceNames");
                    ArrayList M2 = dVar.i().M();
                    d7.k.d(M2, "firstOut.fragment.sharedElementSourceNames");
                    ArrayList N2 = dVar.i().N();
                    d7.k.d(N2, "firstOut.fragment.sharedElementTargetNames");
                    int size = N2.size();
                    it = it2;
                    int i8 = 0;
                    while (i8 < size) {
                        int i9 = size;
                        int indexOf = M.indexOf(N2.get(i8));
                        ArrayList arrayList9 = N2;
                        if (indexOf != -1) {
                            M.set(indexOf, M2.get(i8));
                        }
                        i8++;
                        size = i9;
                        N2 = arrayList9;
                    }
                    N = dVar2.i().N();
                    d7.k.d(N, "lastIn.fragment.sharedElementTargetNames");
                    if (z7) {
                        obj2 = null;
                        dVar.i().s();
                        dVar2.i().v();
                    } else {
                        dVar.i().v();
                        dVar2.i().s();
                        obj2 = null;
                    }
                    r6.j a8 = r6.o.a(obj2, obj2);
                    androidx.appcompat.app.w.a(a8.a());
                    androidx.appcompat.app.w.a(a8.b());
                    int size2 = M.size();
                    int i10 = 0;
                    while (i10 < size2) {
                        Object obj5 = M.get(i10);
                        int i11 = size2;
                        d7.k.d(obj5, "exitingNames[i]");
                        Object obj6 = N.get(i10);
                        d7.k.d(obj6, "enteringNames[i]");
                        aVar.put((String) obj5, (String) obj6);
                        i10++;
                        size2 = i11;
                        t0Var2 = t0Var2;
                    }
                    t0Var = t0Var2;
                    if (i0.I0(2)) {
                        Log.v("FragmentManager", ">>> entering view names <<<");
                        Iterator it3 = N.iterator();
                        while (it3.hasNext()) {
                            Iterator it4 = it3;
                            Log.v("FragmentManager", "Name: " + ((String) it3.next()));
                            it3 = it4;
                        }
                        Log.v("FragmentManager", ">>> exiting view names <<<");
                        Iterator it5 = M.iterator();
                        while (it5.hasNext()) {
                            Iterator it6 = it5;
                            Log.v("FragmentManager", "Name: " + ((String) it5.next()));
                            it5 = it6;
                        }
                    }
                    View view = dVar.i().J;
                    d7.k.d(view, "firstOut.fragment.mView");
                    G(aVar2, view);
                    aVar2.n(M);
                    aVar.n(aVar2.keySet());
                    View view2 = dVar2.i().J;
                    d7.k.d(view2, "lastIn.fragment.mView");
                    G(aVar3, view2);
                    aVar3.n(N);
                    aVar3.n(aVar.values());
                    r0.c(aVar, aVar3);
                    Collection keySet = aVar.keySet();
                    d7.k.d(keySet, "sharedElementNameMapping.keys");
                    H(aVar2, keySet);
                    Collection values = aVar.values();
                    d7.k.d(values, "sharedElementNameMapping.values");
                    H(aVar3, values);
                    if (aVar.isEmpty()) {
                        break;
                    }
                    arrayList8 = M;
                    arrayList7 = N;
                }
                it2 = it;
                t0Var2 = t0Var;
            }
            Log.i("FragmentManager", "Ignoring shared elements transition " + obj + " between " + dVar + " and " + dVar2 + " as there are no matching elements in both the entering and exiting fragment. In order to run a SharedElementTransition, both fragments involved must have the element.");
            arrayList3.clear();
            arrayList4.clear();
            arrayList8 = M;
            arrayList7 = N;
            it2 = it;
            t0Var2 = t0Var;
        }
        t0 t0Var3 = t0Var2;
        if (obj == null) {
            if (!arrayList2.isEmpty()) {
                for (h hVar3 : arrayList2) {
                    if (hVar3.f() == null) {
                        z8 = true;
                        continue;
                    } else {
                        z8 = false;
                        continue;
                    }
                    if (!z8) {
                        z9 = false;
                        break;
                    }
                }
            }
            z9 = true;
            if (z9) {
                return;
            }
        }
        g gVar = new g(arrayList2, dVar, dVar2, t0Var3, obj, arrayList3, arrayList4, aVar, arrayList7, arrayList8, aVar2, aVar3, z7);
        for (h hVar4 : arrayList2) {
            hVar4.a().b(gVar);
        }
    }

    private final void G(Map map, View view) {
        String u7 = androidx.core.view.p0.u(view);
        if (u7 != null) {
            map.put(u7, view);
        }
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            int childCount = viewGroup.getChildCount();
            for (int i8 = 0; i8 < childCount; i8++) {
                View childAt = viewGroup.getChildAt(i8);
                if (childAt.getVisibility() == 0) {
                    d7.k.d(childAt, "child");
                    G(map, childAt);
                }
            }
        }
    }

    private final void H(b1.a aVar, Collection collection) {
        Set entrySet = aVar.entrySet();
        d7.k.d(entrySet, "entries");
        s6.s.n(entrySet, new i(collection));
    }

    private final void I(List list) {
        Object t8;
        t8 = s6.v.t(list);
        p i8 = ((y0.d) t8).i();
        Iterator it = list.iterator();
        while (it.hasNext()) {
            y0.d dVar = (y0.d) it.next();
            dVar.i().M.f3593c = i8.M.f3593c;
            dVar.i().M.f3594d = i8.M.f3594d;
            dVar.i().M.f3595e = i8.M.f3595e;
            dVar.i().M.f3596f = i8.M.f3596f;
        }
    }

    @Override // androidx.fragment.app.y0
    public void d(List list, boolean z7) {
        Object obj;
        Object obj2;
        boolean z8;
        boolean z9;
        d7.k.e(list, "operations");
        Iterator it = list.iterator();
        while (true) {
            obj = null;
            if (!it.hasNext()) {
                obj2 = null;
                break;
            }
            obj2 = it.next();
            y0.d dVar = (y0.d) obj2;
            y0.d.b.a aVar = y0.d.b.f3722e;
            View view = dVar.i().J;
            d7.k.d(view, "operation.fragment.mView");
            y0.d.b a8 = aVar.a(view);
            y0.d.b bVar = y0.d.b.VISIBLE;
            if (a8 != bVar || dVar.h() == bVar) {
                z9 = false;
                continue;
            } else {
                z9 = true;
                continue;
            }
            if (z9) {
                break;
            }
        }
        y0.d dVar2 = (y0.d) obj2;
        ListIterator listIterator = list.listIterator(list.size());
        while (true) {
            if (!listIterator.hasPrevious()) {
                break;
            }
            Object previous = listIterator.previous();
            y0.d dVar3 = (y0.d) previous;
            y0.d.b.a aVar2 = y0.d.b.f3722e;
            View view2 = dVar3.i().J;
            d7.k.d(view2, "operation.fragment.mView");
            y0.d.b a9 = aVar2.a(view2);
            y0.d.b bVar2 = y0.d.b.VISIBLE;
            if (a9 == bVar2 || dVar3.h() != bVar2) {
                z8 = false;
                continue;
            } else {
                z8 = true;
                continue;
            }
            if (z8) {
                obj = previous;
                break;
            }
        }
        y0.d dVar4 = (y0.d) obj;
        if (i0.I0(2)) {
            Log.v("FragmentManager", "Executing operations from " + dVar2 + " to " + dVar4);
        }
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        I(list);
        Iterator it2 = list.iterator();
        while (it2.hasNext()) {
            final y0.d dVar5 = (y0.d) it2.next();
            arrayList.add(new b(dVar5, z7));
            arrayList2.add(new h(dVar5, z7, !z7 ? dVar5 != dVar4 : dVar5 != dVar2));
            dVar5.a(new Runnable() { // from class: androidx.fragment.app.d
                @Override // java.lang.Runnable
                public final void run() {
                    f.E(f.this, dVar5);
                }
            });
        }
        F(arrayList2, z7, dVar2, dVar4);
        D(arrayList);
    }
}
