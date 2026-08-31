package y0;

import android.media.MediaCodecInfo;
import java.util.Objects;
/* loaded from: classes.dex */
public abstract class f1 implements e1 {

    /* renamed from: a  reason: collision with root package name */
    private final MediaCodecInfo f13054a;

    /* renamed from: b  reason: collision with root package name */
    protected final MediaCodecInfo.CodecCapabilities f13055b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public f1(MediaCodecInfo mediaCodecInfo, String str) {
        this.f13054a = mediaCodecInfo;
        try {
            MediaCodecInfo.CodecCapabilities capabilitiesForType = mediaCodecInfo.getCapabilitiesForType(str);
            Objects.requireNonNull(capabilitiesForType);
            this.f13055b = capabilitiesForType;
        } catch (RuntimeException e8) {
            throw new j1("Unable to get CodecCapabilities for mime: " + str, e8);
        }
    }
}
