package n1;

import android.graphics.Paint;
import android.text.style.ReplacementSpan;
import androidx.emoji2.text.g;
/* loaded from: classes.dex */
public abstract class b extends ReplacementSpan {

    /* renamed from: b  reason: collision with root package name */
    private final g f9742b;

    /* renamed from: a  reason: collision with root package name */
    private final Paint.FontMetricsInt f9741a = new Paint.FontMetricsInt();

    /* renamed from: c  reason: collision with root package name */
    private short f9743c = -1;

    /* renamed from: d  reason: collision with root package name */
    private short f9744d = -1;

    /* renamed from: e  reason: collision with root package name */
    private float f9745e = 1.0f;

    /* JADX INFO: Access modifiers changed from: package-private */
    public b(g gVar) {
        g1.e.k(gVar, "metadata cannot be null");
        this.f9742b = gVar;
    }

    public final g a() {
        return this.f9742b;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final int b() {
        return this.f9743c;
    }

    @Override // android.text.style.ReplacementSpan
    public int getSize(Paint paint, CharSequence charSequence, int i8, int i9, Paint.FontMetricsInt fontMetricsInt) {
        paint.getFontMetricsInt(this.f9741a);
        Paint.FontMetricsInt fontMetricsInt2 = this.f9741a;
        this.f9745e = (Math.abs(fontMetricsInt2.descent - fontMetricsInt2.ascent) * 1.0f) / this.f9742b.e();
        this.f9744d = (short) (this.f9742b.e() * this.f9745e);
        short i10 = (short) (this.f9742b.i() * this.f9745e);
        this.f9743c = i10;
        if (fontMetricsInt != null) {
            Paint.FontMetricsInt fontMetricsInt3 = this.f9741a;
            fontMetricsInt.ascent = fontMetricsInt3.ascent;
            fontMetricsInt.descent = fontMetricsInt3.descent;
            fontMetricsInt.top = fontMetricsInt3.top;
            fontMetricsInt.bottom = fontMetricsInt3.bottom;
        }
        return i10;
    }
}
