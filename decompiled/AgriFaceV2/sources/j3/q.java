package j3;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
/* loaded from: classes.dex */
public final class q extends BroadcastReceiver {

    /* renamed from: a  reason: collision with root package name */
    Context f8233a;

    /* renamed from: b  reason: collision with root package name */
    private final p f8234b;

    public q(p pVar) {
        this.f8234b = pVar;
    }

    public final void a(Context context) {
        this.f8233a = context;
    }

    public final synchronized void b() {
        Context context = this.f8233a;
        if (context != null) {
            context.unregisterReceiver(this);
        }
        this.f8233a = null;
    }

    @Override // android.content.BroadcastReceiver
    public final void onReceive(Context context, Intent intent) {
        Uri data = intent.getData();
        if ("com.google.android.gms".equals(data != null ? data.getSchemeSpecificPart() : null)) {
            this.f8234b.a();
            b();
        }
    }
}
