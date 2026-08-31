package a3;

import c3.b;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final /* synthetic */ class g implements b.a {

    /* renamed from: a  reason: collision with root package name */
    private final j f195a;

    /* renamed from: b  reason: collision with root package name */
    private final v2.g f196b;

    /* renamed from: c  reason: collision with root package name */
    private final Iterable f197c;

    /* renamed from: d  reason: collision with root package name */
    private final u2.m f198d;

    /* renamed from: e  reason: collision with root package name */
    private final int f199e;

    private g(j jVar, v2.g gVar, Iterable iterable, u2.m mVar, int i8) {
        this.f195a = jVar;
        this.f196b = gVar;
        this.f197c = iterable;
        this.f198d = mVar;
        this.f199e = i8;
    }

    public static b.a b(j jVar, v2.g gVar, Iterable iterable, u2.m mVar, int i8) {
        return new g(jVar, gVar, iterable, mVar, i8);
    }

    @Override // c3.b.a
    public Object a() {
        return j.c(this.f195a, this.f196b, this.f197c, this.f198d, this.f199e);
    }
}
