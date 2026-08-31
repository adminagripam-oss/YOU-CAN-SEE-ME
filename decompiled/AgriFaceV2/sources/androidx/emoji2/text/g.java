package androidx.emoji2.text;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Typeface;
/* loaded from: classes.dex */
public class g {

    /* renamed from: d  reason: collision with root package name */
    private static final ThreadLocal f3310d = new ThreadLocal();

    /* renamed from: a  reason: collision with root package name */
    private final int f3311a;

    /* renamed from: b  reason: collision with root package name */
    private final l f3312b;

    /* renamed from: c  reason: collision with root package name */
    private volatile int f3313c = 0;

    /* JADX INFO: Access modifiers changed from: package-private */
    public g(l lVar, int i8) {
        this.f3312b = lVar;
        this.f3311a = i8;
    }

    private o1.a g() {
        ThreadLocal threadLocal = f3310d;
        o1.a aVar = (o1.a) threadLocal.get();
        if (aVar == null) {
            aVar = new o1.a();
            threadLocal.set(aVar);
        }
        this.f3312b.d().j(aVar, this.f3311a);
        return aVar;
    }

    public void a(Canvas canvas, float f8, float f9, Paint paint) {
        Typeface g8 = this.f3312b.g();
        Typeface typeface = paint.getTypeface();
        paint.setTypeface(g8);
        canvas.drawText(this.f3312b.c(), this.f3311a * 2, 2, f8, f9, paint);
        paint.setTypeface(typeface);
    }

    public int b(int i8) {
        return g().h(i8);
    }

    public int c() {
        return g().i();
    }

    public int d() {
        return this.f3313c;
    }

    public short e() {
        return g().k();
    }

    public int f() {
        return g().l();
    }

    public short h() {
        return g().m();
    }

    public short i() {
        return g().n();
    }

    public boolean j() {
        return g().j();
    }

    public void k(boolean z7) {
        this.f3313c = z7 ? 2 : 1;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString());
        sb.append(", id:");
        sb.append(Integer.toHexString(f()));
        sb.append(", codepoints:");
        int c8 = c();
        for (int i8 = 0; i8 < c8; i8++) {
            sb.append(Integer.toHexString(b(i8)));
            sb.append(" ");
        }
        return sb.toString();
    }
}
