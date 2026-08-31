package r5;

import a4.be;
import a4.qd;
import android.content.Context;
/* loaded from: classes.dex */
public final class f extends m5.e {

    /* renamed from: b  reason: collision with root package name */
    private final m5.i f11333b;

    public f(m5.i iVar) {
        this.f11333b = iVar;
    }

    @Override // m5.e
    protected final /* bridge */ /* synthetic */ Object a(Object obj) {
        q5.e eVar = (q5.e) obj;
        Context b8 = this.f11333b.b();
        qd b9 = be.b(k.b());
        return new i(be.b(k.b()), eVar, (b.c(b8) || h3.e.f().a(b8) >= 204500000) ? new b(b8, eVar, b9) : new n(b8, eVar, b9));
    }
}
