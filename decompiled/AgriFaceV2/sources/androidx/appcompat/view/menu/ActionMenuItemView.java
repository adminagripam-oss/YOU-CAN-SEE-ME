package androidx.appcompat.view.menu;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.view.menu.g;
import androidx.appcompat.view.menu.n;
import androidx.appcompat.widget.ActionMenuView;
import androidx.appcompat.widget.d0;
import androidx.appcompat.widget.g1;
import androidx.appcompat.widget.n0;
/* loaded from: classes.dex */
public class ActionMenuItemView extends d0 implements n.a, View.OnClickListener, ActionMenuView.a {

    /* renamed from: h  reason: collision with root package name */
    i f1779h;

    /* renamed from: i  reason: collision with root package name */
    private CharSequence f1780i;

    /* renamed from: j  reason: collision with root package name */
    private Drawable f1781j;

    /* renamed from: k  reason: collision with root package name */
    g.b f1782k;

    /* renamed from: l  reason: collision with root package name */
    private n0 f1783l;

    /* renamed from: m  reason: collision with root package name */
    b f1784m;

    /* renamed from: n  reason: collision with root package name */
    private boolean f1785n;

    /* renamed from: o  reason: collision with root package name */
    private boolean f1786o;

    /* renamed from: p  reason: collision with root package name */
    private int f1787p;

    /* renamed from: q  reason: collision with root package name */
    private int f1788q;

    /* renamed from: r  reason: collision with root package name */
    private int f1789r;

    /* loaded from: classes.dex */
    private class a extends n0 {
        public a() {
            super(ActionMenuItemView.this);
        }

        @Override // androidx.appcompat.widget.n0
        public p b() {
            b bVar = ActionMenuItemView.this.f1784m;
            if (bVar != null) {
                return bVar.a();
            }
            return null;
        }

        @Override // androidx.appcompat.widget.n0
        protected boolean c() {
            p b8;
            ActionMenuItemView actionMenuItemView = ActionMenuItemView.this;
            g.b bVar = actionMenuItemView.f1782k;
            return bVar != null && bVar.a(actionMenuItemView.f1779h) && (b8 = b()) != null && b8.c();
        }
    }

    /* loaded from: classes.dex */
    public static abstract class b {
        public abstract p a();
    }

    public ActionMenuItemView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    private boolean s() {
        Configuration configuration = getContext().getResources().getConfiguration();
        int i8 = configuration.screenWidthDp;
        return i8 >= 480 || (i8 >= 640 && configuration.screenHeightDp >= 480) || configuration.orientation == 2;
    }

    private void t() {
        boolean z7 = true;
        boolean z8 = !TextUtils.isEmpty(this.f1780i);
        if (this.f1781j != null && (!this.f1779h.B() || (!this.f1785n && !this.f1786o))) {
            z7 = false;
        }
        boolean z9 = z8 & z7;
        setText(z9 ? this.f1780i : null);
        CharSequence contentDescription = this.f1779h.getContentDescription();
        if (TextUtils.isEmpty(contentDescription)) {
            contentDescription = z9 ? null : this.f1779h.getTitle();
        }
        setContentDescription(contentDescription);
        CharSequence tooltipText = this.f1779h.getTooltipText();
        if (TextUtils.isEmpty(tooltipText)) {
            g1.a(this, z9 ? null : this.f1779h.getTitle());
        } else {
            g1.a(this, tooltipText);
        }
    }

    @Override // androidx.appcompat.view.menu.n.a
    public boolean a() {
        return true;
    }

    @Override // androidx.appcompat.widget.ActionMenuView.a
    public boolean b() {
        return r();
    }

    @Override // androidx.appcompat.widget.ActionMenuView.a
    public boolean c() {
        return r() && this.f1779h.getIcon() == null;
    }

    @Override // androidx.appcompat.view.menu.n.a
    public void d(i iVar, int i8) {
        this.f1779h = iVar;
        setIcon(iVar.getIcon());
        setTitle(iVar.i(this));
        setId(iVar.getItemId());
        setVisibility(iVar.isVisible() ? 0 : 8);
        setEnabled(iVar.isEnabled());
        if (iVar.hasSubMenu() && this.f1783l == null) {
            this.f1783l = new a();
        }
    }

    @Override // android.widget.TextView, android.view.View
    public CharSequence getAccessibilityClassName() {
        return Button.class.getName();
    }

    @Override // androidx.appcompat.view.menu.n.a
    public i getItemData() {
        return this.f1779h;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        g.b bVar = this.f1782k;
        if (bVar != null) {
            bVar.a(this.f1779h);
        }
    }

    @Override // android.widget.TextView, android.view.View
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        this.f1785n = s();
        t();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.appcompat.widget.d0, android.widget.TextView, android.view.View
    public void onMeasure(int i8, int i9) {
        int i10;
        boolean r8 = r();
        if (r8 && (i10 = this.f1788q) >= 0) {
            super.setPadding(i10, getPaddingTop(), getPaddingRight(), getPaddingBottom());
        }
        super.onMeasure(i8, i9);
        int mode = View.MeasureSpec.getMode(i8);
        int size = View.MeasureSpec.getSize(i8);
        int measuredWidth = getMeasuredWidth();
        int min = mode == Integer.MIN_VALUE ? Math.min(size, this.f1787p) : this.f1787p;
        if (mode != 1073741824 && this.f1787p > 0 && measuredWidth < min) {
            super.onMeasure(View.MeasureSpec.makeMeasureSpec(min, 1073741824), i9);
        }
        if (r8 || this.f1781j == null) {
            return;
        }
        super.setPadding((getMeasuredWidth() - this.f1781j.getBounds().width()) / 2, getPaddingTop(), getPaddingRight(), getPaddingBottom());
    }

    @Override // android.widget.TextView, android.view.View
    public void onRestoreInstanceState(Parcelable parcelable) {
        super.onRestoreInstanceState(null);
    }

    @Override // android.widget.TextView, android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        n0 n0Var;
        if (this.f1779h.hasSubMenu() && (n0Var = this.f1783l) != null && n0Var.onTouch(this, motionEvent)) {
            return true;
        }
        return super.onTouchEvent(motionEvent);
    }

    public boolean r() {
        return !TextUtils.isEmpty(getText());
    }

    public void setCheckable(boolean z7) {
    }

    public void setChecked(boolean z7) {
    }

    public void setExpandedFormat(boolean z7) {
        if (this.f1786o != z7) {
            this.f1786o = z7;
            i iVar = this.f1779h;
            if (iVar != null) {
                iVar.c();
            }
        }
    }

    public void setIcon(Drawable drawable) {
        this.f1781j = drawable;
        if (drawable != null) {
            int intrinsicWidth = drawable.getIntrinsicWidth();
            int intrinsicHeight = drawable.getIntrinsicHeight();
            int i8 = this.f1789r;
            if (intrinsicWidth > i8) {
                intrinsicHeight = (int) (intrinsicHeight * (i8 / intrinsicWidth));
                intrinsicWidth = i8;
            }
            if (intrinsicHeight > i8) {
                intrinsicWidth = (int) (intrinsicWidth * (i8 / intrinsicHeight));
            } else {
                i8 = intrinsicHeight;
            }
            drawable.setBounds(0, 0, intrinsicWidth, i8);
        }
        setCompoundDrawables(drawable, null, null, null);
        t();
    }

    public void setItemInvoker(g.b bVar) {
        this.f1782k = bVar;
    }

    @Override // android.widget.TextView, android.view.View
    public void setPadding(int i8, int i9, int i10, int i11) {
        this.f1788q = i8;
        super.setPadding(i8, i9, i10, i11);
    }

    public void setPopupCallback(b bVar) {
        this.f1784m = bVar;
    }

    public void setTitle(CharSequence charSequence) {
        this.f1780i = charSequence;
        t();
    }

    public ActionMenuItemView(Context context, AttributeSet attributeSet, int i8) {
        super(context, attributeSet, i8);
        Resources resources = context.getResources();
        this.f1785n = s();
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, g.j.f7028v, i8, 0);
        this.f1787p = obtainStyledAttributes.getDimensionPixelSize(g.j.f7033w, 0);
        obtainStyledAttributes.recycle();
        this.f1789r = (int) ((resources.getDisplayMetrics().density * 32.0f) + 0.5f);
        setOnClickListener(this);
        this.f1788q = -1;
        setSaveEnabled(false);
    }
}
