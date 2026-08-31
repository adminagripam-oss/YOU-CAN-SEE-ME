package androidx.camera.core.internal.compat.quirk;

import b0.h2;
import b0.i2;
import b0.j2;
import b0.l2;
import e0.c;
import y.f1;
/* loaded from: classes.dex */
public abstract class a {

    /* renamed from: a  reason: collision with root package name */
    private static volatile l2 f2734a;

    static {
        j2.b().c(c.b(), new g1.a() { // from class: i0.a
            @Override // g1.a
            public final void accept(Object obj) {
                androidx.camera.core.internal.compat.quirk.a.d((i2) obj);
            }
        });
    }

    public static h2 b(Class cls) {
        return f2734a.b(cls);
    }

    public static l2 c() {
        return f2734a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void d(i2 i2Var) {
        f2734a = new l2(b.a(i2Var));
        f1.a("DeviceQuirks", "core DeviceQuirks = " + l2.d(f2734a));
    }
}
