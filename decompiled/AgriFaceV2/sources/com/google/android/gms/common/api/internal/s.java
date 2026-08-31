package com.google.android.gms.common.api.internal;

import android.util.Log;
import i3.a;
import java.util.Map;
/* loaded from: classes.dex */
final class s implements Runnable {

    /* renamed from: e  reason: collision with root package name */
    final /* synthetic */ h3.a f5067e;

    /* renamed from: f  reason: collision with root package name */
    final /* synthetic */ t f5068f;

    /* JADX INFO: Access modifiers changed from: package-private */
    public s(t tVar, h3.a aVar) {
        this.f5068f = tVar;
        this.f5067e = aVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        Map map;
        j3.b bVar;
        a.f fVar;
        a.f fVar2;
        a.f fVar3;
        a.f fVar4;
        t tVar = this.f5068f;
        map = tVar.f5074f.f4991j;
        bVar = tVar.f5070b;
        q qVar = (q) map.get(bVar);
        if (qVar == null) {
            return;
        }
        if (!this.f5067e.e()) {
            qVar.H(this.f5067e, null);
            return;
        }
        this.f5068f.f5073e = true;
        fVar = this.f5068f.f5069a;
        if (fVar.o()) {
            this.f5068f.i();
            return;
        }
        try {
            t tVar2 = this.f5068f;
            fVar3 = tVar2.f5069a;
            fVar4 = tVar2.f5069a;
            fVar3.j(null, fVar4.c());
        } catch (SecurityException e8) {
            Log.e("GoogleApiManager", "Failed to get service from broker. ", e8);
            fVar2 = this.f5068f.f5069a;
            fVar2.d("Failed to get service from broker.");
            qVar.H(new h3.a(10), null);
        }
    }
}
