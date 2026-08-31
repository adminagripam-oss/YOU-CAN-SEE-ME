package androidx.camera.camera2.internal.compat.quirk;

import b0.h2;
import b0.i2;
import b0.j2;
import b0.l2;
import y.f1;
/* loaded from: classes.dex */
public abstract class b {

    /* renamed from: a  reason: collision with root package name */
    private static volatile l2 f2659a;

    static {
        j2.b().c(e0.c.b(), new g1.a() { // from class: u.a
            @Override // g1.a
            public final void accept(Object obj) {
                androidx.camera.camera2.internal.compat.quirk.b.d((i2) obj);
            }
        });
    }

    public static h2 b(Class cls) {
        return f2659a.b(cls);
    }

    public static l2 c() {
        return f2659a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void d(i2 i2Var) {
        f2659a = new l2(c.a(i2Var));
        f1.a("DeviceQuirks", "camera2 DeviceQuirks = " + l2.d(f2659a));
    }
}
