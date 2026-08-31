package a4;

import android.content.Context;
/* loaded from: classes.dex */
public final class yd implements pd {

    /* renamed from: a  reason: collision with root package name */
    private h5.b f1320a;

    /* renamed from: b  reason: collision with root package name */
    private final h5.b f1321b;

    /* renamed from: c  reason: collision with root package name */
    private final gd f1322c;

    public yd(Context context, gd gdVar) {
        this.f1322c = gdVar;
        com.google.android.datatransport.cct.a aVar = com.google.android.datatransport.cct.a.f4919g;
        u2.r.f(context);
        final s2.g g8 = u2.r.c().g(aVar);
        if (aVar.a().contains(s2.b.b("json"))) {
            this.f1320a = new b5.u(new h5.b() { // from class: a4.vd
                @Override // h5.b
                public final Object get() {
                    return s2.g.this.a("FIREBASE_ML_SDK", byte[].class, s2.b.b("json"), new s2.e() { // from class: a4.xd
                        @Override // s2.e
                        public final Object apply(Object obj) {
                            return (byte[]) obj;
                        }
                    });
                }
            });
        }
        this.f1321b = new b5.u(new h5.b() { // from class: a4.wd
            @Override // h5.b
            public final Object get() {
                return s2.g.this.a("FIREBASE_ML_SDK", byte[].class, s2.b.b("proto"), new s2.e() { // from class: a4.ud
                    @Override // s2.e
                    public final Object apply(Object obj) {
                        return (byte[]) obj;
                    }
                });
            }
        });
    }

    static s2.c b(gd gdVar, ed edVar) {
        int a8 = gdVar.a();
        int zza = edVar.zza();
        byte[] c8 = edVar.c(a8, false);
        return zza != 0 ? s2.c.d(c8) : s2.c.e(c8);
    }

    @Override // a4.pd
    public final void a(ed edVar) {
        h5.b bVar;
        if (this.f1322c.a() == 0) {
            bVar = this.f1320a;
            if (bVar == null) {
                return;
            }
        } else {
            bVar = this.f1321b;
        }
        ((s2.f) bVar.get()).a(b(this.f1322c, edVar));
    }
}
