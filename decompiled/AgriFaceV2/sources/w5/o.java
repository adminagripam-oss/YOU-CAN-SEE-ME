package w5;

import android.os.Handler;
import android.os.HandlerThread;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class o {

    /* renamed from: a  reason: collision with root package name */
    private final String f12544a;

    /* renamed from: b  reason: collision with root package name */
    private final int f12545b;

    /* renamed from: c  reason: collision with root package name */
    private HandlerThread f12546c;

    /* renamed from: d  reason: collision with root package name */
    private Handler f12547d;

    /* renamed from: e  reason: collision with root package name */
    protected Runnable f12548e;

    /* renamed from: f  reason: collision with root package name */
    private m f12549f;

    /* JADX INFO: Access modifiers changed from: package-private */
    public o(String str, int i8) {
        this.f12544a = str;
        this.f12545b = i8;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean b() {
        m mVar = this.f12549f;
        return mVar != null && mVar.b();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Integer d() {
        m mVar = this.f12549f;
        if (mVar != null) {
            return mVar.a();
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void e(final m mVar) {
        this.f12547d.post(new Runnable() { // from class: w5.n
            @Override // java.lang.Runnable
            public final void run() {
                o.this.c(mVar);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public synchronized void f() {
        HandlerThread handlerThread = this.f12546c;
        if (handlerThread != null) {
            handlerThread.quit();
            this.f12546c = null;
            this.f12547d = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public synchronized void g(Runnable runnable) {
        HandlerThread handlerThread = new HandlerThread(this.f12544a, this.f12545b);
        this.f12546c = handlerThread;
        handlerThread.start();
        this.f12547d = new Handler(this.f12546c.getLooper());
        this.f12548e = runnable;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: h */
    public void c(m mVar) {
        mVar.f12541b.run();
        this.f12549f = mVar;
        this.f12548e.run();
    }
}
