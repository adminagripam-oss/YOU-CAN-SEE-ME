package r;

import android.hardware.camera2.CaptureRequest;
import android.hardware.camera2.CaptureResult;
import android.hardware.camera2.TotalCaptureResult;
import android.util.Range;
import androidx.concurrent.futures.c;
import b0.u0;
import java.util.concurrent.Executor;
import q.a;
import r.t;
import y.k;
/* loaded from: classes.dex */
public class i3 {

    /* renamed from: a  reason: collision with root package name */
    private final t f10526a;

    /* renamed from: b  reason: collision with root package name */
    private final j3 f10527b;

    /* renamed from: c  reason: collision with root package name */
    private final Executor f10528c;

    /* renamed from: d  reason: collision with root package name */
    private boolean f10529d = false;

    /* renamed from: e  reason: collision with root package name */
    private c.a f10530e;

    /* renamed from: f  reason: collision with root package name */
    private t.c f10531f;

    /* JADX INFO: Access modifiers changed from: package-private */
    public i3(t tVar, s.b0 b0Var, Executor executor) {
        this.f10526a = tVar;
        this.f10527b = new j3(b0Var, 0);
        this.f10528c = executor;
    }

    private void d() {
        c.a aVar = this.f10530e;
        if (aVar != null) {
            aVar.f(new k.a("Cancelled by another setExposureCompensationIndex()"));
            this.f10530e = null;
        }
        t.c cVar = this.f10531f;
        if (cVar != null) {
            this.f10526a.m0(cVar);
            this.f10531f = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static y.b0 e(s.b0 b0Var) {
        return new j3(b0Var, 0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ boolean g(int i8, c.a aVar, TotalCaptureResult totalCaptureResult) {
        Integer num = (Integer) totalCaptureResult.get(CaptureResult.CONTROL_AE_STATE);
        Integer num2 = (Integer) totalCaptureResult.get(CaptureResult.CONTROL_AE_EXPOSURE_COMPENSATION);
        if (num != null && num2 != null) {
            int intValue = num.intValue();
            if ((intValue != 2 && intValue != 3 && intValue != 4) || num2.intValue() != i8) {
                return false;
            }
        } else if (num2 == null || num2.intValue() != i8) {
            return false;
        }
        aVar.c(Integer.valueOf(i8));
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void h(final c.a aVar, final int i8) {
        if (!this.f10529d) {
            this.f10527b.e(0);
            aVar.f(new k.a("Camera is not active."));
            return;
        }
        d();
        g1.e.m(this.f10530e == null, "mRunningCompleter should be null when starting set a new exposure compensation value");
        g1.e.m(this.f10531f == null, "mRunningCaptureResultListener should be null when starting set a new exposure compensation value");
        t.c cVar = new t.c() { // from class: r.h3
            @Override // r.t.c
            public final boolean a(TotalCaptureResult totalCaptureResult) {
                boolean g8;
                g8 = i3.g(i8, aVar, totalCaptureResult);
                return g8;
            }
        };
        this.f10531f = cVar;
        this.f10530e = aVar;
        this.f10526a.A(cVar);
        this.f10526a.u0();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ Object i(final int i8, final c.a aVar) {
        this.f10528c.execute(new Runnable() { // from class: r.g3
            @Override // java.lang.Runnable
            public final void run() {
                i3.this.h(aVar, i8);
            }
        });
        return "setExposureCompensationIndex[" + i8 + "]";
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public y.b0 f() {
        return this.f10527b;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void j(boolean z7) {
        if (z7 == this.f10529d) {
            return;
        }
        this.f10529d = z7;
        if (z7) {
            return;
        }
        this.f10527b.e(0);
        d();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void k(a.C0140a c0140a) {
        c0140a.g(CaptureRequest.CONTROL_AE_EXPOSURE_COMPENSATION, Integer.valueOf(this.f10527b.c()), u0.c.REQUIRED);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public i4.d l(final int i8) {
        if (this.f10527b.d()) {
            Range a8 = this.f10527b.a();
            if (a8.contains((Range) Integer.valueOf(i8))) {
                this.f10527b.e(i8);
                return f0.n.B(androidx.concurrent.futures.c.a(new c.InterfaceC0018c() { // from class: r.f3
                    @Override // androidx.concurrent.futures.c.InterfaceC0018c
                    public final Object a(c.a aVar) {
                        Object i9;
                        i9 = i3.this.i(i8, aVar);
                        return i9;
                    }
                }));
            }
            return f0.n.n(new IllegalArgumentException("Requested ExposureCompensation " + i8 + " is not within valid range [" + a8.getUpper() + ".." + a8.getLower() + "]"));
        }
        return f0.n.n(new IllegalArgumentException("ExposureCompensation is not supported"));
    }
}
