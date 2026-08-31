package i6;

import i6.b;
import java.nio.ByteBuffer;
/* loaded from: classes.dex */
public class j {

    /* renamed from: a  reason: collision with root package name */
    private final i6.b f7537a;

    /* renamed from: b  reason: collision with root package name */
    private final String f7538b;

    /* renamed from: c  reason: collision with root package name */
    private final k f7539c;

    /* renamed from: d  reason: collision with root package name */
    private final b.c f7540d;

    /* loaded from: classes.dex */
    private final class a implements b.a {

        /* renamed from: a  reason: collision with root package name */
        private final c f7541a;

        /* renamed from: i6.j$a$a  reason: collision with other inner class name */
        /* loaded from: classes.dex */
        class C0105a implements d {

            /* renamed from: a  reason: collision with root package name */
            final /* synthetic */ b.InterfaceC0103b f7543a;

            C0105a(b.InterfaceC0103b interfaceC0103b) {
                this.f7543a = interfaceC0103b;
            }

            @Override // i6.j.d
            public void error(String str, String str2, Object obj) {
                this.f7543a.a(j.this.f7539c.d(str, str2, obj));
            }

            @Override // i6.j.d
            public void notImplemented() {
                this.f7543a.a(null);
            }

            @Override // i6.j.d
            public void success(Object obj) {
                this.f7543a.a(j.this.f7539c.b(obj));
            }
        }

        a(c cVar) {
            this.f7541a = cVar;
        }

        @Override // i6.b.a
        public void a(ByteBuffer byteBuffer, b.InterfaceC0103b interfaceC0103b) {
            try {
                this.f7541a.onMethodCall(j.this.f7539c.e(byteBuffer), new C0105a(interfaceC0103b));
            } catch (RuntimeException e8) {
                z5.b.c("MethodChannel#" + j.this.f7538b, "Failed to handle method call", e8);
                interfaceC0103b.a(j.this.f7539c.c("error", e8.getMessage(), null, z5.b.d(e8)));
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public final class b implements b.InterfaceC0103b {

        /* renamed from: a  reason: collision with root package name */
        private final d f7545a;

        b(d dVar) {
            this.f7545a = dVar;
        }

        @Override // i6.b.InterfaceC0103b
        public void a(ByteBuffer byteBuffer) {
            try {
                if (byteBuffer == null) {
                    this.f7545a.notImplemented();
                } else {
                    try {
                        this.f7545a.success(j.this.f7539c.f(byteBuffer));
                    } catch (i6.d e8) {
                        this.f7545a.error(e8.f7531e, e8.getMessage(), e8.f7532f);
                    }
                }
            } catch (RuntimeException e9) {
                z5.b.c("MethodChannel#" + j.this.f7538b, "Failed to handle method call result", e9);
            }
        }
    }

    /* loaded from: classes.dex */
    public interface c {
        void onMethodCall(i iVar, d dVar);
    }

    /* loaded from: classes.dex */
    public interface d {
        void error(String str, String str2, Object obj);

        void notImplemented();

        void success(Object obj);
    }

    public j(i6.b bVar, String str) {
        this(bVar, str, o.f7550b);
    }

    public void c(String str, Object obj) {
        d(str, obj, null);
    }

    public void d(String str, Object obj, d dVar) {
        this.f7537a.h(this.f7538b, this.f7539c.a(new i(str, obj)), dVar == null ? null : new b(dVar));
    }

    public void e(c cVar) {
        if (this.f7540d != null) {
            this.f7537a.b(this.f7538b, cVar != null ? new a(cVar) : null, this.f7540d);
        } else {
            this.f7537a.d(this.f7538b, cVar != null ? new a(cVar) : null);
        }
    }

    public j(i6.b bVar, String str, k kVar) {
        this(bVar, str, kVar, null);
    }

    public j(i6.b bVar, String str, k kVar, b.c cVar) {
        this.f7537a = bVar;
        this.f7538b = str;
        this.f7539c = kVar;
        this.f7540d = cVar;
    }
}
