package w1;

import android.os.Bundle;
import androidx.lifecycle.i;
import androidx.lifecycle.m;
import d7.k;
import java.util.Iterator;
import java.util.Map;
import l.b;
import w1.b;
/* loaded from: classes.dex */
public final class d {

    /* renamed from: g  reason: collision with root package name */
    private static final b f12453g = new b(null);

    /* renamed from: b  reason: collision with root package name */
    private boolean f12455b;

    /* renamed from: c  reason: collision with root package name */
    private Bundle f12456c;

    /* renamed from: d  reason: collision with root package name */
    private boolean f12457d;

    /* renamed from: e  reason: collision with root package name */
    private b.C0161b f12458e;

    /* renamed from: a  reason: collision with root package name */
    private final l.b f12454a = new l.b();

    /* renamed from: f  reason: collision with root package name */
    private boolean f12459f = true;

    /* loaded from: classes.dex */
    public interface a {
        void a(f fVar);
    }

    /* loaded from: classes.dex */
    private static final class b {
        private b() {
        }

        public /* synthetic */ b(d7.g gVar) {
            this();
        }
    }

    /* loaded from: classes.dex */
    public interface c {
        Bundle a();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void d(d dVar, m mVar, i.a aVar) {
        boolean z7;
        k.e(dVar, "this$0");
        k.e(mVar, "<anonymous parameter 0>");
        k.e(aVar, "event");
        if (aVar == i.a.ON_START) {
            z7 = true;
        } else if (aVar != i.a.ON_STOP) {
            return;
        } else {
            z7 = false;
        }
        dVar.f12459f = z7;
    }

    public final Bundle b(String str) {
        k.e(str, "key");
        if (this.f12457d) {
            Bundle bundle = this.f12456c;
            if (bundle != null) {
                Bundle bundle2 = bundle != null ? bundle.getBundle(str) : null;
                Bundle bundle3 = this.f12456c;
                if (bundle3 != null) {
                    bundle3.remove(str);
                }
                Bundle bundle4 = this.f12456c;
                boolean z7 = false;
                if (bundle4 != null && !bundle4.isEmpty()) {
                    z7 = true;
                }
                if (!z7) {
                    this.f12456c = null;
                }
                return bundle2;
            }
            return null;
        }
        throw new IllegalStateException("You can consumeRestoredStateForKey only after super.onCreate of corresponding component".toString());
    }

    public final c c(String str) {
        k.e(str, "key");
        Iterator it = this.f12454a.iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            k.d(entry, "components");
            c cVar = (c) entry.getValue();
            if (k.a((String) entry.getKey(), str)) {
                return cVar;
            }
        }
        return null;
    }

    public final void e(i iVar) {
        k.e(iVar, "lifecycle");
        if (!(!this.f12455b)) {
            throw new IllegalStateException("SavedStateRegistry was already attached.".toString());
        }
        iVar.a(new androidx.lifecycle.k() { // from class: w1.c
            @Override // androidx.lifecycle.k
            public final void f(m mVar, i.a aVar) {
                d.d(d.this, mVar, aVar);
            }
        });
        this.f12455b = true;
    }

    public final void f(Bundle bundle) {
        if (!this.f12455b) {
            throw new IllegalStateException("You must call performAttach() before calling performRestore(Bundle).".toString());
        }
        if (!(!this.f12457d)) {
            throw new IllegalStateException("SavedStateRegistry was already restored.".toString());
        }
        this.f12456c = bundle != null ? bundle.getBundle("androidx.lifecycle.BundlableSavedStateRegistry.key") : null;
        this.f12457d = true;
    }

    public final void g(Bundle bundle) {
        k.e(bundle, "outBundle");
        Bundle bundle2 = new Bundle();
        Bundle bundle3 = this.f12456c;
        if (bundle3 != null) {
            bundle2.putAll(bundle3);
        }
        b.d k8 = this.f12454a.k();
        k.d(k8, "this.components.iteratorWithAdditions()");
        while (k8.hasNext()) {
            Map.Entry entry = (Map.Entry) k8.next();
            bundle2.putBundle((String) entry.getKey(), ((c) entry.getValue()).a());
        }
        if (bundle2.isEmpty()) {
            return;
        }
        bundle.putBundle("androidx.lifecycle.BundlableSavedStateRegistry.key", bundle2);
    }

    public final void h(String str, c cVar) {
        k.e(str, "key");
        k.e(cVar, "provider");
        if (!(((c) this.f12454a.n(str, cVar)) == null)) {
            throw new IllegalArgumentException("SavedStateProvider with the given key is already registered".toString());
        }
    }

    public final void i(Class cls) {
        k.e(cls, "clazz");
        if (!this.f12459f) {
            throw new IllegalStateException("Can not perform this action after onSaveInstanceState".toString());
        }
        b.C0161b c0161b = this.f12458e;
        if (c0161b == null) {
            c0161b = new b.C0161b(this);
        }
        this.f12458e = c0161b;
        try {
            cls.getDeclaredConstructor(new Class[0]);
            b.C0161b c0161b2 = this.f12458e;
            if (c0161b2 != null) {
                String name = cls.getName();
                k.d(name, "clazz.name");
                c0161b2.b(name);
            }
        } catch (NoSuchMethodException e8) {
            throw new IllegalArgumentException("Class " + cls.getSimpleName() + " must have default constructor in order to be automatically recreated", e8);
        }
    }
}
