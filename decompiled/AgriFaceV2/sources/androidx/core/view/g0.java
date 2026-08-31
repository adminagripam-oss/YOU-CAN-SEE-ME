package androidx.core.view;

import android.view.View;
import android.view.ViewGroup;
/* loaded from: classes.dex */
public class g0 {

    /* renamed from: a  reason: collision with root package name */
    private int f3134a;

    /* renamed from: b  reason: collision with root package name */
    private int f3135b;

    public g0(ViewGroup viewGroup) {
    }

    public int a() {
        return this.f3134a | this.f3135b;
    }

    public void b(View view, View view2, int i8) {
        c(view, view2, i8, 0);
    }

    public void c(View view, View view2, int i8, int i9) {
        if (i9 == 1) {
            this.f3135b = i8;
        } else {
            this.f3134a = i8;
        }
    }

    public void d(View view) {
        e(view, 0);
    }

    public void e(View view, int i8) {
        if (i8 == 1) {
            this.f3135b = 0;
        } else {
            this.f3134a = 0;
        }
    }
}
