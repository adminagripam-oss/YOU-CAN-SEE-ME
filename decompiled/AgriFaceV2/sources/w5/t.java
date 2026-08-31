package w5;

import android.os.Handler;
import android.os.HandlerThread;
/* loaded from: classes.dex */
class t implements p {

    /* renamed from: a  reason: collision with root package name */
    final String f12561a;

    /* renamed from: b  reason: collision with root package name */
    final int f12562b;

    /* renamed from: c  reason: collision with root package name */
    private HandlerThread f12563c;

    /* renamed from: d  reason: collision with root package name */
    private Handler f12564d;

    /* JADX INFO: Access modifiers changed from: package-private */
    public t(String str, int i8) {
        this.f12561a = str;
        this.f12562b = i8;
    }

    @Override // w5.p
    public void b() {
        HandlerThread handlerThread = this.f12563c;
        if (handlerThread != null) {
            handlerThread.quit();
            this.f12563c = null;
            this.f12564d = null;
        }
    }

    @Override // w5.p
    public void c(m mVar) {
        this.f12564d.post(mVar.f12541b);
    }

    @Override // w5.p
    public void start() {
        HandlerThread handlerThread = new HandlerThread(this.f12561a, this.f12562b);
        this.f12563c = handlerThread;
        handlerThread.start();
        this.f12564d = new Handler(this.f12563c.getLooper());
    }
}
