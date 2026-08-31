package l0;

import androidx.concurrent.futures.c;
import l0.t;
/* loaded from: classes.dex */
final class a extends t.b {

    /* renamed from: a  reason: collision with root package name */
    private final int f8820a;

    /* renamed from: b  reason: collision with root package name */
    private final int f8821b;

    /* renamed from: c  reason: collision with root package name */
    private final c.a f8822c;

    /* JADX INFO: Access modifiers changed from: package-private */
    public a(int i8, int i9, c.a aVar) {
        this.f8820a = i8;
        this.f8821b = i9;
        if (aVar == null) {
            throw new NullPointerException("Null completer");
        }
        this.f8822c = aVar;
    }

    @Override // l0.t.b
    c.a a() {
        return this.f8822c;
    }

    @Override // l0.t.b
    int b() {
        return this.f8820a;
    }

    @Override // l0.t.b
    int c() {
        return this.f8821b;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof t.b) {
            t.b bVar = (t.b) obj;
            return this.f8820a == bVar.b() && this.f8821b == bVar.c() && this.f8822c.equals(bVar.a());
        }
        return false;
    }

    public int hashCode() {
        return ((((this.f8820a ^ 1000003) * 1000003) ^ this.f8821b) * 1000003) ^ this.f8822c.hashCode();
    }

    public String toString() {
        return "PendingSnapshot{jpegQuality=" + this.f8820a + ", rotationDegrees=" + this.f8821b + ", completer=" + this.f8822c + "}";
    }
}
