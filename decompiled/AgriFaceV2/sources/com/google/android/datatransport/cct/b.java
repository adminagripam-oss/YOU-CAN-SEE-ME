package com.google.android.datatransport.cct;

import com.google.android.datatransport.cct.d;
/* loaded from: classes.dex */
final /* synthetic */ class b implements y2.a {

    /* renamed from: a  reason: collision with root package name */
    private final d f4923a;

    private b(d dVar) {
        this.f4923a = dVar;
    }

    public static y2.a a(d dVar) {
        return new b(dVar);
    }

    @Override // y2.a
    public Object apply(Object obj) {
        d.b d8;
        d8 = this.f4923a.d((d.a) obj);
        return d8;
    }
}
