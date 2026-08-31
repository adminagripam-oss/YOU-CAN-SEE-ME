package m6;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import m6.t0;
import y.q;
/* loaded from: classes.dex */
public class c0 implements t0.n {

    /* renamed from: a  reason: collision with root package name */
    private final i6.b f9237a;

    /* renamed from: b  reason: collision with root package name */
    private final v4 f9238b;

    /* renamed from: c  reason: collision with root package name */
    public g0 f9239c = new g0();

    public c0(i6.b bVar, v4 v4Var) {
        this.f9237a = bVar;
        this.f9238b = v4Var;
    }

    @Override // m6.t0.n
    public void b(Long l8, Long l9) {
        q.a b8 = this.f9239c.b();
        if (l9 != null) {
            b8 = b8.b(l9.intValue());
        }
        this.f9238b.a(b8.a(), l8.longValue());
    }

    @Override // m6.t0.n
    public List g(Long l8, List list) {
        Object h8 = this.f9238b.h(l8.longValue());
        Objects.requireNonNull(h8);
        y.q qVar = (y.q) h8;
        ArrayList arrayList = new ArrayList();
        Iterator it = list.iterator();
        while (it.hasNext()) {
            Object h9 = this.f9238b.h(Long.valueOf(((Number) it.next()).longValue()).longValue());
            Objects.requireNonNull(h9);
            arrayList.add((y.p) h9);
        }
        List<y.p> b8 = qVar.b(arrayList);
        ArrayList arrayList2 = new ArrayList();
        for (y.p pVar : b8) {
            arrayList2.add(this.f9238b.g(pVar));
        }
        return arrayList2;
    }
}
