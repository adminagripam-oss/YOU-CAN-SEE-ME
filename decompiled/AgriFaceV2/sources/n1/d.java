package n1;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.text.TextPaint;
import androidx.emoji2.text.g;
/* loaded from: classes.dex */
public final class d extends b {

    /* renamed from: f  reason: collision with root package name */
    private static Paint f9750f;

    public d(g gVar) {
        super(gVar);
    }

    private static Paint c() {
        if (f9750f == null) {
            TextPaint textPaint = new TextPaint();
            f9750f = textPaint;
            textPaint.setColor(androidx.emoji2.text.e.b().c());
            f9750f.setStyle(Paint.Style.FILL);
        }
        return f9750f;
    }

    @Override // android.text.style.ReplacementSpan
    public void draw(Canvas canvas, CharSequence charSequence, int i8, int i9, float f8, int i10, int i11, int i12, Paint paint) {
        if (androidx.emoji2.text.e.b().i()) {
            canvas.drawRect(f8, i10, f8 + b(), i12, c());
        }
        a().a(canvas, f8, i11, paint);
    }
}
