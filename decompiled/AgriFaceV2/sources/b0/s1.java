package b0;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
/* loaded from: classes.dex */
public class s1 implements y.o {

    /* renamed from: b  reason: collision with root package name */
    private final int f4390b;

    public s1(int i8) {
        this.f4390b = i8;
    }

    @Override // y.o
    public List b(List list) {
        ArrayList arrayList = new ArrayList();
        Iterator it = list.iterator();
        while (it.hasNext()) {
            y.p pVar = (y.p) it.next();
            g1.e.b(pVar instanceof g0, "The camera info doesn't contain internal implementation.");
            if (pVar.d() == this.f4390b) {
                arrayList.add(pVar);
            }
        }
        return arrayList;
    }

    public int c() {
        return this.f4390b;
    }
}
