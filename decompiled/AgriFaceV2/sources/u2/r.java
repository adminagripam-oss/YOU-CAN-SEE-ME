package u2;

import android.content.Context;
import java.util.Collections;
import java.util.Set;
/* loaded from: classes.dex */
public class r implements q {

    /* renamed from: e  reason: collision with root package name */
    private static volatile s f12088e;

    /* renamed from: a  reason: collision with root package name */
    private final d3.a f12089a;

    /* renamed from: b  reason: collision with root package name */
    private final d3.a f12090b;

    /* renamed from: c  reason: collision with root package name */
    private final z2.e f12091c;

    /* renamed from: d  reason: collision with root package name */
    private final a3.j f12092d;

    /* JADX INFO: Access modifiers changed from: package-private */
    public r(d3.a aVar, d3.a aVar2, z2.e eVar, a3.j jVar, a3.n nVar) {
        this.f12089a = aVar;
        this.f12090b = aVar2;
        this.f12091c = eVar;
        this.f12092d = jVar;
        nVar.a();
    }

    private h b(l lVar) {
        return h.a().i(this.f12089a.a()).k(this.f12090b.a()).j(lVar.g()).h(new g(lVar.b(), lVar.d())).g(lVar.c().a()).d();
    }

    public static r c() {
        s sVar = f12088e;
        if (sVar != null) {
            return sVar.c();
        }
        throw new IllegalStateException("Not initialized!");
    }

    private static Set d(e eVar) {
        return eVar instanceof f ? Collections.unmodifiableSet(((f) eVar).a()) : Collections.singleton(s2.b.b("proto"));
    }

    public static void f(Context context) {
        if (f12088e == null) {
            synchronized (r.class) {
                if (f12088e == null) {
                    f12088e = d.d().b(context).a();
                }
            }
        }
    }

    @Override // u2.q
    public void a(l lVar, s2.h hVar) {
        this.f12091c.a(lVar.f().e(lVar.c().c()), b(lVar), hVar);
    }

    public a3.j e() {
        return this.f12092d;
    }

    public s2.g g(e eVar) {
        return new n(d(eVar), m.a().b(eVar.getName()).c(eVar.b()).a(), this);
    }
}
