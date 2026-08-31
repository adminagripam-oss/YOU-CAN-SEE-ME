package r;

import android.content.Context;
import android.graphics.SurfaceTexture;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.params.StreamConfigurationMap;
import android.media.CamcorderProfile;
import android.media.MediaRecorder;
import android.os.Build;
import android.util.Pair;
import android.util.Range;
import android.util.Rational;
import android.util.Size;
import b0.u2;
import b0.w2;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
/* loaded from: classes.dex */
final class j4 {

    /* renamed from: i  reason: collision with root package name */
    private final String f10550i;

    /* renamed from: j  reason: collision with root package name */
    private final f f10551j;

    /* renamed from: k  reason: collision with root package name */
    private final s.b0 f10552k;

    /* renamed from: l  reason: collision with root package name */
    private final v.f f10553l;

    /* renamed from: m  reason: collision with root package name */
    private final int f10554m;

    /* renamed from: n  reason: collision with root package name */
    private boolean f10555n;

    /* renamed from: o  reason: collision with root package name */
    private boolean f10556o;

    /* renamed from: p  reason: collision with root package name */
    private boolean f10557p;

    /* renamed from: q  reason: collision with root package name */
    private boolean f10558q;

    /* renamed from: r  reason: collision with root package name */
    private boolean f10559r;

    /* renamed from: s  reason: collision with root package name */
    private boolean f10560s;

    /* renamed from: t  reason: collision with root package name */
    b0.x2 f10561t;

    /* renamed from: v  reason: collision with root package name */
    private final d3 f10563v;

    /* renamed from: y  reason: collision with root package name */
    private final e3 f10566y;

    /* renamed from: a  reason: collision with root package name */
    private final List f10542a = new ArrayList();

    /* renamed from: b  reason: collision with root package name */
    private final List f10543b = new ArrayList();

    /* renamed from: c  reason: collision with root package name */
    private final List f10544c = new ArrayList();

    /* renamed from: d  reason: collision with root package name */
    private final List f10545d = new ArrayList();

    /* renamed from: e  reason: collision with root package name */
    private final Map f10546e = new HashMap();

    /* renamed from: f  reason: collision with root package name */
    private final List f10547f = new ArrayList();

    /* renamed from: g  reason: collision with root package name */
    private final List f10548g = new ArrayList();

    /* renamed from: h  reason: collision with root package name */
    private final List f10549h = new ArrayList();

    /* renamed from: u  reason: collision with root package name */
    List f10562u = new ArrayList();

    /* renamed from: w  reason: collision with root package name */
    private final v.x f10564w = new v.x();

    /* renamed from: x  reason: collision with root package name */
    private final v.t f10565x = new v.t();

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static class a {
        static Size[] a(StreamConfigurationMap streamConfigurationMap, int i8) {
            return streamConfigurationMap.getHighResolutionOutputSizes(i8);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static abstract class b {
        static b e(int i8, int i9, boolean z7, boolean z8) {
            return new e(i8, i9, z7, z8);
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public abstract int a();

        /* JADX INFO: Access modifiers changed from: package-private */
        public abstract int b();

        /* JADX INFO: Access modifiers changed from: package-private */
        public abstract boolean c();

        /* JADX INFO: Access modifiers changed from: package-private */
        public abstract boolean d();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public j4(Context context, String str, s.o0 o0Var, f fVar) {
        this.f10555n = false;
        this.f10556o = false;
        this.f10557p = false;
        this.f10558q = false;
        this.f10559r = false;
        this.f10560s = false;
        String str2 = (String) g1.e.j(str);
        this.f10550i = str2;
        this.f10551j = (f) g1.e.j(fVar);
        this.f10553l = new v.f();
        this.f10563v = d3.c(context);
        try {
            s.b0 c8 = o0Var.c(str2);
            this.f10552k = c8;
            Integer num = (Integer) c8.a(CameraCharacteristics.INFO_SUPPORTED_HARDWARE_LEVEL);
            this.f10554m = num != null ? num.intValue() : 2;
            int[] iArr = (int[]) c8.a(CameraCharacteristics.REQUEST_AVAILABLE_CAPABILITIES);
            if (iArr != null) {
                for (int i8 : iArr) {
                    if (i8 == 3) {
                        this.f10555n = true;
                    } else if (i8 == 6) {
                        this.f10556o = true;
                    } else if (Build.VERSION.SDK_INT >= 31 && i8 == 16) {
                        this.f10559r = true;
                    }
                }
            }
            e3 e3Var = new e3(this.f10552k);
            this.f10566y = e3Var;
            k();
            if (this.f10559r) {
                n();
            }
            boolean hasSystemFeature = context.getPackageManager().hasSystemFeature("android.hardware.camera.concurrent");
            this.f10557p = hasSystemFeature;
            if (hasSystemFeature) {
                h();
            }
            if (e3Var.d()) {
                g();
            }
            if (J()) {
                m();
            }
            boolean h8 = h4.h(this.f10552k);
            this.f10558q = h8;
            if (h8) {
                j();
            }
            boolean a8 = b5.a(this.f10552k);
            this.f10560s = a8;
            if (a8) {
                i();
            }
            l();
            b();
        } catch (s.g e8) {
            throw n2.a(e8);
        }
    }

    private List B(b bVar) {
        List list;
        if (this.f10546e.containsKey(bVar)) {
            return (List) this.f10546e.get(bVar);
        }
        List arrayList = new ArrayList();
        if (bVar.d()) {
            if (bVar.a() == 0) {
                list = this.f10548g;
                arrayList.addAll(list);
            }
            this.f10546e.put(bVar, arrayList);
            return arrayList;
        } else if (bVar.b() != 8) {
            if (bVar.b() == 10 && bVar.a() == 0) {
                list = this.f10547f;
                arrayList.addAll(list);
            }
            this.f10546e.put(bVar, arrayList);
            return arrayList;
        } else {
            int a8 = bVar.a();
            if (a8 == 1) {
                arrayList = this.f10544c;
                this.f10546e.put(bVar, arrayList);
                return arrayList;
            }
            if (a8 == 2) {
                arrayList.addAll(this.f10543b);
            } else if (bVar.c()) {
                list = this.f10545d;
                arrayList.addAll(list);
                this.f10546e.put(bVar, arrayList);
                return arrayList;
            }
            list = this.f10542a;
            arrayList.addAll(list);
            this.f10546e.put(bVar, arrayList);
            return arrayList;
        }
    }

    private Pair C(int i8, List list, List list2, List list3, List list4, int i9, Map map, Map map2) {
        ArrayList arrayList = new ArrayList();
        Iterator it = list.iterator();
        while (it.hasNext()) {
            b0.a aVar = (b0.a) it.next();
            arrayList.add(aVar.g());
            if (map != null) {
                map.put(Integer.valueOf(arrayList.size() - 1), aVar);
            }
        }
        for (int i10 = 0; i10 < list2.size(); i10++) {
            Size size = (Size) list2.get(i10);
            b0.g3 g3Var = (b0.g3) list3.get(((Integer) list4.get(i10)).intValue());
            int B = g3Var.B();
            arrayList.add(b0.w2.h(i8, B, size, F(B)));
            if (map2 != null) {
                map2.put(Integer.valueOf(arrayList.size() - 1), g3Var);
            }
            i9 = E(i9, g3Var.B(), size);
        }
        return new Pair(arrayList, Integer.valueOf(i9));
    }

    private Range D(List list, List list2, List list3) {
        Iterator it = list.iterator();
        Range range = null;
        while (it.hasNext()) {
            range = G(((b0.a) it.next()).h(), range);
        }
        Iterator it2 = list3.iterator();
        while (it2.hasNext()) {
            range = G(((b0.g3) list2.get(((Integer) it2.next()).intValue())).p(null), range);
        }
        return range;
    }

    private int E(int i8, int i9, Size size) {
        return Math.min(i8, q(this.f10552k, i9, size));
    }

    private Range G(Range range, Range range2) {
        if (range2 == null) {
            return range;
        }
        if (range != null) {
            try {
                return range2.intersect(range);
            } catch (IllegalArgumentException unused) {
            }
        }
        return range2;
    }

    private static List H(List list) {
        ArrayList arrayList = new ArrayList();
        ArrayList<Integer> arrayList2 = new ArrayList();
        Iterator it = list.iterator();
        while (it.hasNext()) {
            int v7 = ((b0.g3) it.next()).v(0);
            if (!arrayList2.contains(Integer.valueOf(v7))) {
                arrayList2.add(Integer.valueOf(v7));
            }
        }
        Collections.sort(arrayList2);
        Collections.reverse(arrayList2);
        for (Integer num : arrayList2) {
            int intValue = num.intValue();
            Iterator it2 = list.iterator();
            while (it2.hasNext()) {
                b0.g3 g3Var = (b0.g3) it2.next();
                if (intValue == g3Var.v(0)) {
                    arrayList.add(Integer.valueOf(list.indexOf(g3Var)));
                }
            }
        }
        return arrayList;
    }

    private static boolean I(List list, Map map) {
        Iterator it = list.iterator();
        while (it.hasNext()) {
            if (((b0.a) it.next()).d() == 4101) {
                return true;
            }
        }
        for (b0.g3 g3Var : map.keySet()) {
            if (g3Var.B() == 4101) {
                return true;
            }
        }
        return false;
    }

    private boolean J() {
        int[] b8 = this.f10552k.b().b();
        if (b8 == null) {
            return false;
        }
        for (int i8 : b8) {
            if (i8 == 4101) {
                return true;
            }
        }
        return false;
    }

    private boolean K(b bVar, List list, Map map) {
        ArrayList arrayList = new ArrayList();
        Iterator it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(((b0.a) it.next()).g());
        }
        d0.e eVar = new d0.e();
        for (b0.g3 g3Var : map.keySet()) {
            List list2 = (List) map.get(g3Var);
            boolean z7 = (list2 == null || list2.isEmpty()) ? false : true;
            g1.e.b(z7, "No available output size is found for " + g3Var + ".");
            int B = g3Var.B();
            arrayList.add(b0.w2.h(bVar.a(), B, (Size) Collections.min(list2, eVar), F(B)));
        }
        return c(bVar, arrayList);
    }

    private void L() {
        this.f10563v.g();
        if (this.f10561t == null) {
            l();
            return;
        }
        this.f10561t = b0.x2.a(this.f10561t.b(), this.f10561t.j(), this.f10563v.f(), this.f10561t.h(), this.f10561t.f(), this.f10561t.d(), this.f10561t.l());
    }

    private void N(Map map, int i8) {
        Size r8 = r(this.f10552k.b().d(), i8, true);
        if (r8 != null) {
            map.put(Integer.valueOf(i8), r8);
        }
    }

    private void O(Map map, Size size, int i8) {
        if (this.f10557p) {
            Size r8 = r(this.f10552k.b().d(), i8, false);
            Integer valueOf = Integer.valueOf(i8);
            if (r8 != null) {
                size = (Size) Collections.min(Arrays.asList(size, r8), new d0.e());
            }
            map.put(valueOf, size);
        }
    }

    private void P(Map map, int i8) {
        CameraCharacteristics.Key key;
        if (Build.VERSION.SDK_INT < 31 || !this.f10559r) {
            return;
        }
        s.b0 b0Var = this.f10552k;
        key = CameraCharacteristics.SCALER_STREAM_CONFIGURATION_MAP_MAXIMUM_RESOLUTION;
        StreamConfigurationMap streamConfigurationMap = (StreamConfigurationMap) b0Var.a(key);
        if (streamConfigurationMap == null) {
            return;
        }
        map.put(Integer.valueOf(i8), r(streamConfigurationMap, i8, true));
    }

    private void b() {
    }

    private static Range d(Range range, Range range2, Range range3) {
        double v7 = v(range2.intersect(range));
        double v8 = v(range3.intersect(range));
        double v9 = v8 / v(range3);
        double v10 = v7 / v(range2);
        int i8 = (v8 > v7 ? 1 : (v8 == v7 ? 0 : -1));
        if (i8 > 0) {
            if (v9 >= 0.5d || v9 >= v10) {
                return range3;
            }
        } else if (i8 == 0) {
            int i9 = (v9 > v10 ? 1 : (v9 == v10 ? 0 : -1));
            if (i9 > 0) {
                return range3;
            }
            if (i9 == 0 && ((Integer) range3.getLower()).intValue() > ((Integer) range2.getLower()).intValue()) {
                return range3;
            }
        } else if (v10 < 0.5d && v9 > v10) {
            return range3;
        }
        return range2;
    }

    private b e(int i8, Map map, boolean z7, boolean z8) {
        int z9 = z(map);
        if (i8 == 0 || !z8) {
            if (i8 == 0 || z9 != 10) {
                return b.e(i8, z9, z7, z8);
            }
            throw new IllegalArgumentException(String.format("Camera device id is %s. 10 bit dynamic range is not currently supported in %s camera mode.", this.f10550i, b0.i0.a(i8)));
        }
        throw new IllegalArgumentException(String.format("Camera device id is %s. Ultra HDR is not currently supported in %s camera mode.", this.f10550i, b0.i0.a(i8)));
    }

    private Map f(Map map, b bVar, Range range) {
        HashMap hashMap = new HashMap();
        for (b0.g3 g3Var : map.keySet()) {
            ArrayList arrayList = new ArrayList();
            HashMap hashMap2 = new HashMap();
            for (Size size : (List) map.get(g3Var)) {
                int B = g3Var.B();
                w2.a c8 = b0.w2.h(bVar.a(), B, size, F(B)).c();
                int q8 = range != null ? q(this.f10552k, B, size) : Integer.MAX_VALUE;
                Set set = (Set) hashMap2.get(c8);
                if (set == null) {
                    set = new HashSet();
                    hashMap2.put(c8, set);
                }
                if (!set.contains(Integer.valueOf(q8))) {
                    arrayList.add(size);
                    set.add(Integer.valueOf(q8));
                }
            }
            hashMap.put(g3Var, arrayList);
        }
        return hashMap;
    }

    private void g() {
        this.f10547f.addAll(a4.b());
    }

    private void h() {
        this.f10544c.addAll(a4.d());
    }

    private void i() {
        if (Build.VERSION.SDK_INT >= 33) {
            this.f10545d.addAll(a4.i());
        }
    }

    private void j() {
        if (Build.VERSION.SDK_INT >= 33) {
            this.f10549h.addAll(a4.k());
        }
    }

    private void k() {
        this.f10542a.addAll(a4.a(this.f10554m, this.f10555n, this.f10556o));
        this.f10542a.addAll(this.f10553l.a(this.f10550i));
    }

    private void l() {
        this.f10561t = b0.x2.a(k0.d.f8305c, new HashMap(), this.f10563v.f(), new HashMap(), w(), new HashMap(), new HashMap());
    }

    private void m() {
        this.f10548g.addAll(a4.l());
    }

    private void n() {
        this.f10543b.addAll(a4.m());
    }

    private List o(List list) {
        Iterator it = list.iterator();
        int i8 = 1;
        while (it.hasNext()) {
            i8 *= ((List) it.next()).size();
        }
        if (i8 != 0) {
            ArrayList arrayList = new ArrayList();
            for (int i9 = 0; i9 < i8; i9++) {
                arrayList.add(new ArrayList());
            }
            int size = i8 / ((List) list.get(0)).size();
            int i10 = i8;
            for (int i11 = 0; i11 < list.size(); i11++) {
                List list2 = (List) list.get(i11);
                for (int i12 = 0; i12 < i8; i12++) {
                    ((List) arrayList.get(i12)).add((Size) list2.get((i12 % i10) / size));
                }
                if (i11 < list.size() - 1) {
                    i10 = size;
                    size /= ((List) list.get(i11 + 1)).size();
                }
            }
            return arrayList;
        }
        throw new IllegalArgumentException("Failed to find supported resolutions.");
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r5v1 */
    /* JADX WARN: Type inference failed for: r9v0, types: [android.util.Range] */
    private Range p(Range range, int i8) {
        if (range != 0) {
            Range range2 = b0.u2.f4433a;
            if (!range.equals(range2)) {
                Range[] rangeArr = (Range[]) this.f10552k.a(CameraCharacteristics.CONTROL_AE_AVAILABLE_TARGET_FPS_RANGES);
                if (rangeArr == null) {
                    return range2;
                }
                Range range3 = new Range(Integer.valueOf(Math.min(((Integer) range.getLower()).intValue(), i8)), Integer.valueOf(Math.min(((Integer) range.getUpper()).intValue(), i8)));
                int i9 = 0;
                for (Range range4 : rangeArr) {
                    if (i8 >= ((Integer) range4.getLower()).intValue()) {
                        if (range2.equals(b0.u2.f4433a)) {
                            range2 = range4;
                        }
                        if (range4.equals(range3)) {
                            return range4;
                        }
                        try {
                            int v7 = v(range4.intersect(range3));
                            if (i9 == 0) {
                                i9 = v7;
                            } else {
                                if (v7 >= i9) {
                                    range2 = d(range3, range2, range4);
                                    i9 = v(range3.intersect(range2));
                                }
                                range4 = range2;
                            }
                        } catch (IllegalArgumentException unused) {
                            if (i9 == 0) {
                                if (u(range4, range3) >= u(range2, range3)) {
                                    if (u(range4, range3) == u(range2, range3)) {
                                        if (((Integer) range4.getLower()).intValue() <= ((Integer) range2.getUpper()).intValue() && v(range4) >= v(range2)) {
                                        }
                                    }
                                }
                            }
                        }
                        range2 = range4;
                    }
                }
                return range2;
            }
        }
        return b0.u2.f4433a;
    }

    static int q(s.b0 b0Var, int i8, Size size) {
        try {
            return (int) (1.0E9d / ((StreamConfigurationMap) b0Var.a(CameraCharacteristics.SCALER_STREAM_CONFIGURATION_MAP)).getOutputMinFrameDuration(i8, size));
        } catch (Exception unused) {
            return 0;
        }
    }

    private Size r(StreamConfigurationMap streamConfigurationMap, int i8, boolean z7) {
        Size[] a8;
        Size[] outputSizes = i8 == 34 ? streamConfigurationMap.getOutputSizes(SurfaceTexture.class) : streamConfigurationMap.getOutputSizes(i8);
        if (outputSizes == null || outputSizes.length == 0) {
            return null;
        }
        d0.e eVar = new d0.e();
        Size size = (Size) Collections.max(Arrays.asList(outputSizes), eVar);
        Size size2 = k0.d.f8303a;
        if (z7 && (a8 = a.a(streamConfigurationMap, i8)) != null && a8.length > 0) {
            size2 = (Size) Collections.max(Arrays.asList(a8), eVar);
        }
        return (Size) Collections.max(Arrays.asList(size, size2), eVar);
    }

    private int s(List list) {
        Iterator it = list.iterator();
        int i8 = Integer.MAX_VALUE;
        while (it.hasNext()) {
            b0.a aVar = (b0.a) it.next();
            i8 = E(i8, aVar.d(), aVar.f());
        }
        return i8;
    }

    private static int u(Range range, Range range2) {
        g1.e.m((range.contains((Range) ((Integer) range2.getUpper())) || range.contains((Range) ((Integer) range2.getLower()))) ? false : true, "Ranges must not intersect");
        return ((Integer) range.getLower()).intValue() > ((Integer) range2.getUpper()).intValue() ? ((Integer) range.getLower()).intValue() - ((Integer) range2.getUpper()).intValue() : ((Integer) range2.getLower()).intValue() - ((Integer) range.getUpper()).intValue();
    }

    private static int v(Range range) {
        return (((Integer) range.getUpper()).intValue() - ((Integer) range.getLower()).intValue()) + 1;
    }

    private Size w() {
        try {
            int parseInt = Integer.parseInt(this.f10550i);
            CamcorderProfile a8 = this.f10551j.b(parseInt, 1) ? this.f10551j.a(parseInt, 1) : null;
            return a8 != null ? new Size(a8.videoFrameWidth, a8.videoFrameHeight) : x(parseInt);
        } catch (NumberFormatException unused) {
            return y();
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:22:0x004a  */
    /* JADX WARN: Removed duplicated region for block: B:24:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private android.util.Size x(int r4) {
        /*
            r3 = this;
            android.util.Size r0 = k0.d.f8306d
            r.f r1 = r3.f10551j
            r2 = 10
            boolean r1 = r1.b(r4, r2)
            if (r1 == 0) goto L13
        Lc:
            r.f r1 = r3.f10551j
            android.media.CamcorderProfile r4 = r1.a(r4, r2)
            goto L48
        L13:
            r.f r1 = r3.f10551j
            r2 = 8
            boolean r1 = r1.b(r4, r2)
            if (r1 == 0) goto L1e
            goto Lc
        L1e:
            r.f r1 = r3.f10551j
            r2 = 12
            boolean r1 = r1.b(r4, r2)
            if (r1 == 0) goto L29
            goto Lc
        L29:
            r.f r1 = r3.f10551j
            r2 = 6
            boolean r1 = r1.b(r4, r2)
            if (r1 == 0) goto L33
            goto Lc
        L33:
            r.f r1 = r3.f10551j
            r2 = 5
            boolean r1 = r1.b(r4, r2)
            if (r1 == 0) goto L3d
            goto Lc
        L3d:
            r.f r1 = r3.f10551j
            r2 = 4
            boolean r1 = r1.b(r4, r2)
            if (r1 == 0) goto L47
            goto Lc
        L47:
            r4 = 0
        L48:
            if (r4 == 0) goto L53
            android.util.Size r0 = new android.util.Size
            int r1 = r4.videoFrameWidth
            int r4 = r4.videoFrameHeight
            r0.<init>(r1, r4)
        L53:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: r.j4.x(int):android.util.Size");
    }

    private Size y() {
        Size[] outputSizes = this.f10552k.b().d().getOutputSizes(MediaRecorder.class);
        if (outputSizes == null) {
            return k0.d.f8306d;
        }
        Arrays.sort(outputSizes, new d0.e(true));
        for (Size size : outputSizes) {
            int width = size.getWidth();
            Size size2 = k0.d.f8308f;
            if (width <= size2.getWidth() && size.getHeight() <= size2.getHeight()) {
                return size;
            }
        }
        return k0.d.f8306d;
    }

    private static int z(Map map) {
        for (y.a0 a0Var : map.values()) {
            if (a0Var.a() == 10) {
                return 10;
            }
        }
        return 8;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Pair A(int i8, List list, Map map, boolean z7, boolean z8) {
        Map map2;
        HashMap hashMap;
        HashMap hashMap2;
        Range range;
        List list2;
        Map map3;
        int i9;
        String str;
        String str2;
        Map map4;
        List list3;
        String str3;
        String str4;
        Map map5;
        Map map6;
        List list4;
        List list5;
        HashMap hashMap3;
        int i10;
        int i11;
        String str5;
        L();
        List arrayList = new ArrayList(map.keySet());
        List<Integer> H = H(arrayList);
        Map g8 = this.f10566y.g(list, arrayList, H);
        b e8 = e(i8, g8, z7, I(list, map));
        boolean K = K(e8, list, map);
        String str6 = ".  May be attempting to bind too many use cases. Existing surfaces: ";
        String str7 = " New configs: ";
        String str8 = "No supported surface combination is found for camera device - Id : ";
        if (!K) {
            throw new IllegalArgumentException("No supported surface combination is found for camera device - Id : " + this.f10550i + ".  May be attempting to bind too many use cases. Existing surfaces: " + list + " New configs: " + arrayList);
        }
        Range D = D(list, arrayList, H);
        Map f8 = f(map, e8, D);
        List arrayList2 = new ArrayList();
        for (Integer num : H) {
            b0.g3 g3Var = (b0.g3) arrayList.get(num.intValue());
            arrayList2.add(a((List) f8.get(g3Var), g3Var.B()));
        }
        List o8 = o(arrayList2);
        HashMap hashMap4 = new HashMap();
        HashMap hashMap5 = new HashMap();
        Map hashMap6 = new HashMap();
        Map hashMap7 = new HashMap();
        boolean d8 = h4.d(list, arrayList);
        int s8 = s(list);
        Map map7 = hashMap7;
        if (!this.f10558q || d8) {
            map2 = hashMap6;
            hashMap = hashMap5;
            hashMap2 = hashMap4;
            range = D;
            list2 = H;
            map3 = g8;
            i9 = s8;
            str = "No supported surface combination is found for camera device - Id : ";
            str2 = " New configs: ";
            map4 = map7;
            list3 = null;
        } else {
            Iterator it = o8.iterator();
            List list6 = null;
            while (true) {
                if (!it.hasNext()) {
                    hashMap = hashMap5;
                    hashMap2 = hashMap4;
                    range = D;
                    list2 = H;
                    map3 = g8;
                    i9 = s8;
                    str = str8;
                    str2 = str7;
                    str5 = str6;
                    map4 = map7;
                    map2 = hashMap6;
                    break;
                }
                Map map8 = map7;
                Map map9 = hashMap6;
                hashMap = hashMap5;
                hashMap2 = hashMap4;
                map3 = g8;
                Range range2 = D;
                List list7 = H;
                range = range2;
                str = str8;
                int i12 = s8;
                i9 = s8;
                str2 = str7;
                list2 = H;
                str5 = str6;
                list6 = t(e8, (List) C(i8, list, (List) it.next(), arrayList, list7, i12, map9, map8).first);
                map2 = map9;
                map4 = map8;
                if (list6 != null && !h4.a(map2, map4, list6)) {
                    list6 = null;
                }
                if (list6 != null) {
                    if (h4.c(this.f10552k, list6)) {
                        break;
                    }
                    list6 = null;
                }
                map2.clear();
                map4.clear();
                map7 = map4;
                hashMap6 = map2;
                str6 = str5;
                str8 = str;
                str7 = str2;
                g8 = map3;
                hashMap5 = hashMap;
                hashMap4 = hashMap2;
                D = range;
                s8 = i9;
                H = list2;
            }
            if (list6 == null && !K) {
                throw new IllegalArgumentException(str + this.f10550i + str5 + list + str2 + arrayList);
            }
            list3 = list6;
        }
        Iterator it2 = o8.iterator();
        boolean z9 = false;
        int i13 = Integer.MAX_VALUE;
        int i14 = Integer.MAX_VALUE;
        boolean z10 = false;
        boolean z11 = false;
        List list8 = null;
        List list9 = null;
        while (true) {
            if (!it2.hasNext()) {
                str3 = str;
                str4 = str2;
                map5 = map4;
                map6 = map2;
                list4 = list8;
                list5 = list9;
                break;
            }
            List list10 = (List) it2.next();
            int i15 = i13;
            int i16 = i14;
            str4 = str2;
            map5 = map4;
            str3 = str;
            map6 = map2;
            Pair C = C(i8, list, list10, arrayList, list2, i9, null, null);
            List list11 = (List) C.first;
            i14 = ((Integer) C.second).intValue();
            int i17 = i9;
            boolean z12 = range == null || i17 <= i14 || i14 >= ((Integer) range.getLower()).intValue();
            if (z10 || !c(e8, list11)) {
                i10 = i16;
                i11 = Integer.MAX_VALUE;
            } else {
                i10 = i16;
                i11 = Integer.MAX_VALUE;
                if (i10 == Integer.MAX_VALUE || i10 < i14) {
                    i10 = i14;
                    list8 = list10;
                }
                if (z12) {
                    if (z11) {
                        list5 = list9;
                        list4 = list10;
                        i13 = i15;
                        break;
                    }
                    i10 = i14;
                    z10 = true;
                    list8 = list10;
                }
            }
            if (list3 == null || z11 || t(e8, list11) == null) {
                i13 = i15;
            } else {
                if (i15 != i11 && i15 >= i14) {
                    i13 = i15;
                } else {
                    i13 = i14;
                    list9 = list10;
                }
                if (z12) {
                    i13 = i14;
                    if (z10) {
                        i14 = i10;
                        list4 = list8;
                        list5 = list10;
                        break;
                    }
                    z11 = true;
                    list9 = list10;
                } else {
                    continue;
                }
            }
            i9 = i17;
            i14 = i10;
            map2 = map6;
            map4 = map5;
            str = str3;
            str2 = str4;
        }
        if (list4 == null) {
            throw new IllegalArgumentException(str3 + this.f10550i + " and Hardware level: " + this.f10554m + ". May be the specified resolution is too large and not supported. Existing surfaces: " + list + str4 + arrayList);
        }
        Range p8 = range != null ? p(range, i14) : null;
        Iterator it3 = arrayList.iterator();
        while (it3.hasNext()) {
            b0.g3 g3Var2 = (b0.g3) it3.next();
            List list12 = list2;
            Map map10 = map3;
            Iterator it4 = it3;
            u2.a f9 = b0.u2.a((Size) list4.get(list12.indexOf(Integer.valueOf(arrayList.indexOf(g3Var2))))).b((y.a0) g1.e.j((y.a0) map10.get(g3Var2))).d(h4.e(g3Var2)).f(z8);
            if (p8 != null) {
                f9.c(p8);
            }
            hashMap.put(g3Var2, f9.a());
            it3 = it4;
            list2 = list12;
            map3 = map10;
            p8 = p8;
        }
        HashMap hashMap8 = hashMap;
        if (list3 != null && i14 == i13 && list4.size() == list5.size()) {
            int i18 = 0;
            while (true) {
                if (i18 >= list4.size()) {
                    break;
                } else if (!((Size) list4.get(i18)).equals(list5.get(i18))) {
                    z9 = true;
                    break;
                } else {
                    i18++;
                }
            }
            if (!z9) {
                hashMap3 = hashMap2;
                if (!h4.k(this.f10552k, list, hashMap8, hashMap3)) {
                    h4.l(hashMap8, hashMap3, map6, map5, list3);
                }
                return new Pair(hashMap8, hashMap3);
            }
        }
        hashMap3 = hashMap2;
        return new Pair(hashMap8, hashMap3);
    }

    b0.x2 F(int i8) {
        if (!this.f10562u.contains(Integer.valueOf(i8))) {
            O(this.f10561t.j(), k0.d.f8307e, i8);
            O(this.f10561t.h(), k0.d.f8309g, i8);
            N(this.f10561t.d(), i8);
            P(this.f10561t.l(), i8);
            this.f10562u.add(Integer.valueOf(i8));
        }
        return this.f10561t;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public b0.w2 M(int i8, int i9, Size size) {
        return b0.w2.h(i8, i9, size, F(i9));
    }

    List a(List list, int i8) {
        Rational rational;
        int a8 = this.f10564w.a(this.f10550i, this.f10552k);
        if (a8 == 0) {
            rational = d0.a.f6351a;
        } else if (a8 == 1) {
            rational = d0.a.f6353c;
        } else if (a8 != 2) {
            rational = null;
        } else {
            Size c8 = F(256).c(256);
            rational = new Rational(c8.getWidth(), c8.getHeight());
        }
        if (rational != null) {
            ArrayList arrayList = new ArrayList();
            ArrayList arrayList2 = new ArrayList();
            Iterator it = list.iterator();
            while (it.hasNext()) {
                Size size = (Size) it.next();
                if (d0.a.a(size, rational)) {
                    arrayList.add(size);
                } else {
                    arrayList2.add(size);
                }
            }
            arrayList2.addAll(0, arrayList);
            list = arrayList2;
        }
        return this.f10565x.a(b0.w2.e(i8), list);
    }

    boolean c(b bVar, List list) {
        boolean z7 = false;
        for (b0.v2 v2Var : B(bVar)) {
            if (v2Var.d(list) != null) {
                z7 = true;
                continue;
            } else {
                z7 = false;
                continue;
            }
            if (z7) {
                break;
            }
        }
        return z7;
    }

    List t(b bVar, List list) {
        if (h4.n(bVar)) {
            for (b0.v2 v2Var : this.f10549h) {
                List d8 = v2Var.d(list);
                if (d8 != null) {
                    return d8;
                }
            }
            return null;
        }
        return null;
    }
}
