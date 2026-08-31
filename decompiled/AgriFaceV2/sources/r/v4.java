package r;

import android.hardware.camera2.CameraCaptureSession;
import android.view.Surface;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import r.k4;
/* loaded from: classes.dex */
final class v4 extends k4.c {

    /* renamed from: a  reason: collision with root package name */
    private final List f10831a;

    /* loaded from: classes.dex */
    static class a extends k4.c {

        /* renamed from: a  reason: collision with root package name */
        private final CameraCaptureSession.StateCallback f10832a;

        a(CameraCaptureSession.StateCallback stateCallback) {
            this.f10832a = stateCallback;
        }

        @Override // r.k4.c
        public void o(k4 k4Var) {
            this.f10832a.onActive(k4Var.h().c());
        }

        @Override // r.k4.c
        public void p(k4 k4Var) {
            s.d.a(this.f10832a, k4Var.h().c());
        }

        @Override // r.k4.c
        public void q(k4 k4Var) {
            this.f10832a.onClosed(k4Var.h().c());
        }

        @Override // r.k4.c
        public void r(k4 k4Var) {
            this.f10832a.onConfigureFailed(k4Var.h().c());
        }

        @Override // r.k4.c
        public void s(k4 k4Var) {
            this.f10832a.onConfigured(k4Var.h().c());
        }

        @Override // r.k4.c
        public void t(k4 k4Var) {
            this.f10832a.onReady(k4Var.h().c());
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // r.k4.c
        public void u(k4 k4Var) {
        }

        @Override // r.k4.c
        public void v(k4 k4Var, Surface surface) {
            s.b.a(this.f10832a, k4Var.h().c(), surface);
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public a(List list) {
            this(i2.a(list));
        }
    }

    v4(List list) {
        ArrayList arrayList = new ArrayList();
        this.f10831a = arrayList;
        arrayList.addAll(list);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static k4.c w(k4.c... cVarArr) {
        return new v4(Arrays.asList(cVarArr));
    }

    @Override // r.k4.c
    public void o(k4 k4Var) {
        for (k4.c cVar : this.f10831a) {
            cVar.o(k4Var);
        }
    }

    @Override // r.k4.c
    public void p(k4 k4Var) {
        for (k4.c cVar : this.f10831a) {
            cVar.p(k4Var);
        }
    }

    @Override // r.k4.c
    public void q(k4 k4Var) {
        for (k4.c cVar : this.f10831a) {
            cVar.q(k4Var);
        }
    }

    @Override // r.k4.c
    public void r(k4 k4Var) {
        for (k4.c cVar : this.f10831a) {
            cVar.r(k4Var);
        }
    }

    @Override // r.k4.c
    public void s(k4 k4Var) {
        for (k4.c cVar : this.f10831a) {
            cVar.s(k4Var);
        }
    }

    @Override // r.k4.c
    public void t(k4 k4Var) {
        for (k4.c cVar : this.f10831a) {
            cVar.t(k4Var);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // r.k4.c
    public void u(k4 k4Var) {
        for (k4.c cVar : this.f10831a) {
            cVar.u(k4Var);
        }
    }

    @Override // r.k4.c
    public void v(k4 k4Var, Surface surface) {
        for (k4.c cVar : this.f10831a) {
            cVar.v(k4Var, surface);
        }
    }
}
