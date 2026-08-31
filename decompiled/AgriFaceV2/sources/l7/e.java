package l7;

import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.view.Choreographer;
import r6.k;
import r6.l;
/* loaded from: classes.dex */
public abstract class e {

    /* renamed from: a  reason: collision with root package name */
    public static final d f9073a;
    private static volatile Choreographer choreographer;

    static {
        Object a8;
        try {
            k.a aVar = k.f11363e;
            a8 = k.a(new c(a(Looper.getMainLooper(), true), null, 2, null));
        } catch (Throwable th) {
            k.a aVar2 = k.f11363e;
            a8 = k.a(l.a(th));
        }
        f9073a = k.c(a8) ? null : a8;
    }

    public static final Handler a(Looper looper, boolean z7) {
        Object newInstance;
        if (z7) {
            if (Build.VERSION.SDK_INT >= 28) {
                newInstance = Handler.class.getDeclaredMethod("createAsync", Looper.class).invoke(null, looper);
                d7.k.c(newInstance, "null cannot be cast to non-null type android.os.Handler");
            } else {
                try {
                    newInstance = Handler.class.getDeclaredConstructor(Looper.class, Handler.Callback.class, Boolean.TYPE).newInstance(looper, null, Boolean.TRUE);
                } catch (NoSuchMethodException unused) {
                    return new Handler(looper);
                }
            }
            return (Handler) newInstance;
        }
        return new Handler(looper);
    }
}
