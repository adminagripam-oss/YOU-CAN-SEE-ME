package androidx.lifecycle;

import androidx.lifecycle.b;
import androidx.lifecycle.i;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class x implements k {

    /* renamed from: e  reason: collision with root package name */
    private final Object f3855e;

    /* renamed from: f  reason: collision with root package name */
    private final b.a f3856f;

    /* JADX INFO: Access modifiers changed from: package-private */
    public x(Object obj) {
        this.f3855e = obj;
        this.f3856f = b.f3733c.c(obj.getClass());
    }

    @Override // androidx.lifecycle.k
    public void f(m mVar, i.a aVar) {
        this.f3856f.a(mVar, aVar, this.f3855e);
    }
}
