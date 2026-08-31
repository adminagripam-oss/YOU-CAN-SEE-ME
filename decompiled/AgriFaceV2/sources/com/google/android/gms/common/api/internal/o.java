package com.google.android.gms.common.api.internal;

import i3.a;
/* loaded from: classes.dex */
final class o implements Runnable {

    /* renamed from: e  reason: collision with root package name */
    final /* synthetic */ p f5050e;

    /* JADX INFO: Access modifiers changed from: package-private */
    public o(p pVar) {
        this.f5050e = pVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        a.f fVar;
        a.f fVar2;
        q qVar = this.f5050e.f5051a;
        fVar = qVar.f5053f;
        fVar2 = qVar.f5053f;
        fVar.d(fVar2.getClass().getName().concat(" disconnecting because it was signed out."));
    }
}
