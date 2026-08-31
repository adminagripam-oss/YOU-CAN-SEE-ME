package r;

import android.hardware.camera2.CameraDevice;
import android.hardware.camera2.CaptureRequest;
import android.hardware.camera2.TotalCaptureResult;
import android.view.Surface;
import b0.u0;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import x.j;
/* loaded from: classes.dex */
abstract class c2 {

    /* loaded from: classes.dex */
    static class a {
        static CaptureRequest.Builder a(CameraDevice cameraDevice, TotalCaptureResult totalCaptureResult) {
            return cameraDevice.createReprocessCaptureRequest(totalCaptureResult);
        }
    }

    private static void a(b0.s0 s0Var, CaptureRequest.Builder builder) {
        if (s0Var.e().equals(b0.u2.f4433a)) {
            return;
        }
        builder.set(CaptureRequest.CONTROL_AE_TARGET_FPS_RANGE, s0Var.e());
    }

    private static void b(CaptureRequest.Builder builder, b0.u0 u0Var) {
        x.j b8 = j.a.f(u0Var).b();
        for (u0.a aVar : b8.d()) {
            CaptureRequest.Key key = (CaptureRequest.Key) aVar.d();
            try {
                builder.set(key, b8.b(aVar));
            } catch (IllegalArgumentException unused) {
                y.f1.c("Camera2CaptureRequestBuilder", "CaptureRequest.Key is not supported: " + key);
            }
        }
    }

    private static void c(CaptureRequest.Builder builder, int i8, v.y yVar) {
        for (Map.Entry entry : yVar.a(i8).entrySet()) {
            builder.set((CaptureRequest.Key) entry.getKey(), entry.getValue());
        }
    }

    static void d(b0.s0 s0Var, CaptureRequest.Builder builder) {
        CaptureRequest.Key key;
        int i8;
        if (s0Var.h() == 1 || s0Var.l() == 1) {
            key = CaptureRequest.CONTROL_VIDEO_STABILIZATION_MODE;
            i8 = 0;
        } else if (s0Var.h() == 2) {
            key = CaptureRequest.CONTROL_VIDEO_STABILIZATION_MODE;
            i8 = 2;
        } else if (s0Var.l() != 2) {
            return;
        } else {
            key = CaptureRequest.CONTROL_VIDEO_STABILIZATION_MODE;
            i8 = 1;
        }
        builder.set(key, i8);
    }

    public static CaptureRequest e(b0.s0 s0Var, CameraDevice cameraDevice, Map map, boolean z7, v.y yVar) {
        CaptureRequest.Builder createCaptureRequest;
        if (cameraDevice == null) {
            return null;
        }
        List<Surface> g8 = g(s0Var.i(), map);
        if (g8.isEmpty()) {
            return null;
        }
        b0.x d8 = s0Var.d();
        if (s0Var.k() == 5 && d8 != null && (d8.h() instanceof TotalCaptureResult)) {
            y.f1.a("Camera2CaptureRequestBuilder", "createReprocessCaptureRequest");
            createCaptureRequest = a.a(cameraDevice, (TotalCaptureResult) d8.h());
        } else {
            y.f1.a("Camera2CaptureRequestBuilder", "createCaptureRequest");
            createCaptureRequest = cameraDevice.createCaptureRequest(s0Var.k() == 5 ? z7 ? 1 : 2 : s0Var.k());
        }
        c(createCaptureRequest, s0Var.k(), yVar);
        a(s0Var, createCaptureRequest);
        d(s0Var, createCaptureRequest);
        b0.u0 g9 = s0Var.g();
        u0.a aVar = b0.s0.f4371i;
        if (g9.a(aVar)) {
            createCaptureRequest.set(CaptureRequest.JPEG_ORIENTATION, (Integer) s0Var.g().b(aVar));
        }
        b0.u0 g10 = s0Var.g();
        u0.a aVar2 = b0.s0.f4372j;
        if (g10.a(aVar2)) {
            createCaptureRequest.set(CaptureRequest.JPEG_QUALITY, Byte.valueOf(((Integer) s0Var.g().b(aVar2)).byteValue()));
        }
        b(createCaptureRequest, s0Var.g());
        for (Surface surface : g8) {
            createCaptureRequest.addTarget(surface);
        }
        createCaptureRequest.setTag(s0Var.j());
        return createCaptureRequest.build();
    }

    public static CaptureRequest f(b0.s0 s0Var, CameraDevice cameraDevice, v.y yVar) {
        if (cameraDevice == null) {
            return null;
        }
        y.f1.a("Camera2CaptureRequestBuilder", "template type = " + s0Var.k());
        CaptureRequest.Builder createCaptureRequest = cameraDevice.createCaptureRequest(s0Var.k());
        c(createCaptureRequest, s0Var.k(), yVar);
        b(createCaptureRequest, s0Var.g());
        return createCaptureRequest.build();
    }

    private static List g(List list, Map map) {
        ArrayList arrayList = new ArrayList();
        Iterator it = list.iterator();
        while (it.hasNext()) {
            Surface surface = (Surface) map.get((b0.b1) it.next());
            if (surface == null) {
                throw new IllegalArgumentException("DeferrableSurface not in configuredSurfaceMap");
            }
            arrayList.add(surface);
        }
        return arrayList;
    }
}
