package androidx.fragment.app;

import android.view.View;
import java.util.Iterator;
import java.util.List;
/* loaded from: classes.dex */
public final class r0 {

    /* renamed from: a  reason: collision with root package name */
    public static final r0 f3645a;

    /* renamed from: b  reason: collision with root package name */
    public static final t0 f3646b;

    /* renamed from: c  reason: collision with root package name */
    public static final t0 f3647c;

    static {
        r0 r0Var = new r0();
        f3645a = r0Var;
        f3646b = new s0();
        f3647c = r0Var.b();
    }

    private r0() {
    }

    public static final void a(p pVar, p pVar2, boolean z7, b1.a aVar, boolean z8) {
        d7.k.e(pVar, "inFragment");
        d7.k.e(pVar2, "outFragment");
        d7.k.e(aVar, "sharedElements");
        if (z7) {
            pVar2.s();
        } else {
            pVar.s();
        }
    }

    private final t0 b() {
        try {
            Class<?> cls = Class.forName("androidx.transition.FragmentTransitionSupport");
            d7.k.c(cls, "null cannot be cast to non-null type java.lang.Class<androidx.fragment.app.FragmentTransitionImpl>");
            return (t0) cls.getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
        } catch (Exception unused) {
            return null;
        }
    }

    public static final void c(b1.a aVar, b1.a aVar2) {
        d7.k.e(aVar, "<this>");
        d7.k.e(aVar2, "namedViews");
        int size = aVar.size();
        while (true) {
            size--;
            if (-1 >= size) {
                return;
            }
            if (!aVar2.containsKey((String) aVar.l(size))) {
                aVar.j(size);
            }
        }
    }

    public static final void d(List list, int i8) {
        d7.k.e(list, "views");
        Iterator it = list.iterator();
        while (it.hasNext()) {
            ((View) it.next()).setVisibility(i8);
        }
    }
}
