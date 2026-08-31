package com.google.android.gms.common.api.internal;

import java.util.Map;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class i implements f4.e {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ f4.k f5036a;

    /* renamed from: b  reason: collision with root package name */
    final /* synthetic */ j f5037b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public i(j jVar, f4.k kVar) {
        this.f5037b = jVar;
        this.f5036a = kVar;
    }

    @Override // f4.e
    public final void a(f4.j jVar) {
        Map map;
        map = this.f5037b.f5043b;
        map.remove(this.f5036a);
    }
}
