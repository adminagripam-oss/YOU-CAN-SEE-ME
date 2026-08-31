package com.google.android.gms.internal.mlkit_vision_face_bundled;

import android.content.Context;
/* loaded from: classes.dex */
public final class fi implements ph {

    /* renamed from: a  reason: collision with root package name */
    private h5.b f5226a;

    /* renamed from: b  reason: collision with root package name */
    private final h5.b f5227b;

    /* renamed from: c  reason: collision with root package name */
    private final rh f5228c;

    public fi(Context context, rh rhVar) {
        this.f5228c = rhVar;
        com.google.android.datatransport.cct.a aVar = com.google.android.datatransport.cct.a.f4919g;
        u2.r.f(context);
        final s2.g g8 = u2.r.c().g(aVar);
        if (aVar.a().contains(s2.b.b("json"))) {
            this.f5226a = new b5.u(new h5.b() { // from class: com.google.android.gms.internal.mlkit_vision_face_bundled.ci
                @Override // h5.b
                public final Object get() {
                    return s2.g.this.a("FIREBASE_ML_SDK", byte[].class, s2.b.b("json"), new s2.e() { // from class: com.google.android.gms.internal.mlkit_vision_face_bundled.ei
                        @Override // s2.e
                        public final Object apply(Object obj) {
                            return (byte[]) obj;
                        }
                    });
                }
            });
        }
        this.f5227b = new b5.u(new h5.b() { // from class: com.google.android.gms.internal.mlkit_vision_face_bundled.di
            @Override // h5.b
            public final Object get() {
                return s2.g.this.a("FIREBASE_ML_SDK", byte[].class, s2.b.b("proto"), new s2.e() { // from class: com.google.android.gms.internal.mlkit_vision_face_bundled.bi
                    @Override // s2.e
                    public final Object apply(Object obj) {
                        return (byte[]) obj;
                    }
                });
            }
        });
    }

    static s2.c b(rh rhVar, oh ohVar) {
        int a8 = rhVar.a();
        int zza = ohVar.zza();
        byte[] c8 = ohVar.c(a8, false);
        return zza != 0 ? s2.c.d(c8) : s2.c.e(c8);
    }

    @Override // com.google.android.gms.internal.mlkit_vision_face_bundled.ph
    public final void a(oh ohVar) {
        h5.b bVar;
        if (this.f5228c.a() == 0) {
            bVar = this.f5226a;
            if (bVar == null) {
                return;
            }
        } else {
            bVar = this.f5227b;
        }
        ((s2.f) bVar.get()).a(b(this.f5228c, ohVar));
    }
}
