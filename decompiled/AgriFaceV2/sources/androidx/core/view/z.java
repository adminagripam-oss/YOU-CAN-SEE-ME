package androidx.core.view;

import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import androidx.lifecycle.i;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;
/* loaded from: classes.dex */
public class z {

    /* renamed from: a  reason: collision with root package name */
    private final Runnable f3195a;

    /* renamed from: b  reason: collision with root package name */
    private final CopyOnWriteArrayList f3196b = new CopyOnWriteArrayList();

    /* renamed from: c  reason: collision with root package name */
    private final Map f3197c = new HashMap();

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class a {

        /* renamed from: a  reason: collision with root package name */
        final androidx.lifecycle.i f3198a;

        /* renamed from: b  reason: collision with root package name */
        private androidx.lifecycle.k f3199b;

        a(androidx.lifecycle.i iVar, androidx.lifecycle.k kVar) {
            this.f3198a = iVar;
            this.f3199b = kVar;
            iVar.a(kVar);
        }

        void a() {
            this.f3198a.c(this.f3199b);
            this.f3199b = null;
        }
    }

    public z(Runnable runnable) {
        this.f3195a = runnable;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void f(b0 b0Var, androidx.lifecycle.m mVar, i.a aVar) {
        if (aVar == i.a.ON_DESTROY) {
            l(b0Var);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void g(i.b bVar, b0 b0Var, androidx.lifecycle.m mVar, i.a aVar) {
        if (aVar == i.a.g(bVar)) {
            c(b0Var);
        } else if (aVar == i.a.ON_DESTROY) {
            l(b0Var);
        } else if (aVar == i.a.e(bVar)) {
            this.f3196b.remove(b0Var);
            this.f3195a.run();
        }
    }

    public void c(b0 b0Var) {
        this.f3196b.add(b0Var);
        this.f3195a.run();
    }

    public void d(final b0 b0Var, androidx.lifecycle.m mVar) {
        c(b0Var);
        androidx.lifecycle.i lifecycle = mVar.getLifecycle();
        a aVar = (a) this.f3197c.remove(b0Var);
        if (aVar != null) {
            aVar.a();
        }
        this.f3197c.put(b0Var, new a(lifecycle, new androidx.lifecycle.k() { // from class: androidx.core.view.x
            @Override // androidx.lifecycle.k
            public final void f(androidx.lifecycle.m mVar2, i.a aVar2) {
                z.this.f(b0Var, mVar2, aVar2);
            }
        }));
    }

    public void e(final b0 b0Var, androidx.lifecycle.m mVar, final i.b bVar) {
        androidx.lifecycle.i lifecycle = mVar.getLifecycle();
        a aVar = (a) this.f3197c.remove(b0Var);
        if (aVar != null) {
            aVar.a();
        }
        this.f3197c.put(b0Var, new a(lifecycle, new androidx.lifecycle.k() { // from class: androidx.core.view.y
            @Override // androidx.lifecycle.k
            public final void f(androidx.lifecycle.m mVar2, i.a aVar2) {
                z.this.g(bVar, b0Var, mVar2, aVar2);
            }
        }));
    }

    public void h(Menu menu, MenuInflater menuInflater) {
        Iterator it = this.f3196b.iterator();
        while (it.hasNext()) {
            ((b0) it.next()).c(menu, menuInflater);
        }
    }

    public void i(Menu menu) {
        Iterator it = this.f3196b.iterator();
        while (it.hasNext()) {
            ((b0) it.next()).b(menu);
        }
    }

    public boolean j(MenuItem menuItem) {
        Iterator it = this.f3196b.iterator();
        while (it.hasNext()) {
            if (((b0) it.next()).a(menuItem)) {
                return true;
            }
        }
        return false;
    }

    public void k(Menu menu) {
        Iterator it = this.f3196b.iterator();
        while (it.hasNext()) {
            ((b0) it.next()).d(menu);
        }
    }

    public void l(b0 b0Var) {
        this.f3196b.remove(b0Var);
        a aVar = (a) this.f3197c.remove(b0Var);
        if (aVar != null) {
            aVar.a();
        }
        this.f3195a.run();
    }
}
