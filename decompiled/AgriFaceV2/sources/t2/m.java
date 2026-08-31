package t2;

import java.util.List;
import t2.g;
/* loaded from: classes.dex */
public abstract class m {

    /* loaded from: classes.dex */
    public static abstract class a {
        public abstract m a();

        public abstract a b(k kVar);

        public abstract a c(List list);

        abstract a d(Integer num);

        abstract a e(String str);

        public abstract a f(p pVar);

        public abstract a g(long j8);

        public abstract a h(long j8);

        public a i(int i8) {
            return d(Integer.valueOf(i8));
        }

        public a j(String str) {
            return e(str);
        }
    }

    public static a a() {
        return new g.b();
    }

    public abstract k b();

    public abstract List c();

    public abstract Integer d();

    public abstract String e();

    public abstract p f();

    public abstract long g();

    public abstract long h();
}
