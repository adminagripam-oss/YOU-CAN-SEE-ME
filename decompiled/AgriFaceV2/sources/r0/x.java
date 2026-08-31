package r0;

import android.util.Range;
import android.util.Rational;
import android.util.Size;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
/* loaded from: classes.dex */
class x {

    /* renamed from: b  reason: collision with root package name */
    private static final Map f11281b;

    /* renamed from: c  reason: collision with root package name */
    private static final Map f11282c;

    /* renamed from: a  reason: collision with root package name */
    private final Map f11283a = new HashMap();

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static abstract class a {
        static a c(v vVar, int i8) {
            return new i(vVar, i8);
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public abstract int a();

        /* JADX INFO: Access modifiers changed from: package-private */
        public abstract v b();
    }

    static {
        HashMap hashMap = new HashMap();
        f11281b = hashMap;
        hashMap.put(v.f11270d, Range.create(2160, 4319));
        hashMap.put(v.f11269c, Range.create(1080, 1439));
        hashMap.put(v.f11268b, Range.create(720, 1079));
        hashMap.put(v.f11267a, Range.create(241, 719));
        HashMap hashMap2 = new HashMap();
        f11282c = hashMap2;
        hashMap2.put(0, d0.a.f6351a);
        hashMap2.put(1, d0.a.f6353c);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public x(List list, Map map) {
        for (v vVar : f11281b.keySet()) {
            this.f11283a.put(a.c(vVar, -1), new ArrayList());
            for (Integer num : f11282c.keySet()) {
                this.f11283a.put(a.c(vVar, num.intValue()), new ArrayList());
            }
        }
        b(map);
        c(list);
        i(map);
    }

    private void b(Map map) {
        for (Map.Entry entry : map.entrySet()) {
            List f8 = f((v) entry.getKey(), -1);
            Objects.requireNonNull(f8);
            f8.add((Size) entry.getValue());
        }
    }

    private void c(List list) {
        Integer d8;
        Iterator it = list.iterator();
        while (it.hasNext()) {
            Size size = (Size) it.next();
            v e8 = e(size);
            if (e8 != null && (d8 = d(size)) != null) {
                List f8 = f(e8, d8.intValue());
                Objects.requireNonNull(f8);
                f8.add(size);
            }
        }
    }

    private static Integer d(Size size) {
        for (Map.Entry entry : f11282c.entrySet()) {
            if (d0.a.b(size, (Rational) entry.getValue(), k0.d.f8304b)) {
                return (Integer) entry.getKey();
            }
        }
        return null;
    }

    private static v e(Size size) {
        for (Map.Entry entry : f11281b.entrySet()) {
            if (((Range) entry.getValue()).contains((Range) Integer.valueOf(size.getHeight()))) {
                return (v) entry.getKey();
            }
        }
        return null;
    }

    private List f(v vVar, int i8) {
        return (List) this.f11283a.get(a.c(vVar, i8));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ int h(int i8, Size size, Size size2) {
        return Math.abs(k0.d.c(size) - i8) - Math.abs(k0.d.c(size2) - i8);
    }

    private void i(Map map) {
        for (Map.Entry entry : this.f11283a.entrySet()) {
            Size size = (Size) map.get(((a) entry.getKey()).b());
            if (size != null) {
                final int c8 = k0.d.c(size);
                Collections.sort((List) entry.getValue(), new Comparator() { // from class: r0.w
                    @Override // java.util.Comparator
                    public final int compare(Object obj, Object obj2) {
                        int h8;
                        h8 = x.h(c8, (Size) obj, (Size) obj2);
                        return h8;
                    }
                });
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public List g(v vVar, int i8) {
        List f8 = f(vVar, i8);
        return f8 != null ? new ArrayList(f8) : new ArrayList(0);
    }
}
