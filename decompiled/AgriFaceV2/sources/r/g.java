package r;

import android.hardware.camera2.CaptureResult;
import android.os.Build;
import d0.i;
import java.nio.BufferUnderflowException;
/* loaded from: classes.dex */
public class g implements b0.x {

    /* renamed from: a  reason: collision with root package name */
    private final b0.y2 f10481a;

    /* renamed from: b  reason: collision with root package name */
    private final CaptureResult f10482b;

    public g(CaptureResult captureResult) {
        this(b0.y2.b(), captureResult);
    }

    @Override // b0.x
    public void a(i.b bVar) {
        super.a(bVar);
        try {
            Integer num = (Integer) this.f10482b.get(CaptureResult.JPEG_ORIENTATION);
            if (num != null) {
                bVar.m(num.intValue());
            }
        } catch (BufferUnderflowException unused) {
            y.f1.l("C2CameraCaptureResult", "Failed to get JPEG orientation.");
        }
        Long l8 = (Long) this.f10482b.get(CaptureResult.SENSOR_EXPOSURE_TIME);
        if (l8 != null) {
            bVar.f(l8.longValue());
        }
        Float f8 = (Float) this.f10482b.get(CaptureResult.LENS_APERTURE);
        if (f8 != null) {
            bVar.l(f8.floatValue());
        }
        Integer num2 = (Integer) this.f10482b.get(CaptureResult.SENSOR_SENSITIVITY);
        if (num2 != null) {
            Integer num3 = (Integer) this.f10482b.get(CaptureResult.CONTROL_POST_RAW_SENSITIVITY_BOOST);
            if (num3 != null) {
                num2 = Integer.valueOf(num2.intValue() * ((int) (num3.intValue() / 100.0f)));
            }
            bVar.k(num2.intValue());
        }
        Float f9 = (Float) this.f10482b.get(CaptureResult.LENS_FOCAL_LENGTH);
        if (f9 != null) {
            bVar.h(f9.floatValue());
        }
        Integer num4 = (Integer) this.f10482b.get(CaptureResult.CONTROL_AWB_MODE);
        if (num4 != null) {
            i.c cVar = i.c.AUTO;
            if (num4.intValue() == 0) {
                cVar = i.c.MANUAL;
            }
            bVar.n(cVar);
        }
    }

    @Override // b0.x
    public b0.y2 b() {
        return this.f10481a;
    }

    @Override // b0.x
    public long c() {
        Long l8 = (Long) this.f10482b.get(CaptureResult.SENSOR_TIMESTAMP);
        if (l8 == null) {
            return -1L;
        }
        return l8.longValue();
    }

    @Override // b0.x
    public b0.s d() {
        Integer num = (Integer) this.f10482b.get(CaptureResult.CONTROL_AF_MODE);
        if (num == null) {
            return b0.s.UNKNOWN;
        }
        int intValue = num.intValue();
        if (intValue != 0) {
            if (intValue == 1 || intValue == 2) {
                return b0.s.ON_MANUAL_AUTO;
            }
            if (intValue == 3 || intValue == 4) {
                return b0.s.ON_CONTINUOUS_AUTO;
            }
            if (intValue != 5) {
                y.f1.c("C2CameraCaptureResult", "Undefined af mode: " + num);
                return b0.s.UNKNOWN;
            }
        }
        return b0.s.OFF;
    }

    @Override // b0.x
    public b0.r e() {
        Integer num = (Integer) this.f10482b.get(CaptureResult.CONTROL_AE_STATE);
        if (num == null) {
            return b0.r.UNKNOWN;
        }
        int intValue = num.intValue();
        if (intValue != 0) {
            if (intValue != 1) {
                if (intValue == 2) {
                    return b0.r.CONVERGED;
                }
                if (intValue == 3) {
                    return b0.r.LOCKED;
                }
                if (intValue == 4) {
                    return b0.r.FLASH_REQUIRED;
                }
                if (intValue != 5) {
                    y.f1.c("C2CameraCaptureResult", "Undefined ae state: " + num);
                    return b0.r.UNKNOWN;
                }
            }
            return b0.r.SEARCHING;
        }
        return b0.r.INACTIVE;
    }

    @Override // b0.x
    public b0.v f() {
        Integer num = (Integer) this.f10482b.get(CaptureResult.CONTROL_AWB_STATE);
        if (num == null) {
            return b0.v.UNKNOWN;
        }
        int intValue = num.intValue();
        if (intValue != 0) {
            if (intValue != 1) {
                if (intValue != 2) {
                    if (intValue != 3) {
                        y.f1.c("C2CameraCaptureResult", "Undefined awb state: " + num);
                        return b0.v.UNKNOWN;
                    }
                    return b0.v.LOCKED;
                }
                return b0.v.CONVERGED;
            }
            return b0.v.METERING;
        }
        return b0.v.INACTIVE;
    }

    @Override // b0.x
    public b0.w g() {
        Integer num = (Integer) this.f10482b.get(CaptureResult.FLASH_STATE);
        if (num == null) {
            return b0.w.UNKNOWN;
        }
        int intValue = num.intValue();
        if (intValue == 0 || intValue == 1) {
            return b0.w.NONE;
        }
        if (intValue != 2) {
            if (intValue == 3 || intValue == 4) {
                return b0.w.FIRED;
            }
            y.f1.c("C2CameraCaptureResult", "Undefined flash state: " + num);
            return b0.w.UNKNOWN;
        }
        return b0.w.READY;
    }

    @Override // b0.x
    public CaptureResult h() {
        return this.f10482b;
    }

    @Override // b0.x
    public b0.u i() {
        Integer num = (Integer) this.f10482b.get(CaptureResult.CONTROL_AWB_MODE);
        if (num == null) {
            return b0.u.UNKNOWN;
        }
        switch (num.intValue()) {
            case 0:
                return b0.u.OFF;
            case 1:
                return b0.u.AUTO;
            case 2:
                return b0.u.INCANDESCENT;
            case 3:
                return b0.u.FLUORESCENT;
            case 4:
                return b0.u.WARM_FLUORESCENT;
            case 5:
                return b0.u.DAYLIGHT;
            case androidx.browser.customtabs.b.TAB_HIDDEN /* 6 */:
                return b0.u.CLOUDY_DAYLIGHT;
            case 7:
                return b0.u.TWILIGHT;
            case 8:
                return b0.u.SHADE;
            default:
                return b0.u.UNKNOWN;
        }
    }

    @Override // b0.x
    public b0.q j() {
        Integer num = (Integer) this.f10482b.get(CaptureResult.CONTROL_AE_MODE);
        if (num == null) {
            return b0.q.UNKNOWN;
        }
        int intValue = num.intValue();
        if (intValue != 0) {
            if (intValue != 1) {
                if (intValue != 2) {
                    if (intValue != 3) {
                        if (intValue != 4) {
                            if (intValue == 5 && Build.VERSION.SDK_INT >= 28) {
                                return b0.q.ON_EXTERNAL_FLASH;
                            }
                            return b0.q.UNKNOWN;
                        }
                        return b0.q.ON_AUTO_FLASH_REDEYE;
                    }
                    return b0.q.ON_ALWAYS_FLASH;
                }
                return b0.q.ON_AUTO_FLASH;
            }
            return b0.q.ON;
        }
        return b0.q.OFF;
    }

    @Override // b0.x
    public b0.t k() {
        Integer num = (Integer) this.f10482b.get(CaptureResult.CONTROL_AF_STATE);
        if (num == null) {
            return b0.t.UNKNOWN;
        }
        switch (num.intValue()) {
            case 0:
                return b0.t.INACTIVE;
            case 1:
            case 3:
                return b0.t.SCANNING;
            case 2:
                return b0.t.PASSIVE_FOCUSED;
            case 4:
                return b0.t.LOCKED_FOCUSED;
            case 5:
                return b0.t.LOCKED_NOT_FOCUSED;
            case androidx.browser.customtabs.b.TAB_HIDDEN /* 6 */:
                return b0.t.PASSIVE_NOT_FOCUSED;
            default:
                y.f1.c("C2CameraCaptureResult", "Undefined af state: " + num);
                return b0.t.UNKNOWN;
        }
    }

    public g(b0.y2 y2Var, CaptureResult captureResult) {
        this.f10481a = y2Var;
        this.f10482b = captureResult;
    }
}
