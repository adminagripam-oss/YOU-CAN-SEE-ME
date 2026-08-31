package t6;

import c7.p;
import d7.k;
import java.io.Serializable;
import t6.g;
/* loaded from: classes.dex */
public final class h implements g, Serializable {

    /* renamed from: e  reason: collision with root package name */
    public static final h f11916e = new h();

    private h() {
    }

    @Override // t6.g
    public g G(g.c cVar) {
        k.e(cVar, "key");
        return this;
    }

    @Override // t6.g
    public g M(g gVar) {
        k.e(gVar, "context");
        return gVar;
    }

    @Override // t6.g
    public g.b c(g.c cVar) {
        k.e(cVar, "key");
        return null;
    }

    @Override // t6.g
    public Object g(Object obj, p pVar) {
        k.e(pVar, "operation");
        return obj;
    }

    public int hashCode() {
        return 0;
    }

    public String toString() {
        return "EmptyCoroutineContext";
    }
}
