package y;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
/* loaded from: classes.dex */
public final class q {

    /* renamed from: c  reason: collision with root package name */
    public static final q f12883c = new a().b(0).a();

    /* renamed from: d  reason: collision with root package name */
    public static final q f12884d = new a().b(1).a();

    /* renamed from: a  reason: collision with root package name */
    private final LinkedHashSet f12885a;

    /* renamed from: b  reason: collision with root package name */
    private final String f12886b;

    /* loaded from: classes.dex */
    public static final class a {

        /* renamed from: a  reason: collision with root package name */
        private final LinkedHashSet f12887a = new LinkedHashSet();

        /* renamed from: b  reason: collision with root package name */
        private String f12888b;

        public q a() {
            return new q(this.f12887a, this.f12888b);
        }

        public a b(int i8) {
            g1.e.m(i8 != -1, "The specified lens facing is invalid.");
            this.f12887a.add(new b0.s1(i8));
            return this;
        }
    }

    q(LinkedHashSet linkedHashSet, String str) {
        this.f12885a = linkedHashSet;
        this.f12886b = str;
    }

    public LinkedHashSet a(LinkedHashSet linkedHashSet) {
        ArrayList arrayList = new ArrayList();
        Iterator it = linkedHashSet.iterator();
        while (it.hasNext()) {
            arrayList.add(((b0.h0) it.next()).b());
        }
        List b8 = b(arrayList);
        LinkedHashSet linkedHashSet2 = new LinkedHashSet();
        Iterator it2 = linkedHashSet.iterator();
        while (it2.hasNext()) {
            b0.h0 h0Var = (b0.h0) it2.next();
            if (b8.contains(h0Var.b())) {
                linkedHashSet2.add(h0Var);
            }
        }
        return linkedHashSet2;
    }

    public List b(List list) {
        List arrayList = new ArrayList(list);
        Iterator it = this.f12885a.iterator();
        while (it.hasNext()) {
            arrayList = ((o) it.next()).b(Collections.unmodifiableList(arrayList));
        }
        arrayList.retainAll(list);
        return arrayList;
    }

    public LinkedHashSet c() {
        return this.f12885a;
    }

    public Integer d() {
        Iterator it = this.f12885a.iterator();
        Integer num = null;
        while (it.hasNext()) {
            o oVar = (o) it.next();
            if (oVar instanceof b0.s1) {
                Integer valueOf = Integer.valueOf(((b0.s1) oVar).c());
                if (num == null) {
                    num = valueOf;
                } else if (!num.equals(valueOf)) {
                    throw new IllegalStateException("Multiple conflicting lens facing requirements exist.");
                }
            }
        }
        return num;
    }

    public b0.h0 e(LinkedHashSet linkedHashSet) {
        Iterator it = a(linkedHashSet).iterator();
        if (it.hasNext()) {
            return (b0.h0) it.next();
        }
        throw new IllegalArgumentException("No available camera can be found");
    }
}
