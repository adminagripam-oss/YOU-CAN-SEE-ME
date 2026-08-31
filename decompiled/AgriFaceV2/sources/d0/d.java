package d0;

import android.os.Build;
import android.util.CloseGuard;
/* loaded from: classes.dex */
public final class d {

    /* renamed from: a  reason: collision with root package name */
    private final b f6360a;

    /* loaded from: classes.dex */
    static final class a implements b {

        /* renamed from: a  reason: collision with root package name */
        private final CloseGuard f6361a = new CloseGuard();

        a() {
        }

        @Override // d0.d.b
        public void a() {
            this.f6361a.warnIfOpen();
        }

        @Override // d0.d.b
        public void b(String str) {
            this.f6361a.open(str);
        }

        @Override // d0.d.b
        public void close() {
            this.f6361a.close();
        }
    }

    /* loaded from: classes.dex */
    private interface b {
        void a();

        void b(String str);

        void close();
    }

    /* loaded from: classes.dex */
    static final class c implements b {
        c() {
        }

        @Override // d0.d.b
        public void a() {
        }

        @Override // d0.d.b
        public void b(String str) {
            g1.e.k(str, "CloseMethodName must not be null.");
        }

        @Override // d0.d.b
        public void close() {
        }
    }

    private d(b bVar) {
        this.f6360a = bVar;
    }

    public static d b() {
        return Build.VERSION.SDK_INT >= 30 ? new d(new a()) : new d(new c());
    }

    public void a() {
        this.f6360a.close();
    }

    public void c(String str) {
        this.f6360a.b(str);
    }

    public void d() {
        this.f6360a.a();
    }
}
