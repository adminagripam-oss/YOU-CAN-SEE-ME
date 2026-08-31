package y3;

import android.content.Context;
/* loaded from: classes.dex */
public final class n0 implements b0 {

    /* renamed from: a  reason: collision with root package name */
    private h5.b f13254a;

    /* renamed from: b  reason: collision with root package name */
    private final h5.b f13255b;

    /* renamed from: c  reason: collision with root package name */
    private final e0 f13256c;

    public n0(Context context, e0 e0Var) {
        this.f13256c = e0Var;
        com.google.android.datatransport.cct.a aVar = com.google.android.datatransport.cct.a.f4919g;
        u2.r.f(context);
        final s2.g g8 = u2.r.c().g(aVar);
        if (aVar.a().contains(s2.b.b("json"))) {
            this.f13254a = new b5.u(new h5.b() { // from class: y3.k0
                @Override // h5.b
                public final Object get() {
                    return s2.g.this.a("FIREBASE_ML_SDK", byte[].class, s2.b.b("json"), new s2.e() { // from class: y3.m0
                        @Override // s2.e
                        public final Object apply(Object obj) {
                            return (byte[]) obj;
                        }
                    });
                }
            });
        }
        this.f13255b = new b5.u(new h5.b() { // from class: y3.l0
            @Override // h5.b
            public final Object get() {
                return s2.g.this.a("FIREBASE_ML_SDK", byte[].class, s2.b.b("proto"), new s2.e() { // from class: y3.j0
                    @Override // s2.e
                    public final Object apply(Object obj) {
                        return (byte[]) obj;
                    }
                });
            }
        });
    }
}
