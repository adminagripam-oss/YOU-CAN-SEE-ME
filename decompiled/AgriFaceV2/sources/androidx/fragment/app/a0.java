package androidx.fragment.app;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import java.io.FileDescriptor;
import java.io.PrintWriter;
/* loaded from: classes.dex */
public abstract class a0 extends w {

    /* renamed from: a  reason: collision with root package name */
    private final Activity f3354a;

    /* renamed from: b  reason: collision with root package name */
    private final Context f3355b;

    /* renamed from: c  reason: collision with root package name */
    private final Handler f3356c;

    /* renamed from: d  reason: collision with root package name */
    private final int f3357d;

    /* renamed from: e  reason: collision with root package name */
    private final i0 f3358e;

    public a0(Activity activity, Context context, Handler handler, int i8) {
        d7.k.e(context, "context");
        d7.k.e(handler, "handler");
        this.f3354a = activity;
        this.f3355b = context;
        this.f3356c = handler;
        this.f3357d = i8;
        this.f3358e = new j0();
    }

    public final Activity e() {
        return this.f3354a;
    }

    public final Context f() {
        return this.f3355b;
    }

    public final i0 g() {
        return this.f3358e;
    }

    public final Handler h() {
        return this.f3356c;
    }

    public abstract void i(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr);

    public abstract Object j();

    public abstract LayoutInflater k();

    public void l(p pVar, Intent intent, int i8, Bundle bundle) {
        d7.k.e(pVar, "fragment");
        d7.k.e(intent, "intent");
        if (!(i8 == -1)) {
            throw new IllegalStateException("Starting activity with a requestCode requires a FragmentActivity host".toString());
        }
        androidx.core.content.a.l(this.f3355b, intent, bundle);
    }

    public void m(p pVar, IntentSender intentSender, int i8, Intent intent, int i9, int i10, int i11, Bundle bundle) {
        d7.k.e(pVar, "fragment");
        d7.k.e(intentSender, "intent");
        if (!(i8 == -1)) {
            throw new IllegalStateException("Starting intent sender with a requestCode requires a FragmentActivity host".toString());
        }
        Activity activity = this.f3354a;
        if (activity == null) {
            throw new IllegalStateException("Starting intent sender with a requestCode requires a FragmentActivity host".toString());
        }
        androidx.core.app.b.x(activity, intentSender, i8, intent, i9, i10, i11, bundle);
    }

    public abstract void n();

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public a0(u uVar) {
        this(uVar, uVar, new Handler(), 0);
        d7.k.e(uVar, "activity");
    }
}
