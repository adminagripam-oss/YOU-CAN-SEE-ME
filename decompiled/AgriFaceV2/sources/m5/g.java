package m5;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
/* loaded from: classes.dex */
public class g {

    /* renamed from: b  reason: collision with root package name */
    private static final Object f9148b = new Object();

    /* renamed from: c  reason: collision with root package name */
    private static g f9149c;

    /* renamed from: a  reason: collision with root package name */
    private final Handler f9150a;

    private g(Looper looper) {
        this.f9150a = new y3.a(looper);
    }

    public static g a() {
        g gVar;
        synchronized (f9148b) {
            if (f9149c == null) {
                HandlerThread handlerThread = new HandlerThread("MLHandler", 9);
                handlerThread.start();
                f9149c = new g(handlerThread.getLooper());
            }
            gVar = f9149c;
        }
        return gVar;
    }

    public static Executor d() {
        return t.f9198e;
    }

    public f4.j b(final Callable callable) {
        final f4.k kVar = new f4.k();
        c(new Runnable() { // from class: m5.s
            @Override // java.lang.Runnable
            public final void run() {
                Callable callable2 = callable;
                f4.k kVar2 = kVar;
                try {
                    kVar2.c(callable2.call());
                } catch (i5.a e8) {
                    kVar2.b(e8);
                } catch (Exception e9) {
                    kVar2.b(new i5.a("Internal error has occurred when executing ML Kit tasks", 13, e9));
                }
            }
        });
        return kVar.a();
    }

    public void c(Runnable runnable) {
        d().execute(runnable);
    }
}
