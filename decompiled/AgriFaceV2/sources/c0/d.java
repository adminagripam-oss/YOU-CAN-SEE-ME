package c0;

import android.media.CamcorderProfile;
import b0.h1;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes.dex */
abstract class d {
    public static h1 a(CamcorderProfile camcorderProfile) {
        return h1.b.h(camcorderProfile.duration, camcorderProfile.fileFormat, b(camcorderProfile), c(camcorderProfile));
    }

    private static List b(CamcorderProfile camcorderProfile) {
        ArrayList arrayList = new ArrayList();
        int i8 = camcorderProfile.audioCodec;
        arrayList.add(h1.a.a(i8, h1.d(i8), camcorderProfile.audioBitRate, camcorderProfile.audioSampleRate, camcorderProfile.audioChannels, h1.e(camcorderProfile.audioCodec)));
        return arrayList;
    }

    private static List c(CamcorderProfile camcorderProfile) {
        ArrayList arrayList = new ArrayList();
        int i8 = camcorderProfile.videoCodec;
        arrayList.add(h1.c.a(i8, h1.f(i8), camcorderProfile.videoBitRate, camcorderProfile.videoFrameRate, camcorderProfile.videoFrameWidth, camcorderProfile.videoFrameHeight, -1, 8, 0, 0));
        return arrayList;
    }
}
