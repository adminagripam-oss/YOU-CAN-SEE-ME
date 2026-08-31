package com.google.android.gms.common.api.internal;

import android.os.Handler;
import k3.c;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class p implements c.e {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ q f5051a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public p(q qVar) {
        this.f5051a = qVar;
    }

    @Override // k3.c.e
    public final void a() {
        Handler handler;
        handler = this.f5051a.f5064q.f4995n;
        handler.post(new o(this));
    }
}
