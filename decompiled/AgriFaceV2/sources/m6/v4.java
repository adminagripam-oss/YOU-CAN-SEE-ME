package m6;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.WeakHashMap;
/* loaded from: classes.dex */
public class v4 {

    /* renamed from: a  reason: collision with root package name */
    private final WeakHashMap f9596a = new WeakHashMap();

    /* renamed from: b  reason: collision with root package name */
    private final HashMap f9597b = new HashMap();

    /* renamed from: c  reason: collision with root package name */
    private final HashMap f9598c = new HashMap();

    /* renamed from: d  reason: collision with root package name */
    private final ReferenceQueue f9599d = new ReferenceQueue();

    /* renamed from: e  reason: collision with root package name */
    private final HashMap f9600e = new HashMap();

    /* renamed from: f  reason: collision with root package name */
    private final Handler f9601f;

    /* renamed from: g  reason: collision with root package name */
    private final a f9602g;

    /* renamed from: h  reason: collision with root package name */
    private long f9603h;

    /* renamed from: i  reason: collision with root package name */
    private boolean f9604i;

    /* renamed from: j  reason: collision with root package name */
    private long f9605j;

    /* loaded from: classes.dex */
    public interface a {
        void a(long j8);
    }

    private v4(a aVar) {
        Handler handler = new Handler(Looper.getMainLooper());
        this.f9601f = handler;
        this.f9603h = 65536L;
        this.f9604i = false;
        this.f9605j = 3000L;
        this.f9602g = aVar;
        handler.postDelayed(new u4(this), this.f9605j);
    }

    private void c(Object obj, long j8) {
        if (j8 < 0) {
            throw new IllegalArgumentException(String.format("Identifier must be >= 0: %d", Long.valueOf(j8)));
        }
        if (this.f9597b.containsKey(Long.valueOf(j8))) {
            throw new IllegalArgumentException(String.format("Identifier has already been added: %d", Long.valueOf(j8)));
        }
        WeakReference weakReference = new WeakReference(obj, this.f9599d);
        this.f9596a.put(obj, Long.valueOf(j8));
        this.f9597b.put(Long.valueOf(j8), weakReference);
        this.f9600e.put(weakReference, Long.valueOf(j8));
        this.f9598c.put(Long.valueOf(j8), obj);
    }

    public static v4 f(a aVar) {
        return new v4(aVar);
    }

    private void j() {
        if (i()) {
            Log.w("InstanceManager", "The manager was used after calls to the FinalizationListener have been stopped.");
        }
    }

    public void a(Object obj, long j8) {
        j();
        c(obj, j8);
    }

    public long b(Object obj) {
        j();
        if (!e(obj)) {
            long j8 = this.f9603h;
            this.f9603h = 1 + j8;
            c(obj, j8);
            return j8;
        }
        throw new IllegalArgumentException("Instance of " + obj.getClass() + " has already been added.");
    }

    public void d() {
        this.f9596a.clear();
        this.f9597b.clear();
        this.f9598c.clear();
        this.f9600e.clear();
    }

    public boolean e(Object obj) {
        j();
        return this.f9596a.containsKey(obj);
    }

    public Long g(Object obj) {
        j();
        Long l8 = (Long) this.f9596a.get(obj);
        if (l8 != null) {
            this.f9598c.put(l8, obj);
        }
        return l8;
    }

    public Object h(long j8) {
        j();
        WeakReference weakReference = (WeakReference) this.f9597b.get(Long.valueOf(j8));
        if (weakReference != null) {
            return weakReference.get();
        }
        return null;
    }

    public boolean i() {
        return this.f9604i;
    }

    public void k() {
        if (i()) {
            return;
        }
        while (true) {
            WeakReference weakReference = (WeakReference) this.f9599d.poll();
            if (weakReference == null) {
                this.f9601f.postDelayed(new u4(this), this.f9605j);
                return;
            }
            Long l8 = (Long) this.f9600e.remove(weakReference);
            if (l8 != null) {
                this.f9597b.remove(l8);
                this.f9598c.remove(l8);
                this.f9602g.a(l8.longValue());
            }
        }
    }

    public Object l(long j8) {
        j();
        return this.f9598c.remove(Long.valueOf(j8));
    }

    public void m(long j8) {
        this.f9605j = j8;
    }

    public void n() {
        this.f9601f.removeCallbacks(new u4(this));
        this.f9604i = true;
    }
}
