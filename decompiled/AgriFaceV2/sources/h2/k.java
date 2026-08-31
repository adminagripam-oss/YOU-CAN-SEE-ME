package h2;

import android.graphics.Rect;
import androidx.core.view.b1;
/* loaded from: classes.dex */
public final class k {

    /* renamed from: a  reason: collision with root package name */
    private final e2.b f7211a;

    /* renamed from: b  reason: collision with root package name */
    private final b1 f7212b;

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public k(Rect rect, b1 b1Var) {
        this(new e2.b(rect), b1Var);
        d7.k.e(rect, "bounds");
        d7.k.e(b1Var, "insets");
    }

    public final Rect a() {
        return this.f7211a.f();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (d7.k.a(k.class, obj != null ? obj.getClass() : null)) {
            d7.k.c(obj, "null cannot be cast to non-null type androidx.window.layout.WindowMetrics");
            k kVar = (k) obj;
            return d7.k.a(this.f7211a, kVar.f7211a) && d7.k.a(this.f7212b, kVar.f7212b);
        }
        return false;
    }

    public int hashCode() {
        return (this.f7211a.hashCode() * 31) + this.f7212b.hashCode();
    }

    public String toString() {
        return "WindowMetrics( bounds=" + this.f7211a + ", windowInsetsCompat=" + this.f7212b + ')';
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public /* synthetic */ k(android.graphics.Rect r1, androidx.core.view.b1 r2, int r3, d7.g r4) {
        /*
            r0 = this;
            r3 = r3 & 2
            if (r3 == 0) goto L12
            androidx.core.view.b1$b r2 = new androidx.core.view.b1$b
            r2.<init>()
            androidx.core.view.b1 r2 = r2.a()
            java.lang.String r3 = "Builder().build()"
            d7.k.d(r2, r3)
        L12:
            r0.<init>(r1, r2)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: h2.k.<init>(android.graphics.Rect, androidx.core.view.b1, int, d7.g):void");
    }

    public k(e2.b bVar, b1 b1Var) {
        d7.k.e(bVar, "_bounds");
        d7.k.e(b1Var, "_windowInsetsCompat");
        this.f7211a = bVar;
        this.f7212b = b1Var;
    }
}
