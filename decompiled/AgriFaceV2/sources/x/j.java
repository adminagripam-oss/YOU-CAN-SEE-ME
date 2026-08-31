package x;

import android.hardware.camera2.CaptureRequest;
import b0.a2;
import b0.f2;
import b0.m2;
import b0.u0;
import b0.z1;
import x.j;
import y.c0;
/* loaded from: classes.dex */
public class j implements m2 {
    private final u0 I;

    /* loaded from: classes.dex */
    public static final class a implements c0 {

        /* renamed from: a  reason: collision with root package name */
        private final a2 f12603a = a2.d0();

        public static a f(final u0 u0Var) {
            final a aVar = new a();
            u0Var.c("camera2.captureRequest.option.", new u0.b() { // from class: x.i
                @Override // b0.u0.b
                public final boolean a(u0.a aVar2) {
                    boolean g8;
                    g8 = j.a.g(j.a.this, u0Var, aVar2);
                    return g8;
                }
            });
            return aVar;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static /* synthetic */ boolean g(a aVar, u0 u0Var, u0.a aVar2) {
            aVar.c().F(aVar2, u0Var.h(aVar2), u0Var.b(aVar2));
            return true;
        }

        public j b() {
            return new j(f2.b0(this.f12603a));
        }

        @Override // y.c0
        public z1 c() {
            return this.f12603a;
        }

        public a e(CaptureRequest.Key key) {
            this.f12603a.f0(q.a.Z(key));
            return this;
        }

        public a h(CaptureRequest.Key key, Object obj) {
            this.f12603a.Y(q.a.Z(key), obj);
            return this;
        }
    }

    public j(u0 u0Var) {
        this.I = u0Var;
    }

    @Override // b0.m2
    public u0 z() {
        return this.I;
    }
}
