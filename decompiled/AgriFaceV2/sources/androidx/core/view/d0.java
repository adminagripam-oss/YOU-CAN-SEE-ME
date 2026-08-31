package androidx.core.view;

import android.view.View;
import android.view.ViewParent;
/* loaded from: classes.dex */
public class d0 {

    /* renamed from: a  reason: collision with root package name */
    private ViewParent f3129a;

    /* renamed from: b  reason: collision with root package name */
    private ViewParent f3130b;

    /* renamed from: c  reason: collision with root package name */
    private final View f3131c;

    /* renamed from: d  reason: collision with root package name */
    private boolean f3132d;

    /* renamed from: e  reason: collision with root package name */
    private int[] f3133e;

    public d0(View view) {
        this.f3131c = view;
    }

    private boolean h(int i8, int i9, int i10, int i11, int[] iArr, int i12, int[] iArr2) {
        ViewParent i13;
        int i14;
        int i15;
        int[] iArr3;
        if (!m() || (i13 = i(i12)) == null) {
            return false;
        }
        if (i8 == 0 && i9 == 0 && i10 == 0 && i11 == 0) {
            if (iArr != null) {
                iArr[0] = 0;
                iArr[1] = 0;
            }
            return false;
        }
        if (iArr != null) {
            this.f3131c.getLocationInWindow(iArr);
            i14 = iArr[0];
            i15 = iArr[1];
        } else {
            i14 = 0;
            i15 = 0;
        }
        if (iArr2 == null) {
            int[] j8 = j();
            j8[0] = 0;
            j8[1] = 0;
            iArr3 = j8;
        } else {
            iArr3 = iArr2;
        }
        v0.d(i13, this.f3131c, i8, i9, i10, i11, i12, iArr3);
        if (iArr != null) {
            this.f3131c.getLocationInWindow(iArr);
            iArr[0] = iArr[0] - i14;
            iArr[1] = iArr[1] - i15;
        }
        return true;
    }

    private ViewParent i(int i8) {
        if (i8 != 0) {
            if (i8 != 1) {
                return null;
            }
            return this.f3130b;
        }
        return this.f3129a;
    }

    private int[] j() {
        if (this.f3133e == null) {
            this.f3133e = new int[2];
        }
        return this.f3133e;
    }

    private void o(int i8, ViewParent viewParent) {
        if (i8 == 0) {
            this.f3129a = viewParent;
        } else if (i8 != 1) {
        } else {
            this.f3130b = viewParent;
        }
    }

    public boolean a(float f8, float f9, boolean z7) {
        ViewParent i8;
        if (!m() || (i8 = i(0)) == null) {
            return false;
        }
        return v0.a(i8, this.f3131c, f8, f9, z7);
    }

    public boolean b(float f8, float f9) {
        ViewParent i8;
        if (!m() || (i8 = i(0)) == null) {
            return false;
        }
        return v0.b(i8, this.f3131c, f8, f9);
    }

    public boolean c(int i8, int i9, int[] iArr, int[] iArr2) {
        return d(i8, i9, iArr, iArr2, 0);
    }

    public boolean d(int i8, int i9, int[] iArr, int[] iArr2, int i10) {
        ViewParent i11;
        int i12;
        int i13;
        if (!m() || (i11 = i(i10)) == null) {
            return false;
        }
        if (i8 == 0 && i9 == 0) {
            if (iArr2 != null) {
                iArr2[0] = 0;
                iArr2[1] = 0;
                return false;
            }
            return false;
        }
        if (iArr2 != null) {
            this.f3131c.getLocationInWindow(iArr2);
            i12 = iArr2[0];
            i13 = iArr2[1];
        } else {
            i12 = 0;
            i13 = 0;
        }
        if (iArr == null) {
            iArr = j();
        }
        iArr[0] = 0;
        iArr[1] = 0;
        v0.c(i11, this.f3131c, i8, i9, iArr, i10);
        if (iArr2 != null) {
            this.f3131c.getLocationInWindow(iArr2);
            iArr2[0] = iArr2[0] - i12;
            iArr2[1] = iArr2[1] - i13;
        }
        return (iArr[0] == 0 && iArr[1] == 0) ? false : true;
    }

    public void e(int i8, int i9, int i10, int i11, int[] iArr, int i12, int[] iArr2) {
        h(i8, i9, i10, i11, iArr, i12, iArr2);
    }

    public boolean f(int i8, int i9, int i10, int i11, int[] iArr) {
        return h(i8, i9, i10, i11, iArr, 0, null);
    }

    public boolean g(int i8, int i9, int i10, int i11, int[] iArr, int i12) {
        return h(i8, i9, i10, i11, iArr, i12, null);
    }

    public boolean k() {
        return l(0);
    }

    public boolean l(int i8) {
        return i(i8) != null;
    }

    public boolean m() {
        return this.f3132d;
    }

    public void n(boolean z7) {
        if (this.f3132d) {
            p0.a0(this.f3131c);
        }
        this.f3132d = z7;
    }

    public boolean p(int i8) {
        return q(i8, 0);
    }

    public boolean q(int i8, int i9) {
        if (l(i9)) {
            return true;
        }
        if (m()) {
            View view = this.f3131c;
            for (ViewParent parent = this.f3131c.getParent(); parent != null; parent = parent.getParent()) {
                if (v0.f(parent, view, this.f3131c, i8, i9)) {
                    o(i9, parent);
                    v0.e(parent, view, this.f3131c, i8, i9);
                    return true;
                }
                if (parent instanceof View) {
                    view = (View) parent;
                }
            }
            return false;
        }
        return false;
    }

    public void r() {
        s(0);
    }

    public void s(int i8) {
        ViewParent i9 = i(i8);
        if (i9 != null) {
            v0.g(i9, this.f3131c, i8);
            o(i8, null);
        }
    }
}
