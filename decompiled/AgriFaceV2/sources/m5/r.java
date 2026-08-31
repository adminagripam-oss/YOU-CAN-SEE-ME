package m5;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;
import java.util.Set;
import m5.a;
/* loaded from: classes.dex */
final class r extends PhantomReference implements a.InterfaceC0122a {

    /* renamed from: a  reason: collision with root package name */
    private final Set f9194a;

    /* renamed from: b  reason: collision with root package name */
    private final Runnable f9195b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ r(Object obj, ReferenceQueue referenceQueue, Set set, Runnable runnable, q qVar) {
        super(obj, referenceQueue);
        this.f9194a = set;
        this.f9195b = runnable;
    }

    @Override // m5.a.InterfaceC0122a
    public final void a() {
        if (this.f9194a.remove(this)) {
            clear();
            this.f9195b.run();
        }
    }
}
