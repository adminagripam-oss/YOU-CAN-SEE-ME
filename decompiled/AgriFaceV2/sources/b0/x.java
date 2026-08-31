package b0;

import android.hardware.camera2.CaptureResult;
import d0.i;
/* loaded from: classes.dex */
public interface x {

    /* loaded from: classes.dex */
    public static final class a implements x {
        public static x l() {
            return new a();
        }

        @Override // b0.x
        public y2 b() {
            return y2.b();
        }

        @Override // b0.x
        public long c() {
            return -1L;
        }

        @Override // b0.x
        public s d() {
            return s.UNKNOWN;
        }

        @Override // b0.x
        public r e() {
            return r.UNKNOWN;
        }

        @Override // b0.x
        public v f() {
            return v.UNKNOWN;
        }

        @Override // b0.x
        public w g() {
            return w.UNKNOWN;
        }

        @Override // b0.x
        public CaptureResult h() {
            return null;
        }

        @Override // b0.x
        public u i() {
            return u.UNKNOWN;
        }

        @Override // b0.x
        public q j() {
            return q.UNKNOWN;
        }

        @Override // b0.x
        public t k() {
            return t.UNKNOWN;
        }
    }

    default void a(i.b bVar) {
        bVar.g(g());
    }

    y2 b();

    long c();

    s d();

    r e();

    v f();

    w g();

    default CaptureResult h() {
        return null;
    }

    u i();

    q j();

    t k();
}
