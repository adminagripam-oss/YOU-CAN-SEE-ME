package r0;

import android.util.Range;
import java.util.Arrays;
import r0.n;
/* loaded from: classes.dex */
public abstract class d2 {

    /* renamed from: a  reason: collision with root package name */
    public static final Range f11032a = new Range(0, Integer.MAX_VALUE);

    /* renamed from: b  reason: collision with root package name */
    public static final Range f11033b = new Range(0, Integer.MAX_VALUE);

    /* renamed from: c  reason: collision with root package name */
    public static final y f11034c;

    /* loaded from: classes.dex */
    public static abstract class a {
        public abstract d2 a();

        /* JADX INFO: Access modifiers changed from: package-private */
        public abstract a b(int i8);

        public abstract a c(Range range);

        public abstract a d(Range range);

        public abstract a e(y yVar);
    }

    static {
        v vVar = v.f11269c;
        f11034c = y.g(Arrays.asList(vVar, v.f11268b, v.f11267a), p.a(vVar));
    }

    public static a a() {
        return new n.b().e(f11034c).d(f11032a).c(f11033b).b(-1);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract int b();

    public abstract Range c();

    public abstract Range d();

    public abstract y e();

    public abstract a f();
}
