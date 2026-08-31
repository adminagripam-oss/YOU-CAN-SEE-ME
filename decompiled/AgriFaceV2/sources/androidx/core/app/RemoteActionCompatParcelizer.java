package androidx.core.app;

import android.app.PendingIntent;
import androidx.core.graphics.drawable.IconCompat;
/* loaded from: classes.dex */
public class RemoteActionCompatParcelizer {
    public static RemoteActionCompat read(androidx.versionedparcelable.a aVar) {
        RemoteActionCompat remoteActionCompat = new RemoteActionCompat();
        remoteActionCompat.f2812a = (IconCompat) aVar.v(remoteActionCompat.f2812a, 1);
        remoteActionCompat.f2813b = aVar.l(remoteActionCompat.f2813b, 2);
        remoteActionCompat.f2814c = aVar.l(remoteActionCompat.f2814c, 3);
        remoteActionCompat.f2815d = (PendingIntent) aVar.r(remoteActionCompat.f2815d, 4);
        remoteActionCompat.f2816e = aVar.h(remoteActionCompat.f2816e, 5);
        remoteActionCompat.f2817f = aVar.h(remoteActionCompat.f2817f, 6);
        return remoteActionCompat;
    }

    public static void write(RemoteActionCompat remoteActionCompat, androidx.versionedparcelable.a aVar) {
        aVar.x(false, false);
        aVar.M(remoteActionCompat.f2812a, 1);
        aVar.D(remoteActionCompat.f2813b, 2);
        aVar.D(remoteActionCompat.f2814c, 3);
        aVar.H(remoteActionCompat.f2815d, 4);
        aVar.z(remoteActionCompat.f2816e, 5);
        aVar.z(remoteActionCompat.f2817f, 6);
    }
}
