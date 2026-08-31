package androidx.appcompat.view.menu;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import androidx.appcompat.view.menu.n;
import androidx.appcompat.widget.b1;
import androidx.core.view.p0;
/* loaded from: classes.dex */
public class ListMenuItemView extends LinearLayout implements n.a, AbsListView.SelectionBoundsAdjuster {

    /* renamed from: a  reason: collision with root package name */
    private i f1794a;

    /* renamed from: b  reason: collision with root package name */
    private ImageView f1795b;

    /* renamed from: c  reason: collision with root package name */
    private RadioButton f1796c;

    /* renamed from: d  reason: collision with root package name */
    private TextView f1797d;

    /* renamed from: e  reason: collision with root package name */
    private CheckBox f1798e;

    /* renamed from: f  reason: collision with root package name */
    private TextView f1799f;

    /* renamed from: g  reason: collision with root package name */
    private ImageView f1800g;

    /* renamed from: h  reason: collision with root package name */
    private ImageView f1801h;

    /* renamed from: i  reason: collision with root package name */
    private LinearLayout f1802i;

    /* renamed from: j  reason: collision with root package name */
    private Drawable f1803j;

    /* renamed from: k  reason: collision with root package name */
    private int f1804k;

    /* renamed from: l  reason: collision with root package name */
    private Context f1805l;

    /* renamed from: m  reason: collision with root package name */
    private boolean f1806m;

    /* renamed from: n  reason: collision with root package name */
    private Drawable f1807n;

    /* renamed from: o  reason: collision with root package name */
    private boolean f1808o;

    /* renamed from: p  reason: collision with root package name */
    private LayoutInflater f1809p;

    /* renamed from: q  reason: collision with root package name */
    private boolean f1810q;

    public ListMenuItemView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, g.a.A);
    }

    private void b(View view) {
        c(view, -1);
    }

    private void c(View view, int i8) {
        LinearLayout linearLayout = this.f1802i;
        if (linearLayout != null) {
            linearLayout.addView(view, i8);
        } else {
            addView(view, i8);
        }
    }

    private void e() {
        CheckBox checkBox = (CheckBox) getInflater().inflate(g.g.f6895h, (ViewGroup) this, false);
        this.f1798e = checkBox;
        b(checkBox);
    }

    private void f() {
        ImageView imageView = (ImageView) getInflater().inflate(g.g.f6896i, (ViewGroup) this, false);
        this.f1795b = imageView;
        c(imageView, 0);
    }

    private void g() {
        RadioButton radioButton = (RadioButton) getInflater().inflate(g.g.f6898k, (ViewGroup) this, false);
        this.f1796c = radioButton;
        b(radioButton);
    }

    private LayoutInflater getInflater() {
        if (this.f1809p == null) {
            this.f1809p = LayoutInflater.from(getContext());
        }
        return this.f1809p;
    }

    private void setSubMenuArrowVisible(boolean z7) {
        ImageView imageView = this.f1800g;
        if (imageView != null) {
            imageView.setVisibility(z7 ? 0 : 8);
        }
    }

    @Override // androidx.appcompat.view.menu.n.a
    public boolean a() {
        return false;
    }

    @Override // android.widget.AbsListView.SelectionBoundsAdjuster
    public void adjustListItemSelectionBounds(Rect rect) {
        ImageView imageView = this.f1801h;
        if (imageView == null || imageView.getVisibility() != 0) {
            return;
        }
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) this.f1801h.getLayoutParams();
        rect.top += this.f1801h.getHeight() + layoutParams.topMargin + layoutParams.bottomMargin;
    }

    @Override // androidx.appcompat.view.menu.n.a
    public void d(i iVar, int i8) {
        this.f1794a = iVar;
        setVisibility(iVar.isVisible() ? 0 : 8);
        setTitle(iVar.i(this));
        setCheckable(iVar.isCheckable());
        h(iVar.A(), iVar.g());
        setIcon(iVar.getIcon());
        setEnabled(iVar.isEnabled());
        setSubMenuArrowVisible(iVar.hasSubMenu());
        setContentDescription(iVar.getContentDescription());
    }

    @Override // androidx.appcompat.view.menu.n.a
    public i getItemData() {
        return this.f1794a;
    }

    public void h(boolean z7, char c8) {
        int i8 = (z7 && this.f1794a.A()) ? 0 : 8;
        if (i8 == 0) {
            this.f1799f.setText(this.f1794a.h());
        }
        if (this.f1799f.getVisibility() != i8) {
            this.f1799f.setVisibility(i8);
        }
    }

    @Override // android.view.View
    protected void onFinishInflate() {
        super.onFinishInflate();
        p0.Q(this, this.f1803j);
        TextView textView = (TextView) findViewById(g.f.L);
        this.f1797d = textView;
        int i8 = this.f1804k;
        if (i8 != -1) {
            textView.setTextAppearance(this.f1805l, i8);
        }
        this.f1799f = (TextView) findViewById(g.f.E);
        ImageView imageView = (ImageView) findViewById(g.f.H);
        this.f1800g = imageView;
        if (imageView != null) {
            imageView.setImageDrawable(this.f1807n);
        }
        this.f1801h = (ImageView) findViewById(g.f.f6879r);
        this.f1802i = (LinearLayout) findViewById(g.f.f6873l);
    }

    @Override // android.widget.LinearLayout, android.view.View
    protected void onMeasure(int i8, int i9) {
        if (this.f1795b != null && this.f1806m) {
            ViewGroup.LayoutParams layoutParams = getLayoutParams();
            LinearLayout.LayoutParams layoutParams2 = (LinearLayout.LayoutParams) this.f1795b.getLayoutParams();
            int i10 = layoutParams.height;
            if (i10 > 0 && layoutParams2.width <= 0) {
                layoutParams2.width = i10;
            }
        }
        super.onMeasure(i8, i9);
    }

    public void setCheckable(boolean z7) {
        CompoundButton compoundButton;
        View view;
        if (!z7 && this.f1796c == null && this.f1798e == null) {
            return;
        }
        if (this.f1794a.m()) {
            if (this.f1796c == null) {
                g();
            }
            compoundButton = this.f1796c;
            view = this.f1798e;
        } else {
            if (this.f1798e == null) {
                e();
            }
            compoundButton = this.f1798e;
            view = this.f1796c;
        }
        if (z7) {
            compoundButton.setChecked(this.f1794a.isChecked());
            if (compoundButton.getVisibility() != 0) {
                compoundButton.setVisibility(0);
            }
            if (view == null || view.getVisibility() == 8) {
                return;
            }
            view.setVisibility(8);
            return;
        }
        CheckBox checkBox = this.f1798e;
        if (checkBox != null) {
            checkBox.setVisibility(8);
        }
        RadioButton radioButton = this.f1796c;
        if (radioButton != null) {
            radioButton.setVisibility(8);
        }
    }

    public void setChecked(boolean z7) {
        CompoundButton compoundButton;
        if (this.f1794a.m()) {
            if (this.f1796c == null) {
                g();
            }
            compoundButton = this.f1796c;
        } else {
            if (this.f1798e == null) {
                e();
            }
            compoundButton = this.f1798e;
        }
        compoundButton.setChecked(z7);
    }

    public void setForceShowIcon(boolean z7) {
        this.f1810q = z7;
        this.f1806m = z7;
    }

    public void setGroupDividerEnabled(boolean z7) {
        ImageView imageView = this.f1801h;
        if (imageView != null) {
            imageView.setVisibility((this.f1808o || !z7) ? 8 : 0);
        }
    }

    public void setIcon(Drawable drawable) {
        boolean z7 = this.f1794a.z() || this.f1810q;
        if (z7 || this.f1806m) {
            ImageView imageView = this.f1795b;
            if (imageView == null && drawable == null && !this.f1806m) {
                return;
            }
            if (imageView == null) {
                f();
            }
            if (drawable == null && !this.f1806m) {
                this.f1795b.setVisibility(8);
                return;
            }
            ImageView imageView2 = this.f1795b;
            if (!z7) {
                drawable = null;
            }
            imageView2.setImageDrawable(drawable);
            if (this.f1795b.getVisibility() != 0) {
                this.f1795b.setVisibility(0);
            }
        }
    }

    public void setTitle(CharSequence charSequence) {
        int i8;
        TextView textView;
        if (charSequence != null) {
            this.f1797d.setText(charSequence);
            if (this.f1797d.getVisibility() == 0) {
                return;
            }
            textView = this.f1797d;
            i8 = 0;
        } else {
            i8 = 8;
            if (this.f1797d.getVisibility() == 8) {
                return;
            }
            textView = this.f1797d;
        }
        textView.setVisibility(i8);
    }

    public ListMenuItemView(Context context, AttributeSet attributeSet, int i8) {
        super(context, attributeSet);
        b1 u7 = b1.u(getContext(), attributeSet, g.j.T1, i8, 0);
        this.f1803j = u7.f(g.j.V1);
        this.f1804k = u7.m(g.j.U1, -1);
        this.f1806m = u7.a(g.j.W1, false);
        this.f1805l = context;
        this.f1807n = u7.f(g.j.X1);
        TypedArray obtainStyledAttributes = context.getTheme().obtainStyledAttributes(null, new int[]{16843049}, g.a.f6815x, 0);
        this.f1808o = obtainStyledAttributes.hasValue(0);
        u7.v();
        obtainStyledAttributes.recycle();
    }
}
