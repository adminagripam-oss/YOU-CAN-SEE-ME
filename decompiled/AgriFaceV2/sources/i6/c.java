package i6;

import i6.b;
import java.nio.ByteBuffer;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
/* loaded from: classes.dex */
public final class c {

    /* renamed from: a  reason: collision with root package name */
    private final i6.b f7522a;

    /* renamed from: b  reason: collision with root package name */
    private final String f7523b;

    /* renamed from: c  reason: collision with root package name */
    private final k f7524c;

    /* renamed from: d  reason: collision with root package name */
    private final b.c f7525d;

    /* loaded from: classes.dex */
    public interface b {
        void error(String str, String str2, Object obj);

        void success(Object obj);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: i6.c$c  reason: collision with other inner class name */
    /* loaded from: classes.dex */
    public final class C0104c implements b.a {

        /* renamed from: a  reason: collision with root package name */
        private final d f7526a;

        /* renamed from: b  reason: collision with root package name */
        private final AtomicReference f7527b = new AtomicReference(null);

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: i6.c$c$a */
        /* loaded from: classes.dex */
        public final class a implements b {

            /* renamed from: a  reason: collision with root package name */
            final AtomicBoolean f7529a;

            private a() {
                this.f7529a = new AtomicBoolean(false);
            }

            @Override // i6.c.b
            public void error(String str, String str2, Object obj) {
                if (this.f7529a.get() || C0104c.this.f7527b.get() != this) {
                    return;
                }
                c.this.f7522a.e(c.this.f7523b, c.this.f7524c.d(str, str2, obj));
            }

            @Override // i6.c.b
            public void success(Object obj) {
                if (this.f7529a.get() || C0104c.this.f7527b.get() != this) {
                    return;
                }
                c.this.f7522a.e(c.this.f7523b, c.this.f7524c.b(obj));
            }
        }

        C0104c(d dVar) {
            this.f7526a = dVar;
        }

        private void c(Object obj, b.InterfaceC0103b interfaceC0103b) {
            ByteBuffer d8;
            if (((b) this.f7527b.getAndSet(null)) != null) {
                try {
                    this.f7526a.a(obj);
                    interfaceC0103b.a(c.this.f7524c.b(null));
                    return;
                } catch (RuntimeException e8) {
                    z5.b.c("EventChannel#" + c.this.f7523b, "Failed to close event stream", e8);
                    d8 = c.this.f7524c.d("error", e8.getMessage(), null);
                }
            } else {
                d8 = c.this.f7524c.d("error", "No active stream to cancel", null);
            }
            interfaceC0103b.a(d8);
        }

        private void d(Object obj, b.InterfaceC0103b interfaceC0103b) {
            a aVar = new a();
            if (((b) this.f7527b.getAndSet(aVar)) != null) {
                try {
                    this.f7526a.a(null);
                } catch (RuntimeException e8) {
                    z5.b.c("EventChannel#" + c.this.f7523b, "Failed to close existing event stream", e8);
                }
            }
            try {
                this.f7526a.b(obj, aVar);
                interfaceC0103b.a(c.this.f7524c.b(null));
            } catch (RuntimeException e9) {
                this.f7527b.set(null);
                z5.b.c("EventChannel#" + c.this.f7523b, "Failed to open event stream", e9);
                interfaceC0103b.a(c.this.f7524c.d("error", e9.getMessage(), null));
            }
        }

        @Override // i6.b.a
        public void a(ByteBuffer byteBuffer, b.InterfaceC0103b interfaceC0103b) {
            i e8 = c.this.f7524c.e(byteBuffer);
            if (e8.f7535a.equals("listen")) {
                d(e8.f7536b, interfaceC0103b);
            } else if (e8.f7535a.equals("cancel")) {
                c(e8.f7536b, interfaceC0103b);
            } else {
                interfaceC0103b.a(null);
            }
        }
    }

    /* loaded from: classes.dex */
    public interface d {
        void a(Object obj);

        void b(Object obj, b bVar);
    }

    public c(i6.b bVar, String str) {
        this(bVar, str, o.f7550b);
    }

    public void d(d dVar) {
        if (this.f7525d != null) {
            this.f7522a.b(this.f7523b, dVar != null ? new C0104c(dVar) : null, this.f7525d);
        } else {
            this.f7522a.d(this.f7523b, dVar != null ? new C0104c(dVar) : null);
        }
    }

    public c(i6.b bVar, String str, k kVar) {
        this(bVar, str, kVar, null);
    }

    public c(i6.b bVar, String str, k kVar, b.c cVar) {
        this.f7522a = bVar;
        this.f7523b = str;
        this.f7524c = kVar;
        this.f7525d = cVar;
    }
}
