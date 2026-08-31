package m6;

import androidx.camera.core.f;
import java.util.Objects;
import m6.t0;
/* loaded from: classes.dex */
public class a {

    /* renamed from: a  reason: collision with root package name */
    private final i6.b f9214a;

    /* renamed from: b  reason: collision with root package name */
    private final v4 f9215b;

    /* renamed from: c  reason: collision with root package name */
    private t0.a f9216c;

    public a(i6.b bVar, v4 v4Var) {
        this.f9214a = bVar;
        this.f9215b = v4Var;
        this.f9216c = new t0.a(bVar);
    }

    public void a(f.a aVar, androidx.camera.core.n nVar, t0.a.InterfaceC0127a interfaceC0127a) {
        t0.a aVar2 = this.f9216c;
        Long g8 = this.f9215b.g(aVar);
        Objects.requireNonNull(g8);
        Long g9 = this.f9215b.g(nVar);
        Objects.requireNonNull(g9);
        aVar2.b(g8, g9, interfaceC0127a);
    }
}
