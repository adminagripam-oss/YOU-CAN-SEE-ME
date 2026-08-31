package y0;

import android.media.MediaCodecInfo;
import android.util.Range;
import java.util.Objects;
/* loaded from: classes.dex */
public class s1 extends f1 implements q1 {

    /* renamed from: d  reason: collision with root package name */
    public static final m.a f13176d = new m.a() { // from class: y0.r1
        @Override // m.a
        public final Object apply(Object obj) {
            q1 m8;
            m8 = s1.m((o1) obj);
            return m8;
        }
    };

    /* renamed from: c  reason: collision with root package name */
    private final MediaCodecInfo.VideoCapabilities f13177c;

    /* JADX INFO: Access modifiers changed from: package-private */
    public s1(MediaCodecInfo mediaCodecInfo, String str) {
        super(mediaCodecInfo, str);
        MediaCodecInfo.VideoCapabilities videoCapabilities = this.f13055b.getVideoCapabilities();
        Objects.requireNonNull(videoCapabilities);
        this.f13177c = videoCapabilities;
    }

    public static s1 l(o1 o1Var) {
        return new s1(z0.a.c(o1Var), o1Var.c());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ q1 m(o1 o1Var) {
        try {
            return a1.e.l(l(o1Var), null);
        } catch (j1 e8) {
            y.f1.m("VideoEncoderInfoImpl", "Unable to find a VideoEncoderInfoImpl", e8);
            return null;
        }
    }

    private static IllegalArgumentException n(Throwable th) {
        return th instanceof IllegalArgumentException ? (IllegalArgumentException) th : new IllegalArgumentException(th);
    }

    @Override // y0.q1
    public int b() {
        return this.f13177c.getWidthAlignment();
    }

    @Override // y0.q1
    public Range c() {
        return this.f13177c.getBitrateRange();
    }

    @Override // y0.q1
    public Range d(int i8) {
        try {
            return this.f13177c.getSupportedWidthsFor(i8);
        } catch (Throwable th) {
            throw n(th);
        }
    }

    @Override // y0.q1
    public Range e(int i8) {
        try {
            return this.f13177c.getSupportedHeightsFor(i8);
        } catch (Throwable th) {
            throw n(th);
        }
    }

    @Override // y0.q1
    public int f() {
        return this.f13177c.getHeightAlignment();
    }

    @Override // y0.q1
    public Range g() {
        return this.f13177c.getSupportedWidths();
    }

    @Override // y0.q1
    public boolean h(int i8, int i9) {
        return this.f13177c.isSizeSupported(i8, i9);
    }

    @Override // y0.q1
    public boolean i() {
        return true;
    }

    @Override // y0.q1
    public Range j() {
        return this.f13177c.getSupportedHeights();
    }
}
