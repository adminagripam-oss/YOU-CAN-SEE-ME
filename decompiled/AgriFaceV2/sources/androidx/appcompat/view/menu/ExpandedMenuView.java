package androidx.appcompat.view.menu;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import androidx.appcompat.view.menu.g;
import androidx.appcompat.widget.b1;
/* loaded from: classes.dex */
public final class ExpandedMenuView extends ListView implements g.b, n, AdapterView.OnItemClickListener {

    /* renamed from: c  reason: collision with root package name */
    private static final int[] f1791c = {16842964, 16843049};

    /* renamed from: a  reason: collision with root package name */
    private g f1792a;

    /* renamed from: b  reason: collision with root package name */
    private int f1793b;

    public ExpandedMenuView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 16842868);
    }

    @Override // androidx.appcompat.view.menu.g.b
    public boolean a(i iVar) {
        return this.f1792a.L(iVar, 0);
    }

    @Override // androidx.appcompat.view.menu.n
    public void b(g gVar) {
        this.f1792a = gVar;
    }

    public int getWindowAnimations() {
        return this.f1793b;
    }

    @Override // android.widget.ListView, android.widget.AbsListView, android.widget.AdapterView, android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        setChildrenDrawingCacheEnabled(false);
    }

    @Override // android.widget.AdapterView.OnItemClickListener
    public void onItemClick(AdapterView adapterView, View view, int i8, long j8) {
        a((i) getAdapter().getItem(i8));
    }

    public ExpandedMenuView(Context context, AttributeSet attributeSet, int i8) {
        super(context, attributeSet);
        setOnItemClickListener(this);
        b1 u7 = b1.u(context, attributeSet, f1791c, i8, 0);
        if (u7.r(0)) {
            setBackgroundDrawable(u7.f(0));
        }
        if (u7.r(1)) {
            setDivider(u7.f(1));
        }
        u7.v();
    }
}
