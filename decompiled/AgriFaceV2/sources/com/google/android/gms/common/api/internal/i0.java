package com.google.android.gms.common.api.internal;

import android.app.PendingIntent;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import java.util.concurrent.atomic.AtomicReference;
/* loaded from: classes.dex */
public abstract class i0 extends LifecycleCallback implements DialogInterface.OnCancelListener {

    /* renamed from: b  reason: collision with root package name */
    protected volatile boolean f5038b;

    /* renamed from: c  reason: collision with root package name */
    protected final AtomicReference f5039c;

    /* renamed from: d  reason: collision with root package name */
    private final Handler f5040d;

    /* renamed from: e  reason: collision with root package name */
    protected final h3.d f5041e;

    /* JADX INFO: Access modifiers changed from: package-private */
    public i0(j3.f fVar, h3.d dVar) {
        super(fVar);
        this.f5039c = new AtomicReference(null);
        this.f5040d = new v3.h(Looper.getMainLooper());
        this.f5041e = dVar;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void l(h3.a aVar, int i8) {
        this.f5039c.set(null);
        m(aVar, i8);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void o() {
        this.f5039c.set(null);
        n();
    }

    private static final int p(f0 f0Var) {
        if (f0Var == null) {
            return -1;
        }
        return f0Var.a();
    }

    @Override // com.google.android.gms.common.api.internal.LifecycleCallback
    public final void e(int i8, int i9, Intent intent) {
        f0 f0Var = (f0) this.f5039c.get();
        if (i8 != 1) {
            if (i8 == 2) {
                int g8 = this.f5041e.g(b());
                if (g8 == 0) {
                    o();
                    return;
                } else if (f0Var == null) {
                    return;
                } else {
                    if (f0Var.b().a() == 18 && g8 == 18) {
                        return;
                    }
                }
            }
        } else if (i9 == -1) {
            o();
            return;
        } else if (i9 == 0) {
            if (f0Var != null) {
                l(new h3.a(intent != null ? intent.getIntExtra("<<ResolutionFailureErrorDetail>>", 13) : 13, null, f0Var.b().toString()), p(f0Var));
                return;
            }
            return;
        }
        if (f0Var != null) {
            l(f0Var.b(), f0Var.a());
        }
    }

    @Override // com.google.android.gms.common.api.internal.LifecycleCallback
    public final void f(Bundle bundle) {
        super.f(bundle);
        if (bundle != null) {
            this.f5039c.set(bundle.getBoolean("resolving_error", false) ? new f0(new h3.a(bundle.getInt("failed_status"), (PendingIntent) bundle.getParcelable("failed_resolution")), bundle.getInt("failed_client_id", -1)) : null);
        }
    }

    @Override // com.google.android.gms.common.api.internal.LifecycleCallback
    public final void i(Bundle bundle) {
        super.i(bundle);
        f0 f0Var = (f0) this.f5039c.get();
        if (f0Var == null) {
            return;
        }
        bundle.putBoolean("resolving_error", true);
        bundle.putInt("failed_client_id", f0Var.a());
        bundle.putInt("failed_status", f0Var.b().a());
        bundle.putParcelable("failed_resolution", f0Var.b().c());
    }

    @Override // com.google.android.gms.common.api.internal.LifecycleCallback
    public void j() {
        super.j();
        this.f5038b = true;
    }

    @Override // com.google.android.gms.common.api.internal.LifecycleCallback
    public void k() {
        super.k();
        this.f5038b = false;
    }

    protected abstract void m(h3.a aVar, int i8);

    protected abstract void n();

    @Override // android.content.DialogInterface.OnCancelListener
    public final void onCancel(DialogInterface dialogInterface) {
        l(new h3.a(13, null), p((f0) this.f5039c.get()));
    }

    public final void s(h3.a aVar, int i8) {
        AtomicReference atomicReference;
        f0 f0Var = new f0(aVar, i8);
        do {
            atomicReference = this.f5039c;
            if (c2.c.a(atomicReference, null, f0Var)) {
                this.f5040d.post(new h0(this, f0Var));
                return;
            }
        } while (atomicReference.get() == null);
    }
}
