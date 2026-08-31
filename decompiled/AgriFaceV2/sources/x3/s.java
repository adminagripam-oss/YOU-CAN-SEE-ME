package x3;

import android.content.Context;
import i3.a;
import i3.e;
/* loaded from: classes.dex */
public final class s extends i3.e implements c4.l {
    public s(Context context) {
        super(context, i.f12674l, a.d.f7488a, e.a.f7500c);
    }

    @Override // c4.l
    public final f4.j c(final c4.g gVar) {
        return i(com.google.android.gms.common.api.internal.g.a().b(new j3.i() { // from class: x3.t
            @Override // j3.i
            public final /* synthetic */ void c(Object obj, Object obj2) {
                f4.k kVar = (f4.k) obj2;
                e0 e0Var = (e0) obj;
                c4.g gVar2 = c4.g.this;
                k3.q.b(gVar2 != null, "locationSettingsRequest can't be null");
                ((g1) e0Var.C()).D(gVar2, new v(kVar), null);
            }
        }).e(2426).a());
    }
}
