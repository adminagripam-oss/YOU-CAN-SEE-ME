package r;

import android.graphics.SurfaceTexture;
import android.util.Size;
import android.view.Surface;
import b0.h3;
import b0.q2;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class f4 {

    /* renamed from: a  reason: collision with root package name */
    private b0.b1 f10469a;

    /* renamed from: b  reason: collision with root package name */
    private b0.q2 f10470b;

    /* renamed from: d  reason: collision with root package name */
    private final Size f10472d;

    /* renamed from: f  reason: collision with root package name */
    private final c f10474f;

    /* renamed from: e  reason: collision with root package name */
    private final v.w f10473e = new v.w();

    /* renamed from: g  reason: collision with root package name */
    private q2.c f10475g = null;

    /* renamed from: c  reason: collision with root package name */
    private final b f10471c = new b();

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class a implements f0.c {

        /* renamed from: a  reason: collision with root package name */
        final /* synthetic */ Surface f10476a;

        /* renamed from: b  reason: collision with root package name */
        final /* synthetic */ SurfaceTexture f10477b;

        a(Surface surface, SurfaceTexture surfaceTexture) {
            this.f10476a = surface;
            this.f10477b = surfaceTexture;
        }

        @Override // f0.c
        public void b(Throwable th) {
            throw new IllegalStateException("Future should never fail. Did it get completed by GC?", th);
        }

        @Override // f0.c
        /* renamed from: c */
        public void a(Void r12) {
            this.f10476a.release();
            this.f10477b.release();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class b implements b0.g3 {
        private final b0.u0 I;

        b() {
            b0.a2 d02 = b0.a2.d0();
            d02.Y(b0.g3.f4202v, new f2());
            d02.Y(b0.o1.f4299h, 34);
            Z(d02);
            this.I = d02;
        }

        private void Z(b0.a2 a2Var) {
            a2Var.Y(g0.m.G, f4.class);
            a2Var.Y(g0.m.F, f4.class.getCanonicalName() + "-" + UUID.randomUUID());
        }

        @Override // b0.g3
        public h3.b m() {
            return h3.b.METERING_REPEATING;
        }

        @Override // b0.m2
        public b0.u0 z() {
            return this.I;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public interface c {
        void a();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public f4(s.b0 b0Var, d3 d3Var, c cVar) {
        this.f10474f = cVar;
        Size g8 = g(b0Var, d3Var);
        this.f10472d = g8;
        y.f1.a("MeteringRepeating", "MeteringSession SurfaceTexture size: " + g8);
        this.f10470b = d();
    }

    private Size g(s.b0 b0Var, d3 d3Var) {
        Size[] c8 = b0Var.b().c(34);
        if (c8 == null) {
            y.f1.c("MeteringRepeating", "Can not get output size list.");
            return new Size(0, 0);
        }
        Size[] a8 = this.f10473e.a(c8);
        List asList = Arrays.asList(a8);
        Collections.sort(asList, new Comparator() { // from class: r.e4
            @Override // java.util.Comparator
            public final int compare(Object obj, Object obj2) {
                int k8;
                k8 = f4.k((Size) obj, (Size) obj2);
                return k8;
            }
        });
        Size f8 = d3Var.f();
        long min = Math.min(f8.getWidth() * f8.getHeight(), 307200L);
        int length = a8.length;
        Size size = null;
        int i8 = 0;
        while (true) {
            if (i8 >= length) {
                break;
            }
            Size size2 = a8[i8];
            int i9 = ((size2.getWidth() * size2.getHeight()) > min ? 1 : ((size2.getWidth() * size2.getHeight()) == min ? 0 : -1));
            if (i9 == 0) {
                return size2;
            }
            if (i9 <= 0) {
                i8++;
                size = size2;
            } else if (size != null) {
                return size;
            }
        }
        return (Size) asList.get(0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void j(b0.q2 q2Var, q2.g gVar) {
        this.f10470b = d();
        c cVar = this.f10474f;
        if (cVar != null) {
            cVar.a();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ int k(Size size, Size size2) {
        return Long.signum((size.getWidth() * size.getHeight()) - (size2.getWidth() * size2.getHeight()));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void c() {
        y.f1.a("MeteringRepeating", "MeteringRepeating clear!");
        b0.b1 b1Var = this.f10469a;
        if (b1Var != null) {
            b1Var.d();
        }
        this.f10469a = null;
    }

    b0.q2 d() {
        SurfaceTexture surfaceTexture = new SurfaceTexture(0);
        surfaceTexture.setDefaultBufferSize(this.f10472d.getWidth(), this.f10472d.getHeight());
        Surface surface = new Surface(surfaceTexture);
        q2.b q8 = q2.b.q(this.f10471c, this.f10472d);
        q8.z(1);
        b0.r1 r1Var = new b0.r1(surface);
        this.f10469a = r1Var;
        f0.n.j(r1Var.k(), new a(surface, surfaceTexture), e0.c.b());
        q8.l(this.f10469a);
        q2.c cVar = this.f10475g;
        if (cVar != null) {
            cVar.b();
        }
        q2.c cVar2 = new q2.c(new q2.d() { // from class: r.d4
            @Override // b0.q2.d
            public final void a(b0.q2 q2Var, q2.g gVar) {
                f4.this.j(q2Var, gVar);
            }
        });
        this.f10475g = cVar2;
        q8.t(cVar2);
        return q8.o();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Size e() {
        return this.f10472d;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public String f() {
        return "MeteringRepeating";
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public b0.q2 h() {
        return this.f10470b;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public b0.g3 i() {
        return this.f10471c;
    }
}
