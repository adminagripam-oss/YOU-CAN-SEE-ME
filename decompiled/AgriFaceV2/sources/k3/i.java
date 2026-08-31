package k3;

import android.content.Context;
import android.content.ServiceConnection;
import android.os.HandlerThread;
import java.util.concurrent.Executor;
/* loaded from: classes.dex */
public abstract class i {

    /* renamed from: a  reason: collision with root package name */
    private static final Object f8420a = new Object();

    /* renamed from: b  reason: collision with root package name */
    private static j1 f8421b = null;

    /* renamed from: c  reason: collision with root package name */
    static HandlerThread f8422c = null;

    /* renamed from: d  reason: collision with root package name */
    private static Executor f8423d = null;

    /* renamed from: e  reason: collision with root package name */
    private static boolean f8424e = false;

    public static i a(Context context) {
        synchronized (f8420a) {
            if (f8421b == null) {
                f8421b = new j1(context.getApplicationContext(), f8424e ? b().getLooper() : context.getMainLooper(), f8423d);
            }
        }
        return f8421b;
    }

    public static HandlerThread b() {
        synchronized (f8420a) {
            HandlerThread handlerThread = f8422c;
            if (handlerThread != null) {
                return handlerThread;
            }
            HandlerThread handlerThread2 = new HandlerThread("GoogleApiHandler", 9);
            f8422c = handlerThread2;
            handlerThread2.start();
            return f8422c;
        }
    }

    protected abstract void c(e1 e1Var, ServiceConnection serviceConnection, String str);

    public final void d(String str, String str2, int i8, ServiceConnection serviceConnection, String str3, boolean z7) {
        c(new e1(str, str2, 4225, z7), serviceConnection, str3);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public abstract boolean e(e1 e1Var, ServiceConnection serviceConnection, String str, Executor executor);
}
