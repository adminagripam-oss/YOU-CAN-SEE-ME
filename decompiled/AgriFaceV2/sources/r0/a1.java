package r0;

import java.util.concurrent.atomic.AtomicBoolean;
/* loaded from: classes.dex */
public final class a1 implements AutoCloseable {

    /* renamed from: e  reason: collision with root package name */
    private final AtomicBoolean f10971e;

    /* renamed from: f  reason: collision with root package name */
    private final q0 f10972f;

    /* renamed from: g  reason: collision with root package name */
    private final long f10973g;

    /* renamed from: h  reason: collision with root package name */
    private final s f10974h;

    /* renamed from: i  reason: collision with root package name */
    private final boolean f10975i;

    /* renamed from: j  reason: collision with root package name */
    private final d0.d f10976j;

    a1(q0 q0Var, long j8, s sVar, boolean z7, boolean z8) {
        AtomicBoolean atomicBoolean = new AtomicBoolean(false);
        this.f10971e = atomicBoolean;
        d0.d b8 = d0.d.b();
        this.f10976j = b8;
        this.f10972f = q0Var;
        this.f10973g = j8;
        this.f10974h = sVar;
        this.f10975i = z7;
        if (z8) {
            atomicBoolean.set(true);
        } else {
            b8.c("stop");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static a1 b(u uVar, long j8) {
        g1.e.k(uVar, "The given PendingRecording cannot be null.");
        return new a1(uVar.e(), j8, uVar.d(), uVar.g(), true);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static a1 c(u uVar, long j8) {
        g1.e.k(uVar, "The given PendingRecording cannot be null.");
        return new a1(uVar.e(), j8, uVar.d(), uVar.g(), false);
    }

    private void q(int i8, Throwable th) {
        this.f10976j.a();
        if (this.f10971e.getAndSet(true)) {
            return;
        }
        this.f10972f.P0(this, i8, th);
    }

    @Override // java.lang.AutoCloseable
    public void close() {
        q(0, null);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public s d() {
        return this.f10974h;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public long f() {
        return this.f10973g;
    }

    protected void finalize() {
        try {
            this.f10976j.d();
            q(10, new RuntimeException("Recording stopped due to being garbage collected."));
        } finally {
            super.finalize();
        }
    }

    public void g() {
        if (this.f10971e.get()) {
            throw new IllegalStateException("The recording has been stopped.");
        }
        this.f10972f.r0(this);
    }

    public void j() {
        if (this.f10971e.get()) {
            throw new IllegalStateException("The recording has been stopped.");
        }
        this.f10972f.A0(this);
    }

    public void n() {
        close();
    }
}
