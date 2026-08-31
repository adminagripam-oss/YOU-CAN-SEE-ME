package androidx.core.view;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseArray;
import android.view.ContentInfo;
import android.view.KeyEvent;
import android.view.OnReceiveContentListener;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.ViewTreeObserver;
import android.view.WindowInsets;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityManager;
import android.view.contentcapture.ContentCaptureSession;
import androidx.core.view.a;
import androidx.core.view.b1;
import java.lang.ref.WeakReference;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.WeakHashMap;
/* loaded from: classes.dex */
public abstract class p0 {

    /* renamed from: a  reason: collision with root package name */
    private static WeakHashMap f3158a = null;

    /* renamed from: b  reason: collision with root package name */
    private static Field f3159b = null;

    /* renamed from: c  reason: collision with root package name */
    private static boolean f3160c = false;

    /* renamed from: d  reason: collision with root package name */
    private static final int[] f3161d = {c1.b.f4696b, c1.b.f4697c, c1.b.f4708n, c1.b.f4719y, c1.b.B, c1.b.C, c1.b.D, c1.b.E, c1.b.F, c1.b.G, c1.b.f4698d, c1.b.f4699e, c1.b.f4700f, c1.b.f4701g, c1.b.f4702h, c1.b.f4703i, c1.b.f4704j, c1.b.f4705k, c1.b.f4706l, c1.b.f4707m, c1.b.f4709o, c1.b.f4710p, c1.b.f4711q, c1.b.f4712r, c1.b.f4713s, c1.b.f4714t, c1.b.f4715u, c1.b.f4716v, c1.b.f4717w, c1.b.f4718x, c1.b.f4720z, c1.b.A};

    /* renamed from: e  reason: collision with root package name */
    private static final j0 f3162e = new j0() { // from class: androidx.core.view.o0
        @Override // androidx.core.view.j0
        public final d a(d dVar) {
            d C;
            C = p0.C(dVar);
            return C;
        }
    };

    /* renamed from: f  reason: collision with root package name */
    private static final e f3163f = new e();

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class a extends f {
        a(int i8, Class cls, int i9) {
            super(i8, cls, i9);
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // androidx.core.view.p0.f
        /* renamed from: h */
        public Boolean c(View view) {
            return Boolean.valueOf(j.d(view));
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // androidx.core.view.p0.f
        /* renamed from: i */
        public void d(View view, Boolean bool) {
            j.j(view, bool.booleanValue());
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // androidx.core.view.p0.f
        /* renamed from: j */
        public boolean g(Boolean bool, Boolean bool2) {
            return !a(bool, bool2);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class b extends f {
        b(int i8, Class cls, int i9, int i10) {
            super(i8, cls, i9, i10);
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // androidx.core.view.p0.f
        /* renamed from: h */
        public CharSequence c(View view) {
            return j.b(view);
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // androidx.core.view.p0.f
        /* renamed from: i */
        public void d(View view, CharSequence charSequence) {
            j.h(view, charSequence);
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // androidx.core.view.p0.f
        /* renamed from: j */
        public boolean g(CharSequence charSequence, CharSequence charSequence2) {
            return !TextUtils.equals(charSequence, charSequence2);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class c extends f {
        c(int i8, Class cls, int i9, int i10) {
            super(i8, cls, i9, i10);
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // androidx.core.view.p0.f
        /* renamed from: h */
        public CharSequence c(View view) {
            return l.b(view);
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // androidx.core.view.p0.f
        /* renamed from: i */
        public void d(View view, CharSequence charSequence) {
            l.e(view, charSequence);
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // androidx.core.view.p0.f
        /* renamed from: j */
        public boolean g(CharSequence charSequence, CharSequence charSequence2) {
            return !TextUtils.equals(charSequence, charSequence2);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class d extends f {
        d(int i8, Class cls, int i9) {
            super(i8, cls, i9);
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // androidx.core.view.p0.f
        /* renamed from: h */
        public Boolean c(View view) {
            return Boolean.valueOf(j.c(view));
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // androidx.core.view.p0.f
        /* renamed from: i */
        public void d(View view, Boolean bool) {
            j.g(view, bool.booleanValue());
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // androidx.core.view.p0.f
        /* renamed from: j */
        public boolean g(Boolean bool, Boolean bool2) {
            return !a(bool, bool2);
        }
    }

    /* loaded from: classes.dex */
    static class e implements ViewTreeObserver.OnGlobalLayoutListener, View.OnAttachStateChangeListener {

        /* renamed from: a  reason: collision with root package name */
        private final WeakHashMap f3164a = new WeakHashMap();

        e() {
        }

        private void b(Map.Entry entry) {
            View view = (View) entry.getKey();
            boolean booleanValue = ((Boolean) entry.getValue()).booleanValue();
            boolean z7 = view.isShown() && view.getWindowVisibility() == 0;
            if (booleanValue != z7) {
                p0.D(view, z7 ? 16 : 32);
                entry.setValue(Boolean.valueOf(z7));
            }
        }

        private void c(View view) {
            view.getViewTreeObserver().addOnGlobalLayoutListener(this);
        }

        private void e(View view) {
            view.getViewTreeObserver().removeOnGlobalLayoutListener(this);
        }

        void a(View view) {
            this.f3164a.put(view, Boolean.valueOf(view.isShown() && view.getWindowVisibility() == 0));
            view.addOnAttachStateChangeListener(this);
            if (view.isAttachedToWindow()) {
                c(view);
            }
        }

        void d(View view) {
            this.f3164a.remove(view);
            view.removeOnAttachStateChangeListener(this);
            e(view);
        }

        @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
        public void onGlobalLayout() {
            if (Build.VERSION.SDK_INT < 28) {
                for (Map.Entry entry : this.f3164a.entrySet()) {
                    b(entry);
                }
            }
        }

        @Override // android.view.View.OnAttachStateChangeListener
        public void onViewAttachedToWindow(View view) {
            c(view);
        }

        @Override // android.view.View.OnAttachStateChangeListener
        public void onViewDetachedFromWindow(View view) {
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static abstract class f {

        /* renamed from: a  reason: collision with root package name */
        private final int f3165a;

        /* renamed from: b  reason: collision with root package name */
        private final Class f3166b;

        /* renamed from: c  reason: collision with root package name */
        private final int f3167c;

        /* renamed from: d  reason: collision with root package name */
        private final int f3168d;

        f(int i8, Class cls, int i9) {
            this(i8, cls, 0, i9);
        }

        private boolean b() {
            return Build.VERSION.SDK_INT >= this.f3167c;
        }

        boolean a(Boolean bool, Boolean bool2) {
            return (bool != null && bool.booleanValue()) == (bool2 != null && bool2.booleanValue());
        }

        abstract Object c(View view);

        abstract void d(View view, Object obj);

        Object e(View view) {
            if (b()) {
                return c(view);
            }
            Object tag = view.getTag(this.f3165a);
            if (this.f3166b.isInstance(tag)) {
                return tag;
            }
            return null;
        }

        void f(View view, Object obj) {
            if (b()) {
                d(view, obj);
            } else if (g(e(view), obj)) {
                p0.h(view);
                view.setTag(this.f3165a, obj);
                p0.D(view, this.f3168d);
            }
        }

        abstract boolean g(Object obj, Object obj2);

        f(int i8, Class cls, int i9, int i10) {
            this.f3165a = i8;
            this.f3166b = cls;
            this.f3168d = i9;
            this.f3167c = i10;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static class g {
        static WindowInsets a(View view, WindowInsets windowInsets) {
            return view.dispatchApplyWindowInsets(windowInsets);
        }

        static WindowInsets b(View view, WindowInsets windowInsets) {
            return view.onApplyWindowInsets(windowInsets);
        }

        static void c(View view) {
            view.requestApplyInsets();
        }
    }

    /* loaded from: classes.dex */
    private static class h {

        /* JADX INFO: Access modifiers changed from: package-private */
        /* loaded from: classes.dex */
        public class a implements View.OnApplyWindowInsetsListener {

            /* renamed from: a  reason: collision with root package name */
            b1 f3169a = null;

            /* renamed from: b  reason: collision with root package name */
            final /* synthetic */ View f3170b;

            /* renamed from: c  reason: collision with root package name */
            final /* synthetic */ h0 f3171c;

            a(View view, h0 h0Var) {
                this.f3170b = view;
                this.f3171c = h0Var;
            }

            @Override // android.view.View.OnApplyWindowInsetsListener
            public WindowInsets onApplyWindowInsets(View view, WindowInsets windowInsets) {
                b1 v7 = b1.v(windowInsets, view);
                int i8 = Build.VERSION.SDK_INT;
                if (i8 < 30) {
                    h.a(windowInsets, this.f3170b);
                    if (v7.equals(this.f3169a)) {
                        return this.f3171c.a(view, v7).t();
                    }
                }
                this.f3169a = v7;
                b1 a8 = this.f3171c.a(view, v7);
                if (i8 >= 30) {
                    return a8.t();
                }
                p0.K(view);
                return a8.t();
            }
        }

        static void a(WindowInsets windowInsets, View view) {
            View.OnApplyWindowInsetsListener onApplyWindowInsetsListener = (View.OnApplyWindowInsetsListener) view.getTag(c1.b.S);
            if (onApplyWindowInsetsListener != null) {
                onApplyWindowInsetsListener.onApplyWindowInsets(view, windowInsets);
            }
        }

        static b1 b(View view, b1 b1Var, Rect rect) {
            WindowInsets t8 = b1Var.t();
            if (t8 != null) {
                return b1.v(view.computeSystemWindowInsets(t8, rect), view);
            }
            rect.setEmpty();
            return b1Var;
        }

        static boolean c(View view, float f8, float f9, boolean z7) {
            return view.dispatchNestedFling(f8, f9, z7);
        }

        static boolean d(View view, float f8, float f9) {
            return view.dispatchNestedPreFling(f8, f9);
        }

        static boolean e(View view, int i8, int i9, int[] iArr, int[] iArr2) {
            return view.dispatchNestedPreScroll(i8, i9, iArr, iArr2);
        }

        static boolean f(View view, int i8, int i9, int i10, int i11, int[] iArr) {
            return view.dispatchNestedScroll(i8, i9, i10, i11, iArr);
        }

        static ColorStateList g(View view) {
            return view.getBackgroundTintList();
        }

        static PorterDuff.Mode h(View view) {
            return view.getBackgroundTintMode();
        }

        static float i(View view) {
            return view.getElevation();
        }

        public static b1 j(View view) {
            return b1.a.a(view);
        }

        static String k(View view) {
            return view.getTransitionName();
        }

        static float l(View view) {
            return view.getTranslationZ();
        }

        static float m(View view) {
            return view.getZ();
        }

        static boolean n(View view) {
            return view.hasNestedScrollingParent();
        }

        static boolean o(View view) {
            return view.isImportantForAccessibility();
        }

        static boolean p(View view) {
            return view.isNestedScrollingEnabled();
        }

        static void q(View view, ColorStateList colorStateList) {
            view.setBackgroundTintList(colorStateList);
        }

        static void r(View view, PorterDuff.Mode mode) {
            view.setBackgroundTintMode(mode);
        }

        static void s(View view, float f8) {
            view.setElevation(f8);
        }

        static void t(View view, boolean z7) {
            view.setNestedScrollingEnabled(z7);
        }

        static void u(View view, h0 h0Var) {
            if (Build.VERSION.SDK_INT < 30) {
                view.setTag(c1.b.L, h0Var);
            }
            if (h0Var == null) {
                view.setOnApplyWindowInsetsListener((View.OnApplyWindowInsetsListener) view.getTag(c1.b.S));
            } else {
                view.setOnApplyWindowInsetsListener(new a(view, h0Var));
            }
        }

        static void v(View view, String str) {
            view.setTransitionName(str);
        }

        static void w(View view, float f8) {
            view.setTranslationZ(f8);
        }

        static void x(View view, float f8) {
            view.setZ(f8);
        }

        static boolean y(View view, int i8) {
            return view.startNestedScroll(i8);
        }

        static void z(View view) {
            view.stopNestedScroll();
        }
    }

    /* loaded from: classes.dex */
    private static class i {
        public static b1 a(View view) {
            WindowInsets rootWindowInsets = view.getRootWindowInsets();
            if (rootWindowInsets == null) {
                return null;
            }
            b1 u7 = b1.u(rootWindowInsets);
            u7.r(u7);
            u7.d(view.getRootView());
            return u7;
        }

        static int b(View view) {
            return view.getScrollIndicators();
        }

        static void c(View view, int i8) {
            view.setScrollIndicators(i8);
        }

        static void d(View view, int i8, int i9) {
            view.setScrollIndicators(i8, i9);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static class j {
        static void a(View view, final o oVar) {
            b1.g gVar = (b1.g) view.getTag(c1.b.R);
            if (gVar == null) {
                gVar = new b1.g();
                view.setTag(c1.b.R, gVar);
            }
            Objects.requireNonNull(oVar);
            View.OnUnhandledKeyEventListener onUnhandledKeyEventListener = new View.OnUnhandledKeyEventListener(oVar) { // from class: androidx.core.view.q0
                @Override // android.view.View.OnUnhandledKeyEventListener
                public final boolean onUnhandledKeyEvent(View view2, KeyEvent keyEvent) {
                    throw null;
                }
            };
            gVar.put(oVar, onUnhandledKeyEventListener);
            view.addOnUnhandledKeyEventListener(onUnhandledKeyEventListener);
        }

        static CharSequence b(View view) {
            return view.getAccessibilityPaneTitle();
        }

        static boolean c(View view) {
            return view.isAccessibilityHeading();
        }

        static boolean d(View view) {
            return view.isScreenReaderFocusable();
        }

        static void e(View view, o oVar) {
            View.OnUnhandledKeyEventListener onUnhandledKeyEventListener;
            b1.g gVar = (b1.g) view.getTag(c1.b.R);
            if (gVar == null || (onUnhandledKeyEventListener = (View.OnUnhandledKeyEventListener) gVar.get(oVar)) == null) {
                return;
            }
            view.removeOnUnhandledKeyEventListener(onUnhandledKeyEventListener);
        }

        static <T> T f(View view, int i8) {
            return (T) view.requireViewById(i8);
        }

        static void g(View view, boolean z7) {
            view.setAccessibilityHeading(z7);
        }

        static void h(View view, CharSequence charSequence) {
            view.setAccessibilityPaneTitle(charSequence);
        }

        public static void i(View view, i1.a aVar) {
            view.setAutofillId(null);
        }

        static void j(View view, boolean z7) {
            view.setScreenReaderFocusable(z7);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class k {
        static View.AccessibilityDelegate a(View view) {
            return view.getAccessibilityDelegate();
        }

        static ContentCaptureSession b(View view) {
            return view.getContentCaptureSession();
        }

        static List<Rect> c(View view) {
            return view.getSystemGestureExclusionRects();
        }

        static void d(View view, Context context, int[] iArr, AttributeSet attributeSet, TypedArray typedArray, int i8, int i9) {
            view.saveAttributeDataForStyleable(context, iArr, attributeSet, typedArray, i8, i9);
        }

        static void e(View view, j1.a aVar) {
            view.setContentCaptureSession(null);
        }

        static void f(View view, List<Rect> list) {
            view.setSystemGestureExclusionRects(list);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class l {
        static int a(View view) {
            return view.getImportantForContentCapture();
        }

        static CharSequence b(View view) {
            return view.getStateDescription();
        }

        static boolean c(View view) {
            return view.isImportantForContentCapture();
        }

        static void d(View view, int i8) {
            view.setImportantForContentCapture(i8);
        }

        static void e(View view, CharSequence charSequence) {
            view.setStateDescription(charSequence);
        }
    }

    /* loaded from: classes.dex */
    private static final class m {
        public static String[] a(View view) {
            return view.getReceiveContentMimeTypes();
        }

        public static androidx.core.view.d b(View view, androidx.core.view.d dVar) {
            ContentInfo f8 = dVar.f();
            ContentInfo performReceiveContent = view.performReceiveContent(f8);
            if (performReceiveContent == null) {
                return null;
            }
            return performReceiveContent == f8 ? dVar : androidx.core.view.d.g(performReceiveContent);
        }

        public static void c(View view, String[] strArr, i0 i0Var) {
            if (i0Var == null) {
                view.setOnReceiveContentListener(strArr, null);
            } else {
                view.setOnReceiveContentListener(strArr, new n(i0Var));
            }
        }
    }

    /* loaded from: classes.dex */
    private static final class n implements OnReceiveContentListener {

        /* renamed from: a  reason: collision with root package name */
        private final i0 f3172a;

        n(i0 i0Var) {
            this.f3172a = i0Var;
        }

        public ContentInfo onReceiveContent(View view, ContentInfo contentInfo) {
            androidx.core.view.d g8 = androidx.core.view.d.g(contentInfo);
            androidx.core.view.d a8 = this.f3172a.a(view, g8);
            if (a8 == null) {
                return null;
            }
            return a8 == g8 ? contentInfo : a8.f();
        }
    }

    /* loaded from: classes.dex */
    public interface o {
    }

    /* loaded from: classes.dex */
    static class p {

        /* renamed from: d  reason: collision with root package name */
        private static final ArrayList f3173d = new ArrayList();

        /* renamed from: a  reason: collision with root package name */
        private WeakHashMap f3174a = null;

        /* renamed from: b  reason: collision with root package name */
        private SparseArray f3175b = null;

        /* renamed from: c  reason: collision with root package name */
        private WeakReference f3176c = null;

        p() {
        }

        static p a(View view) {
            p pVar = (p) view.getTag(c1.b.Q);
            if (pVar == null) {
                p pVar2 = new p();
                view.setTag(c1.b.Q, pVar2);
                return pVar2;
            }
            return pVar;
        }

        private View c(View view, KeyEvent keyEvent) {
            WeakHashMap weakHashMap = this.f3174a;
            if (weakHashMap != null && weakHashMap.containsKey(view)) {
                if (view instanceof ViewGroup) {
                    ViewGroup viewGroup = (ViewGroup) view;
                    for (int childCount = viewGroup.getChildCount() - 1; childCount >= 0; childCount--) {
                        View c8 = c(viewGroup.getChildAt(childCount), keyEvent);
                        if (c8 != null) {
                            return c8;
                        }
                    }
                }
                if (e(view, keyEvent)) {
                    return view;
                }
            }
            return null;
        }

        private SparseArray d() {
            if (this.f3175b == null) {
                this.f3175b = new SparseArray();
            }
            return this.f3175b;
        }

        private boolean e(View view, KeyEvent keyEvent) {
            int size;
            ArrayList arrayList = (ArrayList) view.getTag(c1.b.R);
            if (arrayList == null || arrayList.size() - 1 < 0) {
                return false;
            }
            androidx.appcompat.app.w.a(arrayList.get(size));
            throw null;
        }

        private void g() {
            WeakHashMap weakHashMap = this.f3174a;
            if (weakHashMap != null) {
                weakHashMap.clear();
            }
            ArrayList arrayList = f3173d;
            if (arrayList.isEmpty()) {
                return;
            }
            synchronized (arrayList) {
                if (this.f3174a == null) {
                    this.f3174a = new WeakHashMap();
                }
                for (int size = arrayList.size() - 1; size >= 0; size--) {
                    ArrayList arrayList2 = f3173d;
                    View view = (View) ((WeakReference) arrayList2.get(size)).get();
                    if (view == null) {
                        arrayList2.remove(size);
                    } else {
                        this.f3174a.put(view, Boolean.TRUE);
                        for (ViewParent parent = view.getParent(); parent instanceof View; parent = parent.getParent()) {
                            this.f3174a.put((View) parent, Boolean.TRUE);
                        }
                    }
                }
            }
        }

        boolean b(View view, KeyEvent keyEvent) {
            if (keyEvent.getAction() == 0) {
                g();
            }
            View c8 = c(view, keyEvent);
            if (keyEvent.getAction() == 0) {
                int keyCode = keyEvent.getKeyCode();
                if (c8 != null && !KeyEvent.isModifierKey(keyCode)) {
                    d().put(keyCode, new WeakReference(c8));
                }
            }
            return c8 != null;
        }

        boolean f(KeyEvent keyEvent) {
            WeakReference weakReference;
            int indexOfKey;
            WeakReference weakReference2 = this.f3176c;
            if (weakReference2 == null || weakReference2.get() != keyEvent) {
                this.f3176c = new WeakReference(keyEvent);
                SparseArray d8 = d();
                if (keyEvent.getAction() != 1 || (indexOfKey = d8.indexOfKey(keyEvent.getKeyCode())) < 0) {
                    weakReference = null;
                } else {
                    weakReference = (WeakReference) d8.valueAt(indexOfKey);
                    d8.removeAt(indexOfKey);
                }
                if (weakReference == null) {
                    weakReference = (WeakReference) d8.get(keyEvent.getKeyCode());
                }
                if (weakReference != null) {
                    View view = (View) weakReference.get();
                    if (view != null && view.isAttachedToWindow()) {
                        e(view, keyEvent);
                    }
                    return true;
                }
                return false;
            }
            return false;
        }
    }

    public static boolean A(View view) {
        return h.p(view);
    }

    public static boolean B(View view) {
        Boolean bool = (Boolean) M().e(view);
        return bool != null && bool.booleanValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ androidx.core.view.d C(androidx.core.view.d dVar) {
        return dVar;
    }

    static void D(View view, int i8) {
        AccessibilityManager accessibilityManager = (AccessibilityManager) view.getContext().getSystemService("accessibility");
        if (accessibilityManager.isEnabled()) {
            boolean z7 = l(view) != null && view.isShown() && view.getWindowVisibility() == 0;
            if (view.getAccessibilityLiveRegion() != 0 || z7) {
                AccessibilityEvent obtain = AccessibilityEvent.obtain();
                obtain.setEventType(z7 ? 32 : 2048);
                obtain.setContentChangeTypes(i8);
                if (z7) {
                    obtain.getText().add(l(view));
                    U(view);
                }
                view.sendAccessibilityEventUnchecked(obtain);
            } else if (i8 == 32) {
                AccessibilityEvent obtain2 = AccessibilityEvent.obtain();
                view.onInitializeAccessibilityEvent(obtain2);
                obtain2.setEventType(32);
                obtain2.setContentChangeTypes(i8);
                obtain2.setSource(view);
                view.onPopulateAccessibilityEvent(obtain2);
                obtain2.getText().add(l(view));
                accessibilityManager.sendAccessibilityEvent(obtain2);
            } else if (view.getParent() != null) {
                try {
                    view.getParent().notifySubtreeAccessibilityStateChanged(view, view, i8);
                } catch (AbstractMethodError e8) {
                    Log.e("ViewCompat", view.getParent().getClass().getSimpleName() + " does not fully implement ViewParent", e8);
                }
            }
        }
    }

    public static void E(View view, int i8) {
        view.offsetTopAndBottom(i8);
    }

    public static b1 F(View view, b1 b1Var) {
        WindowInsets t8 = b1Var.t();
        if (t8 != null) {
            WindowInsets b8 = g.b(view, t8);
            if (!b8.equals(t8)) {
                return b1.v(b8, view);
            }
        }
        return b1Var;
    }

    private static f G() {
        return new b(c1.b.K, CharSequence.class, 8, 28);
    }

    public static androidx.core.view.d H(View view, androidx.core.view.d dVar) {
        if (Log.isLoggable("ViewCompat", 3)) {
            Log.d("ViewCompat", "performReceiveContent: " + dVar + ", view=" + view.getClass().getSimpleName() + "[" + view.getId() + "]");
        }
        if (Build.VERSION.SDK_INT >= 31) {
            return m.b(view, dVar);
        }
        i0 i0Var = (i0) view.getTag(c1.b.M);
        if (i0Var != null) {
            androidx.core.view.d a8 = i0Var.a(view, dVar);
            if (a8 == null) {
                return null;
            }
            return o(view).a(a8);
        }
        return o(view).a(dVar);
    }

    public static void I(View view, Runnable runnable) {
        view.postOnAnimation(runnable);
    }

    public static void J(View view, Runnable runnable, long j8) {
        view.postOnAnimationDelayed(runnable, j8);
    }

    public static void K(View view) {
        g.c(view);
    }

    public static void L(View view, Context context, int[] iArr, AttributeSet attributeSet, TypedArray typedArray, int i8, int i9) {
        if (Build.VERSION.SDK_INT >= 29) {
            k.d(view, context, iArr, attributeSet, typedArray, i8, i9);
        }
    }

    private static f M() {
        return new a(c1.b.O, Boolean.class, 28);
    }

    public static void N(View view, androidx.core.view.a aVar) {
        if (aVar == null && (j(view) instanceof a.C0031a)) {
            aVar = new androidx.core.view.a();
        }
        U(view);
        view.setAccessibilityDelegate(aVar == null ? null : aVar.d());
    }

    public static void O(View view, boolean z7) {
        b().f(view, Boolean.valueOf(z7));
    }

    public static void P(View view, CharSequence charSequence) {
        G().f(view, charSequence);
        if (charSequence != null) {
            f3163f.a(view);
        } else {
            f3163f.d(view);
        }
    }

    public static void Q(View view, Drawable drawable) {
        view.setBackground(drawable);
    }

    public static void R(View view, ColorStateList colorStateList) {
        h.q(view, colorStateList);
    }

    public static void S(View view, PorterDuff.Mode mode) {
        h.r(view, mode);
    }

    public static void T(View view, float f8) {
        h.s(view, f8);
    }

    private static void U(View view) {
        if (view.getImportantForAccessibility() == 0) {
            view.setImportantForAccessibility(1);
        }
    }

    public static void V(View view, h0 h0Var) {
        h.u(view, h0Var);
    }

    public static void W(View view, boolean z7) {
        M().f(view, Boolean.valueOf(z7));
    }

    public static void X(View view, int i8, int i9) {
        i.d(view, i8, i9);
    }

    public static void Y(View view, String str) {
        h.v(view, str);
    }

    private static f Z() {
        return new c(c1.b.P, CharSequence.class, 64, 30);
    }

    public static void a0(View view) {
        h.z(view);
    }

    private static f b() {
        return new d(c1.b.J, Boolean.class, 28);
    }

    public static x0 c(View view) {
        if (f3158a == null) {
            f3158a = new WeakHashMap();
        }
        x0 x0Var = (x0) f3158a.get(view);
        if (x0Var == null) {
            x0 x0Var2 = new x0(view);
            f3158a.put(view, x0Var2);
            return x0Var2;
        }
        return x0Var;
    }

    public static b1 d(View view, b1 b1Var, Rect rect) {
        return h.b(view, b1Var, rect);
    }

    public static b1 e(View view, b1 b1Var) {
        WindowInsets t8 = b1Var.t();
        if (t8 != null) {
            WindowInsets a8 = g.a(view, t8);
            if (!a8.equals(t8)) {
                return b1.v(a8, view);
            }
        }
        return b1Var;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean f(View view, KeyEvent keyEvent) {
        if (Build.VERSION.SDK_INT >= 28) {
            return false;
        }
        return p.a(view).b(view, keyEvent);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean g(View view, KeyEvent keyEvent) {
        if (Build.VERSION.SDK_INT >= 28) {
            return false;
        }
        return p.a(view).f(keyEvent);
    }

    static void h(View view) {
        androidx.core.view.a i8 = i(view);
        if (i8 == null) {
            i8 = new androidx.core.view.a();
        }
        N(view, i8);
    }

    public static androidx.core.view.a i(View view) {
        View.AccessibilityDelegate j8 = j(view);
        if (j8 == null) {
            return null;
        }
        return j8 instanceof a.C0031a ? ((a.C0031a) j8).f3070a : new androidx.core.view.a(j8);
    }

    private static View.AccessibilityDelegate j(View view) {
        return Build.VERSION.SDK_INT >= 29 ? k.a(view) : k(view);
    }

    private static View.AccessibilityDelegate k(View view) {
        if (f3160c) {
            return null;
        }
        if (f3159b == null) {
            try {
                Field declaredField = View.class.getDeclaredField("mAccessibilityDelegate");
                f3159b = declaredField;
                declaredField.setAccessible(true);
            } catch (Throwable unused) {
                f3160c = true;
                return null;
            }
        }
        try {
            Object obj = f3159b.get(view);
            if (obj instanceof View.AccessibilityDelegate) {
                return (View.AccessibilityDelegate) obj;
            }
            return null;
        } catch (Throwable unused2) {
            f3160c = true;
            return null;
        }
    }

    public static CharSequence l(View view) {
        return (CharSequence) G().e(view);
    }

    public static ColorStateList m(View view) {
        return h.g(view);
    }

    public static PorterDuff.Mode n(View view) {
        return h.h(view);
    }

    private static j0 o(View view) {
        return view instanceof j0 ? (j0) view : f3162e;
    }

    public static int p(View view) {
        return view.getLayoutDirection();
    }

    public static int q(View view) {
        return view.getMinimumHeight();
    }

    public static String[] r(View view) {
        return Build.VERSION.SDK_INT >= 31 ? m.a(view) : (String[]) view.getTag(c1.b.N);
    }

    public static b1 s(View view) {
        return i.a(view);
    }

    public static CharSequence t(View view) {
        return (CharSequence) Z().e(view);
    }

    public static String u(View view) {
        return h.k(view);
    }

    public static int v(View view) {
        return view.getWindowSystemUiVisibility();
    }

    public static boolean w(View view) {
        return view.hasOnClickListeners();
    }

    public static boolean x(View view) {
        Boolean bool = (Boolean) b().e(view);
        return bool != null && bool.booleanValue();
    }

    public static boolean y(View view) {
        return view.isAttachedToWindow();
    }

    public static boolean z(View view) {
        return view.isLaidOut();
    }
}
