package q0;

import androidx.lifecycle.i;
import androidx.lifecycle.l;
import androidx.lifecycle.m;
import androidx.lifecycle.u;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import y.h2;
import y.j;
import y.k;
import y.p;
/* loaded from: classes.dex */
public final class b implements l, j {

    /* renamed from: f  reason: collision with root package name */
    private final m f10209f;

    /* renamed from: g  reason: collision with root package name */
    private final g0.f f10210g;

    /* renamed from: e  reason: collision with root package name */
    private final Object f10208e = new Object();

    /* renamed from: h  reason: collision with root package name */
    private volatile boolean f10211h = false;

    /* renamed from: i  reason: collision with root package name */
    private boolean f10212i = false;

    /* renamed from: j  reason: collision with root package name */
    private boolean f10213j = false;

    /* JADX INFO: Access modifiers changed from: package-private */
    public b(m mVar, g0.f fVar) {
        this.f10209f = mVar;
        this.f10210g = fVar;
        if (mVar.getLifecycle().b().e(i.b.STARTED)) {
            fVar.n();
        } else {
            fVar.z();
        }
        mVar.getLifecycle().a(this);
    }

    @Override // y.j
    public k a() {
        return this.f10210g.a();
    }

    @Override // y.j
    public p b() {
        return this.f10210g.b();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void m(Collection collection) {
        synchronized (this.f10208e) {
            this.f10210g.m(collection);
        }
    }

    public g0.f n() {
        return this.f10210g;
    }

    @u(i.a.ON_DESTROY)
    public void onDestroy(m mVar) {
        synchronized (this.f10208e) {
            g0.f fVar = this.f10210g;
            fVar.Y(fVar.I());
        }
    }

    @u(i.a.ON_PAUSE)
    public void onPause(m mVar) {
        this.f10210g.c(false);
    }

    @u(i.a.ON_RESUME)
    public void onResume(m mVar) {
        this.f10210g.c(true);
    }

    @u(i.a.ON_START)
    public void onStart(m mVar) {
        synchronized (this.f10208e) {
            if (!this.f10212i && !this.f10213j) {
                this.f10210g.n();
                this.f10211h = true;
            }
        }
    }

    @u(i.a.ON_STOP)
    public void onStop(m mVar) {
        synchronized (this.f10208e) {
            if (!this.f10212i && !this.f10213j) {
                this.f10210g.z();
                this.f10211h = false;
            }
        }
    }

    public m r() {
        m mVar;
        synchronized (this.f10208e) {
            mVar = this.f10209f;
        }
        return mVar;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public p s() {
        return this.f10210g.F();
    }

    public List t() {
        List unmodifiableList;
        synchronized (this.f10208e) {
            unmodifiableList = Collections.unmodifiableList(this.f10210g.I());
        }
        return unmodifiableList;
    }

    public boolean u(h2 h2Var) {
        boolean contains;
        synchronized (this.f10208e) {
            contains = this.f10210g.I().contains(h2Var);
        }
        return contains;
    }

    public void v() {
        synchronized (this.f10208e) {
            if (this.f10212i) {
                return;
            }
            onStop(this.f10209f);
            this.f10212i = true;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void w(Collection collection) {
        synchronized (this.f10208e) {
            ArrayList arrayList = new ArrayList(collection);
            arrayList.retainAll(this.f10210g.I());
            this.f10210g.Y(arrayList);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void x() {
        synchronized (this.f10208e) {
            g0.f fVar = this.f10210g;
            fVar.Y(fVar.I());
        }
    }

    public void y() {
        synchronized (this.f10208e) {
            if (this.f10212i) {
                this.f10212i = false;
                if (this.f10209f.getLifecycle().b().e(i.b.STARTED)) {
                    onStart(this.f10209f);
                }
            }
        }
    }
}
