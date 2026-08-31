package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.text.Editable;
import android.text.method.KeyListener;
import android.util.AttributeSet;
import android.view.ActionMode;
import android.view.DragEvent;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.view.textclassifier.TextClassifier;
import android.widget.EditText;
/* loaded from: classes.dex */
public class l extends EditText implements androidx.core.view.j0 {

    /* renamed from: a  reason: collision with root package name */
    private final e f2365a;

    /* renamed from: b  reason: collision with root package name */
    private final c0 f2366b;

    /* renamed from: c  reason: collision with root package name */
    private final b0 f2367c;

    /* renamed from: d  reason: collision with root package name */
    private final androidx.core.widget.j f2368d;

    /* renamed from: e  reason: collision with root package name */
    private final m f2369e;

    /* renamed from: f  reason: collision with root package name */
    private a f2370f;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class a {
        a() {
        }

        public TextClassifier a() {
            return l.super.getTextClassifier();
        }

        public void b(TextClassifier textClassifier) {
            l.super.setTextClassifier(textClassifier);
        }
    }

    public l(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, g.a.f6816y);
    }

    private a getSuperCaller() {
        if (this.f2370f == null) {
            this.f2370f = new a();
        }
        return this.f2370f;
    }

    @Override // androidx.core.view.j0
    public androidx.core.view.d a(androidx.core.view.d dVar) {
        return this.f2368d.a(this, dVar);
    }

    void d(m mVar) {
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
        e eVar = this.f2365a;
        if (eVar != null) {
            eVar.b();
        }
        c0 c0Var = this.f2366b;
        if (c0Var != null) {
            c0Var.b();
        }
    }

    @Override // android.widget.TextView
    public ActionMode.Callback getCustomSelectionActionModeCallback() {
        return androidx.core.widget.i.m(super.getCustomSelectionActionModeCallback());
    }

    public ColorStateList getSupportBackgroundTintList() {
        e eVar = this.f2365a;
        if (eVar != null) {
            return eVar.c();
        }
        return null;
    }

    public PorterDuff.Mode getSupportBackgroundTintMode() {
        e eVar = this.f2365a;
        if (eVar != null) {
            return eVar.d();
        }
        return null;
    }

    public ColorStateList getSupportCompoundDrawablesTintList() {
        return this.f2366b.j();
    }

    public PorterDuff.Mode getSupportCompoundDrawablesTintMode() {
        return this.f2366b.k();
    }

    @Override // android.widget.EditText, android.widget.TextView
    public Editable getText() {
        return Build.VERSION.SDK_INT >= 28 ? super.getText() : super.getEditableText();
    }

    @Override // android.widget.TextView
    public TextClassifier getTextClassifier() {
        b0 b0Var;
        return (Build.VERSION.SDK_INT >= 28 || (b0Var = this.f2367c) == null) ? getSuperCaller().a() : b0Var.a();
    }

    @Override // android.widget.TextView, android.view.View
    public InputConnection onCreateInputConnection(EditorInfo editorInfo) {
        String[] r8;
        InputConnection onCreateInputConnection = super.onCreateInputConnection(editorInfo);
        this.f2366b.r(this, onCreateInputConnection, editorInfo);
        InputConnection a8 = o.a(onCreateInputConnection, editorInfo, this);
        if (a8 != null && Build.VERSION.SDK_INT <= 30 && (r8 = androidx.core.view.p0.r(this)) != null) {
            k1.a.c(editorInfo, r8);
            a8 = k1.c.c(this, a8, editorInfo);
        }
        return this.f2369e.d(a8, editorInfo);
    }

    @Override // android.widget.TextView, android.view.View
    public boolean onDragEvent(DragEvent dragEvent) {
        if (x.a(this, dragEvent)) {
            return true;
        }
        return super.onDragEvent(dragEvent);
    }

    @Override // android.widget.TextView
    public boolean onTextContextMenuItem(int i8) {
        if (x.b(this, i8)) {
            return true;
        }
        return super.onTextContextMenuItem(i8);
    }

    @Override // android.view.View
    public void setBackgroundDrawable(Drawable drawable) {
        super.setBackgroundDrawable(drawable);
        e eVar = this.f2365a;
        if (eVar != null) {
            eVar.f(drawable);
        }
    }

    @Override // android.view.View
    public void setBackgroundResource(int i8) {
        super.setBackgroundResource(i8);
        e eVar = this.f2365a;
        if (eVar != null) {
            eVar.g(i8);
        }
    }

    @Override // android.widget.TextView
    public void setCompoundDrawables(Drawable drawable, Drawable drawable2, Drawable drawable3, Drawable drawable4) {
        super.setCompoundDrawables(drawable, drawable2, drawable3, drawable4);
        c0 c0Var = this.f2366b;
        if (c0Var != null) {
            c0Var.p();
        }
    }

    @Override // android.widget.TextView
    public void setCompoundDrawablesRelative(Drawable drawable, Drawable drawable2, Drawable drawable3, Drawable drawable4) {
        super.setCompoundDrawablesRelative(drawable, drawable2, drawable3, drawable4);
        c0 c0Var = this.f2366b;
        if (c0Var != null) {
            c0Var.p();
        }
    }

    @Override // android.widget.TextView
    public void setCustomSelectionActionModeCallback(ActionMode.Callback callback) {
        super.setCustomSelectionActionModeCallback(androidx.core.widget.i.n(this, callback));
    }

    public void setEmojiCompatEnabled(boolean z7) {
        this.f2369e.e(z7);
    }

    @Override // android.widget.TextView
    public void setKeyListener(KeyListener keyListener) {
        super.setKeyListener(this.f2369e.a(keyListener));
    }

    public void setSupportBackgroundTintList(ColorStateList colorStateList) {
        e eVar = this.f2365a;
        if (eVar != null) {
            eVar.i(colorStateList);
        }
    }

    public void setSupportBackgroundTintMode(PorterDuff.Mode mode) {
        e eVar = this.f2365a;
        if (eVar != null) {
            eVar.j(mode);
        }
    }

    public void setSupportCompoundDrawablesTintList(ColorStateList colorStateList) {
        this.f2366b.w(colorStateList);
        this.f2366b.b();
    }

    public void setSupportCompoundDrawablesTintMode(PorterDuff.Mode mode) {
        this.f2366b.x(mode);
        this.f2366b.b();
    }

    @Override // android.widget.TextView
    public void setTextAppearance(Context context, int i8) {
        super.setTextAppearance(context, i8);
        c0 c0Var = this.f2366b;
        if (c0Var != null) {
            c0Var.q(context, i8);
        }
    }

    @Override // android.widget.TextView
    public void setTextClassifier(TextClassifier textClassifier) {
        b0 b0Var;
        if (Build.VERSION.SDK_INT >= 28 || (b0Var = this.f2367c) == null) {
            getSuperCaller().b(textClassifier);
        } else {
            b0Var.b(textClassifier);
        }
    }

    public l(Context context, AttributeSet attributeSet, int i8) {
        super(y0.b(context), attributeSet, i8);
        x0.a(this, getContext());
        e eVar = new e(this);
        this.f2365a = eVar;
        eVar.e(attributeSet, i8);
        c0 c0Var = new c0(this);
        this.f2366b = c0Var;
        c0Var.m(attributeSet, i8);
        c0Var.b();
        this.f2367c = new b0(this);
        this.f2368d = new androidx.core.widget.j();
        m mVar = new m(this);
        this.f2369e = mVar;
        mVar.c(attributeSet, i8);
        d(mVar);
    }
}
