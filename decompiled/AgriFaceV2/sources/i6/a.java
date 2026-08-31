package i6;

import i6.b;
import java.nio.ByteBuffer;
/* loaded from: classes.dex */
public final class a {

    /* renamed from: a  reason: collision with root package name */
    private final i6.b f7511a;

    /* renamed from: b  reason: collision with root package name */
    private final String f7512b;

    /* renamed from: c  reason: collision with root package name */
    private final h f7513c;

    /* renamed from: d  reason: collision with root package name */
    private final b.c f7514d;

    /* loaded from: classes.dex */
    private final class b implements b.a {

        /* renamed from: a  reason: collision with root package name */
        private final d f7515a;

        /* renamed from: i6.a$b$a  reason: collision with other inner class name */
        /* loaded from: classes.dex */
        class C0102a implements e {

            /* renamed from: a  reason: collision with root package name */
            final /* synthetic */ b.InterfaceC0103b f7517a;

            C0102a(b.InterfaceC0103b interfaceC0103b) {
                this.f7517a = interfaceC0103b;
            }

            @Override // i6.a.e
            public void a(Object obj) {
                this.f7517a.a(a.this.f7513c.a(obj));
            }
        }

        private b(d dVar) {
            this.f7515a = dVar;
        }

        @Override // i6.b.a
        public void a(ByteBuffer byteBuffer, b.InterfaceC0103b interfaceC0103b) {
            try {
                this.f7515a.a(a.this.f7513c.b(byteBuffer), new C0102a(interfaceC0103b));
            } catch (RuntimeException e8) {
                z5.b.c("BasicMessageChannel#" + a.this.f7512b, "Failed to handle message", e8);
                interfaceC0103b.a(null);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public final class c implements b.InterfaceC0103b {

        /* renamed from: a  reason: collision with root package name */
        private final e f7519a;

        private c(e eVar) {
            this.f7519a = eVar;
        }

        @Override // i6.b.InterfaceC0103b
        public void a(ByteBuffer byteBuffer) {
            try {
                this.f7519a.a(a.this.f7513c.b(byteBuffer));
            } catch (RuntimeException e8) {
                z5.b.c("BasicMessageChannel#" + a.this.f7512b, "Failed to handle message reply", e8);
            }
        }
    }

    /* loaded from: classes.dex */
    public interface d {
        void a(Object obj, e eVar);
    }

    /* loaded from: classes.dex */
    public interface e {
        void a(Object obj);
    }

    public a(i6.b bVar, String str, h hVar) {
        this(bVar, str, hVar, null);
    }

    public void c(Object obj) {
        d(obj, null);
    }

    public void d(Object obj, e eVar) {
        this.f7511a.h(this.f7512b, this.f7513c.a(obj), eVar != null ? new c(eVar) : null);
    }

    public void e(d dVar) {
        if (this.f7514d != null) {
            this.f7511a.b(this.f7512b, dVar != null ? new b(dVar) : null, this.f7514d);
        } else {
            this.f7511a.d(this.f7512b, dVar != null ? new b(dVar) : null);
        }
    }

    public a(i6.b bVar, String str, h hVar, b.c cVar) {
        this.f7511a = bVar;
        this.f7512b = str;
        this.f7513c = hVar;
        this.f7514d = cVar;
    }
}
