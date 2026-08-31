package b0;

import android.util.Log;
import android.util.Size;
import androidx.concurrent.futures.c;
import java.util.concurrent.atomic.AtomicInteger;
/* loaded from: classes.dex */
public abstract class b1 {

    /* renamed from: k  reason: collision with root package name */
    public static final Size f4138k = new Size(0, 0);

    /* renamed from: l  reason: collision with root package name */
    private static final boolean f4139l = y.f1.f("DeferrableSurface");

    /* renamed from: m  reason: collision with root package name */
    private static final AtomicInteger f4140m = new AtomicInteger(0);

    /* renamed from: n  reason: collision with root package name */
    private static final AtomicInteger f4141n = new AtomicInteger(0);

    /* renamed from: a  reason: collision with root package name */
    private final Object f4142a;

    /* renamed from: b  reason: collision with root package name */
    private int f4143b;

    /* renamed from: c  reason: collision with root package name */
    private boolean f4144c;

    /* renamed from: d  reason: collision with root package name */
    private c.a f4145d;

    /* renamed from: e  reason: collision with root package name */
    private final i4.d f4146e;

    /* renamed from: f  reason: collision with root package name */
    private c.a f4147f;

    /* renamed from: g  reason: collision with root package name */
    private final i4.d f4148g;

    /* renamed from: h  reason: collision with root package name */
    private final Size f4149h;

    /* renamed from: i  reason: collision with root package name */
    private final int f4150i;

    /* renamed from: j  reason: collision with root package name */
    Class f4151j;

    /* loaded from: classes.dex */
    public static final class a extends Exception {

        /* renamed from: e  reason: collision with root package name */
        b1 f4152e;

        public a(String str, b1 b1Var) {
            super(str);
            this.f4152e = b1Var;
        }

        public b1 a() {
            return this.f4152e;
        }
    }

    /* loaded from: classes.dex */
    public static final class b extends Exception {
        public b(String str) {
            super(str);
        }
    }

    public b1() {
        this(f4138k, 0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ Object n(c.a aVar) {
        synchronized (this.f4142a) {
            this.f4145d = aVar;
        }
        return "DeferrableSurface-termination(" + this + ")";
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ Object o(c.a aVar) {
        synchronized (this.f4142a) {
            this.f4147f = aVar;
        }
        return "DeferrableSurface-close(" + this + ")";
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void p(String str) {
        try {
            this.f4146e.get();
            q("Surface terminated", f4141n.decrementAndGet(), f4140m.get());
        } catch (Exception e8) {
            y.f1.c("DeferrableSurface", "Unexpected surface termination for " + this + "\nStack Trace:\n" + str);
            synchronized (this.f4142a) {
                throw new IllegalArgumentException(String.format("DeferrableSurface %s [closed: %b, use_count: %s] terminated with unexpected exception.", this, Boolean.valueOf(this.f4144c), Integer.valueOf(this.f4143b)), e8);
            }
        }
    }

    private void q(String str, int i8, int i9) {
        if (!f4139l && y.f1.f("DeferrableSurface")) {
            y.f1.a("DeferrableSurface", "DeferrableSurface usage statistics may be inaccurate since debug logging was not enabled at static initialization time. App restart may be required to enable accurate usage statistics.");
        }
        y.f1.a("DeferrableSurface", str + "[total_surfaces=" + i8 + ", used_surfaces=" + i9 + "](" + this + "}");
    }

    public void d() {
        c.a aVar;
        synchronized (this.f4142a) {
            if (this.f4144c) {
                aVar = null;
            } else {
                this.f4144c = true;
                this.f4147f.c(null);
                if (this.f4143b == 0) {
                    aVar = this.f4145d;
                    this.f4145d = null;
                } else {
                    aVar = null;
                }
                if (y.f1.f("DeferrableSurface")) {
                    y.f1.a("DeferrableSurface", "surface closed,  useCount=" + this.f4143b + " closed=true " + this);
                }
            }
        }
        if (aVar != null) {
            aVar.c(null);
        }
    }

    public void e() {
        c.a aVar;
        synchronized (this.f4142a) {
            int i8 = this.f4143b;
            if (i8 == 0) {
                throw new IllegalStateException("Decrementing use count occurs more times than incrementing");
            }
            int i9 = i8 - 1;
            this.f4143b = i9;
            if (i9 == 0 && this.f4144c) {
                aVar = this.f4145d;
                this.f4145d = null;
            } else {
                aVar = null;
            }
            if (y.f1.f("DeferrableSurface")) {
                y.f1.a("DeferrableSurface", "use count-1,  useCount=" + this.f4143b + " closed=" + this.f4144c + " " + this);
                if (this.f4143b == 0) {
                    q("Surface no longer in use", f4141n.get(), f4140m.decrementAndGet());
                }
            }
        }
        if (aVar != null) {
            aVar.c(null);
        }
    }

    public i4.d f() {
        return f0.n.B(this.f4148g);
    }

    public Class g() {
        return this.f4151j;
    }

    public Size h() {
        return this.f4149h;
    }

    public int i() {
        return this.f4150i;
    }

    public final i4.d j() {
        synchronized (this.f4142a) {
            if (this.f4144c) {
                return f0.n.n(new a("DeferrableSurface already closed.", this));
            }
            return r();
        }
    }

    public i4.d k() {
        return f0.n.B(this.f4146e);
    }

    public void l() {
        synchronized (this.f4142a) {
            int i8 = this.f4143b;
            if (i8 == 0 && this.f4144c) {
                throw new a("Cannot begin use on a closed surface.", this);
            }
            this.f4143b = i8 + 1;
            if (y.f1.f("DeferrableSurface")) {
                if (this.f4143b == 1) {
                    q("New surface in use", f4141n.get(), f4140m.incrementAndGet());
                }
                y.f1.a("DeferrableSurface", "use count+1, useCount=" + this.f4143b + " " + this);
            }
        }
    }

    public boolean m() {
        boolean z7;
        synchronized (this.f4142a) {
            z7 = this.f4144c;
        }
        return z7;
    }

    protected abstract i4.d r();

    public void s(Class cls) {
        this.f4151j = cls;
    }

    public b1(Size size, int i8) {
        this.f4142a = new Object();
        this.f4143b = 0;
        this.f4144c = false;
        this.f4149h = size;
        this.f4150i = i8;
        i4.d a8 = androidx.concurrent.futures.c.a(new c.InterfaceC0018c() { // from class: b0.y0
            @Override // androidx.concurrent.futures.c.InterfaceC0018c
            public final Object a(c.a aVar) {
                Object n8;
                n8 = b1.this.n(aVar);
                return n8;
            }
        });
        this.f4146e = a8;
        this.f4148g = androidx.concurrent.futures.c.a(new c.InterfaceC0018c() { // from class: b0.z0
            @Override // androidx.concurrent.futures.c.InterfaceC0018c
            public final Object a(c.a aVar) {
                Object o8;
                o8 = b1.this.o(aVar);
                return o8;
            }
        });
        if (y.f1.f("DeferrableSurface")) {
            q("Surface created", f4141n.incrementAndGet(), f4140m.get());
            final String stackTraceString = Log.getStackTraceString(new Exception());
            a8.d(new Runnable() { // from class: b0.a1
                @Override // java.lang.Runnable
                public final void run() {
                    b1.this.p(stackTraceString);
                }
            }, e0.c.b());
        }
    }
}
