package a6;

import a6.c;
import android.os.Handler;
import android.os.Looper;
/* loaded from: classes.dex */
public class g implements c.d {

    /* renamed from: a  reason: collision with root package name */
    private final Handler f1397a = p6.a.a(Looper.getMainLooper());

    @Override // a6.c.d
    public void a(Runnable runnable) {
        this.f1397a.post(runnable);
    }
}
