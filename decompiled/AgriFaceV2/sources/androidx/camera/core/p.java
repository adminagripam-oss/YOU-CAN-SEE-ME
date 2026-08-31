package androidx.camera.core;

import android.media.ImageReader;
import android.util.LongSparseArray;
import android.view.Surface;
import androidx.camera.core.e;
import b0.q1;
import b0.x;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import y.f1;
import y.w0;
/* loaded from: classes.dex */
public class p implements q1, e.a {

    /* renamed from: a  reason: collision with root package name */
    private final Object f2746a;

    /* renamed from: b  reason: collision with root package name */
    private b0.n f2747b;

    /* renamed from: c  reason: collision with root package name */
    private int f2748c;

    /* renamed from: d  reason: collision with root package name */
    private q1.a f2749d;

    /* renamed from: e  reason: collision with root package name */
    private boolean f2750e;

    /* renamed from: f  reason: collision with root package name */
    private final q1 f2751f;

    /* renamed from: g  reason: collision with root package name */
    q1.a f2752g;

    /* renamed from: h  reason: collision with root package name */
    private Executor f2753h;

    /* renamed from: i  reason: collision with root package name */
    private final LongSparseArray f2754i;

    /* renamed from: j  reason: collision with root package name */
    private final LongSparseArray f2755j;

    /* renamed from: k  reason: collision with root package name */
    private int f2756k;

    /* renamed from: l  reason: collision with root package name */
    private final List f2757l;

    /* renamed from: m  reason: collision with root package name */
    private final List f2758m;

    /* loaded from: classes.dex */
    class a extends b0.n {
        a() {
        }

        @Override // b0.n
        public void b(int i8, x xVar) {
            super.b(i8, xVar);
            p.this.r(xVar);
        }
    }

    public p(int i8, int i9, int i10, int i11) {
        this(i(i8, i9, i10, i11));
    }

    private static q1 i(int i8, int i9, int i10, int i11) {
        return new d(ImageReader.newInstance(i8, i9, i10, i11));
    }

    private void j(n nVar) {
        synchronized (this.f2746a) {
            int indexOf = this.f2757l.indexOf(nVar);
            if (indexOf >= 0) {
                this.f2757l.remove(indexOf);
                int i8 = this.f2756k;
                if (indexOf <= i8) {
                    this.f2756k = i8 - 1;
                }
            }
            this.f2758m.remove(nVar);
            if (this.f2748c > 0) {
                m(this.f2751f);
            }
        }
    }

    private void k(r rVar) {
        final q1.a aVar;
        Executor executor;
        synchronized (this.f2746a) {
            if (this.f2757l.size() < e()) {
                rVar.b(this);
                this.f2757l.add(rVar);
                aVar = this.f2752g;
                executor = this.f2753h;
            } else {
                f1.a("TAG", "Maximum image number reached.");
                rVar.close();
                aVar = null;
                executor = null;
            }
        }
        if (aVar != null) {
            if (executor != null) {
                executor.execute(new Runnable() { // from class: y.g1
                    @Override // java.lang.Runnable
                    public final void run() {
                        androidx.camera.core.p.this.n(aVar);
                    }
                });
            } else {
                aVar.a(this);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void n(q1.a aVar) {
        aVar.a(this);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void o(q1 q1Var) {
        synchronized (this.f2746a) {
            this.f2748c++;
        }
        m(q1Var);
    }

    private void p() {
        synchronized (this.f2746a) {
            for (int size = this.f2754i.size() - 1; size >= 0; size--) {
                w0 w0Var = (w0) this.f2754i.valueAt(size);
                long c8 = w0Var.c();
                n nVar = (n) this.f2755j.get(c8);
                if (nVar != null) {
                    this.f2755j.remove(c8);
                    this.f2754i.removeAt(size);
                    k(new r(nVar, w0Var));
                }
            }
            q();
        }
    }

    private void q() {
        synchronized (this.f2746a) {
            if (this.f2755j.size() != 0 && this.f2754i.size() != 0) {
                Long valueOf = Long.valueOf(this.f2755j.keyAt(0));
                Long valueOf2 = Long.valueOf(this.f2754i.keyAt(0));
                g1.e.a(valueOf2.equals(valueOf) ? false : true);
                if (valueOf2.longValue() > valueOf.longValue()) {
                    for (int size = this.f2755j.size() - 1; size >= 0; size--) {
                        if (this.f2755j.keyAt(size) < valueOf2.longValue()) {
                            ((n) this.f2755j.valueAt(size)).close();
                            this.f2755j.removeAt(size);
                        }
                    }
                } else {
                    for (int size2 = this.f2754i.size() - 1; size2 >= 0; size2--) {
                        if (this.f2754i.keyAt(size2) < valueOf.longValue()) {
                            this.f2754i.removeAt(size2);
                        }
                    }
                }
            }
        }
    }

    @Override // b0.q1
    public int a() {
        int a8;
        synchronized (this.f2746a) {
            a8 = this.f2751f.a();
        }
        return a8;
    }

    @Override // b0.q1
    public n acquireLatestImage() {
        synchronized (this.f2746a) {
            if (this.f2757l.isEmpty()) {
                return null;
            }
            if (this.f2756k < this.f2757l.size()) {
                ArrayList<n> arrayList = new ArrayList();
                for (int i8 = 0; i8 < this.f2757l.size() - 1; i8++) {
                    if (!this.f2758m.contains(this.f2757l.get(i8))) {
                        arrayList.add((n) this.f2757l.get(i8));
                    }
                }
                for (n nVar : arrayList) {
                    nVar.close();
                }
                int size = this.f2757l.size() - 1;
                List list = this.f2757l;
                this.f2756k = size + 1;
                n nVar2 = (n) list.get(size);
                this.f2758m.add(nVar2);
                return nVar2;
            }
            throw new IllegalStateException("Maximum image number reached.");
        }
    }

    @Override // androidx.camera.core.e.a
    public void b(n nVar) {
        synchronized (this.f2746a) {
            j(nVar);
        }
    }

    @Override // b0.q1
    public void c() {
        synchronized (this.f2746a) {
            this.f2751f.c();
            this.f2752g = null;
            this.f2753h = null;
            this.f2748c = 0;
        }
    }

    @Override // b0.q1
    public void close() {
        synchronized (this.f2746a) {
            if (this.f2750e) {
                return;
            }
            for (n nVar : new ArrayList(this.f2757l)) {
                nVar.close();
            }
            this.f2757l.clear();
            this.f2751f.close();
            this.f2750e = true;
        }
    }

    @Override // b0.q1
    public void d(q1.a aVar, Executor executor) {
        synchronized (this.f2746a) {
            this.f2752g = (q1.a) g1.e.j(aVar);
            this.f2753h = (Executor) g1.e.j(executor);
            this.f2751f.d(this.f2749d, executor);
        }
    }

    @Override // b0.q1
    public int e() {
        int e8;
        synchronized (this.f2746a) {
            e8 = this.f2751f.e();
        }
        return e8;
    }

    @Override // b0.q1
    public n f() {
        synchronized (this.f2746a) {
            if (this.f2757l.isEmpty()) {
                return null;
            }
            if (this.f2756k < this.f2757l.size()) {
                List list = this.f2757l;
                int i8 = this.f2756k;
                this.f2756k = i8 + 1;
                n nVar = (n) list.get(i8);
                this.f2758m.add(nVar);
                return nVar;
            }
            throw new IllegalStateException("Maximum image number reached.");
        }
    }

    @Override // b0.q1
    public int getHeight() {
        int height;
        synchronized (this.f2746a) {
            height = this.f2751f.getHeight();
        }
        return height;
    }

    @Override // b0.q1
    public Surface getSurface() {
        Surface surface;
        synchronized (this.f2746a) {
            surface = this.f2751f.getSurface();
        }
        return surface;
    }

    @Override // b0.q1
    public int getWidth() {
        int width;
        synchronized (this.f2746a) {
            width = this.f2751f.getWidth();
        }
        return width;
    }

    public b0.n l() {
        return this.f2747b;
    }

    void m(q1 q1Var) {
        n nVar;
        synchronized (this.f2746a) {
            if (this.f2750e) {
                return;
            }
            int size = this.f2755j.size() + this.f2757l.size();
            if (size >= q1Var.e()) {
                f1.a("MetadataImageReader", "Skip to acquire the next image because the acquired image count has reached the max images count.");
                return;
            }
            do {
                try {
                    nVar = q1Var.f();
                    if (nVar != null) {
                        this.f2748c--;
                        size++;
                        this.f2755j.put(nVar.p().c(), nVar);
                        p();
                    }
                } catch (IllegalStateException e8) {
                    f1.b("MetadataImageReader", "Failed to acquire next image.", e8);
                    nVar = null;
                }
                if (nVar == null || this.f2748c <= 0) {
                    break;
                }
            } while (size < q1Var.e());
        }
    }

    void r(x xVar) {
        synchronized (this.f2746a) {
            if (this.f2750e) {
                return;
            }
            this.f2754i.put(xVar.c(), new g0.c(xVar));
            p();
        }
    }

    p(q1 q1Var) {
        this.f2746a = new Object();
        this.f2747b = new a();
        this.f2748c = 0;
        this.f2749d = new q1.a() { // from class: y.h1
            @Override // b0.q1.a
            public final void a(b0.q1 q1Var2) {
                androidx.camera.core.p.this.o(q1Var2);
            }
        };
        this.f2750e = false;
        this.f2754i = new LongSparseArray();
        this.f2755j = new LongSparseArray();
        this.f2758m = new ArrayList();
        this.f2751f = q1Var;
        this.f2756k = 0;
        this.f2757l = new ArrayList(e());
    }
}
