package io.flutter.plugin.platform;

import android.content.Context;
import android.hardware.display.DisplayManager;
import android.hardware.display.VirtualDisplay;
import android.os.Build;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import io.flutter.plugin.platform.SingleViewPresentation;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class f0 {

    /* renamed from: i  reason: collision with root package name */
    private static VirtualDisplay.Callback f7952i = new a();

    /* renamed from: a  reason: collision with root package name */
    SingleViewPresentation f7953a;

    /* renamed from: b  reason: collision with root package name */
    private final Context f7954b;

    /* renamed from: c  reason: collision with root package name */
    private final io.flutter.plugin.platform.a f7955c;

    /* renamed from: d  reason: collision with root package name */
    private final int f7956d;

    /* renamed from: e  reason: collision with root package name */
    private final int f7957e;

    /* renamed from: f  reason: collision with root package name */
    private final o f7958f;

    /* renamed from: g  reason: collision with root package name */
    private final View.OnFocusChangeListener f7959g;

    /* renamed from: h  reason: collision with root package name */
    private VirtualDisplay f7960h;

    /* loaded from: classes.dex */
    class a extends VirtualDisplay.Callback {
        a() {
        }

        @Override // android.hardware.display.VirtualDisplay.Callback
        public void onPaused() {
        }

        @Override // android.hardware.display.VirtualDisplay.Callback
        public void onResumed() {
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class b implements View.OnAttachStateChangeListener {

        /* renamed from: a  reason: collision with root package name */
        final /* synthetic */ View f7961a;

        /* renamed from: b  reason: collision with root package name */
        final /* synthetic */ Runnable f7962b;

        /* loaded from: classes.dex */
        class a implements Runnable {
            a() {
            }

            @Override // java.lang.Runnable
            public void run() {
                b bVar = b.this;
                bVar.f7961a.postDelayed(bVar.f7962b, 128L);
            }
        }

        b(View view, Runnable runnable) {
            this.f7961a = view;
            this.f7962b = runnable;
        }

        @Override // android.view.View.OnAttachStateChangeListener
        public void onViewAttachedToWindow(View view) {
            c.a(this.f7961a, new a());
            this.f7961a.removeOnAttachStateChangeListener(this);
        }

        @Override // android.view.View.OnAttachStateChangeListener
        public void onViewDetachedFromWindow(View view) {
        }
    }

    /* loaded from: classes.dex */
    static class c implements ViewTreeObserver.OnDrawListener {

        /* renamed from: e  reason: collision with root package name */
        final View f7965e;

        /* renamed from: f  reason: collision with root package name */
        Runnable f7966f;

        /* loaded from: classes.dex */
        class a implements Runnable {
            a() {
            }

            @Override // java.lang.Runnable
            public void run() {
                c.this.f7965e.getViewTreeObserver().removeOnDrawListener(c.this);
            }
        }

        c(View view, Runnable runnable) {
            this.f7965e = view;
            this.f7966f = runnable;
        }

        static void a(View view, Runnable runnable) {
            view.getViewTreeObserver().addOnDrawListener(new c(view, runnable));
        }

        @Override // android.view.ViewTreeObserver.OnDrawListener
        public void onDraw() {
            Runnable runnable = this.f7966f;
            if (runnable == null) {
                return;
            }
            runnable.run();
            this.f7966f = null;
            this.f7965e.post(new a());
        }
    }

    private f0(Context context, io.flutter.plugin.platform.a aVar, VirtualDisplay virtualDisplay, k kVar, o oVar, View.OnFocusChangeListener onFocusChangeListener, int i8, Object obj) {
        this.f7954b = context;
        this.f7955c = aVar;
        this.f7958f = oVar;
        this.f7959g = onFocusChangeListener;
        this.f7957e = i8;
        this.f7960h = virtualDisplay;
        this.f7956d = context.getResources().getDisplayMetrics().densityDpi;
        SingleViewPresentation singleViewPresentation = new SingleViewPresentation(context, this.f7960h.getDisplay(), kVar, aVar, i8, onFocusChangeListener);
        this.f7953a = singleViewPresentation;
        singleViewPresentation.show();
    }

    public static f0 b(Context context, io.flutter.plugin.platform.a aVar, k kVar, o oVar, int i8, int i9, int i10, Object obj, View.OnFocusChangeListener onFocusChangeListener) {
        if (i8 == 0 || i9 == 0) {
            return null;
        }
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        oVar.b(i8, i9);
        VirtualDisplay createVirtualDisplay = ((DisplayManager) context.getSystemService("display")).createVirtualDisplay("flutter-vd#" + i10, i8, i9, displayMetrics.densityDpi, oVar.getSurface(), 0, f7952i, null);
        if (createVirtualDisplay == null) {
            return null;
        }
        return new f0(context, aVar, createVirtualDisplay, kVar, oVar, onFocusChangeListener, i10, obj);
    }

    private void l(View view, int i8, int i9, Runnable runnable) {
        this.f7958f.b(i8, i9);
        this.f7960h.resize(i8, i9, this.f7956d);
        this.f7960h.setSurface(this.f7958f.getSurface());
        view.postDelayed(runnable, 0L);
    }

    public void a() {
        this.f7960h.setSurface(null);
    }

    public void c(MotionEvent motionEvent) {
        SingleViewPresentation singleViewPresentation = this.f7953a;
        if (singleViewPresentation == null) {
            return;
        }
        singleViewPresentation.dispatchTouchEvent(motionEvent);
    }

    public void d() {
        this.f7953a.cancel();
        this.f7953a.detachState();
        this.f7960h.release();
        this.f7958f.release();
    }

    public int e() {
        o oVar = this.f7958f;
        if (oVar != null) {
            return oVar.getHeight();
        }
        return 0;
    }

    public int f() {
        o oVar = this.f7958f;
        if (oVar != null) {
            return oVar.getWidth();
        }
        return 0;
    }

    public View g() {
        SingleViewPresentation singleViewPresentation = this.f7953a;
        if (singleViewPresentation == null) {
            return null;
        }
        return singleViewPresentation.getView().getView();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void h() {
        SingleViewPresentation singleViewPresentation = this.f7953a;
        if (singleViewPresentation == null || singleViewPresentation.getView() == null) {
            return;
        }
        this.f7953a.getView().onInputConnectionLocked();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void i() {
        SingleViewPresentation singleViewPresentation = this.f7953a;
        if (singleViewPresentation == null || singleViewPresentation.getView() == null) {
            return;
        }
        this.f7953a.getView().onInputConnectionUnlocked();
    }

    public void j() {
        int f8 = f();
        int e8 = e();
        boolean isFocused = g().isFocused();
        SingleViewPresentation.d detachState = this.f7953a.detachState();
        this.f7960h.setSurface(null);
        this.f7960h.release();
        this.f7960h = ((DisplayManager) this.f7954b.getSystemService("display")).createVirtualDisplay("flutter-vd#" + this.f7957e, f8, e8, this.f7956d, this.f7958f.getSurface(), 0, f7952i, null);
        SingleViewPresentation singleViewPresentation = new SingleViewPresentation(this.f7954b, this.f7960h.getDisplay(), this.f7955c, detachState, this.f7959g, isFocused);
        singleViewPresentation.show();
        this.f7953a.cancel();
        this.f7953a = singleViewPresentation;
    }

    public void k(int i8, int i9, Runnable runnable) {
        if (i8 == f() && i9 == e()) {
            g().postDelayed(runnable, 0L);
        } else if (Build.VERSION.SDK_INT >= 31) {
            l(g(), i8, i9, runnable);
        } else {
            boolean isFocused = g().isFocused();
            SingleViewPresentation.d detachState = this.f7953a.detachState();
            this.f7960h.setSurface(null);
            this.f7960h.release();
            this.f7958f.b(i8, i9);
            this.f7960h = ((DisplayManager) this.f7954b.getSystemService("display")).createVirtualDisplay("flutter-vd#" + this.f7957e, i8, i9, this.f7956d, this.f7958f.getSurface(), 0, f7952i, null);
            View g8 = g();
            g8.addOnAttachStateChangeListener(new b(g8, runnable));
            SingleViewPresentation singleViewPresentation = new SingleViewPresentation(this.f7954b, this.f7960h.getDisplay(), this.f7955c, detachState, this.f7959g, isFocused);
            singleViewPresentation.show();
            this.f7953a.cancel();
            this.f7953a = singleViewPresentation;
        }
    }
}
