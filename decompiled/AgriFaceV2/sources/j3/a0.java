package j3;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import i3.a;
import i3.f;
import java.util.Set;
import k3.l0;
/* loaded from: classes.dex */
public final class a0 extends e4.d implements f.a, f.b {

    /* renamed from: l  reason: collision with root package name */
    private static final a.AbstractC0098a f8203l = d4.d.f6438c;

    /* renamed from: e  reason: collision with root package name */
    private final Context f8204e;

    /* renamed from: f  reason: collision with root package name */
    private final Handler f8205f;

    /* renamed from: g  reason: collision with root package name */
    private final a.AbstractC0098a f8206g;

    /* renamed from: h  reason: collision with root package name */
    private final Set f8207h;

    /* renamed from: i  reason: collision with root package name */
    private final k3.e f8208i;

    /* renamed from: j  reason: collision with root package name */
    private d4.e f8209j;

    /* renamed from: k  reason: collision with root package name */
    private z f8210k;

    public a0(Context context, Handler handler, k3.e eVar) {
        a.AbstractC0098a abstractC0098a = f8203l;
        this.f8204e = context;
        this.f8205f = handler;
        this.f8208i = (k3.e) k3.q.h(eVar, "ClientSettings must not be null");
        this.f8207h = eVar.e();
        this.f8206g = abstractC0098a;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* bridge */ /* synthetic */ void f0(a0 a0Var, e4.l lVar) {
        h3.a a8 = lVar.a();
        if (a8.e()) {
            l0 l0Var = (l0) k3.q.g(lVar.b());
            a8 = l0Var.a();
            if (a8.e()) {
                a0Var.f8210k.c(l0Var.b(), a0Var.f8207h);
                a0Var.f8209j.n();
            }
            String valueOf = String.valueOf(a8);
            Log.wtf("SignInCoordinator", "Sign-in succeeded with resolve account failure: ".concat(valueOf), new Exception());
        }
        a0Var.f8210k.a(a8);
        a0Var.f8209j.n();
    }

    @Override // j3.c
    public final void d(int i8) {
        this.f8210k.d(i8);
    }

    @Override // j3.h
    public final void e(h3.a aVar) {
        this.f8210k.a(aVar);
    }

    @Override // j3.c
    public final void f(Bundle bundle) {
        this.f8209j.b(this);
    }

    @Override // e4.f
    public final void g(e4.l lVar) {
        this.f8205f.post(new y(this, lVar));
    }

    /* JADX WARN: Type inference failed for: r0v3, types: [d4.e, i3.a$f] */
    public final void g0(z zVar) {
        d4.e eVar = this.f8209j;
        if (eVar != null) {
            eVar.n();
        }
        this.f8208i.i(Integer.valueOf(System.identityHashCode(this)));
        a.AbstractC0098a abstractC0098a = this.f8206g;
        Context context = this.f8204e;
        Handler handler = this.f8205f;
        k3.e eVar2 = this.f8208i;
        this.f8209j = abstractC0098a.a(context, handler.getLooper(), eVar2, eVar2.f(), this, this);
        this.f8210k = zVar;
        Set set = this.f8207h;
        if (set == null || set.isEmpty()) {
            this.f8205f.post(new x(this));
        } else {
            this.f8209j.p();
        }
    }

    public final void h0() {
        d4.e eVar = this.f8209j;
        if (eVar != null) {
            eVar.n();
        }
    }
}
