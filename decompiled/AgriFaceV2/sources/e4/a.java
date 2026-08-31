package e4;

import android.accounts.Account;
import android.content.Context;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import android.os.RemoteException;
import android.util.Log;
import i3.f;
import k3.c;
import k3.j0;
import k3.q;
/* loaded from: classes.dex */
public class a extends k3.h implements d4.e {
    public static final /* synthetic */ int M = 0;
    private final boolean I;
    private final k3.e J;
    private final Bundle K;
    private final Integer L;

    public a(Context context, Looper looper, boolean z7, k3.e eVar, Bundle bundle, f.a aVar, f.b bVar) {
        super(context, looper, 44, eVar, aVar, bVar);
        this.I = true;
        this.J = eVar;
        this.K = bundle;
        this.L = eVar.g();
    }

    public static Bundle j0(k3.e eVar) {
        eVar.f();
        Integer g8 = eVar.g();
        Bundle bundle = new Bundle();
        bundle.putParcelable("com.google.android.gms.signin.internal.clientRequestedAccount", eVar.a());
        if (g8 != null) {
            bundle.putInt("com.google.android.gms.common.internal.ClientSettings.sessionId", g8.intValue());
        }
        bundle.putBoolean("com.google.android.gms.signin.internal.offlineAccessRequested", false);
        bundle.putBoolean("com.google.android.gms.signin.internal.idTokenRequested", false);
        bundle.putString("com.google.android.gms.signin.internal.serverClientId", null);
        bundle.putBoolean("com.google.android.gms.signin.internal.usePromptModeForAuthCode", true);
        bundle.putBoolean("com.google.android.gms.signin.internal.forceCodeForRefreshToken", false);
        bundle.putString("com.google.android.gms.signin.internal.hostedDomain", null);
        bundle.putString("com.google.android.gms.signin.internal.logSessionId", null);
        bundle.putBoolean("com.google.android.gms.signin.internal.waitForAccessTokenRefresh", false);
        return bundle;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // k3.c
    public final String D() {
        return "com.google.android.gms.signin.internal.ISignInService";
    }

    @Override // k3.c
    protected final String E() {
        return "com.google.android.gms.signin.service.START";
    }

    @Override // d4.e
    public final void b(f fVar) {
        q.h(fVar, "Expecting a valid ISignInCallbacks");
        try {
            Account b8 = this.J.b();
            ((g) C()).d0(new j(1, new j0(b8, ((Integer) q.g(this.L)).intValue(), "<<default account>>".equals(b8.name) ? f3.a.a(x()).b() : null)), fVar);
        } catch (RemoteException e8) {
            Log.w("SignInClientImpl", "Remote service probably died when signIn is called");
            try {
                fVar.g(new l(1, new h3.a(8, null), null));
            } catch (RemoteException unused) {
                Log.wtf("SignInClientImpl", "ISignInCallbacks#onSignInComplete should be executed from the same process, unexpected RemoteException.", e8);
            }
        }
    }

    @Override // k3.c, i3.a.f
    public final int g() {
        return h3.g.f7238a;
    }

    @Override // k3.c, i3.a.f
    public final boolean o() {
        return this.I;
    }

    @Override // d4.e
    public final void p() {
        l(new c.d());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // k3.c
    public final /* synthetic */ IInterface r(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.signin.internal.ISignInService");
        return queryLocalInterface instanceof g ? (g) queryLocalInterface : new g(iBinder);
    }

    @Override // k3.c
    protected final Bundle z() {
        if (!x().getPackageName().equals(this.J.d())) {
            this.K.putString("com.google.android.gms.signin.internal.realClientPackageName", this.J.d());
        }
        return this.K;
    }
}
