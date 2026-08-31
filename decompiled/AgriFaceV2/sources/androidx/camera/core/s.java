package androidx.camera.core;

import java.util.concurrent.atomic.AtomicBoolean;
/* loaded from: classes.dex */
final class s extends e {

    /* renamed from: h  reason: collision with root package name */
    private final AtomicBoolean f2772h;

    /* JADX INFO: Access modifiers changed from: package-private */
    public s(n nVar) {
        super(nVar);
        this.f2772h = new AtomicBoolean(false);
    }

    @Override // androidx.camera.core.e, androidx.camera.core.n, java.lang.AutoCloseable
    public void close() {
        if (this.f2772h.getAndSet(true)) {
            return;
        }
        super.close();
    }
}
