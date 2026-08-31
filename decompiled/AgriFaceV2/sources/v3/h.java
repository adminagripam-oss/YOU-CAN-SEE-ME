package v3;

import android.os.Handler;
import android.os.Looper;
/* loaded from: classes.dex */
public class h extends Handler {

    /* renamed from: a  reason: collision with root package name */
    private final Looper f12357a;

    public h(Looper looper) {
        super(looper);
        this.f12357a = Looper.getMainLooper();
    }

    public h(Looper looper, Handler.Callback callback) {
        super(looper, callback);
        this.f12357a = Looper.getMainLooper();
    }
}
