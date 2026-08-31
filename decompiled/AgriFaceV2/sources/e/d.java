package e;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import androidx.lifecycle.i;
import androidx.lifecycle.k;
import androidx.lifecycle.m;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
/* loaded from: classes.dex */
public abstract class d {

    /* renamed from: a  reason: collision with root package name */
    private final Map f6490a = new HashMap();

    /* renamed from: b  reason: collision with root package name */
    final Map f6491b = new HashMap();

    /* renamed from: c  reason: collision with root package name */
    private final Map f6492c = new HashMap();

    /* renamed from: d  reason: collision with root package name */
    ArrayList f6493d = new ArrayList();

    /* renamed from: e  reason: collision with root package name */
    final transient Map f6494e = new HashMap();

    /* renamed from: f  reason: collision with root package name */
    final Map f6495f = new HashMap();

    /* renamed from: g  reason: collision with root package name */
    final Bundle f6496g = new Bundle();

    /* loaded from: classes.dex */
    class a implements k {

        /* renamed from: e  reason: collision with root package name */
        final /* synthetic */ String f6497e;

        /* renamed from: f  reason: collision with root package name */
        final /* synthetic */ e.b f6498f;

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ f.a f6499g;

        a(String str, e.b bVar, f.a aVar) {
            this.f6497e = str;
            this.f6498f = bVar;
            this.f6499g = aVar;
        }

        @Override // androidx.lifecycle.k
        public void f(m mVar, i.a aVar) {
            if (!i.a.ON_START.equals(aVar)) {
                if (i.a.ON_STOP.equals(aVar)) {
                    d.this.f6494e.remove(this.f6497e);
                    return;
                } else if (i.a.ON_DESTROY.equals(aVar)) {
                    d.this.l(this.f6497e);
                    return;
                } else {
                    return;
                }
            }
            d.this.f6494e.put(this.f6497e, new C0081d(this.f6498f, this.f6499g));
            if (d.this.f6495f.containsKey(this.f6497e)) {
                Object obj = d.this.f6495f.get(this.f6497e);
                d.this.f6495f.remove(this.f6497e);
                this.f6498f.a(obj);
            }
            e.a aVar2 = (e.a) d.this.f6496g.getParcelable(this.f6497e);
            if (aVar2 != null) {
                d.this.f6496g.remove(this.f6497e);
                this.f6498f.a(this.f6499g.c(aVar2.b(), aVar2.a()));
            }
        }
    }

    /* loaded from: classes.dex */
    class b extends e.c {

        /* renamed from: a  reason: collision with root package name */
        final /* synthetic */ String f6501a;

        /* renamed from: b  reason: collision with root package name */
        final /* synthetic */ f.a f6502b;

        b(String str, f.a aVar) {
            this.f6501a = str;
            this.f6502b = aVar;
        }

        @Override // e.c
        public void b(Object obj, androidx.core.app.c cVar) {
            Integer num = (Integer) d.this.f6491b.get(this.f6501a);
            if (num != null) {
                d.this.f6493d.add(this.f6501a);
                try {
                    d.this.f(num.intValue(), this.f6502b, obj, cVar);
                    return;
                } catch (Exception e8) {
                    d.this.f6493d.remove(this.f6501a);
                    throw e8;
                }
            }
            throw new IllegalStateException("Attempting to launch an unregistered ActivityResultLauncher with contract " + this.f6502b + " and input " + obj + ". You must ensure the ActivityResultLauncher is registered before calling launch().");
        }

        @Override // e.c
        public void c() {
            d.this.l(this.f6501a);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class c extends e.c {

        /* renamed from: a  reason: collision with root package name */
        final /* synthetic */ String f6504a;

        /* renamed from: b  reason: collision with root package name */
        final /* synthetic */ f.a f6505b;

        c(String str, f.a aVar) {
            this.f6504a = str;
            this.f6505b = aVar;
        }

        @Override // e.c
        public void b(Object obj, androidx.core.app.c cVar) {
            Integer num = (Integer) d.this.f6491b.get(this.f6504a);
            if (num != null) {
                d.this.f6493d.add(this.f6504a);
                try {
                    d.this.f(num.intValue(), this.f6505b, obj, cVar);
                    return;
                } catch (Exception e8) {
                    d.this.f6493d.remove(this.f6504a);
                    throw e8;
                }
            }
            throw new IllegalStateException("Attempting to launch an unregistered ActivityResultLauncher with contract " + this.f6505b + " and input " + obj + ". You must ensure the ActivityResultLauncher is registered before calling launch().");
        }

        @Override // e.c
        public void c() {
            d.this.l(this.f6504a);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: e.d$d  reason: collision with other inner class name */
    /* loaded from: classes.dex */
    public static class C0081d {

        /* renamed from: a  reason: collision with root package name */
        final e.b f6507a;

        /* renamed from: b  reason: collision with root package name */
        final f.a f6508b;

        C0081d(e.b bVar, f.a aVar) {
            this.f6507a = bVar;
            this.f6508b = aVar;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class e {

        /* renamed from: a  reason: collision with root package name */
        final i f6509a;

        /* renamed from: b  reason: collision with root package name */
        private final ArrayList f6510b = new ArrayList();

        e(i iVar) {
            this.f6509a = iVar;
        }

        void a(k kVar) {
            this.f6509a.a(kVar);
            this.f6510b.add(kVar);
        }

        void b() {
            Iterator it = this.f6510b.iterator();
            while (it.hasNext()) {
                this.f6509a.c((k) it.next());
            }
            this.f6510b.clear();
        }
    }

    private void a(int i8, String str) {
        this.f6490a.put(Integer.valueOf(i8), str);
        this.f6491b.put(str, Integer.valueOf(i8));
    }

    private void d(String str, int i8, Intent intent, C0081d c0081d) {
        if (c0081d == null || c0081d.f6507a == null || !this.f6493d.contains(str)) {
            this.f6495f.remove(str);
            this.f6496g.putParcelable(str, new e.a(i8, intent));
            return;
        }
        c0081d.f6507a.a(c0081d.f6508b.c(i8, intent));
        this.f6493d.remove(str);
    }

    private int e() {
        int c8 = e7.c.f6621e.c(2147418112);
        while (true) {
            int i8 = c8 + 65536;
            if (!this.f6490a.containsKey(Integer.valueOf(i8))) {
                return i8;
            }
            c8 = e7.c.f6621e.c(2147418112);
        }
    }

    private void k(String str) {
        if (((Integer) this.f6491b.get(str)) != null) {
            return;
        }
        a(e(), str);
    }

    public final boolean b(int i8, int i9, Intent intent) {
        String str = (String) this.f6490a.get(Integer.valueOf(i8));
        if (str == null) {
            return false;
        }
        d(str, i9, intent, (C0081d) this.f6494e.get(str));
        return true;
    }

    public final boolean c(int i8, Object obj) {
        e.b bVar;
        String str = (String) this.f6490a.get(Integer.valueOf(i8));
        if (str == null) {
            return false;
        }
        C0081d c0081d = (C0081d) this.f6494e.get(str);
        if (c0081d == null || (bVar = c0081d.f6507a) == null) {
            this.f6496g.remove(str);
            this.f6495f.put(str, obj);
            return true;
        } else if (this.f6493d.remove(str)) {
            bVar.a(obj);
            return true;
        } else {
            return true;
        }
    }

    public abstract void f(int i8, f.a aVar, Object obj, androidx.core.app.c cVar);

    public final void g(Bundle bundle) {
        if (bundle == null) {
            return;
        }
        ArrayList<Integer> integerArrayList = bundle.getIntegerArrayList("KEY_COMPONENT_ACTIVITY_REGISTERED_RCS");
        ArrayList<String> stringArrayList = bundle.getStringArrayList("KEY_COMPONENT_ACTIVITY_REGISTERED_KEYS");
        if (stringArrayList == null || integerArrayList == null) {
            return;
        }
        this.f6493d = bundle.getStringArrayList("KEY_COMPONENT_ACTIVITY_LAUNCHED_KEYS");
        this.f6496g.putAll(bundle.getBundle("KEY_COMPONENT_ACTIVITY_PENDING_RESULT"));
        for (int i8 = 0; i8 < stringArrayList.size(); i8++) {
            String str = stringArrayList.get(i8);
            if (this.f6491b.containsKey(str)) {
                Integer num = (Integer) this.f6491b.remove(str);
                if (!this.f6496g.containsKey(str)) {
                    this.f6490a.remove(num);
                }
            }
            a(integerArrayList.get(i8).intValue(), stringArrayList.get(i8));
        }
    }

    public final void h(Bundle bundle) {
        bundle.putIntegerArrayList("KEY_COMPONENT_ACTIVITY_REGISTERED_RCS", new ArrayList<>(this.f6491b.values()));
        bundle.putStringArrayList("KEY_COMPONENT_ACTIVITY_REGISTERED_KEYS", new ArrayList<>(this.f6491b.keySet()));
        bundle.putStringArrayList("KEY_COMPONENT_ACTIVITY_LAUNCHED_KEYS", new ArrayList<>(this.f6493d));
        bundle.putBundle("KEY_COMPONENT_ACTIVITY_PENDING_RESULT", (Bundle) this.f6496g.clone());
    }

    public final e.c i(String str, m mVar, f.a aVar, e.b bVar) {
        i lifecycle = mVar.getLifecycle();
        if (lifecycle.b().e(i.b.STARTED)) {
            throw new IllegalStateException("LifecycleOwner " + mVar + " is attempting to register while current state is " + lifecycle.b() + ". LifecycleOwners must call register before they are STARTED.");
        }
        k(str);
        e eVar = (e) this.f6492c.get(str);
        if (eVar == null) {
            eVar = new e(lifecycle);
        }
        eVar.a(new a(str, bVar, aVar));
        this.f6492c.put(str, eVar);
        return new b(str, aVar);
    }

    public final e.c j(String str, f.a aVar, e.b bVar) {
        k(str);
        this.f6494e.put(str, new C0081d(bVar, aVar));
        if (this.f6495f.containsKey(str)) {
            Object obj = this.f6495f.get(str);
            this.f6495f.remove(str);
            bVar.a(obj);
        }
        e.a aVar2 = (e.a) this.f6496g.getParcelable(str);
        if (aVar2 != null) {
            this.f6496g.remove(str);
            bVar.a(aVar.c(aVar2.b(), aVar2.a()));
        }
        return new c(str, aVar);
    }

    final void l(String str) {
        Integer num;
        if (!this.f6493d.contains(str) && (num = (Integer) this.f6491b.remove(str)) != null) {
            this.f6490a.remove(num);
        }
        this.f6494e.remove(str);
        if (this.f6495f.containsKey(str)) {
            Log.w("ActivityResultRegistry", "Dropping pending result for request " + str + ": " + this.f6495f.get(str));
            this.f6495f.remove(str);
        }
        if (this.f6496g.containsKey(str)) {
            Log.w("ActivityResultRegistry", "Dropping pending result for request " + str + ": " + this.f6496g.getParcelable(str));
            this.f6496g.remove(str);
        }
        e eVar = (e) this.f6492c.get(str);
        if (eVar != null) {
            eVar.b();
            this.f6492c.remove(str);
        }
    }
}
