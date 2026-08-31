package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.util.SparseBooleanArray;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import androidx.appcompat.view.menu.ActionMenuItemView;
import androidx.appcompat.view.menu.m;
import androidx.appcompat.view.menu.n;
import androidx.appcompat.widget.ActionMenuView;
import androidx.core.view.b;
import java.util.ArrayList;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class c extends androidx.appcompat.view.menu.b implements b.a {
    RunnableC0010c A;
    private b B;
    final f C;
    int D;

    /* renamed from: k  reason: collision with root package name */
    d f2219k;

    /* renamed from: l  reason: collision with root package name */
    private Drawable f2220l;

    /* renamed from: m  reason: collision with root package name */
    private boolean f2221m;

    /* renamed from: n  reason: collision with root package name */
    private boolean f2222n;

    /* renamed from: o  reason: collision with root package name */
    private boolean f2223o;

    /* renamed from: p  reason: collision with root package name */
    private int f2224p;

    /* renamed from: q  reason: collision with root package name */
    private int f2225q;

    /* renamed from: r  reason: collision with root package name */
    private int f2226r;

    /* renamed from: s  reason: collision with root package name */
    private boolean f2227s;

    /* renamed from: t  reason: collision with root package name */
    private boolean f2228t;

    /* renamed from: u  reason: collision with root package name */
    private boolean f2229u;

    /* renamed from: v  reason: collision with root package name */
    private boolean f2230v;

    /* renamed from: w  reason: collision with root package name */
    private int f2231w;

    /* renamed from: x  reason: collision with root package name */
    private final SparseBooleanArray f2232x;

    /* renamed from: y  reason: collision with root package name */
    e f2233y;

    /* renamed from: z  reason: collision with root package name */
    a f2234z;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public class a extends androidx.appcompat.view.menu.l {
        public a(Context context, androidx.appcompat.view.menu.r rVar, View view) {
            super(context, rVar, view, false, g.a.f6800i);
            if (!((androidx.appcompat.view.menu.i) rVar.getItem()).l()) {
                View view2 = c.this.f2219k;
                f(view2 == null ? (View) ((androidx.appcompat.view.menu.b) c.this).f1839i : view2);
            }
            j(c.this.C);
        }

        @Override // androidx.appcompat.view.menu.l
        protected void e() {
            c cVar = c.this;
            cVar.f2234z = null;
            cVar.D = 0;
            super.e();
        }
    }

    /* loaded from: classes.dex */
    private class b extends ActionMenuItemView.b {
        b() {
        }

        @Override // androidx.appcompat.view.menu.ActionMenuItemView.b
        public androidx.appcompat.view.menu.p a() {
            a aVar = c.this.f2234z;
            if (aVar != null) {
                return aVar.c();
            }
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: androidx.appcompat.widget.c$c  reason: collision with other inner class name */
    /* loaded from: classes.dex */
    public class RunnableC0010c implements Runnable {

        /* renamed from: e  reason: collision with root package name */
        private e f2237e;

        public RunnableC0010c(e eVar) {
            this.f2237e = eVar;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (((androidx.appcompat.view.menu.b) c.this).f1833c != null) {
                ((androidx.appcompat.view.menu.b) c.this).f1833c.d();
            }
            View view = (View) ((androidx.appcompat.view.menu.b) c.this).f1839i;
            if (view != null && view.getWindowToken() != null && this.f2237e.m()) {
                c.this.f2233y = this.f2237e;
            }
            c.this.A = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public class d extends r implements ActionMenuView.a {

        /* loaded from: classes.dex */
        class a extends n0 {

            /* renamed from: j  reason: collision with root package name */
            final /* synthetic */ c f2240j;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            a(View view, c cVar) {
                super(view);
                this.f2240j = cVar;
            }

            @Override // androidx.appcompat.widget.n0
            public androidx.appcompat.view.menu.p b() {
                e eVar = c.this.f2233y;
                if (eVar == null) {
                    return null;
                }
                return eVar.c();
            }

            @Override // androidx.appcompat.widget.n0
            public boolean c() {
                c.this.K();
                return true;
            }

            @Override // androidx.appcompat.widget.n0
            public boolean d() {
                c cVar = c.this;
                if (cVar.A != null) {
                    return false;
                }
                cVar.B();
                return true;
            }
        }

        public d(Context context) {
            super(context, null, g.a.f6799h);
            setClickable(true);
            setFocusable(true);
            setVisibility(0);
            setEnabled(true);
            g1.a(this, getContentDescription());
            setOnTouchListener(new a(this, c.this));
        }

        @Override // androidx.appcompat.widget.ActionMenuView.a
        public boolean b() {
            return false;
        }

        @Override // androidx.appcompat.widget.ActionMenuView.a
        public boolean c() {
            return false;
        }

        @Override // android.view.View
        public boolean performClick() {
            if (super.performClick()) {
                return true;
            }
            playSoundEffect(0);
            c.this.K();
            return true;
        }

        @Override // android.widget.ImageView
        protected boolean setFrame(int i8, int i9, int i10, int i11) {
            boolean frame = super.setFrame(i8, i9, i10, i11);
            Drawable drawable = getDrawable();
            Drawable background = getBackground();
            if (drawable != null && background != null) {
                int width = getWidth();
                int height = getHeight();
                int max = Math.max(width, height) / 2;
                int paddingLeft = (width + (getPaddingLeft() - getPaddingRight())) / 2;
                int paddingTop = (height + (getPaddingTop() - getPaddingBottom())) / 2;
                androidx.core.graphics.drawable.a.d(background, paddingLeft - max, paddingTop - max, paddingLeft + max, paddingTop + max);
            }
            return frame;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public class e extends androidx.appcompat.view.menu.l {
        public e(Context context, androidx.appcompat.view.menu.g gVar, View view, boolean z7) {
            super(context, gVar, view, z7, g.a.f6800i);
            h(8388613);
            j(c.this.C);
        }

        @Override // androidx.appcompat.view.menu.l
        protected void e() {
            if (((androidx.appcompat.view.menu.b) c.this).f1833c != null) {
                ((androidx.appcompat.view.menu.b) c.this).f1833c.close();
            }
            c.this.f2233y = null;
            super.e();
        }
    }

    /* loaded from: classes.dex */
    private class f implements m.a {
        f() {
        }

        @Override // androidx.appcompat.view.menu.m.a
        public void b(androidx.appcompat.view.menu.g gVar, boolean z7) {
            if (gVar instanceof androidx.appcompat.view.menu.r) {
                gVar.D().e(false);
            }
            m.a m8 = c.this.m();
            if (m8 != null) {
                m8.b(gVar, z7);
            }
        }

        @Override // androidx.appcompat.view.menu.m.a
        public boolean c(androidx.appcompat.view.menu.g gVar) {
            if (gVar == ((androidx.appcompat.view.menu.b) c.this).f1833c) {
                return false;
            }
            c.this.D = ((androidx.appcompat.view.menu.r) gVar).getItem().getItemId();
            m.a m8 = c.this.m();
            if (m8 != null) {
                return m8.c(gVar);
            }
            return false;
        }
    }

    public c(Context context) {
        super(context, g.g.f6890c, g.g.f6889b);
        this.f2232x = new SparseBooleanArray();
        this.C = new f();
    }

    private View z(MenuItem menuItem) {
        ViewGroup viewGroup = (ViewGroup) this.f1839i;
        if (viewGroup == null) {
            return null;
        }
        int childCount = viewGroup.getChildCount();
        for (int i8 = 0; i8 < childCount; i8++) {
            View childAt = viewGroup.getChildAt(i8);
            if ((childAt instanceof n.a) && ((n.a) childAt).getItemData() == menuItem) {
                return childAt;
            }
        }
        return null;
    }

    public Drawable A() {
        d dVar = this.f2219k;
        if (dVar != null) {
            return dVar.getDrawable();
        }
        if (this.f2221m) {
            return this.f2220l;
        }
        return null;
    }

    public boolean B() {
        androidx.appcompat.view.menu.n nVar;
        RunnableC0010c runnableC0010c = this.A;
        if (runnableC0010c != null && (nVar = this.f1839i) != null) {
            ((View) nVar).removeCallbacks(runnableC0010c);
            this.A = null;
            return true;
        }
        e eVar = this.f2233y;
        if (eVar != null) {
            eVar.b();
            return true;
        }
        return false;
    }

    public boolean C() {
        a aVar = this.f2234z;
        if (aVar != null) {
            aVar.b();
            return true;
        }
        return false;
    }

    public boolean D() {
        return this.A != null || E();
    }

    public boolean E() {
        e eVar = this.f2233y;
        return eVar != null && eVar.d();
    }

    public void F(Configuration configuration) {
        if (!this.f2227s) {
            this.f2226r = androidx.appcompat.view.a.b(this.f1832b).d();
        }
        androidx.appcompat.view.menu.g gVar = this.f1833c;
        if (gVar != null) {
            gVar.K(true);
        }
    }

    public void G(boolean z7) {
        this.f2230v = z7;
    }

    public void H(ActionMenuView actionMenuView) {
        this.f1839i = actionMenuView;
        actionMenuView.b(this.f1833c);
    }

    public void I(Drawable drawable) {
        d dVar = this.f2219k;
        if (dVar != null) {
            dVar.setImageDrawable(drawable);
            return;
        }
        this.f2221m = true;
        this.f2220l = drawable;
    }

    public void J(boolean z7) {
        this.f2222n = z7;
        this.f2223o = true;
    }

    public boolean K() {
        androidx.appcompat.view.menu.g gVar;
        if (!this.f2222n || E() || (gVar = this.f1833c) == null || this.f1839i == null || this.A != null || gVar.z().isEmpty()) {
            return false;
        }
        RunnableC0010c runnableC0010c = new RunnableC0010c(new e(this.f1832b, this.f1833c, this.f2219k, true));
        this.A = runnableC0010c;
        ((View) this.f1839i).post(runnableC0010c);
        return true;
    }

    @Override // androidx.appcompat.view.menu.b, androidx.appcompat.view.menu.m
    public void b(androidx.appcompat.view.menu.g gVar, boolean z7) {
        y();
        super.b(gVar, z7);
    }

    @Override // androidx.appcompat.view.menu.b
    public void c(androidx.appcompat.view.menu.i iVar, n.a aVar) {
        aVar.d(iVar, 0);
        ActionMenuItemView actionMenuItemView = (ActionMenuItemView) aVar;
        actionMenuItemView.setItemInvoker((ActionMenuView) this.f1839i);
        if (this.B == null) {
            this.B = new b();
        }
        actionMenuItemView.setPopupCallback(this.B);
    }

    @Override // androidx.appcompat.view.menu.b, androidx.appcompat.view.menu.m
    public void d(Context context, androidx.appcompat.view.menu.g gVar) {
        super.d(context, gVar);
        Resources resources = context.getResources();
        androidx.appcompat.view.a b8 = androidx.appcompat.view.a.b(context);
        if (!this.f2223o) {
            this.f2222n = b8.f();
        }
        if (!this.f2229u) {
            this.f2224p = b8.c();
        }
        if (!this.f2227s) {
            this.f2226r = b8.d();
        }
        int i8 = this.f2224p;
        if (this.f2222n) {
            if (this.f2219k == null) {
                d dVar = new d(this.f1831a);
                this.f2219k = dVar;
                if (this.f2221m) {
                    dVar.setImageDrawable(this.f2220l);
                    this.f2220l = null;
                    this.f2221m = false;
                }
                int makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, 0);
                this.f2219k.measure(makeMeasureSpec, makeMeasureSpec);
            }
            i8 -= this.f2219k.getMeasuredWidth();
        } else {
            this.f2219k = null;
        }
        this.f2225q = i8;
        this.f2231w = (int) (resources.getDisplayMetrics().density * 56.0f);
    }

    @Override // androidx.appcompat.view.menu.b, androidx.appcompat.view.menu.m
    public boolean e(androidx.appcompat.view.menu.r rVar) {
        boolean z7 = false;
        if (rVar.hasVisibleItems()) {
            androidx.appcompat.view.menu.r rVar2 = rVar;
            while (rVar2.f0() != this.f1833c) {
                rVar2 = (androidx.appcompat.view.menu.r) rVar2.f0();
            }
            View z8 = z(rVar2.getItem());
            if (z8 == null) {
                return false;
            }
            this.D = rVar.getItem().getItemId();
            int size = rVar.size();
            int i8 = 0;
            while (true) {
                if (i8 >= size) {
                    break;
                }
                MenuItem item = rVar.getItem(i8);
                if (item.isVisible() && item.getIcon() != null) {
                    z7 = true;
                    break;
                }
                i8++;
            }
            a aVar = new a(this.f1832b, rVar, z8);
            this.f2234z = aVar;
            aVar.g(z7);
            this.f2234z.k();
            super.e(rVar);
            return true;
        }
        return false;
    }

    @Override // androidx.appcompat.view.menu.b, androidx.appcompat.view.menu.m
    public void f(boolean z7) {
        super.f(z7);
        ((View) this.f1839i).requestLayout();
        androidx.appcompat.view.menu.g gVar = this.f1833c;
        boolean z8 = false;
        if (gVar != null) {
            ArrayList s8 = gVar.s();
            int size = s8.size();
            for (int i8 = 0; i8 < size; i8++) {
                androidx.core.view.b b8 = ((androidx.appcompat.view.menu.i) s8.get(i8)).b();
                if (b8 != null) {
                    b8.h(this);
                }
            }
        }
        androidx.appcompat.view.menu.g gVar2 = this.f1833c;
        ArrayList z9 = gVar2 != null ? gVar2.z() : null;
        if (this.f2222n && z9 != null) {
            int size2 = z9.size();
            if (size2 == 1) {
                z8 = !((androidx.appcompat.view.menu.i) z9.get(0)).isActionViewExpanded();
            } else if (size2 > 0) {
                z8 = true;
            }
        }
        d dVar = this.f2219k;
        if (z8) {
            if (dVar == null) {
                this.f2219k = new d(this.f1831a);
            }
            ViewGroup viewGroup = (ViewGroup) this.f2219k.getParent();
            if (viewGroup != this.f1839i) {
                if (viewGroup != null) {
                    viewGroup.removeView(this.f2219k);
                }
                ActionMenuView actionMenuView = (ActionMenuView) this.f1839i;
                actionMenuView.addView(this.f2219k, actionMenuView.D());
            }
        } else if (dVar != null) {
            ViewParent parent = dVar.getParent();
            androidx.appcompat.view.menu.n nVar = this.f1839i;
            if (parent == nVar) {
                ((ViewGroup) nVar).removeView(this.f2219k);
            }
        }
        ((ActionMenuView) this.f1839i).setOverflowReserved(this.f2222n);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r3v0 */
    /* JADX WARN: Type inference failed for: r3v1, types: [int] */
    /* JADX WARN: Type inference failed for: r3v12 */
    @Override // androidx.appcompat.view.menu.m
    public boolean g() {
        ArrayList arrayList;
        int i8;
        int i9;
        int i10;
        boolean z7;
        int i11;
        c cVar = this;
        androidx.appcompat.view.menu.g gVar = cVar.f1833c;
        View view = null;
        ?? r32 = 0;
        if (gVar != null) {
            arrayList = gVar.E();
            i8 = arrayList.size();
        } else {
            arrayList = null;
            i8 = 0;
        }
        int i12 = cVar.f2226r;
        int i13 = cVar.f2225q;
        int makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, 0);
        ViewGroup viewGroup = (ViewGroup) cVar.f1839i;
        boolean z8 = false;
        int i14 = 0;
        int i15 = 0;
        for (int i16 = 0; i16 < i8; i16++) {
            androidx.appcompat.view.menu.i iVar = (androidx.appcompat.view.menu.i) arrayList.get(i16);
            if (iVar.o()) {
                i14++;
            } else if (iVar.n()) {
                i15++;
            } else {
                z8 = true;
            }
            if (cVar.f2230v && iVar.isActionViewExpanded()) {
                i12 = 0;
            }
        }
        if (cVar.f2222n && (z8 || i15 + i14 > i12)) {
            i12--;
        }
        int i17 = i12 - i14;
        SparseBooleanArray sparseBooleanArray = cVar.f2232x;
        sparseBooleanArray.clear();
        if (cVar.f2228t) {
            int i18 = cVar.f2231w;
            i10 = i13 / i18;
            i9 = i18 + ((i13 % i18) / i10);
        } else {
            i9 = 0;
            i10 = 0;
        }
        int i19 = 0;
        int i20 = 0;
        while (i19 < i8) {
            androidx.appcompat.view.menu.i iVar2 = (androidx.appcompat.view.menu.i) arrayList.get(i19);
            if (iVar2.o()) {
                View n8 = cVar.n(iVar2, view, viewGroup);
                if (cVar.f2228t) {
                    i10 -= ActionMenuView.J(n8, i9, i10, makeMeasureSpec, r32);
                } else {
                    n8.measure(makeMeasureSpec, makeMeasureSpec);
                }
                int measuredWidth = n8.getMeasuredWidth();
                i13 -= measuredWidth;
                if (i20 == 0) {
                    i20 = measuredWidth;
                }
                int groupId = iVar2.getGroupId();
                if (groupId != 0) {
                    sparseBooleanArray.put(groupId, true);
                }
                iVar2.u(true);
                z7 = r32;
                i11 = i8;
            } else if (iVar2.n()) {
                int groupId2 = iVar2.getGroupId();
                boolean z9 = sparseBooleanArray.get(groupId2);
                boolean z10 = (i17 > 0 || z9) && i13 > 0 && (!cVar.f2228t || i10 > 0);
                boolean z11 = z10;
                i11 = i8;
                if (z10) {
                    View n9 = cVar.n(iVar2, null, viewGroup);
                    if (cVar.f2228t) {
                        int J = ActionMenuView.J(n9, i9, i10, makeMeasureSpec, 0);
                        i10 -= J;
                        if (J == 0) {
                            z11 = false;
                        }
                    } else {
                        n9.measure(makeMeasureSpec, makeMeasureSpec);
                    }
                    boolean z12 = z11;
                    int measuredWidth2 = n9.getMeasuredWidth();
                    i13 -= measuredWidth2;
                    if (i20 == 0) {
                        i20 = measuredWidth2;
                    }
                    z10 = z12 & (!cVar.f2228t ? i13 + i20 <= 0 : i13 < 0);
                }
                if (z10 && groupId2 != 0) {
                    sparseBooleanArray.put(groupId2, true);
                } else if (z9) {
                    sparseBooleanArray.put(groupId2, false);
                    for (int i21 = 0; i21 < i19; i21++) {
                        androidx.appcompat.view.menu.i iVar3 = (androidx.appcompat.view.menu.i) arrayList.get(i21);
                        if (iVar3.getGroupId() == groupId2) {
                            if (iVar3.l()) {
                                i17++;
                            }
                            iVar3.u(false);
                        }
                    }
                }
                if (z10) {
                    i17--;
                }
                iVar2.u(z10);
                z7 = false;
            } else {
                z7 = r32;
                i11 = i8;
                iVar2.u(z7);
            }
            i19++;
            r32 = z7;
            i8 = i11;
            view = null;
            cVar = this;
        }
        return true;
    }

    @Override // androidx.appcompat.view.menu.b
    public boolean l(ViewGroup viewGroup, int i8) {
        if (viewGroup.getChildAt(i8) == this.f2219k) {
            return false;
        }
        return super.l(viewGroup, i8);
    }

    @Override // androidx.appcompat.view.menu.b
    public View n(androidx.appcompat.view.menu.i iVar, View view, ViewGroup viewGroup) {
        View actionView = iVar.getActionView();
        if (actionView == null || iVar.j()) {
            actionView = super.n(iVar, view, viewGroup);
        }
        actionView.setVisibility(iVar.isActionViewExpanded() ? 8 : 0);
        ActionMenuView actionMenuView = (ActionMenuView) viewGroup;
        ViewGroup.LayoutParams layoutParams = actionView.getLayoutParams();
        if (!actionMenuView.checkLayoutParams(layoutParams)) {
            actionView.setLayoutParams(actionMenuView.m(layoutParams));
        }
        return actionView;
    }

    @Override // androidx.appcompat.view.menu.b
    public androidx.appcompat.view.menu.n o(ViewGroup viewGroup) {
        androidx.appcompat.view.menu.n nVar = this.f1839i;
        androidx.appcompat.view.menu.n o8 = super.o(viewGroup);
        if (nVar != o8) {
            ((ActionMenuView) o8).setPresenter(this);
        }
        return o8;
    }

    @Override // androidx.appcompat.view.menu.b
    public boolean q(int i8, androidx.appcompat.view.menu.i iVar) {
        return iVar.l();
    }

    public boolean y() {
        return B() | C();
    }
}
