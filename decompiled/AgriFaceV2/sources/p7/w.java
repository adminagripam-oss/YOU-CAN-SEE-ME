package p7;

import java.util.Iterator;
import java.util.List;
import java.util.ServiceLoader;
import k7.o1;
/* loaded from: classes.dex */
public final class w {

    /* renamed from: a  reason: collision with root package name */
    public static final w f10196a;

    /* renamed from: b  reason: collision with root package name */
    private static final boolean f10197b = false;

    /* renamed from: c  reason: collision with root package name */
    public static final o1 f10198c;

    static {
        w wVar = new w();
        f10196a = wVar;
        i0.f("kotlinx.coroutines.fast.service.loader", true);
        f10198c = wVar.a();
    }

    private w() {
    }

    private final o1 a() {
        i7.b a8;
        List c8;
        Object next;
        o1 e8;
        try {
            if (f10197b) {
                c8 = l.f10159a.c();
            } else {
                a8 = i7.f.a(ServiceLoader.load(v.class, v.class.getClassLoader()).iterator());
                c8 = i7.h.c(a8);
            }
            Iterator it = c8.iterator();
            if (it.hasNext()) {
                next = it.next();
                if (it.hasNext()) {
                    int c9 = ((v) next).c();
                    do {
                        Object next2 = it.next();
                        int c10 = ((v) next2).c();
                        if (c9 < c10) {
                            next = next2;
                            c9 = c10;
                        }
                    } while (it.hasNext());
                }
            } else {
                next = null;
            }
            v vVar = (v) next;
            return (vVar == null || (e8 = x.e(vVar, c8)) == null) ? x.b(null, null, 3, null) : e8;
        } catch (Throwable th) {
            return x.b(th, null, 2, null);
        }
    }
}
