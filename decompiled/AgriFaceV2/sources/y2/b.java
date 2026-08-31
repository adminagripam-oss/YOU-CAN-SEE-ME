package y2;
/* loaded from: classes.dex */
public abstract class b {
    public static Object a(int i8, Object obj, a aVar, c cVar) {
        Object apply;
        if (i8 < 1) {
            return aVar.apply(obj);
        }
        do {
            apply = aVar.apply(obj);
            obj = cVar.a(obj, apply);
            if (obj == null) {
                break;
            }
            i8--;
        } while (i8 >= 1);
        return apply;
    }
}
