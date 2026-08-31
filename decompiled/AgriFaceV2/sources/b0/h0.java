package b0;

import java.util.Collection;
import y.h2;
/* loaded from: classes.dex */
public interface h0 extends y.j, h2.b {

    /* loaded from: classes.dex */
    public enum a {
        RELEASED(false),
        RELEASING(true),
        CLOSED(false),
        PENDING_OPEN(false),
        CLOSING(true),
        OPENING(true),
        OPEN(true),
        CONFIGURED(true);
        

        /* renamed from: e  reason: collision with root package name */
        private final boolean f4217e;

        a(boolean z7) {
            this.f4217e = z7;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public boolean e() {
            return this.f4217e;
        }
    }

    @Override // y.j
    default y.k a() {
        return p();
    }

    @Override // y.j
    default y.p b() {
        return l();
    }

    default void c(boolean z7) {
    }

    void e(Collection collection);

    default void g(z zVar) {
    }

    void h(Collection collection);

    default boolean i() {
        return b().d() == 0;
    }

    default boolean j() {
        return true;
    }

    default void k(boolean z7) {
    }

    g0 l();

    d0 p();

    default z q() {
        return c0.a();
    }
}
