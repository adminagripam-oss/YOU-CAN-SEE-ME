package androidx.lifecycle;

import android.app.Application;
import android.os.Bundle;
import androidx.lifecycle.l0;
import java.lang.reflect.Constructor;
/* loaded from: classes.dex */
public final class h0 extends l0.d implements l0.b {

    /* renamed from: b  reason: collision with root package name */
    private Application f3767b;

    /* renamed from: c  reason: collision with root package name */
    private final l0.b f3768c;

    /* renamed from: d  reason: collision with root package name */
    private Bundle f3769d;

    /* renamed from: e  reason: collision with root package name */
    private i f3770e;

    /* renamed from: f  reason: collision with root package name */
    private w1.d f3771f;

    public h0(Application application, w1.f fVar, Bundle bundle) {
        d7.k.e(fVar, "owner");
        this.f3771f = fVar.getSavedStateRegistry();
        this.f3770e = fVar.getLifecycle();
        this.f3769d = bundle;
        this.f3767b = application;
        this.f3768c = application != null ? l0.a.f3791f.a(application) : new l0.a();
    }

    @Override // androidx.lifecycle.l0.b
    public k0 a(Class cls) {
        d7.k.e(cls, "modelClass");
        String canonicalName = cls.getCanonicalName();
        if (canonicalName != null) {
            return d(canonicalName, cls);
        }
        throw new IllegalArgumentException("Local and anonymous classes can not be ViewModels");
    }

    @Override // androidx.lifecycle.l0.b
    public k0 b(Class cls, v1.a aVar) {
        d7.k.e(cls, "modelClass");
        d7.k.e(aVar, "extras");
        String str = (String) aVar.a(l0.c.f3800d);
        if (str != null) {
            if (aVar.a(e0.f3755a) == null || aVar.a(e0.f3756b) == null) {
                if (this.f3770e != null) {
                    return d(str, cls);
                }
                throw new IllegalStateException("SAVED_STATE_REGISTRY_OWNER_KEY andVIEW_MODEL_STORE_OWNER_KEY must be provided in the creation extras tosuccessfully create a ViewModel.");
            }
            Application application = (Application) aVar.a(l0.a.f3793h);
            boolean isAssignableFrom = a.class.isAssignableFrom(cls);
            Constructor c8 = i0.c(cls, (!isAssignableFrom || application == null) ? i0.f3782b : i0.f3781a);
            return c8 == null ? this.f3768c.b(cls, aVar) : (!isAssignableFrom || application == null) ? i0.d(cls, c8, e0.a(aVar)) : i0.d(cls, c8, application, e0.a(aVar));
        }
        throw new IllegalStateException("VIEW_MODEL_KEY must always be provided by ViewModelProvider");
    }

    @Override // androidx.lifecycle.l0.d
    public void c(k0 k0Var) {
        d7.k.e(k0Var, "viewModel");
        if (this.f3770e != null) {
            w1.d dVar = this.f3771f;
            d7.k.b(dVar);
            i iVar = this.f3770e;
            d7.k.b(iVar);
            h.a(k0Var, dVar, iVar);
        }
    }

    public final k0 d(String str, Class cls) {
        k0 d8;
        Application application;
        d7.k.e(str, "key");
        d7.k.e(cls, "modelClass");
        i iVar = this.f3770e;
        if (iVar != null) {
            boolean isAssignableFrom = a.class.isAssignableFrom(cls);
            Constructor c8 = i0.c(cls, (!isAssignableFrom || this.f3767b == null) ? i0.f3782b : i0.f3781a);
            if (c8 == null) {
                return this.f3767b != null ? this.f3768c.a(cls) : l0.c.f3798b.a().a(cls);
            }
            w1.d dVar = this.f3771f;
            d7.k.b(dVar);
            d0 b8 = h.b(dVar, iVar, str, this.f3769d);
            if (!isAssignableFrom || (application = this.f3767b) == null) {
                d8 = i0.d(cls, c8, b8.b());
            } else {
                d7.k.b(application);
                d8 = i0.d(cls, c8, application, b8.b());
            }
            d8.e("androidx.lifecycle.savedstate.vm.tag", b8);
            return d8;
        }
        throw new UnsupportedOperationException("SavedStateViewModelFactory constructed with empty constructor supports only calls to create(modelClass: Class<T>, extras: CreationExtras).");
    }
}
