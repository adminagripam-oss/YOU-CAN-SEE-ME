package b5;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
/* loaded from: classes.dex */
public final class c {

    /* renamed from: a  reason: collision with root package name */
    private final Set f4633a;

    /* renamed from: b  reason: collision with root package name */
    private final Set f4634b;

    /* renamed from: c  reason: collision with root package name */
    private final int f4635c;

    /* renamed from: d  reason: collision with root package name */
    private final int f4636d;

    /* renamed from: e  reason: collision with root package name */
    private final g f4637e;

    /* renamed from: f  reason: collision with root package name */
    private final Set f4638f;

    /* loaded from: classes.dex */
    public static class b {

        /* renamed from: a  reason: collision with root package name */
        private final Set f4639a;

        /* renamed from: b  reason: collision with root package name */
        private final Set f4640b;

        /* renamed from: c  reason: collision with root package name */
        private int f4641c;

        /* renamed from: d  reason: collision with root package name */
        private int f4642d;

        /* renamed from: e  reason: collision with root package name */
        private g f4643e;

        /* renamed from: f  reason: collision with root package name */
        private Set f4644f;

        private b(Class cls, Class... clsArr) {
            HashSet hashSet = new HashSet();
            this.f4639a = hashSet;
            this.f4640b = new HashSet();
            this.f4641c = 0;
            this.f4642d = 0;
            this.f4644f = new HashSet();
            a0.c(cls, "Null interface");
            hashSet.add(cls);
            for (Class cls2 : clsArr) {
                a0.c(cls2, "Null interface");
            }
            Collections.addAll(this.f4639a, clsArr);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public b e() {
            this.f4642d = 1;
            return this;
        }

        private void f(Class cls) {
            a0.a(!this.f4639a.contains(cls), "Components are not allowed to depend on interfaces they themselves provide.");
        }

        public b b(o oVar) {
            a0.c(oVar, "Null dependency");
            f(oVar.b());
            this.f4640b.add(oVar);
            return this;
        }

        public c c() {
            a0.d(this.f4643e != null, "Missing required property: factory.");
            return new c(new HashSet(this.f4639a), new HashSet(this.f4640b), this.f4641c, this.f4642d, this.f4643e, this.f4644f);
        }

        public b d(g gVar) {
            this.f4643e = (g) a0.c(gVar, "Null factory");
            return this;
        }
    }

    private c(Set set, Set set2, int i8, int i9, g gVar, Set set3) {
        this.f4633a = Collections.unmodifiableSet(set);
        this.f4634b = Collections.unmodifiableSet(set2);
        this.f4635c = i8;
        this.f4636d = i9;
        this.f4637e = gVar;
        this.f4638f = Collections.unmodifiableSet(set3);
    }

    public static b a(Class cls) {
        return new b(cls, new Class[0]);
    }

    public static b b(Class cls, Class... clsArr) {
        return new b(cls, clsArr);
    }

    public static b g(Class cls) {
        return a(cls).e();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ Object k(Object obj, d dVar) {
        return obj;
    }

    public static c l(Object obj, Class cls, Class... clsArr) {
        return b(cls, clsArr).d(b5.b.b(obj)).c();
    }

    public Set c() {
        return this.f4634b;
    }

    public g d() {
        return this.f4637e;
    }

    public Set e() {
        return this.f4633a;
    }

    public Set f() {
        return this.f4638f;
    }

    public boolean h() {
        return this.f4635c == 1;
    }

    public boolean i() {
        return this.f4635c == 2;
    }

    public boolean j() {
        return this.f4636d == 0;
    }

    public String toString() {
        return "Component<" + Arrays.toString(this.f4633a.toArray()) + ">{" + this.f4635c + ", type=" + this.f4636d + ", deps=" + Arrays.toString(this.f4634b.toArray()) + "}";
    }
}
