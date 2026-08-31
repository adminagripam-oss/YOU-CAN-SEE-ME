package j3;

import android.os.Bundle;
import com.google.android.gms.common.api.internal.LifecycleCallback;
/* loaded from: classes.dex */
final class e0 implements Runnable {

    /* renamed from: e  reason: collision with root package name */
    final /* synthetic */ LifecycleCallback f8223e;

    /* renamed from: f  reason: collision with root package name */
    final /* synthetic */ String f8224f;

    /* renamed from: g  reason: collision with root package name */
    final /* synthetic */ f0 f8225g;

    /* JADX INFO: Access modifiers changed from: package-private */
    public e0(f0 f0Var, LifecycleCallback lifecycleCallback, String str) {
        this.f8225g = f0Var;
        this.f8223e = lifecycleCallback;
        this.f8224f = str;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i8;
        int i9;
        int i10;
        int i11;
        int i12;
        Bundle bundle;
        Bundle bundle2;
        Bundle bundle3;
        f0 f0Var = this.f8225g;
        i8 = f0Var.f8228f0;
        if (i8 > 0) {
            LifecycleCallback lifecycleCallback = this.f8223e;
            bundle = f0Var.f8229g0;
            if (bundle != null) {
                String str = this.f8224f;
                bundle3 = f0Var.f8229g0;
                bundle2 = bundle3.getBundle(str);
            } else {
                bundle2 = null;
            }
            lifecycleCallback.f(bundle2);
        }
        i9 = this.f8225g.f8228f0;
        if (i9 >= 2) {
            this.f8223e.j();
        }
        i10 = this.f8225g.f8228f0;
        if (i10 >= 3) {
            this.f8223e.h();
        }
        i11 = this.f8225g.f8228f0;
        if (i11 >= 4) {
            this.f8223e.k();
        }
        i12 = this.f8225g.f8228f0;
        if (i12 >= 5) {
            this.f8223e.g();
        }
    }
}
