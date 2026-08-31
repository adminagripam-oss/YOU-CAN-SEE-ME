package j4;

import j4.m;
import j4.o;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import t4.d0;
import t4.j0;
import t4.k0;
import y4.c0;
import y4.i0;
/* loaded from: classes.dex */
public final class m implements o {

    /* renamed from: a  reason: collision with root package name */
    private final List f8253a;

    /* renamed from: b  reason: collision with root package name */
    private final Map f8254b;

    /* renamed from: c  reason: collision with root package name */
    private final m f8255c;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static /* synthetic */ class a {

        /* renamed from: a  reason: collision with root package name */
        static final /* synthetic */ int[] f8256a;

        static {
            int[] iArr = new int[y4.z.values().length];
            f8256a = iArr;
            try {
                iArr[y4.z.ENABLED.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f8256a[y4.z.DESTROYED.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f8256a[y4.z.DISABLED.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    /* loaded from: classes.dex */
    public static final class b {

        /* renamed from: a  reason: collision with root package name */
        private final List f8257a = new ArrayList();

        /* renamed from: b  reason: collision with root package name */
        private GeneralSecurityException f8258b = null;

        /* renamed from: c  reason: collision with root package name */
        private final Map f8259c = new HashMap();

        /* renamed from: d  reason: collision with root package name */
        private boolean f8260d = false;

        /* loaded from: classes.dex */
        public static final class a {

            /* renamed from: a  reason: collision with root package name */
            private boolean f8261a;

            /* renamed from: b  reason: collision with root package name */
            private j f8262b;

            /* renamed from: c  reason: collision with root package name */
            private final h f8263c;

            /* renamed from: d  reason: collision with root package name */
            private final w f8264d;

            /* renamed from: e  reason: collision with root package name */
            private C0110b f8265e;

            /* renamed from: f  reason: collision with root package name */
            private b f8266f;

            private a(w wVar) {
                this.f8262b = j.f8247b;
                this.f8265e = null;
                this.f8266f = null;
                this.f8263c = null;
                this.f8264d = wVar;
            }

            public a i() {
                b bVar = this.f8266f;
                if (bVar != null) {
                    bVar.e();
                }
                this.f8261a = true;
                return this;
            }

            public a j() {
                this.f8265e = C0110b.a();
                return this;
            }

            /* synthetic */ a(w wVar, a aVar) {
                this(wVar);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: j4.m$b$b  reason: collision with other inner class name */
        /* loaded from: classes.dex */
        public static class C0110b {

            /* renamed from: b  reason: collision with root package name */
            private static final C0110b f8267b = new C0110b();

            /* renamed from: a  reason: collision with root package name */
            private final int f8268a = 0;

            private C0110b() {
            }

            static /* synthetic */ C0110b a() {
                return e();
            }

            /* JADX INFO: Access modifiers changed from: private */
            public int d() {
                return this.f8268a;
            }

            private static C0110b e() {
                return f8267b;
            }
        }

        private static void d(List list) {
            for (int i8 = 0; i8 < list.size() - 1; i8++) {
                if (((a) list.get(i8)).f8265e == C0110b.f8267b && ((a) list.get(i8 + 1)).f8265e != C0110b.f8267b) {
                    throw new GeneralSecurityException("Entries with 'withRandomId()' may only be followed by other entries with 'withRandomId()'.");
                }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void e() {
            for (a aVar : this.f8257a) {
                aVar.f8261a = false;
            }
        }

        private static int f(a aVar, Set set) {
            if (aVar.f8265e != null) {
                return aVar.f8265e == C0110b.f8267b ? g(set) : aVar.f8265e.d();
            }
            throw new GeneralSecurityException("No ID was set (with withFixedId or withRandomId)");
        }

        private static int g(Set set) {
            int i8 = 0;
            while (true) {
                if (i8 != 0 && !set.contains(Integer.valueOf(i8))) {
                    return i8;
                }
                i8 = k0.f();
            }
        }

        public b b(a aVar) {
            if (aVar.f8266f == null) {
                if (aVar.f8261a) {
                    e();
                }
                aVar.f8266f = this;
                this.f8257a.add(aVar);
                return this;
            }
            throw new IllegalStateException("Entry has already been added to a KeysetHandle.Builder");
        }

        public m c() {
            c cVar;
            if (this.f8258b == null) {
                if (this.f8260d) {
                    throw new GeneralSecurityException("KeysetHandle.Builder#build must only be called once");
                }
                this.f8260d = true;
                ArrayList arrayList = new ArrayList(this.f8257a.size());
                d(this.f8257a);
                HashSet hashSet = new HashSet();
                Integer num = null;
                for (a aVar : this.f8257a) {
                    if (aVar.f8262b == null) {
                        throw new GeneralSecurityException("Key Status not set.");
                    }
                    int f8 = f(aVar, hashSet);
                    if (hashSet.contains(Integer.valueOf(f8))) {
                        throw new GeneralSecurityException("Id " + f8 + " is used twice in the keyset");
                    }
                    hashSet.add(Integer.valueOf(f8));
                    if (aVar.f8263c != null) {
                        m.H(aVar.f8263c, f8);
                        cVar = new c(aVar.f8263c, m.D(aVar.f8262b), f8, aVar.f8261a, false, c.f8269h, null);
                    } else {
                        cVar = new c(t4.o.f().c(aVar.f8264d, aVar.f8264d.a() ? Integer.valueOf(f8) : null), m.D(aVar.f8262b), f8, aVar.f8261a, false, c.f8269h, null);
                    }
                    if (aVar.f8261a) {
                        if (num != null) {
                            throw new GeneralSecurityException("Two primaries were set");
                        }
                        num = Integer.valueOf(f8);
                        if (aVar.f8262b != j.f8247b) {
                            throw new GeneralSecurityException("Primary key is not enabled");
                        }
                    }
                    arrayList.add(cVar);
                }
                if (num != null) {
                    return m.h(new m(arrayList, this.f8259c, null));
                }
                throw new GeneralSecurityException("No primary was set");
            }
            throw new GeneralSecurityException("Cannot build keyset due to error in original", this.f8258b);
        }
    }

    /* loaded from: classes.dex */
    public static final class c implements o.a {

        /* renamed from: h  reason: collision with root package name */
        private static final a f8269h = new a() { // from class: j4.n
            @Override // j4.m.c.a
            public final void a(m.c cVar) {
                m.c.h(cVar);
            }
        };

        /* renamed from: a  reason: collision with root package name */
        private final h f8270a;

        /* renamed from: b  reason: collision with root package name */
        private final y4.z f8271b;

        /* renamed from: c  reason: collision with root package name */
        private final j f8272c;

        /* renamed from: d  reason: collision with root package name */
        private final int f8273d;

        /* renamed from: e  reason: collision with root package name */
        private final boolean f8274e;

        /* renamed from: f  reason: collision with root package name */
        private final boolean f8275f;

        /* renamed from: g  reason: collision with root package name */
        private final a f8276g;

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes.dex */
        public interface a {
            void a(c cVar);
        }

        private c(h hVar, y4.z zVar, int i8, boolean z7, boolean z8, a aVar) {
            this.f8270a = hVar;
            this.f8271b = zVar;
            this.f8272c = m.B(zVar);
            this.f8273d = i8;
            this.f8274e = z7;
            this.f8275f = z8;
            this.f8276g = aVar;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static /* synthetic */ void h(c cVar) {
        }

        @Override // j4.o.a
        public int a() {
            return this.f8273d;
        }

        @Override // j4.o.a
        public j b() {
            return this.f8272c;
        }

        public boolean g() {
            return this.f8274e;
        }

        @Override // j4.o.a
        public h getKey() {
            this.f8276g.a(this);
            return this.f8270a;
        }

        /* synthetic */ c(h hVar, y4.z zVar, int i8, boolean z7, boolean z8, a aVar, a aVar2) {
            this(hVar, zVar, i8, z7, z8, aVar);
        }
    }

    private m(List list, Map map) {
        this.f8253a = list;
        this.f8254b = map;
        if (n4.a.f9762a.getValue()) {
            I(list);
        }
        this.f8255c = null;
    }

    public static b A() {
        return new b();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static j B(y4.z zVar) {
        int i8 = a.f8256a[zVar.ordinal()];
        return i8 != 1 ? i8 != 2 ? j.f8248c : j.f8249d : j.f8247b;
    }

    public static final m C(q qVar, j4.a aVar, byte[] bArr) {
        y4.t b8 = qVar.b();
        i(b8);
        return o(l(b8, aVar, bArr));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static y4.z D(j jVar) {
        if (j.f8247b.equals(jVar)) {
            return y4.z.ENABLED;
        }
        if (j.f8248c.equals(jVar)) {
            return y4.z.DISABLED;
        }
        if (j.f8249d.equals(jVar)) {
            return y4.z.DESTROYED;
        }
        throw new IllegalStateException("Unknown key status");
    }

    private static h E(c0.c cVar) {
        return t4.u.c().g(G(cVar), g.a());
    }

    private static c0.c F(int i8, y4.z zVar, d0 d0Var) {
        return (c0.c) c0.c.e0().q(y4.y.c0().r(d0Var.f()).s(d0Var.g()).q(d0Var.d())).t(zVar).r(i8).s(d0Var.e()).h();
    }

    private static d0 G(c0.c cVar) {
        return d0.b(cVar.Z().a0(), cVar.Z().b0(), cVar.Z().Z(), cVar.b0(), cVar.b0() == i0.RAW ? null : Integer.valueOf(cVar.a0()));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void H(h hVar, int i8) {
        Integer a8 = hVar.a();
        if (a8 != null && a8.intValue() != i8) {
            throw new GeneralSecurityException("Wrong ID set for key with ID requirement");
        }
    }

    private static void I(List list) {
        HashSet hashSet = new HashSet();
        Iterator it = list.iterator();
        boolean z7 = false;
        while (it.hasNext()) {
            c cVar = (c) it.next();
            if (hashSet.contains(Integer.valueOf(cVar.a()))) {
                throw new GeneralSecurityException("KeyID " + cVar.a() + " is duplicated in the keyset, and Tink is configured to reject such keysets with the flag validateKeysetsOnParsing.");
            }
            hashSet.add(Integer.valueOf(cVar.a()));
            if (cVar.g()) {
                z7 = true;
            }
        }
        if (!z7) {
            throw new GeneralSecurityException("Primary key id not found in keyset, and Tink is configured to reject such keysets with the flag validateKeysetsOnParsing.");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static m h(m mVar) {
        mVar.a(t4.k.class);
        return mVar;
    }

    private static void i(y4.t tVar) {
        if (tVar == null || tVar.X().size() == 0) {
            throw new GeneralSecurityException("empty keyset");
        }
    }

    private static void j(c0 c0Var) {
        if (c0Var == null || c0Var.a0() <= 0) {
            throw new GeneralSecurityException("empty keyset");
        }
    }

    private static c0.c k(h hVar, y4.z zVar, int i8) {
        H(hVar, i8);
        return F(i8, zVar, (d0) t4.u.c().n(hVar, d0.class, g.a()));
    }

    private static c0 l(y4.t tVar, j4.a aVar, byte[] bArr) {
        try {
            c0 f02 = c0.f0(aVar.b(tVar.X().w(), bArr), com.google.crypto.tink.shaded.protobuf.p.b());
            j(f02);
            return f02;
        } catch (com.google.crypto.tink.shaded.protobuf.a0 unused) {
            throw new GeneralSecurityException("invalid keyset, corrupted key material");
        }
    }

    private static y4.t m(c0 c0Var, j4.a aVar, byte[] bArr) {
        return (y4.t) y4.t.Y().q(com.google.crypto.tink.shaded.protobuf.h.l(aVar.a(c0Var.i(), bArr))).r(a0.b(c0Var)).h();
    }

    private c n(int i8) {
        c cVar = (c) this.f8253a.get(i8);
        if (!z(cVar.f8271b)) {
            throw new IllegalStateException("Keyset-Entry at position " + i8 + " has wrong status");
        } else if (cVar.f8275f) {
            throw new IllegalStateException("Keyset-Entry at position " + i8 + " didn't parse correctly");
        } else {
            return (c) this.f8253a.get(i8);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final m o(c0 c0Var) {
        j(c0Var);
        return new m(t(c0Var), new HashMap());
    }

    public static b.a p(w wVar) {
        return new b.a(wVar, null);
    }

    public static final m q(k kVar) {
        return r(kVar.d());
    }

    public static final m r(w wVar) {
        return A().b(p(wVar).j().i()).c();
    }

    private static List t(c0 c0Var) {
        h iVar;
        boolean z7;
        ArrayList arrayList = new ArrayList(c0Var.a0());
        for (c0.c cVar : c0Var.b0()) {
            int a02 = cVar.a0();
            try {
                iVar = E(cVar);
                z7 = false;
            } catch (GeneralSecurityException e8) {
                if (n4.a.f9762a.getValue()) {
                    throw e8;
                }
                iVar = new t4.i(G(cVar), g.a());
                z7 = true;
            }
            if (n4.a.f9762a.getValue() && !z(cVar.c0())) {
                throw new GeneralSecurityException("Parsing of a single key failed (wrong status) and Tink is configured via validateKeysetsOnParsing to reject such keysets.");
            }
            arrayList.add(new c(iVar, cVar.c0(), a02, a02 == c0Var.c0(), z7, c.f8269h, null));
        }
        return Collections.unmodifiableList(arrayList);
    }

    private m y() {
        m mVar = this.f8255c;
        return mVar == null ? this : mVar;
    }

    private static boolean z(y4.z zVar) {
        int i8 = a.f8256a[zVar.ordinal()];
        return i8 == 1 || i8 == 2 || i8 == 3;
    }

    public void J(r rVar, j4.a aVar, byte[] bArr) {
        rVar.b(m(u(), aVar, bArr));
    }

    @Override // j4.o
    public j4.b a(Class cls) {
        androidx.appcompat.app.w.a(this.f8254b.get(cls));
        return null;
    }

    @Override // j4.o
    /* renamed from: s */
    public c b(int i8) {
        if (i8 < 0 || i8 >= size()) {
            throw new IndexOutOfBoundsException("Invalid index " + i8 + " for keyset of size " + size());
        }
        return n(i8);
    }

    @Override // j4.o
    public int size() {
        return this.f8253a.size();
    }

    public String toString() {
        return v().toString();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public c0 u() {
        try {
            c0.b d02 = c0.d0();
            for (c cVar : this.f8253a) {
                d02.q(k(cVar.getKey(), cVar.f8271b, cVar.a()));
                if (cVar.g()) {
                    d02.r(cVar.a());
                }
            }
            return (c0) d02.h();
        } catch (GeneralSecurityException e8) {
            throw new j0(e8);
        }
    }

    public y4.d0 v() {
        return a0.b(u());
    }

    @Override // j4.o
    /* renamed from: w */
    public c c() {
        for (c cVar : this.f8253a) {
            if (cVar != null && cVar.g()) {
                if (cVar.b() == j.f8247b) {
                    return cVar;
                }
                throw new IllegalStateException("Keyset has primary which isn't enabled");
            }
        }
        throw new IllegalStateException("Keyset has no valid primary");
    }

    public Object x(e eVar, Class cls) {
        c0 u7 = y().u();
        a0.d(u7);
        for (int i8 = 0; i8 < size(); i8++) {
            if (((c) this.f8253a.get(i8)).f8275f || !z(((c) this.f8253a.get(i8)).f8271b)) {
                c0.c Z = u7.Z(i8);
                throw new GeneralSecurityException("Key parsing of key with index " + i8 + " and type_url " + Z.Z().a0() + " failed, unable to get primitive");
            }
        }
        return eVar.a(y(), cls);
    }

    /* synthetic */ m(List list, Map map, a aVar) {
        this(list, map);
    }
}
