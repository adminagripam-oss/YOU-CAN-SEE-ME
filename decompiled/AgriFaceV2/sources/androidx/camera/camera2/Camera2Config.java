package androidx.camera.camera2;

import android.content.Context;
import androidx.camera.camera2.Camera2Config;
import b0.e0;
import b0.f0;
import b0.h3;
import b0.p0;
import java.util.Set;
import r.d2;
import r.g2;
import r.v;
import y.d1;
import y.q;
import y.s;
import y.x;
/* loaded from: classes.dex */
public abstract class Camera2Config {

    /* loaded from: classes.dex */
    public static final class DefaultProvider implements x.b {
        @Override // y.x.b
        public x getCameraXConfig() {
            return Camera2Config.c();
        }
    }

    public static x c() {
        f0.a aVar = new f0.a() { // from class: p.a
            @Override // b0.f0.a
            public final f0 a(Context context, p0 p0Var, q qVar, long j8) {
                return new v(context, p0Var, qVar, j8);
            }
        };
        e0.a aVar2 = new e0.a() { // from class: p.b
            @Override // b0.e0.a
            public final e0 a(Context context, Object obj, Set set) {
                e0 d8;
                d8 = Camera2Config.d(context, obj, set);
                return d8;
            }
        };
        return new x.a().c(aVar).d(aVar2).g(new h3.c() { // from class: p.c
            @Override // b0.h3.c
            public final h3 a(Context context) {
                h3 e8;
                e8 = Camera2Config.e(context);
                return e8;
            }
        }).a();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ e0 d(Context context, Object obj, Set set) {
        try {
            return new d2(context, obj, set);
        } catch (s e8) {
            throw new d1(e8);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ h3 e(Context context) {
        return new g2(context);
    }
}
