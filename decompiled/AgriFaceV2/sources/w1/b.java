package w1;

import android.os.Bundle;
import androidx.lifecycle.i;
import androidx.lifecycle.k;
import androidx.lifecycle.m;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Set;
import w1.d;
/* loaded from: classes.dex */
public final class b implements k {

    /* renamed from: f  reason: collision with root package name */
    public static final a f12449f = new a(null);

    /* renamed from: e  reason: collision with root package name */
    private final f f12450e;

    /* loaded from: classes.dex */
    public static final class a {
        private a() {
        }

        public /* synthetic */ a(d7.g gVar) {
            this();
        }
    }

    /* renamed from: w1.b$b  reason: collision with other inner class name */
    /* loaded from: classes.dex */
    public static final class C0161b implements d.c {

        /* renamed from: a  reason: collision with root package name */
        private final Set f12451a;

        public C0161b(d dVar) {
            d7.k.e(dVar, "registry");
            this.f12451a = new LinkedHashSet();
            dVar.h("androidx.savedstate.Restarter", this);
        }

        @Override // w1.d.c
        public Bundle a() {
            Bundle bundle = new Bundle();
            bundle.putStringArrayList("classes_to_restore", new ArrayList<>(this.f12451a));
            return bundle;
        }

        public final void b(String str) {
            d7.k.e(str, "className");
            this.f12451a.add(str);
        }
    }

    public b(f fVar) {
        d7.k.e(fVar, "owner");
        this.f12450e = fVar;
    }

    private final void a(String str) {
        try {
            Class<? extends U> asSubclass = Class.forName(str, false, b.class.getClassLoader()).asSubclass(d.a.class);
            d7.k.d(asSubclass, "{\n                Class.…class.java)\n            }");
            try {
                Constructor declaredConstructor = asSubclass.getDeclaredConstructor(new Class[0]);
                declaredConstructor.setAccessible(true);
                try {
                    Object newInstance = declaredConstructor.newInstance(new Object[0]);
                    d7.k.d(newInstance, "{\n                constr…wInstance()\n            }");
                    ((d.a) newInstance).a(this.f12450e);
                } catch (Exception e8) {
                    throw new RuntimeException("Failed to instantiate " + str, e8);
                }
            } catch (NoSuchMethodException e9) {
                throw new IllegalStateException("Class " + asSubclass.getSimpleName() + " must have default constructor in order to be automatically recreated", e9);
            }
        } catch (ClassNotFoundException e10) {
            throw new RuntimeException("Class " + str + " wasn't found", e10);
        }
    }

    @Override // androidx.lifecycle.k
    public void f(m mVar, i.a aVar) {
        d7.k.e(mVar, "source");
        d7.k.e(aVar, "event");
        if (aVar != i.a.ON_CREATE) {
            throw new AssertionError("Next event must be ON_CREATE");
        }
        mVar.getLifecycle().c(this);
        Bundle b8 = this.f12450e.getSavedStateRegistry().b("androidx.savedstate.Restarter");
        if (b8 == null) {
            return;
        }
        ArrayList<String> stringArrayList = b8.getStringArrayList("classes_to_restore");
        if (stringArrayList == null) {
            throw new IllegalStateException("Bundle with restored state for the component \"androidx.savedstate.Restarter\" must contain list of strings by the key \"classes_to_restore\"");
        }
        for (String str : stringArrayList) {
            a(str);
        }
    }
}
