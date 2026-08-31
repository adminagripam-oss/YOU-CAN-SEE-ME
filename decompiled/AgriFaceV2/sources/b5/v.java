package b5;

import java.util.Collection;
import java.util.Collections;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class v implements h5.b {

    /* renamed from: b  reason: collision with root package name */
    private volatile Set f4683b = null;

    /* renamed from: a  reason: collision with root package name */
    private volatile Set f4682a = Collections.newSetFromMap(new ConcurrentHashMap());

    v(Collection collection) {
        this.f4682a.addAll(collection);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static v b(Collection collection) {
        return new v((Set) collection);
    }

    private synchronized void d() {
        for (h5.b bVar : this.f4682a) {
            this.f4683b.add(bVar.get());
        }
        this.f4682a = null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public synchronized void a(h5.b bVar) {
        Set set;
        Object obj;
        if (this.f4683b == null) {
            set = this.f4682a;
            obj = bVar;
        } else {
            set = this.f4683b;
            obj = bVar.get();
        }
        set.add(obj);
    }

    @Override // h5.b
    /* renamed from: c */
    public Set get() {
        if (this.f4683b == null) {
            synchronized (this) {
                if (this.f4683b == null) {
                    this.f4683b = Collections.newSetFromMap(new ConcurrentHashMap());
                    d();
                }
            }
        }
        return Collections.unmodifiableSet(this.f4683b);
    }
}
