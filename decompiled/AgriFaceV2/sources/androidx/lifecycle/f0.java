package androidx.lifecycle;

import android.os.Bundle;
import java.util.Map;
import w1.d;
/* loaded from: classes.dex */
public final class f0 implements d.c {

    /* renamed from: a  reason: collision with root package name */
    private final w1.d f3758a;

    /* renamed from: b  reason: collision with root package name */
    private boolean f3759b;

    /* renamed from: c  reason: collision with root package name */
    private Bundle f3760c;

    /* renamed from: d  reason: collision with root package name */
    private final r6.e f3761d;

    /* loaded from: classes.dex */
    static final class a extends d7.l implements c7.a {

        /* renamed from: f  reason: collision with root package name */
        final /* synthetic */ o0 f3762f;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        a(o0 o0Var) {
            super(0);
            this.f3762f = o0Var;
        }

        @Override // c7.a
        /* renamed from: b */
        public final g0 a() {
            return e0.e(this.f3762f);
        }
    }

    public f0(w1.d dVar, o0 o0Var) {
        r6.e a8;
        d7.k.e(dVar, "savedStateRegistry");
        d7.k.e(o0Var, "viewModelStoreOwner");
        this.f3758a = dVar;
        a8 = r6.g.a(new a(o0Var));
        this.f3761d = a8;
    }

    private final g0 c() {
        return (g0) this.f3761d.getValue();
    }

    @Override // w1.d.c
    public Bundle a() {
        Bundle bundle = new Bundle();
        Bundle bundle2 = this.f3760c;
        if (bundle2 != null) {
            bundle.putAll(bundle2);
        }
        for (Map.Entry entry : c().f().entrySet()) {
            String str = (String) entry.getKey();
            Bundle a8 = ((b0) entry.getValue()).c().a();
            if (!d7.k.a(a8, Bundle.EMPTY)) {
                bundle.putBundle(str, a8);
            }
        }
        this.f3759b = false;
        return bundle;
    }

    public final Bundle b(String str) {
        d7.k.e(str, "key");
        d();
        Bundle bundle = this.f3760c;
        Bundle bundle2 = bundle != null ? bundle.getBundle(str) : null;
        Bundle bundle3 = this.f3760c;
        if (bundle3 != null) {
            bundle3.remove(str);
        }
        Bundle bundle4 = this.f3760c;
        boolean z7 = false;
        if (bundle4 != null && bundle4.isEmpty()) {
            z7 = true;
        }
        if (z7) {
            this.f3760c = null;
        }
        return bundle2;
    }

    public final void d() {
        if (this.f3759b) {
            return;
        }
        Bundle b8 = this.f3758a.b("androidx.lifecycle.internal.SavedStateHandlesProvider");
        Bundle bundle = new Bundle();
        Bundle bundle2 = this.f3760c;
        if (bundle2 != null) {
            bundle.putAll(bundle2);
        }
        if (b8 != null) {
            bundle.putAll(b8);
        }
        this.f3760c = bundle;
        this.f3759b = true;
        c();
    }
}
