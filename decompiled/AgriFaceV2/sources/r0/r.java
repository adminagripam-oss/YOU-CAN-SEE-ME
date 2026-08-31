package r0;

import java.util.Objects;
import r0.d2;
import r0.g;
/* loaded from: classes.dex */
public abstract class r {

    /* loaded from: classes.dex */
    public static abstract class a {
        public abstract r a();

        public a b(g1.a aVar) {
            d2.a f8 = c().f();
            aVar.accept(f8);
            f(f8.a());
            return this;
        }

        abstract d2 c();

        public abstract a d(r0.a aVar);

        public abstract a e(int i8);

        public abstract a f(d2 d2Var);
    }

    public static a a() {
        return new g.b().e(-1).d(r0.a.a().a()).f(d2.a().a());
    }

    public static String e(int i8) {
        return i8 != 1 ? "audio/mp4a-latm" : "audio/vorbis";
    }

    public static int f(int i8) {
        return Objects.equals(e(i8), "audio/mp4a-latm") ? 2 : -1;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int g(int i8) {
        return i8 != 1 ? 0 : 1;
    }

    public static String h(int i8) {
        return i8 != 1 ? "video/avc" : "video/x-vnd.on2.vp8";
    }

    public abstract r0.a b();

    public abstract int c();

    public abstract d2 d();

    public abstract a i();
}
