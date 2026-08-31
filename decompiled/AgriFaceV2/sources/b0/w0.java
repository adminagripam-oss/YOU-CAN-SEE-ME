package b0;

import b0.d2;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
/* loaded from: classes.dex */
public final class w0 implements d2 {

    /* renamed from: b  reason: collision with root package name */
    private static final w0 f4450b = new w0(null);

    /* renamed from: a  reason: collision with root package name */
    private final i4.d f4451a;

    private w0(Object obj) {
        this.f4451a = f0.n.p(obj);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void f(d2.a aVar) {
        try {
            aVar.b(this.f4451a.get());
        } catch (InterruptedException | ExecutionException e8) {
            aVar.a(e8);
        }
    }

    public static d2 g(Object obj) {
        return obj == null ? f4450b : new w0(obj);
    }

    @Override // b0.d2
    public i4.d a() {
        return this.f4451a;
    }

    @Override // b0.d2
    public void b(d2.a aVar) {
    }

    @Override // b0.d2
    public void d(Executor executor, final d2.a aVar) {
        this.f4451a.d(new Runnable() { // from class: b0.v0
            @Override // java.lang.Runnable
            public final void run() {
                w0.this.f(aVar);
            }
        }, executor);
    }
}
