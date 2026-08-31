package r0;

import android.util.Range;
import r0.c;
/* loaded from: classes.dex */
public abstract class a {

    /* renamed from: a  reason: collision with root package name */
    public static final Range f10966a = new Range(0, Integer.MAX_VALUE);

    /* renamed from: b  reason: collision with root package name */
    public static final Range f10967b = new Range(0, Integer.MAX_VALUE);

    /* renamed from: c  reason: collision with root package name */
    public static final a f10968c = a().c(0).a();

    /* renamed from: r0.a$a  reason: collision with other inner class name */
    /* loaded from: classes.dex */
    public static abstract class AbstractC0144a {
        public abstract a a();

        public abstract AbstractC0144a b(Range range);

        public abstract AbstractC0144a c(int i8);

        public abstract AbstractC0144a d(Range range);

        public abstract AbstractC0144a e(int i8);
    }

    public static AbstractC0144a a() {
        return new c.b().f(-1).e(-1).c(-1).b(f10966a).d(f10967b);
    }

    public abstract Range b();

    public abstract int c();

    public abstract Range d();

    public abstract int e();

    public abstract int f();
}
