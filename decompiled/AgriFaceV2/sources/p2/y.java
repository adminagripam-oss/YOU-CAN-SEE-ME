package p2;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.location.LocationManager;
import i6.c;
/* loaded from: classes.dex */
public class y extends BroadcastReceiver {

    /* renamed from: a  reason: collision with root package name */
    private final c.b f10085a;

    /* renamed from: b  reason: collision with root package name */
    private c0 f10086b;

    public y(c.b bVar) {
        this.f10085a = bVar;
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        c0 c0Var;
        if ("android.location.PROVIDERS_CHANGED".equals(intent.getAction())) {
            LocationManager locationManager = (LocationManager) context.getSystemService("location");
            boolean isProviderEnabled = locationManager.isProviderEnabled("gps");
            boolean isProviderEnabled2 = locationManager.isProviderEnabled("network");
            if (isProviderEnabled || isProviderEnabled2) {
                c0 c0Var2 = this.f10086b;
                if (c0Var2 != null && c0Var2 != c0.disabled) {
                    return;
                }
                c0Var = c0.enabled;
            } else {
                c0 c0Var3 = this.f10086b;
                if (c0Var3 != null && c0Var3 != c0.enabled) {
                    return;
                }
                c0Var = c0.disabled;
            }
            this.f10086b = c0Var;
            this.f10085a.success(Integer.valueOf(c0Var.ordinal()));
        }
    }
}
