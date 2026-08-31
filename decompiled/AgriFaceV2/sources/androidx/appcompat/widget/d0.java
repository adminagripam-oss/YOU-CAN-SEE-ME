package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.text.InputFilter;
import android.util.AttributeSet;
import android.view.ActionMode;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.view.textclassifier.TextClassifier;
import android.widget.TextView;
import androidx.core.text.k;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
/* loaded from: classes.dex */
public class d0 extends TextView {

    /* renamed from: a  reason: collision with root package name */
    private final e f2270a;

    /* renamed from: b  reason: collision with root package name */
    private final c0 f2271b;

    /* renamed from: c  reason: collision with root package name */
    private final b0 f2272c;

    /* renamed from: d  reason: collision with root package name */
    private n f2273d;

    /* renamed from: e  reason: collision with root package name */
    private boolean f2274e;

    /* renamed from: f  reason: collision with root package name */
    private a f2275f;

    /* renamed from: g  reason: collision with root package name */
    private Future f2276g;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public interface a {
        void a(int[] iArr, int i8);

        void b(TextClassifier textClassifier);

        int[] c();

        void d(int i8);

        TextClassifier e();

        int f();

        void g(int i8, int i9, int i10, int i11);

        int h();

        int i();

        void j(int i8);

        int k();

        void l(int i8);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class b implements a {
        b() {
        }

        @Override // androidx.appcompat.widget.d0.a
        public void a(int[] iArr, int i8) {
            d0.super.setAutoSizeTextTypeUniformWithPresetSizes(iArr, i8);
        }

        @Override // androidx.appcompat.widget.d0.a
        public void b(TextClassifier textClassifier) {
            d0.super.setTextClassifier(textClassifier);
        }

        @Override // androidx.appcompat.widget.d0.a
        public int[] c() {
            return d0.super.getAutoSizeTextAvailableSizes();
        }

        @Override // androidx.appcompat.widget.d0.a
        public void d(int i8) {
        }

        @Override // androidx.appcompat.widget.d0.a
        public TextClassifier e() {
            return d0.super.getTextClassifier();
        }

        @Override // androidx.appcompat.widget.d0.a
        public int f() {
            return d0.super.getAutoSizeMaxTextSize();
        }

        @Override // androidx.appcompat.widget.d0.a
        public void g(int i8, int i9, int i10, int i11) {
            d0.super.setAutoSizeTextTypeUniformWithConfiguration(i8, i9, i10, i11);
        }

        @Override // androidx.appcompat.widget.d0.a
        public int h() {
            return d0.super.getAutoSizeTextType();
        }

        @Override // androidx.appcompat.widget.d0.a
        public int i() {
            return d0.super.getAutoSizeMinTextSize();
        }

        @Override // androidx.appcompat.widget.d0.a
        public void j(int i8) {
        }

        @Override // androidx.appcompat.widget.d0.a
        public int k() {
            return d0.super.getAutoSizeStepGranularity();
        }

        @Override // androidx.appcompat.widget.d0.a
        public void l(int i8) {
            d0.super.setAutoSizeTextTypeWithDefaults(i8);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class c extends b {
        c() {
            super();
        }

        @Override // androidx.appcompat.widget.d0.b, androidx.appcompat.widget.d0.a
        public void d(int i8) {
            d0.super.setLastBaselineToBottomHeight(i8);
        }

        @Override // androidx.appcompat.widget.d0.b, androidx.appcompat.widget.d0.a
        public void j(int i8) {
            d0.super.setFirstBaselineToTopHeight(i8);
        }
    }

    public d0(Context context) {
        this(context, null);
    }

    private n getEmojiTextViewHelper() {
        if (this.f2273d == null) {
            this.f2273d = new n(this);
        }
        return this.f2273d;
    }

    private void q() {
        Future future = this.f2276g;
        if (future != null) {
            try {
                this.f2276g = null;
                androidx.appcompat.app.w.a(future.get());
                androidx.core.widget.i.k(this, null);
            } catch (InterruptedException | ExecutionException unused) {
            }
        }
    }

    @Override // android.widget.TextView, android.view.View
    protected void drawableStateChanged() {
        super.drawableStateChanged();
        e eVar = this.f2270a;
        if (eVar != null) {
            eVar.b();
        }
        c0 c0Var = this.f2271b;
        if (c0Var != null) {
            c0Var.b();
        }
    }

    @Override // android.widget.TextView
    public int getAutoSizeMaxTextSize() {
        if (i1.f2346b) {
            return getSuperCaller().f();
        }
        c0 c0Var = this.f2271b;
        if (c0Var != null) {
            return c0Var.e();
        }
        return -1;
    }

    @Override // android.widget.TextView
    public int getAutoSizeMinTextSize() {
        if (i1.f2346b) {
            return getSuperCaller().i();
        }
        c0 c0Var = this.f2271b;
        if (c0Var != null) {
            return c0Var.f();
        }
        return -1;
    }

    @Override // android.widget.TextView
    public int getAutoSizeStepGranularity() {
        if (i1.f2346b) {
            return getSuperCaller().k();
        }
        c0 c0Var = this.f2271b;
        if (c0Var != null) {
            return c0Var.g();
        }
        return -1;
    }

    @Override // android.widget.TextView
    public int[] getAutoSizeTextAvailableSizes() {
        if (i1.f2346b) {
            return getSuperCaller().c();
        }
        c0 c0Var = this.f2271b;
        return c0Var != null ? c0Var.h() : new int[0];
    }

    @Override // android.widget.TextView
    public int getAutoSizeTextType() {
        if (i1.f2346b) {
            return getSuperCaller().h() == 1 ? 1 : 0;
        }
        c0 c0Var = this.f2271b;
        if (c0Var != null) {
            return c0Var.i();
        }
        return 0;
    }

    @Override // android.widget.TextView
    public ActionMode.Callback getCustomSelectionActionModeCallback() {
        return androidx.core.widget.i.m(super.getCustomSelectionActionModeCallback());
    }

    @Override // android.widget.TextView
    public int getFirstBaselineToTopHeight() {
        return androidx.core.widget.i.a(this);
    }

    @Override // android.widget.TextView
    public int getLastBaselineToBottomHeight() {
        return androidx.core.widget.i.b(this);
    }

    a getSuperCaller() {
        if (this.f2275f == null) {
            this.f2275f = Build.VERSION.SDK_INT >= 28 ? new c() : new b();
        }
        return this.f2275f;
    }

    public ColorStateList getSupportBackgroundTintList() {
        e eVar = this.f2270a;
        if (eVar != null) {
            return eVar.c();
        }
        return null;
    }

    public PorterDuff.Mode getSupportBackgroundTintMode() {
        e eVar = this.f2270a;
        if (eVar != null) {
            return eVar.d();
        }
        return null;
    }

    public ColorStateList getSupportCompoundDrawablesTintList() {
        return this.f2271b.j();
    }

    public PorterDuff.Mode getSupportCompoundDrawablesTintMode() {
        return this.f2271b.k();
    }

    @Override // android.widget.TextView
    public CharSequence getText() {
        q();
        return super.getText();
    }

    @Override // android.widget.TextView
    public TextClassifier getTextClassifier() {
        b0 b0Var;
        return (Build.VERSION.SDK_INT >= 28 || (b0Var = this.f2272c) == null) ? getSuperCaller().e() : b0Var.a();
    }

    public k.a getTextMetricsParamsCompat() {
        return androidx.core.widget.i.e(this);
    }

    @Override // android.widget.TextView, android.view.View
    public InputConnection onCreateInputConnection(EditorInfo editorInfo) {
        InputConnection onCreateInputConnection = super.onCreateInputConnection(editorInfo);
        this.f2271b.r(this, onCreateInputConnection, editorInfo);
        return o.a(onCreateInputConnection, editorInfo, this);
    }

    @Override // android.widget.TextView, android.view.View
    protected void onLayout(boolean z7, int i8, int i9, int i10, int i11) {
        super.onLayout(z7, i8, i9, i10, i11);
        c0 c0Var = this.f2271b;
        if (c0Var != null) {
            c0Var.o(z7, i8, i9, i10, i11);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.widget.TextView, android.view.View
    public void onMeasure(int i8, int i9) {
        q();
        super.onMeasure(i8, i9);
    }

    @Override // android.widget.TextView
    protected void onTextChanged(CharSequence charSequence, int i8, int i9, int i10) {
        super.onTextChanged(charSequence, i8, i9, i10);
        c0 c0Var = this.f2271b;
        if ((c0Var == null || i1.f2346b || !c0Var.l()) ? false : true) {
            this.f2271b.c();
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
            getSuperCaller().g(i8, i9, i10, i11);
            return;
        }
        c0 c0Var = this.f2271b;
        if (c0Var != null) {
            c0Var.t(i8, i9, i10, i11);
        }
    }

    @Override // android.widget.TextView
    public void setAutoSizeTextTypeUniformWithPresetSizes(int[] iArr, int i8) {
        if (i1.f2346b) {
            getSuperCaller().a(iArr, i8);
            return;
        }
        c0 c0Var = this.f2271b;
        if (c0Var != null) {
            c0Var.u(iArr, i8);
        }
    }

    @Override // android.widget.TextView
    public void setAutoSizeTextTypeWithDefaults(int i8) {
        if (i1.f2346b) {
            getSuperCaller().l(i8);
            return;
        }
        c0 c0Var = this.f2271b;
        if (c0Var != null) {
            c0Var.v(i8);
        }
    }

    @Override // android.view.View
    public void setBackgroundDrawable(Drawable drawable) {
        super.setBackgroundDrawable(drawable);
        e eVar = this.f2270a;
        if (eVar != null) {
            eVar.f(drawable);
        }
    }

    @Override // android.view.View
    public void setBackgroundResource(int i8) {
        super.setBackgroundResource(i8);
        e eVar = this.f2270a;
        if (eVar != null) {
            eVar.g(i8);
        }
    }

    @Override // android.widget.TextView
    public void setCompoundDrawables(Drawable drawable, Drawable drawable2, Drawable drawable3, Drawable drawable4) {
        super.setCompoundDrawables(drawable, drawable2, drawable3, drawable4);
        c0 c0Var = this.f2271b;
        if (c0Var != null) {
            c0Var.p();
        }
    }

    @Override // android.widget.TextView
    public void setCompoundDrawablesRelative(Drawable drawable, Drawable drawable2, Drawable drawable3, Drawable drawable4) {
        super.setCompoundDrawablesRelative(drawable, drawable2, drawable3, drawable4);
        c0 c0Var = this.f2271b;
        if (c0Var != null) {
            c0Var.p();
        }
    }

    @Override // android.widget.TextView
    public void setCompoundDrawablesRelativeWithIntrinsicBounds(int i8, int i9, int i10, int i11) {
        Context context = getContext();
        setCompoundDrawablesRelativeWithIntrinsicBounds(i8 != 0 ? h.a.b(context, i8) : null, i9 != 0 ? h.a.b(context, i9) : null, i10 != 0 ? h.a.b(context, i10) : null, i11 != 0 ? h.a.b(context, i11) : null);
        c0 c0Var = this.f2271b;
        if (c0Var != null) {
            c0Var.p();
        }
    }

    @Override // android.widget.TextView
    public void setCompoundDrawablesWithIntrinsicBounds(int i8, int i9, int i10, int i11) {
        Context context = getContext();
        setCompoundDrawablesWithIntrinsicBounds(i8 != 0 ? h.a.b(context, i8) : null, i9 != 0 ? h.a.b(context, i9) : null, i10 != 0 ? h.a.b(context, i10) : null, i11 != 0 ? h.a.b(context, i11) : null);
        c0 c0Var = this.f2271b;
        if (c0Var != null) {
            c0Var.p();
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

    @Override // android.widget.TextView
    public void setFirstBaselineToTopHeight(int i8) {
        if (Build.VERSION.SDK_INT >= 28) {
            getSuperCaller().j(i8);
        } else {
            androidx.core.widget.i.h(this, i8);
        }
    }

    @Override // android.widget.TextView
    public void setLastBaselineToBottomHeight(int i8) {
        if (Build.VERSION.SDK_INT >= 28) {
            getSuperCaller().d(i8);
        } else {
            androidx.core.widget.i.i(this, i8);
        }
    }

    @Override // android.widget.TextView
    public void setLineHeight(int i8) {
        androidx.core.widget.i.j(this, i8);
    }

    public void setPrecomputedText(androidx.core.text.k kVar) {
        androidx.core.widget.i.k(this, kVar);
    }

    public void setSupportBackgroundTintList(ColorStateList colorStateList) {
        e eVar = this.f2270a;
        if (eVar != null) {
            eVar.i(colorStateList);
        }
    }

    public void setSupportBackgroundTintMode(PorterDuff.Mode mode) {
        e eVar = this.f2270a;
        if (eVar != null) {
            eVar.j(mode);
        }
    }

    public void setSupportCompoundDrawablesTintList(ColorStateList colorStateList) {
        this.f2271b.w(colorStateList);
        this.f2271b.b();
    }

    public void setSupportCompoundDrawablesTintMode(PorterDuff.Mode mode) {
        this.f2271b.x(mode);
        this.f2271b.b();
    }

    @Override // android.widget.TextView
    public void setTextAppearance(Context context, int i8) {
        super.setTextAppearance(context, i8);
        c0 c0Var = this.f2271b;
        if (c0Var != null) {
            c0Var.q(context, i8);
        }
    }

    @Override // android.widget.TextView
    public void setTextClassifier(TextClassifier textClassifier) {
        b0 b0Var;
        if (Build.VERSION.SDK_INT >= 28 || (b0Var = this.f2272c) == null) {
            getSuperCaller().b(textClassifier);
        } else {
            b0Var.b(textClassifier);
        }
    }

    public void setTextFuture(Future<androidx.core.text.k> future) {
        this.f2276g = future;
        if (future != null) {
            requestLayout();
        }
    }

    public void setTextMetricsParamsCompat(k.a aVar) {
        androidx.core.widget.i.l(this, aVar);
    }

    @Override // android.widget.TextView
    public void setTextSize(int i8, float f8) {
        if (i1.f2346b) {
            super.setTextSize(i8, f8);
            return;
        }
        c0 c0Var = this.f2271b;
        if (c0Var != null) {
            c0Var.A(i8, f8);
        }
    }

    @Override // android.widget.TextView
    public void setTypeface(Typeface typeface, int i8) {
        if (this.f2274e) {
            return;
        }
        Typeface a8 = (typeface == null || i8 <= 0) ? null : androidx.core.graphics.h.a(getContext(), typeface, i8);
        this.f2274e = true;
        if (a8 != null) {
            typeface = a8;
        }
        try {
            super.setTypeface(typeface, i8);
        } finally {
            this.f2274e = false;
        }
    }

    public d0(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 16842884);
    }

    @Override // android.widget.TextView
    public void setCompoundDrawablesRelativeWithIntrinsicBounds(Drawable drawable, Drawable drawable2, Drawable drawable3, Drawable drawable4) {
        super.setCompoundDrawablesRelativeWithIntrinsicBounds(drawable, drawable2, drawable3, drawable4);
        c0 c0Var = this.f2271b;
        if (c0Var != null) {
            c0Var.p();
        }
    }

    @Override // android.widget.TextView
    public void setCompoundDrawablesWithIntrinsicBounds(Drawable drawable, Drawable drawable2, Drawable drawable3, Drawable drawable4) {
        super.setCompoundDrawablesWithIntrinsicBounds(drawable, drawable2, drawable3, drawable4);
        c0 c0Var = this.f2271b;
        if (c0Var != null) {
            c0Var.p();
        }
    }

    public d0(Context context, AttributeSet attributeSet, int i8) {
        super(y0.b(context), attributeSet, i8);
        this.f2274e = false;
        this.f2275f = null;
        x0.a(this, getContext());
        e eVar = new e(this);
        this.f2270a = eVar;
        eVar.e(attributeSet, i8);
        c0 c0Var = new c0(this);
        this.f2271b = c0Var;
        c0Var.m(attributeSet, i8);
        c0Var.b();
        this.f2272c = new b0(this);
        getEmojiTextViewHelper().b(attributeSet, i8);
    }
}
