package i3;

import android.accounts.Account;
import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import com.google.android.gms.common.api.GoogleApiActivity;
import com.google.android.gms.common.api.internal.c;
import i3.a;
import j3.a0;
import j3.o;
import java.util.Collections;
import k3.e;
import k3.q;
/* loaded from: classes.dex */
public abstract class e {

    /* renamed from: a  reason: collision with root package name */
    private final Context f7490a;

    /* renamed from: b  reason: collision with root package name */
    private final String f7491b;

    /* renamed from: c  reason: collision with root package name */
    private final i3.a f7492c;

    /* renamed from: d  reason: collision with root package name */
    private final a.d f7493d;

    /* renamed from: e  reason: collision with root package name */
    private final j3.b f7494e;

    /* renamed from: f  reason: collision with root package name */
    private final Looper f7495f;

    /* renamed from: g  reason: collision with root package name */
    private final int f7496g;

    /* renamed from: h  reason: collision with root package name */
    private final f f7497h;

    /* renamed from: i  reason: collision with root package name */
    private final j3.j f7498i;

    /* renamed from: j  reason: collision with root package name */
    protected final com.google.android.gms.common.api.internal.b f7499j;

    /* loaded from: classes.dex */
    public static class a {

        /* renamed from: c  reason: collision with root package name */
        public static final a f7500c = new C0100a().a();

        /* renamed from: a  reason: collision with root package name */
        public final j3.j f7501a;

        /* renamed from: b  reason: collision with root package name */
        public final Looper f7502b;

        /* renamed from: i3.e$a$a  reason: collision with other inner class name */
        /* loaded from: classes.dex */
        public static class C0100a {

            /* renamed from: a  reason: collision with root package name */
            private j3.j f7503a;

            /* renamed from: b  reason: collision with root package name */
            private Looper f7504b;

            public a a() {
                if (this.f7503a == null) {
                    this.f7503a = new j3.a();
                }
                if (this.f7504b == null) {
                    this.f7504b = Looper.getMainLooper();
                }
                return new a(this.f7503a, this.f7504b);
            }
        }

        private a(j3.j jVar, Account account, Looper looper) {
            this.f7501a = jVar;
            this.f7502b = looper;
        }
    }

    private e(Context context, Activity activity, i3.a aVar, a.d dVar, a aVar2) {
        q.h(context, "Null context is not permitted.");
        q.h(aVar, "Api must not be null.");
        q.h(aVar2, "Settings must not be null; use Settings.DEFAULT_SETTINGS instead.");
        Context context2 = (Context) q.h(context.getApplicationContext(), "The provided context did not have an application context.");
        this.f7490a = context2;
        String attributionTag = Build.VERSION.SDK_INT >= 30 ? context.getAttributionTag() : l(context);
        this.f7491b = attributionTag;
        this.f7492c = aVar;
        this.f7493d = dVar;
        this.f7495f = aVar2.f7502b;
        j3.b a8 = j3.b.a(aVar, dVar, attributionTag);
        this.f7494e = a8;
        this.f7497h = new o(this);
        com.google.android.gms.common.api.internal.b t8 = com.google.android.gms.common.api.internal.b.t(context2);
        this.f7499j = t8;
        this.f7496g = t8.k();
        this.f7498i = aVar2.f7501a;
        if (activity != null && !(activity instanceof GoogleApiActivity) && Looper.myLooper() == Looper.getMainLooper()) {
            com.google.android.gms.common.api.internal.k.u(activity, t8, a8);
        }
        t8.F(this);
    }

    private final f4.j r(int i8, com.google.android.gms.common.api.internal.g gVar) {
        f4.k kVar = new f4.k();
        this.f7499j.B(this, i8, gVar, kVar, this.f7498i);
        return kVar.a();
    }

    protected e.a g() {
        e.a aVar = new e.a();
        aVar.d(null);
        aVar.c(Collections.emptySet());
        aVar.e(this.f7490a.getClass().getName());
        aVar.b(this.f7490a.getPackageName());
        return aVar;
    }

    public f4.j h(com.google.android.gms.common.api.internal.g gVar) {
        return r(2, gVar);
    }

    public f4.j i(com.google.android.gms.common.api.internal.g gVar) {
        return r(0, gVar);
    }

    public f4.j j(com.google.android.gms.common.api.internal.f fVar) {
        q.g(fVar);
        q.h(fVar.f5012a.b(), "Listener has already been released.");
        q.h(fVar.f5013b.a(), "Listener has already been released.");
        return this.f7499j.v(this, fVar.f5012a, fVar.f5013b, fVar.f5014c);
    }

    public f4.j k(c.a aVar, int i8) {
        q.h(aVar, "Listener key cannot be null.");
        return this.f7499j.w(this, aVar, i8);
    }

    protected String l(Context context) {
        return null;
    }

    public final j3.b m() {
        return this.f7494e;
    }

    protected String n() {
        return this.f7491b;
    }

    public final int o() {
        return this.f7496g;
    }

    public final a.f p(Looper looper, com.google.android.gms.common.api.internal.q qVar) {
        a.f a8 = ((a.AbstractC0098a) q.g(this.f7492c.a())).a(this.f7490a, looper, g().a(), this.f7493d, qVar, qVar);
        String n8 = n();
        if (n8 != null && (a8 instanceof k3.c)) {
            ((k3.c) a8).O(n8);
        }
        if (n8 == null || !(a8 instanceof j3.g)) {
            return a8;
        }
        j3.g gVar = (j3.g) a8;
        throw null;
    }

    public final a0 q(Context context, Handler handler) {
        return new a0(context, handler, g().a());
    }

    public e(Context context, i3.a aVar, a.d dVar, a aVar2) {
        this(context, null, aVar, dVar, aVar2);
    }
}
