package p2;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import androidx.core.app.j;
/* loaded from: classes.dex */
public class b {

    /* renamed from: a  reason: collision with root package name */
    private final Context f10023a;

    /* renamed from: b  reason: collision with root package name */
    private final Integer f10024b;

    /* renamed from: c  reason: collision with root package name */
    private final String f10025c;

    /* renamed from: d  reason: collision with root package name */
    private j.d f10026d;

    public b(Context context, String str, Integer num, d dVar) {
        this.f10023a = context;
        this.f10024b = num;
        this.f10025c = str;
        this.f10026d = new j.d(context, str).n(1);
        e(dVar, false);
    }

    private PendingIntent b() {
        Intent launchIntentForPackage = this.f10023a.getPackageManager().getLaunchIntentForPackage(this.f10023a.getPackageName());
        if (launchIntentForPackage != null) {
            launchIntentForPackage.setPackage(null);
            launchIntentForPackage.setFlags(270532608);
            return PendingIntent.getActivity(this.f10023a, 0, launchIntentForPackage, 201326592);
        }
        return null;
    }

    private int c(String str, String str2) {
        return this.f10023a.getResources().getIdentifier(str, str2, this.f10023a.getPackageName());
    }

    private void e(d dVar, boolean z7) {
        int c8 = c(dVar.c().b(), dVar.c().a());
        if (c8 == 0) {
            c("ic_launcher.png", "mipmap");
        }
        this.f10026d = this.f10026d.j(dVar.e()).o(c8).i(dVar.d()).h(b()).m(dVar.h());
        Integer a8 = dVar.a();
        if (a8 != null) {
            this.f10026d = this.f10026d.g(a8.intValue());
        }
        if (z7) {
            androidx.core.app.m.c(this.f10023a).e(this.f10024b.intValue(), this.f10026d.b());
        }
    }

    public Notification a() {
        return this.f10026d.b();
    }

    public void d(String str) {
        androidx.core.app.m c8 = androidx.core.app.m.c(this.f10023a);
        NotificationChannel notificationChannel = new NotificationChannel(this.f10025c, str, 0);
        notificationChannel.setLockscreenVisibility(0);
        c8.b(notificationChannel);
    }

    public void f(d dVar, boolean z7) {
        e(dVar, z7);
    }
}
