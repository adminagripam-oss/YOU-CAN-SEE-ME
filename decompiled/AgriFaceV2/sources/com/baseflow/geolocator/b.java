package com.baseflow.geolocator;

import android.content.Context;
import android.content.IntentFilter;
import android.util.Log;
import i6.c;
import p2.y;
/* loaded from: classes.dex */
public class b implements c.d {

    /* renamed from: a  reason: collision with root package name */
    private i6.c f4881a;

    /* renamed from: b  reason: collision with root package name */
    private Context f4882b;

    /* renamed from: c  reason: collision with root package name */
    private y f4883c;

    private void c() {
        y yVar;
        Context context = this.f4882b;
        if (context == null || (yVar = this.f4883c) == null) {
            return;
        }
        context.unregisterReceiver(yVar);
    }

    @Override // i6.c.d
    public void a(Object obj) {
        c();
    }

    @Override // i6.c.d
    public void b(Object obj, c.b bVar) {
        if (this.f4882b == null) {
            return;
        }
        IntentFilter intentFilter = new IntentFilter("android.location.PROVIDERS_CHANGED");
        intentFilter.addAction("android.intent.action.PROVIDER_CHANGED");
        y yVar = new y(bVar);
        this.f4883c = yVar;
        androidx.core.content.a.i(this.f4882b, yVar, intentFilter, 2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void d(Context context) {
        this.f4882b = context;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void e(Context context, i6.b bVar) {
        if (this.f4881a != null) {
            Log.w("LocationServiceHandler", "Setting a event call handler before the last was disposed.");
            f();
        }
        i6.c cVar = new i6.c(bVar, "flutter.baseflow.com/geolocator_service_updates_android");
        this.f4881a = cVar;
        cVar.d(this);
        this.f4882b = context;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void f() {
        if (this.f4881a == null) {
            return;
        }
        c();
        this.f4881a.d(null);
        this.f4881a = null;
    }
}
