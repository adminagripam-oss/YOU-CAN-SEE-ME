package b5;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class b0 extends b5.a {

    /* renamed from: a  reason: collision with root package name */
    private final Set f4624a;

    /* renamed from: b  reason: collision with root package name */
    private final Set f4625b;

    /* renamed from: c  reason: collision with root package name */
    private final Set f4626c;

    /* renamed from: d  reason: collision with root package name */
    private final Set f4627d;

    /* renamed from: e  reason: collision with root package name */
    private final Set f4628e;

    /* renamed from: f  reason: collision with root package name */
    private final Set f4629f;

    /* renamed from: g  reason: collision with root package name */
    private final d f4630g;

    /* loaded from: classes.dex */
    private static class a implements g5.b {

        /* renamed from: a  reason: collision with root package name */
        private final Set f4631a;

        /* renamed from: b  reason: collision with root package name */
        private final g5.b f4632b;

        public a(Set set, g5.b bVar) {
            this.f4631a = set;
            this.f4632b = bVar;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public b0(c cVar, d dVar) {
        HashSet hashSet = new HashSet();
        HashSet hashSet2 = new HashSet();
        HashSet hashSet3 = new HashSet();
        HashSet hashSet4 = new HashSet();
        HashSet hashSet5 = new HashSet();
        for (o oVar : cVar.c()) {
            if (oVar.d()) {
                boolean f8 = oVar.f();
                Class b8 = oVar.b();
                if (f8) {
                    hashSet4.add(b8);
                } else {
                    hashSet.add(b8);
                }
            } else if (oVar.c()) {
                hashSet3.add(oVar.b());
            } else {
                boolean f9 = oVar.f();
                Class b9 = oVar.b();
                if (f9) {
                    hashSet5.add(b9);
                } else {
                    hashSet2.add(b9);
                }
            }
        }
        if (!cVar.f().isEmpty()) {
            hashSet.add(g5.b.class);
        }
        this.f4624a = Collections.unmodifiableSet(hashSet);
        this.f4625b = Collections.unmodifiableSet(hashSet2);
        this.f4626c = Collections.unmodifiableSet(hashSet3);
        this.f4627d = Collections.unmodifiableSet(hashSet4);
        this.f4628e = Collections.unmodifiableSet(hashSet5);
        this.f4629f = cVar.f();
        this.f4630g = dVar;
    }

    @Override // b5.a, b5.d
    public Object a(Class cls) {
        if (this.f4624a.contains(cls)) {
            Object a8 = this.f4630g.a(cls);
            return !cls.equals(g5.b.class) ? a8 : new a(this.f4629f, (g5.b) a8);
        }
        throw new q(String.format("Attempting to request an undeclared dependency %s.", cls));
    }

    @Override // b5.a, b5.d
    public Set b(Class cls) {
        if (this.f4627d.contains(cls)) {
            return this.f4630g.b(cls);
        }
        throw new q(String.format("Attempting to request an undeclared dependency Set<%s>.", cls));
    }

    @Override // b5.d
    public h5.b c(Class cls) {
        if (this.f4625b.contains(cls)) {
            return this.f4630g.c(cls);
        }
        throw new q(String.format("Attempting to request an undeclared dependency Provider<%s>.", cls));
    }

    @Override // b5.d
    public h5.b d(Class cls) {
        if (this.f4628e.contains(cls)) {
            return this.f4630g.d(cls);
        }
        throw new q(String.format("Attempting to request an undeclared dependency Provider<Set<%s>>.", cls));
    }
}
