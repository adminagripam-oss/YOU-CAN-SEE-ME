package x3;

import android.content.Context;
import android.os.Looper;
import com.google.android.gms.location.LocationRequest;
import i3.a;
import i3.e;
/* loaded from: classes.dex */
public final class i extends i3.e implements c4.b {

    /* renamed from: k  reason: collision with root package name */
    static final a.g f12673k;

    /* renamed from: l  reason: collision with root package name */
    public static final i3.a f12674l;

    /* renamed from: m  reason: collision with root package name */
    private static final Object f12675m;

    static {
        a.g gVar = new a.g();
        f12673k = gVar;
        f12674l = new i3.a("LocationServices.API", new f(), gVar);
        f12675m = new Object();
    }

    public i(Context context) {
        super(context, f12674l, a.d.f7488a, e.a.f7500c);
    }

    private final f4.j s(final LocationRequest locationRequest, com.google.android.gms.common.api.internal.c cVar) {
        final h hVar = new h(this, cVar, m.f12688a);
        return j(com.google.android.gms.common.api.internal.f.a().b(new j3.i() { // from class: x3.j
            @Override // j3.i
            public final /* synthetic */ void c(Object obj, Object obj2) {
                i3.a aVar = i.f12674l;
                ((e0) obj).l0(h.this, locationRequest, (f4.k) obj2);
            }
        }).d(hVar).e(cVar).c(2436).a());
    }

    @Override // c4.b
    public final f4.j b() {
        return i(com.google.android.gms.common.api.internal.g.a().b(l.f12687a).e(2414).a());
    }

    @Override // c4.b
    public final f4.j e(c4.e eVar) {
        return k(com.google.android.gms.common.api.internal.d.b(eVar, c4.e.class.getSimpleName()), 2418).e(o.f12693e, k.f12679a);
    }

    @Override // c4.b
    public final f4.j f(LocationRequest locationRequest, c4.e eVar, Looper looper) {
        if (looper == null) {
            looper = Looper.myLooper();
            k3.q.h(looper, "invalid null looper");
        }
        return s(locationRequest, com.google.android.gms.common.api.internal.d.a(eVar, looper, c4.e.class.getSimpleName()));
    }

    @Override // i3.e
    protected final String l(Context context) {
        return null;
    }
}
