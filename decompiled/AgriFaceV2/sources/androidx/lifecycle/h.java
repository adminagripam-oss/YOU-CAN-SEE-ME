package androidx.lifecycle;

import android.os.Bundle;
import androidx.lifecycle.i;
import w1.d;
/* loaded from: classes.dex */
public final class h {

    /* renamed from: a  reason: collision with root package name */
    public static final h f3764a = new h();

    /* loaded from: classes.dex */
    public static final class a implements d.a {
        @Override // w1.d.a
        public void a(w1.f fVar) {
            d7.k.e(fVar, "owner");
            if (!(fVar instanceof o0)) {
                throw new IllegalStateException("Internal error: OnRecreation should be registered only on components that implement ViewModelStoreOwner".toString());
            }
            n0 viewModelStore = ((o0) fVar).getViewModelStore();
            w1.d savedStateRegistry = fVar.getSavedStateRegistry();
            for (String str : viewModelStore.c()) {
                k0 b8 = viewModelStore.b(str);
                d7.k.b(b8);
                h.a(b8, savedStateRegistry, fVar.getLifecycle());
            }
            if (!viewModelStore.c().isEmpty()) {
                savedStateRegistry.i(a.class);
            }
        }
    }

    /* loaded from: classes.dex */
    public static final class b implements k {

        /* renamed from: e  reason: collision with root package name */
        final /* synthetic */ i f3765e;

        /* renamed from: f  reason: collision with root package name */
        final /* synthetic */ w1.d f3766f;

        b(i iVar, w1.d dVar) {
            this.f3765e = iVar;
            this.f3766f = dVar;
        }

        @Override // androidx.lifecycle.k
        public void f(m mVar, i.a aVar) {
            d7.k.e(mVar, "source");
            d7.k.e(aVar, "event");
            if (aVar == i.a.ON_START) {
                this.f3765e.c(this);
                this.f3766f.i(a.class);
            }
        }
    }

    private h() {
    }

    public static final void a(k0 k0Var, w1.d dVar, i iVar) {
        d7.k.e(k0Var, "viewModel");
        d7.k.e(dVar, "registry");
        d7.k.e(iVar, "lifecycle");
        d0 d0Var = (d0) k0Var.c("androidx.lifecycle.savedstate.vm.tag");
        if (d0Var == null || d0Var.c()) {
            return;
        }
        d0Var.a(dVar, iVar);
        f3764a.c(dVar, iVar);
    }

    public static final d0 b(w1.d dVar, i iVar, String str, Bundle bundle) {
        d7.k.e(dVar, "registry");
        d7.k.e(iVar, "lifecycle");
        d7.k.b(str);
        d0 d0Var = new d0(str, b0.f3740f.a(dVar.b(str), bundle));
        d0Var.a(dVar, iVar);
        f3764a.c(dVar, iVar);
        return d0Var;
    }

    private final void c(w1.d dVar, i iVar) {
        i.b b8 = iVar.b();
        if (b8 == i.b.INITIALIZED || b8.e(i.b.STARTED)) {
            dVar.i(a.class);
        } else {
            iVar.a(new b(iVar, dVar));
        }
    }
}
