package i6;

import java.nio.ByteBuffer;
/* loaded from: classes.dex */
public interface b {

    /* loaded from: classes.dex */
    public interface a {
        void a(ByteBuffer byteBuffer, InterfaceC0103b interfaceC0103b);
    }

    /* renamed from: i6.b$b  reason: collision with other inner class name */
    /* loaded from: classes.dex */
    public interface InterfaceC0103b {
        void a(ByteBuffer byteBuffer);
    }

    /* loaded from: classes.dex */
    public interface c {
    }

    /* loaded from: classes.dex */
    public static class d {

        /* renamed from: a  reason: collision with root package name */
        private boolean f7521a = true;

        public boolean a() {
            return this.f7521a;
        }
    }

    c a(d dVar);

    void b(String str, a aVar, c cVar);

    default c c() {
        return a(new d());
    }

    void d(String str, a aVar);

    void e(String str, ByteBuffer byteBuffer);

    void h(String str, ByteBuffer byteBuffer, InterfaceC0103b interfaceC0103b);
}
