package androidx.fragment.app;

import android.graphics.Rect;
import android.graphics.RectF;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
/* loaded from: classes.dex */
public abstract class t0 {

    /* loaded from: classes.dex */
    class a implements Runnable {

        /* renamed from: e  reason: collision with root package name */
        final /* synthetic */ int f3666e;

        /* renamed from: f  reason: collision with root package name */
        final /* synthetic */ ArrayList f3667f;

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ ArrayList f3668g;

        /* renamed from: h  reason: collision with root package name */
        final /* synthetic */ ArrayList f3669h;

        /* renamed from: i  reason: collision with root package name */
        final /* synthetic */ ArrayList f3670i;

        a(int i8, ArrayList arrayList, ArrayList arrayList2, ArrayList arrayList3, ArrayList arrayList4) {
            this.f3666e = i8;
            this.f3667f = arrayList;
            this.f3668g = arrayList2;
            this.f3669h = arrayList3;
            this.f3670i = arrayList4;
        }

        @Override // java.lang.Runnable
        public void run() {
            for (int i8 = 0; i8 < this.f3666e; i8++) {
                androidx.core.view.p0.Y((View) this.f3667f.get(i8), (String) this.f3668g.get(i8));
                androidx.core.view.p0.Y((View) this.f3669h.get(i8), (String) this.f3670i.get(i8));
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static void f(List list, View view) {
        int size = list.size();
        if (i(list, view, size)) {
            return;
        }
        if (androidx.core.view.p0.u(view) != null) {
            list.add(view);
        }
        for (int i8 = size; i8 < list.size(); i8++) {
            View view2 = (View) list.get(i8);
            if (view2 instanceof ViewGroup) {
                ViewGroup viewGroup = (ViewGroup) view2;
                int childCount = viewGroup.getChildCount();
                for (int i9 = 0; i9 < childCount; i9++) {
                    View childAt = viewGroup.getChildAt(i9);
                    if (!i(list, childAt, size) && androidx.core.view.p0.u(childAt) != null) {
                        list.add(childAt);
                    }
                }
            }
        }
    }

    private static boolean i(List list, View view, int i8) {
        for (int i9 = 0; i9 < i8; i9++) {
            if (list.get(i9) == view) {
                return true;
            }
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static boolean l(List list) {
        return list == null || list.isEmpty();
    }

    public abstract void A(Object obj, ArrayList arrayList, ArrayList arrayList2);

    public abstract Object B(Object obj);

    public abstract void a(Object obj, View view);

    public abstract void b(Object obj, ArrayList arrayList);

    public void c(Object obj) {
    }

    public void d(Object obj, Runnable runnable) {
    }

    public abstract void e(ViewGroup viewGroup, Object obj);

    public abstract boolean g(Object obj);

    public abstract Object h(Object obj);

    public Object j(ViewGroup viewGroup, Object obj) {
        return null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void k(View view, Rect rect) {
        if (view.isAttachedToWindow()) {
            RectF rectF = new RectF();
            rectF.set(0.0f, 0.0f, view.getWidth(), view.getHeight());
            view.getMatrix().mapRect(rectF);
            rectF.offset(view.getLeft(), view.getTop());
            ViewParent parent = view.getParent();
            while (parent instanceof View) {
                View view2 = (View) parent;
                rectF.offset(-view2.getScrollX(), -view2.getScrollY());
                view2.getMatrix().mapRect(rectF);
                rectF.offset(view2.getLeft(), view2.getTop());
                parent = view2.getParent();
            }
            int[] iArr = new int[2];
            view.getRootView().getLocationOnScreen(iArr);
            rectF.offset(iArr[0], iArr[1]);
            rect.set(Math.round(rectF.left), Math.round(rectF.top), Math.round(rectF.right), Math.round(rectF.bottom));
        }
    }

    public abstract boolean m();

    public abstract boolean n(Object obj);

    public abstract Object o(Object obj, Object obj2, Object obj3);

    public abstract Object p(Object obj, Object obj2, Object obj3);

    /* JADX INFO: Access modifiers changed from: package-private */
    public ArrayList q(ArrayList arrayList) {
        ArrayList arrayList2 = new ArrayList();
        int size = arrayList.size();
        for (int i8 = 0; i8 < size; i8++) {
            View view = (View) arrayList.get(i8);
            arrayList2.add(androidx.core.view.p0.u(view));
            androidx.core.view.p0.Y(view, null);
        }
        return arrayList2;
    }

    public abstract void r(Object obj, View view, ArrayList arrayList);

    public abstract void s(Object obj, Object obj2, ArrayList arrayList, Object obj3, ArrayList arrayList2, Object obj4, ArrayList arrayList3);

    public void t(Object obj, float f8) {
    }

    public abstract void u(Object obj, Rect rect);

    public abstract void v(Object obj, View view);

    public abstract void w(p pVar, Object obj, androidx.core.os.d dVar, Runnable runnable);

    public void x(p pVar, Object obj, androidx.core.os.d dVar, Runnable runnable, Runnable runnable2) {
        runnable2.run();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void y(View view, ArrayList arrayList, ArrayList arrayList2, ArrayList arrayList3, Map map) {
        int size = arrayList2.size();
        ArrayList arrayList4 = new ArrayList();
        for (int i8 = 0; i8 < size; i8++) {
            View view2 = (View) arrayList.get(i8);
            String u7 = androidx.core.view.p0.u(view2);
            arrayList4.add(u7);
            if (u7 != null) {
                androidx.core.view.p0.Y(view2, null);
                String str = (String) map.get(u7);
                int i9 = 0;
                while (true) {
                    if (i9 >= size) {
                        break;
                    } else if (str.equals(arrayList3.get(i9))) {
                        androidx.core.view.p0.Y((View) arrayList2.get(i9), u7);
                        break;
                    } else {
                        i9++;
                    }
                }
            }
        }
        androidx.core.view.k0.a(view, new a(size, arrayList2, arrayList3, arrayList, arrayList4));
    }

    public abstract void z(Object obj, View view, ArrayList arrayList);
}
