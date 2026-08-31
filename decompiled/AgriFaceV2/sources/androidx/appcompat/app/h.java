package androidx.appcompat.app;

import android.app.Activity;
import android.app.Dialog;
import android.app.UiModeManager;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.media.AudioManager;
import android.os.Build;
import android.os.Bundle;
import android.os.LocaleList;
import android.os.PowerManager;
import android.text.TextUtils;
import android.util.AndroidRuntimeException;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.ActionMode;
import android.view.ContextThemeWrapper;
import android.view.KeyCharacterMap;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.window.OnBackInvokedCallback;
import android.window.OnBackInvokedDispatcher;
import androidx.appcompat.view.b;
import androidx.appcompat.view.f;
import androidx.appcompat.view.menu.g;
import androidx.appcompat.view.menu.m;
import androidx.appcompat.widget.ActionBarContextView;
import androidx.appcompat.widget.ContentFrameLayout;
import androidx.appcompat.widget.Toolbar;
import androidx.appcompat.widget.h1;
import androidx.appcompat.widget.i0;
import androidx.appcompat.widget.i1;
import androidx.core.content.res.f;
import androidx.core.view.b1;
import androidx.core.view.h0;
import androidx.core.view.p0;
import androidx.core.view.t;
import androidx.core.view.x0;
import androidx.core.view.z0;
import androidx.lifecycle.i;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import org.xmlpull.v1.XmlPullParser;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class h extends androidx.appcompat.app.g implements g.a, LayoutInflater.Factory2 {

    /* renamed from: j0  reason: collision with root package name */
    private static final b1.g f1589j0 = new b1.g();

    /* renamed from: k0  reason: collision with root package name */
    private static final boolean f1590k0 = false;

    /* renamed from: l0  reason: collision with root package name */
    private static final int[] f1591l0 = {16842836};

    /* renamed from: m0  reason: collision with root package name */
    private static final boolean f1592m0 = !"robolectric".equals(Build.FINGERPRINT);

    /* renamed from: n0  reason: collision with root package name */
    private static final boolean f1593n0 = true;
    private boolean A;
    ViewGroup B;
    private TextView C;
    private View D;
    private boolean E;
    private boolean F;
    boolean G;
    boolean H;
    boolean I;
    boolean J;
    boolean K;
    private boolean L;
    private u[] M;
    private u N;
    private boolean O;
    private boolean P;
    private boolean Q;
    boolean R;
    private Configuration S;
    private int T;
    private int U;
    private int V;
    private boolean W;
    private q X;
    private q Y;
    boolean Z;

    /* renamed from: a0  reason: collision with root package name */
    int f1594a0;

    /* renamed from: b0  reason: collision with root package name */
    private final Runnable f1595b0;

    /* renamed from: c0  reason: collision with root package name */
    private boolean f1596c0;

    /* renamed from: d0  reason: collision with root package name */
    private Rect f1597d0;

    /* renamed from: e0  reason: collision with root package name */
    private Rect f1598e0;

    /* renamed from: f0  reason: collision with root package name */
    private androidx.appcompat.app.q f1599f0;

    /* renamed from: g0  reason: collision with root package name */
    private androidx.appcompat.app.u f1600g0;

    /* renamed from: h0  reason: collision with root package name */
    private OnBackInvokedDispatcher f1601h0;

    /* renamed from: i0  reason: collision with root package name */
    private OnBackInvokedCallback f1602i0;

    /* renamed from: j  reason: collision with root package name */
    final Object f1603j;

    /* renamed from: k  reason: collision with root package name */
    final Context f1604k;

    /* renamed from: l  reason: collision with root package name */
    Window f1605l;

    /* renamed from: m  reason: collision with root package name */
    private o f1606m;

    /* renamed from: n  reason: collision with root package name */
    final androidx.appcompat.app.e f1607n;

    /* renamed from: o  reason: collision with root package name */
    androidx.appcompat.app.a f1608o;

    /* renamed from: p  reason: collision with root package name */
    MenuInflater f1609p;

    /* renamed from: q  reason: collision with root package name */
    private CharSequence f1610q;

    /* renamed from: r  reason: collision with root package name */
    private i0 f1611r;

    /* renamed from: s  reason: collision with root package name */
    private C0007h f1612s;

    /* renamed from: t  reason: collision with root package name */
    private v f1613t;

    /* renamed from: u  reason: collision with root package name */
    androidx.appcompat.view.b f1614u;

    /* renamed from: v  reason: collision with root package name */
    ActionBarContextView f1615v;

    /* renamed from: w  reason: collision with root package name */
    PopupWindow f1616w;

    /* renamed from: x  reason: collision with root package name */
    Runnable f1617x;

    /* renamed from: y  reason: collision with root package name */
    x0 f1618y;

    /* renamed from: z  reason: collision with root package name */
    private boolean f1619z;

    /* loaded from: classes.dex */
    class a implements Runnable {
        a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            h hVar = h.this;
            if ((hVar.f1594a0 & 1) != 0) {
                hVar.i0(0);
            }
            h hVar2 = h.this;
            if ((hVar2.f1594a0 & 4096) != 0) {
                hVar2.i0(108);
            }
            h hVar3 = h.this;
            hVar3.Z = false;
            hVar3.f1594a0 = 0;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class b implements h0 {
        b() {
        }

        @Override // androidx.core.view.h0
        public b1 a(View view, b1 b1Var) {
            int k8 = b1Var.k();
            int e12 = h.this.e1(b1Var, null);
            if (k8 != e12) {
                b1Var = b1Var.o(b1Var.i(), e12, b1Var.j(), b1Var.h());
            }
            return p0.F(view, b1Var);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class c implements ContentFrameLayout.a {
        c() {
        }

        @Override // androidx.appcompat.widget.ContentFrameLayout.a
        public void a() {
        }

        @Override // androidx.appcompat.widget.ContentFrameLayout.a
        public void onDetachedFromWindow() {
            h.this.g0();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class d implements Runnable {

        /* loaded from: classes.dex */
        class a extends z0 {
            a() {
            }

            @Override // androidx.core.view.y0
            public void b(View view) {
                h.this.f1615v.setAlpha(1.0f);
                h.this.f1618y.h(null);
                h.this.f1618y = null;
            }

            @Override // androidx.core.view.z0, androidx.core.view.y0
            public void c(View view) {
                h.this.f1615v.setVisibility(0);
            }
        }

        d() {
        }

        @Override // java.lang.Runnable
        public void run() {
            h hVar = h.this;
            hVar.f1616w.showAtLocation(hVar.f1615v, 55, 0, 0);
            h.this.j0();
            if (!h.this.U0()) {
                h.this.f1615v.setAlpha(1.0f);
                h.this.f1615v.setVisibility(0);
                return;
            }
            h.this.f1615v.setAlpha(0.0f);
            h hVar2 = h.this;
            hVar2.f1618y = p0.c(hVar2.f1615v).b(1.0f);
            h.this.f1618y.h(new a());
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class e extends z0 {
        e() {
        }

        @Override // androidx.core.view.y0
        public void b(View view) {
            h.this.f1615v.setAlpha(1.0f);
            h.this.f1618y.h(null);
            h.this.f1618y = null;
        }

        @Override // androidx.core.view.z0, androidx.core.view.y0
        public void c(View view) {
            h.this.f1615v.setVisibility(0);
            if (h.this.f1615v.getParent() instanceof View) {
                p0.K((View) h.this.f1615v.getParent());
            }
        }
    }

    /* loaded from: classes.dex */
    private class f implements androidx.appcompat.app.b {
        f() {
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public interface g {
        boolean a(int i8);

        View onCreatePanelView(int i8);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: androidx.appcompat.app.h$h  reason: collision with other inner class name */
    /* loaded from: classes.dex */
    public final class C0007h implements m.a {
        C0007h() {
        }

        @Override // androidx.appcompat.view.menu.m.a
        public void b(androidx.appcompat.view.menu.g gVar, boolean z7) {
            h.this.Z(gVar);
        }

        @Override // androidx.appcompat.view.menu.m.a
        public boolean c(androidx.appcompat.view.menu.g gVar) {
            Window.Callback v02 = h.this.v0();
            if (v02 != null) {
                v02.onMenuOpened(108, gVar);
                return true;
            }
            return true;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class i implements b.a {

        /* renamed from: a  reason: collision with root package name */
        private b.a f1628a;

        /* loaded from: classes.dex */
        class a extends z0 {
            a() {
            }

            @Override // androidx.core.view.y0
            public void b(View view) {
                h.this.f1615v.setVisibility(8);
                h hVar = h.this;
                PopupWindow popupWindow = hVar.f1616w;
                if (popupWindow != null) {
                    popupWindow.dismiss();
                } else if (hVar.f1615v.getParent() instanceof View) {
                    p0.K((View) h.this.f1615v.getParent());
                }
                h.this.f1615v.k();
                h.this.f1618y.h(null);
                h hVar2 = h.this;
                hVar2.f1618y = null;
                p0.K(hVar2.B);
            }
        }

        public i(b.a aVar) {
            this.f1628a = aVar;
        }

        @Override // androidx.appcompat.view.b.a
        public boolean a(androidx.appcompat.view.b bVar, Menu menu) {
            p0.K(h.this.B);
            return this.f1628a.a(bVar, menu);
        }

        @Override // androidx.appcompat.view.b.a
        public void b(androidx.appcompat.view.b bVar) {
            this.f1628a.b(bVar);
            h hVar = h.this;
            if (hVar.f1616w != null) {
                hVar.f1605l.getDecorView().removeCallbacks(h.this.f1617x);
            }
            h hVar2 = h.this;
            if (hVar2.f1615v != null) {
                hVar2.j0();
                h hVar3 = h.this;
                hVar3.f1618y = p0.c(hVar3.f1615v).b(0.0f);
                h.this.f1618y.h(new a());
            }
            h hVar4 = h.this;
            androidx.appcompat.app.e eVar = hVar4.f1607n;
            if (eVar != null) {
                eVar.onSupportActionModeFinished(hVar4.f1614u);
            }
            h hVar5 = h.this;
            hVar5.f1614u = null;
            p0.K(hVar5.B);
            h.this.c1();
        }

        @Override // androidx.appcompat.view.b.a
        public boolean c(androidx.appcompat.view.b bVar, MenuItem menuItem) {
            return this.f1628a.c(bVar, menuItem);
        }

        @Override // androidx.appcompat.view.b.a
        public boolean d(androidx.appcompat.view.b bVar, Menu menu) {
            return this.f1628a.d(bVar, menu);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static class j {
        static Context a(Context context, Configuration configuration) {
            return context.createConfigurationContext(configuration);
        }

        static void b(Configuration configuration, Configuration configuration2, Configuration configuration3) {
            int i8 = configuration.densityDpi;
            int i9 = configuration2.densityDpi;
            if (i8 != i9) {
                configuration3.densityDpi = i9;
            }
        }

        static void c(Configuration configuration, Locale locale) {
            configuration.setLayoutDirection(locale);
        }

        static void d(Configuration configuration, Locale locale) {
            configuration.setLocale(locale);
        }
    }

    /* loaded from: classes.dex */
    static class k {
        static boolean a(PowerManager powerManager) {
            return powerManager.isPowerSaveMode();
        }

        static String b(Locale locale) {
            return locale.toLanguageTag();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static class l {
        static void a(Configuration configuration, Configuration configuration2, Configuration configuration3) {
            LocaleList locales = configuration.getLocales();
            LocaleList locales2 = configuration2.getLocales();
            if (locales.equals(locales2)) {
                return;
            }
            configuration3.setLocales(locales2);
            configuration3.locale = configuration2.locale;
        }

        static androidx.core.os.h b(Configuration configuration) {
            return androidx.core.os.h.b(configuration.getLocales().toLanguageTags());
        }

        public static void c(androidx.core.os.h hVar) {
            LocaleList.setDefault(LocaleList.forLanguageTags(hVar.g()));
        }

        static void d(Configuration configuration, androidx.core.os.h hVar) {
            configuration.setLocales(LocaleList.forLanguageTags(hVar.g()));
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static class m {
        static void a(Configuration configuration, Configuration configuration2, Configuration configuration3) {
            int i8 = configuration.colorMode & 3;
            int i9 = configuration2.colorMode;
            if (i8 != (i9 & 3)) {
                configuration3.colorMode |= i9 & 3;
            }
            int i10 = configuration.colorMode & 12;
            int i11 = configuration2.colorMode;
            if (i10 != (i11 & 12)) {
                configuration3.colorMode |= i11 & 12;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static class n {
        static OnBackInvokedDispatcher a(Activity activity) {
            OnBackInvokedDispatcher onBackInvokedDispatcher;
            onBackInvokedDispatcher = activity.getOnBackInvokedDispatcher();
            return onBackInvokedDispatcher;
        }

        static OnBackInvokedCallback b(Object obj, final h hVar) {
            Objects.requireNonNull(hVar);
            OnBackInvokedCallback onBackInvokedCallback = new OnBackInvokedCallback() { // from class: androidx.appcompat.app.n
                public final void onBackInvoked() {
                    h.this.D0();
                }
            };
            androidx.appcompat.app.k.a(obj).registerOnBackInvokedCallback(1000000, onBackInvokedCallback);
            return onBackInvokedCallback;
        }

        static void c(Object obj, Object obj2) {
            androidx.appcompat.app.k.a(obj).unregisterOnBackInvokedCallback(androidx.appcompat.app.j.a(obj2));
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class o extends androidx.appcompat.view.i {

        /* renamed from: b  reason: collision with root package name */
        private g f1631b;

        /* renamed from: c  reason: collision with root package name */
        private boolean f1632c;

        /* renamed from: d  reason: collision with root package name */
        private boolean f1633d;

        /* renamed from: e  reason: collision with root package name */
        private boolean f1634e;

        o(Window.Callback callback) {
            super(callback);
        }

        public boolean b(Window.Callback callback, KeyEvent keyEvent) {
            try {
                this.f1633d = true;
                return callback.dispatchKeyEvent(keyEvent);
            } finally {
                this.f1633d = false;
            }
        }

        public void c(Window.Callback callback) {
            try {
                this.f1632c = true;
                callback.onContentChanged();
            } finally {
                this.f1632c = false;
            }
        }

        public void d(Window.Callback callback, int i8, Menu menu) {
            try {
                this.f1634e = true;
                callback.onPanelClosed(i8, menu);
            } finally {
                this.f1634e = false;
            }
        }

        @Override // androidx.appcompat.view.i, android.view.Window.Callback
        public boolean dispatchKeyEvent(KeyEvent keyEvent) {
            return this.f1633d ? a().dispatchKeyEvent(keyEvent) : h.this.h0(keyEvent) || super.dispatchKeyEvent(keyEvent);
        }

        @Override // androidx.appcompat.view.i, android.view.Window.Callback
        public boolean dispatchKeyShortcutEvent(KeyEvent keyEvent) {
            return super.dispatchKeyShortcutEvent(keyEvent) || h.this.G0(keyEvent.getKeyCode(), keyEvent);
        }

        void e(g gVar) {
            this.f1631b = gVar;
        }

        final ActionMode f(ActionMode.Callback callback) {
            f.a aVar = new f.a(h.this.f1604k, callback);
            androidx.appcompat.view.b P = h.this.P(aVar);
            if (P != null) {
                return aVar.e(P);
            }
            return null;
        }

        @Override // android.view.Window.Callback
        public void onContentChanged() {
            if (this.f1632c) {
                a().onContentChanged();
            }
        }

        @Override // androidx.appcompat.view.i, android.view.Window.Callback
        public boolean onCreatePanelMenu(int i8, Menu menu) {
            if (i8 != 0 || (menu instanceof androidx.appcompat.view.menu.g)) {
                return super.onCreatePanelMenu(i8, menu);
            }
            return false;
        }

        @Override // androidx.appcompat.view.i, android.view.Window.Callback
        public View onCreatePanelView(int i8) {
            View onCreatePanelView;
            g gVar = this.f1631b;
            return (gVar == null || (onCreatePanelView = gVar.onCreatePanelView(i8)) == null) ? super.onCreatePanelView(i8) : onCreatePanelView;
        }

        @Override // androidx.appcompat.view.i, android.view.Window.Callback
        public boolean onMenuOpened(int i8, Menu menu) {
            super.onMenuOpened(i8, menu);
            h.this.J0(i8);
            return true;
        }

        @Override // androidx.appcompat.view.i, android.view.Window.Callback
        public void onPanelClosed(int i8, Menu menu) {
            if (this.f1634e) {
                a().onPanelClosed(i8, menu);
                return;
            }
            super.onPanelClosed(i8, menu);
            h.this.K0(i8);
        }

        @Override // androidx.appcompat.view.i, android.view.Window.Callback
        public boolean onPreparePanel(int i8, View view, Menu menu) {
            androidx.appcompat.view.menu.g gVar = menu instanceof androidx.appcompat.view.menu.g ? (androidx.appcompat.view.menu.g) menu : null;
            if (i8 == 0 && gVar == null) {
                return false;
            }
            boolean z7 = true;
            if (gVar != null) {
                gVar.b0(true);
            }
            g gVar2 = this.f1631b;
            if (gVar2 == null || !gVar2.a(i8)) {
                z7 = false;
            }
            if (!z7) {
                z7 = super.onPreparePanel(i8, view, menu);
            }
            if (gVar != null) {
                gVar.b0(false);
            }
            return z7;
        }

        @Override // androidx.appcompat.view.i, android.view.Window.Callback
        public void onProvideKeyboardShortcuts(List list, Menu menu, int i8) {
            androidx.appcompat.view.menu.g gVar;
            u t02 = h.this.t0(0, true);
            if (t02 == null || (gVar = t02.f1653j) == null) {
                super.onProvideKeyboardShortcuts(list, menu, i8);
            } else {
                super.onProvideKeyboardShortcuts(list, gVar, i8);
            }
        }

        @Override // android.view.Window.Callback
        public ActionMode onWindowStartingActionMode(ActionMode.Callback callback) {
            return null;
        }

        @Override // androidx.appcompat.view.i, android.view.Window.Callback
        public ActionMode onWindowStartingActionMode(ActionMode.Callback callback, int i8) {
            return (h.this.B0() && i8 == 0) ? f(callback) : super.onWindowStartingActionMode(callback, i8);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public class p extends q {

        /* renamed from: c  reason: collision with root package name */
        private final PowerManager f1636c;

        p(Context context) {
            super();
            this.f1636c = (PowerManager) context.getApplicationContext().getSystemService("power");
        }

        @Override // androidx.appcompat.app.h.q
        IntentFilter b() {
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("android.os.action.POWER_SAVE_MODE_CHANGED");
            return intentFilter;
        }

        @Override // androidx.appcompat.app.h.q
        public int c() {
            return k.a(this.f1636c) ? 2 : 1;
        }

        @Override // androidx.appcompat.app.h.q
        public void d() {
            h.this.T();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public abstract class q {

        /* renamed from: a  reason: collision with root package name */
        private BroadcastReceiver f1638a;

        /* JADX INFO: Access modifiers changed from: package-private */
        /* loaded from: classes.dex */
        public class a extends BroadcastReceiver {
            a() {
            }

            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context, Intent intent) {
                q.this.d();
            }
        }

        q() {
        }

        void a() {
            BroadcastReceiver broadcastReceiver = this.f1638a;
            if (broadcastReceiver != null) {
                try {
                    h.this.f1604k.unregisterReceiver(broadcastReceiver);
                } catch (IllegalArgumentException unused) {
                }
                this.f1638a = null;
            }
        }

        abstract IntentFilter b();

        abstract int c();

        abstract void d();

        void e() {
            a();
            IntentFilter b8 = b();
            if (b8 == null || b8.countActions() == 0) {
                return;
            }
            if (this.f1638a == null) {
                this.f1638a = new a();
            }
            h.this.f1604k.registerReceiver(this.f1638a, b8);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public class r extends q {

        /* renamed from: c  reason: collision with root package name */
        private final z f1641c;

        r(z zVar) {
            super();
            this.f1641c = zVar;
        }

        @Override // androidx.appcompat.app.h.q
        IntentFilter b() {
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("android.intent.action.TIME_SET");
            intentFilter.addAction("android.intent.action.TIMEZONE_CHANGED");
            intentFilter.addAction("android.intent.action.TIME_TICK");
            return intentFilter;
        }

        @Override // androidx.appcompat.app.h.q
        public int c() {
            return this.f1641c.d() ? 2 : 1;
        }

        @Override // androidx.appcompat.app.h.q
        public void d() {
            h.this.T();
        }
    }

    /* loaded from: classes.dex */
    private static class s {
        static void a(ContextThemeWrapper contextThemeWrapper, Configuration configuration) {
            contextThemeWrapper.applyOverrideConfiguration(configuration);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public class t extends ContentFrameLayout {
        public t(Context context) {
            super(context);
        }

        private boolean b(int i8, int i9) {
            return i8 < -5 || i9 < -5 || i8 > getWidth() + 5 || i9 > getHeight() + 5;
        }

        @Override // android.view.ViewGroup, android.view.View
        public boolean dispatchKeyEvent(KeyEvent keyEvent) {
            return h.this.h0(keyEvent) || super.dispatchKeyEvent(keyEvent);
        }

        @Override // android.view.ViewGroup
        public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
            if (motionEvent.getAction() == 0 && b((int) motionEvent.getX(), (int) motionEvent.getY())) {
                h.this.b0(0);
                return true;
            }
            return super.onInterceptTouchEvent(motionEvent);
        }

        @Override // android.view.View
        public void setBackgroundResource(int i8) {
            setBackgroundDrawable(h.a.b(getContext(), i8));
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* loaded from: classes.dex */
    public static final class u {

        /* renamed from: a  reason: collision with root package name */
        int f1644a;

        /* renamed from: b  reason: collision with root package name */
        int f1645b;

        /* renamed from: c  reason: collision with root package name */
        int f1646c;

        /* renamed from: d  reason: collision with root package name */
        int f1647d;

        /* renamed from: e  reason: collision with root package name */
        int f1648e;

        /* renamed from: f  reason: collision with root package name */
        int f1649f;

        /* renamed from: g  reason: collision with root package name */
        ViewGroup f1650g;

        /* renamed from: h  reason: collision with root package name */
        View f1651h;

        /* renamed from: i  reason: collision with root package name */
        View f1652i;

        /* renamed from: j  reason: collision with root package name */
        androidx.appcompat.view.menu.g f1653j;

        /* renamed from: k  reason: collision with root package name */
        androidx.appcompat.view.menu.e f1654k;

        /* renamed from: l  reason: collision with root package name */
        Context f1655l;

        /* renamed from: m  reason: collision with root package name */
        boolean f1656m;

        /* renamed from: n  reason: collision with root package name */
        boolean f1657n;

        /* renamed from: o  reason: collision with root package name */
        boolean f1658o;

        /* renamed from: p  reason: collision with root package name */
        public boolean f1659p;

        /* renamed from: q  reason: collision with root package name */
        boolean f1660q = false;

        /* renamed from: r  reason: collision with root package name */
        boolean f1661r;

        /* renamed from: s  reason: collision with root package name */
        Bundle f1662s;

        u(int i8) {
            this.f1644a = i8;
        }

        androidx.appcompat.view.menu.n a(m.a aVar) {
            if (this.f1653j == null) {
                return null;
            }
            if (this.f1654k == null) {
                androidx.appcompat.view.menu.e eVar = new androidx.appcompat.view.menu.e(this.f1655l, g.g.f6897j);
                this.f1654k = eVar;
                eVar.k(aVar);
                this.f1653j.b(this.f1654k);
            }
            return this.f1654k.c(this.f1650g);
        }

        public boolean b() {
            if (this.f1651h == null) {
                return false;
            }
            return this.f1652i != null || this.f1654k.a().getCount() > 0;
        }

        void c(androidx.appcompat.view.menu.g gVar) {
            androidx.appcompat.view.menu.e eVar;
            androidx.appcompat.view.menu.g gVar2 = this.f1653j;
            if (gVar == gVar2) {
                return;
            }
            if (gVar2 != null) {
                gVar2.O(this.f1654k);
            }
            this.f1653j = gVar;
            if (gVar == null || (eVar = this.f1654k) == null) {
                return;
            }
            gVar.b(eVar);
        }

        void d(Context context) {
            TypedValue typedValue = new TypedValue();
            Resources.Theme newTheme = context.getResources().newTheme();
            newTheme.setTo(context.getTheme());
            newTheme.resolveAttribute(g.a.f6792a, typedValue, true);
            int i8 = typedValue.resourceId;
            if (i8 != 0) {
                newTheme.applyStyle(i8, true);
            }
            newTheme.resolveAttribute(g.a.B, typedValue, true);
            int i9 = typedValue.resourceId;
            if (i9 == 0) {
                i9 = g.i.f6919a;
            }
            newTheme.applyStyle(i9, true);
            androidx.appcompat.view.d dVar = new androidx.appcompat.view.d(context, 0);
            dVar.getTheme().setTo(newTheme);
            this.f1655l = dVar;
            TypedArray obtainStyledAttributes = dVar.obtainStyledAttributes(g.j.f7044y0);
            this.f1645b = obtainStyledAttributes.getResourceId(g.j.B0, 0);
            this.f1649f = obtainStyledAttributes.getResourceId(g.j.A0, 0);
            obtainStyledAttributes.recycle();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public final class v implements m.a {
        v() {
        }

        @Override // androidx.appcompat.view.menu.m.a
        public void b(androidx.appcompat.view.menu.g gVar, boolean z7) {
            androidx.appcompat.view.menu.g D = gVar.D();
            boolean z8 = D != gVar;
            h hVar = h.this;
            if (z8) {
                gVar = D;
            }
            u m02 = hVar.m0(gVar);
            if (m02 != null) {
                if (!z8) {
                    h.this.c0(m02, z7);
                    return;
                }
                h.this.Y(m02.f1644a, m02, D);
                h.this.c0(m02, true);
            }
        }

        @Override // androidx.appcompat.view.menu.m.a
        public boolean c(androidx.appcompat.view.menu.g gVar) {
            Window.Callback v02;
            if (gVar == gVar.D()) {
                h hVar = h.this;
                if (!hVar.G || (v02 = hVar.v0()) == null || h.this.R) {
                    return true;
                }
                v02.onMenuOpened(108, gVar);
                return true;
            }
            return true;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public h(Activity activity, androidx.appcompat.app.e eVar) {
        this(activity, null, eVar, activity);
    }

    private void A0(int i8) {
        this.f1594a0 = (1 << i8) | this.f1594a0;
        if (this.Z) {
            return;
        }
        p0.I(this.f1605l.getDecorView(), this.f1595b0);
        this.Z = true;
    }

    private boolean F0(int i8, KeyEvent keyEvent) {
        if (keyEvent.getRepeatCount() == 0) {
            u t02 = t0(i8, true);
            if (t02.f1658o) {
                return false;
            }
            return P0(t02, keyEvent);
        }
        return false;
    }

    private boolean I0(int i8, KeyEvent keyEvent) {
        boolean z7;
        i0 i0Var;
        if (this.f1614u != null) {
            return false;
        }
        boolean z8 = true;
        u t02 = t0(i8, true);
        if (i8 != 0 || (i0Var = this.f1611r) == null || !i0Var.h() || ViewConfiguration.get(this.f1604k).hasPermanentMenuKey()) {
            boolean z9 = t02.f1658o;
            if (z9 || t02.f1657n) {
                c0(t02, true);
                z8 = z9;
            } else {
                if (t02.f1656m) {
                    if (t02.f1661r) {
                        t02.f1656m = false;
                        z7 = P0(t02, keyEvent);
                    } else {
                        z7 = true;
                    }
                    if (z7) {
                        M0(t02, keyEvent);
                    }
                }
                z8 = false;
            }
        } else if (this.f1611r.b()) {
            z8 = this.f1611r.f();
        } else {
            if (!this.R && P0(t02, keyEvent)) {
                z8 = this.f1611r.g();
            }
            z8 = false;
        }
        if (z8) {
            AudioManager audioManager = (AudioManager) this.f1604k.getApplicationContext().getSystemService("audio");
            if (audioManager != null) {
                audioManager.playSoundEffect(0);
            } else {
                Log.w("AppCompatDelegate", "Couldn't get audio manager");
            }
        }
        return z8;
    }

    /* JADX WARN: Removed duplicated region for block: B:67:0x00f2  */
    /* JADX WARN: Removed duplicated region for block: B:72:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void M0(androidx.appcompat.app.h.u r13, android.view.KeyEvent r14) {
        /*
            Method dump skipped, instructions count: 249
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.app.h.M0(androidx.appcompat.app.h$u, android.view.KeyEvent):void");
    }

    private boolean O0(u uVar, int i8, KeyEvent keyEvent, int i9) {
        androidx.appcompat.view.menu.g gVar;
        boolean z7 = false;
        if (keyEvent.isSystem()) {
            return false;
        }
        if ((uVar.f1656m || P0(uVar, keyEvent)) && (gVar = uVar.f1653j) != null) {
            z7 = gVar.performShortcut(i8, keyEvent, i9);
        }
        if (z7 && (i9 & 1) == 0 && this.f1611r == null) {
            c0(uVar, true);
        }
        return z7;
    }

    private boolean P0(u uVar, KeyEvent keyEvent) {
        i0 i0Var;
        i0 i0Var2;
        i0 i0Var3;
        if (this.R) {
            return false;
        }
        if (uVar.f1656m) {
            return true;
        }
        u uVar2 = this.N;
        if (uVar2 != null && uVar2 != uVar) {
            c0(uVar2, false);
        }
        Window.Callback v02 = v0();
        if (v02 != null) {
            uVar.f1652i = v02.onCreatePanelView(uVar.f1644a);
        }
        int i8 = uVar.f1644a;
        boolean z7 = i8 == 0 || i8 == 108;
        if (z7 && (i0Var3 = this.f1611r) != null) {
            i0Var3.c();
        }
        if (uVar.f1652i == null && (!z7 || !(N0() instanceof x))) {
            androidx.appcompat.view.menu.g gVar = uVar.f1653j;
            if (gVar == null || uVar.f1661r) {
                if (gVar == null && (!z0(uVar) || uVar.f1653j == null)) {
                    return false;
                }
                if (z7 && this.f1611r != null) {
                    if (this.f1612s == null) {
                        this.f1612s = new C0007h();
                    }
                    this.f1611r.a(uVar.f1653j, this.f1612s);
                }
                uVar.f1653j.e0();
                if (!v02.onCreatePanelMenu(uVar.f1644a, uVar.f1653j)) {
                    uVar.c(null);
                    if (z7 && (i0Var = this.f1611r) != null) {
                        i0Var.a(null, this.f1612s);
                    }
                    return false;
                }
                uVar.f1661r = false;
            }
            uVar.f1653j.e0();
            Bundle bundle = uVar.f1662s;
            if (bundle != null) {
                uVar.f1653j.P(bundle);
                uVar.f1662s = null;
            }
            if (!v02.onPreparePanel(0, uVar.f1652i, uVar.f1653j)) {
                if (z7 && (i0Var2 = this.f1611r) != null) {
                    i0Var2.a(null, this.f1612s);
                }
                uVar.f1653j.d0();
                return false;
            }
            boolean z8 = KeyCharacterMap.load(keyEvent != null ? keyEvent.getDeviceId() : -1).getKeyboardType() != 1;
            uVar.f1659p = z8;
            uVar.f1653j.setQwertyMode(z8);
            uVar.f1653j.d0();
        }
        uVar.f1656m = true;
        uVar.f1657n = false;
        this.N = uVar;
        return true;
    }

    private void Q0(boolean z7) {
        i0 i0Var = this.f1611r;
        if (i0Var == null || !i0Var.h() || (ViewConfiguration.get(this.f1604k).hasPermanentMenuKey() && !this.f1611r.e())) {
            u t02 = t0(0, true);
            t02.f1660q = true;
            c0(t02, false);
            M0(t02, null);
            return;
        }
        Window.Callback v02 = v0();
        if (this.f1611r.b() && z7) {
            this.f1611r.f();
            if (this.R) {
                return;
            }
            v02.onPanelClosed(108, t0(0, true).f1653j);
        } else if (v02 == null || this.R) {
        } else {
            if (this.Z && (this.f1594a0 & 1) != 0) {
                this.f1605l.getDecorView().removeCallbacks(this.f1595b0);
                this.f1595b0.run();
            }
            u t03 = t0(0, true);
            androidx.appcompat.view.menu.g gVar = t03.f1653j;
            if (gVar == null || t03.f1661r || !v02.onPreparePanel(0, t03.f1652i, gVar)) {
                return;
            }
            v02.onMenuOpened(108, t03.f1653j);
            this.f1611r.g();
        }
    }

    private boolean R(boolean z7) {
        return S(z7, true);
    }

    private int R0(int i8) {
        if (i8 == 8) {
            Log.i("AppCompatDelegate", "You should now use the AppCompatDelegate.FEATURE_SUPPORT_ACTION_BAR id when requesting this feature.");
            return 108;
        } else if (i8 == 9) {
            Log.i("AppCompatDelegate", "You should now use the AppCompatDelegate.FEATURE_SUPPORT_ACTION_BAR_OVERLAY id when requesting this feature.");
            return 109;
        } else {
            return i8;
        }
    }

    private boolean S(boolean z7, boolean z8) {
        if (this.R) {
            return false;
        }
        int X = X();
        int C0 = C0(this.f1604k, X);
        androidx.core.os.h W = Build.VERSION.SDK_INT < 33 ? W(this.f1604k) : null;
        if (!z8 && W != null) {
            W = s0(this.f1604k.getResources().getConfiguration());
        }
        boolean b12 = b1(C0, W, z7);
        if (X == 0) {
            r0(this.f1604k).e();
        } else {
            q qVar = this.X;
            if (qVar != null) {
                qVar.a();
            }
        }
        if (X == 3) {
            q0(this.f1604k).e();
        } else {
            q qVar2 = this.Y;
            if (qVar2 != null) {
                qVar2.a();
            }
        }
        return b12;
    }

    private void U() {
        ContentFrameLayout contentFrameLayout = (ContentFrameLayout) this.B.findViewById(16908290);
        View decorView = this.f1605l.getDecorView();
        contentFrameLayout.a(decorView.getPaddingLeft(), decorView.getPaddingTop(), decorView.getPaddingRight(), decorView.getPaddingBottom());
        TypedArray obtainStyledAttributes = this.f1604k.obtainStyledAttributes(g.j.f7044y0);
        obtainStyledAttributes.getValue(g.j.K0, contentFrameLayout.getMinWidthMajor());
        obtainStyledAttributes.getValue(g.j.L0, contentFrameLayout.getMinWidthMinor());
        if (obtainStyledAttributes.hasValue(g.j.I0)) {
            obtainStyledAttributes.getValue(g.j.I0, contentFrameLayout.getFixedWidthMajor());
        }
        if (obtainStyledAttributes.hasValue(g.j.J0)) {
            obtainStyledAttributes.getValue(g.j.J0, contentFrameLayout.getFixedWidthMinor());
        }
        if (obtainStyledAttributes.hasValue(g.j.G0)) {
            obtainStyledAttributes.getValue(g.j.G0, contentFrameLayout.getFixedHeightMajor());
        }
        if (obtainStyledAttributes.hasValue(g.j.H0)) {
            obtainStyledAttributes.getValue(g.j.H0, contentFrameLayout.getFixedHeightMinor());
        }
        obtainStyledAttributes.recycle();
        contentFrameLayout.requestLayout();
    }

    private void V(Window window) {
        if (this.f1605l != null) {
            throw new IllegalStateException("AppCompat has already installed itself into the Window");
        }
        Window.Callback callback = window.getCallback();
        if (callback instanceof o) {
            throw new IllegalStateException("AppCompat has already installed itself into the Window");
        }
        o oVar = new o(callback);
        this.f1606m = oVar;
        window.setCallback(oVar);
        androidx.appcompat.widget.b1 t8 = androidx.appcompat.widget.b1.t(this.f1604k, null, f1591l0);
        Drawable g8 = t8.g(0);
        if (g8 != null) {
            window.setBackgroundDrawable(g8);
        }
        t8.v();
        this.f1605l = window;
        if (Build.VERSION.SDK_INT < 33 || this.f1601h0 != null) {
            return;
        }
        L(null);
    }

    private boolean V0(ViewParent viewParent) {
        if (viewParent == null) {
            return false;
        }
        View decorView = this.f1605l.getDecorView();
        while (viewParent != null) {
            if (viewParent == decorView || !(viewParent instanceof View) || p0.y((View) viewParent)) {
                return false;
            }
            viewParent = viewParent.getParent();
        }
        return true;
    }

    private int X() {
        int i8 = this.T;
        return i8 != -100 ? i8 : androidx.appcompat.app.g.m();
    }

    private void Y0() {
        if (this.A) {
            throw new AndroidRuntimeException("Window feature must be requested before adding content");
        }
    }

    private androidx.appcompat.app.d Z0() {
        for (Context context = this.f1604k; context != null; context = ((ContextWrapper) context).getBaseContext()) {
            if (context instanceof androidx.appcompat.app.d) {
                return (androidx.appcompat.app.d) context;
            }
            if (!(context instanceof ContextWrapper)) {
                break;
            }
        }
        return null;
    }

    private void a0() {
        q qVar = this.X;
        if (qVar != null) {
            qVar.a();
        }
        q qVar2 = this.Y;
        if (qVar2 != null) {
            qVar2.a();
        }
    }

    private void a1(Configuration configuration) {
        Activity activity = (Activity) this.f1603j;
        if (activity instanceof androidx.lifecycle.m) {
            if (!((androidx.lifecycle.m) activity).getLifecycle().b().e(i.b.CREATED)) {
                return;
            }
        } else if (!this.Q || this.R) {
            return;
        }
        activity.onConfigurationChanged(configuration);
    }

    /* JADX WARN: Removed duplicated region for block: B:40:0x0082  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private boolean b1(int r9, androidx.core.os.h r10, boolean r11) {
        /*
            r8 = this;
            android.content.Context r1 = r8.f1604k
            r4 = 0
            r5 = 0
            r0 = r8
            r2 = r9
            r3 = r10
            android.content.res.Configuration r0 = r0.d0(r1, r2, r3, r4, r5)
            android.content.Context r1 = r8.f1604k
            int r1 = r8.p0(r1)
            android.content.res.Configuration r2 = r8.S
            if (r2 != 0) goto L1f
            android.content.Context r2 = r8.f1604k
            android.content.res.Resources r2 = r2.getResources()
            android.content.res.Configuration r2 = r2.getConfiguration()
        L1f:
            int r3 = r2.uiMode
            r3 = r3 & 48
            int r4 = r0.uiMode
            r4 = r4 & 48
            androidx.core.os.h r2 = r8.s0(r2)
            r5 = 0
            if (r10 != 0) goto L30
            r0 = r5
            goto L34
        L30:
            androidx.core.os.h r0 = r8.s0(r0)
        L34:
            r6 = 0
            if (r3 == r4) goto L3a
            r3 = 512(0x200, float:7.175E-43)
            goto L3b
        L3a:
            r3 = r6
        L3b:
            if (r0 == 0) goto L47
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L47
            r2 = r3 | 4
            r3 = r2 | 8192(0x2000, float:1.14794E-41)
        L47:
            int r2 = ~r1
            r2 = r2 & r3
            r7 = 1
            if (r2 == 0) goto L71
            if (r11 == 0) goto L71
            boolean r11 = r8.P
            if (r11 == 0) goto L71
            boolean r11 = androidx.appcompat.app.h.f1592m0
            if (r11 != 0) goto L5a
            boolean r11 = r8.Q
            if (r11 == 0) goto L71
        L5a:
            java.lang.Object r11 = r8.f1603j
            boolean r2 = r11 instanceof android.app.Activity
            if (r2 == 0) goto L71
            android.app.Activity r11 = (android.app.Activity) r11
            boolean r11 = r11.isChild()
            if (r11 != 0) goto L71
            java.lang.Object r11 = r8.f1603j
            android.app.Activity r11 = (android.app.Activity) r11
            androidx.core.app.b.r(r11)
            r11 = r7
            goto L72
        L71:
            r11 = r6
        L72:
            if (r11 != 0) goto L7f
            if (r3 == 0) goto L7f
            r11 = r3 & r1
            if (r11 != r3) goto L7b
            r6 = r7
        L7b:
            r8.d1(r4, r0, r6, r5)
            goto L80
        L7f:
            r7 = r11
        L80:
            if (r7 == 0) goto L9c
            java.lang.Object r11 = r8.f1603j
            boolean r1 = r11 instanceof androidx.appcompat.app.d
            if (r1 == 0) goto L9c
            r1 = r3 & 512(0x200, float:7.175E-43)
            if (r1 == 0) goto L91
            androidx.appcompat.app.d r11 = (androidx.appcompat.app.d) r11
            r11.onNightModeChanged(r9)
        L91:
            r9 = r3 & 4
            if (r9 == 0) goto L9c
            java.lang.Object r9 = r8.f1603j
            androidx.appcompat.app.d r9 = (androidx.appcompat.app.d) r9
            r9.onLocalesChanged(r10)
        L9c:
            if (r7 == 0) goto Lb1
            if (r0 == 0) goto Lb1
            android.content.Context r9 = r8.f1604k
            android.content.res.Resources r9 = r9.getResources()
            android.content.res.Configuration r9 = r9.getConfiguration()
            androidx.core.os.h r9 = r8.s0(r9)
            r8.T0(r9)
        Lb1:
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.app.h.b1(int, androidx.core.os.h, boolean):boolean");
    }

    private Configuration d0(Context context, int i8, androidx.core.os.h hVar, Configuration configuration, boolean z7) {
        int i9 = i8 != 1 ? i8 != 2 ? z7 ? 0 : context.getApplicationContext().getResources().getConfiguration().uiMode & 48 : 32 : 16;
        Configuration configuration2 = new Configuration();
        configuration2.fontScale = 0.0f;
        if (configuration != null) {
            configuration2.setTo(configuration);
        }
        configuration2.uiMode = i9 | (configuration2.uiMode & (-49));
        if (hVar != null) {
            S0(configuration2, hVar);
        }
        return configuration2;
    }

    private void d1(int i8, androidx.core.os.h hVar, boolean z7, Configuration configuration) {
        Resources resources = this.f1604k.getResources();
        Configuration configuration2 = new Configuration(resources.getConfiguration());
        if (configuration != null) {
            configuration2.updateFrom(configuration);
        }
        configuration2.uiMode = i8 | (resources.getConfiguration().uiMode & (-49));
        if (hVar != null) {
            S0(configuration2, hVar);
        }
        resources.updateConfiguration(configuration2, null);
        int i9 = this.U;
        if (i9 != 0) {
            this.f1604k.setTheme(i9);
            this.f1604k.getTheme().applyStyle(this.U, true);
        }
        if (z7 && (this.f1603j instanceof Activity)) {
            a1(configuration2);
        }
    }

    private ViewGroup e0() {
        ViewGroup viewGroup;
        TypedArray obtainStyledAttributes = this.f1604k.obtainStyledAttributes(g.j.f7044y0);
        if (!obtainStyledAttributes.hasValue(g.j.D0)) {
            obtainStyledAttributes.recycle();
            throw new IllegalStateException("You need to use a Theme.AppCompat theme (or descendant) with this activity.");
        }
        if (obtainStyledAttributes.getBoolean(g.j.M0, false)) {
            H(1);
        } else if (obtainStyledAttributes.getBoolean(g.j.D0, false)) {
            H(108);
        }
        if (obtainStyledAttributes.getBoolean(g.j.E0, false)) {
            H(109);
        }
        if (obtainStyledAttributes.getBoolean(g.j.F0, false)) {
            H(10);
        }
        this.J = obtainStyledAttributes.getBoolean(g.j.f7049z0, false);
        obtainStyledAttributes.recycle();
        l0();
        this.f1605l.getDecorView();
        LayoutInflater from = LayoutInflater.from(this.f1604k);
        if (this.K) {
            viewGroup = (ViewGroup) from.inflate(this.I ? g.g.f6902o : g.g.f6901n, (ViewGroup) null);
        } else if (this.J) {
            viewGroup = (ViewGroup) from.inflate(g.g.f6893f, (ViewGroup) null);
            this.H = false;
            this.G = false;
        } else if (this.G) {
            TypedValue typedValue = new TypedValue();
            this.f1604k.getTheme().resolveAttribute(g.a.f6795d, typedValue, true);
            viewGroup = (ViewGroup) LayoutInflater.from(typedValue.resourceId != 0 ? new androidx.appcompat.view.d(this.f1604k, typedValue.resourceId) : this.f1604k).inflate(g.g.f6903p, (ViewGroup) null);
            i0 i0Var = (i0) viewGroup.findViewById(g.f.f6877p);
            this.f1611r = i0Var;
            i0Var.setWindowCallback(v0());
            if (this.H) {
                this.f1611r.i(109);
            }
            if (this.E) {
                this.f1611r.i(2);
            }
            if (this.F) {
                this.f1611r.i(5);
            }
        } else {
            viewGroup = null;
        }
        if (viewGroup == null) {
            throw new IllegalArgumentException("AppCompat does not support the current theme features: { windowActionBar: " + this.G + ", windowActionBarOverlay: " + this.H + ", android:windowIsFloating: " + this.J + ", windowActionModeOverlay: " + this.I + ", windowNoTitle: " + this.K + " }");
        }
        p0.V(viewGroup, new b());
        if (this.f1611r == null) {
            this.C = (TextView) viewGroup.findViewById(g.f.L);
        }
        i1.c(viewGroup);
        ContentFrameLayout contentFrameLayout = (ContentFrameLayout) viewGroup.findViewById(g.f.f6863b);
        ViewGroup viewGroup2 = (ViewGroup) this.f1605l.findViewById(16908290);
        if (viewGroup2 != null) {
            while (viewGroup2.getChildCount() > 0) {
                View childAt = viewGroup2.getChildAt(0);
                viewGroup2.removeViewAt(0);
                contentFrameLayout.addView(childAt);
            }
            viewGroup2.setId(-1);
            contentFrameLayout.setId(16908290);
            if (viewGroup2 instanceof FrameLayout) {
                ((FrameLayout) viewGroup2).setForeground(null);
            }
        }
        this.f1605l.setContentView(viewGroup);
        contentFrameLayout.setAttachListener(new c());
        return viewGroup;
    }

    private void f1(View view) {
        Context context;
        int i8;
        if ((p0.v(view) & 8192) != 0) {
            context = this.f1604k;
            i8 = g.c.f6820b;
        } else {
            context = this.f1604k;
            i8 = g.c.f6819a;
        }
        view.setBackgroundColor(androidx.core.content.a.b(context, i8));
    }

    private void k0() {
        if (this.A) {
            return;
        }
        this.B = e0();
        CharSequence u02 = u0();
        if (!TextUtils.isEmpty(u02)) {
            i0 i0Var = this.f1611r;
            if (i0Var != null) {
                i0Var.setWindowTitle(u02);
            } else if (N0() != null) {
                N0().y(u02);
            } else {
                TextView textView = this.C;
                if (textView != null) {
                    textView.setText(u02);
                }
            }
        }
        U();
        L0(this.B);
        this.A = true;
        u t02 = t0(0, false);
        if (this.R) {
            return;
        }
        if (t02 == null || t02.f1653j == null) {
            A0(108);
        }
    }

    private void l0() {
        if (this.f1605l == null) {
            Object obj = this.f1603j;
            if (obj instanceof Activity) {
                V(((Activity) obj).getWindow());
            }
        }
        if (this.f1605l == null) {
            throw new IllegalStateException("We have not been given a Window");
        }
    }

    private static Configuration n0(Configuration configuration, Configuration configuration2) {
        Configuration configuration3 = new Configuration();
        configuration3.fontScale = 0.0f;
        if (configuration2 != null && configuration.diff(configuration2) != 0) {
            float f8 = configuration.fontScale;
            float f9 = configuration2.fontScale;
            if (f8 != f9) {
                configuration3.fontScale = f9;
            }
            int i8 = configuration.mcc;
            int i9 = configuration2.mcc;
            if (i8 != i9) {
                configuration3.mcc = i9;
            }
            int i10 = configuration.mnc;
            int i11 = configuration2.mnc;
            if (i10 != i11) {
                configuration3.mnc = i11;
            }
            l.a(configuration, configuration2, configuration3);
            int i12 = configuration.touchscreen;
            int i13 = configuration2.touchscreen;
            if (i12 != i13) {
                configuration3.touchscreen = i13;
            }
            int i14 = configuration.keyboard;
            int i15 = configuration2.keyboard;
            if (i14 != i15) {
                configuration3.keyboard = i15;
            }
            int i16 = configuration.keyboardHidden;
            int i17 = configuration2.keyboardHidden;
            if (i16 != i17) {
                configuration3.keyboardHidden = i17;
            }
            int i18 = configuration.navigation;
            int i19 = configuration2.navigation;
            if (i18 != i19) {
                configuration3.navigation = i19;
            }
            int i20 = configuration.navigationHidden;
            int i21 = configuration2.navigationHidden;
            if (i20 != i21) {
                configuration3.navigationHidden = i21;
            }
            int i22 = configuration.orientation;
            int i23 = configuration2.orientation;
            if (i22 != i23) {
                configuration3.orientation = i23;
            }
            int i24 = configuration.screenLayout & 15;
            int i25 = configuration2.screenLayout;
            if (i24 != (i25 & 15)) {
                configuration3.screenLayout |= i25 & 15;
            }
            int i26 = configuration.screenLayout & 192;
            int i27 = configuration2.screenLayout;
            if (i26 != (i27 & 192)) {
                configuration3.screenLayout |= i27 & 192;
            }
            int i28 = configuration.screenLayout & 48;
            int i29 = configuration2.screenLayout;
            if (i28 != (i29 & 48)) {
                configuration3.screenLayout |= i29 & 48;
            }
            int i30 = configuration.screenLayout & 768;
            int i31 = configuration2.screenLayout;
            if (i30 != (i31 & 768)) {
                configuration3.screenLayout |= i31 & 768;
            }
            m.a(configuration, configuration2, configuration3);
            int i32 = configuration.uiMode & 15;
            int i33 = configuration2.uiMode;
            if (i32 != (i33 & 15)) {
                configuration3.uiMode |= i33 & 15;
            }
            int i34 = configuration.uiMode & 48;
            int i35 = configuration2.uiMode;
            if (i34 != (i35 & 48)) {
                configuration3.uiMode |= i35 & 48;
            }
            int i36 = configuration.screenWidthDp;
            int i37 = configuration2.screenWidthDp;
            if (i36 != i37) {
                configuration3.screenWidthDp = i37;
            }
            int i38 = configuration.screenHeightDp;
            int i39 = configuration2.screenHeightDp;
            if (i38 != i39) {
                configuration3.screenHeightDp = i39;
            }
            int i40 = configuration.smallestScreenWidthDp;
            int i41 = configuration2.smallestScreenWidthDp;
            if (i40 != i41) {
                configuration3.smallestScreenWidthDp = i41;
            }
            j.b(configuration, configuration2, configuration3);
        }
        return configuration3;
    }

    private int p0(Context context) {
        if (!this.W && (this.f1603j instanceof Activity)) {
            PackageManager packageManager = context.getPackageManager();
            if (packageManager == null) {
                return 0;
            }
            try {
                ActivityInfo activityInfo = packageManager.getActivityInfo(new ComponentName(context, this.f1603j.getClass()), Build.VERSION.SDK_INT >= 29 ? 269221888 : 786432);
                if (activityInfo != null) {
                    this.V = activityInfo.configChanges;
                }
            } catch (PackageManager.NameNotFoundException e8) {
                Log.d("AppCompatDelegate", "Exception while getting ActivityInfo", e8);
                this.V = 0;
            }
        }
        this.W = true;
        return this.V;
    }

    private q q0(Context context) {
        if (this.Y == null) {
            this.Y = new p(context);
        }
        return this.Y;
    }

    private q r0(Context context) {
        if (this.X == null) {
            this.X = new r(z.a(context));
        }
        return this.X;
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x0032  */
    /* JADX WARN: Removed duplicated region for block: B:19:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void w0() {
        /*
            r3 = this;
            r3.k0()
            boolean r0 = r3.G
            if (r0 == 0) goto L37
            androidx.appcompat.app.a r0 = r3.f1608o
            if (r0 == 0) goto Lc
            goto L37
        Lc:
            java.lang.Object r0 = r3.f1603j
            boolean r1 = r0 instanceof android.app.Activity
            if (r1 == 0) goto L20
            androidx.appcompat.app.a0 r0 = new androidx.appcompat.app.a0
            java.lang.Object r1 = r3.f1603j
            android.app.Activity r1 = (android.app.Activity) r1
            boolean r2 = r3.H
            r0.<init>(r1, r2)
        L1d:
            r3.f1608o = r0
            goto L2e
        L20:
            boolean r0 = r0 instanceof android.app.Dialog
            if (r0 == 0) goto L2e
            androidx.appcompat.app.a0 r0 = new androidx.appcompat.app.a0
            java.lang.Object r1 = r3.f1603j
            android.app.Dialog r1 = (android.app.Dialog) r1
            r0.<init>(r1)
            goto L1d
        L2e:
            androidx.appcompat.app.a r0 = r3.f1608o
            if (r0 == 0) goto L37
            boolean r1 = r3.f1596c0
            r0.u(r1)
        L37:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.app.h.w0():void");
    }

    private boolean x0(u uVar) {
        View view = uVar.f1652i;
        if (view != null) {
            uVar.f1651h = view;
            return true;
        } else if (uVar.f1653j == null) {
            return false;
        } else {
            if (this.f1613t == null) {
                this.f1613t = new v();
            }
            View view2 = (View) uVar.a(this.f1613t);
            uVar.f1651h = view2;
            return view2 != null;
        }
    }

    private boolean y0(u uVar) {
        uVar.d(o0());
        uVar.f1650g = new t(uVar.f1655l);
        uVar.f1646c = 81;
        return true;
    }

    private boolean z0(u uVar) {
        Resources.Theme theme;
        Context context = this.f1604k;
        int i8 = uVar.f1644a;
        if ((i8 == 0 || i8 == 108) && this.f1611r != null) {
            TypedValue typedValue = new TypedValue();
            Resources.Theme theme2 = context.getTheme();
            theme2.resolveAttribute(g.a.f6795d, typedValue, true);
            if (typedValue.resourceId != 0) {
                theme = context.getResources().newTheme();
                theme.setTo(theme2);
                theme.applyStyle(typedValue.resourceId, true);
                theme.resolveAttribute(g.a.f6796e, typedValue, true);
            } else {
                theme2.resolveAttribute(g.a.f6796e, typedValue, true);
                theme = null;
            }
            if (typedValue.resourceId != 0) {
                if (theme == null) {
                    theme = context.getResources().newTheme();
                    theme.setTo(theme2);
                }
                theme.applyStyle(typedValue.resourceId, true);
            }
            if (theme != null) {
                androidx.appcompat.view.d dVar = new androidx.appcompat.view.d(context, 0);
                dVar.getTheme().setTo(theme);
                context = dVar;
            }
        }
        androidx.appcompat.view.menu.g gVar = new androidx.appcompat.view.menu.g(context);
        gVar.R(this);
        uVar.c(gVar);
        return true;
    }

    @Override // androidx.appcompat.app.g
    public void A(Bundle bundle) {
        k0();
    }

    @Override // androidx.appcompat.app.g
    public void B() {
        androidx.appcompat.app.a s8 = s();
        if (s8 != null) {
            s8.w(true);
        }
    }

    public boolean B0() {
        return this.f1619z;
    }

    @Override // androidx.appcompat.app.g
    public void C(Bundle bundle) {
    }

    int C0(Context context, int i8) {
        q r02;
        if (i8 != -100) {
            if (i8 != -1) {
                if (i8 != 0) {
                    if (i8 != 1 && i8 != 2) {
                        if (i8 != 3) {
                            throw new IllegalStateException("Unknown value set for night mode. Please use one of the MODE_NIGHT values from AppCompatDelegate.");
                        }
                        r02 = q0(context);
                    }
                } else if (((UiModeManager) context.getApplicationContext().getSystemService("uimode")).getNightMode() == 0) {
                    return -1;
                } else {
                    r02 = r0(context);
                }
                return r02.c();
            }
            return i8;
        }
        return -1;
    }

    @Override // androidx.appcompat.app.g
    public void D() {
        S(true, false);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean D0() {
        boolean z7 = this.O;
        this.O = false;
        u t02 = t0(0, false);
        if (t02 != null && t02.f1658o) {
            if (!z7) {
                c0(t02, true);
            }
            return true;
        }
        androidx.appcompat.view.b bVar = this.f1614u;
        if (bVar != null) {
            bVar.c();
            return true;
        }
        androidx.appcompat.app.a s8 = s();
        return s8 != null && s8.h();
    }

    @Override // androidx.appcompat.app.g
    public void E() {
        androidx.appcompat.app.a s8 = s();
        if (s8 != null) {
            s8.w(false);
        }
    }

    boolean E0(int i8, KeyEvent keyEvent) {
        if (i8 == 4) {
            this.O = (keyEvent.getFlags() & 128) != 0;
        } else if (i8 == 82) {
            F0(0, keyEvent);
            return true;
        }
        return false;
    }

    boolean G0(int i8, KeyEvent keyEvent) {
        androidx.appcompat.app.a s8 = s();
        if (s8 == null || !s8.q(i8, keyEvent)) {
            u uVar = this.N;
            if (uVar != null && O0(uVar, keyEvent.getKeyCode(), keyEvent, 1)) {
                u uVar2 = this.N;
                if (uVar2 != null) {
                    uVar2.f1657n = true;
                }
                return true;
            }
            if (this.N == null) {
                u t02 = t0(0, true);
                P0(t02, keyEvent);
                boolean O0 = O0(t02, keyEvent.getKeyCode(), keyEvent, 1);
                t02.f1656m = false;
                if (O0) {
                    return true;
                }
            }
            return false;
        }
        return true;
    }

    @Override // androidx.appcompat.app.g
    public boolean H(int i8) {
        int R0 = R0(i8);
        if (this.K && R0 == 108) {
            return false;
        }
        if (this.G && R0 == 1) {
            this.G = false;
        }
        if (R0 == 1) {
            Y0();
            this.K = true;
            return true;
        } else if (R0 == 2) {
            Y0();
            this.E = true;
            return true;
        } else if (R0 == 5) {
            Y0();
            this.F = true;
            return true;
        } else if (R0 == 10) {
            Y0();
            this.I = true;
            return true;
        } else if (R0 == 108) {
            Y0();
            this.G = true;
            return true;
        } else if (R0 != 109) {
            return this.f1605l.requestFeature(R0);
        } else {
            Y0();
            this.H = true;
            return true;
        }
    }

    boolean H0(int i8, KeyEvent keyEvent) {
        if (i8 != 4) {
            if (i8 == 82) {
                I0(0, keyEvent);
                return true;
            }
        } else if (D0()) {
            return true;
        }
        return false;
    }

    @Override // androidx.appcompat.app.g
    public void I(int i8) {
        k0();
        ViewGroup viewGroup = (ViewGroup) this.B.findViewById(16908290);
        viewGroup.removeAllViews();
        LayoutInflater.from(this.f1604k).inflate(i8, viewGroup);
        this.f1606m.c(this.f1605l.getCallback());
    }

    @Override // androidx.appcompat.app.g
    public void J(View view) {
        k0();
        ViewGroup viewGroup = (ViewGroup) this.B.findViewById(16908290);
        viewGroup.removeAllViews();
        viewGroup.addView(view);
        this.f1606m.c(this.f1605l.getCallback());
    }

    void J0(int i8) {
        androidx.appcompat.app.a s8;
        if (i8 != 108 || (s8 = s()) == null) {
            return;
        }
        s8.i(true);
    }

    @Override // androidx.appcompat.app.g
    public void K(View view, ViewGroup.LayoutParams layoutParams) {
        k0();
        ViewGroup viewGroup = (ViewGroup) this.B.findViewById(16908290);
        viewGroup.removeAllViews();
        viewGroup.addView(view, layoutParams);
        this.f1606m.c(this.f1605l.getCallback());
    }

    void K0(int i8) {
        if (i8 == 108) {
            androidx.appcompat.app.a s8 = s();
            if (s8 != null) {
                s8.i(false);
            }
        } else if (i8 == 0) {
            u t02 = t0(i8, true);
            if (t02.f1658o) {
                c0(t02, false);
            }
        }
    }

    @Override // androidx.appcompat.app.g
    public void L(OnBackInvokedDispatcher onBackInvokedDispatcher) {
        OnBackInvokedCallback onBackInvokedCallback;
        super.L(onBackInvokedDispatcher);
        OnBackInvokedDispatcher onBackInvokedDispatcher2 = this.f1601h0;
        if (onBackInvokedDispatcher2 != null && (onBackInvokedCallback = this.f1602i0) != null) {
            n.c(onBackInvokedDispatcher2, onBackInvokedCallback);
            this.f1602i0 = null;
        }
        if (onBackInvokedDispatcher == null) {
            Object obj = this.f1603j;
            if ((obj instanceof Activity) && ((Activity) obj).getWindow() != null) {
                onBackInvokedDispatcher = n.a((Activity) this.f1603j);
            }
        }
        this.f1601h0 = onBackInvokedDispatcher;
        c1();
    }

    void L0(ViewGroup viewGroup) {
    }

    @Override // androidx.appcompat.app.g
    public void M(Toolbar toolbar) {
        if (this.f1603j instanceof Activity) {
            androidx.appcompat.app.a s8 = s();
            if (s8 instanceof a0) {
                throw new IllegalStateException("This Activity already has an action bar supplied by the window decor. Do not request Window.FEATURE_SUPPORT_ACTION_BAR and set windowActionBar to false in your theme to use a Toolbar instead.");
            }
            this.f1609p = null;
            if (s8 != null) {
                s8.p();
            }
            this.f1608o = null;
            if (toolbar != null) {
                x xVar = new x(toolbar, u0(), this.f1606m);
                this.f1608o = xVar;
                this.f1606m.e(xVar.f1689c);
                toolbar.setBackInvokedCallbackEnabled(true);
            } else {
                this.f1606m.e(null);
            }
            u();
        }
    }

    @Override // androidx.appcompat.app.g
    public void N(int i8) {
        this.U = i8;
    }

    final androidx.appcompat.app.a N0() {
        return this.f1608o;
    }

    @Override // androidx.appcompat.app.g
    public final void O(CharSequence charSequence) {
        this.f1610q = charSequence;
        i0 i0Var = this.f1611r;
        if (i0Var != null) {
            i0Var.setWindowTitle(charSequence);
        } else if (N0() != null) {
            N0().y(charSequence);
        } else {
            TextView textView = this.C;
            if (textView != null) {
                textView.setText(charSequence);
            }
        }
    }

    @Override // androidx.appcompat.app.g
    public androidx.appcompat.view.b P(b.a aVar) {
        androidx.appcompat.app.e eVar;
        if (aVar != null) {
            androidx.appcompat.view.b bVar = this.f1614u;
            if (bVar != null) {
                bVar.c();
            }
            i iVar = new i(aVar);
            androidx.appcompat.app.a s8 = s();
            if (s8 != null) {
                androidx.appcompat.view.b A = s8.A(iVar);
                this.f1614u = A;
                if (A != null && (eVar = this.f1607n) != null) {
                    eVar.onSupportActionModeStarted(A);
                }
            }
            if (this.f1614u == null) {
                this.f1614u = X0(iVar);
            }
            c1();
            return this.f1614u;
        }
        throw new IllegalArgumentException("ActionMode callback can not be null.");
    }

    void S0(Configuration configuration, androidx.core.os.h hVar) {
        l.d(configuration, hVar);
    }

    public boolean T() {
        return R(true);
    }

    void T0(androidx.core.os.h hVar) {
        l.c(hVar);
    }

    final boolean U0() {
        ViewGroup viewGroup;
        return this.A && (viewGroup = this.B) != null && p0.z(viewGroup);
    }

    androidx.core.os.h W(Context context) {
        androidx.core.os.h r8;
        if (Build.VERSION.SDK_INT < 33 && (r8 = androidx.appcompat.app.g.r()) != null) {
            androidx.core.os.h s02 = s0(context.getApplicationContext().getResources().getConfiguration());
            androidx.core.os.h b8 = androidx.appcompat.app.v.b(r8, s02);
            return b8.e() ? s02 : b8;
        }
        return null;
    }

    boolean W0() {
        if (this.f1601h0 == null) {
            return false;
        }
        u t02 = t0(0, false);
        return (t02 != null && t02.f1658o) || this.f1614u != null;
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x0025  */
    /* JADX WARN: Removed duplicated region for block: B:17:0x0029  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    androidx.appcompat.view.b X0(androidx.appcompat.view.b.a r8) {
        /*
            Method dump skipped, instructions count: 364
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.app.h.X0(androidx.appcompat.view.b$a):androidx.appcompat.view.b");
    }

    void Y(int i8, u uVar, Menu menu) {
        if (menu == null) {
            if (uVar == null && i8 >= 0) {
                u[] uVarArr = this.M;
                if (i8 < uVarArr.length) {
                    uVar = uVarArr[i8];
                }
            }
            if (uVar != null) {
                menu = uVar.f1653j;
            }
        }
        if ((uVar == null || uVar.f1658o) && !this.R) {
            this.f1606m.d(this.f1605l.getCallback(), i8, menu);
        }
    }

    void Z(androidx.appcompat.view.menu.g gVar) {
        if (this.L) {
            return;
        }
        this.L = true;
        this.f1611r.j();
        Window.Callback v02 = v0();
        if (v02 != null && !this.R) {
            v02.onPanelClosed(108, gVar);
        }
        this.L = false;
    }

    @Override // androidx.appcompat.view.menu.g.a
    public boolean a(androidx.appcompat.view.menu.g gVar, MenuItem menuItem) {
        u m02;
        Window.Callback v02 = v0();
        if (v02 == null || this.R || (m02 = m0(gVar.D())) == null) {
            return false;
        }
        return v02.onMenuItemSelected(m02.f1644a, menuItem);
    }

    @Override // androidx.appcompat.view.menu.g.a
    public void b(androidx.appcompat.view.menu.g gVar) {
        Q0(true);
    }

    void b0(int i8) {
        c0(t0(i8, true), true);
    }

    void c0(u uVar, boolean z7) {
        ViewGroup viewGroup;
        i0 i0Var;
        if (z7 && uVar.f1644a == 0 && (i0Var = this.f1611r) != null && i0Var.b()) {
            Z(uVar.f1653j);
            return;
        }
        WindowManager windowManager = (WindowManager) this.f1604k.getSystemService("window");
        if (windowManager != null && uVar.f1658o && (viewGroup = uVar.f1650g) != null) {
            windowManager.removeView(viewGroup);
            if (z7) {
                Y(uVar.f1644a, uVar, null);
            }
        }
        uVar.f1656m = false;
        uVar.f1657n = false;
        uVar.f1658o = false;
        uVar.f1651h = null;
        uVar.f1660q = true;
        if (this.N == uVar) {
            this.N = null;
        }
        if (uVar.f1644a == 0) {
            c1();
        }
    }

    void c1() {
        OnBackInvokedCallback onBackInvokedCallback;
        if (Build.VERSION.SDK_INT >= 33) {
            boolean W0 = W0();
            if (W0 && this.f1602i0 == null) {
                this.f1602i0 = n.b(this.f1601h0, this);
            } else if (W0 || (onBackInvokedCallback = this.f1602i0) == null) {
            } else {
                n.c(this.f1601h0, onBackInvokedCallback);
            }
        }
    }

    @Override // androidx.appcompat.app.g
    public void e(View view, ViewGroup.LayoutParams layoutParams) {
        k0();
        ((ViewGroup) this.B.findViewById(16908290)).addView(view, layoutParams);
        this.f1606m.c(this.f1605l.getCallback());
    }

    final int e1(b1 b1Var, Rect rect) {
        boolean z7;
        boolean z8;
        int k8 = b1Var != null ? b1Var.k() : rect != null ? rect.top : 0;
        ActionBarContextView actionBarContextView = this.f1615v;
        if (actionBarContextView == null || !(actionBarContextView.getLayoutParams() instanceof ViewGroup.MarginLayoutParams)) {
            z7 = false;
        } else {
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) this.f1615v.getLayoutParams();
            if (this.f1615v.isShown()) {
                if (this.f1597d0 == null) {
                    this.f1597d0 = new Rect();
                    this.f1598e0 = new Rect();
                }
                Rect rect2 = this.f1597d0;
                Rect rect3 = this.f1598e0;
                if (b1Var == null) {
                    rect2.set(rect);
                } else {
                    rect2.set(b1Var.i(), b1Var.k(), b1Var.j(), b1Var.h());
                }
                i1.a(this.B, rect2, rect3);
                int i8 = rect2.top;
                int i9 = rect2.left;
                int i10 = rect2.right;
                b1 s8 = p0.s(this.B);
                int i11 = s8 == null ? 0 : s8.i();
                int j8 = s8 == null ? 0 : s8.j();
                if (marginLayoutParams.topMargin == i8 && marginLayoutParams.leftMargin == i9 && marginLayoutParams.rightMargin == i10) {
                    z8 = false;
                } else {
                    marginLayoutParams.topMargin = i8;
                    marginLayoutParams.leftMargin = i9;
                    marginLayoutParams.rightMargin = i10;
                    z8 = true;
                }
                if (i8 <= 0 || this.D != null) {
                    View view = this.D;
                    if (view != null) {
                        ViewGroup.MarginLayoutParams marginLayoutParams2 = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
                        int i12 = marginLayoutParams2.height;
                        int i13 = marginLayoutParams.topMargin;
                        if (i12 != i13 || marginLayoutParams2.leftMargin != i11 || marginLayoutParams2.rightMargin != j8) {
                            marginLayoutParams2.height = i13;
                            marginLayoutParams2.leftMargin = i11;
                            marginLayoutParams2.rightMargin = j8;
                            this.D.setLayoutParams(marginLayoutParams2);
                        }
                    }
                } else {
                    View view2 = new View(this.f1604k);
                    this.D = view2;
                    view2.setVisibility(8);
                    FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, marginLayoutParams.topMargin, 51);
                    layoutParams.leftMargin = i11;
                    layoutParams.rightMargin = j8;
                    this.B.addView(this.D, -1, layoutParams);
                }
                View view3 = this.D;
                r5 = view3 != null;
                if (r5 && view3.getVisibility() != 0) {
                    f1(this.D);
                }
                if (!this.I && r5) {
                    k8 = 0;
                }
                z7 = r5;
                r5 = z8;
            } else if (marginLayoutParams.topMargin != 0) {
                marginLayoutParams.topMargin = 0;
                z7 = false;
            } else {
                z7 = false;
                r5 = false;
            }
            if (r5) {
                this.f1615v.setLayoutParams(marginLayoutParams);
            }
        }
        View view4 = this.D;
        if (view4 != null) {
            view4.setVisibility(z7 ? 0 : 8);
        }
        return k8;
    }

    public View f0(View view, String str, Context context, AttributeSet attributeSet) {
        boolean z7;
        androidx.appcompat.app.q qVar;
        boolean z8 = false;
        if (this.f1599f0 == null) {
            String string = this.f1604k.obtainStyledAttributes(g.j.f7044y0).getString(g.j.C0);
            if (string == null) {
                qVar = new androidx.appcompat.app.q();
            } else {
                try {
                    this.f1599f0 = (androidx.appcompat.app.q) this.f1604k.getClassLoader().loadClass(string).getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
                } catch (Throwable th) {
                    Log.i("AppCompatDelegate", "Failed to instantiate custom view inflater " + string + ". Falling back to default.", th);
                    qVar = new androidx.appcompat.app.q();
                }
            }
            this.f1599f0 = qVar;
        }
        boolean z9 = f1590k0;
        if (z9) {
            if (this.f1600g0 == null) {
                this.f1600g0 = new androidx.appcompat.app.u();
            }
            if (this.f1600g0.a(attributeSet)) {
                z7 = true;
                return this.f1599f0.r(view, str, context, attributeSet, z7, z9, true, h1.c());
            } else if (!(attributeSet instanceof XmlPullParser)) {
                z8 = V0((ViewParent) view);
            } else if (((XmlPullParser) attributeSet).getDepth() > 1) {
                z8 = true;
            }
        }
        z7 = z8;
        return this.f1599f0.r(view, str, context, attributeSet, z7, z9, true, h1.c());
    }

    @Override // androidx.appcompat.app.g
    public Context g(Context context) {
        this.P = true;
        int C0 = C0(context, X());
        if (androidx.appcompat.app.g.v(context)) {
            androidx.appcompat.app.g.Q(context);
        }
        androidx.core.os.h W = W(context);
        if (f1593n0 && (context instanceof ContextThemeWrapper)) {
            try {
                s.a((ContextThemeWrapper) context, d0(context, C0, W, null, false));
                return context;
            } catch (IllegalStateException unused) {
            }
        }
        if (context instanceof androidx.appcompat.view.d) {
            try {
                ((androidx.appcompat.view.d) context).a(d0(context, C0, W, null, false));
                return context;
            } catch (IllegalStateException unused2) {
            }
        }
        if (f1592m0) {
            Configuration configuration = new Configuration();
            configuration.uiMode = -1;
            configuration.fontScale = 0.0f;
            Configuration configuration2 = j.a(context, configuration).getResources().getConfiguration();
            Configuration configuration3 = context.getResources().getConfiguration();
            configuration2.uiMode = configuration3.uiMode;
            Configuration d02 = d0(context, C0, W, !configuration2.equals(configuration3) ? n0(configuration2, configuration3) : null, true);
            androidx.appcompat.view.d dVar = new androidx.appcompat.view.d(context, g.i.f6921c);
            dVar.a(d02);
            boolean z7 = false;
            try {
                z7 = context.getTheme() != null;
            } catch (NullPointerException unused3) {
            }
            if (z7) {
                f.C0024f.a(dVar.getTheme());
            }
            return super.g(dVar);
        }
        return super.g(context);
    }

    void g0() {
        androidx.appcompat.view.menu.g gVar;
        i0 i0Var = this.f1611r;
        if (i0Var != null) {
            i0Var.j();
        }
        if (this.f1616w != null) {
            this.f1605l.getDecorView().removeCallbacks(this.f1617x);
            if (this.f1616w.isShowing()) {
                try {
                    this.f1616w.dismiss();
                } catch (IllegalArgumentException unused) {
                }
            }
            this.f1616w = null;
        }
        j0();
        u t02 = t0(0, false);
        if (t02 == null || (gVar = t02.f1653j) == null) {
            return;
        }
        gVar.close();
    }

    boolean h0(KeyEvent keyEvent) {
        View decorView;
        Object obj = this.f1603j;
        if (((obj instanceof t.a) || (obj instanceof androidx.appcompat.app.p)) && (decorView = this.f1605l.getDecorView()) != null && androidx.core.view.t.d(decorView, keyEvent)) {
            return true;
        }
        if (keyEvent.getKeyCode() == 82 && this.f1606m.b(this.f1605l.getCallback(), keyEvent)) {
            return true;
        }
        int keyCode = keyEvent.getKeyCode();
        return keyEvent.getAction() == 0 ? E0(keyCode, keyEvent) : H0(keyCode, keyEvent);
    }

    void i0(int i8) {
        u t02;
        u t03 = t0(i8, true);
        if (t03.f1653j != null) {
            Bundle bundle = new Bundle();
            t03.f1653j.Q(bundle);
            if (bundle.size() > 0) {
                t03.f1662s = bundle;
            }
            t03.f1653j.e0();
            t03.f1653j.clear();
        }
        t03.f1661r = true;
        t03.f1660q = true;
        if ((i8 != 108 && i8 != 0) || this.f1611r == null || (t02 = t0(0, false)) == null) {
            return;
        }
        t02.f1656m = false;
        P0(t02, null);
    }

    @Override // androidx.appcompat.app.g
    public View j(int i8) {
        k0();
        return this.f1605l.findViewById(i8);
    }

    void j0() {
        x0 x0Var = this.f1618y;
        if (x0Var != null) {
            x0Var.c();
        }
    }

    @Override // androidx.appcompat.app.g
    public Context l() {
        return this.f1604k;
    }

    u m0(Menu menu) {
        u[] uVarArr = this.M;
        int length = uVarArr != null ? uVarArr.length : 0;
        for (int i8 = 0; i8 < length; i8++) {
            u uVar = uVarArr[i8];
            if (uVar != null && uVar.f1653j == menu) {
                return uVar;
            }
        }
        return null;
    }

    @Override // androidx.appcompat.app.g
    public final androidx.appcompat.app.b n() {
        return new f();
    }

    @Override // androidx.appcompat.app.g
    public int o() {
        return this.T;
    }

    final Context o0() {
        androidx.appcompat.app.a s8 = s();
        Context k8 = s8 != null ? s8.k() : null;
        return k8 == null ? this.f1604k : k8;
    }

    @Override // android.view.LayoutInflater.Factory2
    public final View onCreateView(View view, String str, Context context, AttributeSet attributeSet) {
        return f0(view, str, context, attributeSet);
    }

    @Override // androidx.appcompat.app.g
    public MenuInflater q() {
        if (this.f1609p == null) {
            w0();
            androidx.appcompat.app.a aVar = this.f1608o;
            this.f1609p = new androidx.appcompat.view.g(aVar != null ? aVar.k() : this.f1604k);
        }
        return this.f1609p;
    }

    @Override // androidx.appcompat.app.g
    public androidx.appcompat.app.a s() {
        w0();
        return this.f1608o;
    }

    androidx.core.os.h s0(Configuration configuration) {
        return l.b(configuration);
    }

    @Override // androidx.appcompat.app.g
    public void t() {
        LayoutInflater from = LayoutInflater.from(this.f1604k);
        if (from.getFactory() == null) {
            androidx.core.view.u.a(from, this);
        } else if (from.getFactory2() instanceof h) {
        } else {
            Log.i("AppCompatDelegate", "The Activity's LayoutInflater already has a Factory installed so we can not install AppCompat's");
        }
    }

    protected u t0(int i8, boolean z7) {
        u[] uVarArr = this.M;
        if (uVarArr == null || uVarArr.length <= i8) {
            u[] uVarArr2 = new u[i8 + 1];
            if (uVarArr != null) {
                System.arraycopy(uVarArr, 0, uVarArr2, 0, uVarArr.length);
            }
            this.M = uVarArr2;
            uVarArr = uVarArr2;
        }
        u uVar = uVarArr[i8];
        if (uVar == null) {
            u uVar2 = new u(i8);
            uVarArr[i8] = uVar2;
            return uVar2;
        }
        return uVar;
    }

    @Override // androidx.appcompat.app.g
    public void u() {
        if (N0() == null || s().m()) {
            return;
        }
        A0(0);
    }

    final CharSequence u0() {
        Object obj = this.f1603j;
        return obj instanceof Activity ? ((Activity) obj).getTitle() : this.f1610q;
    }

    final Window.Callback v0() {
        return this.f1605l.getCallback();
    }

    @Override // androidx.appcompat.app.g
    public void x(Configuration configuration) {
        androidx.appcompat.app.a s8;
        if (this.G && this.A && (s8 = s()) != null) {
            s8.o(configuration);
        }
        androidx.appcompat.widget.k.b().g(this.f1604k);
        this.S = new Configuration(this.f1604k.getResources().getConfiguration());
        S(false, false);
    }

    @Override // androidx.appcompat.app.g
    public void y(Bundle bundle) {
        String str;
        this.P = true;
        R(false);
        l0();
        Object obj = this.f1603j;
        if (obj instanceof Activity) {
            try {
                str = androidx.core.app.h.c((Activity) obj);
            } catch (IllegalArgumentException unused) {
                str = null;
            }
            if (str != null) {
                androidx.appcompat.app.a N0 = N0();
                if (N0 == null) {
                    this.f1596c0 = true;
                } else {
                    N0.u(true);
                }
            }
            androidx.appcompat.app.g.d(this);
        }
        this.S = new Configuration(this.f1604k.getResources().getConfiguration());
        this.Q = true;
    }

    /* JADX WARN: Removed duplicated region for block: B:18:0x0058  */
    @Override // androidx.appcompat.app.g
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void z() {
        /*
            r3 = this;
            java.lang.Object r0 = r3.f1603j
            boolean r0 = r0 instanceof android.app.Activity
            if (r0 == 0) goto L9
            androidx.appcompat.app.g.F(r3)
        L9:
            boolean r0 = r3.Z
            if (r0 == 0) goto L18
            android.view.Window r0 = r3.f1605l
            android.view.View r0 = r0.getDecorView()
            java.lang.Runnable r1 = r3.f1595b0
            r0.removeCallbacks(r1)
        L18:
            r0 = 1
            r3.R = r0
            int r0 = r3.T
            r1 = -100
            if (r0 == r1) goto L45
            java.lang.Object r0 = r3.f1603j
            boolean r1 = r0 instanceof android.app.Activity
            if (r1 == 0) goto L45
            android.app.Activity r0 = (android.app.Activity) r0
            boolean r0 = r0.isChangingConfigurations()
            if (r0 == 0) goto L45
            b1.g r0 = androidx.appcompat.app.h.f1589j0
            java.lang.Object r1 = r3.f1603j
            java.lang.Class r1 = r1.getClass()
            java.lang.String r1 = r1.getName()
            int r2 = r3.T
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)
            r0.put(r1, r2)
            goto L54
        L45:
            b1.g r0 = androidx.appcompat.app.h.f1589j0
            java.lang.Object r1 = r3.f1603j
            java.lang.Class r1 = r1.getClass()
            java.lang.String r1 = r1.getName()
            r0.remove(r1)
        L54:
            androidx.appcompat.app.a r0 = r3.f1608o
            if (r0 == 0) goto L5b
            r0.p()
        L5b:
            r3.a0()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.app.h.z():void");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public h(Dialog dialog, androidx.appcompat.app.e eVar) {
        this(dialog.getContext(), dialog.getWindow(), eVar, dialog);
    }

    @Override // android.view.LayoutInflater.Factory
    public View onCreateView(String str, Context context, AttributeSet attributeSet) {
        return onCreateView(null, str, context, attributeSet);
    }

    private h(Context context, Window window, androidx.appcompat.app.e eVar, Object obj) {
        androidx.appcompat.app.d Z0;
        this.f1618y = null;
        this.f1619z = true;
        this.T = -100;
        this.f1595b0 = new a();
        this.f1604k = context;
        this.f1607n = eVar;
        this.f1603j = obj;
        if (this.T == -100 && (obj instanceof Dialog) && (Z0 = Z0()) != null) {
            this.T = Z0.getDelegate().o();
        }
        if (this.T == -100) {
            b1.g gVar = f1589j0;
            Integer num = (Integer) gVar.get(obj.getClass().getName());
            if (num != null) {
                this.T = num.intValue();
                gVar.remove(obj.getClass().getName());
            }
        }
        if (window != null) {
            V(window);
        }
        androidx.appcompat.widget.k.h();
    }
}
