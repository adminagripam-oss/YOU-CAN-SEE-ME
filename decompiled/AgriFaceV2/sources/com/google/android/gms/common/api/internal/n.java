package com.google.android.gms.common.api.internal;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class n implements Runnable {

    /* renamed from: e  reason: collision with root package name */
    final /* synthetic */ int f5048e;

    /* renamed from: f  reason: collision with root package name */
    final /* synthetic */ q f5049f;

    /* JADX INFO: Access modifiers changed from: package-private */
    public n(q qVar, int i8) {
        this.f5049f = qVar;
        this.f5048e = i8;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.f5049f.l(this.f5048e);
    }
}
