package p6;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.view.View;
import android.view.ViewGroup;
import h2.k;
import h2.l;
/* loaded from: classes.dex */
public abstract class h {

    /* loaded from: classes.dex */
    public interface a {
        void a(float f8, float f9, float f10);
    }

    /* loaded from: classes.dex */
    public interface b {
        boolean a(View view);
    }

    public static void c(Context context, a aVar) {
        Activity e8 = e(context);
        if (e8 != null) {
            k a8 = l.b().a(e8);
            aVar.a(a8.a().width(), a8.a().height(), context.getResources().getDisplayMetrics().density);
        }
    }

    public static boolean d(View view) {
        return i(view, new b() { // from class: p6.f
            @Override // p6.h.b
            public final boolean a(View view2) {
                boolean hasFocus;
                hasFocus = view2.hasFocus();
                return hasFocus;
            }
        });
    }

    public static Activity e(Context context) {
        if (context == null) {
            return null;
        }
        if (context instanceof Activity) {
            return (Activity) context;
        }
        if (context instanceof ContextWrapper) {
            return e(((ContextWrapper) context).getBaseContext());
        }
        return null;
    }

    public static boolean f(View view, final Class[] clsArr) {
        return i(view, new b() { // from class: p6.g
            @Override // p6.h.b
            public final boolean a(View view2) {
                boolean h8;
                h8 = h.h(clsArr, view2);
                return h8;
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ boolean h(Class[] clsArr, View view) {
        for (Class cls : clsArr) {
            if (cls.isInstance(view)) {
                return true;
            }
        }
        return false;
    }

    public static boolean i(View view, b bVar) {
        if (view == null) {
            return false;
        }
        if (bVar.a(view)) {
            return true;
        }
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            for (int i8 = 0; i8 < viewGroup.getChildCount(); i8++) {
                if (i(viewGroup.getChildAt(i8), bVar)) {
                    return true;
                }
            }
        }
        return false;
    }
}
