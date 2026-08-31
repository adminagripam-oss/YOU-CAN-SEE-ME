package com.google.android.gms.common.api.internal;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class r {

    /* renamed from: a  reason: collision with root package name */
    private final j3.b f5065a;

    /* renamed from: b  reason: collision with root package name */
    private final h3.c f5066b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ r(j3.b bVar, h3.c cVar, j3.n nVar) {
        this.f5065a = bVar;
        this.f5066b = cVar;
    }

    public final boolean equals(Object obj) {
        if (obj != null && (obj instanceof r)) {
            r rVar = (r) obj;
            if (k3.p.a(this.f5065a, rVar.f5065a) && k3.p.a(this.f5066b, rVar.f5066b)) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        return k3.p.b(this.f5065a, this.f5066b);
    }

    public final String toString() {
        return k3.p.c(this).a("key", this.f5065a).a("feature", this.f5066b).toString();
    }
}
