package androidx.core.graphics;

import android.graphics.Insets;
import android.graphics.Rect;
/* loaded from: classes.dex */
public final class f {

    /* renamed from: e  reason: collision with root package name */
    public static final f f2988e = new f(0, 0, 0, 0);

    /* renamed from: a  reason: collision with root package name */
    public final int f2989a;

    /* renamed from: b  reason: collision with root package name */
    public final int f2990b;

    /* renamed from: c  reason: collision with root package name */
    public final int f2991c;

    /* renamed from: d  reason: collision with root package name */
    public final int f2992d;

    /* loaded from: classes.dex */
    static class a {
        static Insets a(int i8, int i9, int i10, int i11) {
            return Insets.of(i8, i9, i10, i11);
        }
    }

    private f(int i8, int i9, int i10, int i11) {
        this.f2989a = i8;
        this.f2990b = i9;
        this.f2991c = i10;
        this.f2992d = i11;
    }

    public static f a(f fVar, f fVar2) {
        return b(Math.max(fVar.f2989a, fVar2.f2989a), Math.max(fVar.f2990b, fVar2.f2990b), Math.max(fVar.f2991c, fVar2.f2991c), Math.max(fVar.f2992d, fVar2.f2992d));
    }

    public static f b(int i8, int i9, int i10, int i11) {
        return (i8 == 0 && i9 == 0 && i10 == 0 && i11 == 0) ? f2988e : new f(i8, i9, i10, i11);
    }

    public static f c(Rect rect) {
        return b(rect.left, rect.top, rect.right, rect.bottom);
    }

    public static f d(Insets insets) {
        int i8;
        int i9;
        int i10;
        int i11;
        i8 = insets.left;
        i9 = insets.top;
        i10 = insets.right;
        i11 = insets.bottom;
        return b(i8, i9, i10, i11);
    }

    public Insets e() {
        return a.a(this.f2989a, this.f2990b, this.f2991c, this.f2992d);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || f.class != obj.getClass()) {
            return false;
        }
        f fVar = (f) obj;
        return this.f2992d == fVar.f2992d && this.f2989a == fVar.f2989a && this.f2991c == fVar.f2991c && this.f2990b == fVar.f2990b;
    }

    public int hashCode() {
        return (((((this.f2989a * 31) + this.f2990b) * 31) + this.f2991c) * 31) + this.f2992d;
    }

    public String toString() {
        return "Insets{left=" + this.f2989a + ", top=" + this.f2990b + ", right=" + this.f2991c + ", bottom=" + this.f2992d + '}';
    }
}
