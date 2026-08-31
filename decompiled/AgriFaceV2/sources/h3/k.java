package h3;

import android.content.Context;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class k extends v3.h {

    /* renamed from: b  reason: collision with root package name */
    private final Context f7247b;

    /* renamed from: c  reason: collision with root package name */
    final /* synthetic */ d f7248c;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public k(d dVar, Context context) {
        super(Looper.myLooper() == null ? Looper.getMainLooper() : Looper.myLooper());
        this.f7248c = dVar;
        this.f7247b = context.getApplicationContext();
    }

    @Override // android.os.Handler
    public final void handleMessage(Message message) {
        int i8 = message.what;
        if (i8 != 1) {
            Log.w("GoogleApiAvailability", "Don't know how to handle this message: " + i8);
            return;
        }
        d dVar = this.f7248c;
        int g8 = dVar.g(this.f7247b);
        if (dVar.j(g8)) {
            this.f7248c.o(this.f7247b, g8);
        }
    }
}
