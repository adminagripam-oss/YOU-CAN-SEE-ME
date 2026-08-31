package k3;

import android.content.ComponentName;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import java.util.HashMap;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class i1 implements Handler.Callback {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ j1 f8425a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ i1(j1 j1Var, h1 h1Var) {
        this.f8425a = j1Var;
    }

    @Override // android.os.Handler.Callback
    public final boolean handleMessage(Message message) {
        HashMap hashMap;
        HashMap hashMap2;
        HashMap hashMap3;
        HashMap hashMap4;
        HashMap hashMap5;
        int i8 = message.what;
        if (i8 == 0) {
            hashMap = this.f8425a.f8432f;
            synchronized (hashMap) {
                e1 e1Var = (e1) message.obj;
                hashMap2 = this.f8425a.f8432f;
                g1 g1Var = (g1) hashMap2.get(e1Var);
                if (g1Var != null && g1Var.i()) {
                    if (g1Var.j()) {
                        g1Var.g("GmsClientSupervisor");
                    }
                    hashMap3 = this.f8425a.f8432f;
                    hashMap3.remove(e1Var);
                }
            }
            return true;
        } else if (i8 != 1) {
            return false;
        } else {
            hashMap4 = this.f8425a.f8432f;
            synchronized (hashMap4) {
                e1 e1Var2 = (e1) message.obj;
                hashMap5 = this.f8425a.f8432f;
                g1 g1Var2 = (g1) hashMap5.get(e1Var2);
                if (g1Var2 != null && g1Var2.a() == 3) {
                    String valueOf = String.valueOf(e1Var2);
                    Log.e("GmsClientSupervisor", "Timeout waiting for ServiceConnection callback " + valueOf, new Exception());
                    ComponentName b8 = g1Var2.b();
                    if (b8 == null) {
                        b8 = e1Var2.a();
                    }
                    if (b8 == null) {
                        String c8 = e1Var2.c();
                        q.g(c8);
                        b8 = new ComponentName(c8, "unknown");
                    }
                    g1Var2.onServiceDisconnected(b8);
                }
            }
            return true;
        }
    }
}
