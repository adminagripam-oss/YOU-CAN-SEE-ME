package j3;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Looper;
import com.google.android.gms.common.api.internal.LifecycleCallback;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.lang.ref.WeakReference;
import java.util.Collections;
import java.util.Map;
import java.util.WeakHashMap;
/* loaded from: classes.dex */
public final class f0 extends androidx.fragment.app.p implements f {

    /* renamed from: h0  reason: collision with root package name */
    private static final WeakHashMap f8226h0 = new WeakHashMap();

    /* renamed from: e0  reason: collision with root package name */
    private final Map f8227e0 = Collections.synchronizedMap(new b1.a());

    /* renamed from: f0  reason: collision with root package name */
    private int f8228f0 = 0;

    /* renamed from: g0  reason: collision with root package name */
    private Bundle f8229g0;

    public static f0 B1(androidx.fragment.app.u uVar) {
        f0 f0Var;
        WeakHashMap weakHashMap = f8226h0;
        WeakReference weakReference = (WeakReference) weakHashMap.get(uVar);
        if (weakReference == null || (f0Var = (f0) weakReference.get()) == null) {
            try {
                f0 f0Var2 = (f0) uVar.getSupportFragmentManager().i0("SupportLifecycleFragmentImpl");
                if (f0Var2 == null || f0Var2.Z()) {
                    f0Var2 = new f0();
                    uVar.getSupportFragmentManager().n().d(f0Var2, "SupportLifecycleFragmentImpl").g();
                }
                weakHashMap.put(uVar, new WeakReference(f0Var2));
                return f0Var2;
            } catch (ClassCastException e8) {
                throw new IllegalStateException("Fragment with tag SupportLifecycleFragmentImpl is not a SupportLifecycleFragmentImpl", e8);
            }
        }
        return f0Var;
    }

    @Override // androidx.fragment.app.p
    public final void E0() {
        super.E0();
        this.f8228f0 = 3;
        for (LifecycleCallback lifecycleCallback : this.f8227e0.values()) {
            lifecycleCallback.h();
        }
    }

    @Override // androidx.fragment.app.p
    public final void F0(Bundle bundle) {
        super.F0(bundle);
        if (bundle == null) {
            return;
        }
        for (Map.Entry entry : this.f8227e0.entrySet()) {
            Bundle bundle2 = new Bundle();
            ((LifecycleCallback) entry.getValue()).i(bundle2);
            bundle.putBundle((String) entry.getKey(), bundle2);
        }
    }

    @Override // androidx.fragment.app.p
    public final void G0() {
        super.G0();
        this.f8228f0 = 2;
        for (LifecycleCallback lifecycleCallback : this.f8227e0.values()) {
            lifecycleCallback.j();
        }
    }

    @Override // androidx.fragment.app.p
    public final void H0() {
        super.H0();
        this.f8228f0 = 4;
        for (LifecycleCallback lifecycleCallback : this.f8227e0.values()) {
            lifecycleCallback.k();
        }
    }

    @Override // j3.f
    public final void a(String str, LifecycleCallback lifecycleCallback) {
        if (this.f8227e0.containsKey(str)) {
            throw new IllegalArgumentException("LifecycleCallback with tag " + str + " already added to this fragment.");
        }
        this.f8227e0.put(str, lifecycleCallback);
        if (this.f8228f0 > 0) {
            new w3.e(Looper.getMainLooper()).post(new e0(this, lifecycleCallback, str));
        }
    }

    @Override // j3.f
    public final LifecycleCallback b(String str, Class cls) {
        return (LifecycleCallback) cls.cast(this.f8227e0.get(str));
    }

    @Override // j3.f
    public final /* synthetic */ Activity c() {
        return j();
    }

    @Override // androidx.fragment.app.p
    public final void e0(int i8, int i9, Intent intent) {
        super.e0(i8, i9, intent);
        for (LifecycleCallback lifecycleCallback : this.f8227e0.values()) {
            lifecycleCallback.e(i8, i9, intent);
        }
    }

    @Override // androidx.fragment.app.p
    public final void g(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        super.g(str, fileDescriptor, printWriter, strArr);
        for (LifecycleCallback lifecycleCallback : this.f8227e0.values()) {
            lifecycleCallback.a(str, fileDescriptor, printWriter, strArr);
        }
    }

    @Override // androidx.fragment.app.p
    public final void j0(Bundle bundle) {
        super.j0(bundle);
        this.f8228f0 = 1;
        this.f8229g0 = bundle;
        for (Map.Entry entry : this.f8227e0.entrySet()) {
            ((LifecycleCallback) entry.getValue()).f(bundle != null ? bundle.getBundle((String) entry.getKey()) : null);
        }
    }

    @Override // androidx.fragment.app.p
    public final void o0() {
        super.o0();
        this.f8228f0 = 5;
        for (LifecycleCallback lifecycleCallback : this.f8227e0.values()) {
            lifecycleCallback.g();
        }
    }
}
