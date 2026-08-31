package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
/* loaded from: classes.dex */
public class ButtonBarLayout extends LinearLayout {

    /* renamed from: a  reason: collision with root package name */
    private boolean f2074a;

    /* renamed from: b  reason: collision with root package name */
    private boolean f2075b;

    /* renamed from: c  reason: collision with root package name */
    private int f2076c;

    public ButtonBarLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f2076c = -1;
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, g.j.N0);
        androidx.core.view.p0.L(this, context, g.j.N0, attributeSet, obtainStyledAttributes, 0, 0);
        this.f2074a = obtainStyledAttributes.getBoolean(g.j.O0, true);
        obtainStyledAttributes.recycle();
        if (getOrientation() == 1) {
            setStacked(this.f2074a);
        }
    }

    private int a(int i8) {
        int childCount = getChildCount();
        while (i8 < childCount) {
            if (getChildAt(i8).getVisibility() == 0) {
                return i8;
            }
            i8++;
        }
        return -1;
    }

    private boolean b() {
        return this.f2075b;
    }

    private void setStacked(boolean z7) {
        if (this.f2075b != z7) {
            if (!z7 || this.f2074a) {
                this.f2075b = z7;
                setOrientation(z7 ? 1 : 0);
                setGravity(z7 ? 8388613 : 80);
                View findViewById = findViewById(g.f.F);
                if (findViewById != null) {
                    findViewById.setVisibility(z7 ? 8 : 4);
                }
                for (int childCount = getChildCount() - 2; childCount >= 0; childCount--) {
                    bringChildToFront(getChildAt(childCount));
                }
            }
        }
    }

    @Override // android.widget.LinearLayout, android.view.View
    protected void onMeasure(int i8, int i9) {
        int i10;
        boolean z7;
        int size = View.MeasureSpec.getSize(i8);
        int i11 = 0;
        if (this.f2074a) {
            if (size > this.f2076c && b()) {
                setStacked(false);
            }
            this.f2076c = size;
        }
        if (b() || View.MeasureSpec.getMode(i8) != 1073741824) {
            i10 = i8;
            z7 = false;
        } else {
            i10 = View.MeasureSpec.makeMeasureSpec(size, Integer.MIN_VALUE);
            z7 = true;
        }
        super.onMeasure(i10, i9);
        if (this.f2074a && !b()) {
            if ((getMeasuredWidthAndState() & (-16777216)) == 16777216) {
                setStacked(true);
                z7 = true;
            }
        }
        if (z7) {
            super.onMeasure(i8, i9);
        }
        int a8 = a(0);
        if (a8 >= 0) {
            View childAt = getChildAt(a8);
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) childAt.getLayoutParams();
            int paddingTop = getPaddingTop() + childAt.getMeasuredHeight() + layoutParams.topMargin + layoutParams.bottomMargin + 0;
            if (b()) {
                int a9 = a(a8 + 1);
                if (a9 >= 0) {
                    paddingTop += getChildAt(a9).getPaddingTop() + ((int) (getResources().getDisplayMetrics().density * 16.0f));
                }
                i11 = paddingTop;
            } else {
                i11 = paddingTop + getPaddingBottom();
            }
        }
        if (androidx.core.view.p0.q(this) != i11) {
            setMinimumHeight(i11);
            if (i9 == 0) {
                super.onMeasure(i8, i9);
            }
        }
    }

    public void setAllowStacking(boolean z7) {
        if (this.f2074a != z7) {
            this.f2074a = z7;
            if (!z7 && b()) {
                setStacked(false);
            }
            requestLayout();
        }
    }
}
