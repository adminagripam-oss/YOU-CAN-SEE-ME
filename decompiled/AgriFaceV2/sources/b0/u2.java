package b0;

import android.util.Range;
import android.util.Size;
import b0.k;
/* loaded from: classes.dex */
public abstract class u2 {

    /* renamed from: a  reason: collision with root package name */
    public static final Range f4433a = new Range(0, 0);

    /* loaded from: classes.dex */
    public static abstract class a {
        public abstract u2 a();

        public abstract a b(y.a0 a0Var);

        public abstract a c(Range range);

        public abstract a d(u0 u0Var);

        public abstract a e(Size size);

        public abstract a f(boolean z7);
    }

    public static a a(Size size) {
        return new k.b().e(size).c(f4433a).b(y.a0.f12730d).f(false);
    }

    public abstract y.a0 b();

    public abstract Range c();

    public abstract u0 d();

    public abstract Size e();

    public abstract boolean f();

    public abstract a g();
}
