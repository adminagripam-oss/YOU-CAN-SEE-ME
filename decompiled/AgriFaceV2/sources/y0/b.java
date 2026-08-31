package y0;

import android.media.MediaCodecInfo;
import java.util.Objects;
/* loaded from: classes.dex */
public class b extends f1 {

    /* renamed from: c  reason: collision with root package name */
    private final MediaCodecInfo.AudioCapabilities f13006c;

    /* JADX INFO: Access modifiers changed from: package-private */
    public b(MediaCodecInfo mediaCodecInfo, String str) {
        super(mediaCodecInfo, str);
        MediaCodecInfo.AudioCapabilities audioCapabilities = this.f13055b.getAudioCapabilities();
        Objects.requireNonNull(audioCapabilities);
        this.f13006c = audioCapabilities;
    }
}
