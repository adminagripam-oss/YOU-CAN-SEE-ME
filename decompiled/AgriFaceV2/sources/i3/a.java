package i3;

import android.content.Context;
import android.os.Looper;
import i3.f;
import java.util.Set;
import k3.c;
import k3.q;
/* loaded from: classes.dex */
public final class a {

    /* renamed from: a  reason: collision with root package name */
    private final AbstractC0098a f7485a;

    /* renamed from: b  reason: collision with root package name */
    private final g f7486b;

    /* renamed from: c  reason: collision with root package name */
    private final String f7487c;

    /* renamed from: i3.a$a  reason: collision with other inner class name */
    /* loaded from: classes.dex */
    public static abstract class AbstractC0098a extends e {
        public f a(Context context, Looper looper, k3.e eVar, Object obj, f.a aVar, f.b bVar) {
            return b(context, looper, eVar, obj, aVar, bVar);
        }

        public f b(Context context, Looper looper, k3.e eVar, Object obj, j3.c cVar, j3.h hVar) {
            throw new UnsupportedOperationException("buildClient must be implemented");
        }
    }

    /* loaded from: classes.dex */
    public interface b {
    }

    /* loaded from: classes.dex */
    public static class c {
    }

    /* loaded from: classes.dex */
    public interface d {

        /* renamed from: a  reason: collision with root package name */
        public static final C0099a f7488a = new C0099a(null);

        /* renamed from: i3.a$d$a  reason: collision with other inner class name */
        /* loaded from: classes.dex */
        public static final class C0099a implements d {
            /* synthetic */ C0099a(l lVar) {
            }
        }
    }

    /* loaded from: classes.dex */
    public static abstract class e {
    }

    /* loaded from: classes.dex */
    public interface f extends b {
        boolean a();

        Set c();

        void d(String str);

        boolean e();

        void f(c.e eVar);

        int g();

        boolean h();

        h3.c[] i();

        void j(k3.k kVar, Set set);

        String k();

        void l(c.InterfaceC0115c interfaceC0115c);

        String m();

        void n();

        boolean o();
    }

    /* loaded from: classes.dex */
    public static final class g extends c {
    }

    public a(String str, AbstractC0098a abstractC0098a, g gVar) {
        q.h(abstractC0098a, "Cannot construct an Api with a null ClientBuilder");
        q.h(gVar, "Cannot construct an Api with a null ClientKey");
        this.f7487c = str;
        this.f7485a = abstractC0098a;
        this.f7486b = gVar;
    }

    public final AbstractC0098a a() {
        return this.f7485a;
    }

    public final String b() {
        return this.f7487c;
    }
}
