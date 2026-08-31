package androidx.appcompat.view.menu;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.view.menu.m;
import androidx.appcompat.view.menu.n;
import java.util.ArrayList;
/* loaded from: classes.dex */
public abstract class b implements m {

    /* renamed from: a  reason: collision with root package name */
    protected Context f1831a;

    /* renamed from: b  reason: collision with root package name */
    protected Context f1832b;

    /* renamed from: c  reason: collision with root package name */
    protected g f1833c;

    /* renamed from: d  reason: collision with root package name */
    protected LayoutInflater f1834d;

    /* renamed from: e  reason: collision with root package name */
    protected LayoutInflater f1835e;

    /* renamed from: f  reason: collision with root package name */
    private m.a f1836f;

    /* renamed from: g  reason: collision with root package name */
    private int f1837g;

    /* renamed from: h  reason: collision with root package name */
    private int f1838h;

    /* renamed from: i  reason: collision with root package name */
    protected n f1839i;

    /* renamed from: j  reason: collision with root package name */
    private int f1840j;

    public b(Context context, int i8, int i9) {
        this.f1831a = context;
        this.f1834d = LayoutInflater.from(context);
        this.f1837g = i8;
        this.f1838h = i9;
    }

    protected void a(View view, int i8) {
        ViewGroup viewGroup = (ViewGroup) view.getParent();
        if (viewGroup != null) {
            viewGroup.removeView(view);
        }
        ((ViewGroup) this.f1839i).addView(view, i8);
    }

    @Override // androidx.appcompat.view.menu.m
    public void b(g gVar, boolean z7) {
        m.a aVar = this.f1836f;
        if (aVar != null) {
            aVar.b(gVar, z7);
        }
    }

    public abstract void c(i iVar, n.a aVar);

    @Override // androidx.appcompat.view.menu.m
    public void d(Context context, g gVar) {
        this.f1832b = context;
        this.f1835e = LayoutInflater.from(context);
        this.f1833c = gVar;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v4, types: [androidx.appcompat.view.menu.g] */
    @Override // androidx.appcompat.view.menu.m
    public boolean e(r rVar) {
        m.a aVar = this.f1836f;
        r rVar2 = rVar;
        if (aVar != null) {
            if (rVar == null) {
                rVar2 = this.f1833c;
            }
            return aVar.c(rVar2);
        }
        return false;
    }

    @Override // androidx.appcompat.view.menu.m
    public void f(boolean z7) {
        ViewGroup viewGroup = (ViewGroup) this.f1839i;
        if (viewGroup == null) {
            return;
        }
        g gVar = this.f1833c;
        int i8 = 0;
        if (gVar != null) {
            gVar.r();
            ArrayList E = this.f1833c.E();
            int size = E.size();
            int i9 = 0;
            for (int i10 = 0; i10 < size; i10++) {
                i iVar = (i) E.get(i10);
                if (q(i9, iVar)) {
                    View childAt = viewGroup.getChildAt(i9);
                    i itemData = childAt instanceof n.a ? ((n.a) childAt).getItemData() : null;
                    View n8 = n(iVar, childAt, viewGroup);
                    if (iVar != itemData) {
                        n8.setPressed(false);
                        n8.jumpDrawablesToCurrentState();
                    }
                    if (n8 != childAt) {
                        a(n8, i9);
                    }
                    i9++;
                }
            }
            i8 = i9;
        }
        while (i8 < viewGroup.getChildCount()) {
            if (!l(viewGroup, i8)) {
                i8++;
            }
        }
    }

    public n.a h(ViewGroup viewGroup) {
        return (n.a) this.f1834d.inflate(this.f1838h, viewGroup, false);
    }

    @Override // androidx.appcompat.view.menu.m
    public boolean i(g gVar, i iVar) {
        return false;
    }

    @Override // androidx.appcompat.view.menu.m
    public boolean j(g gVar, i iVar) {
        return false;
    }

    @Override // androidx.appcompat.view.menu.m
    public void k(m.a aVar) {
        this.f1836f = aVar;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean l(ViewGroup viewGroup, int i8) {
        viewGroup.removeViewAt(i8);
        return true;
    }

    public m.a m() {
        return this.f1836f;
    }

    public View n(i iVar, View view, ViewGroup viewGroup) {
        n.a h8 = view instanceof n.a ? (n.a) view : h(viewGroup);
        c(iVar, h8);
        return (View) h8;
    }

    public n o(ViewGroup viewGroup) {
        if (this.f1839i == null) {
            n nVar = (n) this.f1834d.inflate(this.f1837g, viewGroup, false);
            this.f1839i = nVar;
            nVar.b(this.f1833c);
            f(true);
        }
        return this.f1839i;
    }

    public void p(int i8) {
        this.f1840j = i8;
    }

    public abstract boolean q(int i8, i iVar);
}
