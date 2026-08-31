package r;

import android.hardware.camera2.CameraCharacteristics;
import android.os.Build;
import android.text.TextUtils;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
/* loaded from: classes.dex */
final class e3 {

    /* renamed from: a  reason: collision with root package name */
    private final s.b0 f10458a;

    /* renamed from: b  reason: collision with root package name */
    private final t.g f10459b;

    /* renamed from: c  reason: collision with root package name */
    private final boolean f10460c;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static final class a {
        static y.a0 a(s.b0 b0Var) {
            Long l8 = (Long) b0Var.a(CameraCharacteristics.REQUEST_RECOMMENDED_TEN_BIT_DYNAMIC_RANGE_PROFILE);
            if (l8 != null) {
                return t.d.b(l8.longValue());
            }
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public e3(s.b0 b0Var) {
        this.f10458a = b0Var;
        this.f10459b = t.g.a(b0Var);
        int[] iArr = (int[]) b0Var.a(CameraCharacteristics.REQUEST_AVAILABLE_CAPABILITIES);
        boolean z7 = false;
        if (iArr != null) {
            int length = iArr.length;
            int i8 = 0;
            while (true) {
                if (i8 >= length) {
                    break;
                } else if (iArr[i8] == 18) {
                    z7 = true;
                    break;
                } else {
                    i8++;
                }
            }
        }
        this.f10460c = z7;
    }

    private static boolean a(y.a0 a0Var, y.a0 a0Var2) {
        g1.e.m(a0Var2.e(), "Fully specified range is not actually fully specified.");
        if (a0Var.b() == 2 && a0Var2.b() == 1) {
            return false;
        }
        if (a0Var.b() == 2 || a0Var.b() == 0 || a0Var.b() == a0Var2.b()) {
            return a0Var.a() == 0 || a0Var.a() == a0Var2.a();
        }
        return false;
    }

    private static boolean b(y.a0 a0Var, y.a0 a0Var2, Set set) {
        if (set.contains(a0Var2)) {
            return a(a0Var, a0Var2);
        }
        y.f1.a("DynamicRangeResolver", String.format("Candidate Dynamic range is not within constraints.\nDynamic range to resolve:\n  %s\nCandidate dynamic range:\n  %s", a0Var, a0Var2));
        return false;
    }

    private static y.a0 c(y.a0 a0Var, Collection collection, Set set) {
        if (a0Var.b() == 1) {
            return null;
        }
        Iterator it = collection.iterator();
        while (it.hasNext()) {
            y.a0 a0Var2 = (y.a0) it.next();
            g1.e.k(a0Var2, "Fully specified DynamicRange cannot be null.");
            int b8 = a0Var2.b();
            g1.e.m(a0Var2.e(), "Fully specified DynamicRange must have fully defined encoding.");
            if (b8 != 1 && b(a0Var, a0Var2, set)) {
                return a0Var2;
            }
        }
        return null;
    }

    private static boolean e(y.a0 a0Var) {
        return Objects.equals(a0Var, y.a0.f12729c);
    }

    private static boolean f(y.a0 a0Var) {
        return a0Var.b() == 2 || (a0Var.b() != 0 && a0Var.a() == 0) || (a0Var.b() == 0 && a0Var.a() != 0);
    }

    private y.a0 h(y.a0 a0Var, Set set, Set set2, Set set3, String str) {
        String format;
        y.a0 a0Var2;
        if (a0Var.e()) {
            if (set.contains(a0Var)) {
                return a0Var;
            }
            return null;
        }
        int b8 = a0Var.b();
        int a8 = a0Var.a();
        if (b8 == 1 && a8 == 0) {
            y.a0 a0Var3 = y.a0.f12730d;
            if (set.contains(a0Var3)) {
                return a0Var3;
            }
            return null;
        }
        y.a0 c8 = c(a0Var, set2, set);
        if (c8 != null) {
            format = String.format("Resolved dynamic range for use case %s from existing attached surface.\n%s\n->\n%s", str, a0Var, c8);
        } else {
            c8 = c(a0Var, set3, set);
            if (c8 != null) {
                format = String.format("Resolved dynamic range for use case %s from concurrently bound use case.\n%s\n->\n%s", str, a0Var, c8);
            } else {
                c8 = y.a0.f12730d;
                if (!b(a0Var, c8, set)) {
                    if (b8 == 2 && (a8 == 10 || a8 == 0)) {
                        LinkedHashSet linkedHashSet = new LinkedHashSet();
                        if (Build.VERSION.SDK_INT >= 33) {
                            a0Var2 = a.a(this.f10458a);
                            if (a0Var2 != null) {
                                linkedHashSet.add(a0Var2);
                            }
                        } else {
                            a0Var2 = null;
                        }
                        linkedHashSet.add(y.a0.f12732f);
                        y.a0 c9 = c(a0Var, linkedHashSet, set);
                        if (c9 != null) {
                            Object[] objArr = new Object[4];
                            objArr[0] = str;
                            objArr[1] = c9.equals(a0Var2) ? "recommended" : "required";
                            objArr[2] = a0Var;
                            objArr[3] = c9;
                            y.f1.a("DynamicRangeResolver", String.format("Resolved dynamic range for use case %s from %s 10-bit supported dynamic range.\n%s\n->\n%s", objArr));
                            return c9;
                        }
                    }
                    Iterator it = set.iterator();
                    while (it.hasNext()) {
                        c8 = (y.a0) it.next();
                        g1.e.m(c8.e(), "Candidate dynamic range must be fully specified.");
                        if (!c8.equals(y.a0.f12730d) && a(a0Var, c8)) {
                            format = String.format("Resolved dynamic range for use case %s from validated dynamic range constraints or supported HDR dynamic ranges.\n%s\n->\n%s", str, a0Var, c8);
                        }
                    }
                    return null;
                }
                format = String.format("Resolved dynamic range for use case %s to no compatible HDR dynamic ranges.\n%s\n->\n%s", str, a0Var, c8);
            }
        }
        y.f1.a("DynamicRangeResolver", format);
        return c8;
    }

    private y.a0 i(Set set, Set set2, Set set3, b0.g3 g3Var, Set set4) {
        y.a0 o8 = g3Var.o();
        y.a0 h8 = h(o8, set4, set2, set3, g3Var.r());
        if (h8 != null) {
            j(set4, h8, this.f10459b);
            return h8;
        }
        throw new IllegalArgumentException(String.format("Unable to resolve supported dynamic range. The dynamic range may not be supported on the device or may not be allowed concurrently with other attached use cases.\nUse case:\n  %s\nRequested dynamic range:\n  %s\nSupported dynamic ranges:\n  %s\nConstrained set of concurrent dynamic ranges:\n  %s", g3Var.r(), o8, TextUtils.join("\n  ", set), TextUtils.join("\n  ", set4)));
    }

    private static void j(Set set, y.a0 a0Var, t.g gVar) {
        g1.e.m(!set.isEmpty(), "Cannot update already-empty constraints.");
        Set b8 = gVar.b(a0Var);
        if (b8.isEmpty()) {
            return;
        }
        HashSet hashSet = new HashSet(set);
        set.retainAll(b8);
        if (set.isEmpty()) {
            throw new IllegalArgumentException(String.format("Constraints of dynamic range cannot be combined with existing constraints.\nDynamic range:\n  %s\nConstraints:\n  %s\nExisting constraints:\n  %s", a0Var, TextUtils.join("\n  ", b8), TextUtils.join("\n  ", hashSet)));
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean d() {
        return this.f10460c;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Map g(List list, List list2, List list3) {
        Set<y.a0> linkedHashSet = new LinkedHashSet();
        Iterator it = list.iterator();
        while (it.hasNext()) {
            linkedHashSet.add(((b0.a) it.next()).c());
        }
        Set c8 = this.f10459b.c();
        Set hashSet = new HashSet(c8);
        for (y.a0 a0Var : linkedHashSet) {
            j(hashSet, a0Var, this.f10459b);
        }
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        ArrayList arrayList3 = new ArrayList();
        Iterator it2 = list3.iterator();
        while (it2.hasNext()) {
            b0.g3 g3Var = (b0.g3) list2.get(((Integer) it2.next()).intValue());
            y.a0 o8 = g3Var.o();
            if (e(o8)) {
                arrayList3.add(g3Var);
            } else if (f(o8)) {
                arrayList2.add(g3Var);
            } else {
                arrayList.add(g3Var);
            }
        }
        HashMap hashMap = new HashMap();
        Set linkedHashSet2 = new LinkedHashSet();
        ArrayList<b0.g3> arrayList4 = new ArrayList();
        arrayList4.addAll(arrayList);
        arrayList4.addAll(arrayList2);
        arrayList4.addAll(arrayList3);
        for (b0.g3 g3Var2 : arrayList4) {
            y.a0 i8 = i(c8, linkedHashSet, linkedHashSet2, g3Var2, hashSet);
            hashMap.put(g3Var2, i8);
            if (!linkedHashSet.contains(i8)) {
                linkedHashSet2.add(i8);
            }
        }
        return hashMap;
    }
}
