package androidx.appcompat.app;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.ViewGroup;
import androidx.appcompat.view.b;
/* loaded from: classes.dex */
public abstract class a {

    /* renamed from: androidx.appcompat.app.a$a  reason: collision with other inner class name */
    /* loaded from: classes.dex */
    public static class C0006a extends ViewGroup.MarginLayoutParams {

        /* renamed from: a  reason: collision with root package name */
        public int f1539a;

        public C0006a(int i8, int i9) {
            super(i8, i9);
            this.f1539a = 8388627;
        }

        public C0006a(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            this.f1539a = 0;
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, g.j.f7018t);
            this.f1539a = obtainStyledAttributes.getInt(g.j.f7023u, 0);
            obtainStyledAttributes.recycle();
        }

        public C0006a(ViewGroup.LayoutParams layoutParams) {
            super(layoutParams);
            this.f1539a = 0;
        }

        public C0006a(C0006a c0006a) {
            super((ViewGroup.MarginLayoutParams) c0006a);
            this.f1539a = 0;
            this.f1539a = c0006a.f1539a;
        }
    }

    public androidx.appcompat.view.b A(b.a aVar) {
        return null;
    }

    public boolean g() {
        return false;
    }

    public abstract boolean h();

    public abstract void i(boolean z7);

    public abstract int j();

    public abstract Context k();

    public abstract void l();

    public boolean m() {
        return false;
    }

    public abstract boolean n();

    public void o(Configuration configuration) {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void p() {
    }

    public abstract boolean q(int i8, KeyEvent keyEvent);

    public boolean r(KeyEvent keyEvent) {
        return false;
    }

    public boolean s() {
        return false;
    }

    public abstract void t(Drawable drawable);

    public abstract void u(boolean z7);

    public abstract void v(boolean z7);

    public abstract void w(boolean z7);

    public abstract void x(CharSequence charSequence);

    public abstract void y(CharSequence charSequence);

    public abstract void z();
}
