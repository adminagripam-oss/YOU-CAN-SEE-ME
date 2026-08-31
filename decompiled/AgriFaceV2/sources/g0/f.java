package g0;

import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.SurfaceTexture;
import android.util.Log;
import android.util.Pair;
import android.util.Size;
import android.view.Surface;
import androidx.appcompat.app.w;
import b0.a2;
import b0.d0;
import b0.e0;
import b0.g0;
import b0.g3;
import b0.h0;
import b0.h3;
import b0.l1;
import b0.n1;
import b0.n2;
import b0.o2;
import b0.u0;
import b0.u2;
import b0.z;
import d0.r;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import y.a0;
import y.e1;
import y.f1;
import y.g2;
import y.h2;
import y.i2;
import y.p;
import y.p1;
import y.u0;
/* loaded from: classes.dex */
public final class f implements y.j {

    /* renamed from: e  reason: collision with root package name */
    private final h0 f7062e;

    /* renamed from: f  reason: collision with root package name */
    private final h0 f7063f;

    /* renamed from: g  reason: collision with root package name */
    private final e0 f7064g;

    /* renamed from: h  reason: collision with root package name */
    private final h3 f7065h;

    /* renamed from: i  reason: collision with root package name */
    private final b f7066i;

    /* renamed from: l  reason: collision with root package name */
    private final z.a f7069l;

    /* renamed from: n  reason: collision with root package name */
    private final z f7071n;

    /* renamed from: r  reason: collision with root package name */
    private h2 f7075r;

    /* renamed from: s  reason: collision with root package name */
    private p0.h f7076s;

    /* renamed from: t  reason: collision with root package name */
    private final n2 f7077t;

    /* renamed from: u  reason: collision with root package name */
    private final o2 f7078u;

    /* renamed from: v  reason: collision with root package name */
    private final o2 f7079v;

    /* renamed from: w  reason: collision with root package name */
    private final e1 f7080w;

    /* renamed from: x  reason: collision with root package name */
    private final e1 f7081x;

    /* renamed from: j  reason: collision with root package name */
    private final List f7067j = new ArrayList();

    /* renamed from: k  reason: collision with root package name */
    private final List f7068k = new ArrayList();

    /* renamed from: m  reason: collision with root package name */
    private List f7070m = Collections.emptyList();

    /* renamed from: o  reason: collision with root package name */
    private final Object f7072o = new Object();

    /* renamed from: p  reason: collision with root package name */
    private boolean f7073p = true;

    /* renamed from: q  reason: collision with root package name */
    private u0 f7074q = null;

    /* loaded from: classes.dex */
    public static final class a extends Exception {
        public a(Throwable th) {
            super(th);
        }
    }

    /* loaded from: classes.dex */
    public static abstract class b {
        public static b a(String str, l1 l1Var) {
            return new g0.a(str, l1Var);
        }

        public abstract l1 b();

        public abstract String c();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class c {

        /* renamed from: a  reason: collision with root package name */
        g3 f7082a;

        /* renamed from: b  reason: collision with root package name */
        g3 f7083b;

        c(g3 g3Var, g3 g3Var2) {
            this.f7082a = g3Var;
            this.f7083b = g3Var2;
        }
    }

    public f(h0 h0Var, h0 h0Var2, o2 o2Var, o2 o2Var2, e1 e1Var, e1 e1Var2, z.a aVar, e0 e0Var, h3 h3Var) {
        this.f7062e = h0Var;
        this.f7063f = h0Var2;
        this.f7080w = e1Var;
        this.f7081x = e1Var2;
        this.f7069l = aVar;
        this.f7064g = e0Var;
        this.f7065h = h3Var;
        z p8 = o2Var.p();
        this.f7071n = p8;
        p8.s(null);
        this.f7077t = new n2(h0Var.p(), null);
        this.f7078u = o2Var;
        this.f7079v = o2Var2;
        this.f7066i = A(o2Var, o2Var2);
    }

    public static b A(o2 o2Var, o2 o2Var2) {
        StringBuilder sb = new StringBuilder();
        sb.append(o2Var.g());
        sb.append(o2Var2 == null ? "" : o2Var2.g());
        return b.a(sb.toString(), o2Var.p().G());
    }

    private static g3 B(h3 h3Var, p0.h hVar) {
        g3 k8 = new p1.a().e().k(false, h3Var);
        if (k8 == null) {
            return null;
        }
        a2 e02 = a2.e0(k8);
        e02.f0(m.G);
        return hVar.A(e02).d();
    }

    private int D() {
        synchronized (this.f7072o) {
            return this.f7069l.a() == 2 ? 1 : 0;
        }
    }

    private static Map E(Collection collection, h3 h3Var, h3 h3Var2) {
        HashMap hashMap = new HashMap();
        Iterator it = collection.iterator();
        while (it.hasNext()) {
            h2 h2Var = (h2) it.next();
            hashMap.put(h2Var, new c(p0.h.r0(h2Var) ? B(h3Var, (p0.h) h2Var) : h2Var.k(false, h3Var), h2Var.k(true, h3Var2)));
        }
        return hashMap;
    }

    private int G(boolean z7) {
        int i8;
        synchronized (this.f7072o) {
            Iterator it = this.f7070m.iterator();
            if (it.hasNext()) {
                w.a(it.next());
                throw null;
            }
            i8 = z7 ? 0 | 3 : 0;
        }
        return i8;
    }

    private Set H(Collection collection, boolean z7) {
        HashSet hashSet = new HashSet();
        int G = G(z7);
        Iterator it = collection.iterator();
        while (it.hasNext()) {
            h2 h2Var = (h2) it.next();
            g1.e.b(!p0.h.r0(h2Var), "Only support one level of sharing for now.");
            if (h2Var.C(G)) {
                hashSet.add(h2Var);
            }
        }
        return hashSet;
    }

    private boolean J() {
        synchronized (this.f7072o) {
            this.f7071n.s(null);
        }
        return false;
    }

    /* JADX WARN: Removed duplicated region for block: B:8:0x002e  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static boolean K(b0.u2 r4, b0.q2 r5) {
        /*
            b0.u0 r4 = r4.d()
            b0.u0 r0 = r5.f()
            java.util.Set r1 = r4.d()
            int r1 = r1.size()
            b0.u0 r5 = r5.f()
            java.util.Set r5 = r5.d()
            int r5 = r5.size()
            r2 = 1
            if (r1 == r5) goto L20
            return r2
        L20:
            java.util.Set r5 = r4.d()
            java.util.Iterator r5 = r5.iterator()
        L28:
            boolean r1 = r5.hasNext()
            if (r1 == 0) goto L49
            java.lang.Object r1 = r5.next()
            b0.u0$a r1 = (b0.u0.a) r1
            boolean r3 = r0.a(r1)
            if (r3 == 0) goto L48
            java.lang.Object r3 = r0.b(r1)
            java.lang.Object r1 = r4.b(r1)
            boolean r1 = java.util.Objects.equals(r3, r1)
            if (r1 != 0) goto L28
        L48:
            return r2
        L49:
            r4 = 0
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: g0.f.K(b0.u2, b0.q2):boolean");
    }

    private static boolean L(Collection collection) {
        Iterator it = collection.iterator();
        while (it.hasNext()) {
            if (S(((h2) it.next()).j().o())) {
                return true;
            }
        }
        return false;
    }

    private static boolean M(Collection collection) {
        Iterator it = collection.iterator();
        while (it.hasNext()) {
            h2 h2Var = (h2) it.next();
            if (R(h2Var)) {
                g3 j8 = h2Var.j();
                u0.a aVar = n1.N;
                if (j8.a(aVar) && ((Integer) g1.e.j((Integer) j8.b(aVar))).intValue() == 1) {
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean N(Collection collection) {
        Iterator it = collection.iterator();
        while (it.hasNext()) {
            if (V((h2) it.next())) {
                return true;
            }
        }
        return false;
    }

    private boolean O() {
        boolean z7;
        synchronized (this.f7072o) {
            z7 = true;
            if (this.f7071n.X() != 1) {
                z7 = false;
            }
        }
        return z7;
    }

    private static boolean P(Collection collection) {
        Iterator it = collection.iterator();
        boolean z7 = false;
        boolean z8 = false;
        while (it.hasNext()) {
            h2 h2Var = (h2) it.next();
            if (T(h2Var) || p0.h.r0(h2Var)) {
                z7 = true;
            } else if (R(h2Var)) {
                z8 = true;
            }
        }
        return z7 && !z8;
    }

    private static boolean Q(Collection collection) {
        Iterator it = collection.iterator();
        boolean z7 = false;
        boolean z8 = false;
        while (it.hasNext()) {
            h2 h2Var = (h2) it.next();
            if (T(h2Var) || p0.h.r0(h2Var)) {
                z8 = true;
            } else if (R(h2Var)) {
                z7 = true;
            }
        }
        return z7 && !z8;
    }

    private static boolean R(h2 h2Var) {
        return h2Var instanceof y.u0;
    }

    private static boolean S(a0 a0Var) {
        return (a0Var.a() == 10) || (a0Var.b() != 1 && a0Var.b() != 0);
    }

    private static boolean T(h2 h2Var) {
        return h2Var instanceof p1;
    }

    static boolean U(Collection collection) {
        int[] iArr = {1, 2, 4};
        HashSet hashSet = new HashSet();
        Iterator it = collection.iterator();
        while (it.hasNext()) {
            h2 h2Var = (h2) it.next();
            for (int i8 = 0; i8 < 3; i8++) {
                int i9 = iArr[i8];
                if (h2Var.C(i9)) {
                    if (hashSet.contains(Integer.valueOf(i9))) {
                        return false;
                    }
                    hashSet.add(Integer.valueOf(i9));
                }
            }
        }
        return true;
    }

    private static boolean V(h2 h2Var) {
        if (h2Var != null) {
            if (h2Var.j().a(g3.B)) {
                return h2Var.j().m() == h3.b.VIDEO_CAPTURE;
            }
            Log.e("CameraUseCaseAdapter", h2Var + " UseCase does not have capture type.");
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void W(Surface surface, SurfaceTexture surfaceTexture, g2.g gVar) {
        surface.release();
        surfaceTexture.release();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void X(g2 g2Var) {
        final SurfaceTexture surfaceTexture = new SurfaceTexture(0);
        surfaceTexture.setDefaultBufferSize(g2Var.o().getWidth(), g2Var.o().getHeight());
        surfaceTexture.detachFromGLContext();
        final Surface surface = new Surface(surfaceTexture);
        g2Var.C(surface, e0.c.b(), new g1.a() { // from class: g0.e
            @Override // g1.a
            public final void accept(Object obj) {
                f.W(surface, surfaceTexture, (g2.g) obj);
            }
        });
    }

    private void Z() {
        synchronized (this.f7072o) {
            if (this.f7074q != null) {
                this.f7062e.p().l(this.f7074q);
            }
        }
    }

    private static List b0(List list, Collection collection) {
        ArrayList arrayList = new ArrayList(list);
        Iterator it = collection.iterator();
        while (it.hasNext()) {
            ((h2) it.next()).T(null);
            Iterator it2 = list.iterator();
            if (it2.hasNext()) {
                w.a(it2.next());
                throw null;
            }
        }
        return arrayList;
    }

    static void d0(List list, Collection collection, Collection collection2) {
        List b02 = b0(list, collection);
        ArrayList arrayList = new ArrayList(collection2);
        arrayList.removeAll(collection);
        List b03 = b0(b02, arrayList);
        if (b03.size() > 0) {
            f1.l("CameraUseCaseAdapter", "Unused effects: " + b03);
        }
    }

    private void f0(Map map, Collection collection) {
        synchronized (this.f7072o) {
            Iterator it = collection.iterator();
            while (it.hasNext()) {
                h2 h2Var = (h2) it.next();
                h2Var.U(t(this.f7062e.p().m(), ((u2) g1.e.j((u2) map.get(h2Var))).e()));
            }
        }
    }

    private void o() {
        synchronized (this.f7072o) {
            d0 p8 = this.f7062e.p();
            this.f7074q = p8.e();
            p8.j();
        }
    }

    static Collection r(Collection collection, h2 h2Var, p0.h hVar) {
        ArrayList arrayList = new ArrayList(collection);
        if (h2Var != null) {
            arrayList.add(h2Var);
        }
        if (hVar != null) {
            arrayList.add(hVar);
            arrayList.removeAll(hVar.l0());
        }
        return arrayList;
    }

    private h2 s(Collection collection, p0.h hVar) {
        h2 h2Var;
        synchronized (this.f7072o) {
            ArrayList arrayList = new ArrayList(collection);
            if (hVar != null) {
                arrayList.add(hVar);
                arrayList.removeAll(hVar.l0());
            }
            if (O()) {
                if (Q(arrayList)) {
                    if (!T(this.f7075r)) {
                        h2Var = x();
                    }
                } else if (P(arrayList)) {
                    h2Var = R(this.f7075r) ? this.f7075r : w();
                }
            }
            h2Var = null;
        }
        return h2Var;
    }

    private static Matrix t(Rect rect, Size size) {
        g1.e.b(rect.width() > 0 && rect.height() > 0, "Cannot compute viewport crop rects zero sized sensor rect.");
        RectF rectF = new RectF(rect);
        Matrix matrix = new Matrix();
        matrix.setRectToRect(new RectF(0.0f, 0.0f, size.getWidth(), size.getHeight()), rectF, Matrix.ScaleToFit.CENTER);
        matrix.invert(matrix);
        return matrix;
    }

    private Map u(int i8, g0 g0Var, Collection collection, Collection collection2, Map map) {
        Rect rect;
        boolean z7;
        ArrayList arrayList = new ArrayList();
        String g8 = g0Var.g();
        HashMap hashMap = new HashMap();
        HashMap hashMap2 = new HashMap();
        Iterator it = collection2.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            h2 h2Var = (h2) it.next();
            b0.a a8 = b0.a.a(this.f7064g.a(i8, g8, h2Var.m(), h2Var.f()), h2Var.m(), h2Var.f(), ((u2) g1.e.j(h2Var.e())).b(), p0.h.j0(h2Var), h2Var.e().d(), h2Var.j().p(null));
            arrayList.add(a8);
            hashMap2.put(a8, h2Var);
            hashMap.put(h2Var, h2Var.e());
        }
        if (!collection.isEmpty()) {
            HashMap hashMap3 = new HashMap();
            HashMap hashMap4 = new HashMap();
            try {
                rect = this.f7062e.p().m();
            } catch (NullPointerException unused) {
                rect = null;
            }
            k kVar = new k(g0Var, rect != null ? r.l(rect) : null);
            Iterator it2 = collection.iterator();
            loop1: while (true) {
                z7 = false;
                while (it2.hasNext()) {
                    h2 h2Var2 = (h2) it2.next();
                    c cVar = (c) map.get(h2Var2);
                    g3 E = h2Var2.E(g0Var, cVar.f7082a, cVar.f7083b);
                    hashMap3.put(E, h2Var2);
                    hashMap4.put(E, kVar.m(E));
                    if (h2Var2.j() instanceof b0.g2) {
                        if (((b0.g2) h2Var2.j()).D() == 2) {
                            z7 = true;
                        }
                    }
                }
            }
            Pair b8 = this.f7064g.b(i8, g8, arrayList, hashMap4, z7, N(collection));
            for (Map.Entry entry : hashMap3.entrySet()) {
                hashMap.put((h2) entry.getValue(), (u2) ((Map) b8.first).get(entry.getKey()));
            }
            for (Map.Entry entry2 : ((Map) b8.second).entrySet()) {
                if (hashMap2.containsKey(entry2.getKey())) {
                    hashMap.put((h2) hashMap2.get(entry2.getKey()), (u2) entry2.getValue());
                }
            }
        }
        return hashMap;
    }

    private void v(Collection collection) {
        if (J()) {
            if (L(collection)) {
                throw new IllegalArgumentException("Extensions are only supported for use with standard dynamic range.");
            }
            if (M(collection)) {
                throw new IllegalArgumentException("Extensions are not supported for use with Ultra HDR image capture.");
            }
        }
        synchronized (this.f7072o) {
            if (!this.f7070m.isEmpty() && M(collection)) {
                throw new IllegalArgumentException("Ultra HDR image capture does not support for use with CameraEffect.");
            }
        }
    }

    private y.u0 w() {
        return new u0.b().p("ImageCapture-Extra").e();
    }

    private p1 x() {
        p1 e8 = new p1.a().n("Preview-Extra").e();
        e8.n0(new p1.c() { // from class: g0.d
            @Override // y.p1.c
            public final void a(g2 g2Var) {
                f.X(g2Var);
            }
        });
        return e8;
    }

    private p0.h y(Collection collection, boolean z7) {
        synchronized (this.f7072o) {
            Set H = H(collection, z7);
            if (H.size() >= 2 || (J() && N(H))) {
                p0.h hVar = this.f7076s;
                if (hVar != null && hVar.l0().equals(H)) {
                    p0.h hVar2 = this.f7076s;
                    Objects.requireNonNull(hVar2);
                    return hVar2;
                } else if (U(H)) {
                    return new p0.h(this.f7062e, this.f7063f, this.f7080w, this.f7081x, H, this.f7065h);
                } else {
                    return null;
                }
            }
            return null;
        }
    }

    public b C() {
        return this.f7066i;
    }

    public p F() {
        return this.f7079v;
    }

    public List I() {
        ArrayList arrayList;
        synchronized (this.f7072o) {
            arrayList = new ArrayList(this.f7067j);
        }
        return arrayList;
    }

    public void Y(Collection collection) {
        synchronized (this.f7072o) {
            LinkedHashSet linkedHashSet = new LinkedHashSet(this.f7067j);
            linkedHashSet.removeAll(collection);
            h0 h0Var = this.f7063f;
            boolean z7 = true;
            boolean z8 = h0Var != null;
            if (h0Var == null) {
                z7 = false;
            }
            e0(linkedHashSet, z8, z7);
        }
    }

    @Override // y.j
    public y.k a() {
        return this.f7077t;
    }

    public void a0(List list) {
        synchronized (this.f7072o) {
            this.f7070m = list;
        }
    }

    @Override // y.j
    public p b() {
        return this.f7078u;
    }

    public void c(boolean z7) {
        this.f7062e.c(z7);
    }

    public void c0(i2 i2Var) {
        synchronized (this.f7072o) {
        }
    }

    void e0(Collection collection, boolean z7, boolean z8) {
        Map map;
        u2 u2Var;
        b0.u0 d8;
        synchronized (this.f7072o) {
            v(collection);
            if (!z7 && J() && N(collection)) {
                e0(collection, true, z8);
                return;
            }
            p0.h y7 = y(collection, z7);
            h2 s8 = s(collection, y7);
            Collection r8 = r(collection, s8, y7);
            ArrayList<h2> arrayList = new ArrayList(r8);
            arrayList.removeAll(this.f7068k);
            ArrayList<h2> arrayList2 = new ArrayList(r8);
            arrayList2.retainAll(this.f7068k);
            ArrayList<h2> arrayList3 = new ArrayList(this.f7068k);
            arrayList3.removeAll(r8);
            Map E = E(arrayList, this.f7071n.l(), this.f7065h);
            Map emptyMap = Collections.emptyMap();
            try {
                Map map2 = E;
                Map u7 = u(D(), this.f7062e.l(), arrayList, arrayList2, map2);
                if (this.f7063f != null) {
                    int D = D();
                    h0 h0Var = this.f7063f;
                    Objects.requireNonNull(h0Var);
                    map = u7;
                    emptyMap = u(D, h0Var.l(), arrayList, arrayList2, map2);
                } else {
                    map = u7;
                }
                Map map3 = emptyMap;
                f0(map, r8);
                d0(this.f7070m, r8, collection);
                for (h2 h2Var : arrayList3) {
                    h2Var.X(this.f7062e);
                }
                this.f7062e.h(arrayList3);
                if (this.f7063f != null) {
                    for (h2 h2Var2 : arrayList3) {
                        h0 h0Var2 = this.f7063f;
                        Objects.requireNonNull(h0Var2);
                        h2Var2.X(h0Var2);
                    }
                    h0 h0Var3 = this.f7063f;
                    Objects.requireNonNull(h0Var3);
                    h0Var3.h(arrayList3);
                }
                if (arrayList3.isEmpty()) {
                    for (h2 h2Var3 : arrayList2) {
                        if (map.containsKey(h2Var3) && (d8 = (u2Var = (u2) map.get(h2Var3)).d()) != null && K(u2Var, h2Var3.x())) {
                            h2Var3.a0(d8);
                            if (this.f7073p) {
                                this.f7062e.f(h2Var3);
                                h0 h0Var4 = this.f7063f;
                                if (h0Var4 != null) {
                                    Objects.requireNonNull(h0Var4);
                                    h0Var4.f(h2Var3);
                                }
                            }
                        }
                    }
                }
                for (h2 h2Var4 : arrayList) {
                    Map map4 = map2;
                    c cVar = (c) map4.get(h2Var4);
                    Objects.requireNonNull(cVar);
                    h0 h0Var5 = this.f7063f;
                    if (h0Var5 != null) {
                        h0 h0Var6 = this.f7062e;
                        Objects.requireNonNull(h0Var5);
                        h2Var4.b(h0Var6, h0Var5, cVar.f7082a, cVar.f7083b);
                        h2Var4.Z((u2) g1.e.j((u2) map.get(h2Var4)), (u2) map3.get(h2Var4));
                    } else {
                        h2Var4.b(this.f7062e, null, cVar.f7082a, cVar.f7083b);
                        h2Var4.Z((u2) g1.e.j((u2) map.get(h2Var4)), null);
                    }
                    map2 = map4;
                }
                if (this.f7073p) {
                    this.f7062e.e(arrayList);
                    h0 h0Var7 = this.f7063f;
                    if (h0Var7 != null) {
                        Objects.requireNonNull(h0Var7);
                        h0Var7.e(arrayList);
                    }
                }
                for (h2 h2Var5 : arrayList) {
                    h2Var5.I();
                }
                this.f7067j.clear();
                this.f7067j.addAll(collection);
                this.f7068k.clear();
                this.f7068k.addAll(r8);
                this.f7075r = s8;
                this.f7076s = y7;
            } catch (IllegalArgumentException e8) {
                if (z7 || J() || this.f7069l.a() == 2) {
                    throw e8;
                }
                e0(collection, true, z8);
            }
        }
    }

    public void m(Collection collection) {
        synchronized (this.f7072o) {
            this.f7062e.g(this.f7071n);
            h0 h0Var = this.f7063f;
            if (h0Var != null) {
                h0Var.g(this.f7071n);
            }
            LinkedHashSet linkedHashSet = new LinkedHashSet(this.f7067j);
            linkedHashSet.addAll(collection);
            try {
                h0 h0Var2 = this.f7063f;
                boolean z7 = true;
                boolean z8 = h0Var2 != null;
                if (h0Var2 == null) {
                    z7 = false;
                }
                e0(linkedHashSet, z8, z7);
            } catch (IllegalArgumentException e8) {
                throw new a(e8);
            }
        }
    }

    public void n() {
        synchronized (this.f7072o) {
            if (!this.f7073p) {
                if (!this.f7068k.isEmpty()) {
                    this.f7062e.g(this.f7071n);
                    h0 h0Var = this.f7063f;
                    if (h0Var != null) {
                        h0Var.g(this.f7071n);
                    }
                }
                this.f7062e.e(this.f7068k);
                h0 h0Var2 = this.f7063f;
                if (h0Var2 != null) {
                    h0Var2.e(this.f7068k);
                }
                Z();
                for (h2 h2Var : this.f7068k) {
                    h2Var.I();
                }
                this.f7073p = true;
            }
        }
    }

    public void z() {
        synchronized (this.f7072o) {
            if (this.f7073p) {
                this.f7062e.h(new ArrayList(this.f7068k));
                h0 h0Var = this.f7063f;
                if (h0Var != null) {
                    h0Var.h(new ArrayList(this.f7068k));
                }
                o();
                this.f7073p = false;
            }
        }
    }
}
