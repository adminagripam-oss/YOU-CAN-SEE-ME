package com.google.android.gms.common.api.internal;

import android.app.Dialog;
/* loaded from: classes.dex */
final class g0 extends j3.p {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ Dialog f5031a;

    /* renamed from: b  reason: collision with root package name */
    final /* synthetic */ h0 f5032b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public g0(h0 h0Var, Dialog dialog) {
        this.f5032b = h0Var;
        this.f5031a = dialog;
    }

    @Override // j3.p
    public final void a() {
        this.f5032b.f5035f.o();
        if (this.f5031a.isShowing()) {
            this.f5031a.dismiss();
        }
    }
}
