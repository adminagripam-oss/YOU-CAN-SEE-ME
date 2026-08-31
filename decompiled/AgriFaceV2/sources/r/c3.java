package r;

import android.graphics.Rect;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CaptureRequest;
import android.hardware.camera2.TotalCaptureResult;
import androidx.concurrent.futures.c;
import b0.u0;
import q.a;
import r.h5;
import y.k;
/* loaded from: classes.dex */
final class c3 implements h5.b {

    /* renamed from: a  reason: collision with root package name */
    private final s.b0 f10413a;

    /* renamed from: c  reason: collision with root package name */
    private c.a f10415c;

    /* renamed from: b  reason: collision with root package name */
    private Rect f10414b = null;

    /* renamed from: d  reason: collision with root package name */
    private Rect f10416d = null;

    /* JADX INFO: Access modifiers changed from: package-private */
    public c3(s.b0 b0Var) {
        this.f10413a = b0Var;
    }

    private static Rect h(Rect rect, float f8) {
        float width = rect.width() / f8;
        float height = rect.height() / f8;
        float width2 = (rect.width() - width) / 2.0f;
        float height2 = (rect.height() - height) / 2.0f;
        return new Rect((int) width2, (int) height2, (int) (width2 + width), (int) (height2 + height));
    }

    private Rect i() {
        return (Rect) g1.e.j((Rect) this.f10413a.a(CameraCharacteristics.SENSOR_INFO_ACTIVE_ARRAY_SIZE));
    }

    @Override // r.h5.b
    public void a(TotalCaptureResult totalCaptureResult) {
        if (this.f10415c != null) {
            CaptureRequest request = totalCaptureResult.getRequest();
            Rect rect = request == null ? null : (Rect) request.get(CaptureRequest.SCALER_CROP_REGION);
            Rect rect2 = this.f10416d;
            if (rect2 == null || !rect2.equals(rect)) {
                return;
            }
            this.f10415c.c(null);
            this.f10415c = null;
            this.f10416d = null;
        }
    }

    @Override // r.h5.b
    public void b(float f8, c.a aVar) {
        this.f10414b = h(i(), f8);
        c.a aVar2 = this.f10415c;
        if (aVar2 != null) {
            aVar2.f(new k.a("There is a new zoomRatio being set"));
        }
        this.f10416d = this.f10414b;
        this.f10415c = aVar;
    }

    @Override // r.h5.b
    public Rect c() {
        Rect rect = this.f10414b;
        return rect != null ? rect : i();
    }

    @Override // r.h5.b
    public float d() {
        Float f8 = (Float) this.f10413a.a(CameraCharacteristics.SCALER_AVAILABLE_MAX_DIGITAL_ZOOM);
        if (f8 == null) {
            return 1.0f;
        }
        return f8.floatValue() < f() ? f() : f8.floatValue();
    }

    @Override // r.h5.b
    public void e(a.C0140a c0140a) {
        Rect rect = this.f10414b;
        if (rect != null) {
            c0140a.g(CaptureRequest.SCALER_CROP_REGION, rect, u0.c.REQUIRED);
        }
    }

    @Override // r.h5.b
    public float f() {
        return 1.0f;
    }

    @Override // r.h5.b
    public void g() {
        this.f10416d = null;
        this.f10414b = null;
        c.a aVar = this.f10415c;
        if (aVar != null) {
            aVar.f(new k.a("Camera is not active."));
            this.f10415c = null;
        }
    }
}
