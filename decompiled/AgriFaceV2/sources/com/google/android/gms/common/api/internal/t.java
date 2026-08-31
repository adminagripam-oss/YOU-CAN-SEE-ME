package com.google.android.gms.common.api.internal;

import android.os.Handler;
import android.util.Log;
import i3.a;
import java.util.Map;
import java.util.Set;
import k3.c;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class t implements c.InterfaceC0115c, j3.z {

    /* renamed from: a  reason: collision with root package name */
    private final a.f f5069a;

    /* renamed from: b  reason: collision with root package name */
    private final j3.b f5070b;

    /* renamed from: c  reason: collision with root package name */
    private k3.k f5071c = null;

    /* renamed from: d  reason: collision with root package name */
    private Set f5072d = null;

    /* renamed from: e  reason: collision with root package name */
    private boolean f5073e = false;

    /* renamed from: f  reason: collision with root package name */
    final /* synthetic */ b f5074f;

    public t(b bVar, a.f fVar, j3.b bVar2) {
        this.f5074f = bVar;
        this.f5069a = fVar;
        this.f5070b = bVar2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void i() {
        k3.k kVar;
        if (!this.f5073e || (kVar = this.f5071c) == null) {
            return;
        }
        this.f5069a.j(kVar, this.f5072d);
    }

    @Override // j3.z
    public final void a(h3.a aVar) {
        Map map;
        map = this.f5074f.f4991j;
        q qVar = (q) map.get(this.f5070b);
        if (qVar != null) {
            qVar.I(aVar);
        }
    }

    @Override // k3.c.InterfaceC0115c
    public final void b(h3.a aVar) {
        Handler handler;
        handler = this.f5074f.f4995n;
        handler.post(new s(this, aVar));
    }

    @Override // j3.z
    public final void c(k3.k kVar, Set set) {
        if (kVar == null || set == null) {
            Log.wtf("GoogleApiManager", "Received null response from onSignInSuccess", new Exception());
            a(new h3.a(4));
            return;
        }
        this.f5071c = kVar;
        this.f5072d = set;
        i();
    }

    @Override // j3.z
    public final void d(int i8) {
        Map map;
        boolean z7;
        map = this.f5074f.f4991j;
        q qVar = (q) map.get(this.f5070b);
        if (qVar != null) {
            z7 = qVar.f5060m;
            if (z7) {
                qVar.I(new h3.a(17));
            } else {
                qVar.d(i8);
            }
        }
    }
}
