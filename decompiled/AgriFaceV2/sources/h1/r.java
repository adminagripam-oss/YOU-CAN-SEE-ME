package h1;

import android.graphics.Rect;
import android.os.Build;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.ClickableSpan;
import android.view.View;
import android.view.accessibility.AccessibilityNodeInfo;
import h1.u;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
/* loaded from: classes.dex */
public class r {

    /* renamed from: a  reason: collision with root package name */
    private final AccessibilityNodeInfo f7139a;

    /* renamed from: b  reason: collision with root package name */
    public int f7140b = -1;

    /* renamed from: c  reason: collision with root package name */
    private int f7141c = -1;

    /* loaded from: classes.dex */
    public static class a {
        public static final a A;
        public static final a B;
        public static final a C;
        public static final a D;
        public static final a E;
        public static final a F;
        public static final a G;
        public static final a H;
        public static final a I;
        public static final a J;
        public static final a K;
        public static final a L;
        public static final a M;
        public static final a N;
        public static final a O;
        public static final a P;
        public static final a Q;
        public static final a R;
        public static final a S;
        public static final a T;
        public static final a U;

        /* renamed from: d  reason: collision with root package name */
        public static final a f7142d = new a(1, null);

        /* renamed from: e  reason: collision with root package name */
        public static final a f7143e = new a(2, null);

        /* renamed from: f  reason: collision with root package name */
        public static final a f7144f = new a(4, null);

        /* renamed from: g  reason: collision with root package name */
        public static final a f7145g = new a(8, null);

        /* renamed from: h  reason: collision with root package name */
        public static final a f7146h = new a(16, null);

        /* renamed from: i  reason: collision with root package name */
        public static final a f7147i = new a(32, null);

        /* renamed from: j  reason: collision with root package name */
        public static final a f7148j = new a(64, null);

        /* renamed from: k  reason: collision with root package name */
        public static final a f7149k = new a(128, null);

        /* renamed from: l  reason: collision with root package name */
        public static final a f7150l = new a(256, null, u.b.class);

        /* renamed from: m  reason: collision with root package name */
        public static final a f7151m = new a(512, null, u.b.class);

        /* renamed from: n  reason: collision with root package name */
        public static final a f7152n = new a(1024, null, u.c.class);

        /* renamed from: o  reason: collision with root package name */
        public static final a f7153o = new a(2048, null, u.c.class);

        /* renamed from: p  reason: collision with root package name */
        public static final a f7154p = new a(4096, null);

        /* renamed from: q  reason: collision with root package name */
        public static final a f7155q = new a(8192, null);

        /* renamed from: r  reason: collision with root package name */
        public static final a f7156r = new a(16384, null);

        /* renamed from: s  reason: collision with root package name */
        public static final a f7157s = new a(32768, null);

        /* renamed from: t  reason: collision with root package name */
        public static final a f7158t = new a(65536, null);

        /* renamed from: u  reason: collision with root package name */
        public static final a f7159u = new a(131072, null, u.g.class);

        /* renamed from: v  reason: collision with root package name */
        public static final a f7160v = new a(262144, null);

        /* renamed from: w  reason: collision with root package name */
        public static final a f7161w = new a(524288, null);

        /* renamed from: x  reason: collision with root package name */
        public static final a f7162x = new a(1048576, null);

        /* renamed from: y  reason: collision with root package name */
        public static final a f7163y = new a(2097152, null, u.h.class);

        /* renamed from: z  reason: collision with root package name */
        public static final a f7164z;

        /* renamed from: a  reason: collision with root package name */
        final Object f7165a;

        /* renamed from: b  reason: collision with root package name */
        private final int f7166b;

        /* renamed from: c  reason: collision with root package name */
        private final Class f7167c;

        static {
            AccessibilityNodeInfo.AccessibilityAction accessibilityAction;
            AccessibilityNodeInfo.AccessibilityAction accessibilityAction2;
            AccessibilityNodeInfo.AccessibilityAction accessibilityAction3;
            AccessibilityNodeInfo.AccessibilityAction accessibilityAction4;
            AccessibilityNodeInfo.AccessibilityAction accessibilityAction5;
            AccessibilityNodeInfo.AccessibilityAction accessibilityAction6;
            AccessibilityNodeInfo.AccessibilityAction accessibilityAction7;
            AccessibilityNodeInfo.AccessibilityAction accessibilityAction8;
            AccessibilityNodeInfo.AccessibilityAction accessibilityAction9;
            AccessibilityNodeInfo.AccessibilityAction accessibilityAction10;
            AccessibilityNodeInfo.AccessibilityAction accessibilityAction11;
            AccessibilityNodeInfo.AccessibilityAction accessibilityAction12;
            AccessibilityNodeInfo.AccessibilityAction accessibilityAction13;
            AccessibilityNodeInfo.AccessibilityAction accessibilityAction14;
            AccessibilityNodeInfo.AccessibilityAction accessibilityAction15;
            AccessibilityNodeInfo.AccessibilityAction accessibilityAction16;
            AccessibilityNodeInfo.AccessibilityAction accessibilityAction17;
            AccessibilityNodeInfo.AccessibilityAction accessibilityAction18;
            AccessibilityNodeInfo.AccessibilityAction accessibilityAction19;
            AccessibilityNodeInfo.AccessibilityAction accessibilityAction20;
            AccessibilityNodeInfo.AccessibilityAction accessibilityAction21;
            AccessibilityNodeInfo.AccessibilityAction accessibilityAction22;
            AccessibilityNodeInfo.AccessibilityAction accessibilityAction23;
            AccessibilityNodeInfo.AccessibilityAction accessibilityAction24;
            int i8 = Build.VERSION.SDK_INT;
            f7164z = new a(AccessibilityNodeInfo.AccessibilityAction.ACTION_SHOW_ON_SCREEN, 16908342, null, null, null);
            A = new a(AccessibilityNodeInfo.AccessibilityAction.ACTION_SCROLL_TO_POSITION, 16908343, null, null, u.e.class);
            B = new a(AccessibilityNodeInfo.AccessibilityAction.ACTION_SCROLL_UP, 16908344, null, null, null);
            C = new a(AccessibilityNodeInfo.AccessibilityAction.ACTION_SCROLL_LEFT, 16908345, null, null, null);
            D = new a(AccessibilityNodeInfo.AccessibilityAction.ACTION_SCROLL_DOWN, 16908346, null, null, null);
            E = new a(AccessibilityNodeInfo.AccessibilityAction.ACTION_SCROLL_RIGHT, 16908347, null, null, null);
            if (i8 >= 29) {
                accessibilityAction24 = AccessibilityNodeInfo.AccessibilityAction.ACTION_PAGE_UP;
                accessibilityAction = accessibilityAction24;
            } else {
                accessibilityAction = null;
            }
            F = new a(accessibilityAction, 16908358, null, null, null);
            if (i8 >= 29) {
                accessibilityAction23 = AccessibilityNodeInfo.AccessibilityAction.ACTION_PAGE_DOWN;
                accessibilityAction2 = accessibilityAction23;
            } else {
                accessibilityAction2 = null;
            }
            G = new a(accessibilityAction2, 16908359, null, null, null);
            if (i8 >= 29) {
                accessibilityAction22 = AccessibilityNodeInfo.AccessibilityAction.ACTION_PAGE_LEFT;
                accessibilityAction3 = accessibilityAction22;
            } else {
                accessibilityAction3 = null;
            }
            H = new a(accessibilityAction3, 16908360, null, null, null);
            if (i8 >= 29) {
                accessibilityAction21 = AccessibilityNodeInfo.AccessibilityAction.ACTION_PAGE_RIGHT;
                accessibilityAction4 = accessibilityAction21;
            } else {
                accessibilityAction4 = null;
            }
            I = new a(accessibilityAction4, 16908361, null, null, null);
            J = new a(AccessibilityNodeInfo.AccessibilityAction.ACTION_CONTEXT_CLICK, 16908348, null, null, null);
            K = new a(AccessibilityNodeInfo.AccessibilityAction.ACTION_SET_PROGRESS, 16908349, null, null, u.f.class);
            L = new a(AccessibilityNodeInfo.AccessibilityAction.ACTION_MOVE_WINDOW, 16908354, null, null, u.d.class);
            if (i8 >= 28) {
                accessibilityAction20 = AccessibilityNodeInfo.AccessibilityAction.ACTION_SHOW_TOOLTIP;
                accessibilityAction5 = accessibilityAction20;
            } else {
                accessibilityAction5 = null;
            }
            M = new a(accessibilityAction5, 16908356, null, null, null);
            if (i8 >= 28) {
                accessibilityAction19 = AccessibilityNodeInfo.AccessibilityAction.ACTION_HIDE_TOOLTIP;
                accessibilityAction6 = accessibilityAction19;
            } else {
                accessibilityAction6 = null;
            }
            N = new a(accessibilityAction6, 16908357, null, null, null);
            if (i8 >= 30) {
                accessibilityAction18 = AccessibilityNodeInfo.AccessibilityAction.ACTION_PRESS_AND_HOLD;
                accessibilityAction7 = accessibilityAction18;
            } else {
                accessibilityAction7 = null;
            }
            O = new a(accessibilityAction7, 16908362, null, null, null);
            if (i8 >= 30) {
                accessibilityAction17 = AccessibilityNodeInfo.AccessibilityAction.ACTION_IME_ENTER;
                accessibilityAction8 = accessibilityAction17;
            } else {
                accessibilityAction8 = null;
            }
            P = new a(accessibilityAction8, 16908372, null, null, null);
            if (i8 >= 32) {
                accessibilityAction16 = AccessibilityNodeInfo.AccessibilityAction.ACTION_DRAG_START;
                accessibilityAction9 = accessibilityAction16;
            } else {
                accessibilityAction9 = null;
            }
            Q = new a(accessibilityAction9, 16908373, null, null, null);
            if (i8 >= 32) {
                accessibilityAction15 = AccessibilityNodeInfo.AccessibilityAction.ACTION_DRAG_DROP;
                accessibilityAction10 = accessibilityAction15;
            } else {
                accessibilityAction10 = null;
            }
            R = new a(accessibilityAction10, 16908374, null, null, null);
            if (i8 >= 32) {
                accessibilityAction14 = AccessibilityNodeInfo.AccessibilityAction.ACTION_DRAG_CANCEL;
                accessibilityAction11 = accessibilityAction14;
            } else {
                accessibilityAction11 = null;
            }
            S = new a(accessibilityAction11, 16908375, null, null, null);
            if (i8 >= 33) {
                accessibilityAction13 = AccessibilityNodeInfo.AccessibilityAction.ACTION_SHOW_TEXT_SUGGESTIONS;
                accessibilityAction12 = accessibilityAction13;
            } else {
                accessibilityAction12 = null;
            }
            T = new a(accessibilityAction12, 16908376, null, null, null);
            U = new a(i8 >= 34 ? d.a() : null, 16908382, null, null, null);
        }

        public a(int i8, CharSequence charSequence) {
            this(null, i8, charSequence, null, null);
        }

        public int a() {
            return ((AccessibilityNodeInfo.AccessibilityAction) this.f7165a).getId();
        }

        public CharSequence b() {
            return ((AccessibilityNodeInfo.AccessibilityAction) this.f7165a).getLabel();
        }

        public boolean c(View view, Bundle bundle) {
            return false;
        }

        public boolean equals(Object obj) {
            if (obj != null && (obj instanceof a)) {
                Object obj2 = this.f7165a;
                Object obj3 = ((a) obj).f7165a;
                return obj2 == null ? obj3 == null : obj2.equals(obj3);
            }
            return false;
        }

        public int hashCode() {
            Object obj = this.f7165a;
            if (obj != null) {
                return obj.hashCode();
            }
            return 0;
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("AccessibilityActionCompat: ");
            String e8 = r.e(this.f7166b);
            if (e8.equals("ACTION_UNKNOWN") && b() != null) {
                e8 = b().toString();
            }
            sb.append(e8);
            return sb.toString();
        }

        private a(int i8, CharSequence charSequence, Class cls) {
            this(null, i8, charSequence, null, cls);
        }

        a(Object obj) {
            this(obj, 0, null, null, null);
        }

        a(Object obj, int i8, CharSequence charSequence, u uVar, Class cls) {
            this.f7166b = i8;
            this.f7165a = obj == null ? new AccessibilityNodeInfo.AccessibilityAction(i8, charSequence) : obj;
            this.f7167c = cls;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class b {
        public static Object a(int i8, float f8, float f9, float f10) {
            return new AccessibilityNodeInfo.RangeInfo(i8, f8, f9, f10);
        }

        public static CharSequence b(AccessibilityNodeInfo accessibilityNodeInfo) {
            return accessibilityNodeInfo.getStateDescription();
        }

        public static void c(AccessibilityNodeInfo accessibilityNodeInfo, CharSequence charSequence) {
            accessibilityNodeInfo.setStateDescription(charSequence);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class c {
        public static e a(boolean z7, int i8, int i9, int i10, int i11, boolean z8, String str, String str2) {
            return new e(new AccessibilityNodeInfo.CollectionItemInfo.Builder().setHeading(z7).setColumnIndex(i8).setRowIndex(i9).setColumnSpan(i10).setRowSpan(i11).setSelected(z8).setRowTitle(str).setColumnTitle(str2).build());
        }

        public static r b(AccessibilityNodeInfo accessibilityNodeInfo, int i8, int i9) {
            return r.X(accessibilityNodeInfo.getChild(i8, i9));
        }

        public static String c(Object obj) {
            return ((AccessibilityNodeInfo.CollectionItemInfo) obj).getColumnTitle();
        }

        public static String d(Object obj) {
            return ((AccessibilityNodeInfo.CollectionItemInfo) obj).getRowTitle();
        }

        public static AccessibilityNodeInfo.ExtraRenderingInfo e(AccessibilityNodeInfo accessibilityNodeInfo) {
            return accessibilityNodeInfo.getExtraRenderingInfo();
        }

        public static r f(AccessibilityNodeInfo accessibilityNodeInfo, int i8) {
            return r.X(accessibilityNodeInfo.getParent(i8));
        }

        public static String g(AccessibilityNodeInfo accessibilityNodeInfo) {
            return accessibilityNodeInfo.getUniqueId();
        }

        public static boolean h(AccessibilityNodeInfo accessibilityNodeInfo) {
            return accessibilityNodeInfo.isTextSelectable();
        }

        public static void i(AccessibilityNodeInfo accessibilityNodeInfo, boolean z7) {
            accessibilityNodeInfo.setTextSelectable(z7);
        }

        public static void j(AccessibilityNodeInfo accessibilityNodeInfo, String str) {
            accessibilityNodeInfo.setUniqueId(str);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class d {
        public static AccessibilityNodeInfo.AccessibilityAction a() {
            return AccessibilityNodeInfo.AccessibilityAction.ACTION_SCROLL_IN_DIRECTION;
        }

        public static void b(AccessibilityNodeInfo accessibilityNodeInfo, Rect rect) {
            accessibilityNodeInfo.getBoundsInWindow(rect);
        }

        public static CharSequence c(AccessibilityNodeInfo accessibilityNodeInfo) {
            return accessibilityNodeInfo.getContainerTitle();
        }

        public static long d(AccessibilityNodeInfo accessibilityNodeInfo) {
            return accessibilityNodeInfo.getMinDurationBetweenContentChanges().toMillis();
        }

        public static boolean e(AccessibilityNodeInfo accessibilityNodeInfo) {
            return accessibilityNodeInfo.hasRequestInitialAccessibilityFocus();
        }

        public static boolean f(AccessibilityNodeInfo accessibilityNodeInfo) {
            return accessibilityNodeInfo.isAccessibilityDataSensitive();
        }

        public static void g(AccessibilityNodeInfo accessibilityNodeInfo, boolean z7) {
            accessibilityNodeInfo.setAccessibilityDataSensitive(z7);
        }

        public static void h(AccessibilityNodeInfo accessibilityNodeInfo, Rect rect) {
            accessibilityNodeInfo.setBoundsInWindow(rect);
        }

        public static void i(AccessibilityNodeInfo accessibilityNodeInfo, CharSequence charSequence) {
            accessibilityNodeInfo.setContainerTitle(charSequence);
        }

        public static void j(AccessibilityNodeInfo accessibilityNodeInfo, long j8) {
            accessibilityNodeInfo.setMinDurationBetweenContentChanges(Duration.ofMillis(j8));
        }

        public static void k(AccessibilityNodeInfo accessibilityNodeInfo, View view, boolean z7) {
            accessibilityNodeInfo.setQueryFromAppProcessEnabled(view, z7);
        }

        public static void l(AccessibilityNodeInfo accessibilityNodeInfo, boolean z7) {
            accessibilityNodeInfo.setRequestInitialAccessibilityFocus(z7);
        }
    }

    /* loaded from: classes.dex */
    public static class e {

        /* renamed from: a  reason: collision with root package name */
        final Object f7168a;

        e(Object obj) {
            this.f7168a = obj;
        }
    }

    private r(AccessibilityNodeInfo accessibilityNodeInfo) {
        this.f7139a = accessibilityNodeInfo;
    }

    private void O(int i8, boolean z7) {
        Bundle o8 = o();
        if (o8 != null) {
            int i9 = o8.getInt("androidx.view.accessibility.AccessibilityNodeInfoCompat.BOOLEAN_PROPERTY_KEY", 0) & (~i8);
            if (!z7) {
                i8 = 0;
            }
            o8.putInt("androidx.view.accessibility.AccessibilityNodeInfoCompat.BOOLEAN_PROPERTY_KEY", i8 | i9);
        }
    }

    public static r W(AccessibilityNodeInfo accessibilityNodeInfo) {
        return new r(accessibilityNodeInfo);
    }

    static r X(Object obj) {
        if (obj != null) {
            return new r(obj);
        }
        return null;
    }

    private List c(String str) {
        ArrayList<Integer> integerArrayList = this.f7139a.getExtras().getIntegerArrayList(str);
        if (integerArrayList == null) {
            ArrayList<Integer> arrayList = new ArrayList<>();
            this.f7139a.getExtras().putIntegerArrayList(str, arrayList);
            return arrayList;
        }
        return integerArrayList;
    }

    static String e(int i8) {
        if (i8 != 1) {
            if (i8 != 2) {
                switch (i8) {
                    case 4:
                        return "ACTION_SELECT";
                    case 8:
                        return "ACTION_CLEAR_SELECTION";
                    case 16:
                        return "ACTION_CLICK";
                    case 32:
                        return "ACTION_LONG_CLICK";
                    case 64:
                        return "ACTION_ACCESSIBILITY_FOCUS";
                    case 128:
                        return "ACTION_CLEAR_ACCESSIBILITY_FOCUS";
                    case 256:
                        return "ACTION_NEXT_AT_MOVEMENT_GRANULARITY";
                    case 512:
                        return "ACTION_PREVIOUS_AT_MOVEMENT_GRANULARITY";
                    case 1024:
                        return "ACTION_NEXT_HTML_ELEMENT";
                    case 2048:
                        return "ACTION_PREVIOUS_HTML_ELEMENT";
                    case 4096:
                        return "ACTION_SCROLL_FORWARD";
                    case 8192:
                        return "ACTION_SCROLL_BACKWARD";
                    case 16384:
                        return "ACTION_COPY";
                    case 32768:
                        return "ACTION_PASTE";
                    case 65536:
                        return "ACTION_CUT";
                    case 131072:
                        return "ACTION_SET_SELECTION";
                    case 262144:
                        return "ACTION_EXPAND";
                    case 524288:
                        return "ACTION_COLLAPSE";
                    case 2097152:
                        return "ACTION_SET_TEXT";
                    case 16908354:
                        return "ACTION_MOVE_WINDOW";
                    case 16908382:
                        return "ACTION_SCROLL_IN_DIRECTION";
                    default:
                        switch (i8) {
                            case 16908342:
                                return "ACTION_SHOW_ON_SCREEN";
                            case 16908343:
                                return "ACTION_SCROLL_TO_POSITION";
                            case 16908344:
                                return "ACTION_SCROLL_UP";
                            case 16908345:
                                return "ACTION_SCROLL_LEFT";
                            case 16908346:
                                return "ACTION_SCROLL_DOWN";
                            case 16908347:
                                return "ACTION_SCROLL_RIGHT";
                            case 16908348:
                                return "ACTION_CONTEXT_CLICK";
                            case 16908349:
                                return "ACTION_SET_PROGRESS";
                            default:
                                switch (i8) {
                                    case 16908356:
                                        return "ACTION_SHOW_TOOLTIP";
                                    case 16908357:
                                        return "ACTION_HIDE_TOOLTIP";
                                    case 16908358:
                                        return "ACTION_PAGE_UP";
                                    case 16908359:
                                        return "ACTION_PAGE_DOWN";
                                    case 16908360:
                                        return "ACTION_PAGE_LEFT";
                                    case 16908361:
                                        return "ACTION_PAGE_RIGHT";
                                    case 16908362:
                                        return "ACTION_PRESS_AND_HOLD";
                                    default:
                                        switch (i8) {
                                            case 16908372:
                                                return "ACTION_IME_ENTER";
                                            case 16908373:
                                                return "ACTION_DRAG_START";
                                            case 16908374:
                                                return "ACTION_DRAG_DROP";
                                            case 16908375:
                                                return "ACTION_DRAG_CANCEL";
                                            default:
                                                return "ACTION_UNKNOWN";
                                        }
                                }
                        }
                }
            }
            return "ACTION_CLEAR_FOCUS";
        }
        return "ACTION_FOCUS";
    }

    private boolean f(int i8) {
        Bundle o8 = o();
        return o8 != null && (o8.getInt("androidx.view.accessibility.AccessibilityNodeInfoCompat.BOOLEAN_PROPERTY_KEY", 0) & i8) == i8;
    }

    public static ClickableSpan[] k(CharSequence charSequence) {
        if (charSequence instanceof Spanned) {
            return (ClickableSpan[]) ((Spanned) charSequence).getSpans(0, charSequence.length(), ClickableSpan.class);
        }
        return null;
    }

    private boolean w() {
        return !c("androidx.view.accessibility.AccessibilityNodeInfoCompat.SPANS_START_KEY").isEmpty();
    }

    public boolean A() {
        return this.f7139a.isClickable();
    }

    public boolean B() {
        return this.f7139a.isContextClickable();
    }

    public boolean C() {
        return this.f7139a.isEnabled();
    }

    public boolean D() {
        return this.f7139a.isFocusable();
    }

    public boolean E() {
        return this.f7139a.isFocused();
    }

    public boolean F() {
        return f(67108864);
    }

    public boolean G() {
        return this.f7139a.isImportantForAccessibility();
    }

    public boolean H() {
        return this.f7139a.isLongClickable();
    }

    public boolean I() {
        return this.f7139a.isPassword();
    }

    public boolean J() {
        return this.f7139a.isScrollable();
    }

    public boolean K() {
        return this.f7139a.isSelected();
    }

    public boolean L() {
        return Build.VERSION.SDK_INT >= 33 ? c.h(this.f7139a) : f(8388608);
    }

    public boolean M() {
        return this.f7139a.isVisibleToUser();
    }

    public boolean N(int i8, Bundle bundle) {
        return this.f7139a.performAction(i8, bundle);
    }

    public void P(CharSequence charSequence) {
        this.f7139a.setClassName(charSequence);
    }

    public void Q(boolean z7) {
        if (Build.VERSION.SDK_INT >= 28) {
            this.f7139a.setHeading(z7);
        } else {
            O(2, z7);
        }
    }

    public void R(CharSequence charSequence) {
        if (Build.VERSION.SDK_INT >= 28) {
            this.f7139a.setPaneTitle(charSequence);
        } else {
            this.f7139a.getExtras().putCharSequence("androidx.view.accessibility.AccessibilityNodeInfoCompat.PANE_TITLE_KEY", charSequence);
        }
    }

    public void S(boolean z7) {
        if (Build.VERSION.SDK_INT >= 28) {
            this.f7139a.setScreenReaderFocusable(z7);
        } else {
            O(1, z7);
        }
    }

    public void T(boolean z7) {
        this.f7139a.setScrollable(z7);
    }

    public void U(CharSequence charSequence) {
        if (Build.VERSION.SDK_INT >= 30) {
            b.c(this.f7139a, charSequence);
        } else {
            this.f7139a.getExtras().putCharSequence("androidx.view.accessibility.AccessibilityNodeInfoCompat.STATE_DESCRIPTION_KEY", charSequence);
        }
    }

    public AccessibilityNodeInfo V() {
        return this.f7139a;
    }

    public void a(a aVar) {
        this.f7139a.addAction((AccessibilityNodeInfo.AccessibilityAction) aVar.f7165a);
    }

    public void b(CharSequence charSequence, View view) {
    }

    public List d() {
        List<AccessibilityNodeInfo.AccessibilityAction> actionList = this.f7139a.getActionList();
        if (actionList != null) {
            ArrayList arrayList = new ArrayList();
            int size = actionList.size();
            for (int i8 = 0; i8 < size; i8++) {
                arrayList.add(new a(actionList.get(i8)));
            }
            return arrayList;
        }
        return Collections.emptyList();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && (obj instanceof r)) {
            r rVar = (r) obj;
            AccessibilityNodeInfo accessibilityNodeInfo = this.f7139a;
            if (accessibilityNodeInfo == null) {
                if (rVar.f7139a != null) {
                    return false;
                }
            } else if (!accessibilityNodeInfo.equals(rVar.f7139a)) {
                return false;
            }
            return this.f7141c == rVar.f7141c && this.f7140b == rVar.f7140b;
        }
        return false;
    }

    public void g(Rect rect) {
        this.f7139a.getBoundsInParent(rect);
    }

    public void h(Rect rect) {
        this.f7139a.getBoundsInScreen(rect);
    }

    public int hashCode() {
        AccessibilityNodeInfo accessibilityNodeInfo = this.f7139a;
        if (accessibilityNodeInfo == null) {
            return 0;
        }
        return accessibilityNodeInfo.hashCode();
    }

    public void i(Rect rect) {
        if (Build.VERSION.SDK_INT >= 34) {
            d.b(this.f7139a, rect);
            return;
        }
        Rect rect2 = (Rect) this.f7139a.getExtras().getParcelable("androidx.view.accessibility.AccessibilityNodeInfoCompat.BOUNDS_IN_WINDOW_KEY");
        if (rect2 != null) {
            rect.set(rect2.left, rect2.top, rect2.right, rect2.bottom);
        }
    }

    public CharSequence j() {
        return this.f7139a.getClassName();
    }

    public CharSequence l() {
        return Build.VERSION.SDK_INT >= 34 ? d.c(this.f7139a) : this.f7139a.getExtras().getCharSequence("androidx.view.accessibility.AccessibilityNodeInfoCompat.CONTAINER_TITLE_KEY");
    }

    public CharSequence m() {
        return this.f7139a.getContentDescription();
    }

    public CharSequence n() {
        return this.f7139a.getError();
    }

    public Bundle o() {
        return this.f7139a.getExtras();
    }

    public int p() {
        return this.f7139a.getMaxTextLength();
    }

    public CharSequence q() {
        return this.f7139a.getPackageName();
    }

    public CharSequence r() {
        return Build.VERSION.SDK_INT >= 30 ? b.b(this.f7139a) : this.f7139a.getExtras().getCharSequence("androidx.view.accessibility.AccessibilityNodeInfoCompat.STATE_DESCRIPTION_KEY");
    }

    public CharSequence s() {
        if (w()) {
            List c8 = c("androidx.view.accessibility.AccessibilityNodeInfoCompat.SPANS_START_KEY");
            List c9 = c("androidx.view.accessibility.AccessibilityNodeInfoCompat.SPANS_END_KEY");
            List c10 = c("androidx.view.accessibility.AccessibilityNodeInfoCompat.SPANS_FLAGS_KEY");
            List c11 = c("androidx.view.accessibility.AccessibilityNodeInfoCompat.SPANS_ID_KEY");
            SpannableString spannableString = new SpannableString(TextUtils.substring(this.f7139a.getText(), 0, this.f7139a.getText().length()));
            for (int i8 = 0; i8 < c8.size(); i8++) {
                spannableString.setSpan(new h1.a(((Integer) c11.get(i8)).intValue(), this, o().getInt("androidx.view.accessibility.AccessibilityNodeInfoCompat.SPANS_ACTION_ID_KEY")), ((Integer) c8.get(i8)).intValue(), ((Integer) c9.get(i8)).intValue(), ((Integer) c10.get(i8)).intValue());
            }
            return spannableString;
        }
        return this.f7139a.getText();
    }

    public CharSequence t() {
        CharSequence tooltipText;
        if (Build.VERSION.SDK_INT >= 28) {
            tooltipText = this.f7139a.getTooltipText();
            return tooltipText;
        }
        return this.f7139a.getExtras().getCharSequence("androidx.view.accessibility.AccessibilityNodeInfoCompat.TOOLTIP_TEXT_KEY");
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString());
        Rect rect = new Rect();
        g(rect);
        sb.append("; boundsInParent: " + rect);
        h(rect);
        sb.append("; boundsInScreen: " + rect);
        i(rect);
        sb.append("; boundsInWindow: " + rect);
        sb.append("; packageName: ");
        sb.append(q());
        sb.append("; className: ");
        sb.append(j());
        sb.append("; text: ");
        sb.append(s());
        sb.append("; error: ");
        sb.append(n());
        sb.append("; maxTextLength: ");
        sb.append(p());
        sb.append("; stateDescription: ");
        sb.append(r());
        sb.append("; contentDescription: ");
        sb.append(m());
        sb.append("; tooltipText: ");
        sb.append(t());
        sb.append("; viewIdResName: ");
        sb.append(v());
        sb.append("; uniqueId: ");
        sb.append(u());
        sb.append("; checkable: ");
        sb.append(y());
        sb.append("; checked: ");
        sb.append(z());
        sb.append("; focusable: ");
        sb.append(D());
        sb.append("; focused: ");
        sb.append(E());
        sb.append("; selected: ");
        sb.append(K());
        sb.append("; clickable: ");
        sb.append(A());
        sb.append("; longClickable: ");
        sb.append(H());
        sb.append("; contextClickable: ");
        sb.append(B());
        sb.append("; enabled: ");
        sb.append(C());
        sb.append("; password: ");
        sb.append(I());
        sb.append("; scrollable: " + J());
        sb.append("; containerTitle: ");
        sb.append(l());
        sb.append("; granularScrollingSupported: ");
        sb.append(F());
        sb.append("; importantForAccessibility: ");
        sb.append(G());
        sb.append("; visible: ");
        sb.append(M());
        sb.append("; isTextSelectable: ");
        sb.append(L());
        sb.append("; accessibilityDataSensitive: ");
        sb.append(x());
        sb.append("; [");
        List d8 = d();
        for (int i8 = 0; i8 < d8.size(); i8++) {
            a aVar = (a) d8.get(i8);
            String e8 = e(aVar.a());
            if (e8.equals("ACTION_UNKNOWN") && aVar.b() != null) {
                e8 = aVar.b().toString();
            }
            sb.append(e8);
            if (i8 != d8.size() - 1) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }

    public String u() {
        return Build.VERSION.SDK_INT >= 33 ? c.g(this.f7139a) : this.f7139a.getExtras().getString("androidx.view.accessibility.AccessibilityNodeInfoCompat.UNIQUE_ID_KEY");
    }

    public String v() {
        return this.f7139a.getViewIdResourceName();
    }

    public boolean x() {
        return Build.VERSION.SDK_INT >= 34 ? d.f(this.f7139a) : f(64);
    }

    public boolean y() {
        return this.f7139a.isCheckable();
    }

    public boolean z() {
        return this.f7139a.isChecked();
    }

    public r(Object obj) {
        this.f7139a = (AccessibilityNodeInfo) obj;
    }
}
