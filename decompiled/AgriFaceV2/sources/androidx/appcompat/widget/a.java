package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.ContextThemeWrapper;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public abstract class a extends ViewGroup {

    /* renamed from: a  reason: collision with root package name */
    protected final C0009a f2177a;

    /* renamed from: b  reason: collision with root package name */
    protected final Context f2178b;

    /* renamed from: c  reason: collision with root package name */
    protected ActionMenuView f2179c;

    /* renamed from: d  reason: collision with root package name */
    protected c f2180d;

    /* renamed from: e  reason: collision with root package name */
    protected int f2181e;

    /* renamed from: f  reason: collision with root package name */
    protected androidx.core.view.x0 f2182f;

    /* renamed from: g  reason: collision with root package name */
    private boolean f2183g;

    /* renamed from: h  reason: collision with root package name */
    private boolean f2184h;

    /* renamed from: androidx.appcompat.widget.a$a  reason: collision with other inner class name */
    /* loaded from: classes.dex */
    protected class C0009a implements androidx.core.view.y0 {

        /* renamed from: a  reason: collision with root package name */
        private boolean f2185a = false;

        /* renamed from: b  reason: collision with root package name */
        int f2186b;

        protected C0009a() {
        }

        @Override // androidx.core.view.y0
        public void a(View view) {
            this.f2185a = true;
        }

        @Override // androidx.core.view.y0
        public void b(View view) {
            if (this.f2185a) {
                return;
            }
            a aVar = a.this;
            aVar.f2182f = null;
            a.super.setVisibility(this.f2186b);
        }

        @Override // androidx.core.view.y0
        public void c(View view) {
            a.super.setVisibility(0);
            this.f2185a = false;
        }

        public C0009a d(androidx.core.view.x0 x0Var, int i8) {
            a.this.f2182f = x0Var;
            this.f2186b = i8;
            return this;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public a(Context context, AttributeSet attributeSet, int i8) {
        super(context, attributeSet, i8);
        this.f2177a = new C0009a();
        TypedValue typedValue = new TypedValue();
        if (!context.getTheme().resolveAttribute(g.a.f6792a, typedValue, true) || typedValue.resourceId == 0) {
            this.f2178b = context;
        } else {
            this.f2178b = new ContextThemeWrapper(context, typedValue.resourceId);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static int d(int i8, int i9, boolean z7) {
        return z7 ? i8 - i9 : i8 + i9;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public int c(View view, int i8, int i9, int i10) {
        view.measure(View.MeasureSpec.makeMeasureSpec(i8, Integer.MIN_VALUE), i9);
        return Math.max(0, (i8 - view.getMeasuredWidth()) - i10);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public int e(View view, int i8, int i9, int i10, boolean z7) {
        int measuredWidth = view.getMeasuredWidth();
        int measuredHeight = view.getMeasuredHeight();
        int i11 = i9 + ((i10 - measuredHeight) / 2);
        if (z7) {
            view.layout(i8 - measuredWidth, i11, i8, measuredHeight + i11);
        } else {
            view.layout(i8, i11, i8 + measuredWidth, measuredHeight + i11);
        }
        return z7 ? -measuredWidth : measuredWidth;
    }

    public androidx.core.view.x0 f(int i8, long j8) {
        androidx.core.view.x0 b8;
        androidx.core.view.x0 x0Var = this.f2182f;
        if (x0Var != null) {
            x0Var.c();
        }
        if (i8 == 0) {
            if (getVisibility() != 0) {
                setAlpha(0.0f);
            }
            b8 = androidx.core.view.p0.c(this).b(1.0f);
        } else {
            b8 = androidx.core.view.p0.c(this).b(0.0f);
        }
        b8.f(j8);
        b8.h(this.f2177a.d(b8, i8));
        return b8;
    }

    public int getAnimatedVisibility() {
        return this.f2182f != null ? this.f2177a.f2186b : getVisibility();
    }

    public int getContentHeight() {
        return this.f2181e;
    }

    @Override // android.view.View
    protected void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        TypedArray obtainStyledAttributes = getContext().obtainStyledAttributes(null, g.j.f6923a, g.a.f6794c, 0);
        setContentHeight(obtainStyledAttributes.getLayoutDimension(g.j.f6968j, 0));
        obtainStyledAttributes.recycle();
        c cVar = this.f2180d;
        if (cVar != null) {
            cVar.F(configuration);
        }
    }

    @Override // android.view.View
    public boolean onHoverEvent(MotionEvent motionEvent) {
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 9) {
            this.f2184h = false;
        }
        if (!this.f2184h) {
            boolean onHoverEvent = super.onHoverEvent(motionEvent);
            if (actionMasked == 9 && !onHoverEvent) {
                this.f2184h = true;
            }
        }
        if (actionMasked == 10 || actionMasked == 3) {
            this.f2184h = false;
        }
        return true;
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 0) {
            this.f2183g = false;
        }
        if (!this.f2183g) {
            boolean onTouchEvent = super.onTouchEvent(motionEvent);
            if (actionMasked == 0 && !onTouchEvent) {
                this.f2183g = true;
            }
        }
        if (actionMasked == 1 || actionMasked == 3) {
            this.f2183g = false;
        }
        return true;
    }

    public abstract void setContentHeight(int i8);

    @Override // android.view.View
    public void setVisibility(int i8) {
        if (i8 != getVisibility()) {
            androidx.core.view.x0 x0Var = this.f2182f;
            if (x0Var != null) {
                x0Var.c();
            }
            super.setVisibility(i8);
        }
    }
}
