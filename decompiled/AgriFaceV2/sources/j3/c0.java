package j3;

import android.os.Bundle;
import com.google.android.gms.common.api.internal.LifecycleCallback;
/* loaded from: classes.dex */
final class c0 implements Runnable {

    /* renamed from: e  reason: collision with root package name */
    final /* synthetic */ LifecycleCallback f8215e;

    /* renamed from: f  reason: collision with root package name */
    final /* synthetic */ String f8216f;

    /* renamed from: g  reason: collision with root package name */
    final /* synthetic */ d0 f8217g;

    /* JADX INFO: Access modifiers changed from: package-private */
    public c0(d0 d0Var, LifecycleCallback lifecycleCallback, String str) {
        this.f8217g = d0Var;
        this.f8215e = lifecycleCallback;
        this.f8216f = str;
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
        d0 d0Var = this.f8217g;
        i8 = d0Var.f8220b;
        if (i8 > 0) {
            LifecycleCallback lifecycleCallback = this.f8215e;
            bundle = d0Var.f8221c;
            if (bundle != null) {
                String str = this.f8216f;
                bundle3 = d0Var.f8221c;
                bundle2 = bundle3.getBundle(str);
            } else {
                bundle2 = null;
            }
            lifecycleCallback.f(bundle2);
        }
        i9 = this.f8217g.f8220b;
        if (i9 >= 2) {
            this.f8215e.j();
        }
        i10 = this.f8217g.f8220b;
        if (i10 >= 3) {
            this.f8215e.h();
        }
        i11 = this.f8217g.f8220b;
        if (i11 >= 4) {
            this.f8215e.k();
        }
        i12 = this.f8217g.f8220b;
        if (i12 >= 5) {
            this.f8215e.g();
        }
    }
}
