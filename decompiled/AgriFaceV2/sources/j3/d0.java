package j3;

import android.app.Activity;
import android.app.Fragment;
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
public final class d0 extends Fragment implements f {

    /* renamed from: d  reason: collision with root package name */
    private static final WeakHashMap f8218d = new WeakHashMap();

    /* renamed from: a  reason: collision with root package name */
    private final Map f8219a = Collections.synchronizedMap(new b1.a());

    /* renamed from: b  reason: collision with root package name */
    private int f8220b = 0;

    /* renamed from: c  reason: collision with root package name */
    private Bundle f8221c;

    public static d0 f(Activity activity) {
        d0 d0Var;
        WeakHashMap weakHashMap = f8218d;
        WeakReference weakReference = (WeakReference) weakHashMap.get(activity);
        if (weakReference == null || (d0Var = (d0) weakReference.get()) == null) {
            try {
                d0 d0Var2 = (d0) activity.getFragmentManager().findFragmentByTag("LifecycleFragmentImpl");
                if (d0Var2 == null || d0Var2.isRemoving()) {
                    d0Var2 = new d0();
                    activity.getFragmentManager().beginTransaction().add(d0Var2, "LifecycleFragmentImpl").commitAllowingStateLoss();
                }
                weakHashMap.put(activity, new WeakReference(d0Var2));
                return d0Var2;
            } catch (ClassCastException e8) {
                throw new IllegalStateException("Fragment with tag LifecycleFragmentImpl is not a LifecycleFragmentImpl", e8);
            }
        }
        return d0Var;
    }

    @Override // j3.f
    public final void a(String str, LifecycleCallback lifecycleCallback) {
        if (this.f8219a.containsKey(str)) {
            throw new IllegalArgumentException("LifecycleCallback with tag " + str + " already added to this fragment.");
        }
        this.f8219a.put(str, lifecycleCallback);
        if (this.f8220b > 0) {
            new w3.e(Looper.getMainLooper()).post(new c0(this, lifecycleCallback, str));
        }
    }

    @Override // j3.f
    public final LifecycleCallback b(String str, Class cls) {
        return (LifecycleCallback) cls.cast(this.f8219a.get(str));
    }

    @Override // j3.f
    public final Activity c() {
        return getActivity();
    }

    @Override // android.app.Fragment
    public final void dump(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        super.dump(str, fileDescriptor, printWriter, strArr);
        for (LifecycleCallback lifecycleCallback : this.f8219a.values()) {
            lifecycleCallback.a(str, fileDescriptor, printWriter, strArr);
        }
    }

    @Override // android.app.Fragment
    public final void onActivityResult(int i8, int i9, Intent intent) {
        super.onActivityResult(i8, i9, intent);
        for (LifecycleCallback lifecycleCallback : this.f8219a.values()) {
            lifecycleCallback.e(i8, i9, intent);
        }
    }

    @Override // android.app.Fragment
    public final void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.f8220b = 1;
        this.f8221c = bundle;
        for (Map.Entry entry : this.f8219a.entrySet()) {
            ((LifecycleCallback) entry.getValue()).f(bundle != null ? bundle.getBundle((String) entry.getKey()) : null);
        }
    }

    @Override // android.app.Fragment
    public final void onDestroy() {
        super.onDestroy();
        this.f8220b = 5;
        for (LifecycleCallback lifecycleCallback : this.f8219a.values()) {
            lifecycleCallback.g();
        }
    }

    @Override // android.app.Fragment
    public final void onResume() {
        super.onResume();
        this.f8220b = 3;
        for (LifecycleCallback lifecycleCallback : this.f8219a.values()) {
            lifecycleCallback.h();
        }
    }

    @Override // android.app.Fragment
    public final void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        if (bundle == null) {
            return;
        }
        for (Map.Entry entry : this.f8219a.entrySet()) {
            Bundle bundle2 = new Bundle();
            ((LifecycleCallback) entry.getValue()).i(bundle2);
            bundle.putBundle((String) entry.getKey(), bundle2);
        }
    }

    @Override // android.app.Fragment
    public final void onStart() {
        super.onStart();
        this.f8220b = 2;
        for (LifecycleCallback lifecycleCallback : this.f8219a.values()) {
            lifecycleCallback.j();
        }
    }

    @Override // android.app.Fragment
    public final void onStop() {
        super.onStop();
        this.f8220b = 4;
        for (LifecycleCallback lifecycleCallback : this.f8219a.values()) {
            lifecycleCallback.k();
        }
    }
}
