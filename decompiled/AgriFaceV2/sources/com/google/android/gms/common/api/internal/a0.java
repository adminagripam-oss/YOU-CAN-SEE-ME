package com.google.android.gms.common.api.internal;

import android.os.DeadObjectException;
import android.os.RemoteException;
import com.google.android.gms.common.api.Status;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public abstract class a0 extends j3.r {

    /* renamed from: b  reason: collision with root package name */
    protected final f4.k f4977b;

    public a0(int i8, f4.k kVar) {
        super(i8);
        this.f4977b = kVar;
    }

    @Override // com.google.android.gms.common.api.internal.e0
    public final void a(Status status) {
        this.f4977b.d(new i3.b(status));
    }

    @Override // com.google.android.gms.common.api.internal.e0
    public final void b(Exception exc) {
        this.f4977b.d(exc);
    }

    @Override // com.google.android.gms.common.api.internal.e0
    public final void c(q qVar) {
        try {
            h(qVar);
        } catch (DeadObjectException e8) {
            a(e0.e(e8));
            throw e8;
        } catch (RemoteException e9) {
            a(e0.e(e9));
        } catch (RuntimeException e10) {
            this.f4977b.d(e10);
        }
    }

    protected abstract void h(q qVar);
}
