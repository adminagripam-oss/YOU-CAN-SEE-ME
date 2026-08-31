package com.google.android.gms.common.api.internal;

import android.app.Dialog;
import android.app.PendingIntent;
import com.google.android.gms.common.api.GoogleApiActivity;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class h0 implements Runnable {

    /* renamed from: e  reason: collision with root package name */
    private final f0 f5034e;

    /* renamed from: f  reason: collision with root package name */
    final /* synthetic */ i0 f5035f;

    /* JADX INFO: Access modifiers changed from: package-private */
    public h0(i0 i0Var, f0 f0Var) {
        this.f5035f = i0Var;
        this.f5034e = f0Var;
    }

    @Override // java.lang.Runnable
    public final void run() {
        if (this.f5035f.f5038b) {
            h3.a b8 = this.f5034e.b();
            if (b8.d()) {
                i0 i0Var = this.f5035f;
                i0Var.f4971a.startActivityForResult(GoogleApiActivity.a(i0Var.b(), (PendingIntent) k3.q.g(b8.c()), this.f5034e.a(), false), 1);
                return;
            }
            i0 i0Var2 = this.f5035f;
            if (i0Var2.f5041e.b(i0Var2.b(), b8.a(), null) != null) {
                i0 i0Var3 = this.f5035f;
                i0Var3.f5041e.v(i0Var3.b(), i0Var3.f4971a, b8.a(), 2, this.f5035f);
            } else if (b8.a() != 18) {
                this.f5035f.l(b8, this.f5034e.a());
            } else {
                i0 i0Var4 = this.f5035f;
                Dialog q8 = i0Var4.f5041e.q(i0Var4.b(), i0Var4);
                i0 i0Var5 = this.f5035f;
                i0Var5.f5041e.r(i0Var5.b().getApplicationContext(), new g0(this, q8));
            }
        }
    }
}
