package androidx.browser.customtabs;

import android.app.PendingIntent;
import android.content.ComponentName;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.RemoteException;
import android.widget.RemoteViews;
import b.c;
import java.util.List;
/* loaded from: classes.dex */
public final class i {

    /* renamed from: a  reason: collision with root package name */
    private final Object f2618a = new Object();

    /* renamed from: b  reason: collision with root package name */
    private final b.b f2619b;

    /* renamed from: c  reason: collision with root package name */
    private final b.a f2620c;

    /* renamed from: d  reason: collision with root package name */
    private final ComponentName f2621d;

    /* renamed from: e  reason: collision with root package name */
    private final PendingIntent f2622e;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class a extends c.a {

        /* renamed from: e  reason: collision with root package name */
        private final Handler f2623e = new Handler(Looper.getMainLooper());

        /* renamed from: f  reason: collision with root package name */
        final /* synthetic */ j f2624f;

        a(j jVar) {
            this.f2624f = jVar;
        }

        @Override // b.c
        public void onGreatestScrollPercentageIncreased(final int i8, final Bundle bundle) {
            Handler handler = this.f2623e;
            final j jVar = this.f2624f;
            handler.post(new Runnable() { // from class: androidx.browser.customtabs.h
                @Override // java.lang.Runnable
                public final void run() {
                    j.this.onGreatestScrollPercentageIncreased(i8, bundle);
                }
            });
        }

        @Override // b.c
        public void onSessionEnded(final boolean z7, final Bundle bundle) {
            Handler handler = this.f2623e;
            final j jVar = this.f2624f;
            handler.post(new Runnable() { // from class: androidx.browser.customtabs.g
                @Override // java.lang.Runnable
                public final void run() {
                    j.this.onSessionEnded(z7, bundle);
                }
            });
        }

        @Override // b.c
        public void onVerticalScrollEvent(final boolean z7, final Bundle bundle) {
            Handler handler = this.f2623e;
            final j jVar = this.f2624f;
            handler.post(new Runnable() { // from class: androidx.browser.customtabs.f
                @Override // java.lang.Runnable
                public final void run() {
                    j.this.onVerticalScrollEvent(z7, bundle);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public i(b.b bVar, b.a aVar, ComponentName componentName, PendingIntent pendingIntent) {
        this.f2619b = bVar;
        this.f2620c = aVar;
        this.f2621d = componentName;
        this.f2622e = pendingIntent;
    }

    private void a(Bundle bundle) {
        PendingIntent pendingIntent = this.f2622e;
        if (pendingIntent != null) {
            bundle.putParcelable("android.support.customtabs.extra.SESSION_ID", pendingIntent);
        }
    }

    private Bundle b(Bundle bundle) {
        Bundle bundle2 = new Bundle();
        if (bundle != null) {
            bundle2.putAll(bundle);
        }
        a(bundle2);
        return bundle2;
    }

    private c.a c(j jVar) {
        return new a(jVar);
    }

    private Bundle d(Uri uri) {
        Bundle bundle = new Bundle();
        if (uri != null) {
            bundle.putParcelable("target_origin", uri);
        }
        if (this.f2622e != null) {
            a(bundle);
        }
        if (bundle.isEmpty()) {
            return null;
        }
        return bundle;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public IBinder e() {
        return this.f2620c.asBinder();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ComponentName f() {
        return this.f2621d;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public PendingIntent g() {
        return this.f2622e;
    }

    public boolean h(Bundle bundle) {
        try {
            return this.f2619b.q(this.f2620c, b(bundle));
        } catch (SecurityException e8) {
            throw new UnsupportedOperationException("This method isn't supported by the Custom Tabs implementation.", e8);
        }
    }

    public boolean i(Uri uri, Bundle bundle, List list) {
        try {
            return this.f2619b.M(this.f2620c, uri, b(bundle), list);
        } catch (RemoteException unused) {
            return false;
        }
    }

    public int j(String str, Bundle bundle) {
        int t8;
        Bundle b8 = b(bundle);
        synchronized (this.f2618a) {
            try {
                try {
                    t8 = this.f2619b.t(this.f2620c, str, b8);
                } catch (RemoteException unused) {
                    return -2;
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        return t8;
    }

    public boolean k(Uri uri, Uri uri2, Bundle bundle) {
        try {
            Bundle d8 = d(uri2);
            if (d8 != null) {
                bundle.putAll(d8);
                return this.f2619b.o(this.f2620c, uri, bundle);
            }
            return this.f2619b.h(this.f2620c, uri);
        } catch (RemoteException unused) {
            return false;
        }
    }

    public boolean l(Bitmap bitmap, String str) {
        Bundle bundle = new Bundle();
        bundle.putParcelable("android.support.customtabs.customaction.ICON", bitmap);
        bundle.putString("android.support.customtabs.customaction.DESCRIPTION", str);
        Bundle bundle2 = new Bundle();
        bundle2.putBundle("android.support.customtabs.extra.ACTION_BUTTON_BUNDLE", bundle);
        a(bundle);
        try {
            return this.f2619b.y(this.f2620c, bundle2);
        } catch (RemoteException unused) {
            return false;
        }
    }

    public boolean m(j jVar, Bundle bundle) {
        try {
            return this.f2619b.b0(this.f2620c, c(jVar).asBinder(), b(bundle));
        } catch (SecurityException e8) {
            throw new UnsupportedOperationException("This method isn't supported by the Custom Tabs implementation.", e8);
        }
    }

    public boolean n(RemoteViews remoteViews, int[] iArr, PendingIntent pendingIntent) {
        Bundle bundle = new Bundle();
        bundle.putParcelable("android.support.customtabs.extra.EXTRA_REMOTEVIEWS", remoteViews);
        bundle.putIntArray("android.support.customtabs.extra.EXTRA_REMOTEVIEWS_VIEW_IDS", iArr);
        bundle.putParcelable("android.support.customtabs.extra.EXTRA_REMOTEVIEWS_PENDINGINTENT", pendingIntent);
        a(bundle);
        try {
            return this.f2619b.y(this.f2620c, bundle);
        } catch (RemoteException unused) {
            return false;
        }
    }

    public boolean o(int i8, Uri uri, Bundle bundle) {
        if (i8 >= 1 && i8 <= 2) {
            try {
                return this.f2619b.w(this.f2620c, i8, uri, b(bundle));
            } catch (RemoteException unused) {
            }
        }
        return false;
    }
}
