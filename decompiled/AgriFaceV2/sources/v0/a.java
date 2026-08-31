package v0;

import android.media.AudioFormat;
import android.media.AudioRecord;
/* loaded from: classes.dex */
public abstract class a {
    public static AudioRecord a(AudioRecord.Builder builder) {
        return builder.build();
    }

    public static AudioRecord.Builder b() {
        return new AudioRecord.Builder();
    }

    public static void c(AudioRecord.Builder builder, AudioFormat audioFormat) {
        builder.setAudioFormat(audioFormat);
    }

    public static void d(AudioRecord.Builder builder, int i8) {
        builder.setAudioSource(i8);
    }

    public static void e(AudioRecord.Builder builder, int i8) {
        builder.setBufferSizeInBytes(i8);
    }
}
