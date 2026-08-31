package androidx.emoji2.text;

import android.text.TextPaint;
import androidx.emoji2.text.e;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class d implements e.d {

    /* renamed from: b  reason: collision with root package name */
    private static final ThreadLocal f3273b = new ThreadLocal();

    /* renamed from: a  reason: collision with root package name */
    private final TextPaint f3274a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public d() {
        TextPaint textPaint = new TextPaint();
        this.f3274a = textPaint;
        textPaint.setTextSize(10.0f);
    }

    private static StringBuilder b() {
        ThreadLocal threadLocal = f3273b;
        if (threadLocal.get() == null) {
            threadLocal.set(new StringBuilder());
        }
        return (StringBuilder) threadLocal.get();
    }

    @Override // androidx.emoji2.text.e.d
    public boolean a(CharSequence charSequence, int i8, int i9, int i10) {
        StringBuilder b8 = b();
        b8.setLength(0);
        while (i8 < i9) {
            b8.append(charSequence.charAt(i8));
            i8++;
        }
        return androidx.core.graphics.g.a(this.f3274a, b8.toString());
    }
}
