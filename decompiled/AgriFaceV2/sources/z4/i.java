package z4;

import java.security.GeneralSecurityException;
import java.security.Provider;
import java.security.Security;
import java.util.ArrayList;
import java.util.List;
import z4.j;
/* loaded from: classes.dex */
public final class i {

    /* renamed from: b  reason: collision with root package name */
    public static final i f14427b = new i(new j.a());

    /* renamed from: c  reason: collision with root package name */
    public static final i f14428c = new i(new j.e());

    /* renamed from: d  reason: collision with root package name */
    public static final i f14429d = new i(new j.g());

    /* renamed from: e  reason: collision with root package name */
    public static final i f14430e = new i(new j.f());

    /* renamed from: f  reason: collision with root package name */
    public static final i f14431f = new i(new j.b());

    /* renamed from: g  reason: collision with root package name */
    public static final i f14432g = new i(new j.d());

    /* renamed from: h  reason: collision with root package name */
    public static final i f14433h = new i(new j.c());

    /* renamed from: a  reason: collision with root package name */
    private final e f14434a;

    /* loaded from: classes.dex */
    private static class b implements e {

        /* renamed from: a  reason: collision with root package name */
        private final j f14435a;

        private b(j jVar) {
            this.f14435a = jVar;
        }

        @Override // z4.i.e
        public Object a(String str) {
            Exception exc = null;
            for (Provider provider : i.b("GmsCore_OpenSSL", "AndroidOpenSSL")) {
                try {
                    return this.f14435a.a(str, provider);
                } catch (Exception e8) {
                    if (exc == null) {
                        exc = e8;
                    }
                }
            }
            return this.f14435a.a(str, null);
        }
    }

    /* loaded from: classes.dex */
    private static class c implements e {

        /* renamed from: a  reason: collision with root package name */
        private final j f14436a;

        private c(j jVar) {
            this.f14436a = jVar;
        }

        @Override // z4.i.e
        public Object a(String str) {
            return this.f14436a.a(str, null);
        }
    }

    /* loaded from: classes.dex */
    private static class d implements e {

        /* renamed from: a  reason: collision with root package name */
        private final j f14437a;

        private d(j jVar) {
            this.f14437a = jVar;
        }

        @Override // z4.i.e
        public Object a(String str) {
            Exception exc = null;
            for (Provider provider : i.b("GmsCore_OpenSSL", "AndroidOpenSSL", "Conscrypt")) {
                try {
                    return this.f14437a.a(str, provider);
                } catch (Exception e8) {
                    if (exc == null) {
                        exc = e8;
                    }
                }
            }
            throw new GeneralSecurityException("No good Provider found.", exc);
        }
    }

    /* loaded from: classes.dex */
    private interface e {
        Object a(String str);
    }

    public i(j jVar) {
        this.f14434a = o4.b.c() ? new d(jVar) : q.a() ? new b(jVar) : new c(jVar);
    }

    public static List b(String... strArr) {
        ArrayList arrayList = new ArrayList();
        for (String str : strArr) {
            Provider provider = Security.getProvider(str);
            if (provider != null) {
                arrayList.add(provider);
            }
        }
        return arrayList;
    }

    public Object a(String str) {
        return this.f14434a.a(str);
    }
}
