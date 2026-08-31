package b0;

import b0.q0;
import y.s1;
/* loaded from: classes.dex */
public final class k0 implements p2 {

    /* renamed from: d  reason: collision with root package name */
    private final y.s1 f4266d;

    /* loaded from: classes.dex */
    class a implements y.s1 {

        /* renamed from: d  reason: collision with root package name */
        final /* synthetic */ long f4267d;

        a(long j8) {
            this.f4267d = j8;
        }

        @Override // y.s1
        public s1.c b(s1.b bVar) {
            return bVar.b() == 1 ? s1.c.f12907d : s1.c.f12908e;
        }

        @Override // y.s1
        public long f() {
            return this.f4267d;
        }
    }

    /* loaded from: classes.dex */
    public static final class b implements p2 {

        /* renamed from: d  reason: collision with root package name */
        private final y.s1 f4269d;

        public b(long j8) {
            this.f4269d = new k0(j8);
        }

        @Override // y.s1
        public s1.c b(s1.b bVar) {
            if (this.f4269d.b(bVar).d()) {
                return s1.c.f12908e;
            }
            Throwable a8 = bVar.a();
            if (a8 instanceof q0.b) {
                y.f1.c("CameraX", "The device might underreport the amount of the cameras. Finish the initialize task since we are already reaching the maximum number of retries.");
                if (((q0.b) a8).a() > 0) {
                    return s1.c.f12910g;
                }
            }
            return s1.c.f12907d;
        }

        @Override // b0.p2
        public y.s1 c(long j8) {
            return new b(j8);
        }

        @Override // y.s1
        public long f() {
            return this.f4269d.f();
        }
    }

    public k0(long j8) {
        this.f4266d = new a3(j8, new a(j8));
    }

    @Override // y.s1
    public s1.c b(s1.b bVar) {
        return this.f4266d.b(bVar);
    }

    @Override // b0.p2
    public y.s1 c(long j8) {
        return new k0(j8);
    }

    @Override // y.s1
    public long f() {
        return this.f4266d.f();
    }
}
