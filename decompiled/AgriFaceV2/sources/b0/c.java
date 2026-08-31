package b0;

import android.os.Handler;
import java.util.concurrent.Executor;
/* loaded from: classes.dex */
final class c extends p0 {

    /* renamed from: a  reason: collision with root package name */
    private final Executor f4153a;

    /* renamed from: b  reason: collision with root package name */
    private final Handler f4154b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public c(Executor executor, Handler handler) {
        if (executor == null) {
            throw new NullPointerException("Null cameraExecutor");
        }
        this.f4153a = executor;
        if (handler == null) {
            throw new NullPointerException("Null schedulerHandler");
        }
        this.f4154b = handler;
    }

    @Override // b0.p0
    public Executor b() {
        return this.f4153a;
    }

    @Override // b0.p0
    public Handler c() {
        return this.f4154b;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof p0) {
            p0 p0Var = (p0) obj;
            return this.f4153a.equals(p0Var.b()) && this.f4154b.equals(p0Var.c());
        }
        return false;
    }

    public int hashCode() {
        return ((this.f4153a.hashCode() ^ 1000003) * 1000003) ^ this.f4154b.hashCode();
    }

    public String toString() {
        return "CameraThreadConfig{cameraExecutor=" + this.f4153a + ", schedulerHandler=" + this.f4154b + "}";
    }
}
