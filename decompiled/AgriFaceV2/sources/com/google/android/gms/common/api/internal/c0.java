package com.google.android.gms.common.api.internal;

import android.os.DeadObjectException;
import android.os.RemoteException;
import com.google.android.gms.common.api.Status;
/* loaded from: classes.dex */
public final class c0 extends j3.r {

    /* renamed from: b  reason: collision with root package name */
    private final g f5003b;

    /* renamed from: c  reason: collision with root package name */
    private final f4.k f5004c;

    /* renamed from: d  reason: collision with root package name */
    private final j3.j f5005d;

    public c0(int i8, g gVar, f4.k kVar, j3.j jVar) {
        super(i8);
        this.f5004c = kVar;
        this.f5003b = gVar;
        this.f5005d = jVar;
        if (i8 == 2 && gVar.c()) {
            throw new IllegalArgumentException("Best-effort write calls cannot pass methods that should auto-resolve missing features.");
        }
    }

    @Override // com.google.android.gms.common.api.internal.e0
    public final void a(Status status) {
        this.f5004c.d(this.f5005d.a(status));
    }

    @Override // com.google.android.gms.common.api.internal.e0
    public final void b(Exception exc) {
        this.f5004c.d(exc);
    }

    @Override // com.google.android.gms.common.api.internal.e0
    public final void c(q qVar) {
        try {
            this.f5003b.b(qVar.v(), this.f5004c);
        } catch (DeadObjectException e8) {
            throw e8;
        } catch (RemoteException e9) {
            a(e0.e(e9));
        } catch (RuntimeException e10) {
            this.f5004c.d(e10);
        }
    }

    @Override // com.google.android.gms.common.api.internal.e0
    public final void d(j jVar, boolean z7) {
        jVar.b(this.f5004c, z7);
    }

    @Override // j3.r
    public final boolean f(q qVar) {
        return this.f5003b.c();
    }

    @Override // j3.r
    public final h3.c[] g(q qVar) {
        return this.f5003b.e();
    }
}
