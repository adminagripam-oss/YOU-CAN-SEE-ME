package e2;

import android.graphics.Rect;
/* loaded from: classes.dex */
public final class b {

    /* renamed from: a  reason: collision with root package name */
    private final int f6560a;

    /* renamed from: b  reason: collision with root package name */
    private final int f6561b;

    /* renamed from: c  reason: collision with root package name */
    private final int f6562c;

    /* renamed from: d  reason: collision with root package name */
    private final int f6563d;

    public b(int i8, int i9, int i10, int i11) {
        this.f6560a = i8;
        this.f6561b = i9;
        this.f6562c = i10;
        this.f6563d = i11;
        if (!(i8 <= i10)) {
            throw new IllegalArgumentException(("Left must be less than or equal to right, left: " + i8 + ", right: " + i10).toString());
        }
        if (i9 <= i11) {
            return;
        }
        throw new IllegalArgumentException(("top must be less than or equal to bottom, top: " + i9 + ", bottom: " + i11).toString());
    }

    public final int a() {
        return this.f6563d - this.f6561b;
    }

    public final int b() {
        return this.f6560a;
    }

    public final int c() {
        return this.f6561b;
    }

    public final int d() {
        return this.f6562c - this.f6560a;
    }

    public final boolean e() {
        return a() == 0 && d() == 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (d7.k.a(b.class, obj != null ? obj.getClass() : null)) {
            d7.k.c(obj, "null cannot be cast to non-null type androidx.window.core.Bounds");
            b bVar = (b) obj;
            return this.f6560a == bVar.f6560a && this.f6561b == bVar.f6561b && this.f6562c == bVar.f6562c && this.f6563d == bVar.f6563d;
        }
        return false;
    }

    public final Rect f() {
        return new Rect(this.f6560a, this.f6561b, this.f6562c, this.f6563d);
    }

    public int hashCode() {
        return (((((this.f6560a * 31) + this.f6561b) * 31) + this.f6562c) * 31) + this.f6563d;
    }

    public String toString() {
        return b.class.getSimpleName() + " { [" + this.f6560a + ',' + this.f6561b + ',' + this.f6562c + ',' + this.f6563d + "] }";
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public b(Rect rect) {
        this(rect.left, rect.top, rect.right, rect.bottom);
        d7.k.e(rect, "rect");
    }
}
