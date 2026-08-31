package a0;

import a0.u;
import android.util.Size;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class b extends u.c {

    /* renamed from: d  reason: collision with root package name */
    private final Size f5d;

    /* renamed from: e  reason: collision with root package name */
    private final int f6e;

    /* renamed from: f  reason: collision with root package name */
    private final int f7f;

    /* renamed from: g  reason: collision with root package name */
    private final boolean f8g;

    /* renamed from: h  reason: collision with root package name */
    private final Size f9h;

    /* renamed from: i  reason: collision with root package name */
    private final int f10i;

    /* renamed from: j  reason: collision with root package name */
    private final l0.u f11j;

    /* renamed from: k  reason: collision with root package name */
    private final l0.u f12k;

    /* JADX INFO: Access modifiers changed from: package-private */
    public b(Size size, int i8, int i9, boolean z7, y.b1 b1Var, Size size2, int i10, l0.u uVar, l0.u uVar2) {
        if (size == null) {
            throw new NullPointerException("Null size");
        }
        this.f5d = size;
        this.f6e = i8;
        this.f7f = i9;
        this.f8g = z7;
        this.f9h = size2;
        this.f10i = i10;
        if (uVar == null) {
            throw new NullPointerException("Null requestEdge");
        }
        this.f11j = uVar;
        if (uVar2 == null) {
            throw new NullPointerException("Null errorEdge");
        }
        this.f12k = uVar2;
    }

    @Override // a0.u.c
    l0.u b() {
        return this.f12k;
    }

    @Override // a0.u.c
    y.b1 c() {
        return null;
    }

    @Override // a0.u.c
    int d() {
        return this.f6e;
    }

    @Override // a0.u.c
    int e() {
        return this.f7f;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof u.c) {
            u.c cVar = (u.c) obj;
            if (this.f5d.equals(cVar.j()) && this.f6e == cVar.d() && this.f7f == cVar.e() && this.f8g == cVar.l()) {
                cVar.c();
                Size size = this.f9h;
                if (size != null ? size.equals(cVar.g()) : cVar.g() == null) {
                    if (this.f10i == cVar.f() && this.f11j.equals(cVar.i()) && this.f12k.equals(cVar.b())) {
                        return true;
                    }
                }
            }
            return false;
        }
        return false;
    }

    @Override // a0.u.c
    int f() {
        return this.f10i;
    }

    @Override // a0.u.c
    Size g() {
        return this.f9h;
    }

    public int hashCode() {
        int hashCode = (((((((((this.f5d.hashCode() ^ 1000003) * 1000003) ^ this.f6e) * 1000003) ^ this.f7f) * 1000003) ^ (this.f8g ? 1231 : 1237)) * 1000003) ^ 0) * 1000003;
        Size size = this.f9h;
        return ((((((hashCode ^ (size != null ? size.hashCode() : 0)) * 1000003) ^ this.f10i) * 1000003) ^ this.f11j.hashCode()) * 1000003) ^ this.f12k.hashCode();
    }

    @Override // a0.u.c
    l0.u i() {
        return this.f11j;
    }

    @Override // a0.u.c
    Size j() {
        return this.f5d;
    }

    @Override // a0.u.c
    boolean l() {
        return this.f8g;
    }

    public String toString() {
        return "In{size=" + this.f5d + ", inputFormat=" + this.f6e + ", outputFormat=" + this.f7f + ", virtualCamera=" + this.f8g + ", imageReaderProxyProvider=" + ((Object) null) + ", postviewSize=" + this.f9h + ", postviewImageFormat=" + this.f10i + ", requestEdge=" + this.f11j + ", errorEdge=" + this.f12k + "}";
    }
}
