package r;

import android.hardware.camera2.CaptureRequest;
import android.hardware.camera2.TotalCaptureResult;
import androidx.concurrent.futures.c;
import java.util.Objects;
import java.util.concurrent.Executor;
import r.t;
import y.k;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class a5 {

    /* renamed from: a  reason: collision with root package name */
    private final t f10383a;

    /* renamed from: b  reason: collision with root package name */
    private final androidx.lifecycle.s f10384b;

    /* renamed from: c  reason: collision with root package name */
    private final boolean f10385c;

    /* renamed from: d  reason: collision with root package name */
    private final Executor f10386d;

    /* renamed from: e  reason: collision with root package name */
    private boolean f10387e;

    /* renamed from: f  reason: collision with root package name */
    c.a f10388f;

    /* renamed from: g  reason: collision with root package name */
    boolean f10389g;

    /* JADX INFO: Access modifiers changed from: package-private */
    public a5(t tVar, s.b0 b0Var, Executor executor) {
        this.f10383a = tVar;
        this.f10386d = executor;
        Objects.requireNonNull(b0Var);
        this.f10385c = v.g.a(new u0(b0Var));
        this.f10384b = new androidx.lifecycle.s(0);
        tVar.A(new t.c() { // from class: r.y4
            @Override // r.t.c
            public final boolean a(TotalCaptureResult totalCaptureResult) {
                boolean i8;
                i8 = a5.this.i(totalCaptureResult);
                return i8;
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ Object h(final boolean z7, final c.a aVar) {
        this.f10386d.execute(new Runnable() { // from class: r.z4
            @Override // java.lang.Runnable
            public final void run() {
                a5.this.g(aVar, z7);
            }
        });
        return "enableTorch: " + z7;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ boolean i(TotalCaptureResult totalCaptureResult) {
        if (this.f10388f != null) {
            Integer num = (Integer) totalCaptureResult.getRequest().get(CaptureRequest.FLASH_MODE);
            if ((num != null && num.intValue() == 2) == this.f10389g) {
                this.f10388f.c(null);
                this.f10388f = null;
            }
        }
        return false;
    }

    private void k(androidx.lifecycle.s sVar, Object obj) {
        if (d0.q.c()) {
            sVar.o(obj);
        } else {
            sVar.l(obj);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public i4.d d(final boolean z7) {
        if (this.f10385c) {
            k(this.f10384b, Integer.valueOf(z7 ? 1 : 0));
            return androidx.concurrent.futures.c.a(new c.InterfaceC0018c() { // from class: r.x4
                @Override // androidx.concurrent.futures.c.InterfaceC0018c
                public final Object a(c.a aVar) {
                    Object h8;
                    h8 = a5.this.h(z7, aVar);
                    return h8;
                }
            });
        }
        y.f1.a("TorchControl", "Unable to enableTorch due to there is no flash unit.");
        return f0.n.n(new IllegalStateException("No flash unit"));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: e */
    public void g(c.a aVar, boolean z7) {
        if (!this.f10385c) {
            if (aVar != null) {
                aVar.f(new IllegalStateException("No flash unit"));
            }
        } else if (!this.f10387e) {
            k(this.f10384b, 0);
            if (aVar != null) {
                aVar.f(new k.a("Camera is not active."));
            }
        } else {
            this.f10389g = z7;
            this.f10383a.D(z7);
            k(this.f10384b, Integer.valueOf(z7 ? 1 : 0));
            c.a aVar2 = this.f10388f;
            if (aVar2 != null) {
                aVar2.f(new k.a("There is a new enableTorch being set"));
            }
            this.f10388f = aVar;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public androidx.lifecycle.p f() {
        return this.f10384b;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void j(boolean z7) {
        if (this.f10387e == z7) {
            return;
        }
        this.f10387e = z7;
        if (z7) {
            return;
        }
        if (this.f10389g) {
            this.f10389g = false;
            this.f10383a.D(false);
            k(this.f10384b, 0);
        }
        c.a aVar = this.f10388f;
        if (aVar != null) {
            aVar.f(new k.a("Camera is not active."));
            this.f10388f = null;
        }
    }
}
