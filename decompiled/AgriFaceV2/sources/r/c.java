package r;

import android.graphics.Rect;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CaptureRequest;
import android.hardware.camera2.TotalCaptureResult;
import android.util.Range;
import androidx.concurrent.futures.c;
import b0.u0;
import q.a;
import r.h5;
import y.k;
/* loaded from: classes.dex */
final class c implements h5.b {

    /* renamed from: a  reason: collision with root package name */
    private final s.b0 f10404a;

    /* renamed from: b  reason: collision with root package name */
    private final Range f10405b;

    /* renamed from: d  reason: collision with root package name */
    private c.a f10407d;

    /* renamed from: f  reason: collision with root package name */
    private boolean f10409f;

    /* renamed from: c  reason: collision with root package name */
    private float f10406c = 1.0f;

    /* renamed from: e  reason: collision with root package name */
    private float f10408e = 1.0f;

    /* JADX INFO: Access modifiers changed from: package-private */
    public c(s.b0 b0Var) {
        CameraCharacteristics.Key key;
        this.f10409f = false;
        this.f10404a = b0Var;
        key = CameraCharacteristics.CONTROL_ZOOM_RATIO_RANGE;
        this.f10405b = (Range) b0Var.a(key);
        this.f10409f = b0Var.d();
    }

    @Override // r.h5.b
    public void a(TotalCaptureResult totalCaptureResult) {
        CaptureRequest.Key key;
        Float f8;
        if (this.f10407d != null) {
            CaptureRequest request = totalCaptureResult.getRequest();
            if (request == null) {
                f8 = null;
            } else {
                key = CaptureRequest.CONTROL_ZOOM_RATIO;
                f8 = (Float) request.get(key);
            }
            if (f8 == null) {
                return;
            }
            if (this.f10408e == f8.floatValue()) {
                this.f10407d.c(null);
                this.f10407d = null;
            }
        }
    }

    @Override // r.h5.b
    public void b(float f8, c.a aVar) {
        this.f10406c = f8;
        c.a aVar2 = this.f10407d;
        if (aVar2 != null) {
            aVar2.f(new k.a("There is a new zoomRatio being set"));
        }
        this.f10408e = this.f10406c;
        this.f10407d = aVar;
    }

    @Override // r.h5.b
    public Rect c() {
        return (Rect) g1.e.j((Rect) this.f10404a.a(CameraCharacteristics.SENSOR_INFO_ACTIVE_ARRAY_SIZE));
    }

    @Override // r.h5.b
    public float d() {
        return ((Float) this.f10405b.getUpper()).floatValue();
    }

    @Override // r.h5.b
    public void e(a.C0140a c0140a) {
        CaptureRequest.Key key;
        key = CaptureRequest.CONTROL_ZOOM_RATIO;
        Float valueOf = Float.valueOf(this.f10406c);
        u0.c cVar = u0.c.REQUIRED;
        c0140a.g(key, valueOf, cVar);
        if (this.f10409f) {
            t.b.a(c0140a, cVar);
        }
    }

    @Override // r.h5.b
    public float f() {
        return ((Float) this.f10405b.getLower()).floatValue();
    }

    @Override // r.h5.b
    public void g() {
        this.f10406c = 1.0f;
        c.a aVar = this.f10407d;
        if (aVar != null) {
            aVar.f(new k.a("Camera is not active."));
            this.f10407d = null;
        }
    }
}
