package b0;

import b0.f3;
import b0.q2;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
/* loaded from: classes.dex */
public final class f3 {

    /* renamed from: a  reason: collision with root package name */
    private final String f4180a;

    /* renamed from: b  reason: collision with root package name */
    private final Map f4181b = new LinkedHashMap();

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public interface a {
        boolean a(b bVar);
    }

    /* loaded from: classes.dex */
    public static final class b {

        /* renamed from: a  reason: collision with root package name */
        private final q2 f4182a;

        /* renamed from: b  reason: collision with root package name */
        private final g3 f4183b;

        /* renamed from: c  reason: collision with root package name */
        private final u2 f4184c;

        /* renamed from: d  reason: collision with root package name */
        private final List f4185d;

        /* renamed from: e  reason: collision with root package name */
        private boolean f4186e = false;

        /* renamed from: f  reason: collision with root package name */
        private boolean f4187f = false;

        b(q2 q2Var, g3 g3Var, u2 u2Var, List list) {
            this.f4182a = q2Var;
            this.f4183b = g3Var;
            this.f4184c = u2Var;
            this.f4185d = list;
        }

        boolean a() {
            return this.f4187f;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public boolean b() {
            return this.f4186e;
        }

        public List c() {
            return this.f4185d;
        }

        public q2 d() {
            return this.f4182a;
        }

        public u2 e() {
            return this.f4184c;
        }

        public g3 f() {
            return this.f4183b;
        }

        void g(boolean z7) {
            this.f4187f = z7;
        }

        void h(boolean z7) {
            this.f4186e = z7;
        }

        public String toString() {
            return "UseCaseAttachInfo{mSessionConfig=" + this.f4182a + ", mUseCaseConfig=" + this.f4183b + ", mStreamSpec=" + this.f4184c + ", mCaptureTypes=" + this.f4185d + ", mAttached=" + this.f4186e + ", mActive=" + this.f4187f + '}';
        }
    }

    public f3(String str) {
        this.f4180a = str;
    }

    private b k(String str, q2 q2Var, g3 g3Var, u2 u2Var, List list) {
        b bVar = (b) this.f4181b.get(str);
        if (bVar == null) {
            b bVar2 = new b(q2Var, g3Var, u2Var, list);
            this.f4181b.put(str, bVar2);
            return bVar2;
        }
        return bVar;
    }

    private Collection l(a aVar) {
        ArrayList arrayList = new ArrayList();
        for (Map.Entry entry : this.f4181b.entrySet()) {
            if (aVar == null || aVar.a((b) entry.getValue())) {
                arrayList.add(((b) entry.getValue()).d());
            }
        }
        return arrayList;
    }

    private Collection m(a aVar) {
        ArrayList arrayList = new ArrayList();
        for (Map.Entry entry : this.f4181b.entrySet()) {
            if (aVar == null || aVar.a((b) entry.getValue())) {
                arrayList.add(((b) entry.getValue()).f());
            }
        }
        return arrayList;
    }

    private Collection n(a aVar) {
        ArrayList arrayList = new ArrayList();
        for (Map.Entry entry : this.f4181b.entrySet()) {
            if (aVar == null || aVar.a((b) entry.getValue())) {
                arrayList.add((b) entry.getValue());
            }
        }
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ boolean p(b bVar) {
        return bVar.a() && bVar.b();
    }

    public q2.h e() {
        q2.h hVar = new q2.h();
        ArrayList arrayList = new ArrayList();
        for (Map.Entry entry : this.f4181b.entrySet()) {
            b bVar = (b) entry.getValue();
            if (bVar.a() && bVar.b()) {
                hVar.b(bVar.d());
                arrayList.add((String) entry.getKey());
            }
        }
        y.f1.a("UseCaseAttachState", "Active and attached use case: " + arrayList + " for camera: " + this.f4180a);
        return hVar;
    }

    public Collection f() {
        return Collections.unmodifiableCollection(l(new a() { // from class: b0.d3
            @Override // b0.f3.a
            public final boolean a(f3.b bVar) {
                boolean p8;
                p8 = f3.p(bVar);
                return p8;
            }
        }));
    }

    public q2.h g() {
        q2.h hVar = new q2.h();
        ArrayList arrayList = new ArrayList();
        for (Map.Entry entry : this.f4181b.entrySet()) {
            b bVar = (b) entry.getValue();
            if (bVar.b()) {
                hVar.b(bVar.d());
                arrayList.add((String) entry.getKey());
            }
        }
        y.f1.a("UseCaseAttachState", "All use case: " + arrayList + " for camera: " + this.f4180a);
        return hVar;
    }

    public Collection h() {
        return Collections.unmodifiableCollection(l(new a() { // from class: b0.b3
            @Override // b0.f3.a
            public final boolean a(f3.b bVar) {
                boolean b8;
                b8 = bVar.b();
                return b8;
            }
        }));
    }

    public Collection i() {
        return Collections.unmodifiableCollection(m(new a() { // from class: b0.c3
            @Override // b0.f3.a
            public final boolean a(f3.b bVar) {
                boolean b8;
                b8 = bVar.b();
                return b8;
            }
        }));
    }

    public Collection j() {
        return Collections.unmodifiableCollection(n(new a() { // from class: b0.e3
            @Override // b0.f3.a
            public final boolean a(f3.b bVar) {
                boolean b8;
                b8 = bVar.b();
                return b8;
            }
        }));
    }

    public boolean o(String str) {
        if (this.f4181b.containsKey(str)) {
            return ((b) this.f4181b.get(str)).b();
        }
        return false;
    }

    public void t(String str) {
        this.f4181b.remove(str);
    }

    public void u(String str, q2 q2Var, g3 g3Var, u2 u2Var, List list) {
        k(str, q2Var, g3Var, u2Var, list).g(true);
    }

    public void v(String str, q2 q2Var, g3 g3Var, u2 u2Var, List list) {
        k(str, q2Var, g3Var, u2Var, list).h(true);
        y(str, q2Var, g3Var, u2Var, list);
    }

    public void w(String str) {
        if (this.f4181b.containsKey(str)) {
            b bVar = (b) this.f4181b.get(str);
            bVar.h(false);
            if (bVar.a()) {
                return;
            }
            this.f4181b.remove(str);
        }
    }

    public void x(String str) {
        if (this.f4181b.containsKey(str)) {
            b bVar = (b) this.f4181b.get(str);
            bVar.g(false);
            if (bVar.b()) {
                return;
            }
            this.f4181b.remove(str);
        }
    }

    public void y(String str, q2 q2Var, g3 g3Var, u2 u2Var, List list) {
        if (this.f4181b.containsKey(str)) {
            b bVar = new b(q2Var, g3Var, u2Var, list);
            b bVar2 = (b) this.f4181b.get(str);
            bVar.h(bVar2.b());
            bVar.g(bVar2.a());
            this.f4181b.put(str, bVar);
        }
    }
}
