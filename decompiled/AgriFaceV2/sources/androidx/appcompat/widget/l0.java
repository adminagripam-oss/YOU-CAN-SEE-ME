package androidx.appcompat.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class l0 extends ListView {

    /* renamed from: a  reason: collision with root package name */
    private final Rect f2372a;

    /* renamed from: b  reason: collision with root package name */
    private int f2373b;

    /* renamed from: c  reason: collision with root package name */
    private int f2374c;

    /* renamed from: d  reason: collision with root package name */
    private int f2375d;

    /* renamed from: e  reason: collision with root package name */
    private int f2376e;

    /* renamed from: f  reason: collision with root package name */
    private int f2377f;

    /* renamed from: g  reason: collision with root package name */
    private d f2378g;

    /* renamed from: h  reason: collision with root package name */
    private boolean f2379h;

    /* renamed from: i  reason: collision with root package name */
    private boolean f2380i;

    /* renamed from: j  reason: collision with root package name */
    private boolean f2381j;

    /* renamed from: k  reason: collision with root package name */
    private androidx.core.view.x0 f2382k;

    /* renamed from: l  reason: collision with root package name */
    private androidx.core.widget.f f2383l;

    /* renamed from: m  reason: collision with root package name */
    f f2384m;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static class a {
        static void a(View view, float f8, float f9) {
            view.drawableHotspotChanged(f8, f9);
        }
    }

    /* loaded from: classes.dex */
    static class b {

        /* renamed from: a  reason: collision with root package name */
        private static Method f2385a;

        /* renamed from: b  reason: collision with root package name */
        private static Method f2386b;

        /* renamed from: c  reason: collision with root package name */
        private static Method f2387c;

        /* renamed from: d  reason: collision with root package name */
        private static boolean f2388d;

        static {
            try {
                Class cls = Integer.TYPE;
                Class cls2 = Float.TYPE;
                Method declaredMethod = AbsListView.class.getDeclaredMethod("positionSelector", cls, View.class, Boolean.TYPE, cls2, cls2);
                f2385a = declaredMethod;
                declaredMethod.setAccessible(true);
                Method declaredMethod2 = AdapterView.class.getDeclaredMethod("setSelectedPositionInt", cls);
                f2386b = declaredMethod2;
                declaredMethod2.setAccessible(true);
                Method declaredMethod3 = AdapterView.class.getDeclaredMethod("setNextSelectedPositionInt", cls);
                f2387c = declaredMethod3;
                declaredMethod3.setAccessible(true);
                f2388d = true;
            } catch (NoSuchMethodException e8) {
                e8.printStackTrace();
            }
        }

        static boolean a() {
            return f2388d;
        }

        static void b(l0 l0Var, int i8, View view) {
            try {
                f2385a.invoke(l0Var, Integer.valueOf(i8), view, Boolean.FALSE, -1, -1);
                f2386b.invoke(l0Var, Integer.valueOf(i8));
                f2387c.invoke(l0Var, Integer.valueOf(i8));
            } catch (IllegalAccessException | InvocationTargetException e8) {
                e8.printStackTrace();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static class c {
        static boolean a(AbsListView absListView) {
            return absListView.isSelectedChildViewEnabled();
        }

        static void b(AbsListView absListView, boolean z7) {
            absListView.setSelectedChildViewEnabled(z7);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class d extends i.a {

        /* renamed from: b  reason: collision with root package name */
        private boolean f2389b;

        d(Drawable drawable) {
            super(drawable);
            this.f2389b = true;
        }

        void b(boolean z7) {
            this.f2389b = z7;
        }

        @Override // i.a, android.graphics.drawable.Drawable
        public void draw(Canvas canvas) {
            if (this.f2389b) {
                super.draw(canvas);
            }
        }

        @Override // i.a, android.graphics.drawable.Drawable
        public void setHotspot(float f8, float f9) {
            if (this.f2389b) {
                super.setHotspot(f8, f9);
            }
        }

        @Override // i.a, android.graphics.drawable.Drawable
        public void setHotspotBounds(int i8, int i9, int i10, int i11) {
            if (this.f2389b) {
                super.setHotspotBounds(i8, i9, i10, i11);
            }
        }

        @Override // i.a, android.graphics.drawable.Drawable
        public boolean setState(int[] iArr) {
            if (this.f2389b) {
                return super.setState(iArr);
            }
            return false;
        }

        @Override // i.a, android.graphics.drawable.Drawable
        public boolean setVisible(boolean z7, boolean z8) {
            if (this.f2389b) {
                return super.setVisible(z7, z8);
            }
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static class e {

        /* renamed from: a  reason: collision with root package name */
        private static final Field f2390a;

        static {
            Field field = null;
            try {
                field = AbsListView.class.getDeclaredField("mIsChildViewEnabled");
                field.setAccessible(true);
            } catch (NoSuchFieldException e8) {
                e8.printStackTrace();
            }
            f2390a = field;
        }

        static boolean a(AbsListView absListView) {
            Field field = f2390a;
            if (field != null) {
                try {
                    return field.getBoolean(absListView);
                } catch (IllegalAccessException e8) {
                    e8.printStackTrace();
                    return false;
                }
            }
            return false;
        }

        static void b(AbsListView absListView, boolean z7) {
            Field field = f2390a;
            if (field != null) {
                try {
                    field.set(absListView, Boolean.valueOf(z7));
                } catch (IllegalAccessException e8) {
                    e8.printStackTrace();
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public class f implements Runnable {
        f() {
        }

        public void a() {
            l0 l0Var = l0.this;
            l0Var.f2384m = null;
            l0Var.removeCallbacks(this);
        }

        public void b() {
            l0.this.post(this);
        }

        @Override // java.lang.Runnable
        public void run() {
            l0 l0Var = l0.this;
            l0Var.f2384m = null;
            l0Var.drawableStateChanged();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public l0(Context context, boolean z7) {
        super(context, null, g.a.f6815x);
        this.f2372a = new Rect();
        this.f2373b = 0;
        this.f2374c = 0;
        this.f2375d = 0;
        this.f2376e = 0;
        this.f2380i = z7;
        setCacheColorHint(0);
    }

    private void a() {
        this.f2381j = false;
        setPressed(false);
        drawableStateChanged();
        View childAt = getChildAt(this.f2377f - getFirstVisiblePosition());
        if (childAt != null) {
            childAt.setPressed(false);
        }
        androidx.core.view.x0 x0Var = this.f2382k;
        if (x0Var != null) {
            x0Var.c();
            this.f2382k = null;
        }
    }

    private void b(View view, int i8) {
        performItemClick(view, i8, getItemIdAtPosition(i8));
    }

    private void c(Canvas canvas) {
        Drawable selector;
        if (this.f2372a.isEmpty() || (selector = getSelector()) == null) {
            return;
        }
        selector.setBounds(this.f2372a);
        selector.draw(canvas);
    }

    private void f(int i8, View view) {
        Rect rect = this.f2372a;
        rect.set(view.getLeft(), view.getTop(), view.getRight(), view.getBottom());
        rect.left -= this.f2373b;
        rect.top -= this.f2374c;
        rect.right += this.f2375d;
        rect.bottom += this.f2376e;
        boolean k8 = k();
        if (view.isEnabled() != k8) {
            l(!k8);
            if (i8 != -1) {
                refreshDrawableState();
            }
        }
    }

    private void g(int i8, View view) {
        Drawable selector = getSelector();
        boolean z7 = (selector == null || i8 == -1) ? false : true;
        if (z7) {
            selector.setVisible(false, false);
        }
        f(i8, view);
        if (z7) {
            Rect rect = this.f2372a;
            float exactCenterX = rect.exactCenterX();
            float exactCenterY = rect.exactCenterY();
            selector.setVisible(getVisibility() == 0, false);
            androidx.core.graphics.drawable.a.c(selector, exactCenterX, exactCenterY);
        }
    }

    private void h(int i8, View view, float f8, float f9) {
        g(i8, view);
        Drawable selector = getSelector();
        if (selector == null || i8 == -1) {
            return;
        }
        androidx.core.graphics.drawable.a.c(selector, f8, f9);
    }

    private void i(View view, int i8, float f8, float f9) {
        View childAt;
        this.f2381j = true;
        a.a(this, f8, f9);
        if (!isPressed()) {
            setPressed(true);
        }
        layoutChildren();
        int i9 = this.f2377f;
        if (i9 != -1 && (childAt = getChildAt(i9 - getFirstVisiblePosition())) != null && childAt != view && childAt.isPressed()) {
            childAt.setPressed(false);
        }
        this.f2377f = i8;
        a.a(view, f8 - view.getLeft(), f9 - view.getTop());
        if (!view.isPressed()) {
            view.setPressed(true);
        }
        h(i8, view, f8, f9);
        j(false);
        refreshDrawableState();
    }

    private void j(boolean z7) {
        d dVar = this.f2378g;
        if (dVar != null) {
            dVar.b(z7);
        }
    }

    private boolean k() {
        return androidx.core.os.a.b() ? c.a(this) : e.a(this);
    }

    private void l(boolean z7) {
        if (androidx.core.os.a.b()) {
            c.b(this, z7);
        } else {
            e.b(this, z7);
        }
    }

    private boolean m() {
        return this.f2381j;
    }

    private void n() {
        Drawable selector = getSelector();
        if (selector != null && m() && isPressed()) {
            selector.setState(getDrawableState());
        }
    }

    public int d(int i8, int i9, int i10, int i11, int i12) {
        int listPaddingTop = getListPaddingTop();
        int listPaddingBottom = getListPaddingBottom();
        int dividerHeight = getDividerHeight();
        Drawable divider = getDivider();
        ListAdapter adapter = getAdapter();
        int i13 = listPaddingTop + listPaddingBottom;
        if (adapter == null) {
            return i13;
        }
        if (dividerHeight <= 0 || divider == null) {
            dividerHeight = 0;
        }
        int count = adapter.getCount();
        int i14 = 0;
        int i15 = 0;
        int i16 = 0;
        View view = null;
        while (i14 < count) {
            int itemViewType = adapter.getItemViewType(i14);
            if (itemViewType != i15) {
                view = null;
                i15 = itemViewType;
            }
            view = adapter.getView(i14, view, this);
            ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
            if (layoutParams == null) {
                layoutParams = generateDefaultLayoutParams();
                view.setLayoutParams(layoutParams);
            }
            int i17 = layoutParams.height;
            view.measure(i8, i17 > 0 ? View.MeasureSpec.makeMeasureSpec(i17, 1073741824) : View.MeasureSpec.makeMeasureSpec(0, 0));
            view.forceLayout();
            if (i14 > 0) {
                i13 += dividerHeight;
            }
            i13 += view.getMeasuredHeight();
            if (i13 >= i11) {
                return (i12 < 0 || i14 <= i12 || i16 <= 0 || i13 == i11) ? i11 : i16;
            }
            if (i12 >= 0 && i14 >= i12) {
                i16 = i13;
            }
            i14++;
        }
        return i13;
    }

    @Override // android.widget.ListView, android.widget.AbsListView, android.view.ViewGroup, android.view.View
    protected void dispatchDraw(Canvas canvas) {
        c(canvas);
        super.dispatchDraw(canvas);
    }

    @Override // android.widget.AbsListView, android.view.ViewGroup, android.view.View
    protected void drawableStateChanged() {
        if (this.f2384m != null) {
            return;
        }
        super.drawableStateChanged();
        j(true);
        n();
    }

    /* JADX WARN: Code restructure failed: missing block: B:7:0x000c, code lost:
        if (r0 != 3) goto L7;
     */
    /* JADX WARN: Removed duplicated region for block: B:22:0x0048 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:25:0x004f  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x0065  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean e(android.view.MotionEvent r8, int r9) {
        /*
            r7 = this;
            int r0 = r8.getActionMasked()
            r1 = 1
            r2 = 0
            if (r0 == r1) goto L16
            r3 = 2
            if (r0 == r3) goto L14
            r9 = 3
            if (r0 == r9) goto L11
        Le:
            r3 = r1
            r9 = r2
            goto L46
        L11:
            r9 = r2
            r3 = r9
            goto L46
        L14:
            r3 = r1
            goto L17
        L16:
            r3 = r2
        L17:
            int r9 = r8.findPointerIndex(r9)
            if (r9 >= 0) goto L1e
            goto L11
        L1e:
            float r4 = r8.getX(r9)
            int r4 = (int) r4
            float r9 = r8.getY(r9)
            int r9 = (int) r9
            int r5 = r7.pointToPosition(r4, r9)
            r6 = -1
            if (r5 != r6) goto L31
            r9 = r1
            goto L46
        L31:
            int r3 = r7.getFirstVisiblePosition()
            int r3 = r5 - r3
            android.view.View r3 = r7.getChildAt(r3)
            float r4 = (float) r4
            float r9 = (float) r9
            r7.i(r3, r5, r4, r9)
            if (r0 != r1) goto Le
            r7.b(r3, r5)
            goto Le
        L46:
            if (r3 == 0) goto L4a
            if (r9 == 0) goto L4d
        L4a:
            r7.a()
        L4d:
            if (r3 == 0) goto L65
            androidx.core.widget.f r9 = r7.f2383l
            if (r9 != 0) goto L5a
            androidx.core.widget.f r9 = new androidx.core.widget.f
            r9.<init>(r7)
            r7.f2383l = r9
        L5a:
            androidx.core.widget.f r9 = r7.f2383l
            r9.m(r1)
            androidx.core.widget.f r9 = r7.f2383l
            r9.onTouch(r7, r8)
            goto L6c
        L65:
            androidx.core.widget.f r8 = r7.f2383l
            if (r8 == 0) goto L6c
            r8.m(r2)
        L6c:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.widget.l0.e(android.view.MotionEvent, int):boolean");
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean hasFocus() {
        return this.f2380i || super.hasFocus();
    }

    @Override // android.view.View
    public boolean hasWindowFocus() {
        return this.f2380i || super.hasWindowFocus();
    }

    @Override // android.view.View
    public boolean isFocused() {
        return this.f2380i || super.isFocused();
    }

    @Override // android.view.View
    public boolean isInTouchMode() {
        return (this.f2380i && this.f2379h) || super.isInTouchMode();
    }

    @Override // android.widget.ListView, android.widget.AbsListView, android.widget.AdapterView, android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        this.f2384m = null;
        super.onDetachedFromWindow();
    }

    @Override // android.view.View
    public boolean onHoverEvent(MotionEvent motionEvent) {
        int i8 = Build.VERSION.SDK_INT;
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 10 && this.f2384m == null) {
            f fVar = new f();
            this.f2384m = fVar;
            fVar.b();
        }
        boolean onHoverEvent = super.onHoverEvent(motionEvent);
        if (actionMasked == 9 || actionMasked == 7) {
            int pointToPosition = pointToPosition((int) motionEvent.getX(), (int) motionEvent.getY());
            if (pointToPosition != -1 && pointToPosition != getSelectedItemPosition()) {
                View childAt = getChildAt(pointToPosition - getFirstVisiblePosition());
                if (childAt.isEnabled()) {
                    requestFocus();
                    if (i8 < 30 || !b.a()) {
                        setSelectionFromTop(pointToPosition, childAt.getTop() - getTop());
                    } else {
                        b.b(this, pointToPosition, childAt);
                    }
                }
                n();
            }
        } else {
            setSelection(-1);
        }
        return onHoverEvent;
    }

    @Override // android.widget.AbsListView, android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (motionEvent.getAction() == 0) {
            this.f2377f = pointToPosition((int) motionEvent.getX(), (int) motionEvent.getY());
        }
        f fVar = this.f2384m;
        if (fVar != null) {
            fVar.a();
        }
        return super.onTouchEvent(motionEvent);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setListSelectionHidden(boolean z7) {
        this.f2379h = z7;
    }

    @Override // android.widget.AbsListView
    public void setSelector(Drawable drawable) {
        d dVar = drawable != null ? new d(drawable) : null;
        this.f2378g = dVar;
        super.setSelector(dVar);
        Rect rect = new Rect();
        if (drawable != null) {
            drawable.getPadding(rect);
        }
        this.f2373b = rect.left;
        this.f2374c = rect.top;
        this.f2375d = rect.right;
        this.f2376e = rect.bottom;
    }
}
