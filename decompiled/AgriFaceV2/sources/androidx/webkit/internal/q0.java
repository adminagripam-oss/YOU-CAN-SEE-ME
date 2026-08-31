package androidx.webkit.internal;

import androidx.webkit.internal.a;
import c2.d;
import java.lang.reflect.Array;
import java.util.List;
import java.util.concurrent.Executor;
import org.chromium.support_lib_boundary.ProxyControllerBoundaryInterface;
/* loaded from: classes.dex */
public class q0 extends c2.e {

    /* renamed from: a  reason: collision with root package name */
    private ProxyControllerBoundaryInterface f4076a;

    private ProxyControllerBoundaryInterface d() {
        if (this.f4076a == null) {
            this.f4076a = h1.d().getProxyController();
        }
        return this.f4076a;
    }

    public static String[][] e(List list) {
        String[][] strArr = (String[][]) Array.newInstance(String.class, list.size(), 2);
        for (int i8 = 0; i8 < list.size(); i8++) {
            strArr[i8][0] = ((d.b) list.get(i8)).a();
            strArr[i8][1] = ((d.b) list.get(i8)).b();
        }
        return strArr;
    }

    @Override // c2.e
    public void a(Executor executor, Runnable runnable) {
        if (!g1.Q.d()) {
            throw g1.a();
        }
        d().clearProxyOverride(runnable, executor);
    }

    @Override // c2.e
    public void c(c2.d dVar, Executor executor, Runnable runnable) {
        a.d dVar2 = g1.Q;
        a.d dVar3 = g1.W;
        String[][] e8 = e(dVar.b());
        String[] strArr = (String[]) dVar.a().toArray(new String[0]);
        if (dVar2.d() && !dVar.c()) {
            d().setProxyOverride(e8, strArr, runnable, executor);
        } else if (!dVar2.d() || !dVar3.d()) {
            throw g1.a();
        } else {
            d().setProxyOverride(e8, strArr, runnable, executor, dVar.c());
        }
    }
}
