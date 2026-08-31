package io.flutter.plugin.platform;

import android.content.Context;
import android.content.MutableContextWrapper;
import android.os.Build;
import android.util.SparseArray;
import android.view.MotionEvent;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import h6.q;
import io.flutter.embedding.android.c0;
import io.flutter.embedding.engine.FlutterOverlaySurface;
import io.flutter.embedding.engine.mutatorsstack.FlutterMutatorsStack;
import io.flutter.embedding.engine.renderer.FlutterRenderer;
import io.flutter.plugin.platform.w;
import io.flutter.view.TextureRegistry;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
/* loaded from: classes.dex */
public class w implements q {

    /* renamed from: w  reason: collision with root package name */
    private static Class[] f8003w = {SurfaceView.class};

    /* renamed from: x  reason: collision with root package name */
    private static boolean f8004x = true;

    /* renamed from: y  reason: collision with root package name */
    private static boolean f8005y = true;

    /* renamed from: b  reason: collision with root package name */
    private io.flutter.embedding.android.a f8007b;

    /* renamed from: c  reason: collision with root package name */
    private Context f8008c;

    /* renamed from: d  reason: collision with root package name */
    private io.flutter.embedding.android.s f8009d;

    /* renamed from: e  reason: collision with root package name */
    private TextureRegistry f8010e;

    /* renamed from: f  reason: collision with root package name */
    private io.flutter.plugin.editing.l f8011f;

    /* renamed from: g  reason: collision with root package name */
    private h6.q f8012g;

    /* renamed from: o  reason: collision with root package name */
    private int f8020o = 0;

    /* renamed from: p  reason: collision with root package name */
    private boolean f8021p = false;

    /* renamed from: q  reason: collision with root package name */
    private boolean f8022q = true;

    /* renamed from: u  reason: collision with root package name */
    private boolean f8026u = false;

    /* renamed from: v  reason: collision with root package name */
    private final q.g f8027v = new a();

    /* renamed from: a  reason: collision with root package name */
    private final n f8006a = new n();

    /* renamed from: i  reason: collision with root package name */
    final HashMap f8014i = new HashMap();

    /* renamed from: h  reason: collision with root package name */
    private final io.flutter.plugin.platform.a f8013h = new io.flutter.plugin.platform.a();

    /* renamed from: j  reason: collision with root package name */
    final HashMap f8015j = new HashMap();

    /* renamed from: m  reason: collision with root package name */
    private final SparseArray f8018m = new SparseArray();

    /* renamed from: r  reason: collision with root package name */
    private final HashSet f8023r = new HashSet();

    /* renamed from: s  reason: collision with root package name */
    private final HashSet f8024s = new HashSet();

    /* renamed from: n  reason: collision with root package name */
    private final SparseArray f8019n = new SparseArray();

    /* renamed from: k  reason: collision with root package name */
    private final SparseArray f8016k = new SparseArray();

    /* renamed from: l  reason: collision with root package name */
    private final SparseArray f8017l = new SparseArray();

    /* renamed from: t  reason: collision with root package name */
    private final io.flutter.embedding.android.c0 f8025t = io.flutter.embedding.android.c0.a();

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class a implements q.g {
        a() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void k(f0 f0Var, float f8, q.b bVar) {
            w.this.A0(f0Var);
            if (w.this.f8008c != null) {
                f8 = w.this.V();
            }
            bVar.a(new q.c(w.this.w0(f0Var.f(), f8), w.this.w0(f0Var.e(), f8)));
        }

        @Override // h6.q.g
        public void a(boolean z7) {
            w.this.f8022q = z7;
        }

        @Override // h6.q.g
        public void b(int i8, double d8, double d9) {
            if (w.this.b(i8)) {
                return;
            }
            p pVar = (p) w.this.f8019n.get(i8);
            if (pVar == null) {
                z5.b.b("PlatformViewsController", "Setting offset for unknown platform view with id: " + i8);
                return;
            }
            int y02 = w.this.y0(d8);
            int y03 = w.this.y0(d9);
            FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) pVar.getLayoutParams();
            layoutParams.topMargin = y02;
            layoutParams.leftMargin = y03;
            pVar.setLayoutParams(layoutParams);
        }

        @Override // h6.q.g
        public void c(int i8, int i9) {
            View view;
            StringBuilder sb;
            String str;
            if (!w.B0(i9)) {
                throw new IllegalStateException("Trying to set unknown direction value: " + i9 + "(view id: " + i8 + ")");
            }
            if (w.this.b(i8)) {
                view = ((f0) w.this.f8014i.get(Integer.valueOf(i8))).g();
            } else {
                k kVar = (k) w.this.f8016k.get(i8);
                if (kVar == null) {
                    sb = new StringBuilder();
                    str = "Setting direction to an unknown view with id: ";
                    sb.append(str);
                    sb.append(i8);
                    z5.b.b("PlatformViewsController", sb.toString());
                }
                view = kVar.getView();
            }
            if (view != null) {
                view.setLayoutDirection(i9);
                return;
            }
            sb = new StringBuilder();
            str = "Setting direction to a null view with id: ";
            sb.append(str);
            sb.append(i8);
            z5.b.b("PlatformViewsController", sb.toString());
        }

        @Override // h6.q.g
        public void d(q.f fVar) {
            int i8 = fVar.f7384a;
            float f8 = w.this.f8008c.getResources().getDisplayMetrics().density;
            if (w.this.b(i8)) {
                ((f0) w.this.f8014i.get(Integer.valueOf(i8))).c(w.this.x0(f8, fVar, true));
                return;
            }
            k kVar = (k) w.this.f8016k.get(i8);
            if (kVar == null) {
                z5.b.b("PlatformViewsController", "Sending touch to an unknown view with id: " + i8);
                return;
            }
            View view = kVar.getView();
            if (view != null) {
                view.dispatchTouchEvent(w.this.x0(f8, fVar, false));
                return;
            }
            z5.b.b("PlatformViewsController", "Sending touch to a null view with id: " + i8);
        }

        @Override // h6.q.g
        public long e(q.d dVar) {
            w.this.T(dVar);
            int i8 = dVar.f7368a;
            if (w.this.f8019n.get(i8) != null) {
                throw new IllegalStateException("Trying to create an already created platform view, view id: " + i8);
            } else if (w.this.f8010e == null) {
                throw new IllegalStateException("Texture registry is null. This means that platform views controller was detached, view id: " + i8);
            } else if (w.this.f8009d == null) {
                throw new IllegalStateException("Flutter view is null. This means the platform views controller doesn't have an attached view, view id: " + i8);
            } else {
                k M = w.this.M(dVar, true);
                View view = M.getView();
                if (view.getParent() == null) {
                    if (!(p6.h.f(view, w.f8003w) ? false : true)) {
                        if (dVar.f7375h == q.d.a.TEXTURE_WITH_HYBRID_FALLBACK) {
                            w.this.H(M, dVar);
                            return -2L;
                        } else if (!w.this.f8026u) {
                            return w.this.J(M, dVar);
                        }
                    }
                    return w.this.I(M, dVar);
                }
                throw new IllegalStateException("The Android view returned from PlatformView#getView() was already added to a parent view.");
            }
        }

        @Override // h6.q.g
        public void f(int i8) {
            View view;
            StringBuilder sb;
            String str;
            if (w.this.b(i8)) {
                view = ((f0) w.this.f8014i.get(Integer.valueOf(i8))).g();
            } else {
                k kVar = (k) w.this.f8016k.get(i8);
                if (kVar == null) {
                    sb = new StringBuilder();
                    str = "Clearing focus on an unknown view with id: ";
                    sb.append(str);
                    sb.append(i8);
                    z5.b.b("PlatformViewsController", sb.toString());
                }
                view = kVar.getView();
            }
            if (view != null) {
                view.clearFocus();
                return;
            }
            sb = new StringBuilder();
            str = "Clearing focus on a null view with id: ";
            sb.append(str);
            sb.append(i8);
            z5.b.b("PlatformViewsController", sb.toString());
        }

        @Override // h6.q.g
        public void g(q.d dVar) {
            w.this.S(19);
            w.this.T(dVar);
            w.this.H(w.this.M(dVar, false), dVar);
        }

        @Override // h6.q.g
        public void h(int i8) {
            k kVar = (k) w.this.f8016k.get(i8);
            if (kVar == null) {
                z5.b.b("PlatformViewsController", "Disposing unknown platform view with id: " + i8);
                return;
            }
            if (kVar.getView() != null) {
                View view = kVar.getView();
                ViewGroup viewGroup = (ViewGroup) view.getParent();
                if (viewGroup != null) {
                    viewGroup.removeView(view);
                }
            }
            w.this.f8016k.remove(i8);
            try {
                kVar.dispose();
            } catch (RuntimeException e8) {
                z5.b.c("PlatformViewsController", "Disposing platform view threw an exception", e8);
            }
            if (w.this.b(i8)) {
                f0 f0Var = (f0) w.this.f8014i.get(Integer.valueOf(i8));
                View g8 = f0Var.g();
                if (g8 != null) {
                    w.this.f8015j.remove(g8.getContext());
                }
                f0Var.d();
                w.this.f8014i.remove(Integer.valueOf(i8));
                return;
            }
            p pVar = (p) w.this.f8019n.get(i8);
            if (pVar != null) {
                pVar.removeAllViews();
                pVar.a();
                pVar.c();
                ViewGroup viewGroup2 = (ViewGroup) pVar.getParent();
                if (viewGroup2 != null) {
                    viewGroup2.removeView(pVar);
                }
                w.this.f8019n.remove(i8);
                return;
            }
            d6.a aVar = (d6.a) w.this.f8017l.get(i8);
            if (aVar != null) {
                aVar.removeAllViews();
                aVar.b();
                ViewGroup viewGroup3 = (ViewGroup) aVar.getParent();
                if (viewGroup3 != null) {
                    viewGroup3.removeView(aVar);
                }
                w.this.f8017l.remove(i8);
            }
        }

        @Override // h6.q.g
        public void i(q.e eVar, final q.b bVar) {
            int y02 = w.this.y0(eVar.f7382b);
            int y03 = w.this.y0(eVar.f7383c);
            int i8 = eVar.f7381a;
            if (w.this.b(i8)) {
                final float V = w.this.V();
                final f0 f0Var = (f0) w.this.f8014i.get(Integer.valueOf(i8));
                w.this.d0(f0Var);
                f0Var.k(y02, y03, new Runnable() { // from class: io.flutter.plugin.platform.v
                    @Override // java.lang.Runnable
                    public final void run() {
                        w.a.this.k(f0Var, V, bVar);
                    }
                });
                return;
            }
            k kVar = (k) w.this.f8016k.get(i8);
            p pVar = (p) w.this.f8019n.get(i8);
            if (kVar == null || pVar == null) {
                z5.b.b("PlatformViewsController", "Resizing unknown platform view with id: " + i8);
                return;
            }
            if (y02 > pVar.getRenderTargetWidth() || y03 > pVar.getRenderTargetHeight()) {
                pVar.b(y02, y03);
            }
            ViewGroup.LayoutParams layoutParams = pVar.getLayoutParams();
            layoutParams.width = y02;
            layoutParams.height = y03;
            pVar.setLayoutParams(layoutParams);
            View view = kVar.getView();
            if (view != null) {
                ViewGroup.LayoutParams layoutParams2 = view.getLayoutParams();
                layoutParams2.width = y02;
                layoutParams2.height = y03;
                view.setLayoutParams(layoutParams2);
            }
            bVar.a(new q.c(w.this.v0(pVar.getRenderTargetWidth()), w.this.v0(pVar.getRenderTargetHeight())));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void A0(f0 f0Var) {
        io.flutter.plugin.editing.l lVar = this.f8011f;
        if (lVar == null) {
            return;
        }
        lVar.F();
        f0Var.i();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean B0(int i8) {
        return i8 == 0 || i8 == 1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void H(k kVar, q.d dVar) {
        S(19);
        z5.b.e("PlatformViewsController", "Using hybrid composition for platform view: " + dVar.f7368a);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public long J(k kVar, final q.d dVar) {
        S(20);
        z5.b.e("PlatformViewsController", "Hosting view in a virtual display for platform view: " + dVar.f7368a);
        o e02 = e0(this.f8010e);
        f0 b8 = f0.b(this.f8008c, this.f8013h, kVar, e02, y0(dVar.f7370c), y0(dVar.f7371d), dVar.f7368a, null, new View.OnFocusChangeListener() { // from class: io.flutter.plugin.platform.u
            @Override // android.view.View.OnFocusChangeListener
            public final void onFocusChange(View view, boolean z7) {
                w.this.a0(dVar, view, z7);
            }
        });
        if (b8 != null) {
            this.f8014i.put(Integer.valueOf(dVar.f7368a), b8);
            View view = kVar.getView();
            this.f8015j.put(view.getContext(), view);
            return e02.a();
        }
        throw new IllegalStateException("Failed creating virtual display for a " + dVar.f7369b + " with id: " + dVar.f7368a);
    }

    private void R() {
        while (this.f8016k.size() > 0) {
            this.f8027v.h(this.f8016k.keyAt(0));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void S(int i8) {
        int i9 = Build.VERSION.SDK_INT;
        if (i9 >= i8) {
            return;
        }
        throw new IllegalStateException("Trying to use platform views with API " + i9 + ", required API level is: " + i8);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void T(q.d dVar) {
        if (B0(dVar.f7374g)) {
            return;
        }
        throw new IllegalStateException("Trying to create a view with unknown direction value: " + dVar.f7374g + "(view id: " + dVar.f7368a + ")");
    }

    private void U(boolean z7) {
        for (int i8 = 0; i8 < this.f8018m.size(); i8++) {
            int keyAt = this.f8018m.keyAt(i8);
            c cVar = (c) this.f8018m.valueAt(i8);
            if (this.f8023r.contains(Integer.valueOf(keyAt))) {
                this.f8009d.m(cVar);
                z7 &= cVar.e();
            } else {
                if (!this.f8021p) {
                    cVar.b();
                }
                cVar.setVisibility(8);
                this.f8009d.removeView(cVar);
            }
        }
        for (int i9 = 0; i9 < this.f8017l.size(); i9++) {
            int keyAt2 = this.f8017l.keyAt(i9);
            View view = (View) this.f8017l.get(keyAt2);
            if (!this.f8024s.contains(Integer.valueOf(keyAt2)) || (!z7 && this.f8022q)) {
                view.setVisibility(8);
            } else {
                view.setVisibility(0);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public float V() {
        return this.f8008c.getResources().getDisplayMetrics().density;
    }

    private void Y() {
        if (!this.f8022q || this.f8021p) {
            return;
        }
        this.f8009d.p();
        this.f8021p = true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void Z(q.d dVar, View view, boolean z7) {
        if (z7) {
            this.f8012g.d(dVar.f7368a);
            return;
        }
        io.flutter.plugin.editing.l lVar = this.f8011f;
        if (lVar != null) {
            lVar.k(dVar.f7368a);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void a0(q.d dVar, View view, boolean z7) {
        if (z7) {
            this.f8012g.d(dVar.f7368a);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void b0(int i8, View view, boolean z7) {
        if (z7) {
            this.f8012g.d(i8);
            return;
        }
        io.flutter.plugin.editing.l lVar = this.f8011f;
        if (lVar != null) {
            lVar.k(i8);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void c0() {
        U(false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d0(f0 f0Var) {
        io.flutter.plugin.editing.l lVar = this.f8011f;
        if (lVar == null) {
            return;
        }
        lVar.t();
        f0Var.h();
    }

    private static o e0(TextureRegistry textureRegistry) {
        if (f8005y && Build.VERSION.SDK_INT >= 29) {
            TextureRegistry.SurfaceProducer b8 = textureRegistry.b();
            z5.b.e("PlatformViewsController", "PlatformView is using SurfaceProducer backend");
            return new d0(b8);
        } else if (!f8004x || Build.VERSION.SDK_INT < 29) {
            TextureRegistry.SurfaceTextureEntry c8 = textureRegistry.c();
            z5.b.e("PlatformViewsController", "PlatformView is using SurfaceTexture backend");
            return new e0(c8);
        } else {
            TextureRegistry.ImageTextureEntry a8 = textureRegistry.a();
            z5.b.e("PlatformViewsController", "PlatformView is using ImageReader backend");
            return new b(a8);
        }
    }

    private void f0(k kVar) {
        io.flutter.embedding.android.s sVar = this.f8009d;
        if (sVar == null) {
            z5.b.e("PlatformViewsController", "null flutterView");
        } else {
            kVar.onFlutterViewAttached(sVar);
        }
    }

    private static MotionEvent.PointerCoords p0(Object obj, float f8) {
        List list = (List) obj;
        MotionEvent.PointerCoords pointerCoords = new MotionEvent.PointerCoords();
        pointerCoords.orientation = (float) ((Double) list.get(0)).doubleValue();
        pointerCoords.pressure = (float) ((Double) list.get(1)).doubleValue();
        pointerCoords.size = (float) ((Double) list.get(2)).doubleValue();
        double d8 = f8;
        pointerCoords.toolMajor = (float) (((Double) list.get(3)).doubleValue() * d8);
        pointerCoords.toolMinor = (float) (((Double) list.get(4)).doubleValue() * d8);
        pointerCoords.touchMajor = (float) (((Double) list.get(5)).doubleValue() * d8);
        pointerCoords.touchMinor = (float) (((Double) list.get(6)).doubleValue() * d8);
        pointerCoords.x = (float) (((Double) list.get(7)).doubleValue() * d8);
        pointerCoords.y = (float) (((Double) list.get(8)).doubleValue() * d8);
        return pointerCoords;
    }

    private static List q0(Object obj, float f8) {
        ArrayList arrayList = new ArrayList();
        for (Object obj2 : (List) obj) {
            arrayList.add(p0(obj2, f8));
        }
        return arrayList;
    }

    private static MotionEvent.PointerProperties r0(Object obj) {
        List list = (List) obj;
        MotionEvent.PointerProperties pointerProperties = new MotionEvent.PointerProperties();
        pointerProperties.id = ((Integer) list.get(0)).intValue();
        pointerProperties.toolType = ((Integer) list.get(1)).intValue();
        return pointerProperties;
    }

    private static List s0(Object obj) {
        ArrayList arrayList = new ArrayList();
        for (Object obj2 : (List) obj) {
            arrayList.add(r0(obj2));
        }
        return arrayList;
    }

    private void t0() {
        if (this.f8009d == null) {
            z5.b.b("PlatformViewsController", "removeOverlaySurfaces called while flutter view is null");
            return;
        }
        for (int i8 = 0; i8 < this.f8018m.size(); i8++) {
            this.f8009d.removeView((View) this.f8018m.valueAt(i8));
        }
        this.f8018m.clear();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int v0(double d8) {
        return w0(d8, V());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int w0(double d8, float f8) {
        return (int) Math.round(d8 / f8);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int y0(double d8) {
        return (int) Math.round(d8 * V());
    }

    private static void z0(MotionEvent motionEvent, MotionEvent.PointerCoords[] pointerCoordsArr) {
        if (pointerCoordsArr.length < 1) {
            return;
        }
        motionEvent.offsetLocation(pointerCoordsArr[0].x - motionEvent.getX(), pointerCoordsArr[0].y - motionEvent.getY());
    }

    public void C(Context context, TextureRegistry textureRegistry, a6.a aVar) {
        if (this.f8008c != null) {
            throw new AssertionError("A PlatformViewsController can only be attached to a single output target.\nattach was called while the PlatformViewsController was already attached.");
        }
        this.f8008c = context;
        this.f8010e = textureRegistry;
        h6.q qVar = new h6.q(aVar);
        this.f8012g = qVar;
        qVar.e(this.f8027v);
    }

    public void D(io.flutter.plugin.editing.l lVar) {
        this.f8011f = lVar;
    }

    public void E(FlutterRenderer flutterRenderer) {
        this.f8007b = new io.flutter.embedding.android.a(flutterRenderer, true);
    }

    public void F(io.flutter.embedding.android.s sVar) {
        this.f8009d = sVar;
        for (int i8 = 0; i8 < this.f8019n.size(); i8++) {
            this.f8009d.addView((p) this.f8019n.valueAt(i8));
        }
        for (int i9 = 0; i9 < this.f8017l.size(); i9++) {
            this.f8009d.addView((d6.a) this.f8017l.valueAt(i9));
        }
        for (int i10 = 0; i10 < this.f8016k.size(); i10++) {
            ((k) this.f8016k.valueAt(i10)).onFlutterViewAttached(this.f8009d);
        }
    }

    public boolean G(View view) {
        if (view != null && this.f8015j.containsKey(view.getContext())) {
            View view2 = (View) this.f8015j.get(view.getContext());
            if (view2 == view) {
                return true;
            }
            return view2.checkInputConnectionProxy(view);
        }
        return false;
    }

    public long I(k kVar, final q.d dVar) {
        p pVar;
        long j8;
        S(23);
        z5.b.e("PlatformViewsController", "Hosting view in view hierarchy for platform view: " + dVar.f7368a);
        int y02 = y0(dVar.f7370c);
        int y03 = y0(dVar.f7371d);
        if (this.f8026u) {
            pVar = new p(this.f8008c);
            j8 = -1;
        } else {
            o e02 = e0(this.f8010e);
            p pVar2 = new p(this.f8008c, e02);
            long a8 = e02.a();
            pVar = pVar2;
            j8 = a8;
        }
        pVar.setTouchProcessor(this.f8007b);
        pVar.b(y02, y03);
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(y02, y03);
        int y04 = y0(dVar.f7372e);
        int y05 = y0(dVar.f7373f);
        layoutParams.topMargin = y04;
        layoutParams.leftMargin = y05;
        pVar.setLayoutParams(layoutParams);
        View view = kVar.getView();
        view.setLayoutParams(new FrameLayout.LayoutParams(y02, y03));
        view.setImportantForAccessibility(4);
        pVar.addView(view);
        pVar.setOnDescendantFocusChangeListener(new View.OnFocusChangeListener() { // from class: io.flutter.plugin.platform.t
            @Override // android.view.View.OnFocusChangeListener
            public final void onFocusChange(View view2, boolean z7) {
                w.this.Z(dVar, view2, z7);
            }
        });
        this.f8009d.addView(pVar);
        this.f8019n.append(dVar.f7368a, pVar);
        f0(kVar);
        return j8;
    }

    public FlutterOverlaySurface K() {
        return L(new c(this.f8009d.getContext(), this.f8009d.getWidth(), this.f8009d.getHeight(), this.f8013h));
    }

    public FlutterOverlaySurface L(c cVar) {
        int i8 = this.f8020o;
        this.f8020o = i8 + 1;
        this.f8018m.put(i8, cVar);
        return new FlutterOverlaySurface(i8, cVar.getSurface());
    }

    public k M(q.d dVar, boolean z7) {
        l b8 = this.f8006a.b(dVar.f7369b);
        if (b8 == null) {
            throw new IllegalStateException("Trying to create a platform view of unregistered type: " + dVar.f7369b);
        }
        k create = b8.create(z7 ? new MutableContextWrapper(this.f8008c) : this.f8008c, dVar.f7368a, dVar.f7376i != null ? b8.getCreateArgsCodec().b(dVar.f7376i) : null);
        View view = create.getView();
        if (view != null) {
            view.setLayoutDirection(dVar.f7374g);
            this.f8016k.put(dVar.f7368a, create);
            f0(create);
            return create;
        }
        throw new IllegalStateException("PlatformView#getView() returned null, but an Android view reference was expected.");
    }

    public void N() {
        for (int i8 = 0; i8 < this.f8018m.size(); i8++) {
            c cVar = (c) this.f8018m.valueAt(i8);
            cVar.b();
            cVar.g();
        }
    }

    public void O() {
        h6.q qVar = this.f8012g;
        if (qVar != null) {
            qVar.e(null);
        }
        N();
        this.f8012g = null;
        this.f8008c = null;
        this.f8010e = null;
    }

    public void P() {
        for (int i8 = 0; i8 < this.f8019n.size(); i8++) {
            this.f8009d.removeView((p) this.f8019n.valueAt(i8));
        }
        for (int i9 = 0; i9 < this.f8017l.size(); i9++) {
            this.f8009d.removeView((d6.a) this.f8017l.valueAt(i9));
        }
        N();
        t0();
        this.f8009d = null;
        this.f8021p = false;
        for (int i10 = 0; i10 < this.f8016k.size(); i10++) {
            ((k) this.f8016k.valueAt(i10)).onFlutterViewDetached();
        }
    }

    public void Q() {
        this.f8011f = null;
    }

    public m W() {
        return this.f8006a;
    }

    void X(final int i8) {
        k kVar = (k) this.f8016k.get(i8);
        if (kVar == null) {
            throw new IllegalStateException("Platform view hasn't been initialized from the platform view channel.");
        }
        if (this.f8017l.get(i8) != null) {
            return;
        }
        View view = kVar.getView();
        if (view == null) {
            throw new IllegalStateException("PlatformView#getView() returned null, but an Android view reference was expected.");
        }
        if (view.getParent() != null) {
            throw new IllegalStateException("The Android view returned from PlatformView#getView() was already added to a parent view.");
        }
        Context context = this.f8008c;
        d6.a aVar = new d6.a(context, context.getResources().getDisplayMetrics().density, this.f8007b);
        aVar.setOnDescendantFocusChangeListener(new View.OnFocusChangeListener() { // from class: io.flutter.plugin.platform.r
            @Override // android.view.View.OnFocusChangeListener
            public final void onFocusChange(View view2, boolean z7) {
                w.this.b0(i8, view2, z7);
            }
        });
        this.f8017l.put(i8, aVar);
        view.setImportantForAccessibility(4);
        aVar.addView(view);
        this.f8009d.addView(aVar);
    }

    @Override // io.flutter.plugin.platform.q
    public void a(io.flutter.view.g gVar) {
        this.f8013h.c(gVar);
    }

    @Override // io.flutter.plugin.platform.q
    public boolean b(int i8) {
        return this.f8014i.containsKey(Integer.valueOf(i8));
    }

    @Override // io.flutter.plugin.platform.q
    public View c(int i8) {
        if (b(i8)) {
            return ((f0) this.f8014i.get(Integer.valueOf(i8))).g();
        }
        k kVar = (k) this.f8016k.get(i8);
        if (kVar == null) {
            return null;
        }
        return kVar.getView();
    }

    @Override // io.flutter.plugin.platform.q
    public void d() {
        this.f8013h.c(null);
    }

    public void g0() {
    }

    public void h0() {
        this.f8023r.clear();
        this.f8024s.clear();
    }

    public void i0() {
        R();
    }

    public void j0(int i8, int i9, int i10, int i11, int i12) {
        if (this.f8018m.get(i8) == null) {
            throw new IllegalStateException("The overlay surface (id:" + i8 + ") doesn't exist");
        }
        Y();
        View view = (c) this.f8018m.get(i8);
        if (view.getParent() == null) {
            this.f8009d.addView(view);
        }
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(i11, i12);
        layoutParams.leftMargin = i9;
        layoutParams.topMargin = i10;
        view.setLayoutParams(layoutParams);
        view.setVisibility(0);
        view.bringToFront();
        this.f8023r.add(Integer.valueOf(i8));
    }

    public void k0(int i8, int i9, int i10, int i11, int i12, int i13, int i14, FlutterMutatorsStack flutterMutatorsStack) {
        Y();
        X(i8);
        d6.a aVar = (d6.a) this.f8017l.get(i8);
        aVar.a(flutterMutatorsStack, i9, i10, i11, i12);
        aVar.setVisibility(0);
        aVar.bringToFront();
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(i13, i14);
        View view = ((k) this.f8016k.get(i8)).getView();
        if (view != null) {
            view.setLayoutParams(layoutParams);
            view.bringToFront();
        }
        this.f8024s.add(Integer.valueOf(i8));
    }

    public void l0() {
        boolean z7 = false;
        if (this.f8021p && this.f8024s.isEmpty()) {
            this.f8021p = false;
            this.f8009d.A(new Runnable() { // from class: io.flutter.plugin.platform.s
                @Override // java.lang.Runnable
                public final void run() {
                    w.this.c0();
                }
            });
            return;
        }
        if (this.f8021p && this.f8009d.k()) {
            z7 = true;
        }
        U(z7);
    }

    public void m0() {
        R();
    }

    public void n0() {
        for (f0 f0Var : this.f8014i.values()) {
            f0Var.j();
        }
    }

    public void o0(int i8) {
        if (i8 < 40) {
            return;
        }
        for (f0 f0Var : this.f8014i.values()) {
            f0Var.a();
        }
    }

    public void u0(boolean z7) {
        this.f8026u = z7;
    }

    public MotionEvent x0(float f8, q.f fVar, boolean z7) {
        MotionEvent b8 = this.f8025t.b(c0.a.c(fVar.f7399p));
        MotionEvent.PointerCoords[] pointerCoordsArr = (MotionEvent.PointerCoords[]) q0(fVar.f7390g, f8).toArray(new MotionEvent.PointerCoords[fVar.f7388e]);
        if (z7 || b8 == null) {
            return MotionEvent.obtain(fVar.f7385b.longValue(), fVar.f7386c.longValue(), fVar.f7387d, fVar.f7388e, (MotionEvent.PointerProperties[]) s0(fVar.f7389f).toArray(new MotionEvent.PointerProperties[fVar.f7388e]), pointerCoordsArr, fVar.f7391h, fVar.f7392i, fVar.f7393j, fVar.f7394k, fVar.f7395l, fVar.f7396m, fVar.f7397n, fVar.f7398o);
        }
        z0(b8, pointerCoordsArr);
        return b8;
    }
}
