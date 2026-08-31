package r;

import android.hardware.camera2.CameraCharacteristics;
import android.os.Build;
import b0.h3;
import b0.u0;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import r.j4;
/* loaded from: classes.dex */
public abstract class h4 {

    /* renamed from: a  reason: collision with root package name */
    public static final u0.a f10507a = u0.a.a("camera2.streamSpec.streamUseCase", Long.TYPE);

    /* renamed from: b  reason: collision with root package name */
    private static final Map f10508b;

    /* renamed from: c  reason: collision with root package name */
    private static final Map f10509c;

    static {
        HashMap hashMap = new HashMap();
        f10508b = hashMap;
        HashMap hashMap2 = new HashMap();
        f10509c = hashMap2;
        if (Build.VERSION.SDK_INT >= 33) {
            HashSet hashSet = new HashSet();
            h3.b bVar = h3.b.PREVIEW;
            hashSet.add(bVar);
            h3.b bVar2 = h3.b.METERING_REPEATING;
            hashSet.add(bVar2);
            hashMap.put(4L, hashSet);
            HashSet hashSet2 = new HashSet();
            hashSet2.add(bVar);
            hashSet2.add(bVar2);
            hashSet2.add(h3.b.IMAGE_ANALYSIS);
            hashMap.put(1L, hashSet2);
            HashSet hashSet3 = new HashSet();
            h3.b bVar3 = h3.b.IMAGE_CAPTURE;
            hashSet3.add(bVar3);
            hashMap.put(2L, hashSet3);
            HashSet hashSet4 = new HashSet();
            h3.b bVar4 = h3.b.VIDEO_CAPTURE;
            hashSet4.add(bVar4);
            hashMap.put(3L, hashSet4);
            HashSet hashSet5 = new HashSet();
            hashSet5.add(bVar);
            hashSet5.add(bVar3);
            hashSet5.add(bVar4);
            hashMap2.put(4L, hashSet5);
            HashSet hashSet6 = new HashSet();
            hashSet6.add(bVar);
            hashSet6.add(bVar4);
            hashMap2.put(3L, hashSet6);
        }
    }

    public static boolean a(Map map, Map map2, List list) {
        for (int i8 = 0; i8 < list.size(); i8++) {
            long f8 = ((b0.w2) list.get(i8)).f();
            if (map.containsKey(Integer.valueOf(i8))) {
                b0.a aVar = (b0.a) map.get(Integer.valueOf(i8));
                if (!g(aVar.b().size() == 1 ? (h3.b) aVar.b().get(0) : h3.b.STREAM_SHARING, f8, aVar.b())) {
                    return false;
                }
            } else if (!map2.containsKey(Integer.valueOf(i8))) {
                throw new AssertionError("SurfaceConfig does not map to any use case");
            } else {
                b0.g3 g3Var = (b0.g3) map2.get(Integer.valueOf(i8));
                if (!g(g3Var.m(), f8, g3Var.m() == h3.b.STREAM_SHARING ? ((p0.j) g3Var).Z() : Collections.emptyList())) {
                    return false;
                }
            }
        }
        return true;
    }

    private static boolean b(Set set, Set set2) {
        Iterator it = set2.iterator();
        while (it.hasNext()) {
            if (!set.contains((Long) it.next())) {
                return false;
            }
        }
        return true;
    }

    public static boolean c(s.b0 b0Var, List list) {
        CameraCharacteristics.Key key;
        if (Build.VERSION.SDK_INT < 33) {
            return false;
        }
        key = CameraCharacteristics.SCALER_AVAILABLE_STREAM_USE_CASES;
        long[] jArr = (long[]) b0Var.a(key);
        if (jArr == null || jArr.length == 0) {
            return false;
        }
        HashSet hashSet = new HashSet();
        for (long j8 : jArr) {
            hashSet.add(Long.valueOf(j8));
        }
        Iterator it = list.iterator();
        while (it.hasNext()) {
            if (!hashSet.contains(Long.valueOf(((b0.w2) it.next()).f()))) {
                return false;
            }
        }
        return true;
    }

    public static boolean d(List list, List list2) {
        Iterator it = list.iterator();
        while (it.hasNext()) {
            b0.a aVar = (b0.a) it.next();
            if (j(aVar.e(), (h3.b) aVar.b().get(0))) {
                return true;
            }
        }
        Iterator it2 = list2.iterator();
        while (it2.hasNext()) {
            b0.g3 g3Var = (b0.g3) it2.next();
            if (j(g3Var, g3Var.m())) {
                return true;
            }
        }
        return false;
    }

    public static q.a e(b0.g3 g3Var) {
        b0.a2 d02 = b0.a2.d0();
        u0.a aVar = q.a.K;
        if (g3Var.a(aVar)) {
            d02.Y(aVar, (Long) g3Var.b(aVar));
        }
        u0.a aVar2 = b0.g3.f4206z;
        if (g3Var.a(aVar2)) {
            d02.Y(aVar2, (Boolean) g3Var.b(aVar2));
        }
        u0.a aVar3 = b0.n1.J;
        if (g3Var.a(aVar3)) {
            d02.Y(aVar3, (Integer) g3Var.b(aVar3));
        }
        u0.a aVar4 = b0.o1.f4299h;
        if (g3Var.a(aVar4)) {
            d02.Y(aVar4, (Integer) g3Var.b(aVar4));
        }
        return new q.a(d02);
    }

    private static b0.u0 f(b0.u0 u0Var, long j8) {
        u0.a aVar = f10507a;
        if (u0Var.a(aVar) && ((Long) u0Var.b(aVar)).longValue() == j8) {
            return null;
        }
        b0.a2 e02 = b0.a2.e0(u0Var);
        e02.Y(aVar, Long.valueOf(j8));
        return new q.a(e02);
    }

    private static boolean g(h3.b bVar, long j8, List list) {
        if (Build.VERSION.SDK_INT < 33) {
            return false;
        }
        if (bVar != h3.b.STREAM_SHARING) {
            Map map = f10508b;
            return map.containsKey(Long.valueOf(j8)) && ((Set) map.get(Long.valueOf(j8))).contains(bVar);
        }
        Map map2 = f10509c;
        if (map2.containsKey(Long.valueOf(j8))) {
            Set set = (Set) map2.get(Long.valueOf(j8));
            if (list.size() != set.size()) {
                return false;
            }
            Iterator it = list.iterator();
            while (it.hasNext()) {
                if (!set.contains((h3.b) it.next())) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    public static boolean h(s.b0 b0Var) {
        CameraCharacteristics.Key key;
        if (Build.VERSION.SDK_INT < 33) {
            return false;
        }
        key = CameraCharacteristics.SCALER_AVAILABLE_STREAM_USE_CASES;
        long[] jArr = (long[]) b0Var.a(key);
        return (jArr == null || jArr.length == 0) ? false : true;
    }

    /* JADX WARN: Code restructure failed: missing block: B:17:0x0058, code lost:
        if (r9 != false) goto L23;
     */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x005a, code lost:
        o();
     */
    /* JADX WARN: Code restructure failed: missing block: B:19:0x005d, code lost:
        r1 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x006d, code lost:
        if (r9 != false) goto L23;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static boolean i(java.util.List r9, java.util.List r10, java.util.Set r11) {
        /*
            java.util.HashSet r0 = new java.util.HashSet
            r0.<init>()
            java.util.Iterator r9 = r9.iterator()
            boolean r1 = r9.hasNext()
            r2 = 0
            r4 = 1
            r5 = 0
            if (r1 == 0) goto L3e
            java.lang.Object r9 = r9.next()
            b0.a r9 = (b0.a) r9
            b0.u0 r1 = r9.e()
            b0.u0$a r6 = q.a.K
            boolean r1 = r1.a(r6)
            if (r1 != 0) goto L28
        L25:
            r1 = r4
            r9 = r5
            goto L40
        L28:
            b0.u0 r9 = r9.e()
            java.lang.Object r9 = r9.b(r6)
            java.lang.Long r9 = (java.lang.Long) r9
            long r6 = r9.longValue()
            int r9 = (r6 > r2 ? 1 : (r6 == r2 ? 0 : -1))
            if (r9 != 0) goto L3b
            goto L25
        L3b:
            r9 = r4
            r1 = r5
            goto L40
        L3e:
            r9 = r5
            r1 = r9
        L40:
            java.util.Iterator r10 = r10.iterator()
        L44:
            boolean r6 = r10.hasNext()
            if (r6 == 0) goto L7e
            java.lang.Object r6 = r10.next()
            b0.g3 r6 = (b0.g3) r6
            b0.u0$a r7 = q.a.K
            boolean r8 = r6.a(r7)
            if (r8 != 0) goto L5f
            if (r9 == 0) goto L5d
        L5a:
            o()
        L5d:
            r1 = r4
            goto L44
        L5f:
            java.lang.Object r6 = r6.b(r7)
            java.lang.Long r6 = (java.lang.Long) r6
            long r6 = r6.longValue()
            int r8 = (r6 > r2 ? 1 : (r6 == r2 ? 0 : -1))
            if (r8 != 0) goto L70
            if (r9 == 0) goto L5d
            goto L5a
        L70:
            if (r1 == 0) goto L75
            o()
        L75:
            java.lang.Long r9 = java.lang.Long.valueOf(r6)
            r0.add(r9)
            r9 = r4
            goto L44
        L7e:
            if (r1 != 0) goto L87
            boolean r9 = b(r11, r0)
            if (r9 == 0) goto L87
            goto L88
        L87:
            r4 = r5
        L88:
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: r.h4.i(java.util.List, java.util.List, java.util.Set):boolean");
    }

    private static boolean j(b0.u0 u0Var, h3.b bVar) {
        if (((Boolean) u0Var.f(b0.g3.f4206z, Boolean.FALSE)).booleanValue()) {
            return false;
        }
        u0.a aVar = b0.n1.J;
        return u0Var.a(aVar) && w4.b(bVar, ((Integer) u0Var.b(aVar)).intValue()) == 5;
    }

    public static boolean k(s.b0 b0Var, List list, Map map, Map map2) {
        CameraCharacteristics.Key key;
        if (Build.VERSION.SDK_INT < 33) {
            return false;
        }
        ArrayList<b0.g3> arrayList = new ArrayList(map.keySet());
        Iterator it = list.iterator();
        while (it.hasNext()) {
            g1.e.j(((b0.a) it.next()).e());
        }
        for (b0.g3 g3Var : arrayList) {
            g1.e.j(((b0.u2) g1.e.j((b0.u2) map.get(g3Var))).d());
        }
        key = CameraCharacteristics.SCALER_AVAILABLE_STREAM_USE_CASES;
        long[] jArr = (long[]) b0Var.a(key);
        if (jArr != null && jArr.length != 0) {
            HashSet hashSet = new HashSet();
            for (long j8 : jArr) {
                hashSet.add(Long.valueOf(j8));
            }
            if (i(list, arrayList, hashSet)) {
                Iterator it2 = list.iterator();
                while (it2.hasNext()) {
                    b0.a aVar = (b0.a) it2.next();
                    b0.u0 e8 = aVar.e();
                    b0.u0 f8 = f(e8, ((Long) e8.b(q.a.K)).longValue());
                    if (f8 != null) {
                        map2.put(aVar, aVar.i(f8));
                    }
                }
                for (b0.g3 g3Var2 : arrayList) {
                    b0.u2 u2Var = (b0.u2) map.get(g3Var2);
                    b0.u0 d8 = u2Var.d();
                    b0.u0 f9 = f(d8, ((Long) d8.b(q.a.K)).longValue());
                    if (f9 != null) {
                        map.put(g3Var2, u2Var.g().d(f9).a());
                    }
                }
                return true;
            }
        }
        return false;
    }

    public static void l(Map map, Map map2, Map map3, Map map4, List list) {
        for (int i8 = 0; i8 < list.size(); i8++) {
            long f8 = ((b0.w2) list.get(i8)).f();
            if (map3.containsKey(Integer.valueOf(i8))) {
                b0.a aVar = (b0.a) map3.get(Integer.valueOf(i8));
                b0.u0 f9 = f(aVar.e(), f8);
                if (f9 != null) {
                    map2.put(aVar, aVar.i(f9));
                }
            } else if (!map4.containsKey(Integer.valueOf(i8))) {
                throw new AssertionError("SurfaceConfig does not map to any use case");
            } else {
                b0.g3 g3Var = (b0.g3) map4.get(Integer.valueOf(i8));
                b0.u2 u2Var = (b0.u2) map.get(g3Var);
                b0.u0 f10 = f(u2Var.d(), f8);
                if (f10 != null) {
                    map.put(g3Var, u2Var.g().d(f10).a());
                }
            }
        }
    }

    public static void m(Collection collection, Collection collection2, Map map) {
        boolean z7;
        ArrayList arrayList = new ArrayList(collection2);
        Iterator it = collection.iterator();
        while (true) {
            if (!it.hasNext()) {
                z7 = false;
                break;
            }
            b0.q2 q2Var = (b0.q2) it.next();
            b0.u0 f8 = q2Var.f();
            u0.a aVar = f10507a;
            if (f8.a(aVar) && q2Var.n().size() != 1) {
                y.f1.c("StreamUseCaseUtil", String.format("SessionConfig has stream use case but also contains %d surfaces, abort populateSurfaceToStreamUseCaseMapping().", Integer.valueOf(q2Var.n().size())));
                return;
            } else if (q2Var.f().a(aVar)) {
                z7 = true;
                break;
            }
        }
        if (z7) {
            Iterator it2 = collection.iterator();
            int i8 = 0;
            while (it2.hasNext()) {
                b0.q2 q2Var2 = (b0.q2) it2.next();
                if (((b0.g3) arrayList.get(i8)).m() == h3.b.METERING_REPEATING) {
                    g1.e.m(!q2Var2.n().isEmpty(), "MeteringRepeating should contain a surface");
                    map.put((b0.b1) q2Var2.n().get(0), 1L);
                } else {
                    b0.u0 f9 = q2Var2.f();
                    u0.a aVar2 = f10507a;
                    if (f9.a(aVar2) && !q2Var2.n().isEmpty()) {
                        map.put((b0.b1) q2Var2.n().get(0), (Long) q2Var2.f().b(aVar2));
                    }
                }
                i8++;
            }
        }
    }

    public static boolean n(j4.b bVar) {
        return bVar.a() == 0 && bVar.b() == 8;
    }

    private static void o() {
        throw new IllegalArgumentException("Either all use cases must have non-default stream use case assigned or none should have it");
    }
}
