package p5;

import androidx.lifecycle.i;
import androidx.lifecycle.l;
import androidx.lifecycle.u;
import f4.m;
import java.io.Closeable;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicBoolean;
import k3.j;
import k3.q;
import z3.jb;
/* loaded from: classes.dex */
public abstract class e implements Closeable, l {

    /* renamed from: j  reason: collision with root package name */
    private static final j f10122j = new j("MobileVisionBase", "");

    /* renamed from: k  reason: collision with root package name */
    public static final /* synthetic */ int f10123k = 0;

    /* renamed from: e  reason: collision with root package name */
    private final AtomicBoolean f10124e = new AtomicBoolean(false);

    /* renamed from: f  reason: collision with root package name */
    private final m5.f f10125f;

    /* renamed from: g  reason: collision with root package name */
    private final f4.b f10126g;

    /* renamed from: h  reason: collision with root package name */
    private final Executor f10127h;

    /* renamed from: i  reason: collision with root package name */
    private final f4.j f10128i;

    public e(m5.f fVar, Executor executor) {
        this.f10125f = fVar;
        f4.b bVar = new f4.b();
        this.f10126g = bVar;
        this.f10127h = executor;
        fVar.c();
        this.f10128i = fVar.a(executor, new Callable() { // from class: p5.g
            @Override // java.util.concurrent.Callable
            public final Object call() {
                int i8 = e.f10123k;
                return null;
            }
        }, bVar.b()).c(new f4.f() { // from class: p5.h
            @Override // f4.f
            public final void a(Exception exc) {
                e.f10122j.d("MobileVisionBase", "Error preloading model resource", exc);
            }
        });
    }

    public synchronized f4.j c(final o5.a aVar) {
        q.h(aVar, "InputImage can not be null");
        if (this.f10124e.get()) {
            return m.b(new i5.a("This detector is already closed!", 14));
        } else if (aVar.m() < 32 || aVar.i() < 32) {
            return m.b(new i5.a("InputImage width and height should be at least 32!", 3));
        } else {
            return this.f10125f.a(this.f10127h, new Callable() { // from class: p5.f
                @Override // java.util.concurrent.Callable
                public final Object call() {
                    return e.this.g(aVar);
                }
            }, this.f10126g.b());
        }
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    @u(i.a.ON_DESTROY)
    public synchronized void close() {
        if (this.f10124e.getAndSet(true)) {
            return;
        }
        this.f10126g.a();
        this.f10125f.e(this.f10127h);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final /* synthetic */ Object g(o5.a aVar) {
        jb g8 = jb.g("detectorTaskWithResource#run");
        g8.c();
        try {
            Object i8 = this.f10125f.i(aVar);
            g8.close();
            return i8;
        } catch (Throwable th) {
            try {
                g8.close();
            } catch (Throwable th2) {
                try {
                    Throwable.class.getDeclaredMethod("addSuppressed", Throwable.class).invoke(th, th2);
                } catch (Exception unused) {
                }
            }
            throw th;
        }
    }
}
