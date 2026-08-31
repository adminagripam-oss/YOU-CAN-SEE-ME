package m3;

import android.content.Context;
import com.google.android.gms.common.api.internal.g;
import f4.j;
import f4.k;
import i3.a;
import i3.e;
import j3.i;
import k3.t;
import k3.v;
import k3.w;
/* loaded from: classes.dex */
public final class d extends i3.e implements v {

    /* renamed from: k  reason: collision with root package name */
    private static final a.g f9132k;

    /* renamed from: l  reason: collision with root package name */
    private static final a.AbstractC0098a f9133l;

    /* renamed from: m  reason: collision with root package name */
    private static final i3.a f9134m;

    /* renamed from: n  reason: collision with root package name */
    public static final /* synthetic */ int f9135n = 0;

    static {
        a.g gVar = new a.g();
        f9132k = gVar;
        c cVar = new c();
        f9133l = cVar;
        f9134m = new i3.a("ClientTelemetry.API", cVar, gVar);
    }

    public d(Context context, w wVar) {
        super(context, f9134m, wVar, e.a.f7500c);
    }

    @Override // k3.v
    public final j a(final t tVar) {
        g.a a8 = g.a();
        a8.d(v3.d.f12354a);
        a8.c(false);
        a8.b(new i() { // from class: m3.b
            @Override // j3.i
            public final void c(Object obj, Object obj2) {
                int i8 = d.f9135n;
                ((a) ((e) obj).C()).d0(t.this);
                ((k) obj2).c(null);
            }
        });
        return h(a8.a());
    }
}
