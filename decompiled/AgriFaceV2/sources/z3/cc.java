package z3;

import android.content.Context;
/* loaded from: classes.dex */
public final class cc implements pb {

    /* renamed from: a  reason: collision with root package name */
    private h5.b f13484a;

    /* renamed from: b  reason: collision with root package name */
    private final h5.b f13485b;

    /* renamed from: c  reason: collision with root package name */
    private final rb f13486c;

    public cc(Context context, rb rbVar) {
        this.f13486c = rbVar;
        com.google.android.datatransport.cct.a aVar = com.google.android.datatransport.cct.a.f4919g;
        u2.r.f(context);
        final s2.g g8 = u2.r.c().g(aVar);
        if (aVar.a().contains(s2.b.b("json"))) {
            this.f13484a = new b5.u(new h5.b() { // from class: z3.zb
                @Override // h5.b
                public final Object get() {
                    return s2.g.this.a("FIREBASE_ML_SDK", byte[].class, s2.b.b("json"), new s2.e() { // from class: z3.bc
                        @Override // s2.e
                        public final Object apply(Object obj) {
                            return (byte[]) obj;
                        }
                    });
                }
            });
        }
        this.f13485b = new b5.u(new h5.b() { // from class: z3.ac
            @Override // h5.b
            public final Object get() {
                return s2.g.this.a("FIREBASE_ML_SDK", byte[].class, s2.b.b("proto"), new s2.e() { // from class: z3.yb
                    @Override // s2.e
                    public final Object apply(Object obj) {
                        return (byte[]) obj;
                    }
                });
            }
        });
    }

    static s2.c b(rb rbVar, ob obVar) {
        return s2.c.e(obVar.c(rbVar.a(), false));
    }

    @Override // z3.pb
    public final void a(ob obVar) {
        h5.b bVar;
        if (this.f13486c.a() == 0) {
            bVar = this.f13484a;
            if (bVar == null) {
                return;
            }
        } else {
            bVar = this.f13485b;
        }
        ((s2.f) bVar.get()).a(b(this.f13486c, obVar));
    }
}
