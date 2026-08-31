package w1;

import android.os.Bundle;
import androidx.lifecycle.i;
import d7.k;
/* loaded from: classes.dex */
public final class e {

    /* renamed from: d  reason: collision with root package name */
    public static final a f12460d = new a(null);

    /* renamed from: a  reason: collision with root package name */
    private final f f12461a;

    /* renamed from: b  reason: collision with root package name */
    private final d f12462b;

    /* renamed from: c  reason: collision with root package name */
    private boolean f12463c;

    /* loaded from: classes.dex */
    public static final class a {
        private a() {
        }

        public /* synthetic */ a(d7.g gVar) {
            this();
        }

        public final e a(f fVar) {
            k.e(fVar, "owner");
            return new e(fVar, null);
        }
    }

    private e(f fVar) {
        this.f12461a = fVar;
        this.f12462b = new d();
    }

    public static final e a(f fVar) {
        return f12460d.a(fVar);
    }

    public final d b() {
        return this.f12462b;
    }

    public final void c() {
        i lifecycle = this.f12461a.getLifecycle();
        if (!(lifecycle.b() == i.b.INITIALIZED)) {
            throw new IllegalStateException("Restarter must be created only during owner's initialization stage".toString());
        }
        lifecycle.a(new b(this.f12461a));
        this.f12462b.e(lifecycle);
        this.f12463c = true;
    }

    public final void d(Bundle bundle) {
        if (!this.f12463c) {
            c();
        }
        i lifecycle = this.f12461a.getLifecycle();
        if (!lifecycle.b().e(i.b.STARTED)) {
            this.f12462b.f(bundle);
            return;
        }
        throw new IllegalStateException(("performRestore cannot be called when owner is " + lifecycle.b()).toString());
    }

    public final void e(Bundle bundle) {
        k.e(bundle, "outBundle");
        this.f12462b.g(bundle);
    }

    public /* synthetic */ e(f fVar, d7.g gVar) {
        this(fVar);
    }
}
