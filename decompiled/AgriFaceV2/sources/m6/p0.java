package m6;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import m6.t0;
import y.d0;
/* loaded from: classes.dex */
public class p0 implements t0.d0 {

    /* renamed from: a  reason: collision with root package name */
    private final v4 f9421a;

    /* renamed from: b  reason: collision with root package name */
    private final a f9422b;

    /* loaded from: classes.dex */
    public static class a {
        public y.d0 a(List list, List list2, Boolean bool) {
            if (list.size() < 1 || list.size() == list2.size()) {
                y.i1 i1Var = (y.i1) list.get(0);
                Integer num = (Integer) list2.get(0);
                d0.a b8 = num == null ? b(i1Var) : c(i1Var, num.intValue());
                for (int i8 = 1; i8 < list.size(); i8++) {
                    y.i1 i1Var2 = (y.i1) list.get(i8);
                    Integer num2 = (Integer) list2.get(i8);
                    if (num2 == null) {
                        b8.a(i1Var2);
                    } else {
                        b8.b(i1Var2, num2.intValue());
                    }
                }
                if (bool != null && bool.booleanValue()) {
                    b8.d();
                }
                return b8.c();
            }
            throw new IllegalArgumentException("One metering point must be specified and the number of specified metering points must match the number of specified metering point modes.");
        }

        public d0.a b(y.i1 i1Var) {
            return new d0.a(i1Var);
        }

        public d0.a c(y.i1 i1Var, int i8) {
            return new d0.a(i1Var, i8);
        }
    }

    public p0(v4 v4Var) {
        this(v4Var, new a());
    }

    @Override // m6.t0.d0
    public void b(Long l8, List list, Boolean bool) {
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        Iterator it = list.iterator();
        while (it.hasNext()) {
            t0.v0 v0Var = (t0.v0) it.next();
            arrayList.add((y.i1) this.f9421a.h(v0Var.c().longValue()));
            Long b8 = v0Var.b();
            arrayList2.add(b8 == null ? null : Integer.valueOf(b8.intValue()));
        }
        this.f9421a.a(this.f9422b.a(arrayList, arrayList2, bool), l8.longValue());
    }

    p0(v4 v4Var, a aVar) {
        this.f9421a = v4Var;
        this.f9422b = aVar;
    }
}
