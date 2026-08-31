package z0;

import android.media.MediaCodec;
import android.media.MediaCodecInfo;
import android.util.LruCache;
import java.io.IOException;
import y0.j1;
import y0.n;
/* loaded from: classes.dex */
public abstract class a {

    /* renamed from: a  reason: collision with root package name */
    private static final LruCache f13367a = new LruCache(10);

    private static MediaCodec a(String str) {
        try {
            return MediaCodec.createEncoderByType(str);
        } catch (IOException | IllegalArgumentException e8) {
            throw new j1(e8);
        }
    }

    public static MediaCodec b(n nVar) {
        return a(nVar.c());
    }

    public static MediaCodecInfo c(n nVar) {
        MediaCodecInfo mediaCodecInfo;
        MediaCodec mediaCodec;
        String c8 = nVar.c();
        LruCache lruCache = f13367a;
        synchronized (lruCache) {
            mediaCodecInfo = (MediaCodecInfo) lruCache.get(c8);
        }
        if (mediaCodecInfo != null) {
            return mediaCodecInfo;
        }
        try {
            mediaCodec = a(c8);
            try {
                MediaCodecInfo codecInfo = mediaCodec.getCodecInfo();
                synchronized (lruCache) {
                    lruCache.put(c8, codecInfo);
                }
                mediaCodec.release();
                return codecInfo;
            } catch (Throwable th) {
                th = th;
                if (mediaCodec != null) {
                    mediaCodec.release();
                }
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
            mediaCodec = null;
        }
    }
}
