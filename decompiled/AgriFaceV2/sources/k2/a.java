package k2;

import android.graphics.Rect;
import androidx.window.sidecar.SidecarDeviceState;
import androidx.window.sidecar.SidecarDisplayFeature;
import androidx.window.sidecar.SidecarWindowLayoutInfo;
import d7.g;
import d7.k;
import d7.l;
import e2.h;
import e2.j;
import h2.c;
import h2.d;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import s6.n;
/* loaded from: classes.dex */
public final class a {

    /* renamed from: b  reason: collision with root package name */
    public static final C0114a f8315b = new C0114a(null);

    /* renamed from: c  reason: collision with root package name */
    private static final String f8316c = a.class.getSimpleName();

    /* renamed from: a  reason: collision with root package name */
    private final j f8317a;

    /* renamed from: k2.a$a  reason: collision with other inner class name */
    /* loaded from: classes.dex */
    public static final class C0114a {
        private C0114a() {
        }

        public /* synthetic */ C0114a(g gVar) {
            this();
        }

        public final int a(SidecarDeviceState sidecarDeviceState) {
            k.e(sidecarDeviceState, "sidecarDeviceState");
            try {
                return sidecarDeviceState.posture;
            } catch (NoSuchFieldError unused) {
                try {
                    Object invoke = SidecarDeviceState.class.getMethod("getPosture", new Class[0]).invoke(sidecarDeviceState, new Object[0]);
                    k.c(invoke, "null cannot be cast to non-null type kotlin.Int");
                    return ((Integer) invoke).intValue();
                } catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException unused2) {
                    return 0;
                }
            }
        }

        public final int b(SidecarDeviceState sidecarDeviceState) {
            k.e(sidecarDeviceState, "sidecarDeviceState");
            int a8 = a(sidecarDeviceState);
            if (a8 < 0 || a8 > 4) {
                return 0;
            }
            return a8;
        }

        public final List c(SidecarWindowLayoutInfo sidecarWindowLayoutInfo) {
            List f8;
            List f9;
            k.e(sidecarWindowLayoutInfo, "info");
            try {
                try {
                    List list = sidecarWindowLayoutInfo.displayFeatures;
                    if (list == null) {
                        f9 = n.f();
                        return f9;
                    }
                    return list;
                } catch (NoSuchFieldError unused) {
                    Object invoke = SidecarWindowLayoutInfo.class.getMethod("getDisplayFeatures", new Class[0]).invoke(sidecarWindowLayoutInfo, new Object[0]);
                    k.c(invoke, "null cannot be cast to non-null type kotlin.collections.List<androidx.window.sidecar.SidecarDisplayFeature>");
                    return (List) invoke;
                }
            } catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException unused2) {
                f8 = n.f();
                return f8;
            }
        }

        public final void d(SidecarDeviceState sidecarDeviceState, int i8) {
            k.e(sidecarDeviceState, "sidecarDeviceState");
            try {
                try {
                    sidecarDeviceState.posture = i8;
                } catch (NoSuchFieldError unused) {
                    SidecarDeviceState.class.getMethod("setPosture", Integer.TYPE).invoke(sidecarDeviceState, Integer.valueOf(i8));
                }
            } catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException unused2) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static final class b extends l implements c7.l {

        /* renamed from: f  reason: collision with root package name */
        public static final b f8318f = new b();

        b() {
            super(1);
        }

        @Override // c7.l
        /* renamed from: b */
        public final Boolean g(SidecarDisplayFeature sidecarDisplayFeature) {
            k.e(sidecarDisplayFeature, "$this$require");
            boolean z7 = true;
            if (sidecarDisplayFeature.getType() != 1 && sidecarDisplayFeature.getType() != 2) {
                z7 = false;
            }
            return Boolean.valueOf(z7);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static final class c extends l implements c7.l {

        /* renamed from: f  reason: collision with root package name */
        public static final c f8319f = new c();

        c() {
            super(1);
        }

        @Override // c7.l
        /* renamed from: b */
        public final Boolean g(SidecarDisplayFeature sidecarDisplayFeature) {
            k.e(sidecarDisplayFeature, "$this$require");
            return Boolean.valueOf((sidecarDisplayFeature.getRect().width() == 0 && sidecarDisplayFeature.getRect().height() == 0) ? false : true);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static final class d extends l implements c7.l {

        /* renamed from: f  reason: collision with root package name */
        public static final d f8320f = new d();

        d() {
            super(1);
        }

        @Override // c7.l
        /* renamed from: b */
        public final Boolean g(SidecarDisplayFeature sidecarDisplayFeature) {
            k.e(sidecarDisplayFeature, "$this$require");
            boolean z7 = true;
            if (sidecarDisplayFeature.getType() == 1 && sidecarDisplayFeature.getRect().width() != 0 && sidecarDisplayFeature.getRect().height() != 0) {
                z7 = false;
            }
            return Boolean.valueOf(z7);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static final class e extends l implements c7.l {

        /* renamed from: f  reason: collision with root package name */
        public static final e f8321f = new e();

        e() {
            super(1);
        }

        @Override // c7.l
        /* renamed from: b */
        public final Boolean g(SidecarDisplayFeature sidecarDisplayFeature) {
            k.e(sidecarDisplayFeature, "$this$require");
            return Boolean.valueOf(sidecarDisplayFeature.getRect().left == 0 || sidecarDisplayFeature.getRect().top == 0);
        }
    }

    public a(j jVar) {
        k.e(jVar, "verificationMode");
        this.f8317a = jVar;
    }

    private final boolean b(SidecarDisplayFeature sidecarDisplayFeature, SidecarDisplayFeature sidecarDisplayFeature2) {
        if (k.a(sidecarDisplayFeature, sidecarDisplayFeature2)) {
            return true;
        }
        if (sidecarDisplayFeature == null || sidecarDisplayFeature2 == null || sidecarDisplayFeature.getType() != sidecarDisplayFeature2.getType()) {
            return false;
        }
        return k.a(sidecarDisplayFeature.getRect(), sidecarDisplayFeature2.getRect());
    }

    private final boolean c(List list, List list2) {
        if (list == list2) {
            return true;
        }
        if (list == null || list2 == null || list.size() != list2.size()) {
            return false;
        }
        int size = list.size();
        for (int i8 = 0; i8 < size; i8++) {
            if (!b((SidecarDisplayFeature) list.get(i8), (SidecarDisplayFeature) list2.get(i8))) {
                return false;
            }
        }
        return true;
    }

    public final boolean a(SidecarDeviceState sidecarDeviceState, SidecarDeviceState sidecarDeviceState2) {
        if (k.a(sidecarDeviceState, sidecarDeviceState2)) {
            return true;
        }
        if (sidecarDeviceState == null || sidecarDeviceState2 == null) {
            return false;
        }
        C0114a c0114a = f8315b;
        return c0114a.b(sidecarDeviceState) == c0114a.b(sidecarDeviceState2);
    }

    public final boolean d(SidecarWindowLayoutInfo sidecarWindowLayoutInfo, SidecarWindowLayoutInfo sidecarWindowLayoutInfo2) {
        if (k.a(sidecarWindowLayoutInfo, sidecarWindowLayoutInfo2)) {
            return true;
        }
        if (sidecarWindowLayoutInfo == null || sidecarWindowLayoutInfo2 == null) {
            return false;
        }
        C0114a c0114a = f8315b;
        return c(c0114a.c(sidecarWindowLayoutInfo), c0114a.c(sidecarWindowLayoutInfo2));
    }

    public final h2.j e(SidecarWindowLayoutInfo sidecarWindowLayoutInfo, SidecarDeviceState sidecarDeviceState) {
        List f8;
        k.e(sidecarDeviceState, "state");
        if (sidecarWindowLayoutInfo == null) {
            f8 = n.f();
            return new h2.j(f8);
        }
        SidecarDeviceState sidecarDeviceState2 = new SidecarDeviceState();
        C0114a c0114a = f8315b;
        c0114a.d(sidecarDeviceState2, c0114a.b(sidecarDeviceState));
        return new h2.j(f(c0114a.c(sidecarWindowLayoutInfo), sidecarDeviceState2));
    }

    public final List f(List list, SidecarDeviceState sidecarDeviceState) {
        k.e(list, "sidecarDisplayFeatures");
        k.e(sidecarDeviceState, "deviceState");
        ArrayList arrayList = new ArrayList();
        Iterator it = list.iterator();
        while (it.hasNext()) {
            h2.a g8 = g((SidecarDisplayFeature) it.next(), sidecarDeviceState);
            if (g8 != null) {
                arrayList.add(g8);
            }
        }
        return arrayList;
    }

    public final h2.a g(SidecarDisplayFeature sidecarDisplayFeature, SidecarDeviceState sidecarDeviceState) {
        d.b a8;
        c.b bVar;
        k.e(sidecarDisplayFeature, "feature");
        k.e(sidecarDeviceState, "deviceState");
        h.a aVar = h.f6581a;
        String str = f8316c;
        k.d(str, "TAG");
        SidecarDisplayFeature sidecarDisplayFeature2 = (SidecarDisplayFeature) h.a.b(aVar, sidecarDisplayFeature, str, this.f8317a, null, 4, null).c("Type must be either TYPE_FOLD or TYPE_HINGE", b.f8318f).c("Feature bounds must not be 0", c.f8319f).c("TYPE_FOLD must have 0 area", d.f8320f).c("Feature be pinned to either left or top", e.f8321f).a();
        if (sidecarDisplayFeature2 == null) {
            return null;
        }
        int type = sidecarDisplayFeature2.getType();
        if (type == 1) {
            a8 = d.b.f7183b.a();
        } else if (type != 2) {
            return null;
        } else {
            a8 = d.b.f7183b.b();
        }
        int b8 = f8315b.b(sidecarDeviceState);
        if (b8 == 0 || b8 == 1) {
            return null;
        }
        if (b8 == 2) {
            bVar = c.b.f7177d;
        } else if (b8 != 3 && b8 == 4) {
            return null;
        } else {
            bVar = c.b.f7176c;
        }
        Rect rect = sidecarDisplayFeature.getRect();
        k.d(rect, "feature.rect");
        return new h2.d(new e2.b(rect), a8, bVar);
    }

    public /* synthetic */ a(j jVar, int i8, g gVar) {
        this((i8 & 1) != 0 ? j.QUIET : jVar);
    }
}
