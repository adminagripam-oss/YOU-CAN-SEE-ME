package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.text.InputFilter;
import android.util.AttributeSet;
import android.view.ActionMode;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.Button;
/* loaded from: classes.dex */
public class f extends Button {

    /* renamed from: a  reason: collision with root package name */
    private final e f2301a;

    /* renamed from: b  reason: collision with root package name */
    private final c0 f2302b;

    /* renamed from: c  reason: collision with root package name */
    private n f2303c;

    public f(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, g.a.f6805n);
    }

    private n getEmojiTextViewHelper() {
        if (this.f2303c == null) {
            this.f2303c = new n(this);
        }
        return this.f2303c;
    }

    @Override // android.widget.TextView, android.view.View
    protected void drawableStateChanged() {
        super.drawableStateChanged();
        e eVar = this.f2301a;
        if (eVar != null) {
            eVar.b();
        }
        c0 c0Var = this.f2302b;
        if (c0Var != null) {
            c0Var.b();
        }
    }

    @Override // android.widget.TextView
    public int getAutoSizeMaxTextSize() {
        if (i1.f2346b) {
            return super.getAutoSizeMaxTextSize();
        }
        c0 c0Var = this.f2302b;
        if (c0Var != null) {
            return c0Var.e();
        }
        return -1;
    }

    @Override // android.widget.TextView
    public int getAutoSizeMinTextSize() {
        if (i1.f2346b) {
            return super.getAutoSizeMinTextSize();
        }
        c0 c0Var = this.f2302b;
        if (c0Var != null) {
            return c0Var.f();
        }
        return -1;
    }

    @Override // android.widget.TextView
    public int getAutoSizeStepGranularity() {
        if (i1.f2346b) {
            return super.getAutoSizeStepGranularity();
        }
        c0 c0Var = this.f2302b;
        if (c0Var != null) {
            return c0Var.g();
        }
        return -1;
    }

    @Override // android.widget.TextView
    public int[] getAutoSizeTextAvailableSizes() {
        if (i1.f2346b) {
            return super.getAutoSizeTextAvailableSizes();
        }
        c0 c0Var = this.f2302b;
        return c0Var != null ? c0Var.h() : new int[0];
    }

    @Override // android.widget.TextView
    public int getAutoSizeTextType() {
        if (i1.f2346b) {
            return super.getAutoSizeTextType() == 1 ? 1 : 0;
        }
        c0 c0Var = this.f2302b;
        if (c0Var != null) {
            return c0Var.i();
        }
        return 0;
    }

    @Override // android.widget.TextView
    public ActionMode.Callback getCustomSelectionActionModeCallback() {
        return androidx.core.widget.i.m(super.getCustomSelectionActionModeCallback());
    }

    public ColorStateList getSupportBackgroundTintList() {
        e eVar = this.f2301a;
        if (eVar != null) {
            return eVar.c();
        }
        return null;
    }

    public PorterDuff.Mode getSupportBackgroundTintMode() {
        e eVar = this.f2301a;
        if (eVar != null) {
            return eVar.d();
        }
        return null;
    }

    public ColorStateList getSupportCompoundDrawablesTintList() {
        return this.f2302b.j();
    }

    public PorterDuff.Mode getSupportCompoundDrawablesTintMode() {
        return this.f2302b.k();
    }

    @Override // android.view.View
    public void onInitializeAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        super.onInitializeAccessibilityEvent(accessibilityEvent);
        accessibilityEvent.setClassName(Button.class.getName());
    }

    @Override // android.view.View
    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        accessibilityNodeInfo.setClassName(Button.class.getName());
    }

    @Override // android.widget.TextView, android.view.View
    protected void onLayout(boolean z7, int i8, int i9, int i10, int i11) {
        super.onLayout(z7, i8, i9, i10, i11);
        c0 c0Var = this.f2302b;
        if (c0Var != null) {
            c0Var.o(z7, i8, i9, i10, i11);
        }
    }

    @Override // android.widget.TextView
    protected void onTextChanged(CharSequence charSequence, int i8, int i9, int i10) {
        super.onTextChanged(charSequence, i8, i9, i10);
        c0 c0Var = this.f2302b;
        if ((c0Var == null || i1.f2346b || !c0Var.l()) ? false : true) {
            this.f2302b.c();
        }
    }

    @Override // android.widget.TextView
    public void setAllCaps(boolean z7) {
        super.setAllCaps(z7);
        getEmojiTextViewHelper().c(z7);
    }

    @Override // android.widget.TextView
    public void setAutoSizeTextTypeUniformWithConfiguration(int i8, int i9, int i10, int i11) {
        if (i1.f2346b) {
            super.setAutoSizeTextTypeUniformWithConfiguration(i8, i9, i10, i11);
            return;
        }
        c0 c0Var = this.f2302b;
        if (c0Var != null) {
            c0Var.t(i8, i9, i10, i11);
        }
    }

    @Override // android.widget.TextView
    public void setAutoSizeTextTypeUniformWithPresetSizes(int[] iArr, int i8) {
        if (i1.f2346b) {
            super.setAutoSizeTextTypeUniformWithPresetSizes(iArr, i8);
            return;
        }
        c0 c0Var = this.f2302b;
        if (c0Var != null) {
            c0Var.u(iArr, i8);
        }
    }

    @Override // android.widget.TextView
    public void setAutoSizeTextTypeWithDefaults(int i8) {
        if (i1.f2346b) {
            super.setAutoSizeTextTypeWithDefaults(i8);
            return;
        }
        c0 c0Var = this.f2302b;
        if (c0Var != null) {
            c0Var.v(i8);
        }
    }

    @Override // android.view.View
    public void setBackgroundDrawable(Drawable drawable) {
        super.setBackgroundDrawable(drawable);
        e eVar = this.f2301a;
        if (eVar != null) {
            eVar.f(drawable);
        }
    }

    @Override // android.view.View
    public void setBackgroundResource(int i8) {
        super.setBackgroundResource(i8);
        e eVar = this.f2301a;
        if (eVar != null) {
            eVar.g(i8);
        }
    }

    @Override // android.widget.TextView
    public void setCustomSelectionActionModeCallback(ActionMode.Callback callback) {
        super.setCustomSelectionActionModeCallback(androidx.core.widget.i.n(this, callback));
    }

    public void setEmojiCompatEnabled(boolean z7) {
        getEmojiTextViewHelper().d(z7);
    }

    @Override // android.widget.TextView
    public void setFilters(InputFilter[] inputFilterArr) {
        super.setFilters(getEmojiTextViewHelper().a(inputFilterArr));
    }

    public void setSupportAllCaps(boolean z7) {
        c0 c0Var = this.f2302b;
        if (c0Var != null) {
            c0Var.s(z7);
        }
    }

    public void setSupportBackgroundTintList(ColorStateList colorStateList) {
        e eVar = this.f2301a;
        if (eVar != null) {
            eVar.i(colorStateList);
        }
    }

    public void setSupportBackgroundTintMode(PorterDuff.Mode mode) {
        e eVar = this.f2301a;
        if (eVar != null) {
            eVar.j(mode);
        }
    }

    public void setSupportCompoundDrawablesTintList(ColorStateList colorStateList) {
        this.f2302b.w(colorStateList);
        this.f2302b.b();
    }

    public void setSupportCompoundDrawablesTintMode(PorterDuff.Mode mode) {
        this.f2302b.x(mode);
        this.f2302b.b();
    }

    @Override // android.widget.TextView
    public void setTextAppearance(Context context, int i8) {
        super.setTextAppearance(context, i8);
        c0 c0Var = this.f2302b;
        if (c0Var != null) {
            c0Var.q(context, i8);
        }
    }

    @Override // android.widget.TextView
    public void setTextSize(int i8, float f8) {
        if (i1.f2346b) {
            super.setTextSize(i8, f8);
            return;
        }
        c0 c0Var = this.f2302b;
        if (c0Var != null) {
            c0Var.A(i8, f8);
        }
    }

    public f(Context context, AttributeSet attributeSet, int i8) {
        super(y0.b(context), attributeSet, i8);
        x0.a(this, getContext());
        e eVar = new e(this);
        this.f2301a = eVar;
        eVar.e(attributeSet, i8);
        c0 c0Var = new c0(this);
        this.f2302b = c0Var;
        c0Var.m(attributeSet, i8);
        c0Var.b();
        getEmojiTextViewHelper().b(attributeSet, i8);
    }
}
