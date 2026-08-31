package y0;

import android.media.MediaFormat;
import b0.z2;
import java.util.Objects;
import y0.c;
/* loaded from: classes.dex */
public abstract class a implements n {

    /* renamed from: y0.a$a  reason: collision with other inner class name */
    /* loaded from: classes.dex */
    public static abstract class AbstractC0165a {
        abstract a a();

        public a b() {
            a a8 = a();
            if (Objects.equals(a8.c(), "audio/mp4a-latm") && a8.g() == -1) {
                throw new IllegalArgumentException("Encoder mime set to AAC, but no AAC profile was provided.");
            }
            return a8;
        }

        public abstract AbstractC0165a c(int i8);

        public abstract AbstractC0165a d(int i8);

        public abstract AbstractC0165a e(z2 z2Var);

        public abstract AbstractC0165a f(String str);

        public abstract AbstractC0165a g(int i8);

        public abstract AbstractC0165a h(int i8);
    }

    public static AbstractC0165a d() {
        return new c.b().g(-1);
    }

    @Override // y0.n
    public abstract z2 a();

    @Override // y0.n
    public MediaFormat b() {
        MediaFormat createAudioFormat = MediaFormat.createAudioFormat(c(), h(), f());
        createAudioFormat.setInteger("bitrate", e());
        if (g() != -1) {
            createAudioFormat.setInteger(c().equals("audio/mp4a-latm") ? "aac-profile" : "profile", g());
        }
        return createAudioFormat;
    }

    @Override // y0.n
    public abstract String c();

    public abstract int e();

    public abstract int f();

    public abstract int g();

    public abstract int h();
}
