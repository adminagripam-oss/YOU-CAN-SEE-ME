package io.flutter.plugin.platform;

import android.app.AlertDialog;
import android.app.Presentation;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.MutableContextWrapper;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.view.accessibility.AccessibilityEvent;
import android.view.inputmethod.InputMethodManager;
import android.widget.FrameLayout;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class SingleViewPresentation extends Presentation {
    private static final String TAG = "PlatformViewsController";
    private final io.flutter.plugin.platform.a accessibilityEventsDelegate;
    private FrameLayout container;
    private final View.OnFocusChangeListener focusChangeListener;
    private final Context outerContext;
    private a rootView;
    private boolean startFocused;
    private final d state;
    private int viewId;

    /* loaded from: classes.dex */
    private static class a extends FrameLayout {

        /* renamed from: a  reason: collision with root package name */
        private final io.flutter.plugin.platform.a f7923a;

        /* renamed from: b  reason: collision with root package name */
        private final View f7924b;

        public a(Context context, io.flutter.plugin.platform.a aVar, View view) {
            super(context);
            this.f7923a = aVar;
            this.f7924b = view;
        }

        @Override // android.view.ViewGroup, android.view.ViewParent
        public boolean requestSendAccessibilityEvent(View view, AccessibilityEvent accessibilityEvent) {
            return this.f7923a.b(this.f7924b, view, accessibilityEvent);
        }
    }

    /* loaded from: classes.dex */
    private static class b extends ContextWrapper {

        /* renamed from: a  reason: collision with root package name */
        private final InputMethodManager f7925a;

        b(Context context) {
            this(context, null);
        }

        @Override // android.content.ContextWrapper, android.content.Context
        public Context createDisplayContext(Display display) {
            return new b(super.createDisplayContext(display), this.f7925a);
        }

        @Override // android.content.ContextWrapper, android.content.Context
        public Object getSystemService(String str) {
            return "input_method".equals(str) ? this.f7925a : super.getSystemService(str);
        }

        private b(Context context, InputMethodManager inputMethodManager) {
            super(context);
            this.f7925a = inputMethodManager == null ? (InputMethodManager) context.getSystemService("input_method") : inputMethodManager;
        }
    }

    /* loaded from: classes.dex */
    private static class c extends ContextWrapper {

        /* renamed from: a  reason: collision with root package name */
        private final g0 f7926a;

        /* renamed from: b  reason: collision with root package name */
        private WindowManager f7927b;

        /* renamed from: c  reason: collision with root package name */
        private final Context f7928c;

        c(Context context, g0 g0Var, Context context2) {
            super(context);
            this.f7926a = g0Var;
            this.f7928c = context2;
        }

        private WindowManager a() {
            if (this.f7927b == null) {
                this.f7927b = this.f7926a;
            }
            return this.f7927b;
        }

        private boolean b() {
            StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
            for (int i8 = 0; i8 < stackTrace.length && i8 < 11; i8++) {
                if (stackTrace[i8].getClassName().equals(AlertDialog.class.getCanonicalName()) && stackTrace[i8].getMethodName().equals("<init>")) {
                    return true;
                }
            }
            return false;
        }

        @Override // android.content.ContextWrapper, android.content.Context
        public Object getSystemService(String str) {
            return "window".equals(str) ? b() ? this.f7928c.getSystemService(str) : a() : super.getSystemService(str);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static class d {

        /* renamed from: a  reason: collision with root package name */
        private k f7929a;

        /* renamed from: b  reason: collision with root package name */
        private g0 f7930b;

        /* renamed from: c  reason: collision with root package name */
        private x f7931c;

        d() {
        }
    }

    public SingleViewPresentation(Context context, Display display, io.flutter.plugin.platform.a aVar, d dVar, View.OnFocusChangeListener onFocusChangeListener, boolean z7) {
        super(new b(context), display);
        this.startFocused = false;
        this.accessibilityEventsDelegate = aVar;
        this.state = dVar;
        this.focusChangeListener = onFocusChangeListener;
        this.outerContext = context;
        getWindow().setFlags(8, 8);
        this.startFocused = z7;
    }

    public d detachState() {
        FrameLayout frameLayout = this.container;
        if (frameLayout != null) {
            frameLayout.removeAllViews();
        }
        a aVar = this.rootView;
        if (aVar != null) {
            aVar.removeAllViews();
        }
        return this.state;
    }

    public k getView() {
        return this.state.f7929a;
    }

    @Override // android.app.Dialog
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        getWindow().setBackgroundDrawable(new ColorDrawable(0));
        if (this.state.f7931c == null) {
            this.state.f7931c = new x(getContext());
        }
        if (this.state.f7930b == null) {
            d dVar = this.state;
            dVar.f7930b = new g0((WindowManager) getContext().getSystemService("window"), dVar.f7931c);
        }
        this.container = new FrameLayout(getContext());
        c cVar = new c(getContext(), this.state.f7930b, this.outerContext);
        View view = this.state.f7929a.getView();
        if (view.getContext() instanceof MutableContextWrapper) {
            ((MutableContextWrapper) view.getContext()).setBaseContext(cVar);
        } else {
            z5.b.g(TAG, "Unexpected platform view context for view ID " + this.viewId + "; some functionality may not work correctly. When constructing a platform view in the factory, ensure that the view returned from PlatformViewFactory#create returns the provided context from getContext(). If you are unable to associate the view with that context, consider using Hybrid Composition instead.");
        }
        this.container.addView(view);
        a aVar = new a(getContext(), this.accessibilityEventsDelegate, view);
        this.rootView = aVar;
        aVar.addView(this.container);
        this.rootView.addView(this.state.f7931c);
        view.setOnFocusChangeListener(this.focusChangeListener);
        this.rootView.setFocusableInTouchMode(true);
        if (this.startFocused) {
            view.requestFocus();
        } else {
            this.rootView.requestFocus();
        }
        setContentView(this.rootView);
    }

    public SingleViewPresentation(Context context, Display display, k kVar, io.flutter.plugin.platform.a aVar, int i8, View.OnFocusChangeListener onFocusChangeListener) {
        super(new b(context), display);
        this.startFocused = false;
        this.accessibilityEventsDelegate = aVar;
        this.viewId = i8;
        this.focusChangeListener = onFocusChangeListener;
        this.outerContext = context;
        d dVar = new d();
        this.state = dVar;
        dVar.f7929a = kVar;
        getWindow().setFlags(8, 8);
        getWindow().setType(2030);
    }
}
