package m6;

import android.util.Size;
import android.view.Surface;
import io.flutter.view.TextureRegistry;
import java.util.Objects;
import java.util.concurrent.Executors;
import m6.s5;
import m6.t0;
import y.g2;
import y.p1;
/* loaded from: classes.dex */
public class s5 implements t0.c1 {

    /* renamed from: a  reason: collision with root package name */
    final i6.b f9461a;

    /* renamed from: b  reason: collision with root package name */
    private final v4 f9462b;

    /* renamed from: c  reason: collision with root package name */
    private final TextureRegistry f9463c;

    /* renamed from: d  reason: collision with root package name */
    public g0 f9464d = new g0();

    /* renamed from: e  reason: collision with root package name */
    public TextureRegistry.SurfaceProducer f9465e;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class a implements p1.c {

        /* renamed from: a  reason: collision with root package name */
        final /* synthetic */ TextureRegistry.SurfaceProducer f9466a;

        /* renamed from: m6.s5$a$a  reason: collision with other inner class name */
        /* loaded from: classes.dex */
        class C0126a implements TextureRegistry.SurfaceProducer.a {

            /* renamed from: a  reason: collision with root package name */
            final /* synthetic */ y.g2 f9468a;

            C0126a(y.g2 g2Var) {
                this.f9468a = g2Var;
            }

            @Override // io.flutter.view.TextureRegistry.SurfaceProducer.a
            public void a() {
            }

            @Override // io.flutter.view.TextureRegistry.SurfaceProducer.a
            public void b() {
                this.f9468a.q();
            }
        }

        a(TextureRegistry.SurfaceProducer surfaceProducer) {
            this.f9466a = surfaceProducer;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static /* synthetic */ void d(Void r02) {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void e(Surface surface, g2.g gVar) {
            surface.release();
            int a8 = gVar.a();
            if (a8 == 0 || a8 == 1 || a8 == 3 || a8 == 4) {
                return;
            }
            s5 s5Var = s5.this;
            s5Var.f9464d.i(s5Var.f9461a).e(s5.this.t(a8), new t0.s1.a() { // from class: m6.r5
                @Override // m6.t0.s1.a
                public final void a(Object obj) {
                    s5.a.d((Void) obj);
                }
            });
        }

        @Override // y.p1.c
        public void a(y.g2 g2Var) {
            this.f9466a.setCallback(new C0126a(g2Var));
            this.f9466a.setSize(g2Var.o().getWidth(), g2Var.o().getHeight());
            final Surface surface = this.f9466a.getSurface();
            g2Var.C(surface, Executors.newSingleThreadExecutor(), new g1.a() { // from class: m6.q5
                @Override // g1.a
                public final void accept(Object obj) {
                    s5.a.this.e(surface, (g2.g) obj);
                }
            });
        }
    }

    public s5(i6.b bVar, v4 v4Var, TextureRegistry textureRegistry) {
        this.f9461a = bVar;
        this.f9462b = v4Var;
        this.f9463c = textureRegistry;
    }

    private y.p1 s(Long l8) {
        y.p1 p1Var = (y.p1) this.f9462b.h(l8.longValue());
        Objects.requireNonNull(p1Var);
        return p1Var;
    }

    @Override // m6.t0.c1
    public void b(Long l8, Long l9) {
        s(l8).o0(l9.intValue());
    }

    @Override // m6.t0.c1
    public void c(Long l8, Long l9, Long l10) {
        p1.a g8 = this.f9464d.g();
        if (l9 != null) {
            g8.a(l9.intValue());
        }
        if (l10 != null) {
            o0.c cVar = (o0.c) this.f9462b.h(l10.longValue());
            Objects.requireNonNull(cVar);
            g8.j(cVar);
        }
        this.f9462b.a(g8.e(), l8.longValue());
    }

    @Override // m6.t0.c1
    public Long g(Long l8) {
        y.p1 s8 = s(l8);
        TextureRegistry.SurfaceProducer b8 = this.f9463c.b();
        this.f9465e = b8;
        s8.n0(r(b8));
        return Long.valueOf(this.f9465e.id());
    }

    @Override // m6.t0.c1
    public void k() {
        TextureRegistry.SurfaceProducer surfaceProducer = this.f9465e;
        if (surfaceProducer != null) {
            surfaceProducer.release();
        }
    }

    @Override // m6.t0.c1
    public t0.n1 m(Long l8) {
        Size a8 = s(l8).h0().a();
        return new t0.n1.a().c(Long.valueOf(a8.getWidth())).b(Long.valueOf(a8.getHeight())).a();
    }

    public p1.c r(TextureRegistry.SurfaceProducer surfaceProducer) {
        return new a(surfaceProducer);
    }

    String t(int i8) {
        StringBuilder sb;
        String str;
        if (i8 != 2) {
            sb = new StringBuilder();
            sb.append(i8);
            str = ": Attempt to provide a surface resulted with unrecognizable code.";
        } else {
            sb = new StringBuilder();
            sb.append(i8);
            str = ": Provided surface could not be used by the camera.";
        }
        sb.append(str);
        return sb.toString();
    }
}
