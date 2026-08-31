package p7;

import java.util.Collection;
import java.util.List;
import java.util.ServiceLoader;
/* loaded from: classes.dex */
public abstract class g {

    /* renamed from: a  reason: collision with root package name */
    private static final Collection f10145a;

    static {
        i7.b a8;
        List c8;
        a8 = i7.f.a(ServiceLoader.load(k7.b0.class, k7.b0.class.getClassLoader()).iterator());
        c8 = i7.h.c(a8);
        f10145a = c8;
    }

    public static final Collection a() {
        return f10145a;
    }

    public static final void b(Throwable th) {
        Thread currentThread = Thread.currentThread();
        currentThread.getUncaughtExceptionHandler().uncaughtException(currentThread, th);
    }
}
