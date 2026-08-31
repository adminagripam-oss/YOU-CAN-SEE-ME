package r0;

import r0.c1;
import y.g2;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class m extends c1 {

    /* renamed from: d  reason: collision with root package name */
    private final int f11089d;

    /* renamed from: e  reason: collision with root package name */
    private final c1.a f11090e;

    /* renamed from: f  reason: collision with root package name */
    private final g2.h f11091f;

    /* JADX INFO: Access modifiers changed from: package-private */
    public m(int i8, c1.a aVar, g2.h hVar) {
        this.f11089d = i8;
        if (aVar == null) {
            throw new NullPointerException("Null streamState");
        }
        this.f11090e = aVar;
        this.f11091f = hVar;
    }

    @Override // r0.c1
    public int a() {
        return this.f11089d;
    }

    @Override // r0.c1
    public g2.h b() {
        return this.f11091f;
    }

    @Override // r0.c1
    public c1.a c() {
        return this.f11090e;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof c1) {
            c1 c1Var = (c1) obj;
            if (this.f11089d == c1Var.a() && this.f11090e.equals(c1Var.c())) {
                g2.h hVar = this.f11091f;
                g2.h b8 = c1Var.b();
                if (hVar == null) {
                    if (b8 == null) {
                        return true;
                    }
                } else if (hVar.equals(b8)) {
                    return true;
                }
            }
            return false;
        }
        return false;
    }

    public int hashCode() {
        int hashCode = (((this.f11089d ^ 1000003) * 1000003) ^ this.f11090e.hashCode()) * 1000003;
        g2.h hVar = this.f11091f;
        return hashCode ^ (hVar == null ? 0 : hVar.hashCode());
    }

    public String toString() {
        return "StreamInfo{id=" + this.f11089d + ", streamState=" + this.f11090e + ", inProgressTransformationInfo=" + this.f11091f + "}";
    }
}
