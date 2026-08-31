package androidx.lifecycle;

import android.os.Bundle;
import androidx.lifecycle.i;
import androidx.lifecycle.l0;
import v1.a;
import w1.d;
/* loaded from: classes.dex */
public abstract class e0 {

    /* renamed from: a  reason: collision with root package name */
    public static final a.b f3755a = new b();

    /* renamed from: b  reason: collision with root package name */
    public static final a.b f3756b = new c();

    /* renamed from: c  reason: collision with root package name */
    public static final a.b f3757c = new a();

    /* loaded from: classes.dex */
    public static final class a implements a.b {
        a() {
        }
    }

    /* loaded from: classes.dex */
    public static final class b implements a.b {
        b() {
        }
    }

    /* loaded from: classes.dex */
    public static final class c implements a.b {
        c() {
        }
    }

    /* loaded from: classes.dex */
    public static final class d implements l0.b {
        d() {
        }

        @Override // androidx.lifecycle.l0.b
        public k0 b(Class cls, v1.a aVar) {
            d7.k.e(cls, "modelClass");
            d7.k.e(aVar, "extras");
            return new g0();
        }
    }

    public static final b0 a(v1.a aVar) {
        d7.k.e(aVar, "<this>");
        w1.f fVar = (w1.f) aVar.a(f3755a);
        if (fVar != null) {
            o0 o0Var = (o0) aVar.a(f3756b);
            if (o0Var != null) {
                Bundle bundle = (Bundle) aVar.a(f3757c);
                String str = (String) aVar.a(l0.c.f3800d);
                if (str != null) {
                    return b(fVar, o0Var, str, bundle);
                }
                throw new IllegalArgumentException("CreationExtras must have a value by `VIEW_MODEL_KEY`");
            }
            throw new IllegalArgumentException("CreationExtras must have a value by `VIEW_MODEL_STORE_OWNER_KEY`");
        }
        throw new IllegalArgumentException("CreationExtras must have a value by `SAVED_STATE_REGISTRY_OWNER_KEY`");
    }

    private static final b0 b(w1.f fVar, o0 o0Var, String str, Bundle bundle) {
        f0 d8 = d(fVar);
        g0 e8 = e(o0Var);
        b0 b0Var = (b0) e8.f().get(str);
        if (b0Var == null) {
            b0 a8 = b0.f3740f.a(d8.b(str), bundle);
            e8.f().put(str, a8);
            return a8;
        }
        return b0Var;
    }

    public static final void c(w1.f fVar) {
        d7.k.e(fVar, "<this>");
        i.b b8 = fVar.getLifecycle().b();
        if (!(b8 == i.b.INITIALIZED || b8 == i.b.CREATED)) {
            throw new IllegalArgumentException("Failed requirement.".toString());
        }
        if (fVar.getSavedStateRegistry().c("androidx.lifecycle.internal.SavedStateHandlesProvider") == null) {
            f0 f0Var = new f0(fVar.getSavedStateRegistry(), (o0) fVar);
            fVar.getSavedStateRegistry().h("androidx.lifecycle.internal.SavedStateHandlesProvider", f0Var);
            fVar.getLifecycle().a(new c0(f0Var));
        }
    }

    public static final f0 d(w1.f fVar) {
        d7.k.e(fVar, "<this>");
        d.c c8 = fVar.getSavedStateRegistry().c("androidx.lifecycle.internal.SavedStateHandlesProvider");
        f0 f0Var = c8 instanceof f0 ? (f0) c8 : null;
        if (f0Var != null) {
            return f0Var;
        }
        throw new IllegalStateException("enableSavedStateHandles() wasn't called prior to createSavedStateHandle() call");
    }

    public static final g0 e(o0 o0Var) {
        d7.k.e(o0Var, "<this>");
        return (g0) new l0(o0Var, new d()).b("androidx.lifecycle.internal.SavedStateHandlesVM", g0.class);
    }
}
