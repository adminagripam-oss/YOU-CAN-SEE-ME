package androidx.appcompat.widget;

import android.content.res.TypedArray;
import android.text.InputFilter;
import android.util.AttributeSet;
import android.widget.TextView;
/* loaded from: classes.dex */
class n {

    /* renamed from: a  reason: collision with root package name */
    private final TextView f2394a;

    /* renamed from: b  reason: collision with root package name */
    private final p1.f f2395b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public n(TextView textView) {
        this.f2394a = textView;
        this.f2395b = new p1.f(textView, false);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public InputFilter[] a(InputFilter[] inputFilterArr) {
        return this.f2395b.a(inputFilterArr);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void b(AttributeSet attributeSet, int i8) {
        TypedArray obtainStyledAttributes = this.f2394a.getContext().obtainStyledAttributes(attributeSet, g.j.f6954g0, i8, 0);
        try {
            boolean z7 = obtainStyledAttributes.hasValue(g.j.f7024u0) ? obtainStyledAttributes.getBoolean(g.j.f7024u0, true) : true;
            obtainStyledAttributes.recycle();
            d(z7);
        } catch (Throwable th) {
            obtainStyledAttributes.recycle();
            throw th;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void c(boolean z7) {
        this.f2395b.b(z7);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void d(boolean z7) {
        this.f2395b.c(z7);
    }
}
