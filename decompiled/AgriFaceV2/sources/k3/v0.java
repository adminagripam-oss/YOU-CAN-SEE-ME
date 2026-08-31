package k3;

import android.util.Log;
import java.util.ArrayList;
/* loaded from: classes.dex */
public abstract class v0 {

    /* renamed from: a  reason: collision with root package name */
    private Object f8481a;

    /* renamed from: b  reason: collision with root package name */
    private boolean f8482b = false;

    /* renamed from: c  reason: collision with root package name */
    final /* synthetic */ c f8483c;

    public v0(c cVar, Object obj) {
        this.f8483c = cVar;
        this.f8481a = obj;
    }

    protected abstract void a(Object obj);

    /* JADX INFO: Access modifiers changed from: protected */
    public abstract void b();

    public final void c() {
        Object obj;
        synchronized (this) {
            obj = this.f8481a;
            if (this.f8482b) {
                String obj2 = toString();
                Log.w("GmsClient", "Callback proxy " + obj2 + " being reused. This is not safe.");
            }
        }
        if (obj != null) {
            a(obj);
        }
        synchronized (this) {
            this.f8482b = true;
        }
        e();
    }

    public final void d() {
        synchronized (this) {
            this.f8481a = null;
        }
    }

    public final void e() {
        ArrayList arrayList;
        ArrayList arrayList2;
        d();
        arrayList = this.f8483c.f8349r;
        synchronized (arrayList) {
            arrayList2 = this.f8483c.f8349r;
            arrayList2.remove(this);
        }
    }
}
