package androidx.browser.customtabs;

import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.RemoteException;
import android.text.TextUtils;
import android.util.Log;
import b.a;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes.dex */
public abstract class c {

    /* renamed from: a  reason: collision with root package name */
    private final b.b f2557a;

    /* renamed from: b  reason: collision with root package name */
    private final ComponentName f2558b;

    /* renamed from: c  reason: collision with root package name */
    private final Context f2559c;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class a extends a.AbstractBinderC0056a {

        /* renamed from: e  reason: collision with root package name */
        private Handler f2560e = new Handler(Looper.getMainLooper());

        /* renamed from: f  reason: collision with root package name */
        final /* synthetic */ androidx.browser.customtabs.b f2561f;

        /* renamed from: androidx.browser.customtabs.c$a$a  reason: collision with other inner class name */
        /* loaded from: classes.dex */
        class RunnableC0012a implements Runnable {

            /* renamed from: e  reason: collision with root package name */
            final /* synthetic */ Bundle f2563e;

            RunnableC0012a(Bundle bundle) {
                this.f2563e = bundle;
            }

            @Override // java.lang.Runnable
            public void run() {
                a.this.f2561f.onUnminimized(this.f2563e);
            }
        }

        /* loaded from: classes.dex */
        class b implements Runnable {

            /* renamed from: e  reason: collision with root package name */
            final /* synthetic */ int f2565e;

            /* renamed from: f  reason: collision with root package name */
            final /* synthetic */ Bundle f2566f;

            b(int i8, Bundle bundle) {
                this.f2565e = i8;
                this.f2566f = bundle;
            }

            @Override // java.lang.Runnable
            public void run() {
                a.this.f2561f.onNavigationEvent(this.f2565e, this.f2566f);
            }
        }

        /* renamed from: androidx.browser.customtabs.c$a$c  reason: collision with other inner class name */
        /* loaded from: classes.dex */
        class RunnableC0013c implements Runnable {

            /* renamed from: e  reason: collision with root package name */
            final /* synthetic */ String f2568e;

            /* renamed from: f  reason: collision with root package name */
            final /* synthetic */ Bundle f2569f;

            RunnableC0013c(String str, Bundle bundle) {
                this.f2568e = str;
                this.f2569f = bundle;
            }

            @Override // java.lang.Runnable
            public void run() {
                a.this.f2561f.extraCallback(this.f2568e, this.f2569f);
            }
        }

        /* loaded from: classes.dex */
        class d implements Runnable {

            /* renamed from: e  reason: collision with root package name */
            final /* synthetic */ Bundle f2571e;

            d(Bundle bundle) {
                this.f2571e = bundle;
            }

            @Override // java.lang.Runnable
            public void run() {
                a.this.f2561f.onMessageChannelReady(this.f2571e);
            }
        }

        /* loaded from: classes.dex */
        class e implements Runnable {

            /* renamed from: e  reason: collision with root package name */
            final /* synthetic */ String f2573e;

            /* renamed from: f  reason: collision with root package name */
            final /* synthetic */ Bundle f2574f;

            e(String str, Bundle bundle) {
                this.f2573e = str;
                this.f2574f = bundle;
            }

            @Override // java.lang.Runnable
            public void run() {
                a.this.f2561f.onPostMessage(this.f2573e, this.f2574f);
            }
        }

        /* loaded from: classes.dex */
        class f implements Runnable {

            /* renamed from: e  reason: collision with root package name */
            final /* synthetic */ int f2576e;

            /* renamed from: f  reason: collision with root package name */
            final /* synthetic */ Uri f2577f;

            /* renamed from: g  reason: collision with root package name */
            final /* synthetic */ boolean f2578g;

            /* renamed from: h  reason: collision with root package name */
            final /* synthetic */ Bundle f2579h;

            f(int i8, Uri uri, boolean z7, Bundle bundle) {
                this.f2576e = i8;
                this.f2577f = uri;
                this.f2578g = z7;
                this.f2579h = bundle;
            }

            @Override // java.lang.Runnable
            public void run() {
                a.this.f2561f.onRelationshipValidationResult(this.f2576e, this.f2577f, this.f2578g, this.f2579h);
            }
        }

        /* loaded from: classes.dex */
        class g implements Runnable {

            /* renamed from: e  reason: collision with root package name */
            final /* synthetic */ int f2581e;

            /* renamed from: f  reason: collision with root package name */
            final /* synthetic */ int f2582f;

            /* renamed from: g  reason: collision with root package name */
            final /* synthetic */ Bundle f2583g;

            g(int i8, int i9, Bundle bundle) {
                this.f2581e = i8;
                this.f2582f = i9;
                this.f2583g = bundle;
            }

            @Override // java.lang.Runnable
            public void run() {
                a.this.f2561f.onActivityResized(this.f2581e, this.f2582f, this.f2583g);
            }
        }

        /* loaded from: classes.dex */
        class h implements Runnable {

            /* renamed from: e  reason: collision with root package name */
            final /* synthetic */ Bundle f2585e;

            h(Bundle bundle) {
                this.f2585e = bundle;
            }

            @Override // java.lang.Runnable
            public void run() {
                a.this.f2561f.onWarmupCompleted(this.f2585e);
            }
        }

        /* loaded from: classes.dex */
        class i implements Runnable {

            /* renamed from: e  reason: collision with root package name */
            final /* synthetic */ int f2587e;

            /* renamed from: f  reason: collision with root package name */
            final /* synthetic */ int f2588f;

            /* renamed from: g  reason: collision with root package name */
            final /* synthetic */ int f2589g;

            /* renamed from: h  reason: collision with root package name */
            final /* synthetic */ int f2590h;

            /* renamed from: i  reason: collision with root package name */
            final /* synthetic */ int f2591i;

            /* renamed from: j  reason: collision with root package name */
            final /* synthetic */ Bundle f2592j;

            i(int i8, int i9, int i10, int i11, int i12, Bundle bundle) {
                this.f2587e = i8;
                this.f2588f = i9;
                this.f2589g = i10;
                this.f2590h = i11;
                this.f2591i = i12;
                this.f2592j = bundle;
            }

            @Override // java.lang.Runnable
            public void run() {
                a.this.f2561f.onActivityLayout(this.f2587e, this.f2588f, this.f2589g, this.f2590h, this.f2591i, this.f2592j);
            }
        }

        /* loaded from: classes.dex */
        class j implements Runnable {

            /* renamed from: e  reason: collision with root package name */
            final /* synthetic */ Bundle f2594e;

            j(Bundle bundle) {
                this.f2594e = bundle;
            }

            @Override // java.lang.Runnable
            public void run() {
                a.this.f2561f.onMinimized(this.f2594e);
            }
        }

        a(androidx.browser.customtabs.b bVar) {
            this.f2561f = bVar;
        }

        @Override // b.a
        public void C(Bundle bundle) {
            if (this.f2561f == null) {
                return;
            }
            this.f2560e.post(new h(bundle));
        }

        @Override // b.a
        public void G(int i8, Bundle bundle) {
            if (this.f2561f == null) {
                return;
            }
            this.f2560e.post(new b(i8, bundle));
        }

        @Override // b.a
        public Bundle J(String str, Bundle bundle) {
            androidx.browser.customtabs.b bVar = this.f2561f;
            if (bVar == null) {
                return null;
            }
            return bVar.extraCallbackWithResult(str, bundle);
        }

        @Override // b.a
        public void R(String str, Bundle bundle) {
            if (this.f2561f == null) {
                return;
            }
            this.f2560e.post(new e(str, bundle));
        }

        @Override // b.a
        public void T(Bundle bundle) {
            if (this.f2561f == null) {
                return;
            }
            this.f2560e.post(new d(bundle));
        }

        @Override // b.a
        public void W(int i8, Uri uri, boolean z7, Bundle bundle) {
            if (this.f2561f == null) {
                return;
            }
            this.f2560e.post(new f(i8, uri, z7, bundle));
        }

        @Override // b.a
        public void X(Bundle bundle) {
            if (this.f2561f == null) {
                return;
            }
            this.f2560e.post(new j(bundle));
        }

        @Override // b.a
        public void a0(Bundle bundle) {
            if (this.f2561f == null) {
                return;
            }
            this.f2560e.post(new RunnableC0012a(bundle));
        }

        @Override // b.a
        public void i(int i8, int i9, Bundle bundle) {
            if (this.f2561f == null) {
                return;
            }
            this.f2560e.post(new g(i8, i9, bundle));
        }

        @Override // b.a
        public void v(String str, Bundle bundle) {
            if (this.f2561f == null) {
                return;
            }
            this.f2560e.post(new RunnableC0013c(str, bundle));
        }

        @Override // b.a
        public void z(int i8, int i9, int i10, int i11, int i12, Bundle bundle) {
            if (this.f2561f == null) {
                return;
            }
            this.f2560e.post(new i(i8, i9, i10, i11, i12, bundle));
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public c(b.b bVar, ComponentName componentName, Context context) {
        this.f2557a = bVar;
        this.f2558b = componentName;
        this.f2559c = context;
    }

    public static boolean a(Context context, String str, e eVar) {
        eVar.setApplicationContext(context.getApplicationContext());
        Intent intent = new Intent("android.support.customtabs.action.CustomTabsService");
        if (!TextUtils.isEmpty(str)) {
            intent.setPackage(str);
        }
        return context.bindService(intent, eVar, 33);
    }

    private a.AbstractBinderC0056a b(b bVar) {
        return new a(bVar);
    }

    public static String c(Context context, List list, boolean z7) {
        ResolveInfo resolveActivity;
        PackageManager packageManager = context.getPackageManager();
        List<String> arrayList = list == null ? new ArrayList() : list;
        Intent intent = new Intent("android.intent.action.VIEW", Uri.parse("http://"));
        if (!z7 && (resolveActivity = packageManager.resolveActivity(intent, 0)) != null) {
            String str = resolveActivity.activityInfo.packageName;
            ArrayList arrayList2 = new ArrayList(arrayList.size() + 1);
            arrayList2.add(str);
            if (list != null) {
                arrayList2.addAll(list);
            }
            arrayList = arrayList2;
        }
        Intent intent2 = new Intent("android.support.customtabs.action.CustomTabsService");
        for (String str2 : arrayList) {
            intent2.setPackage(str2);
            if (packageManager.resolveService(intent2, 0) != null) {
                return str2;
            }
        }
        if (Build.VERSION.SDK_INT >= 30) {
            Log.w("CustomTabsClient", "Unable to find any Custom Tabs packages, you may need to add a <queries> element to your manifest. See the docs for CustomTabsClient#getPackageName.");
            return null;
        }
        return null;
    }

    private i e(b bVar, PendingIntent pendingIntent) {
        boolean Z;
        a.AbstractBinderC0056a b8 = b(bVar);
        try {
            if (pendingIntent != null) {
                Bundle bundle = new Bundle();
                bundle.putParcelable("android.support.customtabs.extra.SESSION_ID", pendingIntent);
                Z = this.f2557a.B(b8, bundle);
            } else {
                Z = this.f2557a.Z(b8);
            }
            if (Z) {
                return new i(this.f2557a, b8, this.f2558b, pendingIntent);
            }
            return null;
        } catch (RemoteException unused) {
            return null;
        }
    }

    public i d(b bVar) {
        return e(bVar, null);
    }

    public boolean f(long j8) {
        try {
            return this.f2557a.U(j8);
        } catch (RemoteException unused) {
            return false;
        }
    }
}
