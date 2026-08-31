package androidx.appcompat.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.PopupWindow;
/* loaded from: classes.dex */
class t extends PopupWindow {

    /* renamed from: b  reason: collision with root package name */
    private static final boolean f2484b = false;

    /* renamed from: a  reason: collision with root package name */
    private boolean f2485a;

    public t(Context context, AttributeSet attributeSet, int i8, int i9) {
        super(context, attributeSet, i8, i9);
        a(context, attributeSet, i8, i9);
    }

    private void a(Context context, AttributeSet attributeSet, int i8, int i9) {
        b1 u7 = b1.u(context, attributeSet, g.j.Y1, i8, i9);
        if (u7.r(g.j.f6926a2)) {
            b(u7.a(g.j.f6926a2, false));
        }
        setBackgroundDrawable(u7.f(g.j.Z1));
        u7.v();
    }

    private void b(boolean z7) {
        if (f2484b) {
            this.f2485a = z7;
        } else {
            androidx.core.widget.h.a(this, z7);
        }
    }

    @Override // android.widget.PopupWindow
    public void showAsDropDown(View view, int i8, int i9) {
        if (f2484b && this.f2485a) {
            i9 -= view.getHeight();
        }
        super.showAsDropDown(view, i8, i9);
    }

    @Override // android.widget.PopupWindow
    public void update(View view, int i8, int i9, int i10, int i11) {
        if (f2484b && this.f2485a) {
            i9 -= view.getHeight();
        }
        super.update(view, i8, i9, i10, i11);
    }

    @Override // android.widget.PopupWindow
    public void showAsDropDown(View view, int i8, int i9, int i10) {
        if (f2484b && this.f2485a) {
            i9 -= view.getHeight();
        }
        super.showAsDropDown(view, i8, i9, i10);
    }
}
