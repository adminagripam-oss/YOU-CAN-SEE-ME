package i7;

import d7.k;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import s6.m;
import s6.n;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public abstract class h extends g {
    public static List c(b bVar) {
        List b8;
        List f8;
        k.e(bVar, "<this>");
        Iterator it = bVar.iterator();
        if (!it.hasNext()) {
            f8 = n.f();
            return f8;
        }
        Object next = it.next();
        if (!it.hasNext()) {
            b8 = m.b(next);
            return b8;
        }
        ArrayList arrayList = new ArrayList();
        while (true) {
            arrayList.add(next);
            if (!it.hasNext()) {
                return arrayList;
            }
            next = it.next();
        }
    }
}
