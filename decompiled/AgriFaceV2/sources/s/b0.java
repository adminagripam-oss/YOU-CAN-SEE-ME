package s;

import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.params.StreamConfigurationMap;
import android.os.Build;
import java.util.HashMap;
import java.util.Map;
/* loaded from: classes.dex */
public class b0 {

    /* renamed from: b  reason: collision with root package name */
    private final a f11429b;

    /* renamed from: c  reason: collision with root package name */
    private final String f11430c;

    /* renamed from: a  reason: collision with root package name */
    private final Map f11428a = new HashMap();

    /* renamed from: d  reason: collision with root package name */
    private t0 f11431d = null;

    /* loaded from: classes.dex */
    public interface a {
        Object a(CameraCharacteristics.Key key);
    }

    private b0(CameraCharacteristics cameraCharacteristics, String str) {
        this.f11429b = Build.VERSION.SDK_INT >= 28 ? new y(cameraCharacteristics) : new z(cameraCharacteristics);
        this.f11430c = str;
    }

    private boolean c(CameraCharacteristics.Key key) {
        return key.equals(CameraCharacteristics.SENSOR_ORIENTATION);
    }

    public static b0 e(CameraCharacteristics cameraCharacteristics, String str) {
        return new b0(cameraCharacteristics, str);
    }

    public Object a(CameraCharacteristics.Key key) {
        if (c(key)) {
            return this.f11429b.a(key);
        }
        synchronized (this) {
            Object obj = this.f11428a.get(key);
            if (obj != null) {
                return obj;
            }
            Object a8 = this.f11429b.a(key);
            if (a8 != null) {
                this.f11428a.put(key, a8);
            }
            return a8;
        }
    }

    public t0 b() {
        if (this.f11431d == null) {
            try {
                StreamConfigurationMap streamConfigurationMap = (StreamConfigurationMap) a(CameraCharacteristics.SCALER_STREAM_CONFIGURATION_MAP);
                if (streamConfigurationMap == null) {
                    throw new IllegalArgumentException("StreamConfigurationMap is null!");
                }
                this.f11431d = t0.e(streamConfigurationMap, new v.m(this.f11430c));
            } catch (AssertionError | NullPointerException e8) {
                throw new IllegalArgumentException(e8.getMessage());
            }
        }
        return this.f11431d;
    }

    public boolean d() {
        CameraCharacteristics.Key key;
        if (Build.VERSION.SDK_INT >= 34) {
            a aVar = this.f11429b;
            key = CameraCharacteristics.CONTROL_AVAILABLE_SETTINGS_OVERRIDES;
            int[] iArr = (int[]) aVar.a(key);
            if (iArr != null) {
                for (int i8 : iArr) {
                    if (i8 == 1) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
