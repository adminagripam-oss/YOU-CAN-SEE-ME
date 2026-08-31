package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.text.method.KeyListener;
import android.util.AttributeSet;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.widget.MultiAutoCompleteTextView;
/* loaded from: classes.dex */
public class s extends MultiAutoCompleteTextView {

    /* renamed from: d  reason: collision with root package name */
    private static final int[] f2470d = {16843126};

    /* renamed from: a  reason: collision with root package name */
    private final e f2471a;

    /* renamed from: b  reason: collision with root package name */
    private final c0 f2472b;

    /* renamed from: c  reason: collision with root package name */
    private final m f2473c;

    public s(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, g.a.f6804m);
    }

    void a(m mVar) {
        KeyListener keyListener = getKeyListener();
        if (mVar.b(keyListener)) {
            boolean isFocusable = super.isFocusable();
            boolean isClickable = super.isClickable();
            boolean isLongClickable = super.isLongClickable();
            int inputType = super.getInputType();
            KeyListener a8 = mVar.a(keyListener);
            if (a8 == keyListener) {
                return;
            }
            super.setKeyListener(a8);
            super.setRawInputType(inputType);
            super.setFocusable(isFocusable);
            super.setClickable(isClickable);
            super.setLongClickable(isLongClickable);
        }
    }

    @Override // android.widget.TextView, android.view.View
    protected void drawableStateChanged() {
        super.drawableStateChanged();
        e eVar = this.f2471a;
        if (eVar != null) {
            eVar.b();
        }
        c0 c0Var = this.f2472b;
        if (c0Var != null) {
            c0Var.b();
        }
    }

    public ColorStateList getSupportBackgroundTintList() {
        e eVar = this.f2471a;
        if (eVar != null) {
            return eVar.c();
        }
        return null;
    }

    public PorterDuff.Mode getSupportBackgroundTintMode() {
        e eVar = this.f2471a;
        if (eVar != null) {
            return eVar.d();
        }
        return null;
    }

    public ColorStateList getSupportCompoundDrawablesTintList() {
        return this.f2472b.j();
    }

    public PorterDuff.Mode getSupportCompoundDrawablesTintMode() {
        return this.f2472b.k();
    }

    @Override // android.widget.TextView, android.view.View
    public InputConnection onCreateInputConnection(EditorInfo editorInfo) {
        return this.f2473c.d(o.a(super.onCreateInputConnection(editorInfo), editorInfo, this), editorInfo);
    }

    @Override // android.view.View
    public void setBackgroundDrawable(Drawable drawable) {
        super.setBackgroundDrawable(drawable);
        e eVar = this.f2471a;
        if (eVar != null) {
            eVar.f(drawable);
        }
    }

    @Override // android.view.View
    public void setBackgroundResource(int i8) {
        super.setBackgroundResource(i8);
        e eVar = this.f2471a;
        if (eVar != null) {
            eVar.g(i8);
        }
    }

    @Override // android.widget.TextView
    public void setCompoundDrawables(Drawable drawable, Drawable drawable2, Drawable drawable3, Drawable drawable4) {
        super.setCompoundDrawables(drawable, drawable2, drawable3, drawable4);
        c0 c0Var = this.f2472b;
        if (c0Var != null) {
            c0Var.p();
        }
    }

    @Override // android.widget.TextView
    public void setCompoundDrawablesRelative(Drawable drawable, Drawable drawable2, Drawable drawable3, Drawable drawable4) {
        super.setCompoundDrawablesRelative(drawable, drawable2, drawable3, drawable4);
        c0 c0Var = this.f2472b;
        if (c0Var != null) {
            c0Var.p();
        }
    }

    @Override // android.widget.AutoCompleteTextView
    public void setDropDownBackgroundResource(int i8) {
        setDropDownBackgroundDrawable(h.a.b(getContext(), i8));
    }

    public void setEmojiCompatEnabled(boolean z7) {
        this.f2473c.e(z7);
    }

    @Override // android.widget.TextView
    public void setKeyListener(KeyListener keyListener) {
        super.setKeyListener(this.f2473c.a(keyListener));
    }

    public void setSupportBackgroundTintList(ColorStateList colorStateList) {
        e eVar = this.f2471a;
        if (eVar != null) {
            eVar.i(colorStateList);
        }
    }

    public void setSupportBackgroundTintMode(PorterDuff.Mode mode) {
        e eVar = this.f2471a;
        if (eVar != null) {
            eVar.j(mode);
        }
    }

    public void setSupportCompoundDrawablesTintList(ColorStateList colorStateList) {
        this.f2472b.w(colorStateList);
        this.f2472b.b();
    }

    public void setSupportCompoundDrawablesTintMode(PorterDuff.Mode mode) {
        this.f2472b.x(mode);
        this.f2472b.b();
    }

    @Override // android.widget.TextView
    public void setTextAppearance(Context context, int i8) {
        super.setTextAppearance(context, i8);
        c0 c0Var = this.f2472b;
        if (c0Var != null) {
            c0Var.q(context, i8);
        }
    }

    public s(Context context, AttributeSet attributeSet, int i8) {
        super(y0.b(context), attributeSet, i8);
        x0.a(this, getContext());
        b1 u7 = b1.u(getContext(), attributeSet, f2470d, i8, 0);
        if (u7.r(0)) {
            setDropDownBackgroundDrawable(u7.f(0));
        }
        u7.v();
        e eVar = new e(this);
        this.f2471a = eVar;
        eVar.e(attributeSet, i8);
        c0 c0Var = new c0(this);
        this.f2472b = c0Var;
        c0Var.m(attributeSet, i8);
        c0Var.b();
        m mVar = new m(this);
        this.f2473c = mVar;
        mVar.c(attributeSet, i8);
        a(mVar);
    }
}
