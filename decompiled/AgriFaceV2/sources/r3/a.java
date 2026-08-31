package r3;

import android.os.Handler;
import android.os.Looper;
import java.util.concurrent.Executor;
import w3.e;
/* loaded from: classes.dex */
public class a implements Executor {

    /* renamed from: e  reason: collision with root package name */
    private final Handler f11320e;

    public a(Looper looper) {
        this.f11320e = new e(looper);
    }

    @Override // java.util.concurrent.Executor
    public final void execute(Runnable runnable) {
        this.f11320e.post(runnable);
    }
}
