package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.transition.Transition;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.widget.HeaderViewListAdapter;
import android.widget.ListAdapter;
import android.widget.PopupWindow;
import androidx.appcompat.view.menu.ListMenuItemView;
import java.lang.reflect.Method;
/* loaded from: classes.dex */
public class r0 extends p0 implements q0 {
    private static Method J;
    private q0 I;

    /* loaded from: classes.dex */
    static class a {
        static void a(PopupWindow popupWindow, Transition transition) {
            popupWindow.setEnterTransition(transition);
        }

        static void b(PopupWindow popupWindow, Transition transition) {
            popupWindow.setExitTransition(transition);
        }
    }

    /* loaded from: classes.dex */
    static class b {
        static void a(PopupWindow popupWindow, boolean z7) {
            popupWindow.setTouchModal(z7);
        }
    }

    /* loaded from: classes.dex */
    public static class c extends l0 {

        /* renamed from: n  reason: collision with root package name */
        final int f2466n;

        /* renamed from: o  reason: collision with root package name */
        final int f2467o;

        /* renamed from: p  reason: collision with root package name */
        private q0 f2468p;

        /* renamed from: q  reason: collision with root package name */
        private MenuItem f2469q;

        /* loaded from: classes.dex */
        static class a {
            static int a(Configuration configuration) {
                return configuration.getLayoutDirection();
            }
        }

        public c(Context context, boolean z7) {
            super(context, z7);
            if (1 == a.a(context.getResources().getConfiguration())) {
                this.f2466n = 21;
                this.f2467o = 22;
                return;
            }
            this.f2466n = 22;
            this.f2467o = 21;
        }

        @Override // androidx.appcompat.widget.l0
        public /* bridge */ /* synthetic */ int d(int i8, int i9, int i10, int i11, int i12) {
            return super.d(i8, i9, i10, i11, i12);
        }

        @Override // androidx.appcompat.widget.l0
        public /* bridge */ /* synthetic */ boolean e(MotionEvent motionEvent, int i8) {
            return super.e(motionEvent, i8);
        }

        @Override // androidx.appcompat.widget.l0, android.view.ViewGroup, android.view.View
        public /* bridge */ /* synthetic */ boolean hasFocus() {
            return super.hasFocus();
        }

        @Override // androidx.appcompat.widget.l0, android.view.View
        public /* bridge */ /* synthetic */ boolean hasWindowFocus() {
            return super.hasWindowFocus();
        }

        @Override // androidx.appcompat.widget.l0, android.view.View
        public /* bridge */ /* synthetic */ boolean isFocused() {
            return super.isFocused();
        }

        @Override // androidx.appcompat.widget.l0, android.view.View
        public /* bridge */ /* synthetic */ boolean isInTouchMode() {
            return super.isInTouchMode();
        }

        @Override // androidx.appcompat.widget.l0, android.view.View
        public boolean onHoverEvent(MotionEvent motionEvent) {
            androidx.appcompat.view.menu.f fVar;
            int i8;
            int pointToPosition;
            int i9;
            if (this.f2468p != null) {
                ListAdapter adapter = getAdapter();
                if (adapter instanceof HeaderViewListAdapter) {
                    HeaderViewListAdapter headerViewListAdapter = (HeaderViewListAdapter) adapter;
                    i8 = headerViewListAdapter.getHeadersCount();
                    fVar = (androidx.appcompat.view.menu.f) headerViewListAdapter.getWrappedAdapter();
                } else {
                    fVar = (androidx.appcompat.view.menu.f) adapter;
                    i8 = 0;
                }
                androidx.appcompat.view.menu.i item = (motionEvent.getAction() == 10 || (pointToPosition = pointToPosition((int) motionEvent.getX(), (int) motionEvent.getY())) == -1 || (i9 = pointToPosition - i8) < 0 || i9 >= fVar.getCount()) ? null : fVar.getItem(i9);
                MenuItem menuItem = this.f2469q;
                if (menuItem != item) {
                    androidx.appcompat.view.menu.g b8 = fVar.b();
                    if (menuItem != null) {
                        this.f2468p.f(b8, menuItem);
                    }
                    this.f2469q = item;
                    if (item != null) {
                        this.f2468p.b(b8, item);
                    }
                }
            }
            return super.onHoverEvent(motionEvent);
        }

        @Override // android.widget.ListView, android.widget.AbsListView, android.view.View, android.view.KeyEvent.Callback
        public boolean onKeyDown(int i8, KeyEvent keyEvent) {
            ListMenuItemView listMenuItemView = (ListMenuItemView) getSelectedView();
            if (listMenuItemView != null && i8 == this.f2466n) {
                if (listMenuItemView.isEnabled() && listMenuItemView.getItemData().hasSubMenu()) {
                    performItemClick(listMenuItemView, getSelectedItemPosition(), getSelectedItemId());
                }
                return true;
            } else if (listMenuItemView == null || i8 != this.f2467o) {
                return super.onKeyDown(i8, keyEvent);
            } else {
                setSelection(-1);
                ListAdapter adapter = getAdapter();
                if (adapter instanceof HeaderViewListAdapter) {
                    adapter = ((HeaderViewListAdapter) adapter).getWrappedAdapter();
                }
                ((androidx.appcompat.view.menu.f) adapter).b().e(false);
                return true;
            }
        }

        @Override // androidx.appcompat.widget.l0, android.widget.AbsListView, android.view.View
        public /* bridge */ /* synthetic */ boolean onTouchEvent(MotionEvent motionEvent) {
            return super.onTouchEvent(motionEvent);
        }

        public void setHoverListener(q0 q0Var) {
            this.f2468p = q0Var;
        }

        @Override // androidx.appcompat.widget.l0, android.widget.AbsListView
        public /* bridge */ /* synthetic */ void setSelector(Drawable drawable) {
            super.setSelector(drawable);
        }
    }

    static {
        try {
            if (Build.VERSION.SDK_INT <= 28) {
                J = PopupWindow.class.getDeclaredMethod("setTouchModal", Boolean.TYPE);
            }
        } catch (NoSuchMethodException unused) {
            Log.i("MenuPopupWindow", "Could not find method setTouchModal() on PopupWindow. Oh well.");
        }
    }

    public r0(Context context, AttributeSet attributeSet, int i8, int i9) {
        super(context, attributeSet, i8, i9);
    }

    public void N(Object obj) {
        a.a(this.F, (Transition) obj);
    }

    public void O(Object obj) {
        a.b(this.F, (Transition) obj);
    }

    public void P(q0 q0Var) {
        this.I = q0Var;
    }

    public void Q(boolean z7) {
        if (Build.VERSION.SDK_INT > 28) {
            b.a(this.F, z7);
            return;
        }
        Method method = J;
        if (method != null) {
            try {
                method.invoke(this.F, Boolean.valueOf(z7));
            } catch (Exception unused) {
                Log.i("MenuPopupWindow", "Could not invoke setTouchModal() on PopupWindow. Oh well.");
            }
        }
    }

    @Override // androidx.appcompat.widget.q0
    public void b(androidx.appcompat.view.menu.g gVar, MenuItem menuItem) {
        q0 q0Var = this.I;
        if (q0Var != null) {
            q0Var.b(gVar, menuItem);
        }
    }

    @Override // androidx.appcompat.widget.q0
    public void f(androidx.appcompat.view.menu.g gVar, MenuItem menuItem) {
        q0 q0Var = this.I;
        if (q0Var != null) {
            q0Var.f(gVar, menuItem);
        }
    }

    @Override // androidx.appcompat.widget.p0
    l0 s(Context context, boolean z7) {
        c cVar = new c(context, z7);
        cVar.setHoverListener(this);
        return cVar;
    }
}
