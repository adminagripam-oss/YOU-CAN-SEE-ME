package k3;

import android.app.PendingIntent;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import k3.c;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class u0 extends w3.e {

    /* renamed from: b  reason: collision with root package name */
    final /* synthetic */ c f8480b;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public u0(c cVar, Looper looper) {
        super(looper);
        this.f8480b = cVar;
    }

    private static final void a(Message message) {
        v0 v0Var = (v0) message.obj;
        v0Var.b();
        v0Var.e();
    }

    private static final boolean b(Message message) {
        int i8 = message.what;
        return i8 == 2 || i8 == 1 || i8 == 7;
    }

    @Override // android.os.Handler
    public final void handleMessage(Message message) {
        c.a aVar;
        c.a aVar2;
        h3.a aVar3;
        h3.a aVar4;
        boolean z7;
        if (this.f8480b.C.get() != message.arg1) {
            if (b(message)) {
                a(message);
                return;
            }
            return;
        }
        int i8 = message.what;
        if ((i8 == 1 || i8 == 7 || ((i8 == 4 && !this.f8480b.s()) || message.what == 5)) && !this.f8480b.h()) {
            a(message);
            return;
        }
        int i9 = message.what;
        if (i9 == 4) {
            this.f8480b.f8357z = new h3.a(message.arg2);
            if (c.f0(this.f8480b)) {
                c cVar = this.f8480b;
                z7 = cVar.A;
                if (!z7) {
                    cVar.g0(3, null);
                    return;
                }
            }
            c cVar2 = this.f8480b;
            aVar4 = cVar2.f8357z;
            h3.a aVar5 = aVar4 != null ? cVar2.f8357z : new h3.a(8);
            this.f8480b.f8347p.b(aVar5);
            this.f8480b.K(aVar5);
        } else if (i9 == 5) {
            c cVar3 = this.f8480b;
            aVar3 = cVar3.f8357z;
            h3.a aVar6 = aVar3 != null ? cVar3.f8357z : new h3.a(8);
            this.f8480b.f8347p.b(aVar6);
            this.f8480b.K(aVar6);
        } else if (i9 == 3) {
            Object obj = message.obj;
            h3.a aVar7 = new h3.a(message.arg2, obj instanceof PendingIntent ? (PendingIntent) obj : null);
            this.f8480b.f8347p.b(aVar7);
            this.f8480b.K(aVar7);
        } else if (i9 == 6) {
            this.f8480b.g0(5, null);
            c cVar4 = this.f8480b;
            aVar = cVar4.f8352u;
            if (aVar != null) {
                aVar2 = cVar4.f8352u;
                aVar2.d(message.arg2);
            }
            this.f8480b.L(message.arg2);
            c.e0(this.f8480b, 5, 1, null);
        } else if (i9 == 2 && !this.f8480b.a()) {
            a(message);
        } else if (b(message)) {
            ((v0) message.obj).c();
        } else {
            int i10 = message.what;
            Log.wtf("GmsClient", "Don't know how to handle message: " + i10, new Exception());
        }
    }
}
