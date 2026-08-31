package s1;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.ViewGroup;
import androidx.fragment.app.i0;
import androidx.fragment.app.p;
import d7.k;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import s6.e0;
import s6.j0;
import s6.v;
/* loaded from: classes.dex */
public final class c {

    /* renamed from: a  reason: collision with root package name */
    public static final c f11528a = new c();

    /* renamed from: b  reason: collision with root package name */
    private static C0146c f11529b = C0146c.f11541d;

    /* loaded from: classes.dex */
    public enum a {
        PENALTY_LOG,
        PENALTY_DEATH,
        DETECT_FRAGMENT_REUSE,
        DETECT_FRAGMENT_TAG_USAGE,
        DETECT_WRONG_NESTED_HIERARCHY,
        DETECT_RETAIN_INSTANCE_USAGE,
        DETECT_SET_USER_VISIBLE_HINT,
        DETECT_TARGET_FRAGMENT_USAGE,
        DETECT_WRONG_FRAGMENT_CONTAINER
    }

    /* loaded from: classes.dex */
    public interface b {
    }

    /* renamed from: s1.c$c  reason: collision with other inner class name */
    /* loaded from: classes.dex */
    public static final class C0146c {

        /* renamed from: c  reason: collision with root package name */
        public static final a f11540c = new a(null);

        /* renamed from: d  reason: collision with root package name */
        public static final C0146c f11541d;

        /* renamed from: a  reason: collision with root package name */
        private final Set f11542a;

        /* renamed from: b  reason: collision with root package name */
        private final Map f11543b;

        /* renamed from: s1.c$c$a */
        /* loaded from: classes.dex */
        public static final class a {
            private a() {
            }

            public /* synthetic */ a(d7.g gVar) {
                this();
            }
        }

        static {
            Set b8;
            Map d8;
            b8 = j0.b();
            d8 = e0.d();
            f11541d = new C0146c(b8, null, d8);
        }

        public C0146c(Set set, b bVar, Map map) {
            k.e(set, "flags");
            k.e(map, "allowedViolations");
            this.f11542a = set;
            LinkedHashMap linkedHashMap = new LinkedHashMap();
            for (Map.Entry entry : map.entrySet()) {
                linkedHashMap.put((String) entry.getKey(), (Set) entry.getValue());
            }
            this.f11543b = linkedHashMap;
        }

        public final Set a() {
            return this.f11542a;
        }

        public final b b() {
            return null;
        }

        public final Map c() {
            return this.f11543b;
        }
    }

    private c() {
    }

    private final C0146c b(p pVar) {
        while (pVar != null) {
            if (pVar.U()) {
                i0 C = pVar.C();
                k.d(C, "declaringFragment.parentFragmentManager");
                if (C.B0() != null) {
                    C0146c B0 = C.B0();
                    k.b(B0);
                    return B0;
                }
            }
            pVar = pVar.B();
        }
        return f11529b;
    }

    private final void c(C0146c c0146c, final g gVar) {
        p a8 = gVar.a();
        final String name = a8.getClass().getName();
        if (c0146c.a().contains(a.PENALTY_LOG)) {
            Log.d("FragmentStrictMode", "Policy violation in " + name, gVar);
        }
        c0146c.b();
        if (c0146c.a().contains(a.PENALTY_DEATH)) {
            k(a8, new Runnable() { // from class: s1.b
                @Override // java.lang.Runnable
                public final void run() {
                    c.d(name, gVar);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void d(String str, g gVar) {
        k.e(gVar, "$violation");
        Log.e("FragmentStrictMode", "Policy violation with PENALTY_DEATH in " + str, gVar);
        throw gVar;
    }

    private final void e(g gVar) {
        if (i0.I0(3)) {
            Log.d("FragmentManager", "StrictMode violation in " + gVar.a().getClass().getName(), gVar);
        }
    }

    public static final void f(p pVar, String str) {
        k.e(pVar, "fragment");
        k.e(str, "previousFragmentId");
        s1.a aVar = new s1.a(pVar, str);
        c cVar = f11528a;
        cVar.e(aVar);
        C0146c b8 = cVar.b(pVar);
        if (b8.a().contains(a.DETECT_FRAGMENT_REUSE) && cVar.l(b8, pVar.getClass(), aVar.getClass())) {
            cVar.c(b8, aVar);
        }
    }

    public static final void g(p pVar, ViewGroup viewGroup) {
        k.e(pVar, "fragment");
        d dVar = new d(pVar, viewGroup);
        c cVar = f11528a;
        cVar.e(dVar);
        C0146c b8 = cVar.b(pVar);
        if (b8.a().contains(a.DETECT_FRAGMENT_TAG_USAGE) && cVar.l(b8, pVar.getClass(), dVar.getClass())) {
            cVar.c(b8, dVar);
        }
    }

    public static final void h(p pVar) {
        k.e(pVar, "fragment");
        e eVar = new e(pVar);
        c cVar = f11528a;
        cVar.e(eVar);
        C0146c b8 = cVar.b(pVar);
        if (b8.a().contains(a.DETECT_TARGET_FRAGMENT_USAGE) && cVar.l(b8, pVar.getClass(), eVar.getClass())) {
            cVar.c(b8, eVar);
        }
    }

    public static final void i(p pVar, ViewGroup viewGroup) {
        k.e(pVar, "fragment");
        k.e(viewGroup, "container");
        h hVar = new h(pVar, viewGroup);
        c cVar = f11528a;
        cVar.e(hVar);
        C0146c b8 = cVar.b(pVar);
        if (b8.a().contains(a.DETECT_WRONG_FRAGMENT_CONTAINER) && cVar.l(b8, pVar.getClass(), hVar.getClass())) {
            cVar.c(b8, hVar);
        }
    }

    public static final void j(p pVar, p pVar2, int i8) {
        k.e(pVar, "fragment");
        k.e(pVar2, "expectedParentFragment");
        i iVar = new i(pVar, pVar2, i8);
        c cVar = f11528a;
        cVar.e(iVar);
        C0146c b8 = cVar.b(pVar);
        if (b8.a().contains(a.DETECT_WRONG_NESTED_HIERARCHY) && cVar.l(b8, pVar.getClass(), iVar.getClass())) {
            cVar.c(b8, iVar);
        }
    }

    private final void k(p pVar, Runnable runnable) {
        if (pVar.U()) {
            Handler h8 = pVar.C().v0().h();
            if (!k.a(h8.getLooper(), Looper.myLooper())) {
                h8.post(runnable);
                return;
            }
        }
        runnable.run();
    }

    private final boolean l(C0146c c0146c, Class cls, Class cls2) {
        boolean o8;
        Set set = (Set) c0146c.c().get(cls.getName());
        if (set == null) {
            return true;
        }
        if (!k.a(cls2.getSuperclass(), g.class)) {
            o8 = v.o(set, cls2.getSuperclass());
            if (o8) {
                return false;
            }
        }
        return !set.contains(cls2);
    }
}
