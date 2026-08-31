package androidx.lifecycle;

import java.io.Closeable;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
/* loaded from: classes.dex */
public abstract class k0 {

    /* renamed from: a  reason: collision with root package name */
    private final Map f3785a = new HashMap();

    /* renamed from: b  reason: collision with root package name */
    private final Set f3786b = new LinkedHashSet();

    /* renamed from: c  reason: collision with root package name */
    private volatile boolean f3787c = false;

    private static void b(Object obj) {
        if (obj instanceof Closeable) {
            try {
                ((Closeable) obj).close();
            } catch (IOException e8) {
                throw new RuntimeException(e8);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void a() {
        this.f3787c = true;
        Map map = this.f3785a;
        if (map != null) {
            synchronized (map) {
                for (Object obj : this.f3785a.values()) {
                    b(obj);
                }
            }
        }
        Set set = this.f3786b;
        if (set != null) {
            synchronized (set) {
                for (Closeable closeable : this.f3786b) {
                    b(closeable);
                }
            }
            this.f3786b.clear();
        }
        d();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Object c(String str) {
        Object obj;
        Map map = this.f3785a;
        if (map == null) {
            return null;
        }
        synchronized (map) {
            obj = this.f3785a.get(str);
        }
        return obj;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void d() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Object e(String str, Object obj) {
        Object obj2;
        synchronized (this.f3785a) {
            obj2 = this.f3785a.get(str);
            if (obj2 == null) {
                this.f3785a.put(str, obj);
            }
        }
        if (obj2 != null) {
            obj = obj2;
        }
        if (this.f3787c) {
            b(obj);
        }
        return obj;
    }
}
