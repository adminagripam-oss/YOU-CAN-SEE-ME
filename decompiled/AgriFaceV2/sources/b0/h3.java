package b0;

import android.content.Context;
/* loaded from: classes.dex */
public interface h3 {

    /* renamed from: a  reason: collision with root package name */
    public static final h3 f4218a = new a();

    /* loaded from: classes.dex */
    class a implements h3 {
        a() {
        }

        @Override // b0.h3
        public u0 a(b bVar, int i8) {
            return null;
        }
    }

    /* loaded from: classes.dex */
    public enum b {
        IMAGE_CAPTURE,
        PREVIEW,
        IMAGE_ANALYSIS,
        VIDEO_CAPTURE,
        STREAM_SHARING,
        METERING_REPEATING
    }

    /* loaded from: classes.dex */
    public interface c {
        h3 a(Context context);
    }

    u0 a(b bVar, int i8);
}
