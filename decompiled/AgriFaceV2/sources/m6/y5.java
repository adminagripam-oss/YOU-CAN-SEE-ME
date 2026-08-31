package m6;

import android.content.Context;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import m6.t0;
/* loaded from: classes.dex */
public class y5 implements t0.f1 {

    /* renamed from: a  reason: collision with root package name */
    private final i6.b f9625a;

    /* renamed from: b  reason: collision with root package name */
    private final v4 f9626b;

    /* renamed from: c  reason: collision with root package name */
    private Context f9627c;

    /* renamed from: d  reason: collision with root package name */
    private androidx.lifecycle.m f9628d;

    public y5(i6.b bVar, v4 v4Var, Context context) {
        this.f9625a = bVar;
        this.f9626b = v4Var;
        this.f9627c = context;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void A(Void r02) {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void B(i4.d dVar, t0.r1 r1Var) {
        try {
            q0.g gVar = (q0.g) dVar.get();
            t5 t5Var = new t5(this.f9625a, this.f9626b);
            if (!this.f9626b.e(gVar)) {
                t5Var.e(gVar, new t0.e1.a() { // from class: m6.x5
                    @Override // m6.t0.e1.a
                    public final void a(Object obj) {
                        y5.A((Void) obj);
                    }
                });
            }
            r1Var.success(this.f9626b.g(gVar));
        } catch (Exception e8) {
            r1Var.a(e8);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void y(Void r02) {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void z(Void r02) {
    }

    public void C(Context context) {
        this.f9627c = context;
    }

    public void D(androidx.lifecycle.m mVar) {
        this.f9628d = mVar;
    }

    @Override // m6.t0.f1
    public List e(Long l8) {
        Object h8 = this.f9626b.h(l8.longValue());
        Objects.requireNonNull(h8);
        List<y.p> p8 = ((q0.g) h8).p();
        ArrayList arrayList = new ArrayList();
        v vVar = new v(this.f9625a, this.f9626b);
        for (y.p pVar : p8) {
            if (!this.f9626b.e(pVar)) {
                vVar.e(pVar, new t0.k.a() { // from class: m6.v5
                    @Override // m6.t0.k.a
                    public final void a(Object obj) {
                        y5.z((Void) obj);
                    }
                });
            }
            arrayList.add(this.f9626b.g(pVar));
        }
        return arrayList;
    }

    @Override // m6.t0.f1
    public void j(final t0.r1 r1Var) {
        Context context = this.f9627c;
        if (context == null) {
            throw new IllegalStateException("Context must be set to get ProcessCameraProvider instance.");
        }
        final i4.d t8 = q0.g.t(context);
        t8.d(new Runnable() { // from class: m6.w5
            @Override // java.lang.Runnable
            public final void run() {
                y5.this.B(t8, r1Var);
            }
        }, androidx.core.content.a.g(this.f9627c));
    }

    @Override // m6.t0.f1
    public void l(Long l8, List list) {
        Object h8 = this.f9626b.h(l8.longValue());
        Objects.requireNonNull(h8);
        q0.g gVar = (q0.g) h8;
        y.h2[] h2VarArr = new y.h2[list.size()];
        for (int i8 = 0; i8 < list.size(); i8++) {
            Object h9 = this.f9626b.h(((Number) list.get(i8)).longValue());
            Objects.requireNonNull(h9);
            h2VarArr[i8] = (y.h2) h9;
        }
        gVar.B(h2VarArr);
    }

    @Override // m6.t0.f1
    public Boolean m(Long l8, Long l9) {
        Object h8 = this.f9626b.h(l8.longValue());
        Objects.requireNonNull(h8);
        Object h9 = this.f9626b.h(l9.longValue());
        Objects.requireNonNull(h9);
        return Boolean.valueOf(((q0.g) h8).x((y.h2) h9));
    }

    @Override // m6.t0.f1
    public void o(Long l8) {
        Object h8 = this.f9626b.h(l8.longValue());
        Objects.requireNonNull(h8);
        ((q0.g) h8).C();
    }

    @Override // m6.t0.f1
    public Long q(Long l8, Long l9, List list) {
        if (this.f9628d != null) {
            Object h8 = this.f9626b.h(l8.longValue());
            Objects.requireNonNull(h8);
            q0.g gVar = (q0.g) h8;
            Object h9 = this.f9626b.h(l9.longValue());
            Objects.requireNonNull(h9);
            y.q qVar = (y.q) h9;
            y.h2[] h2VarArr = new y.h2[list.size()];
            for (int i8 = 0; i8 < list.size(); i8++) {
                Object h10 = this.f9626b.h(((Number) list.get(i8)).longValue());
                Objects.requireNonNull(h10);
                h2VarArr[i8] = (y.h2) h10;
            }
            y.j n8 = gVar.n(this.f9628d, qVar, h2VarArr);
            r rVar = new r(this.f9625a, this.f9626b);
            if (!this.f9626b.e(n8)) {
                rVar.e(n8, new t0.i.a() { // from class: m6.u5
                    @Override // m6.t0.i.a
                    public final void a(Object obj) {
                        y5.y((Void) obj);
                    }
                });
            }
            Long g8 = this.f9626b.g(n8);
            Objects.requireNonNull(g8);
            return g8;
        }
        throw new IllegalStateException("LifecycleOwner must be set to get ProcessCameraProvider instance.");
    }
}
