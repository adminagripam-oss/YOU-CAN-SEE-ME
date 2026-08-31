package com.google.android.gms.common.api.internal;

import android.os.Handler;
import com.google.android.gms.common.api.internal.a;
/* loaded from: classes.dex */
final class l implements a.InterfaceC0064a {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ b f5046a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public l(b bVar) {
        this.f5046a = bVar;
    }

    @Override // com.google.android.gms.common.api.internal.a.InterfaceC0064a
    public final void a(boolean z7) {
        Handler handler;
        Handler handler2;
        b bVar = this.f5046a;
        handler = bVar.f4995n;
        handler2 = bVar.f4995n;
        handler.sendMessage(handler2.obtainMessage(1, Boolean.valueOf(z7)));
    }
}
