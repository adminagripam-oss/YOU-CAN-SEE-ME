package s;

import android.content.Context;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CameraDevice;
import android.hardware.camera2.CameraManager;
import android.os.Handler;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Executor;
import s.c0;
import s.o0;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class s0 implements o0.b {

    /* renamed from: a  reason: collision with root package name */
    final CameraManager f11504a;

    /* renamed from: b  reason: collision with root package name */
    final Object f11505b;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static final class a {

        /* renamed from: a  reason: collision with root package name */
        final Map f11506a = new HashMap();

        /* renamed from: b  reason: collision with root package name */
        final Handler f11507b;

        a(Handler handler) {
            this.f11507b = handler;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public s0(Context context, Object obj) {
        this.f11504a = (CameraManager) context.getSystemService("camera");
        this.f11505b = obj;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static s0 h(Context context, Handler handler) {
        return new s0(context, new a(handler));
    }

    @Override // s.o0.b
    public Set b() {
        return Collections.emptySet();
    }

    @Override // s.o0.b
    public void c(Executor executor, CameraManager.AvailabilityCallback availabilityCallback) {
        o0.a aVar;
        if (executor == null) {
            throw new IllegalArgumentException("executor was null");
        }
        a aVar2 = (a) this.f11505b;
        if (availabilityCallback != null) {
            synchronized (aVar2.f11506a) {
                aVar = (o0.a) aVar2.f11506a.get(availabilityCallback);
                if (aVar == null) {
                    aVar = new o0.a(executor, availabilityCallback);
                    aVar2.f11506a.put(availabilityCallback, aVar);
                }
            }
        } else {
            aVar = null;
        }
        this.f11504a.registerAvailabilityCallback(aVar, aVar2.f11507b);
    }

    @Override // s.o0.b
    public void d(CameraManager.AvailabilityCallback availabilityCallback) {
        o0.a aVar;
        if (availabilityCallback != null) {
            a aVar2 = (a) this.f11505b;
            synchronized (aVar2.f11506a) {
                aVar = (o0.a) aVar2.f11506a.remove(availabilityCallback);
            }
        } else {
            aVar = null;
        }
        if (aVar != null) {
            aVar.g();
        }
        this.f11504a.unregisterAvailabilityCallback(aVar);
    }

    @Override // s.o0.b
    public CameraCharacteristics e(String str) {
        try {
            return this.f11504a.getCameraCharacteristics(str);
        } catch (CameraAccessException e8) {
            throw g.e(e8);
        }
    }

    @Override // s.o0.b
    public void f(String str, Executor executor, CameraDevice.StateCallback stateCallback) {
        g1.e.j(executor);
        g1.e.j(stateCallback);
        try {
            this.f11504a.openCamera(str, new c0.b(executor, stateCallback), ((a) this.f11505b).f11507b);
        } catch (CameraAccessException e8) {
            throw g.e(e8);
        }
    }

    @Override // s.o0.b
    public String[] g() {
        try {
            return this.f11504a.getCameraIdList();
        } catch (CameraAccessException e8) {
            throw g.e(e8);
        }
    }
}
