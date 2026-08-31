package e3;

import android.util.SparseArray;
import java.util.HashMap;
import s2.d;
/* loaded from: classes.dex */
public abstract class a {

    /* renamed from: a  reason: collision with root package name */
    private static SparseArray f6601a = new SparseArray();

    /* renamed from: b  reason: collision with root package name */
    private static HashMap f6602b;

    static {
        HashMap hashMap = new HashMap();
        f6602b = hashMap;
        hashMap.put(d.DEFAULT, 0);
        f6602b.put(d.VERY_LOW, 1);
        f6602b.put(d.HIGHEST, 2);
        for (d dVar : f6602b.keySet()) {
            f6601a.append(((Integer) f6602b.get(dVar)).intValue(), dVar);
        }
    }

    public static int a(d dVar) {
        Integer num = (Integer) f6602b.get(dVar);
        if (num != null) {
            return num.intValue();
        }
        throw new IllegalStateException("PriorityMapping is missing known Priority value " + dVar);
    }

    public static d b(int i8) {
        d dVar = (d) f6601a.get(i8);
        if (dVar != null) {
            return dVar;
        }
        throw new IllegalArgumentException("Unknown Priority for value " + i8);
    }
}
