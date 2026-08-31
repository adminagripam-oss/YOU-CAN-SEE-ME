package y0;

import android.media.MediaFormat;
import android.util.Size;
import b0.z2;
import y0.d;
/* loaded from: classes.dex */
public abstract class o1 implements n {

    /* loaded from: classes.dex */
    public static abstract class a {
        public abstract o1 a();

        public abstract a b(int i8);

        public abstract a c(int i8);

        public abstract a d(p1 p1Var);

        public abstract a e(int i8);

        public abstract a f(int i8);

        public abstract a g(z2 z2Var);

        public abstract a h(String str);

        public abstract a i(int i8);

        public abstract a j(Size size);
    }

    public static a d() {
        return new d.b().i(-1).f(1).c(2130708361).d(p1.f13161a);
    }

    @Override // y0.n
    public abstract z2 a();

    @Override // y0.n
    public MediaFormat b() {
        Size k8 = k();
        MediaFormat createVideoFormat = MediaFormat.createVideoFormat(c(), k8.getWidth(), k8.getHeight());
        createVideoFormat.setInteger("color-format", f());
        createVideoFormat.setInteger("bitrate", e());
        createVideoFormat.setInteger("frame-rate", h());
        createVideoFormat.setInteger("i-frame-interval", i());
        if (j() != -1) {
            createVideoFormat.setInteger("profile", j());
        }
        p1 g8 = g();
        if (g8.c() != 0) {
            createVideoFormat.setInteger("color-standard", g8.c());
        }
        if (g8.d() != 0) {
            createVideoFormat.setInteger("color-transfer", g8.d());
        }
        if (g8.b() != 0) {
            createVideoFormat.setInteger("color-range", g8.b());
        }
        return createVideoFormat;
    }

    @Override // y0.n
    public abstract String c();

    public abstract int e();

    public abstract int f();

    public abstract p1 g();

    public abstract int h();

    public abstract int i();

    public abstract int j();

    public abstract Size k();
}
