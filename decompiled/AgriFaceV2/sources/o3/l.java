package o3;

import android.content.Context;
import com.google.android.gms.common.api.internal.g;
import i3.a;
import i3.e;
/* loaded from: classes.dex */
public final class l extends i3.e implements n3.d {

    /* renamed from: k  reason: collision with root package name */
    private static final a.g f9843k;

    /* renamed from: l  reason: collision with root package name */
    private static final a.AbstractC0098a f9844l;

    /* renamed from: m  reason: collision with root package name */
    private static final i3.a f9845m;

    static {
        a.g gVar = new a.g();
        f9843k = gVar;
        j jVar = new j();
        f9844l = jVar;
        f9845m = new i3.a("ModuleInstall.API", jVar, gVar);
    }

    public l(Context context) {
        super(context, f9845m, a.d.f7488a, e.a.f7500c);
    }

    @Override // n3.d
    public final f4.j d(n3.f fVar) {
        final a a8 = a.a(fVar);
        fVar.b();
        fVar.c();
        if (a8.b().isEmpty()) {
            return f4.m.c(new n3.g(0));
        }
        g.a a9 = com.google.android.gms.common.api.internal.g.a();
        a9.d(v3.i.f12358a);
        a9.c(true);
        a9.e(27304);
        a9.b(new j3.i() { // from class: o3.i
            @Override // j3.i
            public final void c(Object obj, Object obj2) {
                ((g) ((m) obj).C()).d0(new k(l.this, (f4.k) obj2), a8, null);
            }
        });
        return i(a9.a());
    }
}
