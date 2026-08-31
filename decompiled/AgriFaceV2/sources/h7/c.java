package h7;

import d7.k;
/* loaded from: classes.dex */
public abstract class c {
    public static final Object a(b bVar, Object obj) {
        k.e(bVar, "<this>");
        if (bVar.b(obj)) {
            k.c(obj, "null cannot be cast to non-null type T of kotlin.reflect.KClasses.cast");
            return obj;
        }
        throw new ClassCastException("Value cannot be cast to " + bVar.a());
    }
}
