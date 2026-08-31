package v;

import android.hardware.camera2.CameraCharacteristics;
import android.os.Build;
import androidx.camera.camera2.internal.compat.quirk.FlashAvailabilityBufferUnderflowQuirk;
import java.nio.BufferUnderflowException;
import y.f1;
/* loaded from: classes.dex */
public abstract class g {
    public static boolean a(c cVar) {
        return b(false, cVar);
    }

    public static boolean b(boolean z7, c cVar) {
        Boolean bool;
        try {
            bool = (Boolean) cVar.a(CameraCharacteristics.FLASH_INFO_AVAILABLE);
        } catch (BufferUnderflowException e8) {
            if (androidx.camera.camera2.internal.compat.quirk.b.b(FlashAvailabilityBufferUnderflowQuirk.class) != null) {
                f1.a("FlashAvailability", String.format("Device is known to throw an exception while checking flash availability. Flash is not available. [Manufacturer: %s, Model: %s, API Level: %d].", Build.MANUFACTURER, Build.MODEL, Integer.valueOf(Build.VERSION.SDK_INT)));
            } else {
                f1.d("FlashAvailability", String.format("Exception thrown while checking for flash availability on device not known to throw exceptions during this check. Please file an issue at https://issuetracker.google.com/issues/new?component=618491&template=1257717 with this error message [Manufacturer: %s, Model: %s, API Level: %d].\nFlash is not available.", Build.MANUFACTURER, Build.MODEL, Integer.valueOf(Build.VERSION.SDK_INT)), e8);
            }
            if (z7) {
                throw e8;
            }
            bool = Boolean.FALSE;
        }
        if (bool == null) {
            f1.l("FlashAvailability", "Characteristics did not contain key FLASH_INFO_AVAILABLE. Flash is not available.");
        }
        if (bool != null) {
            return bool.booleanValue();
        }
        return false;
    }
}
