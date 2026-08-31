package androidx.core.view;

import android.os.Bundle;
import android.text.style.ClickableSpan;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.accessibility.AccessibilityNodeProvider;
import h1.r;
import java.lang.ref.WeakReference;
import java.util.Collections;
import java.util.List;
/* loaded from: classes.dex */
public class a {

    /* renamed from: c  reason: collision with root package name */
    private static final View.AccessibilityDelegate f3067c = new View.AccessibilityDelegate();

    /* renamed from: a  reason: collision with root package name */
    private final View.AccessibilityDelegate f3068a;

    /* renamed from: b  reason: collision with root package name */
    private final View.AccessibilityDelegate f3069b;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: androidx.core.view.a$a  reason: collision with other inner class name */
    /* loaded from: classes.dex */
    public static final class C0031a extends View.AccessibilityDelegate {

        /* renamed from: a  reason: collision with root package name */
        final a f3070a;

        C0031a(a aVar) {
            this.f3070a = aVar;
        }

        @Override // android.view.View.AccessibilityDelegate
        public boolean dispatchPopulateAccessibilityEvent(View view, AccessibilityEvent accessibilityEvent) {
            return this.f3070a.a(view, accessibilityEvent);
        }

        @Override // android.view.View.AccessibilityDelegate
        public AccessibilityNodeProvider getAccessibilityNodeProvider(View view) {
            h1.s b8 = this.f3070a.b(view);
            if (b8 != null) {
                return (AccessibilityNodeProvider) b8.a();
            }
            return null;
        }

        @Override // android.view.View.AccessibilityDelegate
        public void onInitializeAccessibilityEvent(View view, AccessibilityEvent accessibilityEvent) {
            this.f3070a.f(view, accessibilityEvent);
        }

        @Override // android.view.View.AccessibilityDelegate
        public void onInitializeAccessibilityNodeInfo(View view, AccessibilityNodeInfo accessibilityNodeInfo) {
            h1.r W = h1.r.W(accessibilityNodeInfo);
            W.S(p0.B(view));
            W.Q(p0.x(view));
            W.R(p0.l(view));
            W.U(p0.t(view));
            this.f3070a.g(view, W);
            W.b(accessibilityNodeInfo.getText(), view);
            List c8 = a.c(view);
            for (int i8 = 0; i8 < c8.size(); i8++) {
                W.a((r.a) c8.get(i8));
            }
        }

        @Override // android.view.View.AccessibilityDelegate
        public void onPopulateAccessibilityEvent(View view, AccessibilityEvent accessibilityEvent) {
            this.f3070a.h(view, accessibilityEvent);
        }

        @Override // android.view.View.AccessibilityDelegate
        public boolean onRequestSendAccessibilityEvent(ViewGroup viewGroup, View view, AccessibilityEvent accessibilityEvent) {
            return this.f3070a.i(viewGroup, view, accessibilityEvent);
        }

        @Override // android.view.View.AccessibilityDelegate
        public boolean performAccessibilityAction(View view, int i8, Bundle bundle) {
            return this.f3070a.j(view, i8, bundle);
        }

        @Override // android.view.View.AccessibilityDelegate
        public void sendAccessibilityEvent(View view, int i8) {
            this.f3070a.l(view, i8);
        }

        @Override // android.view.View.AccessibilityDelegate
        public void sendAccessibilityEventUnchecked(View view, AccessibilityEvent accessibilityEvent) {
            this.f3070a.m(view, accessibilityEvent);
        }
    }

    public a() {
        this(f3067c);
    }

    static List c(View view) {
        List list = (List) view.getTag(c1.b.H);
        return list == null ? Collections.emptyList() : list;
    }

    private boolean e(ClickableSpan clickableSpan, View view) {
        if (clickableSpan != null) {
            ClickableSpan[] k8 = h1.r.k(view.createAccessibilityNodeInfo().getText());
            for (int i8 = 0; k8 != null && i8 < k8.length; i8++) {
                if (clickableSpan.equals(k8[i8])) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean k(int i8, View view) {
        WeakReference weakReference;
        SparseArray sparseArray = (SparseArray) view.getTag(c1.b.I);
        if (sparseArray == null || (weakReference = (WeakReference) sparseArray.get(i8)) == null) {
            return false;
        }
        ClickableSpan clickableSpan = (ClickableSpan) weakReference.get();
        if (e(clickableSpan, view)) {
            clickableSpan.onClick(view);
            return true;
        }
        return false;
    }

    public boolean a(View view, AccessibilityEvent accessibilityEvent) {
        return this.f3068a.dispatchPopulateAccessibilityEvent(view, accessibilityEvent);
    }

    public h1.s b(View view) {
        AccessibilityNodeProvider accessibilityNodeProvider = this.f3068a.getAccessibilityNodeProvider(view);
        if (accessibilityNodeProvider != null) {
            return new h1.s(accessibilityNodeProvider);
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public View.AccessibilityDelegate d() {
        return this.f3069b;
    }

    public void f(View view, AccessibilityEvent accessibilityEvent) {
        this.f3068a.onInitializeAccessibilityEvent(view, accessibilityEvent);
    }

    public void g(View view, h1.r rVar) {
        this.f3068a.onInitializeAccessibilityNodeInfo(view, rVar.V());
    }

    public void h(View view, AccessibilityEvent accessibilityEvent) {
        this.f3068a.onPopulateAccessibilityEvent(view, accessibilityEvent);
    }

    public boolean i(ViewGroup viewGroup, View view, AccessibilityEvent accessibilityEvent) {
        return this.f3068a.onRequestSendAccessibilityEvent(viewGroup, view, accessibilityEvent);
    }

    public boolean j(View view, int i8, Bundle bundle) {
        List c8 = c(view);
        boolean z7 = false;
        int i9 = 0;
        while (true) {
            if (i9 >= c8.size()) {
                break;
            }
            r.a aVar = (r.a) c8.get(i9);
            if (aVar.a() == i8) {
                z7 = aVar.c(view, bundle);
                break;
            }
            i9++;
        }
        if (!z7) {
            z7 = this.f3068a.performAccessibilityAction(view, i8, bundle);
        }
        return (z7 || i8 != c1.b.f4695a || bundle == null) ? z7 : k(bundle.getInt("ACCESSIBILITY_CLICKABLE_SPAN_ID", -1), view);
    }

    public void l(View view, int i8) {
        this.f3068a.sendAccessibilityEvent(view, i8);
    }

    public void m(View view, AccessibilityEvent accessibilityEvent) {
        this.f3068a.sendAccessibilityEventUnchecked(view, accessibilityEvent);
    }

    public a(View.AccessibilityDelegate accessibilityDelegate) {
        this.f3068a = accessibilityDelegate;
        this.f3069b = new C0031a(this);
    }
}
