package m6;

import androidx.camera.core.n;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import m6.t0;
/* loaded from: classes.dex */
public class t4 implements t0.k0 {

    /* renamed from: a  reason: collision with root package name */
    private final i6.b f9579a;

    /* renamed from: b  reason: collision with root package name */
    private final v4 f9580b;

    /* renamed from: c  reason: collision with root package name */
    public g0 f9581c = new g0();

    /* renamed from: d  reason: collision with root package name */
    public p5 f9582d;

    public t4(i6.b bVar, v4 v4Var) {
        this.f9579a = bVar;
        this.f9580b = v4Var;
        this.f9582d = new p5(bVar, v4Var);
    }

    private androidx.camera.core.n j(Long l8) {
        androidx.camera.core.n nVar = (androidx.camera.core.n) this.f9580b.h(l8.longValue());
        Objects.requireNonNull(nVar);
        return nVar;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void k(Void r02) {
    }

    @Override // m6.t0.k0
    public void b(Long l8) {
        j(l8).close();
    }

    @Override // m6.t0.k0
    public List e(Long l8) {
        n.a[] i8 = j(l8).i();
        ArrayList arrayList = new ArrayList();
        for (n.a aVar : i8) {
            ByteBuffer c8 = aVar.c();
            byte[] j8 = this.f9581c.j(c8.remaining());
            c8.get(j8, 0, j8.length);
            this.f9582d.a(aVar, j8, Long.valueOf(aVar.b()), Long.valueOf(aVar.a()), new t0.b1.a() { // from class: m6.s4
                @Override // m6.t0.b1.a
                public final void a(Object obj) {
                    t4.k((Void) obj);
                }
            });
            arrayList.add(this.f9580b.g(aVar));
        }
        return arrayList;
    }
}
