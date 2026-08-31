package y0;

import android.media.MediaCodec;
import java.nio.ByteBuffer;
/* loaded from: classes.dex */
public interface i extends AutoCloseable {
    MediaCodec.BufferInfo F();

    boolean I();

    ByteBuffer a();

    @Override // java.lang.AutoCloseable
    void close();

    long size();

    long y();
}
