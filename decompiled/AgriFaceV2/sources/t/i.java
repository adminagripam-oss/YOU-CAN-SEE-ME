package t;

import android.hardware.camera2.params.DynamicRangeProfiles;
import java.util.Collections;
import java.util.Set;
import t.g;
import y.a0;
/* loaded from: classes.dex */
class i implements g.a {

    /* renamed from: a  reason: collision with root package name */
    static final g f11609a = new g(new i());

    /* renamed from: b  reason: collision with root package name */
    private static final Set f11610b = Collections.singleton(a0.f12730d);

    i() {
    }

    @Override // t.g.a
    public DynamicRangeProfiles a() {
        return null;
    }

    @Override // t.g.a
    public Set b() {
        return f11610b;
    }

    @Override // t.g.a
    public Set c(a0 a0Var) {
        boolean equals = a0.f12730d.equals(a0Var);
        g1.e.b(equals, "DynamicRange is not supported: " + a0Var);
        return f11610b;
    }
}
