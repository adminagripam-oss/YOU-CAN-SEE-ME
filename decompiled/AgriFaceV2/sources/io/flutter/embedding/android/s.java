package io.flutter.embedding.android;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.database.ContentObserver;
import android.graphics.Insets;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.provider.Settings;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.DisplayCutout;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.PointerIcon;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewStructure;
import android.view.WindowInsets;
import android.view.WindowManager;
import android.view.accessibility.AccessibilityManager;
import android.view.accessibility.AccessibilityNodeProvider;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.view.textservice.SpellCheckerInfo;
import android.view.textservice.TextServicesManager;
import android.widget.FrameLayout;
import io.flutter.embedding.android.a0;
import io.flutter.embedding.android.k;
import io.flutter.embedding.engine.renderer.FlutterRenderer;
import io.flutter.view.g;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import k6.a;
/* loaded from: classes.dex */
public class s extends FrameLayout implements a.c, a0.e {

    /* renamed from: a  reason: collision with root package name */
    private l f7644a;

    /* renamed from: b  reason: collision with root package name */
    private m f7645b;

    /* renamed from: c  reason: collision with root package name */
    private k f7646c;

    /* renamed from: d  reason: collision with root package name */
    io.flutter.embedding.engine.renderer.m f7647d;

    /* renamed from: e  reason: collision with root package name */
    private io.flutter.embedding.engine.renderer.m f7648e;

    /* renamed from: f  reason: collision with root package name */
    private final Set f7649f;

    /* renamed from: g  reason: collision with root package name */
    private boolean f7650g;

    /* renamed from: h  reason: collision with root package name */
    private io.flutter.embedding.engine.a f7651h;

    /* renamed from: i  reason: collision with root package name */
    private final Set f7652i;

    /* renamed from: j  reason: collision with root package name */
    private k6.a f7653j;

    /* renamed from: k  reason: collision with root package name */
    private io.flutter.plugin.editing.l f7654k;

    /* renamed from: l  reason: collision with root package name */
    private io.flutter.plugin.editing.j f7655l;

    /* renamed from: m  reason: collision with root package name */
    private j6.a f7656m;

    /* renamed from: n  reason: collision with root package name */
    private a0 f7657n;

    /* renamed from: o  reason: collision with root package name */
    private io.flutter.embedding.android.a f7658o;

    /* renamed from: p  reason: collision with root package name */
    private io.flutter.view.g f7659p;

    /* renamed from: q  reason: collision with root package name */
    private TextServicesManager f7660q;

    /* renamed from: r  reason: collision with root package name */
    private f0 f7661r;

    /* renamed from: s  reason: collision with root package name */
    private final FlutterRenderer.h f7662s;

    /* renamed from: t  reason: collision with root package name */
    private final g.k f7663t;

    /* renamed from: u  reason: collision with root package name */
    private final ContentObserver f7664u;

    /* renamed from: v  reason: collision with root package name */
    private final io.flutter.embedding.engine.renderer.l f7665v;

    /* renamed from: w  reason: collision with root package name */
    private final g1.a f7666w;

    /* loaded from: classes.dex */
    class a implements g.k {
        a() {
        }

        @Override // io.flutter.view.g.k
        public void a(boolean z7, boolean z8) {
            s.this.z(z7, z8);
        }
    }

    /* loaded from: classes.dex */
    class b extends ContentObserver {
        b(Handler handler) {
            super(handler);
        }

        @Override // android.database.ContentObserver
        public boolean deliverSelfNotifications() {
            return true;
        }

        @Override // android.database.ContentObserver
        public void onChange(boolean z7) {
            super.onChange(z7);
            if (s.this.f7651h == null) {
                return;
            }
            z5.b.f("FlutterView", "System settings changed. Sending user settings to Flutter.");
            s.this.B();
        }
    }

    /* loaded from: classes.dex */
    class c implements io.flutter.embedding.engine.renderer.l {
        c() {
        }

        @Override // io.flutter.embedding.engine.renderer.l
        public void b() {
            s.this.f7650g = false;
            for (io.flutter.embedding.engine.renderer.l lVar : s.this.f7649f) {
                lVar.b();
            }
        }

        @Override // io.flutter.embedding.engine.renderer.l
        public void d() {
            s.this.f7650g = true;
            for (io.flutter.embedding.engine.renderer.l lVar : s.this.f7649f) {
                lVar.d();
            }
        }
    }

    /* loaded from: classes.dex */
    class d implements g1.a {
        d() {
        }

        @Override // g1.a
        /* renamed from: a */
        public void accept(h2.j jVar) {
            s.this.setWindowInfoListenerDisplayFeatures(jVar);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class e implements io.flutter.embedding.engine.renderer.l {

        /* renamed from: a  reason: collision with root package name */
        final /* synthetic */ FlutterRenderer f7671a;

        /* renamed from: b  reason: collision with root package name */
        final /* synthetic */ Runnable f7672b;

        e(FlutterRenderer flutterRenderer, Runnable runnable) {
            this.f7671a = flutterRenderer;
            this.f7672b = runnable;
        }

        @Override // io.flutter.embedding.engine.renderer.l
        public void b() {
        }

        @Override // io.flutter.embedding.engine.renderer.l
        public void d() {
            this.f7671a.u(this);
            this.f7672b.run();
            s sVar = s.this;
            if ((sVar.f7647d instanceof k) || sVar.f7646c == null) {
                return;
            }
            s.this.f7646c.b();
            s.this.x();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public enum f {
        NONE,
        LEFT,
        RIGHT,
        BOTH
    }

    private s(Context context, AttributeSet attributeSet, l lVar) {
        super(context, attributeSet);
        this.f7649f = new HashSet();
        this.f7652i = new HashSet();
        this.f7662s = new FlutterRenderer.h();
        this.f7663t = new a();
        this.f7664u = new b(new Handler(Looper.getMainLooper()));
        this.f7665v = new c();
        this.f7666w = new d();
        this.f7644a = lVar;
        this.f7647d = lVar;
        u();
    }

    private void C() {
        if (!v()) {
            z5.b.g("FlutterView", "Tried to send viewport metrics from Android to Flutter but this FlutterView was not attached to a FlutterEngine.");
            return;
        }
        this.f7662s.f7826a = getResources().getDisplayMetrics().density;
        this.f7662s.f7841p = ViewConfiguration.get(getContext()).getScaledTouchSlop();
        this.f7651h.t().y(this.f7662s);
    }

    private f o() {
        Context context = getContext();
        int i8 = context.getResources().getConfiguration().orientation;
        int rotation = ((WindowManager) context.getSystemService("window")).getDefaultDisplay().getRotation();
        if (i8 == 2) {
            if (rotation == 1) {
                return f.RIGHT;
            }
            if (rotation == 3) {
                return f.LEFT;
            }
            if (rotation == 0 || rotation == 2) {
                return f.BOTH;
            }
        }
        return f.NONE;
    }

    private int t(WindowInsets windowInsets) {
        if (windowInsets.getSystemWindowInsetBottom() < getRootView().getHeight() * 0.18d) {
            return 0;
        }
        return windowInsets.getSystemWindowInsetBottom();
    }

    private void u() {
        View view;
        z5.b.f("FlutterView", "Initializing FlutterView");
        if (this.f7644a != null) {
            z5.b.f("FlutterView", "Internally using a FlutterSurfaceView.");
            view = this.f7644a;
        } else if (this.f7645b != null) {
            z5.b.f("FlutterView", "Internally using a FlutterTextureView.");
            view = this.f7645b;
        } else {
            z5.b.f("FlutterView", "Internally using a FlutterImageView.");
            view = this.f7646c;
        }
        addView(view);
        setFocusable(true);
        setFocusableInTouchMode(true);
        setImportantForAutofill(1);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ boolean w(SpellCheckerInfo spellCheckerInfo) {
        return spellCheckerInfo.getPackageName().equals("com.google.android.inputmethod.latin");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void x() {
        k kVar = this.f7646c;
        if (kVar != null) {
            kVar.g();
            removeView(this.f7646c);
            this.f7646c = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void z(boolean z7, boolean z8) {
        boolean z9 = false;
        if (!this.f7651h.t().o() && !z7 && !z8) {
            z9 = true;
        }
        setWillNotDraw(z9);
    }

    public void A(Runnable runnable) {
        if (this.f7646c == null) {
            z5.b.f("FlutterView", "Tried to revert the image view, but no image view is used.");
            return;
        }
        io.flutter.embedding.engine.renderer.m mVar = this.f7648e;
        if (mVar == null) {
            z5.b.f("FlutterView", "Tried to revert the image view, but no previous surface was used.");
            return;
        }
        this.f7647d = mVar;
        this.f7648e = null;
        FlutterRenderer t8 = this.f7651h.t();
        if (this.f7651h != null && t8 != null) {
            this.f7647d.c();
            t8.j(new e(t8, runnable));
            return;
        }
        this.f7646c.b();
        x();
        runnable.run();
    }

    /* JADX WARN: Code restructure failed: missing block: B:15:0x003f, code lost:
        if (r1 != false) goto L13;
     */
    /* JADX WARN: Removed duplicated region for block: B:20:0x007c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    void B() {
        /*
            r6 = this;
            android.content.res.Resources r0 = r6.getResources()
            android.content.res.Configuration r0 = r0.getConfiguration()
            int r0 = r0.uiMode
            r0 = r0 & 48
            r1 = 32
            r2 = 0
            r3 = 1
            if (r0 != r1) goto L14
            r0 = r3
            goto L15
        L14:
            r0 = r2
        L15:
            if (r0 == 0) goto L1a
            h6.t$c r0 = h6.t.c.dark
            goto L1c
        L1a:
            h6.t$c r0 = h6.t.c.light
        L1c:
            android.view.textservice.TextServicesManager r1 = r6.f7660q
            if (r1 == 0) goto L43
            int r4 = android.os.Build.VERSION.SDK_INT
            r5 = 31
            if (r4 < r5) goto L41
            java.util.List r1 = io.flutter.embedding.android.p.a(r1)
            java.util.stream.Stream r1 = r1.stream()
            io.flutter.embedding.android.r r4 = new io.flutter.embedding.android.r
            r4.<init>()
            boolean r1 = r1.anyMatch(r4)
            android.view.textservice.TextServicesManager r4 = r6.f7660q
            boolean r4 = io.flutter.embedding.android.q.a(r4)
            if (r4 == 0) goto L43
            if (r1 == 0) goto L43
        L41:
            r1 = r3
            goto L44
        L43:
            r1 = r2
        L44:
            io.flutter.embedding.engine.a r4 = r6.f7651h
            h6.t r4 = r4.v()
            h6.t$b r4 = r4.d()
            android.content.res.Resources r5 = r6.getResources()
            android.content.res.Configuration r5 = r5.getConfiguration()
            float r5 = r5.fontScale
            h6.t$b r4 = r4.f(r5)
            android.content.res.Resources r5 = r6.getResources()
            android.util.DisplayMetrics r5 = r5.getDisplayMetrics()
            h6.t$b r4 = r4.c(r5)
            h6.t$b r1 = r4.d(r1)
            android.content.Context r4 = r6.getContext()
            android.content.ContentResolver r4 = r4.getContentResolver()
            java.lang.String r5 = "show_password"
            int r4 = android.provider.Settings.System.getInt(r4, r5, r3)
            if (r4 != r3) goto L7d
            r2 = r3
        L7d:
            h6.t$b r1 = r1.b(r2)
            android.content.Context r2 = r6.getContext()
            boolean r2 = android.text.format.DateFormat.is24HourFormat(r2)
            h6.t$b r1 = r1.g(r2)
            h6.t$b r0 = r1.e(r0)
            r0.a()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: io.flutter.embedding.android.s.B():void");
    }

    @Override // io.flutter.embedding.android.a0.e
    public void a(KeyEvent keyEvent) {
        getRootView().dispatchKeyEvent(keyEvent);
    }

    @Override // android.view.View
    public void autofill(SparseArray sparseArray) {
        this.f7654k.j(sparseArray);
    }

    @Override // k6.a.c
    public PointerIcon b(int i8) {
        return PointerIcon.getSystemIcon(getContext(), i8);
    }

    @Override // io.flutter.embedding.android.a0.e
    public boolean c(KeyEvent keyEvent) {
        return this.f7654k.q(keyEvent);
    }

    @Override // android.view.View
    public boolean checkInputConnectionProxy(View view) {
        io.flutter.embedding.engine.a aVar = this.f7651h;
        return aVar != null ? aVar.q().G(view) : super.checkInputConnectionProxy(view);
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        if (keyEvent.getAction() == 0 && keyEvent.getRepeatCount() == 0) {
            getKeyDispatcherState().startTracking(keyEvent, this);
        } else if (keyEvent.getAction() == 1) {
            getKeyDispatcherState().handleUpEvent(keyEvent);
        }
        return (v() && this.f7657n.b(keyEvent)) || super.dispatchKeyEvent(keyEvent);
    }

    @Override // android.view.View
    public AccessibilityNodeProvider getAccessibilityNodeProvider() {
        io.flutter.view.g gVar = this.f7659p;
        if (gVar == null || !gVar.C()) {
            return null;
        }
        return this.f7659p;
    }

    public io.flutter.embedding.engine.a getAttachedFlutterEngine() {
        return this.f7651h;
    }

    @Override // io.flutter.embedding.android.a0.e
    public i6.b getBinaryMessenger() {
        return this.f7651h.k();
    }

    public k getCurrentImageSurface() {
        return this.f7646c;
    }

    public boolean k() {
        k kVar = this.f7646c;
        if (kVar != null) {
            return kVar.e();
        }
        return false;
    }

    public void l(io.flutter.embedding.engine.renderer.l lVar) {
        this.f7649f.add(lVar);
    }

    public void m(k kVar) {
        io.flutter.embedding.engine.a aVar = this.f7651h;
        if (aVar != null) {
            kVar.d(aVar.t());
        }
    }

    public void n(io.flutter.embedding.engine.a aVar) {
        z5.b.f("FlutterView", "Attaching to a FlutterEngine: " + aVar);
        if (v()) {
            if (aVar == this.f7651h) {
                z5.b.f("FlutterView", "Already attached to this engine. Doing nothing.");
                return;
            } else {
                z5.b.f("FlutterView", "Currently attached to a different engine. Detaching and then attaching to new engine.");
                s();
            }
        }
        this.f7651h = aVar;
        FlutterRenderer t8 = aVar.t();
        this.f7650g = t8.n();
        this.f7647d.d(t8);
        t8.j(this.f7665v);
        this.f7653j = new k6.a(this, this.f7651h.n());
        this.f7654k = new io.flutter.plugin.editing.l(this, this.f7651h.y(), this.f7651h.q());
        try {
            TextServicesManager textServicesManager = (TextServicesManager) getContext().getSystemService("textservices");
            this.f7660q = textServicesManager;
            this.f7655l = new io.flutter.plugin.editing.j(textServicesManager, this.f7651h.w());
        } catch (Exception unused) {
            z5.b.b("FlutterView", "TextServicesManager not supported by device, spell check disabled.");
        }
        this.f7656m = this.f7651h.m();
        this.f7657n = new a0(this);
        this.f7658o = new io.flutter.embedding.android.a(this.f7651h.t(), false);
        io.flutter.view.g gVar = new io.flutter.view.g(this, aVar.h(), (AccessibilityManager) getContext().getSystemService("accessibility"), getContext().getContentResolver(), this.f7651h.q());
        this.f7659p = gVar;
        gVar.a0(this.f7663t);
        z(this.f7659p.C(), this.f7659p.E());
        this.f7651h.q().a(this.f7659p);
        this.f7651h.q().E(this.f7651h.t());
        this.f7654k.p().restartInput(this);
        B();
        getContext().getContentResolver().registerContentObserver(Settings.System.getUriFor("show_password"), false, this.f7664u);
        C();
        aVar.q().F(this);
        Iterator it = this.f7652i.iterator();
        if (it.hasNext()) {
            androidx.appcompat.app.w.a(it.next());
            throw null;
        } else if (this.f7650g) {
            this.f7665v.d();
        }
    }

    @Override // android.view.View
    public final WindowInsets onApplyWindowInsets(WindowInsets windowInsets) {
        Insets insets;
        int i8;
        int i9;
        int i10;
        int i11;
        int ime;
        Insets insets2;
        int i12;
        int i13;
        int i14;
        int i15;
        int systemGestures;
        Insets insets3;
        int i16;
        int i17;
        int i18;
        int i19;
        DisplayCutout displayCutout;
        Insets waterfallInsets;
        int i20;
        int safeInsetTop;
        int i21;
        int safeInsetRight;
        int i22;
        int safeInsetBottom;
        int i23;
        int safeInsetLeft;
        int statusBars;
        int navigationBars;
        Insets systemGestureInsets;
        int i24;
        int i25;
        int i26;
        int i27;
        WindowInsets onApplyWindowInsets = super.onApplyWindowInsets(windowInsets);
        int i28 = Build.VERSION.SDK_INT;
        if (i28 == 29) {
            systemGestureInsets = windowInsets.getSystemGestureInsets();
            FlutterRenderer.h hVar = this.f7662s;
            i24 = systemGestureInsets.top;
            hVar.f7837l = i24;
            FlutterRenderer.h hVar2 = this.f7662s;
            i25 = systemGestureInsets.right;
            hVar2.f7838m = i25;
            FlutterRenderer.h hVar3 = this.f7662s;
            i26 = systemGestureInsets.bottom;
            hVar3.f7839n = i26;
            FlutterRenderer.h hVar4 = this.f7662s;
            i27 = systemGestureInsets.left;
            hVar4.f7840o = i27;
        }
        int i29 = 0;
        boolean z7 = (getWindowSystemUiVisibility() & 4) == 0;
        boolean z8 = (getWindowSystemUiVisibility() & 2) == 0;
        if (i28 >= 30) {
            if (z8) {
                navigationBars = WindowInsets.Type.navigationBars();
                i29 = 0 | navigationBars;
            }
            if (z7) {
                statusBars = WindowInsets.Type.statusBars();
                i29 |= statusBars;
            }
            insets = windowInsets.getInsets(i29);
            FlutterRenderer.h hVar5 = this.f7662s;
            i8 = insets.top;
            hVar5.f7829d = i8;
            FlutterRenderer.h hVar6 = this.f7662s;
            i9 = insets.right;
            hVar6.f7830e = i9;
            FlutterRenderer.h hVar7 = this.f7662s;
            i10 = insets.bottom;
            hVar7.f7831f = i10;
            FlutterRenderer.h hVar8 = this.f7662s;
            i11 = insets.left;
            hVar8.f7832g = i11;
            ime = WindowInsets.Type.ime();
            insets2 = windowInsets.getInsets(ime);
            FlutterRenderer.h hVar9 = this.f7662s;
            i12 = insets2.top;
            hVar9.f7833h = i12;
            FlutterRenderer.h hVar10 = this.f7662s;
            i13 = insets2.right;
            hVar10.f7834i = i13;
            FlutterRenderer.h hVar11 = this.f7662s;
            i14 = insets2.bottom;
            hVar11.f7835j = i14;
            FlutterRenderer.h hVar12 = this.f7662s;
            i15 = insets2.left;
            hVar12.f7836k = i15;
            systemGestures = WindowInsets.Type.systemGestures();
            insets3 = windowInsets.getInsets(systemGestures);
            FlutterRenderer.h hVar13 = this.f7662s;
            i16 = insets3.top;
            hVar13.f7837l = i16;
            FlutterRenderer.h hVar14 = this.f7662s;
            i17 = insets3.right;
            hVar14.f7838m = i17;
            FlutterRenderer.h hVar15 = this.f7662s;
            i18 = insets3.bottom;
            hVar15.f7839n = i18;
            FlutterRenderer.h hVar16 = this.f7662s;
            i19 = insets3.left;
            hVar16.f7840o = i19;
            displayCutout = windowInsets.getDisplayCutout();
            if (displayCutout != null) {
                waterfallInsets = displayCutout.getWaterfallInsets();
                FlutterRenderer.h hVar17 = this.f7662s;
                int i30 = hVar17.f7829d;
                i20 = waterfallInsets.top;
                int max = Math.max(i30, i20);
                safeInsetTop = displayCutout.getSafeInsetTop();
                hVar17.f7829d = Math.max(max, safeInsetTop);
                FlutterRenderer.h hVar18 = this.f7662s;
                int i31 = hVar18.f7830e;
                i21 = waterfallInsets.right;
                int max2 = Math.max(i31, i21);
                safeInsetRight = displayCutout.getSafeInsetRight();
                hVar18.f7830e = Math.max(max2, safeInsetRight);
                FlutterRenderer.h hVar19 = this.f7662s;
                int i32 = hVar19.f7831f;
                i22 = waterfallInsets.bottom;
                int max3 = Math.max(i32, i22);
                safeInsetBottom = displayCutout.getSafeInsetBottom();
                hVar19.f7831f = Math.max(max3, safeInsetBottom);
                FlutterRenderer.h hVar20 = this.f7662s;
                int i33 = hVar20.f7832g;
                i23 = waterfallInsets.left;
                int max4 = Math.max(i33, i23);
                safeInsetLeft = displayCutout.getSafeInsetLeft();
                hVar20.f7832g = Math.max(max4, safeInsetLeft);
            }
        } else {
            f fVar = f.NONE;
            if (!z8) {
                fVar = o();
            }
            this.f7662s.f7829d = z7 ? windowInsets.getSystemWindowInsetTop() : 0;
            this.f7662s.f7830e = (fVar == f.RIGHT || fVar == f.BOTH) ? 0 : windowInsets.getSystemWindowInsetRight();
            this.f7662s.f7831f = (z8 && t(windowInsets) == 0) ? windowInsets.getSystemWindowInsetBottom() : 0;
            this.f7662s.f7832g = (fVar == f.LEFT || fVar == f.BOTH) ? 0 : windowInsets.getSystemWindowInsetLeft();
            FlutterRenderer.h hVar21 = this.f7662s;
            hVar21.f7833h = 0;
            hVar21.f7834i = 0;
            hVar21.f7835j = t(windowInsets);
            this.f7662s.f7836k = 0;
        }
        z5.b.f("FlutterView", "Updating window insets (onApplyWindowInsets()):\nStatus bar insets: Top: " + this.f7662s.f7829d + ", Left: " + this.f7662s.f7832g + ", Right: " + this.f7662s.f7830e + "\nKeyboard insets: Bottom: " + this.f7662s.f7835j + ", Left: " + this.f7662s.f7836k + ", Right: " + this.f7662s.f7834i + "System Gesture Insets - Left: " + this.f7662s.f7840o + ", Top: " + this.f7662s.f7837l + ", Right: " + this.f7662s.f7838m + ", Bottom: " + this.f7662s.f7835j);
        C();
        return onApplyWindowInsets;
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.f7661r = r();
        Activity e8 = p6.h.e(getContext());
        f0 f0Var = this.f7661r;
        if (f0Var == null || e8 == null) {
            return;
        }
        f0Var.a(e8, androidx.core.content.a.g(getContext()), this.f7666w);
    }

    @Override // android.view.View
    protected void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        if (this.f7651h != null) {
            z5.b.f("FlutterView", "Configuration changed. Sending locales and user settings to Flutter.");
            this.f7656m.d(configuration);
            B();
            p6.h.c(getContext(), this.f7651h);
        }
    }

    @Override // android.view.View
    public InputConnection onCreateInputConnection(EditorInfo editorInfo) {
        return !v() ? super.onCreateInputConnection(editorInfo) : this.f7654k.n(this, this.f7657n, editorInfo);
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        f0 f0Var = this.f7661r;
        if (f0Var != null) {
            f0Var.b(this.f7666w);
        }
        this.f7661r = null;
        super.onDetachedFromWindow();
    }

    @Override // android.view.View
    public boolean onGenericMotionEvent(MotionEvent motionEvent) {
        if (v() && this.f7658o.i(motionEvent, getContext())) {
            return true;
        }
        return super.onGenericMotionEvent(motionEvent);
    }

    @Override // android.view.View
    public boolean onHoverEvent(MotionEvent motionEvent) {
        return !v() ? super.onHoverEvent(motionEvent) : this.f7659p.L(motionEvent);
    }

    @Override // android.view.View
    public void onProvideAutofillVirtualStructure(ViewStructure viewStructure, int i8) {
        super.onProvideAutofillVirtualStructure(viewStructure, i8);
        this.f7654k.y(viewStructure, i8);
    }

    @Override // android.view.View
    protected void onSizeChanged(int i8, int i9, int i10, int i11) {
        super.onSizeChanged(i8, i9, i10, i11);
        z5.b.f("FlutterView", "Size changed. Sending Flutter new viewport metrics. FlutterView was " + i10 + " x " + i11 + ", it is now " + i8 + " x " + i9);
        FlutterRenderer.h hVar = this.f7662s;
        hVar.f7827b = i8;
        hVar.f7828c = i9;
        C();
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (v()) {
            requestUnbufferedDispatch(motionEvent);
            return this.f7658o.j(motionEvent);
        }
        return super.onTouchEvent(motionEvent);
    }

    public void p() {
        this.f7647d.a();
        k kVar = this.f7646c;
        if (kVar == null) {
            k q8 = q();
            this.f7646c = q8;
            addView(q8);
        } else {
            kVar.k(getWidth(), getHeight());
        }
        this.f7648e = this.f7647d;
        k kVar2 = this.f7646c;
        this.f7647d = kVar2;
        io.flutter.embedding.engine.a aVar = this.f7651h;
        if (aVar != null) {
            kVar2.d(aVar.t());
        }
    }

    public k q() {
        return new k(getContext(), getWidth(), getHeight(), k.b.background);
    }

    protected f0 r() {
        try {
            return new f0(new g2.a(h2.f.f7194a.d(getContext())));
        } catch (NoClassDefFoundError unused) {
            return null;
        }
    }

    public void s() {
        z5.b.f("FlutterView", "Detaching from a FlutterEngine: " + this.f7651h);
        if (!v()) {
            z5.b.f("FlutterView", "FlutterView not attached to an engine. Not detaching.");
            return;
        }
        Iterator it = this.f7652i.iterator();
        if (it.hasNext()) {
            androidx.appcompat.app.w.a(it.next());
            throw null;
        }
        getContext().getContentResolver().unregisterContentObserver(this.f7664u);
        this.f7651h.q().P();
        this.f7651h.q().d();
        this.f7659p.S();
        this.f7659p = null;
        this.f7654k.p().restartInput(this);
        this.f7654k.o();
        this.f7657n.d();
        io.flutter.plugin.editing.j jVar = this.f7655l;
        if (jVar != null) {
            jVar.b();
        }
        k6.a aVar = this.f7653j;
        if (aVar != null) {
            aVar.c();
        }
        FlutterRenderer t8 = this.f7651h.t();
        this.f7650g = false;
        t8.u(this.f7665v);
        t8.A();
        t8.x(false);
        io.flutter.embedding.engine.renderer.m mVar = this.f7648e;
        if (mVar != null && this.f7647d == this.f7646c) {
            this.f7647d = mVar;
        }
        this.f7647d.b();
        x();
        this.f7648e = null;
        this.f7651h = null;
    }

    @Override // android.view.View
    public void setVisibility(int i8) {
        super.setVisibility(i8);
        io.flutter.embedding.engine.renderer.m mVar = this.f7647d;
        if (mVar instanceof l) {
            ((l) mVar).setVisibility(i8);
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:24:0x009e, code lost:
        r7 = r7.getDisplayCutout();
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    protected void setWindowInfoListenerDisplayFeatures(h2.j r7) {
        /*
            r6 = this;
            java.util.List r7 = r7.a()
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            java.util.Iterator r7 = r7.iterator()
        Ld:
            boolean r1 = r7.hasNext()
            java.lang.String r2 = "FlutterView"
            if (r1 == 0) goto L92
            java.lang.Object r1 = r7.next()
            h2.a r1 = (h2.a) r1
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r4 = "WindowInfoTracker Display Feature reported with bounds = "
            r3.append(r4)
            android.graphics.Rect r4 = r1.a()
            java.lang.String r4 = r4.toString()
            r3.append(r4)
            java.lang.String r4 = " and type = "
            r3.append(r4)
            java.lang.Class r4 = r1.getClass()
            java.lang.String r4 = r4.getSimpleName()
            r3.append(r4)
            java.lang.String r3 = r3.toString()
            z5.b.f(r2, r3)
            boolean r2 = r1 instanceof h2.c
            if (r2 == 0) goto L80
            r2 = r1
            h2.c r2 = (h2.c) r2
            h2.c$a r3 = r2.c()
            h2.c$a r4 = h2.c.a.f7173d
            if (r3 != r4) goto L59
            io.flutter.embedding.engine.renderer.FlutterRenderer$e r3 = io.flutter.embedding.engine.renderer.FlutterRenderer.e.HINGE
            goto L5b
        L59:
            io.flutter.embedding.engine.renderer.FlutterRenderer$e r3 = io.flutter.embedding.engine.renderer.FlutterRenderer.e.FOLD
        L5b:
            h2.c$b r4 = r2.b()
            h2.c$b r5 = h2.c.b.f7176c
            if (r4 != r5) goto L66
            io.flutter.embedding.engine.renderer.FlutterRenderer$d r2 = io.flutter.embedding.engine.renderer.FlutterRenderer.d.POSTURE_FLAT
            goto L73
        L66:
            h2.c$b r2 = r2.b()
            h2.c$b r4 = h2.c.b.f7177d
            if (r2 != r4) goto L71
            io.flutter.embedding.engine.renderer.FlutterRenderer$d r2 = io.flutter.embedding.engine.renderer.FlutterRenderer.d.POSTURE_HALF_OPENED
            goto L73
        L71:
            io.flutter.embedding.engine.renderer.FlutterRenderer$d r2 = io.flutter.embedding.engine.renderer.FlutterRenderer.d.UNKNOWN
        L73:
            io.flutter.embedding.engine.renderer.FlutterRenderer$c r4 = new io.flutter.embedding.engine.renderer.FlutterRenderer$c
            android.graphics.Rect r1 = r1.a()
            r4.<init>(r1, r3, r2)
            r0.add(r4)
            goto Ld
        L80:
            io.flutter.embedding.engine.renderer.FlutterRenderer$c r2 = new io.flutter.embedding.engine.renderer.FlutterRenderer$c
            android.graphics.Rect r1 = r1.a()
            io.flutter.embedding.engine.renderer.FlutterRenderer$e r3 = io.flutter.embedding.engine.renderer.FlutterRenderer.e.UNKNOWN
            io.flutter.embedding.engine.renderer.FlutterRenderer$d r4 = io.flutter.embedding.engine.renderer.FlutterRenderer.d.UNKNOWN
            r2.<init>(r1, r3, r4)
            r0.add(r2)
            goto Ld
        L92:
            int r7 = android.os.Build.VERSION.SDK_INT
            r1 = 28
            if (r7 < r1) goto Ldb
            android.view.WindowInsets r7 = r6.getRootWindowInsets()
            if (r7 == 0) goto Ldb
            android.view.DisplayCutout r7 = androidx.core.view.k1.a(r7)
            if (r7 == 0) goto Ldb
            java.util.List r7 = io.flutter.embedding.android.n.a(r7)
            java.util.Iterator r7 = r7.iterator()
        Lac:
            boolean r1 = r7.hasNext()
            if (r1 == 0) goto Ldb
            java.lang.Object r1 = r7.next()
            android.graphics.Rect r1 = (android.graphics.Rect) r1
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r4 = "DisplayCutout area reported with bounds = "
            r3.append(r4)
            java.lang.String r4 = r1.toString()
            r3.append(r4)
            java.lang.String r3 = r3.toString()
            z5.b.f(r2, r3)
            io.flutter.embedding.engine.renderer.FlutterRenderer$c r3 = new io.flutter.embedding.engine.renderer.FlutterRenderer$c
            io.flutter.embedding.engine.renderer.FlutterRenderer$e r4 = io.flutter.embedding.engine.renderer.FlutterRenderer.e.CUTOUT
            r3.<init>(r1, r4)
            r0.add(r3)
            goto Lac
        Ldb:
            io.flutter.embedding.engine.renderer.FlutterRenderer$h r7 = r6.f7662s
            r7.f7842q = r0
            r6.C()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: io.flutter.embedding.android.s.setWindowInfoListenerDisplayFeatures(h2.j):void");
    }

    public boolean v() {
        io.flutter.embedding.engine.a aVar = this.f7651h;
        return aVar != null && aVar.t() == this.f7647d.getAttachedRenderer();
    }

    public void y(io.flutter.embedding.engine.renderer.l lVar) {
        this.f7649f.remove(lVar);
    }

    private s(Context context, AttributeSet attributeSet, m mVar) {
        super(context, attributeSet);
        this.f7649f = new HashSet();
        this.f7652i = new HashSet();
        this.f7662s = new FlutterRenderer.h();
        this.f7663t = new a();
        this.f7664u = new b(new Handler(Looper.getMainLooper()));
        this.f7665v = new c();
        this.f7666w = new d();
        this.f7645b = mVar;
        this.f7647d = mVar;
        u();
    }

    public s(Context context, l lVar) {
        this(context, (AttributeSet) null, lVar);
    }

    public s(Context context, m mVar) {
        this(context, (AttributeSet) null, mVar);
    }
}
