package r;

import android.hardware.camera2.CameraCaptureSession;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.params.InputConfiguration;
import android.hardware.camera2.params.StreamConfigurationMap;
import android.media.Image;
import android.media.ImageWriter;
import android.util.Size;
import android.view.Surface;
import androidx.camera.camera2.internal.compat.quirk.ZslDisablerQuirk;
import b0.q1;
import b0.q2;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Objects;
import k0.c;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class n5 implements j5 {

    /* renamed from: a  reason: collision with root package name */
    private final s.b0 f10673a;

    /* renamed from: b  reason: collision with root package name */
    final k0.f f10674b;

    /* renamed from: c  reason: collision with root package name */
    private boolean f10675c = false;

    /* renamed from: d  reason: collision with root package name */
    private boolean f10676d = false;

    /* renamed from: e  reason: collision with root package name */
    private boolean f10677e;

    /* renamed from: f  reason: collision with root package name */
    private boolean f10678f;

    /* renamed from: g  reason: collision with root package name */
    androidx.camera.core.q f10679g;

    /* renamed from: h  reason: collision with root package name */
    private b0.n f10680h;

    /* renamed from: i  reason: collision with root package name */
    private b0.b1 f10681i;

    /* renamed from: j  reason: collision with root package name */
    ImageWriter f10682j;

    /* loaded from: classes.dex */
    class a extends CameraCaptureSession.StateCallback {
        a() {
        }

        @Override // android.hardware.camera2.CameraCaptureSession.StateCallback
        public void onConfigureFailed(CameraCaptureSession cameraCaptureSession) {
        }

        @Override // android.hardware.camera2.CameraCaptureSession.StateCallback
        public void onConfigured(CameraCaptureSession cameraCaptureSession) {
            Surface inputSurface = cameraCaptureSession.getInputSurface();
            if (inputSurface != null) {
                n5.this.f10682j = h0.a.c(inputSurface, 1);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public n5(s.b0 b0Var) {
        this.f10677e = false;
        this.f10678f = false;
        this.f10673a = b0Var;
        this.f10677e = o5.a(b0Var, 4);
        this.f10678f = androidx.camera.camera2.internal.compat.quirk.b.b(ZslDisablerQuirk.class) != null;
        this.f10674b = new k0.f(3, new c.a() { // from class: r.m5
            @Override // k0.c.a
            public final void a(Object obj) {
                ((androidx.camera.core.n) obj).close();
            }
        });
    }

    private void j() {
        k0.f fVar = this.f10674b;
        while (!fVar.isEmpty()) {
            ((androidx.camera.core.n) fVar.a()).close();
        }
        b0.b1 b1Var = this.f10681i;
        if (b1Var != null) {
            androidx.camera.core.q qVar = this.f10679g;
            if (qVar != null) {
                b1Var.k().d(new l5(qVar), e0.c.e());
                this.f10679g = null;
            }
            b1Var.d();
            this.f10681i = null;
        }
        ImageWriter imageWriter = this.f10682j;
        if (imageWriter != null) {
            imageWriter.close();
            this.f10682j = null;
        }
    }

    private Map k(s.b0 b0Var) {
        StreamConfigurationMap streamConfigurationMap;
        int[] inputFormats;
        try {
            streamConfigurationMap = (StreamConfigurationMap) b0Var.a(CameraCharacteristics.SCALER_STREAM_CONFIGURATION_MAP);
        } catch (AssertionError e8) {
            y.f1.c("ZslControlImpl", "Failed to retrieve StreamConfigurationMap, error = " + e8.getMessage());
            streamConfigurationMap = null;
        }
        if (streamConfigurationMap == null || streamConfigurationMap.getInputFormats() == null) {
            return new HashMap();
        }
        HashMap hashMap = new HashMap();
        for (int i8 : streamConfigurationMap.getInputFormats()) {
            Size[] inputSizes = streamConfigurationMap.getInputSizes(i8);
            if (inputSizes != null) {
                Arrays.sort(inputSizes, new d0.e(true));
                hashMap.put(Integer.valueOf(i8), inputSizes[0]);
            }
        }
        return hashMap;
    }

    private boolean l(s.b0 b0Var, int i8) {
        int[] validOutputFormatsForInput;
        StreamConfigurationMap streamConfigurationMap = (StreamConfigurationMap) b0Var.a(CameraCharacteristics.SCALER_STREAM_CONFIGURATION_MAP);
        if (streamConfigurationMap == null || (validOutputFormatsForInput = streamConfigurationMap.getValidOutputFormatsForInput(i8)) == null) {
            return false;
        }
        for (int i9 : validOutputFormatsForInput) {
            if (i9 == 256) {
                return true;
            }
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void m(b0.q1 q1Var) {
        try {
            androidx.camera.core.n acquireLatestImage = q1Var.acquireLatestImage();
            if (acquireLatestImage != null) {
                this.f10674b.b(acquireLatestImage);
            }
        } catch (IllegalStateException e8) {
            y.f1.c("ZslControlImpl", "Failed to acquire latest image IllegalStateException = " + e8.getMessage());
        }
    }

    @Override // r.j5
    public boolean a() {
        return this.f10675c;
    }

    @Override // r.j5
    public void b(q2.b bVar) {
        j();
        if (this.f10675c) {
            bVar.z(1);
        } else if (this.f10678f) {
            bVar.z(1);
        } else {
            Map k8 = k(this.f10673a);
            if (!this.f10677e || k8.isEmpty() || !k8.containsKey(34) || !l(this.f10673a, 34)) {
                bVar.z(1);
                return;
            }
            Size size = (Size) k8.get(34);
            androidx.camera.core.p pVar = new androidx.camera.core.p(size.getWidth(), size.getHeight(), 34, 9);
            this.f10680h = pVar.l();
            this.f10679g = new androidx.camera.core.q(pVar);
            pVar.d(new q1.a() { // from class: r.k5
                @Override // b0.q1.a
                public final void a(b0.q1 q1Var) {
                    n5.this.m(q1Var);
                }
            }, e0.c.d());
            b0.r1 r1Var = new b0.r1(this.f10679g.getSurface(), new Size(this.f10679g.getWidth(), this.f10679g.getHeight()), 34);
            this.f10681i = r1Var;
            androidx.camera.core.q qVar = this.f10679g;
            i4.d k9 = r1Var.k();
            Objects.requireNonNull(qVar);
            k9.d(new l5(qVar), e0.c.e());
            bVar.l(this.f10681i);
            bVar.e(this.f10680h);
            bVar.k(new a());
            bVar.w(new InputConfiguration(this.f10679g.getWidth(), this.f10679g.getHeight(), this.f10679g.a()));
        }
    }

    @Override // r.j5
    public boolean c() {
        return this.f10676d;
    }

    @Override // r.j5
    public boolean d(androidx.camera.core.n nVar) {
        Image z7 = nVar.z();
        ImageWriter imageWriter = this.f10682j;
        if (imageWriter != null && z7 != null) {
            try {
                h0.a.d(imageWriter, z7);
                return true;
            } catch (IllegalStateException e8) {
                y.f1.c("ZslControlImpl", "enqueueImageToImageWriter throws IllegalStateException = " + e8.getMessage());
            }
        }
        return false;
    }

    @Override // r.j5
    public void e(boolean z7) {
        this.f10676d = z7;
    }

    @Override // r.j5
    public void f(boolean z7) {
        this.f10675c = z7;
    }

    @Override // r.j5
    public androidx.camera.core.n g() {
        try {
            return (androidx.camera.core.n) this.f10674b.a();
        } catch (NoSuchElementException unused) {
            y.f1.c("ZslControlImpl", "dequeueImageFromBuffer no such element");
            return null;
        }
    }
}
