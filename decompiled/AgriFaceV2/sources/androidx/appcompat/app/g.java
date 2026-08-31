package androidx.appcompat.app;

import android.app.Activity;
import android.app.Dialog;
import android.app.LocaleManager;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.LocaleList;
import android.util.Log;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.window.OnBackInvokedDispatcher;
import androidx.appcompat.app.t;
import androidx.appcompat.view.b;
import androidx.appcompat.widget.Toolbar;
import java.lang.ref.WeakReference;
import java.util.Iterator;
/* loaded from: classes.dex */
public abstract class g {

    /* renamed from: a  reason: collision with root package name */
    static t.a f1580a = new t.a(new t.b());

    /* renamed from: b  reason: collision with root package name */
    private static int f1581b = -100;

    /* renamed from: c  reason: collision with root package name */
    private static androidx.core.os.h f1582c = null;

    /* renamed from: d  reason: collision with root package name */
    private static androidx.core.os.h f1583d = null;

    /* renamed from: e  reason: collision with root package name */
    private static Boolean f1584e = null;

    /* renamed from: f  reason: collision with root package name */
    private static boolean f1585f = false;

    /* renamed from: g  reason: collision with root package name */
    private static final b1.b f1586g = new b1.b();

    /* renamed from: h  reason: collision with root package name */
    private static final Object f1587h = new Object();

    /* renamed from: i  reason: collision with root package name */
    private static final Object f1588i = new Object();

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static class a {
        /* JADX INFO: Access modifiers changed from: package-private */
        public static LocaleList a(String str) {
            return LocaleList.forLanguageTags(str);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static class b {
        static LocaleList a(Object obj) {
            return ((LocaleManager) obj).getApplicationLocales();
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public static void b(Object obj, LocaleList localeList) {
            ((LocaleManager) obj).setApplicationLocales(localeList);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void F(g gVar) {
        synchronized (f1587h) {
            G(gVar);
        }
    }

    private static void G(g gVar) {
        synchronized (f1587h) {
            Iterator it = f1586g.iterator();
            while (it.hasNext()) {
                g gVar2 = (g) ((WeakReference) it.next()).get();
                if (gVar2 == gVar || gVar2 == null) {
                    it.remove();
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void Q(final Context context) {
        if (v(context)) {
            if (androidx.core.os.a.b()) {
                if (f1585f) {
                    return;
                }
                f1580a.execute(new Runnable() { // from class: androidx.appcompat.app.f
                    @Override // java.lang.Runnable
                    public final void run() {
                        g.w(context);
                    }
                });
                return;
            }
            synchronized (f1588i) {
                androidx.core.os.h hVar = f1582c;
                if (hVar == null) {
                    if (f1583d == null) {
                        f1583d = androidx.core.os.h.b(t.b(context));
                    }
                    if (f1583d.e()) {
                        return;
                    }
                    f1582c = f1583d;
                } else if (!hVar.equals(f1583d)) {
                    androidx.core.os.h hVar2 = f1582c;
                    f1583d = hVar2;
                    t.a(context, hVar2.g());
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void d(g gVar) {
        synchronized (f1587h) {
            G(gVar);
            f1586g.add(new WeakReference(gVar));
        }
    }

    public static g h(Activity activity, e eVar) {
        return new h(activity, eVar);
    }

    public static g i(Dialog dialog, e eVar) {
        return new h(dialog, eVar);
    }

    public static androidx.core.os.h k() {
        if (androidx.core.os.a.b()) {
            Object p8 = p();
            if (p8 != null) {
                return androidx.core.os.h.i(b.a(p8));
            }
        } else {
            androidx.core.os.h hVar = f1582c;
            if (hVar != null) {
                return hVar;
            }
        }
        return androidx.core.os.h.d();
    }

    public static int m() {
        return f1581b;
    }

    static Object p() {
        Context l8;
        Iterator it = f1586g.iterator();
        while (it.hasNext()) {
            g gVar = (g) ((WeakReference) it.next()).get();
            if (gVar != null && (l8 = gVar.l()) != null) {
                return l8.getSystemService("locale");
            }
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static androidx.core.os.h r() {
        return f1582c;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean v(Context context) {
        if (f1584e == null) {
            try {
                Bundle bundle = r.a(context).metaData;
                if (bundle != null) {
                    f1584e = Boolean.valueOf(bundle.getBoolean("autoStoreLocales"));
                }
            } catch (PackageManager.NameNotFoundException unused) {
                Log.d("AppCompatDelegate", "Checking for metadata for AppLocalesMetadataHolderService : Service not found");
                f1584e = Boolean.FALSE;
            }
        }
        return f1584e.booleanValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void w(Context context) {
        t.c(context);
        f1585f = true;
    }

    public abstract void A(Bundle bundle);

    public abstract void B();

    public abstract void C(Bundle bundle);

    public abstract void D();

    public abstract void E();

    public abstract boolean H(int i8);

    public abstract void I(int i8);

    public abstract void J(View view);

    public abstract void K(View view, ViewGroup.LayoutParams layoutParams);

    public void L(OnBackInvokedDispatcher onBackInvokedDispatcher) {
    }

    public abstract void M(Toolbar toolbar);

    public abstract void N(int i8);

    public abstract void O(CharSequence charSequence);

    public abstract androidx.appcompat.view.b P(b.a aVar);

    public abstract void e(View view, ViewGroup.LayoutParams layoutParams);

    public void f(Context context) {
    }

    public Context g(Context context) {
        f(context);
        return context;
    }

    public abstract View j(int i8);

    public abstract Context l();

    public abstract androidx.appcompat.app.b n();

    public abstract int o();

    public abstract MenuInflater q();

    public abstract androidx.appcompat.app.a s();

    public abstract void t();

    public abstract void u();

    public abstract void x(Configuration configuration);

    public abstract void y(Bundle bundle);

    public abstract void z();
}
