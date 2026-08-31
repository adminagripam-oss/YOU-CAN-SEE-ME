package h2;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import androidx.window.extensions.layout.WindowLayoutComponent;
import d7.s;
import j2.a;
/* loaded from: classes.dex */
public interface f {

    /* renamed from: a  reason: collision with root package name */
    public static final a f7194a = a.f7195a;

    /* loaded from: classes.dex */
    public static final class a {

        /* renamed from: b  reason: collision with root package name */
        private static final boolean f7196b = false;

        /* renamed from: a  reason: collision with root package name */
        static final /* synthetic */ a f7195a = new a();

        /* renamed from: c  reason: collision with root package name */
        private static final String f7197c = s.b(f.class).c();

        /* renamed from: d  reason: collision with root package name */
        private static final r6.e f7198d = r6.f.a(C0091a.f7200f);

        /* renamed from: e  reason: collision with root package name */
        private static g f7199e = b.f7170a;

        /* renamed from: h2.f$a$a  reason: collision with other inner class name */
        /* loaded from: classes.dex */
        static final class C0091a extends d7.l implements c7.a {

            /* renamed from: f  reason: collision with root package name */
            public static final C0091a f7200f = new C0091a();

            C0091a() {
                super(0);
            }

            @Override // c7.a
            /* renamed from: b */
            public final i2.a a() {
                WindowLayoutComponent g8;
                try {
                    ClassLoader classLoader = f.class.getClassLoader();
                    e eVar = classLoader != null ? new e(classLoader, new e2.d(classLoader)) : null;
                    if (eVar == null || (g8 = eVar.g()) == null) {
                        return null;
                    }
                    a.C0109a c0109a = j2.a.f8186a;
                    d7.k.d(classLoader, "loader");
                    return c0109a.a(g8, new e2.d(classLoader));
                } catch (Throwable unused) {
                    if (a.f7196b) {
                        Log.d(a.f7197c, "Failed to load WindowExtensions");
                        return null;
                    }
                    return null;
                }
            }
        }

        private a() {
        }

        public final i2.a c() {
            return (i2.a) f7198d.getValue();
        }

        public final f d(Context context) {
            d7.k.e(context, "context");
            i2.a c8 = c();
            if (c8 == null) {
                c8 = androidx.window.layout.adapter.sidecar.b.f4112c.a(context);
            }
            return f7199e.a(new i(o.f7217b, c8));
        }
    }

    n7.c a(Activity activity);
}
