package androidx.appcompat.widget;

import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.widget.CompoundButton;
/* loaded from: classes.dex */
class j {

    /* renamed from: a  reason: collision with root package name */
    private final CompoundButton f2347a;

    /* renamed from: b  reason: collision with root package name */
    private ColorStateList f2348b = null;

    /* renamed from: c  reason: collision with root package name */
    private PorterDuff.Mode f2349c = null;

    /* renamed from: d  reason: collision with root package name */
    private boolean f2350d = false;

    /* renamed from: e  reason: collision with root package name */
    private boolean f2351e = false;

    /* renamed from: f  reason: collision with root package name */
    private boolean f2352f;

    /* JADX INFO: Access modifiers changed from: package-private */
    public j(CompoundButton compoundButton) {
        this.f2347a = compoundButton;
    }

    void a() {
        Drawable a8 = androidx.core.widget.c.a(this.f2347a);
        if (a8 != null) {
            if (this.f2350d || this.f2351e) {
                Drawable mutate = androidx.core.graphics.drawable.a.i(a8).mutate();
                if (this.f2350d) {
                    androidx.core.graphics.drawable.a.g(mutate, this.f2348b);
                }
                if (this.f2351e) {
                    androidx.core.graphics.drawable.a.h(mutate, this.f2349c);
                }
                if (mutate.isStateful()) {
                    mutate.setState(this.f2347a.getDrawableState());
                }
                this.f2347a.setButtonDrawable(mutate);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int b(int i8) {
        return i8;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ColorStateList c() {
        return this.f2348b;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public PorterDuff.Mode d() {
        return this.f2349c;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0066 A[Catch: all -> 0x008e, TryCatch #1 {all -> 0x008e, blocks: (B:3:0x001f, B:5:0x0027, B:7:0x002f, B:11:0x0041, B:13:0x0049, B:15:0x0051, B:16:0x005e, B:18:0x0066, B:19:0x0071, B:21:0x0079), top: B:30:0x001f }] */
    /* JADX WARN: Removed duplicated region for block: B:21:0x0079 A[Catch: all -> 0x008e, TRY_LEAVE, TryCatch #1 {all -> 0x008e, blocks: (B:3:0x001f, B:5:0x0027, B:7:0x002f, B:11:0x0041, B:13:0x0049, B:15:0x0051, B:16:0x005e, B:18:0x0066, B:19:0x0071, B:21:0x0079), top: B:30:0x001f }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void e(android.util.AttributeSet r11, int r12) {
        /*
            r10 = this;
            android.widget.CompoundButton r0 = r10.f2347a
            android.content.Context r0 = r0.getContext()
            int[] r1 = g.j.U0
            r2 = 0
            androidx.appcompat.widget.b1 r0 = androidx.appcompat.widget.b1.u(r0, r11, r1, r12, r2)
            android.widget.CompoundButton r3 = r10.f2347a
            android.content.Context r4 = r3.getContext()
            int[] r5 = g.j.U0
            android.content.res.TypedArray r7 = r0.q()
            r9 = 0
            r6 = r11
            r8 = r12
            androidx.core.view.p0.L(r3, r4, r5, r6, r7, r8, r9)
            int r11 = g.j.W0     // Catch: java.lang.Throwable -> L8e
            boolean r11 = r0.r(r11)     // Catch: java.lang.Throwable -> L8e
            if (r11 == 0) goto L3e
            int r11 = g.j.W0     // Catch: java.lang.Throwable -> L8e
            int r11 = r0.m(r11, r2)     // Catch: java.lang.Throwable -> L8e
            if (r11 == 0) goto L3e
            android.widget.CompoundButton r12 = r10.f2347a     // Catch: android.content.res.Resources.NotFoundException -> L3e java.lang.Throwable -> L8e
            android.content.Context r1 = r12.getContext()     // Catch: android.content.res.Resources.NotFoundException -> L3e java.lang.Throwable -> L8e
            android.graphics.drawable.Drawable r11 = h.a.b(r1, r11)     // Catch: android.content.res.Resources.NotFoundException -> L3e java.lang.Throwable -> L8e
            r12.setButtonDrawable(r11)     // Catch: android.content.res.Resources.NotFoundException -> L3e java.lang.Throwable -> L8e
            r11 = 1
            goto L3f
        L3e:
            r11 = r2
        L3f:
            if (r11 != 0) goto L5e
            int r11 = g.j.V0     // Catch: java.lang.Throwable -> L8e
            boolean r11 = r0.r(r11)     // Catch: java.lang.Throwable -> L8e
            if (r11 == 0) goto L5e
            int r11 = g.j.V0     // Catch: java.lang.Throwable -> L8e
            int r11 = r0.m(r11, r2)     // Catch: java.lang.Throwable -> L8e
            if (r11 == 0) goto L5e
            android.widget.CompoundButton r12 = r10.f2347a     // Catch: java.lang.Throwable -> L8e
            android.content.Context r1 = r12.getContext()     // Catch: java.lang.Throwable -> L8e
            android.graphics.drawable.Drawable r11 = h.a.b(r1, r11)     // Catch: java.lang.Throwable -> L8e
            r12.setButtonDrawable(r11)     // Catch: java.lang.Throwable -> L8e
        L5e:
            int r11 = g.j.X0     // Catch: java.lang.Throwable -> L8e
            boolean r11 = r0.r(r11)     // Catch: java.lang.Throwable -> L8e
            if (r11 == 0) goto L71
            android.widget.CompoundButton r11 = r10.f2347a     // Catch: java.lang.Throwable -> L8e
            int r12 = g.j.X0     // Catch: java.lang.Throwable -> L8e
            android.content.res.ColorStateList r12 = r0.c(r12)     // Catch: java.lang.Throwable -> L8e
            androidx.core.widget.c.b(r11, r12)     // Catch: java.lang.Throwable -> L8e
        L71:
            int r11 = g.j.Y0     // Catch: java.lang.Throwable -> L8e
            boolean r11 = r0.r(r11)     // Catch: java.lang.Throwable -> L8e
            if (r11 == 0) goto L8a
            android.widget.CompoundButton r11 = r10.f2347a     // Catch: java.lang.Throwable -> L8e
            int r12 = g.j.Y0     // Catch: java.lang.Throwable -> L8e
            r1 = -1
            int r12 = r0.j(r12, r1)     // Catch: java.lang.Throwable -> L8e
            r1 = 0
            android.graphics.PorterDuff$Mode r12 = androidx.appcompat.widget.k0.d(r12, r1)     // Catch: java.lang.Throwable -> L8e
            androidx.core.widget.c.c(r11, r12)     // Catch: java.lang.Throwable -> L8e
        L8a:
            r0.v()
            return
        L8e:
            r11 = move-exception
            r0.v()
            throw r11
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.widget.j.e(android.util.AttributeSet, int):void");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void f() {
        if (this.f2352f) {
            this.f2352f = false;
            return;
        }
        this.f2352f = true;
        a();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void g(ColorStateList colorStateList) {
        this.f2348b = colorStateList;
        this.f2350d = true;
        a();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void h(PorterDuff.Mode mode) {
        this.f2349c = mode;
        this.f2351e = true;
        a();
    }
}
