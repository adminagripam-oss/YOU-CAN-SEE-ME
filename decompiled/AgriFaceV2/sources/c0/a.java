package c0;

import android.media.CamcorderProfile;
import android.media.EncoderProfiles;
import android.os.Build;
import b0.h1;
import y.f1;
/* loaded from: classes.dex */
public abstract class a {
    public static h1 a(CamcorderProfile camcorderProfile) {
        int i8 = Build.VERSION.SDK_INT;
        if (i8 >= 31) {
            f1.l("EncoderProfilesProxyCompat", "Should use from(EncoderProfiles) on API " + i8 + "instead. CamcorderProfile is deprecated on API 31.");
        }
        return d.a(camcorderProfile);
    }

    public static h1 b(EncoderProfiles encoderProfiles) {
        int i8 = Build.VERSION.SDK_INT;
        if (i8 >= 33) {
            return c.a(encoderProfiles);
        }
        if (i8 >= 31) {
            return b.a(encoderProfiles);
        }
        throw new RuntimeException("Unable to call from(EncoderProfiles) on API " + i8 + ". Version 31 or higher required.");
    }
}
