package m6;

import android.content.Context;
import java.io.File;
import java.io.IOException;
import java.util.Objects;
import java.util.concurrent.Executors;
import m6.t0;
import y.u0;
/* loaded from: classes.dex */
public class q4 implements t0.i0 {

    /* renamed from: a  reason: collision with root package name */
    private final i6.b f9439a;

    /* renamed from: b  reason: collision with root package name */
    private final v4 f9440b;

    /* renamed from: c  reason: collision with root package name */
    private Context f9441c;

    /* renamed from: d  reason: collision with root package name */
    public g0 f9442d = new g0();

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class a implements u0.f {

        /* renamed from: a  reason: collision with root package name */
        final /* synthetic */ t0.r1 f9443a;

        /* renamed from: b  reason: collision with root package name */
        final /* synthetic */ File f9444b;

        a(t0.r1 r1Var, File file) {
            this.f9443a = r1Var;
            this.f9444b = file;
        }

        @Override // y.u0.f
        public void d(u0.h hVar) {
            this.f9443a.success(this.f9444b.getAbsolutePath());
        }

        @Override // y.u0.f
        public void e(y.v0 v0Var) {
            this.f9443a.a(v0Var);
        }
    }

    public q4(i6.b bVar, v4 v4Var, Context context) {
        this.f9439a = bVar;
        this.f9440b = v4Var;
        this.f9441c = context;
    }

    private y.u0 p(Long l8) {
        y.u0 u0Var = (y.u0) this.f9440b.h(l8.longValue());
        Objects.requireNonNull(u0Var);
        return u0Var;
    }

    @Override // m6.t0.i0
    public void b(Long l8, Long l9) {
        p(l8).F0(l9.intValue());
    }

    @Override // m6.t0.i0
    public void c(Long l8, Long l9, Long l10, Long l11) {
        u0.b e8 = this.f9442d.e();
        if (l9 != null) {
            e8.a(l9.intValue());
        }
        if (l10 != null) {
            e8.j(l10.intValue());
        }
        if (l11 != null) {
            o0.c cVar = (o0.c) this.f9440b.h(l11.longValue());
            Objects.requireNonNull(cVar);
            e8.l(cVar);
        }
        this.f9440b.a(e8.e(), l8.longValue());
    }

    @Override // m6.t0.i0
    public void j(Long l8, t0.r1 r1Var) {
        if (this.f9441c == null) {
            throw new IllegalStateException("Context must be set to take picture.");
        }
        y.u0 p8 = p(l8);
        try {
            File createTempFile = File.createTempFile("CAP", ".jpg", this.f9441c.getCacheDir());
            p8.y0(this.f9442d.f(createTempFile), Executors.newSingleThreadExecutor(), o(createTempFile, r1Var));
        } catch (IOException | SecurityException e8) {
            r1Var.a(e8);
        }
    }

    @Override // m6.t0.i0
    public void l(Long l8, Long l9) {
        p(l8).C0(l9.intValue());
    }

    public u0.f o(File file, t0.r1 r1Var) {
        return new a(r1Var, file);
    }

    public void q(Context context) {
        this.f9441c = context;
    }
}
