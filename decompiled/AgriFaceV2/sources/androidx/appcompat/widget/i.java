package androidx.appcompat.widget;

import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.widget.CheckedTextView;
/* loaded from: classes.dex */
class i {

    /* renamed from: a  reason: collision with root package name */
    private final CheckedTextView f2339a;

    /* renamed from: b  reason: collision with root package name */
    private ColorStateList f2340b = null;

    /* renamed from: c  reason: collision with root package name */
    private PorterDuff.Mode f2341c = null;

    /* renamed from: d  reason: collision with root package name */
    private boolean f2342d = false;

    /* renamed from: e  reason: collision with root package name */
    private boolean f2343e = false;

    /* renamed from: f  reason: collision with root package name */
    private boolean f2344f;

    /* JADX INFO: Access modifiers changed from: package-private */
    public i(CheckedTextView checkedTextView) {
        this.f2339a = checkedTextView;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a() {
        Drawable a8 = androidx.core.widget.b.a(this.f2339a);
        if (a8 != null) {
            if (this.f2342d || this.f2343e) {
                Drawable mutate = androidx.core.graphics.drawable.a.i(a8).mutate();
                if (this.f2342d) {
                    androidx.core.graphics.drawable.a.g(mutate, this.f2340b);
                }
                if (this.f2343e) {
                    androidx.core.graphics.drawable.a.h(mutate, this.f2341c);
                }
                if (mutate.isStateful()) {
                    mutate.setState(this.f2339a.getDrawableState());
                }
                this.f2339a.setCheckMarkDrawable(mutate);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ColorStateList b() {
        return this.f2340b;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public PorterDuff.Mode c() {
        return this.f2341c;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0066 A[Catch: all -> 0x008e, TryCatch #1 {all -> 0x008e, blocks: (B:3:0x001f, B:5:0x0027, B:7:0x002f, B:11:0x0041, B:13:0x0049, B:15:0x0051, B:16:0x005e, B:18:0x0066, B:19:0x0071, B:21:0x0079), top: B:30:0x001f }] */
    /* JADX WARN: Removed duplicated region for block: B:21:0x0079 A[Catch: all -> 0x008e, TRY_LEAVE, TryCatch #1 {all -> 0x008e, blocks: (B:3:0x001f, B:5:0x0027, B:7:0x002f, B:11:0x0041, B:13:0x0049, B:15:0x0051, B:16:0x005e, B:18:0x0066, B:19:0x0071, B:21:0x0079), top: B:30:0x001f }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void d(android.util.AttributeSet r11, int r12) {
        /*
            r10 = this;
            android.widget.CheckedTextView r0 = r10.f2339a
            android.content.Context r0 = r0.getContext()
            int[] r1 = g.j.P0
            r2 = 0
            androidx.appcompat.widget.b1 r0 = androidx.appcompat.widget.b1.u(r0, r11, r1, r12, r2)
            android.widget.CheckedTextView r3 = r10.f2339a
            android.content.Context r4 = r3.getContext()
            int[] r5 = g.j.P0
            android.content.res.TypedArray r7 = r0.q()
            r9 = 0
            r6 = r11
            r8 = r12
            androidx.core.view.p0.L(r3, r4, r5, r6, r7, r8, r9)
            int r11 = g.j.R0     // Catch: java.lang.Throwable -> L8e
            boolean r11 = r0.r(r11)     // Catch: java.lang.Throwable -> L8e
            if (r11 == 0) goto L3e
            int r11 = g.j.R0     // Catch: java.lang.Throwable -> L8e
            int r11 = r0.m(r11, r2)     // Catch: java.lang.Throwable -> L8e
            if (r11 == 0) goto L3e
            android.widget.CheckedTextView r12 = r10.f2339a     // Catch: android.content.res.Resources.NotFoundException -> L3e java.lang.Throwable -> L8e
            android.content.Context r1 = r12.getContext()     // Catch: android.content.res.Resources.NotFoundException -> L3e java.lang.Throwable -> L8e
            android.graphics.drawable.Drawable r11 = h.a.b(r1, r11)     // Catch: android.content.res.Resources.NotFoundException -> L3e java.lang.Throwable -> L8e
            r12.setCheckMarkDrawable(r11)     // Catch: android.content.res.Resources.NotFoundException -> L3e java.lang.Throwable -> L8e
            r11 = 1
            goto L3f
        L3e:
            r11 = r2
        L3f:
            if (r11 != 0) goto L5e
            int r11 = g.j.Q0     // Catch: java.lang.Throwable -> L8e
            boolean r11 = r0.r(r11)     // Catch: java.lang.Throwable -> L8e
            if (r11 == 0) goto L5e
            int r11 = g.j.Q0     // Catch: java.lang.Throwable -> L8e
            int r11 = r0.m(r11, r2)     // Catch: java.lang.Throwable -> L8e
            if (r11 == 0) goto L5e
            android.widget.CheckedTextView r12 = r10.f2339a     // Catch: java.lang.Throwable -> L8e
            android.content.Context r1 = r12.getContext()     // Catch: java.lang.Throwable -> L8e
            android.graphics.drawable.Drawable r11 = h.a.b(r1, r11)     // Catch: java.lang.Throwable -> L8e
            r12.setCheckMarkDrawable(r11)     // Catch: java.lang.Throwable -> L8e
        L5e:
            int r11 = g.j.S0     // Catch: java.lang.Throwable -> L8e
            boolean r11 = r0.r(r11)     // Catch: java.lang.Throwable -> L8e
            if (r11 == 0) goto L71
            android.widget.CheckedTextView r11 = r10.f2339a     // Catch: java.lang.Throwable -> L8e
            int r12 = g.j.S0     // Catch: java.lang.Throwable -> L8e
            android.content.res.ColorStateList r12 = r0.c(r12)     // Catch: java.lang.Throwable -> L8e
            androidx.core.widget.b.b(r11, r12)     // Catch: java.lang.Throwable -> L8e
        L71:
            int r11 = g.j.T0     // Catch: java.lang.Throwable -> L8e
            boolean r11 = r0.r(r11)     // Catch: java.lang.Throwable -> L8e
            if (r11 == 0) goto L8a
            android.widget.CheckedTextView r11 = r10.f2339a     // Catch: java.lang.Throwable -> L8e
            int r12 = g.j.T0     // Catch: java.lang.Throwable -> L8e
            r1 = -1
            int r12 = r0.j(r12, r1)     // Catch: java.lang.Throwable -> L8e
            r1 = 0
            android.graphics.PorterDuff$Mode r12 = androidx.appcompat.widget.k0.d(r12, r1)     // Catch: java.lang.Throwable -> L8e
            androidx.core.widget.b.c(r11, r12)     // Catch: java.lang.Throwable -> L8e
        L8a:
            r0.v()
            return
        L8e:
            r11 = move-exception
            r0.v()
            throw r11
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.widget.i.d(android.util.AttributeSet, int):void");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void e() {
        if (this.f2344f) {
            this.f2344f = false;
            return;
        }
        this.f2344f = true;
        a();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void f(ColorStateList colorStateList) {
        this.f2340b = colorStateList;
        this.f2342d = true;
        a();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void g(PorterDuff.Mode mode) {
        this.f2341c = mode;
        this.f2343e = true;
        a();
    }
}
