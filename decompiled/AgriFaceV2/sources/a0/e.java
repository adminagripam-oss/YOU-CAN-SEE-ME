package a0;

import a0.o0;
/* loaded from: classes.dex */
final class e extends o0.a {

    /* renamed from: a  reason: collision with root package name */
    private final l0.u f25a;

    /* renamed from: b  reason: collision with root package name */
    private final l0.u f26b;

    /* renamed from: c  reason: collision with root package name */
    private final int f27c;

    /* renamed from: d  reason: collision with root package name */
    private final int f28d;

    /* JADX INFO: Access modifiers changed from: package-private */
    public e(l0.u uVar, l0.u uVar2, int i8, int i9) {
        if (uVar == null) {
            throw new NullPointerException("Null edge");
        }
        this.f25a = uVar;
        if (uVar2 == null) {
            throw new NullPointerException("Null postviewEdge");
        }
        this.f26b = uVar2;
        this.f27c = i8;
        this.f28d = i9;
    }

    @Override // a0.o0.a
    l0.u a() {
        return this.f25a;
    }

    @Override // a0.o0.a
    int b() {
        return this.f27c;
    }

    @Override // a0.o0.a
    int c() {
        return this.f28d;
    }

    @Override // a0.o0.a
    l0.u d() {
        return this.f26b;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof o0.a) {
            o0.a aVar = (o0.a) obj;
            return this.f25a.equals(aVar.a()) && this.f26b.equals(aVar.d()) && this.f27c == aVar.b() && this.f28d == aVar.c();
        }
        return false;
    }

    public int hashCode() {
        return ((((((this.f25a.hashCode() ^ 1000003) * 1000003) ^ this.f26b.hashCode()) * 1000003) ^ this.f27c) * 1000003) ^ this.f28d;
    }

    public String toString() {
        return "In{edge=" + this.f25a + ", postviewEdge=" + this.f26b + ", inputFormat=" + this.f27c + ", outputFormat=" + this.f28d + "}";
    }
}
